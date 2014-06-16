package com.oakhole.customerManage.model;

import com.oakhole.common.model.BaseEntity;

@SuppressWarnings("serial")
public class SmsPackage extends BaseEntity {
	private String name;
	private String packageType;
	private int smsCount;
	private double pricie;
	private long appUserId;
	private String memo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public int getSmsCount() {
		return smsCount;
	}

	public void setSmsCount(int smsCount) {
		this.smsCount = smsCount;
	}

	public double getPricie() {
		return pricie;
	}

	public void setPricie(double pricie) {
		this.pricie = pricie;
	}

	public long getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(long appUserId) {
		this.appUserId = appUserId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
