package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.SecurityKey;


/**
 * 安全秘钥管理表业务接口
 * @ClassName: ISecurityKeyService   
 * @Description: 提供安全秘钥管理表操作的增删改查   
 * @author HiSay  
 * @date  2016-01-21 14:43:44
 *
 */
public interface ISecurityKeyService extends IBaseService<SecurityKey, String> {

	/**
	 * 保存安全秘钥管理表实体
	 * @Title: ISecurityKeyService
	 * @Description:
	 * @param @param publicKey  公钥
	 * @param @param secretKey  密钥
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String publicKey, String remark, String versionFlag) throws ServiceException;
	
	/**
	 * 修改安全秘钥管理表实体
	 * @Title: ISecurityKeyService
	 * @Description:
	 * @param @param publicKey  公钥
	 * @param @param secretKey  密钥
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String publicKey, String remark, String versionFlag) throws ServiceException;
	
	/**
	 * 移除安全秘钥管理表实体
	 * @Title: ISecurityKeyService
	 * @Description: 
	 * @param @param id 安全秘钥管理表ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 通过公钥获取密钥对象
	 * @param publicKey
	 * @return
	 */
	SecurityKey getByPublicKey(String publicKey);
}