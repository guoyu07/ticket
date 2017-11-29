package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjServiceCodeDelayRule;


/**
 * 服务码延期规则业务接口
 * @ClassName: IBjdjServiceCodeDelayRuleService   
 * @Description: 提供服务码延期规则操作的增删改查   
 * @author HiSay  
 * @date  2015-12-11 14:46:34
 *
 */
public interface IBjdjServiceCodeDelayRuleService extends IBaseService<BjdjServiceCodeDelayRule, String> {
	/**
	 * 保存服务码延期规则实体
	 * @Title: IBjdjServiceCodeDelayRuleService
	 * @Description:
	 * @param @param deadline  延长时间
	 * @param @param record  扣除积分
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(Integer deadline,Integer record, Integer orderValue) throws ServiceException;
	
	/**
	 * 修改服务码延期规则实体
	 * @Title: IBjdjServiceCodeDelayRuleService
	 * @Description:
	 * @param @param deadline  延长时间
	 * @param @param record  扣除积分
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, Integer deadline,Integer record, Integer orderValue) throws ServiceException;
	
	/**
	 * 移除服务码延期规则实体
	 * @Title: IBjdjServiceCodeDelayRuleService
	 * @Description: 
	 * @param @param id 服务码延期规则ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * @Description：查询所有
	 * @return
	 */
	List<BjdjServiceCodeDelayRule> queryAll();
}