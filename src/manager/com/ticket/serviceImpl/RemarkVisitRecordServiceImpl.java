package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.RemarkVisitRecord;
import com.ticket.service.IRemarkVisitRecordService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.PaginationContext;

/**
 * 备注拜访记录业务接口实现类
 * @ClassName: IRemarkVisitRecordService   
 * @Description: 提供备注拜访记录操作的增删改查   
 * @author HiSay  
 * @date 2016-04-28 15:38:02
 *
 */
public class RemarkVisitRecordServiceImpl extends BaseServiceImpl<RemarkVisitRecord, String> implements IRemarkVisitRecordService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(RemarkVisitRecordServiceImpl.class);
	
	@Override
	public boolean merge(String id, String record_id,String employee_id,String remark, String versionFlag) throws ServiceException {
		RemarkVisitRecord remarkVisitRecord = dbDAO.queryById(this.getTableNameFromEntity(RemarkVisitRecord.class), id);
		remarkVisitRecord.setRecord_id(DecoderUtil.UtfDecoder(record_id));
		remarkVisitRecord.setEmployee_id(DecoderUtil.UtfDecoder(employee_id));
		remarkVisitRecord.setRemark(DecoderUtil.UtfDecoder(remark));
		CommonEntity status = remarkVisitRecord.getStatus();
		status.setVersionFlag(versionFlag);
		remarkVisitRecord.setStatus(status);
		dbDAO.merge(remarkVisitRecord);
		return true;
	}

	@Override
	public boolean persist(String record_id,String employee_id,String remark, String versionFlag) throws ServiceException {
		RemarkVisitRecord remarkVisitRecord = new RemarkVisitRecord();
		remarkVisitRecord.setRecord_id(DecoderUtil.UtfDecoder(record_id));
		remarkVisitRecord.setEmployee_id(DecoderUtil.UtfDecoder(employee_id));
		remarkVisitRecord.setRemark(DecoderUtil.UtfDecoder(remark));
		CommonEntity status = remarkVisitRecord.getStatus();
		status.setVersionFlag(versionFlag);
		remarkVisitRecord.setStatus(status);
		dbDAO.persist(remarkVisitRecord);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		RemarkVisitRecord remarkVisitRecord = dbDAO.queryById(this.getTableNameFromEntity(RemarkVisitRecord.class), id);
		dbDAO.remove(remarkVisitRecord);
		return true;
	}

	@Override
	public Pagination queryEntityByRecordId(String record_id,
			String versionFlag, int pageSize) throws ServiceException {
		return dbDAO.queryByPageModule(RemarkVisitRecord.class.getSimpleName(), deleteFlag, versionFlag, "and s.record_id =?3", new Object[]{record_id}, orderBy, PaginationContext.getOffset(), pageSize);
	}

	@Override
	public List<RemarkVisitRecord> queryListByRecordId(String record_id,
			String versionFlag) throws ServiceException {
		return dbDAO.queryByList(RemarkVisitRecord.class.getSimpleName(), deleteFlag, versionFlag, " and s.record_id =?3", new Object[]{record_id}, orderBy, null);
	}

}