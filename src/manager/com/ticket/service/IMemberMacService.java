package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.MemberMac;
import com.ticket.pojo.WifiPush;

/**
 * 用户的mac地址业务接口
 * @ClassName: IMemberMacService   
 * @Description: 提供用户的mac地址操作的增删改查   
 * @author HiSay  
 * @date  2016-08-09 10:51:20
 */
public interface IMemberMacService extends IBaseService<MemberMac, String> {
	
	/**
	 * 保存推送过来的动态数据
	 * @param mid 商家ID
	 * @param rid 设备ID
	 * @param mac mac地址
	 * @param ts 时间戳
	 * @param sig 信号
	 * @param tel 电话号码
	 * @return
	 */
	WifiPush persistPush(String mid, String rid, String mac, String ts, String sig, String tel);
	
	/**
	 * 保存用户的mac地址实体
	 * @Title: IMemberMacService
	 * @Description:
	 * @param @param phone  电话号码
	 * @param @param mac  mac地址
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String phone,String mac, String versionFlag) throws ServiceException;
	
	/**
	 * 修改用户的mac地址实体
	 * @Title: IMemberMacService
	 * @Description:
	 * @param @param phone 电话号码
	 * @param @param mac  mac地址
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String phone,String mac, String versionFlag) throws ServiceException;
	
	/**
	 * 移除用户的mac地址实体
	 * @Title: IMemberMacService
	 * @Description: 
	 * @param @param id 用户的mac地址ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 根据电话号码和mac地址查看数据是否已存在对应对象
	 * @param phone
	 * @param mac
	 * @return
	 */
	MemberMac query(String phone, String mac);
	
	/**
	 * 根据mac地址获取一系列对象
	 * @param mac
	 * @return
	 */
	List<MemberMac> queryByMac(String mac);
	
	/**
	 * 根据phone获取一系列对象
	 * @param phone
	 * @return
	 */
	List<MemberMac> queryByPhone(String phone);
	
	/**
	 * 接收wifi探针发送的动态数据
	 * @param json
	 */
	void receiveMobileData(String json) throws ServiceException;
}