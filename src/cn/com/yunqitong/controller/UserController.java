package cn.com.yunqitong.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.com.yunqitong.logic.AccountLogic;
import cn.com.yunqitong.logic.UserLogic;
import cn.com.yunqitong.util.HttpsUtil;
import cn.com.yunqitong.util.IpUtil;
/**
* 项目名称：LJAuthorizationServer   
* 类名称：UserController   
* 创建人：huli   
* 创建时间：2016-2-2 上午11:32:21      
 */
@RequestMapping("/mphone")
@Controller
public class UserController {
	protected Logger log=Logger.getLogger(UserController.class);
	@Autowired
	private AccountLogic accountLogic;
	@Autowired
	private UserLogic userLogic;
	/**
	 * 注册/登录
	 * @param request  请求数据
	 * @param response 响应数据
 	 * @return
	 */
	@RequestMapping("/get/code")
	public String Login(HttpServletRequest request,HttpServletResponse response){
		String requestText = HttpsUtil.getJsonFromRequest(request);
		log.info("get valicode  start "+requestText);
		log.info("enter to  get valicode method ... ");
		String loginresponse=userLogic.login(requestText);
		HttpsUtil.sendAppMessage(loginresponse, response);
		return null;
	}
	/**
	 * 验证码验证
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/login")
	public String validatecode(HttpServletRequest request,HttpServletResponse response){
		String requestText = HttpsUtil.getJsonFromRequest(request);
		log.info("validate code  start "+requestText);
		String loginresponse=userLogic.validateCode(requestText);
		HttpsUtil.sendAppMessage(loginresponse, response);
		return null;
	}
	/**
	 * 后台token登录
	 * @param request 请求信息
	 * @param response 响应信息
	 * @return
	 */
	@RequestMapping("back/login")
	public String backLogin(HttpServletRequest request,HttpServletResponse response){
		String requestText = HttpsUtil.getJsonFromRequest(request);
		log.info("back login start "+requestText);
		log.info("enter to  back login method ...");
		String loginresponse=userLogic.backLogin(requestText);
		HttpsUtil.sendAppMessage(loginresponse, response);
		return null;
	}
	/**
	 * 获取账户信息
	 * 
	 * @param object
	 *            请求数据
	 * @return
	 */
/*	@RequestMapping("/info")
	public String accountInfo(HttpServletRequest request, HttpServletResponse response) {
		String reqText = HttpsUtil.getJsonFromRequest(request);
		log.info("get account start :" + reqText);
		JSONObject jsonObject = JSONObject.fromObject(reqText);
		AccountVo accountVo = accountLogic.getAccountInfoById(jsonObject);
		JSONObject fromObject = JSONObject.fromObject(accountVo);
		HttpsUtil.sendAppMessage(fromObject.toString(), response);
		return null;
	}
*/
	/**
	 * 查询余额
	 * @param object
	 * @return
	 */
	@RequestMapping("/balance")
	public String balance(HttpServletRequest request, HttpServletResponse response) {
		String reqText = HttpsUtil.getJsonFromRequest(request);
		log.info("get account balance start :" + reqText);
		String  jsonObject= accountLogic.getAccountBalanceById(reqText);
		HttpsUtil.sendAppMessage(jsonObject, response);
		return null;
	}

	@RequestMapping("/order")
	public String orderProduct(HttpServletRequest request, HttpServletResponse response) {
		String appIP = IpUtil.getIpAddr(request);
		log.info("获得请求端 request ip " + appIP);
		String reqText = HttpsUtil.getJsonFromRequest(request);
		log.info("请求信息 续费/变更  order product start :" + reqText);
		JSONObject jsonObject = JSONObject.fromObject(reqText);
		jsonObject.put("spbill_create_ip", appIP);
		jsonObject = accountLogic.bookProduct(jsonObject);
		HttpsUtil.sendAppMessage(jsonObject.toString(), response);
		return null;
	}

	/*@RequestMapping("/fees")
	public String getFee(HttpServletRequest request, HttpServletResponse response) {
		String reqText = HttpsUtil.getJsonFromRequest(request);
		log.info("get product fees  :" + reqText);
		JSONObject jsonObject = JSONObject.fromObject(reqText);
		JsonConfig con = new JsonConfig();
		String[] excludes = { "id", "unit" };
		con.setExcludes(excludes);
		PackFeesVo li = accountLogic.getFees(jsonObject);
		JSONObject fromObject = JSONObject.fromObject(li, con);
		HttpsUtil.sendAppMessage(fromObject.toString(), response);
		return null;
	}*/

	/**
	 * 获取PS支付结果通知
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/pay/wx")
	public String appOrderData(HttpServletRequest request, HttpServletResponse response) {
		log.info("校验通知是否来自信任的ip ...");
		// 获取请求端ip
		String reqIP = IpUtil.getIpAddr(request);
		log.info("请求ip为 " + reqIP);
		log.info("validate ip start ");
		boolean checkIpAccess = IpUtil.checkIpAccess(reqIP);
		log.info("check result " + checkIpAccess + "");
		if (!checkIpAccess) {
			// 验证失败
			JSONObject userResult = new JSONObject();
			userResult.put("errorcode", "23003");
			userResult.put("msg", "您的Ip不合法,服务器拒绝了您的请求");
			log.info("response text " + userResult.toString());
			HttpsUtil.sendAppMessage(userResult.toString(), response);
			return null;
		}
		String reqText = HttpsUtil.getJsonFromRequest(request);
		log.info("get notify PS  :" + reqText);
		JSONObject jsonObject = JSONObject.fromObject(reqText);
		accountLogic.orderWhileNofiy(jsonObject);
		return null;
	}
	/**
	 * 更改昵称
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/update/nickname")
	public String updateNickName(HttpServletRequest request, HttpServletResponse response) {
		log.info("update nickname start ..");
		String reqText = HttpsUtil.getJsonFromRequest(request);
		log.info("accept json "+reqText);
		String resText=userLogic.updateNickname(reqText);
		HttpsUtil.sendAppMessage(resText, response);
		return null;
	}
	
	@RequestMapping("/update/userstate")
	public String updateEnable(HttpServletRequest request, HttpServletResponse response) {
		log.info("update user enable start ..");
		String reqText = HttpsUtil.getJsonFromRequest(request);
		log.info("accept json "+reqText);
		String resText=userLogic.updateUserEnable(reqText);
		HttpsUtil.sendAppMessage(resText, response);
		return null;
	}
	/**
	 * 用户信息查看
	 * @param request
	 * @param response
	 * @return
	 */
/*	@RequestMapping("/userinfo")
	public String getUserInfo(HttpServletRequest request, HttpServletResponse response) {
		log.info("get userInfo start ..");
		String reqText = HttpsUtil.getJsonFromRequest(request);
		log.info("accept json "+reqText);
		String resText=userLogic.getUserInfo(reqText);
		HttpsUtil.sendAppMessage(resText, response);
		return null;
	}*/
}
