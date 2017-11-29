package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CityHistorySelect;


/**
 * 历史选择城市业务接口
 * @ClassName: ICityHistorySelectService   
 * @Description: 提供历史选择城市操作的增删改查   
 * @author HiSay  
 * @date  2015-12-26 17:40:35
 *
 */
public interface ICityHistorySelectService extends IBaseService<CityHistorySelect, String> {
	/**
	 * 保存历史选择城市实体
	 * @Title: ICityHistorySelectService
	 * @Description:
	 * @param @param member_id  会员id
	 * @param @param city_id  城市id
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String member_id,String city_id, String versionFlag) throws ServiceException;
	
	/**
	 * 修改历史选择城市实体
	 * @Title: ICityHistorySelectService
	 * @Description:
	 * @param @param member_id  会员id
	 * @param @param city_id  城市id
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String member_id,String city_id, String versionFlag) throws ServiceException;
	
	/**
	 * 移除历史选择城市实体
	 * @Title: ICityHistorySelectService
	 * @Description: 
	 * @param @param id 历史选择城市ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 验证改记录是否已保存
	 * @param member_id  会员id
	 * @param city_id	 城市id
	 * @return
	 * @throws ServiceException
	 */
	boolean validateExist(String member_id, String city_id) throws ServiceException;

	/**
	 * 查询会员历史选择城市列表
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<CityHistorySelect> queryListByMember(String versionFlag) throws ServiceException;
	
	/**
	 * 根据游客查找历史选择城市
	 * @param touristId
	 * @return
	 * @throws ServiceException
	 */
	List<CityHistorySelect> queryByTourist(String touristId) throws ServiceException;
}