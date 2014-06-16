package com.oakhole.financialManage.model;

import java.util.Date;

import com.oakhole.common.model.BaseEntity;

@SuppressWarnings("serial")
public class Financial extends BaseEntity {
	private String account;
	private String actAccount;
	private int actCount;
	private Date actTime;
	private int actType;
	private int actMethod;
	private String memo;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getActAccount() {
		return actAccount;
	}

	public void setActAccount(String actAccount) {
		this.actAccount = actAccount;
	}

	public int getActCount() {
		return actCount;
	}

	public void setActCount(int actCount) {
		this.actCount = actCount;
	}

	public Date getActTime() {
		return actTime;
	}

	public void setActTime(Date actTime) {
		this.actTime = actTime;
	}

	public int getActType() {
		return actType;
	}

	public void setActType(int actType) {
		this.actType = actType;
	}

	public int getActMethod() {
		return actMethod;
	}

	public void setActMethod(int actMethod) {
		this.actMethod = actMethod;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
