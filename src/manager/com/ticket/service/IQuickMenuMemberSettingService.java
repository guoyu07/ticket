package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.QuickMenuMemberSetting;


/**
 * 会员设置快捷菜单业务接口
 * @ClassName: IQuickMenuMemberSettingService   
 * @Description: 提供会员设置快捷菜单操作的增删改查   
 * @author HiSay  
 * @date  2015-10-31 13:04:17
 *
 */
public interface IQuickMenuMemberSettingService extends IBaseService<QuickMenuMemberSetting, String> {
	
	/**
	 * 初始化用户的快捷菜单
	 * @param member_id
	 */
	void init(String member_id);
	
	/**
	 * 给特殊人群设置快捷菜单
	 * @param types
	 * @param personCount
	 * @param memberSelfMenuId
	 * @param position
	 */
	void setBySpecialPerson(String types, int personCount, String memberSelfMenuId, String position);
	
	/**
	 * 保存会员设置快捷菜单实体
	 * @Title: IQuickMenuMemberSettingService
	 * @Description:
	 * @param @param member_id  会员id
	 * @param @param quickMenu_id  快捷菜单id
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param defaultShowPosition 快捷菜单默认显示的位置
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String member_id, String quickMenu_id,String flightQuickMenu,String defaultShowPosition, String versionFlag);
	
	/**
	 * 修改会员设置快捷菜单实体
	 * @Title: IQuickMenuMemberSettingService
	 * @Description:
	 * @param @param member_id  会员id
	 * @param @param quickMenu_id  快捷菜单id
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String member_id,String quickMenu_id, String versionFlag) throws ServiceException;
	
	/**
	 * 移除会员设置快捷菜单实体
	 * @Title: IQuickMenuMemberSettingService
	 * @Description: 
	 * @param @param id 会员设置快捷菜单ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	/**
	 * 根据会员ID获取快捷菜单列表
	 * @param member_id
	 * @return
	 * @throws ServiceException
	 */
	List<QuickMenuMemberSetting> queryListByMember(String member_id) throws ServiceException;

	/**
	 * 验证会员是否已设置该快捷菜单
	 * @param memberId
	 * @param quickMenuId
	 * @return
	 * @throws ServiceException
	 */
	boolean validateIfSetting(String memberId, String quickMenuId,String defaultShowPosition) throws ServiceException;

	/**
	 * 根据会员Id查看会员默认显示快捷菜单列表
	 * @param isDefaultShow  是否默认显示
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<QuickMenuMemberSetting> queryListByMemberIdAndSet(String defaultShowPosition, String versionFlag) throws ServiceException;
	
	/**
	 * 查询前台会员设置的快捷菜单
	 * @param value
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<QuickMenuMemberSetting> queryQuickMenuListByFront(Integer size,String versionFlag) throws ServiceException;

	/**
	 * 会员在某个页面设置过的快捷菜单
	 * @param defaultShowPosition   设置了的位置
	 * @param versionFlag
	 * @return
	 */
	List<QuickMenuMemberSetting> queryListByMemberHasSet(String defaultShowPosition,
			String versionFlag);

	/**
	 * 验证用户设置的快捷菜单是否已达上限
	 * @param defaultShowPosition 设置快捷菜单的位置
	 * @param versionFlag  版本标识
	 * @return 
	 * @throws ServiceException
	 */
	boolean validateCount(String defaultShowPosition,Integer menuCount,String flightQuickMenu,String versionFlag) throws ServiceException;

	/**
	 * 验证个人中心的快捷菜单是否已达上限
	 * @param defaultShowPosition
	 * @param versionFlag
	 * @return
	 */
	boolean validateCenterCount(String defaultShowPosition, String versionFlag);

	/**
	 * 判断会员是否设置过该快捷菜单
	 * @param quickMenuId  快捷菜单id
	 * @param defaultShowPosition  默认显示位置
	 * @return
	 * @throws ServiceException
	 */
	QuickMenuMemberSetting queryByMenuIdAndPosition(String quickMenuId, String defaultShowPosition) throws ServiceException;

	/**
	 * 根据服务菜单查询会员设置的快捷菜单列表
	 * @param flightQuickMenu  服务菜单值
	 * @param size   查询数量
	 * @param versionFlag 版本标识
	 * @return
	 * @throws ServiceException
	 */
	List<QuickMenuMemberSetting> queryListByFlightMenu(String flightQuickMenu,Integer size,String position,String versionFlag) throws ServiceException;

	/**
	 * 查询服务菜单
	 * @param value
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<QuickMenuMemberSetting> queryListByServiceMenu(String flightQuickMenu,
			String versionFlag) throws ServiceException;

	/**
	 * 验证是否已设置过菜单
	 * @param memberId
	 * @param flightQuickMenu
	 * @param defaultShowPosition
	 * @return
	 * @throws ServiceException
	 */
	boolean validateIfSettingFlight(String member_id, String quickMenu_id, String flightQuickMenu,String position) ;

	/**
	 * 查询会员设置的快捷菜单
	 * @param memberId  会员id
	 * @param i  数量
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<QuickMenuMemberSetting> queryQuickMenuListByFront(String memberId,
			int i, String versionFlag) throws ServiceException;

	/**
	 * 根据会员设置的服务菜单编号和位置查询菜单列表
	 * @param position   位置
	 * @param flightQuickMenu  服务菜单编号
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<QuickMenuMemberSetting> queryServiceListByMemberHasSet(String position,
			String flightQuickMenu, String versionFlag) throws ServiceException;

	/**
	 * 删除会员关注航班的快捷菜单
	 * @param memberSelfMenuId  快捷服务菜单标识Id
	 * @param versionFlag
	 * @throws ServiceException
	 */
	void removeBySelfMenuId(String memberSelfMenuId, String versionFlag) throws ServiceException;
}