package com.ticket.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.fusesource.hawtbuf.ByteArrayInputStream;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.EvaluationReportTask;
import com.ticket.pojo.Member;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.SystemUser;
import com.ticket.service.IBaseService;
import com.ticket.service.IFrontMenuService;
import com.ticket.util.GeneralUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * 自定义Struts2控制器,作为基础公用控制器
 * @ClassName: BaseAction   
 * @Description:    
 * @author HiSay  
 * @date Jul 8, 2013 5:55:38 PM   
 *
 */
public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 1L;
	
	@Resource
	protected IBaseService<Object, String> baseService;
	@Resource
	protected IFrontMenuService frontMenuService;
	
	protected Date startTime, endTime;
	
	/**
	 * 上传的附件
	 */
	public String files = null;
	/**
	 * 附件标题
	 */
	public String annexTitle = null;
	/**
	 * 附件内容
	 */
	public String annexContent = null;
	/**
	 * 附件排序值
	 */
	public String annexOrderValue = null;
	
	/**
	 * request变量, 在访问Action的时候就已经初始化此变量了.
	 */
	public static HttpServletRequest request = null;
	/**
	 * response变量, 在访问Action的时候就已经初始化此变量了.
	 */
	public static HttpServletResponse response = null;
	/**
	 * 随机值
	 */
	public String random = null;
	/**
	 * 在页面Ajax方式提交批量操作的时候, 传递的ID集合存放到此变量里面. 因为每个Action基本都有批量操作的方法.
	 */
	public String idsValue = null;
	
	/**
	 * id
	 */
	protected String id = null;
	
	/**
	 * 操作类型, 在批量操作的时候. 要传入的参数.例如:audit,commend,hot,logicDelete,restore等.
	 */
	public String batchOperationType = null;
	/**
	 * 批量操作的时候, 是否选中复选框.
	 */
	public Boolean isChecked = null;
	/**
	 * 验证码变量
	 */
	public String verificateCode = null;
	/**
	 * 版本变量, 默认为中文版.
	 */
	public String versionFlag = ContextConstants.VERSION_FLAG_OF_CHINESE;
	/**
	 * 是否操作的变量, 用于进入添加页面,或者修改页面的时候.如果此变量为NULL, 则说明是进入页面,而非操作.
	 */
	public String operationFlag = null;
	/**
	 * 当前请求的方法
	 */
	public String currentMethod = null;
	/**
	 * 当前页码
	 */
	public Integer pn = 0;
	/**
	 * 后台页面错误提示变量.
	 * 当在后台操作的时候, 如果出现相关错误提示信息. 都放入这个
	 */
	public String managerPageTipsMessage = null;
	/**
	 * 后台信息错误提示页面
	 * 当后台出现错误信息提示时, 转向到这个页面.
	 */
	public static final String MANAGER_MESSAGE_TIPS_ERROR_PAGE = "managerMessageErrorPage";
	/**
	 * 后台信息成功提示页面
	 * 当后台出现成功信息提示时, 转向到这个页面.
	 */
	public static final String MANAGER_MESSAGE_TIPS_SUCCESS_PAGE = "managerMessageSuccessPage";
	/**
	 * 分页模型
	 */
	public Pagination pageModule = null;
	/**
	 * 分页大小
	 */
	public Integer pageSize = null;
	/**
	 * 模块ID
	 */
	public Long moduleId = null;
	/**
	 * 审核/热门/推荐的状态值
	 */
	public Integer statusValue = null;
	
	/**
	 * currentPage 当前页面
	 */
	public Integer cp = 1;
	
	/**
	 * pageSize 分页大小
	 */
	public Integer ps = 15;
	
	/**
	 * 顶部导航索引值
	 */
	public Integer markIndex = null;
	/**
	 * 左侧导航索引值
	 */
	public Integer leftIndex = null;
	/**
	 * 实体排序值
	 */
	public Integer orderValue = 0;
	
	/** JQuery Uploadify 文件数组 */
	public String[] fileNames = null;
	
	/** JQuery Uploadify 文件目录 */
	public String directory = ContextConstants.UPLOAD_PATH;
	
	/** 获取实体数量 */
	public Integer size = null;
	
	/**
	 * 页面提示信息变量
	 */
	public String tipMessage = null;
	
	/**
	 * 短信验证码
	 */
	public String smsCode = null;
	
	/**
	 * app端执行的令牌
	 */
	public String token = null;
	
	/**
	 * 从app过来
	 */
	protected String fromApp;
	
	/**
	 * 菜单ID
	 */
	protected String menuId;
	
	/**
	 * app返回数据
	 */
	protected Object data;
	
	/**
	 * 表示返回JSON类型
	 */
	protected final static String JSON = "JSON";
	
	/**
	 * 表示返回JSON类型
	 */
	protected final static String TEXT = "TEXT";

	/**
	 * 获取ROOT根目录路径
	 */
	public String getFileBasePath() {
		return (String) ActionContext.getContext().getApplication().get("fileBasePath");
	}
	
	/**
	 * 获取Session对象
	 * 
	 * @return
	 */
	public static Map<String, Object> getSession() {
		return ActionContext.getContext().getSession();
	}

	/**
	 * 获取Application对象
	 * @return
	 */
	public static Map getApplication() {
		return ActionContext.getContext().getApplication();
	}
	
	/**
	 * @author wangjiafeng
	 * 获取手机端登录的员工
	 * @method getWapEmployeeInfo
	 * @return
	 * @return EmployeeInfo
	 * @date 2016-1-18 下午03:41:44
	 */
	public EmployeeInfo getWapEmployeeInfo() {
		if(getSession().get(ContextConstants.SCOPE_EMPLOYEEINFO_USER) != null) {
			return (EmployeeInfo) getSession().get(ContextConstants.SCOPE_EMPLOYEEINFO_USER);
		} else {
			return null;
		}
	}
	
	/**
	 * @author wangjiafeng
	 * 获取后台登录的员工
	 * @method getSystemEmployeeInfo
	 * @return
	 * @return EmployeeInfo
	 * @date 2015-12-31 上午10:51:58
	 */
	public EmployeeInfo getSystemEmployeeInfo() {
		if(getSession().get(ContextConstants.SCOPE_SYSTEM_EMPLOYEEINFO) != null){
			return (EmployeeInfo)getSession().get(ContextConstants.SCOPE_SYSTEM_EMPLOYEEINFO);
		}
		if(getSession().get(ContextConstants.SCOPE_SYSTEM_USER) != null) {
			SystemUser systemUser = (SystemUser) getSession().get(ContextConstants.SCOPE_SYSTEM_USER);
			if(systemUser instanceof EmployeeInfo){
				EmployeeInfo employeeInfo = (EmployeeInfo)systemUser;
				getSession().put(ContextConstants.SCOPE_SYSTEM_EMPLOYEEINFO,employeeInfo);
				return employeeInfo;
			}
			return null;
		} else {
			return null;
		}
	}
	
	/**
	 * @author wangjiafeng
	 * 获取后台登录的渠道客户
	 * @method getSystemEmployeeInfo
	 * @return
	 * @return EmployeeInfo
	 * @date 2015-12-31 上午10:51:58
	 */
	public ChannelCustomerInfo getSystemChannelCustomer() {
		if(getSession().get(ContextConstants.SCOPE_CUSTOMER_USER) != null){
			return (ChannelCustomerInfo)getSession().get(ContextConstants.SCOPE_CUSTOMER_USER);
		}
		if(getSession().get(ContextConstants.SCOPE_SYSTEM_USER) != null) {
			SystemUser systemUser = (SystemUser) getSession().get(ContextConstants.SCOPE_SYSTEM_USER);
			if(systemUser instanceof ChannelCustomerInfo){
				ChannelCustomerInfo channelCustomerInfo = (ChannelCustomerInfo)systemUser;
				getSession().put(ContextConstants.SCOPE_CUSTOMER_USER,channelCustomerInfo);
				return channelCustomerInfo;
			}
			return null;
		} else {
			return null;
		}
	}
	
	/**
	 * 得到第一个错误表单域的信息
	 * @return
	 */
	public String getFirstFieldErrorMessage(){
		
		Map<String, List<String>> errors = getFieldErrors();
		Set<String> keys = errors.keySet();
		Iterator<String> keySet = keys.iterator();
		while(keySet.hasNext()){
			
			return errors.get(keySet.next()).get(0);
		}
		return null;
	}
	
	/**
	 * 获取游客在session中的编号
	 * @Title: getRandomMember 
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public String getVisitorMember() {
		if(getSession().get(ContextConstants.MEMBER_TEMP_RANDOM) != null) {
			return getSession().get(ContextConstants.MEMBER_TEMP_RANDOM).toString();
		} else {
			return "";
		}
	}
	
	
	/**
	 * 获取当前实体的名称
	 * @Title: BaseAction
	 * @Description: 例如当前访问ModuleAction,则此方法返回Module. 如果访问ContentModuleAction,则此方法返回ContentModule   
	 * @param @return    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String getEntityName() {
		String className = this.getClass().getSimpleName();
		className = className.replace("Action", "");
		return className;
	}
	/**
	 * 获取当前Action对应在struts配置文件的前缀名称
	 * @Description: 例如当前访问ModuleAction,则此方法返回module. 如果访问ContentModuleAction,则此方法返回contentmodule   
	 * @param @return    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String getActionName() {
		String className = this.getClass().getSimpleName();
		className = className.replace("Action", "");
		return GeneralUtil.firstCharacterToLowerCase(className);
	}
	
	/**
	 * 获取后台已经登陆的管理员用户实体
	 * @Title: BaseAction
	 * @Description:    
	 * @param @return 
	 * @return 
	 * @throws
	 */
	public static SystemUser getSessionAdminUser() {
		if(getSession().get(ContextConstants.SCOPE_SYSTEM_USER) != null) {
			return (SystemUser) getSession().get(ContextConstants.SCOPE_SYSTEM_USER);
		} else {
			return null;
		}
	}
	
	/**
	 * 获取session中的member
	 * @return
	 */
	public static Member getSessionMember() {
		if(getSession().get(ContextConstants.SCOPE_MEMBER) != null) {
			return (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		} else {
			return null;
		}
	}
	
	/**
	 * 判断验证码是否输入准确
	 */
	public boolean validateVerificateCodeBySession() {
		//判断是否输入
		if(GeneralUtil.isNull(verificateCode)) {
			return false;
		}
		if(getSession().get(ContextConstants.SCOPE_VERIFICATE_CODE) == null) {
			return false;
		}
		String sessionCode = getSession().get(ContextConstants.SCOPE_VERIFICATE_CODE).toString();
		if(!sessionCode.equals(verificateCode)) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断验证码是否输入准确
	 */
	public boolean validateVerificateCodeBySms() {
		//判断是否输入
		if(GeneralUtil.isNull(verificateCode)) {
			return false;
		}
		if(getSession().get(ContextConstants.SMS_VERIFICATE_CODE) == null) {
			return false;
		}
		String sessionCode = getSession().get(ContextConstants.SMS_VERIFICATE_CODE).toString();
		if(!sessionCode.equals(verificateCode)) {
			return false;
		}
		return true;
	}
	
	/**
	 * 移动单个文件-> 用于将JQuery Uploadify上传的文件移动到指定的路径
	 * 
	 * @param fileNames
	 *            所有图片路径
	 * @param directory
	 *            移动到目录
	 * @param imageType
	 *            区分大图和小图 ContextConstant.EDIT_BIG_PICTURE
	 *            ContextConstant.EDIT_SMALL_PICTURE
	 */
	@SuppressWarnings("deprecation")
	public String getSinglePictureUrlFromJQueryUpLoader(String[] fileNames,
			String directory, Integer imageType) {
		try {
			String picture = "";
			String servicePath = ServletActionContext.getRequest().getRealPath("/");
			if (fileNames != null) {
				for (int i = 0; i < fileNames.length; i++) {
					String str = fileNames[i];
					// 先判断图片类型是否一直,如果不一致则跳过当前循环
					String suffix = str.substring(str.indexOf(ContextConstants.UPLOAD_SEPARATOR_VALUE) + 2,str.lastIndexOf(ContextConstants.UPLOAD_SEPARATOR_VALUE));
					if (!suffix.equals(imageType.toString())) {
						continue;
					}
					String path = str.substring(0, str.indexOf(ContextConstants.UPLOAD_SEPARATOR_VALUE));
					path = path.replace("\\", "/").substring(1);
					if(path.indexOf("temp") == -1) {
						return str;
					}
					String fileName = null;
					if(str.lastIndexOf(File.separator) == -1) {
						fileName = str.substring(str.lastIndexOf("/") + 1, str.indexOf(ContextConstants.UPLOAD_SEPARATOR_VALUE));
					} else {
						fileName = str.substring(str.lastIndexOf(File.separator) + 1, str.indexOf(ContextConstants.UPLOAD_SEPARATOR_VALUE));
					}
					// 被移动的文件夹
					File sourceFile = new File(servicePath + path);
					// 目标文件夹
					File targetFile = new File(servicePath + directory + path.substring(path.indexOf("temp") + 5, path.lastIndexOf("/")));
					// 如果目录没有存在,则创建目录
					if (!targetFile.isDirectory()) {
						targetFile.mkdirs();
					}
					// 将文件移动到另一个文件目录下
					sourceFile.renameTo(new File(targetFile + File.separator +fileName));
					String tempPic = "/"+ directory + path.substring(path.indexOf("temp") + 5, path.lastIndexOf("/")) + "/" + fileName;
					tempPic = tempPic.replace("\\", "/");
					picture += tempPic+ContextConstants.UPLOAD_SEPARATOR_VALUE+str.substring(str.lastIndexOf(ContextConstants.UPLOAD_SEPARATOR_VALUE)+2);
				}
			}
			if (GeneralUtil.isNotNull(picture)) {
				return picture;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 移动多个文件-> 用于将JQuery Uploadify上传的文件移动到指定的路径
	 * 
	 * @param fileNames
	 *            所有图片路径
	 * @param directory
	 *            移动到目录
	 * @param imageType
	 *            区分大图和小图 ContextConstant.EDIT_BIG_PICTURE
	 *            ContextConstant.EDIT_SMALL_PICTURE
	 */
	@SuppressWarnings("deprecation")
	public List<String> getMultiPictureUrlFromJQueryUpLoader(
			String[] fileNames, String directory, Integer imageType) {
		try {
			List<String> fileUrl = null;
			String servicePath = ServletActionContext.getRequest().getRealPath(
					"/");
			if (fileNames != null) {
				fileUrl = new ArrayList<String>();
				for (int i = 0; i < fileNames.length; i++) {
					String str = fileNames[i];
					// 先判断图片类型是否一直,如果不一致则跳过当前循环
					String suffix = str.substring(str.indexOf(ContextConstants.UPLOAD_SEPARATOR_VALUE) + 2,str.lastIndexOf(ContextConstants.UPLOAD_SEPARATOR_VALUE));
					if (!suffix.equals(imageType.toString())) {
						continue;
					}
					String path = str.substring(0, str.indexOf(ContextConstants.UPLOAD_SEPARATOR_VALUE));
					path = path.replace("\\", "/").substring(1);
					String tempPic;
					if(path.indexOf("temp") == -1) {
						tempPic = str;
					} else {
						String fileName = null;
						if(str.lastIndexOf(File.separator) == -1) {
							fileName = str.substring(str
									.lastIndexOf("/") + 1, str.indexOf(ContextConstants.UPLOAD_SEPARATOR_VALUE));
						} else {
							fileName = str.substring(str
									.lastIndexOf(File.separator) + 1, str.indexOf(ContextConstants.UPLOAD_SEPARATOR_VALUE));
						}
						// 被移动的文件夹
						File sourceFile = new File(servicePath + path);
						// 目标文件夹
						File targetFile = new File(servicePath + directory + path.substring(path.indexOf("temp") + 5, path.lastIndexOf("/")));
						// 如果目录没有存在,则创建目录
						if (!targetFile.isDirectory()) {
							targetFile.mkdirs();
						}
						// 将文件移动到另一个文件目录下
						sourceFile.renameTo(new File(targetFile + File.separator +fileName));
						tempPic = "/"+ directory + path.substring(path.indexOf("temp") + 5, path.lastIndexOf("/")) + "/" + fileName;
						tempPic = tempPic.replace("\\", "/");
						tempPic +=ContextConstants.UPLOAD_SEPARATOR_VALUE+str.substring(str.lastIndexOf(ContextConstants.UPLOAD_SEPARATOR_VALUE));
					}
					
					fileUrl.add(tempPic);
				}
			}
			if (fileUrl != null && fileUrl.size() > 0) {
				return fileUrl;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 物理删除评论报表任务对象
	 * @Title: EvaluationReportTaskAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String batchRealDelete() throws ServiceException {
		boolean isSuc = baseService.batchRealDelete(EvaluationReportTask.class, idsValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 保存菜单
	 * @return
	 */
	public String saveMenuId(){
		
		if(GeneralUtil.isNotNull(id)){
			
			getSession().put("menuId", id);
			data = AjaxData.responseSuccess(getText("addSuccess"));
			return JSON;
		}
		data = AjaxData.responseError(getText("addFail"));
		return JSON;
	}
	
	/**
	 * 导出html
	 * @param templatePath
	 * @param data
	 * @param title
	 */
	public void exportHTML(String templatePath, Collection<?> data, String title){
		
		response.setCharacterEncoding("UTF-8");
		ServletContext servletContext = ServletActionContext.getServletContext();
		//得到jasper文件
		try {
			File jasperFile = new File(servletContext.getRealPath(templatePath));
			JasperReport jasperReport = (JasperReport)JRLoader.loadObject(jasperFile);
			
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("title", title);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(data));
			JRHtmlExporter exporter = new JRHtmlExporter();
			
			exporter.setParameter(JRHtmlExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRHtmlExporterParameter.OUTPUT_WRITER, response.getWriter());
			exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
			exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "utf-8");
			exporter.exportReport();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 导出excel
	 * @param templatePath
	 * @param data
	 * @param title
	 */
	public void exportExcel(String templatePath, Collection<?> data, String downFileName){
		
		exportExcel(templatePath, data, downFileName, null);
	}
	
	/**
	 * 导出excel
	 * @param templatePath
	 * @param data
	 * @param title
	 */
	public void exportExcel(String templatePath, Collection<?> data, String downFileName, String subReportDir){
		
		try {
			response.setCharacterEncoding("UTF-8");
			ServletContext servletContext = ServletActionContext.getServletContext();
			//得到jasper文件
			File jasperFile = new File(servletContext.getRealPath(templatePath));
			JasperReport jasperReport = (JasperReport)JRLoader.loadObject(jasperFile);
			
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("title", downFileName);
			if(GeneralUtil.isNotNull(subReportDir)){
				
				parameters.put("SUBREPORT_DIR", servletContext.getRealPath(subReportDir) + "/");
			}
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(data));
			JRXlsExporter exporter = new JRXlsExporter();
			exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, response.getOutputStream());
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);

			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(downFileName, "utf-8") + ".xls");
			response.setContentType("application/vnd_ms-excel");
			exporter.exportReport();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}
	public String getIdsValue() {
		return idsValue;
	}
	public void setIdsValue(String idsValue) {
		this.idsValue = idsValue;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public String getOperationFlag() {
		return operationFlag;
	}
	public void setOperationFlag(String operationFlag) {
		this.operationFlag = operationFlag;
	}
	public String getManagerPageTipsMessage() {
		return managerPageTipsMessage;
	}
	public void setManagerPageTipsMessage(String managerPageTipsMessage) {
		this.managerPageTipsMessage = managerPageTipsMessage;
	}
	public Pagination getPageModule() {
		return pageModule;
	}
	public void setPageModule(Pagination pageModule) {
		this.pageModule = pageModule;
	}
	public String getVersionFlag() {
		return versionFlag;
	}
	public void setVersionFlag(String versionFlag) {
		this.versionFlag = versionFlag;
	}
	public String getVerificateCode() {
		return verificateCode;
	}
	public void setVerificateCode(String verificateCode) {
		this.verificateCode = verificateCode;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public Integer getStatusValue() {
		return statusValue;
	}

	public void setStatusValue(Integer statusValue) {
		this.statusValue = statusValue;
	}

	public String getBatchOperationType() {
		return batchOperationType;
	}

	public void setBatchOperationType(String batchOperationType) {
		this.batchOperationType = batchOperationType;
	}

	public Boolean getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}

	public Integer getCp() {
		return cp;
	}

	public void setCp(Integer cp) {
		this.cp = cp;
	}

	public Integer getPs() {
		return ps;
	}

	public void setPs(Integer ps) {
		this.ps = ps;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getAnnexTitle() {
		return annexTitle;
	}

	public void setAnnexTitle(String annexTitle) {
		this.annexTitle = annexTitle;
	}

	public String getAnnexContent() {
		return annexContent;
	}

	public void setAnnexContent(String annexContent) {
		this.annexContent = annexContent;
	}

	public String getAnnexOrderValue() {
		return annexOrderValue;
	}

	public void setAnnexOrderValue(String annexOrderValue) {
		this.annexOrderValue = annexOrderValue;
	}

	public String getCurrentMethod() {
		return currentMethod;
	}

	public void setCurrentMethod(String currentMethod) {
		this.currentMethod = currentMethod;
	}

	public Integer getPn() {
		return pn;
	}

	public void setPn(Integer pn) {
		this.pn = pn;
	}

	public Integer getMarkIndex() {
		return markIndex;
	}

	public void setMarkIndex(Integer markIndex) {
		this.markIndex = markIndex;
		if(markIndex != null) {
			getSession().put("markIndex", markIndex);
		}
	}

	public Integer getLeftIndex() {
		return leftIndex;
	}

	public void setLeftIndex(Integer leftIndex) {
		this.leftIndex = leftIndex;
		if(leftIndex != null) {
			getSession().put("leftIndex", leftIndex);
		}
	}

	public Integer getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(Integer orderValue) {
		this.orderValue = orderValue;
	}

	public String[] getFileNames() {
		return fileNames;
	}

	public void setFileNames(String[] fileNames) {
		this.fileNames = fileNames;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getTipMessage() {
		return tipMessage;
	}

	public void setTipMessage(String tipMessage) {
		this.tipMessage = tipMessage;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getFromApp() {
		return fromApp;
	}

	public void setFromApp(String fromApp) {
		this.fromApp = fromApp;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public IBaseService<Object, String> getBaseService() {
		return baseService;
	}

	public void setBaseService(IBaseService<Object, String> baseService) {
		this.baseService = baseService;
	}

	public IFrontMenuService getFrontMenuService() {
		return frontMenuService;
	}

	public void setFrontMenuService(IFrontMenuService frontMenuService) {
		this.frontMenuService = frontMenuService;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public InputStream getInputStream() throws UnsupportedEncodingException{
		
		if(data != null){
			
			String msg = (String)data;
			return new ByteArrayInputStream(msg.getBytes("utf-8")); 
		}else{
			
			return null;
		}
	}
}
