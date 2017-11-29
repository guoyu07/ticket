package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Advert;
import com.ticket.pojo.AdvertFlight;


/**
 * 航班详情对应广告业务接口
 * @ClassName: IAdvertFlightService   
 * @Description: 提供航班详情对应广告操作的增删改查   
 * @author HiSay  
 * @date  2016-09-28 11:33:27
 *
 */
public interface IAdvertFlightService extends IBaseService<AdvertFlight, String> {
	/**
	 * 保存航班详情对应广告实体
	 * @Title: IAdvertFlightService
	 * @Description:
	 * @param @param advert  广告
	 * @param @param arriveCity  到达城市
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(Advert advert,String arriveCity, String versionFlag) throws ServiceException;
	
	/**
	 * 修改航班详情对应广告实体
	 * @Title: IAdvertFlightService
	 * @Description:
	 * @param @param advert  广告
	 * @param @param arriveCity  到达城市
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, Advert advert,String arriveCity, String versionFlag) throws ServiceException;
	
	/**
	 * 移除航班详情对应广告实体
	 * @Title: IAdvertFlightService
	 * @Description: 
	 * @param @param id 航班详情对应广告ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 查询是否已经保存了对应的数据
	 * @param advert
	 * @param city
	 * @return
	 * @throws ServiceException
	 */
	AdvertFlight queryByAdvertAndCity(Advert advert,String city) throws ServiceException;
	
	/**
	 * 根据广告查询
	 * @param advert
	 * @return
	 * @throws ServiceException
	 */
	List<AdvertFlight> queryByAdvert(Advert advert) throws ServiceException;
	
	/**
	 * 根据到达城市查询广告
	 * @param city
	 * @return
	 * @throws ServiceException
	 */
	List<AdvertFlight> queryByCity(String city) throws ServiceException;
}