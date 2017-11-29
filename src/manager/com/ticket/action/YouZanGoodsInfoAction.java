package com.ticket.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.http.HttpResponse;

import com.kdt.api.KdtApiClient;
import com.kdt.common.GlobalConfig;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.YouZanGoodsInfo;
import com.ticket.service.IYouZanGoodsInfoService;
import com.ticket.util.GeneralUtil;

/**
 * 有赞出售中的商品控制器
 * @ClassName: YouZanGoodsInfoAction   
 * @Description:  提供有赞出售中的商品的相关操作方法. 
 * @author HiSay  
 * @date 2017-01-05 16:13:06
 *
 */
public class YouZanGoodsInfoAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//有赞出售中的商品的业务层
	@Resource private IYouZanGoodsInfoService youZanGoodsInfoService;
	//有赞出售中的商品实体
	private YouZanGoodsInfo youZanGoodsInfo;
	//主键
	private String id;
    //商品ID
	private String num_iid;
    //商品名称
	private String name;
    //商品别名
	private String alias;
    //商品详情url
	private String detail_url;
    //商品价格
	private String price;
    //商品销量
	private String sold_num;
    //上架状态
	private String listing;
	
	public String gobindBusinessInfo(){
		return "gobindBusinessInfo";
	}
	
	/**
	 * 刷新
	 * @return
	 */
	public String refrech(){
		try {
			youZanGoodsInfoService.getYouZanOpenData();
			data = "刷新成功！";
		} catch (Exception e) {
			data = "请求失败！";
			e.printStackTrace();
		}
		return TEXT;
	}
	
	/**
	 * 获取出售中的商品列表
	 * @return
	 */
	public String getOnsale(){
		String method = "kdt.items.onsale.get";
		HashMap<String, String> params = new HashMap<String, String>();
		KdtApiClient kdtApiClient;
		HttpResponse response;
		
		try {
			kdtApiClient = new KdtApiClient(GlobalConfig.APP_ID, GlobalConfig.APP_SECRET);
			response = kdtApiClient.get(method, params);
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line);
			}

			System.out.println(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 添加有赞出售中的商品
	 * @Title: YouZanGoodsInfoAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addYouZanGoodsInfo";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(num_iid)) {
				data = getText("num_iid.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(name)) {
				data = getText("name.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(alias)) {
				data = getText("alias.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(detail_url)) {
				data = getText("detail_url.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(price)) {
				data = getText("price.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(sold_num)) {
				data = getText("sold_num.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(listing)) {
				data = getText("listing.required");
				return TEXT;
			}
			//保存有赞出售中的商品实体
			boolean isSuc = youZanGoodsInfoService.persist(num_iid, name, alias, detail_url, price, sold_num, listing, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				data = getText("addSuccess");
			} else {
				data = getText("addFailed");
			}
			return TEXT;
		}
	}
	
	/**
	 * 修改有赞出售中的商品
	 * @Title: YouZanGoodsInfoAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setYouZanGoodsInfo(youZanGoodsInfoService.queryById(YouZanGoodsInfo.class.getSimpleName(), id));
			return "editYouZanGoodsInfo";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = getText("id.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(num_iid)) {
				data = getText("num_iid.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(name)) {
				data = getText("name.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(alias)) {
				data = getText("alias.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(detail_url)) {
				data = getText("detail_url.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(price)) {
				data = getText("price.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(sold_num)) {
				data = getText("sold_num.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(listing)) {
				data = getText("listing.required");
				return TEXT;
			}
			//修改有赞出售中的商品实体
			boolean isSuc = youZanGoodsInfoService.merge(id, num_iid, name, alias, detail_url, price, sold_num, listing,  versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				data = getText("editSuccess");
			} else {
				data = getText("editFailed");
			}
			return TEXT;
		}
	}
	
	/**
	 * 管理有赞出售中的商品实体
	 * @Title: YouZanGoodsInfoAction
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(youZanGoodsInfoService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageYouZanGoodsInfo";
	}
	
	/**
	 * 查看回收站
	 * @Title: YouZanGoodsInfoAction
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(youZanGoodsInfoService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleYouZanGoodsInfo";
	}
	
	/**
	 * 逻辑删除有赞出售中的商品对象
	 * @Title: YouZanGoodsInfoAction
	 * @Description: 把有赞出售中的商品对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = youZanGoodsInfoService.logicDeleteEntity(YouZanGoodsInfo.class.getSimpleName(), id);
		if(isSuc) {
			data = getText("deleteSuccess");
		} else {
			data = getText("deleteFailed");
		}
		return null;
	}
	
	/**
	 * 物理删除有赞出售中的商品对象
	 * @Title: YouZanGoodsInfoAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = youZanGoodsInfoService.remove(id);
		if(isSuc) {
			data = getText("deleteSuccess");
		} else {
			data = getText("deleteFailed");
		}
		return null;
	}
	
	/**
	 * 还原一个有赞出售中的商品对象
	 * @Title: YouZanGoodsInfoAction
	 * @Description: 从回收站还原有赞出售中的商品对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = youZanGoodsInfoService.restoreEntity(YouZanGoodsInfo.class.getSimpleName(), id);
		if(isSuc) {
			data = getText("restoreSuccess");
		} else {
			data = getText("restoreFailed");
		}
		return null;
	}
	
	/**
	 * 审核有赞出售中的商品对象
	 * @Title: YouZanGoodsInfoAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = youZanGoodsInfoService.auditEntity(YouZanGoodsInfo.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = getText("auditSuccess");
		} else {
			data = getText("auditFailed");
		}
		return null;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: YouZanGoodsInfoAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = youZanGoodsInfoService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = getText("batchSuccess");
		} else {
			data = getText("batchFailed");
		}
		return null;
	}
	
	public YouZanGoodsInfo getYouZanGoodsInfo() {
		return youZanGoodsInfo;
	}
	public void setYouZanGoodsInfo(YouZanGoodsInfo youZanGoodsInfo) {
		this.youZanGoodsInfo = youZanGoodsInfo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNum_iid() {
		return num_iid;
	}
	public void setNum_iid(String num_iid) {
		this.num_iid = num_iid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getDetail_url() {
		return detail_url;
	}
	public void setDetail_url(String detail_url) {
		this.detail_url = detail_url;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSold_num() {
		return sold_num;
	}
	public void setSold_num(String sold_num) {
		this.sold_num = sold_num;
	}
	public String getListing() {
		return listing;
	}
	public void setListing(String listing) {
		this.listing = listing;
	}
}
