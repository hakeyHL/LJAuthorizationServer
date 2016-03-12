package cn.com.yunqitong.logic;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.yunqitong.domain.TAccount;
import cn.com.yunqitong.domain.TFee;
import cn.com.yunqitong.domain.TFeeExample;
import cn.com.yunqitong.mapper.TAccountMapper;
import cn.com.yunqitong.mapper.TFeeMapper;
import cn.com.yunqitong.util.AppSecurity;
import cn.com.yunqitong.util.HttpsUtil;
import cn.com.yunqitong.util.PropertyFactory;
import cn.com.yunqitong.util.VersionUtil;
import cn.com.yunqitong.vo.AccountVo;
import cn.com.yunqitong.vo.PackFeesVo;
@Service
public class AccountLogic {
	@Autowired
	private TAccountMapper accountMapper;
	@Autowired
	private TFeeMapper feeMapper;
	/**
	 * 根据用户id查询账户信息
	 * 
	 * @param jsonObject
	 *            接收数据
	 * @return 返回结果
	 */
	protected Logger log = Logger.getLogger(AccountLogic.class);
	
	/*log.info("check version start ");
	String version = fromObject.optString("version");
	JSONObject standVersionCheck = VersionUtil.standVersionCheck(version);
	if(!standVersionCheck.optString("errorcode").equals("00000")){
		result.put("errorcode", standVersionCheck.optString("errorcode"));
		result.put("msg", standVersionCheck.optString("msg"));
		return result.toString();
	}*/
	public AccountVo getAccountInfoById(JSONObject jsonObject) {
		// 1.获取和校验参数
		log.info("根据用户id查询账户信息... get account info start ");
		log.info("传入数据 accept json "+jsonObject.toString());
		
		AccountVo result = new AccountVo();
		String accountid = jsonObject.optString("accountid");
		
		log.info("check version start ");
		String version = jsonObject.optString("version");
		JSONObject standVersionCheck = VersionUtil.standVersionCheck(version);
		if(!standVersionCheck.optString("errorcode").equals("00000")){
			result.setErrorcode(standVersionCheck.optString("errorcode"));
			result.setMsg(standVersionCheck.optString("msg"));
			return result;
		}
		log.info("check token");
		TAccount tAccount = accountMapper.selectByPrimaryKey(accountid);
		if(tAccount==null){
			//不存在
			result.setErrorcode("20006");
			result.setMsg("此用户不存在");
			return result;
		}
		if(!jsonObject.optString("token").equals(tAccount.getToken())){
			result.setErrorcode("20002");
			result.setMsg("登录信息已过期，请重新获取验证码登录");
			return result;
		}
		String timestamp = jsonObject.optString("timestamp");
		String authenticator = jsonObject.optString("authenticator");
		if (accountid.equals("")) {
			result.setErrorcode("20004");
			result.setMsg("账户id不能为空");
			return result;
		}
		Map<String, String> map = AppSecurity.valid(timestamp, authenticator);
		if (!map.get(AppSecurity.code).equals(AppSecurity.success)) {
			if(map.get("code").equals("20025")){
				//说明是时间戳过期
				result.setErrorcode(map.get("code"));
				result.setMsg(map.get("msg"));
				return result;
			}
			result.setErrorcode("20005");
			result.setMsg("认证失败");
			return result;
		}
		// 2.向BSS获取账户信息
		String url = PropertyFactory.getProperty("BSSADDR");
		try {
			String requestJson = "{\"accountId\":\"" + accountid +"\",\"asId\":\""+"1"+ "\"}";
			log.info("request requestJson" + requestJson);
			String textEntity = HttpsUtil.doPost(url + "/user/queryInfoById", requestJson);
			log.info("response textEntity = " + textEntity);
			JSONObject RegJsonResult = JSONObject.fromObject(textEntity);
			result = (AccountVo) JSONObject.toBean(RegJsonResult, AccountVo.class);
			if (!result.getErrorcode().equals("00000")) {
				// 如果BSS返回不可以
				return result;
			}
		} catch (Exception ex) {
			log.error("向BSS查询账户异常 " + ex.getMessage(), ex);
			result.setErrorcode("21001");
			result.setMsg("查询账户失败");
			return result;
		}
		return result;
	}

	/**
	 * 通过账户id向BSS查询账户余额
	 * @param reqText
	 *          请求数据
	 * @return
	 */
	public String getAccountBalanceById(String reqTexts) {
		log.info("get account balance "+reqTexts);
		JSONObject reqText=JSONObject.fromObject(reqTexts);
		JSONObject res=new JSONObject();
		log.info("check version start ");
		String version = reqText.optString("version");
		JSONObject standVersionCheck = VersionUtil.standVersionCheck(version);
		if(!standVersionCheck.optString("errorcode").equals("00000")){
			res.put("errorcode",standVersionCheck.optString("errorcode"));
			res.put("msg",standVersionCheck.optString("msg"));
			return res.toString();
		}
		
		String accountid = reqText.optString("accountid");
		log.info("check token");
		TAccount tAccount = accountMapper.selectByPrimaryKey(accountid);
		if(tAccount==null){
			//不存在
			res.put("errorcode","20006");
			res.put("msg", "此用户不存在");
			return res.toString();
		}
		if(!reqText.optString("token").equals(tAccount.getToken())){
			res.put("errorcode","20002");
			res.put("msg", "登录信息已过期，请重新获取验证码登录");
			return res.toString();
		}
		String timestamp = reqText.optString("timestamp");
		String authenticator = reqText.optString("authenticator");
		log.info("验证账户id ");
		if (accountid.equals("")) {
			res.put("errorcode", "20004");
			res.put("msg", "账户id不能为空");
			return res.toString();
		} else {
			TAccount user = accountMapper.selectByPrimaryKey(accountid);
			if (user == null) {
				res.put("errorcode", "20005");
				res.put("msg", "此账户不存在");
				return res.toString();
			}
		}
		
		Map<String, String> map = AppSecurity.valid(timestamp, authenticator);
		if (!map.get(AppSecurity.code).equals(AppSecurity.success)) {
			if(map.get("code").equals("20025")){
				//说明是时间戳过期
				res.put("errorcode", map.get("code"));
				res.put("msg", map.get("msg"));
				return res.toString();
			}
			res.put("errorcode", "20005");
			res.put("msg", "认证失败");
			return res.toString();
		}
		
		// 2.向BSS获取余额
		log.info("向bss 查询余额 ");
		String url = PropertyFactory.getProperty("BSSADDR");
		log.info("url ="+url);
		try {
			String requestJson = "{\"accountId\":\"" + accountid +"\",\"asId\":\""+"1"+ "\"}";
			log.info("request requestJson" + requestJson);
			String textEntity = HttpsUtil.doPost(url + "/user/queryInfoById", requestJson);
			log.info("response textEntity = " + textEntity);
			if(textEntity==null){
				log.info("向恋家bss查询用户账户时返回了null ");
				res.put("errorcode", "20014");
				res.put("msg", "查询余额失败");
				return res.toString();
			}
			JSONObject RegJsonResult = JSONObject.fromObject(textEntity);
			String errorcode = RegJsonResult.optString("errorcode");
			if (!errorcode.equals("00000")) {
				res.put("errorcode", errorcode);
				res.put("msg", RegJsonResult.optString("msg"));
				return res.toString();
			}
			res.put("balance", RegJsonResult.optLong("balance"));
			//2016-02-01 16:48添加
			res.put("restAmount", RegJsonResult.optString("restAmount"));
		} catch (Exception ex) {
			log.error("向BSS查询余额异常 " + ex.getMessage(), ex);
			res.put("errorcode", "20014");
			res.put("msg", "查询余额失败");
			return res.toString();
		}
		res.put("errorcode", "00000");
		res.put("msg", "查询余额成功");
		return res.toString();
	}

	/**
	 * 续费/变更
	 * @param jsonObject
	 * @return
	 */
	public JSONObject bookProduct(JSONObject jsonObject) {
		// 向PS发起订购,将全部客户端请求消息及其他必须信息全部传与PS
		// 1.先从客户端拿到 accountid type maxNum count paytype optype timestamp authenticator
		// 2.从BSS获取当前用户的账户信息 包括 套餐类型 余额
		// 3.判断当前操作是否允许 变更/续费
		// 4.从本地库中查询资费信息 计算金额 应付金额是否大于余额
		// 5.确认无误后将指定信息发送给PS做支付
		// 6.将PS返回的支付凭证返回给客户端
		log.info("续费start ....");
		log.info("accept data "+jsonObject.toString());
		JSONObject j = new JSONObject();
		
		log.info("check version start ");
		String version = jsonObject.optString("version");
		JSONObject standVersionCheck = VersionUtil.standVersionCheck(version);
		if(!standVersionCheck.optString("errorcode").equals("00000")){
			jsonObject.put("errorcode",standVersionCheck.optString("errorcode"));
			jsonObject.put("msg",standVersionCheck.optString("msg"));
			return jsonObject;
		}
		
		String accountid = jsonObject.optString("accountid");
		log.info("check token");
		TAccount tAccount = accountMapper.selectByPrimaryKey(accountid);
		if(tAccount==null){
			//不存在
			j.put("errorcode","20006");
			j.put("msg", "此用户不存在");
			return j;
		}
		if(!jsonObject.optString("token").equals(tAccount.getToken())){
			j.put("errorcode","20002");
			j.put("msg", "登录信息已过期，请重新获取验证码登录");
			return j;
		}
		
		int type = 1;
		int maxNum = 10;
		log.info("maxNum "+maxNum);
		int count = jsonObject.optInt("count");
		log.info("count "+count);
		int paytype = jsonObject.optInt("paytype");
		//3.3.1 加入  设备信息
		String device_info=jsonObject.optString("device_info");
		int optype = 2;
		log.info("optype "+optype);
		Long fee = jsonObject.optLong("fee");
		log.info("fee "+fee+"");
		String timestamp = jsonObject.optString("timestamp");
		String authenticator = jsonObject.optString("authenticator");
		if (accountid.equals("")) {
			j.put("errorcode", "20000");
			j.put("msg", "用户id不存在");
			return j;
		}else{
			TAccount user = accountMapper.selectByPrimaryKey(accountid);
			if (user == null) {
				j.put("errorcode", "20005");
				j.put("msg", "此账户不存在");
				return j;
			}
		}
		Map<String, String> map = AppSecurity.valid(timestamp, authenticator);
		if (!map.get(AppSecurity.code).equals(AppSecurity.success)) {
			if(map.get("code").equals("20025")){
				//说明是时间戳过期
				j.put("errorcode", map.get("code"));
				j.put("msg", map.get("msg"));
				return j;
			}
			j.put("errorcode", "20005");
			j.put("msg", "认证失败");
			return j;
		}
		// 2.基本校验完毕,调用本类中方法向BSS查询用户账户信息
		JSONObject accountInfo = new JSONObject();
		accountInfo.put("accountid", accountid);
		accountInfo.put("timestamp", timestamp);
		accountInfo.put("authenticator", authenticator);
		log.info("向BSS查询账户信息 ....");
		//AccountVo accountVo = this.getAccountInfoById(accountInfo);
		// 3.判断当前操作是否允许
		//int resourceType = accountVo.getType();
		//log.info("账户原套餐类型  "+resourceType);
		//if (optype==2) {
			// 如果是续订,验证类型和方数是否都相同
		//	if (!(resourceType==type&&maxNum==accountVo.getMaxNum())) {
				// 如果当前类型与原始类型不一致
		//		j.put("errorcode", "20004");
		//		j.put("msg", "续订只能原套餐相同");
				//return j;
		//	}
		//}
		// 查询当前费率
		log.info("获取当前费率 ....");
		TFeeExample example = new TFeeExample();
		example.createCriteria().andIdIsNotNull();
		List<TFee> feeList = feeMapper.selectByExample(example);
		if (!(feeList != null && feeList.size() > 0)) {
			// 如果没有查询到结果
			j.put("errorcode", "20022");
			j.put("msg", "操作失败,请稍后重试");
			return j;
		}
		// 遍历匹配当前操作,计算出应付金额
		Long orderMoney = Long.valueOf(0);// 应付金额
		int actMaxNum = Integer.valueOf(maxNum);
		int actcount = Integer.valueOf(count);
		int actype = Integer.valueOf(type);
		for (TFee tFee : feeList) {
			if (tFee.getType() == actype) {
				// 如果类型相同,判断方数是否相同
				if (tFee.getMaxnum() == actMaxNum) {
					// 如果类型和方数都相同,那么获取其价格
					orderMoney =  tFee.getPrice()* actcount;
				}
			}
		}
		log.info("总金额  "+orderMoney);
		// 判断应付金额是否大于0
		// 判断客户端算得的金额与本服务器算得的是否一致且
		//Long actOrderMoney=orderMoney - accountVo.getBalance();
		//log.info("应付金额 "+actOrderMoney);
		if(optype==2){
			//之前校验无误后,如果是续费,则算应付金额时不再减去余额
			//actOrderMoney=orderMoney;
			log.info("如果为续费,则不计余额,此时应付金额为   "+orderMoney);
		}
		//判断客户端提交应付金额与服务端算得是否相等,
		if(!(orderMoney-fee==0)){
			j.put("errorcode", "20004");
			j.put("msg", "操作失败,应付金额有误");
			return j;
		}
		log.info("商品描述 .....");
		StringBuilder body=new StringBuilder();
		if(type==1){
			body.append(" 计时  "+count+" 百分钟 ");
		}
		log.info("订购参数为 "+body.toString());
		JSONObject toPs=new JSONObject();
		//将信息发送给PS
		JSONObject sendResult=new JSONObject();
		String url = PropertyFactory.getProperty("PSADDR");
		if(paytype==1){
		//如果是微信支付
			url=url+"/pay/wx";
			toPs.put("spbill_create_ip", jsonObject.optString("spbill_create_ip"));
		}else{
			//否则为支付宝支付(暂时只有这两种)
			url=url+"/pay/ali";
		}
		toPs.put("body",body.toString());
		toPs.put("accountId", accountid);
		toPs.put("type", type);
		toPs.put("maxNum", maxNum);
		toPs.put("count", count);
		toPs.put("orderMoney", orderMoney);
		toPs.put("operateType", optype);
		toPs.put("device_info", device_info);
		toPs.put("tradetype", "APP");
		
		toPs.put("asId", "1");
		toPs.put("adapterId", "2");
		//告诉ps该将支付结果通知给哪个adapter 默认1为云企通的as,2为恋家as 后续再增加
		//toPs.put("adapterid", 2);
		if(jsonObject.optString("platform").equals("androidtv")){
			//如果是安卓tv则支付方式为Native扫码支付
			toPs.put("tradetype", "NATIVE");
		}
		log.info("url = " + url);
		try {
			String requestJson =toPs.toString();
			log.info("request requestJson" + requestJson);
			requestJson=URLEncoder.encode(requestJson);
			String textEntity = HttpsUtil.doPost(url, requestJson);
			log.info("response textEntity = " + textEntity);
			if(textEntity==null){
				log.info("向LJPayServer请求支付异常,返回了null ");
				j.put("errorcode","24004");
				j.put("msg","请求支付失败");
				return j;
			}
			sendResult = JSONObject.fromObject(textEntity);
			if (!sendResult.optString("errorcode").equals("00000")) {
				// 如果PS返回不成功
				j.put("errorcode",sendResult.optString("errorcode"));
				j.put("msg",sendResult.optString("msg"));
				return j;
			}
		} catch (Exception ex) {
			log.error("向PS获取支付凭证时异常 " + ex.getMessage(), ex);
			j.put("errorcode","24004");
			j.put("msg","支付失败");
			return j;
		}
		//如果没有出错,取回支付凭证返回给客户端
		if(paytype==1){
			//如果是微信支付
			j.put("errorcode", "00000");
			j.put("msg", "成功");
			if(jsonObject.optString("platform").equals("androidtv")){
				//如果是安卓tv则返回扫码支付图片url即可
				j.put("codeurl", sendResult.optString("code_url"));
				return j;
			}
			//之前没有,每个回复都要有errorcode  切记
			j.put("appid", sendResult.optString("appid"));
			j.put("partnerid", sendResult.optString("mch_id"));
			j.put("prepayid", sendResult.optString("prepay_id"));
			j.put("package", sendResult.optString("package"));
			j.put("noncestr", sendResult.optString("nonce_str"));
			j.put("timestamp", sendResult.optString("timestamp"));
			j.put("sign", sendResult.optString("sign"));
			return j;
		}else{
			//支付宝支付
			j.put("errorcode", "20022");
			j.put("msg", "暂不支持微信支付以外的其他支付");
		}
		//插入了什么,就返回什么.
		return j;
	}

	/**
	 * 获取费率
	 * @param jsonObject
	 * @return 从数据库中获取,按类别分成三个list
	 */
	/*public PackFeesVo getFees(JSONObject jsonObject) {
		log.info("get fees 获取费率 "+jsonObject.toString());
		PackFeesVo packFe = new PackFeesVo();
		try {
			TFeeExample example = new TFeeExample();
			example.createCriteria().andIdIsNotNull();
			List<TFee> feeList = feeMapper.selectByExample(example);
			if (!(feeList != null && feeList.size() > 0)) {
				packFe.setErrorcode("20022");
				packFe.setMsg("查询资费信息失败");
				return packFe;
			}
			List<TFee> minfees = new ArrayList<TFee>();
			List<TFee> monthfees = new ArrayList<TFee>();
			List<TFee> yearfees = new ArrayList<TFee>();
			for (TFee tFee : feeList) {
				TFee fee1 = new TFee();
				if (tFee.getType() == 1) {
					// 如果是计时
					fee1.setPrice(tFee.getPrice());
					fee1.setMaxnum(10);
					fee1.setType(1);
					minfees.add(fee1);
				} else if (tFee.getType() == 2) {
					fee1.setPrice(tFee.getPrice());
					fee1.setMaxnum(tFee.getMaxnum());
					fee1.setType(2);
					monthfees.add(fee1);
				} else {
					fee1.setPrice(tFee.getPrice());
					fee1.setMaxnum(tFee.getMaxnum());
					fee1.setType(3);
					yearfees.add(fee1);
				}
			}
			// 都没有问题
			packFe.setMinfees(minfees);
			packFe.setMonthfees(monthfees);
			packFe.setYearfees(yearfees);
			packFe.setErrorcode("00000");
			packFe.setMsg("成功");
		} catch (Exception e) {
			log.error("查询资费信息失败");
			packFe.setErrorcode("20022");
			packFe.setMsg("查询资费信息异常");
			return packFe;
		}
		return packFe;
	}*/
	/**
	 * 收到PS支付成功消息后向BSS发起 续费/变更
	 * @param jsonObject
	 * @return
	 */
	public void orderWhileNofiy(JSONObject jsonObject) {
		log.info("支付成功,向BSS发起充值  info from ps "+jsonObject.toString());
		JSONObject jobj=new JSONObject();
		
		jobj.put("accountId", jsonObject.optString("accountId"));
		//订单号   PS给
		jobj.put("orderNum", jsonObject.optString("orderNum"));
		jobj.put("type", jsonObject.optString("type"));
		jobj.put("maxNum", jsonObject.optString("maxNum"));
		jobj.put("count", jsonObject.optString("count"));
		
		jobj.put("adId", jsonObject.optString("asId"));
		jobj.put("adapterId", jsonObject.optString("adapterId"));
		
		//支付金额,long  
		jobj.put("orderMoney", jsonObject.optString("orderMoney"));
		jobj.put("operateType", jsonObject.optString("operateType"));
		//向BSS赠送充值
		JSONObject sendResult=new JSONObject();
		String bsurl = PropertyFactory.getProperty("BSSADDR");
		log.info("url = " + bsurl);
		try {
			String requestJson =jobj.toString();
			log.info("request requestJson" + requestJson);
			String textEntity = HttpsUtil.doPost(bsurl+"/product/order", requestJson);
			log.info("response textEntity = " + textEntity);
			if(textEntity==null){
				log.error("接收PS支付成功通知后向bss续费失败");
			}
			sendResult = JSONObject.fromObject(textEntity);
			if (!sendResult.optString("errorcode").equals("00000")) {
				// 如果BSS返回赠送失败
				log.info(sendResult.optString("errorcode"));
				log.info("向BSS 续订 "+sendResult.optString("msg"));
			}
		} catch (Exception ex) {
			log.error("向BSS异常 "+ jsonObject.optString("operateType") + ex.getMessage(), ex);
		}
	}
}
