package com.oakhole.systemManage.model;

import java.util.Date;

import com.oakhole.common.model.BaseEntity;

@SuppressWarnings("serial")
public class SystemLog extends BaseEntity {
	private String action;
	private String actor;
	private String actorName;
	private String actIP;
	private Date actionTime;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getActorName() {
		return actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public String getActIP() {
		return actIP;
	}

	public void setActIP(String actIP) {
		this.actIP = actIP;
	}

	public Date getActionTime() {
		return actionTime;
	}

	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}

}
