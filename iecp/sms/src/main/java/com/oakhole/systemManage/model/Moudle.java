package com.oakhole.systemManage.model;

import com.oakhole.common.model.BaseEntity;

@SuppressWarnings("serial")
public class Moudle extends BaseEntity {
	private String name;
	private String code;

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
