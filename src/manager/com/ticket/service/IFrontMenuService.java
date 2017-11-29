package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.FrontMenu;


/**
 * 前端菜单管理表业务接口
 * @ClassName: IFrontMenuService   
 * @Description: 提供前端菜单管理表操作的增删改查   
 * @author HiSay  
 * @date  2016-02-19 14:55:25
 *
 */
public interface IFrontMenuService extends ITreeService<Object, String> {
	
	/**
	 * 保存系统菜单表实体
	 * @Title: ISysDictionaryService
	 * @Description:
	 * @param name  菜单名称
	 * @param value  菜单值
	 * @param loadSub  是否加载子菜单
	 * @param parent_id  父菜单ID
	 * @return  保存成功则返回true, 保存失败则返回false.
	 * @throws ServiceException   
	 */
	boolean persist(String name,String value, boolean loadSub, int sort, String description,String parent_id) throws ServiceException;
	
	/**
	 * 保存系统菜单表实体
	 * @Title: ISysDictionaryService
	 * @Description:
	 * @param name  菜单名称
	 * @param value  菜单值
	 * @param loadSub  是否加载子菜单
	 * @param parent  父菜单
	 * @return  保存成功则返回true, 保存失败则返回false.
	 * @throws ServiceException   
	 */
	boolean persist(String name,String value, boolean loadSub, int sort, String description,FrontMenu parent) throws ServiceException;
	
	/**
	 * 修改系统菜单表实体
	 * @Title: ISysDictionaryService
	 * @Description:
	 * @param name  菜单名称
	 * @param value  菜单值
	 * @param loadSub  是否加载子菜单
	 * @param parent_id  父菜单ID
	 * @return  修改成功则返回true, 修改失败则返回false.
	 * @throws ServiceException    
	 */
	boolean merge(String id, String name,String value, boolean loadSub, int sort, String description,String parent_id) throws ServiceException;
	
	/**
	 * 修改系统菜单表实体
	 * @Title: ISysDictionaryService
	 * @Description:
	 * @param name  菜单名称
	 * @param value  菜单值
	 * @param loadSub  是否加载子菜单
	 * @param parent  父菜单
	 * @return  修改成功则返回true, 修改失败则返回false.
	 * @throws ServiceException    
	 */
	boolean merge(String id, String name,String value, boolean loadSub, int sort, String description,FrontMenu parent) throws ServiceException;
	
	/**
	 * 移除系统菜单表实体
	 * @Title: ISysDictionaryService
	 * @Description: 
	 * @param id 系统菜单表ID
	 * @return 移除成功返回true, 移除失败返回false.
	 * @throws ServiceException    
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * @Description：通过菜单名称获得菜单值
	 * @param name 菜单名称
	 * @return
	 */
	String getValueByName(String name);
	
	/**
	 * @Description：通过菜单名称获得菜单值
	 * @param name 菜单名称
	 * @return
	 */
	String getDescriptByName(String name);
	
	/**
	 * @Description：查询子节点节点通过父节点名称
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	List<FrontMenu> querySubByParentName(String parentName);
	
	/**
	 * @Description：查询子节点节点通过父节点名称
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	List<FrontMenu> querySubByParentValue(String parentValue);
	
	/**
	 * @Description：查询子节点节点通过父节点名称 根据首字母排序
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	List<FrontMenu> querySubByParentValueOrderByLetter(String parentValue, String...columnNames);
	
	/**
	 * @Description：通过名称获取菜单对象
	 * @param name
	 * @return
	 */
	FrontMenu getByName(String name);
	
	/**
	 * @Description：通过菜单值获取菜单对象
	 * @param value
	 * @return
	 */
	FrontMenu getByValue(String value);
	
	/**
	 * @Description：在父节点下是否存在name相同的对象
	 * @param parent_id
	 * @param name
	 * @return
	 */
	boolean existSameUnderParent(String parent_id, String name);
	
	/**
	 * @Description：在父节点下是否存在name相同的对象
	 * @param parent_id
	 * @param name
	 * @return
	 */
	boolean existSameUnderParent(FrontMenu parent, String name);
	
	/**
	 * @Description：在父节点下是否存在name相同的对象
	 * @param parent_id
	 * @param name
	 * @return
	 */
	boolean existSameUnderParentByName(String parent_name, String name);
	
	/**
	 * @Description：在父节点下是否存在name相同的对象
	 * @param parent_id
	 * @param name
	 * @return
	 */
	FrontMenu getUnderParent(String parent_id, String name);
	
	/**
	 * @Description：在父节点下是否存在name相同的对象
	 * @param parent_id
	 * @param name
	 * @return
	 */
	FrontMenu getUnderParent(FrontMenu parent, String name);
	
	/**
	 * @Description：在父节点下是否存在name相同的对象
	 * @param parent_id
	 * @param name
	 * @return
	 */
	FrontMenu getUnderParentByName(String parent_name, String name);
	
	/**
	 * 查询顶层菜单
	 * @return
	 */
	List<FrontMenu> queryRoot();
	
	/**
	 * 查询子菜单，通过父菜单
	 * @param parent
	 * @return
	 */
	List<FrontMenu> querySub(FrontMenu parent);
	
}