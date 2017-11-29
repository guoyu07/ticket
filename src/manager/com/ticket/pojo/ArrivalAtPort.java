package com.ticket.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * 进港航班
 * @ClassName: ArrivalAtPort   
 * @Description: 进港航班表
 * @author HiSay  
 * @date  2015-12-01 10:07:57
 *
 */
@Entity
@Table(name="ticket_ArrivalAtPort",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class ArrivalAtPort implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@Id
	private String id = UUID.randomUUID().toString();
	/**
	 * 实体状态
	 */
	@Embedded
	private CommonEntity status = new CommonEntity();
	
	/**
	 * 航班号
	 */
	private String flt;
	
	/**
	 * 航班日期
	 */
	private String flightDate;
	
	/**
	 * 航班重复次数
	 */
	private Integer flightRepeat;
	
	/**
	 * 计划起飞时间
	 */	
	private Date std;
	
	/**
	 * 计划到达时间
	 */	
	private Date sta;
	
	/**
	 * 实际到达时间
	 */
	private Date tdt;
	
	/**
	 * 预计到达时间
	 */
	private Date eta;
	
	/**
	 * 起飞机场三字码
	 */
	private String depCode;
	
	/**
	 * 落地机场三字码
	 */
	private String arrCode;
	
	/**
	 * 航班实时状态
	 */
	private String frs;
	
	/**
	 * 登机口
	 */
	private String gate;
	
	/**
	 * 行李提取转盘
	 */
	private String btt;
	
	/**
	 * 出港机位\到达机位
	 */
	private String stand;
	
	/**
	 * 登机状态：开始登机、催促登机、登机结束等
	 */
	private String boardStaus;
	
	/**
	 * 飞机编号
	 */
	private String airnum;
	
	/**
	 * 延误
	 */
	private String caacCode;
	
	/**
	 * VIP
	 */
	private String vip;
	
	/**
	 * 航班性质（大类）
	 */
	private String nat;
	
	/**
	 * 航班性质（大类细分）
	 */
	private String fst;
	
	/**
	 * 航班分类
	 */
	private String cla;
	
	/**
	 * 航段操作类型
	 */
	private String otc;

	/**
	 * 起始站
	 */
	private String org;
	
	/**
	 * 终点站
	 */
	private String des;

	/**
	 * 经停地
	 */
	private String pft;
	
	/**
	 * 经停地
	 */
	private String pft2;
	
	/**
	 * 经停地
	 */
	@Column
	private String pft3;
	
	/**
	 * 经停地
	 */
	private String pft4;
	
	/**
	 * 经停地
	 */
	private String pft5;
	
	/**
	 * 经停地
	 */
	private String pft6;
	
	/**
	 * 主航班号
	 */
	private String mFlightNo;
	
	/**
	 * 前站起飞时间（仅到港航班关注）
	 */
	private Date abp;
	
	/**
	 * 前站预计离港时间（仅到港航班关注）
	 */
	private Date edp;
	
	/**
	 * 前站计划离港时间（仅到港航班关注）
	 */
	private Date stp;
	
	/**
	 * 始发站计划时间（仅到港航班关注）
	 */
	private Date torg;
	
	/**
	 * 到达口
	 */
	private String cedn;

	/**
	 * 只有中转航班有此属性
	 */
	private String type;
	
	/**
	 * 航空公司两字码
	 */
	private String acw;

	/**
	 * 国内或国际
	 */
	private String doi;
	
	/**
	 * ICAO
	 */
	private String IFC;
	
	/**
	 * IATA
	 */
	private String FLC;
	
	/**
	 * 实际上轮档时间
	 */
	private Date Ata;
	
	/**
	 * 行李提取转盘
	 */
	private String CAR;
	
	/**
	 * 行李提取转盘2
	 */
	private String CR2;
	
	/**
	 * 机型
	 */
	private String ITY;
	
	/**
	 * 连接航班标识
	 */
	private String LKF;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CommonEntity getStatus() {
		return status;
	}

	public void setStatus(CommonEntity status) {
		this.status = status;
	}

	public String getFlt() {
		return flt;
	}

	public void setFlt(String flt) {
		this.flt = flt;
	}

	public String getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}

	public Integer getFlightRepeat() {
		return flightRepeat;
	}

	public void setFlightRepeat(Integer flightRepeat) {
		this.flightRepeat = flightRepeat;
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

	public Date getEta() {
		return eta;
	}

	public void setEta(Date eta) {
		this.eta = eta;
	}

	public String getDepCode() {
		return depCode;
	}

	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}

	public String getArrCode() {
		return arrCode;
	}

	public void setArrCode(String arrCode) {
		this.arrCode = arrCode;
	}

	public String getFrs() {
		return frs;
	}

	public void setFrs(String frs) {
		this.frs = frs;
	}

	public String getGate() {
		return gate;
	}

	public void setGate(String gate) {
		this.gate = gate;
	}

	public String getBtt() {
		return btt;
	}

	public void setBtt(String btt) {
		this.btt = btt;
	}

	public String getStand() {
		return stand;
	}

	public void setStand(String stand) {
		this.stand = stand;
	}

	public String getBoardStaus() {
		return boardStaus;
	}

	public void setBoardStaus(String boardStaus) {
		this.boardStaus = boardStaus;
	}

	public String getAirnum() {
		return airnum;
	}

	public void setAirnum(String airnum) {
		this.airnum = airnum;
	}

	public String getCaacCode() {
		return caacCode;
	}

	public void setCaacCode(String caacCode) {
		this.caacCode = caacCode;
	}

	public String getVip() {
		return vip;
	}

	public void setVip(String vip) {
		this.vip = vip;
	}

	public String getNat() {
		return nat;
	}

	public void setNat(String nat) {
		this.nat = nat;
	}

	public String getFst() {
		return fst;
	}

	public void setFst(String fst) {
		this.fst = fst;
	}

	public String getCla() {
		return cla;
	}

	public void setCla(String cla) {
		this.cla = cla;
	}

	public String getOtc() {
		return otc;
	}

	public void setOtc(String otc) {
		this.otc = otc;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getPft() {
		return pft;
	}

	public void setPft(String pft) {
		this.pft = pft;
	}

	public String getPft2() {
		return pft2;
	}

	public void setPft2(String pft2) {
		this.pft2 = pft2;
	}

	public String getPft3() {
		return pft3;
	}

	public void setPft3(String pft3) {
		this.pft3 = pft3;
	}

	public String getPft4() {
		return pft4;
	}

	public void setPft4(String pft4) {
		this.pft4 = pft4;
	}

	public String getPft5() {
		return pft5;
	}

	public void setPft5(String pft5) {
		this.pft5 = pft5;
	}

	public String getPft6() {
		return pft6;
	}

	public void setPft6(String pft6) {
		this.pft6 = pft6;
	}

	public String getmFlightNo() {
		return mFlightNo;
	}

	public void setmFlightNo(String mFlightNo) {
		this.mFlightNo = mFlightNo;
	}

	public Date getAbp() {
		return abp;
	}

	public void setAbp(Date abp) {
		this.abp = abp;
	}

	public Date getEdp() {
		return edp;
	}

	public void setEdp(Date edp) {
		this.edp = edp;
	}

	public Date getStp() {
		return stp;
	}

	public void setStp(Date stp) {
		this.stp = stp;
	}

	public Date getTorg() {
		return torg;
	}

	public void setTorg(Date torg) {
		this.torg = torg;
	}

	public String getCedn() {
		return cedn;
	}

	public void setCedn(String cedn) {
		this.cedn = cedn;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAcw() {
		return acw;
	}

	public void setAcw(String acw) {
		this.acw = acw;
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

	public String getIFC() {
		return IFC;
	}

	public void setIFC(String iFC) {
		IFC = iFC;
	}

	public String getFLC() {
		return FLC;
	}

	public void setFLC(String fLC) {
		FLC = fLC;
	}

	public Date getAta() {
		return Ata;
	}

	public void setAta(Date ata) {
		Ata = ata;
	}

	public String getCAR() {
		return CAR;
	}

	public void setCAR(String cAR) {
		CAR = cAR;
	}

	public String getCR2() {
		return CR2;
	}

	public void setCR2(String cR2) {
		CR2 = cR2;
	}

	public String getITY() {
		return ITY;
	}

	public void setITY(String iTY) {
		ITY = iTY;
	}

	public String getLKF() {
		return LKF;
	}

	public void setLKF(String lKF) {
		LKF = lKF;
	}

}
