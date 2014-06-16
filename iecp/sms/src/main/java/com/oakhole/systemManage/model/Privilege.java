package com.oakhole.systemManage.model;

import com.oakhole.common.model.BaseEntity;

@SuppressWarnings("serial")
public class Privilege extends BaseEntity {
	private long functionId;
	private String name;
	private boolean addPrivilege;
	private boolean deletePrivilege;
	private boolean updatePrivilege;
	private boolean queryPrivilege;
	private boolean execPrivilge;

	public long getFunctionId() {
		return functionId;
	}

	public void setFunctionId(long functionId) {
		this.functionId = functionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAddPrivilege() {
		return addPrivilege;
	}

	public void setAddPrivilege(boolean addPrivilege) {
		this.addPrivilege = addPrivilege;
	}

	public boolean isDeletePrivilege() {
		return deletePrivilege;
	}

	public void setDeletePrivilege(boolean deletePrivilege) {
		this.deletePrivilege = deletePrivilege;
	}

	public boolean isUpdatePrivilege() {
		return updatePrivilege;
	}

	public void setUpdatePrivilege(boolean updatePrivilege) {
		this.updatePrivilege = updatePrivilege;
	}

	public boolean isQueryPrivilege() {
		return queryPrivilege;
	}

	public void setQueryPrivilege(boolean queryPrivilege) {
		this.queryPrivilege = queryPrivilege;
	}

	public boolean isExecPrivilge() {
		return execPrivilge;
	}

	public void setExecPrivilge(boolean execPrivilge) {
		this.execPrivilge = execPrivilge;
	}

}
