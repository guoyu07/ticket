package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.RegulationType;


/**
 * 规章制度类别业务接口
 * @ClassName: IRegulationTypeService   
 * @Description: 提供规章制度类别操作的增删改查   
 * @author HiSay  
 * @date  2015-11-20 13:20:33
 *
 */
public interface IRegulationTypeService extends IBaseService<RegulationType, String> {
	/**
	 * 保存规章制度类别实体
	 * @Title: IRegulationTypeService
	 * @Description:
	 * @param @param name  制度名称
	 * @param @param descript  制度描述
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String descript, String versionFlag, Integer orderValue) throws ServiceException;
	
	/**
	 * 修改规章制度类别实体
	 * @Title: IRegulationTypeService
	 * @Description:
	 * @param @param name  制度名称
	 * @param @param descript  制度描述
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String descript, String versionFlag, Integer orderValue) throws ServiceException;
	
	/**
	 * 移除规章制度类别实体
	 * @Title: IRegulationTypeService
	 * @Description: 
	 * @param @param id 规章制度类别ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 批量彻底删除制度类别实体
	 * @param idsValue
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException;

	/**
	 * 查询制度类别列表
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<RegulationType> queryList(String versionFlag) throws ServiceException;
	/**
	 * 后台查看
	 * @param versionFlag
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryByAdmin(String versionFlag, Integer pageSize) throws ServiceException;
}