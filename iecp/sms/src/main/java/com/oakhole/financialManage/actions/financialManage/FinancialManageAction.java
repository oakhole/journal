package com.oakhole.financialManage.actions.financialManage;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.oakhole.common.action.BaseAction;
import com.oakhole.common.model.PageEntity;
import com.oakhole.common.util.constants.GlobalConstants;
import com.oakhole.financialManage.dao.FinancialManageDao;
import com.oakhole.financialManage.model.Financial;
import com.oakhole.systemManage.dao.AppUserManageDao;
import com.oakhole.systemManage.model.AppUser;

@SuppressWarnings("serial")
public class FinancialManageAction extends BaseAction {

	@Autowired
	private FinancialManageDao financialManageDao;

	@Autowired
	private AppUserManageDao appUserManageDao;

	/**
	 * 充值记录
	 * 
	 * @return
	 */
	public String income() {

		AppUser currentAppUser = (AppUser) session
				.getAttribute(GlobalConstants.GLOBALAPPUSER);

		String currentPage = request.getParameter("currentPage");
		if (currentPage == null || "".equals(currentPage)) {
			currentPage = "0";
		}
		String account = request.getParameter("account");
		String actType = request.getParameter("actType");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");

		String sql = "select * from financial f where (actMethod=0 or actMethod=3)";

		if (currentAppUser.getUserType() == 1) {
			sql += " and EXISTS (select account from appUser a where a.account=f.account and "
					+ "(a.parentId="
					+ currentAppUser.getId()
					+ " or a.account='" + currentAppUser.getAccount() + "'))";
		}
		if (currentAppUser.getUserType() == 2
				|| currentAppUser.getUserType() == 3) {
			sql += " and account='" + currentAppUser.getAccount() + "'";
		}

		if (account != null && !"".equals(account)) {
			sql += " and account='" + account + "'";
			request.setAttribute("account", account);
		}

		if (actType != null && !"".equals(actType)) {
			sql += " and actType=" + Integer.valueOf(actType);
			request.setAttribute("actType", actType);
		}

		if (startTime != null && !"".equals(startTime)) {
			sql += " and actTime >= '" + startTime + "'";
			request.setAttribute("startTime", startTime);
		}

		if (endTime != null && !"".equals(endTime)) {
			sql += " and actTime <= '" + endTime + "'";
			request.setAttribute("endTime", endTime);
		}

		PageEntity<Financial> page = this.financialManageDao.findAllPaginator(
				"findAllFinancialTotalCount", "findAllFinancial", sql,
				Integer.valueOf(currentPage));
		request.setAttribute("page", page);
		return "income";
	}

	/**
	 * 消费记录
	 * 
	 * @return
	 */
	public String withdraw() {

		AppUser currentAppUser = (AppUser) session
				.getAttribute(GlobalConstants.GLOBALAPPUSER);

		String currentPage = request.getParameter("currentPage");
		if (currentPage == null || "".equals(currentPage)) {
			currentPage = "0";
		}
		String account = request.getParameter("account");
		String actType = request.getParameter("actType");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");

		String sql = "select * from financial f where (actMethod=1 or actMethod=2)";

		if (currentAppUser.getUserType() == 1) {
			sql += " and EXISTS (select account from appUser a where a.account=f.account and "
					+ "(a.parentId="
					+ currentAppUser.getId()
					+ " or a.account='" + currentAppUser.getAccount() + "'))";
		}
		if (currentAppUser.getUserType() == 2
				|| currentAppUser.getUserType() == 3) {
			sql += " and account='" + currentAppUser.getAccount() + "'";
		}

		if (account != null && !"".equals(account)) {
			sql += " and account='" + account + "'";
			request.setAttribute("account", account);
		}

		if (actType != null && !"".equals(actType)) {
			sql += " and actType=" + Integer.valueOf(actType);
			request.setAttribute("actType", actType);
		}

		if (startTime != null && !"".equals(startTime)) {
			sql += " and actTime >= '" + startTime + "'";
			request.setAttribute("startTime", startTime);
		}

		if (endTime != null && !"".equals(endTime)) {
			sql += " and actTime <= '" + endTime + "'";
			request.setAttribute("endTime", endTime);
		}

		PageEntity<Financial> page = this.financialManageDao.findAllPaginator(
				"findAllFinancialTotalCount", "findAllFinancial", sql,
				Integer.valueOf(currentPage));
		request.setAttribute("page", page);
		return "withdraw";
	}

	/**
	 * 充值扣费
	 * 
	 * @return
	 */
	public String recharge() {
		String actMethod = request.getParameter("actMethod");
		String account = request.getParameter("account");
		request.setAttribute("actMethod", actMethod);
		request.setAttribute("account", account);
		return "recharge";
	}

	/**
	 * 人工充值扣费
	 * 
	 * @return
	 */
	public String charge() {

		AppUser currentAppUser = (AppUser) session
				.getAttribute(GlobalConstants.GLOBALAPPUSER);

		String chargeErr = "";
		AppUser appUser = null;

		String account = request.getParameter("account");
		String actCount = request.getParameter("actCount");
		String actType = request.getParameter("actType");
		String actMethod = request.getParameter("actMethod");
		String memo = request.getParameter("memo");

		if (currentAppUser.getUserType() == 1
				&& Integer.valueOf(actCount) > currentAppUser.getBalance()) {
			chargeErr = "充值失败,当前余额不足以充值给" + account;
			request.setAttribute("chargeErr", chargeErr);
			return "recharge";
		}

		if (account != null && !"".equals(account)) {
			appUser = this.appUserManageDao.findAppUserByAccount(account);
		} else {
			chargeErr = "找不到该帐号";
			request.setAttribute("chargeErr", chargeErr);
			return "recharge";
		}

		if (!"0".equals(actMethod)
				&& Integer.valueOf(actCount) > appUser.getBalance()) {
			chargeErr = account + "帐号的余额小于即将被扣除的量";
			request.setAttribute("chargeErr", chargeErr);
			return "recharge";
		}

		int count = Integer.valueOf(actCount);
		if (!"0".equals(actMethod)) {
			count = 0 - count;
		}

		Financial financial = new Financial();
		financial.setAccount(account);
		financial.setActAccount(currentAppUser.getAccount());
		financial.setActCount(count);
		financial.setActType(Integer.valueOf(actType));
		financial.setActMethod(Integer.valueOf(actMethod));
		financial.setActTime(new Date());
		financial.setMemo(memo);

		this.addFinancial(financial);

		if (currentAppUser.getUserType() == 1) {

			currentAppUser.setBalance(currentAppUser.getBalance() - count);
			this.appUserManageDao.updateObject("updateAppUser", currentAppUser);

			Financial newFinancial = new Financial();
			newFinancial.setAccount(currentAppUser.getAccount());
			newFinancial.setActAccount(currentAppUser.getAccount());
			newFinancial.setActCount(0 - count);
			newFinancial.setActType(Integer.valueOf(actType));
			newFinancial.setActMethod("0".equals(actMethod) ? 1 : 0);
			newFinancial.setActTime(new Date());
			newFinancial.setMemo("0".equals(actMethod) ? "给子账户充值的条数"
					: "扣除子账户的条数");

			this.addFinancial(newFinancial);
			this.writeLog("0".equals(actMethod) ? currentAppUser.getAccount()
					+ "短信扣除了" + count + "条" : currentAppUser.getAccount()
					+ "短信充值了" + count + "条");
		}

		if ("0".equals(actMethod)) {
			chargeErr = "充值成功";
			this.writeLog(account + "充值了" + actCount + "条");
		} else {
			chargeErr = "扣费成功";
			this.writeLog(account + "扣除了" + actCount + "条");
		}
		appUser.setBalance(appUser.getBalance() + count);
		this.appUserManageDao.updateObject("updateAppUser", appUser);
		request.setAttribute("chargeErr", chargeErr);
		return "recharge";
	}

	public void setFinancialManageDao(FinancialManageDao financialManageDao) {
		this.financialManageDao = financialManageDao;
	}

	public void setAppUserManageDao(AppUserManageDao appUserManageDao) {
		this.appUserManageDao = appUserManageDao;
	}

}
