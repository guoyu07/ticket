package com.ticket.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Infringement;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.Regulation;
import com.ticket.pojo.RegulationType;
import com.ticket.service.IInfringementService;
import com.ticket.service.IRegulationService;
import com.ticket.service.IRegulationTypeService;
import com.ticket.util.GeneralUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 内部员工罚单控制器
 * @ClassName: InfringementAction   
 * @Description:  提供内部员工罚单的相关操作方法. 
 * @author HiSay  
 * @date 2016-03-04 19:58:20
 *
 */
public class InfringementAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//内部员工罚单的业务层
	@Resource private IInfringementService infringementService = null;
	@Resource private IRegulationTypeService typeService = null;
	@Resource private IRegulationService regulationService = null;
	//内部员工罚单实体
	private Infringement infringement = null;
	//主键
	private String id = null;
    //时间
	private String time = null;
    //检查人
	private String inspectName = null;
    //单位名称
	private String unitName = null;
    //违规事项
	private String illegalMatter = null;
    //涉及规章制度
	private String rules = null;
    //整改意见
	private String rectificationOpinion = null;
    //现场照片
	private String scenePhoto = null;
    //罚单编号
	private String numberId = null;
    //违规人姓名
	private String illegalName = null;
    //违规人证件号
	private String illegalCard = null;
	
	/**
	 * 添加内部员工罚单
	 * @Title: InfringementAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			Long count = infringementService.infringementCount();
			ActionContext.getContext().put("count", count+1);
			StringBuffer sb = new StringBuffer();
			//得到所有规章制度类型
			List<RegulationType> types = typeService.queryAll(RegulationType.class);
			//根据类型找到所有的规章制度
			sb.append("[");
			for(RegulationType type: types){
				List<Regulation> regulations = regulationService.queryListByType(type.getId(), versionFlag);
					sb.append("{").append("\"").append("n").append("\"").append(":").append("\"")
					.append(type.getName()).append("\"").append(",").append("\"").append("s").append("\"")
					.append(":[");
					if(GeneralUtil.isNotNull(regulations)){
						for(Regulation regulation: regulations){
							sb.append("{");
							sb.append("\"").append("n").append("\"").append(":")
							.append("\"").append(regulation.getTitle()).append("\"")
							.append("}").append(",");
						}
					}else{
						sb.append("{");
						sb.append("\"").append("n").append("\"").append(":")
						.append("\"").append("").append("\"")
						.append("}").append(",");
					}
					sb.deleteCharAt(sb.length() - 1);
					sb.append("]},");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
			
			ActionContext.getContext().put("json", sb.toString());
			
			return "addInfringement";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(time)) {
				data = AjaxData.responseError(getText("time.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(inspectName)) {
				data = AjaxData.responseError(getText("inspectName.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(unitName)) {
				data = AjaxData.responseError(getText("unitName.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(numberId)) {
				data = AjaxData.responseError(getText("numberId.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(illegalName)) {
				data = AjaxData.responseError(getText("illegalName.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(illegalCard)) {
				data = AjaxData.responseError(getText("illegalCard.required"));
				return JSON;
			}
			//保存内部员工罚单实体
			boolean isSuc = infringementService.persist(time, inspectName, unitName, illegalMatter, rules, rectificationOpinion, scenePhoto, numberId, illegalName, illegalCard, versionFlag);
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
	 * 获取所有规章制度类型
	 * @return
	 * @throws ServiceException
	 */
	public String getRegulation() throws ServiceException{
		//得到所有规章制度类型
		List<RegulationType> types = typeService.queryAll(RegulationType.class);
		//根据类型找到所有的规章制度
		JSONArray sb = new JSONArray();
		for(RegulationType type: types){
			List<Regulation> regulations = regulationService.queryListByType(type.getId(), versionFlag);
			
			JSONObject obj = new JSONObject();
			obj.put("n", type.getName());
			
			JSONArray subArray = new JSONArray();
			if(GeneralUtil.isNotNull(regulations)){
				for(Regulation regulation: regulations){
					
					JSONObject subObject = new JSONObject();
					subObject.put("n", regulation.getTitle());
					subObject.put("n", regulation.getTitle());
					subArray.add(subObject);
				}
			}
			obj.put("s", subArray);
			sb.add(obj);
		}
		
		data = sb.toString();
		return TEXT;
	}
	/**
	 * 修改内部员工罚单
	 * @Title: InfringementAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setInfringement(infringementService.queryById(Infringement.class.getSimpleName(), id));
			return "editInfringement";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(time)) {
				data = AjaxData.responseError(getText("time.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(inspectName)) {
				data = AjaxData.responseError(getText("inspectName.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(unitName)) {
				data = AjaxData.responseError(getText("unitName.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(illegalMatter)) {
				data = AjaxData.responseError(getText("illegalMatter.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(rules)) {
				data = AjaxData.responseError(getText("rules.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(rectificationOpinion)) {
				data = AjaxData.responseError(getText("rectificationOpinion.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(scenePhoto)) {
				data = AjaxData.responseError(getText("scenePhoto.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(numberId)) {
				data = AjaxData.responseError(getText("numberId.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(illegalName)) {
				data = AjaxData.responseError(getText("illegalName.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(illegalCard)) {
				data = AjaxData.responseError(getText("illegalCard.required"));
				return JSON;
			}
			//修改内部员工罚单实体
			boolean isSuc = infringementService.merge(id, time, inspectName, unitName, illegalMatter, rules, rectificationOpinion, scenePhoto, numberId, illegalName, illegalCard,  versionFlag);
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
	 * 管理内部员工罚单实体
	 * @Title: InfringementAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		Pagination paging = infringementService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		if(paging != null){
			
			List list = paging.getPageList();
			Set<String> times = new HashSet<String>();
			Set<String> unitNames = new HashSet<String>();
			Set<String> illegalNames = new HashSet<String>();
			for(Object o : list){
				
				Infringement i = (Infringement)o;
				times.add(i.getTime());
				unitNames.add(i.getUnitName());
				illegalNames.add(i.getIllegalName());
			}
			ActionContext.getContext().put("times", times);
			ActionContext.getContext().put("unitNames", unitNames);
			ActionContext.getContext().put("illegalNames", illegalNames);
			
		}
		this.setPageModule(paging);
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageInfringement";
	}
	
	/**
	 * 查看罚单具体信息
	 * @return
	 * @throws ServiceException
	 */
	public String look() throws ServiceException{
		Infringement infringement = infringementService.findById(id);
		ActionContext.getContext().put("infringement", infringement);
		return "lookInfringement";
	}
	/**
	 * 根据时间，单位名称，被罚人员名称查找罚单信息
	 * @return
	 * @throws ServiceException
	 */
	public String findByTime() throws ServiceException{
		JSONArray array = new JSONArray();
		List<Infringement> list = new ArrayList<Infringement>();
		list = infringementService.findByTimeAndUnitNameAndIllegalName(time,unitName,illegalName);
		for(Infringement infringement: list){
			array.add(infringement);
		}
		data = AjaxData.responseSuccess(array);
		return JSON;
	}
	
	/**
	 * 查看回收站
	 * @Title: InfringementAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(infringementService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleInfringement";
	}
	
	/**
	 * 逻辑删除内部员工罚单对象
	 * @Title: InfringementAction
	 * @Description: 把内部员工罚单对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = infringementService.logicDeleteEntity(Infringement.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除内部员工罚单对象
	 * @Title: InfringementAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = infringementService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个内部员工罚单对象
	 * @Title: InfringementAction
	 * @Description: 从回收站还原内部员工罚单对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = infringementService.restoreEntity(Infringement.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核内部员工罚单对象
	 * @Title: InfringementAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = infringementService.auditEntity(Infringement.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: InfringementAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = infringementService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public Infringement getInfringement() {
		return infringement;
	}
	public void setInfringement(Infringement infringement) {
		this.infringement = infringement;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getInspectName() {
		return inspectName;
	}
	public void setInspectName(String inspectName) {
		this.inspectName = inspectName;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getIllegalMatter() {
		return illegalMatter;
	}
	public void setIllegalMatter(String illegalMatter) {
		this.illegalMatter = illegalMatter;
	}
	public String getRules() {
		return rules;
	}
	public void setRules(String rules) {
		this.rules = rules;
	}
	public String getRectificationOpinion() {
		return rectificationOpinion;
	}
	public void setRectificationOpinion(String rectificationOpinion) {
		this.rectificationOpinion = rectificationOpinion;
	}
	public String getScenePhoto() {
		return scenePhoto;
	}
	public void setScenePhoto(String scenePhoto) {
		this.scenePhoto = scenePhoto;
	}
	public String getNumberId() {
		return numberId;
	}
	public void setNumberId(String numberId) {
		this.numberId = numberId;
	}
	public String getIllegalName() {
		return illegalName;
	}
	public void setIllegalName(String illegalName) {
		this.illegalName = illegalName;
	}
	public String getIllegalCard() {
		return illegalCard;
	}
	public void setIllegalCard(String illegalCard) {
		this.illegalCard = illegalCard;
	}
}
