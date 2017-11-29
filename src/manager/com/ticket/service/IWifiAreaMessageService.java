package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.MessageTemplate;
import com.ticket.pojo.WifiArea;
import com.ticket.pojo.WifiAreaMessage;


/**
 * ifi消息关联业务接口
 * @ClassName: IWifiMessageService   
 * @Description: 提供ifi消息关联操作的增删改查   
 * @author HiSay  
 * @date  2016-08-09 10:51:02
 *
 */
public interface IWifiAreaMessageService extends IBaseService<WifiAreaMessage, String> {
	/**
	 * 保存ifi消息关联实体
	 * @Title: IWifiMessageService
	 * @Description:
	 * @param @param wifi  wifi
	 * @param @param message  消息
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(WifiArea wifiArea,MessageTemplate message, String versionFlag) throws ServiceException;
	
	/**
	 * 修改ifi消息关联实体
	 * @Title: IWifiMessageService
	 * @Description:
	 * @param @param wifi  wifi
	 * @param @param message  消息
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, WifiArea wifiArea,MessageTemplate message, String versionFlag) throws ServiceException;
	
	/**
	 * 移除ifi消息关联实体
	 * @Title: IWifiMessageService
	 * @Description: 
	 * @param @param id ifi消息关联ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 根据wifi设备号获取与此设备关联的所有的消息关系
	 * @param sid
	 * @return
	 */
	public List<WifiAreaMessage> query(String sid);
}