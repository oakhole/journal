package com.oakhole.sendStatistics.model;

import com.oakhole.common.model.BaseEntity;

@SuppressWarnings("serial")
public class SmsStatistics extends BaseEntity {

	private String account;
	private String linkman;
	private int sendPhoneCount;
	private int chargeCount;
	private int sendSuccess;
	private int sendFailure;
	private int sendUnknown;
	private int cutCount;
	private String memo;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public int getSendPhoneCount() {
		return sendPhoneCount;
	}

	public void setSendPhoneCount(int sendPhoneCount) {
		this.sendPhoneCount = sendPhoneCount;
	}

	public int getChargeCount() {
		return chargeCount;
	}

	public void setChargeCount(int chargeCount) {
		this.chargeCount = chargeCount;
	}

	public int getSendSuccess() {
		return sendSuccess;
	}

	public void setSendSuccess(int sendSuccess) {
		this.sendSuccess = sendSuccess;
	}

	public int getSendFailure() {
		return sendFailure;
	}

	public void setSendFailure(int sendFailure) {
		this.sendFailure = sendFailure;
	}

	public int getSendUnknown() {
		return sendUnknown;
	}

	public void setSendUnknown(int sendUnknown) {
		this.sendUnknown = sendUnknown;
	}

	public int getCutCount() {
		return cutCount;
	}

	public void setCutCount(int cutCount) {
		this.cutCount = cutCount;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
