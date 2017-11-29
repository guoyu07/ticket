package com.ticket.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import ognl.DefaultTypeConverter;

/**
 * 日期类型转换器
 * @ClassName: DateTypeConverter   
 * @Description: Struts2的日期转换器,因为在windows和linux的日期格式不同, 所以需要定义格式.   
 * @author HiSay  
 * @date Jul 8, 2013 4:53:21 PM   
 *
 */
public class DateTypeConverter extends DefaultTypeConverter {
	private static final DateFormat[] ACCEPT_DATE_FORMATS = {
			new SimpleDateFormat("dd/MM/yyyy"),
			new SimpleDateFormat("HH:mm"),
			new SimpleDateFormat("yyyMMdd"),
			new SimpleDateFormat("yyyy-MM-dd"),
			new SimpleDateFormat("yyyy/MM/dd"),
			new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"),
			new SimpleDateFormat("yyyy-MM-dd HH:mm"),
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")}; // 支持转换的日期格式

	@Override
	public Object convertValue(Map context, Object value, Class toType) {
		
		if (toType == Date.class) { // 浏览器向服务器提交时，进行String to Date的转换
			
			if(value == null){
				
				return null;
			}
			
			Date[] date = null;
			String[] params = null;
			if(!value.getClass().isArray()){
				
				date = new Date[1];
				params = new String[1];
				params[0] = ((String)value).replace("T", " ");
			}else{
				
				params = (String[]) value;
				date = new Date[params.length];
			}
			
			for(int i = 0; i < date.length; i++){ // 遍历日期支持格式，进行转换
				
				String param = params[i].replace("T", " ");
				for (DateFormat format : ACCEPT_DATE_FORMATS) {
					
					try {
						
						date[i] = format.parse(param);
					} catch (Exception e) {
						
						continue;
					}
				}
			}
			if(date.length == 1){
				
				return date[0];
			}
			return date;
		} else if (toType == String.class) { // 服务器向浏览器输出时，进行Date to String的类型转换
			
			Date date = (Date) value;
			return new SimpleDateFormat("yyyy-MM-dd").format(date);// 输出的格式是yyyy-MM-dd
		}
		return null;
	}
}
