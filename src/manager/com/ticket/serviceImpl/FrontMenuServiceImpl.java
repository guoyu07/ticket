package com.ticket.serviceImpl;


import java.util.List;

import javax.annotation.Resource;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.FrontMenu;
import com.ticket.service.IFrontMenuService;
import com.ticket.service.ITreeService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.ResourceUtil;

/**
 * 前端菜单管理表业务接口实现类
 * @ClassName: IFrontMenuService   
 * @Description: 提供前端菜单管理表操作的增删改查   
 * @author HiSay  
 * @date 2016-02-19 14:55:25
 *
 */
public class FrontMenuServiceImpl extends TreeServiceImpl implements IFrontMenuService {

	@Resource(name="treeService")
	private ITreeService<FrontMenu, String> treeService;

	@Override
	public boolean persist(String name,String value,boolean loadSub,int sort, String description,String parent_id) throws ServiceException {
		FrontMenu parent = get(FrontMenu.class, parent_id);
		return persist(name, value, loadSub, sort, description, parent);
	}
	
	@Override
	public boolean persist(String name,String value,boolean loadSub,int sort, String description,FrontMenu parent) throws ServiceException {
		
		if(existSameUnderParent(parent, name)){
			
			throw new ServiceException(ResourceUtil.getText("dictionary.existSameUnderParent"));
		}
		
		FrontMenu systemDictionary = new FrontMenu();
		systemDictionary.setName(DecoderUtil.UtfDecoder(name));
		systemDictionary.setValue(DecoderUtil.UtfDecoder(value));
		systemDictionary.setParent(parent);
		systemDictionary.setDescription(description);
		systemDictionary.setLoadSub(loadSub);
		CommonEntity status = systemDictionary.getStatus();
		status.setOrderValue(sort);
		systemDictionary.setStatus(status);
		dbDAO.persist(systemDictionary);
		return true;
	}
	
	@Override
	public boolean merge(String id, String name,String value,boolean loadSub, int sort, String description,String parent_id) throws ServiceException {
		
		FrontMenu parent = get(FrontMenu.class, parent_id);
		return merge(id, name, value, loadSub, sort, description, parent);
	}
	
	@Override
	public boolean merge(String id, String name,String value,boolean loadSub, int sort, String description,FrontMenu parent) throws ServiceException {
		
		FrontMenu systemDictionary = get(FrontMenu.class, id);
		systemDictionary.setName(DecoderUtil.UtfDecoder(name));
		systemDictionary.setValue(DecoderUtil.UtfDecoder(value));
		systemDictionary.setDescription(description);
		systemDictionary.setParent(parent);
		systemDictionary.setLoadSub(loadSub);
		CommonEntity status = systemDictionary.getStatus();
		status.setOrderValue(sort);
		systemDictionary.setStatus(status);
		dbDAO.merge(systemDictionary);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		FrontMenu systemDictionary = get(FrontMenu.class, id);
		dbDAO.remove(systemDictionary);
		return true;
	}
	
	@Override
	public List<FrontMenu> querySubByParentName(String parentName){
		Class c = FrontMenu.class;
		FrontMenu dict = getByName(parentName);
		if(dict == null) {
			dict = getByValue(parentName) ;
		}
		List<FrontMenu> sysDictionary = treeService.querySubByParent(c, dict.getId());
		return sysDictionary;
	}
	
	@Override
	public List<FrontMenu> querySubByParentValue(String parentValue){
		
		Class c = FrontMenu.class;
		FrontMenu dict = getByValue(parentValue);
		List<FrontMenu> sysDictionary = treeService.querySubByParent(c, dict.getId());
		return sysDictionary;
	}
	
	@Override
	public List<FrontMenu> querySubByParentValueOrderByLetter(String parentValue, String...columnNames){
		
		StringBuffer columns = new StringBuffer();
		for(String column : columnNames){
			
			columns.append(column);
			columns.append(",");
		}
		String sql = "select * from ticket_"+this.getTableNameFromEntity(FrontMenu.class)+" where parent_id = ? order by CONVERT( name USING gbk ) COLLATE gbk_chinese_ci ASC";
		FrontMenu dict = getByValue(parentValue);
		List<FrontMenu> list = dbDAO.executeSQLForQuery(sql, FrontMenu.class, dict.getId());
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}
	
	@Override
	public String getValueByName(String name) {
		
		FrontMenu dict = (FrontMenu)dbDAO.queryByCustom(FrontMenu.class.getName(), 
				deleteFlag, versionFlag, "and name=?3", new Object[]{name});
		if(dict != null){
			return dict.getValue();
		}else{
			return null;
		}
	}
	
	@Override
	public String getDescriptByName(String name) {
		FrontMenu dict = (FrontMenu)dbDAO.queryByCustom(FrontMenu.class.getName(), 
				deleteFlag, versionFlag, "and name=?3", new Object[]{name});
		if(dict != null){
			return dict.getDescription();
		}else{
			return null;
		}
	}
	
	@Override
	public FrontMenu getByName(String name){
		
		FrontMenu dict = (FrontMenu)dbDAO.queryByCustom(FrontMenu.class.getName(), 
				deleteFlag, versionFlag, "and name=?3", new Object[]{name});
		return dict;
	}

	@Override
	public FrontMenu getByValue(String value){
		
		FrontMenu dict = (FrontMenu)dbDAO.queryByCustom(FrontMenu.class.getName(), 
				deleteFlag, versionFlag, "and value=?3", new Object[]{value});
		return dict;
	}
	
	@Override
	public boolean existSameUnderParent(String parent_id, String name) {
		
		FrontMenu parent = get(FrontMenu.class, parent_id);
		return existSameUnderParent(parent, name);
	}

	@Override
	public boolean existSameUnderParent(FrontMenu parent, String name) {
		List<FrontMenu> subs = null;
		if(parent != null){
			
			subs = treeService.querySubByParent(FrontMenu.class, parent.getId());
		}else{
			subs = treeService.queryRootNode(FrontMenu.class);
		}
		for(FrontMenu sub : subs){
			
			if(sub.getName().equals(name)){
				
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean existSameUnderParentByName(String parent_name, String name) {
		
		FrontMenu parent = getByName(parent_name);
		return existSameUnderParent(parent, name);
	}
	
	@Override
	public FrontMenu getUnderParent(String parent_id, String name) {
		
		FrontMenu parent = get(FrontMenu.class, parent_id);
		return getUnderParent(parent, name);
	}
	
	@Override
	public FrontMenu getUnderParent(FrontMenu parent, String name) {
		List<FrontMenu> subs = null;
		if(parent != null){
			
			subs = treeService.querySubByParent(FrontMenu.class, parent.getId());
		}else{
			subs = treeService.queryRootNode(FrontMenu.class);
		}
		for(FrontMenu sub : subs){
			
			if(sub.getName().equals(name.trim())){
				
				return sub;
			}
		}
		return null;
	}
	
	@Override
	public FrontMenu getUnderParentByName(String parent_name, String name) {
		
		FrontMenu parent = getByName(parent_name);
		return getUnderParent(parent, name);
	}

	@Override
	public List<FrontMenu> queryRoot() {
		
		return queryRootNode(FrontMenu.class);
	}

	@Override
	public List<FrontMenu> querySub(FrontMenu parent) {
		
		return querySubByParent(FrontMenu.class, parent.getId());
	}
}