/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.github.yoma.common.persistence;


import java.util.List;

/**
 * DAO支持类实现
 * @author ThinkGem
 * @version 2014-05-16
 * @param <T>
 */
public interface CrudDao<T> extends BaseDao {

	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T get(Long id);

	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	public T get(T entity);

	/**
	 * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity);
	/**
	 * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
	 * @param query
	 * @return
	 */
	public List<T> findList(BaseQueryDTO query);

	/**
	 * 查询所有数据列表
	 * @param entity
	 * @return
	 */
	public List<T> findAllList(T entity);

	/**
	 * 查询所有数据列表
	 * @see public List<T> findAllList(T entity)
	 * @return
	 */
	@Deprecated
	public List<T> findAllList();

	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	public int insert(T entity);
	/**
	 * 批量插入数据
	 * @param entityList
	 * @return
	 */
	public int batchInsert(List<T> entityList);

	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public int update(T entity);

//	/**
//	 * 删除数据（一般为逻辑删除，更新del_flag字段为1）
//	 * @param id
//	 * @see public int delete(T entity)
//	 * @return
//	 */
//	@Deprecated
//	public int delete(String id);

	/**
	 * 根据查询条件 删除数据（物理删除）
	 * @param entity
	 * @return
	 */
	public int delete(T entity);

	/**
	 * 根据查询条件 删除数据（物理删除）
	 * @param query
	 * @return
	 */
	public int batchDelete(BaseQueryDTO query);



	/**
	 * 根据查询条件 删除数据（物理删除）
	 * @param batchDTO
	 * @return
	 */
	public int batchDelete(BatchDTO batchDTO);
	/**
	 * 根据id list 批量更新对应id的记录为同样的记录值
	 * @param entity
	 * @return
	 */
	public int batchUpdateToSameValue(T entity);
	/**
	 * 批量更新
	 * @param entityList
	 * @return
	 */
	public int batchUpdate(List<T> entityList);

}
