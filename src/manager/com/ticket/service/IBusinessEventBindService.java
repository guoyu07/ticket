package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BusinessEvent;
import com.ticket.pojo.BusinessEventBind;
import com.ticket.pojo.BusinessInfo;


/**
 * 商家活动绑定业务接口
 * @ClassName: IBusinessEventBindService   
 * @Description: 提供商家活动绑定操作的增删改查   
 * @author HiSay  
 * @date  2016-12-15 10:18:13
 *
 */
public interface IBusinessEventBindService extends IBaseService<BusinessEventBind, String> {
	/**
	 * 保存商家活动绑定实体
	 * @Title: IBusinessEventBindService
	 * @Description:
	 * @param @param businessInfo  商家
	 * @param @param businessEvent  活动
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(BusinessInfo businessInfo,BusinessEvent businessEvent, String versionFlag) throws ServiceException;
	
	/**
	 * 修改商家活动绑定实体
	 * @Title: IBusinessEventBindService
	 * @Description:
	 * @param @param businessInfo  商家
	 * @param @param businessEvent  活动
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, BusinessInfo businessInfo,BusinessEvent businessEvent, String versionFlag) throws ServiceException;
	
	/**
	 * 移除商家活动绑定实体
	 * @Title: IBusinessEventBindService
	 * @Description: 
	 * @param @param id 商家活动绑定ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 根据活动iD查询绑定
	 * @param event_id
	 * @return
	 */
	List<BusinessEventBind> queryByEvent(String event_id);
	
	/**
	 * 根据商家和商家活动查询是否绑定
	 * @param business_id
	 * @param event_id
	 * @return
	 */
	BusinessEventBind queryByBusinessIdAndEventId(String business_id,String event_id);
}