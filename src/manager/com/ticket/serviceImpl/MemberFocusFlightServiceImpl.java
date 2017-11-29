package com.ticket.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Member;
import com.ticket.pojo.MemberFocusFlight;
import com.ticket.pojo.MemberQQ;
import com.ticket.pojo.MemberWeiBo;
import com.ticket.pojo.MemberWeixin;
import com.ticket.service.IMemberFocusFlightService;
import com.ticket.service.IMemberService;
import com.ticket.util.DecoderUtil;

/**
 * 会员关注航班业务接口实现类
 * 
 * @ClassName: IMemberFocusFlightService
 * @Description: 提供会员关注航班操作的增删改查
 * @author HiSay
 * @date 2015-12-04 16:03:42
 * 
 */
public class MemberFocusFlightServiceImpl extends BaseServiceImpl<MemberFocusFlight, String> implements IMemberFocusFlightService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory
			.getLog(MemberFocusFlightServiceImpl.class);
	
	@Resource private IMemberService memberService = null;

	@Override
	public boolean merge(String id, String member_id, String flightNumber,
			String flightDate, String memberRole, String flightState,
			Integer ifTakeLuggage, String takePerson, Integer personCount,
			Integer ifSet, String mobile, String seatNo, String ticketNo,
			String couponId, String stopover) throws ServiceException {
		
		MemberFocusFlight memberFocusFlight = dbDAO.queryById(this.getTableNameFromEntity(MemberFocusFlight.class), id);
		memberFocusFlight.setMember_id(DecoderUtil.UtfDecoder(member_id));
		memberFocusFlight.setFlightNumber(DecoderUtil.UtfDecoder(flightNumber));
		memberFocusFlight.setFlightDate(flightDate);
		memberFocusFlight.setMemberRole(DecoderUtil.UtfDecoder(memberRole));
		memberFocusFlight.setFlightState(DecoderUtil.UtfDecoder(flightState));
		memberFocusFlight.setIfTakeLuggage(ifTakeLuggage);
		memberFocusFlight.setTakePerson(DecoderUtil.UtfDecoder(takePerson));
		memberFocusFlight.setPersonCount(personCount);
		memberFocusFlight.setIfSet(ifSet);
		memberFocusFlight.setMobile(mobile);
		memberFocusFlight.setSeatNo(seatNo);
		memberFocusFlight.setTicketNo(ticketNo);
		memberFocusFlight.setCouponId(couponId);
		memberFocusFlight.setStopover(stopover);
		dbDAO.merge(memberFocusFlight);
		return true;
	}

	@Override
	public MemberFocusFlight persist(String member_id, String flightNumber,
			String flightDate, String memberRole, String flightState,
			Integer ifTakeLuggage, String takePerson, Integer personCount,
			Integer ifSet,  String mobile, String seatNo, String ticketNo,
			String couponId,String stopover) {
		
		MemberFocusFlight memberFocusFlight = queryByMember(flightNumber, flightDate, member_id, versionFlag);
		if(memberFocusFlight != null){
			
//			set(memberFocusFlight.getId(), ifTakeLuggage, personCount, takePerson, mobile, null);
			return memberFocusFlight;
		}
		
		memberFocusFlight = new MemberFocusFlight();
		memberFocusFlight.setMember_id(DecoderUtil.UtfDecoder(member_id));
		memberFocusFlight.setFlightNumber(DecoderUtil.UtfDecoder(flightNumber));
		memberFocusFlight.setFlightDate(flightDate);
		memberFocusFlight.setMemberRole(DecoderUtil.UtfDecoder(memberRole));
		memberFocusFlight.setFlightState(DecoderUtil.UtfDecoder(flightState));
		memberFocusFlight.setIfTakeLuggage(ifTakeLuggage);
		memberFocusFlight.setTakePerson(DecoderUtil.UtfDecoder(takePerson));
		memberFocusFlight.setPersonCount(personCount);
		memberFocusFlight.setIfSet(ifSet);
		memberFocusFlight.setMobile(mobile);
		memberFocusFlight.setSeatNo(seatNo);
		memberFocusFlight.setTicketNo(ticketNo);
		memberFocusFlight.setCouponId(couponId);
		memberFocusFlight.setStopover(stopover);
		dbDAO.persist(memberFocusFlight);
		return memberFocusFlight;
	}
	
	@Override
	public MemberFocusFlight persist(String member_id, String flightNumber, String flightDate, String flightState, String couponId) {
		
		return persist(member_id, flightNumber, flightDate, null, flightState, null, null, null, null, null, null, null, couponId, versionFlag);
	}
	
	@Override
	public boolean saveFocus(String member_id, String flightNumber,
			String flightDate, String flightState,String stopover) throws ServiceException {
		
		try {
			persist(member_id, flightNumber, flightDate, null, flightState, 
					null, null, null, null, null, 
					null, null, null, stopover);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public MemberFocusFlight set(String id, Integer takeLuggage,
			Integer personCount, String specialPerson, String phone,
			String IDCard) {
	
		MemberFocusFlight memberFocusFlight = get(MemberFocusFlight.class, id);
		memberFocusFlight.setIfSet(1);
		memberFocusFlight.setIfTakeLuggage(takeLuggage);
		memberFocusFlight.setPersonCount(personCount);
		memberFocusFlight.setTakePerson(specialPerson);
		memberFocusFlight.setMobile(phone);
		memberFocusFlight.setIdcard(IDCard);
		merge(memberFocusFlight);
		
		return memberFocusFlight;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		MemberFocusFlight memberFocusFlight = dbDAO.queryById(this.getTableNameFromEntity(MemberFocusFlight.class), id);
		dbDAO.remove(memberFocusFlight);
		return true;
	}

	@Override
	public MemberFocusFlight queryByMemberAndFlightNoAndDate(
			String flightNumber, String flightDate, String flightState,
			String versionFlag) throws ServiceException {
		Member member = null;
		member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		if(member != null){
			MemberFocusFlight memberFocusFlight = dbDAO.queryByCustom(
					MemberFocusFlight.class.getSimpleName(),deleteFlag,versionFlag,
					"and s.flightNumber=?3 and s.flightDate =?4 and s.member_id=?5 and flightState=?6  and (s.stopover is null or s.stopover !='1')",
					new Object[] {flightNumber,flightDate,member.getId(), flightState});
			if (memberFocusFlight != null) {
				return memberFocusFlight;
			}
		}
		return null;
	}

	@Override
	public List<MemberFocusFlight> queryListByMember(String versionFlag)
			throws ServiceException {
		Object object = getSession().get(ContextConstants.SCOPE_MEMBER);
		Member member = null;
		MemberQQ memberQQ = null;
		MemberWeiBo memberWeiBo = null;
		MemberWeixin memberWeixin = null;
		List<MemberFocusFlight> list = new ArrayList<MemberFocusFlight>();
		if(object instanceof MemberQQ){
			memberQQ = (MemberQQ) object;
			list = dbDAO.queryByList(
					MemberFocusFlight.class.getSimpleName(), deleteFlag,
					versionFlag, "and s.member_id =?3 ", new Object[] { memberQQ
							.getId() }, orderBy, null);
		}else if(object instanceof MemberWeiBo){
			memberWeiBo = (MemberWeiBo) object;
			list = dbDAO.queryByList(
					MemberFocusFlight.class.getSimpleName(), deleteFlag,
					versionFlag, "and s.member_id =?3 ", new Object[] { memberWeiBo
							.getId() }, orderBy, null);
		}else if(object instanceof MemberWeixin){
			memberWeixin = (MemberWeixin) object;
			list = dbDAO.queryByList(
					MemberFocusFlight.class.getSimpleName(), deleteFlag,
					versionFlag, "and s.member_id =?3 ", new Object[] { memberWeixin
							.getId() }, orderBy, null);
		}else if(object instanceof Member){
			member = (Member) object;
			list = dbDAO.queryByList(
					MemberFocusFlight.class.getSimpleName(), deleteFlag,
					versionFlag, "and s.member_id =?3 ", new Object[] { member
							.getId() }, orderBy, null);
		}
		if (list != null && !list.isEmpty()) {
			return list;
		}
		return null;
	}

	@Override
	public void deleteMenuByFlight(String menuPosition, String versionFlag)
			throws ServiceException {

	}

	@Override
	public List<MemberFocusFlight> queryTransferListByMember(
			String flightState, String versionFlag) throws ServiceException {
		Member member = (Member) getSession()
				.get(ContextConstants.SCOPE_MEMBER);
		List<MemberFocusFlight> list = dbDAO.queryByList(
				MemberFocusFlight.class.getSimpleName(), deleteFlag,
				versionFlag, "and s.member_id =?3 and s.flightState= ?4",
				new Object[] { member.getId(), flightState}, orderBy, null);
		if (list != null && !list.isEmpty()) {
			return list;
		}
		return null;
	}
	
	@Override
	public List<MemberFocusFlight> queryListByMember(
			String flightState, String flightNumber) throws ServiceException {
		Member member = (Member) getSession()
				.get(ContextConstants.SCOPE_MEMBER);
		List<MemberFocusFlight> list = dbDAO.queryByList(
				MemberFocusFlight.class.getSimpleName(), deleteFlag,
				versionFlag, "and s.member_id =?3 and s.flightState= ?4 and s.flightNumber=?5",
				new Object[] { member.getId(), flightState, flightNumber}, orderBy, null);
		if (list != null && !list.isEmpty()) {
			return list;
		}
		return null;
	}

	@Override
	public MemberFocusFlight queryAppMemberFocus(String memberId,
			String flightNumber, String flightDate, String flightState,
			String memberRole) throws ServiceException {
		MemberFocusFlight memberFocusFlight = dbDAO
				.queryByCustom(
						MemberFocusFlight.class.getSimpleName(),
						deleteFlag,
						versionFlag,
						"and s.flightNumber=?3 and s.flightDate=?4 and s.member_id=?5 and s.flightState= ?6",
						new Object[] {flightNumber,flightDate, memberId,flightState });
		if (memberFocusFlight != null) {
			return memberFocusFlight;
		}
		return null;
	}

	@Override
	public List<MemberFocusFlight> queryListByCoupon(String memberId, String versionFlag) throws ServiceException {
		List<MemberFocusFlight> list = dbDAO.queryByList(MemberFocusFlight.class.getSimpleName(), deleteFlag, versionFlag, 
				"and s.member_id =?3 and s.couponId is not null", new Object[]{memberId}, orderBy, null);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public MemberFocusFlight queryArrivalByMemberAndNo(String flightNumber,
			Date flightDate, String versionFlag) throws ServiceException {
		MemberFocusFlight memberFocusFlight = dbDAO.queryByCustom(MemberFocusFlight.class.getSimpleName(), deleteFlag, versionFlag, " and s.flightNumber =?3 and s.flightDate =?4 and s.flightState ='arrival'", new Object[]{flightNumber,flightDate});
		if(memberFocusFlight != null){
			return memberFocusFlight;
		}
		return null;
	}
	
	@Override
	public MemberFocusFlight query(String member_id, String flightNumber,
			String flightDate) throws ServiceException {
		
		String tableName = MemberFocusFlight.class.getName();
		List<MemberFocusFlight> list = dbDAO.executeJPQLForQuery("select t from " + tableName + " t where t.member_id=? and t.flightNumber=? and t.flightDate =?"
					, MemberFocusFlight.class, member_id, flightNumber, flightDate);
		if(list != null && !list.isEmpty()){
			
			return list.get(0);
		}
		return null;
	}

	@Override
	public MemberFocusFlight queryByMemberAndStopover(String flightNumber,
			String flightDate, String flightState, String versionFlag)
			throws ServiceException {
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		if(member != null){
			return dbDAO.queryByCustom(MemberFocusFlight.class.getSimpleName(), deleteFlag, versionFlag, "and s.member_id =?3 and s.flightNumber =?4 and s.flightDate =?5 and s.flightState =?6 and s.stopover ='1'", new Object[]{member.getId(),flightNumber,flightDate,flightState});
		}
		return null;
	}

	@Override
	public MemberFocusFlight queryByJudge(String flightNumber, String flightDate,
			String flightState, String stopover,
			String versionFlag) throws ServiceException {
		MemberFocusFlight mf = null;
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		if(member != null){
			if("1".equals(stopover)){
				mf = dbDAO.queryByCustom(MemberFocusFlight.class.getSimpleName(), deleteFlag, versionFlag, "and s.member_id =?3 and s.flightNumber =?4 and s.flightDate =?5 and s.flightState =?6 and s.stopover='1'", new Object[]{member.getId(),flightNumber,flightDate,flightState});
				
			}else{
				
				mf = dbDAO.queryByCustom(MemberFocusFlight.class.getSimpleName(), deleteFlag, versionFlag, "and s.member_id =?3 and s.flightNumber =?4 and s.flightDate =?5 and s.flightState =?6 and (s.stopover is null or s.stopover!='1')", new Object[]{member.getId(),flightNumber,flightDate,flightState});
			}
		}
		return mf;
	}

	@Override
	public MemberFocusFlight queryByMember(String flightNumber,
			String flightDate, String member_id, String versionFlag) {
		return dbDAO.queryByCustom(MemberFocusFlight.class.getSimpleName(), deleteFlag, versionFlag, "and s.member_id =?3 and s.flightNumber=?4 and s.flightDate=?5", new Object[]{member_id,flightNumber,flightDate});
	}

	@Override
	public List<MemberFocusFlight> queryListByMemberId(String member_id)
			throws ServiceException {
		return dbDAO.queryByList(MemberFocusFlight.class.getSimpleName(), deleteFlag, versionFlag, " and s.member_id =?3", new Object[]{member_id}, orderBy, null);
	}
	
	@Override
	public List<MemberFocusFlight> queryDepartListByDate(String member_id, String flightDate) {

		return dbDAO.queryByList(MemberFocusFlight.class.getSimpleName(), deleteFlag, versionFlag, "and s.member_id =?3 and s.flightDate=?4 and s.flightState='depart'", new Object[]{member_id,flightDate}, orderBy, null);
	}

	@Override
	public MemberFocusFlight queryByShare(String ticketNo)
			throws ServiceException {
		return dbDAO.queryByCustom(MemberFocusFlight.class.getSimpleName(), deleteFlag, versionFlag, " and s.ticketNo =?3 and s.mobile is null", new Object[]{ticketNo});
	}

}