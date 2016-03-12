package cn.com.yunqitong.vo;
/**
 * 
 * @author HL
 * @Description : 为了解决想pushserver发送要推送的账户数组创建的vo
 * @CreateDate ; 2016-2-20 下午5:34:22
 */
public class ClientVo {
	private String clientid;
	private String platform;
	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
}
