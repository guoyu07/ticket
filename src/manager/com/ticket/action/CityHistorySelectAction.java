package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CityHistorySelect;
import com.ticket.service.ICityHistorySelectService;
import com.ticket.util.GeneralUtil;

/**
 * 历史选择城市控制器
 * @ClassName: CityHistorySelectAction   
 * @Description:  提供历史选择城市的相关操作方法. 
 * @author HiSay  
 * @date 2015-12-26 17:40:35
 *
 */
public class CityHistorySelectAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//历史选择城市的业务层
	@Resource private ICityHistorySelectService cityHistorySelectService = null;
	//历史选择城市实体
	private CityHistorySelect cityHistorySelect = null;
	//主键
	private String id = null;
    //会员id
	private String member_id = null;
    //城市id
	private String city_id = null;
	
	/**
	 * 添加历史选择城市
	 * @Title: CityHistorySelectAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addCityHistorySelect";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(member_id)) {
				data = AjaxData.responseError(getText("member_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(city_id)) {
				data = AjaxData.responseError(getText("city_id.required"));
				return JSON;
			}
			//保存历史选择城市实体
			boolean isSuc = cityHistorySelectService.persist(member_id, city_id, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				data = AjaxData.responseSuccess(getText("addSuccess"));
			} else {
				data = AjaxData.responseError(getText("addFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 修改历史选择城市
	 * @Title: CityHistorySelectAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setCityHistorySelect(cityHistorySelectService.queryById(CityHistorySelect.class.getSimpleName(), id));
			return "editCityHistorySelect";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(member_id)) {
				data = AjaxData.responseError(getText("member_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(city_id)) {
				data = AjaxData.responseError(getText("city_id.required"));
				return JSON;
			}
			//修改历史选择城市实体
			boolean isSuc = cityHistorySelectService.merge(id, member_id, city_id,  versionFlag);
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
	 * 管理历史选择城市实体
	 * @Title: CityHistorySelectAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(cityHistorySelectService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageCityHistorySelect";
	}
	
	/**
	 * 查看回收站
	 * @Title: CityHistorySelectAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(cityHistorySelectService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleCityHistorySelect";
	}
	
	/**
	 * 逻辑删除历史选择城市对象
	 * @Title: CityHistorySelectAction
	 * @Description: 把历史选择城市对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = cityHistorySelectService.logicDeleteEntity(CityHistorySelect.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除历史选择城市对象
	 * @Title: CityHistorySelectAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = cityHistorySelectService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个历史选择城市对象
	 * @Title: CityHistorySelectAction
	 * @Description: 从回收站还原历史选择城市对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = cityHistorySelectService.restoreEntity(CityHistorySelect.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核历史选择城市对象
	 * @Title: CityHistorySelectAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = cityHistorySelectService.auditEntity(CityHistorySelect.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: CityHistorySelectAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = cityHistorySelectService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public CityHistorySelect getCityHistorySelect() {
		return cityHistorySelect;
	}
	public void setCityHistorySelect(CityHistorySelect cityHistorySelect) {
		this.cityHistorySelect = cityHistorySelect;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
}
