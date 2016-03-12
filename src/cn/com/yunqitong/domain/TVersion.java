package cn.com.yunqitong.domain;
import java.util.Date;
public class TVersion{
	private String name;

    private String firewirename;

    private String apppath;

    private Date createtime;

    private String firewiresize;

    private String remark;

    private String platform;

    private String filemd5;

    private byte[] file;
    
    private String errorcode = "00000";
    
    private String msg;
    
    private String build;

    public String getFirewirename() {
        return firewirename;
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getBuild() {
		return build;
	}

	public void setBuild(String build) {
		this.build = build;
	}

	public void setFirewirename(String firewirename) {
        this.firewirename = firewirename == null ? null : firewirename.trim();
    }

    public String getApppath() {
        return apppath;
    }

    public void setApppath(String apppath) {
        this.apppath = apppath == null ? null : apppath.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getFirewiresize() {
        return firewiresize;
    }

    public void setFirewiresize(String firewiresize) {
        this.firewiresize = firewiresize == null ? null : firewiresize.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform == null ? null : platform.trim();
    }

    public String getFilemd5() {
        return filemd5;
    }

    public void setFilemd5(String filemd5) {
        this.filemd5 = filemd5 == null ? null : filemd5.trim();
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}