package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.DataAuthoritys;
import com.ticket.pojo.PositionDataAuthority;
import com.ticket.pojo.SystemModule;


/**
 * 岗位权限业务接口
 * @ClassName: IPositionDataAuthorityService   
 * @Description: 提供岗位权限操作的增删改查   
 * @author HiSay  
 * @date  2016-05-25 15:20:45
 *
 */
public interface IPositionDataAuthorityService extends IBaseService<PositionDataAuthority, String> {
	/**
	 * 保存岗位权限实体
	 * @Title: IPositionDataAuthorityService
	 * @Description:
	 * @param @param position_id  岗位id
	 * @param @param dataAuthoritys_id  数据权限id
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String position_id,String dataAuthoritys_id, String systemModuleId,String versionFlag) throws ServiceException;
	
	/**
	 * 修改岗位权限实体
	 * @Title: IPositionDataAuthorityService
	 * @Description:
	 * @param @param position_id  岗位id
	 * @param @param dataAuthoritys_id  数据权限id
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String position_id,String dataAuthoritys_id,String systemModuleId, String versionFlag) throws ServiceException;
	
	/**
	 * 移除岗位权限实体
	 * @Title: IPositionDataAuthorityService
	 * @Description: 
	 * @param @param id 岗位权限ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 根据模块id查询数据权限
	 * @param id
	 * @return
	 */
	List<PositionDataAuthority> queryBuSystemModulId(String id);
	
	/**
	 * 根据岗位id/模块id/权限id查询中间表
	 * @param positionId
	 * @param moduleId
	 * @param authorityId
	 * @param versionFlag
	 * @return
	 */
	PositionDataAuthority queryByRIDAndMIDAndPID(String positionId,
			String moduleId, String authorityId, String versionFlag);
	/**
	 * 给某某授予全部权限
	 * @param position_id
	 * @param moduleList
	 * @param authoritys
	 * @param versionFlag
	 */
	void selectAll(String position_id, List<SystemModule> moduleList,
			List<DataAuthoritys> authoritys, String versionFlag) throws ServiceException;
	/**
	 * 取消全部数据权限
	 * @param position_id
	 * @param versionFlag
	 * @throws ServiceException
	 */
	void selectNone(String position_id, String versionFlag) throws ServiceException;
	
	/**
	 * 删除其他的数据权限
	 * @param id
	 * @throws ServiceException
	 */
	void deleteOthers(String id) throws ServiceException;
}