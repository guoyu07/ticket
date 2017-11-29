package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.MemberLogo;


/**
 * 推荐会员头像业务接口
 * @ClassName: IMemberLogoService   
 * @Description: 提供推荐会员头像操作的增删改查   
 * @author HiSay  
 * @date  2016-03-14 10:49:59
 *
 */
public interface IMemberLogoService extends IBaseService<MemberLogo, String> {
	/**
	 * 保存推荐会员头像实体
	 * @Title: IMemberLogoService
	 * @Description:
	 * @param @param logoUrl  头像路径
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String logoUrl, String versionFlag) throws ServiceException;
	
	/**
	 * 修改推荐会员头像实体
	 * @Title: IMemberLogoService
	 * @Description:
	 * @param @param logoUrl  头像路径
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String logoUrl, String versionFlag) throws ServiceException;
	
	/**
	 * 移除推荐会员头像实体
	 * @Title: IMemberLogoService
	 * @Description: 
	 * @param @param id 推荐会员头像ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	/**
	 * 批量删除推荐会员头像实体
	 * @param idsValue
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException;
}