package com.ticket.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ticket.pojo.TemplateCode;
import com.ticket.service.ISystemConfigService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.views.JspSupportServlet;
import org.apache.struts2.views.freemarker.FreemarkerManager;

import freemarker.ext.jsp.TaglibFactory;
import freemarker.ext.servlet.HttpRequestHashModel;
import freemarker.ext.servlet.ServletContextHashModel;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * FreeMarker模板工具类
 * @ClassName: FreeMarkerUitl   
 * @Description: 用于写入.ftl数据的工具   
 * @author HiSay  
 * @date Jul 8, 2013 5:02:13 PM   
 */
public class FreeMarkerUitl {
	
	private static Configuration config = null;
	private static ServletContext servletContext = null;
	private static FreemarkerManager freemarkerManager = null;
	private static ServletContextHashModel servletContextModel = null;
	private static GenericServlet servlet = null;
	private static TaglibFactory taglibs = null;
	private static HttpRequestHashModel requestModel = null;
	private static final Log log = LogFactory.getLog(FreeMarkerUitl.class);
	
	private static ISystemConfigService systemConfigService = null;
	
	/**
	 * 初始化模板引擎
	 * @Title: FreeMarkerUitl
	 * @Description: 初始化FreeMarker的模板引擎配置类, 设置模板语言的目录.   
	 * @param @param request
	 * @param @param response    
	 * @return  void   
	 * @throws
	 */
	public void initConfiguration(HttpServletRequest request, HttpServletResponse response) {
		config = new Configuration();
		config.setServletContextForTemplateLoading(request.getSession() .getServletContext(), "WEB-INF/ftl");
	}

	/**
	 *  初始化数据
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private void initFreeMarkerParameters(HttpServletRequest request, HttpServletResponse response, Map data) {
		try {
			if(servletContext == null) {
				servletContext = request.getSession().getServletContext();
				freemarkerManager = new FreemarkerManager();
				config = freemarkerManager.getConfiguration(servletContext);
				servletContextModel = (ServletContextHashModel) servletContext.getAttribute(".freemarker.Application");
				if (servletContextModel == null) {
					servlet = JspSupportServlet.jspSupportServlet;
					if (servlet != null) {
						servletContextModel = new ServletContextHashModel(servlet, config.getObjectWrapper());
						servletContext.setAttribute(".freemarker.Application", servletContextModel);
						taglibs = new TaglibFactory(servletContext);
						servletContext.setAttribute(".freemarker.JspTaglibs", taglibs);
					}
				}
				requestModel = (HttpRequestHashModel) request.getAttribute(".freemarker.Request");
				if ((requestModel == null) || (requestModel.getRequest() != request)) {
					requestModel = new HttpRequestHashModel(request, response, config.getObjectWrapper());
					request.setAttribute(".freemarker.Request", requestModel);
				}
				data.put("Application", servletContextModel);
				data.put("Request", requestModel);
				data.put("Session", request.getSession());
				data.put("JspTaglibs", new TaglibFactory(servletContext));
				//data.put("siteSetting", settingService.queryLatestSetting());
			} else {
				requestModel = (HttpRequestHashModel) request.getAttribute(".freemarker.Request");
				if ((requestModel == null) || (requestModel.getRequest() != request)) {
					requestModel = new HttpRequestHashModel(request, response, config.getObjectWrapper());
					request.setAttribute(".freemarker.Request", requestModel);
				}
				data.put("Application", servletContextModel);
				data.put("Request", requestModel);
				data.put("Session", request.getSession());
				data.put("JspTaglibs", new TaglibFactory(servletContext));
				//data.put("siteSetting", settingService.queryLatestSetting());
			}
		} catch(Exception e) {
			log.error("初始化FreeMarker出现异常");
		}
	}
	
	/**
	 * 写Action的模板代码方法
	 * @Title: FreeMarkerUitl
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param @param imageList
	 * @param @param coverImage
	 * @param @param backCoverImage
	 * @param @param request    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void writeJavaCode(HttpServletRequest request, String entityName, String entityTitle, String entityDescript, List<TemplateCode> attrList,
			String attributesValue, String jsAttributesValue, String serviceAttributesValue, String savePath, String pojoIdType) {
		try {
			if(systemConfigService == null) {
				systemConfigService = (ISystemConfigService)SpringUtil.getObjectFromSpring("systemConfigService");
			}
			config.setDefaultEncoding("UTF-8");
			// 建立数据模型
			Map root = new HashMap();
			// 把数据放入root里面.
			root.put("entityName", entityName);
			root.put("entityNameLowerCase", entityName.toLowerCase());
			root.put("entityTitle", entityTitle);
			root.put("entityDescript", entityDescript);
			root.put("dateTime", DateUtil.parseDateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			root.put("attrList", attrList);
			root.put("attributesValue", attributesValue);
			root.put("jsAttributesValue", jsAttributesValue);
			root.put("serviceAttributesValue", serviceAttributesValue);
			root.put("adminName", systemConfigService.querySystemConfig().getName());//这个后期要读取系统配置的参数,目前为临时缩写.
			root.put("entityIdType", pojoIdType);
			//因为POJO页面要导入一些包. 所以判断一下.
			if(attrList != null && !attrList.isEmpty()) {
				if(this.validateAttrListHaveDataType(attrList, "@Column(length=8000)")) {
					root.put("importLob", "true");
				}
				if(this.validateAttrListHaveDataType(attrList, "@Temporal(TemporalType.TIMESTAMP)")) {
					root.put("importDate", "true");
				}
			}
			if("UUID".equals(pojoIdType)) {
				root.put("importUUID", "true");
			}
			
			
			String firstLetter = entityName.substring(0, 1).toLowerCase();
			String entityLowerCase = entityName.substring(1, entityName.length());
			String lowerCase = firstLetter + entityLowerCase;
			root.put("entityNameFirstLowerCase", lowerCase);
			
			//定义各个ftl文件名称
			String actionFtlName = "Action.ftl";
			String pojoFtlName = "Pojo.ftl";
			String jsFtlName = "Js.ftl";
			String serviceFtlName = "IService.ftl";
			String serviceImplFtlName = "ServiceImpl.ftl";
			String jspAddFtlName = "JspAdd.ftl";
			String jspEditFtlName = "JspEdit.ftl";
			String jspManageFtlName = "JspManage.ftl";
			String jspRecycleFtlName = "JspRecycle.ftl";
			String messageFtlName = "Message_zh_CN.ftl";
			
			String targetBasePath = GeneralUtil.getPropertyValue("project_base_path");//目标路径的基路径
			//判断目录是否存在
			File directory = new File(targetBasePath + GeneralUtil.getPropertyValue("manager_jsp_path") + GeneralUtil.firstCharacterToLowerCase(entityName));
			if(!directory.exists()) {
				directory.mkdir();
			}
			
			// 获取模板路径
			Template template = null;
			String fileName = null;
			File file = null;
			String lastFileName = null;
			
			//生成Action的java代码 开始=======================================
			template = config.getTemplate("code/" + actionFtlName);
			template.setEncoding("UTF-8");
			// 生成文件
			fileName = entityName + "Action.java";
			lastFileName = targetBasePath + GeneralUtil.getPropertyValue(savePath) + "action/" + fileName;
			file = new File(lastFileName);
			if (file.exists()) {
				file.delete();
			}
			template.process(root, new OutputStreamWriter(new FileOutputStream(new File(lastFileName)), "UTF-8"));
			//生成Action的java代码 结束=======================================
			
			//生成Action的message_zh_CNjava代码 开始=======================================
			template = config.getTemplate("code/" + messageFtlName);
			template.setEncoding("UTF-8");
			// 生成文件
			fileName = entityName + "Action_zh_CN.properties";
			lastFileName = targetBasePath + GeneralUtil.getPropertyValue(savePath) + "action/" + fileName;
			file = new File(lastFileName);
			if (file.exists()) {
				file.delete();
			}
			template.process(root, new OutputStreamWriter(new FileOutputStream(new File(lastFileName)), "UTF-8"));
			//生成Action的message_zh_CNkjava代码 结束=======================================
			
			//生成Pojo的java代码 开始=======================================
			template = config.getTemplate("code/" + pojoFtlName);
			template.setEncoding("UTF-8");
			// 生成文件
			fileName = entityName + ".java";
			lastFileName = targetBasePath + GeneralUtil.getPropertyValue(savePath) + "pojo/" + fileName;
			file = new File(lastFileName);
			if (file.exists()) {
				file.delete();
			}
			template.process(root, new OutputStreamWriter(new FileOutputStream(new File(lastFileName)), "UTF-8"));
			//生成Pojo的java代码 结束=======================================
			
			//生成Service的java代码 开始=======================================
			template = config.getTemplate("code/" + serviceFtlName);
			template.setEncoding("UTF-8");
			// 生成文件
			fileName = "I" + entityName + "Service.java";
			lastFileName = targetBasePath + GeneralUtil.getPropertyValue(savePath) + "service/" + fileName;
			file = new File(lastFileName);
			if (file.exists()) {
				file.delete();
			}
			template.process(root, new OutputStreamWriter(new FileOutputStream(new File(lastFileName)), "UTF-8"));
			//生成Service的java代码 结束=======================================
			
			//生成ServiceImpl的java代码 开始=======================================
			template = config.getTemplate("code/" + serviceImplFtlName);
			template.setEncoding("UTF-8");
			// 生成文件
			fileName = entityName + "ServiceImpl.java";
			lastFileName = targetBasePath + GeneralUtil.getPropertyValue(savePath) + "serviceImpl/" + fileName;
			file = new File(lastFileName);
			if (file.exists()) {
				file.delete();
			}
			template.process(root, new OutputStreamWriter(new FileOutputStream(new File(lastFileName)), "UTF-8"));
			//生成ServiceImpl的java代码 结束=======================================
			
			//生成Js代码 开始=======================================
			template = config.getTemplate("code/" + jsFtlName);
			template.setEncoding("UTF-8");
			// 生成文件
			fileName = lowerCase + ".js";
			lastFileName = targetBasePath + GeneralUtil.getPropertyValue("manager_js_path") + "/" + fileName;
			file = new File(lastFileName);
			if (file.exists()) {
				file.delete();
			}
			template.process(root, new OutputStreamWriter(new FileOutputStream(new File(lastFileName)), "UTF-8"));
			//生成Js代码 结束=======================================
			
			//生成Jsp代码 开始=======================================
			
			root.remove("entityName");
			root.put("actionName", "${actionName}");
			root.put("entityName", "${entityName}");
			root.put("versionFlag", "${versionFlag}");
			root.put("id", "${id}");
			root.put("cId", "${c.id }");
			root.put("noDataMessage", "${noDataMessage }");
			root.put("dollar", "$");
			root.put("leftBrackets", "{");
			root.put("rightBrackets", "}");
			template = config.getTemplate("code/" + jspAddFtlName);
			template.setEncoding("UTF-8");
			// 生成文件
			fileName = "add" + entityName + ".jsp";
			lastFileName = targetBasePath + GeneralUtil.getPropertyValue("manager_jsp_path") + entityName.toLowerCase() + "/" + fileName;
			file = new File(lastFileName);
			if (file.exists()) {
				file.delete();
			}
			template.process(root, new OutputStreamWriter(new FileOutputStream(new File(lastFileName)), "UTF-8"));
			
			template = config.getTemplate("code/" + jspEditFtlName);
			template.setEncoding("UTF-8");
			// 生成文件
			fileName = "edit" + entityName + ".jsp";
			lastFileName = targetBasePath + GeneralUtil.getPropertyValue("manager_jsp_path") + entityName.toLowerCase() + "/" + fileName;
			file = new File(lastFileName);
			if (file.exists()) {
				file.delete();
			}
			template.process(root, new OutputStreamWriter(new FileOutputStream(new File(lastFileName)), "UTF-8"));
			
			template = config.getTemplate("code/" + jspManageFtlName);
			template.setEncoding("UTF-8");
			// 生成文件
			fileName = "manage" + entityName + ".jsp";
			lastFileName = targetBasePath + GeneralUtil.getPropertyValue("manager_jsp_path") + entityName.toLowerCase() + "/" + fileName;
			file = new File(lastFileName);
			if (file.exists()) {
				file.delete();
			}
			template.process(root, new OutputStreamWriter(new FileOutputStream(new File(lastFileName)), "UTF-8"));
			
			template = config.getTemplate("code/" + jspRecycleFtlName);
			template.setEncoding("UTF-8");
			// 生成文件
			fileName = "recycle" + entityName + ".jsp";
			lastFileName = targetBasePath + GeneralUtil.getPropertyValue("manager_jsp_path") + entityName.toLowerCase() + "/" + fileName;
			file = new File(lastFileName);
			if (file.exists()) {
				file.delete();
			}
			template.process(root, new OutputStreamWriter(new FileOutputStream(new File(lastFileName)), "UTF-8"));
			//生成Jsp代码 结束=======================================
		} catch (Exception e) {
			log.info("生成Java代码出错: " + e.fillInStackTrace());
		}
	}
	
	/**
	 * 验证attrList是否包含指定包
	 * @Title: FreeMarkerUitl
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param @param attrList  模板代码List
	 * @param @param dataType  属性类型
	 * @param @return    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	private boolean validateAttrListHaveDataType(List<TemplateCode> attrList, String dataType) {
		try {
			for(TemplateCode code : attrList) {
				if(code.getAttrAnnotation().equals(dataType)) {
					return true;
				}
			}
			return false;
		} catch(Exception e) {
			log.info(e.fillInStackTrace());
			return false;
		}
	}
}
