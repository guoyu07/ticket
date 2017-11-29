package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.service.IGenerateTemplateCodeService;
import com.ticket.util.GeneralUtil;

/**
 * 创建模板代码控制器
 * @Description:创建一个实体Action具备的基本方法和属性等.   
 * @author HiSay  
 * @date Jul 31, 2013 3:10:11 PM   
 *
 */
public class GenerateTemplateCodeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Resource private IGenerateTemplateCodeService generateTemplateCodeService = null;
	
	/**
	 * 实体名称例如:Module,System,Message
	 */
	private String entityName = null;
	/**
	 * 实体说明:例如模块,留言,产品
	 */
	private String entityTitle = null;
	/**
	 * 实体描述
	 */
	private String entityDescript = null;
	/**
	 * 实体属性
	 */
	private String entityAttribute = null;
	
	/**
	 * 是否删除对应的Action
	 */
	private Integer isDeleteAction = null;
	/**
	 * 是否删除对应的业务接口层
	 */
	private Integer isDeleteService = null;
	/**
	 * 是否删除对应的业务接口实现层
	 */
	private Integer isDeleteServiceImpl = null;
	/**
	 * 是否删除对应的JS
	 */
	private Integer isDeleteJs = null;
	/**
	 * 是否删除对应的JSP
	 */
	private Integer isDeleteJsp = null;
	/**
	 * 是否删除对应的Message_zh_CN.properties文件
	 */
	private Integer isDeleteMessage = null;
	/**
	 * 是否删除对应的POJO
	 */
	private Integer isDeletePojo = null;
	/**
	 * 生成代码的保存路径
	 */
	private String savePath = null;
	/**
	 * 实体ID的类型
	 */
	private String pojoIdType = null;
	
	public String getPojoIdType() {
		return pojoIdType;
	}

	public void setPojoIdType(String pojoIdType) {
		this.pojoIdType = pojoIdType;
	}

	/**
	 * 创建模板代码方法
	 * @Title: GenerateTemplateCodeAction
	 * @Description:根据页面提交的相关参数创建模板.   
	 * @param @return
	 * @param @throws Exception    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String add() throws Exception {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addTemplateCode";
		}
		boolean isSuc = generateTemplateCodeService.generateJavaCode(entityName, entityTitle, entityDescript, entityAttribute, request, 
				response, savePath, pojoIdType);
		//根据保存结果返回页面
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("addSuccess"));
		} else {
			data = AjaxData.responseError(getText("addFailed"));
		}
		return JSON;
	}
	
	/**
	 * 根据实体名称删除已经创建的java,js和jsp代码
	 * @Title: GenerateTemplateCodeAction
	 * @param @return
	 * @param @throws Exception    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String delete() throws Exception {
		if(GeneralUtil.isNull(operationFlag)) {
			return "deleteTemplateCode";
		} else {
			boolean isSuc = generateTemplateCodeService.delete(entityName, isDeleteAction, isDeleteService, isDeleteServiceImpl, 
					        isDeleteJs, isDeleteJsp, isDeleteMessage, isDeletePojo, savePath);
			//根据保存结果返回页面
			if(isSuc) {
				data = AjaxData.responseSuccess(getText("delSuccess"));
			} else {
				data = AjaxData.responseError(getText("delFailed"));
			}
			return JSON;
		}
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityTitle() {
		return entityTitle;
	}

	public void setEntityTitle(String entityTitle) {
		this.entityTitle = entityTitle;
	}

	public String getEntityDescript() {
		return entityDescript;
	}

	public void setEntityDescript(String entityDescript) {
		this.entityDescript = entityDescript;
	}

	public String getEntityAttribute() {
		return entityAttribute;
	}

	public void setEntityAttribute(String entityAttribute) {
		this.entityAttribute = entityAttribute;
	}

	public Integer getIsDeleteAction() {
		return isDeleteAction;
	}

	public void setIsDeleteAction(Integer isDeleteAction) {
		this.isDeleteAction = isDeleteAction;
	}

	public Integer getIsDeleteService() {
		return isDeleteService;
	}

	public void setIsDeleteService(Integer isDeleteService) {
		this.isDeleteService = isDeleteService;
	}

	public Integer getIsDeleteServiceImpl() {
		return isDeleteServiceImpl;
	}

	public void setIsDeleteServiceImpl(Integer isDeleteServiceImpl) {
		this.isDeleteServiceImpl = isDeleteServiceImpl;
	}

	public Integer getIsDeleteJs() {
		return isDeleteJs;
	}

	public void setIsDeleteJs(Integer isDeleteJs) {
		this.isDeleteJs = isDeleteJs;
	}

	public Integer getIsDeleteJsp() {
		return isDeleteJsp;
	}

	public void setIsDeleteJsp(Integer isDeleteJsp) {
		this.isDeleteJsp = isDeleteJsp;
	}

	public Integer getIsDeleteMessage() {
		return isDeleteMessage;
	}

	public void setIsDeleteMessage(Integer isDeleteMessage) {
		this.isDeleteMessage = isDeleteMessage;
	}

	public Integer getIsDeletePojo() {
		return isDeletePojo;
	}

	public void setIsDeletePojo(Integer isDeletePojo) {
		this.isDeletePojo = isDeletePojo;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	
}
