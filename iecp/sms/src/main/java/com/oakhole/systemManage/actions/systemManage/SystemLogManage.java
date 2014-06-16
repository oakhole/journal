package com.oakhole.systemManage.actions.systemManage;

import org.springframework.beans.factory.annotation.Autowired;

import com.oakhole.common.action.BaseAction;
import com.oakhole.common.model.PageEntity;
import com.oakhole.common.util.constants.GlobalConstants;
import com.oakhole.systemManage.dao.SystemLogManageDao;
import com.oakhole.systemManage.model.AppUser;
import com.oakhole.systemManage.model.SystemLog;

@SuppressWarnings("serial")
public class SystemLogManage extends BaseAction {

	@Autowired
	private SystemLogManageDao systemLogManageDao;

	/**
	 * 分页读取所有日志
	 * 
	 * @return
	 */
	public String findAllSystemLog() {

		// 接收页面传递的参数
		String actor = request.getParameter("account");
		String actorName = request.getParameter("realname");
		String content = request.getParameter("content");
		String startTime = request.getParameter("starttime");
		String endTime = request.getParameter("endtime");
		int currentPage = Integer
				.parseInt((request.getParameter("currentPage") == null
						|| "".equals(request.getParameter("currentPage")) ? "1"
						: request.getParameter("currentPage")));

		AppUser currentAppUser = (AppUser) session
				.getAttribute(GlobalConstants.GLOBALAPPUSER);

		/**
		 * 模糊匹配
		 */
		if (content == null || "".equals(content)) {
			content = "";
		}
		request.setAttribute("content", content);

		// 配置sql语句
		String sql = "select * from system_log s where action like '%"
				+ content + "%'";

		if (currentAppUser.getUserType() != 0) {
			if (currentAppUser.getUserType() == 1) {
				sql += " and EXISTS (select account from appUser a where a.account=s.actor and "
						+ "(a.parentId="
						+ currentAppUser.getId()
						+ " or a.account='"
						+ currentAppUser.getAccount()
						+ "'))";
			}
			if (currentAppUser.getUserType() == 2
					|| currentAppUser.getUserType() == 3) {
				sql += " and actor='" + currentAppUser.getAccount() + "'";
			}
		}

		if (startTime != null && !"".equals(startTime)) {
			request.setAttribute("startTime", startTime);
			startTime += " 00:00:00";
			sql += " and actionTime >= '" + startTime + "'";
		}

		if (endTime != null && !"".equals(endTime)) {
			request.setAttribute("endTime", endTime);
			endTime += " 23:59:59";
			sql += " and actionTime <= '" + endTime + "'";
		}

		if (!"".equals(actor) && actor != null) {
			request.setAttribute("actor", actor);
			sql += " and actor='" + actor + "'";
		}

		if (!"".equals(actorName) && actorName != null) {
			request.setAttribute("actorName", actorName);
			sql += " and actorName='" + actorName + "'";
		}

		// 执行分页查询
		PageEntity<SystemLog> page = this.systemLogManageDao.findAllPaginator(
				"findAllSystemLogTotalCount", "findAllSystemLog", sql,
				currentPage);

		request.setAttribute("totalCount", page.getTotalCount());
		request.setAttribute("pageCount", page.getPageCount());
		request.setAttribute("allSysLog", page.getResult());
		request.setAttribute("pageNum", page.getPageNum());
		request.setAttribute("currentPage", page.getCurrentPage());

		return "allSysLog";
	}

	// 注入dao的实例
	public void setSystemLogManageDao(SystemLogManageDao systemLogManageDao) {
		this.systemLogManageDao = systemLogManageDao;
	}

}
