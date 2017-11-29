package com.ticket.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * 离港航班
 * @ClassName: DepartFromPort   
 * @Description: 离港航班表
 * @author HiSay  
 * @date  2015-12-01 10:07:22
 *
 */
@Entity
@Table(name="ticket_DepartFromPort",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class DepartFromPort implements Serializable {

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
	 * 计划出港时间
	 */
	private Date std;
	
	/**
	 * 计划到达时间
	 */	
	private Date sta;
	
	/**
	 * 实际撤轮挡时间
	 */
	private Date atd;
	
	/**
	 * 实际出港时间
	 */
	private Date abt;
	
	/**
	 * 预计出港时间
	 */
	private Date etd;
	
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
	private String gat;
	
	/**
	 * 行李转盘
	 */
	private String baggage;
	
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
	 * 起飞地三字码
	 */
	private String org;
	
	/**
	 * 目的地三字码
	 */
	private String des;
	
	/**
	 * 经停1
	 */
	private String vi1;
	
	/**
	 * 经停2
	 */
	private String vi2;
	
	/**
	 * 经停3
	 */
	private String vi3;
	
	/**
	 * 经停4
	 */
	private String vi4;
	
	/**
	 * 经停5
	 */
	private String vi5;
	
	/**
	 * 经停6
	 */
	private String vi6;
	
	/**
	 * 主航班号
	 */
	private String mFlightNo;
	
	/**
	 * 值机柜台
	 */
	private String cid;
	
	/**
	 * 登机门开启时间（仅离港航班关注）
	 */
	private Date got;
	
	/**
	 * 下站到达时间（仅离港航班关注）
	 */
	private Date aan;
	
	/**
	 * 下站预计到港时间（仅离港航班关注）
	 */
	private Date ean;
	
	/**
	 * 下站计划到港时间（仅离港航班关注）
	 */
	private Date stn;
	
	/**
	 * 终到站计划时间（仅离港航班关注）
	 */
	private Date tdes;
	
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
	 * 机型
	 */
	private String ITY;
	
	/**
	 * 连接航班标识
	 */
	private String LKF;
	
	/**
	 * 值机柜台2
	 */
	private String CD2;

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

	public Date getEtd() {
		return etd;
	}

	public void setEtd(Date etd) {
		this.etd = etd;
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

	public String getGat() {
		return gat;
	}

	public void setGat(String gat) {
		this.gat = gat;
	}

	public String getBaggage() {
		return baggage;
	}

	public void setBaggage(String baggage) {
		this.baggage = baggage;
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

	public String getVi1() {
		return vi1;
	}

	public void setVi1(String vi1) {
		this.vi1 = vi1;
	}

	public String getVi2() {
		return vi2;
	}

	public void setVi2(String vi2) {
		this.vi2 = vi2;
	}

	public String getVi3() {
		return vi3;
	}

	public void setVi3(String vi3) {
		this.vi3 = vi3;
	}

	public String getVi4() {
		return vi4;
	}

	public void setVi4(String vi4) {
		this.vi4 = vi4;
	}

	public String getVi5() {
		return vi5;
	}

	public void setVi5(String vi5) {
		this.vi5 = vi5;
	}

	public String getVi6() {
		return vi6;
	}

	public void setVi6(String vi6) {
		this.vi6 = vi6;
	}

	public String getmFlightNo() {
		return mFlightNo;
	}

	public void setmFlightNo(String mFlightNo) {
		this.mFlightNo = mFlightNo;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public Date getGot() {
		return got;
	}

	public void setGot(Date got) {
		this.got = got;
	}

	public Date getAan() {
		return aan;
	}

	public void setAan(Date aan) {
		this.aan = aan;
	}

	public Date getEan() {
		return ean;
	}

	public void setEan(Date ean) {
		this.ean = ean;
	}

	public Date getStn() {
		return stn;
	}

	public void setStn(Date stn) {
		this.stn = stn;
	}

	public Date getTdes() {
		return tdes;
	}

	public void setTdes(Date tdes) {
		this.tdes = tdes;
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

	public Date getSta() {
		return sta;
	}

	public void setSta(Date sta) {
		this.sta = sta;
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

	public String getCD2() {
		return CD2;
	}

	public void setCD2(String cD2) {
		CD2 = cD2;
	}

	public Date getAtd() {
		return atd;
	}

	public void setAtd(Date atd) {
		this.atd = atd;
	}
	
}
