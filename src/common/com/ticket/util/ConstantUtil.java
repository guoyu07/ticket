package com.ticket.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 系统常量工具类
 * @ClassName: ConstantUtil   
 * @Description: 读取constant.properties里面的键值对   
 * @author HiSay  
 * @date Aug 14, 2013 11:07:32 AM   
 *
 */
public class ConstantUtil {
	private static final Log log = LogFactory.getLog(ConstantUtil.class);
	/**
	 * 私有构造方法, 防止在类外面实例化对象.
	 * @Title: ConstantUtil
	 * @Description:   
	 */
	private ConstantUtil(){}
	
	//系统属性
	private static Properties pros = null;
	
	static {
		InputStream path = GeneralUtil.class.getClassLoader().getResourceAsStream("constants.properties");
		pros = new Properties();
		try {
			pros.load(path);
		} catch (IOException e) {
			log.info("加载constants.properties文件失败： " + e.fillInStackTrace());
		}
	}
	
	/**
	 * 根据key值获取constants.properties里面的value值
	 * @Title: ConstantUtil
	 * @Description:    
	 * @param @param  属性文件里面的Key值
	 * @param @return 返回属性文件里面的value值
	 * @return 
	 * @throws
	 */
	public static Integer getConstantValueAsInteger(String key) {
		try {
			String value = pros.getProperty(key);
			return Integer.valueOf(value);
		} catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * 根据key值获取constants.properties里面的value值
	 * @Title: ConstantUtil
	 * @Description:    
	 * @param @param  属性文件里面的Key值
	 * @param @return 返回属性文件里面的value值
	 * @return 
	 * @throws
	 */
	public static Long getConstantValueAsLong(String key) {
		try {
			String value = pros.getProperty(key);
			return Long.valueOf(value);
		} catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * 根据key值获取constants.properties里面的value值
	 * @Title: ConstantUtil
	 * @Description:    
	 * @param @param  属性文件里面的Key值
	 * @param @return 返回属性文件里面的value值
	 * @return 
	 * @throws
	 */
	public static Double getConstantValueAsDouble(String key) {
		try {
			String value = pros.getProperty(key);
			return Double.valueOf(value);
		} catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * 根据key值获取constants.properties里面的value值
	 * @Title: ConstantUtil
	 * @Description:    
	 * @param @param  属性文件里面的Key值
	 * @param @return 返回属性文件里面的value值
	 * @return 
	 * @throws
	 */
	public static String getConstantValueAsString(String key) {
		try {
			return pros.getProperty(key);
		} catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * 根据key值获取constants.properties里面的value值
	 * @Title: ConstantUtil
	 * @Description:    
	 * @param @param  属性文件里面的Key值
	 * @param @return 返回属性文件里面的value值
	 * @return 
	 * @throws
	 */
	public static Date getConstantValueAsDate(String key, String dateFormat) {
		try {
			String value = pros.getProperty(key);
			return DateUtil.parseStringToDate(value, dateFormat);
		} catch(Exception e) {
			return null;
		}
	}
}
