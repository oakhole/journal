package com.oakhole.smsManage.dao;

import java.util.List;

import com.oakhole.common.dao.BaseDao;
import com.oakhole.smsManage.model.SmsReceive;

public interface SmsReceiveManageDao extends BaseDao<SmsReceive> {

	// 每次读取多少条短信回复
	public List<SmsReceive> getReceive(String account, int limit);

	public void batchAddSmsReceive(List<SmsReceive> smsReceiveList);
}
