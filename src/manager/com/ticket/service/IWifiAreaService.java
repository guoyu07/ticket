package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.WifiArea;


/**
 * ifi区域业务接口
 * @ClassName: IWifiAreaService   
 * @Description: 提供ifi区域操作的增删改查   
 * @author HiSay  
 * @date  2016-09-22 10:17:00
 *
 */
public interface IWifiAreaService extends IBaseService<WifiArea, String> {
	/**
	 * 保存ifi区域实体
	 * @Title: IWifiAreaService
	 * @Description:
	 * @param @param name  区域名称
	 * @param @param interval  时间间隔（分钟）
	 * @param @param remark  备注
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,int interval,String remark, String versionFlag) throws ServiceException;
	
	/**
	 * 修改ifi区域实体
	 * @Title: IWifiAreaService
	 * @Description:
	 * @param @param name  区域名称
	 * @param @param interval  时间间隔（分钟）
	 * @param @param remark  备注
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,int interval,String remark, String versionFlag) throws ServiceException;
	
	/**
	 * 移除ifi区域实体
	 * @Title: IWifiAreaService
	 * @Description: 
	 * @param @param id ifi区域ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
}