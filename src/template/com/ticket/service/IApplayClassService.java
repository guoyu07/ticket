package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.ApplayClass;


/**
 * 申请类别业务接口
 * @ClassName: IApplayClassService   
 * @Description: 提供申请类别操作的增删改查   
 * @author HiSay  
 * @date  2015-11-04 14:18:35
 *
 */
public interface IApplayClassService extends IBaseService<ApplayClass, String> {
	/**
	 * 保存申请类别实体
	 * @Title: IApplayClassService
	 * @Description:
	 * @param @param name  申请类别名称
	 * @param @param descript  申请类别描述
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String descript, String versionFlag) throws ServiceException;
	
	/**
	 * 修改申请类别实体
	 * @Title: IApplayClassService
	 * @Description:
	 * @param @param name  申请类别名称
	 * @param @param descript  申请类别描述
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String descript, String versionFlag) throws ServiceException;
	
	/**
	 * 移除申请类别实体
	 * @Title: IApplayClassService
	 * @Description: 
	 * @param @param id 申请类别ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 查询申请类别列表
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<ApplayClass> queryList(String versionFlag) throws ServiceException;
	
}