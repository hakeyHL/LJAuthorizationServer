package cn.com.yunqitong.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 用来包装账户对象 
 * 项目名称：AuthorizationServer 
 * 类名称：AccountVo 
 * 创建人：huli 
 * 创建时间：2015-12-21 下午12:05:05
 */
public class AccountVo {
	private String accountId;
	private String errorcode;
	private String msg;
	private String nickname;
	private String pic;
	private String name;
	private List<PhoneVo> phoneList=new ArrayList<PhoneVo>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	// 计费类型（1、计时；2包月；3包年）
	private int type;
	// 250小时/300天
	private Long balance;
	// 最大方数
	private int maxNum;
	// 剩余分钟/天数
	private Long restAmount;
	//应付金额
	private long orderMoney;
	//当前套餐单价
	private Long price;
	//订购数量
	private String count;
	//操作类型
	private int operateType;
	
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public List<PhoneVo> getPhoneList() {
		return phoneList;
	}
	public void setPhoneList(List<PhoneVo> phoneList) {
		this.phoneList = phoneList;
	}
	public long getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(long orderMoney) {
		this.orderMoney = orderMoney;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public int getOperateType() {
		return operateType;
	}
	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Long getBalance() {
		return balance;
	}
	public void setBalance(Long balance) {
		this.balance = balance;
	}
	public int getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}
	public Long getRestAmount() {
		return restAmount;
	}
	public void setRestAmount(Long restAmount) {
		this.restAmount = restAmount;
	}
}
