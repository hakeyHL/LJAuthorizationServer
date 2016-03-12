package cn.com.yunqitong.vo;

import java.util.ArrayList;
import java.util.List;

import cn.com.yunqitong.domain.TFee;

public class PackFeesVo {
	private String errorcode;
	private String msg;
	List<TFee> minfees=new ArrayList<TFee>();
	List<TFee> monthfees=new ArrayList<TFee>();
	List <TFee>yearfees=new ArrayList<TFee>();
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
	public List<TFee> getMinfees() {
		return minfees;
	}
	public void setMinfees(List<TFee> minfees) {
		this.minfees = minfees;
	}
	public List<TFee> getMonthfees() {
		return monthfees;
	}
	public void setMonthfees(List<TFee> monthfees) {
		this.monthfees = monthfees;
	}
	public List<TFee> getYearfees() {
		return yearfees;
	}
	public void setYearfees(List<TFee> yearfees) {
		this.yearfees = yearfees;
	}
	
	
	
}
