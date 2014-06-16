package com.oakhole.sendStatistics.actions.sendStatistics;

import org.springframework.beans.factory.annotation.Autowired;

import com.oakhole.common.action.BaseAction;
import com.oakhole.common.model.PageEntity;
import com.oakhole.common.util.constants.GlobalConstants;
import com.oakhole.sendStatistics.dao.SmsStatisticsDao;
import com.oakhole.sendStatistics.model.SmsStatistics;
import com.oakhole.systemManage.dao.ChannelManageDao;
import com.oakhole.systemManage.model.AppUser;

@SuppressWarnings("serial")
public class SmsStatisticsAction extends BaseAction {

	@Autowired
	private SmsStatisticsDao smsStatisticsDao;

	@Autowired
	private ChannelManageDao channelManageDao;

	/**
	 * 当天的发送统计数据
	 * 
	 * @return
	 */
	public String findAllSmsStatistics() {

		AppUser currentAppUser = (AppUser) session
				.getAttribute(GlobalConstants.GLOBALAPPUSER);

		String currentPage = request.getParameter("currentPage");
		String userType = request.getParameter("userType");
		String channelId = request.getParameter("channelId");
		String account = request.getParameter("account");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");

		String isCut = request.getParameter("isCut");

		if (currentPage == null || "".equals(currentPage)) {
			currentPage = "0";
		}

		String sql = "select appUserId id,account,linkman,sum(sendPhoneCount) sendPhoneCount,sum(chargeCount) chargeCount,sum(sendSuccess) sendSuccess,sum(sendFailure) sendFailure,sum(sendUnknown) sendUnknown,sum(cutCount) cutCount,memo from sms_send where sendStatus=2";

		if (userType == null) {
			userType = "";
		}

		if (currentAppUser.getUserType() == 1) {
			// 代理商只能看到子账户的统计结果
			if (isCut != null && "1".equals(isCut)) {
				sql += " and appUserId in (select id from appUser where parentId="
						+ currentAppUser.getId() + " and isCut=1)";
			} else {
				sql += " and appUserId in (select id from appUser where parentId="
						+ currentAppUser.getId()
						+ " or id="
						+ currentAppUser.getId() + ")";
			}
			// 管理员可以查看所有人的统计结果
		} else if (currentAppUser.getUserType() == 0) {

			if (isCut != null && "1".equals(isCut)) {
				sql += " and appUserId in (select id from appUser where userType="
						+ Integer.valueOf(userType) + " and isCut=1)";
			}

			if (userType != null && !"".equals(userType)) {
				sql += " and appUserId in (select id from appUser where userType="
						+ Integer.valueOf(userType) + ")";
			}
			// 普通客户只能看到自己的统计结果
		} else if (currentAppUser.getUserType() == 2) {
			sql += " and appUserId=" + currentAppUser.getId();
		}
		request.setAttribute("userType", userType);

		if (channelId != null && !"".equals(channelId)) {
			sql += " and channelId=" + Integer.valueOf(channelId);
			request.setAttribute("channelId", channelId);
		}

		if (startTime != null && !"".equals(startTime)) {
			sql += " and sendTime >='" + startTime + "'";
			request.setAttribute("startTime", startTime);
		}

		if (endTime != null && !"".equals(endTime)) {
			sql += " and sendTime <='" + endTime + "'";
			request.setAttribute("endTime", endTime);
		}

		if (account != null && !"".equals(account)) {
			sql += " and account='" + account + "'";
			request.setAttribute("account", account);
		}
		sql += " group by appUserId";
		PageEntity<SmsStatistics> page = this.smsStatisticsDao
				.findAllPaginator("findAllSmsStatisticsTotalCount",
						"findAllSmsStatistics", sql,
						Integer.valueOf(currentPage));

		String channelSql = "select * from channel";

		request.setAttribute(
				"allChannel",
				this.channelManageDao.findAllPaginator(
						"findAllChannelTotalCount", "findAllChannel",
						channelSql, "asc").getResult());

		request.setAttribute("page", page);

		return "allSmsStatistics";
	}

	/**
	 * 按天查找发送统计
	 * 
	 * @return
	 */
	public String findAllSendStatisticsByDay() {
		return "allSendStatisticsByDay";
	}

	/**
	 * 按月查找发送统计
	 * 
	 * @return
	 */
	public String findAllSendStatisticsByMonth() {
		return "allSendStatisticsByMonth";
	}

	public void setSendStatisticsDao(SmsStatisticsDao sendStatisticsDao) {
		this.smsStatisticsDao = sendStatisticsDao;
	}

	public void setChannelManageDao(ChannelManageDao channelManageDao) {
		this.channelManageDao = channelManageDao;
	}

}
