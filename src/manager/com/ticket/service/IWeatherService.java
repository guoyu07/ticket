package com.ticket.service;

import java.math.BigDecimal;
import java.util.Date;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Weather;


/**
 * 处理天气的服务层接口
 * @author tuyou
 */
public interface IWeatherService extends IBaseService<Weather, String> {
	/**
	 * 保存天气
	 * @param FVTIME
	 * @param FTEMP
	 * @param FDP
	 * @param FDT
	 * @param FSP
	 * @param FGS
	 * @param FCT1
	 * @param FCT2
	 * @param FCH1
	 * @param FCH2
	 * @param FCAV
	 * @param FVBY
	 * @param FWT
	 * @param FVA
	 * @return
	 * @throws ServiceException
	 */
	Weather persist(Date FVTIME, BigDecimal FTEMP, BigDecimal FDP, String FDT, String FSP,
			String FGS, String FCT1, String FCT2, String FCH1, String FCH2, 
			boolean FCAV, boolean FVBY, BigDecimal FWT, boolean FVA) throws ServiceException;
	/**
	 * 修改天气
	 * @param id
	 * @param FVTIME
	 * @param FTEMP
	 * @param FDP
	 * @param FDT
	 * @param FSP
	 * @param FGS
	 * @param FCT1
	 * @param FCT2
	 * @param FCH1
	 * @param FCH2
	 * @param FCAV
	 * @param FVBY
	 * @param FWT
	 * @param FVA
	 * @return
	 * @throws ServiceException
	 */
	Weather merge(String id, Date FVTIME, BigDecimal FTEMP, BigDecimal FDP, String FDT, String FSP,
			String FGS, String FCT1, String FCT2, String FCH1, String FCH2, 
			boolean FCAV, boolean FVBY, BigDecimal FWT, boolean FVA) throws ServiceException;
}