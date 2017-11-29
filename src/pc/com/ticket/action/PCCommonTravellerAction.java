package com.ticket.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonTraveller;
import com.ticket.pojo.CommonTravellerCard;
import com.ticket.pojo.Member;
import com.ticket.pojo.SystemDictionary;

import net.sf.json.JSONObject;

public class PCCommonTravellerAction extends CommonTravellerAction{

	private static final long serialVersionUID = 1L;
	/**
	 * 常用旅客列表
	 */
	public String show() throws ServiceException{
		return super.show();
	}
	/**
	 * 添加常用旅客
	 */
	public String add(){
		List<SystemDictionary> dicts = dictionaryService.querySubByParentName("证件类型");
		JSONObject object = new JSONObject();
		object.put("type", dicts);
		object.put("1", 1);
		data = AjaxData.responseSuccess(JSONObject.fromObject(object).toString());
		return JSON;
	}
	/**
	 * 添加常用旅客
	 */
	public String addTraveller() throws ServiceException {
		return super.addTraveller();
	}
	/**
	 * 删除常用旅客
	 */
	public String del() throws ServiceException {
		return super.del();
	}
	/**
	 * 修改常用旅客
	 */
	public String editCommon() throws ServiceException{
		Member member = (Member)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_MEMBER);
		memberId = member.getId();
		CommonTraveller traveller = commonTravellerService.findById(id, memberId);

		List<CommonTravellerCard> list =  commonTravellerCardService.findByParentId(id);
		
		List<SystemDictionary> dicts = dictionaryService.querySubByParentName("证件类型");
		JSONObject object = new JSONObject();
		object.put("traveller", traveller);
		object.put("list", list);
		object.put("type", dicts);
		data = AjaxData.responseSuccess(JSONObject.fromObject(object).toString());
		return JSON;
	}
	/**
	 * 修改常用旅客
	 */
	public String xiugai() throws ServiceException{
		Member member = (Member)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_MEMBER);
		memberId = member.getId();
		return super.xiugai();
	}
}
