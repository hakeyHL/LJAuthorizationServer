package cn.com.yunqitong.vo;

import java.util.ArrayList;
import java.util.List;

/**
* 项目名称：LJAuthorizationServer   
* 类名称：MailingVo   
* 创建人：huli   
* 创建时间：2016-2-3 上午9:37:47      
*
 */
public class MailingVo {
	private String errorcode;
	private String msg;
	private List<PhoneVo> phoneList=new ArrayList<PhoneVo>();
	public String getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<PhoneVo> getPhoneList() {
		return phoneList;
	}
	public void setPhoneList(List<PhoneVo> phoneList) {
		this.phoneList = phoneList;
	}
	
}
