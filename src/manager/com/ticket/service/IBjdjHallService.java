package com.ticket.service;

import java.util.Date;
import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjHall;
import com.ticket.pojo.BjdjHallGate;
import com.ticket.pojo.InfoPosition;
import com.ticket.pojo.SystemDictionary;


/**
 * 服务厅表业务接口
 * @ClassName: IBjdjHallService   
 * @Description: 提供服务厅表操作的增删改查   
 * @author HiSay  
 * @date  2015-10-23 15:24:57
 *
 */
public interface IBjdjHallService extends IBaseService<BjdjHall, String> {
	/**
	 * 保存服务厅表实体
	 * @Title: IBjdjHallService
	 * @Description:
	 * @param @param number  厅号
	 * @param @param capacity  容量（最大人数）
	 * @param @param longitude  经度
	 * @param @param latitude  纬度
	 * @param @param description  描述
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String number,Integer capacity,String longitude,String latitude,String description, String versionFlag) throws ServiceException;
	
	/**
	 * 修改服务厅表实体
	 * @Title: IBjdjHallService
	 * @Description:
	 * @param @param number  厅号
	 * @param @param capacity  容量（最大人数）
	 * @param @param longitude  经度
	 * @param @param latitude  纬度
	 * @param @param description  描述
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String number,Integer capacity,String longitude,String latitude,String description, String versionFlag) throws ServiceException;
	
	/**
	 * 移除服务厅表实体
	 * @Title: IBjdjHallService
	 * @Description: 
	 * @param @param id 服务厅表ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * @Description：通过服务厅的厅号得到服务厅的对象
	 * @param hallName
	 * @return
	 */
	BjdjHall getByName(String hallNumber);
	
	/**
	 * 得到一个渠道类型，可以进入的大厅（排队未满）
	 * @param channel
	 * @return
	 */
	BjdjHall accessibleHall(Date flightDate, SystemDictionary channel);
	
	/**
	 * 计算某个渠道类型大厅的总容量
	 * @param channel
	 * @return
	 */
	int totalCapcity(SystemDictionary channel);
	
	/**
	 * 新增一个大厅、登机口的关联
	 * @param hall
	 * @param gate
	 * @param meter
	 * @param description
	 */
	void addGate(BjdjHall hall, InfoPosition gate, int meter, String description) throws ServiceException;
	
	/**
	 * 编辑一个大厅、登机口的关联
	 * @param hall
	 * @param gate
	 * @param meter
	 * @param description
	 */
	void editGate(String id, BjdjHall hall, InfoPosition gate, int meter, String description) throws ServiceException;
	
	/**
	 * 查询一个大厅所有的登机口
	 * @param hall
	 * @return
	 */
	List<BjdjHallGate> queryGates(BjdjHall hall);
	
	/**
	 * 离指定大厅最近的一个登机口
	 * @param hall
	 * @return
	 */
	BjdjHallGate queryNearestGate(BjdjHall hall);
	
	/**
	 * 通过大厅和登机口，查实是否有
	 * @param hall
	 * @param gate
	 * @return
	 */
	BjdjHallGate queryByHallAndGate(BjdjHall hall, InfoPosition gate);
	
	
}