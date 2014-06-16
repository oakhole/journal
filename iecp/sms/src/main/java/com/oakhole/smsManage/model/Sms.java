package com.oakhole.smsManage.model;

import java.util.Date;

import com.oakhole.common.model.BaseEntity;

@SuppressWarnings("serial")
public class Sms extends BaseEntity {

	private String content;
	private String mobile;
	private int pushStatus;
	private Date pushTime;
	private String status;
	private Date statusTime;
	private long sendId;
	private String sequenceId;
	private int isRead;
	private int isCut;

	private int priority;
	private long channelId;
	private int seg; // 网段 0:移动 1:联通 2:电信

	public Sms() {

	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getPushStatus() {
		return pushStatus;
	}

	public void setPushStatus(int pushStatus) {
		this.pushStatus = pushStatus;
	}

	public Date getPushTime() {
		return pushTime;
	}

	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStatusTime() {
		return statusTime;
	}

	public void setStatusTime(Date statusTime) {
		this.statusTime = statusTime;
	}

	public long getSendId() {
		return sendId;
	}

	public void setSendId(long sendId) {
		this.sendId = sendId;
	}

	public String getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(String sequenceId) {
		this.sequenceId = sequenceId;
	}

	public int getIsRead() {
		return isRead;
	}

	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

	public int getIsCut() {
		return isCut;
	}

	public void setIsCut(int isCut) {
		this.isCut = isCut;
	}

	public long getChannelId() {
		return channelId;
	}

	public void setChannelId(long channelId) {
		this.channelId = channelId;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getSeg() {
		return seg;
	}

	public void setSeg(int seg) {
		this.seg = seg;
	}

}
