package com.ticket.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.constants.JQueryUploadConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BusinessInfo;
import com.ticket.service.IAirportBusinessTypeService;
import com.ticket.service.IBusinessInfoService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

import net.sf.json.JSONObject;

/**
 * 商家信息控制器
 * @ClassName: BusinessInfoAction   
 * @Description:  提供商家信息的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-16 15:35:54
 *
 */
public class BusinessInfoAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//商家信息的业务层
	@Resource private IBusinessInfoService businessInfoService = null;
	//商家类别的业务层
	@Resource private IAirportBusinessTypeService airportBusinessTypeService = null;
	@Resource private ISystemOperationLogService logService = null;
	//商家信息实体
	private BusinessInfo businessInfo = null;
	//主键
	private String id = null;
    //商业类别id
	private String businessType_id = null;
    //商家名称
	private String name = null;
    //商家简介
	private String introduce = null;
    //主营商品
	private String mainSale = null;
    //营业时间
	private String businessHours = null;
    //活动预告
	private String activityForecast = null;
    //联系电话
	private String phone = null;
    //联系地址
	private String address = null;
    //经度
	private Double longitude = null;
    //纬度
	private Double latitude = null;
	//商户类别树
	private String classTree = null;
	//商户图片
	private String picture = null;
	//排序值
	private Integer orderValue = 0;
	private String lc,wz,fl;
	//关键词
	private String keyword = null;
	//商家评分
	private Integer score;
	//人均价格
	private Double averagePrice;
	//楼层号
	private String floorNumber;
	//登机口
	private String djk;
	
	/**
	 * 绑定有赞商品
	 * @return
	 */
	public String bindYouZanGoods(){
		this.setBusinessInfo(businessInfoService.get(BusinessInfo.class, id));
		return "bindYouZanGoods";
	}
	
	/**
	 * 根据主营商品模糊查询商家信息接口
	 * @return
	 * @throws ServiceException
	 * @throws UnsupportedEncodingException 
	 */
	public String getBusinessInfoMainSaleLike() throws ServiceException, UnsupportedEncodingException{
		keyword = request.getParameter("keyword");
		List<BusinessInfo> infos = businessInfoService.getBusinessInfoMainSaleLike(keyword);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("infos", infos);
		data = jsonObject.toString();
		return TEXT;
	}
	/**
	 * 添加商家信息
	 * @Title: BusinessInfoAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setClassTree(airportBusinessTypeService.queryAirportBusinessTypeSelectOptionHtml(businessType_id, versionFlag));
			return "addBusinessInfo";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(businessType_id)) {
				data = AjaxData.responseError(getText("businessType_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(businessHours)) {
				data = AjaxData.responseError(getText("businessHours.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(address)) {
				data = AjaxData.responseError(getText("address.required"));
				return JSON;
			}
			//保存商家信息实体
			boolean isSuc = businessInfoService.persist(businessType_id, name,
					getSinglePictureUrlFromJQueryUpLoader(fileNames, directory, JQueryUploadConstants.PICTURE_TYPE_DEFAULT),
					introduce, mainSale, businessHours, activityForecast, phone, address, 
					longitude, latitude,orderValue,lc,wz,fl,score,averagePrice,floorNumber,djk, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增商家信息";
				String logName = SystemOparetionLogUtil.getLogName();
				String logTime = SystemOparetionLogUtil.getLogTime();
				String logIp = SystemOparetionLogUtil.getLogIp();
				
				logService.persist(logName, logContent, logTime, logIp, versionFlag);
				data = AjaxData.responseSuccess(getText("addSuccess"));
			} else {
				data = AjaxData.responseError(getText("addFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 修改商家信息
	 * @Title: BusinessInfoAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setBusinessInfo(businessInfoService.queryById(BusinessInfo.class.getSimpleName(), id));
			this.setClassTree(airportBusinessTypeService.queryAirportBusinessTypeSelectOptionHtml(businessType_id, versionFlag));
			return "editBusinessInfo";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(businessType_id)) {
				data = AjaxData.responseError(getText("businessType_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(businessHours)) {
				data = AjaxData.responseError(getText("businessHours.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(address)) {
				data = AjaxData.responseError(getText("address.required"));
				return JSON;
			}
			//修改商家信息实体
			boolean isSuc = businessInfoService.merge(id, businessType_id, name,
					getSinglePictureUrlFromJQueryUpLoader(fileNames, directory, JQueryUploadConstants.PICTURE_TYPE_DEFAULT), 
					introduce, mainSale, businessHours, activityForecast, phone, address, 
					longitude, latitude,orderValue,lc,wz,fl,score,averagePrice,floorNumber,djk, versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改广告信息";
				String logName = SystemOparetionLogUtil.getLogName();
				String logTime = SystemOparetionLogUtil.getLogTime();
				String logIp = SystemOparetionLogUtil.getLogIp();
				
				logService.persist(logName, logContent, logTime, logIp, versionFlag);
				data = AjaxData.responseSuccess(getText("editSuccess"));
			} else {
				data = AjaxData.responseError(getText("editFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 管理商家信息实体
	 * @Title: BusinessInfoAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(businessInfoService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageBusinessInfo";
	}
	
	public String queryByKeyword() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(businessInfoService.queryEntityByKeyword(keyword, ContextConstants.ADMIN_COMMON_PAGE_SIZE,versionFlag));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageBusinessInfo";
	}
	
	public String queryByConditions() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(businessInfoService.queryEntityByConditions(businessType_id,lc,wz, ContextConstants.ADMIN_COMMON_PAGE_SIZE,versionFlag));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageBusinessInfo";
	}
	/**
	 * 查看回收站
	 * @Title: BusinessInfoAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(businessInfoService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleBusinessInfo";
	}
	
	/**
	 * 逻辑删除商家信息对象
	 * @Title: BusinessInfoAction
	 * @Description: 把商家信息对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = businessInfoService.logicDeleteEntity(BusinessInfo.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除商家信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除商家信息对象
	 * @Title: BusinessInfoAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = businessInfoService.remove(id);
		if(isSuc) {
			String logContent = "物理删除商家信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个商家信息对象
	 * @Title: BusinessInfoAction
	 * @Description: 从回收站还原商家信息对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = businessInfoService.restoreEntity(BusinessInfo.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原商家信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核商家信息对象
	 * @Title: BusinessInfoAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = businessInfoService.auditEntity(BusinessInfo.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核商家信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: BusinessInfoAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = businessInfoService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作商家信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	/**
	 * 批量彻底删除商家信息
	 * @return
	 * @throws ServiceException
	 */
	public String batchRealDelete() throws ServiceException {
		boolean isSuc = businessInfoService.batchRealDelete( idsValue,versionFlag);
		if(isSuc) {
			String logContent = "批量删除商家信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	public BusinessInfo getBusinessInfo() {
		return businessInfo;
	}
	public void setBusinessInfo(BusinessInfo businessInfo) {
		this.businessInfo = businessInfo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBusinessType_id() {
		return businessType_id;
	}
	public void setBusinessType_id(String businessType_id) {
		this.businessType_id = businessType_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getMainSale() {
		return mainSale;
	}
	public void setMainSale(String mainSale) {
		this.mainSale = mainSale;
	}
	public String getBusinessHours() {
		return businessHours;
	}
	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}
	public String getActivityForecast() {
		return activityForecast;
	}
	public void setActivityForecast(String activityForecast) {
		this.activityForecast = activityForecast;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getClassTree() {
		return classTree;
	}

	public void setClassTree(String classTree) {
		this.classTree = classTree;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Integer getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(Integer orderValue) {
		this.orderValue = orderValue;
	}

	public String getLc() {
		return lc;
	}

	public void setLc(String lc) {
		this.lc = lc;
	}

	public String getWz() {
		return wz;
	}

	public void setWz(String wz) {
		this.wz = wz;
	}

	public String getFl() {
		return fl;
	}

	public void setFl(String fl) {
		this.fl = fl;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Double getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(Double averagePrice) {
		this.averagePrice = averagePrice;
	}

	public String getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(String floorNumber) {
		this.floorNumber = floorNumber;
	}
	public String getDjk() {
		return djk;
	}
	public void setDjk(String djk) {
		this.djk = djk;
	}
	
}
