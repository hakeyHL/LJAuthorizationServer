package cn.com.yunqitong.domain;

public class TMailing {
    private String mccountid;

    private String faccountid;

    public String getMccountid() {
        return mccountid;
    }

    public void setMccountid(String mccountid) {
        this.mccountid = mccountid == null ? null : mccountid.trim();
    }

    public String getFaccountid() {
        return faccountid;
    }

    public void setFaccountid(String faccountid) {
        this.faccountid = faccountid == null ? null : faccountid.trim();
    }
}