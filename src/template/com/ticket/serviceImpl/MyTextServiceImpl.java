package com.ticket.serviceImpl;


import java.util.ArrayList;
import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.MyText;
import com.ticket.pojo.Pagination;
import com.ticket.service.IMyTextService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.PaginationContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 我的记事本业务接口实现类
 * @ClassName: IMyTextService   
 * @Description: 提供我的记事本操作的增删改查   
 * @author HiSay  
 * @date 2016-02-15 11:26:54
 *
 */
public class MyTextServiceImpl extends BaseServiceImpl<MyText, String> implements IMyTextService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(MyTextServiceImpl.class);
	
	@Override
	public MyText merge(String id, EmployeeInfo employeeInfo,String title,String content) throws ServiceException {
		MyText myText = dbDAO.queryById(this.getTableNameFromEntity(MyText.class), id);
		if(employeeInfo != null){
			myText.setEmployeeInfo_id(employeeInfo.getId());
		}
		myText.setTitle(DecoderUtil.UtfDecoder(title));
		myText.setContent(DecoderUtil.UtfDecoder(content));
		CommonEntity status = myText.getStatus();
		status.setVersionFlag(versionFlag);
		myText.setStatus(status);
		dbDAO.merge(myText);
		return myText;
	}

	@Override
	public MyText persist(EmployeeInfo employeeInfo,String title,String content) throws ServiceException {
		MyText myText = new MyText();
		if(employeeInfo != null){
			myText.setEmployeeInfo_id(employeeInfo.getId());
		}
		myText.setTitle(DecoderUtil.UtfDecoder(title));
		myText.setContent(DecoderUtil.UtfDecoder(content));
		CommonEntity status = myText.getStatus();
		status.setVersionFlag(versionFlag);
		myText.setStatus(status);
		dbDAO.persist(myText);
		return myText;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		MyText myText = dbDAO.queryById(this.getTableNameFromEntity(MyText.class), id);
		dbDAO.remove(myText);
		return true;
	}

	@Override
	public Pagination queryAll(EmployeeInfo employeeInfo, Integer isRead,
			String order, Integer pageSize) throws ServiceException {
		try {
			StringBuffer sb = new StringBuffer();
			List<Object[]> objects = new ArrayList<Object[]>();
			sb.append("");
			if(employeeInfo != null){
				sb.append("and s.employeeInfo_id=?3 ");
				objects.add(new Object[]{3,employeeInfo.getId()});
			}
			if(GeneralUtil.isNotNull(isRead)){
				sb.append("and s.isRead=?4 ");
				objects.add(new Object[]{4,isRead});
			}
			if(GeneralUtil.isNull(order)){
				order = "s.status.createTime desc,s.isRead asc";
			}
			return dbDAO.queryByPageModuleNew(MyText.class.getSimpleName(), 0, versionFlag,
					sb.toString(), objects, order, PaginationContext.getOffset(), pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean changeRead(String id, Integer read) throws ServiceException {
		try {
			MyText myText = queryById(MyText.class.getSimpleName(), id);
			myText.setIsRead(read);
			this.merge(myText);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}