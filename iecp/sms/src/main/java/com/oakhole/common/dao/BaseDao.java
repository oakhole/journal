package com.oakhole.common.dao;

import java.io.Serializable;

import com.oakhole.common.model.PageEntity;

/**
 * 基类接口
 * 
 * @author oakhole
 * @param <T>
 */
public interface BaseDao<T> extends Serializable {

	/**
	 * 增加一个对象
	 * 
	 * @param paremt
	 * @param t
	 * @return 主键值,自增id
	 */
	void addObject(String paremt, T t);

	/**
	 * 删除一个对象
	 * 
	 * @param paremt
	 * @param t
	 * @return
	 */
	int delObject(String paremt, long id);

	/**
	 * 更新一个对象
	 * 
	 * @param paremt
	 * @param t
	 * @return
	 */
	int updateObject(String paremt, T t);

	/**
	 * 按id值查询对象
	 * 
	 * @param id
	 * @return
	 */
	public T findObjectById(String paremt, long id);

	/**
	 * 根据参数，查找所有对象并分页
	 * 
	 * @param arg1
	 *            获取总记录数sqlmap id
	 * @param arg2
	 *            获取分页记录的sqlmap id
	 * @param sql
	 * @return
	 */

	public PageEntity<T> findAllPaginator(String arg1, String arg2, String sql,
			int currentPage, int pageSize, String sortRule);

	public PageEntity<T> findAllPaginator(String arg1, String arg2, String sql,
			int currentPage, int pageSize);

	public PageEntity<T> findAllPaginator(String arg1, String arg2, String sql,
			int currentPage);

	public PageEntity<T> findAllPaginator(String arg1, String arg2, String sql,
			String sortRule);

}
