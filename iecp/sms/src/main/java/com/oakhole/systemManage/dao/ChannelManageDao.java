package com.oakhole.systemManage.dao;

import java.util.List;

import com.oakhole.common.dao.BaseDao;
import com.oakhole.systemManage.model.Channel;

public interface ChannelManageDao extends BaseDao<Channel> {
	public long insertChannel(Channel channel);

	public List<Channel> volidChannelList();
}
