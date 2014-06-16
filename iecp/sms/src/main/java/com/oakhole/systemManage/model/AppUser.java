package com.oakhole.systemManage.model;

import com.oakhole.common.model.BaseEntity;

@SuppressWarnings("serial")
public class AppUser extends BaseEntity {
	private String linkman;
	private String account;
	private String password;
	private String phoneNumber;
	private String mobile;
	private String qq;
	private String email;
	private int userType;
	private int isAudit;
	private int auditCondition;
	private int isCut;
	private int cutCondition;
	private int cutPercent;
	private int sysCutCondition;
	private int sysCutPercent;
	private int isStatus;
	private String whiteListPath;
	private int whitePhoneCount;
	private int chargeType;
	private String signature;
	private long channelGroupId;
	private int balance;
	private float price;
	private long parentId;
	private int locked;
	private String memo;

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getAuditCondition() {
		return auditCondition;
	}

	public void setAuditCondition(int auditCondition) {
		this.auditCondition = auditCondition;
	}

	public int getCutCondition() {
		return cutCondition;
	}

	public void setCutCondition(int cutCondition) {
		this.cutCondition = cutCondition;
	}

	public int getCutPercent() {
		return cutPercent;
	}

	public void setCutPercent(int cutPercent) {
		this.cutPercent = cutPercent;
	}

	public String getWhiteListPath() {
		return whiteListPath;
	}

	public int getWhitePhoneCount() {
		return whitePhoneCount;
	}

	public void setWhitePhoneCount(int whitePhoneCount) {
		this.whitePhoneCount = whitePhoneCount;
	}

	public void setWhiteListPath(String whiteListPath) {
		this.whiteListPath = whiteListPath;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public long getChannelGroupId() {
		return channelGroupId;
	}

	public void setChannelGroupId(long channelGroupId) {
		this.channelGroupId = channelGroupId;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(int isAudit) {
		this.isAudit = isAudit;
	}

	public int getIsCut() {
		return isCut;
	}

	public void setIsCut(int isCut) {
		this.isCut = isCut;
	}

	public int getIsStatus() {
		return isStatus;
	}

	public void setIsStatus(int isStatus) {
		this.isStatus = isStatus;
	}

	public int getChargeType() {
		return chargeType;
	}

	public void setChargeType(int chargeType) {
		this.chargeType = chargeType;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public int getLocked() {
		return locked;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getSysCutCondition() {
		return sysCutCondition;
	}

	public void setSysCutCondition(int sysCutCondition) {
		this.sysCutCondition = sysCutCondition;
	}

	public int getSysCutPercent() {
		return sysCutPercent;
	}

	public void setSysCutPercent(int sysCutPercent) {
		this.sysCutPercent = sysCutPercent;
	}

}
