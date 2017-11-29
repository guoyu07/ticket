package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonTraveller;
import com.ticket.pojo.CommonTravellerCard;
import com.ticket.pojo.Member;
import com.ticket.pojo.SystemDictionary;
import com.ticket.service.ICommonTravellerCardService;
import com.ticket.service.ICommonTravellerService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.util.GeneralUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 常用旅客控制器
 * 
 * @ClassName: CommonTravellerAction
 * @Description: 提供常用旅客的相关操作方法.
 * @author HiSay
 * @date 2016-01-05 16:30:37
 * 
 */
public class CommonTravellerAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	// 常用旅客的业务层
	@Resource
	protected ICommonTravellerService commonTravellerService;
	@Resource
	protected ISystemDictionaryService dictionaryService;
	@Resource
	protected ICommonTravellerCardService commonTravellerCardService;

	// 常用旅客实体
	protected CommonTraveller commonTraveller;
	protected List<?> list;
	// 主键
	protected String id;
	// 中文名
	protected String chaName;
	// 英文名
	protected String engName;
	// 手机号
	protected String phone;
	// 性别
	protected String gender;
	// 生日
	protected String birth;
	// 会员ID
	protected String memberId;

	protected String typeList;

	protected String valueList;

	/**
	 * 进入添加常用旅客页面
	 * 
	 * @return
	 */
	public String add() {
		List<SystemDictionary> dicts = dictionaryService
				.querySubByParentName("证件类型");
		ActionContext.getContext().put("type", dicts);
		return "addCommonTraveller";
	}

	/**
	 * 添加常用旅客
	 * 
	 * @Title: CommonTravellerAction
	 * @Description:
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String addTraveller() throws ServiceException {
		if (GeneralUtil.isNull(chaName) && GeneralUtil.isNull(engName)) {
			data = AjaxData.responseError(getText("cNameAndeName"));
			return JSON;
		}
		if (GeneralUtil.isNull(engName)) {
			engName = null;
		}
		if (GeneralUtil.isNull(chaName)) {
			chaName = null;
		}
		// 非空验证
		if (GeneralUtil.isNull(phone)) {
			data = AjaxData.responseError(getText("phone.required"));
			return JSON;
		}

		if (GeneralUtil.isNull(memberId)) {
			Member member = (Member) ActionContext.getContext().getSession()
					.get(ContextConstants.SCOPE_MEMBER);
			memberId = member.getId();
		}
		List<CommonTraveller> tras = commonTravellerService
				.getByMemberId(memberId);
		String[] valueLists = valueList.split(",");
		CommonTravellerCard card = null;
		for (String cardValue : valueLists) {
			for (CommonTraveller c : tras) {
				card = commonTravellerCardService.findByParentIdAndIdCard(
						c.getId(), cardValue);
				if (card != null) {
					data = AjaxData.responseError("此证件号码的信息已存在");
					return JSON;
				}
			}
			CommonTraveller isSuc = null;
			if (chaName != null) {
				CommonTraveller traveller = commonTravellerService
						.findByMemberIdAndName(memberId, chaName);
				if (traveller != null) {
					data = AjaxData.responseError(getText("already"));
				} else {
					// 保存常用旅客实体
					isSuc = commonTravellerService.persist(chaName, engName,
							phone, gender, birth, memberId, versionFlag);
				}
			}
			if (engName != null && chaName == null) {
				CommonTraveller traveller = commonTravellerService
						.findByMemberIdAndName(memberId, engName);
				if (traveller != null) {
					data = AjaxData.responseError(getText("already"));
				} else {
					// 保存常用旅客实体
					isSuc = commonTravellerService.persist(chaName, engName,
							phone, gender, birth, memberId, versionFlag);
				}
			}

			// 根据保存结果返回页面
			if (isSuc != null) {
				// List<CommonTraveller> travellers = new
				// ArrayList<CommonTraveller>();
				List<CommonTraveller> list = commonTravellerService
						.getByMemberId(memberId);
				// for(Object o: list){
				// CommonTraveller traveller = (CommonTraveller)o;
				// travellers.add(traveller);
				// }
				CommonTraveller c = list.get(0);
				data = AjaxData.responseSuccess(JSONObject
						.fromObject(c).toString());
			}
		}

		return JSON;
	}

	/**
	 * 常用旅客列表页面
	 * 
	 * @return
	 */
	public String showCommonTraveller() {
		return "showCommonTraveller";
	}

	/**
	 * 常用旅客列表页面
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public String show() throws ServiceException {
//		Member member = (Member) ActionContext.getContext().getSession()
//				.get(ContextConstants.SCOPE_MEMBER);
//		String memberId = member.getId();
//		// List<CommonTraveller> travellers = new ArrayList<CommonTraveller>();
//		List<CommonTraveller> list = commonTravellerService
//				.getByMemberId(memberId);
//		// for(Object o: list){
//		// CommonTraveller traveller = (CommonTraveller)o;
//		// travellers.add(traveller);
//		// }
//		List<CommonTravellerCard> list1 = new ArrayList<CommonTravellerCard>();
//		for (CommonTraveller c : list) {
//			String id = c.getId();
//			List<CommonTravellerCard> cards = commonTravellerCardService
//					.findByParentId(id);
//			for (CommonTravellerCard card : cards) {
//				list1.add(card);
//			}
//		}
//		if (list != null && list.size() > 0) {
//			ActionContext.getContext().put("travellerList", list);
//		}else{
//			ActionContext.getContext().put("travellerList", null);
//		}
//		if (list1 != null && list1.size() > 0) {
//			ActionContext.getContext().put("cardList", list1);
//		}else{
//			ActionContext.getContext().put("cardList", null);
//		}
		return "showCommonTraveller";
	}

	/**
	 * 简单列表
	 * 
	 * @return
	 */
	public String simpleList() {

		try {
			list = commonTravellerService.getByMemberId(getSessionMember()
					.getId());
			request.setAttribute("list", list);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "simpleList";
	}

	/**
	 * 修改常用旅客
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public String editCommon() throws ServiceException {
		Member member = (Member) ActionContext.getContext().getSession()
				.get(ContextConstants.SCOPE_MEMBER);
		memberId = member.getId();
		CommonTraveller traveller = commonTravellerService.findById(id,
				memberId);

		List<CommonTravellerCard> list = commonTravellerCardService
				.findByParentId(id);
		ActionContext.getContext().put("cardList", list);

		List<SystemDictionary> dicts = dictionaryService
				.querySubByParentName("证件类型");
		ActionContext.getContext().put("type", dicts);

		ActionContext.getContext().put("traveller", traveller);
		return "editCommonTraveller";
	}

	/**
	 * 修改常用旅客
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public String xiugai() throws ServiceException {
		boolean suc = commonTravellerService.merge(id, chaName, engName, phone,
				gender, birth, memberId, birth);
		if (suc) {
			data = AjaxData.responseSuccess(getText("editSuccess"));
		} else {
			data = AjaxData.responseError(getText("editFailed"));
		}
		return JSON;
	}

	/**
	 * 获取当前用户常用旅客list
	 * 
	 * @return
	 */
	public String traverList() throws ServiceException {

		List<CommonTraveller> list = commonTravellerService
				.getByMemberId(getSessionMember().getId());

		JSONArray jsonArray = new JSONArray();
		for (CommonTraveller traveller : list) {

			JSONArray subJsonArray = new JSONArray();
			subJsonArray.add(traveller.getName());

			List<CommonTravellerCard> cards = commonTravellerCardService
					.findByParentId(traveller.getId());
			for (CommonTravellerCard card : cards) {

				if ("身份证".equals(card.getCardType())) {

					subJsonArray.add(card.getCardValue());
				}
			}
			jsonArray.add(subJsonArray);
		}

		data = jsonArray.toString();
		return TEXT;
	}

	/**
	 * 管理常用旅客实体
	 * 
	 * @Title: CommonTravellerAction
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0]
				.getMethodName());
		this.setPageModule(commonTravellerService.queryEntityByAdmin(
				versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageCommonTraveller";
	}

	/**
	 * 查看回收站
	 * @Title: CommonTravellerAction
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(commonTravellerService.queryRecycleEntity(
				versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleCommonTraveller";
	}

	/**
	 * 逻辑删除常用旅客对象
	 * 
	 * @Title: CommonTravellerAction
	 * @Description: 把常用旅客对象放入回收站.
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = commonTravellerService.logicDeleteEntity(
				CommonTraveller.class.getSimpleName(), id);
		if (isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}

	/**
	 * 物理删除常用旅客对象
	 * 
	 * @Title: CommonTravellerAction
	 * @Description: 把配置对象实际从数据库里面删除.
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String del() throws ServiceException {
		List<CommonTravellerCard> cards = commonTravellerCardService
				.findByParentId(id);
		if (GeneralUtil.isNotNull(cards)) {
			for (CommonTravellerCard card : cards) {
				commonTravellerCardService.remove(card);
			}
		}

		boolean isSuc = commonTravellerService.remove(id);
		if (isSuc) {
			data = AjaxData.responseSuccess(getText("delSuccess"));
		} else {
			data = AjaxData.responseError(getText("delFailed"));
		}
		return JSON;
	}

	/**
	 * 还原一个常用旅客对象
	 * 
	 * @Title: CommonTravellerAction
	 * @Description: 从回收站还原常用旅客对象
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = commonTravellerService.restoreEntity(
				CommonTraveller.class.getSimpleName(), id);
		if (isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}

	/**
	 * 审核常用旅客对象
	 * 
	 * @Title: CommonTravellerAction
	 * @Description:
	 * @param @return
	 * @param @throws ServiceException 设定文件
	 * @return 返回类型
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = commonTravellerService.auditEntity(
				CommonTraveller.class.getSimpleName(), id, statusValue);
		if (isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}

	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * 
	 * @Title: CommonTravellerAction
	 * @Description:
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = commonTravellerService.batchOperationEntity(
				versionFlag, idsValue, batchOperationType, isChecked);
		if (isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}

	public CommonTraveller getCommonTraveller() {
		return commonTraveller;
	}

	public void setCommonTraveller(CommonTraveller commonTraveller) {
		this.commonTraveller = commonTraveller;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChaName() {
		return chaName;
	}

	public void setChaName(String chaName) {
		this.chaName = chaName;
	}

	public String getEngName() {
		return engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getTypeList() {
		return typeList;
	}

	public void setTypeList(String typeList) {
		this.typeList = typeList;
	}

	public String getValueList() {
		return valueList;
	}

	public void setValueList(String valueList) {
		this.valueList = valueList;
	}

	public ICommonTravellerService getCommonTravellerService() {
		return commonTravellerService;
	}

	public void setCommonTravellerService(
			ICommonTravellerService commonTravellerService) {
		this.commonTravellerService = commonTravellerService;
	}

	public ISystemDictionaryService getDictionaryService() {
		return dictionaryService;
	}

	public void setDictionaryService(ISystemDictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	public ICommonTravellerCardService getCommonTravellerCardService() {
		return commonTravellerCardService;
	}

	public void setCommonTravellerCardService(
			ICommonTravellerCardService commonTravellerCardService) {
		this.commonTravellerCardService = commonTravellerCardService;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}
}
