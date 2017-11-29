package com.ticket.listener;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.constants.ContextConstants;
import com.ticket.pojo.SystemConfig;
import com.ticket.pojo.SystemDictionary;
import com.ticket.service.IAirportInfoService;
import com.ticket.service.ISystemConfigService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.util.SpringUtil;

/**
 * 系统初始化监听器
 * @ClassName: InitialListener   
 * @Description: 初始化一些系统必要的数据.   
 * @author HiSay  
 * @date Jul 8, 2013 6:51:41 PM   
 *
 */
public class InitialListener implements ServletContextListener {
	
	private static final Log log = LogFactory.getLog(InitialListener.class);
	
	//系统基本设置业务对象
	private static final ISystemConfigService systemConfigService = (ISystemConfigService)SpringUtil.getObjectFromSpring("systemConfigService");
	private static final IAirportInfoService airportInfoService = (IAirportInfoService)SpringUtil.getObjectFromSpring("airportInfoService");
	private static final ISystemDictionaryService systemDictionaryService = (ISystemDictionaryService)SpringUtil.getObjectFromSpring("systemDictionaryService");
	
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("销毁系统监听器初始化参数");
	}

	public void contextInitialized(ServletContextEvent event) {
		log.info("初始化系统参数开始...");
		ServletContext application = event.getServletContext();
        //网站页面
        String domainName = application.getInitParameter("domainName");
        //页面没有检索到相关数据时,显示的提示信息
        String noDataMessage = application.getInitParameter("noDataMessage");
		application.setAttribute("noDataMessage", noDataMessage);
		application.setAttribute("baseUrl", domainName);
		application.setAttribute("siteUrl", "www." + domainName);
		//application.setAttribute("groupUrl", "www." + domainName);
		application.setAttribute("groupUrl", "http://211.149.196.53:30000");
		//全局上下文路径
		application.setAttribute("contextPath", application.getContextPath());
		application.setAttribute("fileBasePath", event.getServletContext().getRealPath("/"));
		application.setAttribute("fileDirectory", "temp");
		application.setAttribute("separator", File.separator);
		SystemDictionary sysDictionary = systemDictionaryService.getByName("image_server_url");
		application.setAttribute("image_server_url", sysDictionary.getValue());
		ContextConstants.FILE_SERVER_PREFIX=sysDictionary.getValue();
		//获取网站设置的基本对象
		SystemConfig systemConfig = null;
		try {
			systemConfig = systemConfigService.querySystemConfig();
			if(systemConfig != null) {
				application.setAttribute(ContextConstants.SCOPE_SYSTEM_SETTING, systemConfig);
			}
		} catch(Exception e){
			log.info("初始化系统基本设置对象的时候出错: " + e.fillInStackTrace());
		}
		
		try {
			airportInfoService.initialAllCityByCityPoi(application);
			//systemDictionaryService.initialAllCityByCityPoi(application);
		} catch(Exception e){
			log.info("初始化机场信息的时候出错: " + e.fillInStackTrace());
		}
		
		application.setAttribute("UPLOAD_SEPARATOR_VALUE", ContextConstants.UPLOAD_SEPARATOR_VALUE);
		log.info("初始化系统参数结束...");
	}
}
