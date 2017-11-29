package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Position;


/**
 * 员工岗位职责业务接口
 * @ClassName: IPositionService   
 * @Description: 提供员工岗位职责操作的增删改查   
 * @author HiSay  
 * @date  2016-01-09 09:51:13
 *
 */
public interface IPositionService extends IBaseService<Position, String> {
	/**
	 * 保存员工岗位职责实体
	 * @Title: IPositionService
	 * @Description:
	 * @param @param name  岗位民称
	 * @param @param duty  岗位职责
	 * @param @param remark  岗位描述
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String duty,String remark, String versionFlag) throws ServiceException;
	
	/**
	 * 修改员工岗位职责实体
	 * @Title: IPositionService
	 * @Description:
	 * @param @param name  岗位民称
	 * @param @param duty  岗位职责
	 * @param @param remark  岗位描述
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String duty,String remark, String versionFlag) throws ServiceException;
	
	/**
	 * 移除员工岗位职责实体
	 * @Title: IPositionService
	 * @Description: 
	 * @param @param id 员工岗位职责ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 获取所有的岗位列表
	 * @method queryAll
	 * @param size
	 * @return
	 * @throws ServiceException
	 * @return List<Position>
	 * @date 2016-1-9 上午10:24:41
	 */
	List<Position> queryAll(Integer size) throws ServiceException;
	
	/**
	 * 根据岗位名称查找岗位信息
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	Position queryByName(String name) throws ServiceException;
}