package com.ticket.serviceImpl;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportInfo;
import com.ticket.pojo.ArrivalAtPort;
import com.ticket.pojo.DepartFromPort;
import com.ticket.service.IAirportInfoService;
import com.ticket.service.IArrivalAtPortService;
import com.ticket.util.DateUtil;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * 进港航班业务接口实现类
 * @ClassName: IArrivalAtPortService   
 * @Description: 提供进港航班操作的增删改查   
 * @author HiSay  
 * @date 2015-12-01 10:07:57
 *
 */
public class ArrivalAtPortServiceImpl extends BaseServiceImpl<ArrivalAtPort, String> implements IArrivalAtPortService {

	private static final Log log = LogFactory.getLog(ArrivalAtPortServiceImpl.class);
	@Resource IAirportInfoService airportInfoService = null;
	@Override
	public boolean merge(String id, String flt,String acw,String frs,String doi,String pft,String org,Date std,Date abt,Date eta,Date sta,Date tdt,String btt,String cedn,boolean isShare, String des) throws ServiceException {
		ArrivalAtPort arrivalAtPort = dbDAO.queryById(this.getTableNameFromEntity(ArrivalAtPort.class), id);
		arrivalAtPort.setFlt(DecoderUtil.UtfDecoder(flt));
		arrivalAtPort.setAcw(DecoderUtil.UtfDecoder(acw));
		arrivalAtPort.setFrs(DecoderUtil.UtfDecoder(frs));
		arrivalAtPort.setDoi(DecoderUtil.UtfDecoder(doi));
		arrivalAtPort.setPft(DecoderUtil.UtfDecoder(pft));
		arrivalAtPort.setOrg(DecoderUtil.UtfDecoder(org));
		arrivalAtPort.setDes(DecoderUtil.UtfDecoder(des));
		arrivalAtPort.setEta(eta);
		arrivalAtPort.setSta(sta);
		arrivalAtPort.setTdt(tdt);
		arrivalAtPort.setCedn(cedn);
		arrivalAtPort.setBtt(DecoderUtil.UtfDecoder(btt));
		dbDAO.merge(arrivalAtPort);
		return true;
	}

	@Override
	public boolean persist(String flt,String acw,String frs,String doi,String pft,String org,Date std,Date abt,Date eta,Date sta,Date tdt,String btt,String cedn,boolean isShare, String des) throws ServiceException {
		ArrivalAtPort arrivalAtPort = new ArrivalAtPort();
		arrivalAtPort.setFlt(DecoderUtil.UtfDecoder(flt));
		arrivalAtPort.setAcw(DecoderUtil.UtfDecoder(acw));
		arrivalAtPort.setFrs(DecoderUtil.UtfDecoder(frs));
		arrivalAtPort.setDoi(DecoderUtil.UtfDecoder(doi));
		arrivalAtPort.setPft(DecoderUtil.UtfDecoder(pft));
		arrivalAtPort.setOrg(DecoderUtil.UtfDecoder(org));
		arrivalAtPort.setEta(eta);
		arrivalAtPort.setSta(sta);
		arrivalAtPort.setTdt(tdt);
		arrivalAtPort.setDes(des);
		arrivalAtPort.setCedn(cedn);
		arrivalAtPort.setBtt(DecoderUtil.UtfDecoder(btt));
		dbDAO.persist(arrivalAtPort);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		ArrivalAtPort arrivalAtPort = dbDAO.queryById(this.getTableNameFromEntity(ArrivalAtPort.class), id);
		dbDAO.remove(arrivalAtPort);
		return true;
	}

	@Override
	public Integer importFromFile(String filePath) throws ServiceException {
		try {
			Workbook workBook = null;
			// 构建Workbook对象 只读Workbook对象
			// 直接从本地文件创建Workbook
			// 从输入流创建Workbook
			InputStream inputStream = new FileInputStream("D:\\flightData\\arrivalPort.xls");//new FileInputStream(getFileBasePath() + filePath);
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
					String pft = sheets.getCell(4, i).getContents().trim();//经停地
					String org = sheets.getCell(5, i).getContents().trim();//起始站
					String std = sheets.getCell(6, i).getContents().trim();//计划起飞时间
					String abt = sheets.getCell(7, i).getContents().trim();//实际起飞时间
					String eta = sheets.getCell(8, i).getContents().trim();//预计到达时间
					String sta = sheets.getCell(9, i).getContents().trim();//计划到达时间
					String tdt = sheets.getCell(10, i).getContents().trim();//实际到达时间
					String btt = sheets.getCell(11, i).getContents().trim();//行李提取转盘
					this.persist(flt, acw, frs, doi, pft, org, DateUtil.parseStringToDate(std, "MM/dd/yy HH:mm"),
							DateUtil.parseStringToDate(abt, "MM/dd/yy HH:mm"), DateUtil.parseStringToDate(eta, "MM/dd/yy HH:mm"), 
							DateUtil.parseStringToDate(sta, "MM/dd/yy HH:mm"), DateUtil.parseStringToDate(tdt, "MM/dd/yy HH:mm"), btt,null, false, versionFlag) ;
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
	public ArrivalAtPort queryByFlightNoAndDate(String flightNumber,String flightDate, String versionFlag) throws ServiceException {
		ArrivalAtPort arrivalAtPort =dbDAO.queryByCustom(ArrivalAtPort.class.getSimpleName(), deleteFlag, versionFlag, " and s.flt=?3 and s.flightDate=?4", new Object[]{flightNumber,flightDate});
		return arrivalAtPort;
	}
	
	@Override
	public List<ArrivalAtPort> queryListByCityAndDate(String org, String des, String flightDate, String versionFlag) throws ServiceException {
		List<ArrivalAtPort> list = dbDAO.queryByList(ArrivalAtPort.class.getSimpleName(), deleteFlag, versionFlag, " and (s.org = ?3 or s.pft =?4 or s.pft2 =?5 or s.pft3 =?6 or s.pft4 =?7 or s.pft5 =?8 or s.pft6 =?9) and s.des =?10 and s.flightDate =?11", new Object[]{org,org,org,org,org,org,org,des,flightDate}, "s.stp asc", null);
		List<ArrivalAtPort> tempList = new ArrayList<ArrivalAtPort>();
		List<ArrivalAtPort> frontList = new ArrayList<ArrivalAtPort>();
		for(ArrivalAtPort dept:list){
			if(GeneralUtil.isNotNull(dept.getSta())){
				Date tdes = dept.getSta();
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
		for(ArrivalAtPort dp : tempList){
			frontList.add(dp);
		}
		return frontList;
	}

	@Override
	public ArrivalAtPort queryArrivalFlightNoAndDate(String flightNumber,
			String flightDate, String versionFlag) throws ServiceException {
		return  dbDAO.queryByCustom(ArrivalAtPort.class.getSimpleName(), deleteFlag, versionFlag, " and s.flt =?3 and s.flightDate =?4 and s.type is null", new Object[]{flightNumber,flightDate});
	}

	@Override
	public ArrivalAtPort parseToBeTransferFlight(
			ArrivalAtPort arrivalAtPort, DepartFromPort departFromPort)
			throws ServiceException {
		try {
			if(arrivalAtPort != null && departFromPort != null) {
				if(arrivalAtPort.getFlt().equals(departFromPort.getFlt())) {
					ArrivalAtPort transfer = this.queryTrasferByFlightNoAndDate(arrivalAtPort.getFlt(), arrivalAtPort.getFlightDate(), versionFlag) ;
					if(transfer == null) {
						transfer = new ArrivalAtPort();
						transfer.setFlightDate(arrivalAtPort.getFlightDate());
						transfer.setFlt(arrivalAtPort.getFlt());
						transfer.setOrg(arrivalAtPort.getOrg());
						transfer.setDes(departFromPort.getDes()) ;
						transfer.setAcw(arrivalAtPort.getAcw());
						transfer.setTorg(arrivalAtPort.getStp());
						transfer.setSta(departFromPort.getTdes());
						transfer.setFrs(arrivalAtPort.getFrs());
						transfer.setStp(departFromPort.getStd());
						transfer.setAbp(departFromPort.getSta());
						transfer.setType("transfer");
						dbDAO.persist(transfer);
					}
					return transfer;
				}
			}
			return null;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrivalAtPort queryTrasferByFlightNoAndDate(String flightNumber, String flightDate, String versionFlag)
			throws ServiceException {
		ArrivalAtPort arrivalAtPort = dbDAO.queryByCustom(ArrivalAtPort.class.getSimpleName(), deleteFlag, versionFlag, "and s.flt =?3 and s.flightDate =?4 and s.type='transfer'", new Object[]{flightNumber,flightDate});
		return arrivalAtPort ;
	}

	@Override
	public List<ArrivalAtPort> queryByKeyword(String keyword, String versionFlag)
			throws ServiceException {
		keyword = DecoderUtil.UtfDecoder(keyword);
		List<ArrivalAtPort> list = dbDAO.queryByList(ArrivalAtPort.class.getSimpleName(), deleteFlag, versionFlag, "and s.flt like ?3", new Object[]{new Object[]{"%"+keyword+"%"}}, orderValueOrderBy, null);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public Long getCountByStopover(String org, String des, String flightDate,
			String versionFlag) {
		List<ArrivalAtPort> list = dbDAO.queryByList(ArrivalAtPort.class.getSimpleName(), deleteFlag, versionFlag, " and (s.org = ?3 or s.pft =?4 or s.pft2 =?5 or s.pft3 =?6 or s.pft4 =?7 or s.pft5 =?8 or s.pft6 =?9) and s.des =?10 and s.flightDate =?11 and s.pft !=''", new Object[]{org,org,org,org,org,org,org,des,flightDate}, orderBy, null);
		if(list != null && !list.isEmpty()){
			Long l = new Long(list.size());
			for(ArrivalAtPort obj : list){
				if(org.equals(obj.getPft())){
					l --;
				}
			}
			return l;
		}
		return dbDAO.getTotalCount(ArrivalAtPort.class.getSimpleName(), deleteFlag, versionFlag, " and (s.org = ?3 or s.pft =?4 or s.pft2 =?5 or s.pft3 =?6 or s.pft4 =?7 or s.pft5 =?8 or s.pft6 =?9) and s.des =?10 and s.flightDate =?11 and s.pft !=''", new Object[]{org,org,org,org,org,org,org,des,flightDate});
	}

	@Override
	public List<ArrivalAtPort> queryByConditions(String keyword,
			String flightDate, String flightTime) throws ServiceException{
		String tableName = this.getTableNameFromEntity(ArrivalAtPort.class);
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
				String endTime =flightDate+" "+end+":00";
				
				sql += " and t.stp between '"+startTime+"' and '"+endTime+"'";
			}
		}
		if(GeneralUtil.isNotNull(keyword)){
			 List<AirportInfo> apList = airportInfoService.queryByName(keyword);
			 if(apList != null && !apList.isEmpty()){
				 String tempAcw="";
				 for(AirportInfo ap : apList){
					 tempAcw +="'" + ap.getThreeCode()+"',";
				 }
				 sql += " and (t.org in ("+tempAcw.substring(0,tempAcw.length()-1)+")"+" or t.flt ='"+keyword+"' or t.org='"+keyword+"' or t.acw='"+keyword+"')";
			 }else{
				 
				 sql += " and (t.flt ='"+keyword+"' or t.org='"+keyword+"' or t.acw='"+keyword+"')";
			 }
		}
		sql += " order By t.torg asc";
		List<ArrivalAtPort> list = dbDAO.executeJPQLForQueryEntity(sql);
		
		return list;
	}

}