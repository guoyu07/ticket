package com.ticket.serviceImpl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.AuditVisitRecord;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.Pagination;
import com.ticket.service.IAuditVisitRecordService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.PaginationContext;

/**
 * 审核拜访记录业务接口实现类
 * @ClassName: IAuditVisitRecordService   
 * @Description: 提供审核拜访记录操作的增删改查   
 * @author HiSay  
 * @date 2016-04-29 10:39:00
 *
 */
public class AuditVisitRecordServiceImpl extends BaseServiceImpl<AuditVisitRecord, String> implements IAuditVisitRecordService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(AuditVisitRecordServiceImpl.class);
	
	@Override
	public boolean merge(String id, String record_id,String employee_id,String remark, String versionFlag) throws ServiceException {
		AuditVisitRecord auditVisitRecord = dbDAO.queryById(this.getTableNameFromEntity(AuditVisitRecord.class), id);
		auditVisitRecord.setRecord_id(DecoderUtil.UtfDecoder(record_id));
		auditVisitRecord.setEmployee_id(DecoderUtil.UtfDecoder(employee_id));
		auditVisitRecord.setRemark(DecoderUtil.UtfDecoder(remark));
		CommonEntity status = auditVisitRecord.getStatus();
		status.setVersionFlag(versionFlag);
		auditVisitRecord.setStatus(status);
		dbDAO.merge(auditVisitRecord);
		return true;
	}

	@Override
	public boolean persist(String record_id,String employee_id,String phoneOrOut,String remark,Integer auditState, String versionFlag) throws ServiceException {
		AuditVisitRecord auditVisitRecord = new AuditVisitRecord();
		auditVisitRecord.setRecord_id(DecoderUtil.UtfDecoder(record_id));
		auditVisitRecord.setEmployee_id(DecoderUtil.UtfDecoder(employee_id));
		auditVisitRecord.setRemark(DecoderUtil.UtfDecoder(remark));
		auditVisitRecord.setPhoneOrOut(phoneOrOut);
		CommonEntity status = auditVisitRecord.getStatus();
		status.setVersionFlag(versionFlag);
		status.setAudit(auditState);
		auditVisitRecord.setStatus(status);
		dbDAO.persist(auditVisitRecord);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		AuditVisitRecord auditVisitRecord = dbDAO.queryById(this.getTableNameFromEntity(AuditVisitRecord.class), id);
		dbDAO.remove(auditVisitRecord);
		return true;
	}

	@Override
	public Pagination queryByPhone(Integer auditState,int pageSize, String versionFlag) throws ServiceException {
		if(GeneralUtil.isNull(auditState)){
			
			return dbDAO.queryByPageModule(AuditVisitRecord.class.getSimpleName(), deleteFlag, versionFlag, " and s.phoneOrOut='phone'", null, orderBy, PaginationContext.getOffset(), pageSize);
		}else{
			if(auditState==1||auditState==0){
				
				return dbDAO.queryByPageModule(AuditVisitRecord.class.getSimpleName(), deleteFlag, versionFlag, " and s.phoneOrOut='phone' and s.status.audit =?3", new Object[]{auditState}, orderBy, PaginationContext.getOffset(), pageSize);
			}else{
				return dbDAO.queryByPageModule(AuditVisitRecord.class.getSimpleName(), deleteFlag, versionFlag, " and s.phoneOrOut='phone'", null, orderBy, PaginationContext.getOffset(), pageSize);
				
			}
		}
	}

	@Override
	public Pagination queryByOut(Integer auditState,int pageSize, String versionFlag)
			throws ServiceException {
		if(GeneralUtil.isNull(auditState)){
			
			return dbDAO.queryByPageModule(AuditVisitRecord.class.getSimpleName(), deleteFlag, versionFlag, " and s.phoneOrOut='out'", null, orderBy, PaginationContext.getOffset(), pageSize);
		}else{
			
			if(auditState==1||auditState==0){
				
				return dbDAO.queryByPageModule(AuditVisitRecord.class.getSimpleName(), deleteFlag, versionFlag, " and s.phoneOrOut='out' and s.status.audit=?3", new Object[]{auditState}, orderBy, PaginationContext.getOffset(), pageSize);
			}else{
				return dbDAO.queryByPageModule(AuditVisitRecord.class.getSimpleName(), deleteFlag, versionFlag, " and s.phoneOrOut='out'", null, orderBy, PaginationContext.getOffset(), pageSize);
				
			}
		}
	}

}