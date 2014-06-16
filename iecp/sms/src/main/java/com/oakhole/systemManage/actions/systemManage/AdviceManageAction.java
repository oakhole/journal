package com.oakhole.systemManage.actions.systemManage;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.oakhole.common.action.BaseAction;
import com.oakhole.common.model.PageEntity;
import com.oakhole.common.util.constants.GlobalConstants;
import com.oakhole.systemManage.dao.AdviceManageDao;
import com.oakhole.systemManage.model.Advice;
import com.oakhole.systemManage.model.AppUser;

@SuppressWarnings("serial")
public class AdviceManageAction extends BaseAction {

	@Autowired
	private AdviceManageDao adviceManageDao;

	/**
	 * 添加新的公告
	 * 
	 * @return
	 */
	public String addAdvice() {

		String flag = request.getParameter("flag");
		String adviceId = request.getParameter("adviceId");

		if ("0".equals(flag) || adviceId == null || "".equals(adviceId)) {
			return "addAdvice";
		} else {

			Advice advice = this.adviceManageDao.findObjectById(
					"findAdviceById", Integer.valueOf(adviceId));
			request.setAttribute("advice", advice);

			return "updateAdvice";
		}
	}

	/**
	 * 将公告存入数据库，持久化操作
	 * 
	 * @return
	 */
	public String saveAdvice() {

		String flag = request.getParameter("flag");
		String adviceId = request.getParameter("adviceId");

		// post参数
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String author = ((AppUser) session
				.getAttribute(GlobalConstants.GLOBALAPPUSER)).getAccount();

		if ("0".equals(flag) || adviceId == null || "".equals(adviceId)) {

			Advice advice = new Advice();
			advice.setAuthor(author);
			advice.setContent(content);
			advice.setTitle(title);
			advice.setCreateTime(new Date());
			this.writeLog(author + "发布了新的公告:" + title);
			this.adviceManageDao.addObject("addAdvice", advice);

		} else {
			Advice advice = this.adviceManageDao.findObjectById(
					"findAdviceById", Long.valueOf(adviceId));
			advice.setAuthor(author);
			advice.setTitle(title);
			advice.setContent(content);
			advice.setUpdateTime(new Date());
			this.writeLog(author + "更新了公告:" + title);
			this.adviceManageDao.updateObject("updateAdvice", advice);
		}

		return this.findAllAdvice();
	}

	/**
	 * 删除公告
	 * 
	 * @return
	 */
	public String deleteAdvice() {

		String adviceId = request.getParameter("adviceId");
		if (adviceId != null && !"".equals(adviceId)) {
			Advice advice = this.adviceManageDao.findObjectById(
					"findAdviceById", Long.valueOf(adviceId));
			this.adviceManageDao.delObject("deleteAdvice",
					Long.valueOf(adviceId));
			this.writeLog(advice.getAuthor() + "删除了公告:" + advice.getTitle());
		}

		return this.findAllAdvice();
	}

	/**
	 * 查询所有公告内容
	 * 
	 * @return
	 */
	public String findAllAdvice() {
		String currentPage = request.getParameter("currentPage");

		String author = request.getParameter("author");
		request.setAttribute("author", author);

		String sql = "select * from advice where 1=1";

		if (author != null && !"".equals(author)) {
			sql += " and author=" + author;
		}

		if (currentPage == null || "".equals(currentPage)) {
			currentPage = "1";
		}

		PageEntity<Advice> page = this.adviceManageDao.findAllPaginator(
				"findAllAdviceTotalCount", "findAllAdvice", sql,
				Integer.valueOf(currentPage));

		request.setAttribute("allAdvice", page.getResult());
		request.setAttribute("totalCount", page.getTotalCount());
		request.setAttribute("pageNum", page.getPageNum());
		request.setAttribute("currentPage", page.getCurrentPage());

		return "allAdvice";
	}

	public void setAdviceManageDao(AdviceManageDao adviceManageDao) {
		this.adviceManageDao = adviceManageDao;
	}
}
