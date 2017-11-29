package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.FriendlyLink;
import com.ticket.pojo.Pagination;


/**
 * 友情链接业务接口
 * @ClassName: IFriendlyLinkService   
 * @Description: 提供友情链接操作的增删改查   
 * @author HiSay  
 * @date  2015-11-20 10:43:11
 *
 */
public interface IFriendlyLinkService extends IBaseService<FriendlyLink, String> {
	/**
	 * 保存友情链接实体
	 * @Title: IFriendlyLinkService
	 * @Description:
	 * @param @param businessInfo_id  商家id
	 * @param @param name  链接名称
	 * @param @param url  链接地址
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String businessInfo_id,String name,String url, String versionFlag) throws ServiceException;
	
	/**
	 * 修改友情链接实体
	 * @Title: IFriendlyLinkService
	 * @Description:
	 * @param @param businessInfo_id  商家id
	 * @param @param name  链接名称
	 * @param @param url  链接地址
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String businessInfo_id,String name,String url, String versionFlag) throws ServiceException;
	
	/**
	 * 移除友情链接实体
	 * @Title: IFriendlyLinkService
	 * @Description: 
	 * @param @param id 友情链接ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 根据商家id查询友情链接
	 * @param businessInfoId
	 * @param adminCommonPageSize
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryEntityByBusinessInfoId(String businessInfoId,
			int pageSize, String versionFlag) throws ServiceException;

	/**
	 * 根据商家id查询友情链接列表
	 * @param buinessInfo_id  商家id
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<FriendlyLink> queryList(String buinessInfo_id, String versionFlag) throws ServiceException;
}