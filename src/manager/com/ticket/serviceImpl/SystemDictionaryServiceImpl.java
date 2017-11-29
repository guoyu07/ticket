package com.ticket.serviceImpl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.SystemDictionary;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.service.ITreeService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.PaginationContext;
import com.ticket.util.PinyinUtil;
import com.ticket.util.ResourceUtil;

/**
 * 系统字典表业务接口实现类
 * @ClassName: ISysDictionaryService   
 * @Description: 提供系统字典表操作的增删改查   
 * @author HiSay  
 * @date 2015-10-24 15:27:39
 *
 */
public class SystemDictionaryServiceImpl extends BaseServiceImpl<SystemDictionary, String> implements ISystemDictionaryService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(SystemDictionaryServiceImpl.class);
	@Resource(name="treeService")
	private ITreeService treeService;

	@Override
	public boolean persist(String name,String value,int sort, String description,String parent_id) throws ServiceException {
		SystemDictionary parent = queryById(SystemDictionary.class.getName(), parent_id);
		return persist(name, value, sort, description, parent);
	}
	
	@Override
	public boolean persist(String name,String value,int sort, String description,SystemDictionary parent) throws ServiceException {
		
		if(existSameUnderParent(parent, name)){
			
			throw new ServiceException(ResourceUtil.getText("dictionary.existSameUnderParent"));
		}
		
		SystemDictionary systemDictionary = new SystemDictionary();
		systemDictionary.setName(DecoderUtil.UtfDecoder(name));
		systemDictionary.setValue(DecoderUtil.UtfDecoder(value));
		systemDictionary.setParent(parent);
		systemDictionary.setDescription(description);
		systemDictionary.setPingYin(PinyinUtil.getPinYinByFirstChar(value)) ;
		CommonEntity status = systemDictionary.getStatus();
		status.setVersionFlag(versionFlag);
		status.setOrderValue(sort);
		systemDictionary.setStatus(status);
		dbDAO.persist(systemDictionary);
		return true;
	}
	
	@Override
	public boolean merge(String id, String name,String value, int sort, String description,String parent_id) throws ServiceException {
		
		SystemDictionary parent = queryById(SystemDictionary.class.getName(), parent_id);
		return merge(id, name, value, sort, description, parent);
	}
	
	@Override
	public boolean merge(String id, String name,String value, int sort, String description,SystemDictionary parent) throws ServiceException {
		
		SystemDictionary systemDictionary = dbDAO.queryById(SystemDictionary.class.getName(), id);
		systemDictionary.setName(DecoderUtil.UtfDecoder(name));
		systemDictionary.setValue(DecoderUtil.UtfDecoder(value));
		systemDictionary.setDescription(description);
		systemDictionary.setParent(parent);
		CommonEntity status = systemDictionary.getStatus();
		status.setVersionFlag(versionFlag);
		status.setOrderValue(sort);
		systemDictionary.setStatus(status);
		dbDAO.merge(systemDictionary);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		SystemDictionary systemDictionary = dbDAO.queryById(this.getTableNameFromEntity(SystemDictionary.class), id);
		dbDAO.remove(systemDictionary);
		return true;
	}
	
	@Override
	public List<SystemDictionary> querySubByParentName(String parentName){
		Class c = SystemDictionary.class;
		SystemDictionary dict = getByName(parentName);
		if(dict == null) {
			dict = getByValue(parentName) ;
		}
		List<SystemDictionary> sysDictionary = treeService.querySubByParent(c, dict.getId());
		return sysDictionary;
	}
	
	@Override
	public List<SystemDictionary> querySubByParentValue(String parentValue){
		
		Class c = SystemDictionary.class;
		SystemDictionary dict = getByValue(parentValue);
		List<SystemDictionary> sysDictionary = treeService.querySubByParent(c, dict.getId());
		return sysDictionary;
	}
	
	@Override
	public List<SystemDictionary> querySubByParentValueOrderByLetter(String parentValue, String...columnNames){
		
		StringBuffer columns = new StringBuffer();
		for(String column : columnNames){
			
			columns.append(column);
			columns.append(",");
		}
		String sql = "select * from ticket_"+this.getTableNameFromEntity(SystemDictionary.class)+" where parent_id = ? order by CONVERT( name USING gbk ) COLLATE gbk_chinese_ci ASC";
		SystemDictionary dict = getByValue(parentValue);
		List<SystemDictionary> list = dbDAO.executeSQLForQuery(sql, SystemDictionary.class, dict.getId());
		if(list != null && !list.isEmpty()){
			list.remove(0);
			return list;
		}
		return null;
	}
	
	@Override
	public String getValueByName(String name) {
		
		SystemDictionary dict = (SystemDictionary)dbDAO.queryByCustom(SystemDictionary.class.getName(), 
				deleteFlag, versionFlag, "and name=?3", new Object[]{name});
		if(dict != null){
			return dict.getValue();
		}else{
			return null;
		}
	}
	
	@Override
	public String getDescriptByName(String name) {
		SystemDictionary dict = (SystemDictionary)dbDAO.queryByCustom(SystemDictionary.class.getName(), 
				deleteFlag, versionFlag, "and name=?3", new Object[]{name});
		if(dict != null){
			return dict.getDescription();
		}else{
			return null;
		}
	}
	
	@Override
	public SystemDictionary getByName(String name){
		
		SystemDictionary dict = (SystemDictionary)dbDAO.queryByCustom(SystemDictionary.class.getName(), 
				deleteFlag, versionFlag, "and name=?3", new Object[]{name});
		return dict;
	}

	@Override
	public SystemDictionary getByValue(String value){
		
		SystemDictionary dict = (SystemDictionary)dbDAO.queryByCustom(SystemDictionary.class.getName(), 
				deleteFlag, versionFlag, "and value=?3", new Object[]{value});
		return dict;
	}
	
	@Override
	public Integer importFromFile(String versionFlag) throws ServiceException{
		try {
			Workbook workbook = null;
			InputStream inputStream = new FileInputStream("D:\\flightData\\cityThreeCode.xls");
			workbook = Workbook.getWorkbook(inputStream);
			Sheet sheet = workbook.getSheet(0);
			int sheetColumns = sheet.getColumns();
			int sheetRows = sheet.getRows();
			SystemDictionary sysDic = null;
			sysDic = this.getByValue("cityThreeCode");
			if(sysDic == null){
				sysDic = new SystemDictionary();
				sysDic.setName("城市三字码");
				sysDic.setValue("cityThreeCode");
				sysDic.setDescription("城市三字码");
				
				this.persist(sysDic);
			}
			
			Integer count = 0;
			if(sheetColumns >0 && sheetRows>0){
				for(int i=1;i<sheetRows;i++){
					String name = sheet.getCell(0, i).getContents().trim();//三字码
					String value = sheet.getCell(1, i).getContents().trim();//城市名
					String description = sheet.getCell(2, i).getContents().trim();//机场名称
					this.persist(name, value, i, description, sysDic.getId());
					count += 1;
				}
			}
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public boolean existSameUnderParent(String parent_id, String name) {
		
		SystemDictionary parent = queryById(SystemDictionary.class.getName(), parent_id);
		return existSameUnderParent(parent, name);
	}

	@Override
	public boolean existSameUnderParent(SystemDictionary parent, String name) {
		List<SystemDictionary> subs = null;
		if(parent != null){
			
			subs = treeService.querySubByParent(SystemDictionary.class, parent.getId());
		}else{
			subs = treeService.queryRootNode(SystemDictionary.class);
		}
		for(SystemDictionary sub : subs){
			
			if(sub.getName().equals(name)){
				
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean existSameUnderParentByName(String parent_name, String name) {
		
		SystemDictionary parent = getByName(parent_name);
		return existSameUnderParent(parent, name);
	}
	
	@Override
	public SystemDictionary getUnderParent(String parent_id, String name) {
		
		SystemDictionary parent = queryById(SystemDictionary.class.getName(), parent_id);
		return getUnderParent(parent, name);
	}
	
	@Override
	public SystemDictionary getUnderParent(SystemDictionary parent, String name) {
		List<SystemDictionary> subs = null;
		if(parent != null){
			
			subs = treeService.querySubByParent(SystemDictionary.class, parent.getId());
		}else{
			subs = treeService.queryRootNode(SystemDictionary.class);
		}
		for(SystemDictionary sub : subs){
			
			if(sub.getName().equals(name.trim())){
				
				return sub;
			}
		}
		return null;
	}
	
	@Override
	public SystemDictionary getUnderParentByName(String parent_name, String name) {
		
		SystemDictionary parent = getByName(parent_name);
		return getUnderParent(parent, name);
	}

	@Override
	public Integer importDomesticCityCode(String versionFlag)
			throws ServiceException {
		try {
			Workbook workbook = null;
			InputStream inputStream = new FileInputStream("D:\\flightData\\domesticCityThreeCode.xls");
			workbook = Workbook.getWorkbook(inputStream);
			Sheet sheet = workbook.getSheet(0);
			int sheetColumns = sheet.getColumns();
			int sheetRows = sheet.getRows();
			SystemDictionary sysDic = null;
			sysDic = this.getByValue("domesticCityThreeCode");
			if(sysDic == null){
				sysDic = new SystemDictionary();
				sysDic.setName("国内城市三字码");
				sysDic.setValue("domesticCityThreeCode");
				sysDic.setDescription("国内城市三字码");
				
				this.persist(sysDic);
			}
			
			Integer count = 0;
			if(sheetColumns >0 && sheetRows>0){
				for(int i=1;i<sheetRows;i++){
					String name = sheet.getCell(0, i).getContents().trim();//三字码
					String value = sheet.getCell(1, i).getContents().trim();//机场拼音
					String description = sheet.getCell(2, i).getContents().trim();//机场名称
					this.persist(name, value, i, description, sysDic.getId());
					count += 1;
				}
			}
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public Integer importInternationalCityCode(String versionFlag)
			throws ServiceException {
		try {
			Workbook workbook = null;
			InputStream inputStream = new FileInputStream("D:\\flightData\\InternationalCityThreeCode.xls");
			workbook = Workbook.getWorkbook(inputStream);
			Sheet sheet = workbook.getSheet(0);
			int sheetColumns = sheet.getColumns();
			int sheetRows = sheet.getRows();
			SystemDictionary sysDic = null;
			sysDic = this.getByValue("InternationalCityThreeCode");
			if(sysDic == null){
				sysDic = new SystemDictionary();
				sysDic.setName("国际城市三字码");
				sysDic.setValue("InternationalCityThreeCode");
				sysDic.setDescription("国际城市三字码");
				
				this.persist(sysDic);
			}
			
			Integer count = 0;
			if(sheetColumns >0 && sheetRows>0){
				for(int i=1;i<sheetRows;i++){
					String name = sheet.getCell(0, i).getContents().trim();//三字码
					String value = sheet.getCell(1, i).getContents().trim();//机场拼音
					String description = sheet.getCell(2, i).getContents().trim();//机场名称
					this.persist(name, value, i, description, sysDic.getId());
					count += 1;
				}
			}
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public SystemDictionary queryByName(String keyword, String versionFlag)
			throws ServiceException {
		List<SystemDictionary> list = dbDAO.queryByList(SystemDictionary.class.getSimpleName(), deleteFlag, versionFlag, "and s.name=?3", new Object[]{DecoderUtil.UtfDecoder(keyword)}, orderBy, null);
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
		
	}

	@Override
	public List<SystemDictionary> querySubByParentNameAndValue(String name, String keyword) {
		if(GeneralUtil.isNotNull(keyword)) {
			SystemDictionary dict = this.getByValue(name) ;
			if(dict == null) {
				dict = this.getByName(name);
			}
			keyword = DecoderUtil.UtfDecoder(keyword).trim();
			List<SystemDictionary> list = dbDAO.queryByList(SystemDictionary.class.getSimpleName(), deleteFlag, versionFlag, "and (s.value like ?3 or s.name like ?4 or s.description like ?5) and s.parent.id=?6", new Object[]{"%"+keyword+"%", "%"+keyword+"%",  "%"+keyword+"%", dict.getId()}, orderBy, null);
			if(list != null && !list.isEmpty()){
				return list;
			}else{
				return null;
			}
		} else {
			return null;
		}
	}
	
	@Override
	public List<SystemDictionary> querySubByParentNameAndValueAndPinYing(String name, String keyword) {
		if(GeneralUtil.isNotNull(keyword)) {
			SystemDictionary dict = this.getByValue(name) ;
			if(dict == null) {
				dict = this.getByName(name);
			}
			keyword = DecoderUtil.UtfDecoder(keyword).trim();
			List<SystemDictionary> list = dbDAO.queryByList(SystemDictionary.class.getSimpleName(), deleteFlag, versionFlag, "and s.pingYin=?3 and s.parent.id=?4", new Object[]{keyword, dict.getId()}, orderBy, null);
			if(list != null && !list.isEmpty()){
				return list;
			}else{
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public Integer initialCityCodeHasPinYin() throws ServiceException {
		List<SystemDictionary> list = this.querySubByParentName("城市三字码");
		if(list != null && !list.isEmpty()) {
			int total = 0;
			for(SystemDictionary dict : list) {
				dict.setPingYin(PinyinUtil.getPinYinByFirstChar(dict.getValue())) ;
				dbDAO.merge(dict);
				total += 1;
			}
			return total;
		}
		return 0;
	}

	@Override
	public Integer importFlightCompanyTwoCode(String versionFlag) throws ServiceException {
		try {
			Workbook workbook = null;
			InputStream inputStream = new FileInputStream("D:\\flightData\\flightCompanyTwoCode.xls");
			workbook = Workbook.getWorkbook(inputStream);
			Sheet sheet = workbook.getSheet(0);
			int sheetColumns = sheet.getColumns();
			int sheetRows = sheet.getRows();
			SystemDictionary sysDic = null;
			sysDic = this.getByValue("flightCompanyTwoCode");
			if(sysDic == null){
				sysDic = new SystemDictionary();
				sysDic.setName("航空公司二字码");
				sysDic.setValue("flightCompanyTwoCode");
				sysDic.setDescription("航空公司二字码");
				
				this.persist(sysDic);
			}
			
			Integer count = 0;
			if(sheetColumns >0 && sheetRows>0){
				for(int i=1;i<sheetRows;i++){
					String name = sheet.getCell(0, i).getContents().trim();//航空公司名称
					String value = sheet.getCell(1, i).getContents().trim();//二字码
					this.persist(name, value, i+100, null, sysDic.getId());
					count += 1;
				}
			}
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public void initialAllCityByCityPoi(ServletContext application) throws ServiceException {
		//domesticCityThreeCode
		try {
			List<String> alphaList = new ArrayList<String>();
			alphaList.add("a");
			alphaList.add("b");
			alphaList.add("c");
			alphaList.add("d");
			alphaList.add("e");
			alphaList.add("f");
			alphaList.add("g");
			alphaList.add("h");
			alphaList.add("i");
			alphaList.add("j");
			alphaList.add("k");
			alphaList.add("l");
			alphaList.add("m");
			alphaList.add("n");
			alphaList.add("o");
			alphaList.add("p");
			alphaList.add("q");
			alphaList.add("r");
			alphaList.add("s");
			alphaList.add("t");
			alphaList.add("u");
			alphaList.add("v");
			alphaList.add("w");
			alphaList.add("x");
			alphaList.add("y");
			alphaList.add("z");
			for(String alpha : alphaList) {
				List<SystemDictionary> tempList = this.querySubByParentNameAndValueAndPinYing("domesticCityThreeCode", alpha);
				List<SystemDictionary> tempList2 = this.querySubByParentNameAndValueAndPinYing("InternationalCityThreeCode", alpha);
				if(tempList != null) {
					if(getApplication() != null) {
						getApplication().put(alpha + "_domesticCityList", tempList);
					} else {
						application.setAttribute(alpha + "_domesticCityList", tempList);
					}
				}
				if(tempList2 != null) {
					if(getApplication() != null) {
						getApplication().put(alpha + "_internationalCityList", tempList2);
					} else {
						application.setAttribute(alpha + "_internationalCityList", tempList2);
					}
				}
			}
		} catch(Exception e) {
			
		}
	}

	@Override
	public Integer importNationality(String versionFlag) throws ServiceException {
		try {
			Workbook workbook = null;
			InputStream inputStream = new FileInputStream("D:\\flightData\\nationality.xls");
			workbook = Workbook.getWorkbook(inputStream);
			Sheet sheet = workbook.getSheet(0);
			int sheetColumns = sheet.getColumns();
			int sheetRows = sheet.getRows();
			SystemDictionary sysDic = null;
			sysDic = this.getByValue("nationality");
			if(sysDic == null){
				sysDic = new SystemDictionary();
				sysDic.setName("国籍");
				sysDic.setValue("nationality");
				sysDic.setDescription("国籍");
				
				this.persist(sysDic);
			}
			
			Integer count = 0;
			if(sheetColumns >0 && sheetRows>0){
				for(int i=1;i<sheetRows;i++){
					String name = sheet.getCell(0, i).getContents().trim();//国籍
					this.persist(name, name, i, name, sysDic.getId());
					count += 1;
				}
			}
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<SystemDictionary> queryDomCityByHot(String versionFlag) {
		SystemDictionary sysDic = this.getByValue("domesticCityThreeCode");
		List<SystemDictionary> list = dbDAO.queryByList(SystemDictionary.class.getSimpleName(), deleteFlag, versionFlag, "and s.parent= ?3 and s.status.hot=1", new Object[]{sysDic }, orderBy, null);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<SystemDictionary> queryIntCityByHot(String versionFlag) {
		SystemDictionary sysDic = this.getByValue("InternationalCityThreeCode");
		List<SystemDictionary> list = dbDAO.queryByList(SystemDictionary.class.getSimpleName(), deleteFlag, versionFlag, "and s.parent= ?3 and s.status.hot =1", new Object[]{sysDic }, orderBy, null);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public Pagination queryPageModuleByParentDom(int pageSize, String versionFlag) throws ServiceException {
		SystemDictionary sysDic = this.getByValue("domesticCityThreeCode");
		return dbDAO.queryByPageModule(SystemDictionary.class.getSimpleName(), deleteFlag, versionFlag, " and s.parent =?3 ", new Object[]{sysDic}, orderBy, PaginationContext.getOffset(), pageSize);
	}
	
	@Override
	public Pagination queryPageModuleByParentInt(int pageSize, String versionFlag) throws ServiceException {
		SystemDictionary sysDic = this.getByValue("InternationalCityThreeCode");
		return dbDAO.queryByPageModule(SystemDictionary.class.getSimpleName(), deleteFlag, versionFlag, " and s.parent =?3 ", new Object[]{sysDic}, orderBy, PaginationContext.getOffset(), pageSize);
	}

	@Override
	public Pagination queryPageModuleByKeywordAndDom(String keyword, int pageSize,
			String versionFlag) throws ServiceException {
		if(GeneralUtil.isNotNull(keyword)){
			keyword = DecoderUtil.UtfDecoder(keyword).trim();
			SystemDictionary sysDic = this.getByValue("domesticCityThreeCode");
			return dbDAO.queryByPageModule(SystemDictionary.class.getSimpleName(), deleteFlag, versionFlag, " and s.parent =?3 and s.description like ?4", new Object[]{sysDic,"%"+keyword+"%"}, orderBy, PaginationContext.getOffset(), pageSize);
		}else{
			return null;
		}
	}
	@Override
	public Pagination queryPageModuleByKeywordAndInt(String keyword, int pageSize,
			String versionFlag) throws ServiceException {
		if(GeneralUtil.isNotNull(keyword)){
			keyword = DecoderUtil.UtfDecoder(keyword).trim();
			SystemDictionary sysDic = this.getByValue("InternationalCityThreeCode");
			return dbDAO.queryByPageModule(SystemDictionary.class.getSimpleName(), deleteFlag, versionFlag, " and s.parent =?3 and s.description like ?4", new Object[]{sysDic,"%"+keyword+"%"}, orderBy, PaginationContext.getOffset(), pageSize);
		}else{
			return null;
		}
	}

	@Override
	public List<SystemDictionary> queryByDescription(String keyword,
			String versionFlag) throws ServiceException {
		List<SystemDictionary> list = dbDAO.queryByList(SystemDictionary.class.getSimpleName(), deleteFlag, versionFlag, " and s.description like ?3", new Object[]{"%"+keyword+"%"}, orderBy, null);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public SystemDictionary queryParentNameAndDes(String parentName, String des)
			throws ServiceException {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("select s from SystemDictionary s where s.parent.name=?1 and s.description like ?2 ");
			return (SystemDictionary)dbDAO.executeJPQLForQuerySingle(sb.toString(), parentName,"%"+des+"%");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Integer importAirportCode(String versionFlag) throws ServiceException {
		try {
			Workbook workbook = null;
			InputStream inputStream = new FileInputStream("D:\\airportTFCode.xls");
			workbook = Workbook.getWorkbook(inputStream);
			Sheet sheet = workbook.getSheet(0);
			int sheetColumns = sheet.getColumns();
			int sheetRows = sheet.getRows();
			SystemDictionary sysDic = null;
			sysDic = this.getByValue("airportCode");
			if(sysDic == null){
				sysDic = new SystemDictionary();
				sysDic.setName("机场三四字码");
				sysDic.setValue("airportCode");
				sysDic.setDescription("机场三四字码");
				
				this.persist(sysDic);
			}
			
			Integer count = 0;
			if(sheetColumns >0 && sheetRows>0){
				for(int i=1;i<sheetRows;i++){
					String description = sheet.getCell(0, i).getContents().trim();//地名
					String value = sheet.getCell(1, i).getContents().trim();//三字码
					String name = sheet.getCell(2, i).getContents().trim();//四字码
					this.persist(name, value, i, description, sysDic.getId());
					count += 1;
				}
			}
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<SystemDictionary> querySubByParent(String parent_id) {
		
		List<SystemDictionary> list = dbDAO.executeJPQLForQueryEntity(
				"select c from " + SystemDictionary.class.getName() + " c where c.parent.id=? order by c.status.orderValue",
				parent_id);
		return list;
	}

	@Override
	public SystemDictionary queryByParentAndName(SystemDictionary parent,
			String name) throws ServiceException {
		SystemDictionary dictionary = dbDAO.executeJPQLForQuerySingle("select c from " + SystemDictionary.class.getName() + " c where c.parent.id = ? and c.name = ?", SystemDictionary.class , parent.getId() , name);
		return dictionary;
	}
}