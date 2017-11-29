package com.ticket.serviceImpl;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.constants.JQueryUploadConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.FCSelfCheckinPosition;
import com.ticket.pojo.FlightCompany;
import com.ticket.service.IFCSelfCheckinPositionService;
import com.ticket.service.IFlightCompanyService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * 航空公司信息业务接口实现类
 * @ClassName: IFlightCompanyService   
 * @Description: 提供航空公司信息操作的增删改查   
 * @author HiSay  
 * @date 2015-11-03 13:39:40
 *
 */
public class FlightCompanyServiceImpl extends BaseServiceImpl<FlightCompany, String> implements IFlightCompanyService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(FlightCompanyServiceImpl.class);
	@Resource
	private IFCSelfCheckinPositionService fcCheckinPositionService = null;
	@Override
	public boolean merge(String id, String name,String twoCode,String threeCode,String phone,String theOfficialWebsite,String customerCounter,String teamCounter,String firstClassCounter,String emergencyCounter,Integer orderValue,String logo,Double longitude,Double latitude,String[] selfLongitude,String[] selfLatitude,String[] positionName,String[] floorNumber, String versionFlag) throws ServiceException {
		FlightCompany flightCompany = dbDAO.queryById(this.getTableNameFromEntity(FlightCompany.class), id);
		flightCompany.setName(DecoderUtil.UtfDecoder(name));
		flightCompany.setTwoCode(DecoderUtil.UtfDecoder(twoCode));
		flightCompany.setThreeCode(DecoderUtil.UtfDecoder(threeCode));
		flightCompany.setPhone(DecoderUtil.UtfDecoder(phone));
		flightCompany.setTheOfficialWebsite(DecoderUtil.UtfDecoder(theOfficialWebsite));
		flightCompany.setCustomerCounter(DecoderUtil.UtfDecoder(customerCounter));
		flightCompany.setTeamCounter(DecoderUtil.UtfDecoder(teamCounter));
		flightCompany.setFirstClassCounter(DecoderUtil.UtfDecoder(firstClassCounter));
		flightCompany.setEmergencyCounter(DecoderUtil.UtfDecoder(emergencyCounter));
		flightCompany.setLongitude(longitude);
		flightCompany.setLatitude(latitude);
		CommonEntity status = flightCompany.getStatus();
		status.setOrderValue(orderValue);
		status.setVersionFlag(versionFlag);
		flightCompany.setStatus(status);
		dbDAO.merge(flightCompany);
		fcCheckinPositionService.deleteByCompanyId(id,versionFlag);
		if(GeneralUtil.isNotNull(positionName)){
			for(int i = 0;i<selfLongitude.length;i++){
				FCSelfCheckinPosition fcSelfCheckinPosition = new FCSelfCheckinPosition();
				fcSelfCheckinPosition.setFlightCompany_id(flightCompany.getId());
				fcSelfCheckinPosition.setName(positionName[i]);
				fcSelfCheckinPosition.setLongitude(Double.parseDouble(selfLongitude[i]));
				fcSelfCheckinPosition.setLatitude(Double.parseDouble(selfLatitude[i]));
				fcSelfCheckinPosition.setFloorNumber(floorNumber[i]);
				fcCheckinPositionService.persist(fcSelfCheckinPosition);
			}
		}
		if(GeneralUtil.isNotNull(logo)){
			this.addAnnex(flightCompany, flightCompany.getId(), logo, JQueryUploadConstants.PICTURE_TYPE_DEFAULT, versionFlag);
		}
		return true;
	}

	@Override
	public boolean persist(String name,String twoCode,String threeCode,String phone,String theOfficialWebsite,String customerCounter,String teamCounter,String firstClassCounter,String emergencyCounter,Integer orderValue,String logo,Double longitude,Double latitude,String[] selfLongitude,String[] selfLatitude,String[] positionName,String[] floorNumber, String versionFlag) throws ServiceException {
		FlightCompany flightCompany = new FlightCompany();
		flightCompany.setName(DecoderUtil.UtfDecoder(name));
		flightCompany.setTwoCode(DecoderUtil.UtfDecoder(twoCode));
		flightCompany.setThreeCode(DecoderUtil.UtfDecoder(threeCode));
		flightCompany.setPhone(DecoderUtil.UtfDecoder(phone));
		flightCompany.setTheOfficialWebsite(DecoderUtil.UtfDecoder(theOfficialWebsite));
		flightCompany.setCustomerCounter(DecoderUtil.UtfDecoder(customerCounter));
		flightCompany.setTeamCounter(DecoderUtil.UtfDecoder(teamCounter));
		flightCompany.setFirstClassCounter(DecoderUtil.UtfDecoder(firstClassCounter));
		flightCompany.setEmergencyCounter(DecoderUtil.UtfDecoder(emergencyCounter));
		flightCompany.setLongitude(longitude);
		flightCompany.setLatitude(latitude);
		CommonEntity status = flightCompany.getStatus();
		status.setOrderValue(orderValue);
		status.setVersionFlag(versionFlag);
		flightCompany.setStatus(status);
		dbDAO.persist(flightCompany);
		if(GeneralUtil.isNotNull(positionName)){
			for(int i = 0;i<selfLongitude.length;i++){
				FCSelfCheckinPosition fcSelfCheckinPosition = new FCSelfCheckinPosition();
				fcSelfCheckinPosition.setFlightCompany_id(flightCompany.getId());
				fcSelfCheckinPosition.setName(positionName[i]);
				fcSelfCheckinPosition.setLongitude(Double.parseDouble(selfLongitude[i]));
				fcSelfCheckinPosition.setLatitude(Double.parseDouble(selfLatitude[i]));
				fcSelfCheckinPosition.setFloorNumber(floorNumber[i]);
				fcCheckinPositionService.persist(fcSelfCheckinPosition);
			}
		}
		if(GeneralUtil.isNotNull(logo)){
			this.addAnnex(flightCompany, flightCompany.getId(), logo, JQueryUploadConstants.PICTURE_TYPE_DEFAULT, versionFlag);
		}
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		FlightCompany flightCompany = dbDAO.queryById(this.getTableNameFromEntity(FlightCompany.class), id);
		dbDAO.remove(flightCompany);
		return true;
	}

	@Override
	public boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException { 
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(FlightCompany.class.getSimpleName(), idsValue, null);
		return true;
	}

	@Override
	public List<FlightCompany> queryList(Integer size,String versionFlag) throws ServiceException {
		return dbDAO.queryByList(FlightCompany.class.getSimpleName(), deleteFlag, versionFlag, null, null, orderValueOrderBy, size);
	}

	@Override
	public FlightCompany queryEntityByTwoCode(String twoCode, String versionFlag) {
		FlightCompany flightCompany = dbDAO.queryByCustom(FlightCompany.class.getSimpleName(), deleteFlag, versionFlag, "and s.twoCode =?3", new Object[]{twoCode});
		if(flightCompany != null){
			return flightCompany;
		}
		return null;
	}

	@Override
	public List<FlightCompany> queryByConditions(String keyword,String orderFlag, String versionFlag) throws ServiceException {
		keyword = DecoderUtil.UtfDecoder(keyword);
		if(GeneralUtil.isNotNull(orderFlag)){
			if("twoCode".equals(orderFlag)){
				orderValueOrderBy = " s.twoCode desc";
			}
			if("pinyin".equals(orderFlag)){
				orderValueOrderBy = " s.name asc";
			}
		}
		List<FlightCompany> list = dbDAO.queryByList(FlightCompany.class.getSimpleName(), deleteFlag, versionFlag, "and s.name like ?3 or s.customerCounter like ?4 or s.emergencyCounter like ?5", new Object[]{"%"+keyword+"%","%"+keyword+"%","%"+keyword+"%"}, orderValueOrderBy,6);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}
	
	@Override
	public List<FlightCompany> queryByConditions2(String keyword,String orderFlag, String versionFlag) throws ServiceException {
		keyword = DecoderUtil.UtfDecoder(keyword);
		if(GeneralUtil.isNotNull(orderFlag)){
			if("twoCode".equals(orderFlag)){
				orderValueOrderBy = " s.twoCode desc";
			}
			if("pinyin".equals(orderFlag)){
				orderValueOrderBy = " s.name asc";
			}
		}
		List<FlightCompany> list = dbDAO.queryByList(FlightCompany.class.getSimpleName(), deleteFlag, versionFlag, "and s.name like ?3 or s.customerCounter like ?4 or s.emergencyCounter like ?5", new Object[]{"%"+keyword+"%","%"+keyword+"%","%"+keyword+"%"}, orderValueOrderBy,null);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public FlightCompany queryByThreeCode(String threeCode, String versionFlag)
			throws ServiceException {
		return dbDAO.queryByCustom(FlightCompany.class.getSimpleName(), deleteFlag, versionFlag, " and s.threeCode =?3", new Object[]{threeCode});
	}

	@Override
	public String changeCode(String threeCode, String versionFlag)
			throws ServiceException {
		FlightCompany fc = dbDAO.queryByCustom(FlightCompany.class.getSimpleName(), deleteFlag, versionFlag, " and s.threeCode =?3", new Object[]{threeCode});
		if(fc != null){
			if(GeneralUtil.isNotNull(fc.getTwoCode())){
				return fc.getTwoCode();
			}
		}
		return "";
	}

	@Override
	public String trangeFlightNoByCompanyCode(String flightNumber) throws ServiceException {
		String threeCode = flightNumber.substring(0, 3);
		String number = flightNumber.substring(3);
		String twoCode = this.changeCode(threeCode,versionFlag);
		if(!"".equals(twoCode)){
			return twoCode+number;
		}else{
			return "";
		}
	}

	@Override
	public String changeCodeTwoToThree(String twoCode, String versionFlag)
			throws ServiceException {
		FlightCompany fc = dbDAO.queryByCustom(FlightCompany.class.getSimpleName(), deleteFlag, versionFlag, " and s.twoCode =?3", new Object[]{twoCode});
		if(fc != null){
			if(GeneralUtil.isNotNull(fc.getThreeCode())){
				return fc.getThreeCode();
			}
		}
		return null;
	}

	@Override
	public boolean isExistByName(String name) throws ServiceException {
		FlightCompany fc = dbDAO.queryByCustom(FlightCompany.class.getSimpleName(), deleteFlag, versionFlag, "and s.name =?3", new Object[]{name.trim()});
		if(fc != null){
			return  true;
		}
		return false;
	}

	@Override
	public String transferFlightNumber(String flightNumber) {
		if(GeneralUtil.isNotNull(flightNumber)){
			String regStr = flightNumber.substring(0, 3);
			String str = "^[A-Za-z]{3}$";
			Pattern pattern = Pattern.compile(str);  
			Matcher matcher=pattern.matcher(regStr);  
			boolean flag = matcher.matches();
			if(flag){
				FlightCompany fc = dbDAO.queryByCustom(FlightCompany.class.getSimpleName(), deleteFlag, versionFlag, "and s.threeCode =?3", new Object[]{flightNumber.substring(0, 3)});
				if(fc != null){
					return fc.getTwoCode()+flightNumber.substring(3);
				}
			}else{
				FlightCompany fc = dbDAO.queryByCustom(FlightCompany.class.getSimpleName(), deleteFlag, versionFlag, "and s.twoCode =?3", new Object[]{flightNumber.substring(0, 2)});
				if(fc != null){
					return fc.getThreeCode()+flightNumber.substring(2);
				}
			}
		}
		return "";
	}

	@Override
	public FlightCompany queryCompanyByFlt(String flightNumber)
			throws ServiceException {
		if(GeneralUtil.isNotNull(flightNumber)){
			String regStr = flightNumber.substring(0, 3);
			String str = "^[A-Za-z]{3}$";
			Pattern pattern = Pattern.compile(str);  
			Matcher matcher=pattern.matcher(regStr);  
			boolean flag = matcher.matches();
			if(flag){
				FlightCompany fc = dbDAO.queryByCustom(FlightCompany.class.getSimpleName(), deleteFlag, versionFlag, "and s.threeCode =?3", new Object[]{flightNumber.substring(0, 3)});
				if(fc != null){
					return fc;
				}
			}else{
				String twoReg = "^[A-Za-z]{2}$";
				Matcher matcherOne=pattern.matcher(twoReg); 
				boolean twoFlag = matcherOne.matches();
				if(twoFlag){
					FlightCompany fc = dbDAO.queryByCustom(FlightCompany.class.getSimpleName(), deleteFlag, versionFlag, "and s.twoCode =?3", new Object[]{flightNumber.substring(0, 2)});
					if(fc != null){
						return fc;
					}
				}
			}
		}
		return null;
	}

}