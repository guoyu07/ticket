package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.ChannelPosition;


/**
 * 渠道联系人岗位业务接口
 * @ClassName: IChannelPositionService   
 * @Description: 提供渠道联系人岗位操作的增删改查   
 * @author HiSay  
 * @date  2016-01-11 11:35:13
 *
 */
public interface IChannelPositionService extends IBaseService<ChannelPosition, String> {
	/**
	 * 保存渠道联系人岗位实体
	 * @Title: IChannelPositionService
	 * @Description:
	 * @param @param name  岗位名称
	 * @param @param duty  岗位职责
	 * @param @param remark  岗位描述
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String duty,String remark, String versionFlag) throws ServiceException;
	
	/**
	 * 修改渠道联系人岗位实体
	 * @Title: IChannelPositionService
	 * @Description:
	 * @param @param name  岗位名称
	 * @param @param duty  岗位职责
	 * @param @param remark  岗位描述
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String duty,String remark, String versionFlag) throws ServiceException;
	
	/**
	 * 移除渠道联系人岗位实体
	 * @Title: IChannelPositionService
	 * @Description: 
	 * @param @param id 渠道联系人岗位ID
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
	 * @return List<ChannelPosition>
	 * @date 2016-1-9 上午10:24:41
	 */
	List<ChannelPosition> queryAll(Integer size) throws ServiceException;
}