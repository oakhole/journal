package com.oakhole.smsManage.model;

import java.util.Date;

import com.oakhole.common.model.BaseEntity;

@SuppressWarnings("serial")
public class SmsSend extends BaseEntity {
	private long appUserId;
	private String account;
	private String linkman;
	private long channelGroupId;
	private int isVarSms;
	private int sendPhoneCount;
	private int chargeCount;
	private int yidongCount;
	private int liantongCount;
	private int dianxinCount;
	private int yidongSendPhoneCount;
	private int liantongSendPhoneCount;
	private int dianxinSendPhoneCount;
	private int sendSuccess;
	private int sendFailure;
	private int sendUnknown;
	private int cutCount;
	private int sysCutCount;
	private String sendFilePath;
	private String smsTable;
	private String content;
	private int totalwords;
	private int sendType;
	private int priority;
	private Date planTime;
	private int auditStatus;
	private int sendStatus;
	private Date sendTime;
	private String memo;

	public long getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(long appUserId) {
		this.appUserId = appUserId;
	}

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

	public long getChannelGroupId() {
		return channelGroupId;
	}

	public void setChannelGroupId(long channelGroupId) {
		this.channelGroupId = channelGroupId;
	}

	public int getIsVarSms() {
		return isVarSms;
	}

	public void setIsVarSms(int isVarSms) {
		this.isVarSms = isVarSms;
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

	public int getSendType() {
		return sendType;
	}

	public void setSendType(int sendType) {
		this.sendType = sendType;
	}

	public Date getPlanTime() {
		return planTime;
	}

	public void setPlanTime(Date planTime) {
		this.planTime = planTime;
	}

	public String getSmsTable() {
		return smsTable;
	}

	public void setSmsTable(String smsTable) {
		this.smsTable = smsTable;
	}

	public int getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getSendFilePath() {
		return sendFilePath;
	}

	public void setSendFilePath(String sendFilePath) {
		this.sendFilePath = sendFilePath;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getTotalwords() {
		return totalwords;
	}

	public void setTotalwords(int totalwords) {
		this.totalwords = totalwords;
	}

	public int getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(int sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getYidongCount() {
		return yidongCount;
	}

	public void setYidongCount(int yidongCount) {
		this.yidongCount = yidongCount;
	}

	public int getLiantongCount() {
		return liantongCount;
	}

	public void setLiantongCount(int liantongCount) {
		this.liantongCount = liantongCount;
	}

	public int getDianxinCount() {
		return dianxinCount;
	}

	public void setDianxinCount(int dianxinCount) {
		this.dianxinCount = dianxinCount;
	}

	public int getYidongSendPhoneCount() {
		return yidongSendPhoneCount;
	}

	public void setYidongSendPhoneCount(int yidongSendPhoneCount) {
		this.yidongSendPhoneCount = yidongSendPhoneCount;
	}

	public int getLiantongSendPhoneCount() {
		return liantongSendPhoneCount;
	}

	public void setLiantongSendPhoneCount(int liantongSendPhoneCount) {
		this.liantongSendPhoneCount = liantongSendPhoneCount;
	}

	public int getDianxinSendPhoneCount() {
		return dianxinSendPhoneCount;
	}

	public void setDianxinSendPhoneCount(int dianxinSendPhoneCount) {
		this.dianxinSendPhoneCount = dianxinSendPhoneCount;
	}

	public int getSysCutCount() {
		return sysCutCount;
	}

	public void setSysCutCount(int sysCutCount) {
		this.sysCutCount = sysCutCount;
	}

}
