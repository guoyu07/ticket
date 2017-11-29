package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.ChannelType;


/**
 * 渠道分类业务接口
 * @ClassName: IChannelTypeService   
 * @Description: 提供渠道分类操作的增删改查   
 * @author HiSay  
 * @date  2015-11-03 17:39:54
 *
 */
public interface IChannelTypeService extends IBaseService<ChannelType, String> {
	/**
	 * 保存渠道分类实体
	 * @Title: IChannelTypeService
	 * @Description:
	 * @param @param name  分类名称
	 * @param @param descript  分类描述
	 * @param @param empCustomerFlag  员工渠道客户标识
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String descript, String systemDictionary_id,Integer empCustomerFlag) throws ServiceException;
	
	/**
	 * 修改渠道分类实体
	 * @Title: IChannelTypeService
	 * @Description:
	 * @param @param name  分类名称
	 * @param @param descript  分类描述
	 * @param @param empCustomerFlag  员工渠道客户标识
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String descript, String systemDictionary_id,Integer empCustomerFlag) throws ServiceException;
	
	/**
	 * 移除渠道分类实体
	 * @Title: IChannelTypeService
	 * @Description: 
	 * @param @param id 渠道分类ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 查询渠道分类列表
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<ChannelType> queryList(String versionFlag) throws ServiceException;
	
	/**
	 * 查询员工的渠道客户分类标识
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<ChannelType> queryEmpCustomerTypeList(String versionFlag) throws ServiceException;
	
	/**
	 * 通过渠道客户获取渠道类型
	 * @param channelCustomer_id
	 * @return
	 */
	ChannelType getByChannelCustomer(String channelCustomer_id);
	/**
	 * 通过渠道类型名称查询渠道类型信息
	 * @param name
	 * @return
	 */
	ChannelType getByName(String name);
}