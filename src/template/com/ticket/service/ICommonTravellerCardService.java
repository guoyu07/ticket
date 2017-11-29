package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonTravellerCard;
import com.ticket.pojo.Member;


/**
 * 常用旅客证件业务接口
 * @ClassName: ICommonTravellerCardService   
 * @Description: 提供常用旅客证件操作的增删改查   
 * @author HiSay  
 * @date  2016-01-07 15:20:41
 *
 */
public interface ICommonTravellerCardService extends IBaseService<CommonTravellerCard, String> {
	/**
	 * 保存常用旅客证件实体
	 * @Title: ICommonTravellerCardService
	 * @Description:
	 * @param @param cardType  证件类型
	 * @param @param cardValue  证件号码
	 * @param @param parentId  旅客id
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String cardType,String cardValue,String parentId, String versionFlag) throws ServiceException;
	
	/**
	 * 修改常用旅客证件实体
	 * @Title: ICommonTravellerCardService
	 * @Description:
	 * @param @param cardType  证件类型
	 * @param @param cardValue  证件号码
	 * @param @param parentId  旅客id
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String cardType,String cardValue,String parentId, String versionFlag) throws ServiceException;
	
	/**
	 * 移除常用旅客证件实体
	 * @Title: ICommonTravellerCardService
	 * @Description: 
	 * @param @param id 常用旅客证件ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	/**
	 * 根据父id查找常用旅客卡证件
	 * @param parentid
	 * @return
	 * @throws ServiceException
	 */
	public List<CommonTravellerCard> findByParentId(String parentid) throws ServiceException;
	/**
	 * 根据证件号码查找常用旅客卡证件
	 * @param member 
	 * @param cardValue
	 * @return
	 */
	public CommonTravellerCard findByIdCardValue(Member member, String cardValue);
	/**
	 * 根据父id和证件号码查找常用旅客卡证件
	 * @param parentId
	 * @param idCard
	 * @return
	 */
	CommonTravellerCard findByParentIdAndIdCard(String parentId,String idCard);
	/**
	 * 根据父id删除常用旅客卡证件
	 * @param parentId
	 * @return
	 */
	boolean removeByParentId(String parentId);
}