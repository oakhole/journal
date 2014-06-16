package com.oakhole.systemManage.model;

import com.oakhole.common.model.BaseEntity;

@SuppressWarnings("serial")
public class Function extends BaseEntity {
	private long moudleId;
	private String name;
	private String code;

	public long getMoudleId() {
		return moudleId;
	}

	public void setMoudleId(long moudleId) {
		this.moudleId = moudleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
