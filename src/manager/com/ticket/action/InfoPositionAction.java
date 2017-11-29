package com.ticket.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.InfoPosition;
import com.ticket.pojo.SqlParam.Condition;
import com.ticket.pojo.SqlParamGroup;
import com.ticket.service.IInfoPositionService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

import net.sf.json.JSONObject;

/**
 * 信息定位控制器
 * 
 * @ClassName: InfoPositionAction
 * @Description: 提供信息定位的相关操作方法.
 * @author HiSay
 * @date 2015-10-20 18:13:14
 * 
 */
public class InfoPositionAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	// 信息定位的业务层
	@Resource
	private IInfoPositionService infoPositionService = null;
	
	@Resource private ISystemOperationLogService logService = null;
	// 信息定位实体
	private InfoPosition infoPosition = null;
	// 主键
	private String id = null;
	// 新闻id
	private String news_id = null;
	// 位置名称
	private String name = null;
	// 位置别名
	private String positionAlias = null;
	// 经度
	private String longitude = null;
	// 纬度
	private String latitude = null;
	private String mobile = null;
	private String url = null;
	// 楼层号
	private String floorNumber = null;
	private String[] longitudes = null;
	private String[] latitudes = null;
	private String[] floorNumbers = null;
	//多点位的点位名称
	private String className = null;
	
	private String[] classNames = null;
	
	private String keyword = null;
	
	/**
	 * 下载报表
	 * @return
	 * @throws ServiceException
	 */
	public String downReport() {
		
		SqlParamGroup group = new SqlParamGroup("s.status.deleteFlag", Condition.eq, ContextConstants.STATUS_OF_ZERO);
		group.and("s.status.versionFlag", Condition.eq, versionFlag);
		SqlParamGroup group2 = new SqlParamGroup("s.name", Condition.like_left, keyword).or("s.positionAlias", Condition.like_left, keyword);
		group.and(group2);
		List<InfoPosition> list = infoPositionService.getDbDAO().executeJPQLForQuery("select s from " + InfoPosition.class.getName() + " s " + group.toString(true), group.getParamArray());
		exportExcel("/WEB-INF/excel/jasper/infoPosition.jasper", list, "机场设施位置");
		return null;
	}
	
	/**
	 * 添加信息定位
	 * 
	 * @Title: InfoPositionAction
	 * @Description:
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String add() throws ServiceException {
		if (GeneralUtil.isNull(operationFlag)) {
			return "addInfoPosition";
		} else {
			// 非空验证.
			if (GeneralUtil.isNull(positionAlias)) {
				data = AjaxData.responseError(getText("positionAlias.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(longitudes)) {
				data = AjaxData.responseError(getText("longitude.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(latitudes)) {
				data = AjaxData.responseError(getText("latitude.required"));
				return JSON;
			}
			if (longitudes.length == 1) {
				for (String a : longitudes) {
					longitude = a.trim();
				}
			} else {
				longitude = longitudes[0];
				for (int i = 1; i < longitudes.length; i++) {
					longitude += "," + longitudes[i].trim() + ",";
				}
			}
			if (latitudes.length == 1) {
				for (String b : latitudes) {
					latitude = b.trim();
				}
			} else {
				latitude = latitudes[0];
				for (int i = 1; i < latitudes.length; i++) {
					latitude += "," + latitudes[i].trim() + ",";
				}
			}
			if (floorNumbers.length == 1) {
				for (String c : floorNumbers) {
					floorNumber = c.trim();
				}
			} else {
				floorNumber = floorNumbers[0];
				for (int i = 1; i < floorNumbers.length; i++) {
					floorNumber += "," + floorNumbers[i].trim() + ",";
				}
			}
			
			if(classNames.length == 1){
				for (String c : classNames) {
					className = c.trim();
				}
			}else{
				className = classNames[0];
				for (int i = 1; i < classNames.length; i++) {
					className += "," + classNames[i].trim() + ",";
				}
			}

			// 验证别名是否存在
			if (infoPositionService.validateAliasExists(positionAlias)) {
				data = AjaxData.responseError(getText("alias.exists"));
				return JSON;
			}
			// 保存信息定位实体
			boolean isSuc = infoPositionService.persist(news_id, name,
					positionAlias, longitude, latitude, floorNumber, mobile,
					url,className, versionFlag);
			// 根据保存结果返回页面
			if (isSuc) {
				String logContent = "新增信息定位";
				String logName = SystemOparetionLogUtil.getLogName();
				String logTime = SystemOparetionLogUtil.getLogTime();
				String logIp = SystemOparetionLogUtil.getLogIp();
				
				logService.persist(logName, logContent, logTime, logIp, versionFlag);
				data = AjaxData.responseSuccess(getText("addSuccess"));
			} else {
				data = AjaxData.responseError(getText("addFailed"));
			}
			return JSON;
		}
	}

	/**
	 * 修改信息定位
	 * 
	 * @Title: InfoPositionAction
	 * @Description:
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String edit() throws ServiceException {
		if (GeneralUtil.isNull(operationFlag)) {
			InfoPosition position = infoPositionService.queryById(
					InfoPosition.class.getSimpleName(), id);
			String longitudes = position.getLongitude();
			String latitudes = position.getLatitude();
			String floorNumbers = position.getFloorNumber();
			String name = position.getName();
			String mobile = position.getMobile();
			String url = position.getUrl();
			String bieming = position.getPositionAlias();
			String classNames = position.getClassName();
			if(GeneralUtil.isNotNull(classNames)){
				String[] classNameArr = classNames.split(",");
				ActionContext.getContext().put("classNameArr", classNameArr);
			}
			if (longitudes.indexOf(",") != -1) {
				String[] longitudesArr = longitudes.split(",");
				String[] latitudesArr = latitudes.split(",");
				String[] floorNumbersArr = floorNumbers.split(",");
				ActionContext.getContext().put("longitudesArr", longitudesArr);
				ActionContext.getContext().put("latitudesArr", latitudesArr);
				ActionContext.getContext().put("floorNumbersArr", floorNumbersArr);
				ActionContext.getContext().put("name", name);
				ActionContext.getContext().put("positionAlias", bieming);
				ActionContext.getContext().put("mobile", mobile);
				ActionContext.getContext().put("url", url);
			} else {
				this.setInfoPosition(infoPositionService.queryById(
						InfoPosition.class.getSimpleName(), id));
			}
			return "editInfoPosition";
		} else {
			// 非空验证.
			if (GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(positionAlias)) {
				data = AjaxData.responseError(getText("positionAlias.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(longitudes)) {
				data = AjaxData.responseError(getText("longitude.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(latitudes)) {
				data = AjaxData.responseError(getText("latitude.required"));
				return JSON;
			}

			if (longitudes.length == 1) {
				for (String a : longitudes) {
					longitude = a;
				}
			} else {
				longitude = longitudes[0];
				for (int i = 1; i < longitudes.length; i++) {
					longitude += "," + longitudes[i] + ",";
				}
			}
			if (latitudes.length == 1) {
				for (String b : latitudes) {
					latitude = b;
				}
			} else {
				latitude = latitudes[0];
				for (int i = 1; i < latitudes.length; i++) {
					latitude += "," + latitudes[i] + ",";
				}
			}
			if (floorNumbers.length == 1) {
				for (String c : floorNumbers) {
					floorNumber = c;
				}
			} else {
				floorNumber = floorNumbers[0];
				for (int i = 1; i < floorNumbers.length; i++) {
					floorNumber += "," + floorNumbers[i] + ",";
				}
			}
			
			if(classNames.length == 1){
				for (String c : classNames) {
					className = c;
				}
			}else{
				className = classNames[0];
				for (int i = 1; i < classNames.length; i++) {
					className += "," + classNames[i] + ",";
				}
			}
			// 修改信息定位实体
			boolean isSuc = infoPositionService.merge(id, news_id, name,
					positionAlias, longitude, latitude, floorNumber, mobile,
					url,className, versionFlag);
			// 根据修改结果返回页面
			if (isSuc) {
				String logContent = "修改信息定位";
				String logName = SystemOparetionLogUtil.getLogName();
				String logTime = SystemOparetionLogUtil.getLogTime();
				String logIp = SystemOparetionLogUtil.getLogIp();
				
				logService.persist(logName, logContent, logTime, logIp, versionFlag);
				data = AjaxData.responseSuccess(getText("editSuccess"));
			} else {
				data = AjaxData.responseError(getText("editFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * @author fq
	 * 根据位置别名获取定位信息
	 * @method getInfoPositionInterface
	 * @return
	 * @throws ServiceException
	 * @return String
	 * @date 2016-3-15 上午10:31:16
	 */
	public String getInfoPositionInterface() throws ServiceException {
		InfoPosition position = infoPositionService.queryByAlias(positionAlias);
		String longitudes = position.getLongitude();
		String latitudes = position.getLatitude();
		String floorNumbers = position.getFloorNumber();
		String names = position.getClassName();
		String[] longitudesArr = longitudes.split(",");
		String[] latitudesArr = latitudes.split(",");
		String[] floorNumbersArr = floorNumbers.split(",");
		String[] nameArr = names.split(",");
		StringBuffer sb = new StringBuffer();
		sb.append("{\"morePoint\":[");

		for (int i = 0; i < longitudesArr.length; i++) {
			sb.append("{");
			sb.append("\"longitude\":\"").append(longitudesArr[i]).append("\",");
			sb.append("\"latitude\":\"").append(latitudesArr[i]).append("\",");
			sb.append("\"title\":\"").append(nameArr[i]).append("\",");
			sb.append("\"floorNumber\":\"").append(floorNumbersArr[i]).append("\"");
			sb.append("},");
		}

		sb.deleteCharAt(sb.length() - 1);
		sb.append("]}");
		data = sb.toString();
		return TEXT;
	}
	
	/**
	 * 根据柜台查找点位
	 * @return
	 * @throws ServiceException
	 */
	public String getInfoPositionByCounter() throws ServiceException{
		Integer index = positionAlias.indexOf("(");
		if(index != -1){
			positionAlias = positionAlias.substring(0, index);
		}
		
		String[] aliass = positionAlias.trim().split("/");
		ArrayList<Map<String, Object>> arrayList = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		if(GeneralUtil.isNotNull(aliass)){
			for(String alias: aliass){
				String[] a = alias.split("-");
				if(a.length > 1){//如果数据格式如D01-09
					String first = a[0];
					String numOne = first.substring(1);
					String last = a[1];
					Integer one = Integer.valueOf(numOne);
					Integer two = Integer.valueOf(last);
					for(int i = one;i <= two;i++){
						String alia = "";
						if(i < 10){
							alia = first.substring(0, 1) + "0" + i;
						}else{
							alia = first.substring(0, 1) + i;
						}
						InfoPosition infoPosition = infoPositionService.queryByAlias(alia);
						if(GeneralUtil.isNotNull(infoPosition)){
							map.put("longitude", infoPosition.getLongitude());
							map.put("latitude", infoPosition.getLatitude());
							map.put("title", infoPosition.getName());
							map.put("floorNumber", infoPosition.getFloorNumber());
							arrayList.add(map);
							map = new HashMap<String, Object>();
						}
					}
				}else{//格式D01
					InfoPosition infoPosition = infoPositionService.queryByAlias(a[0]);
					if(GeneralUtil.isNotNull(infoPosition)){
						map.put("longitude", infoPosition.getLongitude());
						map.put("latitude", infoPosition.getLatitude());
						map.put("title", infoPosition.getName());
						map.put("floorNumber", infoPosition.getFloorNumber());
						arrayList.add(map);
						map = new HashMap<String, Object>();
					}
				}
			}
		}
		
		JSONObject object = new JSONObject();
		object.put("morePoint", arrayList);
		data = JSONObject.fromObject(object).toString();
		return TEXT;
	}
	
	/**
	 * @author xw
	 * 根据名称获取定位信息接口
	 * @return
	 * @throws ServiceException
	 * @throws UnsupportedEncodingException 
	 */
	public String getInfoPositionByName() throws ServiceException, UnsupportedEncodingException {
		name = request.getParameter("name");
		List<InfoPosition> positions = infoPositionService.queryByNameAndAlias(name,positionAlias);
		
		ArrayList<Map<String, Object>> arrayList = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		if(positions != null){
			for(InfoPosition position:positions){
				String longitudes = position.getLongitude();
				String latitudes = position.getLatitude();
				String floorNumbers = position.getFloorNumber();
				String names = position.getClassName();
				String[] longitudesArr = longitudes.split(",");
				String[] latitudesArr = latitudes.split(",");
				String[] floorNumbersArr = floorNumbers.split(",");
				String[] nameArr = new String[]{};
				if(GeneralUtil.isNotNull(names)){
					nameArr = names.split(",");
				}
				for (int i = 0; i < longitudesArr.length; i++) {
					map.put("longitude", longitudesArr[i]);
					map.put("latitude", latitudesArr[i]);
					if(GeneralUtil.isNotNull(nameArr) && nameArr.length > 0){
						map.put("name", nameArr[i]);
					}else{
						map.put("name", "");
					}
					map.put("floorNumber", floorNumbersArr[i]);
					arrayList.add(map);
					map = new HashMap<String, Object>();
				}
			}
		}
		JSONObject object = new JSONObject();
		object.put("morePoint", arrayList);
		data = JSONObject.fromObject(object).toString();
		return TEXT;
	}
	
	/**
	 * 航班查询的柜台导航
	 * @return
	 * @throws ServiceException
	 * @throws UnsupportedEncodingException
	 */
	public String getInfoPositionByPositionAlias() throws ServiceException, UnsupportedEncodingException {
		String[] aliass = positionAlias.trim().split("-");
		ArrayList<Map<String, Object>> arrayList = new ArrayList<Map<String,Object>>();
		List<InfoPosition> infoPositions = new ArrayList<InfoPosition>();
		char[] chars = new char[aliass.length];
		char[] charss = new char[]{};
		Map<String, Object> map = new HashMap<String, Object>();
		if(GeneralUtil.isNotNull(aliass)){
			for(int i=0;i<aliass.length;i++){
				String first = aliass[i].substring(0,1);
				chars[i] = first.charAt(0);
			}
		}
		if (GeneralUtil.isNotNull(chars)) {
			charss = GeneralUtil.getAlpha(chars[0], chars[1]);
		}
		if(GeneralUtil.isNotNull(charss)){
			for(int j=0;j<charss.length;j++){
				infoPosition = infoPositionService.queryByAlias("newszhijiqu-" + String.valueOf(charss[j]).toLowerCase());
				if(infoPosition != null){
					infoPositions.add(infoPosition);
					String longitudes = infoPosition.getLongitude();
					String latitudes = infoPosition.getLatitude();
					String floorNumbers = infoPosition.getFloorNumber();
					String names = infoPosition.getClassName();
					String[] longitudesArr = longitudes.split(",");
					String[] latitudesArr = latitudes.split(",");
					String[] floorNumbersArr = floorNumbers.split(",");
					String[] nameArr = new String[]{};
					if(GeneralUtil.isNotNull(names)){
						nameArr = names.split(",");
					}
					for (int i = 0; i < longitudesArr.length; i++) {
						map.put("longitude", longitudesArr[i]);
						map.put("latitude", latitudesArr[i]);
						if(GeneralUtil.isNotNull(nameArr) && nameArr.length > 0){
							map.put("title", nameArr[i]);
						}else{
							map.put("title", "");
						}
						map.put("floorNumber", floorNumbersArr[i]);
						arrayList.add(map);
						map = new HashMap<String, Object>();
					}
				}
			}
		}
		JSONObject object = new JSONObject();
		object.put("morePoints", arrayList);
		data = JSONObject.fromObject(object).toString();
		return TEXT;
	}
	
	/**
	 * 管理信息定位实体
	 * 
	 * @Title: InfoPositionAction
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
//		if(GeneralUtil.isNotNull(keyword)){
//			this.setPageModule(infoPositionService.paginationQuery("select c from " + InfoPosition.class.getName() + " c where c.name like ? or c.positionAlias like ?", "%"+keyword+"%","%"+keyword+"%"));
//		}else{
//			this.setPageModule(infoPositionService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
//		}
		SqlParamGroup group = new SqlParamGroup("s.status.deleteFlag", Condition.eq, ContextConstants.STATUS_OF_ZERO);
		group = group.and("s.status.versionFlag", Condition.eq, versionFlag);
		SqlParamGroup group2 = new SqlParamGroup("s.name", Condition.like_left, keyword).or("s.positionAlias", Condition.like_left, keyword);
		group.and(group2);
		this.setPageModule(infoPositionService.paginationQuery("select s from " + InfoPosition.class.getName() + " s " + group.toString(true) + " order by s.status.createTime desc", group.getParamArray()));
		
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageInfoPosition";
	}

	/**
	 * 查看回收站
	 * 
	 * @Title: InfoPositionAction
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(infoPositionService.queryRecycleEntity(versionFlag,
				ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleInfoPosition";
	}

	/**
	 * 逻辑删除信息定位对象
	 * 
	 * @Title: InfoPositionAction
	 * @Description: 把信息定位对象放入回收站.
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = infoPositionService.logicDeleteEntity(
				InfoPosition.class.getSimpleName(), id);
		if (isSuc) {
			String logContent = "逻辑删除信息定位";
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
	 * 物理删除信息定位对象
	 * 
	 * @Title: InfoPositionAction
	 * @Description: 把配置对象实际从数据库里面删除.
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = infoPositionService.remove(id);
		if (isSuc) {
			String logContent = "物理删除信息定位";
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
	 * 还原一个信息定位对象
	 * 
	 * @Title: InfoPositionAction
	 * @Description: 从回收站还原信息定位对象
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = infoPositionService.restoreEntity(
				InfoPosition.class.getSimpleName(), id);
		if (isSuc) {
			String logContent = "还原信息定位";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}

	/**
	 * 审核信息定位对象
	 * 
	 * @Title: InfoPositionAction
	 * @Description:
	 * @param @return
	 * @param @throws ServiceException 设定文件
	 * @return 返回类型
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = infoPositionService.auditEntity(
				InfoPosition.class.getSimpleName(), id, statusValue);
		if (isSuc) {
			String logContent = "审核信息定位";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}

	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * 
	 * @Title: InfoPositionAction
	 * @Description:
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = infoPositionService.batchOperationEntity(versionFlag,
				idsValue, batchOperationType, isChecked);
		if (isSuc) {
			String logContent = "批量操作信息定位";
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

	public InfoPosition getInfoPosition() {
		return infoPosition;
	}

	public void setInfoPosition(InfoPosition infoPosition) {
		this.infoPosition = infoPosition;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNews_id() {
		return news_id;
	}

	public void setNews_id(String news_id) {
		this.news_id = news_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getPositionAlias() {
		return positionAlias;
	}

	public void setPositionAlias(String positionAlias) {
		this.positionAlias = positionAlias;
	}

	public String getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(String floorNumber) {
		this.floorNumber = floorNumber;
	}

	public IInfoPositionService getInfoPositionService() {
		return infoPositionService;
	}

	public void setInfoPositionService(IInfoPositionService infoPositionService) {
		this.infoPositionService = infoPositionService;
	}

	public String[] getLongitudes() {
		return longitudes;
	}

	public void setLongitudes(String[] longitudes) {
		this.longitudes = longitudes;
	}

	public String[] getLatitudes() {
		return latitudes;
	}

	public void setLatitudes(String[] latitudes) {
		this.latitudes = latitudes;
	}

	public String[] getFloorNumbers() {
		return floorNumbers;
	}

	public void setFloorNumbers(String[] floorNumbers) {
		this.floorNumbers = floorNumbers;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String[] getClassNames() {
		return classNames;
	}

	public void setClassNames(String[] classNames) {
		this.classNames = classNames;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
