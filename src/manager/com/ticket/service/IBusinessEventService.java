package com.ticket.service;

import java.util.Date;
import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BusinessEvent;


/**
 * 商家活动业务接口
 * @ClassName: IBusinessEventService   
 * @Description: 提供商家活动操作的增删改查   
 * @author HiSay  
 * @date  2016-12-14 10:20:43
 *
 */
public interface IBusinessEventService extends IBaseService<BusinessEvent, String> {
	/**
	 * 保存商家活动实体
	 * @Title: IBusinessEventService
	 * @Description:
	 * @param @param name  商家活动名称
	 * @param @param content  活动内容
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String content,String pic,Date startTime, Date endTime, String versionFlag) throws ServiceException;
	
	/**
	 * 修改商家活动实体
	 * @Title: IBusinessEventService
	 * @Description:
	 * @param @param name  商家活动名称
	 * @param @param content  活动内容
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String content, String pic,Date startTime, Date endTime, String versionFlag) throws ServiceException;
	
	/**
	 * 移除商家活动实体
	 * @Title: IBusinessEventService
	 * @Description: 
	 * @param @param id 商家活动ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 冻结
	 * @param id
	 * @return
	 */
	boolean freeze(String id);
	
	/**
	 * 解冻
	 * @param id
	 * @return
	 */
	boolean actived(String id);
	
	/**
	 * 可用的活动
	 * @return
	 */
	List<BusinessEvent> canBindList();
}