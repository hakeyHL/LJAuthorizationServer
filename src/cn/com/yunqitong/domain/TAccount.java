package cn.com.yunqitong.domain;

public class TAccount {
    private String accountid;

    private String nickname;

    private String account;

    private String deletetag;

    private String picurl;

    private String picuptime;

    private String ringurl;

    private String ringname;

    private String ringsize;

    private String ringuptime;

    private String enable;

    private String plateform;

    private String createtime;

    private Long maxnum;

    private String adpid;

    private String token;

    private String deviceinfo;

    private String pushid;

    private String remark;

    private String meetingnum;
    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid == null ? null : accountid.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getDeletetag() {
        return deletetag;
    }

    public void setDeletetag(String deletetag) {
        this.deletetag = deletetag == null ? null : deletetag.trim();
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl == null ? null : picurl.trim();
    }

    public String getPicuptime() {
        return picuptime;
    }

    public void setPicuptime(String picuptime) {
        this.picuptime = picuptime == null ? null : picuptime.trim();
    }

    public String getRingurl() {
        return ringurl;
    }

    public void setRingurl(String ringurl) {
        this.ringurl = ringurl == null ? null : ringurl.trim();
    }

    public String getRingname() {
        return ringname;
    }

    public void setRingname(String ringname) {
        this.ringname = ringname == null ? null : ringname.trim();
    }

    public String getRingsize() {
        return ringsize;
    }

    public void setRingsize(String ringsize) {
        this.ringsize = ringsize == null ? null : ringsize.trim();
    }

    public String getRinguptime() {
        return ringuptime;
    }

    public void setRinguptime(String ringuptime) {
        this.ringuptime = ringuptime == null ? null : ringuptime.trim();
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable == null ? null : enable.trim();
    }

    public String getPlateform() {
        return plateform;
    }

    public void setPlateform(String plateform) {
        this.plateform = plateform == null ? null : plateform.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    public Long getMaxnum() {
        return maxnum;
    }

    public void setMaxnum(Long maxnum) {
        this.maxnum = maxnum;
    }

    public String getAdpid() {
        return adpid;
    }

    public void setAdpid(String adpid) {
        this.adpid = adpid == null ? null : adpid.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getDeviceinfo() {
        return deviceinfo;
    }

    public void setDeviceinfo(String deviceinfo) {
        this.deviceinfo = deviceinfo == null ? null : deviceinfo.trim();
    }

    public String getPushid() {
        return pushid;
    }

    public void setPushid(String pushid) {
        this.pushid = pushid == null ? null : pushid.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getMeetingnum() {
        return meetingnum;
    }

    public void setMeetingnum(String meetingnum) {
        this.meetingnum = meetingnum == null ? null : meetingnum.trim();
    }
}