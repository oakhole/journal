package com.oakhole.customerManage.actions.customerManage;

import org.springframework.beans.factory.annotation.Autowired;

import com.oakhole.common.action.BaseAction;
import com.oakhole.customerManage.dao.CustomerManageDao;

@SuppressWarnings("serial")
public class CustomerManageAction extends BaseAction {

	@SuppressWarnings("unused")
	@Autowired
	private CustomerManageDao customerManageDao;

	/**
	 * 查询所有客户
	 * 
	 * @return
	 */
	public String findAllCustomer() {
		return "allCustomer";
	}

	/**
	 * 直接客户
	 * 
	 * @return
	 */
	public String directCustomer() {
		return "directCustomer";
	}

	/**
	 * 作废客户
	 * 
	 * @return
	 */
	public String invalidCustomer() {
		return "invalidCustomer";
	}

	/**
	 * 间接客户
	 * 
	 * @return
	 */
	public String indirectCustomer() {
		return "indirectCustomer";
	}

	/**
	 * 查询子账户
	 * 
	 * @return
	 */
	public String subCustomer() {
		return "subCustomer";
	}

	/**
	 * 更新客户
	 * 
	 * @return
	 */
	public String updateCustomer() {
		return "updateCustomer";
	}

	/**
	 * 添加新客户
	 * 
	 * @return
	 */
	public String addCustomer() {
		return "addCustomer";
	}

	/**
	 * 客户信息详情
	 * 
	 * @return
	 */
	public String customerInfo() {
		return "customerInfo";
	}

	public void setCustomerManageDao(CustomerManageDao customerManageDao) {
		this.customerManageDao = customerManageDao;
	}
}
