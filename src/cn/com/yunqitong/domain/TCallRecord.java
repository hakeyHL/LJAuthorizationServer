package cn.com.yunqitong.domain;

public class TCallRecord {
    private String callid;

    private String calleds;

    private Integer calledstatus;

    private String callerid;

    private Integer callerstatus;

    private String meetingid;

    private String createtime;

    public String getCallid() {
        return callid;
    }

    public void setCallid(String callid) {
        this.callid = callid == null ? null : callid.trim();
    }

    public String getCalleds() {
        return calleds;
    }

    public void setCalleds(String calleds) {
        this.calleds = calleds == null ? null : calleds.trim();
    }

    public Integer getCalledstatus() {
        return calledstatus;
    }

    public void setCalledstatus(Integer calledstatus) {
        this.calledstatus = calledstatus;
    }

    public String getCallerid() {
        return callerid;
    }

    public void setCallerid(String callerid) {
        this.callerid = callerid == null ? null : callerid.trim();
    }

    public Integer getCallerstatus() {
        return callerstatus;
    }

    public void setCallerstatus(Integer callerstatus) {
        this.callerstatus = callerstatus;
    }

    public String getMeetingid() {
        return meetingid;
    }

    public void setMeetingid(String meetingid) {
        this.meetingid = meetingid == null ? null : meetingid.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }
}