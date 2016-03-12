package cn.com.yunqitong.logic;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.yunqitong.domain.TAccount;
import cn.com.yunqitong.domain.TShare;
import cn.com.yunqitong.domain.TShareExample;
import cn.com.yunqitong.domain.TSuggest;
import cn.com.yunqitong.mapper.TAccountMapper;
import cn.com.yunqitong.mapper.TShareMapper;
import cn.com.yunqitong.mapper.TSuggestMapper;
import cn.com.yunqitong.util.AppSecurity;
import cn.com.yunqitong.util.DateHelper;
import cn.com.yunqitong.util.ErrorCode;
import cn.com.yunqitong.util.HttpsUtil;
import cn.com.yunqitong.util.IDGenerator;
import cn.com.yunqitong.util.JsonUtil;
import cn.com.yunqitong.util.PropertyFactory;
import cn.com.yunqitong.util.VersionUtil;
import cn.com.yunqitong.vo.PicVo;
import cn.com.yunqitong.vo.RingVo;
import cn.com.yunqitong.vo.SuggestVo;
@Service
public class CommonLogic {
	protected Logger log=Logger.getLogger(CommonLogic.class);
	@Autowired
	private TAccountMapper accountMapper;
	@Autowired
	private TSuggestMapper suggestMapper;
	@Autowired
	private TShareMapper tShareMapper;
	String message = "";
	byte[] fileBytes = null;
	public String check(String requestStr) {
		log.info("requestStr = " + requestStr);
		// 判断请求json格式是否错误
		JSONObject jsonObject = JsonUtil.getJsonObject(requestStr);
		JSONObject resJson=new JSONObject();
		// 判断请求参数是否正确。
		String platform = jsonObject.optString("platform");
		log.info("platform = " + platform);
		String version = jsonObject.optString("version");
		log.info("version = " + version);
		
		log.info("check version start ");
		JSONObject standVersionCheck = VersionUtil.standVersionCheck(version);
		if(!standVersionCheck.optString("errorcode").equals("00000")){
			resJson.put("errorcode",standVersionCheck.optString("errorcode"));
			resJson.put("msg",standVersionCheck.optString("msg"));
			return resJson.toString();
		}
		
		String build = jsonObject.optString("build");
		log.info("build = " + build);
		String timestamp = jsonObject.optString("timestamp");
		log.info("timestamp = " + timestamp);
		String authenticator = jsonObject.optString("authenticator");
		log.info("authenticator = " + authenticator);
		// 认证判断
		
		Map<String, String> map = AppSecurity.valid(timestamp, authenticator);
		if (!map.get(AppSecurity.code).equals(AppSecurity.success)) {
			if(map.get("code").equals("20025")){
				//说明是时间戳过期
				resJson.put("errorcode",map.get("code"));
				resJson.put("msg",map.get("msg"));
				return resJson.toString();
			}
			resJson.put("errorcode","20005");
			resJson.put("msg","认证失败");
			return resJson.toString();
		}
		//向ds检查版本
		log.info("get  version from ds  start ...");
		String url=PropertyFactory.getProperty("DSADDR");
		log.info("ds address "+url);
		try {
			log.info("request str "+requestStr);
			String res = HttpsUtil.doPost(url+"version/check", requestStr);
			log.info("res text "+res);
			resJson = JSONObject.fromObject(res);
			if(!resJson.optString("errorcode").equals("00000")){
				resJson.put("errorcode",resJson.optString("errorcode"));
				resJson.put("msg",resJson.optString("msg"));
				return resJson.toString();
			}
		} catch (Exception e) {
			log.error("向ds 检查版本异常..");
			resJson.put("errorcode","24004");
			resJson.put("msg","版本检查异常,请稍后重试");
			return resJson.toString();
		}
		return resJson.toString();
	}
	/**
	 * 头像上传
	 * @param request
	 * @return
	 */
	public PicVo upload(HttpServletRequest request) {
		PicVo vo=new PicVo();
		try {
			InputStream in = request.getInputStream();
			byte[] buffer1 = new byte[1024];
			int len = -1;
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			while ((len = in.read(buffer1)) != -1) {
				outputStream.write(buffer1, 0, len);
			}
			
			outputStream.close();
			in.close();
			byte[] buffer = outputStream.toByteArray();
			String sepStr = "===uploadavatarpartdivder===";
			String sourceStr = new String(buffer, "utf-8");
			int index = sourceStr.indexOf(sepStr);
			log.info("index = " + index);
			message = sourceStr.substring(0, index + sepStr.length());
			log.info("ressage src = " + message);
			byte[] strBytes = message.getBytes("utf-8");
			log.info("-strBytes-------------------------: " + strBytes);
			fileBytes = new byte[buffer.length - strBytes.length];
			log.info("-fileBytes-------------------------: " + fileBytes);
			System.arraycopy(buffer, strBytes.length, fileBytes, 0, fileBytes.length);

			message = message.substring(0, message.indexOf(sepStr)).trim();
			log.info("message = " + message);
			
			JSONObject jsonObject = JSONObject.fromObject(URLDecoder.decode(message));
			log.info("check version start ");
			String version=jsonObject.optString("version");
			JSONObject standVersionCheck = VersionUtil.standVersionCheck(version);
			if(!standVersionCheck.optString("errorcode").equals("00000")){
				vo.setErrorcode(standVersionCheck.optString("errorcode"));
				vo.setMsg(standVersionCheck.optString("msg"));
				return vo;
			}
			
			Map<String, String> map = AppSecurity.valid(jsonObject.optString("timestamp"), jsonObject.optString("authenticator"));
			if (!map.get(AppSecurity.code).equals(AppSecurity.success)) {
				if(map.get("code").equals("20025")){
					//说明是时间戳过期
					vo.setErrorcode(map.get("code"));
					vo.setMsg(map.get("msg"));
					return vo;
				}
				vo.setErrorcode("20005");
				vo.setMsg("认证失败");
				return vo;
			}
			//JSONObject jsonObject = JSONObject.fromObject(URLDecoder.decode(message));
			String accountid = jsonObject.optString("accountid");
			log.info("accountid = " + accountid);
			log.info("check token");
			TAccount tAccount = accountMapper.selectByPrimaryKey(accountid);
			if(tAccount==null){
				//不存在
				vo.setErrorcode("20006");
				vo.setMsg("此用户不存在");
				return vo;
			}
			if(jsonObject.optInt("actor")!=2){
				//移动端自己
				log.info("check token");
				if (!jsonObject.optString("token").equals(tAccount.getToken())) {
					vo.setErrorcode("20002");
					vo.setMsg("登录信息已过期，请重新获取验证码登录");
					return vo;
				}
			}

			String imagename = IDGenerator.getId() + ".png";
			String timefile = DateHelper.formatDate(new Date(), "yyyyMMdd");
			String imagepath = PropertyFactory.getProperty("IMAGEPARTADDRESS") + timefile;

			String imageportpath = PropertyFactory.getProperty("IMAGEPARPOSTADDRESS") + timefile;
			// 保存图片文件
			String imageurl = PropertyFactory.getProperty("fileaddress") + imageportpath + File.separator + imagename;
			log.debug("imagename:" + imagename + ",imagepath:" + imagepath + ",imageurl:" + imageurl);
			getFile(fileBytes, imagepath, imagename);

			//String tableimagepathaddress = PropertyFactory.getProperty("IMAGEPARTADDRESS");
			String tableimagename = timefile + File.separator + imagename;

			TAccount account = accountMapper.selectByPrimaryKey(accountid);
			account.setPicurl(tableimagename);
			account.setPicuptime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

			int result = accountMapper.updateByPrimaryKey(account);
			log.info("update use pic result = " + result);
			if(result>0){
				if(tAccount.getPlateform().equals("androidtv")){
					log.info("start push ...");
					String pushiUrl = PropertyFactory.getProperty("PushServerAddr");
					log.info("pushiUrl = " + pushiUrl);
					try {
						String requestJson = "{\"platform\":\""+jsonObject.optString("platform")+"\",\"content\":{\"notifytype\":2},";
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
						vo.setErrorcode(ErrorCode.SUCCESS);
						vo.setMsg("上传成功");
						return vo;
					}
				}
				
			}
			vo.setErrorcode(ErrorCode.SUCCESS);
			String url = PropertyFactory.getProperty("PICADDRESS") + PropertyFactory.getProperty("IMAGEPARPOSTADDRESS")
					+ "/" + account.getPicurl();
			vo.setUrl(url);
			return vo;

		} catch (Exception ee) {
			log.error(ee.getMessage(), ee);
			vo.setErrorcode(ErrorCode.SUCCESS);
			vo.setMsg("修改失败");
			return vo;
		}
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
	public SuggestVo suggest(String requestStr) {
		JSONObject jsonObject = JsonUtil.getJsonObject(requestStr);
		SuggestVo vo = new SuggestVo();
		
		log.info("check version start ");
		String version = jsonObject.optString("version");
		JSONObject standVersionCheck = VersionUtil.standVersionCheck(version);
		if(!standVersionCheck.optString("errorcode").equals("00000")){
			vo.setErrorcode(standVersionCheck.optString("errorcode"));
			vo.setMsg(standVersionCheck.optString("msg"));
			return vo;
		}
		
		Map<String, String> map = AppSecurity.valid(jsonObject.optString("timestamp"), jsonObject.optString("authenticator"));
		if (!map.get(AppSecurity.code).equals(AppSecurity.success)) {
			vo.setErrorcode("20006");
			vo.setMsg("此用户不存在");
			if(map.get("code").equals("20025")){
				//说明是时间戳过期
				vo.setErrorcode(map.get("code"));
				vo.setMsg(map.get("msg"));
				return vo;
			}
			vo.setErrorcode("20005");
			vo.setMsg("认证失败");
			return vo;
		}
		
		String accountid = jsonObject.optString("accountid");
		log.info("accountid = " + accountid);
		log.info("check token");
		TAccount tAccount = accountMapper.selectByPrimaryKey(accountid);
		if(tAccount==null){
			//不存在
			vo.setErrorcode("20006");
			vo.setMsg("此用户不存在");
			return vo;
		}
		if(jsonObject.optInt("actor")!=2){
			//移动端自己
			log.info("check token");
			if (!jsonObject.optString("token").equals(tAccount.getToken())) {
				vo.setErrorcode("20002");
				vo.setMsg("登录信息已过期，请重新获取验证码登录");
				return vo;
			}
		}
		
		String build = jsonObject.optString("build");
		log.info("build = " + build);

		String platform = jsonObject.optString("platform");
		log.info("platform = " + platform);

		String timestamp = jsonObject.optString("timestamp");
		log.info("timestamp = " + timestamp);

		String suggestion = jsonObject.optString("suggestion");
		log.info("suggestion = " + suggestion);

		String authenticator = jsonObject.optString("authenticator");
		log.info("authenticator = " + authenticator);

		TSuggest suggest = new TSuggest();
		suggest.setId(IDGenerator.getId());
		suggest.setAccountid(accountid);
		suggest.setBuild(build);
		suggest.setPlatform(platform);
		suggest.setSuggestion(suggestion);
		suggest.setTimestamp(timestamp);
		suggest.setVersion(version);
		int result = suggestMapper.insert(suggest);
		log.info("save suggest result = " + result);
		//SuggestVo vo = new SuggestVo();
		vo.setErrorcode(ErrorCode.SUCCESS);
		return vo;
	}
	/**
	 * 上传铃声
	 * @param request
	 * @return
	 */
	public RingVo uploadRing(HttpServletRequest request) {
		RingVo vo = new RingVo();
		try {
			InputStream in = request.getInputStream();
			byte[] buffer1 = new byte[1024];
			int len = -1;
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			while ((len = in.read(buffer1)) != -1) {
				outputStream.write(buffer1, 0, len);
			}
			outputStream.close();
			in.close();
			byte[] buffer = outputStream.toByteArray();
			String sepStr = "===uploadavatarpartdivder===";
			String sourceStr = new String(buffer, "utf-8");
			int index = sourceStr.indexOf(sepStr);
			log.info("index = " + index);
			message = sourceStr.substring(0, index + sepStr.length());
			log.info("ressage src = " + message);
			byte[] strBytes = message.getBytes("utf-8");
			log.info("-strBytes-------------------------: " + strBytes);
			fileBytes = new byte[buffer.length - strBytes.length];
			log.info("-fileBytes-------------------------: " + fileBytes);
			System.arraycopy(buffer, strBytes.length, fileBytes, 0, fileBytes.length);

			message = message.substring(0, message.indexOf(sepStr)).trim();
			log.info("message = " + message);
			
			JSONObject jsonObject = JSONObject.fromObject(URLDecoder.decode(message));
			Map<String, String> map = AppSecurity.valid(jsonObject.optString("timestamp"), jsonObject.optString("authenticator"));
			if (!map.get(AppSecurity.code).equals(AppSecurity.success)) {
				if(map.get("code").equals("20025")){
					//说明是时间戳过期
					vo.setErrorcode(map.get("code"));
					vo.setMsg(map.get("msg"));
					return vo;
				}
				vo.setErrorcode("20005");
				vo.setMsg("认证失败");
				return vo;
			}
			//JSONObject jsonObject = JSONObject.fromObject(URLDecoder.decode(message));
			String accountid = jsonObject.optString("accountid");
			log.info("accountid = " + accountid);
			
			log.info("check token");
			TAccount tAccount = accountMapper.selectByPrimaryKey(accountid);
			if(tAccount==null){
				//不存在
				vo.setErrorcode("20006");
				vo.setMsg("此用户不存在");
				return vo;
			}
			if(jsonObject.optInt("actor")!=2){
				//移动端自己
				log.info("check token");
				if (!jsonObject.optString("token").equals(tAccount.getToken())) {
					vo.setErrorcode("20002");
					vo.setMsg("登录信息已过期，请重新获取验证码登录");
					return vo;
				}
			}
			String timestamp = jsonObject.optString("timestamp");
			log.info("timestamp = " + timestamp);

			String authenticator = jsonObject.optString("authenticator");
			log.info("authenticator = " + authenticator);

			String ringname = IDGenerator.getId() + ".mp3";
			String timefile = DateHelper.formatDate(new Date(), "yyyyMMdd");
			String ringpath = PropertyFactory.getProperty("RINGPARTADDRESS") + timefile;

			String ringportpath = PropertyFactory.getProperty("RINGPARPOSTADDRESS") + timefile;
			// 保存铃声文件
			String ringurl = PropertyFactory.getProperty("fileaddress") + ringportpath + File.separator + ringname;
			log.debug("ringname:" + ringname + ",ringpath:" + ringpath + ",ringurl:" + ringurl);
			getFile(fileBytes, ringpath, ringname);

			//String tableringpathaddress = PropertyFactory.getProperty("RINGPARTADDRESS");
			String tableringname = timefile + File.separator + ringname;

			TAccount account = accountMapper.selectByPrimaryKey(accountid);
			account.setRingname(jsonObject.optString("ringname"));
			account.setRingurl(tableringname);
			account.setRinguptime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

			int result = accountMapper.updateByPrimaryKeySelective(account);
			log.info("update user ring result = " + result);
			if(result>0){
				if(tAccount.getPlateform().equals("androidtv")){
					log.info("start push ...");
					String pushiUrl = PropertyFactory.getProperty("PushServerAddr");
					log.info("pushiUrl = " + pushiUrl);
					try {
						String requestJson = "{\"platform\":\""+jsonObject.optString("platform")+"\",\"content\":{\"notifytype\":2},";
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
						vo.setErrorcode(ErrorCode.SUCCESS);
						vo.setMsg("上传成功");
						return vo;
					}
				}
				
				vo.setErrorcode(ErrorCode.SUCCESS);
				String url = PropertyFactory.getProperty("RINGADDRESS") + PropertyFactory.getProperty("RINGPARPOSTADDRESS")
						+ "/" + account.getPicurl();
				vo.setUrl(url);
				return vo;
			}

		} catch (Exception ee) {
			log.error(ee.getMessage(), ee);
			vo.setErrorcode("20022");
			vo.setErrorcode("修改失败");
			return vo;
		}
		return null;
	}
	/**
	 * 获取分享信息
	 * @param requestStr
	 * @return
	 */
	public String getShareInfo(String requestStr) {
		JSONObject obj = null;
		JSONObject res=new JSONObject();
		try {
			obj = JSONObject.fromObject(requestStr);
			if(obj==null){
				res.put("errorcode", "20005");
				res.put("msg", "Json数据格式有误");
				return res.toString();
			}
		} catch (Exception e) {
			res.put("errorcode", "20005");
			res.put("msg", "Json数据格式有误");
			return res.toString();
		}
		log.info("check version start ");
		String version = obj.optString("version");
		JSONObject standVersionCheck = VersionUtil.standVersionCheck(version);
		if (!standVersionCheck.optString("errorcode").equals("00000")) {
			res.put("errorcode", standVersionCheck.optString("errorcode"));
			res.put("msg", standVersionCheck.optString("msg"));
			return res.toString();
		}
		// 2.合法性校验
		String timestamp = obj.optString("timestamp");
		String authenticator = obj.optString("authenticator");
		Map<String, String> map = AppSecurity.valid(timestamp, authenticator);
		if (!map.get(AppSecurity.code).equals(AppSecurity.success)) {
			if (map.get("code").equals("20025")) {
				// 说明是时间戳过期
				res.put("errorcode", map.get("code"));
				res.put("msg", map.get("msg"));
				return obj.toString();
			}
			res.put("errorcode", "20005");
			res.put("msg", "认证失败");
			return res.toString();
		}
		TShareExample ShareExample=new TShareExample();
		ShareExample.setOrderByClause("createtime desc");
		log.info("start get latest record ");
		try {
			List<TShare> shareList = tShareMapper.selectByExample(ShareExample);
			if(shareList!=null&&shareList.size()>0){
				log.info("get it ");
				//有,获取第一个
				TShare tShare = shareList.get(0);
				res.put("errorcode", "00000");
				res.put("msg", "成功");
				res.put("wechatcontent", tShare.getWechatcontent());
				res.put("smscontent", tShare.getSmscontent());
				res.put("qrcodepic", tShare.getQrcodepic());
				res.put("mailtitle", tShare.getMailtitle());
				res.put("mailbody", tShare.getMailbody());
				res.put("friendspics", tShare.getFriendspics());
				res.put("friendscontent", tShare.getFriendscontent());
				res.put("createtime", tShare.getCreatetime());
				res.put("copyhint", tShare.getCopyhint());
				res.put("copycontent", tShare.getCopycontent());
				res.put("contactusnumber", tShare.getContactusnumber());
				res.put("contactusinfo", tShare.getContactusinfo());
				return res.toString();
			}else{
				log.info("no records ");
				//无,还没有,请上传
				res.put("errorcode", "20008");
				res.put("msg", "无记录");
				return res.toString();
			}
		} catch (Exception e) {
			log.error("从库中按时间倒叙查询记录时异常  "+e.getMessage(),e);
			res.put("errorcode", "20022");
			res.put("msg", "查询异常,请稍后重试");
			return res.toString();
		}
	}
}
