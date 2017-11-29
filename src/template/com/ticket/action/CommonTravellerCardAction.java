package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonTravellerCard;
import com.ticket.service.ICommonTravellerCardService;

/**
 * 常用旅客证件控制器
 * @ClassName: CommonTravellerCardAction   
 * @Description:  提供常用旅客证件的相关操作方法. 
 * @author HiSay  
 * @date 2016-01-07 15:20:41
 *
 */
public class CommonTravellerCardAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//常用旅客证件的业务层
	@Resource protected ICommonTravellerCardService commonTravellerCardService = null;
	//常用旅客证件实体
	protected CommonTravellerCard commonTravellerCard = null;
	//主键
	protected String id = null;
    //证件类型
	protected String cardType = null;
    //证件号码
	protected String cardValue = null;
    //旅客id
	protected String parentId = null;
	//证件类型集合
	protected String valueList = null;
	//证件值集合
	protected String typeList = null;
	//主键集合
	protected String idList = null;
	/**
	 * 添加常用旅客证件
	 * @Title: CommonTravellerCardAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		String[] cardTypes = typeList.split(",");
		String[] cardValues = valueList.split(",");
		for(int i=0;i<cardTypes.length;i++){
			cardType = cardTypes[i];
			cardValue = cardValues[i];
			commonTravellerCardService.persist(cardType, cardValue,
					parentId, versionFlag);
		}
		// 根据保存结果返回页面
		data = AjaxData.responseSuccess(getText("addSuccess"));
		return JSON;
	}
	
	/**
	 * 修改常用旅客证件
	 * @Title: CommonTravellerCardAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		String[] ids = idList.split(",");
		String[] cardTypes = typeList.split(",");
		String[] cardValues = valueList.split(",");
		boolean isSuc = true;
		for(int i=0;i<ids.length;i++){
			id = ids[i];
			cardType = cardTypes[i];
			cardValue = cardValues[i];
			// 修改常用旅客证件实体
			isSuc = commonTravellerCardService.merge(id, cardType,
					cardValue, parentId, versionFlag);
		}
		//如果新增了证件，则插入数据库
		if(cardTypes.length > ids.length){
			for(int i=ids.length;i<cardTypes.length;i++){
				commonTravellerCardService.persist(cardTypes[i], cardValues[i],
						parentId, versionFlag);
			}
		}
		//如果减少了证件？
		//原本已经添加过的证件list
		List<CommonTravellerCard> list = commonTravellerCardService.findByParentId(parentId);
		boolean iss = true;
		if(cardTypes.length < list.size()){//传进来的证件数据比数据库的少
			for(int i=0;i<cardTypes.length;i++){
				for(int j=0;j<list.size();j++){
					String type1 = cardTypes[i];
					String type2 = list.get(j).getCardType();
					if(!type1.equals(type2)){
						iss = false;
					}
					if(type1.equals(type2)){
						iss = true;
						continue;
					}
					if(!iss){
						commonTravellerCardService.remove(list.get(j).getId());
					}
				}
			}
		}
		// 根据修改结果返回页面
		if (!isSuc) {
			data = AjaxData.responseError(getText("editFailed"));
		} else {
			data = AjaxData.responseSuccess(getText("editSuccess"));
		}
		return JSON;
		
	}
	
	/**
	 * 管理常用旅客证件实体
	 * @Title: CommonTravellerCardAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(commonTravellerCardService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageCommonTravellerCard";
	}
	
	/**
	 * 查看回收站
	 * @Title: CommonTravellerCardAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(commonTravellerCardService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleCommonTravellerCard";
	}
	
	/**
	 * 逻辑删除常用旅客证件对象
	 * @Title: CommonTravellerCardAction
	 * @Description: 把常用旅客证件对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = commonTravellerCardService.logicDeleteEntity(CommonTravellerCard.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除常用旅客证件对象
	 * @Title: CommonTravellerCardAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String del() throws ServiceException {
		boolean isSuc = commonTravellerCardService.removeByParentId(parentId);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("delSuccess"));
		} else {
			data = AjaxData.responseError(getText("delFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个常用旅客证件对象
	 * @Title: CommonTravellerCardAction
	 * @Description: 从回收站还原常用旅客证件对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = commonTravellerCardService.restoreEntity(CommonTravellerCard.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核常用旅客证件对象
	 * @Title: CommonTravellerCardAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = commonTravellerCardService.auditEntity(CommonTravellerCard.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: CommonTravellerCardAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = commonTravellerCardService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public CommonTravellerCard getCommonTravellerCard() {
		return commonTravellerCard;
	}
	public void setCommonTravellerCard(CommonTravellerCard commonTravellerCard) {
		this.commonTravellerCard = commonTravellerCard;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardValue() {
		return cardValue;
	}
	public void setCardValue(String cardValue) {
		this.cardValue = cardValue;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getValueList() {
		return valueList;
	}

	public void setValueList(String valueList) {
		this.valueList = valueList;
	}

	public String getTypeList() {
		return typeList;
	}

	public void setTypeList(String typeList) {
		this.typeList = typeList;
	}

	public String getIdList() {
		return idList;
	}

	public void setIdList(String idList) {
		this.idList = idList;
	}
}
