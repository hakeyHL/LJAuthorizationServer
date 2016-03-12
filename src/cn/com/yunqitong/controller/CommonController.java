package cn.com.yunqitong.controller;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.yunqitong.domain.TShare;
import cn.com.yunqitong.logic.CommonLogic;
import cn.com.yunqitong.util.DateHelper;
import cn.com.yunqitong.vo.PicVo;
import cn.com.yunqitong.vo.RingVo;
import cn.com.yunqitong.vo.SuggestVo;
/**
* 项目名称：LJAuthorizationServer   
* 类名称：CommonController   
* 创建人：huli   
* 创建时间：2016-2-1 下午5:54:49      
 */
@RequestMapping("/mphone")
@Controller
public class CommonController {
	protected static Logger log=Logger.getLogger(CommonController.class);
	@Autowired
	private CommonLogic commonLogic;
	// 获取时间戳
		@RequestMapping("/get/timestamp")
		public String timestamp(HttpServletRequest request,
				HttpServletResponse response) {
			String jsonStr1 = "{\"timestamp\":\"" + DateHelper.parse(new Date())
					+ "\",\"errorcode\":\"00000\"}";
			JSONObject json = JSONObject.fromObject(jsonStr1);
			sendAppMessage(json.toString(), response);
			return null;
		}
		/**
		 * 检查版本
		 */
		@RequestMapping("/version")
		@ResponseBody
		public String  version(HttpServletRequest request, HttpServletResponse response) {
			log.info("version start ");
			String requestStr = getJsonFromRequest(request);
			log.info("reqest = " + requestStr);
			String res = this.commonLogic.check(requestStr);
			log.info("resposne content = " + res);
			sendAppMessage(res, response);
			return null;
		}
		
		/**
		 * 上传头像
		 */
		@RequestMapping("update/picture")
		@ResponseBody
		public String picture(HttpServletRequest request, HttpServletResponse response) {
			log.info("picture start ");
			PicVo picVo = this.commonLogic.upload(request);
			JSONObject jsonUser = JSONObject.fromObject(picVo);
			log.info("resposne content = " + jsonUser.toString());
			sendAppMessage(jsonUser.toString(), response);
			return null;
		}
		/**
		 * 上传铃声
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping("update/ring")
		@ResponseBody
		public String Ring(HttpServletRequest request, HttpServletResponse response) {
			log.info("upload ring  start ");
			RingVo ringVo = this.commonLogic.uploadRing(request);
			JSONObject jsonUser = JSONObject.fromObject(ringVo);
			log.info("resposne content = " + jsonUser.toString());
			sendAppMessage(jsonUser.toString(), response);
			return null;
		}
		/**
		 * 意见反馈
		 */
		@RequestMapping("/suggest")
		@ResponseBody
		public String suggest(HttpServletRequest request, HttpServletResponse response) {
			log.info("suggest start ");
			String requestStr = getJsonFromRequest(request);
			log.info("reqest = " + requestStr);
			SuggestVo vo = this.commonLogic.suggest(requestStr);
			JSONObject jsonUser = JSONObject.fromObject(vo);
			log.info("resposne content = " + jsonUser.toString());
			sendAppMessage(jsonUser.toString(), response);
			return null;
		}
		/**
		 * 获取分享信息
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping("/get/ShareInfo")
		@ResponseBody
		public String getShareInfo(HttpServletRequest request, HttpServletResponse response) {
			log.info("get shareInfo start ");
			String requestStr = getJsonFromRequest(request);
			log.info("reqest = " + requestStr);
			String  tShare= commonLogic.getShareInfo(requestStr);
			log.info("resposne content = " + tShare);
			sendAppMessage(tShare, response);
			return null;
		}
		
		public static void sendAppMessage(String message,
				HttpServletResponse response) {
			PrintWriter out = null;
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json");
			try {
				out = response.getWriter();
				out.println(message);
			} catch (Exception ee) {
				log.error(ee.getMessage(), ee);
			} finally {
				out.close();
			}
		}
		public static String getJsonFromRequest(HttpServletRequest request) {
			StringBuffer info = new java.lang.StringBuffer();
			try {
				InputStream in = request.getInputStream();
				BufferedInputStream buf = new BufferedInputStream(in);
				byte[] buffer = new byte[1024];
				int iRead;
				while ((iRead = buf.read(buffer)) != -1) {
					info.append(new String(buffer, 0, iRead, "UTF-8"));
				}
			} catch (Exception ee) {
				log.error(ee.getMessage(), ee);
			}
			return URLDecoder.decode(info.toString());
		}
}
