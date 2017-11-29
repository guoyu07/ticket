package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BindYouZan;
import com.ticket.pojo.BusinessInfo;
import com.ticket.pojo.YouZanGoodsInfo;


/**
 * 有赞商品绑定机场商家业务接口
 * @ClassName: IBindYouZanService   
 * @Description: 提供有赞商品绑定机场商家操作的增删改查   
 * @author HiSay  
 * @date  2017-01-11 10:26:44
 *
 */
public interface IBindYouZanService extends IBaseService<BindYouZan, String> {
	/**
	 * 保存有赞商品绑定机场商家实体
	 * @Title: IBindYouZanService
	 * @Description:
	 * @param @param businessInfo  商家
	 * @param @param goodsInfo  有赞商品
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(BusinessInfo businessInfo,List<YouZanGoodsInfo> goodsInfo, String versionFlag) throws ServiceException;
	/**
	 * 保存有赞商品绑定机场商家实体
	 * @Title: IBindYouZanService
	 * @Description:
	 * @param @param businessInfo  商家
	 * @param @param goodsInfo  有赞商品
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(BusinessInfo businessInfo,YouZanGoodsInfo goodsInfo, String versionFlag) throws ServiceException;
	
	/**
	 * 修改有赞商品绑定机场商家实体
	 * @Title: IBindYouZanService
	 * @Description:
	 * @param @param businessInfo  商家
	 * @param @param goodsInfo  有赞商品
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, BusinessInfo businessInfo,List<YouZanGoodsInfo> goodsInfo, String versionFlag) throws ServiceException;
	/**
	 * 修改有赞商品绑定机场商家实体
	 * @Title: IBindYouZanService
	 * @Description:
	 * @param @param businessInfo  商家
	 * @param @param goodsInfo  有赞商品
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, BusinessInfo businessInfo,YouZanGoodsInfo goodsInfo, String versionFlag) throws ServiceException;
	/**
	 * 移除有赞商品绑定机场商家实体
	 * @Title: IBindYouZanService
	 * @Description: 
	 * @param @param id 有赞商品绑定机场商家ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRemove(String[] ids,String business_id) throws ServiceException;
	
	/**
	 * 根据商家和有赞商品查询
	 * @param business_id
	 * @param goods_id
	 * @return
	 */
	BindYouZan queryByBusinessAndGoods(String business_id,String goods_id);
	
	/**
	 * 根据商家查询有赞商品
	 * @param business_id
	 * @return
	 */
	List<BindYouZan> queryByBusiness(String business_id);
}