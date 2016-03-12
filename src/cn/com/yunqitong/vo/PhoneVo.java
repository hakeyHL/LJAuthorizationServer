package cn.com.yunqitong.vo;
/**
* 项目名称：LJAuthorizationServer   
* 类名称：PhoneVo   
* 创建人：huli   
* 创建时间：2016-2-3 上午9:39:09      
*
 */
public class PhoneVo {
	private String phone;
    private String pic;
    private String nickname;
    private String account;
    private String picurl;
    private int status;
    
    
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPicurl() {
		return picurl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
    
}
