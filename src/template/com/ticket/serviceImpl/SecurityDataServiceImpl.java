package com.ticket.serviceImpl;

import java.util.List;

import com.ticket.pojo.SecurityData;
import com.ticket.service.ISecurityDataService;
import com.ticket.util.GeneralUtil;

/**
 * 安检数据服务实现类
 * @author zzf
 */
public class SecurityDataServiceImpl extends BaseServiceImpl<SecurityData, String> implements ISecurityDataService{

	/**
	 * 通过电子客票号获取安检实体数据
	 * @param ticketNo
	 * @return
	 */
	@Override
	public SecurityData getSecurityByTicketNo(String ticketNo) {
		
		String jpaQL = "select s from " + SecurityData.class.getName() + " s where s.ticketNo=?";
		SecurityData securityData = dbDAO.executeJPQLForQuerySingle(jpaQL, SecurityData.class,ticketNo);
		return securityData;
	}
	
	/**
	 * 通过身份证号获取安检数据
	 * @param IDCard
	 * @return
	 */
	public SecurityData getSecurityByIDCardAndTicketNo(String IDCard,String ticketNo){
		
		String jpaQL = "select s from " + SecurityData.class.getName() + " s where s.IDCard=? and s.ticketNo=?";
		SecurityData securityData = dbDAO.executeJPQLForQuerySingle(jpaQL, SecurityData.class, IDCard, ticketNo);
		return securityData;
	}

	/**
	 * 存储安检数据通过，实体对象, 如果数据存在的情况下就是更新数据
	 * @param securityData
	 * @return
	 */
	@Override
	public boolean saveSecurityByEntity(SecurityData securityData) {

		SecurityData securityDataByDataBase = getSecurityByIDCardAndTicketNo(securityData.getIDCard(), securityData.getTicketNo());
		if(GeneralUtil.isNotNull(securityDataByDataBase)){
			
			securityData.setId(securityDataByDataBase.getId());
			//更新数据通过一个实体
			dbDAO.merge(securityData);
		}else{
			
			dbDAO.persist(securityData);
		}
		return true;
	}

	@Override
	public List<SecurityData> findByIDCard(String IDCard) {
		
		List<SecurityData> list = dbDAO.executeJPQLForQueryEntity("select s from " + SecurityData.class.getName() + " s where s.IDCard=?", IDCard);
		return list;
	}

	@Override
	public SecurityData get(String idCard, String flightNo) {
		
		SecurityData list = dbDAO.executeJPQLForQuerySingle("select s from " + SecurityData.class.getName() + " s where s.IDCard=? and s.fltNo=?", 
				SecurityData.class , idCard, flightNo);
		return list;
	}
}
