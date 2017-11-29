package com.ticket.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ticket.exception.ServiceException;

/**
 * @ClassName AirportPlaneUtil
 * @description 航班计划工具类
 * @author dfq
 * @date 2016-3-23 17:52:00
 */
public class AirportPlaneUtil {
	
	/**
	 * 判断指定日期是否在当前系统日期的未来三天之内
	 * @param date
	 * @return
	 * @throws ServiceException
	 */
	public static boolean compareDate(Date date) throws ServiceException{
		Date curDate = new Date();//当前日期
		long diff = date.getTime() - curDate.getTime();
	    long days = diff / (1000 * 60 * 60 * 24);
	    return days > 2 ? true : false;
	}
	
	/**
	 * 判断查询航班是否在航班计划的周期内
	 * @param startDate		计划开始日期
	 * @param endDate		计划结束日期
	 * @param SearcherDate  查找航班日期
	 * @return
	 */
	public static boolean airportPlanisInCycle(Date startDate, Date endDate, Date SearcherDate){
		
		boolean flag = false;
		//查询时间大于开始时间并且查询时间小于结束时间
		if (startDate.getTime() <= SearcherDate.getTime() && SearcherDate.getTime() <= endDate.getTime()) {
			
			flag = true;
		} 
		return flag;
	}
	
	/**
	 * 根据查询日期和周期
	 * @param cycle
	 * @param searcherDate 格式是:"2016-03-03"
	 * @return
	 */
	public static boolean weekIsAirportPlan(String cycle, Date searcherDate){
		
		boolean flag = false;
		String weekNumber = String.valueOf(getWeek(searcherDate));
		if(GeneralUtil.isNotNull(cycle)){
			
			if(cycle.indexOf(weekNumber) != -1){
				
				flag = true;
			}else{
				
				flag = false;
			}
		}
		return flag;
	}
	
	/**
	 * 根据日期获取是星期几，这里将( 星期几 转化成 数字对象的1234567)
	 * @param date
	 * @return
	 */
	public static int getWeek(Date date) {  
        
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");  
        String week = sdf.format(date);  
        int weekNumber = 1;
        if("星期一".equals(week)){		
        	
        	weekNumber = 1;
        }else if("星期二".equals(week)){
        	
        	weekNumber = 2;
        } else if("星期三".equals(week)){
        	
        	weekNumber = 3;
        }else if("星期四".equals(week)){
        	
        	weekNumber = 4;
        }else if("星期五".equals(week)){
        	
        	weekNumber = 5;
        }else if("星期六".equals(week)){
        	
        	weekNumber = 6;
        }else if("星期日".equals(week)){
        	
        	weekNumber = 7;
        }
        
        return weekNumber;  
    }  
	
	/**
	 * 通过航班号获取三字码
	 * @param flt
	 * @return
	 */
	public static String getAirPortThreeWordByFlt(String flt){
		
		if (GeneralUtil.isNotNull(flt)) {

			flt = flt.substring(0, 3);
		}
			
    	return flt;
	}
	
}

