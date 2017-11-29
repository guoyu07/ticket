package com.ticket.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 天气表（数据由MQ传输过来）
 * @author tuyou
 */
@Entity
@Table(name="ticket_weather",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class Weather implements Serializable {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */ 
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
	 * 气象信息发布时间
	 */
	@Column
	public Date FVTIME;
	
	/**
	 * 温度
	 */
	@Column(precision=10, scale=4)
	public BigDecimal FTEMP;
	
	/**
	 * 结露点
	 */
	@Column(precision=10, scale=4)
	public BigDecimal FDP;
	
	/**
	 * 风向
	 */
	public String FDT;
	
	/**
	 * 风速
	 */
	public String FSP;
	
	/**
	 * 阵风速
	 */
	public String FGS;
	
	/**
	 * 云量1
	 */
	public String FCT1;
	
	/**
	 * 云量2
	 */
	public String FCT2;
	
	/**
	 * 云高1
	 */
	public String FCH1;
	
	/**
	 * 云高2
	 */
	public String FCH2;
	
	/**
	 * 云层及能见度是否良好
	 */
	public boolean FCAV;
	
	/**
	 * 能见度
	 */
	public boolean FVBY;
	
	/**
	 * 天气类型
	 */
	@Column(precision=10, scale=4)
	public BigDecimal FWT;
	
	/**
	 * 风力预报可变因素
	 */
	public boolean FVA;

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

	public Date getFVTIME() {
		return FVTIME;
	}

	public void setFVTIME(Date fVTIME) {
		FVTIME = fVTIME;
	}

	public BigDecimal getFTEMP() {
		return FTEMP;
	}

	public void setFTEMP(BigDecimal fTEMP) {
		FTEMP = fTEMP;
	}

	public BigDecimal getFDP() {
		return FDP;
	}

	public void setFDP(BigDecimal fDP) {
		FDP = fDP;
	}

	public String getFDT() {
		return FDT;
	}

	public void setFDT(String fDT) {
		FDT = fDT;
	}

	public String getFSP() {
		return FSP;
	}

	public void setFSP(String fSP) {
		FSP = fSP;
	}

	public String getFGS() {
		return FGS;
	}

	public void setFGS(String fGS) {
		FGS = fGS;
	}

	public String getFCT1() {
		return FCT1;
	}

	public void setFCT1(String fCT1) {
		FCT1 = fCT1;
	}

	public String getFCT2() {
		return FCT2;
	}

	public void setFCT2(String fCT2) {
		FCT2 = fCT2;
	}

	public String getFCH1() {
		return FCH1;
	}

	public void setFCH1(String fCH1) {
		FCH1 = fCH1;
	}

	public String getFCH2() {
		return FCH2;
	}

	public void setFCH2(String fCH2) {
		FCH2 = fCH2;
	}

	public boolean isFCAV() {
		return FCAV;
	}

	public void setFCAV(boolean fCAV) {
		FCAV = fCAV;
	}

	public boolean isFVBY() {
		return FVBY;
	}

	public void setFVBY(boolean fVBY) {
		FVBY = fVBY;
	}

	public BigDecimal getFWT() {
		return FWT;
	}

	public void setFWT(BigDecimal fWT) {
		FWT = fWT;
	}

	public boolean isFVA() {
		return FVA;
	}

	public void setFVA(boolean fVA) {
		FVA = fVA;
	}
	
	
}
