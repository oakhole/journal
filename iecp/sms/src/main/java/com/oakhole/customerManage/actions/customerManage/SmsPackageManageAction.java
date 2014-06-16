package com.oakhole.customerManage.actions.customerManage;

import org.springframework.beans.factory.annotation.Autowired;

import com.oakhole.common.action.BaseAction;
import com.oakhole.customerManage.dao.SmsPackageManageDao;

@SuppressWarnings("serial")
public class SmsPackageManageAction extends BaseAction {

	@SuppressWarnings("unused")
	@Autowired
	private SmsPackageManageDao smsPackageManageDao;

	public String findAllSmsPackage() {

		return "allSmsPackage";
	}

	public void setSmsPackageManageDao(SmsPackageManageDao smsPackageManageDao) {
		this.smsPackageManageDao = smsPackageManageDao;
	}

}
