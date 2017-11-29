package com.ticket.serviceImpl;


import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.Industry;
import com.ticket.service.IIndustryService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * 企业行业业务接口实现类
 * @ClassName: IIndustryService   
 * @Description: 提供企业行业操作的增删改查   
 * @author HiSay  
 * @date 2016-01-11 09:44:02
 *
 */
public class IndustryServiceImpl extends BaseServiceImpl<Industry, String> implements IIndustryService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(IndustryServiceImpl.class);
	
	@Override
	public boolean merge(String id, String name,String introduce,String parentId) throws ServiceException {
		Industry industry = dbDAO.queryById(this.getTableNameFromEntity(Industry.class), id);
		industry.setName(DecoderUtil.UtfDecoder(name));
		industry.setIntroduce(DecoderUtil.UtfDecoder(introduce));
		if(GeneralUtil.isNotNull(parentId)){
			Industry parent = queryById(Industry.class.getSimpleName(), parentId);
			if(parent.getId().equals(industry.getId())){
				industry.setParent(null);
			}else{
				industry.setParent(parent);
			}
		}else{
			industry.setParent(null);
		}
		CommonEntity status = industry.getStatus();
		status.setVersionFlag(versionFlag);
		industry.setStatus(status);
		dbDAO.merge(industry);
		changeDeep(industry);
		changePath(industry);
		return true;
	}

	@Override
	public boolean persist(String name,String introduce,String parentId) throws ServiceException {
		Industry industry = new Industry();
		industry.setName(DecoderUtil.UtfDecoder(name));
		industry.setIntroduce(DecoderUtil.UtfDecoder(introduce));
		if (!"-1".equals(parentId)) {
			Industry parent = queryById(Industry.class.getSimpleName(), parentId);
			industry.setParent(parent);
			industry.setDeep(parent.getDeep()+1);
		} else {
			industry.setParent(null);
			industry.setDeep(0);
		}
		CommonEntity status = industry.getStatus();
		status.setVersionFlag(versionFlag);
		industry.setStatus(status);
		dbDAO.persist(industry);
		changePath(industry);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		Industry industry = dbDAO.queryById(this.getTableNameFromEntity(Industry.class), id);
		dbDAO.remove(industry);
		return true;
	}
	@Override
	public void changeDeep(Industry departmentInfo)
			throws ServiceException {
		try {
			Industry productClass2 = queryById(Industry.class.getSimpleName(), departmentInfo.getId());
			if(productClass2.getParent() != null){
				productClass2.setDeep(productClass2.getParent().getDeep()+1);
				this.merge(productClass2);
				if(productClass2.getChilds() != null && productClass2.getChilds().size() > 0){
					for(Industry child:productClass2.getChilds()){
						changeDeep(child);
					}
				}
			}else{
				productClass2.setDeep(0);
				this.merge(productClass2);
				if(productClass2.getChilds() != null && productClass2.getChilds().size() > 0){
					for(Industry child:productClass2.getChilds()){
						changeDeep(child);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void changePath(Industry productClass)
			throws ServiceException {
		try {
			if(productClass.getParent() != null){
				productClass.setPath(productClass.getParent().getPath()+productClass.getId()+",");
			}else{
				productClass.setPath(","+productClass.getId()+",");
			}
			this.merge(productClass);
			if(productClass.getChilds() != null && productClass.getChilds().size() > 0){
				for(Industry child:productClass.getChilds()){
					changePath(child);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String createIndustryOptionHtml(String id)
			throws ServiceException {
		try {
			List<Industry> list = queryClassByParentIsNull(null);
			return createIndustryOptionHtml(list, 0, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String createIndustryOptionHtml(List<Industry> topList,
			int level, String selectId) throws ServiceException {
		try {
			StringBuffer result = new StringBuffer();
			if (topList != null && topList.size() > 0) {
				Iterator<Industry> it = topList.iterator();
				if (selectId == null) {
					selectId = "-1";
				}
				while (it.hasNext()) {
					Industry info = it.next();
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
							result.append(createIndustryOptionHtml(
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
	public String createIndustryTree() throws ServiceException {
		try {
			List<Industry> list = queryClassByParentIsNull(null);
			return createIndustryTree(list);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String createIndustryTree(List<Industry> list)
			throws ServiceException {
		try {
			StringBuilder sb = new StringBuilder();
			if (list != null) {

				Iterator<Industry> it = list.iterator();

				while (it.hasNext()) {
					Industry info = it.next();

					String currentId = info.getId();
					Industry _parentStoreType = info.getParent();
					String parentId = "-1";
					if (_parentStoreType != null) {
						parentId = _parentStoreType.getId();
					}
					StringBuilder sbControl = new StringBuilder();
					sbControl.append("<div class=\"dTreeRight\">");
					sbControl
							.append("<input type=\"hidden\" name=\"id\" value=\""
									+ info.getId() + "\" >");
					sbControl
							.append("<span class=\"dTree_add\"><input class=\"changeOrderValue\" name=\"orderValue\" value=\""
									+ info.getStatus().getOrderValue()
									+ "\" ></span>");
					sbControl
							.append("<span class=\"dTree_add\"><a href=\"javascript:;\" class=\"add\" attrValue=\""+info.getId()+"\">添加子行业</a></span>");
					sbControl
							.append("<span class=\"dTree_add\"><a href=\"javascript:;\" class=\"update\" attrValue=\""+info.getId()+"\">修改</a></span>");
					sbControl
							.append("<span class=\"dTree_add\"><a href=\"javascript:;\" class=\"remove\" attrValue=\""+info.getId()+"\">删除</a></span>");
					sbControl.append("</div>");

					// id, pid, name, url, title, target, icon, iconOpen, open,
					// controlString
					sb.append("d.add('" + currentId + "', '" + parentId
							+ "', '" + info.getName()
							+ "', '#', '', '', '', '', '', '"
							+ sbControl.toString() + "');");

					List<Industry> children = info.getChilds();
					if (children != null && children.size() > 0) {
						sb.append(createIndustryTree(children));
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
			Industry productClass = queryById(Industry.class.getSimpleName(), classId);
			if (productClass != null) {
				StringBuffer result = new StringBuffer();
				/** 如果是子栏目 */
				if (productClass.getChilds().size() <= 0) {
					result.append(productClass.getId());
					return result.toString();
				}
				/** 如果是父栏目 */
				result.append(productClass.getId()).append(",");
				getChildsIds(productClass.getChilds(), result);
				return result.toString().substring(0,
						result.toString().lastIndexOf(","));
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void getChildsIds(List<Industry> sets,
			StringBuffer result) {
		Iterator<Industry> iter = sets.iterator();
		Industry n = null;
		while (iter.hasNext()) {
			n = iter.next();
			result.append(n.getId()).append(",");
			if (n.getChilds().size() > 0) {
				getChildsIds(n.getChilds(), result);
			}
		}
	}

	@Override
	public List<Industry> queryClassByParentIsNull(Integer size)
			throws ServiceException {
		try {
			return dbDAO.queryByListNew(Industry.class.getSimpleName(), deleteFlag, versionFlag,
					"and s.parent is null ", null, "s.status.orderValue desc,s.id desc", size);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}