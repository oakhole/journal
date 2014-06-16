package com.oakhole.customerManage.actions.customerManage;

import org.springframework.beans.factory.annotation.Autowired;

import com.oakhole.common.action.BaseAction;
import com.oakhole.customerManage.dao.CustomerGroupManageDao;

@SuppressWarnings("serial")
public class CustomerGroupManageAction extends BaseAction {

	@SuppressWarnings("unused")
	@Autowired
	private CustomerGroupManageDao customerGroupManageDao;

	public String findAllCustomerGroup() {

		return "allCustomerGroup";
	}

	public void setCustomerGroupManageDao(
			CustomerGroupManageDao customerGroupManageDao) {
		this.customerGroupManageDao = customerGroupManageDao;
	}
}
