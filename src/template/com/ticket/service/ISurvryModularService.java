package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.ApplayClass;
import com.ticket.pojo.SurvryModular;


/**
 * 调查模块业务接口
 * @ClassName: ISurvryModularService   
 * @Description: 提供调查模块操作的增删改查   
 * @author HiSay  
 * @date  2015-11-11 17:08:07
 *
 */
public interface ISurvryModularService extends IBaseService<SurvryModular, String> {
	/**
	 * 保存调查模块实体
	 * @Title: ISurvryModularService
	 * @Description:
	 * @param @param name  调查模块名称
	 * @param @param descript  调查模块描述
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String descript, String versionFlag) throws ServiceException;
	
	/**
	 * 修改调查模块实体
	 * @Title: ISurvryModularService
	 * @Description:
	 * @param @param name  调查模块名称
	 * @param @param descript  调查模块描述
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String descript, String versionFlag) throws ServiceException;
	
	/**
	 * 移除调查模块实体
	 * @Title: ISurvryModularService
	 * @Description: 
	 * @param @param id 调查模块ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 查询调查模块列表
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<SurvryModular> queryList(String versionFlag) throws ServiceException;
}