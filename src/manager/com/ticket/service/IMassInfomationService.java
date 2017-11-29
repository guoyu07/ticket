package com.ticket.service;

import java.util.Date;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.MassInfomation;


/**
 * 群发信息业务接口
 * @ClassName: IMassInfomationService   
 * @Description: 提供群发信息操作的增删改查   
 * @author HiSay  
 * @date  2016-02-03 20:46:58
 *
 */
public interface IMassInfomationService extends IBaseService<MassInfomation, String> {
	/**
	 * 保存群发信息实体
	 * @Title: IMassInfomationService
	 * @Description:
	 * @param @param title  信息标题
	 * @param @param content  短信内容
	 * @param @param url  短信链接
	 * @param @param massObjCharacter  群发对象特性
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String title,String content,String url,String massObjCharacter, Date reminderTime, String path
			, boolean sendSms, String flightNumber, Date flightDate,int type) throws ServiceException;
	
	/**
	 * 修改群发信息实体
	 * @Title: IMassInfomationService
	 * @Description:
	 * @param @param title  信息标题
	 * @param @param content  短信内容
	 * @param @param url  短信链接
	 * @param @param massObjCharacter  群发对象特性
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String title,String content,String url,String massObjCharacter, Date reminderTime
			, String path, boolean sendSms, String flightNumber, Date flightDate) throws ServiceException;
	
	/**
	 * 移除群发信息实体
	 * @Title: IMassInfomationService
	 * @Description: 
	 * @param @param id 群发信息ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
}