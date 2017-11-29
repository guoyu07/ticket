package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.SpecialPersonWithQuickMenu;


/**
 * 服务菜单业务接口
 * @ClassName: ISpecialPersonWithQuickMenuService   
 * @Description: 提供服务菜单操作的增删改查   
 * @author HiSay  
 * @date  2015-12-05 09:41:10
 *
 */
public interface ISpecialPersonWithQuickMenuService extends IBaseService<SpecialPersonWithQuickMenu, String> {
	/**
	 * 保存服务菜单实体
	 * @Title: ISpecialPersonWithQuickMenuService
	 * @Description:
	 * @param @param personType  服务人员类型
	 * @param @param quickMenu_id  快捷菜单id
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String personType,String quickMenu_id, String versionFlag) throws ServiceException;
	
	/**
	 * 修改服务菜单实体
	 * @Title: ISpecialPersonWithQuickMenuService
	 * @Description:
	 * @param @param personType  服务人员类型
	 * @param @param quickMenu_id  快捷菜单id
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String personType,String quickMenu_id, String versionFlag) throws ServiceException;
	
	/**
	 * 移除服务菜单实体
	 * @Title: ISpecialPersonWithQuickMenuService
	 * @Description: 
	 * @param @param id 服务菜单ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 批量彻底删除服务菜单
	 * @param idsValue
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException;

	/**
	 * 根据人员类型查询对应的菜单列表
	 * @param personType  人员类型
	 * @param versionFlag 版本标识
	 * @return
	 * @throws ServiceException
	 */
	List<SpecialPersonWithQuickMenu> queryListByPersonType(String personType, String versionFlag);
}