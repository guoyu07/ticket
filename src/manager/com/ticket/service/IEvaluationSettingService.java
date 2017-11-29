package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.EvaluationSetting;


/**
 * 评价设置业务接口
 * @ClassName: IEstimateSetManageService   
 * @Description: 提供评价设置操作的增删改查   
 * @author HiSay  
 * @date  2016-01-26 10:56:19
 *
 */
public interface IEvaluationSettingService extends IBaseService<EvaluationSetting, String> {
	/**
	 * 保存评价设置实体
	 * @Title: IEstimateSetManageService
	 * @Description:
	 * @param @param classes  评价类别
	 * @param @param project  评价项目
	 * @param @param target  评价指标
	 * @param @param enabled  是否启用(具体到指标)
	 * @param @param parentId  父ID
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(Integer enabled,String name, Integer order,String parent_id) throws ServiceException;
	boolean persist(Integer enabled,String name, Integer order,EvaluationSetting parent) throws ServiceException;
	/**
	 * 修改评价设置实体
	 * @Title: IEstimateSetManageService
	 * @Description:
	 * @param @param classes  评价类别
	 * @param @param project  评价项目
	 * @param @param target  评价指标
	 * @param @param enabled  是否启用(具体到指标)
	 * @param @param parentId  父ID
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id,Integer enabled,String name, Integer order,String parent_id) throws ServiceException;
	boolean merge(String id,Integer enabled,String name, Integer order,EvaluationSetting parent) throws ServiceException;
	/**
	 * 移除评价设置实体
	 * @Title: IEstimateSetManageService
	 * @Description: 
	 * @param @param id 评价设置ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 根据父id查找到评价设置
	 * @param parentId
	 * @return
	 * @throws ServiceException
	 */
	List<EvaluationSetting> findByParentId(String parentId);
	/**
	 * 根据设置名称查找评价设置
	 * @param name
	 * @return
	 */
	EvaluationSetting getByName(String name);
	
	/**
	 * 在同一个父id下是否已经存在同样的子节点
	 * @param parent
	 * @param name
	 * @return
	 */
	public boolean existSameUnderParent(EvaluationSetting parent,String name);
}