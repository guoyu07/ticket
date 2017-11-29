package com.ticket.serviceImpl;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.persistence.Table;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportInfo;
import com.ticket.pojo.DepartFromPort;
import com.ticket.service.IAirportInfoService;
import com.ticket.service.IDepartFromPortService;
import com.ticket.util.DateUtil;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * 离港航班业务接口实现类
 * @ClassName: IDepartFromPortService   
 * @Description: 提供离港航班操作的增删改查   
 * @author HiSay  
 * @date 2015-12-01 10:07:22
 *
 */
public class DepartFromPortServiceImpl extends BaseServiceImpl<DepartFromPort, String> implements IDepartFromPortService {

	private static final Log log = LogFactory.getLog(DepartFromPortServiceImpl.class);
	@Resource IAirportInfoService airportInfoService = null;//机场信息的业务层
	
	@Override
	public boolean merge(String id, String flt,String acw,String frs,String doi,Date std,String des,Date sta,Date tdt,String cid,String gat,Date got,Date etd,Date abt,boolean isShare, String org) throws ServiceException {
		DepartFromPort departFromPort = dbDAO.queryById(this.getTableNameFromEntity(DepartFromPort.class), id);
		departFromPort.setFlt(DecoderUtil.UtfDecoder(flt));
		departFromPort.setAcw(DecoderUtil.UtfDecoder(acw));
		departFromPort.setFrs(DecoderUtil.UtfDecoder(frs));
		departFromPort.setDoi(DecoderUtil.UtfDecoder(doi));
		departFromPort.setStd(std);
		departFromPort.setDes(DecoderUtil.UtfDecoder(des));
		departFromPort.setCid(DecoderUtil.UtfDecoder(cid));
		departFromPort.setGat(DecoderUtil.UtfDecoder(gat));
		departFromPort.setGot(got);
		departFromPort.setEtd(etd);
		departFromPort.setAbt(abt);
		departFromPort.setOrg(org);
		dbDAO.merge(departFromPort);
		return true;
	}

	@Override
	public boolean persist(String flt,String acw,String frs,String doi,Date std, String des,Date sta,Date tdt,String cid,String gat,Date got,Date etd,Date abt,boolean isShare, String org) throws ServiceException {
		DepartFromPort departFromPort = new DepartFromPort();
		departFromPort.setFlt(DecoderUtil.UtfDecoder(flt));
		departFromPort.setAcw(DecoderUtil.UtfDecoder(acw));
		departFromPort.setFrs(DecoderUtil.UtfDecoder(frs));
		departFromPort.setDoi(DecoderUtil.UtfDecoder(doi));
		departFromPort.setStd(std);
		departFromPort.setDes(DecoderUtil.UtfDecoder(des));
		departFromPort.setCid(DecoderUtil.UtfDecoder(cid));
		departFromPort.setGat(DecoderUtil.UtfDecoder(gat));
		departFromPort.setGot(got);
		departFromPort.setEtd(etd);
		departFromPort.setAbt(abt);
		departFromPort.setOrg(org);
		dbDAO.persist(departFromPort);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		DepartFromPort departFromPort = dbDAO.queryById(this.getTableNameFromEntity(DepartFromPort.class), id);
		dbDAO.remove(departFromPort);
		return true;
	}
	
	@Override
	public Integer importFromFile(String filePath) throws ServiceException {
		try {
			Workbook workBook = null;
			// 构建Workbook对象 只读Workbook对象
			// 直接从本地文件创建Workbook
			// 从输入流创建Workbook
			InputStream inputStream = new FileInputStream("D:\\flightData\\departPort.xls");//new FileInputStream(getFileBasePath() + filePath);
			workBook = Workbook.getWorkbook(inputStream);
			// Sheet(术语：工作表)就是Excel表格左下角的Sheet1,Sheet2,Sheet3但在程序中
			// Sheet的下标是从0开始的
			// 获取第一张Sheet表
			Sheet sheets = workBook.getSheet(0);
			// 获取Sheet表中所包含的总列数
			int sheetColumns = sheets.getColumns();
			// 获取Sheet表中所包含的总行数
			int sheetRows = sheets.getRows();
			//导入成功的数量
			Integer sucCount = 0;
			if(sheetColumns > 0 && sheetRows > 0) {
				for(int i=1; i<sheetRows; i++) {
					String flt = sheets.getCell(0, i).getContents().trim();//航班号
					String acw = sheets.getCell(1, i).getContents().trim();//航空公司二字码
					String frs = sheets.getCell(2, i).getContents().trim();//航班实时状态
					String doi = sheets.getCell(3, i).getContents().trim();//国内或国际
					String std = sheets.getCell(4, i).getContents().trim();//计划出港时间
					String des = sheets.getCell(5, i).getContents().trim();//目的地三字码
					String sta = sheets.getCell(6, i).getContents().trim();//计划到达时间
					String tdt = sheets.getCell(7, i).getContents().trim();//实际到达时间
					String cid = sheets.getCell(8, i).getContents().trim();//值机柜台
					String gat = sheets.getCell(9, i).getContents().trim();//登机口
					String got = sheets.getCell(10, i).getContents().trim();//预计登机时间
					String etd = sheets.getCell(11, i).getContents().trim();//预计出港时间
					String abt = sheets.getCell(12, i).getContents().trim();//实际出港时间
					this.persist(flt, acw, frs, doi, DateUtil.parseStringToDate(std, "MM/dd/yy HH:mm"), des, 
							DateUtil.parseStringToDate(sta, "MM/dd/yy HH:mm"), DateUtil.parseStringToDate(tdt, "MM/dd/yy HH:mm"),
							cid, gat, DateUtil.parseStringToDate(got, "MM/dd/yy HH:mm"), DateUtil.parseStringToDate(etd, "MM/dd/yy HH:mm"),
							DateUtil.parseStringToDate(abt, "MM/dd/yy HH:mm"), false, versionFlag);
					sucCount += 1;
				}
			}
			return sucCount;
		} catch(Exception e) {
			e.printStackTrace();
			log.info(e.fillInStackTrace()) ;
			return 0;
		}
	}

	@Override
	public List<DepartFromPort> queryListByCityAndDate(String org, String des,
			String flightDate, String versionFlag) throws ServiceException {
		String sql = "select * from "+DepartFromPort.class.getAnnotation(Table.class).name()+" t where t.org =? and (t.des=? or t.vi1=? or t.vi2=? or t.vi3=? or t.vi4=? or t.vi5=? or t.vi6=?) and t.flightDate =? order by t.std asc";
		List<DepartFromPort> list = dbDAO.executeSQLForQuery(sql, DepartFromPort.class, org,des,des,des,des,des,des,des,flightDate);

		//把小于当前时间的排到后面
		List<DepartFromPort> tempList = new ArrayList<DepartFromPort>();
		List<DepartFromPort> frontList = new ArrayList<DepartFromPort>();
		for(DepartFromPort dept:list){
			if(GeneralUtil.isNotNull(dept.getTdes())){
				Date tdes = dept.getTdes();
				Date now = new Date();
				if(tdes.getTime() < now.getTime()) {
					tempList.add(dept);
				}else{
					frontList.add(dept);
				}
			}else{
				frontList.add(dept);
			}
		}
		for(DepartFromPort dp : tempList){
			frontList.add(dp);
		}
		return frontList;
	}

	@Override
	public DepartFromPort randomGetOne() {
		
		Long count = dbDAO.executeJPQLForQuerySingle("select count(*) from " + DepartFromPort.class.getSimpleName(), 
				Long.class);
		int rand = new Random().nextInt(count.intValue());
		List<DepartFromPort> one = dbDAO.executeSQLForQuery(
				"select t.* from ticket_" + DepartFromPort.class.getSimpleName() + " t limit " + rand + ",1",
				DepartFromPort.class);
		return one.get(0);
	}

	@Override
	public List<DepartFromPort> queryList(Integer size, String versionFlag)
			throws ServiceException {
		List<DepartFromPort> list = dbDAO.queryByList(DepartFromPort.class.getSimpleName(), deleteFlag, versionFlag, null, null, orderValueOrderBy, size);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<DepartFromPort> queryBykeyword(String keyword,String versionFlag) throws ServiceException {
		keyword = DecoderUtil.UtfDecoder(keyword);
		List<DepartFromPort> list = dbDAO.queryByList(DepartFromPort.class.getSimpleName(), deleteFlag, versionFlag, "and s.flt like ?3", new Object[]{"%"+keyword+"%"}, orderValueOrderBy, null);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<DepartFromPort> getByFrs(String frs){
		List<DepartFromPort> list = null;
		try{
			list = dbDAO.executeJPQLForQueryEntity("select c from " + DepartFromPort.class.getName() + " c where c.frs = ?",frs);
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return list;
	}

	@Override
	public DepartFromPort queryByFlightNoAndDate(String flightNumber,String flightDate, String versionFlag) {
		return dbDAO.queryByCustom(DepartFromPort.class.getSimpleName(), deleteFlag, versionFlag, " and s.flt =?3 and s.flightDate =?4 ", new Object[]{flightNumber,flightDate});
	}

	@Override
	public Long getCountByStopover(String org, String des, String flightDate,
			String versionFlag) throws ServiceException {
		List<DepartFromPort> list = dbDAO.queryByList(DepartFromPort.class.getSimpleName(), deleteFlag, versionFlag, "and s.org =?3 and (s.des=?4 or s.vi1 =?5 or s.vi2 =?6 or s.vi3 =?7 or s.vi4 =?8 or s.vi5 =?9 or s.vi6 =?10) and s.flightDate =?11 and s.vi1 !=''", new Object[]{org,des,des,des,des,des,des,flightDate}, orderBy, null);
		if(list != null && !list.isEmpty()){
			Long i = new Long(list.size());
			for(DepartFromPort dept : list){
				if(des.equals(dept.getVi1())){
					i --;
				}
			}
			return i;
		}
		return dbDAO.getTotalCount(DepartFromPort.class.getSimpleName(), deleteFlag, versionFlag, "and s.org =?3 and (s.des=?4 or s.vi1 =?5 or s.vi2 =?6 or s.vi3 =?7 or s.vi4 =?8 or s.vi5 =?9 or s.vi6 =?10) and s.flightDate =?11 and s.vi1 !=''", new Object[]{org,des,des,des,des,des,des,flightDate});
	}
	
	@Override
	public List<DepartFromPort> queryByConditions(String keyword, String flightDate, String flightTime) throws ServiceException{
		String tableName = this.getTableNameFromEntity(DepartFromPort.class);
		String sql = "select t from "+tableName+" t where 1=1";
		if(GeneralUtil.isNull(flightDate)){
			sql += " and t.flightDate="+DateUtil.parseDateToString(new Date(), "yyyy-MM-dd");
		}else{
			sql += " and t.flightDate='"+flightDate+"'";
		}
		if(GeneralUtil.isNotNull(flightTime)){
			if("1".equals(flightTime)){
				
			}else{
				String[] tempStr = flightTime.split("-"); 
				String start = tempStr[0];
				String end = tempStr[1];
				
				String startTime =flightDate+" "+start+":00";
				String endTime = flightDate+" "+end+":00";
				
				sql += " and t.std between '"+startTime+"' and '"+endTime+"'";
			}
		}
		if(GeneralUtil.isNotNull(keyword)){
			 List<AirportInfo> apList = airportInfoService.queryByName(keyword);
			 if(apList != null && !apList.isEmpty()){
				 String tempAcw="";
				 for(AirportInfo ap : apList){
					 tempAcw += "'"+ap.getThreeCode()+"',";
				 }
				 sql += " and (t.des in ("+tempAcw.substring(0,tempAcw.length()-1)+")"+" or t.flt ='"+keyword+"' or t.des='"+keyword+"' or t.acw='"+keyword+"')";
			 }else{
				 
				 sql += " and (t.flt ='"+keyword+"' or t.des='"+keyword+"' or t.acw='"+keyword+"')";
			 }
		}
		sql += " order By t.std asc";
		List<DepartFromPort> list = dbDAO.executeJPQLForQueryEntity(sql);
		return list;
	}

}