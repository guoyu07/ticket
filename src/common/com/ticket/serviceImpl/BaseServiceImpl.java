package com.ticket.serviceImpl;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.constants.ContextConstants;
import com.ticket.dao.IDbDAO;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonAnnex;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.Member;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.SystemUser;
import com.ticket.service.IBaseService;
import com.ticket.service.ICommonAnnexService;
import com.ticket.util.ConvertUtil;
import com.ticket.util.FileUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.PaginationContext;

public class BaseServiceImpl<T, ID extends Serializable> implements IBaseService<T, ID> {

	private static final Log log = LogFactory.getLog(BaseServiceImpl.class);
	
	@Resource
	public EntityManagerFactory entityManagerFactory;
	
	/**
	 * 通用的DAO层
	 */
	@Resource public IDbDAO<T, ID> dbDAO = null;
	/**
	 * 附件Service层
	 */
	@Resource public ICommonAnnexService commonAnnexService = null;
	
	/**
	 * 删除标记(默认为正常)
	 */
	public Integer deleteFlag = ContextConstants.STATUS_OF_ZERO;
	
	/**
	 * 默认版本标记(默认为中文版)
	 */
	public String versionFlag = ContextConstants.VERSION_FLAG_OF_CHINESE;
	
	/**
	 * 默认排序为根据数据的添加日期降序排序
	 */
	public String orderBy = "s.status.createTime desc";
	
	public String orderValueOrderBy = "s.status.orderValue desc"; //根据排序值排序
	public String orderByOrderValueAsc = "s.status.orderValue asc"; //根据排序值排序
	
	/**
	 * 转换工具类
	 */
	public static ConvertUtil<String> convertUtil = new ConvertUtil<String>();
	
	/**
	 * 获取在InitialListerner里面设置的相对于应用的根目录路径
	 * @Title: BaseServiceImpl
	 * @Description: TODO(主要用于文件的相关操作: 保存文件, 删除文件等)   
	 * @param @return    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String getFileBasePath() {
		return (String) ActionContext.getContext().getApplication().get("fileBasePath");
	}
	
	/**
	 * 获取Session对象
	 * @Title: BaseServiceImpl
	 * @Description:    
	 * @param @return    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public Map getSession() {
		return ActionContext.getContext().getSession();
	}
	
	/**
	 * 获取Application对象
	 * @Title: BaseServiceImpl
	 * @Description:    
	 * @param @return    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public Map getApplication() {
		try {
			return ActionContext.getContext().getApplication();
		} catch(Exception e) {
			return null;
		}
	}

	/**
	 * 根据类获取类的实体名称
	 * @Title: BaseServiceImpl
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param @param classObj
	 * @param @return    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	@Override
	public String getTableNameFromEntity(Class<?> classObject) {
		// 获取泛型实际的包名+类名
		String packageClassName = classObject.getName();
		// 通过字符串截取类名
		String targetClassName = packageClassName.substring(packageClassName.lastIndexOf(".") + 1);
		targetClassName = targetClassName.replace("ServiceImpl", "");
		return targetClassName;
	}
	
	@Override
	public void merge(Object object) {
		try {
			dbDAO.merge(object);
		} catch(Exception e) {
			log.info(e.fillInStackTrace());
		}
	}

	@Override
	public void persist(Object object) {
		try {
			dbDAO.persist(object);
		} catch(Exception e) {
			log.info(e.fillInStackTrace());
		}
	}
	
	@Override
	public void remove(Object object) {
		try {
			dbDAO.remove(object);
		} catch(Exception e) {
			log.info(e.fillInStackTrace());
		}
	}

	@Override
	public T queryById(String entityName, ID id) {
		try {
			return dbDAO.queryById(entityName, id);
		} catch(Exception e) {
			log.info("根据ID获取实体对象失败 :" + e.fillInStackTrace());
			return null;
		}
	}
	
	@Override
	public T queryByUuid(String entityName, String uuid) {
		try {
			return dbDAO.queryByUuid(entityName, uuid);
		} catch(Exception e) {
			log.info("根据UUID获取实体对象失败 :" + e.fillInStackTrace());
			return null;
		}
	}
	
	@Override
	public <E> E get(Class<E> c, String id) {

		E entity = dbDAO.executeJPQLForQuerySingle("select s from " + c.getName() + " s where id=?", c, id);
		return entity;
	}

	@Override
	public boolean logicDeleteEntity(String entityName, ID id) throws ServiceException {
		try {
			//先获取当前的实体名称
			int sucCount = dbDAO.logicDelete(entityName, id);
			if(sucCount > 0) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			log.info("逻辑删除实体对象失败: " + e.fillInStackTrace());
			return false;
		}
	}

	@Override
	public boolean restoreEntity(String entityName, ID id) throws ServiceException {
		try {
			//先获取当前的实体名称
			int sucCount = dbDAO.restore(entityName, id);
			if(sucCount > 0) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			log.info("还原单个实体对象出错: " + e.fillInStackTrace());
			return false;
		}
	}

	@Override
	public Pagination queryRecycleEntity(String versionFlag, Integer pageSize)
			throws ServiceException {
		try {
			//先获取当前的实体名称
			String entityName = this.getTableNameFromEntity(this.getClass());
			return dbDAO.queryByPageModule(entityName, ContextConstants.STATUS_OF_ONE, versionFlag, null, null, orderBy, PaginationContext.getOffset(), pageSize) ;
		} catch(Exception e) {
			log.info("查询回收站的实体列表出错: " + e.fillInStackTrace());
			return null;
		}
	}

	@Override
	public Pagination queryEntityByAdmin(String versionFlag, Integer pageSize)
			throws ServiceException {
		try {
			//先获取当前的实体名称
			String entityName = this.getTableNameFromEntity(this.getClass());
			return dbDAO.queryByPageModule(entityName, deleteFlag, versionFlag, null, null, orderBy, PaginationContext.getOffset(), pageSize);
		} catch(Exception e) {
			log.info("查询实体列表出错: " + e.fillInStackTrace());
			return null;
		}
	}
	
	@Override
	public Pagination queryEntity(String condition, Object...params)
			throws ServiceException {
		try {
			//先获取当前的实体名称
			String entityName = this.getTableNameFromEntity(this.getClass());
			return dbDAO.queryByPageModule(entityName, deleteFlag, versionFlag, condition, params, orderBy, PaginationContext.getOffset(), PaginationContext.getPagesize());
		} catch(Exception e) {
			log.info("查询实体列表出错: " + e.fillInStackTrace());
			return null;
		}
	}
	
	public Pagination queryEntity(Class<?> tableClass, String condition, Object...params)
			throws ServiceException{
		
		try {
			//先获取当前的实体名称
			return dbDAO.queryByPageModule(tableClass.getName(), deleteFlag, versionFlag, condition, params, orderBy, PaginationContext.getOffset(), PaginationContext.getPagesize());
		} catch(Exception e) {
			log.info("查询实体列表出错: " + e.fillInStackTrace());
			return null;
		}
	}
	
	@Override
	public Pagination queryEntity(String condition, String order, Object...params)
			throws ServiceException {
		try {
			//先获取当前的实体名称
			String entityName = this.getTableNameFromEntity(this.getClass());
			return dbDAO.queryByPageModule(entityName, deleteFlag, versionFlag, condition, params, order, PaginationContext.getOffset(), PaginationContext.getPagesize());
		} catch(Exception e) {
			log.info("查询实体列表出错: " + e.fillInStackTrace());
			return null;
		}
	}
	
	@Override
	public Pagination queryEntity(Class<?> tableClass, String condition, String order, Object...params)
			throws ServiceException {
		try {
			//先获取当前的实体名称
			return dbDAO.queryByPageModule(tableClass.getName(), deleteFlag, versionFlag, condition, params, order, PaginationContext.getOffset(), PaginationContext.getPagesize());
		} catch(Exception e) {
			log.info("查询实体列表出错: " + e.fillInStackTrace());
			return null;
		}
	}
	
	@Override
	public Pagination queryEntityByAdmin(String versionFlag, Integer start, Integer pageSize)
			throws ServiceException {
		try {
			//先获取当前的实体名称
			String entityName = this.getTableNameFromEntity(this.getClass());
			return dbDAO.queryByPageModule(entityName, deleteFlag, versionFlag, null, null, orderBy, start, pageSize);
		} catch(Exception e) {
			log.info("查询实体列表出错: " + e.fillInStackTrace());
			return null;
		}
	}
	
	@Override
	public boolean auditEntity(String entityName, ID id, Integer statusValue) throws ServiceException {
		try {
			int sucCount = dbDAO.setState(this.getTableNameFromEntity(this.getClass()), id, "audit", statusValue);
			if(sucCount > 0) {
				return true;
			}
			return false;
		} catch(Exception e) {
			log.info("审核单个实体出错 :" + e.fillInStackTrace());;
			return false;
		}
	}

	@Override
	public boolean batchOperationEntity(String versionFlag, String idsValue,
			String batchOperationType, boolean isChecked) throws ServiceException {
		try {
			//如果要批量操作的ID字符串不为空
			if(GeneralUtil.isNotNull(idsValue)) {
				if(idsValue.endsWith(",")){
					idsValue = idsValue.substring(0, idsValue.length() - 1);
				}
				//判断选中值
				Integer stateValue = ContextConstants.STATUS_OF_ZERO;
				if(isChecked) {
					stateValue = ContextConstants.STATUS_OF_ONE;
				}
				idsValue = GeneralUtil.convertIdsValueToString(idsValue, ",") ;
				/** 执行批量操作 */
				if("audit".equals(batchOperationType)) {          		//如果是批量审核操作
					dbDAO.setStateAll(this.getTableNameFromEntity(this.getClass()), idsValue, "audit", stateValue);
				} else if("commend".equals(batchOperationType)) { 		//如果是批量推荐操作
					dbDAO.setStateAll(this.getTableNameFromEntity(this.getClass()), idsValue, "commend", stateValue);
				} else if("hot".equals(batchOperationType)) { 			//如果是批量热门操作
					dbDAO.setStateAll(this.getTableNameFromEntity(this.getClass()), idsValue, "hot", stateValue);
				} else if("logicDelete".equals(batchOperationType)) { 	//如果是批量逻辑删除操作
					dbDAO.setStateAll(this.getTableNameFromEntity(this.getClass()), idsValue, "deleteFlag", stateValue);
				} else if("restore".equals(batchOperationType)) { 		//如果是批量还原操作
					dbDAO.setStateAll(this.getTableNameFromEntity(this.getClass()), idsValue, "deleteFlag", stateValue);
				}
			}
			return true;
		} catch(Exception e) {
			log.info("批量操作"+batchOperationType+"实体出错: " + e.fillInStackTrace());
			return false;
		}
	}
	
	/**
	 * @Description：批量删除
	 * @param ids
	 * @return
	 */
	public boolean batchRealDelete(Class clazz, String idsValue){
		
		try {
			//如果要批量操作的ID字符串不为空
			if(GeneralUtil.isNotNull(idsValue)) {
				
				if(idsValue.endsWith(",")){
					
					idsValue = idsValue.substring(0, idsValue.length() - 1);
				}
				idsValue = GeneralUtil.convertIdsValueToString(idsValue, ",") ;
				Table table = (Table)clazz.getAnnotation(Table.class);
				String tableName = table.name();
				
				StringBuffer sb = new StringBuffer("delete from ");
				sb.append(tableName);
				sb.append(" where id in(");
				sb.append(idsValue);
				sb.append(")");
				dbDAO.executeSQLForUpdate(sb.toString());
			}
			return true;
		} catch(Exception e) {
			log.info("批量操作删除实体出错: " + e.fillInStackTrace());
			return false;
		}
	}
	
	/**
	 * 获取后台已经登陆的管理员用户实体
	 * @Title: BaseAction
	 * @Description:    
	 * @param @return 
	 * @return 
	 * @throws
	 */
	public SystemUser getSystemUser() {
		if(getSession().get(ContextConstants.SCOPE_SYSTEM_USER) != null) {
			return (SystemUser) getSession().get(ContextConstants.SCOPE_SYSTEM_USER);
		} else {
			return null;
		}
	}
	
	/**
	 * 获取已登录会员
	 * @Title: BaseAction
	 * @Description:    
	 * @param @return 
	 * @return 
	 * @throws
	 */
	public Member getMember() {
		if(getSession().get(ContextConstants.SCOPE_MEMBER) != null) {
			return (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		} else {
			return null;
		}
	}
	
	/**
	 * 获取游客在session中的编号
	 * @Title: getRandomMember 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public String getVisitorMember() {
		if(getSession().get(ContextConstants.MEMBER_TEMP_RANDOM) != null) {
			return getSession().get(ContextConstants.MEMBER_TEMP_RANDOM).toString();
		} else {
			return "";
		}
	}
	
	@Override
	public CommonAnnex addAnnex(T entity, Object entityId, String annex, Integer type, String versionFlag) throws ServiceException{
		try {
			if(GeneralUtil.isNotNull(entityId)){
				CommonAnnex ba = null;
				List<CommonAnnex> list1 = commonAnnexService.queryList(type, entityId.toString(), versionFlag, entity.getClass().getSimpleName());
				if(GeneralUtil.isNotNull(annex)){
					if(list1 != null && list1.size() > 0){
						for(CommonAnnex b:list1){
							String ppp = getFileBasePath() + b.getAnnexPath();
							File file = new File(ppp);
							if(!file.exists()) {
								commonAnnexService.remove(b);
								if(GeneralUtil.isNotNull(ppp)) {
									FileUtil.deleteFile(ppp);
								}
							}
						}
					}
					ba = commonAnnexService.queryByUrl(annex.substring(0,annex.indexOf(ContextConstants.UPLOAD_SEPARATOR_VALUE)),entity.getClass().getSimpleName());
					if(GeneralUtil.isNull(ba)){
						CommonAnnex an=new CommonAnnex();
						an.setAnnexPath(annex.substring(0,annex.indexOf(ContextConstants.UPLOAD_SEPARATOR_VALUE)));
						an.setEntityId(entityId.toString());
						an.setAnnexType(type);
						an.setEntityName(entity.getClass().getSimpleName());
						an.setExtensionName(annex.substring(annex.lastIndexOf(".")+1));
						an.setTitle(annex.substring(annex.lastIndexOf(ContextConstants.UPLOAD_SEPARATOR_VALUE)+2));
						an.setOriginalName(an.getTitle());
						commonAnnexService.persist(an);
						return an; 
					}
				}else{
					if (list1 != null && list1.size() > 0) {
						for(CommonAnnex b : list1) {
							String ppp = getFileBasePath() + b.getAnnexPath();
							commonAnnexService.remove(b);
							if(GeneralUtil.isNotNull(ppp)) {
								FileUtil.deleteFile(ppp);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean addManyAnnex(T entity, Object entityId, List<String> annexs, Integer type, String versoinFlag) {
		try {
			if(GeneralUtil.isNotNull(entityId)){
				List<CommonAnnex> list = commonAnnexService.queryList(type, entityId.toString(), versoinFlag, entity.getClass().getSimpleName());
				if(annexs != null){
					CommonAnnex  ba = null;
					CommonEntity s = null;
					if(list != null && list.size() > 0){
						for(CommonAnnex b:list){
							String ppp = getFileBasePath() + b.getAnnexPath();
							File file = new File(ppp);
							if(!file.exists()) {
								commonAnnexService.remove(b);
								if(GeneralUtil.isNotNull(ppp)) {
									FileUtil.deleteFile(ppp);
								}
							}
						}
					}
					int value = annexs.size();
					if(value > 0){
						for(int i = 0;i < value;i++){
							ba = commonAnnexService.queryByUrl(annexs.get(i).substring(0,annexs.get(i).indexOf(ContextConstants.UPLOAD_SEPARATOR_VALUE)), entity.getClass().getSimpleName());
							if(ba == null){
								ba = new CommonAnnex();
								s = new CommonEntity();
								ba.setAnnexPath(annexs.get(i).substring(0,annexs.get(i).indexOf(ContextConstants.UPLOAD_SEPARATOR_VALUE)));
								ba.setEntityId(entityId.toString());
								ba.setAnnexType(type);
								ba.setEntityName(entity.getClass().getSimpleName());
								ba.setExtensionName(annexs.get(i).substring(annexs.get(i).lastIndexOf(".")+1,annexs.get(i).length()));
								ba.setTitle(annexs.get(i).substring(annexs.get(i).lastIndexOf(ContextConstants.UPLOAD_SEPARATOR_VALUE)+2));
								s.setOrderValue(value-i);
								s.setVersionFlag(versoinFlag);
								ba.setStatus(s);
								ba.setOriginalName(ba.getTitle());
								commonAnnexService.persist(ba);
								ba = null;
								s = null;
							}
							ba = null;
						}
					}
				}else{
					if (list != null && list.size() > 0) {
						for(CommonAnnex b : list) {
							String ppp = getFileBasePath() + b.getAnnexPath();
							commonAnnexService.remove(b);
							if(GeneralUtil.isNotNull(ppp)) {
								FileUtil.deleteFile(ppp);
							}
						}
					}
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public Boolean deleteAnnex(T entity, Object entityId, Integer type,
			String versoinFlag) throws ServiceException {
		try {
			if(GeneralUtil.isNotNull(entityId)){
				List<CommonAnnex> list = null;			
				list = commonAnnexService.queryList(type, entityId.toString(), versoinFlag, entity.getClass().getSimpleName());
				if(list != null && list.size() > 0){
					for(CommonAnnex a:list){
						CommonAnnex annex = commonAnnexService.queryById(CommonAnnex.class.getSimpleName(), a.getId());
						if(annex !=null){
							List<CommonAnnex> list2 = commonAnnexService.queryList(null, annex.getId(), annex.getStatus().getVersionFlag(), "CommonAnnex");
							if(list2 != null && list2.size() > 0){
								for(CommonAnnex an:list2){
									CommonAnnex an2 = commonAnnexService.queryById(CommonAnnex.class.getSimpleName(), an.getId());
									String ppp = getFileBasePath() + an2.getAnnexPath();
									File file = new File(ppp);
									if(file.exists()) {
										commonAnnexService.remove(an2);
										if(GeneralUtil.isNotNull(ppp)) {
											FileUtil.deleteFile(ppp);
										}
									}
								}
							}
							String ppp = getFileBasePath() + annex.getAnnexPath();
							File file = new File(ppp);
							commonAnnexService.remove(annex);
							if(file.exists()) {
								if(GeneralUtil.isNotNull(ppp)) {
									FileUtil.deleteFile(ppp);
								}
							}
						}
					}
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean deleteAllAnnex(T entity, Object entityId, String versoinFlag) throws ServiceException {
		try {
			if(GeneralUtil.isNotNull(entityId)){
				List<CommonAnnex> list = null;			
				list = commonAnnexService.queryByObjectIdAnnex(entity.getClass().getSimpleName(), entityId.toString(), versoinFlag);
				if(list != null && list.size() > 0){
					for(CommonAnnex a:list){
						CommonAnnex annex = commonAnnexService.queryById(CommonAnnex.class.getSimpleName(), a.getId());
						if(annex !=null){
							List<CommonAnnex> list2 = commonAnnexService.queryList(null, annex.getId(), annex.getStatus().getVersionFlag(), "CommonAnnex");
							if(list2 != null && list2.size() > 0){
								for(CommonAnnex an:list2){
									CommonAnnex an2 = commonAnnexService.queryById(CommonAnnex.class.getSimpleName(), an.getId());
									String ppp = getFileBasePath() + an2.getAnnexPath();
									File file = new File(ppp);
									commonAnnexService.remove(an2);
									if(file.exists()) {
										if(GeneralUtil.isNotNull(ppp)) {
											FileUtil.deleteFile(ppp);
										}
									}
								}
							}
							String ppp = getFileBasePath() + annex.getAnnexPath();
							File file = new File(ppp);
							commonAnnexService.remove(annex);
							if(file.exists()) {
								if(GeneralUtil.isNotNull(ppp)) {
									FileUtil.deleteFile(ppp);
								}
							}
						}
					}
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String getParameterValues(HttpServletRequest request) {
		try {
			StringBuffer result = new StringBuffer();
			Map<String, String[]> maps = request.getParameterMap();
			if(maps != null && !maps.isEmpty()) {
				result.append("?");
				for(String key : maps.keySet()) {
					String[] temp = (String[]) maps.get(key);
					result.append(key).append("=").append(temp[0]).append("&");
				}
				String tempValue = result.toString();
				tempValue = tempValue.substring(0, tempValue.length() - 1);
				return tempValue;
			}
			return "";
		} catch(Exception e) {
			return "";
		}
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public boolean validateSmsCodeIsOK(String mobile, String smsCode) throws ServiceException {
		//验证短信验证码是否生成
		if(getSession().get(mobile + ContextConstants.SMS_VERIFICATE_CODE) == null) {
			return false;
		}
		//验证是否输入短信验证码
		if(GeneralUtil.isNull(smsCode)) {
			return false;
		}
		String smsCodeSession = getSession().get(mobile + ContextConstants.SMS_VERIFICATE_CODE).toString();
		if(smsCode.equals(smsCodeSession)) {
			return true;
		}
		return false;
	}

	@Override
	public IDbDAO<T, ID> getDbDAO() {
		return dbDAO;
	}

	@Override
	public <B> List<B> queryAll(Class<B> c) {
		
		List<B> list = dbDAO.executeJPQLForQuery("select s from " + c.getName() + " s where s.status.deleteFlag=0 order by s.status.orderValue", c);
		return list;
	}
	
	@Override
	public <B> long count(Class<B> c) {
		
		long count = (Long)dbDAO.executeJPQLForQuerySingle("select count(*) from " + c.getName() + " s where s.status.deleteFlag=0");
		return count;
	}

	@Override
	public Pagination paginationQuery(String jpql, Object... params) {
		
		Pagination page = new Pagination();
		int start = "select ".length();
		int end = jpql.indexOf(" from");
		String obj = "select count(" + jpql.substring(start, end) + ")" + jpql.substring(end);
		Long count = dbDAO.executeJPQLForQuerySingle(obj, Long.class, params);
		List<?> list = dbDAO.executeJPQLForQuery(jpql, PaginationContext.getOffset(), PaginationContext.getPagesize(), params);
		page.setTotalCount(count);
		page.setPageList(list);
		return page;
	}

	@Override
	public Pagination paginationQuery(String jpql, Class<?> tableName, Object... params) {

		Pagination page = new Pagination();
		int start = "select ".length();
		int end = jpql.indexOf(" from");
		String obj = "select count(" + jpql.substring(start, end) + ")" + jpql.substring(end);
		Long count = dbDAO.executeJPQLForQuerySingle(obj, Long.class, params);
		List<?> list = dbDAO.executeJPQLForQuery(jpql, tableName, PaginationContext.getOffset(), PaginationContext.getPagesize(), params);
		page.setTotalCount(count);
		page.setPageList(list);
		return page;
	}
}
