package cn.com.yunqitong.logic;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import cn.com.yunqitong.mapper.TAccountMapper;
import cn.com.yunqitong.mapper.TMailingMapper;
import cn.com.yunqitong.util.AppSecurity;
import cn.com.yunqitong.util.HttpsUtil;
import cn.com.yunqitong.util.PropertyFactory;
import cn.com.yunqitong.util.VersionUtil;
import cn.com.yunqitong.vo.MailingVo;
import cn.com.yunqitong.vo.PhoneVo;
/**
 * 项目名称：LJAuthorizationServer 
 * 类名称：MailingLogic 
 * 创建人：huli 
 * 创建时间：2016-2-2 下午7:17:12
 * 
 */
@Service
public class MailingLogic {
	protected Logger log = Logger.getLogger(MailingLogic.class);
	@Autowired
	private TAccountMapper accountMapper;
	@Autowired
	private TMailingMapper tMailingMapper;

	/**
	 * 获取通讯录列表
	 * 
	 * @param jsonFromRequest
	 * @return
	 */
	public MailingVo getMailingList(String jsonFromRequest) {
		log.info("enter get mailing list method ");
		JSONObject fromObject = null;
		MailingVo mailingVo = new MailingVo();
		
		try {
			fromObject = JSONObject.fromObject(jsonFromRequest);
		} catch (Exception e) {
			log.info("Json数据格式有误");
			mailingVo.setErrorcode("20005");
			mailingVo.setMsg("传递json格式错误");
			return mailingVo;
		}
		
		String accountid = fromObject.optString("accountid");
		TAccount tAccount = accountMapper.selectByPrimaryKey(accountid);
		if (tAccount == null) {
			// 不存在
			mailingVo.setErrorcode("20006");
			mailingVo.setMsg("用户不存在");
			return mailingVo;
		}
		if(fromObject.optInt("actor")!=2){
			//移动端自己
			log.info("check token");
			if (!fromObject.optString("token").equals(tAccount.getToken())) {
				mailingVo.setErrorcode("20002");
				mailingVo.setMsg("登录信息已过期，请重新获取验证码登录");
				return mailingVo;
			}
		}
		// 2.合法性校验
		String timestamp = fromObject.optString("timestamp");
		String authenticator = fromObject.optString("authenticator");
		Map<String, String> map = AppSecurity.valid(timestamp, authenticator);
		if (!map.get(AppSecurity.code).equals(AppSecurity.success)) {
			if(map.get("code").equals("20025")){
				//说明是时间戳过期
				mailingVo.setErrorcode(map.get("code"));
				mailingVo.setMsg(map.get("msg"));
				return mailingVo;
			}
			mailingVo.setErrorcode("20005");
			mailingVo.setMsg("认证失败");
			return mailingVo;
		}
		List<PhoneVo> phoneList = tMailingMapper
				.selectUserMailingList(accountid);
		mailingVo.setPhoneList(phoneList);
		mailingVo.setErrorcode("00000");
		mailingVo.setMsg("成功");
		return mailingVo;
		/*
		 * TAccount tAccount = accountMapper.selectByPrimaryKey(accountid); List
		 * li=new ArrayList(); for(int i=0;i<3;i++){ PhoneVo phoneVo=new
		 * PhoneVo(); phoneVo.setNickname("第"+i+"个昵称");
		 * phoneVo.setPhone("第"+i+"个手机号"); phoneVo.setPic(
		 * "http://img3.douban.com/view/note/large/public/p8107361.jpg");
		 * li.add(phoneVo); }
		 */
		// mailingVo.setPhoneList(li);
	}

	/**
	 * 联系人过滤
	 * 
	 * @param jsonFromRequest
	 * @return
	 */
	@SuppressWarnings("all")
	public String fileterMailingList(String jsonFromRequest) {
		log.info("enter get mailing list method ");
		JSONObject fromObject = JSONObject.fromObject(jsonFromRequest);
		JSONObject result = new JSONObject();
		
		try {
			fromObject = JSONObject.fromObject(jsonFromRequest);
		} catch (Exception e) {
			log.info("Json数据格式有误");
			result.put("errorcode", "20005");
			result.put("msg", "传递json格式错误");
			return result.toString();
		}
		
		
		log.info("check version start ");
		String version = fromObject.optString("version");
		JSONObject standVersionCheck = VersionUtil.standVersionCheck(version);
		if(!standVersionCheck.optString("errorcode").equals("00000")){
			result.put("errorcode", standVersionCheck.optString("errorcode"));
			result.put("msg", standVersionCheck.optString("msg"));
			return result.toString();
		}
		
		String accountid = fromObject.optString("accountid");
		log.info("check token");
		TAccount tAccount = accountMapper.selectByPrimaryKey(accountid);
		if (tAccount == null) {
			// 不存在
			result.put("errorcode", "20006");
			result.put("msg", "用户不存在");
			return result.toString();
		}
		if (!fromObject.optString("token").equals(tAccount.getToken())) {
			result.put("errorcode", "20002");
			result.put("msg", "登录信息已过期，请重新获取验证码登录");
			return result.toString();
		}
		// 2.合法性校验
		String timestamp = fromObject.optString("timestamp");
		String authenticator = fromObject.optString("authenticator");
		Map<String, String> map = AppSecurity.valid(timestamp, authenticator);
		if (!map.get(AppSecurity.code).equals(AppSecurity.success)) {
			if(map.get("code").equals("20025")){
				//说明是时间戳过期
				result.put("errorcode", map.get("code"));
				result.put("msg", map.get("msg"));
				return result.toString();
			}
			result.put("errorcode", "20005");
			result.put("msg", "认证失败");
			return result.toString();
		}
		String phoneList=fromObject.optString("phoneList");
		JSONArray clientJsonArray =null;
		try {
			clientJsonArray = JSONArray.fromObject(phoneList);
		} catch (Exception e) {
			log.info("联系人筛选传递参数有误..");
			result.put("errorcode", "20000");
			result.put("msg", "传递联系人参数格式有误");
			return result.toString();
		}
		List<PhoneVo> registeredPhone = (ArrayList<PhoneVo>) JSONArray
				.toCollection(clientJsonArray, PhoneVo.class);
		Iterator<PhoneVo> iterator = registeredPhone.iterator();
		while (iterator.hasNext()) {
			PhoneVo phoneVo = iterator.next();
			// 遍历查询号码是否存在
			TAccountExample accountExample = new TAccountExample();
			accountExample.createCriteria().andAccountEqualTo(
					phoneVo.getPhone());
			List<TAccount> accountList = accountMapper
					.selectByExample(accountExample);
			if (!(accountList != null && accountList.size() > 0)) {
				// 从集合中移除此号码
				iterator.remove();
			}else{
				//存在,把头像地址传过去
				phoneVo.setPicurl(accountList.get(0).getPicurl());
				}
		}
		result.put("errorcode", "00000");
		result.put("msg", "联系人筛选成功");
		JsonConfig jsonConfig = new JsonConfig();
		String excludes[] = new String[] { "pic", "nickname", "account"};
		jsonConfig.setExcludes(excludes);
		result.put("registeredPhone",
				JSONArray.fromObject(registeredPhone, jsonConfig).toString());
		return result.toString();
	}
	/**
	 * 添加通讯录联系人
	 * @param jsonFromRequest
	 * @return
	 */
	@SuppressWarnings("all")
	public String addMailingList(String jsonFromRequest) {
		log.info("enter get mailing list method ");
		JSONObject result = new JSONObject();
		JSONObject fromObject = null;
		try {
			fromObject = JSONObject.fromObject(jsonFromRequest);
		} catch (Exception e) {
			log.info("Json数据格式有误");
			result.put("errorcode", "20005");
			result.put("msg", "传递json格式错误");
			return result.toString();
		}
		
		String version = fromObject.optString("version");
		log.info("check version start ");
		JSONObject standVersionCheck = VersionUtil.standVersionCheck(version);
		if(!standVersionCheck.optString("errorcode").equals("00000")){
			result.put("errorcode", standVersionCheck.optString("errorcode"));
			result.put("msg", standVersionCheck.optString("msg"));
			return result.toString();
		}
		String accountid = fromObject.optString("accountid");
		log.info("check token");
		TAccount tAccount = accountMapper.selectByPrimaryKey(accountid);
		if (tAccount == null) {
			// 不存在
			result.put("errorcode", "20006");
			result.put("msg", "用户不存在");
			return result.toString();
		}
		/*if (!fromObject.optString("token").equals(tAccount.getToken())) {
			result.put("errorcode", "20002");
			result.put("msg", "登录信息已过期，请重新获取验证码登录");
			return result.toString();
		}*/
		// 2.合法性校验
		String timestamp = fromObject.optString("timestamp");
		String authenticator = fromObject.optString("authenticator");
		Map<String, String> map = AppSecurity.valid(timestamp, authenticator);
		if (!map.get(AppSecurity.code).equals(AppSecurity.success)) {
			if(map.get("code").equals("20025")){
				//说明是时间戳过期
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
			clientJsonArray = JSONArray.fromObject(fromObject
					.optString("addPhoneList"));
		} catch (Exception e) {
			log.info("添加联系人传递参数有误 ");
			result.put("errorcode", "20000");
			result.put("msg", "添加联系人传递参数格式有误");
			return result.toString();
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
			List<TAccount> accountList = accountMapper
					.selectByExample(accountExample);
			TAccount account = null;
			if (accountList.size() > 0 && phoneVo != null) {
				account = accountList.get(0);
				TMailing record = new TMailing();
				record.setFaccountid(account.getAccountid());
				record.setMccountid(accountid);
				int count = tMailingMapper.insert(record);
				if (count > 0) {
					log.info("添加 " + accountid + " 的好友"
							+ account.getAccountid() + "  "
							+ account.getNickname() + "  成功");
				} else {
					log.info("添加 " + accountid + " 的好友"
							+ account.getAccountid() + "  "
							+ account.getNickname() + "  失败");
				}
			}
		}
		//如果上次登录平台是androidTv才退送
		if(tAccount.getPlateform().equals("androidtv")){
			String pushiUrl = PropertyFactory.getProperty("PushServerAddr");
			log.info("pushiUrl = " + pushiUrl);
			try {
				String requestJson = "{\"platform\":\""+fromObject.optString("platform")+"\",\"content\":{\"notifytype\":3},";
				requestJson += "\"clients\":[{\"clientid\":\""
						+ tAccount.getPushid() + "\",\"platform\":\""
						+ tAccount.getPlateform() + "\"}]}";
				log.info("request requestJson" + requestJson);
				String textEntity = HttpsUtil.doPost(pushiUrl
						+ "mobile/push", requestJson);
				log.info("response textEntity = " + textEntity);
				JSONObject resJson = JSONObject
						.fromObject(textEntity);
				if(!resJson.optString("errorcode").equals("00000")){
					log.error("请求推送失败..push fail .!! ");
				}else{
					log.error("请求推送成功..push success .!! ");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				log.error("请求推送异常.. "+ex.getMessage(),ex);
				result.put("errorcode", "00000");
				result.put("msg", "添加联系人异常");
				return result.toString();
			}
		}
		
		result.put("errorcode", "00000");
		result.put("msg", "添加联系人成功");
		return result.toString();
	}

	/**
	 * 删除通讯录联系人
	 * @param jsonFromRequest
	 * @return
	 */
	@SuppressWarnings("all")
	public String deleteMailingList(String jsonFromRequest) {
		log.info("enter get mailing list method ");
		JSONObject result = new JSONObject();
		JSONObject fromObject = null;
		
		try {
			fromObject = JSONObject.fromObject(jsonFromRequest);
		} catch (Exception e) {
			log.info("Json数据格式有误");
			result.put("errorcode", "20005");
			result.put("msg", "传递json格式错误");
			return result.toString();
		}
		
		
		String version = fromObject.optString("version");
		
		log.info("check version start ");
		JSONObject standVersionCheck = VersionUtil.standVersionCheck(version);
		if(!standVersionCheck.optString("errorcode").equals("00000")){
			result.put("errorcode", standVersionCheck.optString("errorcode"));
			result.put("msg", standVersionCheck.optString("msg"));
			return result.toString();
		}
		String accountid = fromObject.optString("accountid");
		//log.info("check token");
		TAccount tAccount = accountMapper.selectByPrimaryKey(accountid);
		if (tAccount == null) {
			// 不存在
			result.put("errorcode", "20006");
			result.put("msg", "用户不存在");
			return result.toString();
		}
		/*if (!fromObject.optString("token").equals(tAccount.getToken())) {
			result.put("errorcode", "20002");
			result.put("msg", "登录信息已过期，请重新获取验证码登录");
			return result.toString();
		}*/
		// 2.合法性校验
		String timestamp = fromObject.optString("timestamp");
		String authenticator = fromObject.optString("authenticator");
		Map<String, String> map = AppSecurity.valid(timestamp, authenticator);
		if (!map.get(AppSecurity.code).equals(AppSecurity.success)) {
			if(map.get("code").equals("20025")){
				//说明是时间戳过期
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
			JSONArray.fromObject(fromObject.optString("delPhoneList"));
		} catch (Exception e) {
			log.info("删除联系人传递参数格式有误");
			result.put("errorcode", "20000");
			result.put("msg", "传递联系人参数格式有误");
			return result.toString();
		}
		List<PhoneVo> delPhoneList = (ArrayList<PhoneVo>) JSONArray
				.toCollection(clientJsonArray, PhoneVo.class);
		Iterator<PhoneVo> iterator = delPhoneList.iterator();
		while (iterator.hasNext()) {
			PhoneVo phoneVo = iterator.next();
			// 查询
			TAccountExample accountExample = new TAccountExample();
			accountExample.createCriteria().andAccountEqualTo(
					phoneVo.getPhone());
			List<TAccount> accountList = accountMapper
					.selectByExample(accountExample);
			if (accountList.size() > 0 && accountList != null) {
				TAccount account = accountList.get(0);
				TMailingExample mailingExample = new TMailingExample();
				mailingExample.createCriteria()
						.andFaccountidEqualTo(account.getAccountid())
						.andMccountidEqualTo(accountid);
				int count = tMailingMapper.deleteByExample(mailingExample);
				if (count > 0) {
					log.info("删除 " + accountid + " 的好友"
							+ account.getAccountid() + "  "
							+ account.getNickname() + "  成功");
				} else {
					log.info("删除 " + accountid + " 的好友"
							+ account.getAccountid() + "  "
							+ account.getNickname() + "  失败");
				}
			}
		}
		if(tAccount.getPlateform().equals("androidtv")){
			String pushiUrl = PropertyFactory.getProperty("PushServerAddr");
			log.info("pushiUrl = " + pushiUrl);
			try {
				String requestJson = "{\"platform\":\""+fromObject.optString("platform")+"\",\"content\":{\"notifytype\":3},";
				requestJson += "\"clients\":[{\"clientid\":\""
						+ tAccount.getPushid() + "\",\"platform\":\""
						+ tAccount.getPlateform() + "\"}]}";
				log.info("request requestJson" + requestJson);
				String textEntity = HttpsUtil.doPost(pushiUrl
						+ "mobile/push", requestJson);
				log.info("response textEntity = " + textEntity);
				JSONObject resJson = JSONObject
						.fromObject(textEntity);
				if(!resJson.optString("errorcode").equals("00000")){
					log.error("请求推送失败..push fail .!! ");
				}else{
					log.error("请求推送成功..push success .!! ");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				log.error("请求推送异常.. "+ex.getMessage(),ex);
				result.put("errorcode", "00000");
				result.put("msg", "删除联系人成功");
				return result.toString();
			}
		}
		result.put("errorcode", "00000");
		result.put("msg", "删除联系人成功");
		return result.toString();
	}
}
