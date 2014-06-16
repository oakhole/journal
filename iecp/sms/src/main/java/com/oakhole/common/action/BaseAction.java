package com.oakhole.common.action;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.oakhole.common.util.constants.GlobalConstants;
import com.oakhole.financialManage.dao.FinancialManageDao;
import com.oakhole.financialManage.model.Financial;
import com.oakhole.systemManage.dao.SettingManageDao;
import com.oakhole.systemManage.dao.SystemLogManageDao;
import com.oakhole.systemManage.model.AppUser;
import com.oakhole.systemManage.model.Setting;
import com.oakhole.systemManage.model.SystemLog;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public abstract class BaseAction extends ActionSupport {

	@Autowired
	protected SystemLogManageDao systemLogManageDao;

	@Autowired
	protected FinancialManageDao financialManageDao;

	@Autowired
	protected SettingManageDao settingManageDao;

	protected Log log = LogFactory.getLog(BaseAction.class);

	protected HttpServletRequest request = ServletActionContext.getRequest();

	protected HttpServletResponse response = ServletActionContext.getResponse();

	protected HttpSession session = this.request.getSession();

	protected ServletContext application = ServletActionContext
			.getServletContext();

	/**
	 * 返回txt文本响应
	 * 
	 * @param data
	 */
	public void responsePrint(String data) {
		response.setContentType("application/x-www-form-urlencoded;");
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().print(data);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 写入数据库日志
	 * 
	 * @param content
	 * @param actIP
	 */
	public void writeLog(String content) {
		AppUser appUser = (AppUser) session
				.getAttribute(GlobalConstants.GLOBALAPPUSER);
		SystemLog sysLog = new SystemLog();
		sysLog.setAction(content);
		sysLog.setActorName(appUser.getLinkman());
		sysLog.setActor(appUser.getAccount());
		sysLog.setActionTime(new Date());
		sysLog.setActIP(request.getRemoteAddr());
		this.systemLogManageDao.addObject("addSystemLog", sysLog);
		this.log.info(content);
	}

	/**
	 * 写入充值消费记录集中
	 * 
	 * @param financial
	 */
	public void addFinancial(Financial financial) {
		this.financialManageDao.addObject("addFinancial", financial);
	}

	/**
	 * 获取当前系统设置
	 * 
	 * @return
	 */
	public Setting getCurrrentSystemSetting() {
		String sql = "select * from setting";
		return this.settingManageDao
				.findAllPaginator("findAllSettingTotalCount", "findAllSetting",
						sql, "asc").getResult().get(0);
	}

	public void setSystemLogManageDao(SystemLogManageDao systemLogManageDao) {
		this.systemLogManageDao = systemLogManageDao;
	}

	public void setSettingManageDao(SettingManageDao settingManageDao) {
		this.settingManageDao = settingManageDao;
	}

}
