package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Wifi;


/**
 * ifi设备业务接口
 * @ClassName: IWifiService   
 * @Description: 提供ifi设备操作的增删改查   
 * @author HiSay  
 * @date  2016-08-09 10:49:51
 *
 */
public interface IWifiService extends IBaseService<Wifi, String> {
	/**
	 * 保存ifi设备实体
	 * @Title: IWifiService
	 * @Description:
	 * @param @param sid  设备id
	 * @param @param remark  描述
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String sid, String area_id,String remark, String versionFlag) throws ServiceException;
	
	/**
	 * 修改ifi设备实体
	 * @Title: IWifiService
	 * @Description:
	 * @param @param sid  设备id
	 * @param @param remark  描述
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String sid, String area_id,String remark, String versionFlag) throws ServiceException;
	
	/**
	 * 移除ifi设备实体
	 * @Title: IWifiService
	 * @Description: 
	 * @param @param id ifi设备ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
}