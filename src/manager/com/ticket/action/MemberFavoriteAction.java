package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Member;
import com.ticket.pojo.MemberFavorite;
import com.ticket.service.IMemberFavoriteService;
import com.ticket.util.GeneralUtil;

/**
 * 会员收藏控制器
 * @ClassName: MemberFavoriteAction   
 * @Description:  提供会员收藏的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-13 09:59:42
 *
 */
public class MemberFavoriteAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//会员收藏的业务层
	@Resource private IMemberFavoriteService memberFavoriteService = null;
	//会员收藏实体
	private MemberFavorite memberFavorite = null;
	//主键
	private String id = null;
    //会员id
	private String member_id = null;
    //收藏对象id
	private String objectId = null;
    //收藏类型
	private String objectType = null;
    //收藏标题
	private String title = null;
    //收藏链接
	private String url = null;
	
	/**
	 * 添加会员收藏
	 * @Title: MemberFavoriteAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addMemberFavorite";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(member_id)) {
				data = AjaxData.responseError(getText("member_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(objectId)) {
				data = AjaxData.responseError(getText("objectId.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(objectType)) {
				data = AjaxData.responseError(getText("objectType.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(title)) {
				data = AjaxData.responseError(getText("Title.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(url)) {
				data = AjaxData.responseError(getText("url.required"));
				return JSON;
			}
			//保存会员收藏实体
			boolean isSuc = memberFavoriteService.persist(member_id, objectId, objectType, title, url, versionFlag);
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
	 * 修改会员收藏
	 * @Title: MemberFavoriteAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setMemberFavorite(memberFavoriteService.queryById(MemberFavorite.class.getSimpleName(), id));
			return "editMemberFavorite";
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
			if(GeneralUtil.isNull(objectId)) {
				data = AjaxData.responseError(getText("objectId.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(objectType)) {
				data = AjaxData.responseError(getText("objectType.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(title)) {
				data = AjaxData.responseError(getText("Title.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(url)) {
				data = AjaxData.responseError(getText("url.required"));
				return JSON;
			}
			//修改会员收藏实体
			boolean isSuc = memberFavoriteService.merge(id, member_id, objectId, objectType, title, url,  versionFlag);
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
	 * 管理会员收藏实体
	 * @Title: MemberFavoriteAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(memberFavoriteService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageMemberFavorite";
	}
	
	/**
	 * 查看回收站
	 * @Title: MemberFavoriteAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(memberFavoriteService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleMemberFavorite";
	}
	
	/**
	 * 逻辑删除会员收藏对象
	 * @Title: MemberFavoriteAction
	 * @Description: 把会员收藏对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = memberFavoriteService.logicDeleteEntity(MemberFavorite.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除会员收藏对象
	 * @Title: MemberFavoriteAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = memberFavoriteService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个会员收藏对象
	 * @Title: MemberFavoriteAction
	 * @Description: 从回收站还原会员收藏对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = memberFavoriteService.restoreEntity(MemberFavorite.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核会员收藏对象
	 * @Title: MemberFavoriteAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = memberFavoriteService.auditEntity(MemberFavorite.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: MemberFavoriteAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = memberFavoriteService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	/**
	 * @Description：判断是否收藏
	 * @date 2015年12月15日 下午8:06:07
	 * @return
	 * @throws ServiceException 
	 */
	public String isFavorite() throws ServiceException{
		
		Member member = (Member)getSession().get(ContextConstants.SCOPE_MEMBER);
		if(member == null){
			
			data = AjaxData.responseError("");
			
		}else if(GeneralUtil.isNotNull(objectId)){
			
			if(memberFavoriteService.queryByObjectId(objectId, versionFlag) != null){
				
				data = AjaxData.responseSuccess("");
			}else{
				
				data = AjaxData.responseError("");
			}
		}else if(GeneralUtil.isNotNull(url)){
			
			if(memberFavoriteService.queryByTitleAndUrl(title, url, versionFlag) != null){
				
				data = AjaxData.responseSuccess("");
			}else{
				
				data = AjaxData.responseError("");
			}
		}
		return JSON;
	}
	
	public MemberFavorite getMemberFavorite() {
		return memberFavorite;
	}
	public void setMemberFavorite(MemberFavorite memberFavorite) {
		this.memberFavorite = memberFavorite;
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
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String Title) {
		this.title = Title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
