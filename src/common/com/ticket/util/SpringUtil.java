package com.ticket.util;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring辅助工具类
 * @ClassName: SpringUtil   
 * @Description: 通过非注入的方式获取Spring实例化的对象   
 * @author HiSay  
 * @date Jul 12, 2013 8:02:39 AM   
 *
 */
public class SpringUtil implements ApplicationContextAware{
	private SpringUtil(){}
	
	public static ApplicationContext context = null;
	
	static {
		context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml", "spring-common.xml","spring-manager.xml","spring-template.xml"});
	}
	
	public void init(){
		
		
	}
	
	/**
	 * 从Spring容器中获取指定的对象 
	 * @param value Spring中bean的ID值
	 * @return 返回Spring实例化后的实体
	 */
	public static Object getObjectFromSpring(String value) {
		
		try {
			return context.getBean(value);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {

		context = arg0;
	}
}
