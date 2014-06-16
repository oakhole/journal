package com.oakhole.customerManage.model;

import com.oakhole.common.model.BaseEntity;

@SuppressWarnings("serial")
public class Customer extends BaseEntity {
	private long parentId;
	private String account;
	private String password;
	private String extno;
	private long groupId;
	private long customerManagerId;
	private long clerkId;
	private long cashierId;
	private long customerServiceId;
	private String customerName;
	private String customerManager;
	private String linkman;
	private String phone;
	private String mobile;
	private String qq;
	private String email;
	private boolean isBindIp;
	private String bindIp;
	private boolean isCut;
	private String cutPercent;
	private boolean isAutoCollectWhiteList;
	private boolean isWhiteListable;
	private boolean isReceiptTimeViewable;
	private boolean isBadSegFilterable;
	private boolean isSubAccountSetable;
	private boolean isPhoneNumberDownloadable;
	private boolean paymentMethod;
	private boolean isReportViewable;
	private boolean chargebackWay;
	private boolean isReissueable;
	private boolean isLongSms;
	private boolean isReceiveSms;
	private boolean isAudit;
	private int auditCount;
	private boolean isAccumulativeAudit;
	private int accumulativeCount;
	private int accumulativeHour;
	private int perpackCount;
	private boolean isViewChannel;
	private double unitPrice;
	private int firstchargebackLength;
	private boolean isForceSignature;
	private String forceSignture;
	private long smsChannelGroupId;
	private long mmsChannelGroupId;
	private int smsBalance;
	private int mmsBalance;
	private String whiteList;
	private String blackList;
	private String status;

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
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

	public String getExtno() {
		return extno;
	}

	public void setExtno(String extno) {
		this.extno = extno;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public long getCustomerManagerId() {
		return customerManagerId;
	}

	public void setCustomerManagerId(long customerManagerId) {
		this.customerManagerId = customerManagerId;
	}

	public long getClerkId() {
		return clerkId;
	}

	public void setClerkId(long clerkId) {
		this.clerkId = clerkId;
	}

	public long getCashierId() {
		return cashierId;
	}

	public void setCashierId(long cashierId) {
		this.cashierId = cashierId;
	}

	public long getCustomerServiceId() {
		return customerServiceId;
	}

	public void setCustomerServiceId(long customerServiceId) {
		this.customerServiceId = customerServiceId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerManager() {
		return customerManager;
	}

	public void setCustomerManager(String customerManager) {
		this.customerManager = customerManager;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public boolean isBindIp() {
		return isBindIp;
	}

	public void setBindIp(boolean isBindIp) {
		this.isBindIp = isBindIp;
	}

	public String getBindIp() {
		return bindIp;
	}

	public void setBindIp(String bindIp) {
		this.bindIp = bindIp;
	}

	public boolean isCut() {
		return isCut;
	}

	public void setCut(boolean isCut) {
		this.isCut = isCut;
	}

	public String getCutPercent() {
		return cutPercent;
	}

	public void setCutPercent(String cutPercent) {
		this.cutPercent = cutPercent;
	}

	public boolean isAutoCollectWhiteList() {
		return isAutoCollectWhiteList;
	}

	public void setAutoCollectWhiteList(boolean isAutoCollectWhiteList) {
		this.isAutoCollectWhiteList = isAutoCollectWhiteList;
	}

	public boolean isWhiteListable() {
		return isWhiteListable;
	}

	public void setWhiteListable(boolean isWhiteListable) {
		this.isWhiteListable = isWhiteListable;
	}

	public boolean isReceiptTimeViewable() {
		return isReceiptTimeViewable;
	}

	public void setReceiptTimeViewable(boolean isReceiptTimeViewable) {
		this.isReceiptTimeViewable = isReceiptTimeViewable;
	}

	public boolean isBadSegFilterable() {
		return isBadSegFilterable;
	}

	public void setBadSegFilterable(boolean isBadSegFilterable) {
		this.isBadSegFilterable = isBadSegFilterable;
	}

	public boolean isSubAccountSetable() {
		return isSubAccountSetable;
	}

	public void setSubAccountSetable(boolean isSubAccountSetable) {
		this.isSubAccountSetable = isSubAccountSetable;
	}

	public boolean isPhoneNumberDownloadable() {
		return isPhoneNumberDownloadable;
	}

	public void setPhoneNumberDownloadable(boolean isPhoneNumberDownloadable) {
		this.isPhoneNumberDownloadable = isPhoneNumberDownloadable;
	}

	public boolean isPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(boolean paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public boolean isReportViewable() {
		return isReportViewable;
	}

	public void setReportViewable(boolean isReportViewable) {
		this.isReportViewable = isReportViewable;
	}

	public boolean isChargebackWay() {
		return chargebackWay;
	}

	public void setChargebackWay(boolean chargebackWay) {
		this.chargebackWay = chargebackWay;
	}

	public boolean isReissueable() {
		return isReissueable;
	}

	public void setReissueable(boolean isReissueable) {
		this.isReissueable = isReissueable;
	}

	public boolean isLongSms() {
		return isLongSms;
	}

	public void setLongSms(boolean isLongSms) {
		this.isLongSms = isLongSms;
	}

	public boolean isReceiveSms() {
		return isReceiveSms;
	}

	public void setReceiveSms(boolean isReceiveSms) {
		this.isReceiveSms = isReceiveSms;
	}

	public boolean isAudit() {
		return isAudit;
	}

	public void setAudit(boolean isAudit) {
		this.isAudit = isAudit;
	}

	public int getAuditCount() {
		return auditCount;
	}

	public void setAuditCount(int auditCount) {
		this.auditCount = auditCount;
	}

	public boolean isAccumulativeAudit() {
		return isAccumulativeAudit;
	}

	public void setAccumulativeAudit(boolean isAccumulativeAudit) {
		this.isAccumulativeAudit = isAccumulativeAudit;
	}

	public int getAccumulativeCount() {
		return accumulativeCount;
	}

	public void setAccumulativeCount(int accumulativeCount) {
		this.accumulativeCount = accumulativeCount;
	}

	public int getAccumulativeHour() {
		return accumulativeHour;
	}

	public void setAccumulativeHour(int accumulativeHour) {
		this.accumulativeHour = accumulativeHour;
	}

	public int getPerpackCount() {
		return perpackCount;
	}

	public void setPerpackCount(int perpackCount) {
		this.perpackCount = perpackCount;
	}

	public boolean isViewChannel() {
		return isViewChannel;
	}

	public void setViewChannel(boolean isViewChannel) {
		this.isViewChannel = isViewChannel;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getFirstchargebackLength() {
		return firstchargebackLength;
	}

	public void setFirstchargebackLength(int firstchargebackLength) {
		this.firstchargebackLength = firstchargebackLength;
	}

	public boolean isForceSignature() {
		return isForceSignature;
	}

	public void setForceSignature(boolean isForceSignature) {
		this.isForceSignature = isForceSignature;
	}

	public String getForceSignture() {
		return forceSignture;
	}

	public void setForceSignture(String forceSignture) {
		this.forceSignture = forceSignture;
	}

	public long getSmsChannelGroupId() {
		return smsChannelGroupId;
	}

	public void setSmsChannelGroupId(long smsChannelGroupId) {
		this.smsChannelGroupId = smsChannelGroupId;
	}

	public long getMmsChannelGroupId() {
		return mmsChannelGroupId;
	}

	public void setMmsChannelGroupId(long mmsChannelGroupId) {
		this.mmsChannelGroupId = mmsChannelGroupId;
	}

	public int getSmsBalance() {
		return smsBalance;
	}

	public void setSmsBalance(int smsBalance) {
		this.smsBalance = smsBalance;
	}

	public int getMmsBalance() {
		return mmsBalance;
	}

	public void setMmsBalance(int mmsBalance) {
		this.mmsBalance = mmsBalance;
	}

	public String getWhiteList() {
		return whiteList;
	}

	public void setWhiteList(String whiteList) {
		this.whiteList = whiteList;
	}

	public String getBlackList() {
		return blackList;
	}

	public void setBlackList(String blackList) {
		this.blackList = blackList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
