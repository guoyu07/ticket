package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.YouZanGoodsInfo;


/**
 * 有赞出售中的商品业务接口
 * @ClassName: IYouZanGoodsInfoService   
 * @Description: 提供有赞出售中的商品操作的增删改查   
 * @author HiSay  
 * @date  2017-01-05 16:13:06
 *
 */
public interface IYouZanGoodsInfoService extends IBaseService<YouZanGoodsInfo, String> {
	/**
	 * 保存有赞出售中的商品实体
	 * @Title: IYouZanGoodsInfoService
	 * @Description:
	 * @param @param num_iid  商品ID
	 * @param @param name  商品名称
	 * @param @param alias  商品别名
	 * @param @param detail_url  商品详情url
	 * @param @param price  商品价格
	 * @param @param sold_num  商品销量
	 * @param @param listing  上架状态
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String num_iid,String name,String alias,String detail_url,String price,String sold_num,String listing, String versionFlag) throws ServiceException;
	
	/**
	 * 修改有赞出售中的商品实体
	 * @Title: IYouZanGoodsInfoService
	 * @Description:
	 * @param @param num_iid  商品ID
	 * @param @param name  商品名称
	 * @param @param alias  商品别名
	 * @param @param detail_url  商品详情url
	 * @param @param price  商品价格
	 * @param @param sold_num  商品销量
	 * @param @param listing  上架状态
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String num_iid,String name,String alias,String detail_url,String price,String sold_num,String listing, String versionFlag) throws ServiceException;
	
	/**
	 * 移除有赞出售中的商品实体
	 * @Title: IYouZanGoodsInfoService
	 * @Description: 
	 * @param @param id 有赞出售中的商品ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 获取有赞商品数据
	 */
	void getYouZanOpenData();
	
	/**
	 * 根据商品num_iid查询是否存在
	 * @param num_iid
	 * @return
	 */
	YouZanGoodsInfo getByNum_iid(String num_iid);
}