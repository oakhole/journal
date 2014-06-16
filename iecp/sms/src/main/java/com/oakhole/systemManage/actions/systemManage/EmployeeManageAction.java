package com.oakhole.systemManage.actions.systemManage;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import com.oakhole.common.action.BaseAction;
import com.oakhole.common.model.PageEntity;
import com.oakhole.common.util.constants.GlobalConstants;
import com.oakhole.financialManage.model.Financial;
import com.oakhole.systemManage.dao.AppUserManageDao;
import com.oakhole.systemManage.dao.ChannelManageDao;
import com.oakhole.systemManage.model.AppUser;
import com.oakhole.systemManage.model.Channel;

@SuppressWarnings("serial")
public class EmployeeManageAction extends BaseAction {

	@Autowired
	private AppUserManageDao appUserManageDao;

	@Autowired
	private ChannelManageDao channelManageDao;

	/**
	 * 更新员工信息 parameter : flag 为 0 时,则为新增，为1时，则为更新
	 * 
	 * @return
	 */
	public String addEmployee() {

		String flag = request.getParameter("flag");

		request.setAttribute("flag", flag);

		String employeeId = request.getParameter("employeeId");

		String sql = "select * from channel_group";

		PageEntity<Channel> page = this.channelManageDao.findAllPaginator(
				"findAllChannelGroupTotalCount", "findAllChannelGroup", sql,
				"asc");

		request.setAttribute("allChannelGroup", page.getResult());

		AppUser appUser = null;

		if ("0".equals(flag)) {
			return "addEmployee";
		} else {

			appUser = this.appUserManageDao.findObjectById("findAppUserById",
					Long.valueOf(employeeId));
			request.setAttribute("appUser", appUser);
			return "updateEmployee";
		}
	}

	/**
	 * 删除选中员工
	 * 
	 * @return
	 */
	public String deleteEmployee() {

		String employeeId = request.getParameter("employeeId");
		AppUser appUser = this.appUserManageDao.findObjectById(
				"findAppUserById", Long.valueOf(employeeId));
		this.appUserManageDao.delObject("deleteAppUser",
				Long.valueOf(employeeId));
		this.writeLog("删除员工" + appUser.getAccount() + "成功");

		return this.findAllEmployee();
	}

	/**
	 * 检查此账户是否存在
	 * 
	 * @return
	 */
	public String checkAccount() {

		String account = request.getParameter("account");
		AppUser appUser = this.appUserManageDao.findAppUserByAccount(account);
		if (appUser == null) {
			this.responsePrint("恭喜你,此账户可以使用");
		} else {
			this.responsePrint("亲,该用户已经存在,请换其他账户名试试看");
		}

		return null;
	}

	/**
	 * 将employee信息存入数据库
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String saveEmployee() {

		String saveEmpErr = "";

		AppUser currentAppUser = (AppUser) session
				.getAttribute(GlobalConstants.GLOBALAPPUSER);

		AppUser appUser = null;

		// 若flag==0添加新客户,flag==1更新客户
		String flag = request.getParameter("flag");
		request.setAttribute("flag", flag);

		String employeeId = request.getParameter("employeeId");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String linkman = request.getParameter("linkman");
		String phoneNumber = request.getParameter("phoneNumber");
		String mobile = request.getParameter("mobile");
		String qq = request.getParameter("qq");
		String email = request.getParameter("email");
		String userType = request.getParameter("userType");
		/**
		 * TODO : update temp 代理不能设置审核扣量
		 */
		// ~----------------------------------------------------------------------
		String isAudit = request.getParameter("isAudit") == null ? "0"
				: request.getParameter("isAudit");
		String auditCondition = request.getParameter("auditCondition") == null ? "0"
				: request.getParameter("auditCondition");
		String isCut = request.getParameter("isCut") == null ? "0" : request
				.getParameter("isCut");
		String cutCondition = request.getParameter("cutCondition") == null ? "0"
				: request.getParameter("cutCondition");
		String cutPercent = request.getParameter("cutPercent") == null ? "0"
				: request.getParameter("cutPercent");
		// ~----------------------------------------------------------------------
		String isStatus = request.getParameter("isStatus");
		String chargeType = request.getParameter("chargeType");
		String signature = request.getParameter("signature");
		String channelGroupId = request.getParameter("channelGroupId");
		String balance = request.getParameter("balance");
		String price = request.getParameter("price");

		// 新增系统控制功能
		String sysCutCondition = request.getParameter("sysCutCondition") == null ? "0"
				: request.getParameter("sysCutCondition");
		String sysCutPercent = request.getParameter("sysCutPercent") == null ? "0"
				: request.getParameter("sysCutPercent");

		// check the current request account whether exists .
		if ("0".equals(flag) || employeeId == null || "".equals(employeeId)) {

			if (account != null
					&& !"".equals(account)
					&& this.appUserManageDao.findAppUserByAccount(account) != null) {
				saveEmpErr = "亲,该用户已经存在,请换其他账户名试试看";
				request.setAttribute("saveEmpErr", saveEmpErr);
				return "addEmployee";
			}

			if (balance != null && !"".equals(balance)) {
				if (currentAppUser.getBalance() < Integer.valueOf(balance)
						&& currentAppUser.getUserType() == 1) {
					saveEmpErr = "您的余额不足以充值到新的帐号";
					request.setAttribute("saveEmpErr", saveEmpErr);
					return "addEmployee";
				}
			}

			appUser = new AppUser();
			appUser.setAccount(account);
			appUser.setEmail(email);
			appUser.setPassword(password);
			appUser.setPhoneNumber(phoneNumber);
			appUser.setQq(qq);
			appUser.setMobile(mobile);
			appUser.setLinkman(linkman);
			appUser.setUserType(Integer.valueOf(userType));
			appUser.setIsAudit(Integer.valueOf(isAudit));
			appUser.setAuditCondition(Integer.valueOf(auditCondition));
			appUser.setIsCut(Integer.valueOf(isCut));
			appUser.setCutCondition(Integer.valueOf(cutCondition));
			appUser.setCutPercent(Integer.valueOf(cutPercent));
			appUser.setIsStatus(Integer.valueOf(isStatus));
			appUser.setChargeType(Integer.valueOf(chargeType));
			appUser.setSignature(signature);
			appUser.setChannelGroupId(Integer.valueOf(channelGroupId));
			appUser.setBalance(Integer.valueOf(balance));
			appUser.setPrice(Float.valueOf(price));
			appUser.setParentId(((AppUser) session
					.getAttribute(GlobalConstants.GLOBALAPPUSER)).getId());
			String whiteListPath = "/whiteList/" + account + ".txt";
			File file = new File(request.getRealPath("/") + whiteListPath);
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					saveEmpErr = "创建白名单失败";
					e.printStackTrace();
				}
			}
			appUser.setWhitePhoneCount(0);
			appUser.setWhiteListPath(whiteListPath);

			// 新增系统控制功能
			appUser.setSysCutCondition(Integer.valueOf(sysCutCondition));
			appUser.setSysCutPercent(Integer.valueOf(sysCutPercent));

			this.appUserManageDao.addObject("addAppUser", appUser);

			saveEmpErr = "success";
			request.setAttribute("saveEmpErr", saveEmpErr);

			this.writeLog(account + "注册成功");

			if (balance != null && !"".equals(balance)) {
				// 将初始余额写入充值消费记录中
				Financial financial = new Financial();
				financial.setAccount(account);
				String actAccount = currentAppUser.getAccount();
				financial.setActAccount(actAccount);
				financial.setActCount(Integer.valueOf(balance));
				financial.setActType(0);
				financial.setActTime(new Date());
				financial.setActMethod(0);
				this.addFinancial(financial);

				if (currentAppUser.getUserType() == 1) {
					Financial newFinancial = new Financial();
					newFinancial.setAccount(account);
					newFinancial.setActAccount(actAccount);
					newFinancial.setActCount(0 - Integer.valueOf(balance));
					newFinancial.setActType(0);
					newFinancial.setActTime(new Date());
					newFinancial.setActMethod(1);
					newFinancial.setMemo("给帐号:" + appUser.getAccount()
							+ "开户充值时扣除");
					this.addFinancial(newFinancial);

					currentAppUser.setBalance(currentAppUser.getBalance()
							- Integer.valueOf(balance));
					this.appUserManageDao.updateObject("updateAppUser",
							currentAppUser);
				}
			}

			return "addEmployee";

		} else {
			appUser = this.appUserManageDao.findObjectById("findAppUserById",
					Integer.valueOf(employeeId));
			appUser.setAccount(account);
			appUser.setEmail(email);
			appUser.setPassword(password);
			appUser.setPhoneNumber(phoneNumber);
			appUser.setQq(qq);
			appUser.setMobile(mobile);
			appUser.setLinkman(linkman);
			appUser.setUserType(Integer.valueOf(userType));
			appUser.setIsAudit(Integer.valueOf(isAudit));
			appUser.setAuditCondition(Integer.valueOf(auditCondition));
			appUser.setIsCut(Integer.valueOf(isCut));
			appUser.setCutCondition(Integer.valueOf(cutCondition));
			appUser.setCutPercent(Integer.valueOf(cutPercent));
			appUser.setIsStatus(Integer.valueOf(isStatus));
			appUser.setChargeType(Integer.valueOf(chargeType));
			appUser.setSignature(signature);
			appUser.setChannelGroupId(Integer.valueOf(channelGroupId));
			appUser.setBalance(Integer.valueOf(balance));
			appUser.setPrice(Float.valueOf(price));

			// 新增系统控制功能
			appUser.setSysCutCondition(Integer.valueOf(sysCutCondition));
			appUser.setSysCutPercent(Integer.valueOf(sysCutPercent));

			this.appUserManageDao.updateObject("updateAppUser", appUser);
			saveEmpErr = "success";
			request.setAttribute("saveEmpErr", saveEmpErr);

			this.writeLog(account + "更新信息成功");

			if (isCut != null && !"".equals(isCut)) {
				request.setAttribute("isCut", isCut);
			}

			return "updateEmployee";
		}
	}

	/**
	 * 查询所有员工
	 * 
	 * @return
	 */
	public String findAllEmployee() {
		int currentPage = Integer
				.parseInt((request.getParameter("currentPage") == null
						|| "".equals(request.getParameter("currentPage")) ? "1"
						: request.getParameter("currentPage")));

		String isCut = request.getParameter("isCut");
		request.setAttribute("isCut", isCut);

		AppUser appUser = (AppUser) session
				.getAttribute(GlobalConstants.GLOBALAPPUSER);

		String account = request.getParameter("account");
		String linkman = request.getParameter("linkman");
		String userType = request.getParameter("userType");
		String mobile = request.getParameter("mobile");

		/**
		 * 代理商只能看到其子客户
		 */
		String sql = "select * from appUser where 1=1";

		if (isCut != null && !"".equals(isCut)) {
			sql += " and isCut=" + isCut;
		}

		if (appUser.getUserType() == 1) {
			sql += " and parentId=" + appUser.getId();
		}

		if (!"".equals(account) && account != null) {
			sql += " and account='" + account + "'";
			request.setAttribute("account", account);
		}

		if (!"".equals(linkman) && linkman != null) {
			sql += " and linkman='" + linkman + "'";
			request.setAttribute("linkman", linkman);
		}

		if (!"".equals(mobile) && mobile != null) {
			sql += " and mobile='" + mobile + "'";
			request.setAttribute("mobile", mobile);
		}

		if (!"".equals(userType) && userType != null) {
			sql += " and userType=" + Integer.valueOf(userType);
			request.setAttribute("userType", userType);
		}

		PageEntity<AppUser> page = this.appUserManageDao.findAllPaginator(
				"findAllEmployeeTotalCount", "findAllEmployee", sql,
				currentPage);

		request.setAttribute("currentPage", page.getCurrentPage());
		request.setAttribute("allEmployee", page.getResult());
		request.setAttribute("pageNum", page.getPageNum());
		request.setAttribute("pageCount", page.getPageCount());
		request.setAttribute("totalCount", page.getTotalCount());

		return "allEmployee";
	}

	public void setAppUserManageDao(AppUserManageDao appUserManageDao) {
		this.appUserManageDao = appUserManageDao;
	}

	public void setChannelManageDao(ChannelManageDao channelManageDao) {
		this.channelManageDao = channelManageDao;
	}

}
