package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Electromobile;


/**
 * 电瓶车信息业务接口
 * @ClassName: IElectrombileService   
 * @Description: 提供电瓶车信息操作的增删改查   
 * @author HiSay  
 * @date  2015-10-15 10:55:04
 *
 */
public interface IElectrombileService extends IBaseService<Electromobile, String> {
	/**
	 * 保存电瓶车信息实体
	 * @Title: IElectrombileService
	 * @Description:
	 * @param @param electrombileId  电瓶车编号
	 * @param @param ifUsing  是否正在使用
	 * @param @param longitude  经度
	 * @param @param latitude  纬度
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String electrombileId,Integer ifUsing,Double longitude,Double latitude, String versionFlag) throws ServiceException;
	
	/**
	 * 修改电瓶车信息实体
	 * @Title: IElectrombileService
	 * @Description:
	 * @param @param electrombileId  电瓶车编号
	 * @param @param ifUsing  是否正在使用
	 * @param @param longitude  经度
	 * @param @param latitude  纬度
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String electrombileId,Integer ifUsing,Double longitude,Double latitude, String versionFlag) throws ServiceException;
	
	/**
	 * 移除电瓶车信息实体
	 * @Title: IElectrombileService
	 * @Description: 
	 * @param @param id 电瓶车信息ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 批量彻底删除电瓶车信息
	 * @param idsValue
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRealDelete(String idsValue,String versionFlag)throws ServiceException;
}