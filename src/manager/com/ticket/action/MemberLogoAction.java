package com.ticket.action;

import java.io.File;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.MemberLogo;
import com.ticket.service.IMemberLogoService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 推荐会员头像控制器
 * @ClassName: MemberLogoAction   
 * @Description:  提供推荐会员头像的相关操作方法. 
 * @author HiSay  
 * @date 2016-03-14 10:49:59
 *
 */
public class MemberLogoAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//推荐会员头像的业务层
	@Resource private IMemberLogoService memberLogoService = null;
	
	@Resource private ISystemOperationLogService logService = null;
	//推荐会员头像实体
	private MemberLogo memberLogo = null;
	//主键
	private String id = null;
    //头像路径
	private String logoUrl = null;
	
	private String[] fileNames = null;
	
	/**
	 * 添加推荐会员头像
	 * @Title: MemberLogoAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addMemberLogo";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(logoUrl)) {
				data = AjaxData.responseError("请上传再提交");
				return JSON;
			}
			//保存推荐会员头像实体
			boolean isSuc = memberLogoService.persist(logoUrl, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增推荐会员头像";
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
	 * 修改推荐会员头像
	 * @Title: MemberLogoAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setMemberLogo(memberLogoService.queryById(MemberLogo.class.getSimpleName(), id));
			return "editMemberLogo";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(logoUrl)) {
				data = AjaxData.responseError(getText("logoUrl.required"));
				return JSON;
			}
			//修改推荐会员头像实体
			boolean isSuc = memberLogoService.merge(id, logoUrl,  versionFlag);
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
	 * 管理推荐会员头像实体
	 * @Title: MemberLogoAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(memberLogoService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageMemberLogo";
	}
	
	/**
	 * 查看回收站
	 * @Title: MemberLogoAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(memberLogoService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleMemberLogo";
	}
	
	/**
	 * 逻辑删除推荐会员头像对象
	 * @Title: MemberLogoAction
	 * @Description: 把推荐会员头像对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = memberLogoService.logicDeleteEntity(MemberLogo.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除推荐会员头像对象
	 * @Title: MemberLogoAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		MemberLogo logo = memberLogoService.queryById(MemberLogo.class.getName(), id);
		logoUrl = logo.getLogoUrl();
		String path = ServletActionContext.getRequest().getRealPath("");
		String thumbnail = ServletActionContext.getRequest().getRealPath("/upload/thumbnail/") + "/";
		int index = logoUrl.indexOf("/", 2);
		String thumbnailLogoUrl = logoUrl.substring(index+1);
		
		String thumbnailUrl = thumbnail + "thumbnail" +thumbnailLogoUrl;
		String url = path + logoUrl;
		File file = new File(url);
		File file2 = new File(thumbnailUrl);
		boolean isSuc = memberLogoService.remove(id);
		if(isSuc) {//如果删除成功，则应该讲服务器对应的图片也删除
			if(file.exists()){
				file.delete();
			}
			if(file2.exists()){
				file2.delete();
			}
			String logContent = "删除推荐会员头像";
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
	 * 还原一个推荐会员头像对象
	 * @Title: MemberLogoAction
	 * @Description: 从回收站还原推荐会员头像对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = memberLogoService.restoreEntity(MemberLogo.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核推荐会员头像对象
	 * @Title: MemberLogoAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = memberLogoService.auditEntity(MemberLogo.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: MemberLogoAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = memberLogoService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	/**
	 * 批量删除会员头像推荐
	 */
	public String batchRealDelete() throws ServiceException{
		String[] ids = idsValue.split(",");
		String path = ServletActionContext.getRequest().getRealPath("");
		String thumbnail = ServletActionContext.getRequest().getRealPath("/upload/thumbnail/") + "/";
		for(int i=0;i<ids.length;i++){
			MemberLogo logo = memberLogoService.queryById(MemberLogo.class.getName(), ids[i]);
			String logoUrl= logo.getLogoUrl();
			int index = logoUrl.indexOf("/", 2);
			String thumbnailLogoUrl = logoUrl.substring(index+1);
			
			String thumbnailUrl = thumbnail + "thumbnail" +thumbnailLogoUrl;
			String url = path + logoUrl;
			File file = new File(url);
			File file2 = new File(thumbnailUrl);
			if(file.exists()){
				file.delete();
			}
			if(file2.exists()){
				file2.delete();
			}
		}
		boolean isSuc = memberLogoService.batchRealDelete(idsValue, versionFlag);
		if(isSuc){
			String logContent = "批量删除会员头像推荐";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess("批量删除成功");
		}else{
			data = AjaxData.responseError("批量删除失败");
		}
		return JSON;
	}
	
	public MemberLogo getMemberLogo() {
		return memberLogo;
	}
	public void setMemberLogo(MemberLogo memberLogo) {
		this.memberLogo = memberLogo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String[] getFileNames() {
		return fileNames;
	}

	public void setFileNames(String[] fileNames) {
		this.fileNames = fileNames;
	}
}
