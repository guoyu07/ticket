package com.ticket.serviceImpl;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.DepartmentInfo;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.service.IChannelCustomerInfoService;
import com.ticket.service.IDepartmentInfoService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * 部门信息业务接口实现类
 * @ClassName: IDepartmentInfoService   
 * @Description: 提供部门信息操作的增删改查   
 * @author HiSay  
 * @date 2015-11-02 22:46:07
 *
 */
public class DepartmentInfoServiceImpl extends BaseServiceImpl<DepartmentInfo, String> implements IDepartmentInfoService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(DepartmentInfoServiceImpl.class);
	
	//渠道客户的业务层
	@Resource private IChannelCustomerInfoService channelCustomerInfoService  = null;
	@Override
	public boolean merge(String id, String name,String introduce, String parentId) throws ServiceException {
		DepartmentInfo departmentInfo = dbDAO.queryById(this.getTableNameFromEntity(DepartmentInfo.class), id);
		departmentInfo.setName(DecoderUtil.UtfDecoder(name));
		departmentInfo.setIntroduce(DecoderUtil.UtfDecoder(introduce));
		CommonEntity status = departmentInfo.getStatus();
		status.setVersionFlag(versionFlag);
		departmentInfo.setStatus(status);
		if("-1".equals(parentId)){
			departmentInfo.setParent(null);
		}else{
			departmentInfo.setParent(queryById(DepartmentInfo.class.getSimpleName(), parentId));
		}
		dbDAO.merge(departmentInfo);
		changeDeep(departmentInfo);
		changePath(departmentInfo);
		return true;
	}

	@Override
	public boolean persist(String name,String introduce, String parentId) throws ServiceException {
		DepartmentInfo departmentInfo = new DepartmentInfo();
		departmentInfo.setName(DecoderUtil.UtfDecoder(name));
		departmentInfo.setIntroduce(DecoderUtil.UtfDecoder(introduce));
		CommonEntity status = departmentInfo.getStatus();
		status.setVersionFlag(versionFlag);
		departmentInfo.setStatus(status);
		if (!"-1".equals(parentId)) {
			DepartmentInfo parent = queryById(DepartmentInfo.class.getSimpleName(), parentId);
			departmentInfo.setParent(parent);
			departmentInfo.setDeep(parent.getDeep()+1);
		} else {
			departmentInfo.setParent(null);
			departmentInfo.setDeep(0);
		}
		dbDAO.persist(departmentInfo);
		changePath(departmentInfo);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		DepartmentInfo departmentInfo = dbDAO.queryById(this.getTableNameFromEntity(DepartmentInfo.class), id);
		dbDAO.remove(departmentInfo);
		return true;
	}

	@Override
	public boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException {
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(DepartmentInfo.class.getSimpleName(), idsValue, null);
		return true;
	}

	@Override
	public List<DepartmentInfo> queryByList(String versionFlag) throws ServiceException {
		return dbDAO.queryByList(DepartmentInfo.class.getSimpleName(), deleteFlag, versionFlag, null, null, orderBy, null);
	}

	@Override
	public void changeDeep(DepartmentInfo departmentInfo)
			throws ServiceException {
		try {
			DepartmentInfo productClass2 = queryById(DepartmentInfo.class.getSimpleName(), departmentInfo.getId());
			if(productClass2.getParent() != null){
				productClass2.setDeep(productClass2.getParent().getDeep()+1);
				this.merge(productClass2);
				if(productClass2.getChilds() != null && productClass2.getChilds().size() > 0){
					for(DepartmentInfo child:productClass2.getChilds()){
						changeDeep(child);
					}
				}
			}else{
				productClass2.setDeep(0);
				this.merge(productClass2);
				if(productClass2.getChilds() != null && productClass2.getChilds().size() > 0){
					for(DepartmentInfo child:productClass2.getChilds()){
						changeDeep(child);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void changePath(DepartmentInfo productClass)
			throws ServiceException {
		try {
			if(productClass.getParent() != null){
				productClass.setPath(productClass.getParent().getPath()+productClass.getId()+",");
			}else{
				productClass.setPath(","+productClass.getId()+",");
			}
			this.merge(productClass);
			if(productClass.getChilds() != null && productClass.getChilds().size() > 0){
				for(DepartmentInfo child:productClass.getChilds()){
					changePath(child);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String createDepartmentInfoOptionHtml(String id)
			throws ServiceException {
		try {
			List<DepartmentInfo> list = queryClassByParentIsNull(null);
			return createDepartmentInfoOptionHtml(list, 0, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String createDepartmentInfoOptionHtml(List<DepartmentInfo> topList,
			int level, String selectId) throws ServiceException {
		try {
			StringBuffer result = new StringBuffer();
			if (topList != null && topList.size() > 0) {
				Iterator<DepartmentInfo> it = topList.iterator();
				if (selectId == null) {
					selectId = "-1";
				}
				while (it.hasNext()) {
					DepartmentInfo info = it.next();
					if (info != null) {
						int index = level;
						String step = "";

						while (index > 0) {
							step += "&nbsp;&nbsp;";
							index--;
						}
						if (info.getParent() != null) {
							if (it.hasNext() == false) {
								step += "└&nbsp;";
							} else {
								step += "├&nbsp;";
							}
						}
						if (info.getId().equals(selectId)) {
							result.append("<option value=\"" + info.getId()
									+ "\" selected=\"selected\">" + step
									+ info.getName() + "</option>");
						} else {
							result.append("<option value=\"" + info.getId()
									+ "\">" + step + info.getName()
									+ "</option>");
						}
						if (info.getChilds().size() > 0) {
							result.append(createDepartmentInfoOptionHtml(
									info.getChilds(), level + 1, selectId));
						}
					}
				}
			}
			return result.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String createDepartmentInfoTree() throws ServiceException {
		try {
			List<DepartmentInfo> list = queryClassByParentIsNull(null);
			return createDepartmentInfoTree(list);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String createDepartmentInfoTree(List<DepartmentInfo> list)
			throws ServiceException {
		try {
			StringBuilder sb = new StringBuilder();
			if (list != null) {

				Iterator<DepartmentInfo> it = list.iterator();

				while (it.hasNext()) {
					DepartmentInfo info = it.next();

					String currentId = info.getId();
					DepartmentInfo _parentStoreType = info.getParent();
					String parentId = "-1";
					if (_parentStoreType != null) {
						parentId = _parentStoreType.getId();
					}
					StringBuilder sbControl = new StringBuilder();
					sbControl.append("<div class=\"dTreeRight\">");
					sbControl.append("<input type=\"hidden\" name=\"id\" value=\"" + info.getId() + "\" >");
					sbControl.append("<span class=\"dTree_add\"><input class=\"changeOrderValue\" name=\"orderValue\" value=\""
									+ info.getStatus().getOrderValue() + "\" ></span>");
					sbControl.append("<span class=\"dTree_add\"><a href=\"javascript:;\" class=\"editInCharge\" attrValue=\""+info.getId()+"\">部门负责人</a></span>");
					sbControl.append("<span class=\"dTree_add\"><a href=\"javascript:;\" class=\"add\" attrValue=\""+info.getId()+"\">添加子部门</a></span>");
					sbControl.append("<span class=\"dTree_add\"><a href=\"javascript:;\" class=\"update\" attrValue=\""+info.getId()+"\">修改</a></span>");
					sbControl.append("<span class=\"dTree_add\"><a href=\"javascript:;\" class=\"remove\" attrValue=\""+info.getId()+"\">删除</a></span>");
					sbControl.append("</div>");

					// id, pid, name, url, title, target, icon, iconOpen, open,
					// controlString
					sb.append("d.add('" + currentId + "', '" + parentId
							+ "', '" + info.getName()
							+ "', '#', '', '', '', '', '', '"
							+ sbControl.toString() + "');");

					List<DepartmentInfo> children = info.getChilds();
					if (children != null && children.size() > 0) {
						sb.append(createDepartmentInfoTree(children));
					}
				}
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String parseClassIds(String classId) throws ServiceException {
		try {
			DepartmentInfo productClass = queryById(DepartmentInfo.class.getSimpleName(), classId);
			if (productClass != null) {
				StringBuffer result = new StringBuffer();
				/** 如果是子栏目 */
				if (productClass.getChilds().size() <= 0) {
					result.append("'");
					result.append(productClass.getId());
					result.append("'");
					return result.toString();
				}
				/** 如果是父栏目 */
				
				result.append("'");
				result.append(productClass.getId());
				result.append("'");
				result.append(",");
				getChildsIds(productClass.getChilds(), result);
				return result.toString().substring(0, result.toString().lastIndexOf(","));
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void getChildsIds(List<DepartmentInfo> sets,
			StringBuffer result) {
		Iterator<DepartmentInfo> iter = sets.iterator();
		DepartmentInfo n = null;
		while (iter.hasNext()) {
			n = iter.next();
			result.append("'");
			result.append(n.getId());
			result.append("'");
			result.append(",");
			if (n.getChilds().size() > 0) {
				getChildsIds(n.getChilds(), result);
			}
		}
	}

	@Override
	public List<DepartmentInfo> queryClassByParentIsNull(Integer size)
			throws ServiceException {
		try {
			return dbDAO.queryByListNew(DepartmentInfo.class.getSimpleName(), deleteFlag, versionFlag,
					"and s.parent is null ", null, "s.status.orderValue desc,s.id desc", size);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<DepartmentInfo> queryFirstDept(String versionFlag)
			throws ServiceException {
		return dbDAO.queryByList(DepartmentInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.parent is null", null, orderBy, null);
	}

	@Override
	public String getDeptTree(Collection<DepartmentInfo> depts) throws ServiceException {
		if(depts == null || depts.isEmpty())//如果顶级部门为空返回空字符串
		{
			return "";
		}
		StringBuilder sb = new StringBuilder();//定义一个动态字符串
		
		Iterator<DepartmentInfo> it = depts.iterator();
		
		while (it.hasNext()) {//判断有没有下一个元素
			DepartmentInfo dept = it.next();
			
			if(dept.getStatus().getDeleteFlag().intValue()==ContextConstants.STATUS_OF_ONE){
				continue; //如果该部门处于删除状态(在回收站里)，则跳过执行
			}
			
			String currentId = dept.getId();
			String parentId = "-1";
			
			DepartmentInfo parentDept = null;
			
			if(GeneralUtil.isNotNull(dept.getParent())){
				parentDept = dbDAO.queryById(this.getTableNameFromEntity(DepartmentInfo.class), dept.getParent().getId());
			}
			
			if(parentDept != null){
				
				parentId = parentDept.getId();
			}
			
			StringBuilder sbTwo = new StringBuilder();
		
			Collection<DepartmentInfo> childs = dbDAO.queryByList(this.getTableNameFromEntity(DepartmentInfo.class), deleteFlag, versionFlag, "and s.parent =?3", new Object[]{dept}, orderBy, null);
			
			if(childs != null && !childs.isEmpty()){
				sbTwo.append("<div class=\"dTreeRight\">");
				sbTwo.append("</div>");
				sb.append("d.add('"+ currentId +"', '"+ parentId +"', '"+ dept.getName() +"', '#', '', '', '', '', '', '"+ sbTwo.toString() +"');");
				sb.append(this.getDeptTree(childs));
			} else{
				sbTwo.append("<div class=\"dTreeRight\">");
				
				sbTwo.append("<span><input name=\"selectedDept\" type=\"checkbox\" value=\""+dept.getId()+"\"/></span>");
				
				sbTwo.append("</div>");
				
				sb.append("d.add('"+ currentId +"', '"+ parentId +"', '"+ dept.getName() +"', '#', '', '', '', '', '', '"+ sbTwo.toString() +"');");
			}
		}
		
		return sb.toString();
	}

	@Override
	public List<DepartmentInfo> getChildList(DepartmentInfo dept)
			throws ServiceException {
		return dbDAO.queryByList(DepartmentInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.parent = ?3", new Object[]{dept}, orderBy, null);
	}

	@Override
	public DepartmentInfo getByEmployee(String employee_id) {
		
		DepartmentInfo department = dbDAO.executeJPQLForQuerySingle("select t from " + DepartmentInfo.class.getName() + " t," + EmployeeInfo.class.getName() + " e"
				+ " where e.department_id=t.id and e.id=?", DepartmentInfo.class, employee_id);
		return department;
	}

	@Override
	public DepartmentInfo getByName(String name) throws ServiceException {
		DepartmentInfo departmentInfo = (DepartmentInfo)dbDAO.executeJPQLForQuerySingle("select c from " + DepartmentInfo.class.getName() + " c where c.name = ?", name);
		return departmentInfo;
	}

	@Override
	public List<DepartmentInfo> queryListByCurrentId(String current_id,
			String versionFlag) throws ServiceException {
		return dbDAO.queryByList(DepartmentInfo.class.getSimpleName(), deleteFlag, versionFlag, " and s.path like ?3", new Object[]{"%"+current_id+"%"}, orderBy, null);
	}

	@Override
	public String getChildIdsBycurrentId(String department_id) throws ServiceException {
		StringBuilder sb = new StringBuilder();
		String jpql = "select t from "+DepartmentInfo.class.getName()+" t,"+DepartmentInfo.class.getName()+" t1 where t1.path like ? and t.id = ?";
		List<DepartmentInfo> list = dbDAO.executeJPQLForQuery(jpql, DepartmentInfo.class, "%"+department_id+"%",department_id);
		if(list != null && !list.isEmpty()){
			for(DepartmentInfo departmentInfo :list){
				sb.append("'");
				sb.append(departmentInfo.getId());
				sb.append("'");
				sb.append(",");
			}
		}
		return sb.toString().substring(0, sb.toString().length()-1);
	}


}