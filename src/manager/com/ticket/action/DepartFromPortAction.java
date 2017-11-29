package com.ticket.action;

import java.util.Date;

import javax.annotation.Resource;

import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.DepartFromPort;
import com.ticket.pojo.SqlParam.Condition;
import com.ticket.pojo.SqlParamGroup;
import com.ticket.service.IDepartFromPortService;
import com.ticket.util.DateUtil;

/**
 * 离港航班控制器
 * @ClassName: DepartFromPortAction   
 * @Description:  提供离港航班的相关操作方法. 
 * @author HiSay  
 * @date 2015-12-01 10:07:22
 *
 */
public class DepartFromPortAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//离港航班的业务层
	@Resource private IDepartFromPortService departFromPortService = null;
	//离港航班实体
	private DepartFromPort departFromPort = null;
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
    //计划离港时间
	private Date std = null;
    //目的地三字码
	private String des = null;
    //计划到达时间
	private Date sta = null;
    //实际到达时间
	private Date tdt = null;
    //值机柜台
	private String cid = null;
    //登机口
	private String gat = null;
    //预计登机时间
	private Date got = null;
    //预计出港时间
	private Date etd = null;
    //实际出港时间
	private Date abt = null;
	//用户输入航班编号
	private String flightNumber = null;
	//航班日期
	private String flightDate = null;
	/**
	 * 管理离港航班实体
	 * @Title: DepartFromPortAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		
		SqlParamGroup group = new SqlParamGroup("s.flt", Condition.eq, flt);
		group.and("s.flightDate", Condition.eq, DateUtil.formatDateToShortString(startTime));
		this.setPageModule(departFromPortService.paginationQuery("select s from " + DepartFromPort.class.getName() + " s " + group.toString(true) + " order by s.status.createTime desc", group.getParamArray()));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageDepartFromPort";
	}
	
	/**
	 * 从excel导入数据
	 * @return
	 * @throws Exception
	 */
	public void importFromExcel() throws Exception{
		System.out.println(departFromPortService.importFromFile(null));
	}
	
	/**
	 * 根据航班编号和日期查询航班信息
	 * @return
	 * @throws ServiceException
	 */
	public String queryFlightByNo() throws ServiceException {
		DepartFromPort departFromPort = departFromPortService.queryByFlightNoAndDate(flt, flightDate, versionFlag);
		this.setDepartFromPort(departFromPort);
		return "queryResultByNo";
	}
	
	public DepartFromPort getDepartFromPort() {
		return departFromPort;
	}
	public void setDepartFromPort(DepartFromPort departFromPort) {
		this.departFromPort = departFromPort;
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
	public Date getStd() {
		return std;
	}
	public void setStd(Date std) {
		this.std = std;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
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
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getGat() {
		return gat;
	}
	public void setGat(String gat) {
		this.gat = gat;
	}
	public Date getGot() {
		return got;
	}
	public void setGot(Date got) {
		this.got = got;
	}
	public Date getEtd() {
		return etd;
	}
	public void setEtd(Date etd) {
		this.etd = etd;
	}
	public Date getAbt() {
		return abt;
	}
	public void setAbt(Date abt) {
		this.abt = abt;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}

}
