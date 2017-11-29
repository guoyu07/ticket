package com.ticket.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.enumer.SearchType;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CountSearch;
import com.ticket.pojo.PreResultDefinition;
import com.ticket.pojo.SqlParam.Condition;
import com.ticket.pojo.SqlParamGroup;
import com.ticket.pojo.TimeSearch;
import com.ticket.service.ICommonSearchService;
import com.ticket.service.ICountSearchService;
import com.ticket.service.IPreResultDefinitionService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.service.ITimeSearchService;
import com.ticket.util.DateUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;
import com.ticket.util.ValidateUtil;

import net.sf.jxls.transformer.XLSTransformer;

/**
 * 统计搜索词表控制器
 * @ClassName: CountSearchAction   
 * @Description:  提供统计搜索词表的相关操作方法. 
 * @author HiSay  
 * @date 2016-03-10 15:49:15
 *
 */
public class CountSearchAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//统计搜索词表的业务层
	@Resource private ICountSearchService countSearchService;
	
	@Resource private ISystemOperationLogService logService;
	
	@Resource private ICommonSearchService commonSearchService;
	
	@Resource private ITimeSearchService timeSearchService;
	
	@Resource private IPreResultDefinitionService preResultDefinitionService;
	//统计搜索词表实体
	private CountSearch countSearch;
	//主键
	private String id;
    //搜索词
	private String searchWord;
	//排除搜索词
	private String excludeWord;
    //搜索时间
	private String time;
    //触发的关键词
	private String keyword;
    //落地页
	private String goUrl;
	//落地页链接
	private String goUrlHref;
	private SearchType type;
	
	private InputStream inputStream;
	
	private String fileName;
	
	private String clickKeyword;
	
	/**
	 * 添加收索记录接口
	 * @return
	 * @throws ServiceException
	 */
	public String addCountSearchInterface() throws ServiceException{
		countSearchService.merge(id,goUrl,goUrlHref,versionFlag);
		PreResultDefinition definition = preResultDefinitionService.queryByPagename(goUrl);
		TimeSearch search = timeSearchService.queryByDefinitionId(definition.getId());
		String clickKeyword1 = search.getClickKeyword();
		if(GeneralUtil.isNotNull(clickKeyword1)){
			String clickKeyword2 = clickKeyword1 + "," + clickKeyword;
			search.setClickKeyword(clickKeyword2);
		}else{
			search.setClickKeyword(clickKeyword);
		}
		if(GeneralUtil.isNull(search.getClickRate())){
			search.setClickRate(1);
		}else{
			Integer clickRate = search.getClickRate() + 1;
			search.setClickRate(clickRate);
		}
		String url = search.getGoUrl();
		if(GeneralUtil.isNull(url)){
			search.setGoUrl(goUrlHref);
		}
		timeSearchService.merge(search);
		return null;
	}
	/**
	 * 添加统计搜索词表
	 * @Title: CountSearchAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addCountSearch";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(searchWord)) {
				data = AjaxData.responseError(getText("searchWord.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(time)) {
				data = AjaxData.responseError(getText("time.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(keyword)) {
				data = AjaxData.responseError(getText("keyword.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(goUrl)) {
				data = AjaxData.responseError(getText("goUrl.required"));
				return JSON;
			}
			//保存统计搜索词表实体
//			CountSearch isSuc = countSearchService.persist(searchWord, time, keyword, goUrl, goUrlHref, versionFlag);
			//根据保存结果返回页面
//			if(isSuc != null) {
				data = AjaxData.responseSuccess(getText("addSuccess"));
//			} else {
//				data = AjaxData.responseError(getText("addFailed"));
//			}
			return JSON;
		}
	}
	
	/**
	 * 修改统计搜索词表
	 * @Title: CountSearchAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String addGoUrl() throws ServiceException {
			/**
			 * 保存落地页
			 */
			countSearchService.merge(id,goUrl,goUrlHref,versionFlag);
			
			PreResultDefinition definition = preResultDefinitionService.queryByPagename(goUrl);
			TimeSearch search = timeSearchService.queryByDefinitionId(definition.getId());
			String clickKeyword1 = search.getClickKeyword();
			if(GeneralUtil.isNotNull(clickKeyword1)){
				String clickKeyword2 = clickKeyword1 + "," + clickKeyword;
				search.setClickKeyword(clickKeyword2);
			}else{
				search.setClickKeyword(clickKeyword);
			}
			if(GeneralUtil.isNull(search.getClickRate())){
				search.setClickRate(1);
			}else{
				Integer clickRate = search.getClickRate() + 1;
				search.setClickRate(clickRate);
			}
			String url = search.getGoUrl();
			if(GeneralUtil.isNull(url)){
				search.setGoUrl(goUrlHref);
			}
			timeSearchService.merge(search);
			
			
			data = AjaxData.responseSuccess(getText("editSuccess"));
			return JSON;
	}
	
	/**
	 * 管理统计搜索词表实体
	 * @Title: CountSearchAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		
		SqlParamGroup group = new SqlParamGroup("s.searchWord", Condition.eq, searchWord);
		group.and("s.type", Condition.eq, type);
		group.and("s.searchWord", Condition.not_in, ValidateUtil.isNull(excludeWord) ? null : excludeWord.split(","));
		group.and("s.status.createTime", Condition.ge, DateUtil.getDayStart(startTime));
		group.and("s.status.createTime", Condition.le, DateUtil.getDayEnd(endTime));
		this.setPageModule(countSearchService.paginationQuery("select s from " + CountSearch.class.getName() + " s " + group.toString(true) + " order by s.status.createTime desc", group.getParamArray()));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageCountSearch";
	}
	/**
	 * 批量下载
	 * @return
	 * @throws Exception
	 */
	public String batchDownLoad() throws Exception{
		String logContent = "下载搜索词统计信息";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		String destFilePath = createJxls();
		File file = new File(destFilePath);
		fileName = file.getName();
		inputStream = new FileInputStream(file);
		return "downLoad";
	}
	/**
	 * 生成xls文件
	 * @return
	 * @throws Exception
	 */
	public String createJxls() throws Exception{
		
		SqlParamGroup param = new SqlParamGroup("s.searchWord", Condition.eq, searchWord);
		param.and("s.status.createTime", Condition.ge, DateUtil.getDayStart(startTime));
		param.and("s.status.createTime", Condition.le, DateUtil.getDayEnd(endTime));
		param.and("s.type", Condition.eq, type);
		param.and("s.searchWord", Condition.not_in, ValidateUtil.isNull(excludeWord) ? null : excludeWord.split(","));
		
		List<CountSearch> searchs = countSearchService.getDbDAO().executeJPQLForQuery(
				"select s from " + CountSearch.class.getName() + " s " + param.toString(true) + " order by s.status.createTime desc", 
				param.getParamArray());
		
		String rootPath = ServletActionContext.getRequest().getRealPath("/manager");
		String srcFilePath = rootPath + "/memberExcel/countSearch.xls";  
		String destFilePath = rootPath + "/memberExcelTemplate/countSearch.xls";
		
		Map<String, List<CountSearch>> map = new HashMap<String, List<CountSearch>>();
		map.put("countSearchs", searchs);
		
		XLSTransformer former = new XLSTransformer();
		former.transformXLS(srcFilePath, map,destFilePath);
		return destFilePath;
	}
	/**
	 * 查看搜索详情
	 */
	public String detail() throws ServiceException{
		this.setCountSearch(countSearchService.queryById(CountSearch.class.getName(), id));
		return "detail";
	}
	/**
	 * 查看回收站
	 * @Title: CountSearchAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(countSearchService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleCountSearch";
	}
	
	/**
	 * 逻辑删除统计搜索词表对象
	 * @Title: CountSearchAction
	 * @Description: 把统计搜索词表对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = countSearchService.logicDeleteEntity(CountSearch.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除统计搜索词表对象
	 * @Title: CountSearchAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = countSearchService.remove(id);
		if(isSuc) {
			String logContent = "删除搜索词统计信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个统计搜索词表对象
	 * @Title: CountSearchAction
	 * @Description: 从回收站还原统计搜索词表对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = countSearchService.restoreEntity(CountSearch.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核统计搜索词表对象
	 * @Title: CountSearchAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = countSearchService.auditEntity(CountSearch.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: CountSearchAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = countSearchService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量彻底删除
	 * @return
	 * @throws ServiceException
	 */
	public String batchRealDelete() throws ServiceException{
		boolean isSuc = countSearchService.batchRealDelete(idsValue,versionFlag);
		if(isSuc) {
			String logContent = "批量删除角色信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public CountSearch getCountSearch() {
		return countSearch;
	}
	public void setCountSearch(CountSearch countSearch) {
		this.countSearch = countSearch;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getGoUrl() {
		return goUrl;
	}
	public void setGoUrl(String goUrl) {
		this.goUrl = goUrl;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getGoUrlHref() {
		return goUrlHref;
	}

	public void setGoUrlHref(String goUrlHref) {
		this.goUrlHref = goUrlHref;
	}
	public String getClickKeyword() {
		return clickKeyword;
	}
	public void setClickKeyword(String clickKeyword) {
		this.clickKeyword = clickKeyword;
	}
	public SearchType getType() {
		return type;
	}
	public void setType(SearchType type) {
		this.type = type;
	}
	public String getExcludeWord() {
		return excludeWord;
	}
	public void setExcludeWord(String excludeWord) {
		this.excludeWord = excludeWord;
	}
}
