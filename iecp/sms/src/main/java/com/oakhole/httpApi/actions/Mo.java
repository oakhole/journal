package com.oakhole.httpApi.actions;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.oakhole.common.action.BaseAction;
import com.oakhole.smsManage.dao.SmsReceiveManageDao;
import com.oakhole.smsManage.model.SmsReceive;
import com.oakhole.systemManage.dao.AppUserManageDao;
import com.oakhole.systemManage.model.AppUser;

@SuppressWarnings("serial")
public class Mo extends BaseAction {

	@Autowired
	private AppUserManageDao appUserManageDao;

	@Autowired
	private SmsReceiveManageDao smsReceiveManageDao;

	private String account;
	private String password;

	@Override
	public String execute() throws Exception {
		AppUser currentAppUser = this.appUserManageDao
				.findAppUserByAccount(account);

		if (account == null || currentAppUser == null) {
			this.response.getWriter().println("1,没有此用户");
			return null;
		}

		if (password == null || !currentAppUser.getPassword().equals(password)) {
			this.response.getWriter().println("2,密码错误");
			return null;
		}

		int limit = 100;

		List<SmsReceive> smsReceiveList = this.smsReceiveManageDao.getReceive(
				account, limit);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

		this.response.getWriter().println("0," + smsReceiveList.size());

		for (SmsReceive s : smsReceiveList) {
			this.response.getWriter().println(
					s.getMobile() + "," + s.getContent() + ","
							+ sdf.format(s.getReceiveTime()));
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

	public void setSmsReceiveManageDao(SmsReceiveManageDao smsReceiveManageDao) {
		this.smsReceiveManageDao = smsReceiveManageDao;
	}
}
