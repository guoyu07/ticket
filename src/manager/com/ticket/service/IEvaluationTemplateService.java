package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.EvaluationTemplate;


/**
 * 评论模板业务接口
 * @ClassName: IEvaluationTemplateService   
 * @Description: 提供评论模板操作的增删改查   
 * @author HiSay  
 * @date  2016-02-03 18:17:36
 *
 */
public interface IEvaluationTemplateService extends IBaseService<EvaluationTemplate, String> {
	/**
	 * 保存评论模板实体
	 * @Title: IEvaluationTemplateService
	 * @Description:
	 * @param @param title  标题
	 * @param @param content  内容
	 * @param @param user  此条模板所属用户
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String title,String content) throws ServiceException;
	
	/**
	 * 修改评论模板实体
	 * @Title: IEvaluationTemplateService
	 * @Description:
	 * @param @param title  标题
	 * @param @param content  内容
	 * @param @param user  此条模板所属用户
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String title,String content) throws ServiceException;
	
	/**
	 * 移除评论模板实体
	 * @Title: IEvaluationTemplateService
	 * @Description: 
	 * @param @param id 评论模板ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
}