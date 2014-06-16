package com.oakhole.systemManage.model;

import com.oakhole.common.model.BaseEntity;

@SuppressWarnings("serial")
public class Channel extends BaseEntity {
	private String name;
	private int channelType;
	private int operators;
	private String description;
	private int balance;
	private float price;
	private int perpack;
	private int dayFlow;
	private int callback;
	private int longSms;
	private int perSmsWords;
	private int maxSmsWords;
	private int status;
	private boolean isAbled;

	// 新增功能
	private long interfaceId;
	private String interfaceParameters;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getChannelType() {
		return channelType;
	}

	public void setChannelType(int channelType) {
		this.channelType = channelType;
	}

	public int getOperators() {
		return operators;
	}

	public void setOperators(int operators) {
		this.operators = operators;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public int getDayFlow() {
		return dayFlow;
	}

	public void setDayFlow(int dayFlow) {
		this.dayFlow = dayFlow;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getPerpack() {
		return perpack;
	}

	public void setPerpack(int perpack) {
		this.perpack = perpack;
	}

	public int getCallback() {
		return callback;
	}

	public void setCallback(int callback) {
		this.callback = callback;
	}

	public int getPerSmsWords() {
		return perSmsWords;
	}

	public void setPerSmsWords(int perSmsWords) {
		this.perSmsWords = perSmsWords;
	}

	public int getMaxSmsWords() {
		return maxSmsWords;
	}

	public void setMaxSmsWords(int maxSmsWords) {
		this.maxSmsWords = maxSmsWords;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getLongSms() {
		return longSms;
	}

	public void setLongSms(int longSms) {
		this.longSms = longSms;
	}

	public boolean isAbled() {
		return isAbled;
	}

	public void setAbled(boolean isAbled) {
		this.isAbled = isAbled;
	}

	public long getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(long interfaceId) {
		this.interfaceId = interfaceId;
	}

	public String getInterfaceParameters() {
		return interfaceParameters;
	}

	public void setInterfaceParameters(String interfaceParameters) {
		this.interfaceParameters = interfaceParameters;
	}

}
