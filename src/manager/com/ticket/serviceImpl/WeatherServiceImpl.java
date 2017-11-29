package com.ticket.serviceImpl;

import java.math.BigDecimal;
import java.util.Date;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Weather;
import com.ticket.service.IWeatherService;


/**
 * 处理天气的服务层接口
 * @author tuyou
 */
public class WeatherServiceImpl extends BaseServiceImpl<Weather, String> implements IWeatherService{
	
	public Weather persist(Date FVTIME, BigDecimal FTEMP, BigDecimal FDP, String FDT, String FSP,
			String FGS, String FCT1, String FCT2, String FCH1, String FCH2, 
			boolean FCAV, boolean FVBY, BigDecimal FWT, boolean FVA) throws ServiceException{
		
		Weather weather = new Weather();
		weather.setFVTIME(FVTIME);
		weather.setFTEMP(FTEMP);
		weather.setFDP(FDP);
		weather.setFDT(FDT);
		weather.setFSP(FSP);
		weather.setFGS(FGS);
		weather.setFCT1(FCT1);
		weather.setFCT2(FCT2);
		weather.setFCH1(FCH1);
		weather.setFCH2(FCH2);
		weather.setFCAV(FCAV);
		weather.setFVBY(FVBY);
		weather.setFWT(FWT);
		weather.setFVA(FVA);
		dbDAO.persist(weather);
		
		return weather;
	}
	
	public Weather merge(String id, Date FVTIME, BigDecimal FTEMP, BigDecimal FDP, String FDT, String FSP,
			String FGS, String FCT1, String FCT2, String FCH1, String FCH2, 
			boolean FCAV, boolean FVBY, BigDecimal FWT, boolean FVA) throws ServiceException{
		
		Weather weather = dbDAO.get(Weather.class, id);
		weather.setFVTIME(FVTIME);
		weather.setFTEMP(FTEMP);
		weather.setFDP(FDP);
		weather.setFDT(FDT);
		weather.setFSP(FSP);
		weather.setFGS(FGS);
		weather.setFCT1(FCT1);
		weather.setFCT2(FCT2);
		weather.setFCH1(FCH1);
		weather.setFCH2(FCH2);
		weather.setFCAV(FCAV);
		weather.setFVBY(FVBY);
		weather.setFWT(FWT);
		weather.setFVA(FVA);
		dbDAO.merge(weather);
		
		return weather;
	}
}