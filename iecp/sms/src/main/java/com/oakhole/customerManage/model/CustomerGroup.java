package com.oakhole.customerManage.model;

import com.oakhole.common.model.BaseEntity;

@SuppressWarnings("serial")
public class CustomerGroup extends BaseEntity {
	private String name;
	private long appUserId;
	private String memo;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
