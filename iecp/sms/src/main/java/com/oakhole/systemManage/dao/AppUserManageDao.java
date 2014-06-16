package com.oakhole.systemManage.dao;

import com.oakhole.common.dao.BaseDao;
import com.oakhole.systemManage.model.AppUser;

public interface AppUserManageDao extends BaseDao<AppUser> {

	public AppUser findAppUserByAccount(String account);

	public void chargeFailure();

}
