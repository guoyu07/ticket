package com.ticket.serviceImpl;


import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.CommonSearch;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.PreResultDefinition;
import com.ticket.pojo.TimeSearch;
import com.ticket.service.IPreResultDefinitionService;
import com.ticket.service.ITimeSearchService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.PaginationContext;

/**
 * 搜索统计业务接口实现类
 * @ClassName: ITimeSearchService   
 * @Description: 提供搜索统计操作的增删改查   
 * @author HiSay  
 * @date 2016-08-12 11:26:22
 *
 */
public class TimeSearchServiceImpl extends BaseServiceImpl<TimeSearch, String> implements ITimeSearchService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(TimeSearchServiceImpl.class);
	
	@Resource
	private IPreResultDefinitionService preResultDefinitionService;

	@Override
	public boolean persist(String goUrl,Integer showRate,Integer clickRate,String showKeyword,String clickKeyword,String preResultDefinitionId, String versionFlag) throws ServiceException {
		TimeSearch timeSearch = new TimeSearch();
		timeSearch.setGoUrl(DecoderUtil.UtfDecoder(goUrl));
		timeSearch.setShowRate(showRate);
		timeSearch.setClickRate(clickRate);
		timeSearch.setShowKeyword(showKeyword);
		timeSearch.setClickKeyword(clickKeyword);
		PreResultDefinition definition = preResultDefinitionService.queryById(PreResultDefinition.class.getName(), preResultDefinitionId);
		timeSearch.setDefinition(definition);
		CommonEntity status = timeSearch.getStatus();
		status.setVersionFlag(versionFlag);
		timeSearch.setStatus(status);
		dbDAO.persist(timeSearch);
		return true;
	}
	
	@Override
	public boolean merge(String id, String goUrl,Integer showRate,Integer clickRate,String showKeyword,String clickKeyword,String preResultDefinitionId, String versionFlag) throws ServiceException {
		TimeSearch timeSearch = dbDAO.queryById(this.getTableNameFromEntity(TimeSearch.class), id);
		timeSearch.setGoUrl(DecoderUtil.UtfDecoder(goUrl));
		timeSearch.setShowRate(showRate);
		timeSearch.setClickRate(clickRate);
		timeSearch.setShowKeyword(showKeyword);
		timeSearch.setClickKeyword(clickKeyword);
		PreResultDefinition definition = preResultDefinitionService.queryById(PreResultDefinition.class.getName(), preResultDefinitionId);
		timeSearch.setDefinition(definition);
		CommonEntity status = timeSearch.getStatus();
		status.setVersionFlag(versionFlag);
		timeSearch.setStatus(status);
		dbDAO.merge(timeSearch);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		TimeSearch timeSearch = dbDAO.queryById(this.getTableNameFromEntity(TimeSearch.class), id);
		dbDAO.remove(timeSearch);
		return true;
	}

	@Override
	public boolean persist(String showKeyword,List<CommonSearch> commonSearchs)
			throws ServiceException {
		if(GeneralUtil.isNotNull(commonSearchs) && commonSearchs.size() > 0){
			for(CommonSearch commonSearch : commonSearchs){
				TimeSearch timeSearch = null;
				String title = commonSearch.getTitle();
				PreResultDefinition definition = preResultDefinitionService.queryByPagename(title);
				if(queryByDefinitionId(definition.getId()) != null){//如果今天已经搜索出来过此页面
					timeSearch = queryByDefinitionId(definition.getId());
					Integer showRate = timeSearch.getShowRate() + 1;
					timeSearch.setShowRate(showRate);
					String showKeyword1 = timeSearch.getShowKeyword();
					if(GeneralUtil.isNotNull(showKeyword1)){
						String showKeyword2 = showKeyword1 + "," + showKeyword;
						timeSearch.setShowKeyword(showKeyword2);
					}else{
						timeSearch.setShowKeyword(showKeyword);
					}
					
					this.merge(timeSearch);
				}else{
					timeSearch = new TimeSearch();
					timeSearch.setShowKeyword(showKeyword);
					timeSearch.setShowRate(1);
					timeSearch.setDefinition(definition);
					
					this.persist(timeSearch);
				}
				
			}
		}else{//输入的关键词没有触发任何预定搜索词
			TimeSearch search = new TimeSearch();
			search.setShowKeyword(showKeyword);
			this.persist(search);
		}
		return true;
	}

	@Override
	public boolean isExsit(String preResultDefinitionId)
			throws ServiceException {
		TimeSearch search = queryByDefinitionId(preResultDefinitionId);
		if(search  != null){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public TimeSearch queryByDefinitionId(String preResultDefinitionId)
			throws ServiceException {
		TimeSearch search = dbDAO.executeJPQLForQuerySingle("select c from " + TimeSearch.class.getName() + " c where c.definition.id = ? and c.status.deleteFlag=0 and YEAR(c.status.createTime) = YEAR(?) and MONTH(c.status.createTime) = MONTH(?) and DAY(c.status.createTime) = DAY(?)", TimeSearch.class , preResultDefinitionId,new Date(),new Date(),new Date());
		return search;
	}

	@Override
	public Pagination queryAllNoDate(Date date, String versionFlag, Integer pageSize) throws ServiceException {
		try {
			//先获取当前的实体名称
			String entityName = this.getTableNameFromEntity(this.getClass());
			
			StringBuffer whereCondition = new StringBuffer();
			int index = 3;
			List<Object> params = new ArrayList<Object>();
			if(GeneralUtil.isNotNull(date)){
				
				whereCondition.append(" and YEAR(s.status.createTime) = YEAR(?");
				whereCondition.append(index++);
				params.add(date);
				
				whereCondition.append(") and MONTH(s.status.createTime) = MONTH(?");
				whereCondition.append(index++);
				params.add(date);
				
				whereCondition.append(") and DAY(s.status.createTime) = DAY(?");
				whereCondition.append(index++);
				whereCondition.append(")");
				params.add(date);
				
				whereCondition.append(" and s.definition.id = null");
			}else{
				whereCondition.append(" and YEAR(s.status.createTime) = YEAR(?");
				whereCondition.append(index++);
				params.add(new Date());
				
				whereCondition.append(") and MONTH(s.status.createTime) = MONTH(?");
				whereCondition.append(index++);
				params.add(new Date());
				
				whereCondition.append(") and DAY(s.status.createTime) = DAY(?");
				whereCondition.append(index++);
				whereCondition.append(")");
				params.add(new Date());
				
				whereCondition.append(" and s.definition.id = null");
			}
			
			return dbDAO.queryByPageModule(entityName, deleteFlag, versionFlag, whereCondition.toString(), params.toArray(), orderBy, PaginationContext.getOffset(), pageSize);
		} catch(Exception e) {
			log.info("查询实体列表出错: " + e.fillInStackTrace());
			return null;
		}
	}

	@Override
	public Pagination queryEntityByAdmin(Date date, String versionFlag,
			Integer pageSize) throws ServiceException {
		try {
			//先获取当前的实体名称
			String entityName = this.getTableNameFromEntity(this.getClass());
			
			StringBuffer whereCondition = new StringBuffer();
			int index = 3;
			List<Object> params = new ArrayList<Object>();
			if(GeneralUtil.isNotNull(date)){
				
				whereCondition.append(" and YEAR(s.status.createTime) = YEAR(?");
				whereCondition.append(index++);
				params.add(date);
				
				whereCondition.append(") and MONTH(s.status.createTime) = MONTH(?");
				whereCondition.append(index++);
				params.add(date);
				
				whereCondition.append(") and DAY(s.status.createTime) = DAY(?");
				whereCondition.append(index++);
				whereCondition.append(")");
				params.add(date);
				
				whereCondition.append(" and s.definition.id != null");
			}else{
				whereCondition.append(" and YEAR(s.status.createTime) = YEAR(?");
				whereCondition.append(index++);
				params.add(new Date());
				
				whereCondition.append(") and MONTH(s.status.createTime) = MONTH(?");
				whereCondition.append(index++);
				params.add(new Date());
				
				whereCondition.append(") and DAY(s.status.createTime) = DAY(?");
				whereCondition.append(index++);
				whereCondition.append(")");
				params.add(new Date());
				
				whereCondition.append(" and s.definition.id != null");
			}
			
			return dbDAO.queryByPageModule(entityName, deleteFlag, versionFlag, whereCondition.toString(), params.toArray(), orderBy, PaginationContext.getOffset(), pageSize);
		} catch(Exception e) {
			log.info("查询实体列表出错: " + e.fillInStackTrace());
			return null;
		}
	}

	@Override
	public List<TimeSearch> queryByDate(Date date) throws ServiceException {
		List<TimeSearch> list = null;
		if(GeneralUtil.isNotNull(date)){
			list = dbDAO.executeJPQLForQuery("select c from " + TimeSearch.class.getName() + " c where YEAR(c.status.createTime) = YEAR(?) and MONTH(c.status.createTime) = MONTH(?) and DAY(c.status.createTime) = DAY(?)", TimeSearch.class,date,date,date);
		}else{
			list = this.queryAll(TimeSearch.class);
		}
		return list;
	}

	@Override
	public String createJxls(Date date) throws ServiceException {
		List<TimeSearch> list = queryByDate(date);
		if(GeneralUtil.isNotNull(list)){
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("搜索统计信息");
			
			HSSFRow row = sheet.createRow(0); 
		    HSSFCellStyle style = wb.createCellStyle();   
		    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    
		    HSSFCell cell = row.createCell(0);     //第一个单元格 
		    cell.setCellValue("日期");         //设定值 
		    cell.setCellStyle(style);          //内容居中 
		      
		    cell = row.createCell(1);          //第二个单元格   
		    cell.setCellValue("页面名称"); 
		    cell.setCellStyle(style); 
		      
		    cell = row.createCell(2);          //第三个单元格  
		    cell.setCellValue("落地页链接"); 
		    cell.setCellStyle(style); 
		    
		    cell = row.createCell(3);          //第三个单元格  
		    cell.setCellValue("展现量"); 
		    cell.setCellStyle(style); 
		    
		    cell = row.createCell(4);          //第三个单元格  
		    cell.setCellValue("点击量"); 
		    cell.setCellStyle(style); 
		    
		    cell = row.createCell(5);          //第三个单元格  
		    cell.setCellValue("触发展现的搜索词"); 
		    cell.setCellStyle(style); 
		    
		    cell = row.createCell(6);          //第三个单元格  
		    cell.setCellValue("触发点击的搜索词"); 
		    cell.setCellStyle(style); 
		    
		    for (int i = 0; i < list.size(); i++) { 
		    	TimeSearch timeSearch = list.get(i);
		    	row = sheet.createRow(i+1); 
		    	
		    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    	String time = sdf.format(timeSearch.getStatus().getCreateTime());
		    	row.createCell(0).setCellValue(time); 
		    	PreResultDefinition definition = timeSearch.getDefinition();
		    	if(definition != null){
		    		row.createCell(1).setCellValue(definition.getPageName()); 
		    	}
		        row.createCell(2).setCellValue(timeSearch.getGoUrl()); 
		        if(GeneralUtil.isNotNull(timeSearch.getShowRate())){
		        	row.createCell(3).setCellValue(timeSearch.getShowRate());
		        }
		        if(GeneralUtil.isNotNull(timeSearch.getClickRate())){
		        	row.createCell(4).setCellValue(timeSearch.getClickRate());
		        }
		        if(GeneralUtil.isNotNull(timeSearch.getShowKeyword())){
		        	row.createCell(5).setCellValue(timeSearch.getShowKeyword());
		        }
		        if(GeneralUtil.isNotNull(timeSearch.getClickKeyword())){
		        	row.createCell(6).setCellValue(timeSearch.getClickKeyword());
		        }
		    }
		    String rootPath = ServletActionContext.getRequest().getRealPath("/manager");
		    String srcFilePath = rootPath + "/memberExcel/timeSearch.xls";
		    try { 
		        FileOutputStream fout = new FileOutputStream(srcFilePath); 
		        wb.write(fout); 
		        fout.close(); 
		      } catch (IOException e) { 
		        e.printStackTrace(); 
		      } 
		    return srcFilePath;
		}else{
			return null;
		}
	}

}