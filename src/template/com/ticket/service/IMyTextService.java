package com.ticket.service;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.MyText;
import com.ticket.pojo.Pagination;


/**
 * 我的记事本业务接口
 * @ClassName: IMyTextService   
 * @Description: 提供我的记事本操作的增删改查   
 * @author HiSay  
 * @date  2016-02-15 11:26:54
 *
 */
public interface IMyTextService extends IBaseService<MyText, String> {
	/**
	 * 保存我的记事本实体
	 * @Title: IMyTextService
	 * @Description:
	 * @param @param employeeInfo_id  关联客户信id
	 * @param @param title  标题
	 * @param @param content  内容
	 * @param @param isRead  是否阅读
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	MyText persist(EmployeeInfo employeeInfo,String title,String content) throws ServiceException;
	
	/**
	 * 修改我的记事本实体
	 * @Title: IMyTextService
	 * @Description:
	 * @param @param employeeInfo_id  关联客户信id
	 * @param @param title  标题
	 * @param @param content  内容
	 * @param @param isRead  是否阅读
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	MyText merge(String id,EmployeeInfo employeeInfo,String title,String content) throws ServiceException;
	
	/**
	 * 移除我的记事本实体
	 * @Title: IMyTextService
	 * @Description: 
	 * @param @param id 我的记事本ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 获取所有的我的记事本
	 * @method queryAll
	 * @param employeeInfo
	 * @param isRead
	 * @param order
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 * @return Pagination
	 * @date 2016-2-15 上午11:38:34
	 */
	Pagination queryAll(EmployeeInfo employeeInfo,Integer isRead,String order,Integer pageSize) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 改变阅读状态
	 * @method changeRead
	 * @param id
	 * @param read
	 * @return
	 * @throws ServiceException
	 * @return Boolean
	 * @date 2016-2-15 上午11:52:33
	 */
	Boolean changeRead(String id,Integer read) throws ServiceException;
}