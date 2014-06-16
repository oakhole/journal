package com.oakhole.httpApi.actions;

import org.springframework.beans.factory.annotation.Autowired;

import com.oakhole.common.action.BaseAction;
import com.oakhole.systemManage.dao.AppUserManageDao;
import com.oakhole.systemManage.model.AppUser;

@SuppressWarnings("serial")
public class Getbalance extends BaseAction {

	@Autowired
	private AppUserManageDao appUserManageDao;

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

		this.response.getWriter().println("0," + currentAppUser.getBalance());

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

}
