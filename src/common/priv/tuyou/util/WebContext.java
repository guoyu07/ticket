package priv.tuyou.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * web应用的上下文
 * @author：涂有
 * @date 2016年12月19日 下午11:41:33
 */
public class WebContext implements ApplicationContextAware{
	
	public static ApplicationContext context;

	public static <T> T getBean(Class<T> cla){
		
		T obj = context.getBean(cla);
		return obj;
	}
	
	public static <T> T getBean(Class<T> cla, String name){
		
		T obj = context.getBean(name, cla);
		return obj;
	}
	
	public static Object getBean(String name){
		
		Object obj = context.getBean(name);
		return obj;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {

		context = arg0;
	}
}
