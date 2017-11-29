package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.SystemModule;
import com.ticket.pojo.SystemUser;


/**
 * 系统模块业务接口
 * @ClassName: ISystemModuleService   
 * @Description: 提供系统模块操作的增删改查   
 * @author HiSay  
 * @date  2014-10-15 13:49:51
 *
 */
public interface ISystemModuleService extends IBaseService<SystemModule, String> {
	/**
	 * 保存系统模块实体
	 * @Title: ISystemModuleService
	 * @Description:
	 * @param @param name  模块名称
	 * @param @param url  模块链接
	 * @param @param parent_id  所属上级模块
	 * @param @param icon  模块图标
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String url,String parent_id,String icon, String versionFlag, Integer orderValue) throws ServiceException;
	
	/**
	 * 修改系统模块实体
	 * @Title: ISystemModuleService
	 * @Description:
	 * @param @param name  模块名称
	 * @param @param url  模块链接
	 * @param @param parent_id  所属上级模块
	 * @param @param icon  模块图标
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String url,String parent_id,String icon, String versionFlag, Integer orderValue) throws ServiceException;
	
	/**
	 * 移除系统模块实体
	 * @Title: ISystemModuleService
	 * @Description: 
	 * @param @param id 系统模块ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 获得系统模块Select列表的Option字符串
	 * @Title: getModuleHtml 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param versionFlag
	 * @param @return
	 * @param @throws ServiceException    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	String getModuleHtml(String versionFlag) throws ServiceException;
	
	/**
	 * 获得系统模块Select列表的Option字符串
	 * @Title: getModuleHtml 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param versionFlag
	 * @param @return
	 * @param @throws ServiceException    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	String getModuleHtmlByAction(String versionFlag, String systemUser_id) throws ServiceException;
	
	/**
	 * 设置模块是否为默认显示
	 * @Title: IModuleService
	 * @Description: 根据模块的ID设置模块是否为默认显示.   
	 * @param @param id
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	boolean setModuleIsDefaultShow(String entityName, String id) throws ServiceException;
	
	/**
	 * 获取第一级的模块列表(也就是顶级模块列表)
	 * @Title: IModuleService
	 * @Description: 根据版本标识获取顶级模块列表   
	 * @param @param versionFlag 模块的版本标识
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	List<SystemModule> queryFirstModuleList(String versionFlag) throws ServiceException;
	
	/**
	 * 根据管理员获取已授权的第一级的模块列表(也就是顶级模块列表)
	 * @Title: IModuleService
	 * @Description: 根据版本标识获取顶级模块列表   
	 * @param @param versionFlag 模块的版本标识
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	List<SystemModule> queryFirstModuleList(SystemUser systemUser, String versionFlag) throws ServiceException;
	
	/**
	 * 根据父模块ID获取该父模块下面的子模块列表
	 * @Title: IModuleService
	 * @Description:    
	 * @param @param parent_id  父模块ID
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	List<SystemModule> queryChildModulesByParent(String parent_id) throws ServiceException;
	
	/**
	 * 根据父模块ID获取该父模块下面“有权限访问”的子模块列表
	 * @Title: IModuleService
	 * @Description:    
	 * @param @param parent_id  父模块ID
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	List<SystemModule> queryChildModulesByParent(String userId, String parent_id) throws ServiceException;
	
	/**
	 * 验证父模块是否含有子模块
	 * @Title: validateHaveChildModules 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param parent_id
	 * @param @return
	 * @param @throws ServiceException    设定文件 
	 * @return boolean    返回类型 
	 * @throws
	 */
	boolean validateHaveChildModules(String parent_id) throws ServiceException;
	
	/**
	 * 根据版本标识和父模块ID获取模块的HTML代码
	 * @Title: IModuleService
	 * @Description:   
	 * @param @param parent_id    上级模块ID, 如果获取第一模块, 则传入0
	 * @param @param versionFlag  版本表示
	 * @param @return 生成后的HTML代码
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	String queryModuleSelectOptionHtml(String parent_id, String versionFlag) throws ServiceException;
	
	/**
	 * 根据左侧模块列表和版本标识获取左侧模块导航的JSON代码
	 * @Title: IModuleService
	 * @Description:   
	 * @param @param leftModueList  左侧的模块列表数据
	 * @param @param versionFlag    版本表示
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	void queryModuleLeftTreeJson(List<SystemModule> leftModuleList, String versionFlag) throws ServiceException;
	
	/**
	 * 获取左侧的模块列表
	 * @Title: queryLeftModules 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param parent_id
	 * @param @param versionFlag
	 * @param @return
	 * @param @throws ServiceException    设定文件 
	 * @return List<SystemModule>    返回类型 
	 * @throws
	 */
	List<SystemModule> queryLeftModules(String parent_id, String versionFlag) throws ServiceException;

	/**
	 * 查询系统模块列表
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<SystemModule> queryByList(String versionFlag) throws ServiceException;

	/**
	 * 保存类别模块并生成子模块
	 * @param name  模块名称
	 * @param url   模块链接
	 * @param parent_id 模块父id
	 * @param icon 模块图标
	 * @param versionFlag  版本标识
	 * @param orderValue  排序值
	 * @return  Boolean
	 */
	boolean persistAndGetChild(String name, String url, String parent_id, String icon, String versionFlag, Integer orderValue,String newsClass_id);
}