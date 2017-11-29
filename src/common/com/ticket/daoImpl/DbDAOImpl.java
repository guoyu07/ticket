/**
 * @author 聚名科技
 * @date   2014-02-01
 */
package com.ticket.daoImpl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.ticket.dao.IDbDAO;
import com.ticket.pojo.Pagination;
import com.ticket.util.ConvertUtil;
import com.ticket.util.GeneralUtil;

/**
 * 数据层接口实现类
 * @ClassName: IDbDAO   
 * @Description:提供常用对增删改查功能   
 * @author 晏海水(Henry)  
 * @date Feb 1, 2014 6:50:37 AM   
 *   
 * @param <E>
 */
@SuppressWarnings("unchecked")
@Transactional(rollbackFor=Exception.class)
public class DbDAOImpl<E, ID extends Serializable> implements IDbDAO<E, ID> {

	@PersistenceContext
	private EntityManager entityManager = null;
	
	/**泛型实体类型，此方法可以不需要从外面传入类名称*/
	/*private Class<E> entityClass = null;
	
	public DbDAOImpl() {
		this.entityClass = null;
		Class c = this.getClass();
		Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
            this.entityClass = (Class<E>) parameterizedType[0];
        }
	}*/
	
	@Override
	public void merge(Object object) {
		entityManager.merge(object);
	}
	
	@Override
	public void persist(Object object) {
		entityManager.persist(object);
	}

	@Override
	public void remove(Object object) {
		
			String id = null;
			try {
				Method getId = object.getClass().getMethod("getId");
				id = (String)getId.invoke(object);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			Object obj = entityManager.find(object.getClass(), id);
			entityManager.remove(obj);
	}
	
	@Override
	public <T> T get(Class<T> c, String id) {
		
		T t = entityManager.find(c, id);
		return t;
	}

	@Override
	public int logicDelete(String entityName, ID id) {
		String jpl = "update " + entityName + " s set s.status.deleteFlag=1 where s.id=?1 ";
		Query query = entityManager.createQuery(jpl).setHint("org.hibernate.cacheable", true);
		query.setParameter(1, id);
		return query.executeUpdate();
	}
	
	@Override
	public int logicDeleteAll(String entityName, String ids) {
		if(GeneralUtil.isNotNull(ids)){
			String jpl = "update " + entityName + " s set s.status.deleteFlag=1 where s.id in ( "+ids+" )";
			Query query = entityManager.createQuery(jpl).setHint("org.hibernate.cacheable", true);
			return query.executeUpdate();
		}
		return 0;
	}
	
	@Override
	public int delete(String entityName, ID id, String path) {
		/*//删除图片
		Object[] file = queryByCustom(entityName,null,null,"s.status.url","and s.id = ?3 ",new Object[]{id});
			if(file != null && file[0] != null){
				String picture = file[0].toString();
				if(picture != null) {
					FileUtil.deleteFile(path + picture);
				}
		}
		
		String jpl = "delete " + entityName + " s where s.id=?1";
		Query query = entityManager.createQuery(jpl).setHint("org.hibernate.cacheable", true);
		query.setParameter(1, id);
		int i = query.executeUpdate();
		return i;*/
		//删除图片
		/*Object[] file = this.queryByCustom(entityName, null, versionFlag, returnValue, whereValue, value) ;
			if(file != null && file[0] != null){
				String picture = file[0].toString();
				if(picture != null) {
					FileUtil.deleteFile(path + picture);
				}
		}
		*/
		String jpl = "delete " + entityName + " s where s.id=?1";
		Query query = entityManager.createQuery(jpl).setHint("org.hibernate.cacheable", true);
		query.setParameter(1, id);
		int i = query.executeUpdate();
		return i;
	}
	
	@Override
	public int deleteAll(String entityName, String ids,String path) {
		int count = 0; 
		if(GeneralUtil.isNotNull(ids)){
			List<String> idsList = new ConvertUtil<String>().stringToList(ids, ",");
			if(idsList != null && idsList.size()>0){
				for(int i=0;i<idsList.size();i++){
					ID id = (ID)idsList.get(i).toString();
					count += delete(entityName, id,path);
				}
			}
			return count;
		}
		return 0;
	}
	
	@Override
	public int deleteByWhere(String entityName,Integer isDelete ,String versionFlag,String whereValue,Object[] value,String path) {
		Pagination pm = queryByPageModule(entityName, isDelete,versionFlag,"s.id", whereValue, value,null, 0, 1);
		if(pm != null ){
			pm.setMaxPage(200);
			int size = pm.getMaxPage(); 
			int count = 0;
			for(int i=0;i<size;i++){
				int page = i*200;
				Pagination tmp = queryByPageModule(entityName, isDelete,versionFlag,"s.id", whereValue, value,null, page, 200);
				if(tmp != null){
					for(int j=0;j<tmp.getPageObject().length;j++){
						Object[] objList = tmp.getPageObject();
						ID id = (ID)objList[j].toString();							
						count += delete(entityName, id,path);
					}
				}
			}
			return count;
		}
		return 0;
	}
	
	@Override
	public int deleteByWhere(String entityName,Integer isDelete ,String versionFlag,String whereValue,Object[] value) {
		String jpl = "delete " + entityName + " s where 1=1";
		if(GeneralUtil.isNotNull(isDelete)){
			jpl += " and s.status.deleteFlag=?1 ";
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			jpl += " and s.status.versionFlag=?2 ";
		}
		if(GeneralUtil.isNotNull(whereValue)){
			jpl += whereValue;
		}
		Query query = entityManager.createQuery(jpl).setHint("org.hibernate.cacheable", true);
		if(GeneralUtil.isNotNull(isDelete)){
			query.setParameter(1, isDelete);
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			query.setParameter(2, versionFlag);
		}
		if(GeneralUtil.isNotNull(whereValue) && (value != null && value.length>0) ){
			 for(int i=0;i<value.length;i++) {
		         int j = i+3;
		         query.setParameter(j, value[i]);   
		     }
		}
		return query.executeUpdate();
	}
	
	@Override
	public int restore(String entityName, ID id) {
		String jpl = "update " + entityName + " s set s.status.deleteFlag = 0 where s.id= ?1 " ;
		Query query = entityManager.createQuery(jpl).setHint("org.hibernate.cacheable", true);
		query.setParameter(1, id);
		int i = query.executeUpdate();
		return i;
	}
	
	@Override
	public int restoreAll(String entityName, String ids) {
		if(GeneralUtil.isNotNull(ids)){
			String jpl = "update " + entityName + " s set s.status.deleteFlag=0 where s.id in ( "+ids+" )";
			Query query = entityManager.createQuery(jpl).setHint("org.hibernate.cacheable", true);
			int i = query.executeUpdate();
			return i;
		}
		return 0;
	}
	
	@Override
	public int restore(String entityName) {
		String jpl = "update " + entityName + " s set s.status.deleteFlag=0 ";
		Query query = entityManager.createQuery(jpl).setHint("org.hibernate.cacheable", true);
		int i = query.executeUpdate();
		return i;
	}
	
	@Override
	public int setState(String entityName, ID id, String stateField, Integer stateValue) {
		if(GeneralUtil.isNotNull(stateField)){
			stateField = stateField.replace("status.", "").replace("s.", "");
			String jpl = "update " + entityName + " s set s.status."+stateField+" = ?1 where s.id= ?2";
			Query query = entityManager.createQuery(jpl).setHint("org.hibernate.cacheable", true);
			query.setParameter(1, stateValue); 
			query.setParameter(2, id);
			return query.executeUpdate();
		}
		return 0;
	}
	
	@Override
	public int setStateAll(String entityName, String ids, String stateField, Integer stateValue) {
		if(GeneralUtil.isNotNull(stateField) && (GeneralUtil.isNotNull(ids))){
			stateField = stateField.replace("status.", "").replace("s.", "");
			String jpl = "update " + entityName + " s set s.status."+stateField+" = ?1 where s.id in ("+ids+") ";
			Query query = entityManager.createQuery(jpl).setHint("org.hibernate.cacheable", true);
			query.setParameter(1, stateValue); 
			return query.executeUpdate();
		}
		return 0;
	}
	
	@Override
	public int setStateAll(String entityName, String stateField, Integer stateValue, String whereValue, Object[] value) {
		if(GeneralUtil.isNotNull(stateField)){
			stateField = stateField.replace("status.", "").replace("s.", "");
			String jpl = "update " + entityName + " s set s.status."+stateField+" = ?1 where 1=1";
			if(GeneralUtil.isNotNull(whereValue)){
				jpl += whereValue;
			}
			Query query = entityManager.createQuery(jpl).setHint("org.hibernate.cacheable", true);
			query.setParameter(1, stateValue); 
			if(GeneralUtil.isNotNull(whereValue) && (value != null && value.length>0) ){
				 for(int i=0;i<value.length;i++) {
			         int j = i+3;
			         query.setParameter(j, value[i]);   
			     }
			}
			return query.executeUpdate();
		}
		return 0;
	}
	
	@Override
	public Long getMaxOrderValue(String entityName, String versionFlag){
		String jpl = "select max(s.status.orderValue) from "+entityName+" s where 1=1 ";
		
		if(versionFlag != null && !"".equals(versionFlag)){
			jpl += " and s.status.versionFlag = ?1 ";
		}
		Query query = entityManager.createQuery(jpl).setHint("org.hibernate.cacheable", true);
		if(versionFlag != null && !"".equals(versionFlag)){
			query.setParameter(1, versionFlag);
		}
		List list = query.getResultList();
		if(list != null && list.size() > 0) {
			Object result = query.getSingleResult();
			if(result == null){
				return 0L;
			}else{
				return (Long)result + 1;
			}
		}
		return 0L;
	}
	
	@Override
	public E queryById(String entityName, ID id) {
		String jpl = "select s from "+entityName+" s where s.id = ?1 ";
		Query query = entityManager.createQuery(jpl).setHint("org.hibernate.cacheable", true);
		query.setParameter(1, id);
		return (E) query.getSingleResult();
	}

	@Override
	public E queryByUuid(String entityName, String id) {
		String jpl = "select s from "+entityName+" s  where s.status.uuid = ?1 ";
		Query query = entityManager.createQuery(jpl).setHint("org.hibernate.cacheable", true);
		query.setParameter(1, id);
		
		List list = query.getResultList();
		if(list != null && !list.isEmpty()) {
			return (E) list.get(0);
		}
		return null;
	}
	
	@Override
	public E queryByCustom(String entityName, Integer isDelete ,String versionFlag ,String whereValue,Object[] value) {
		String jpl = "select s from "+entityName+" s  where 1=1 ";
		
		if(GeneralUtil.isNotNull(isDelete)){
			jpl += " and s.status.deleteFlag=?1 ";
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			jpl += " and s.status.versionFlag=?2 ";
		}
		
		if(GeneralUtil.isNotNull(whereValue)){
			jpl += whereValue;
		}
		Query query = entityManager.createQuery(jpl).setHint("org.hibernate.cacheable", true);
		
		if(GeneralUtil.isNotNull(isDelete)){
			query.setParameter(1, isDelete);
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			query.setParameter(2, versionFlag);
		}
		if(GeneralUtil.isNotNull(whereValue) && (value != null && value.length>0) ){
			 for(int i=0;i<value.length;i++) {
		         int j = i+3;
		         query.setParameter(j, value[i]);   
		     }
		}
		
		List list = query.getResultList();
		if(list != null && !list.isEmpty()) {
			return (E) list.get(0);
		}
		return null;
	}
	
	@Override
	public Object[] queryByCustom(String entityName, Integer isDelete ,String versionFlag,String returnValue ,String whereValue,Object[] value) {
		String jpl = "select "+returnValue+" from "+entityName+" s where 1=1 ";
		
		if(GeneralUtil.isNotNull(isDelete)){
			jpl += " and s.status.deleteFlag=?1 ";
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			jpl += " and s.status.versionFlag=?2 ";
		}
		
		if(GeneralUtil.isNotNull(whereValue)){
			jpl += whereValue;//.replace("status.", "");
		}
		Query query = entityManager.createQuery(jpl).setHint("org.hibernate.cacheable", true);
		if(GeneralUtil.isNotNull(isDelete)){
			query.setParameter(1, isDelete);
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			query.setParameter(2, versionFlag);
		}
		if(GeneralUtil.isNotNull(whereValue) && (value != null && value.length>0) ){
			 for(int i=0;i<value.length;i++) {
		         int j = i+3;
		         query.setParameter(j, value[i]);   
		     }
		}
		
		List list = query.getResultList();
		if(list != null && !list.isEmpty()) {
			return (Object[]) list.get(0);
		}
		return null;
	}
	
	@Override
	public Long queryByCustomLong(String entityName, Integer isDelete ,String versionFlag,String returnValue ,String whereValue,Object[] value) {
		String jpl = "select "+returnValue+" from "+entityName+" s where 1=1 ";
		
		if(GeneralUtil.isNotNull(isDelete)){
			jpl += " and s.status.deleteFlag=?1 ";
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			jpl += " and s.status.versionFlag=?2 ";
		}
		
		if(GeneralUtil.isNotNull(whereValue)){
			jpl += whereValue;//.replace("status.", "");
		}
		Query query = entityManager.createQuery(jpl).setHint("org.hibernate.cacheable", true);
		if(GeneralUtil.isNotNull(isDelete)){
			query.setParameter(1, isDelete);
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			query.setParameter(2, versionFlag);
		}
		if(GeneralUtil.isNotNull(whereValue) && (value != null && value.length>0) ){
			 for(int i=0;i<value.length;i++) {
		         int j = i+3;
		         query.setParameter(j, value[i]);   
		     }
		}
		query.setMaxResults(1);
		List list = query.getResultList();
		if(list != null && !list.isEmpty()) {
			return (Long) list.get(0);
		}
		return null;
	}
	
	@Override
	public Long queryMaxVisitUrl(String entityName, Integer isDelete, String versionFlag, String whereValue, Object[] value, String orderBy) {
		String jpl = "select s.status.visitUrl from "+entityName+" s where 1=1 ";
		
		if(GeneralUtil.isNotNull(isDelete)){
			jpl += " and s.status.deleteFlag=?1 ";
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			jpl += " and s.status.versionFlag=?2 ";
		}
		
		if(GeneralUtil.isNotNull(whereValue)){
			jpl += whereValue.replace("status.", "");
		}
		
		if(orderBy != null && !"".equals(orderBy)){
			jpl += " order by "+orderBy.replace("status.", "").replace("s.", "");
		}
		
		Query query = entityManager.createQuery(jpl).setHint("org.hibernate.cacheable", true);
		if(GeneralUtil.isNotNull(isDelete)){
			query.setParameter(1, isDelete);
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			query.setParameter(2, versionFlag);
		}
		if(GeneralUtil.isNotNull(whereValue) && (value != null && value.length>0) ){
			 for(int i=0;i<value.length;i++) {
		         int j = i+3;
		         query.setParameter(j, value[i]);   
		     }
		}
		List list = query.getResultList();
		if(list != null && !list.isEmpty()) {
			return (Long) list.get(0);
		}
		return 0L;
	}
	
	@Override
	public List<E> queryByListAndAllVersion(String entityName,Integer isDelete ,String whereValue,Object[] value, String orderby, Integer size) {
		String jpl = "select s from "+entityName+" s where 1=1 ";
		
		if(GeneralUtil.isNotNull(isDelete)){
			jpl += " and s.status.deleteFlag=?1 ";
		}

		if(GeneralUtil.isNotNull(whereValue)){
			jpl += whereValue;
		}
		if(orderby != null && !"".equals(orderby)){
			jpl += " order by "+orderby;
		}
		Query query = entityManager.createQuery(jpl).setHint("org.hibernate.cacheable", true);
		if(size != null && size > 0){
			query.setFirstResult(0).setMaxResults(size);
		}

		if(GeneralUtil.isNotNull(isDelete)){
			query.setParameter(1, isDelete);
		}

		if(GeneralUtil.isNotNull(whereValue) && (value != null && value.length>0) ){
			 for(int i=0;i<value.length;i++) {
		         int j = i+3;
		         query.setParameter(j, value[i]);   
		     }
		}
		return query.getResultList();
	}
	
	@Override
	public List<E> queryByList(String entityName,Integer isDelete ,String versionFlag ,String whereValue,Object[] value, String orderby, Integer size) {
		String jpl = "select s from "+entityName+" s where 1=1 ";
		
		if(GeneralUtil.isNotNull(isDelete)){
			jpl += " and s.status.deleteFlag=?1 ";
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			jpl += " and s.status.versionFlag=?2 ";
		}
		
		if(GeneralUtil.isNotNull(whereValue)){
			jpl += whereValue;
		}
		if(orderby != null && !"".equals(orderby)){
			jpl += " order by "+orderby;
		}
		Query query = entityManager.createQuery(jpl).setHint("org.hibernate.cacheable", true);
		if(size != null && size > 0){
			query.setFirstResult(0).setMaxResults(size);
		}

		if(GeneralUtil.isNotNull(isDelete)){
			query.setParameter(1, isDelete);
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			query.setParameter(2, versionFlag);
		}
		if(GeneralUtil.isNotNull(whereValue) && (value != null && value.length>0) ){
			 for(int i=0;i<value.length;i++) {
		         int j = i+3;
		         query.setParameter(j, value[i]);   
		     }
		}
		return query.getResultList();
	}
	
	@Override
	public List<E> queryByList(String entityName, Integer isDelete, 
			String versionFlag, String whereValue, Object[] value, String orderby, Integer startSize, Integer getCount) {
		String jpl = "select s from "+entityName+" s where 1=1 ";
		
		if(GeneralUtil.isNotNull(isDelete)){
			jpl += " and s.status.deleteFlag=?1 ";
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			jpl += " and s.status.versionFlag=?2 ";
		}
		
		if(GeneralUtil.isNotNull(whereValue)){
			jpl += whereValue;
		}
		if(orderby != null && !"".equals(orderby)){
			jpl += " order by "+orderby;
		}
		Query query = entityManager.createQuery(jpl).setHint("org.hibernate.cacheable", true);
		if(GeneralUtil.isNotNull(isDelete)){
			query.setParameter(1, isDelete);
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			query.setParameter(2, versionFlag);
		}
		if(GeneralUtil.isNotNull(whereValue) && (value != null && value.length>0) ){
			 for(int i=0;i<value.length;i++) {
		         int j = i+3;
		         query.setParameter(j, value[i]);   
		     }
		}
		if (query.getResultList() != null && query.getResultList().size() > 0) {
			return query.setFirstResult(startSize).setMaxResults(getCount).getResultList();
		} else {
			return null;
		}
	}
	
	@Override
	public Object[] queryByList(String entityName,Integer isDelete ,String versionFlag ,String returnValue, String whereValue,Object[] value, String orderby, Integer size) {
		String jpl = "select "+returnValue+" from "+entityName+" s  where 1=1 ";
		
		if(GeneralUtil.isNotNull(isDelete)){
			jpl += " and s.status.deleteFlag=?1 ";
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			jpl += " and s.status.versionFlag=?2 ";
		}
		
		if(GeneralUtil.isNotNull(whereValue)){
			jpl += whereValue.replace("status.", "");
		}
		if(orderby != null && !"".equals(orderby)){
			jpl += " order by "+orderby.replace("status.", "");
		}
		Query query = entityManager.createQuery(jpl).setHint("org.hibernate.cacheable", true);
		if(size != null && size > 0){
			query.setFirstResult(0).setMaxResults(size);
		}
		if(GeneralUtil.isNotNull(isDelete)){
			query.setParameter(1, isDelete);
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			query.setParameter(2, versionFlag);
		}
		if(GeneralUtil.isNotNull(whereValue) && (value != null && value.length>0) ){
			 for(int i=0;i<value.length;i++) {
		         int j = i+3;
		         query.setParameter(j, value[i]);   
		     }
		}
		
		List list = query.getResultList();
		if(list != null && !list.isEmpty()) {
			return list.toArray();
		}
		return null;
	}
	
	@Override
	public Pagination queryByPageModule(String entityName, Integer isDelete,
			String versionFlag, String whereValue, Object[] value,
			String orderby, Integer page, Integer pagesize) {
		
		String jpl_count = "select count(s.id) from "+entityName+" s where 1=1 ";
		
		if(GeneralUtil.isNotNull(isDelete)){
			jpl_count += " and s.status.deleteFlag=?1 ";
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			jpl_count += " and s.status.versionFlag=?2 ";
		}
		
		if(GeneralUtil.isNotNull(whereValue)){
			jpl_count += whereValue;//.replace("status.", "");
		}
		
		Query query_count = entityManager.createQuery(jpl_count).setHint("org.hibernate.cacheable", true);
		if(GeneralUtil.isNotNull(isDelete)){
			query_count.setParameter(1, isDelete);
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			query_count.setParameter(2, versionFlag);
		}
		
		if(GeneralUtil.isNotNull(whereValue) && (value != null && value.length>0) ){
			 for(int i=0;i<value.length;i++) {
		         int j = i+3;
		         query_count.setParameter(j, value[i]);   
		     }
		}
		Long count = getPageModuleCount(jpl_count,query_count);
		if(count > 0){
		
			String jpl = "select s from "+entityName+" s where 1=1 ";
			
			
			if(GeneralUtil.isNotNull(isDelete)){
				jpl += " and s.status.deleteFlag=?1 ";
			}
			if(GeneralUtil.isNotNull(versionFlag)){
				jpl += " and s.status.versionFlag=?2 ";
			}
			
			if(GeneralUtil.isNotNull(whereValue)){
				jpl += whereValue;
			}
			
			if(orderby != null && !"".equals(orderby)){
				jpl += " order by "+orderby;
			}
			
			Query query = entityManager.createQuery(jpl).setHint("org.hibernate.cacheable", true);

			if(GeneralUtil.isNotNull(isDelete)){
				query.setParameter(1, isDelete);
			}
			if(GeneralUtil.isNotNull(versionFlag)){
				query.setParameter(2, versionFlag);
			}
			if(GeneralUtil.isNotNull(whereValue) && (value != null && value.length>0) ){
				 for(int i=0;i<value.length;i++) {
			         int j = i+3;
			         query.setParameter(j, value[i]);   
			     }
			}
			query.setFirstResult(page).setMaxResults(pagesize);
			
			List list = query.getResultList();
			if(list != null && !list.isEmpty()) {
				Pagination pageModule = new Pagination();
				pageModule.setTotalCount(count);
				pageModule.setPageList(list);
				return pageModule;
			}
		}
		return null;
	}
	
	@Override
	public Pagination queryByPageModule(String entityName, Integer isDelete,
			String versionFlag,String returnValue, String whereValue, Object[] value,
			String orderby, Integer page, Integer pagesize) {
		
		String jpl_count = "select count(s.id) from "+entityName+" s where 1=1 ";
		
		if(GeneralUtil.isNotNull(isDelete)){
			jpl_count += " and s.status.deleteFlag=?1 ";
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			jpl_count += " and s.status.versionFlag=?2 ";
		}
		
		if(GeneralUtil.isNotNull(whereValue)){
			jpl_count += whereValue;//.replace("status.", "");
		}
		
		Query query_count = entityManager.createQuery(jpl_count).setHint("org.hibernate.cacheable", true);

		if(GeneralUtil.isNotNull(isDelete)){
			query_count.setParameter(1, isDelete);
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			query_count.setParameter(2, versionFlag);
		}
		
		if(GeneralUtil.isNotNull(whereValue) && (value != null && value.length>0) ){
			 for(int i=0;i<value.length;i++) {
		         int j = i+3;
		         query_count.setParameter(j, value[i]);   
		     }
		}
		Long count = getPageModuleCount(jpl_count,query_count);
		if(count > 0){
		
			String jpl = "select "+returnValue+" from "+entityName+" s where 1=1 ";
			
			if(GeneralUtil.isNotNull(isDelete)){
				jpl += " and s.status.deleteFlag=?1 ";
			}
			if(GeneralUtil.isNotNull(versionFlag)){
				jpl += " and s.status.versionFlag=?2 ";
			}
			
			if(GeneralUtil.isNotNull(whereValue)){
				jpl += whereValue.replace("status.", "");;
			}
			
			if(orderby != null && !"".equals(orderby)){
				jpl += " order by "+orderby.replace("status.", "");;
			}
			
			Query query = entityManager.createQuery(jpl).setHint("org.hibernate.cacheable", true);

			if(GeneralUtil.isNotNull(isDelete)){
				query.setParameter(1, isDelete);
			}
			if(GeneralUtil.isNotNull(versionFlag)){
				query.setParameter(2, versionFlag);
			}
			
			if(GeneralUtil.isNotNull(whereValue) && (value != null && value.length>0) ){
				 for(int i=0;i<value.length;i++) {
			         int j = i+3;
			         query.setParameter(j, value[i]);   
			     }
			}
			query.setFirstResult(page).setMaxResults(pagesize);
			
			List list = query.getResultList();
			if(list != null && !list.isEmpty()) {
				Pagination pageModule = new Pagination();
				pageModule.setTotalCount(count);
				pageModule.setPageList(list);
				return pageModule;
			}
		}
		return null;
	}
	
	@Override
	public Pagination queryByPageModuleCallStore(String entityName, Integer isDelete,
			String versionFlag,String returnValue, String whereValue, Object[] value,
			String orderby, Integer page, Integer pagesize) {
		
		String jpl_count = "select count(s.id) from "+entityName+" s where 1=1 ";
		
		if(GeneralUtil.isNotNull(isDelete)){
			jpl_count += " and s.status.deleteFlag=?1 ";
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			jpl_count += " and s.status.versionFlag=?2 ";
		}
		
		if(GeneralUtil.isNotNull(whereValue)){
			jpl_count += whereValue;//.replace("status.", "");
		}
		
		Query query_count = entityManager.createQuery(jpl_count).setHint("org.hibernate.cacheable", true);
		if(GeneralUtil.isNotNull(isDelete)){
			query_count.setParameter(1, isDelete);
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			query_count.setParameter(2, versionFlag);
		}
		
		if(GeneralUtil.isNotNull(whereValue) && (value != null && value.length>0) ){
			 for(int i=0;i<value.length;i++) {
		         int j = i+3;
		         query_count.setParameter(j, value[i]);   
		     }
		}
		Long count = getPageModuleCount(jpl_count,query_count);
		if(count > 0){
			
			Integer page_now = (page+pagesize)/pagesize;;
			String jpl = "";
			
			if(GeneralUtil.isNotNull(isDelete)){
				jpl += " and s.status.deleteFlag = "+isDelete+" ";
			}
			if(GeneralUtil.isNotNull(versionFlag)){
				jpl += " and s.status.versionFlag = "+versionFlag+" ";
			}
			
			
			if(GeneralUtil.isNotNull(whereValue) && (value != null && value.length>0)){
				for(int i=0;i<value.length;i++) {
			         int j = i+3;
			         if(GeneralUtil.isNumeric(value[i].toString())){
			        	 whereValue = whereValue.replace("?"+j, value[i].toString()); 
			         }else{
			        	 whereValue = whereValue.replace("?"+j, "'"+value[i].toString()+"'"); 
			         }
			    }
				whereValue = whereValue.replace("status.", "").replace("s.", "");
				
			}
			
			if(orderby != null && !"".equals(orderby)){
				orderby = " order by "+orderby.replace("status.", "").replace("s.", "");
				
			}
			
			Query query = entityManager.createQuery("{call MyPage(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11)}").setHint("org.hibernate.cacheable", true);
			
			query.setParameter(1, entityName);
			query.setParameter(2, returnValue);
			query.setParameter(3, "id");
			query.setParameter(4, whereValue);
			query.setParameter(5, orderby);
			query.setParameter(6, 3);
			query.setParameter(7, count);
			query.setParameter(8, pagesize);
			query.setParameter(9, page_now);
			query.setParameter(10,"");
			query.setParameter(11,"");
			query.setFirstResult(page).setMaxResults(pagesize);
			
			List list = query.getResultList();
			if(list != null && !list.isEmpty()) {
				Pagination pageModule = new Pagination();
				pageModule.setTotalCount(count);
				pageModule.setPageList(list);
				return pageModule;
			}
			return null;
		}
		return null;
	}
	
	public Long getPageModuleCount(String jpl_c,Query query){
		Long total = 0L;
		if(query != null && jpl_c != null){
			List list = query.getResultList();
			if (list != null && !list.isEmpty()) {
				total = (Long) list.get(0);
			}
		}
		return total;
	}

	@Override
	public Long getTotalCount(String entityName, Integer isDelete,
			String versionFlag, String whereValue, Object[] value) {
		String jpl = "select count(s.id) from "+entityName+" s where 1=1  ";
		
		if(GeneralUtil.isNotNull(isDelete)){
			jpl += " and s.status.deleteFlag=?1 ";
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			jpl += " and s.status.versionFlag=?2 ";
		}
		
		if(GeneralUtil.isNotNull(whereValue)){
			jpl += whereValue;//.replace("status.", "");
		}
		
		Query query = entityManager.createQuery(jpl).setHint("org.hibernate.cacheable", true);
		if(GeneralUtil.isNotNull(isDelete)){
			query.setParameter(1, isDelete);
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			query.setParameter(2, versionFlag);
		}
		
		if(GeneralUtil.isNotNull(whereValue) && (value != null && value.length>0) ){
			 for(int i=0;i<value.length;i++) {
		         int j = i+3;
		         query.setParameter(j, value[i]);   
		     }
		}
		Object result = query.getSingleResult();
		if(result != null){
			return (Long) result;
		}
		return 0L;
	}

	@Override
	public Object[] getTotalCount(String entityName, Integer isDelete,
			String versionFlag, String returnValue, String whereValue,
			Object[] value) {
		String jpl = "select "+returnValue+" from "+entityName+" s where 1=1  ";
		
		if(GeneralUtil.isNotNull(isDelete)){
			jpl += " and s.status.deleteFlag=?1 ";
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			jpl += " and s.status.versionFlag=?2 ";
		}
		
		if(GeneralUtil.isNotNull(whereValue)){
			jpl += whereValue.replace("status.", "");
		}
		
		Query query = entityManager.createQuery(jpl).setHint("org.hibernate.cacheable", true);
		if(GeneralUtil.isNotNull(isDelete)){
			query.setParameter(1, isDelete);
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			query.setParameter(2, versionFlag);
		}
		if(GeneralUtil.isNotNull(whereValue) && (value != null && value.length>0) ){
			 for(int i=0;i<value.length;i++) {
		         int j = i+3;
		         query.setParameter(j, value[i]);   
		     }
		}
		
		List list = query.getResultList();
		if (list != null && !list.isEmpty()) {
			return list.toArray();
		}
		return null;
	}
	
	@Override
	public int executeSQL(String sqlString) {
		if(GeneralUtil.isNotNull(sqlString)){
			sqlString = sqlString.replace("status.", "").replace("as s", "").replace("s.", "");
			Query query = entityManager.createQuery(sqlString).setHint("org.hibernate.cacheable", true);
			Integer count = query.executeUpdate();
			return count;
		}
		return 0;
	}

	@Override
	public int executeSQL(String sqlString, Object[] value) {
		if(GeneralUtil.isNotNull(sqlString)){
			sqlString = sqlString.replace("status.", "").replace("as s", "").replace("s.", "");
			Query query = entityManager.createQuery(sqlString).setHint("org.hibernate.cacheable", true);
			if(value != null && value.length>0){
				for(int i=0;i<value.length;i++) {
			         int j = i+1;
			         query.setParameter(j, value[i]);   
			    }
			}
			Integer count = query.executeUpdate();
			return count;
		}
		return 0;
	}
	
	@Override
	public Double executeCountSQL(String sqlString) {
		if(GeneralUtil.isNotNull(sqlString)){
			sqlString = sqlString.replace("status.", "").replace("as s", "").replace("s.", "");
			Query query = entityManager.createQuery(sqlString).setHint("org.hibernate.cacheable", true);
			List list = query.getResultList();
			if(list != null && !list.isEmpty()) {
				try {
					return Double.valueOf(list.get(0).toString());
				} catch(Exception e) {
					return 0D;
				}
			}
			return 0D;
		}
		return 0D;
	}

	@Override
	public Double executeCountSQL(String sqlString, Object[] value) {
		if(GeneralUtil.isNotNull(sqlString)){
			sqlString = sqlString.replace("status.", "").replace("as s", "").replace("s.", "");
			Query query = entityManager.createQuery(sqlString).setHint("org.hibernate.cacheable", true);
			if(value != null && value.length>0){
				for(int i=0;i<value.length;i++) {
			         int j = i+1;
			         query.setParameter(j, value[i]);   
			    }
			}
			List list = query.getResultList();
			if(list != null && !list.isEmpty()) {
				try {
					return Double.valueOf(list.get(0).toString());
				} catch(Exception e) {
					return 0D;
				}
			}
			return 0D;
		}
		return 0D;
	}
	
	@Override
	public List<Object[]> executeSQLForQuery(String sqlString, Object...values){
		
		Query query = entityManager.createNativeQuery(sqlString).setHint("org.hibernate.cacheable", true);
		for(int i = 0; i < values.length; i++){
			
			query.setParameter(i+1, values[i]);
		}
		List<Object[]> list = query.getResultList();
		
		return list;
	}
	
	@Override
	public List<Object[]> executeSQLQueryNoCache(String sqlString, Object...values){
		
		Query query = entityManager.createNativeQuery(sqlString);
		for(int i = 0; i < values.length; i++){
			
			query.setParameter(i+1, values[i]);
		}
		List<Object[]> list = query.getResultList();
		
		return list;
	}
	
	@Override
	public <T> List<T> executeSQLForQuery(String sqlString, Class<T> c,
			Object... values) {
	
		Query query = entityManager.createNativeQuery(sqlString, c).setHint("org.hibernate.cacheable", true);
		for(int i = 0; i < values.length; i++){
			
			query.setParameter(i+1, values[i]);
		}
		List<T> list = query.getResultList();
		
		return list;
	}
	
	@Override
	public <T> List<T> executeSQLForQuery(String jpqlString, Class<T> c,
			Integer start, Integer count, Object... values) {
		Query query = entityManager.createNativeQuery(jpqlString).setHint("org.hibernate.cacheable", true);
		for(int i = 0; i < values.length; i++){
			
			query.setParameter(i+1, values[i]);
		}
		if(start != null){
			query.setFirstResult(start);
		}
		if(count != null){
			query.setMaxResults(count);
		}
		
		List<T> list = query.getResultList();
		return list;
	}

	@Override
	public <T> T executeSQLForQuerySingle(String sqlString, Class<T> c,Object... values) {
		
		List<T> list = executeSQLForQuery(sqlString, c, 0, 1, values);
		if(list.size() != 0){
			
			return list.get(0);
		}
		return null;
	}

	@Override
	public int executeSQLForUpdate(String sqlString, Object... values) {
		
		Query query = entityManager.createNativeQuery(sqlString).setHint("org.hibernate.cacheable", true);
		for(int i = 0; i < values.length; i++){
			
			query.setParameter(i+1, values[i]);
		}
		int list = query.executeUpdate();
		return list;
	}
	
	@Override
	public String executeSQLForQuerySingle(String sqlString, Object...values){
		
		List<Object[]> list = this.executeSQLForQuery(sqlString, values);
		if(list == null || list.isEmpty()){
			
			return null;
		}
		return list.get(0)[0].toString();
	}
	
	@Override
	public List<Object> executeJPQLForQuery(String jpqlString, Object... values) {
		
		return executeJPQLForQuery(jpqlString, null, null, values);
	}
	
	@Override
	public List<E> executeJPQLForQueryEntity(String jpqlString, Object... values) {
		
		Query query = entityManager.createQuery(jpqlString).setHint("org.hibernate.cacheable", true);
		for(int i = 0; i < values.length; i++){
			
			query.setParameter(i+1, values[i]);
		}
		List<Object> list = query.getResultList();
		List<E> returnList = new ArrayList<E>();
		for(Object obj : list){
			returnList.add((E)obj);
		}
		return returnList;
	}

	@Override
	public Object executeJPQLForQuerySingle(String jpqlString, Object... values) {
		
		List<Object> list = this.executeJPQLForQuery(jpqlString, Object.class, 0, 1, values);
		if(list == null || list.isEmpty()){
			
			return null;
		}
		return list.get(0);
	}

	@Override
	public int executeJPQLForUpdate(String sqlString, Object...values) {
		
		Query query = entityManager.createQuery(sqlString).setHint("org.hibernate.cacheable", true);
		for(int i = 0; i < values.length; i++){
			
			query.setParameter(i+1, values[i]);
		}
		int list = query.executeUpdate();
		return list;
	}

	@Override
	public <T> List<T> executeJPQLForQuery(String jpqlString, Class<T> c,
			Object... values) {
		
		return executeJPQLForQuery(jpqlString, c, null, null, values);
	}

	@Override
	public <T> T executeJPQLForQuerySingle(String jpqlString, Class<T> c,
			Object... values) {
		
		List<T> list = executeJPQLForQuery(jpqlString, c, 0, 1, values);
		if(list.size() != 0){
			
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Object> executeJPQLForQuery(String jpqlString, Integer start,
			Integer count, Object... values) {
		
		return executeJPQLForQuery(jpqlString, Object.class, start, count, values);
	}

	@Override
	public <T> List<T> executeJPQLForQuery(String jpqlString, Class<T> c,
			Integer start, Integer count, Object... values) {
		
		Query query = entityManager.createQuery(jpqlString).setHint("org.hibernate.cacheable", true);
		for(int i = 0; i < values.length; i++){
			
			query.setParameter(i+1, values[i]);
		}
		if(start != null){
			query.setFirstResult(start);
		}
		if(count != null){
			query.setMaxResults(count);
		}
		
		List<T> list = query.getResultList();
		return list;
	}
	@Override
	public Object[] queryByCustomNew(String entityName, Integer isDelete,
			String versionFlag, String returnValue, String whereValue,
			List<Object[]> value) {
		String jpl = "select "+returnValue+" from "+entityName+" s where 1=1 ";
		
		if(GeneralUtil.isNotNull(isDelete)){
			jpl += " and s.status.deleteFlag=?1 ";
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			jpl += " and s.status.versionFlag=?2 ";
		}
		
		if(GeneralUtil.isNotNull(whereValue)){
			jpl += whereValue.replace("status.", "");
		}
		Query query = entityManager.createQuery(jpl).setHint("org.hibernate.cacheable", true);
		if(GeneralUtil.isNotNull(isDelete)){
			query.setParameter(1, isDelete);
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			query.setParameter(2, versionFlag);
		}
		if(GeneralUtil.isNotNull(whereValue) && (value != null && value.size()>0) ){
			for(Object[] objects:value) {
		         query.setParameter(Integer.parseInt(objects[0].toString()), objects[1]);   
		     }
		}
		List list = query.getResultList();
		if(list != null && !list.isEmpty()) {
			return (Object[]) list.get(0);
		}
		return null;
	}
	
	@Override
	public List<E> queryByListNew(String entityName, Integer deleteFlag,
			String versionFlag, String whereValue, List<Object[]> value,
			String orderBy, Integer size) {
		String jpl = "select s from "+entityName+" s where 1=1 ";
		if(GeneralUtil.isNotNull(deleteFlag)){
			jpl += " and s.status.deleteFlag=?1 ";
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			jpl += " and s.status.versionFlag=?2 ";
		}
		
		if(GeneralUtil.isNotNull(whereValue)){
			jpl += whereValue;
		}
		if(orderBy != null && !"".equals(orderBy)){
			jpl += " order by "+orderBy;
		}
		Query query = entityManager.createQuery(jpl).setHint("org.hibernate.cacheable", true);
		if(size != null && size > 0){
			query.setFirstResult(0).setMaxResults(size);
		}

		if(GeneralUtil.isNotNull(deleteFlag)){
			query.setParameter(1, deleteFlag);
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			query.setParameter(2, versionFlag);
		}
		if(GeneralUtil.isNotNull(whereValue) && (value != null && value.size()>0) ){
			 for(Object[] objects:value) {
		         query.setParameter(Integer.parseInt(objects[0].toString()), objects[1]);   
		     }
		}
		return query.getResultList();
	}
	
	@Override
	public Long getTotalCountNew(String entityName, Integer isDelete,
			String versionFlag, String whereValue, List<Object[]> value) {
		String jpl = "select count(s.id) from "+entityName+" s where 1=1  ";
		
		if(GeneralUtil.isNotNull(isDelete)){
			jpl += " and s.status.deleteFlag=?1 ";
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			jpl += " and s.status.versionFlag=?2 ";
		}
		
		if(GeneralUtil.isNotNull(whereValue)){
			jpl += whereValue;//.replace("status.", "");
		}
		
		Query query = entityManager.createQuery(jpl).setHint("org.hibernate.cacheable", true);
		if(GeneralUtil.isNotNull(isDelete)){
			query.setParameter(1, isDelete);
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			query.setParameter(2, versionFlag);
		}
		
		if(GeneralUtil.isNotNull(whereValue) && (value != null && value.size()>0) ){
			 for(Object[] objects:value) {
		         query.setParameter(Integer.parseInt(objects[0].toString()), objects[1]);   
		     }
		}
		Object result = query.getSingleResult();
		if(result != null){
			return (Long) result;
		}
		return 0L;
	}

	@Override
	public Pagination queryByPageModuleNew(String entityName, Integer isDelete,
			String versionFlag, String whereValue, List<Object[]> value,
			String orderby, Integer page, Integer pagesize) {
		
		String jpl_count = "select count(s.id) from "+entityName+" s where 1=1 ";
		
		if(GeneralUtil.isNotNull(isDelete)){
			jpl_count += " and s.status.deleteFlag=?1 ";
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			jpl_count += " and s.status.versionFlag=?2 ";
		}
		
		if(GeneralUtil.isNotNull(whereValue)){
			jpl_count += whereValue;//.replace("status.", "");
		}
		
		Query query_count = entityManager.createQuery(jpl_count).setHint("org.hibernate.cacheable", true);
		if(GeneralUtil.isNotNull(isDelete)){
			query_count.setParameter(1, isDelete);
		}
		if(GeneralUtil.isNotNull(versionFlag)){
			query_count.setParameter(2, versionFlag);
		}
		
		if(GeneralUtil.isNotNull(whereValue) && (value != null && value.size() > 0) ){
			 for(Object[] objects:value) {
		         query_count.setParameter(Integer.parseInt(objects[0].toString()), objects[1]);   
		     }
		}
		Long count = getPageModuleCount(jpl_count,query_count);
		if(count > 0){
		
			String jpl = "select s from "+entityName+" s where 1=1 ";
			
			
			if(GeneralUtil.isNotNull(isDelete)){
				jpl += " and s.status.deleteFlag=?1 ";
			}
			if(GeneralUtil.isNotNull(versionFlag)){
				jpl += " and s.status.versionFlag=?2 ";
			}
			
			if(GeneralUtil.isNotNull(whereValue)){
				jpl += whereValue;
			}
			
			if(orderby != null && !"".equals(orderby)){
				jpl += " order by "+orderby;
			}
			
			Query query = entityManager.createQuery(jpl).setHint("org.hibernate.cacheable", true);

			if(GeneralUtil.isNotNull(isDelete)){
				query.setParameter(1, isDelete);
			}
			if(GeneralUtil.isNotNull(versionFlag)){
				query.setParameter(2, versionFlag);
			}
			if(GeneralUtil.isNotNull(whereValue) && (value != null && value.size()>0) ){
				 for(Object[] objects:value) {
			         query.setParameter(Integer.parseInt(objects[0].toString()), objects[1]);   
			     }
			}
			query.setFirstResult(page).setMaxResults(pagesize);
			
			List list = query.getResultList();
			if(list != null && !list.isEmpty()) {
				Pagination pageModule = new Pagination();
				pageModule.setCurrentPage(page+1);
				pageModule.setTotalCount(count);
				pageModule.setPageList(list);
				return pageModule;
			}
		}
		return null;
	}


	@Override
	public Pagination queryByPageModuleNew(String sqlStart,String jpqlString,
			List<Object[]> value,Integer currentPage,
			Integer pageSize) {
		try {
			String jpl_count = "select count(*) "+jpqlString;
			Query query_count = entityManager.createQuery(jpl_count).setHint("org.hibernate.cacheable", true);
			if(value != null && value.size() > 0){
				for(Object[] objects:value) {
					query_count.setParameter(Integer.parseInt(objects[0].toString()), objects[1]);   
			     }
			}
			Long count = getPageModuleCount(jpl_count,query_count);
			if(count > 0){
				Query query = entityManager.createQuery(sqlStart+jpqlString).setHint("org.hibernate.cacheable", true);
				if(value != null && value.size() > 0){
					for(Object[] objects:value) {
				         query.setParameter(Integer.parseInt(objects[0].toString()), objects[1]);   
				     }
				}
				query.setFirstResult(currentPage).setMaxResults(pageSize);
				List list = query.getResultList();
				if(list != null && !list.isEmpty()) {
					Pagination pageModule = new Pagination();
					pageModule.setCurrentPage(currentPage+1);
					pageModule.setTotalCount(count);
					pageModule.setPageList(list);
					return pageModule;
				}
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}