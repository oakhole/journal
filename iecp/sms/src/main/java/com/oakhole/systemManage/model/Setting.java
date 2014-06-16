package com.oakhole.systemManage.model;

import com.oakhole.common.model.BaseEntity;

@SuppressWarnings("serial")
public class Setting extends BaseEntity {

	private int sendLimitDown;
	private int sendLimitUp;
	private int cutCondition;
	private int cutPercent;
	private int maxWhiteNum;
	private int isCollectWhiteList;
	private int collectWhiteListCondition;

	public int getSendLimitDown() {
		return sendLimitDown;
	}

	public void setSendLimitDown(int sendLimitDown) {
		this.sendLimitDown = sendLimitDown;
	}

	public int getSendLimitUp() {
		return sendLimitUp;
	}

	public void setSendLimitUp(int sendLimitUp) {
		this.sendLimitUp = sendLimitUp;
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

	public int getMaxWhiteNum() {
		return maxWhiteNum;
	}

	public void setMaxWhiteNum(int maxWhiteNum) {
		this.maxWhiteNum = maxWhiteNum;
	}

	public int getIsCollectWhiteList() {
		return isCollectWhiteList;
	}

	public void setIsCollectWhiteList(int isCollectWhiteList) {
		this.isCollectWhiteList = isCollectWhiteList;
	}

	public int getCollectWhiteListCondition() {
		return collectWhiteListCondition;
	}

	public void setCollectWhiteListCondition(int collectWhiteListCondition) {
		this.collectWhiteListCondition = collectWhiteListCondition;
	}

}
