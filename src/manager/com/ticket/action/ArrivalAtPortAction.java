package com.ticket.action;

import java.util.Date;

import javax.annotation.Resource;

import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.ArrivalAtPort;
import com.ticket.pojo.SqlParam.Condition;
import com.ticket.pojo.SqlParamGroup;
import com.ticket.service.IArrivalAtPortService;
import com.ticket.util.DateUtil;

/**
 * 进港航班控制器
 * @ClassName: ArrivalAtPortAction   
 * @Description:  提供进港航班的相关操作方法. 
 * @author HiSay  
 * @date 2015-12-01 10:07:57
 *
 */
public class ArrivalAtPortAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//进港航班的业务层
	@Resource private IArrivalAtPortService arrivalAtPortService = null;
	//进港航班实体
	private ArrivalAtPort arrivalAtPort = null;
	//主键
	private String id = null;
    //航班号
	private String flt = null;
    //航空公司两字码
	private String acw = null;
    //航班实时状态
	private String frs = null;
    //国内或国际
	private String doi = null;
    //经停地
	private String pft = null;
    //起始站
	private String org = null;
    //计划起飞时间
	private Date std = null;
    //实际起飞时间
	private Date abt = null;
    //预计到达时间
	private Date eta = null;
    //计划到达时间
	private Date sta = null;
    //实际到达时间
	private Date tdt = null;
    //行李提取转盘
	private String btt = null;
	//到达口
	private String cedn = null;
	
	/**
	 * 管理进港航班实体
	 * @Title: ArrivalAtPortAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		
		SqlParamGroup group = new SqlParamGroup("s.flt", Condition.eq, flt);
		group.and("s.flightDate", Condition.eq, DateUtil.formatDateToShortString(startTime));
		this.setPageModule(arrivalAtPortService.paginationQuery("select s from " + ArrivalAtPort.class.getName() + " s " + group.toString(true) + " order by s.status.createTime desc", group.getParamArray()));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageArrivalAtPort";
	}
	
	/**
	 * 导入数据
	 * @throws Exception
	 */
	public void importFromExcel() throws Exception{
		System.out.println(arrivalAtPortService.importFromFile(null));
	}
	public ArrivalAtPort getArrivalAtPort() {
		return arrivalAtPort;
	}
	public void setArrivalAtPort(ArrivalAtPort arrivalAtPort) {
		this.arrivalAtPort = arrivalAtPort;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFlt() {
		return flt;
	}
	public void setFlt(String flt) {
		this.flt = flt;
	}
	public String getAcw() {
		return acw;
	}
	public void setAcw(String acw) {
		this.acw = acw;
	}
	public String getFrs() {
		return frs;
	}
	public void setFrs(String frs) {
		this.frs = frs;
	}
	public String getDoi() {
		return doi;
	}
	public void setDoi(String doi) {
		this.doi = doi;
	}
	public String getPft() {
		return pft;
	}
	public void setPft(String pft) {
		this.pft = pft;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public Date getStd() {
		return std;
	}
	public void setStd(Date std) {
		this.std = std;
	}
	public Date getAbt() {
		return abt;
	}
	public void setAbt(Date abt) {
		this.abt = abt;
	}
	public Date getEta() {
		return eta;
	}
	public void setEta(Date eta) {
		this.eta = eta;
	}
	public Date getSta() {
		return sta;
	}
	public void setSta(Date sta) {
		this.sta = sta;
	}
	public Date getTdt() {
		return tdt;
	}
	public void setTdt(Date tdt) {
		this.tdt = tdt;
	}
	public String getBtt() {
		return btt;
	}
	public void setBtt(String btt) {
		this.btt = btt;
	}

	public String getCedn() {
		return cedn;
	}

	public void setCedn(String cedn) {
		this.cedn = cedn;
	}
}
