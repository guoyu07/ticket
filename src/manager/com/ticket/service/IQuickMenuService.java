package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.QuickMenu;


/**
 * 快捷菜单业务接口
 * @ClassName: IQuickMenuService   
 * @Description: 提供快捷菜单操作的增删改查   
 * @author HiSay  
 * @date  2015-10-31 13:01:07
 *
 */
public interface IQuickMenuService extends IBaseService<QuickMenu, String> {
	/**
	 * 保存快捷菜单实体
	 * @Title: IQuickMenuService
	 * @Description:
	 * @param @param name  菜单名称
	 * @param @param url  菜单链接
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String url,String isDefaultShow,String defaultShowPosition, String versionFlag, String icon, String quickMenuType_id, Integer orderValue) throws ServiceException;
	
	/**
	 * 修改快捷菜单实体
	 * @Title: IQuickMenuService
	 * @Description:
	 * @param @param name  菜单名称
	 * @param @param url  菜单链接
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String url,String isDefaultShow,String defaultShowPosition, String versionFlag, String icon, String quickMenuType_id, Integer orderValue) throws ServiceException;
	
	/**
	 * 移除快捷菜单实体
	 * @Title: IQuickMenuService
	 * @Description: 
	 * @param @param id 快捷菜单ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 根据菜单类别id查询菜单列表
	 * @param menu_id
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<QuickMenu> queryByMenuId(String menuType_id, String versionFlag) throws ServiceException;

	/**
	 * 查询默认显示的出发快捷菜单
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<QuickMenu> queryQuickMenuListByVisitor(String defaultShowPosition,String versionFlag);

	/**
	 * 查询默认显示的快捷菜单列表
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<QuickMenu> queryListByDefaultShow(String versionFlag) throws ServiceException;

	/**
	 * 批量彻底删除快捷菜单实体
	 * @param idsValue
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException;

	/**
	 * 查询快捷菜单列表
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<QuickMenu> queryList(String versionFlag) throws ServiceException;


	/**
	 * 根据菜单类别id和位置查询菜单
	 * @param menu_id
	 * @param position
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<QuickMenu> queryByIdAndPosition(String menuType_id, String position,String versionFlag) throws ServiceException;
}