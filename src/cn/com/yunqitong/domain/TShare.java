package cn.com.yunqitong.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TShare {
	private String errorcode;
	
	private String msg;
	
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

	private String shareid;

    private String friendscontent;

    private String friendspics;

    private String wechatcontent;

    private String smscontent;

    private String mailtitle;

    private String mailbody;

    private String copycontent;

    private String copyhint;

    private String qrcodepic;

    private String contactusinfo;

    private String contactusnumber;

    private Date createtime;

    public String getShareid() {
        return shareid;
    }

    public void setShareid(String shareid) {
        this.shareid = shareid == null ? null : shareid.trim();
    }

    public String getFriendscontent() {
        return friendscontent;
    }

    public void setFriendscontent(String friendscontent) {
        this.friendscontent = friendscontent == null ? null : friendscontent.trim();
    }

    public String getFriendspics() {
        return friendspics;
    }

    public void setFriendspics(String friendspics) {
        this.friendspics = friendspics == null ? null : friendspics.trim();
    }

    public String getWechatcontent() {
        return wechatcontent;
    }

    public void setWechatcontent(String wechatcontent) {
        this.wechatcontent = wechatcontent == null ? null : wechatcontent.trim();
    }

    public String getSmscontent() {
        return smscontent;
    }

    public void setSmscontent(String smscontent) {
        this.smscontent = smscontent == null ? null : smscontent.trim();
    }

    public String getMailtitle() {
        return mailtitle;
    }

    public void setMailtitle(String mailtitle) {
        this.mailtitle = mailtitle == null ? null : mailtitle.trim();
    }

    public String getMailbody() {
        return mailbody;
    }

    public void setMailbody(String mailbody) {
        this.mailbody = mailbody == null ? null : mailbody.trim();
    }

    public String getCopycontent() {
        return copycontent;
    }

    public void setCopycontent(String copycontent) {
        this.copycontent = copycontent == null ? null : copycontent.trim();
    }

    public String getCopyhint() {
        return copyhint;
    }

    public void setCopyhint(String copyhint) {
        this.copyhint = copyhint == null ? null : copyhint.trim();
    }

    public String getQrcodepic() {
        return qrcodepic;
    }

    public void setQrcodepic(String qrcodepic) {
        this.qrcodepic = qrcodepic == null ? null : qrcodepic.trim();
    }

    public String getContactusinfo() {
        return contactusinfo;
    }

    public void setContactusinfo(String contactusinfo) {
        this.contactusinfo = contactusinfo == null ? null : contactusinfo.trim();
    }

    public String getContactusnumber() {
        return contactusnumber;
    }

    public void setContactusnumber(String contactusnumber) {
        this.contactusnumber = contactusnumber == null ? null : contactusnumber.trim();
    }

    public String getCreatetime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createtime) ;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}