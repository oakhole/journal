package com.oakhole.systemManage.model;

import com.oakhole.common.model.BaseEntity;

@SuppressWarnings("serial")
public class ChannelGroup extends BaseEntity {
	private String name;
	private long yidongChannelId;
	private long liantongChannelId;
	private long dianxinChannelId;
	private String memo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getYidongChannelId() {
		return yidongChannelId;
	}

	public void setYidongChannelId(long yidongChannelId) {
		this.yidongChannelId = yidongChannelId;
	}

	public long getLiantongChannelId() {
		return liantongChannelId;
	}

	public void setLiantongChannelId(long liantongChannelId) {
		this.liantongChannelId = liantongChannelId;
	}

	public long getDianxinChannelId() {
		return dianxinChannelId;
	}

	public void setDianxinChannelId(long dianxinChannelId) {
		this.dianxinChannelId = dianxinChannelId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
