package com.oakhole.smsManage.actions.smsManage;

import org.springframework.beans.factory.annotation.Autowired;

import com.oakhole.common.action.BaseAction;
import com.oakhole.common.model.PageEntity;
import com.oakhole.common.util.constants.GlobalConstants;
import com.oakhole.smsManage.dao.SmsReceiveManageDao;
import com.oakhole.smsManage.model.SmsReceive;
import com.oakhole.systemManage.model.AppUser;

@SuppressWarnings("serial")
public class SmsReceiveManageAction extends BaseAction {

	@Autowired
	private SmsReceiveManageDao smsReceiveManageDao;

	/**
	 * 更改短信回复是否被查阅
	 * 
	 * @return
	 */
	public String udpateSmsReceive() {

		String receiveId = request.getParameter("receiveId");
		SmsReceive smsReceive = this.smsReceiveManageDao.findObjectById(
				"findSmsReceiveById", Long.valueOf(receiveId));
		smsReceive.setIsRead(1);
		this.smsReceiveManageDao.updateObject("updateSmsReceive", smsReceive);
		return null;
	}

	/**
	 * 查询所有短信回复
	 * 
	 * @return
	 */
	public String findAllSmsReceive() {

		AppUser currentAppUser = (AppUser) session
				.getAttribute(GlobalConstants.GLOBALAPPUSER);

		String startTime = request.getParameter("starttime");
		String endTime = request.getParameter("endtime");
		String account = request.getParameter("account");
		String mobile = request.getParameter("mobile");

		String currentPage = request.getParameter("currentPage");

		if (currentPage == null || "".equals(currentPage)) {
			currentPage = "1";
		}

		String sql = "select * from sms_receive s where 1=1";

		if (currentAppUser.getUserType() == 2
				|| currentAppUser.getUserType() == 3) {
			sql += " and appUserId=" + currentAppUser.getId();
		}

		if (currentAppUser.getUserType() == 1) {
			sql += " and appUserId="
					+ currentAppUser.getId()
					+ " or "
					+ currentAppUser.getId()
					+ " in (select parentId from appUser a where a.id=s.appUserId)";
		}

		if (startTime != null && !"".equals(startTime)) {
			sql = sql + " and receiveTime >" + startTime + " 00:00:00";
		}
		if (endTime != null && !"".equals(endTime)) {
			sql = sql + " and receiveTime <" + endTime + " 23:59:59";
		}

		if (account != null && !"".equals(account)) {
			sql = sql + " and account=" + account;
		}

		if (mobile != null && !"".equals(mobile)) {
			sql = sql + " and mobile=" + mobile;
		}

		PageEntity<SmsReceive> page = this.smsReceiveManageDao
				.findAllPaginator("findAllSmsReceiveTotalCount",
						"findAllSmsReceive", sql, Integer.parseInt(currentPage));

		this.request.setAttribute("currentPage", currentPage);
		this.request.setAttribute("page", page);
		this.request.setAttribute("startTime", startTime);
		this.request.setAttribute("endTime", endTime);
		this.request.setAttribute("mobile", mobile);
		this.request.setAttribute("account", account);

		return "allSmsReceive";
	}

	public void setSmsReceiveManageDao(SmsReceiveManageDao smsReceiveManageDao) {
		this.smsReceiveManageDao = smsReceiveManageDao;
	}

}
