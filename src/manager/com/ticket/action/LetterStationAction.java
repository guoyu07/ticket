package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.LetterStation;
import com.ticket.pojo.StationLetterReadLog;
import com.ticket.pojo.StationLetterSendRecord;
import com.ticket.service.IChannelCustomerInfoService;
import com.ticket.service.IDepartmentInfoService;
import com.ticket.service.IEmployeeInfoService;
import com.ticket.service.ILetterStationService;
import com.ticket.service.IStationLetterReadLogService;
import com.ticket.service.IStationLetterSendRecordService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 站内信控制器
 * @ClassName: LetterStationAction   
 * @Description:  提供站内信的相关操作方法. 
 * @author HiSay  
 * @date 2016-01-03 13:52:54
 *
 */
public class LetterStationAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//站内信的业务层
	@Resource private ILetterStationService letterStationService = null;
	@Resource private ISystemOperationLogService logService = null;
	@Resource private IDepartmentInfoService departmentInfoService = null; //员工部门信息的业务层
	@Resource private IEmployeeInfoService employeeInfoService = null; //员工信息的业务层
	@Resource private IStationLetterReadLogService stationLetterReadLogService = null; //站内信阅读日志的业务层
	@Resource private IChannelCustomerInfoService channelCustomerInfoService = null; //渠道客户的业务层
	@Resource private IStationLetterSendRecordService stationLetterSendRecordService = null; //渠道客户的业务层
	//站内信实体
	private LetterStation letterStation = null;
	private String id,systemUser_id,content;
	
	private String url = null; //站内信链接地址
	
	private String title = null; //站内信标题
	
	private String deptHtml = null; //部门树
	
	private String idsValue = null; //要发送的对象id列表
	
	private Integer sendType = null; //发送类别1 部门 2 渠道分类 3 都有
	
	private List<StationLetterSendRecord> deptSendRecords = null;//根据部门发送站内信记录列表
	
	private List<StationLetterSendRecord> channelTypeSendRecords = null;//根据渠道分类发送站内信记录列表
	
	/**
	 * 添加站内信
	 * @Title: LetterStationAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addLetterStation";
		} else {
			if(GeneralUtil.isNull(content)) {
				data = AjaxData.responseError(getText("content.required"));
				return JSON;
			}
			if(getSystemEmployeeInfo() == null){
				data = AjaxData.responseError(getText("systemuser.cannotoper"));
				return JSON;
			}
			//保存站内信实体
			boolean isSuc = letterStationService.persist(getSystemEmployeeInfo().getId(),title, content,url);
			//根据保存结果返回页面
			if(isSuc) {
				
				data = AjaxData.responseSuccess(getText("addSuccess"));
			} else {
				data = AjaxData.responseError(getText("addFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 编辑站内信
	 * @return
	 * @throws ServiceException
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setLetterStation(letterStationService.queryById(LetterStation.class.getSimpleName(), id));
			return "editLetterStation";
		} else {
			if(GeneralUtil.isNull(content)) {
				data = AjaxData.responseError(getText("content.required"));
				return JSON;
			}
			//保存站内信实体
			boolean isSuc = letterStationService.merge(id,title,content,url);
			//根据保存结果返回页面
			if(isSuc) {
				
				data = AjaxData.responseSuccess(getText("editSuccess"));
			} else {
				data = AjaxData.responseError(getText("editFailed"));
			}
			return JSON;
		}
	}
	/**
	 * @author wangjiafeng
	 * 查看站内信详细
	 * @method view
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-3 下午02:31:48
	 */
	public String view() throws Exception{
		this.setLetterStation(letterStationService.queryById(LetterStation.class.getSimpleName(), id));
		//letterStation = letterStationService.view(id);
		return "view";
	}
	
	
	/**
	 * 管理站内信实体
	 * @Title: LetterStationAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(letterStationService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageLetterStation";
	}
	
	/**
	 * 查看回收站
	 * @Title: LetterStationAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(letterStationService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleLetterStation";
	}
	
	/**
	 * 逻辑删除站内信对象
	 * @Title: LetterStationAction
	 * @Description: 把站内信对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = letterStationService.logicDeleteEntity(LetterStation.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除站内信";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除站内信对象
	 * @Title: LetterStationAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = letterStationService.remove(id);
		if(isSuc) {
			String logContent = "物理删除站内信";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个站内信对象
	 * @Title: LetterStationAction
	 * @Description: 从回收站还原站内信对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = letterStationService.restoreEntity(LetterStation.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原站内信";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核站内信对象
	 * @Title: LetterStationAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = letterStationService.auditEntity(LetterStation.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核删除站内信";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: LetterStationAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = letterStationService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作站内信";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	/**
	 * 发送站内信
	 * @return
	 * @throws ServiceException
	 */
	public String send() throws ServiceException{
		if(GeneralUtil.isNull(operationFlag)){
			this.setDeptHtml(departmentInfoService.getDeptTree(departmentInfoService.queryFirstDept(versionFlag)));
			return "selectSendLetterObject";
		}else{
			if(getSystemEmployeeInfo() == null){
				data = AjaxData.responseError(getText("systemuser.cannotoper"));
				return JSON;
			}
			LetterStation ls = letterStationService.queryById(LetterStation.class.getSimpleName(), id);
			if(GeneralUtil.isNull(sendType)){
				return JSON;
			}else{
				if(sendType == 1 ){
					try {
						String[] depts = idsValue.split(",");
						for(String dept_id : depts){
							Integer sendCount = 0;
							List<EmployeeInfo> empList = employeeInfoService.queryByDepartmentId(dept_id, versionFlag);
							if(empList != null && !empList.isEmpty()){
								for(EmployeeInfo emp : empList){
									
									StationLetterReadLog stationLetterReadLog = new StationLetterReadLog();
									stationLetterReadLog.setIsRead(0);
									stationLetterReadLog.setLetter_id(ls.getId());
									stationLetterReadLog.setObject_id(emp.getId());
									stationLetterReadLog.setReadDate(null);
									stationLetterReadLogService.persist(stationLetterReadLog);
									sendCount ++;
								}
							}
							StationLetterSendRecord stationLetterSendRecord = new StationLetterSendRecord();
							stationLetterSendRecord.setLetter_id(ls.getId());
							stationLetterSendRecord.setObject_id(dept_id);
							stationLetterSendRecord.setObjectFlag("employee");
							stationLetterSendRecord.setOpertator_id(getSystemEmployeeInfo().getId());
							stationLetterSendRecord.setSendCount(sendCount);
							
							stationLetterSendRecordService.persist(stationLetterSendRecord);
						}
						
						ls.setIsSend(1);
						letterStationService.merge(ls);
						data = AjaxData.responseSuccess("");
					} catch (Exception e) {
						data = AjaxData.responseError("");
						e.printStackTrace();
					}
				}else if(sendType == 2){
					try {
						String[] typeIds = idsValue.split(",");
						for(String type_id : typeIds){
							Integer sendCount = 0;
							List<ChannelCustomerInfo> customerList = channelCustomerInfoService.queryChannelCustomerList(type_id, versionFlag);
							if(customerList != null && !customerList.isEmpty()){
								for(ChannelCustomerInfo customer : customerList){
									StationLetterReadLog stationLetterReadLog = new StationLetterReadLog();
									
									stationLetterReadLog.setIsRead(0);
									stationLetterReadLog.setLetter_id(ls.getId());
									stationLetterReadLog.setObject_id(customer.getId());
									stationLetterReadLog.setReadDate(null);
									
									stationLetterReadLogService.persist(stationLetterReadLog);
									sendCount ++;
								}
							}
							
							StationLetterSendRecord stationLetterSendRecord = new StationLetterSendRecord();
							stationLetterSendRecord.setLetter_id(ls.getId());
							stationLetterSendRecord.setObject_id(type_id);
							stationLetterSendRecord.setObjectFlag("customer");
							stationLetterSendRecord.setOpertator_id(getSystemEmployeeInfo().getId());
							stationLetterSendRecord.setSendCount(sendCount);
							
							stationLetterSendRecordService.persist(stationLetterSendRecord);
						}
						ls.setIsSend(1);
						letterStationService.merge(ls);
						data = AjaxData.responseSuccess("");
					} catch (Exception e) {
						data = AjaxData.responseError("");
						e.printStackTrace();
					}
				}else if(sendType == 3){
					try {
						String[] ids = idsValue.split("#");
						String deptIds = ids[0];
						String typeIds = ids[1];
						String[] depts = deptIds.split(",");
						String[] types = typeIds.split(",");
						for(String dept_id :depts){
							Integer sendCount = 0;
							List<EmployeeInfo> empList = employeeInfoService.queryByDepartmentId(dept_id, versionFlag);
							if(empList != null && !empList.isEmpty()){
								for(EmployeeInfo emp : empList){
									
									StationLetterReadLog stationLetterReadLog = new StationLetterReadLog();
									stationLetterReadLog.setIsRead(0);
									stationLetterReadLog.setLetter_id(ls.getId());
									stationLetterReadLog.setObject_id(emp.getId());
									stationLetterReadLog.setReadDate(null);
									
									stationLetterReadLogService.persist(stationLetterReadLog);
									sendCount ++;
								}
								StationLetterSendRecord stationLetterSendRecord = new StationLetterSendRecord();
								stationLetterSendRecord.setLetter_id(ls.getId());
								stationLetterSendRecord.setObject_id(dept_id);
								stationLetterSendRecord.setObjectFlag("employee");
								stationLetterSendRecord.setOpertator_id(getSystemEmployeeInfo().getId());
								stationLetterSendRecord.setSendCount(sendCount);
								
								stationLetterSendRecordService.persist(stationLetterSendRecord);
							}
						}
						for(String type_id : types){
							Integer sendCount = 0;
							List<ChannelCustomerInfo> customerList = channelCustomerInfoService.queryChannelCustomerList(type_id, versionFlag);
							if(customerList != null && !customerList.isEmpty()){
								for(ChannelCustomerInfo customer : customerList){
									StationLetterReadLog stationLetterReadLog = new StationLetterReadLog();
									
									stationLetterReadLog.setIsRead(0);
									stationLetterReadLog.setLetter_id(ls.getId());
									stationLetterReadLog.setObject_id(customer.getId());
									stationLetterReadLog.setReadDate(null);
									stationLetterReadLogService.persist(stationLetterReadLog);
									sendCount ++;
								}
								StationLetterSendRecord stationLetterSendRecord = new StationLetterSendRecord();
								stationLetterSendRecord.setLetter_id(ls.getId());
								stationLetterSendRecord.setObject_id(type_id);
								stationLetterSendRecord.setObjectFlag("customer");
								stationLetterSendRecord.setOpertator_id(getSystemEmployeeInfo().getId());
								stationLetterSendRecord.setSendCount(sendCount);
								
								stationLetterSendRecordService.persist(stationLetterSendRecord);
							}
							
						}
						ls.setIsSend(1);
						letterStationService.merge(ls);
						data = AjaxData.responseSuccess("");
					} catch (Exception e) {
						data = AjaxData.responseError("");
						e.printStackTrace();
					}
				}else{
					data = AjaxData.responseError("");
				}
			}
			return JSON;
		}
	}
	
	/**
	 * 统计信息发送记录
	 * @return
	 * @throws ServiceException
	 */
	public String statistic() throws ServiceException{
		List<StationLetterSendRecord> deptSendlist = stationLetterSendRecordService.queryByDept(id,versionFlag);
		List<StationLetterSendRecord> channelTypeSendlist = stationLetterSendRecordService.queryByChannelType(id,versionFlag);
		this.setDeptSendRecords(deptSendlist);
		this.setChannelTypeSendRecords(channelTypeSendlist);
		this.setLetterStation(letterStationService.queryById(LetterStation.class.getSimpleName(), id));
		return "letterSendStatistic";
	}
	
	public LetterStation getLetterStation() {
		return letterStation;
	}
	public void setLetterStation(LetterStation letterStation) {
		this.letterStation = letterStation;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSystemUser_id() {
		return systemUser_id;
	}
	public void setSystemUser_id(String systemUser_id) {
		this.systemUser_id = systemUser_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDeptHtml() {
		return deptHtml;
	}

	public void setDeptHtml(String deptHtml) {
		this.deptHtml = deptHtml;
	}

	public String getIdsValue() {
		return idsValue;
	}

	public void setIdsValue(String idsValue) {
		this.idsValue = idsValue;
	}

	public Integer getSendType() {
		return sendType;
	}

	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}

	public List<StationLetterSendRecord> getDeptSendRecords() {
		return deptSendRecords;
	}

	public void setDeptSendRecords(List<StationLetterSendRecord> deptSendRecords) {
		this.deptSendRecords = deptSendRecords;
	}

	public List<StationLetterSendRecord> getChannelTypeSendRecords() {
		return channelTypeSendRecords;
	}

	public void setChannelTypeSendRecords(
			List<StationLetterSendRecord> channelTypeSendRecords) {
		this.channelTypeSendRecords = channelTypeSendRecords;
	}

	public IStationLetterReadLogService getStationLetterReadLogService() {
		return stationLetterReadLogService;
	}

	public void setStationLetterReadLogService(
			IStationLetterReadLogService stationLetterReadLogService) {
		this.stationLetterReadLogService = stationLetterReadLogService;
	}
}
