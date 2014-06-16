package com.oakhole.httpApi.actions;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.oakhole.common.action.BaseAction;
import com.oakhole.smsManage.dao.SmsManageDao;
import com.oakhole.smsManage.model.Sms;
import com.oakhole.systemManage.dao.AppUserManageDao;
import com.oakhole.systemManage.model.AppUser;

@SuppressWarnings("serial")
public class Getreport extends BaseAction {

	@Autowired
	private AppUserManageDao appUserManageDao;

	@Autowired
	private SmsManageDao smsManageDao;

	private String account;
	private String password;

	@Override
	public String execute() throws Exception {

		AppUser currentAppUser = this.appUserManageDao
				.findAppUserByAccount(account);

		if (account == null || currentAppUser == null) {
			this.response.getWriter().println("1,incorrect account");
			return null;
		}

		if (password == null || !currentAppUser.getPassword().equals(password)) {
			this.response.getWriter().println("2,incorrect password");
			return null;
		}

		int limit = 100;

		List<Sms> result = this.smsManageDao.getReport(account, limit);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

		this.response.getWriter().println("0," + result.size());

		for (Sms s : result) {
			this.response.getWriter().println(
					s.getSendId() + "," + s.getMobile() + "," + s.getStatus()
							+ "," + sdf.format(s.getStatusTime()));
		}

		return null;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAppUserManageDao(AppUserManageDao appUserManageDao) {
		this.appUserManageDao = appUserManageDao;
	}

	public void setSmsManageDao(SmsManageDao smsManageDao) {
		this.smsManageDao = smsManageDao;
	}
}
