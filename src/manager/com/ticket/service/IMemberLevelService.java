package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.MemberLevel;


/**
 * 会员等级业务接口
 * @ClassName: IMemberLevelService   
 * @Description: 提供会员等级操作的增删改查   
 * @author HiSay  
 * @date  2015-10-14 16:38:05
 *
 */
public interface IMemberLevelService extends IBaseService<MemberLevel, String> {
	/**
	 * 保存会员等级实体
	 * @Title: IMemberLevelService
	 * @Description:
	 * @param @param name  等级名称
	 * @param @param needScore  所需积分
	 * @param @param descript  等级描述
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String needScore,String descript, String versionFlag) throws ServiceException;
	
	/**
	 * 修改会员等级实体
	 * @Title: IMemberLevelService
	 * @Description:
	 * @param @param name  等级名称
	 * @param @param needScore  所需积分
	 * @param @param descript  等级描述
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String needScore,String descript, String versionFlag) throws ServiceException;
	
	/**
	 * 移除会员等级实体
	 * @Title: IMemberLevelService
	 * @Description: 
	 * @param @param id 会员等级ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 查询会员等级列表
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<MemberLevel> queryList(String versionFlag) throws ServiceException;

	/**
	 * 批量彻底删除会员等级
	 * @param idsValue
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException;
}