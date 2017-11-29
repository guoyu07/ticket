package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.Pagination;


/**
 * 员工信息业务接口
 * @ClassName: IEmployeeInfoService   
 * @Description: 提供员工信息操作的增删改查   
 * @author HiSay  
 * @date  2015-11-03 15:33:02
 *
 */
public interface IEmployeeInfoService extends IBaseService<EmployeeInfo, String> {
	
	/**
	 * 保存员工信息实体
	 * @Title: IEmployeeInfoService
	 * @Description:
	 * @param @param position  职务
	 * @param @param department_id  部门
	 * @param @param name  姓名
	 * @param @param entryDate  入职日期
	 * @param @param IDCard  身份证
	 * @param @param phone  联系电话
	 * @param @param emergencyContact  紧急联系人
	 * @param @param emergencyPhone  紧急联系电话
	 * @param @return  保存成功则返回id
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	 String add(String position,String department_id,String name,String entryDate,String IDCard,
			 String phone,String emergencyContact,String emergencyPhone,String loginId,String password,
			 Integer state,String position_id) throws ServiceException;
	
	
	/**
	 * 修改员工信息实体
	 * @Title: IEmployeeInfoService
	 * @Description:
	 * @param @param position  职务
	 * @param @param department_id  部门
	 * @param @param name  姓名
	 * @param @param entryDate  入职日期
	 * @param @param IDCard  身份证
	 * @param @param phone  联系电话
	 * @param @param emergencyContact  紧急联系人
	 * @param @param emergencyPhone  紧急联系电话
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String position,String department_id,String name,String entryDate,String IDCard,
			String phone,String emergencyContact,String emergencyPhone, Integer state,String position_id,
			String loginId,String password) throws ServiceException;
	
	/**
	 * 移除员工信息实体
	 * @Title: IEmployeeInfoService
	 * @Description: 
	 * @param @param id 员工信息ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 批量彻底删除员工信息实体
	 * @param idsValue
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException;

	/**
	 * 获取员工列表
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<EmployeeInfo> queryByList(String versionFlag) throws ServiceException;

	/**
	 * 根据部门信息查找员工信息
	 * @param department_id
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<EmployeeInfo> queryByDepartmentId(String department_id, String versionFlag)throws ServiceException;

	/**
	 * 根据员工姓名/电话关键词检索员工信息
	 * @param keyword  关键词
	 * @param pageSize 页面大小
	 * @param versionFlag  版本信息
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryPageModuleByKeyword(String keyword, int pageSize, String versionFlag) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 获取登录的会员
	 * @method queryByLogin
	 * @param loginId
	 * @param password
	 * @return
	 * @throws ServiceException
	 * @return EmployeeInfo
	 * @date 2016-1-18 下午04:13:08
	 */
	EmployeeInfo queryByLogin(String loginId,String password) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 绑定渠道客户
	 * @method bindChannelCustomerInfo
	 * @param id
	 * @param channelCustomerInfo_id
	 * @return
	 * @throws ServiceException
	 * @return Boolean
	 * @date 2016-1-19 下午01:44:16
	 */
	Boolean bindChannelCustomerInfo(String id,String channelCustomerInfo_id) throws ServiceException;
	
	/**
	 * 根据所在部门查询所在部门及以下部门数据
	 * @param employeeInfo
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryWhereInDepartment(EmployeeInfo employeeInfo) throws ServiceException;
}