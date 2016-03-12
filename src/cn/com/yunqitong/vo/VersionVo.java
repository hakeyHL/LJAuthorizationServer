package cn.com.yunqitong.vo;

import java.math.BigDecimal;

public class VersionVo {

	private String errorcode;
	private String msg;
	private String version;
	private String url;
	private String md5;
	private BigDecimal size;
	private String apppath;
	private String firewirename;
	public String getFirewirename() {
		return firewirename;
	}
	public void setFirewirename(String firewirename) {
		this.firewirename = firewirename;
	}
	public String getApppath() {
		return apppath;
	}
	public void setApppath(String apppath) {
		this.apppath = apppath;
	}
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
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public BigDecimal getSize() {
		return size;
	}
	public void setSize(BigDecimal size) {
		this.size = size;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	private String remark;
}
