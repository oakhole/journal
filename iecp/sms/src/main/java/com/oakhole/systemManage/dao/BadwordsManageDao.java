package com.oakhole.systemManage.dao;

import com.oakhole.common.dao.BaseDao;
import com.oakhole.systemManage.model.BadWords;

public interface BadwordsManageDao extends BaseDao<BadWords> {

	public String findBadwordsByChannelGroupId(long channelId);
}
