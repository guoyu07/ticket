/**
 * @author 聚名科技
 * @date   2014-02-01
 */
package com.ticket.dao;

import java.io.Serializable;
import java.util.List;

import com.ticket.pojo.Pagination;

/**
 * 数据层接口
 * @ClassName: IDbDAO   
 * @Description:提供常用对增删改查功能   
 * @author 晏海水(Henry)  
 * @date Feb 1, 2014 6:50:37 AM   
 *   
 * @param <E>
 */
public interface IDbDAO<E, ID extends Serializable> {

	/**
	 * 持久化一个对象
	 * 
	 * @param object
	 */
	public void persist(Object object);

	/**
	 * 更新一个对象
	 * 
	 * @param object
	 */
	public void merge(Object object);

	/**
	 * 移除一个对象
	 * 
	 * @param object
	 */
	public void remove(Object object);
	
	/**
	 * @Description：根据提示类型和id得到一个实体
	 * @date 2015年12月17日 下午2:13:43
	 * @param c
	 * @param id
	 */
	public <T> T get(Class<T> c, String id);
	
	/**
	 * 逻辑删除一个对象
	 * 
	 * @param entityName
	 *            操作对象名称
	 * @param id
	 *            id
	 * @return 0失败 大于0成功多少条
	 */
	public int logicDelete(String entityName, ID id);

	/**
	 * 批量逻辑删除一批对象
	 * 
	 * @param entityName
	 *            操作对象名称
	 * @param ids
	 *            id集合 ,号隔开
	 * @return 0失败 大于0成功多少条
	 */
	public int logicDeleteAll(String entityName, String ids);

	/**
	 * 物理删除一个对象
	 * 
	 * @param entityName
	 *            操作对象名称
	 * @param id
	 *            id
	 * @return 0失败 大于0成功多少条
	 */
	public int delete(String entityName, ID id, String path);

	/**
	 * 物理删除一批对象
	 * 
	 * @param entityName
	 *            操作对象名称
	 * @param ids
	 *            id集合 ,号隔开
	 * @return 0失败 大于0成功多少条
	 */
	public int deleteAll(String entityName, String ids, String path);

	/**
	 * 按条件物理删除一批对象
	 * 
	 * @param entityName
	 *            操作对象名称
	 * @param deleteFlag
	 *            逻辑删除标识
	 * @param versionFlag
	 *            版本标识
	 * @param whereValue
	 *            搜索条件 前面必需加上 and
	 * @param value
	 *            搜索条件参数
	 * @return
	 */
	public int deleteByWhere(String entityName, Integer deleteFlag,
			String versionFlag, String whereValue, Object[] value, String path);
	
	/**
	 * 按条件物理删除一批对象
	 * 
	 * @param entityName
	 *            操作对象名称
	 * @param deleteFlag
	 *            逻辑删除标识
	 * @param versionFlag
	 *            版本标识
	 * @param whereValue
	 *            搜索条件 前面必需加上 and
	 * @param value
	 *            搜索条件参数
	 * @return
	 */
	public int deleteByWhere(String entityName, Integer deleteFlag,
			String versionFlag, String whereValue, Object[] value);

	/**
	 * 还原一个逻辑删除对象
	 * 
	 * @param entityName
	 *            操作对象名称
	 * @param id
	 *            id
	 * @return 0失败 大于0成功多少条
	 */
	public int restore(String entityName, ID id);

	/**
	 * 还原一批逻辑删除对象
	 * 
	 * @param entityName
	 *            操作对象名称
	 * @param ids
	 *            id集合 ,号隔开
	 * @return 0失败 大于0成功多少条
	 */
	public int restoreAll(String entityName, String ids);

	/**
	 * 还原所有逻辑删除对象
	 * 
	 * @param entityName
	 *            操作对象名称
	 * @return 0失败 大于0成功多少条
	 */
	public int restore(String entityName);

	/**
	 * 设置一个对象状态值
	 * 
	 * @param entityName
	 *            操作对象名称
	 * @param id
	 *            id
	 * @param stateField
	 *            要设置的对象的字段 比如审核isAudit
	 * @param stateValue
	 *            要设置的对象的状态值
	 * @return 0失败 大于0成功多少条
	 */
	public int setState(String entityName, ID id, String stateField,
			Integer stateValue);

	/**
	 * 批量设置一个对象状态值
	 * 
	 * @param entityName
	 *            操作对象名称
	 * @param id
	 *            id
	 * @param stateField
	 *            要设置的对象的字段 比如审核isAudit
	 * @param stateValue
	 *            要设置的对象的状态值
	 * @return 0失败 大于0成功多少条
	 */
	public int setStateAll(String entityName, String ids, String stateField,
			Integer stateValue);
	
	/**
	 * 批量设置一个对象状态值
	 * 
	 * @param entityName
	 *            操作对象名称
	 * @param id
	 *            id
	 * @param stateField
	 *            要设置的对象的字段 比如审核isAudit
	 * @param stateValue
	 *            要设置的对象的状态值
	 * @return 0失败 大于0成功多少条
	 */
	public int setStateAll(String entityName, String stateField,
			Integer stateValue, String whereValue, Object[] value);

	/**
	 * 获取对象目前最大的排序值
	 * 
	 * @param entityName
	 *            操作对象名称
	 * @param versionFlag
	 *            版本标识
	 * @return 0失败 大于0成功多少条
	 */
	public Long getMaxOrderValue(String entityName, String versionFlag);

	/**
	 * 以id搜索对象
	 * 
	 * @param entityName
	 *            操作对象名称
	 * @param id
	 *            id
	 * @return 对象
	 */
	public E queryById(String entityName, ID id);
	
	/**
	 * 根据实体对象的UUID获取对象实体
	 * @Title: IDbDAO
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param @param entityName 操作对象名称
	 * @param @param uuid      UUID属性
	 * @param @return    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public E queryByUuid(String entityName, String uuid);

	/**
	 * 以自定义条件搜索一个对象
	 * 
	 * @param entityName
	 *            操作对象名称
	 * @param deleteFlag
	 *            逻辑删除标识
	 * @param versionFlag
	 *            版本标识
	 * @param whereValue
	 *            搜索条件 前面必需加上 and
	 * @param value
	 *            搜索条件参数
	 * @return
	 */
	public E queryByCustom(String entityName, Integer deleteFlag,
			String versionFlag, String whereValue, Object[] value);

	/**
	 * 以自定义条件搜索一个对象 （对象某些属性）
	 * 
	 * @param entityName
	 *            操作对象名称
	 * @param deleteFlag
	 *            逻辑删除标识
	 * @param versionFlag
	 *            版本标识
	 * @param returnValue
	 *            返回对象属性名称
	 * @param whereValue
	 *            搜索条件 前面必需加上 and
	 * @param value
	 *            搜索条件参数
	 * @return
	 */
	public Object[] queryByCustom(String entityName, Integer deleteFlag,
			String versionFlag, String returnValue, String whereValue,
			Object[] value);
	
	/**
	 * 以自定义条件搜索一个对象 （对象某些属性）
	 * 
	 * @param entityName
	 *            操作对象名称
	 * @param deleteFlag
	 *            逻辑删除标识
	 * @param versionFlag
	 *            版本标识
	 * @param returnValue
	 *            返回对象属性名称
	 * @param whereValue
	 *            搜索条件 前面必需加上 and
	 * @param value
	 *            搜索条件参数
	 * @return
	 */
	public Long queryByCustomLong(String entityName, Integer deleteFlag,
			String versionFlag, String returnValue, String whereValue,
			Object[] value);
	
	/**
	 * 获取某个实体的最大ID值
	 * @Title: queryMaxVisitUrl 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param entityName
	 * @param @param deleteFlag
	 * @param @param versionFlag
	 * @param @param whereValue
	 * @param @param value
	 * @param @return    设定文件 
	 * @return Long    返回类型 
	 * @throws
	 */
	public Long queryMaxVisitUrl(String entityName, Integer deleteFlag,
			String versionFlag, String whereValue,
			Object[] value, String orderBy);

	
	/**
	 * 获取对象列表(不限版本标识)
	 * 
	 * @param entityName
	 *            操作对象名称
	 * @param deleteFlag
	 *            逻辑删除标识
	 * @param versionFlag
	 *            版本标识
	 * @param whereValue
	 *            搜索条件 前面必需加上 and
	 * @param value
	 *            搜索条件参数
	 * @param orderBy
	 *            排序 id desc , createtime asc 多个请以 ‘,’隔开
	 * @param size
	 *            一次获取多少个
	 * @return 返回整个实体对象 List
	 */
	public List<E> queryByListAndAllVersion(String entityName, Integer deleteFlag, String whereValue, Object[] value,
			String orderBy, Integer size);
	
	/**
	 * 获取对象列表
	 * 
	 * @param entityName
	 *            操作对象名称
	 * @param deleteFlag
	 *            逻辑删除标识
	 * @param versionFlag
	 *            版本标识
	 * @param whereValue
	 *            搜索条件 前面必需加上 and
	 * @param value
	 *            搜索条件参数
	 * @param orderBy
	 *            排序 id desc , createtime asc 多个请以 ‘,’隔开
	 * @param size
	 *            一次获取多少个
	 * @return 返回整个实体对象 List
	 */
	public List<E> queryByList(String entityName, Integer deleteFlag,
			String versionFlag, String whereValue, Object[] value,
			String orderBy, Integer size);

	/**
	 * 获取对象列表
	 * 
	 * @param entityName
	 *            操作对象名称
	 * @param deleteFlag
	 *            逻辑删除标识
	 * @param versionFlag
	 *            版本标识
	 * @param whereValue
	 *            搜索条件 前面必需加上 and
	 * @param value
	 *            搜索条件参数
	 * @param orderBy
	 *            排序 id desc , createtime asc 多个请以 ‘,’隔开
	 * @param size
	 *            一次获取多少个
	 * @return 返回整个实体对象 List
	 */
	public List<E> queryByList(String entityName, Integer deleteFlag, 
			String versionFlag, String whereValue, Object[] value, String orderBy, Integer startSize, Integer getCount);
	
	/**
	 * 获取对象列表 （对象某些属性）
	 * 
	 * @param entityName
	 *            操作对象名称
	 * @param deleteFlag
	 *            逻辑删除标识
	 * @param versionFlag
	 *            版本标识
	 * @param returnValue
	 *            返回对象属性名称
	 * @param whereValue
	 *            搜索条件 前面必需加上 and
	 * @param value
	 *            搜索条件参数
	 * @param orderBy
	 *            排序 id desc , createtime asc 多个请以 ‘,’隔开
	 * @param size
	 *            一次获取多少个
	 * @return 返回实体对象Object[]
	 */
	public Object[] queryByList(String entityName, Integer deleteFlag,
			String versionFlag, String returnValue, String whereValue,
			Object[] value, String orderBy, Integer size);

	/**
	 * 获取对象分页模型
	 * 
	 * @param entityName
	 *            操作对象名称
	 * @param deleteFlag
	 *            逻辑删除标识
	 * @param versionFlag
	 *            版本标识
	 * @param whereValue
	 *            搜索条件 前面必需加上 and
	 * @param value
	 *            搜索条件参数
	 * @param orderBy
	 *            排序 id desc , createtime asc 多个请以 ‘,’隔开
	 * @param page
	 *            当前页数
	 * @param pageSize
	 *            每页显示记录数
	 * @return 返回实体分页模型
	 */
	public Pagination queryByPageModule(String entityName, Integer deleteFlag,
			String versionFlag, String whereValue, Object[] value,
			String orderBy, Integer currentPage, Integer pageSize);

	/**
	 * 获取对象分页模型（对象某些属性）
	 * 
	 * @param entityName
	 *            操作对象名称
	 * @param deleteFlag
	 *            逻辑删除标识
	 * @param versionFlag
	 *            版本标识
	 * @param returnValue
	 *            返回对象属性名称
	 * @param whereValue
	 *            搜索条件 前面必需加上 and
	 * @param value
	 *            搜索条件参数
	 * @param orderBy
	 *            排序 id desc , createtime asc 多个请以 ‘,’隔开
	 * @param page
	 *            当前页数
	 * @param pageSize
	 *            每页显示记录数
	 * @return 返回实体某些属性分页模型
	 */
	public Pagination queryByPageModule(String entityName, Integer deleteFlag,
			String versionFlag, String returnValue, String whereValue,
			Object[] value, String orderBy, Integer currentPage, Integer pageSize);

	/**
	 * 调用存储过程获取对象分页模型（对象某些属性）
	 * 
	 * @param entityName
	 *            操作对象名称
	 * @param deleteFlag
	 *            逻辑删除标识
	 * @param versionFlag
	 *            版本标识
	 * @param returnValue
	 *            返回对象属性名称
	 * @param whereValue
	 *            搜索条件 前面必需加上 and
	 * @param value
	 *            搜索条件参数
	 * @param orderBy
	 *            排序 id desc , createtime asc 多个请以 ‘,’隔开
	 * @param page
	 *            当前页数
	 * @param pageSize
	 *            每页显示记录数
	 * @return 返回实体某些属性分页模型
	 */
	public Pagination queryByPageModuleCallStore(String entityName,
			Integer deleteFlag, String versionFlag, String returnValue,
			String whereValue, Object[] value, String orderBy, Integer currentPage,
			Integer pageSize);

	/**
	 * 统计对象记录数
	 * 
	 * @param entityName
	 *            操作对象名称
	 * @param deleteFlag
	 *            逻辑删除标识
	 * @param versionFlag
	 *            版本标识
	 * @param whereValue
	 *            搜索条件 前面必需加上 and
	 * @param value
	 *            搜索条件参数
	 * @return
	 */
	public Long getTotalCount(String entityName, Integer deleteFlag,
			String versionFlag, String whereValue, Object[] value);

	/**
	 * 统计对象记录数
	 * 
	 * @param entityName
	 *            操作对象名称
	 * @param deleteFlag
	 *            逻辑删除标识
	 * @param versionFlag
	 *            版本标识
	 * @param returnValue
	 *            返回对象属性运算 如 sum(s.num),count(s.id)
	 * @param whereValue
	 *            搜索条件 前面必需加上 and
	 * @param value
	 *            搜索条件参数
	 * @return
	 */
	public Object[] getTotalCount(String entityName, Integer deleteFlag,
			String versionFlag, String returnValue, String whereValue,
			Object[] value);

	/**
	 * 直接运行sql语句
	 * 
	 * @param sqlString
	 * @return
	 */
	public int executeSQL(String sqlString);

	/**
	 * 直接运行sql语句
	 * 
	 * @param sqlString
	 * @return
	 */
	public int executeSQL(String sqlString, Object[] value);
	
	/**
	 * 直接运行sql语句（用于统计金额类的数据）
	 * 
	 * @param sqlString
	 * @return
	 */
	public Double executeCountSQL(String sqlString);

	/**
	 * 直接运行sql语句（用于统计金额类的数据）
	 * 
	 * @param sqlString
	 * @return
	 */
	public Double executeCountSQL(String sqlString, Object[] value);
	
	/**
	 * @Description：执行原生sql的查询
	 * @param：@param sqlString sql语句
	 * @param：@param values sql占位符的的值
	 * @param：@return 返回查询的数据
	 * @throws
	 */
	public List<Object[]> executeSQLForQuery(String sqlString, Object...values);
	
	/**
	 * @Description：执行原生sql的查询
	 * @param：@param sqlString sql语句
	 * @param：@param values sql占位符的的值
	 * @param：@return 返回查询的数据
	 * @throws
	 */
	public List<Object[]> executeSQLQueryNoCache(String sqlString, Object...values);
	
	/**
	 * @Description：执行原生sql的查询并得到实体（暂时只支持一个实体）
	 * @param sqlString
	 * @param c
	 * @param values
	 * @return
	 */
	public <T> List<T> executeSQLForQuery(String sqlString, Class<T> c, Object...values);
	
	/**
	 * @Description：执行原生sql的查询并得到实体（暂时只支持一个实体）
	 * @param jpqlString jpqlString jpql语句
	 * @param c 返回的类型（只限返回一种实体的情况）
	 * @param start 分页查询的开始
	 * @param count 分页查询的条数
	 * @param values jpql占位符的的值
	 * @return 返回查询的数据
	 */
	public <T> List<T> executeSQLForQuery(String jpqlString, Class<T> c, Integer start, Integer count, Object...values);
	
	
	/**
	 * @Description：执行原生sql的查询并得到实体（暂时只支持一个实体）
	 * @param sqlString
	 * @param c
	 * @param values
	 * @return
	 */
	public <T> T executeSQLForQuerySingle(String sqlString, Class<T> c, Object...values);
	
	/**
	 * @Description：执行原生sql的更新
	 * @param：@param sqlString sql语句
	 * @param：@param values sql占位符的的值
	 * @param：@return 返回查询的数据
	 * @throws
	 */
	public int executeSQLForUpdate(String sqlString, Object...values);
	
	/**
	 * @Description：执行原生sql的更新
	 * @param：@param sqlString sql语句
	 * @param：@param values sql占位符的的值
	 * @param：@return 返回查询的数据
	 * @throws
	 */
	public int executeJPQLForUpdate(String sqlString, Object...values);
	
	/**
	 * @Description：执行原生sql返回单一值
	 * @param：@param sqlString sql语句
	 * @param：@param values sql占位符的的值
	 * @param：@return 返回查询的一个值
	 * @throws
	 */
	public String executeSQLForQuerySingle(String sqlString, Object...values);
	
	/**
	 * @Description：执行jpql的查询
	 * @param：@param jpqlString jpql语句
	 * @param：@param values jpql占位符的的值
	 * @param：@return 返回查询的数据
	 * @throws
	 */
	public List<Object> executeJPQLForQuery(String jpqlString, Object...values);
	
	/**
	 * @Description：执行jpql的查询
	 * @param：@param jpqlString jpql语句
	 * @param：@param values jpql占位符的的值
	 * @param：@return 返回查询的数据
	 * @throws
	 */
	public <T> List<T> executeJPQLForQuery(String jpqlString, Class<T> c, Object...values);

	/**
	 * @Description：
	 * @param：@param 
	 * @param：@param values 
	 * @param：@return 返回查询的数据
	 * @throws
	 */
	/**
	 * @Description：执行jpql的查询
	 * @param jpqlString jpqlString jpql语句
	 * @param start 分页查询的开始
	 * @param count 分页查询的条数
	 * @param valuesjpql占位符的的值
	 * @return
	 */
	public List<Object> executeJPQLForQuery(String jpqlString, Integer start, Integer count, Object...values);
	
	/**
	 * @Description：执行jpql的查询
	 * @param jpqlString jpqlString jpql语句
	 * @param c 返回的类型（只限返回一种实体的情况）
	 * @param start 分页查询的开始
	 * @param count 分页查询的条数
	 * @param values jpql占位符的的值
	 * @return 返回查询的数据
	 */
	public <T> List<T> executeJPQLForQuery(String jpqlString, Class<T> c, Integer start, Integer count, Object...values);
	
	/**
	 * @Description：执行jpql的查询
	 * @param：@param jpqlString jpql语句
	 * @param：@param values jpql占位符的的值
	 * @param：@return 返回查询的数据
	 * @throws
	 */
	public List<E> executeJPQLForQueryEntity(String jpqlString, Object...values);
	
	/**
	 * @Description：执行jpql返回单一值
	 * @param：@param jpqlString jpql语句
	 * @param：@param values jpql占位符的的值
	 * @param：@return 返回查询的一个对象
	 * @throws
	 */
	public Object executeJPQLForQuerySingle(String jpqlString, Object...values);
	
	/**
	 * @Description：执行jpql返回单一值
	 * @param：@param jpqlString jpql语句
	 * @param：@param values jpql占位符的的值
	 * @param：@return 返回查询的一个对象
	 * @throws
	 */
	public <T> T executeJPQLForQuerySingle(String jpqlString, Class<T> c, Object...values);
	/**
	 * 以自定义条件搜索一个对象 （对象某些属性）
	 * 
	 * @param entityName
	 *            操作对象名称
	 * @param deleteFlag
	 *            逻辑删除标识
	 * @param versionFlag
	 *            版本标识
	 * @param returnValue
	 *            返回对象属性名称
	 * @param whereValue
	 *            搜索条件 前面必需加上 and
	 * @param value
	 *            搜索条件参数
	 * @return
	 */
	public Object[] queryByCustomNew(String entityName, Integer deleteFlag,
			String versionFlag, String returnValue, String whereValue,
			List<Object[]> value);
	/**
	 * 获取对象列表
	 * 
	 * @param entityName
	 *            操作对象名称
	 * @param deleteFlag
	 *            逻辑删除标识
	 * @param versionFlag
	 *            版本标识
	 * @param whereValue
	 *            搜索条件 前面必需加上 and
	 * @param value
	 *            搜索条件参数
	 * @param orderBy
	 *            排序 id desc , createtime asc 多个请以 ‘,’隔开
	 * @param size
	 *            一次获取多少个
	 * @return 返回整个实体对象 List
	 */
	public List<E> queryByListNew(String entityName, Integer deleteFlag,
			String versionFlag, String whereValue, List<Object[]> value,
			String orderBy, Integer size);
	/**
	 * 统计对象记录数
	 * 
	 * @param entityName
	 *            操作对象名称
	 * @param deleteFlag
	 *            逻辑删除标识
	 * @param versionFlag
	 *            版本标识
	 * @param whereValue
	 *            搜索条件 前面必需加上 and
	 * @param value
	 *            搜索条件参数
	 * @return
	 */
	public Long getTotalCountNew(String entityName, Integer deleteFlag,
			String versionFlag, String whereValue, List<Object[]> value);
	/**
	 * 获取对象分页模型
	 * 
	 * @param entityName
	 *            操作对象名称
	 * @param deleteFlag
	 *            逻辑删除标识
	 * @param versionFlag
	 *            版本标识
	 * @param whereValue
	 *            搜索条件 前面必需加上 and
	 * @param value
	 *            搜索条件参数
	 * @param orderBy
	 *            排序 id desc , createtime asc 多个请以 ‘,’隔开
	 * @param page
	 *            当前页数
	 * @param pageSize
	 *            每页显示记录数
	 * @return 返回实体分页模型
	 */
	public Pagination queryByPageModuleNew(String entityName, Integer deleteFlag,
			String versionFlag, String whereValue, List<Object[]> value,
			String orderBy, Integer currentPage, Integer pageSize);
	/**
	 * @author wangjiafeng
	 * 执行编写好的sql获取分页模型
	 * @method queryByPageModuleNew
	 * @param jpqlString
	 * @param value
	 * @param orderBy
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @return Pagination
	 * @date 2015-12-29 下午04:20:11
	 */
	public Pagination queryByPageModuleNew(String sqlStart,String jpqlString, 
			List<Object[]> value,Integer currentPage, Integer pageSize);
}
