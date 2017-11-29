package com.ticket.action;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Member;
import com.ticket.pojo.QuickMenuMemberSetting;
import com.ticket.service.IQuickMenuMemberSettingService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;

/**
 * 会员设置快捷菜单控制器
 * @ClassName: QuickMenuMemberSettingAction   
 * @Description:  提供会员设置快捷菜单的相关操作方法. 
 * @author HiSay  
 * @date 2015-10-31 13:04:17
 *
 */
public class QuickMenuMemberSettingAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//会员设置快捷菜单的业务层
	@Resource private IQuickMenuMemberSettingService quickMenuMemberSettingService = null;
	@Resource private ISystemOperationLogService logService = null;
	//会员设置快捷菜单实体
	private QuickMenuMemberSetting quickMenuMemberSetting = null;
	//主键
	private String id = null;
    //会员id
	private String member_id = null;
    //快捷菜单id
	private String quickMenu_id = null;
	//快捷菜单默认显示的位置
	private String defaultShowPosition = null;
	//服务菜单
	private String flightQuickMenu = null;
	//已设置菜单数量
	private Integer menuCount = 0;
	/**
	 * 添加会员设置快捷菜单
	 * @Title: QuickMenuMemberSettingAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		member_id = member.getId();
		//验证用户设置的菜单数
		if("g".equals(defaultShowPosition)){//判断是不是个人中心
			if(quickMenuMemberSettingService.validateCenterCount(defaultShowPosition,versionFlag)){
				getSession().put(ContextConstants.ISSETQUICKMENU, true);
				data = AjaxData.responseError(getText("reachMax"));
				return JSON;
			}
		}else if(quickMenuMemberSettingService.validateCount(defaultShowPosition,menuCount,flightQuickMenu,versionFlag)){
			getSession().put(ContextConstants.ISSETQUICKMENU, true);
			data = AjaxData.responseError(getText("reachMax"));
			return JSON;
		}
		if(GeneralUtil.isNotNull(flightQuickMenu)&&!"undefined".equals(flightQuickMenu)){
			if(quickMenuMemberSettingService.validateIfSettingFlight(member_id,quickMenu_id,flightQuickMenu,defaultShowPosition)) {
				//data = AjaxData.responseError(getText("settingRepeat"));
				return JSON;
			}
		}else{
			if(quickMenuMemberSettingService.validateIfSetting(member_id,quickMenu_id,defaultShowPosition)) {
				//data = AjaxData.responseError(getText("settingRepeat"));
				return JSON;
			}
		}
		
		if(GeneralUtil.isNull(quickMenu_id)) {
			data = AjaxData.responseError(getText("quickMenu_id.required"));
			return JSON;
		}
		if(GeneralUtil.isNull(defaultShowPosition)){
			defaultShowPosition = null;
		}
		if(GeneralUtil.isNull(flightQuickMenu)){
			flightQuickMenu = null;
		}
		//保存会员设置快捷菜单实体
		boolean isSuc = quickMenuMemberSettingService.persist(member_id, quickMenu_id,flightQuickMenu,defaultShowPosition, versionFlag);
		//根据保存结果返回页面
		if(isSuc) {
			getSession().put(ContextConstants.ISSETQUICKMENU, true);
			data = AjaxData.responseSuccess(getText("addSuccess"));
		} else {
			data = AjaxData.responseError(getText("addFailed"));
		}
		return JSON;
	}
	
	/**
	 * 修改会员设置快捷菜单
	 * @Title: QuickMenuMemberSettingAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setQuickMenuMemberSetting(quickMenuMemberSettingService.queryById(QuickMenuMemberSetting.class.getSimpleName(), id));
			return "editQuickMenuMemberSetting";
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
			if(GeneralUtil.isNull(quickMenu_id)) {
				data = AjaxData.responseError(getText("quickMenu_id.required"));
				return JSON;
			}
			//修改会员设置快捷菜单实体
			boolean isSuc = quickMenuMemberSettingService.merge(id, member_id, quickMenu_id,  versionFlag);
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
	 * 管理会员设置快捷菜单实体
	 * @Title: QuickMenuMemberSettingAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(quickMenuMemberSettingService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageQuickMenuMemberSetting";
	}
	
	/**
	 * 查看回收站
	 * @Title: QuickMenuMemberSettingAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(quickMenuMemberSettingService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleQuickMenuMemberSetting";
	}
	
	/**
	 * 逻辑删除会员设置快捷菜单对象
	 * @Title: QuickMenuMemberSettingAction
	 * @Description: 把会员设置快捷菜单对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = quickMenuMemberSettingService.logicDeleteEntity(QuickMenuMemberSetting.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除会员设置快捷菜单对象
	 * @Title: QuickMenuMemberSettingAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = quickMenuMemberSettingService.remove(id);
		if(isSuc) {
			getSession().put(ContextConstants.ISSETQUICKMENU, true);
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个会员设置快捷菜单对象
	 * @Title: QuickMenuMemberSettingAction
	 * @Description: 从回收站还原会员设置快捷菜单对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = quickMenuMemberSettingService.restoreEntity(QuickMenuMemberSetting.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核会员设置快捷菜单对象
	 * @Title: QuickMenuMemberSettingAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = quickMenuMemberSettingService.auditEntity(QuickMenuMemberSetting.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: QuickMenuMemberSettingAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = quickMenuMemberSettingService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	/**
	 * 根据会员ID、菜单ID和位置获取是否已设置过
	 * @return
	 * @throws ServiceException
	 */
	public String queryCurrentStatus() throws ServiceException {
		QuickMenuMemberSetting obj = quickMenuMemberSettingService.queryByMenuIdAndPosition(quickMenu_id,defaultShowPosition);//是否设置过
		JSONObject json = new JSONObject();
		if(obj != null) {
			json.put("flag", "1");
			json.put("memberMenuId", obj.getId());
			getSession().put(ContextConstants.ISSETQUICKMENU, true);
			data = json.toString();
		} else {
			json.put("flag","0");
			getSession().put(ContextConstants.ISSETQUICKMENU, true);
			data = json.toString();
		}
		return TEXT;
	}
	
	public QuickMenuMemberSetting getQuickMenuMemberSetting() {
		return quickMenuMemberSetting;
	}
	public void setQuickMenuMemberSetting(QuickMenuMemberSetting quickMenuMemberSetting) {
		this.quickMenuMemberSetting = quickMenuMemberSetting;
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
	public String getQuickMenu_id() {
		return quickMenu_id;
	}
	public void setQuickMenu_id(String quickMenu_id) {
		this.quickMenu_id = quickMenu_id;
	}

	public String getDefaultShowPosition() {
		return defaultShowPosition;
	}

	public void setDefaultShowPosition(String defaultShowPosition) {
		this.defaultShowPosition = defaultShowPosition;
	}

	public String getFlightQuickMenu() {
		return flightQuickMenu;
	}

	public void setFlightQuickMenu(String flightQuickMenu) {
		this.flightQuickMenu = flightQuickMenu;
	}

	public Integer getMenuCount() {
		return menuCount;
	}

	public void setMenuCount(Integer menuCount) {
		this.menuCount = menuCount;
	}
}
