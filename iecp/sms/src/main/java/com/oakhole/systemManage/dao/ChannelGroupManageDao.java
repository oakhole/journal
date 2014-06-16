package com.oakhole.systemManage.dao;

import com.oakhole.common.dao.BaseDao;
import com.oakhole.systemManage.model.ChannelGroup;

public interface ChannelGroupManageDao extends BaseDao<ChannelGroup> {

	public long insertChannelGroup(ChannelGroup channelGroup);
}
