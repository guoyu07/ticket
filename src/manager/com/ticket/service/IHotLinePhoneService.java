package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.HotLinePhone;
import com.ticket.pojo.Pagination;


/**
 * 热线电话业务接口
 * @ClassName: IHotLinePhoneService   
 * @Description: 提供热线电话操作的增删改查   
 * @author HiSay  
 * @date  2015-11-17 17:34:17
 *
 */
public interface IHotLinePhoneService extends IBaseService<HotLinePhone, String> {
	/**
	 * 保存热线电话实体
	 * @Title: IHotLinePhoneService
	 * @Description:
	 * @param @param name  热线名称
	 * @param @param phone  热线电话
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String phone,String oneKeyCall,Integer orderValue, String versionFlag) throws ServiceException;
	
	/**
	 * 修改热线电话实体
	 * @Title: IHotLinePhoneService
	 * @Description:
	 * @param @param name  热线名称
	 * @param @param phone  热线电话
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String phone,String oneKeyCall,Integer orderValue, String versionFlag) throws ServiceException;
	
	/**
	 * 移除热线电话实体
	 * @Title: IHotLinePhoneService
	 * @Description: 
	 * @param @param id 热线电话ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 批量彻底删除机场热线实体
	 * @param idsValue
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException;

	/**
	 * 机场热线电话列表
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<HotLinePhone> queryList(String versionFlag) throws ServiceException;

	/**
	 * 查询机场列表 以排序值大小排序
	 * @param versionFlag
	 * @param pageSize 页面大小
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryEntityByAdminAndOrder(String versionFlag,
			int pageSize) throws ServiceException;
}