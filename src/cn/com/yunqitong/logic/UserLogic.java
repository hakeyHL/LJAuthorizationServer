package cn.com.yunqitong.logic;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.yunqitong.domain.TAccount;
import cn.com.yunqitong.domain.TAccountExample;
import cn.com.yunqitong.domain.TMailing;
import cn.com.yunqitong.domain.TMailingExample;
import cn.com.yunqitong.domain.TValidate;
import cn.com.yunqitong.mapper.TAccountMapper;
import cn.com.yunqitong.mapper.TMailingMapper;
import cn.com.yunqitong.mapper.TValidateMapper;
import cn.com.yunqitong.util.AppSecurity;
import cn.com.yunqitong.util.DateHelper;
import cn.com.yunqitong.util.ErrorCode;
import cn.com.yunqitong.util.HttpsUtil;
import cn.com.yunqitong.util.IDGenerator;
import cn.com.yunqitong.util.PropertyFactory;
import cn.com.yunqitong.util.StringUtil;
import cn.com.yunqitong.util.VersionUtil;
import cn.com.yunqitong.vo.AccountVo;
import cn.com.yunqitong.vo.PhoneVo;
import cn.com.yunqitong.vo.PicVo;

/**
 * 项目名称：LJAuthorizationServer 类名称：UserLogic 创建人：huli 创建时间：2016-1-25 下午5:05:31
 */
@Service
public class UserLogic {
	protected Logger log = Logger.getLogger(UserLogic.class);
	@Autowired
	private TAccountMapper tAccountMapper;
	@Autowired
	private TValidateMapper tValidateMapper;
	@Autowired
	private TMailingMapper tMailingMapper;

	/**
	 * 注册/登录--获取短信验证码
	 * 
	 * @param requestText
	 *            请求内容
	 * @return 处理结果
	 */
	public String login(String requestText) {
		/*
		 * 1.验证手机号是否合法 2.查询账户是否存在 3.不存在则添加 4.存在则查询该账号下是否存在验证码 5.没有则生成
		 * 5.有则判断是否已经过期(5分钟) 6.未过期则取出 7.过期则取用新验证码 8.设置有效期,将验证信息存入库中
		 * 9.请求短信服务系统向用户发送短信验证码 10.获取响应结果并告知客户端
		 */
		log.info("enter login method success ");
		JSONObject requestJson = null;
		JSONObject loginJObject = new JSONObject();
		try {
			requestJson = JSONObject.fromObject(requestText);
		} catch (Exception e) {
			log.info("Json数据格式有误");
			loginJObject.put("errorcode", "20005");
			loginJObject.put("msg", "传递json格式错误");
			return loginJObject.toString();
		}
		log.info("check version start ");
		JSONObject standVersionCheck = VersionUtil
				.standVersionCheck(requestJson.optString("version"));
		if (!standVersionCheck.optString("errorcode").equals("00000")) {
			loginJObject.put("errorcode",
					standVersionCheck.optString("errorcode"));
			loginJObject.put("msg", standVersionCheck.optString("msg"));
			return loginJObject.toString();
		}
		String account = requestJson.optString("account");
		log.info("account " + account);
		String timestamp = requestJson.optString("timestamp");
		String authenticator = requestJson.optString("authenticator");
		Map<String, String> map = AppSecurity.valid(timestamp, authenticator);
		if (!map.get(AppSecurity.code).equals(AppSecurity.success)) {
			if (map.get("code").equals("20025")) {
				// 说明是时间戳过期
				loginJObject.put("errorcode", map.get("code"));
				loginJObject.put("msg", map.get("msg"));
				return loginJObject.toString();
			}
			loginJObject.put("errorcode", "20005");
			loginJObject.put("msg", "认证失败");
			return loginJObject.toString();
		}
		// 将时间戳转为日期对象用于比较如果已经存在验证码是否过期
		Date userDate = DateHelper.transferFormat2Date(timestamp);
		log.info("check phone " + account + " start");
		Pattern pattern = Pattern.compile("^1\\d{10}$");
		Matcher matcher = pattern.matcher(account);
		boolean matches = matcher.matches();
		if (!matches) {
			loginJObject.put("errorcode", "20000");
			loginJObject.put("msg", "请正确填手机号");
			return loginJObject.toString();
		}
		log.info("check phone pass... ");

		log.info("query code by name ");
		TValidate tValidate = tValidateMapper.selectByPrimaryKey(account);
		// Long code = Long.valueOf(0);
		Long code = Long.valueOf(8888);
		// Random ne = new Random();// 实例化一个random的对象ne
		// code = (long) (ne.nextInt(9999 - 1000 + 1) + 1000);//
		// 为变量赋随机值1000-9999
		log.info("generate new code " + code + "");
		if (tValidate != null) {
			// 存在则取出
			log.info("code aleady exist " + tValidate.getCode());
			// 有则判断是否已经过期
			String expireTime = tValidate.getExpiretime();
			Date expireDate = DateHelper.transferFormat2Date(expireTime);
			int comResult = userDate.compareTo(expireDate);
			if (comResult != 1) {
				// 若未过期,取出此验证码
				code = tValidate.getCode();
				log.info("unexpired....");
			} else {
				// 若已过期则删除后添加
				tValidateMapper.deleteByPrimaryKey(account);
				log.info("expired ,insert new code ...");
				TValidate record1 = new TValidate();
				record1.setCode(code);
				record1.setPhone(account);
				Date date = new Date();
				date.setTime(date.getTime() + 5 * 60 * 1000);
				record1.setExpiretime(new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss").format(date));
				try {
					tValidateMapper.insert(record1);
				} catch (Exception e) {
					log.error("将新的验证信息插入库中异常" + e.getMessage(), e);
					loginJObject.put("errorcode", "20022");
					loginJObject.put("msg", "获取验证码失败请重新获取");
					return loginJObject.toString();
				}
			}
		} else {
			// 不存在则存入新信息
			log.info("insert new data to tvalidate ");
			TValidate record = new TValidate();
			record.setCode(code);
			record.setPhone(account);
			record.setExpiretime(DateHelper.setCurrentMinutesDelay(new Date(),
					5));
			try {
				tValidateMapper.insert(record);
			} catch (Exception e) {
				log.error("将新的验证信息插入库中异常" + e.getMessage(), e);
				loginJObject.put("errorcode", "20022");
				loginJObject.put("msg", "获取验证码失败请重新获取");
				return loginJObject.toString();
			}
		}
		log.info("request messageserver send message to client ...");
		JSONObject req2MessageServer = new JSONObject();
		req2MessageServer.put("phone", account);
		req2MessageServer.put("content1", code + "");
		req2MessageServer.put("content2", 5 + "");
		req2MessageServer.put("optype", 1);
		req2MessageServer.put("mainbody", 2);
		String url = PropertyFactory.getProperty("MessageServerAddr");
		log.info("url " + url);
		try {
			log.info("request text " + req2MessageServer.toString());
			String messageServerResponse = HttpsUtil.doPost(url
					+ "/message/send", req2MessageServer.toString());
			log.info("response text " + messageServerResponse);
			if (messageServerResponse == null) {
				log.error("向messageServer 请求发送短信返回了null");
				loginJObject.put("errorcode", "20008");
				loginJObject.put("msg", "获取验证码失败,请稍后重试");
				return loginJObject.toString();
			}
			JSONObject JsonObjectResponseFromMeserver = JSONObject
					.fromObject(messageServerResponse);
			if (!JsonObjectResponseFromMeserver.optString("errorcode").equals(
					"00000")) {
				// 失败
				log.error("向messageServer 请求发送短信失败");
				loginJObject.put("errorcode", "20008");
				loginJObject.put("msg", "获取验证码失败请重新获取");
				return loginJObject.toString();
			}
		} catch (Exception e) {
			log.error("向messageServer 请求发送短信异常");
			loginJObject.put("errorcode", "20008");
			loginJObject.put("msg", "获取验证码失败请重新获取");
			return loginJObject.toString();
		}
		loginJObject.put("errorcode", "00000");
		loginJObject.put("msg", "获取成功");
		return loginJObject.toString();
	}

	/**
	 * 验证短信验证码
	 * 
	 * @param requestText
	 * @return
	 */
	public String validateCode(String requestText) {
		// 1.校验手机号是否合法
		// 2.查询账户是否有获取记录
		// 3.若不存在则提示 "请先点击获取验证码"
		// 4.存在查询验证码是否过期
		// 5.已过期则直接返回"验证码已过期"
		// 6.未过期则取出与客户端提交验证码进行比对
		// 7.不相等则直接返回"验证码错误"
		// 8.一致则查询此账户是否已经存在
		// 9.不存在则添加该用户,调用bss开户
		// 10.赠送分钟.生成新的token
		// 11.存在则更新用户登录token
		// 12.查询用户上次登录平台并推送当前最新token
		// 13.保存当前登录平台
		// 14.将头像地址、铃声地址、铃声名称、昵称、token、accountid返回
		// 15.其他失败情况,依具体情形返回具体信息。

		JSONObject valicodeJson = null;
		JSONObject valicodeResJson = new JSONObject();
		log.info("enter valicode method ...");
		try {
			valicodeJson = JSONObject.fromObject(requestText);
		} catch (Exception e) {
			log.info("Json数据格式有误");
			valicodeJson.put("errorcode", "20005");
			valicodeJson.put("msg", "传递json格式错误");
			return valicodeJson.toString();
		}
		String name = valicodeJson.optString("account");
		if (name.equals("")) {
			valicodeResJson.put("errorcode", "20000");
			valicodeResJson.put("msg", "验证码错误，请先获取验证码！");
			return valicodeResJson.toString();
		}
		String deviceinfo = valicodeJson.optString("terminalUUID");
		String pushid = valicodeJson.optString("pushUUID");
		/*if (StringUtil.isEmpty(pushid)) {
			valicodeResJson.put("errorcode", "20000");
			valicodeResJson.put("msg", "推送标识不能为空！");
			return valicodeResJson.toString();
		}*/
		if (StringUtil.isEmpty(deviceinfo)) {
			valicodeResJson.put("errorcode", "20000");
			valicodeResJson.put("msg", "设备信息不能为空！");
			return valicodeResJson.toString();
		}
		String platform = valicodeJson.optString("platform");
		String version = valicodeJson.optString("version");
		log.info("check version start ");
		JSONObject standVersionCheck = VersionUtil.standVersionCheck(version);
		if (!standVersionCheck.optString("errorcode").equals("00000")) {
			valicodeResJson.put("errorcode",
					standVersionCheck.optString("errorcode"));
			valicodeResJson.put("msg", standVersionCheck.optString("msg"));
			return valicodeResJson.toString();
		}
		Long code = valicodeJson.optLong("validateCode");
		log.info("account " + name);
		log.info("code " + code);
		String token = IDGenerator.getId();
		log.info("new token .." + token);
		Map<String, String> map = AppSecurity.valid(
				valicodeJson.optString("timestamp"),
				valicodeJson.optString("authenticator"));
		if (!map.get(AppSecurity.code).equals(AppSecurity.success)) {
			if (map.get("code").equals("20025")) {
				// 说明是时间戳过期
				valicodeResJson.put("errorcode", map.get("code"));
				valicodeResJson.put("msg", map.get("msg"));
				return valicodeResJson.toString();
			}
			valicodeResJson.put("errorcode", "20005");
			valicodeResJson.put("msg", "认证失败");
			return valicodeResJson.toString();
		}
		log.info("check phone " + name + " start");
		Pattern pattern = Pattern.compile("^1\\d{10}$");
		Matcher matcher = pattern.matcher(name);
		boolean matches = matcher.matches();
		if (!matches) {
			valicodeResJson.put("errorcode", "20000");
			valicodeResJson.put("msg", "请正确填写手机号");
			return valicodeResJson.toString();
		}

		log.info("check phone pass... ");
		TValidate tValidate = tValidateMapper.selectByPrimaryKey(name);
		if (tValidate == null) {
			// 不存在获取验证码记录,直接返回
			valicodeResJson.put("errorcode", "20000");
			valicodeResJson.put("msg", "验证码错误，请先获取验证码！");
			return valicodeResJson.toString();
		} else {
			// 存在则判断其是否过期
			Date userDate = DateHelper.transferFormat2Date(valicodeJson
					.optString("timestamp"));
			String expireTime = tValidate.getExpiretime();
			Date expireDate = DateHelper.transferFormat2Date(expireTime);
			int comResult = userDate.compareTo(expireDate);
			if (comResult != 1) {
				log.info("code is unexpired");
				// 若未过期,取出验证码进行比对
				if (!code.equals(tValidate.getCode())) {
					log.info("not match ...");
					valicodeResJson.put("errorcode", "20000");
					valicodeResJson.put("msg", "验证码错误");
					return valicodeResJson.toString();
				}
			} else {
				log.info("expired....");
				// 若已过期则删除记录,然后返回过期
				tValidateMapper.deleteByPrimaryKey(name);
				valicodeResJson.put("errorcode", "20000");
				valicodeResJson.put("msg", "验证码已过期，请重新获取验证码！");
				return valicodeResJson.toString();
			}
		}
		// 若验证码验证通过,查询用户是否存在
		TAccount account = new TAccount();
		TAccountExample accountExample = new TAccountExample();
		accountExample.createCriteria().andAccountEqualTo(name);
		List<TAccount> accountList = null;
		try {
			accountList = tAccountMapper.selectByExample(accountExample);
		} catch (Exception e) {
			log.error("查询用户异常 " + e.getMessage());
			valicodeResJson.put("errorcode", "20022");
			valicodeResJson.put("msg", "验证失败,请稍后重试");
			return valicodeResJson.toString();
		}
		if (!(accountList != null && accountList.size() > 0)) {
			log.info("query result " + accountList.size());
			// 如果查询不到即为注册,添加用户到库中
			account = new TAccount();
			account.setAccountid(IDGenerator.getId());
			account.setAccount(name);
			String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(new Date());
			account.setCreatetime(currentTime);
			account.setDeviceinfo(deviceinfo);
			account.setNickname(name);
			account.setPlateform(platform);
			if(pushid.equals("")){
				pushid="0";
			}
			account.setPushid(pushid);
			account.setToken(token);
			account.setPicuptime(currentTime);
			account.setRinguptime(currentTime);
			account.setPicurl(PropertyFactory.getProperty("DEFAULTPICURL"));
			// 将用户信息插入库中之前检查此pushid是否已经归属其他用户
			log.info("check pushid if already exit ");
			TAccountExample pushExample = new TAccountExample();
			pushExample.createCriteria().andPushidEqualTo(pushid);
			List<TAccount> pushidlist = tAccountMapper
					.selectByExample(pushExample);
			if (pushidlist != null && pushidlist.size() > 0) {
				// 有,全部将其置为空
				log.info(" already exit ");
				for (TAccount pushidAccount : pushidlist) {
					pushidAccount.setPushid(null);
					try {
						int count = tAccountMapper
								.updateByPrimaryKeySelective(pushidAccount);
						if (count > 0) {
							log.info(" set " + pushidAccount.getNickname()
									+ " pushid is null ,successful ");
						} else {
							log.info(" set " + pushidAccount.getNickname()
									+ " pushid is null ,failed ");
							valicodeResJson.put("errorcode", "20022");
							valicodeResJson.put("msg", "验证失败,请稍后重试");
							return valicodeResJson.toString();
						}
					} catch (Exception e) {
						log.error("更新  " + pushidAccount.getNickname()
								+ " 的pushid 为空发生异常 ");
						valicodeResJson.put("errorcode", "20022");
						valicodeResJson.put("msg", "验证失败,请稍后重试");
						return valicodeResJson.toString();
					}
				}
			}

			try {
				log.info(name + "为新用户将其加入库中...");
				int count = tAccountMapper.insertSelective(account);
				if (count > 0) {
					log.info("向库中插入用户" + name + "成功");
					// 1.调用bss开户
					log.info("调用bss开户...");
					JSONObject toBss = new JSONObject();
					toBss.put("accountId", account.getAccountid());
					toBss.put("asId", "1");
					toBss.put("name", name);
					String bsurl = PropertyFactory.getProperty("BSSADDR");
					log.info("url = " + bsurl);
					try {
						String requestJson = toBss.toString();
						log.info("request requestJson" + requestJson);
						String textEntity = HttpsUtil.doPost(bsurl
								+ "/user/create", requestJson);
						log.info("response textEntity = " + textEntity);
						if (textEntity == null) {
							log.error("调用bss开户返回了null ");
							valicodeResJson.put("errorcode", "21001");
							valicodeResJson.put("msg", "注册失败");
							// 失败就从库中删除此用户
							log.info("向bss开户失败,从库中删除刚刚添加的用户");
							tAccountMapper.deleteByPrimaryKey(account
									.getAccountid());
							return valicodeResJson.toString();
						}
						JSONObject sendResult = JSONObject
								.fromObject(textEntity);
						if (!sendResult.optString("errorcode").equals("00000")) {
							// 如果BSS返回赠送失败
							valicodeResJson.put("errorcode",
									sendResult.optString("errorcode"));
							valicodeResJson.put("msg",
									sendResult.optString("msg"));
							return valicodeResJson.toString();
						}
					} catch (Exception ex) {
						log.error("调用bss开户异常" + ex.getMessage(), ex);
						valicodeResJson.put("errorcode", "21001");
						valicodeResJson.put("msg", "注册失败");
						// 失败就从库中删除此用户
						log.info("向bss开户失败,从库中删除刚刚添加的用户");
						tAccountMapper.deleteByPrimaryKey(account
								.getAccountid());
						return valicodeResJson.toString();
					}
					log.info("向bss 发起赠送 ...");
					// 2.发起赠送
					JSONObject jobj = new JSONObject();
					jobj.put("accountId", account.getAccountid());
					jobj.put("orderNum", IDGenerator.get32Random() + "");
					jobj.put("type", "1");
					jobj.put("maxNum", 10);
					jobj.put("count", 1000);
					jobj.put("orderMoney", 3000);

					jobj.put("adapterId", "2");
					jobj.put("asId", "1");
					jobj.put("operateType", 2);
					// 赠送是带有效期的续费

					// 向BSS赠送充值
					JSONObject sendResult = new JSONObject();
					String bssurl = PropertyFactory.getProperty("BSSADDR");
					log.info("url = " + bsurl);
					try {
						String requestJson = jobj.toString();
						log.info("request requestJson" + requestJson);
						String textEntity = HttpsUtil.doPost(bssurl
								+ "/product/order", requestJson);
						log.info("response textEntity = " + textEntity);
						if (textEntity == null) {
							valicodeResJson.put("errorcode", "21001");
							valicodeResJson.put("msg", "注册成功,赠送失败了");
							return valicodeResJson.toString();
						}
						sendResult = JSONObject.fromObject(textEntity);
						if (!sendResult.optString("errorcode").equals("00000")) {
							// 如果BSS返回赠送失败
							valicodeResJson.put("errorcode",
									sendResult.optString("errorcode"));
							valicodeResJson.put("msg",
									sendResult.optString("msg"));
							return valicodeResJson.toString();
						}
					} catch (Exception ex) {
						log.error("向BSS赠送时异常 " + ex.getMessage(), ex);
						valicodeResJson.put("errorcode", "21001");
						valicodeResJson.put("msg", "注册成功,赠送失败了");
						return valicodeResJson.toString();
					}
				} else {
					log.info("将用户添加至库中失败");
					valicodeResJson.put("errorcode", "20022");
					valicodeResJson.put("msg", "验证失败,请稍后重试");
					return valicodeResJson.toString();
				}
			} catch (Exception e) {
				log.error("添加用户异常" + e.getMessage(), e);
				valicodeResJson.put("errorcode", "20022");
				valicodeResJson.put("msg", "验证失败,请稍后重试");
				return valicodeResJson.toString();
			}
		} else {
			// 存在,则更新用户登录token,登录平台
			account = accountList.get(0);
			account.setToken(token);
			account.setPlateform(platform);
			account.setPushid(pushid.equals("")?"0":pushid);
			account.setDeviceinfo(deviceinfo);
			//account.setIsonline(1);
			try {
				int count = tAccountMapper.updateByPrimaryKeySelective(account);
				if (count < 1) {
					log.info("更新用户登录信息失败");
					valicodeResJson.put("errorcode", "20022");
					valicodeResJson.put("msg", "更新用户登录信息失败");
					return valicodeResJson.toString();
				}
			} catch (Exception e) {
				log.error("更新用户登录信息异常");
				valicodeResJson.put("errorcode", "20022");
				valicodeResJson.put("msg", "验证失败,请稍后重试");
				return valicodeResJson.toString();
			}
		}
		// 无错误,则通过 将地址、铃声地址、铃声名称、昵称、token、accountid返回
		valicodeResJson.put("nickname", account.getNickname());
		valicodeResJson.put("pic", account.getPicurl());
		valicodeResJson.put("ring", account.getRingurl());
		valicodeResJson.put("token", token);
		valicodeResJson.put("accountid", account.getAccountid());
		valicodeResJson.put("errorcode", "00000");
		valicodeResJson.put("msg", "验证通过");
		return valicodeResJson.toString();
	}

	/**
	 * 使用token和其他信息进行后台自动登录
	 * 
	 * @param requestText
	 * @return 2016-02-01 后台登录
	 */
	public String backLogin(String requestText) {
		// 手机号、token、设备标识、推送标识、platform、build、version、timestame、authenticator
		log.info("back login method " + requestText);
		JSONObject resJson = new JSONObject();
		JSONObject acceptJson = null;
		try {
			acceptJson = JSONObject.fromObject(requestText);
		} catch (Exception e) {
			log.info("Json数据格式有误");
			resJson.put("errorcode", "20005");
			resJson.put("msg", "传递json格式错误");
			return resJson.toString();
		}
		String account = acceptJson.optString("account");
		String token = acceptJson.optString("token");
		String deviceinfo = acceptJson.optString("terminalUUID");
		String pushid = acceptJson.optString("pushUUID");
		String platform = acceptJson.optString("platform");
		String timestamp = acceptJson.optString("timestamp");
		String authenticator = acceptJson.optString("authenticator");
		String version = acceptJson.optString("version");
		log.info("check version start ");
		JSONObject standVersionCheck = VersionUtil.standVersionCheck(version);
		if (!standVersionCheck.optString("errorcode").equals("00000")) {
			resJson.put("errorcode", standVersionCheck.optString("errorcode"));
			resJson.put("msg", standVersionCheck.optString("msg"));
			return resJson.toString();
		}
		Map<String, String> map = AppSecurity.valid(timestamp, authenticator);
		if (!map.get(AppSecurity.code).equals(AppSecurity.success)) {
			if (map.get("code").equals("20025")) {
				// 说明是时间戳过期
				resJson.put("errorcode", map.get("code"));
				resJson.put("msg", map.get("msg"));
				return resJson.toString();
			}
			resJson.put("errorcode", "20005");
			resJson.put("msg", "认证失败");
			return resJson.toString();
		}
		TAccountExample example = new TAccountExample();
		example.createCriteria().andAccountEqualTo(account);
		List<TAccount> accountList = tAccountMapper.selectByExample(example);
		if (!(accountList.size() > 0 && accountList != null)) {
			// 未查到
			resJson.put("errorcode", "20002");
			resJson.put("msg", "用户不存在");
			return resJson.toString();
		}
		TAccount tAccount = accountList.get(0);
		// 存在,则验证token和diviceinfo
		if (!tAccount.getDeviceinfo().equals(deviceinfo)) {
			// 若不相等
			log.info("设备已更换 ...");
			resJson.put("errorcode", "20002");
			resJson.put("msg", "登录信息已过期，请重新获取验证码登录");
			return resJson.toString();
		}
		if (!tAccount.getToken().equals(token)) {
			// 若不相等
			log.info("token 不匹配 ...");
			resJson.put("errorcode", "20002");
			resJson.put("msg", "登录信息已过期，请重新获取验证码登录");
			return resJson.toString();
		}
		if(pushid.equals("")){
			pushid="0";
		}
		tAccount.setPushid(pushid);
		tAccount.setPlateform(platform);
		tAccount.setToken(IDGenerator.getId());
		//tAccount.setIsonline(1);
		int count = tAccountMapper.updateByPrimaryKey(tAccount);
		if (count < 1) {
			log.info("更新用户pushid 、 platform 、token 失败");
		}
		resJson.put("errorcode", "00000");
		resJson.put("nickname", tAccount.getNickname());
		resJson.put("pic", tAccount.getPicurl());
		resJson.put("ring", tAccount.getRingurl());
		resJson.put("token", tAccount.getToken());
		resJson.put("msg", "登录成功");
		return resJson.toString();
	}

	/**
	 * 更改昵称
	 * @param reqText
	 * @return
	 */
	public String updateNickname(String reqText) {
		log.info("enter update nickname method ...");
		JSONObject resJson = new JSONObject();
		JSONObject fromObject = null;
		try {
			fromObject = JSONObject.fromObject(reqText);
		} catch (Exception e) {
			log.info("Json数据格式有误");
			resJson.put("errorcode", "20005");
			resJson.put("msg", "传递json格式错误");
			return resJson.toString();
		}
		String version = fromObject.optString("version");
		log.info("check version start ");
		JSONObject standVersionCheck = VersionUtil.standVersionCheck(version);
		if (!standVersionCheck.optString("errorcode").equals("00000")) {
			resJson.put("errorcode", standVersionCheck.optString("errorcode"));
			resJson.put("msg", standVersionCheck.optString("msg"));
			return resJson.toString();
		}
		String accountid = fromObject.optString("accountid");
		TAccount tAccount = tAccountMapper.selectByPrimaryKey(accountid);
		if (tAccount == null) {
			// 不存在
			resJson.put("errorcode", "20006");
			resJson.put("msg", "此用户不存在");
			return resJson.toString();
		}
		if (fromObject.optInt("actor") != 2) {
			// 移动端自己
			log.info("check token");
			if (!fromObject.optString("token").equals(tAccount.getToken())) {
				resJson.put("errorcode", "20002");
				resJson.put("msg", "登录信息已过期，请重新获取验证码登录");
				return resJson.toString();
			}
		}
		String nickname = fromObject.optString("nickname");
		Map<String, String> map = AppSecurity.valid(
				fromObject.optString("timestamp"),
				fromObject.optString("authenticator"));
		if (!map.get(AppSecurity.code).equals(AppSecurity.success)) {
			if (map.get("code").equals("20025")) {
				// 说明是时间戳过期
				resJson.put("errorcode", map.get("code"));
				resJson.put("msg", map.get("msg"));
				return resJson.toString();
			}
			resJson.put("errorcode", "20005");
			resJson.put("msg", "认证失败");
			return resJson.toString();
		}
		// 通知bss修改用户昵称
		JSONObject sendResult = new JSONObject();
		sendResult.put("accountId", tAccount.getAccountid());
		sendResult.put("asId", "1");
		sendResult.put("name", nickname);
		String bsurl = PropertyFactory.getProperty("BSSADDR");
		log.info("url = " + bsurl);
		try {
			String requestJson = sendResult.toString();
			log.info("request requestJson" + requestJson);
			String textEntity = HttpsUtil.doPost(bsurl + "/user/update",
					requestJson);
			log.info("response textEntity = " + textEntity);
			sendResult = JSONObject.fromObject(textEntity);
			if (!sendResult.optString("errorcode").equals("00000")) {
				// 如果BSS返回修改昵称
				log.info("请求bss更改昵称失败...");
				resJson.put("errorcode", "20001");
				resJson.put("msg", "更新昵称失败");
				return resJson.toString();
			}
		} catch (Exception ex) {
			log.error("向BSS修改昵称异常 " + ex.getMessage(), ex);
			resJson.put("errorcode", "20001");
			resJson.put("msg", "更新昵称失败");
			return resJson.toString();
		}
		// 修改本地库
		tAccount.setNickname(nickname);
		int count = tAccountMapper.updateByPrimaryKeySelective(tAccount);
		if (count < 1) {
			resJson.put("errorcode", "20022");
			resJson.put("msg", "更新昵称失败");
			return resJson.toString();
		}
		if (tAccount.getPlateform().equals("androidtv")) {
			String pushiUrl = PropertyFactory.getProperty("PushServerAddr");
			log.info("pushiUrl = " + pushiUrl);
			try {
				String requestJson = "{\"platform\":\""
						+ fromObject.optString("platform")
						+ "\",\"content\":{\"notifytype\":2},";
				requestJson += "\"clients\":[{\"clientid\":\""
						+ tAccount.getPushid() + "\",\"platform\":\""
						+ tAccount.getPlateform() + "\"}]}";
				log.info("request requestJson" + requestJson);
				String textEntity = HttpsUtil.doPost(pushiUrl + "mobile/push",
						requestJson);
				log.info("response textEntity = " + textEntity);
				JSONObject pushResult = JSONObject.fromObject(textEntity);
				if (!pushResult.optString("errorcode").equals("00000")) {
					log.info("昵称修改推送失败....");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				log.error("请求推送异常.. " + ex.getMessage(), ex);
				resJson.put("errorcode", "00000");
				resJson.put("msg", "昵称修改成功");
				return resJson.toString();
			}
		}

		resJson.put("errorcode", "00000");
		resJson.put("msg", "昵称修改成功");
		return resJson.toString();
	}

	/**
	 * 接收bss通知用户是否可用(开会)
	 * @param reqText
	 * @return
	 */
	public String updateUserEnable(String reqText) {
		log.info("bss notify to update user enable ");
		JSONObject fromObject = JSONObject.fromObject(reqText);
		JSONObject resJson = new JSONObject();

		try {
			resJson = JSONObject.fromObject(reqText);
		} catch (Exception e) {
			log.info("Json数据格式有误");
			resJson.put("errorcode", "20005");
			resJson.put("msg", "传递json格式错误");
			return resJson.toString();
		}

		String account = fromObject.optString("account");
		Long maxNum = Long.valueOf(fromObject.optString("capacity"));
		String enable = fromObject.optString("enable");
		TAccountExample accountExample = new TAccountExample();
		accountExample.createCriteria().andAccountidEqualTo(account);
		List<TAccount> accountList = tAccountMapper
				.selectByExample(accountExample);
		if (!(accountList.size() > 0 && accountList != null)) {
			// 未查询到返回此用户不存在
			resJson.put("errorcode", "20006");
			resJson.put("msg", "此用户不存在");
			return resJson.toString();
		}
		// 存在
		TAccount aAccount = accountList.get(0);
		aAccount.setEnable(enable);
		aAccount.setMaxnum(maxNum);
		int count = tAccountMapper.updateByPrimaryKey(aAccount);
		if (count < 1) {
			// 更新失败
			log.info("更新用户是否可用状态失败....");
			resJson.put("errorcode", "22002");
			resJson.put("msg", "更新失败");
			return resJson.toString();
		}
		resJson.put("errorcode", "00000");
		resJson.put("msg", "成功");
		return resJson.toString();
	}

	/**
	 * 查tv信息获取
	 * @param reqText
	 * @return
	 */
	/*public String getUserInfo(String reqText) {
		log.info("get userinfo start ...");
		AccountVo accountVo = new AccountVo();
		JSONObject fromObject = JSONObject.fromObject(reqText);
		JSONObject resJson = new JSONObject();
		log.info("req text " + reqText);
		try {
			resJson = JSONObject.fromObject(reqText);
		} catch (Exception e) {
			log.info("Json数据格式有误");
			resJson.put("errorcode", "20005");
			resJson.put("msg", "传递json格式错误");
			return resJson.toString();
		}

		String version = fromObject.optString("version");
		log.info("check version start ");
		JSONObject standVersionCheck = VersionUtil.standVersionCheck(version);
		if (!standVersionCheck.optString("errorcode").equals("00000")) {
			resJson.put("errorcode", standVersionCheck.optString("errorcode"));
			resJson.put("msg", standVersionCheck.optString("msg"));
			return resJson.toString();
		}

		String accountid = fromObject.optString("accountid");
		log.info("check token");
		TAccount tAccount = tAccountMapper.selectByPrimaryKey(accountid);
		if (tAccount == null) {
			// 不存在
			resJson.put("errorcode", "20006");
			resJson.put("msg", "此用户不存在");
			return resJson.toString();
		}
		int actor = fromObject.optInt("actor");
		if (actor != 2 && actor != 0) {
			// 移动端自己
			log.info("check token");
			if (!fromObject.optString("token").equals(tAccount.getToken())) {
				resJson.put("errorcode", "20002");
				resJson.put("msg", "登录信息已过期，请重新获取验证码登录");
				return resJson.toString();
			}
		}
		Map<String, String> map = AppSecurity.valid(
				fromObject.optString("timestamp"),
				fromObject.optString("authenticator"));
		if (!map.get(AppSecurity.code).equals(AppSecurity.success)) {
			if (map.get("code").equals("20025")) {
				// 说明是时间戳过期
				resJson.put("errorcode", map.get("code"));
				resJson.put("msg", map.get("msg"));
				return resJson.toString();
			}
			resJson.put("errorcode", "20005");
			resJson.put("msg", "认证失败");
			return resJson.toString();
		}

		List<PhoneVo> phoneList = tMailingMapper
				.selectUserMailingList(accountid);
		accountVo.setPhoneList(phoneList);
		accountVo.setPic(tAccount.getPicurl());
		accountVo.setNickname(tAccount.getNickname());
		accountVo.setErrorcode("00000");
		accountVo.setMsg("成功");

		JsonConfig jsonConfig = new JsonConfig();
		String excludes[] = new String[] { "accountId", "name", "type",
				"balance", "maxNum", "restAmount", "orderMoney", "price",
				"count", "operateType", "account" };
		jsonConfig.setExcludes(excludes);
		JSONObject userInfo = JSONObject.fromObject(accountVo, jsonConfig);

		return userInfo.toString();
	}*/

	/**
	 * 更新tv信息
	 * @param reqText
	 * @return
	 */
	@SuppressWarnings("all")
	public String updateUserInfo(HttpServletRequest request) {
		log.info("get userinfo start ...");
		byte[] fileBytes = null;
		byte[] buffer1 = new byte[1024];
		byte[] buffer = null;
		String sourceStr = null;
		InputStream in;
		try {
			in = request.getInputStream();
			int len = -1;
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			while ((len = in.read(buffer1)) != -1) {
				outputStream.write(buffer1, 0, len);
			}
			outputStream.close();
			in.close();
			buffer = outputStream.toByteArray();
			sourceStr = new String(buffer, "utf-8");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String message = "";
		String sepStr = "===uploadavatarpartdivder===";
		int index = sourceStr.indexOf(sepStr);
		String reqText = null;
		if (index == -1) {
			log.info("未传送图片....");
			reqText = HttpsUtil.getJsonFromRequest(request);
			log.info("accept json  " + reqText);
		} else {
			log.info("index = " + index);
			reqText = sourceStr.substring(0, index);
			message = sourceStr.substring(0, index + sepStr.length());
		}
		JSONObject fromObject = null;
		JSONObject resJson = new JSONObject();
		log.info("req text " + reqText);
		try {
			fromObject = JSONObject.fromObject(reqText);
		} catch (Exception e) {
			log.info("Json数据格式有误");
			resJson.put("errorcode", "20005");
			resJson.put("msg", "传递json格式错误");
			return resJson.toString();
		}

		String version = fromObject.optString("version");

		log.info("check version start ");
		JSONObject standVersionCheck = VersionUtil.standVersionCheck(version);
		if (!standVersionCheck.optString("errorcode").equals("00000")) {
			resJson.put("errorcode", standVersionCheck.optString("errorcode"));
			resJson.put("msg", standVersionCheck.optString("msg"));
			return resJson.toString();
		}

		String accountid = fromObject.optString("accountid");
		log.info("check token");
		TAccount tAccount = tAccountMapper.selectByPrimaryKey(accountid);
		if (tAccount == null) {
			// 不存在
			resJson.put("errorcode", "20006");
			resJson.put("msg", "此用户不存在");
			return resJson.toString();
		}

		Map<String, String> map = AppSecurity.valid(
				fromObject.optString("timestamp"),
				fromObject.optString("authenticator"));
		if (!map.get(AppSecurity.code).equals(AppSecurity.success)) {
			if (map.get("code").equals("20025")) { // 说明是时间戳过期
				resJson.put("errorcode", map.get("code"));
				resJson.put("msg", map.get("msg"));
				return resJson.toString();
			}
			resJson.put("errorcode", "20005");
			resJson.put("msg", "认证失败");
			return resJson.toString();
		}

		try {
			String nickname = fromObject.optString("nickname");
			if (nickname != null && !nickname.equals("")) {
				// 提交了昵称
				tAccount.setNickname(nickname);
				// sqlSession.update("update t_account set nickname='"+nickname+"' where accountid='"+tAccount.getAccountid()+"'");
				tAccountMapper.updateByPrimaryKeySelective(tAccount);
			}
			String phoneList = fromObject.optString("phoneList");
			if (phoneList != null && !phoneList.equals("")) {
				log.info("delete source mailing list ..");
				TMailingExample example = new TMailingExample();
				example.createCriteria().andMccountidEqualTo(accountid);
				int counts = tMailingMapper.deleteByExample(example);
				log.info(counts + " rows has deleted ,user  "
						+ tAccount.getNickname());
				// 提交了联系人组
				JSONArray clientJsonArray = null;
				try {
					clientJsonArray = JSONArray.fromObject(phoneList);
				} catch (Exception e) {
					log.info("添加联系人传递参数有误 ");
					resJson.put("errorcode", "20000");
					resJson.put("msg", "联系人传递参数格式有误");
					return resJson.toString();
				}
				List<PhoneVo> delPhoneList = (ArrayList<PhoneVo>) JSONArray
						.toCollection(clientJsonArray, PhoneVo.class);
				Iterator<PhoneVo> iterator = delPhoneList.iterator();
				while (iterator.hasNext()) {
					// 查询
					PhoneVo phoneVo = iterator.next();
					TAccountExample accountExample = new TAccountExample();
					accountExample.createCriteria().andAccountEqualTo(
							phoneVo.getPhone());
					List<TAccount> accountList = tAccountMapper
							.selectByExample(accountExample);
					TAccount account = null;
					if (accountList.size() > 0 && phoneVo != null) {
						account = accountList.get(0);
						TMailing record = new TMailing();
						record.setFaccountid(account.getAccountid());
						record.setMccountid(accountid);
						try {
							int count = tMailingMapper.insert(record);
							// int
							// count=sqlSession.insert("insert into t_mailing (mccountid,faccountid) values('"+accountid+"','"+account.getAccountid()+"')");
							if (count > 0) {
								log.info("添加 " + accountid + " 的好友"
										+ account.getAccountid() + "  "
										+ account.getNickname() + "  成功");
							} else {
								log.info("添加 " + accountid + " 的好友"
										+ account.getAccountid() + "  "
										+ account.getNickname() + "  失败");
							}
						} catch (Exception e) {
							// sqlSession.rollback();
							log.error("添加联系人出现了异常 " + e.getMessage());
							resJson.put("errorcode", "20022");
							resJson.put("msg", "同步信息异常,请稍后重试");
							return resJson.toString();
						}
					}
				}
			}
			// 更新头像
			PicVo vo = new PicVo();
			try {
				if (index != -1) {
					byte[] strBytes = message.getBytes("utf-8");
					log.info("-strBytes-------------------------: " + strBytes);
					fileBytes = new byte[buffer.length - strBytes.length];
					log.info("-fileBytes-------------------------: "
							+ fileBytes);
					System.arraycopy(buffer, strBytes.length, fileBytes, 0,
							fileBytes.length);

					message = message.substring(0, message.indexOf(sepStr))
							.trim();
					log.info("message = " + message);

					JSONObject jsonObject = JSONObject.fromObject(URLDecoder
							.decode(message));
					String imagename = IDGenerator.getId() + ".png";
					String timefile = DateHelper.formatDate(new Date(),
							"yyyyMMdd");
					String imagepath = PropertyFactory
							.getProperty("IMAGEPARTADDRESS") + timefile;

					String imageportpath = PropertyFactory
							.getProperty("IMAGEPARPOSTADDRESS") + timefile;
					// 保存图片文件
					String imageurl = PropertyFactory
							.getProperty("fileaddress")
							+ imageportpath
							+ File.separator + imagename;
					log.debug("imagename:" + imagename + ",imagepath:"
							+ imagepath + ",imageurl:" + imageurl);
					getFile(fileBytes, imagepath, imagename);

					String tableimagename = timefile + File.separator
							+ imagename;

					TAccount account = tAccountMapper
							.selectByPrimaryKey(accountid);
					account.setPicurl(tableimagename);
					account.setPicuptime(new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss").format(new Date()));
					/*
					 * int
					 * result=sqlSession.update("update t_account set picurl='"
					 * +tableimagename+"',"+"picuptime='"+new SimpleDateFormat(
					 * "yyyy-MM-dd HH:mm:ss").format(new Date())+"'");
					 */
					int result = tAccountMapper.updateByPrimaryKey(account);
					log.info("update use pic result = " + result);
					vo.setErrorcode(ErrorCode.SUCCESS);
					String url = PropertyFactory.getProperty("PICADDRESS")
							+ PropertyFactory
									.getProperty("IMAGEPARPOSTADDRESS") + "/"
							+ account.getPicurl();
					log.info("picture url .." + url);
				}
			} catch (Exception e) {
				log.error("更新用户头像异常...." + e.getMessage());
				resJson.put("errorcode", "20022");
				resJson.put("msg", "同步信息异常,请稍后重试");
				return resJson.toString();
			}
		} catch (Exception e) {
			log.error("更新tv信息异常...." + e.getMessage());
			resJson.put("errorcode", "20022");
			resJson.put("msg", "同步信息异常,请稍后重试");
			return resJson.toString();
		}
		log.info("push to tv to get latest info ");
		try {
			// 如果上次登录平台是androidTv才退送
			if (tAccount.getPlateform().equals("androidtv")) {
				String pushiUrl = PropertyFactory.getProperty("PushServerAddr");
				log.info("pushiUrl = " + pushiUrl);
				String requestJson = "{\"platform\":\""
						+ fromObject.optString("platform")
						+ "\",\"content\":{\"notifytype\":2},";
				requestJson += "\"clients\":[{\"clientid\":\""
						+ tAccount.getPushid() + "\",\"platform\":\""
						+ tAccount.getPlateform() + "\"}]}";
				log.info("request requestJson" + requestJson);
				String textEntity = HttpsUtil.doPost(pushiUrl + "mobile/push",
						requestJson);
				log.info("response textEntity = " + textEntity);
				if (textEntity == null) {
					log.error("请求推送tv端异常, ");
				}
				JSONObject resJsons = JSONObject.fromObject(textEntity);
				if (!resJsons.optString("errorcode").equals("00000")) {
					log.error("请求推送失败..push fail .!! ");
				} else {
					log.error("请求推送成功..push success .!! ");
				}
			}
		} catch (Exception e) {
			log.error("向tv推送获取最新信息异常...");
		}
		resJson.put("errorcode", "00000");
		resJson.put("msg", "同步成功");
		return resJson.toString();
	}

	public static void getFile(byte[] bfile, String filePath, String fileName) {
		// log.debug("bfile:" + Arrays.toString(bfile));
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			File dir = new File(filePath);
			if (!dir.exists()) {// 判断文件目录是否存在
				dir.mkdirs();
			}
			file = new File(filePath + File.separator + fileName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(bfile);
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * 更新用户是否在线
	 * @param request
	 * @return
	 */
	/*public String updateIsOnline(String reqText) {
		log.info("update user isonline start ...");
		JSONObject fromObject = JSONObject.fromObject(reqText);
		JSONObject resJson = new JSONObject();
		log.info("req text " + reqText);
		try {
			resJson = JSONObject.fromObject(reqText);
		} catch (Exception e) {
			log.info("Json数据格式有误");
			resJson.put("errorcode", "20005");
			resJson.put("msg", "传递json格式错误");
			return resJson.toString();
		}
		String version = fromObject.optString("version");
		log.info("check version start ");
		JSONObject standVersionCheck = VersionUtil.standVersionCheck(version);
		if (!standVersionCheck.optString("errorcode").equals("00000")) {
			resJson.put("errorcode", standVersionCheck.optString("errorcode"));
			resJson.put("msg", standVersionCheck.optString("msg"));
			return resJson.toString();
		}
		String accountid = fromObject.optString("accountid");
		log.info("check user is exist or not ...");
		TAccount tAccount = tAccountMapper.selectByPrimaryKey(accountid);
		if (tAccount == null) {
			// 不存在
			resJson.put("errorcode", "20006");
			resJson.put("msg", "此用户不存在");
			return resJson.toString();
		}
		Map<String, String> map = AppSecurity.valid(
				fromObject.optString("timestamp"),
				fromObject.optString("authenticator"));
		if (!map.get(AppSecurity.code).equals(AppSecurity.success)) {
			if (map.get("code").equals("20025")) {
				// 说明是时间戳过期
				resJson.put("errorcode", map.get("code"));
				resJson.put("msg", map.get("msg"));
				return resJson.toString();
			}
			resJson.put("errorcode", "20005");
			resJson.put("msg", "认证失败");
			return resJson.toString();
		}
		tAccount.setIsonline(0);
		try {
			int count = tAccountMapper.updateByPrimaryKey(tAccount);
			if (count > 0) {
				log.info("更改用户在线状态成功...");
			} else {
				log.error("更改用户在线状态成功...");
				resJson.put("errorcode", "20022");
				resJson.put("msg", "更新用户在线状态失败");
				return resJson.toString();
			}
		} catch (Exception e) {
			log.error("更改用户在线状态异常.." + e.getMessage(), e);
			resJson.put("errorcode", "20022");
			resJson.put("msg", "更新用户在线状态异常");
			return resJson.toString();
		}
		resJson.put("errorcode", "00000");
		resJson.put("msg", "成功");
		return resJson.toString();
	}*/
}
