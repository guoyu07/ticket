package com.ticket.serviceImpl;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ticket.service.ITreeService;

/**
 * @Description：专门处理树的结构的服务类
 * @author：涂有
 * @date 2015年10月24日 下午2:20:12
 */
public class TreeServiceImpl extends BaseServiceImpl<Object, String> implements ITreeService<Object, String> {

	@Override
	public <T> List<T> query(Class<T> c) {
		
		List<T> list = dbDAO.executeJPQLForQuery(
				"select c from " + c.getName() + " c order by c.status.orderValue", c);
		return list;
	}
	
	@Override
	public <T> Long countSubByParent(Class<T> c, String parent_id) {
		
		Long count = (Long)dbDAO.executeJPQLForQuerySingle(
				"select count(c) from " + c.getName() + " c where parent_id=? order by c.status.orderValue", 
				parent_id);
		return count;
	}

	@Override
	public <T> List<T> querySubByParent(Class<T> c, String parent_id) {
		
		List<T> list = dbDAO.executeJPQLForQuery(
				"select c from " + c.getName() + " c where parent_id=? order by c.status.orderValue", c,
				parent_id);
		return list;
	}
	
	@Override
	public <T> T getParentBySub(Class<T> c, String sub_id){
		T obj = dbDAO.executeJPQLForQuerySingle("select p from " + c.getName() + " s," 
					+ c.getName() +" p where s.id=? and s.parent_id=p.id order by p.status.orderValue", c, sub_id);
		return obj;
	}
	
	@Override
	public <T> boolean hasSubByParent(Class<T> c, String parent_id) {
		
		return countSubByParent(c, parent_id) == 0 ? false : true;
	}

	@Override
	public <T> List<T> queryRootNode(Class<T> c) {
		
		List<T> list = dbDAO.executeJPQLForQuery(
				"select c from " + c.getName() + " c where parent_id is null or"
				+ " length(trim(parent_id))=0 order by c.status.orderValue", c);
		return list;
	}

	@Override
	public <T> JSONArray traverse(Class<T> c, String...textColumnName) throws Exception {
		
		JSONArray jsonArray = new JSONArray();
		List<T> rootList = this.queryRootNode(c);
		for(Object node : rootList){
			
			//设置id属性
			Method getId = node.getClass().getMethod("getId");
			String id = (String)getId.invoke(node);
			JSONObject json = new JSONObject();
			json.put("id", id);
			
			if(textColumnName != null){
				
				for(int i = 0; i < textColumnName.length; i++){
					
					//设置text属性
					String methodName = "get" + textColumnName[i].substring(0,1).toUpperCase() + textColumnName[i].substring(1);
					Method columnName = node.getClass().getMethod(methodName);
					String text = (String)columnName.invoke(node);
					if(i == 0){
						
						json.put("text", text);
					}else{
						
						json.put(textColumnName[i], text);
					}
				}
			}
			
			JSONArray subJsonArray = traverseUnderParent(c, id, textColumnName);
			json.put("children", subJsonArray);
			jsonArray.add(json);
		}
		return jsonArray;
	}
	
	@Override
	public <T> JSONArray traverseUnderParent(Class<T> c, String parent_id, String...textColumnName) throws Exception{
		
		JSONArray jsonArray = new JSONArray();
		List<T> subList = this.querySubByParent(c, parent_id);
		for(Object node : subList){
			
			//设置id属性
			Method getId = node.getClass().getMethod("getId");
			String id = (String)getId.invoke(node);
			JSONObject json = new JSONObject();
			json.put("id", id);
			
			if(textColumnName != null){
				
				for(int i = 0; i < textColumnName.length; i++){
					
					//设置text属性
					String methodName = "get" + textColumnName[i].substring(0,1).toUpperCase() + textColumnName[i].substring(1);
					Method columnName = node.getClass().getMethod(methodName);
					String text = (String)columnName.invoke(node);
					if(i == 0){
						
						json.put("text", text);
					}else{
						
						json.put(textColumnName[i], text);
					}
				}
			}
			
			JSONArray subJsonArray = traverseUnderParent(c, id, textColumnName);
			json.put("children", subJsonArray);
			jsonArray.add(json);
		}
		return jsonArray;
	}
	
	@Override
	public <T> List<T> querySubByParent(Class<T> c, String columnName, String value){
		
		String tableName = c.getName();
		List<T> systemDictionary = dbDAO.executeJPQLForQuery(
				"select s from "+tableName+" s, "+tableName+" p"
				+ " where p."+columnName+"=? and p=s.parent order by s.status.orderValue", c, value);
		return systemDictionary;
	}
}
