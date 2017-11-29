package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BusinessInfo;
import com.ticket.pojo.Pagination;


/**
 * 商家信息业务接口
 * @ClassName: IBusinessInfoService   
 * @Description: 提供商家信息操作的增删改查   
 * @author HiSay  
 * @date  2015-11-16 15:35:54
 *
 */
public interface IBusinessInfoService extends IBaseService<BusinessInfo, String> {
	/**
	 * 保存商家信息实体
	 * @Title: IBusinessInfoService
	 * @Description:
	 * @param @param businessType_id  商业类别id
	 * @param @param name  商家名称
	 * @param @param introduce  商家简介
	 * @param @param mainSale  主营商品
	 * @param @param businessHours  营业时间
	 * @param @param activityForecast  活动预告
	 * @param @param phone  联系电话
	 * @param @param address  联系地址
	 * @param @param longitude  经度
	 * @param @param latitude  纬度
	 * @param @param lc  楼层号
	 * @param @param score  商家评分
	 * @param @param averagePrice  人均价格
	 * @param @param floorNumber  楼层号
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String businessType_id,String name,String picture,String introduce,
			String mainSale,String businessHours,String activityForecast,
			String phone,String address,Double longitude,
			Double latitude,Integer orderValue,String lc,String wz,String fl,Integer score,Double averagePrice,String floorNumber,String djk,String versionFlag) throws ServiceException;
	
	/**
	 * 修改商家信息实体
	 * @Title: IBusinessInfoService
	 * @Description:
	 * @param @param businessType_id  商业类别id
	 * @param @param name  商家名称
	 * @param @param introduce  商家简介
	 * @param @param mainSale  主营商品
	 * @param @param businessHours  营业时间
	 * @param @param activityForecast  活动预告
	 * @param @param phone  联系电话
	 * @param @param address  联系地址
	 * @param @param longitude  经度
	 * @param @param latitude  纬度
	 * @param @param lc  楼层号
	 * @param @param score  商家评分
	 * @param @param averagePrice  人均价格
	 * @param @param floorNumber   楼层号
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String businessType_id,String name,String picture,String introduce,
			String mainSale,String businessHours,String activityForecast,
			String phone,String address,Double longitude,Double latitude,
			Integer orderValue,String lc,String wz,String fl,Integer score,Double averagePrice,String floorNumber,String djk, String versionFlag) throws ServiceException;
	
	/**
	 * 移除商家信息实体
	 * @Title: IBusinessInfoService
	 * @Description: 
	 * @param @param id 商家信息ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 根据商户类型id查询商户列表
	 * @param businessType_id 商户类型id
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<BusinessInfo> queryListByTypeId(String businessType_id, String versionFlag) throws ServiceException;

	/**
	 * 批量彻底删除商家信息
	 * @param idsValue
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException;

	/**
	 * 根据商户类别和商户名称关键词搜索商户信息
	 * @param businessType  商户类别
	 * @param keyword  商户名称关键词
	 * @return
	 * @throws ServiceException
	 */
	List<BusinessInfo> queryByTypeAndKeyword(String businessType, String keyword ,String versionFlag) throws ServiceException;

	/**
	 * 根据商户类别和地址搜索商户信息
	 * @param businessType
	 * @param address
	 * @return
	 * @throws ServiceException
	 */
	List<BusinessInfo> queryByConditions(String businessType, String address ,String versionFlag) throws ServiceException;

	/**
	 * 查询所有的商户列表
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<BusinessInfo> queryListByType(String businessType,String versionFlag) throws ServiceException;

	/**
	 * 查询推荐的商家列表
	 * @param businessType
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<BusinessInfo> queryListByCommend(Integer size,String versionFlag) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 获取pc的机场商业
	 * @method queryByPc
	 * @param businessInfoType_id
	 * @param keyword
	 * @param lc
	 * @param wz
	 * @param fl
	 * @return
	 * @throws ServiceException
	 * @return List<BusinessInfo>
	 * @date 2016-2-20 下午02:06:42
	 */
	List<BusinessInfo> queryByPc(String businessInfoType_id,String keyword,String lc,String wz,String fl) throws ServiceException;

	/**
	 * 根据关键词查询商家信息列表
	 * @param keyword  关键词
	 * @param pageSize 页面大小
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryEntityByKeyword(String keyword, int pageSize,
			String versionFlag) throws ServiceException;

	/**
	 * 
	 * @param businessType_id  类别ID  
	 * @param pageSize  页面大小
	 * @param lc  楼层
	 * @param wz  位置
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryEntityByConditions(String businessType_id,String lc,String wz, int pageSize,
			String versionFlag) throws ServiceException;
	
	/**
	 * 根据主营商品模糊查询商家信息接口
	 * @param keyword
	 * @return
	 * @throws ServiceException
	 */
	List<BusinessInfo> getBusinessInfoMainSaleLike(String keyword) throws ServiceException;
	
	/**
	 * 根据登机口,楼层，区域查询商家
	 * @return
	 * @throws ServiceException
	 */
	List<BusinessInfo> queryByWz(String djk) throws ServiceException;
	
	/**
	 * 自动设置有赞销量
	 */
	void aotuSales();
	
	/**
	 * 根据有赞销量排序
	 * @param order
	 * @return
	 */
	List<BusinessInfo> queryByOrder(String order);
	
	/**
	 * 根据分类／位置／排序查询
	 * @param type_id
	 * @param wz
	 * @param order
	 * @return
	 */
	List<BusinessInfo> queryByTypeAndWzAndOrder(String type_id,String wz,String order);
}