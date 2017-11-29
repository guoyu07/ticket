package com.ticket.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.LostGoodsInfo;
import com.ticket.pojo.Pagination;


/**
 * 遗失物品信息业务接口
 * @ClassName: ILostGoodsInfoService   
 * @Description: 提供遗失物品信息操作的增删改查   
 * @author HiSay  
 * @date  2015-11-23 16:17:30
 *
 */
public interface ILostGoodsInfoService extends IBaseService<LostGoodsInfo, String> {
	/**
	 * 保存遗失物品信息实体
	 * @Title: ILostGoodsInfoService
	 * @Description:
	 * @param @param type_id  物品类型
	 * @param @param goodsId  物品编号
	 * @param @param weight  物品重量
	 * @param @param color  物品颜色
	 * @param @param otherInfoOne  其他信息一
	 * @param @param otherInfoTwo  其他信息二
	 * @param @param pickerName  拾取者姓名
	 * @param @param pickerPhone  拾取者电话
	 * @param @param pickUpTime  拾取时间
	 * @param @param pickPosition_id  拾取位置
	 * @param @param goodsDescript  物品描述
	 * @param @param remark  备注
	 * @param @param operator_id  操作员
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String type_id,String name,String goodsId,Double weight,String color,String otherInfoOne,String otherInfoTwo,String pickerName,String pickerPhone,String pickUpTime,String pickPosition_id,String goodsDescript,String remark,String operator_id,String commitTime,String stockPosition,String goodsNumber, String versionFlag) throws ServiceException;
	
	/**
	 * 修改遗失物品信息实体
	 * @Title: ILostGoodsInfoService
	 * @Description:
	 * @param @param type_id  物品类型
	 * @param @param goodsId  物品编号
	 * @param @param weight  物品重量
	 * @param @param color  物品颜色
	 * @param @param otherInfoOne  其他信息一
	 * @param @param otherInfoTwo  其他信息二
	 * @param @param pickerName  拾取者姓名
	 * @param @param pickerPhone  拾取者电话
	 * @param @param pickUpTime  拾取时间
	 * @param @param pickPosition_id  拾取位置
	 * @param @param goodsDescript  物品描述
	 * @param @param remark  备注
	 * @param @param operator_id  操作员
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String type_id,String name,String goodsId,Double weight,String color,String otherInfoOne,String otherInfoTwo,String pickerName,String pickerPhone,String pickUpTime,String pickPosition_id,String goodsDescript,String remark,String operator_id,String commitTime,String stockPosition,String goodsNumber, String versionFlag) throws ServiceException;
	
	/**
	 * 移除遗失物品信息实体
	 * @Title: ILostGoodsInfoService
	 * @Description: 
	 * @param @param id 遗失物品信息ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 根据关键词检索物品信息
	 * @param keyword
	 * @param versionFlag
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryByKeyword(String keyword, String versionFlag, int pageSize) throws ServiceException ;
	
	/**
	 * 根据用户选择条件查询遗失物品实体
	 * @param name
	 * @param color
	 * @param type_id
	 * @param pickPosition_id
	 * @param startTime
	 * @param endTime
	 * @param versionFlag
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryEntityByAdmin(String goodsNumber,String name,String color,String type_id,String pickPosition_id,Date startTime, Date endTime, String versionFlag, Integer pageSize) throws ServiceException;
	/**
	 * 未领取的物品实体
	 * @param versionFlag
	 * @param adminCommonPageSize
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryEntityByCommendAndHot(String versionFlag, int pageSize) throws ServiceException;

	/**
	 * 批量彻底删除物品实体
	 * @param idsValue
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException;

	/**
	 * 根据用户提供的信息查询遗失物品
	 * @param pickUpTime
	 * @param pickPositionId
	 * @param typeId
	 * @param color
	 * @param goodsDescript
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<LostGoodsInfo> queryByFront(String pickUpTime, String pickPositionId,
			String typeId, String color, String goodsDescript,
			String versionFlag) throws ServiceException;
	/**
	 * 根据提供的时间查询遗失物品信息
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ServiceException
	 */
	List<LostGoodsInfo> queryByTimes(Date startTime,Date endTime) throws ServiceException;

	/**
	 * 上传遗失物品信息
	 * @param simpleName 
	 * @param id
	 * @param statusValue
	 * @return
	 * @throws ServiceException
	 */
	boolean hotEntity(String id,Integer stateValue) throws ServiceException;

	/**
	 * 查询未上传的物品信息
	 * @param versionFlag
	 * @param pageSize
	 * @return
	 */
	Pagination queryEntityByNotHot(String versionFlag, int pageSize);
	
	/**
	 * 导入遗失物品
	 * @param request
	 * @param file
	 * @return
	 */
	boolean importLostGoods(HttpServletRequest request,String file) throws Exception;

}