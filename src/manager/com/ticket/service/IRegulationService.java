package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.Regulation;


/**
 * 规章制度信息业务接口
 * @ClassName: IRegulationService   
 * @Description: 提供规章制度信息操作的增删改查   
 * @author HiSay  
 * @date  2015-11-20 13:23:55
 *
 */
public interface IRegulationService extends IBaseService<Regulation, String> {
	/**
	 * 保存规章制度信息实体
	 * @Title: IRegulationService
	 * @Description:
	 * @param @param type_id  制度类别
	 * @param @param title  标题
	 * @param @param content  制度内容
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String type_id,String title,String content, String versionFlag, Integer orderValue) throws ServiceException;
	
	/**
	 * 修改规章制度信息实体
	 * @Title: IRegulationService
	 * @Description:
	 * @param @param type_id  制度类别
	 * @param @param title  标题
	 * @param @param content  制度内容
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String type_id,String title,String content, String versionFlag, Integer orderValue) throws ServiceException;
	
	/**
	 * 移除规章制度信息实体
	 * @Title: IRegulationService
	 * @Description: 
	 * @param @param id 规章制度信息ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 批量彻底删除制度实体
	 * @param idsValue
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException;

	/**
	 *  根据机场制度类别查询机场制度列表
	 * @param type_id 制度类别id
	 * @param versionFlag  版本标识
	 * @return
	 * @throws ServiceException
	 */
	List<Regulation> queryListByType(String type_id, String versionFlag) throws ServiceException;
	
	/**
	 * 后台查看
	 * @param versionFlag
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryByAdmin(String versionFlag, Integer pageSize) throws ServiceException;
	/**
	 * 根据机场制度标题查找机场规章制度
	 * @param title
	 * @return
	 * @throws ServiceException
	 */
	Regulation findByTitle(String title) throws ServiceException;
}