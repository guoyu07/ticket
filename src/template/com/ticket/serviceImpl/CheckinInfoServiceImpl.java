package com.ticket.serviceImpl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CheckinInfo;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.DepartFromPort;
import com.ticket.pojo.Member;
import com.ticket.service.ICheckinInfoService;
import com.ticket.service.IDepartFromPortService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * 值机信息表业务接口实现类
 * @ClassName: ICheckinInfoService   
 * @Description: 提供值机信息表操作的增删改查   
 * @author HiSay  
 * @date 2016-02-24 16:09:34
 *
 */
public class CheckinInfoServiceImpl extends BaseServiceImpl<CheckinInfo, String> implements ICheckinInfoService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(CheckinInfoServiceImpl.class);
	@Resource
	IDepartFromPortService departFromPortService;
	
	@Override
	public CheckinInfo merge(String id, Member member,String flightNumber,String flightDate,String mobile,String ticketNo,String seatNo,String couponId, String versionFlag) throws ServiceException {
		CheckinInfo checkinInfo = dbDAO.queryById(this.getTableNameFromEntity(CheckinInfo.class), id);
		checkinInfo.setMember(member);
		checkinInfo.setFlightNumber(DecoderUtil.UtfDecoder(flightNumber));
		checkinInfo.setFlightDate(flightDate);
		checkinInfo.setMobile(DecoderUtil.UtfDecoder(mobile));
		checkinInfo.setTicketNo(DecoderUtil.UtfDecoder(ticketNo));
		checkinInfo.setSeatNo(DecoderUtil.UtfDecoder(seatNo));
		checkinInfo.setCouponId(DecoderUtil.UtfDecoder(couponId));
		CommonEntity status = checkinInfo.getStatus();
		status.setVersionFlag(versionFlag);
		checkinInfo.setStatus(status);
		dbDAO.merge(checkinInfo);
		return checkinInfo;
	}

	@Override
	public CheckinInfo persist(Member member,String flightNumber,String flightDate,String mobile,String ticketNo,String seatNo,String couponId, String sendMobile) throws ServiceException {
		
		DepartFromPort departFromPort = departFromPortService.queryByFlightNoAndDate(flightNumber, flightDate, versionFlag);
		if(departFromPort == null){
			
			return null;
		}
		
		CheckinInfo checkinInfo = new CheckinInfo();
		checkinInfo.setMember(member);
		checkinInfo.setDeptAirportCode(departFromPort.getOrg());
		checkinInfo.setDestAirportCode(departFromPort.getDes());
		checkinInfo.setFlightNumber(DecoderUtil.UtfDecoder(flightNumber));
		checkinInfo.setFlightDate(flightDate);
		checkinInfo.setMobile(DecoderUtil.UtfDecoder(mobile));
		checkinInfo.setTicketNo(DecoderUtil.UtfDecoder(ticketNo));
		checkinInfo.setSeatNo(DecoderUtil.UtfDecoder(seatNo));
		checkinInfo.setCouponId(DecoderUtil.UtfDecoder(couponId));
		checkinInfo.setSendMobile(sendMobile);
		dbDAO.persist(checkinInfo);
		return checkinInfo;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		CheckinInfo checkinInfo = dbDAO.queryById(this.getTableNameFromEntity(CheckinInfo.class), id);
		dbDAO.remove(checkinInfo);
		return true;
	}

	@Override
	public List<CheckinInfo> query(String flightNo, String flightDate) {
		
		if(GeneralUtil.isNull(flightNo) || flightDate == null){
			
			return new ArrayList<CheckinInfo>();
		}
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		Table table = CheckinInfo.class.getAnnotation(Table.class);
		String tableName = table.name();
		List<CheckinInfo> list = dbDAO.executeSQLForQuery("select t.* from " + tableName + " t where flightNumber=? and flightDate=? and (member_id=? or memberQQ_id=? or memberWeiBo_id=? or memberWeixin_id=?)"
					, CheckinInfo.class, flightNo, flightDate, member.getId(), member.getId(), member.getId(), member.getId());
		return list;
	}

	@Override
	public CheckinInfo queryByMember(Member member, String flightNumber,
			String flightDate) throws ServiceException {
		return dbDAO.queryByCustom(CheckinInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.member =?3 and s.flightNumber =?4 and s.flightDate =?5", new Object[]{member,flightNumber,flightDate});
	}

	@Override
	public List<CheckinInfo> queryByMemberId(Member member)
			throws ServiceException {
		return dbDAO.queryByList(CheckinInfo.class.getSimpleName(),deleteFlag , versionFlag, "and (s.member.id =?3 or s.memberQQ.id=?4 or s.memberWeiBo.id=?5 or s.memberWeixin.id=?6)", 
				new Object[]{member.getId(), member.getId(), member.getId(), member.getId()}, orderBy, null);
	}

	@Override
	public CheckinInfo queryByTicketNotShare(String ticketNo)
			throws ServiceException {
		return dbDAO.queryByCustom(CheckinInfo.class.getSimpleName(), deleteFlag, versionFlag, " and s.ticketNo =?3 and s.byShare is null", new Object[]{ticketNo});
	}

}