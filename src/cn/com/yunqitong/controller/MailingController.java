package cn.com.yunqitong.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.yunqitong.logic.MailingLogic;
import cn.com.yunqitong.util.HttpsUtil;
import cn.com.yunqitong.vo.MailingVo;
/**
* 项目名称：LJAuthorizationServer   
* 类名称：MailingController   
* 创建人：huli   
* 创建时间：2016-2-2 下午7:08:07      
 */
@RequestMapping("/mphone")
@Controller
public class MailingController {
	protected static Logger log=Logger.getLogger(MailingController.class);
	@Autowired
	private MailingLogic mailingLogic;
	/**
	 * 获取通讯录列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/mailing/list")
	public String getMailingList(HttpServletRequest request, HttpServletResponse response){
		String jsonFromRequest = HttpsUtil.getJsonFromRequest(request);
		log.info("accept json "+jsonFromRequest);
		MailingVo resJson=mailingLogic.getMailingList(jsonFromRequest);
		JsonConfig jsonConfig=new JsonConfig();
		String excludes[]=new String[]{"account","picurl"};
		jsonConfig.setExcludes(excludes);
		JSONObject mainlingList=JSONObject.fromObject(resJson,jsonConfig);
		HttpsUtil.sendAppMessage(mainlingList.toString(), response);
		return null;
	}
	/**
	 * 通讯录筛选联系人
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/mailing/filter")
	public String getMailingListFilter(HttpServletRequest request, HttpServletResponse response){
		String jsonFromRequest = HttpsUtil.getJsonFromRequest(request);
		log.info("accept json "+jsonFromRequest);
		String resJson=mailingLogic.fileterMailingList(jsonFromRequest);
		HttpsUtil.sendAppMessage(resJson, response);
		return null;
	}
	/**
	 * 通讯录添加联系人
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/mailing/add")
	public String getMailingADD(HttpServletRequest request, HttpServletResponse response){
		String jsonFromRequest = HttpsUtil.getJsonFromRequest(request);
		log.info("accept json "+jsonFromRequest);
		String resJson=mailingLogic.addMailingList(jsonFromRequest);
		HttpsUtil.sendAppMessage(resJson, response);
		return null;
	}
	/**
	 * 通讯录删除联系人
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/mailing/delete")
	public String getMailingDelete(HttpServletRequest request, HttpServletResponse response){
		String jsonFromRequest = HttpsUtil.getJsonFromRequest(request);
		log.info("accept json "+jsonFromRequest);
		String resJson=mailingLogic.deleteMailingList(jsonFromRequest);
		HttpsUtil.sendAppMessage(resJson, response);
		return null;
	}
}
