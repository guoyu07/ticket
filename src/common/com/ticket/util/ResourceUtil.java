package com.ticket.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @Description：读取国际化配置文件的工具类(方便在action之外的其他地方调用)
 * @author：涂有
 * @date 2015年11月23日 上午11:15:49
 */
public class ResourceUtil {
	
	public static final String fileName = "i18n";
	public static ResourceBundle resource;
	
	static{
		//一次性加载，节约cpu和内存资源
		resource = ResourceBundle.getBundle(fileName, Locale.getDefault());
	}

	public static String getText(String key, String...params){
		
		String value = resource.getString(key);
		String string = MessageFormat.format(value, params);
		
		return string;
	}
	
	public static void main(String args[]){
		
		System.out.println(getText("sms.regist.success", new String[]{"tuyou", "123456"}));
	}
}
