package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.ticket.pojo.SystemDictionary;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.service.ITreeService;
import com.ticket.util.GeneralUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 系统字典表控制器
 * @ClassName: SystemDictionaryAction   
 * @Description:  提供系统字典表的相关操作方法. 
 * @author HiSay  
 * @date 2015-10-24 15:27:39
 *
 */
public class SystemDictionaryTemplateAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//系统字典表的业务层
	@Resource private ISystemDictionaryService systemDictionaryService = null;
	//树结构服务
	@Resource private ITreeService treeService;
	//系统字典表实体
	private SystemDictionary systemDictionary = null;
	//主键
	private String id = null;
    //字典名称
	private String name = null;
	//排序值
	private Integer orderValue = null;
	//说明
	private String description = null;
    //字典值
	private String value = null;
    //父字典ID
	private String parent_id = null;
	
	/**
	 * @Description：遍历所有字典
	 * @return
	 */
	public String traverse() {
		
		try {
			if(GeneralUtil.isNull(name)){
				
				JSONArray json = treeService.traverse(SystemDictionary.class, "name", "value");
				data = json.toString();
				return TEXT;
			}else{
				
				SystemDictionary parent = systemDictionaryService.getByName(name);
				JSONArray json = treeService.traverseUnderParent(SystemDictionary.class, parent.getId(), "name", "value");
				data = json.toString();
				return TEXT;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	/**
	 * @Description：加载树的单层数据,根据父ID
	 * @return
	 */
	public String loadTree(){
		
		String parentId = (String)ServletActionContext.getRequest().getParameter("id");
		List<SystemDictionary> dictionarys = null;
		
		//加载根节点
		if(parentId == null){
			
			JSONObject json = new JSONObject();
			Long count = treeService.count(SystemDictionary.class);
			json.put("total", count);
			
			dictionarys = treeService.queryRootNode(SystemDictionary.class);
			JSONArray jsonArray = new JSONArray();
			for(Object o : dictionarys){
				
				SystemDictionary dict = (SystemDictionary)o;
				Long subCount = treeService.countSubByParent(SystemDictionary.class, dict.getId());
				JSONObject jsonSingle = new JSONObject();
				if(subCount > 0){
					
					jsonSingle.put("state", "closed");
				}
				jsonSingle.put("id", dict.getId());
				jsonSingle.put("name", dict.getName());
				jsonSingle.put("value", dict.getValue());
				jsonSingle.put("orderValue", dict.getStatus().getOrderValue());
				jsonSingle.put("description", dict.getDescription());
				jsonArray.add(jsonSingle);
			}
			json.put("rows", jsonArray);
			data = json.toString();
		}else{ //加载非根节点
			
			dictionarys = treeService.querySubByParent(SystemDictionary.class, parentId);
			JSONArray jsonArray = new JSONArray();
			for(Object o : dictionarys){
				
				SystemDictionary dict = (SystemDictionary)o;
				Long subCount = treeService.countSubByParent(SystemDictionary.class, dict.getId());
				JSONObject jsonSingle = new JSONObject();
				if(subCount > 0){
					
					jsonSingle.put("state", "closed");
				}
				jsonSingle.put("_parentId", dict.getParent().getId());
				jsonSingle.put("id", dict.getId());
				jsonSingle.put("name", dict.getName());
				jsonSingle.put("value", dict.getValue());
				jsonSingle.put("orderValue", dict.getStatus().getOrderValue());
				jsonSingle.put("description", dict.getDescription());
				jsonArray.add(jsonSingle);
			}
			data = jsonArray.toString();
		}
		return TEXT;
	}
	
	/**
	 * @Description：查询兄弟节点
	 * @return
	 */
	public String querySibling(){
		
		SystemDictionary parent = (SystemDictionary)treeService.getParentBySub(SystemDictionary.class, id);
		List<SystemDictionary> dicts = treeService.querySubByParent(SystemDictionary.class, parent.getId());
		data = net.sf.json.JSONArray.fromObject(dicts).toString();
		return TEXT;
	}
	
	/**
	 * @Description：根据父字典的name查询所有的子节点
	 * @return
	 */
	public String querySubByParentName(){
		
		List<SystemDictionary> dicts = treeService.querySubByParent(SystemDictionary.class, "name", name);
		data = net.sf.json.JSONArray.fromObject(dicts).toString();
		return TEXT;
	}
	
	/**
	 * @Description：根据父字典的name查询所有的子节点
	 * @return
	 */
	public String querySubByParentValue(){
		
		List<SystemDictionary> dicts = treeService.querySubByParent(SystemDictionary.class, "value", value);
		data = net.sf.json.JSONArray.fromObject(dicts).toString();
		return TEXT;
	}
	
	/**
	 * @Description：通过字典名称，获得字典值
	 * @return 字典值
	 */
	public String getValueByName(){
		
		data = systemDictionaryService.getValueByName(name);
		return TEXT;
	}
	
	/**
	 * @Description：根据id得到一个字典对象
	 * @return
	 */
	public String loadSystemDictionary()throws Exception{
		
		data = net.sf.json.JSONObject.fromObject(systemDictionaryService.queryById("SystemDictionary", id)).toString(); 
		return TEXT;
	}
	
	public SystemDictionary getSystemDictionary() {
		return systemDictionary;
	}
	public void setSystemDictionary(SystemDictionary systemDictionary) {
		this.systemDictionary = systemDictionary;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public ITreeService getTreeService() {
		return treeService;
	}
	public void setTreeService(ITreeService treeService) {
		this.treeService = treeService;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getOrderValue() {
		return orderValue;
	}
	public void setOrderValue(Integer orderValue) {
		this.orderValue = orderValue;
	}
}
