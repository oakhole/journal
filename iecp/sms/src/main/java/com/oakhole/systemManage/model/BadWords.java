package com.oakhole.systemManage.model;

import com.oakhole.common.model.BaseEntity;

@SuppressWarnings("serial")
public class BadWords extends BaseEntity {

	private long channelGroupId;
	private String channelGroupName;
	private String badwords;

	public long getChannelGroupId() {
		return channelGroupId;
	}

	public void setChannelGroupId(long channelGroupId) {
		this.channelGroupId = channelGroupId;
	}

	public String getChannelGroupName() {
		return channelGroupName;
	}

	public void setChannelGroupName(String channelGroupName) {
		this.channelGroupName = channelGroupName;
	}

	public String getBadwords() {
		return badwords;
	}

	public void setBadwords(String badwords) {
		this.badwords = badwords;
	}

}
