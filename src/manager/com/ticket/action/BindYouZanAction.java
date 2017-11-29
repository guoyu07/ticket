package com.ticket.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BindYouZan;
import com.ticket.pojo.BusinessInfo;
import com.ticket.pojo.YouZanGoodsInfo;
import com.ticket.service.IBindYouZanService;
import com.ticket.service.IBusinessInfoService;
import com.ticket.service.IYouZanGoodsInfoService;
import com.ticket.bo.AjaxData;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * 有赞商品绑定机场商家控制器
 * @ClassName: BindYouZanAction   
 * @Description:  提供有赞商品绑定机场商家的相关操作方法. 
 * @author HiSay  
 * @date 2017-01-11 10:26:44
 *
 */
public class BindYouZanAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//有赞商品绑定机场商家的业务层
	@Resource private IBindYouZanService bindYouZanService;
	@Resource private IYouZanGoodsInfoService youZanGoodsInfoService;
	@Resource private IBusinessInfoService businessInfoService;
	//有赞商品绑定机场商家实体
	private BindYouZan bindYouZan;
	//主键
	private String id;
    //商家
	private BusinessInfo businessInfo;
    //有赞商品
	private YouZanGoodsInfo goodsInfo;
	
	private List<BindYouZan> bindYouZans;
	
	private String ids;
	
	private String business_id;
	
	private String goods_id;
	
	private String keyword;
	
	/**
	 * 添加有赞商品绑定机场商家
	 * @Title: BindYouZanAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
			if(GeneralUtil.isNotNull(keyword)){
				keyword = DecoderUtil.UtfDecoder(keyword);
				this.setPageModule(youZanGoodsInfoService.paginationQuery("select c from " + YouZanGoodsInfo.class.getName() + " c where c.name like ?", YouZanGoodsInfo.class, "%"+keyword+"%"));
			}else{
				this.setPageModule(youZanGoodsInfoService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
			}
			this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
			this.setBusinessInfo(businessInfoService.get(BusinessInfo.class, id));
			return "bindYouZanGoods";
		} else {
			businessInfo = businessInfoService.get(BusinessInfo.class, id);
			//非空验证.
			if(GeneralUtil.isNull(businessInfo)) {
				data = AjaxData.responseError(getText("businessInfo.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(ids)) {
				data = AjaxData.responseError(getText("goodsInfo.required"));
				return JSON;
			}
			List<YouZanGoodsInfo> goodsInfos = new ArrayList<YouZanGoodsInfo>();
			String[] idss = ids.split(",");
			if(GeneralUtil.isNotNull(idss)){
				for(String id:idss){
					YouZanGoodsInfo goodsInfo = youZanGoodsInfoService.get(YouZanGoodsInfo.class, id.trim());
					if(goodsInfo != null){
						goodsInfos.add(goodsInfo);
					}
				}
			}
			//保存有赞商品绑定机场商家实体
			try {
				bindYouZanService.persist(businessInfo, goodsInfos, versionFlag);
			} catch (Exception e) {
				data = AjaxData.responseError("请求失败!");
				e.printStackTrace();
				return JSON;
			}
			data = AjaxData.responseSuccess(getText("addSuccess"));
			return JSON;
		}
	}
	
	/**
	 * 绑定
	 * @return
	 */
	public String bind() throws ServiceException{
		if(GeneralUtil.isNotNull(business_id)){
			businessInfo = businessInfoService.get(BusinessInfo.class, business_id);
		}
		if(GeneralUtil.isNotNull(goods_id)){
			goodsInfo = youZanGoodsInfoService.get(YouZanGoodsInfo.class, goods_id);
		}
		if(GeneralUtil.isNotNull(businessInfo) && GeneralUtil.isNotNull(goodsInfo)){
			bindYouZanService.persist(businessInfo, goodsInfo, versionFlag);
			data = AjaxData.responseError("绑定成功！");
		}else{
			data = AjaxData.responseError("绑定失败！");
		}
		
		return JSON;
	}
	
	/**
	 * 批量绑定
	 * @return
	 * @throws ServiceException
	 */
	public String batchBind() throws ServiceException{
		businessInfo = businessInfoService.get(BusinessInfo.class, business_id);
		//非空验证.
		if(GeneralUtil.isNull(businessInfo)) {
			data = AjaxData.responseError(getText("businessInfo.required"));
			return JSON;
		}
		if(GeneralUtil.isNull(ids)) {
			data = AjaxData.responseError(getText("goodsInfo.required"));
			return JSON;
		}
		List<YouZanGoodsInfo> goodsInfos = new ArrayList<YouZanGoodsInfo>();
		String[] idss = ids.split(",");
		if(GeneralUtil.isNotNull(idss)){
			for(String id:idss){
				YouZanGoodsInfo goodsInfo = youZanGoodsInfoService.get(YouZanGoodsInfo.class, id.trim());
				if(goodsInfo != null){
					goodsInfos.add(goodsInfo);
				}
			}
		}
		try {
			bindYouZanService.persist(businessInfo, goodsInfos, versionFlag);
			data = AjaxData.responseSuccess("批量绑定成功！");
		} catch (Exception e) {
			data = AjaxData.responseError("请求失败!");
			e.printStackTrace();
			return JSON;
		}
		data = AjaxData.responseSuccess(getText("addSuccess"));
		return JSON;
	}
	
	/**
	 * 批量取消绑定
	 * @return
	 * @throws ServiceException
	 */
	public String batchUnBind() throws ServiceException{
		String[] idss = ids.split(",");
		try {
			bindYouZanService.batchRemove(idss,business_id);
			data = AjaxData.responseSuccess("批量取消绑定成功！");
		} catch (Exception e) {
			data = AjaxData.responseError("批量取消绑定失败！");
			e.printStackTrace();
		}
		return JSON;
	}
	
	/**
	 * 取消绑定
	 * @return
	 * @throws ServiceException
	 */
	public String unBind() throws ServiceException{
		bindYouZan = bindYouZanService.queryByBusinessAndGoods(business_id, goods_id);
		if(GeneralUtil.isNotNull(bindYouZan)){
			bindYouZanService.remove(bindYouZan);
			data = AjaxData.responseSuccess("取消绑定成功！");
		}else{
			data = AjaxData.responseError("取消绑定失败！");
		}
		return JSON;
	}
	
	/**
	 * 修改有赞商品绑定机场商家
	 * @Title: BindYouZanAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
			if(GeneralUtil.isNotNull(keyword)){
				keyword = DecoderUtil.UtfDecoder(keyword);
				this.setPageModule(youZanGoodsInfoService.paginationQuery("select c from " + YouZanGoodsInfo.class.getName() + " c where c.name like ?", YouZanGoodsInfo.class, "%"+keyword+"%"));
			}else{
				this.setPageModule(youZanGoodsInfoService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
			}
			this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
			this.setBindYouZans(bindYouZanService.queryByBusiness(id));
			this.setBusinessInfo(businessInfoService.get(BusinessInfo.class, id));
//			this.setBindYouZan(bindYouZanService.queryById(BindYouZan.class.getSimpleName(), id));
			return "editBindYouZan";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			businessInfo = businessInfoService.get(BusinessInfo.class, id);
			if(GeneralUtil.isNull(businessInfo)) {
				data = AjaxData.responseError(getText("businessInfo.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(ids)) {
				data = AjaxData.responseError(getText("goodsInfo.required"));
				return JSON;
			}
			List<YouZanGoodsInfo> goodsInfos = new ArrayList<YouZanGoodsInfo>();
			String[] idss = ids.split(",");
			if(GeneralUtil.isNotNull(idss)){
				for(String id:idss){
					YouZanGoodsInfo goodsInfo = youZanGoodsInfoService.get(YouZanGoodsInfo.class, id.trim());
					if(goodsInfo != null){
						goodsInfos.add(goodsInfo);
					}
				}
			}
			//修改有赞商品绑定机场商家实体
			boolean isSuc = bindYouZanService.merge(id, businessInfo, goodsInfos,  versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				data = AjaxData.responseSuccess(getText("editSuccess"));
			} else {
				data = AjaxData.responseError(getText("editFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 管理有赞商品绑定机场商家实体
	 * @Title: BindYouZanAction
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(bindYouZanService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageBindYouZan";
	}
	
	/**
	 * 查看回收站
	 * @Title: BindYouZanAction
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(bindYouZanService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleBindYouZan";
	}
	
	/**
	 * 逻辑删除有赞商品绑定机场商家对象
	 * @Title: BindYouZanAction
	 * @Description: 把有赞商品绑定机场商家对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = bindYouZanService.logicDeleteEntity(BindYouZan.class.getSimpleName(), id);
		if(isSuc) {
			data = getText("deleteSuccess");
		} else {
			data = getText("deleteFailed");
		}
		return TEXT;
	}
	
	/**
	 * 物理删除有赞商品绑定机场商家对象
	 * @Title: BindYouZanAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = bindYouZanService.remove(id);
		if(isSuc) {
			data = getText("deleteSuccess");
		} else {
			data = getText("deleteFailed");
		}
		return TEXT;
	}
	
	/**
	 * 还原一个有赞商品绑定机场商家对象
	 * @Title: BindYouZanAction
	 * @Description: 从回收站还原有赞商品绑定机场商家对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = bindYouZanService.restoreEntity(BindYouZan.class.getSimpleName(), id);
		if(isSuc) {
			data = getText("restoreSuccess");
		} else {
			data = getText("restoreFailed");
		}
		return TEXT;
	}
	
	/**
	 * 审核有赞商品绑定机场商家对象
	 * @Title: BindYouZanAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = bindYouZanService.auditEntity(BindYouZan.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = getText("auditSuccess");
		} else {
			data = getText("auditFailed");
		}
		return TEXT;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: BindYouZanAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = bindYouZanService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = getText("batchSuccess");
		} else {
			data = getText("batchFailed");
		}
		return TEXT;
	}
	
	public BindYouZan getBindYouZan() {
		return bindYouZan;
	}
	public void setBindYouZan(BindYouZan bindYouZan) {
		this.bindYouZan = bindYouZan;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BusinessInfo getBusinessInfo() {
		return businessInfo;
	}
	public void setBusinessInfo(BusinessInfo businessInfo) {
		this.businessInfo = businessInfo;
	}
	public YouZanGoodsInfo getGoodsInfo() {
		return goodsInfo;
	}
	public void setGoodsInfo(YouZanGoodsInfo goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public List<BindYouZan> getBindYouZans() {
		return bindYouZans;
	}

	public void setBindYouZans(List<BindYouZan> bindYouZans) {
		this.bindYouZans = bindYouZans;
	}

	public String getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}

	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
