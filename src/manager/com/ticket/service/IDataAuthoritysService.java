package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.DataAuthoritys;


/**
 * 数据权限业务接口
 * @ClassName: IDataAuthoritysService   
 * @Description: 提供数据权限操作的增删改查   
 * @author HiSay  
 * @date  2016-05-25 15:20:21
 *
 */
public interface IDataAuthoritysService extends IBaseService<DataAuthoritys, String> {
	/**
	 * 保存数据权限实体
	 * @Title: IDataAuthoritysService
	 * @Description:
	 * @param @param name  权限名称
	 * @param @param descript  权限描述
	 * @param @param content  权限内容
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String descript,String content,String systemModulId,String inMethod, String versionFlag) throws ServiceException;
	
	/**
	 * 修改数据权限实体
	 * @Title: IDataAuthoritysService
	 * @Description:
	 * @param @param name  权限名称
	 * @param @param descript  权限描述
	 * @param @param content  权限内容
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String descript,String content, String systemModulId,String inMethod,String versionFlag) throws ServiceException;
	
	/**
	 * 移除数据权限实体
	 * @Title: IDataAuthoritysService
	 * @Description: 
	 * @param @param id 数据权限ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	/**
	 * 根据模块id查询这个模块的所有数据权限设置
	 * @param id
	 * @return
	 */
	List<DataAuthoritys> queryBuSystemModulId(String id);
	/**
	 * 根据员工id查找所有数据权限
	 * @param employeeId
	 * @return
	 */
	List<DataAuthoritys> queryByEmployeeId(String employeeId);
}