package cn.com.yunqitong.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.com.yunqitong.logic.MeetingLogic;
import cn.com.yunqitong.util.HttpsUtil;
/**
* 项目名称：LJAuthorizationServer   
* 类名称：MeetingController   
* 创建人：huli   
* 创建时间：2016-2-2 上午11:39:10      
 */
@Controller
@RequestMapping("mphone")
public class MeetingController {
	protected Logger log=Logger.getLogger(MeetingController.class);
	@Autowired
	private MeetingLogic metingLogic;
	/**
	 * 主叫
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/calling")
	public String calling(HttpServletRequest request, HttpServletResponse response) {
		String json = HttpsUtil.getJsonFromRequest(request);
		String callingResult = metingLogic.calling(json);
		HttpsUtil.sendAppMessage(callingResult, response);
		return null;
	}
	
	/**
	 * 增加通话人
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addPerson")
	public String addPerson(HttpServletRequest request, HttpServletResponse response) {
		String json = HttpsUtil.getJsonFromRequest(request);
		String callingResult = metingLogic.addPerson(json);
		HttpsUtil.sendAppMessage(callingResult, response);
		return null;
	}
	
	/**
	 * 结束通话
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/endMeeting")
	public String endMeeting(HttpServletRequest request, HttpServletResponse response) {
		String json = HttpsUtil.getJsonFromRequest(request);
		String callingResult = metingLogic.endMeeting(json);
		HttpsUtil.sendAppMessage(callingResult, response);
		return null;
	}
	
	//被叫用户未接接听状态上报
	@RequestMapping(value = "/reportAnswerStatus")
	public String reportAnswerStatus(HttpServletRequest request, HttpServletResponse response){
		String json = HttpsUtil.getJsonFromRequest(request);
		// 判断请求json格式是否错误
		String reuslt = this.metingLogic.notifyAnswerStatus(json);
		HttpsUtil.sendAppMessage(reuslt, response);
		return null;
	} 
	
	//检查被叫接听结果
	@RequestMapping(value = "/checkAnswerStatus")
	public String checkAnswerStatus(HttpServletRequest request, HttpServletResponse response){
		String json = HttpsUtil.getJsonFromRequest(request);
		// 判断请求json格式是否错误
		String reuslt = this.metingLogic.checkAnswerStatus(json);
		HttpsUtil.sendAppMessage(reuslt, response);
		return null;
	} 
	
	/**
	 * 加入会议验证
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/join")
	public String valid(HttpServletRequest request, HttpServletResponse response) {
		String json = HttpsUtil.getJsonFromRequest(request);
		// 判断请求json格式是否错误
		String reuslt = this.metingLogic.valid(json);
		HttpsUtil.sendAppMessage(reuslt, response);
		return null;
	}
	/**
	 * 查询会议状态
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/get/metstate")
	public String  getMeetingState(HttpServletRequest request, HttpServletResponse response) {
		String json = HttpsUtil.getJsonFromRequest(request);
		// 判断请求json格式是否错误
		String reuslt = this.metingLogic.getMeetingState(json);
		HttpsUtil.sendAppMessage(reuslt, response);
		return null;
	}
	
	@RequestMapping(value = "/ptocalled")
	public String  push2Called(HttpServletRequest request, HttpServletResponse response) {
		String json = HttpsUtil.getJsonFromRequest(request);
		// 判断请求json格式是否错误
		String reuslt = this.metingLogic.push2Called(json);
		HttpsUtil.sendAppMessage(reuslt, response);
		return null;
	}
}
