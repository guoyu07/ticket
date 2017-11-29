package com.ticket.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期处理工具类
 * @ClassName: DateUtil   
 * @Description: 对日期的各种操作   
 * @author HiSay  
 * @date Jul 8, 2013 11:43:36 AM   
 *
 */
public class DateUtil {
	private DateUtil(){}
	
	/** 默认的时间日期格式 */
	public static final SimpleDateFormat DEFAULT_DATE_TIME_FOMRAT = new SimpleDateFormat("yyyyMMddHHmmss");
	
	/**
	 * 简单的日期个数对象
	 */
	public static final SimpleDateFormat yyyy_MM_dd_HH_mm_ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 完整的日期个数对象
	 */
	public static final SimpleDateFormat yyyy_MM_dd_HH_mm_ss_SSSSSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
	
	/**
	 * 短的日期个数对象
	 */
	public static final SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat HH_mm_ss = new SimpleDateFormat("HH:mm:ss");
	public static final SimpleDateFormat HH_mm = new SimpleDateFormat("HH:mm");
	public static final SimpleDateFormat yyyy年MM月dd日 = new SimpleDateFormat("yyyy年MM月dd日");
	
	/**
	 * getSystemDateTime(获取系统的时间日期)
	 * @format 默认格式: yyyy-MM-dd hh:mm:ss
	 * @return 按照系统的默认格式返回系统当前日期和时间
	 */
	public static String getSystemDateTime() {
		return DEFAULT_DATE_TIME_FOMRAT.format(new Date());
	}
	
	/**
	 * getSystemDateTime(根据指定的格式获取系统的时间日期)
	 * @param dateFormat 指定的日期时间格式
	 * @return 按照系统的默认格式返回系统当前日期和时间
	 */
	public static String getSystemDateTime(String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(new Date());
	}
	
	/**
	 * 获取昨天的日期
	 * @param dateFormat
	 * @return
	 */
	public static String getSystemDateTimeYesterday(String dateFormat) {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		long lo = date.getTime()- 86400000;
		date.setTime(lo);
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date);
	}
	
	/**
	 * 获取月份的第一天
	 * @return
	 */
	public static String getFirstDayByMonth() {
		Calendar cal = Calendar.getInstance();
		// 当前月的最后一天
		cal.set(Calendar.DATE, 1);
		cal.roll(Calendar.DATE, -1);
		// 当前月的第一天
		cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
		Date beginTime = cal.getTime();
		String beginTime1 = yyyy_MM_dd.format(beginTime);
		return beginTime1;
	}
	
	/**
	 * 获取月份的最后第一天
	 * @return
	 */
	public static String getLastDayByMonth() {
		Calendar cal = Calendar.getInstance(); 
         //当前月的最后一天   
         cal.set( Calendar.DATE, 1 );
         cal.roll(Calendar.DATE, - 1 );
         Date endTime=cal.getTime();
         String endTime1=yyyy_MM_dd.format(endTime);
          //当前月的第一天          
         cal.set(GregorianCalendar.DAY_OF_MONTH, 1); 
         return endTime1;
	}
	
	/**
	 * getSystemDateTime(根据指定的格式获取指定日期的字符串)
	 * @param dateFormat 指定的日期时间格式
	 * @param date 日期对象
	 */
	public static String getSystemDateTime(String dateFormat, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date);
	}
	
	/**
	 * parseStringToDate(根据指定的日期字符串返回日期对象)
	 * @param  dateString 要解析的日期字符串
	 * @return 返回这个dateString所代表的日期对象
	 * @throws ParseException 如果转换出现异常, 则返回<code>null</code>对象
	 */
	public static Date parseStringToDate(String dateString) {
		try {
			return yyyy_MM_dd_HH_mm_ss.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * parseStringToDate(根据指定的日期字符串返回日期对象)
	 * @param  dateString 要解析的日期字符串
	 * @return 返回这个dateString所代表的日期对象
	 * @throws ParseException 如果转换出现异常, 则返回<code>null</code>对象
	 */
	public static Date parseShortStringToDate(String dateString) {
		try {
			return yyyy_MM_dd.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * parseStringToDate(根据一个指定的日期字符串返回一个指定格式的日期对象)
	 * @param  dateStr 指定的日期格式
	 * @param  pattern 指定的字符串格式
	 * @return 返回指定的日期格式
	 * @throws ParseException 如果转换出现异常, 则返回<code>null</code>对象
	 */
	public static Date parseStringToDate(String dateStr, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			try {
				return sdf.parse(dateStr);
			} catch (ParseException e) {
				return null;
			}
	}
	
	/**
	 * parseSimpleStringToDate(根据一个简单的的日期字符串返回日期对象 例如:yyyy-MM-dd)
	 * @param  simpleDateString 要解析的简单日期字符串
	 * @return 返回这个simpleDateString所代表的日期对象
	 * @throws ParseException 如果转换出现异常, 则返回<code>null</code>对象
	 */
	public static Date parseSimpleStringToDate(String simpleDateString) {
		try {
			if(simpleDateString.indexOf(':') > 0){
				
				return yyyy_MM_dd_HH_mm_ss.parse(simpleDateString);
			}
			return yyyy_MM_dd_HH_mm_ss.parse(new StringBuilder(simpleDateString).append(" 00:00:00").toString());
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * parseDateToString(根据一个简单的的日期字符串返回日期对象 例如:yyyy-MM-dd)
	 * @param  date 指定的日期对象
	 * @param  pattern 要返回的字符串格式
	 * @result 根据指定的pattern格式返回date的指定格式
	 */
	public static String parseDateToString(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	/**
	 * parseCurrentDateToLongString(根据当前的日期返回一个Long类型的字符串)
	 * @param  pattern 指定的字符串格式
	 * @result 根据指定的pattern格式返回Long的指定格式
	 */
	public static String parseCurrentDateToLongString(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date().getTime());
	}
	
	/**
	 * getDateByMonth(返回当天在一月中序号)
	 * @return 返回一个整数
	 */
	public static int getDateByMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.get(Calendar.DATE);
	}
    
	/**
	 * getDayByWeek(返回星期几)
	 * @return 返回一个整数
	 */
	public static int getDayByWeek() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (week == 0)
			return 7;
		return week;
	}
	
	/**
	 * 
	 * strToDate(指定的字符串转换为Calendar独享)
	 * @param strDate 指定的日期字符串
	 * @return {@link java.util.Calendar}对象
	 */
	public static Calendar strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		ParsePosition pos = new ParsePosition(0);
		Calendar curCalendar = Calendar.getInstance();
		curCalendar.setTime(formatter.parse(strDate, pos));
		return curCalendar;
	}
	
	/**
	 * getCalendarByStrShort(将短日期字符串类型转换为Calendar类型对象)
	 * @param dateStrShort 指定的日期格式的字符串
	 * @return {@link java.util.Calendar}对象
	 */
	public static Calendar getCalendarByStrShort(String dateStrShort) {
		return strToDate(dateStrShort + " 00:00:00.000");
	}
	
	/**
	 * getDayCount(获取指定日期范围内的天数)
	 * 
	 * @param begDate 开始日期
	 * @param endDate   结束日期
	 * @return 一个整数
	 */
	public static int getDayCount(Date begDate, Date endDate) {
		Calendar startCalendar = getCalendarByStrShort(yyyy_MM_dd.format(begDate));
		Calendar endCalendar = getCalendarByStrShort(yyyy_MM_dd.format(endDate));
		int dayMCount = 24 * 60 * 60 * 1000;
		return (int) ((endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis())/ dayMCount);
	}
	
	/**
	 * getDayCount(获取指定日期范围内的天数)
	 * 
	 * @param begDate 开始日期字符串
	 * @param endDate 结束日期字符串
	 * @return 一个整数
	 */
	public static int getDayCount(String begDate, String endDate) {
		Calendar startCalendar = getCalendarByStrShort(begDate);
		Calendar endCalendar = getCalendarByStrShort(endDate);
		int dayMCount = 24 * 60 * 60 * 1000;
		return (int) ((endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis())/ dayMCount + 1);
	}
	
	/**
	 * getNextWeek(获得制定时间前或后的week的开始时间和结束时间)
	 * 
	 * @param dateStr
	 * @param count
	 * @return
	 */
	public static String[] getNextWeek(String dateStr, int weekCount) {
		String[] dateRange = { "", "" };
		Calendar curCalendar = getCalendarByStrShort(dateStr);
		curCalendar.setFirstDayOfWeek(Calendar.SUNDAY);
		int dayOfWeek = curCalendar.get(Calendar.DAY_OF_WEEK);
		dayOfWeek = dayOfWeek == 1 ? 7 : dayOfWeek - 1;
		int dayDiff = weekCount <= 0 ? -(dayOfWeek - 1 + 7 * Math
				.abs(weekCount)) : +(1 - dayOfWeek + 7 * weekCount);

		curCalendar.add(Calendar.DATE, dayDiff);
		dateRange[0] = yyyy_MM_dd.format(curCalendar.getTime());
		curCalendar.add(Calendar.DATE, 6);
		dateRange[1] = yyyy_MM_dd.format(curCalendar.getTime());

		return dateRange;

	}

	/**
	 * getNextMonth(获得指定时间后的指定月份开始和结束时间)
	 * 
	 * @param dateStr
	 * @param monthCount
	 * @return
	 */
	public static String[] getNextMonth(String dateStr, int monthCount) {
		String[] dateRange = { "", "" };
		Calendar curCalendar = getCalendarByStrShort(dateStr);
		curCalendar.add(Calendar.MONTH, monthCount);
		curCalendar.set(Calendar.DAY_OF_MONTH, 1);
		dateRange[0] = yyyy_MM_dd.format(curCalendar.getTime());
		curCalendar.set(Calendar.DAY_OF_MONTH, curCalendar
				.getActualMaximum(Calendar.DAY_OF_MONTH));
		dateRange[1] = yyyy_MM_dd.format(curCalendar.getTime());
		return dateRange;

	}

	/**
	 * getYear(返回年份)
	 *
	 * @param date 日期
	 * @return 返回年份
	 */
	public static int getYear(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.YEAR);
	}

	/**
	 * getMonth(返回月份)
	 *
	 * @param date 日期
	 * @return     返回月份
	 */
	public static int getMonth(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.MONTH) + 1;
	}

	/**
	 * 根据日期计算年龄
	 * @Title: getAge 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param date
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	public static int getAge(java.util.Date date) {
		try {
			Calendar cal = Calendar.getInstance();
			
			int yearNow = cal.get(Calendar.YEAR);
	        int monthNow = cal.get(Calendar.MONTH)+1;
	        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
	       
	        cal.setTime(date);
	        int yearBirth = cal.get(Calendar.YEAR);
	        int monthBirth = cal.get(Calendar.MONTH);
	        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

	        int age = yearNow - yearBirth;

	        if (monthNow <= monthBirth) {
	            if (monthNow == monthBirth) {
	                //monthNow==monthBirth
	                if (dayOfMonthNow < dayOfMonthBirth) {
	                    age--;
	                }
	            } else {
	                //monthNow>monthBirth
	                age--;
	            }
	        }
	        return age;
		} catch(Exception e) {
			return 0;
		}
	}

	/**
	 * 获到SQL的当前日期时间
	 * @return
	 */
	public static java.sql.Date getSQLdate() {
		SimpleDateFormat formatter_date = yyyy_MM_dd;
		java.util.Date currentTime = new java.util.Date();// 得到当前系统时间
		String str_date1 = formatter_date.format(currentTime).toString(); // 将日期时间格式化
		java.sql.Date curDate = java.sql.Date.valueOf(str_date1);
		return curDate;
	}
	
	/**
	 * 将字符串转日期转成SQL日期
	 * @param str_date 字符串日期
	 * @return
	 */
	public static java.sql.Date getSQLDate(String str_date){
		java.sql.Date curDate = java.sql.Date.valueOf(str_date);
		return curDate;
	}
	
	/**
	 * 获取一天的开始时间以字符串形式返回
	 * Jul 19, 2010 
	 * @return
	 */
	public static String getStringDateStart() {
		return DateUtil.getSystemDateTime("yyyy-MM-dd") + " 00:00:00";
	}
	
	/**
	 * 获取一天的结束时间以字符串形式返回
	 * Jul 19, 2010 
	 * @return
	 */
	public static String getStringDateEnd() {
		return DateUtil.getSystemDateTime("yyyy-MM-dd") + " 23:59:59";
	}
	
	/**
	 * 获取一天的开始时间以日期返回
	 * @return
	 */
	public static Date getDayStart(Date date){
		
		if(date == null){
			
			return null;
		}
		
		return parseStringToDate(formatDateToShortString(date) + " 00:00:00");
	}
	
	/**
	 * 获取一天的结束时间以日期返回
	 * @return
	 */
	public static Date getDayEnd(Date date){
		
		if(date == null){
			
			return null;
		}
		
		return parseStringToDate(formatDateToShortString(date) + " 23:59:59");
	}
	
	/**
	 * 获取一天的开始时间以日期返回
	 * @return
	 */
	public static Date getTodayStart() {
		return parseStringToDate(getSystemDateTime("yyyy-MM-dd") + " 00:00:00");
	}
	
	/**
	 * 获取一天的结束时间以日期返回
	 * @return
	 */
	public static Date getTodayEnd() {
		return parseStringToDate(getSystemDateTime("yyyy-MM-dd") + " 23:59:59");
	}
	
	/**
	 * 获取昨天的日期字符串
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getYesterdayDate() {
		try {
			Date myDate = new Date(); // 今天的时间
			myDate.setTime(myDate.getTime() - 24 * 60 * 60 * 1000); // 减掉一天后的时间
			return myDate.toLocaleString().substring(0, myDate.toLocaleString().indexOf(" "));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取今天的日期字符串
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getTodayDate() {
		try {
			Date myDate = new Date(); // 今天的时间
			return myDate.toLocaleString().substring(0, myDate.toLocaleString().indexOf(" "));
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 获取本周的开始时间
	 * @return
	 */
	public static Date getThisWeekStartDate() {
		try {
			Calendar thisRegion = Calendar.getInstance();
			thisRegion.set(Calendar.DAY_OF_WEEK, 2);
			return  DateUtil.getDayStart(thisRegion.getTime());
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 获取本月的开始时间
	 * @return
	 */
	public static Date getThisMonthStartDate() {
		try {
			Calendar thisRegion = Calendar.getInstance();
			thisRegion.set(Calendar.DAY_OF_MONTH, 1);
			return DateUtil.getDayStart(thisRegion.getTime());
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String timeStamp2Date(String timestampString){  
		
		Long timestamp = Long.parseLong(timestampString)*1000;  
		String date = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(timestamp));  
		return date;  
	}
	/**
	 * @author wangjiafeng
	 * 获取距离指定日期指定天内的开始时间
	 * @method getDateToThisDateStart
	 * @param dateInt
	 * @param date
	 * @return
	 * @return Date
	 * @date 2013-11-8 上午09:16:47
	 */
	public static Date getDateToThisDateStart(String dateInt,Date date){
		SimpleDateFormat sdf = yyyy_MM_dd;
		Calendar calendar = Calendar.getInstance();
		Date startTime = null;
		if(GeneralUtil.isNull(date)){
			date = calendar.getTime();
		}
		if("30".equals(dateInt)){
			calendar.setTime(date);
			calendar.set(GregorianCalendar.DAY_OF_MONTH, 1);
			startTime = parseStringToDate(sdf.format(calendar.getTime())
					+ " 00:00:00", "yyyy-MM-dd HH:mm:ss");
		}else if("7".equals(dateInt)){
			calendar.setTime(date);
			Calendar calendar2 = Calendar.getInstance();
			calendar2.setTime(date);
			calendar2.set(GregorianCalendar.DAY_OF_WEEK,1);
			Long test = date.getTime();
			calendar.set(GregorianCalendar.DAY_OF_WEEK, 2);
			if(test.longValue() == calendar2.getTimeInMillis()){
				long lo = calendar2.getTimeInMillis()
				- (long)6* 24 * 60
				* 60 * 1000;
				date.setTime(lo);	
				startTime = parseStringToDate(sdf.format(date)
				+ " 00:00:00", "yyyy-MM-dd HH:mm:ss");
			}else{
				startTime = parseStringToDate(sdf.format(calendar.getTime())
				+ " 00:00:00", "yyyy-MM-dd HH:mm:ss");
			}
		}else if("365".equals(dateInt)){
			calendar.setTime(date);
			calendar.set(GregorianCalendar.DAY_OF_YEAR, 1);
			startTime = parseStringToDate(sdf.format(calendar.getTime())
					+ " 00:00:00", "yyyy-MM-dd HH:mm:ss");
		}else{
			long lo = date.getTime()
			- (long) (Integer.valueOf(dateInt).intValue()) * 24 * 60
			* 60 * 1000;
			date.setTime(lo);
			startTime = parseStringToDate(sdf.format(date)
			+ " 00:00:00", "yyyy-MM-dd HH:mm:ss");
		}
		return startTime;
	}
	
	/**
	 * @author wangjiafeng
	 * 获取距离指定日期指定天内的结束时间
	 * @method getDateToThisDateStart
	 * @param dateInt
	 * @param date
	 * @return
	 * @return Date
	 * @date 2013-11-8 上午09:16:47
	 */
	public static Date getDateToThisDateEnd(String dateInt,Date date){
		Date endTime = null;
		Calendar calendar = Calendar.getInstance();
		if(GeneralUtil.isNull(date)){
			date = calendar.getTime();
		}
		if("30".equals(dateInt)){
			endTime = date;
		}else if("7".equals(dateInt)){
			endTime = date;
		}else if("365".equals(dateInt)){
			endTime = date;
		}else{
			long lo = date.getTime()
			- (long) (Integer.valueOf(dateInt).intValue()) * 24 * 60
			* 60 * 1000;
	
			date.setTime(lo);
			endTime = DateUtil.parseStringToDate(yyyy_MM_dd.format(date)
			+ " 23:59:59");
		}
		
		return endTime;
	}
	
	/**
	 * 把日期转为字符串
	 * @param date
	 * @return
	 */
	public static String formatDateToSimpleString(Date date){
		
		if(date == null){
			
			return null;
		}
		return yyyy_MM_dd_HH_mm_ss.format(date);
	}
	
	/**
	 * 把日期转为字符串
	 * @param date
	 * @return
	 */
	public static String formatDateToShortString(Date date){
		
		if(date == null){
			
			return null;
		}
		return yyyy_MM_dd.format(date);
	}
	
	/**
	 * 把日期转为字符串
	 * @param date
	 * @return
	 */
	public static String formatDateToTimeString(Date date){
		
		if(date == null){
			
			return null;
		}
		return HH_mm_ss.format(date);
	}
	
	/**
	 * 把日期转为完整字符串
	 * @param date
	 * @return
	 */
	public static String formatDateToFullString(Date date){
		
		if(date == null){
			
			return null;
		}
		return yyyy_MM_dd_HH_mm_ss_SSSSSS.format(date);
	}
	
	/**
	 * 把日期转为字符串
	 * @param date
	 * @return
	 */
	public static String formatDateToShortString_ZH_CN(Date date){
		
		if(date == null){
			
			return null;
		}
		return yyyy年MM月dd日.format(date);
	}
	
	/**
	 * 把日期转为字符串
	 * @param date
	 * @return
	 */
	public static String formatDateToString(Date date){
		
		if(date == null){
			
			return null;
		}
		return DEFAULT_DATE_TIME_FOMRAT.format(date);
	}
	
	/**
	 * 把日期转为字符串
	 * @param date
	 * @return
	 */
	public static String formatDateToHHmm(Date date){
		
		if(date == null){
			
			return null;
		}
		return HH_mm.format(date);
	}
	
	public static String dayOfWeek(Date date){
		
		switch (date.getDay()) {
		case 1:
			return "星期一";
		case 2:
			return "星期二";
		case 3:
			return "星期三";
		case 4:
			return "星期四";
		case 5:
			return "星期五";
		case 6:
			return "星期六";
		default:
			return "星期日";
		}
	}
	
	public static void main(String[] args) {
		
		System.out.println(formatDateToFullString(new Date()));
	}
}
