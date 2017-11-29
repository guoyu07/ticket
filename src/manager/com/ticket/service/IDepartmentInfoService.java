package com.ticket.service;

import java.util.Collection;
import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.DepartmentInfo;


/**
 * 部门信息业务接口
 * @ClassName: IDepartmentInfoService   
 * @Description: 提供部门信息操作的增删改查   
 * @author HiSay  
 * @date  2015-11-02 22:46:07
 *
 */
public interface IDepartmentInfoService extends IBaseService<DepartmentInfo, String> {
	/**
	 * 保存部门信息实体
	 * @Title: IDepartmentInfoService
	 * @Description:
	 * @param @param name  部门名称
	 * @param @param introduce  部门介绍
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String introduce, String parentId) throws ServiceException;
	
	/**
	 * 修改部门信息实体
	 * @Title: IDepartmentInfoService
	 * @Description:
	 * @param @param name  部门名称
	 * @param @param introduce  部门介绍
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String introduce, String parentId) throws ServiceException;
	
	/**
	 * 移除部门信息实体
	 * @Title: IDepartmentInfoService
	 * @Description: 
	 * @param @param id 部门信息ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 批量彻底删除部门实体
	 * @param idsValue
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException;

	/**
	 * 获取部门列表
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<DepartmentInfo> queryByList(String versionFlag) throws ServiceException;
	/**
	 * @author wangjiafeng
	 *  改变一个类别的path
	 * @method changePath
	 * @param departmentInfo
	 * @throws ServiceException
	 * @return void
	 * @date 2014-5-14 上午10:34:32
	 */
	void changePath(DepartmentInfo departmentInfo) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 改变一个类别的深度
	 * @method changeDept
	 * @param departmentInfo
	 * @throws ServiceException
	 * @return void
	 * @date 2014-5-14 上午10:35:07
	 */
	void changeDeep(DepartmentInfo departmentInfo) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 解析类别下面的所有子类别id
	 * @method parseClassIds
	 * @param classId
	 * @return
	 * @throws ServiceException
	 * @return String
	 * @date 2014-5-14 上午10:00:27
	 */
	String parseClassIds(String classId) throws ServiceException;
	/**
	 * @author wangjiafeng
	 *  获取所有的顶级级类别
	 * @param size 
	 * @method queryClassByParentIsNull
	 * @return
	 * @return List<DepartmentInfo>
	 * @date 2014-5-14 上午09:38:22
	 */
	List<DepartmentInfo> queryClassByParentIsNull(Integer size) throws  ServiceException;
	/**
	 * @author wangjiafeng
	 * 获取部门顶级目录所树
	 * @method createDepartmentInfoTree
	 * @return
	 * @throws ServiceException
	 * @return String
	 * @date 2014-5-14 上午09:44:31
	 */
	String createDepartmentInfoTree() throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 根据LisT写出目录树
	 * @method createDepartmentInfoTree
	 * @param list
	 * @return
	 * @throws ServiceException
	 * @return String
	 * @date 2014-5-14 上午09:43:52
	 */
	String createDepartmentInfoTree(List<DepartmentInfo> list)
			throws ServiceException;

	/**
	 * @author wangjaifeng
	 * 根据栏目id获取目录树
	 * @method createMemberCompanyTypeOptionHtml
	 * @param id
	 * @return
	 * @throws ServiceException
	 * @return String
	 * @date 2014-5-14 上午09:43:14
	 */
	String createDepartmentInfoOptionHtml(String id)
			throws ServiceException;

	/**
	 * @author wangjiafeng
	 * 根据list和指定的id获取指定的目录树
	 * @method createMemberCompanyTypeOptionHtml
	 * @param topList
	 * @param level
	 * @param selectId
	 * @return
	 * @throws ServiceException
	 * @return String
	 * @date 2014-5-14 上午09:42:31
	 */
	String createDepartmentInfoOptionHtml(List<DepartmentInfo> topList,
			int level, String selectId) throws ServiceException;

	/**
	 * 获取顶级部门
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<DepartmentInfo> queryFirstDept(String versionFlag) throws ServiceException;
	
	/**
	 * 获取部门树
	 * @param depts 顶级部门集合
	 * @return
	 * @throws ServiceException
	 */
	String getDeptTree(Collection<DepartmentInfo> depts) throws ServiceException;
	
	/**
	 * 通过员工id得到部门信息
	 * @param employee_id
	 * @return
	 */
	DepartmentInfo getByEmployee(String employee_id);

	/**
	 * 查询部门的子部门
	 * @param dept 部门
	 * @return
	 * @throws ServiceException
	 */
	List<DepartmentInfo> getChildList(DepartmentInfo dept) throws ServiceException;
	/**
	 * 根据部门名称查找部门
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	DepartmentInfo getByName(String name) throws ServiceException;

	/**
	 * 根据当前部门id查询下属部门列表
	 * @param current_id 当前部门id
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<DepartmentInfo> queryListByCurrentId(String current_id, String versionFlag) throws ServiceException;

	/**
	 * 根据当前部门id查询下属部门id集合(含本部门id)
	 * @param department_id 部门id
	 * @return
	 * @throws ServiceException
	 */
	String getChildIdsBycurrentId(String department_id) throws ServiceException;

}