package com.oakhole.smsManage.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.oakhole.common.dao.BaseDao;
import com.oakhole.smsManage.model.Sms;
import com.oakhole.systemManage.model.AppUser;

public interface SmsManageDao extends BaseDao<Sms> {

	public int existsSmsTable(String smsTable);

	public void createNewSmsTable(String smsTable);

	public void batchAddSms(Set<Sms> allSms, String smsTable);

	/**
	 * 更新sequenceId
	 * 
	 * @param allSms
	 * @param smsTable
	 */
	public void batchUpdateSms(Set<Sms> allSms, String smsTable);

	/**
	 * 更新状态回执
	 * 
	 * @param allSms
	 * @param smsTable
	 */
	public void batchUpdateSmsStatus(Set<Sms> allSms, String smsTable);

	/**
	 * 判断是否发送完成
	 * 
	 * @param sendId
	 * @return
	 */
	public boolean isSendFinished(long sendId);

	/**
	 * 指定通道读取对应表的limit条数据
	 * 
	 * @param smsTable
	 * @param channelId
	 * @param limit
	 * @return
	 */
	public Map<String, Object> getSmsList(long channelId, int limit);

	// 用于推送短信回复
	public AppUser findAppUser(String mobile);

	// 获取当前发送成功数
	public int getSendSuccessCount(long sendId, String smsTable);

	// 获取当前发送失败数
	public int getSendFailureCount(long sendId, String smsTable);

	// 获取状态报告
	public List<Sms> getReport(String account, int limit);

	public void updateStatus(String smsTable, long sendId, int flag);

	public void statitics();
}
