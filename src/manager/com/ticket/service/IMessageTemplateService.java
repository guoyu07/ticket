package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.MessageTemplate;


/**
 * 消息模板业务接口
 * @ClassName: IMessageTemplateService   
 * @Description: 提供消息模板操作的增删改查   
 * @author HiSay  
 * @date  2016-08-09 10:50:43
 *
 */
public interface IMessageTemplateService extends IBaseService<MessageTemplate, String> {
	/**
	 * 保存消息模板实体
	 * @Title: IMessageTemplateService
	 * @Description:
	 * @param @param content  消息内容
	 * @param @param remark  描述
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String title, String url, String content,String remark, String versionFlag) throws ServiceException;
	
	/**
	 * 修改消息模板实体
	 * @Title: IMessageTemplateService
	 * @Description:
	 * @param @param content  消息内容
	 * @param @param remark  描述
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String title, String url, String content,String remark, String versionFlag) throws ServiceException;
	
	/**
	 * 移除消息模板实体
	 * @Title: IMessageTemplateService
	 * @Description: 
	 * @param @param id 消息模板ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 根据设备id，查询需要推送的数据
	 * @param sid
	 * @return
	 */
	List<MessageTemplate> query(String sid);
}