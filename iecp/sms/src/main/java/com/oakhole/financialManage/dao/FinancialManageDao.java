package com.oakhole.financialManage.dao;

import java.util.List;

import com.oakhole.common.dao.BaseDao;
import com.oakhole.financialManage.model.Financial;

public interface FinancialManageDao extends BaseDao<Financial> {

	public void batchAddFinancial(List<Financial> financiaList);
}
