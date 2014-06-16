package com.oakhole.systemManage.actions.systemManage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;

import com.oakhole.common.action.BaseAction;
import com.oakhole.common.model.PageEntity;
import com.oakhole.common.util.constants.GlobalConstants;
import com.oakhole.common.util.fileUtils.PhoneFileUtils;
import com.oakhole.systemManage.dao.AppUserManageDao;
import com.oakhole.systemManage.dao.SettingManageDao;
import com.oakhole.systemManage.model.AppUser;
import com.oakhole.systemManage.model.Setting;

@SuppressWarnings("serial")
public class SystemManageAction extends BaseAction {

	@Autowired
	private AppUserManageDao appUserManageDao;

	@Autowired
	private SettingManageDao settingManageDao;

	/**
	 * 生日提醒
	 * 
	 * @return
	 */
	public String birthday() {
		return "birthday";
	}

	/**
	 * 号码分流
	 * 
	 * @return
	 */
	public String phoneSplit() {
		return "phoneSplit";
	}

	/**
	 * 号码告警设置
	 * 
	 * @return
	 */
	public String alarmSetting() {
		return "alarmSetting";
	}

	/**
	 * 屏蔽号段
	 * 
	 * @return
	 */
	public String badSeg() {
		return "badSeg";
	}

	/**
	 * 数据备份
	 * 
	 * @return
	 */
	public String dataBackup() {
		return "dataBackup";
	}

	/**
	 * 数据清理
	 * 
	 * @return
	 */
	public String dataCleanup() {
		return "dataCleanup";
	}

	/**
	 * 更改个人信息
	 * 
	 * @return
	 */
	public String updateMe() {

		AppUser appUser = (AppUser) session
				.getAttribute(GlobalConstants.GLOBALAPPUSER);
		request.setAttribute("appUser", appUser);
		return "updateMe";
	}

	/**
	 * 系统设置
	 * 
	 * @return
	 */
	public String systemSetting() {

		String sql = "select * from setting";

		PageEntity<Setting> page = this.settingManageDao.findAllPaginator(
				"findAllSettingTotalCount", "findAllSetting", sql, "asc");

		Setting setting = page.getResult().get(0);

		request.setAttribute("setting", setting);

		return "systemSetting";
	}

	/**
	 * 更新系统设置
	 */
	public String updateSystemSetting() {

		String settingId = request.getParameter("settingId");

		Setting setting = this.settingManageDao.findObjectById(
				"findSettingById", Integer.valueOf(settingId));

		setting.setSendLimitDown(Integer.valueOf(request
				.getParameter("sendLimitDown")));
		setting.setSendLimitUp(Integer.valueOf(request
				.getParameter("sendLimitUp")));
		setting.setCutCondition(Integer.valueOf(request
				.getParameter("cutCondition")));
		setting.setCutPercent(Integer.valueOf(request
				.getParameter("cutPercent")));
		setting.setMaxWhiteNum(Integer.valueOf(request
				.getParameter("maxWhiteNum")));
		setting.setIsCollectWhiteList(Integer.valueOf(request
				.getParameter("isCollectWhiteList")));

		setting.setCollectWhiteListCondition(Integer.valueOf(request
				.getParameter("collectWhiteListCondition")));

		this.settingManageDao.updateObject("updateSetting", setting);

		request.setAttribute("updateMsg", "更新成功");

		request.setAttribute("setting", setting);

		return "systemSetting";
	}

	/**
	 * 白名单
	 * 
	 * @return
	 */
	public String whiteList() {

		AppUser appUser = (AppUser) session
				.getAttribute(GlobalConstants.GLOBALAPPUSER);

		String sql = "select * from appUser where userType <> 0";

		if (appUser.getUserType() == 1) {
			sql += " and parentId=" + appUser.getId();
		}

		String currentPage = request.getParameter("currentPage");

		if (currentPage == null || "".equals(currentPage)) {
			currentPage = "1";
		}

		PageEntity<AppUser> page = this.appUserManageDao.findAllPaginator(
				"findAllEmployeeTotalCount", "findAllEmployee", sql,
				Integer.valueOf(currentPage));

		request.setAttribute("allEmployee", page.getResult());
		request.setAttribute("totalCount", page.getTotalCount());
		request.setAttribute("currentPage", page.getCurrentPage());
		request.setAttribute("pageCount", page.getPageCount());
		request.setAttribute("pageNum", page.getPageNum());

		return "whiteList";
	}

	/**
	 * 保存白名单
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String saveWhiteList() {

		String employeeId = request.getParameter("employeeId");
		String whiteListPath = request.getParameter("whiteListPath");
		String mobile = request.getParameter("mobile");
		File file = new File(request.getRealPath("/") + whiteListPath);
		int count = PhoneFileUtils.write2Txt(mobile, file);
		AppUser user = this.appUserManageDao.findObjectById("findAppUserById",
				Long.valueOf(employeeId));
		user.setWhitePhoneCount(count);
		this.appUserManageDao.updateObject("updateAppUser", user);

		return this.whiteList();
	}

	/**
	 * 更新白名单
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String updateWhiteList() {
		String employeeId = request.getParameter("employeeId");
		String whiteListPath = this.appUserManageDao.findObjectById(
				"findAppUserById", Long.valueOf(employeeId)).getWhiteListPath();
		String mobile = "";
		StringBuffer sb = new StringBuffer();
		File file = new File(request.getRealPath("/") + whiteListPath);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String tmp = "";
			while ((tmp = br.readLine()) != null) {
				sb.append(tmp + "\n");
			}
			mobile = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		request.setAttribute("employeeId", employeeId);
		request.setAttribute(
				"mobile",
				"".equals(mobile) ? "" : mobile.substring(0,
						mobile.lastIndexOf("\n")));
		request.setAttribute("whiteListPath", whiteListPath);
		return "updateWhiteList";
	}
	
	/**
	 * 查看白名单
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String getWhiteList(){
		String employeeId = request.getParameter("employeeId");
		String whiteListPath = this.appUserManageDao.findObjectById(
				"findAppUserById", Long.valueOf(employeeId)).getWhiteListPath();
		String mobile = "";
		StringBuffer sb = new StringBuffer();
		File file = new File(request.getRealPath("/") + whiteListPath);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String tmp = "";
			while ((tmp = br.readLine()) != null) {
				sb.append(tmp + "\n");
			}
			mobile = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		request.setAttribute(
				"mobile",
				"".equals(mobile) ? "" : mobile.substring(0,
						mobile.lastIndexOf("\n")));
		return "getWhiteList";
	}

	/**
	 * 清空白名单
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String clearWhiteList() {
		String employeeId = request.getParameter("employeeId");

		AppUser appUser = this.appUserManageDao.findObjectById(
				"findAppUserById", Long.valueOf(employeeId));
		String path = appUser.getWhiteListPath();
		PrintWriter pw = null;
		try {
			File file = new File(request.getRealPath("/") + path);
			if (file.exists()) {
				pw = new PrintWriter(file);
				pw.print("");
				pw.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (pw != null) {
			pw.close();
		}
		appUser.setWhitePhoneCount(0);
		this.appUserManageDao.updateObject("updateAppUser", appUser);
		return this.whiteList();
	}

	/**
	 * 黑名单
	 * 
	 * @return
	 */
	public String blackList() {
		return "blackList";
	}

	/**
	 * 注册模版
	 * 
	 * @return
	 */
	public String registTemplate() {
		return "registTemplate";
	}

	/**
	 * 密码修改
	 * 
	 * @return
	 */
	public String editPass() {

		String oldpassword = request.getParameter("oldpassword");
		String password = request.getParameter("password");
		String password1 = request.getParameter("password1");

		String editPwdErr = "";

		if (oldpassword != null && password != null && password1 != null) {
			AppUser appUser = (AppUser) session
					.getAttribute(GlobalConstants.GLOBALAPPUSER);
			String srcPassword = appUser.getPassword();
			if (oldpassword.equals(srcPassword)) {
				appUser.setPassword(password);
				int flag = this.appUserManageDao.updateObject("updateAppUser",
						appUser);
				if (flag == 1) {
					editPwdErr = "恭喜您,密码修改成功";
					this.writeLog("密码修改成功");
				} else {
					editPwdErr = "更新出现异常,请及时与管理员取得联系";
				}
			} else {
				editPwdErr = "旧密码输入错误，请重新输入";
			}
		}
		request.setAttribute("editPwdErr", editPwdErr);
		return "editPass";
	}

	public void setAppUserManageDao(AppUserManageDao appUserManageDao) {
		this.appUserManageDao = appUserManageDao;
	}

	public void setSettingManageDao(SettingManageDao settingManageDao) {
		this.settingManageDao = settingManageDao;
	}
}
