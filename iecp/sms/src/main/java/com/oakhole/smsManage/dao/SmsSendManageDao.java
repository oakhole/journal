package com.oakhole.smsManage.dao;

import com.oakhole.common.dao.BaseDao;
import com.oakhole.smsManage.model.SmsSend;

public interface SmsSendManageDao extends BaseDao<SmsSend> {

	/**
	 * 插入一条记录并返回该自增ID
	 * 
	 * @param smsSend
	 * @return
	 */
	public long insertSmsSend(SmsSend smsSend);

	/**
	 * 按一定规则取得一条记录
	 * 
	 * @return
	 */
	public SmsSend getFirstSmsSend();

}
