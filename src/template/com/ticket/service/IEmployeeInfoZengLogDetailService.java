package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.EmployeeInfoZengLogDetail;


/**
 * 员工转移服务码附表业务接口
 * @ClassName: IEmployeeInfoZengLogDetailService   
 * @Description: 提供员工转移服务码附表操作的增删改查   
 * @author HiSay  
 * @date  2016-01-18 17:27:18
 *
 */
public interface IEmployeeInfoZengLogDetailService extends IBaseService<EmployeeInfoZengLogDetail, String> {
	/**
	 * 保存员工转移服务码附表实体
	 * @Title: IEmployeeInfoZengLogDetailService
	 * @Description:
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist( String versionFlag) throws ServiceException;
	
	/**
	 * 修改员工转移服务码附表实体
	 * @Title: IEmployeeInfoZengLogDetailService
	 * @Description:
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id,  String versionFlag) throws ServiceException;
	
	/**
	 * 移除员工转移服务码附表实体
	 * @Title: IEmployeeInfoZengLogDetailService
	 * @Description: 
	 * @param @param id 员工转移服务码附表ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 查询某个客户收到的赠送的服务码
	 * @param mobile
	 * @return
	 */
	List<EmployeeInfoZengLogDetail> query(String mobile);
	
	/**
	 * 是否可以激活
	 * @param zengLogDetail
	 * @return
	 */
	boolean canActivate(EmployeeInfoZengLogDetail zengLogDetail);
	
	/**
	 * 是否可以激活
	 * @param zengLogDetail
	 * @return
	 */
	boolean canActivate(BjdjServiceCode serviceCode);
	
	/**
	 * 是否可以评论
	 * @param zengLogDetail
	 * @return
	 */
	boolean canComment(EmployeeInfoZengLogDetail zengLogDetail);
}