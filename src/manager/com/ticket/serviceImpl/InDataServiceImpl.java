package com.ticket.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportPlan;
import com.ticket.service.IFlightCompanyService;
import com.ticket.service.IInDataService;
import com.ticket.util.AirportPlaneUtil;
import com.ticket.util.DateUtil;
import com.ticket.util.ReadExcelUtils;

/**
 * 表示的是导入航班计划数据的
 * @author zzf
 *
 */
public class InDataServiceImpl extends BaseServiceImpl<AirportPlan, String> implements IInDataService {
	@Resource
	private IFlightCompanyService flightCompanyService;
	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean inDataExcelToDataBase(String filePath) throws Exception {

		ReadExcelUtils excelReader = new ReadExcelUtils(filePath);
		// 对读取Excel表格内容测试
		List<AirportPlan> list = excelReader.readExcelContent();
		for (int i = 0; i < list.size(); i++) {

			dbDAO.persist(list.get(i));
		}

		return true;
	}

	@Override
	public AirportPlan getFirsrtData(String start, String end,
			String versionFlag) {
		List<AirportPlan> list = dbDAO.queryByList(AirportPlan.class.getSimpleName(), deleteFlag, versionFlag, " and s.dept = ?3 and s.arrive = ?4", new Object[]{start,end}, orderBy, 5);
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<AirportPlan> queryFlight(Date searchDate, String start,
			String arrive, String versionFlag) throws ServiceException {
		return dbDAO.queryByList(AirportPlan.class.getSimpleName(), deleteFlag, versionFlag, " and s.dept =?3 and s.arrive =?4 and s.start_date<=?5 and s.end_date >=?6", new Object[]{start,arrive,searchDate,searchDate}, " s.std asc", null);
	}

	@Override
	public AirportPlan getDataByNo(String flightNo, String versionFlag) {
		List<AirportPlan> list = dbDAO.queryByList(AirportPlan.class.getSimpleName(), deleteFlag, versionFlag, " and s.flt = ?3", new Object[]{flightNo}, orderBy, 5);
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<AirportPlan> queryFlightByNo(String flightNumber,
			Date searchDate, String versionFlag) throws ServiceException {
		List<AirportPlan> list = dbDAO.queryByList(AirportPlan.class.getSimpleName(), deleteFlag, versionFlag, " and s.flt = ?3 and start_date<=?4 and s.end_date>=?5", new Object[]{flightNumber,searchDate,searchDate}, orderBy, null);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public AirportPlan queryByNoAndDateAndCycle(String flightDate,
			String setoutCityFourCode, String arriveCityFourCode, String cycle,
			String versionFlag) throws ServiceException {
		Date date = DateUtil.parseShortStringToDate(flightDate);
		List<AirportPlan> list = dbDAO.queryByList(AirportPlan.class.getSimpleName(), deleteFlag, versionFlag, "and s.dept =?3 and s.arrive =?4 and s.start_date<=?5 and s.end_date >=?6", new Object[]{setoutCityFourCode,arriveCityFourCode,date,date}, orderBy, null);
		if(list != null && !list.isEmpty()){
			for(AirportPlan ap : list){
				if(AirportPlaneUtil.weekIsAirportPlan(cycle, date)){
					return ap;
				}
			}
		}
		return null;
	}

	@Override
	public AirportPlan queryFlightByNoAndDate(String flightNumber,String versionFlag) throws ServiceException {
		return dbDAO.queryByCustom(AirportPlan.class.getSimpleName(), deleteFlag, versionFlag, " and s.flt =?3", new Object[]{flightNumber});
	}

	@Override
	public List<AirportPlan> getListGroupByFlt(String flightNumber) throws ServiceException {
		return dbDAO.queryByList(AirportPlan.class.getSimpleName(), deleteFlag, versionFlag, "and s.flt =?3 group by s.start_date,s.end_date", new Object[]{flightNumber}, orderBy, null);
	}

	@Override
	public List<AirportPlan> getListGroupByPlanDate(String setoutCity, String reachCity) throws ServiceException {
		return dbDAO.queryByList(AirportPlan.class.getSimpleName(), deleteFlag, versionFlag, "and s.dept =?3 and s.arrive = ?4", new Object[]{setoutCity,reachCity}, orderBy, null);
	}

	@Override
	public AirportPlan getDataByNoAndSADate(String flightNumber,
			Date startDate, Date endDate,String flag, String versionFlag)
			throws ServiceException {
		List<AirportPlan> list = new ArrayList<AirportPlan>();
		if("depart".equals(flag)){
			list = dbDAO.queryByList(AirportPlan.class.getSimpleName(), deleteFlag, versionFlag, "and flt =?3 and s.start_date = ?4 and s.end_date =?5 and s.dept='ZPPP'", new Object[]{flightNumber,startDate,endDate}, orderBy, null);
		}else{
			list = dbDAO.queryByList(AirportPlan.class.getSimpleName(), deleteFlag, versionFlag, "and flt =?3 and s.start_date = ?4 and s.end_date =?5 and s.arrive='ZPPP'", new Object[]{flightNumber,startDate,endDate}, orderBy, null);
		}
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public AirportPlan queryArrivalByNoAndDate(String flightNumber,
			String flightDate, String versionFlag) throws ServiceException {
		String regStr = flightNumber.substring(0, 3);
		String str = "^[A-Za-z]{3}$";
		Pattern pattern = Pattern.compile(str);  
		Matcher matcher=pattern.matcher(regStr);  
		boolean flag = matcher.matches();
		List<AirportPlan> apList = null;
		if(flag){
			apList = this.getListGroupByFlt(flightNumber);
		}else{
			
			apList = this.getListGroupByFlt(flightCompanyService.transferFlightNumber(flightNumber));
		}
		if(apList != null && !apList.isEmpty()){
			for(AirportPlan ap : apList){
				//查询航班计划表，取出计划时间段
				Date startDate = ap.getStart_date();
				Date endDate = ap.getEnd_date();
				//3 判断是否在航班计划时段之内
				boolean duringDate = AirportPlaneUtil.airportPlanisInCycle(startDate, endDate, DateUtil.parseShortStringToDate(flightDate));
				if(!duringDate){
					continue;
				}else{
					boolean isInPlan = AirportPlaneUtil.weekIsAirportPlan(ap.getCycle(), DateUtil.parseShortStringToDate(flightDate));
					if(isInPlan){
						if("ZPPP".equals(ap.getArrive())){
							return ap;
						}
					}
				}
			}
		}
		return null;
	}

	@Override
	public AirportPlan queryDepartByNoAndDate(String flightNumber,
			String flightDate, String versionFlag) throws ServiceException {
		String regStr = flightNumber.substring(0, 3);
		String str = "^[A-Za-z]{3}$";
		Pattern pattern = Pattern.compile(str);  
		Matcher matcher=pattern.matcher(regStr);  
		boolean flag = matcher.matches();
		List<AirportPlan> apList = null;
		if(flag){
			apList = this.getListGroupByFlt(flightNumber);
		}else{
			
			apList = this.getListGroupByFlt(flightCompanyService.transferFlightNumber(flightNumber));
		}
		if(apList != null && !apList.isEmpty()){
			for(AirportPlan ap : apList){
				//查询航班计划表，取出计划时间段
				Date startDate = ap.getStart_date();
				Date endDate = ap.getEnd_date();
				//3 判断是否在航班计划时段之内
				boolean duringDate = AirportPlaneUtil.airportPlanisInCycle(startDate, endDate, DateUtil.parseShortStringToDate(flightDate));
				if(!duringDate){
					continue;
				}else{
					boolean isInPlan = AirportPlaneUtil.weekIsAirportPlan(ap.getCycle(), DateUtil.parseShortStringToDate(flightDate));
					if(isInPlan){
						if("ZPPP".equals(ap.getDept())){
							return ap;
						}
					}
				}
			}
		}
		return null;
	}
}
