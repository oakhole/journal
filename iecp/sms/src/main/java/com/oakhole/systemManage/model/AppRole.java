package com.oakhole.systemManage.model;

import com.oakhole.common.model.BaseEntity;

@SuppressWarnings("serial")
public class AppRole extends BaseEntity {
	private String name;
	private String memo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
