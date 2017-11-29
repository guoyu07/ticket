package com.ticket.serviceImpl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.constants.JQueryUploadConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BusinessInfo;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.Pagination;
import com.ticket.service.IAirportBusinessTypeService;
import com.ticket.service.IBindYouZanService;
import com.ticket.service.IBusinessInfoService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.PaginationContext;

/**
 * 商家信息业务接口实现类
 * @ClassName: IBusinessInfoService   
 * @Description: 提供商家信息操作的增删改查   
 * @author HiSay  
 * @date 2015-11-16 15:35:54
 *
 */
public class BusinessInfoServiceImpl extends BaseServiceImpl<BusinessInfo, String> implements IBusinessInfoService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(BusinessInfoServiceImpl.class);
	@Resource
	private IAirportBusinessTypeService airportBusinessTypeService = null;
	@Resource
	private IBindYouZanService bindYouZanService;
	
	@Override
	public boolean merge(String id, String businessType_id,String name,String picture,
			String introduce,String mainSale,String businessHours,String activityForecast,
			String phone,String address,Double longitude,Double latitude,Integer orderValue,
			String lc,String wz,String fl,Integer score,Double averagePrice,String floorNumber,String djk,String versionFlag) throws ServiceException {
		BusinessInfo businessInfo = dbDAO.queryById(this.getTableNameFromEntity(BusinessInfo.class), id);
		businessInfo.setBusinessType_id(DecoderUtil.UtfDecoder(businessType_id));
		businessInfo.setName(DecoderUtil.UtfDecoder(name));
		businessInfo.setIntroduce(DecoderUtil.UtfDecoder(introduce));
		businessInfo.setMainSale(DecoderUtil.UtfDecoder(mainSale));
		businessInfo.setBusinessHours(DecoderUtil.UtfDecoder(businessHours));
		businessInfo.setActivityForecast(DecoderUtil.UtfDecoder(activityForecast));
		businessInfo.setPhone(DecoderUtil.UtfDecoder(phone));
		businessInfo.setAddress(DecoderUtil.UtfDecoder(address));
		businessInfo.setLongitude(longitude);
		businessInfo.setLatitude(latitude);
		businessInfo.setLc(lc);
		businessInfo.setWz(wz);
		businessInfo.setFl(fl);
		businessInfo.setScore(score);
		businessInfo.setAveragePrice(averagePrice);
		businessInfo.setFloorNumber(floorNumber);
		businessInfo.setDjk(djk);
		CommonEntity status = businessInfo.getStatus();
		status.setOrderValue(orderValue);
		status.setVersionFlag(versionFlag);
		businessInfo.setStatus(status);
		dbDAO.merge(businessInfo);
		if(GeneralUtil.isNotNull(picture)){
			this.addAnnex(businessInfo, businessInfo.getId(), picture, JQueryUploadConstants.PICTURE_TYPE_DEFAULT, versionFlag);
		}
		return true;
	}

	@Override
	public boolean persist(String businessType_id,String name,String picture,String introduce,
			String mainSale,String businessHours,String activityForecast,String phone,
			String address,Double longitude,Double latitude,Integer orderValue,
			String lc,String wz,String fl,Integer score,Double averagePrice,String floorNumber,String djk, String versionFlag) throws ServiceException {
		BusinessInfo businessInfo = new BusinessInfo();
		businessInfo.setBusinessType_id(DecoderUtil.UtfDecoder(businessType_id));
		businessInfo.setName(DecoderUtil.UtfDecoder(name));
		businessInfo.setIntroduce(DecoderUtil.UtfDecoder(introduce));
		businessInfo.setMainSale(DecoderUtil.UtfDecoder(mainSale));
		businessInfo.setBusinessHours(DecoderUtil.UtfDecoder(businessHours));
		businessInfo.setActivityForecast(DecoderUtil.UtfDecoder(activityForecast));
		businessInfo.setPhone(DecoderUtil.UtfDecoder(phone));
		businessInfo.setAddress(DecoderUtil.UtfDecoder(address));
		businessInfo.setLongitude(longitude);
		businessInfo.setLatitude(latitude);
		CommonEntity status = businessInfo.getStatus();
		status.setOrderValue(orderValue);
		status.setVersionFlag(versionFlag);
		businessInfo.setStatus(status);
		businessInfo.setLc(lc);
		businessInfo.setWz(wz);
		businessInfo.setFl(fl);
		businessInfo.setScore(score);
		businessInfo.setAveragePrice(averagePrice);
		businessInfo.setFloorNumber(floorNumber);
		businessInfo.setDjk(djk);
		dbDAO.persist(businessInfo);
		if(GeneralUtil.isNotNull(picture)){
			this.addAnnex(businessInfo, businessInfo.getId(), picture, JQueryUploadConstants.PICTURE_TYPE_DEFAULT, versionFlag);
		}
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		BusinessInfo businessInfo = dbDAO.queryById(this.getTableNameFromEntity(BusinessInfo.class), id);
		dbDAO.remove(businessInfo);
		return true;
	}

	@Override
	public List<BusinessInfo> queryListByTypeId(String businessType_id, String versionFlag) throws ServiceException {
		List<BusinessInfo> list = dbDAO.queryByList(BusinessInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.businessType_id =?3", new Object[]{businessType_id}, orderValueOrderBy, null);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException {
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(BusinessInfo.class.getSimpleName(), idsValue, null);
		return true;
	}

	@Override
	public List<BusinessInfo> queryByConditions(String businessType, String address,String versionFlag) throws ServiceException {
		List<BusinessInfo> list = dbDAO.queryByList(BusinessInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.businessType_id =?3 and s.address like ?4", new Object[]{businessType,"%"+address+"%"}, orderValueOrderBy, null);
		if(list != null && list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<BusinessInfo> queryByTypeAndKeyword(String businessType, String keyword ,String versionFlag) throws ServiceException {
		keyword = DecoderUtil.UtfDecoder(keyword);
		List<BusinessInfo> list = dbDAO.queryByList(BusinessInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.businessType_id =?3 and s.name like ?4", new Object[]{businessType,"%"+keyword+"%"}, orderValueOrderBy, null);
		if(list != null && list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<BusinessInfo> queryListByType(String businessType, String versionFlag) throws ServiceException {
		List<BusinessInfo> list = dbDAO.queryByList(BusinessInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.businessType_id =?3", new Object[]{businessType}, orderValueOrderBy, null);
		if(list != null && list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<BusinessInfo> queryListByCommend(Integer size,String versionFlag) throws ServiceException {
		List<BusinessInfo> list = dbDAO.queryByList(BusinessInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.status.commend=1", null, orderValueOrderBy, size);
		if(list != null && list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<BusinessInfo> queryByPc(String businessInfoTypeId,
			String keyword, String lc, String wz, String fl)
			throws ServiceException {
		try {
			StringBuffer sb = new StringBuffer();
			List<Object[]> objects = new ArrayList<Object[]>();
			sb.append("select b from BusinessInfo b where b.status.deleteFlag=0 ");
			if(GeneralUtil.isNotNull(businessInfoTypeId)){
				sb.append("and b.businessType_id in(");
				sb.append(airportBusinessTypeService.parseClassIds(businessInfoTypeId));
				sb.append(") ");
			}
			if(GeneralUtil.isNotNull(keyword)){
				sb.append("and b.name like ?2 ");
				objects.add(new Object[]{2,"%"+keyword+"%"});
			}
			if(GeneralUtil.isNotNull(lc)){
				sb.append("and b.lc=?3 ");
				objects.add(new Object[]{3,lc});
			}
			if(GeneralUtil.isNotNull(wz)){
				sb.append("and b.wz=?4 ");
				objects.add(new Object[]{4,wz});
			}
			if(GeneralUtil.isNotNull(fl)){
				sb.append("and b.fl=?5 ");
				objects.add(new Object[]{5,fl});
			}
			return dbDAO.executeJPQLForQuery(sb.toString(), BusinessInfo.class, objects.toArray());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Pagination queryEntityByKeyword(String keyword, int pageSize,
			String versionFlag) throws ServiceException {
		keyword = DecoderUtil.UtfDecoder(keyword);
		return dbDAO.queryByPageModule(BusinessInfo.class.getSimpleName(), deleteFlag, versionFlag, " and s.name like ?3", new Object[]{"%"+keyword+"%"}, orderBy, PaginationContext.getOffset(), pageSize);
	}

	@Override
	public Pagination queryEntityByConditions(String businessType_id,
			String lc, String wz, int pageSize, String versionFlag)
			throws ServiceException {
		if(GeneralUtil.isNotNull(businessType_id)){
			if(GeneralUtil.isNotNull(lc)){
				if(GeneralUtil.isNotNull(wz)){
					return dbDAO.queryByPageModule(BusinessInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.businessType_id =?3 and s.lc =?4 and s.wz =?5", new Object[]{businessType_id,lc,wz}, orderBy, PaginationContext.getOffset(), pageSize);
				}else{
					return dbDAO.queryByPageModule(BusinessInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.businessType_id =?3 and s.lc =?4", new Object[]{businessType_id,lc}, orderBy, PaginationContext.getOffset(), pageSize);
				}
			}else{
				if(GeneralUtil.isNotNull(wz)){
					return dbDAO.queryByPageModule(BusinessInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.businessType_id =?3 and s.wz =?4", new Object[]{businessType_id,wz}, orderBy, PaginationContext.getOffset(), pageSize);
				}
				else{
					return dbDAO.queryByPageModule(BusinessInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.businessType_id =?3", new Object[]{businessType_id}, orderBy, PaginationContext.getOffset(), pageSize);
					
				}
			}
		}else{
			if(GeneralUtil.isNotNull(lc)){
				if(GeneralUtil.isNotNull(wz)){
					return dbDAO.queryByPageModule(BusinessInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.lc =?3 and s.wz =?4", new Object[]{lc,wz}, orderBy, PaginationContext.getOffset(), pageSize);
					
				}else{
					return dbDAO.queryByPageModule(BusinessInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.lc =?3", new Object[]{lc}, orderBy, PaginationContext.getOffset(), pageSize);
					
				}
			}else{
				if(GeneralUtil.isNotNull(wz)){
					return dbDAO.queryByPageModule(BusinessInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.wz =?3", new Object[]{wz}, orderBy, PaginationContext.getOffset(), pageSize);
					
				}
				else{
					return dbDAO.queryByPageModule(BusinessInfo.class.getSimpleName(), deleteFlag, versionFlag, null, null, orderBy, PaginationContext.getOffset(), pageSize);
					
				}
			}
		}
	}

	@Override
	public List<BusinessInfo> getBusinessInfoMainSaleLike(String keyword)
			throws ServiceException {
		List<BusinessInfo> infos = dbDAO.executeJPQLForQueryEntity("select c from " + BusinessInfo.class.getName() + " c where c.mainSale like ?", "%" + keyword + "%");
		return infos;
	}

	@Override
	public List<BusinessInfo> queryByWz(String djk) throws ServiceException {
		return dbDAO.executeJPQLForQuery("select c from " + BusinessInfo.class.getName() + " c where c.djk = ? or c.wz = ? or c.lc = ?", BusinessInfo.class, djk,djk,djk);
	}

	@Override
	public void aotuSales() {
		String sql = "select c.businessInfo.id,sum(c.goodsInfo.sold_num) from BindYouZan c group by c.businessInfo.id";
		List<Object[]> list = dbDAO.executeJPQLForQuery(sql,Object[].class);
		if(list != null && list.size() > 0){
			for(Object[] objects:list){
				BusinessInfo businessInfo = get(BusinessInfo.class, (String)objects[0]);
				if(businessInfo != null){
					int a = Integer.parseInt((String)objects[1]);
					businessInfo.setYouZanSales(a);
					dbDAO.merge(businessInfo);
				}
			}
		}
	}

	@Override
	public List<BusinessInfo> queryByOrder(String order) {
		return dbDAO.executeJPQLForQuery("select c from " + BusinessInfo.class.getName() + " c order by c.youZanSales " + order, BusinessInfo.class);
	}

	@Override
	public List<BusinessInfo> queryByTypeAndWzAndOrder(String type_id,
			String wz, String order) {
		String sql = "select c from " + BusinessInfo.class.getName() + " c where 1=1 ";
		if(GeneralUtil.isNotNull(type_id)){
			sql += " and c.businessType_id = ?";
		}
		if(GeneralUtil.isNotNull(wz)){
			sql += " and (c.djk = ? or c.wz = ? or c.lc = ?) ";
		}
		sql += " and c.status.deleteFlag = 0";
		if(GeneralUtil.isNotNull(order)){
			sql += " order by c.youZanSales " +order;
		}
		if(GeneralUtil.isNotNull(type_id) && GeneralUtil.isNotNull(wz)){
			return dbDAO.executeJPQLForQuery(sql, BusinessInfo.class, type_id, wz, wz, wz);
		}
		if(GeneralUtil.isNotNull(type_id) && GeneralUtil.isNull(wz)){
			return dbDAO.executeJPQLForQuery(sql, BusinessInfo.class, type_id);
		}
		if(GeneralUtil.isNull(type_id) && GeneralUtil.isNotNull(wz)){
			return dbDAO.executeJPQLForQuery(sql, BusinessInfo.class, wz, wz, wz);
		}
		return dbDAO.executeJPQLForQuery(sql, BusinessInfo.class);
	}

}