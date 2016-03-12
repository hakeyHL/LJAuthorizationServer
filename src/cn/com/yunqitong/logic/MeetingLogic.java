package cn.com.yunqitong.logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.yunqitong.domain.TAccount;
import cn.com.yunqitong.domain.TAccountExample;
import cn.com.yunqitong.domain.TCallRecord;
import cn.com.yunqitong.domain.TCallRecordExample;
import cn.com.yunqitong.mapper.TAccountMapper;
import cn.com.yunqitong.mapper.TCallRecordMapper;
import cn.com.yunqitong.util.AppSecurity;
import cn.com.yunqitong.util.DateHelper;
import cn.com.yunqitong.util.HttpsUtil;
import cn.com.yunqitong.util.IDGenerator;
import cn.com.yunqitong.util.PropertyFactory;
import cn.com.yunqitong.util.VersionUtil;
import cn.com.yunqitong.vo.PhoneVo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 项目名称：LJAuthorizationServer 类名称：MeetingLogic 创建人：huli 创建时间：2016-2-2 上午11:41:36
 */
@Service
public class MeetingLogic {
	protected static Logger log = Logger.getLogger(MeetingLogic.class);
	@Autowired
	private TAccountMapper accountMapper;
	@Autowired
	private TCallRecordMapper callRecordMapper;

	/**
	 * @param json
	 * @return
	 */
	@SuppressWarnings("all")
	// 主叫
	public String calling(String json) {
		log.info("主叫呼叫: " + json);
		// 1.将加入会议信息转为json对象
		JSONObject obj = null;
		JSONObject result = new JSONObject();
		try {
			obj = JSONObject.fromObject(json);
		} catch (Exception e) {
			log.info("Json数据格式有误");
			result.put("errorcode", "20005");
			result.put("msg", "传递json格式错误");
			return result.toString();
		}
		String calledAccount = obj.optString("calledAccount");

		log.info("check version start ");
		String version = obj.optString("version");
		JSONObject standVersionCheck = VersionUtil.standVersionCheck(version);
		if (!standVersionCheck.optString("errorcode").equals("00000")) {
			result.put("errorcode", standVersionCheck.optString("errorcode"));
			result.put("msg", standVersionCheck.optString("msg"));
			return result.toString();
		}

		String accountid = obj.optString("accountid");
		log.info("check token");
		TAccount tAccount = accountMapper.selectByPrimaryKey(accountid);
		if (tAccount == null) {
			// 不存在
			result.put("errorcode", "20006");
			result.put("msg", "用户不存在");
			return result.toString();
		}
		if (!obj.optString("token").equals(tAccount.getToken())) {
			result.put("errorcode", "20002");
			result.put("msg", "登录信息已过期，请重新获取验证码登录");
			return result.toString();
		}
		// 2.合法性校验
		String timestamp = obj.optString("timestamp");
		String authenticator = obj.optString("authenticator");
		String platform = obj.optString("platform");
		Map<String, String> map = AppSecurity.valid(timestamp, authenticator);
		if (!map.get(AppSecurity.code).equals(AppSecurity.success)) {
			if (map.get("code").equals("20025")) {
				// 说明是时间戳过期
				result.put("errorcode", map.get("code"));
				result.put("msg", map.get("msg"));
				return result.toString();
			}
			result.put("errorcode", "20005");
			result.put("msg", "认证失败");
			return result.toString();
		}
		String meetingid = null;
		String hostid = null;
		String token = null;
		// 1、判断是否能开会
		if (tAccount.getEnable().equals("0")) {
			result.put("errorcode", "22000");
			result.put("msg", "当前帐号不能开会");
			return result.toString();
		}

		// 2、调用oss创建会议
		JSONObject meetingObj = getCreateMeetingObj(tAccount);
		String url = PropertyFactory.getProperty("OSSADDR");
		log.info("url = " + url);
		try {
			String requestJson = meetingObj.toString();
			log.info("request requestJson" + requestJson);
			String textEntity = HttpsUtil.doPost(url + "/meeting/create",
					requestJson);
			log.info("response textEntity = " + textEntity);
			if (textEntity == null) {
				log.error("向MediaServer创建会议,MediaServer返回了null ");
				result.put("errorcode", "23003");
				result.put("msg", "呼叫失败,请稍后重试");
				return result.toString();
			}
			JSONObject addToMeetingJsonResult = JSONObject
					.fromObject(textEntity);
			if (!addToMeetingJsonResult.optString("errorcode").equals("00000")) {
				// 如果MediaServer返回不可以
				result.put("errorcode",
						addToMeetingJsonResult.optString("errorcode"));
				result.put("msg", addToMeetingJsonResult.optString("msg"));
				return result.toString();
			}
			meetingid = addToMeetingJsonResult.optString("id");
			hostid = addToMeetingJsonResult.optString("host_id");
			token = addToMeetingJsonResult.optString("token");
		} catch (Exception ex) {
			ex.printStackTrace();
			log.info("创建会议失败= " + ex);
			result.put("errorcode", "23003");
			result.put("msg", "呼叫失败,请稍后重试");
			return result.toString();
		}
		// 5、把会议号返回给主叫用户
		result.put("errorcode", "00000");
		result.put("msg", "呼叫成功");
		result.put("meetingid", meetingid);
		result.put("hostid", hostid);
		result.put("token", token);
		return result.toString();
	}

	/**
	 * 呼叫被叫(向被叫推送)
	 * 
	 * @param json
	 * @return
	 */
	@SuppressWarnings("all")
	public String push2Called(String json) {
		log.info("主叫拉起会议后开始向被叫退送...." + json);
		// 1.将加入会议信息转为json对象
		JSONObject obj = null;
		JSONObject result = new JSONObject();
		try {
			obj = JSONObject.fromObject(json);
		} catch (Exception e) {
			log.info("Json数据格式有误");
			result.put("errorcode", "20005");
			result.put("msg", "传递json格式错误");
			return result.toString();
		}
		String calledAccount = obj.optString("calledAccount");
		String meetingid = obj.optString("meetingid");
		if (meetingid.equals("")) {
			result.put("errorcode", "2000");
			result.put("msg", "会议id不能为空");
			return result.toString();
		}
		log.info("check version start ");
		String version = obj.optString("version");
		JSONObject standVersionCheck = VersionUtil.standVersionCheck(version);
		if (!standVersionCheck.optString("errorcode").equals("00000")) {
			result.put("errorcode", standVersionCheck.optString("errorcode"));
			result.put("msg", standVersionCheck.optString("msg"));
			return result.toString();
		}
		String accountid = obj.optString("accountid");
		log.info("check token");
		TAccount tAccount = accountMapper.selectByPrimaryKey(accountid);
		if (tAccount == null) {
			// 不存在
			result.put("errorcode", "20006");
			result.put("msg", "用户不存在");
			return result.toString();
		}
		if (!obj.optString("token").equals(tAccount.getToken())) {
			result.put("errorcode", "20002");
			result.put("msg", "登录信息已过期，请重新获取验证码登录");
			return result.toString();
		}
		// 2.合法性校验
		String timestamp = obj.optString("timestamp");
		String authenticator = obj.optString("authenticator");
		String platform = obj.optString("platform");
		Map<String, String> map = AppSecurity.valid(timestamp, authenticator);
		if (!map.get(AppSecurity.code).equals(AppSecurity.success)) {
			if (map.get("code").equals("20025")) {
				// 说明是时间戳过期
				result.put("errorcode", map.get("code"));
				result.put("msg", map.get("msg"));
				return result.toString();
			}
			result.put("errorcode", "20005");
			result.put("msg", "认证失败");
			return result.toString();
		}
		JSONArray clientJsonArray = null;
		try {
			clientJsonArray = JSONArray.fromObject(calledAccount);
		} catch (Exception e) {
			log.info("客户端传递被叫用户格式有误");
			result.put("errorcode", "20000");
			result.put("msg", "传递被叫参数格式有误!");
			return result.toString();
		}
		List<PhoneVo> clientArray = (ArrayList<PhoneVo>) JSONArray
				.toCollection(clientJsonArray, PhoneVo.class);
		Iterator<PhoneVo> iterator = clientArray.iterator();
		while (iterator.hasNext()) {
			TAccountExample example = new TAccountExample();
			example.createCriteria().andAccountEqualTo(
					iterator.next().getPhone());// (String)iterator.next()
			List<TAccount> accountList = accountMapper.selectByExample(example);
			if (accountList.size() > 0 && accountList != null) {
				TAccount account = accountList.get(0);
				// 3、保存通话记录
				if (!tAccount.getAccountid().equals(account.getAccountid())) {
					TCallRecord record = new TCallRecord();
					record.setCallid(IDGenerator.getId());
					record.setMeetingid(meetingid);
					record.setCallerid(tAccount.getAccountid());
					record.setCalleds(account.getAccountid());
					record.setCreatetime(DateHelper.parse(new Date()));
					callRecordMapper.insertSelective(record);
					// 4、把会议号推送给被叫用户
					if (!tAccount.getPushid().equals("0")) {
						String pushiUrl = PropertyFactory
								.getProperty("PushServerAddr");
						log.info("pushiUrl = " + pushiUrl);
						try {
							String requestJson = "{\"platform\":\""
									+ account.getPlateform()
									+ "\",\"content\":{\"meetingid\":\""
									+ meetingid + "\",\"nickname\":\""
									+ tAccount.getNickname()
									+ "\",\"notifytype\":1,\"phone\":\""
									+ tAccount.getAccount() + "\"},";
							requestJson += "\"clients\":[{\"clientid\":\""
									+ account.getPushid()
									+ "\",\"platform\":\""
									+ account.getPlateform() + "\"}]}";
							log.info("request requestJson" + requestJson);
							String textEntity = HttpsUtil.doPost(pushiUrl
									+ "mobile/push", requestJson);
							log.info("response textEntity = " + textEntity);
							if (textEntity != null) {
								JSONObject addToMeetingJsonResult = JSONObject
										.fromObject(textEntity);
								if (!addToMeetingJsonResult.optString(
										"errorcode").equals("00000")) {
									log.info("推送失败,设置对方状态为不在线");
									// 推送失败，更新呼叫状态不在线
									record.setCalledstatus(4);
									callRecordMapper
											.updateByPrimaryKeySelective(record);
								}
							}
						} catch (Exception ex) {
							ex.printStackTrace();
							log.error("请求推送异常.. " + ex.getMessage(), ex);
							log.info(record.getCallerid() + " 呼叫 "
									+ record.getCalleds() + "  异常 ");
						}
					} else {
						record.setCalledstatus(4);
						int count = callRecordMapper
								.updateByPrimaryKeySelective(record);
						if (count > 0) {
							log.info("pushid 为空,设置用户未不在线");
						}
					}
				}
			}
		}
		// 都没问题,更新会议id为此会议id,主叫id为此用户id的状态为已接听
		TCallRecordExample callRecordExample = new TCallRecordExample();
		callRecordExample.createCriteria().andCalleridEqualTo(accountid)
				.andMeetingidEqualTo(meetingid);
		List<TCallRecord> callRecordList = callRecordMapper
				.selectByExample(callRecordExample);
		if (callRecordList != null && callRecordList.size() > 0) {
			// 更新主叫状态
			TCallRecord tCallRecord = callRecordList.get(0);
			tCallRecord.setCallerstatus(0);
			int count = callRecordMapper.updateByPrimaryKey(tCallRecord);
			if (count < 1) {
				// 更新主叫状态失败
				log.info("更新主叫状态失败");
			}
		}
		// 5、把会议号返回给主叫用户
		result.put("errorcode", "00000");
		result.put("msg", "呼叫成功");
		return result.toString();
	}

	/**
	 * @param json
	 * @return
	 */
	@SuppressWarnings("all")
	// 增加通话人
	public String addPerson(String json) {
		log.info("add  person : " + json);
		// 1.将加入会议信息转为json对象
		JSONObject obj = JSONObject.fromObject(json);
		JSONObject result = new JSONObject();

		try {
			obj = JSONObject.fromObject(json);
		} catch (Exception e) {
			log.info("Json数据格式有误");
			result.put("errorcode", "20005");
			result.put("msg", "传递json格式错误");
			return result.toString();
		}

		log.info("check version start ...");
		String version = obj.optString("version");
		JSONObject standVersionCheck = VersionUtil.standVersionCheck(version);
		if (!standVersionCheck.optString("errorcode").equals("00000")) {
			result.put("errorcode", standVersionCheck.optString("errorcode"));
			result.put("msg", standVersionCheck.optString("msg"));
			return result.toString();
		}

		String accountid = obj.optString("accountid");
		log.info("check token");
		TAccount tAccount = accountMapper.selectByPrimaryKey(accountid);
		if (tAccount == null) {
			// 不存在
			result.put("errorcode", "20006");
			result.put("msg", "用户不存在");
			return result.toString();
		}
		if (!obj.optString("token").equals(tAccount.getToken())) {
			result.put("errorcode", "20002");
			result.put("msg", "登录信息已过期，请重新获取验证码登录");
			return result.toString();
		}
		// 2.合法性校验
		String timestamp = obj.optString("timestamp");
		String authenticator = obj.optString("authenticator");

		Map<String, String> map = AppSecurity.valid(timestamp, authenticator);
		if (!map.get(AppSecurity.code).equals(AppSecurity.success)) {
			if (map.get("code").equals("20025")) {
				// 说明是时间戳过期
				result.put("errorcode", map.get("code"));
				result.put("msg", map.get("msg"));
				return result.toString();
			}
			result.put("errorcode", "20005");
			result.put("msg", "认证失败");
			return result.toString();
		}
		String calledAccount = obj.optString("calledAccount");
		String meetingid = obj.optString("meetingid");
		JSONArray clientJsonArray = null;
		try {
			clientJsonArray = JSONArray.fromObject(calledAccount);
		} catch (Exception e) {
			log.info("客户端传递被叫参数格式有误");
			result.put("errorcode", "20000");
			result.put("msg", "传递被叫参数格式有误");
			return result.toString();
		}
		List<PhoneVo> clientArray = (ArrayList<PhoneVo>) JSONArray
				.toCollection(clientJsonArray, PhoneVo.class);
		Iterator<PhoneVo> iterator = clientArray.iterator();
		if (iterator.hasNext()) {
			String phone = iterator.next().getPhone();
			TAccountExample example = new TAccountExample();
			example.createCriteria().andAccountEqualTo(phone);
			List<TAccount> accountList = accountMapper.selectByExample(example);
			if (accountList.size() > 0 && accountList != null) {
				TAccount account = accountList.get(0);
				// 3、保存通话记录
				if (!tAccount.getAccountid().equals(account.getAccountid())) {
					TCallRecord record = new TCallRecord();
					record.setCallid(IDGenerator.getId());
					record.setCallerid(tAccount.getAccountid());
					record.setCalleds(account.getAccountid());
					record.setCreatetime(DateHelper.parse(new Date()));
					record.setMeetingid(meetingid);
					try {
						callRecordMapper.insertSelective(record);
					} catch (Exception e) {
						log.error("保存通话记录异常.." + e.getMessage(), e);
						log.error("保存  " + record.getCallerid() + " 呼叫 "
								+ record.getCalleds() + "时异常..");
					}
					if (!account.getPushid().equals("0")) {
						// 4、把会议号推送给被叫用户
						log.info("push message to " + phone);
						String pushiUrl = PropertyFactory
								.getProperty("PushServerAddr");
						log.info("pushiUrl = " + pushiUrl);
						try {
							String requestJson = "{\"platform\":\""
									+ account.getPlateform()
									+ "\",\"content\":{\"meetingid\":\""
									+ meetingid + "\",\"nickname\":\""
									+ tAccount.getNickname()
									+ "\",\"notifytype\":1,\"phone\":\""
									+ tAccount.getAccount() + "\"},";
							requestJson += "\"clients\":[{\"clientid\":\""
									+ account.getPushid()
									+ "\",\"platform\":\""
									+ account.getPlateform() + "\"}]}";
							log.info("request requestJson" + requestJson);
							String textEntity = HttpsUtil.doPost(pushiUrl
									+ "mobile/push", requestJson);
							log.info("response textEntity = " + textEntity);
							JSONObject addToMeetingJsonResult = JSONObject
									.fromObject(textEntity);
							if (!addToMeetingJsonResult.optString("errorcode")
									.equals("00000")) {
								// 推送失败，更新呼叫状态不在线
								record.setCalledstatus(4);
								callRecordMapper
										.updateByPrimaryKeySelective(record);
							}
						} catch (Exception ex) {
							ex.printStackTrace();
							log.error("推送异常 " + ex.getMessage(), ex);
						}
					} else {
						record.setCalledstatus(4);
						int count = callRecordMapper
								.updateByPrimaryKeySelective(record);
						if (count > 0) {
							log.info("用户pushid不存在,更新用户状态为不在线");
						}
					}

				}

			}
		}
		result.put("errorcode", "00000");
		result.put("msg", "添加通话人成功");
		return result.toString();
	}

	public JSONObject getCreateMeetingObj(TAccount tAccount) {
		Date startDate = new Date(new Date().getTime() + (1000 * 60 * 30));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sf.format(startDate);

		Calendar calendar = Calendar.getInstance();
		TimeZone timeZone = calendar.getTimeZone();
		String timezone = timeZone.getID();

		JSONObject obj = new JSONObject();
		obj.put("accountid", tAccount.getAccountid());
		obj.put("accountname", tAccount.getNickname());
		obj.put("maximum", 10);
		obj.put("adpid", 2);
		obj.put("asid", 1);
		obj.put("topic", tAccount.getNickname());
		obj.put("type", 1);
		obj.put("accounttype", 1);
		obj.put("nickname", tAccount.getNickname());
		obj.put("start_time", dateStr);
		obj.put("duration", 2);
		//obj.put("maximum", tAccount.getApplymum());
		//obj.put("applymum", tAccount.getMaxnum());
		obj.put("option_no_video_host", true);
		obj.put("option_no_video_participants", true);
		obj.put("timezone", timezone);
		obj.put("option_jbh", true);
		return obj;
	}

	/**
	 * @param json
	 * @return
	 */
	// 被叫用户未接听状态上报
	public String notifyAnswerStatus(String json) {
		log.info("被叫用户未接接听状态上报,传入数据为: " + json);
		// 1.将加入会议信息转为json对象
		JSONObject obj = JSONObject.fromObject(json);
		JSONObject result = new JSONObject();

		try {
			obj = JSONObject.fromObject(json);
		} catch (Exception e) {
			log.info("Json数据格式有误");
			result.put("errorcode", "20005");
			result.put("msg", "传递json格式错误");
			return result.toString();
		}

		log.info("check version start ...");
		String version = obj.optString("version");
		JSONObject standVersionCheck = VersionUtil.standVersionCheck(version);
		if (!standVersionCheck.optString("errorcode").equals("00000")) {
			result.put("errorcode", standVersionCheck.optString("errorcode"));
			result.put("msg", standVersionCheck.optString("msg"));
			return result.toString();
		}

		String accountid = obj.optString("accountid");

		log.info("check token");
		TAccount tAccount = accountMapper.selectByPrimaryKey(accountid);
		if (tAccount == null) {
			// 不存在
			result.put("errorcode", "20006");
			result.put("msg", "用户不存在");
			return result.toString();
		}
		if (!obj.optString("token").equals(tAccount.getToken())) {
			result.put("errorcode", "20002");
			result.put("msg", "登录信息已过期，请重新获取验证码登录");
			return result.toString();
		}
		try {
			// 2.合法性校验
			String timestamp = obj.optString("timestamp");
			String authenticator = obj.optString("authenticator");
			String meetingid = obj.optString("meetingid");
			int status = obj.optInt("status");

			Map<String, String> map = AppSecurity.valid(timestamp,
					authenticator);
			if (!map.get(AppSecurity.code).equals(AppSecurity.success)) {
				if (map.get("code").equals("20025")) {
					// 说明是时间戳过期
					result.put("errorcode", map.get("code"));
					result.put("msg", map.get("msg"));
					return result.toString();
				}
				result.put("errorcode", "20005");
				result.put("msg", "认证失败");
				return result.toString();
			}

			try {
				/*
				 * TCallRecordExample example = new TCallRecordExample();
				 * example.createCriteria().andCalledsEqualTo(accountid)
				 * .andMeetingidEqualTo(meetingid); List<TCallRecord> list =
				 * callRecordMapper .selectByExample(example); if (!(list !=
				 * null && list.size() > 0)) { result.put("errorcode", "23008");
				 * result.put("msg", "通话记录不存在"); return result.toString(); }
				 */
				TCallRecordExample example = new TCallRecordExample();
				example.createCriteria().andMeetingidEqualTo(meetingid)
						.andCalledsEqualTo(accountid);
				List<TCallRecord> selectByExample = callRecordMapper
						.selectByExample(example);
				if (selectByExample != null && selectByExample.size() > 0) {
					TCallRecord tCallRecord = selectByExample.get(0);
					tCallRecord.setCalledstatus(status);
					int count = callRecordMapper
							.updateByPrimaryKey(tCallRecord);
					if (count < 0) {
						log.error("更新被叫 " + accountid + " 被叫状态 " + status
								+ " 失败 !");
					}
				}

				TCallRecordExample examples = new TCallRecordExample();
				example.createCriteria().andMeetingidEqualTo(meetingid)
						.andCalleridEqualTo(accountid);
				List<TCallRecord> selectByExamples = callRecordMapper
						.selectByExample(examples);
				if (selectByExamples != null && selectByExamples.size() > 0) {
					TCallRecord tCallRecords = selectByExamples.get(0);
					tCallRecords.setCalledstatus(status);
					int count = callRecordMapper
							.updateByPrimaryKey(tCallRecords);
					if (count < 0) {
						log.error("更新被叫 " + accountid + " 主叫状态 " + status
								+ " 失败 !");
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				log.error("更新被叫接听状态异常 " + ex.getMessage(), ex);
				result.put("errorcode", "23003");
				result.put("msg", "接听状态上报异常");
				return result.toString();
			}
			result.put("errorcode", "00000");
			result.put("msg", "接听状态上报成功");
			return result.toString();
		} catch (Exception e) {
			log.info(e.getMessage(), e);
			result.put("errorcode", "20022");
			result.put("msg", "接听状态上报异常");
			return result.toString();
		}
	}

	/**
	 * 检查被叫未接听状态
	 * 
	 * @param json
	 * @return
	 */
	@SuppressWarnings("all")
	// 检查被叫接听状态
	public String checkAnswerStatus(String json) {
		log.info("检查被叫接听状态: " + json);
		// 1.将加入会议信息转为json对象
		JSONObject obj = JSONObject.fromObject(json);
		JSONObject result = new JSONObject();

		try {
			obj = JSONObject.fromObject(json);
		} catch (Exception e) {
			log.info("Json数据格式有误");
			result.put("errorcode", "20005");
			result.put("msg", "传递json格式错误");
			return result.toString();
		}

		log.info("check version start ");
		String version = obj.optString("version");
		JSONObject standVersionCheck = VersionUtil.standVersionCheck(version);
		if (!standVersionCheck.optString("errorcode").equals("00000")) {
			result.put("errorcode", standVersionCheck.optString("errorcode"));
			result.put("msg", standVersionCheck.optString("msg"));
			return result.toString();
		}
		String calledAccount = obj.optString("calledAccount");
		String accountid = obj.optString("accountid");
		log.info("check token");
		TAccount tAccount = accountMapper.selectByPrimaryKey(accountid);
		if (tAccount == null) {
			// 不存在
			result.put("errorcode", "20006");
			result.put("msg", "用户不存在");
			return result.toString();
		}
		if (!obj.optString("token").equals(tAccount.getToken())) {
			result.put("errorcode", "20002");
			result.put("msg", "登录信息已过期，请重新获取验证码登录");
			return result.toString();
		}
		// 2.合法性校验
		String timestamp = obj.optString("timestamp");
		String authenticator = obj.optString("authenticator");
		String meetingid = obj.optString("meetingid");

		Map<String, String> map = AppSecurity.valid(timestamp, authenticator);
		if (!map.get(AppSecurity.code).equals(AppSecurity.success)) {
			if (map.get("code").equals("20025")) {
				// 说明是时间戳过期
				result.put("errorcode", map.get("code"));
				result.put("msg", map.get("msg"));
				return result.toString();
			}
			result.put("errorcode", "20005");
			result.put("msg", "认证失败");
			return result.toString();
		}
		if (calledAccount == null || calledAccount.equals("")) {
			result.put("errorcode", "20000");
			result.put("msg", "查询被叫不能为空");
			return result.toString();
		}
		JSONArray clientJsonArray = null;
		try {
			clientJsonArray = JSONArray.fromObject(calledAccount);
		} catch (Exception e) {
			// 如果传的不是数组
			result.put("errorcode", "20000");
			result.put("msg", "传递被叫参数格式有误");
			return result.toString();
		}

		log.info("check phones " + calledAccount);
		List<PhoneVo> clientArray = (ArrayList<PhoneVo>) JSONArray
				.toCollection(clientJsonArray, PhoneVo.class);
		Iterator<PhoneVo> iterator = clientArray.iterator();
		List<PhoneVo> results = new ArrayList<PhoneVo>();
		while (iterator.hasNext()) {
			String phone = iterator.next().getPhone();
			log.info("phone is " + phone);
			TAccountExample accountExample = new TAccountExample();
			accountExample.createCriteria().andAccountEqualTo(phone);
			List<TAccount> accountList = accountMapper
					.selectByExample(accountExample);
			if (accountList != null && accountList.size() > 0) {
				// 有这个用户
				TAccount callAccount = accountList.get(0);
				TCallRecordExample calledRecordExample = new TCallRecordExample();
				calledRecordExample.createCriteria()
						.andCalleridEqualTo(accountid)
						.andCalledsEqualTo(callAccount.getAccountid())
						.andMeetingidEqualTo(obj.optString("meetingid"));
				List<TCallRecord> callRecordExampleList = callRecordMapper
						.selectByExample(calledRecordExample);
				if (callRecordExampleList != null
						&& callRecordExampleList.size() > 0) {
					PhoneVo phoneVo = new PhoneVo();
					TCallRecord tCallRecord = callRecordExampleList.get(0);
					phoneVo.setStatus(tCallRecord.getCalledstatus() == 0 ? tCallRecord
							.getCallerstatus() : tCallRecord.getCalledstatus());
					phoneVo.setPhone(phone);
					results.add(phoneVo);
				} else {
					// 未查询到呼叫记录则返回0
					log.info("no calling record ,set default 0");
					result.put("errorcode", "00000");
					result.put("msg", "成功");
					PhoneVo phoneVo = new PhoneVo();
					phoneVo.setPhone(phone);
					phoneVo.setStatus(0);
					results.add(phoneVo);
				}
			} else {
				log.info("未查询到相关记录 " + phone);
				result.put("errorcode", "00000");
				result.put("msg", "成功");
				PhoneVo phoneVo = new PhoneVo();
				phoneVo.setPhone(phone);
				phoneVo.setStatus(4);
				results.add(phoneVo);
			}
		}
		result.put("errorcode", "00000");
		result.put("msg", "成功");
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "pic", "nickname", "account",
				"picurl" });
		result.put("results", JSONArray.fromObject(results, jsonConfig));
		return result.toString();
	}

	public String endMeeting(String json) {
		log.info("结束会议: " + json);
		// 1.将加入会议信息转为json对象
		JSONObject obj = JSONObject.fromObject(json);
		JSONObject result = new JSONObject();
		try {
			obj = JSONObject.fromObject(json);
		} catch (Exception e) {
			log.info("Json数据格式有误");
			result.put("errorcode", "20005");
			result.put("msg", "传递json格式错误");
			return result.toString();
		}

		log.info("check version start ");
		String version = obj.optString("version");
		JSONObject standVersionCheck = VersionUtil.standVersionCheck(version);
		if (!standVersionCheck.optString("errorcode").equals("00000")) {
			result.put("errorcode", standVersionCheck.optString("errorcode"));
			result.put("msg", standVersionCheck.optString("msg"));
			return result.toString();
		}

		String accountid = obj.optString("accountid");
		log.info("check token");
		TAccount tAccount = accountMapper.selectByPrimaryKey(accountid);
		if (tAccount == null) {
			// 不存在
			result.put("errorcode", "20006");
			result.put("msg", "用户不存在");
			return result.toString();
		}
		if (!obj.optString("token").equals(tAccount.getToken())) {
			result.put("errorcode", "20002");
			result.put("msg", "登录信息已过期，请重新获取验证码登录");
			return result.toString();
		}

		String timestamp = obj.optString("timestamp");
		String authenticator = obj.optString("authenticator");
		Map<String, String> map = AppSecurity.valid(timestamp, authenticator);
		if (!map.get(AppSecurity.code).equals(AppSecurity.success)) {
			if (map.get("code").equals("20025")) {
				// 说明是时间戳过期
				result.put("errorcode", map.get("code"));
				result.put("msg", map.get("msg"));
				return result.toString();
			}
			result.put("errorcode", "20005");
			result.put("msg", "认证失败");
			return result.toString();
		}
		try {
			// 2.合法性校验
			String meetingid = obj.optString("meetingId");
			// 3.向OSS请求主动结束会议
			String url = PropertyFactory.getProperty("OSSADDR");
			log.info("url = " + url);
			try {
				String requestJson = "{\"meetingid\":\"" + meetingid + "\"}";
				log.info("request requestJson" + requestJson);
				String textEntity = HttpsUtil.doPost(
						url + "/meeting/meetingid", requestJson);
				log.info("response textEntity = " + textEntity);
				JSONObject addToMeetingJsonResult = JSONObject
						.fromObject(textEntity);
				if (!addToMeetingJsonResult.optString("errorcode").equals(
						"00000")) {
					// 如果OSS返回不可以
					result.put("errorcode",
							addToMeetingJsonResult.optString("errorcode"));
					result.put("msg", addToMeetingJsonResult.optString("msg"));
					return result.toString();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				log.info("结束会议错误= " + ex);
				result.put("errorcode", "23003");
				result.put("msg", "结束会议失败");
				return result.toString();
			}
			result.put("errorcode", "00000");
			result.put("msg", "结束会议成功");
			return result.toString();
		} catch (Exception e) {
			log.info(e.getMessage(), e);
			result.put("errorcode", "20022");
			result.put("msg", "结束会议异常");
			return result.toString();
		}
	}

	/**
	 * @param json
	 * @return
	 */
	public String valid(String json) {
		log.info("开启加入会议验证,传入数据为: " + json);
		// 1.将加入会议信息转为json对象
		JSONObject obj = JSONObject.fromObject(json);
		JSONObject result = new JSONObject();

		try {
			obj = JSONObject.fromObject(json);
		} catch (Exception e) {
			log.info("Json数据格式有误");
			result.put("errorcode", "20005");
			result.put("msg", "传递json格式错误");
			return result.toString();
		}

		log.info("check version start ");
		String version = obj.optString("version");
		JSONObject standVersionCheck = VersionUtil.standVersionCheck(version);
		if (!standVersionCheck.optString("errorcode").equals("00000")) {
			result.put("errorcode", standVersionCheck.optString("errorcode"));
			result.put("msg", standVersionCheck.optString("msg"));
			return result.toString();
		}
		String accountid = obj.optString("accountid");
		log.info("check token");
		TAccount tAccount = accountMapper.selectByPrimaryKey(accountid);
		if (tAccount == null) {
			// 不存在
			result.put("errorcode", "20006");
			result.put("msg", "用户不存在");
			return result.toString();
		}
		if (!obj.optString("token").equals(tAccount.getToken())) {
			result.put("errorcode", "20002");
			result.put("msg", "登录信息已过期，请重新获取验证码登录");
			return result.toString();
		}

		String timestamp = obj.optString("timestamp");
		String authenticator = obj.optString("authenticator");
		Map<String, String> map = AppSecurity.valid(timestamp, authenticator);
		if (!map.get(AppSecurity.code).equals(AppSecurity.success)) {
			if (map.get("code").equals("20025")) {
				// 说明是时间戳过期
				result.put("errorcode", map.get("code"));
				result.put("msg", map.get("msg"));
				return result.toString();
			}
			result.put("errorcode", "20005");
			result.put("msg", "认证失败");
			return result.toString();
		}
		try {
			// 2.合法性校验
			String meetingid = obj.optString("meetingid");
			// 3.向OSS请求创建会议
			String url = PropertyFactory.getProperty("OSSADDR");
			log.info("url = " + url);
			try {
				String requestJson = "{\"meetingid\":\"" + meetingid + "\"}";
				// URLEncoder 因为生成的uuid有特殊字符
				// requestJson = URLEncoder.encode(requestJson);
				log.info("request requestJson" + requestJson);
				String textEntity = HttpsUtil.doPost(url + "/meeting/valid",
						requestJson);
				log.info("response textEntity = " + textEntity);
				if (textEntity == null) {
					log.info("向MediaServer 请求加会时返回了null ");
					result.put("errorcode", "23003");
					result.put("msg", "加入会议失败");
					return result.toString();
				}
				JSONObject addToMeetingJsonResult = JSONObject
						.fromObject(textEntity);
				if (!addToMeetingJsonResult.optString("errorcode").equals(
						"00000")) {
					// 如果OSS返回不可以
					result.put("errorcode",
							addToMeetingJsonResult.optString("errorcode"));
					result.put("msg", addToMeetingJsonResult.optString("msg"));
					return result.toString();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				log.error("加入会议错误= " + ex.getMessage(), ex);
				result.put("errorcode", "23003");
				result.put("msg", "加入会议失败");
				return result.toString();
			}
			result.put("errorcode", "00000");
			result.put("msg", "加入会议成功");

			TCallRecordExample example = new TCallRecordExample();
			example.createCriteria().andMeetingidEqualTo(meetingid)
					.andCalledsEqualTo(accountid);
			List<TCallRecord> list = callRecordMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				TCallRecord callRecord = list.get(0);
				// 将通话记录更新为已接听
				callRecord.setCalledstatus(5);
				callRecordMapper.updateByPrimaryKeySelective(callRecord);
			}
			return result.toString();
		} catch (Exception e) {
			log.info(e.getMessage(), e);
			result.put("errorcode", "22002");
			result.put("msg", "验证会议异常");
			return result.toString();
		}
	}

	/**
	 * 
	 * @param timezome
	 * @param timeStr
	 * @return
	 */
	public String getUTCByTimezone(String timezome, String timeStr) {
		try {
			TimeZone tz = TimeZone.getTimeZone(timezome);
			Calendar calendar = Calendar.getInstance(tz);
			int zoneOffset = calendar.get(java.util.Calendar.ZONE_OFFSET);
			System.out.println(zoneOffset / 60 / 60 / 1000);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf1 = new SimpleDateFormat(
					"yyyy-MM-dd'T'HH:mm:ss'Z'");
			String str = sdf1.format(new Date(sdf.parse(timeStr).getTime()
					- zoneOffset));
			return str;
		} catch (ParseException e) {
			log.error("时间格式化失败timezome" + timezome + "timeStr" + timeStr + e, e);
		}
		return null;
	}

	/**
	 * 查询会议状态
	 * 
	 * @param json
	 * @return
	 */
	public String getMeetingState(String json) {
		JSONObject obj = JSONObject.fromObject(json);
		log.info("参会前查询会议状态... " + json);
		JSONObject result = new JSONObject();

		try {
			obj = JSONObject.fromObject(json);
		} catch (Exception e) {
			log.info("Json数据格式有误");
			result.put("errorcode", "20005");
			result.put("msg", "传递json格式错误");
			return result.toString();
		}

		log.info("check version start ");
		String version = obj.optString("version");
		JSONObject standVersionCheck = VersionUtil.standVersionCheck(version);
		if (!standVersionCheck.optString("errorcode").equals("00000")) {
			result.put("errorcode", standVersionCheck.optString("errorcode"));
			result.put("msg", standVersionCheck.optString("msg"));
			return result.toString();
		}

		String accountid = obj.optString("accountid");
		log.info("check token");
		TAccount tAccount = accountMapper.selectByPrimaryKey(accountid);
		if (tAccount == null) {
			// 不存在
			result.put("errorcode", "20006");
			result.put("msg", "用户不存在");
			return result.toString();
		}
		if (!obj.optString("token").equals(tAccount.getToken())) {
			result.put("errorcode", "20002");
			result.put("msg", "登录信息已过期，请重新获取验证码登录");
			return result.toString();
		}

		String timestamp = obj.optString("timestamp");
		String authenticator = obj.optString("authenticator");
		Map<String, String> map = AppSecurity.valid(timestamp, authenticator);
		if (!map.get(AppSecurity.code).equals(AppSecurity.success)) {
			if (map.get("code").equals("20025")) {
				// 说明是时间戳过期
				result.put("errorcode", map.get("code"));
				result.put("msg", map.get("msg"));
				return result.toString();
			}
			result.put("errorcode", "20005");
			result.put("msg", "认证失败");
			return result.toString();
		}
		String url = PropertyFactory.getProperty("OSSADDR");
		String meetingid = obj.optString("meetingid");
		log.info("meetingid " + meetingid);
		JSONObject addToMeetingJsonResult = null;
		log.info("url = " + url);
		try {
			String requestJson = "{\"meetingid\":\"" + meetingid + "\"}";
			log.info("request requestJson" + requestJson);
			String textEntity = HttpsUtil.doPost(
					url + "/meeting/meetingStatus", requestJson);
			log.info("response textEntity = " + textEntity);
			if (textEntity == null) {
				log.info("向MediaServer查询会议状态,MediaServer返回了null ");
				result.put("errorcode", "23003");
				result.put("msg", "查询会议状态异常");
				return result.toString();
			}
			addToMeetingJsonResult = JSONObject.fromObject(textEntity);
			if (!addToMeetingJsonResult.optString("errorcode").equals("00000")) {
				// 如果OSS返回不可以
				result.put("errorcode",
						addToMeetingJsonResult.optString("errorcode"));
				result.put("msg", addToMeetingJsonResult.optString("msg"));
				return result.toString();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			log.info("向mediaServer查询会议状态异常 " + ex);
			result.put("errorcode", "23003");
			result.put("msg", "查询会议状态异常");
			return result.toString();
		}
		result.put("errorcode", "00000");
		result.put("msg", "成功");
		result.put("status", addToMeetingJsonResult.optInt("status"));
		return result.toString();
	}

}
