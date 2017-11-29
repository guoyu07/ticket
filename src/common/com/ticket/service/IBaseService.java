package com.ticket.service;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ticket.dao.IDbDAO;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonAnnex;
import com.ticket.pojo.Pagination;

public interface IBaseService<T, ID extends Serializable> {
	
	/**
	 * @Description：得到dao
	 * @return
	 */
	IDbDAO getDbDAO();
	
	/**
	 * 持久化一个对象
	 * @param object
	 */
	void persist(Object object) throws ServiceException;

	/**
	 * 更新一个对象
	 * @param object
	 */
	void merge(Object object) throws ServiceException;

	/**
	 * 移除一个对象
	 * @param object
	 */
	void remove(Object object) throws ServiceException;
	
	/**
	 * @Description：查询指定类型的所有实体
	 * @date 2015年12月23日 上午11:37:22
	 * @param c
	 */
	<B> List<B> queryAll(Class<B> c);

	/**
	 * 总计数据条数
	 * @return
	 */
	public <B> long count(Class<B> c);
	
	/**
	 * 根据实体类别获取对应的数据库表名称
	 * @Title: IBaseService
	 * @Description: 从实体名称到表名称的转换   
	 * @param @param classObject  实体的类对象
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	String getTableNameFromEntity(Class<?> classObject) throws ServiceException;
	
	/**
	 * 根据实体对象ID获取对象
	 * @Title: IBaseService
	 * @Description: 根据实体的ID获取对象.   
	 * @param @param id
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	T queryById(String entityName, ID id) throws ServiceException;
	
	/**
	 * 根据实体对象的UUID获取对象
	 * @Title: IBaseService
	 * @Description: 根据实体的UUID获取对象.   
	 * @param @param uuid
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	T queryByUuid(String entityName, String uuid) throws ServiceException;
	
	/**
	 * @Description：根据实体的类型，和id获取实体
	 * @date 2015年12月17日 下午2:07:07
	 * @param c 实体类型
	 * @param id 实体id
	 * @return
	 */
	<E> E get(Class<E> c, String id);
	
	/**
	 * 逻辑删除实体对象(放入回收站)
	 * @Title: IBaseService
	 * @Description: 把实体对象放入回收站   
	 * @param @param id  实体主键
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	boolean logicDeleteEntity(String entityName, ID id) throws ServiceException;
	
	/**
	 * 从回收站还原一个实体对象
	 * @Title: IBaseService
	 * @Description: 把实体对象从回收站还原   
	 * @param @param id  实体对象ID
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	boolean restoreEntity(String entityName, ID id) throws ServiceException;
	
	/**
	 * 审核一个实体对象
	 * @Title: IBaseService
	 * @Description: 审核实体对象   
	 * @param @param id  实体对象ID
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	boolean auditEntity(String entityName, ID id, Integer statusValue) throws ServiceException;
	
	/**
	 * 查询在回收站的实体分页列表
	 * @Title: IBaseService
	 * @Description: 根据版本标识查询回收站的数据   
	 * @param @param versionFlag  版本标识
	 * @param @param pageSize     分页大小
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	Pagination queryRecycleEntity(String versionFlag, Integer pageSize) throws ServiceException;
	
	/**
	 * 查询正常的实体列表
	 * @Title: IBaseService
	 * @Description: 根据版本标识查询回收站的数据   
	 * @param @param versionFlag  版本标识
	 * @param @param pageSize     分页大小
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	Pagination queryEntityByAdmin(String versionFlag, Integer pageSize) throws ServiceException;
	
	/**
	 * @Description：条件分页查询
	 * @param condition
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryEntity(String condition, Object...params)
			throws ServiceException;
	
	/**
	 * @Description：条件分页查询
	 * @param condition
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryEntity(Class<?> tableClass, String condition, Object...params)
			throws ServiceException;
	
	/**
	 * 条件分页查询
	 * @param condition
	 * @param order
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryEntity(String condition, String order, Object...params)
			throws ServiceException;
	
	/**
	 * 条件分页查询
	 * @param condition
	 * @param order
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryEntity(Class<?> tableClass, String condition, String order, Object...params)
			throws ServiceException;
	
	/**
	 * 条件分页查询
	 * @param condition
	 * @param order
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	Pagination paginationQuery(String jpql, Object...params);
	
	/**
	 * 条件分页查询
	 * @param condition
	 * @param order
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	Pagination paginationQuery(String jpql, Class<?> tableName, Object...params);
	
	/**
	 * 查询正常的实体列表
	 * @Title: IBaseService
	 * @Description: 根据版本标识查询回收站的数据   
	 * @param @param versionFlag  版本标识
	 * @param @param pageSize     分页大小
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	Pagination queryEntityByAdmin(String versionFlag, Integer start, Integer pageSize) throws ServiceException;
	
	/**
	 * 批量操作实体状态属性   <海水提示: 这个只适合普通的实体批量操作, 对于有特殊业务关联的批量请自己实现相关的批量操作方法.>
	 * @Title: IBaseService
	 * @Description: 批量审核、批量推荐、批量还原、批量热门等   
	 * @param @param versionFlag    	 版本标识
	 * @param @param idsValue       	 要操作的ID集合字符串
	 * @param @param batchOperationType  操作类型（audit,commend,hot,logicDelete,restore）
	 * @param @param isChecked      	 是否选中,对于审核,推荐等操作时; 如果选中,则表示要把选中的信息设置为已审核,反之亦然.
	 * @param @return               	 操作成功返回true, 失败返回false
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	boolean batchOperationEntity(String versionFlag, String idsValue, String batchOperationType, boolean isChecked) throws ServiceException;
	
	/**
	 * @Description：批量真实删除
	 * @param ids
	 * @return
	 */
	boolean batchRealDelete(Class clazz, String ids);
	
	/**
	 * @Title: deleteAnnex 
	 * @Description: 删除实体附件数据 
	 * @param @param entity
	 * @param @param objectId
	 * @param @param type
	 * @param @param versoinFlag
	 * @param @return
	 * @param @throws ServiceException    设定文件 
	 * @return Boolean    返回类型 
	 * @throws
	 */
	Boolean deleteAnnex(T entity, Object objectId, Integer type, String versoinFlag) throws ServiceException;
	/**
	 * 删除实体的全部附件
	 * @Title: deleteAllAnnex 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param entity
	 * @param @param objectId
	 * @param @param versoinFlag
	 * @param @return
	 * @param @throws ServiceException    设定文件 
	 * @return Boolean    返回类型 
	 * @throws
	 */
	Boolean deleteAllAnnex(T entity, Object objectId, String versoinFlag) throws ServiceException;
	/**
	 * 添加单个附件
	 * @param object
	 * @param annex
	 * @return
	 */
	CommonAnnex addAnnex(T entity, Object objectId, String annex, Integer type, String versoinFlag) throws ServiceException;
	/**
	 * 添加多个附件
	 * @param object
	 * @param annexs
	 * @return
	 */
	Boolean addManyAnnex(T entity, Object objectId, List<String> annexs, Integer type, String versoinFlag) throws ServiceException;
	
	/**
	 * 解析request里面的参数
	 * @param request
	 * @return
	 */
	String getParameterValues(HttpServletRequest request) throws ServiceException;
	
	/**
	 * 验证短信验证码是否输入准确
	 * @Title: validateSmsCodeIsOK 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param smsCode
	 * @param @return
	 * @param @throws ServiceException    设定文件 
	 * @return boolean    返回类型 
	 * @throws
	 */
	boolean validateSmsCodeIsOK(String mobile, String smsCode) throws ServiceException;
	
	
}