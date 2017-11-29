package com.ticket.serviceImpl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportInfo;
import com.ticket.pojo.AirportPlan;
import com.ticket.pojo.ArrivalAtPort;
import com.ticket.pojo.DepartFromPort;
import com.ticket.pojo.Member;
import com.ticket.pojo.MemberFocusFlight;
import com.ticket.pojo.MemberQQ;
import com.ticket.pojo.MemberSendInfo;
import com.ticket.pojo.MemberSendInfo.PushMethod;
import com.ticket.pojo.MemberWeiBo;
import com.ticket.pojo.MemberWeixin;
import com.ticket.pojo.SystemDictionary;
import com.ticket.service.IAirportInfoService;
import com.ticket.service.IArrivalAtPortService;
import com.ticket.service.IAutoSendMessageService;
import com.ticket.service.IBjdjActivateQueueService;
import com.ticket.service.IBjdjAppointmentService;
import com.ticket.service.IBjdjHallService;
import com.ticket.service.IDepartFromPortService;
import com.ticket.service.IInDataService;
import com.ticket.service.IMassInfomationService;
import com.ticket.service.IMemberQqService;
import com.ticket.service.IMemberSendInfoService;
import com.ticket.service.IMemberService;
import com.ticket.service.IMemberWeiBoService;
import com.ticket.service.IMemberWeixinService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.util.AirportPlaneUtil;
import com.ticket.util.DateUtil;
import com.ticket.util.GeneralUtil;

public class AutoSendMessageServiceImpl extends BaseServiceImpl<MemberFocusFlight, String> implements IAutoSendMessageService {
	@Resource
	private IMemberService memberService;
	@Resource
	private IDepartFromPortService departFromPortService;
	@Resource
	private IArrivalAtPortService arrivalAtPortService;
	@Resource
	private IAirportInfoService airportInfoService;
	@Resource
	private IInDataService airportPlanService;
	@Resource 
	private IMemberSendInfoService memberSendInfoService;
	@Resource 
	private IMassInfomationService massInfomationService;
	@Resource 
	private IBjdjHallService hallService;
	@Resource 
	private ISystemDictionaryService dictionaryService;
	@Resource 
	private IBjdjActivateQueueService activateQueueService;
	@Resource 
	private IBjdjAppointmentService appointmentService;
	@Resource
	private IMemberQqService memberQqService;
	@Resource
	private IMemberWeiBoService memberWeiBoService;
	@Resource
	private IMemberWeixinService memberWeixinService;
	
	@Override
	public void sendMsgAtFocusFlight(String flightNumber, String flightDate,String flightState,String memberRole)
			throws ServiceException {
		Object object = getSession().get(ContextConstants.SCOPE_MEMBER);
		if(object != null){
			if(object instanceof MemberQQ){
				MemberQQ memberQQ = (MemberQQ) object;
				//实时航班
				if(!AirportPlaneUtil.compareDate(DateUtil.parseShortStringToDate(flightDate))){
					//离港航班
					if("depart".equals(flightState)){
						sendDepartMessage(flightNumber,flightDate,memberRole,memberQQ.getId());
					}
					//进港航班
					else if("arrival".equals(flightState)&&"seat".equals(memberRole)){
						sendArrivalMessage(flightNumber,flightDate,memberRole,memberQQ.getId());
					}else{
						
					}
				}else{//计划航班
					sendPlanFlightMessage(flightNumber,flightDate,flightState,memberRole,memberQQ.getId());
				}
			}else if(object instanceof MemberWeiBo){
				MemberWeiBo bo = (MemberWeiBo) object;
				//实时航班
				if(!AirportPlaneUtil.compareDate(DateUtil.parseShortStringToDate(flightDate))){
					//离港航班
					if("depart".equals(flightState)){
						sendDepartMessage(flightNumber,flightDate,memberRole,bo.getId());
					}
					//进港航班
					else if("arrival".equals(flightState)&&"seat".equals(memberRole)){
						sendArrivalMessage(flightNumber,flightDate,memberRole,bo.getId());
					}else{
						
					}
				}else{//计划航班
					sendPlanFlightMessage(flightNumber,flightDate,flightState,memberRole,bo.getId());
				}
			}else if(object instanceof MemberWeixin){
				MemberWeixin memberWeixin = (MemberWeixin) object;
				//实时航班
				if(!AirportPlaneUtil.compareDate(DateUtil.parseShortStringToDate(flightDate))){
					//离港航班
					if("depart".equals(flightState)){
						sendDepartMessage(flightNumber,flightDate,memberRole,memberWeixin.getId());
					}
					//进港航班
					else if("arrival".equals(flightState)&&"seat".equals(memberRole)){
						sendArrivalMessage(flightNumber,flightDate,memberRole,memberWeixin.getId());
					}else{
						
					}
				}else{//计划航班
						sendPlanFlightMessage(flightNumber,flightDate,flightState,memberRole,memberWeixin.getId());
				}
			}else if(object instanceof Member){
				Member member = (Member) object;
				//实时航班
				if(!AirportPlaneUtil.compareDate(DateUtil.parseShortStringToDate(flightDate))){
					//离港航班
					if("depart".equals(flightState)){
						sendDepartMessage(flightNumber,flightDate,memberRole,member.getId());
					}
					//进港航班
					else if("arrival".equals(flightState)&&"seat".equals(memberRole)){
						sendArrivalMessage(flightNumber,flightDate,memberRole,member.getId());
					}else{
						
					}
				}else{//计划航班
					if(GeneralUtil.isNotNull(member.getPhone())){
						sendPlanFlightMessage(flightNumber,flightDate,flightState,memberRole,member.getId());
					}else{
						
					}
				}
			}
		}
	}
	
	@Override
	public void sendMsgAtSucCheck(String flightNumber, String flightDate,String member_id,String ticketNo)
			throws ServiceException {
		
		Member member = memberService.get(Member.class, member_id);
//		MemberQQ memberQQ = memberQqService.queryById(MemberQQ.class.getName(), member_id);
//		MemberWeiBo bo = memberWeiBoService.queryById(MemberWeiBo.class.getName(), member_id);
//		MemberWeixin memberWeixin = memberWeixinService.queryById(MemberWeixin.class.getName(), member_id);
//		if(memberQQ != null){
//			String msg="尊敬的会员："+memberQQ.getNickName()+"您已成功值机，你的电子客票号为："+ticketNo;
//			sendMsg(member_id, msg);
//		}else if(memberWeixin != null){
//			String msg="尊敬的会员："+memberWeixin.getNickName()+"您已成功值机，你的电子客票号为："+ticketNo;
//			sendMsg(member_id, msg);
//		}else if(bo != null){
//			String msg="尊敬的会员："+bo.getNickName()+"您已成功值机，你的电子客票号为："+ticketNo;
//			sendMsg(member_id, msg);
//		}else if(member != null){
//			String msg="尊敬的会员："+member.getNickName()+"您已成功值机，你的电子客票号为："+ticketNo;
//			sendMsg(member_id, msg);
//		}
		if (member != null) {
			
			String msg = null;
			String title = null;
			SystemDictionary dictionary = this.dictionaryService.getByName("checkinSuccess");
			if (dictionary != null) {
				msg = MessageFormat.format(dictionary.getDescription(), member.getRealName(), ticketNo);
				title = dictionary.getValue();
			} else {
				title = "值机信息成功提示";
				msg = "尊敬的会员：" + member.getRealName() + "您已成功值机，你的电子客票号为：" + ticketNo;
			}
			sendMsg(member.getId(), title, msg);
		}
	}
	
	@Override
	public void sendMsgAtOpenBoardingGate(String flightNumber, String flightDate, String boardingGate)
			throws ServiceException {
		List<Object> memberList = new ArrayList<Object>();
		//乘机人离港航班
		List<MemberFocusFlight> seatDepartList = getListByConditions(flightNumber,flightDate,"depart","seat");
		memberList = getMemberList(seatDepartList);
		if(memberList != null && !memberList.isEmpty()){
			
			String msg = null;
			String title = null;
			SystemDictionary dictionary = this.dictionaryService.getByName("boardingGateOpen_passenger");
			if (dictionary != null)
			{
				msg = MessageFormat.format(dictionary.getDescription(), new Object[] { flightNumber, boardingGate });
				title = dictionary.getValue();
			}else {
				msg = "尊敬的用户：您好！您关注的航班号为" + flightNumber + "的航班" + boardingGate + "号登机口已开放，请您抓紧时间登机！";
		        title = "登机口开放";
			}
			for(int i=0;i<memberList.size();i++){
				if(memberList.get(i) instanceof MemberQQ){
					MemberQQ memberQQ = (MemberQQ) memberList.get(i);
					sendMsg(memberQQ.getId(), title, msg);
				}else if(memberList.get(i) instanceof MemberWeiBo){
					MemberWeiBo bo = (MemberWeiBo) memberList.get(i);
					sendMsg(bo.getId(), title, msg);
				}else if(memberList.get(i) instanceof MemberWeixin){
					MemberWeixin memberWeixin = (MemberWeixin) memberList.get(i);
					sendMsg(memberWeixin.getId(), title, msg);
				}else if(memberList.get(i) instanceof Member){
					Member member = (Member) memberList.get(i);
					sendMsg(member.getId(), title, msg);
				}
			}
		}
		//送机人离港航班
		List<MemberFocusFlight> sendDepartList = getListByConditions(flightNumber,flightDate,"depart","send");
		memberList = getMemberList(sendDepartList);
		if(memberList != null && !memberList.isEmpty()){
			
			String msg = null;
			String title = null;
			SystemDictionary dictionary = this.dictionaryService.getByName("boardingGateOpen_visitor");
			if (dictionary != null){
				msg = MessageFormat.format(dictionary.getDescription(), new Object[] { flightNumber, boardingGate });
				title = dictionary.getValue();
			}else {
				msg = "尊敬的用户：您好！您关注的航班号为" + flightNumber + "的航班已在" + boardingGate + "号登机口开始登机，感谢您的关注！";
		        title = "登机口开放";
			}
			for(int i=0;i<memberList.size();i++){
				if(memberList.get(i) instanceof MemberQQ){
					MemberQQ memberQQ = (MemberQQ) memberList.get(i);
					sendMsg(memberQQ.getId(), title, msg);
				}else if(memberList.get(i) instanceof MemberWeiBo){
					MemberWeiBo bo = (MemberWeiBo) memberList.get(i);
					sendMsg(bo.getId(), title, msg);
				}else if(memberList.get(i) instanceof MemberWeixin){
					MemberWeixin memberWeixin = (MemberWeixin) memberList.get(i);
					sendMsg(memberWeixin.getId(), title, msg);
				}else if(memberList.get(i) instanceof Member){
					Member member = (Member) memberList.get(i);
					sendMsg(member.getId(), title, msg);
				}
			}
		}
	}
	
	@Override
	public void sendMsgAtFlightArrive(String flightNumber, String flightDate)
			throws ServiceException {
		List<Object> memberList = new ArrayList<Object>();
		//乘机人离港航班
		List<MemberFocusFlight> seatDepartList = getListByConditions(flightNumber,flightDate,"depart","seat");
		memberList = getMemberList(seatDepartList);
		if(memberList != null && !memberList.isEmpty()){
			
			String msg = null;
			String title = null;
			SystemDictionary dictionary = this.dictionaryService.getByName("flightArrive_passenger");
			if (dictionary != null)
			{
		        msg = MessageFormat.format(dictionary.getDescription(), new Object[] { flightNumber });
		        title = dictionary.getValue();
			} else {
		        msg = "尊敬的用户：您好！您关注的航班号为" + flightNumber + "的离港航班已经到达，欢迎您再次乘坐！";
		        title = "航班到达提示";
			}
			for(int i=0;i<memberList.size();i++){
				if(memberList.get(i) instanceof MemberQQ){
					MemberQQ memberQQ = (MemberQQ) memberList.get(i);
					sendMsg(memberQQ.getId(), title, msg);
				}else if(memberList.get(i) instanceof MemberWeiBo){
					MemberWeiBo bo = (MemberWeiBo) memberList.get(i);
					sendMsg(bo.getId(), title, msg);
				}else if(memberList.get(i) instanceof MemberWeixin){
					MemberWeixin memberWeixin = (MemberWeixin) memberList.get(i);
					sendMsg(memberWeixin.getId(), title, msg);
				}else if(memberList.get(i) instanceof Member){
					Member member = (Member) memberList.get(i);
					sendMsg(member.getId(), title, msg);
				}
			}
		}
		//送机人离港航班
		List<MemberFocusFlight> sendDepartList = getListByConditions(flightNumber,flightDate,"depart","send");
		memberList = getMemberList(sendDepartList);
		if(memberList != null && !memberList.isEmpty()){
			
			String msg = null;
			String title = null;
			SystemDictionary dictionary = this.dictionaryService.getByName("flightArrive_visitor");
			if (dictionary != null) {
				msg = MessageFormat.format(dictionary.getDescription(), new Object[] { flightNumber });
				title = dictionary.getValue();
			} else {
				msg = "尊敬的用户：您好！您关注的航班号为" + flightNumber + "的离港航班已经到达！";
				title = "航班到达提示";
			}
			for(int i=0;i<memberList.size();i++){
				if(memberList.get(i) instanceof MemberQQ){
					MemberQQ memberQQ = (MemberQQ) memberList.get(i);
					sendMsg(memberQQ.getId(), title, msg);
				}else if(memberList.get(i) instanceof MemberWeiBo){
					MemberWeiBo bo = (MemberWeiBo) memberList.get(i);
					sendMsg(bo.getId(), title, msg);
				}else if(memberList.get(i) instanceof MemberWeixin){
					MemberWeixin memberWeixin = (MemberWeixin) memberList.get(i);
					sendMsg(memberWeixin.getId(), title, msg);
				}else if(memberList.get(i) instanceof Member){
					Member member = (Member) memberList.get(i);
					sendMsg(member.getId(), title, msg);
				}
			}
		}
		//接机人离港航班
		List<MemberFocusFlight> receptDepartList = getListByConditions(flightNumber,flightDate,"depart","recept");
		memberList = getMemberList(receptDepartList);
		if(memberList != null && !memberList.isEmpty()){
			
			String msg = null;
			String title = null;
			SystemDictionary dictionary = this.dictionaryService.getByName("flightArrive_receive");
			if (dictionary != null) {
				msg = MessageFormat.format(dictionary.getDescription(),new Object[] { flightNumber });
				title = dictionary.getValue();
			} else {
				msg = "尊敬的用户：您好！您关注的航班号为" + flightNumber + "的离港航班已经到达！请您开始接机，谢谢！";
				title = "航班到达提示";
			}
			for(int i=0;i<memberList.size();i++){
				if(memberList.get(i) instanceof MemberQQ){
					MemberQQ memberQQ = (MemberQQ) memberList.get(i);
					sendMsg(memberQQ.getId(), title, msg);
				}else if(memberList.get(i) instanceof MemberWeiBo){
					MemberWeiBo bo = (MemberWeiBo) memberList.get(i);
					sendMsg(bo.getId(), title, msg);
				}else if(memberList.get(i) instanceof MemberWeixin){
					MemberWeixin memberWeixin = (MemberWeixin) memberList.get(i);
					sendMsg(memberWeixin.getId(), title, msg);
				}else if(memberList.get(i) instanceof Member){
					Member member = (Member) memberList.get(i);
					sendMsg(member.getId(), title, msg);
				}
			}
		}
		//乘机人进港航班
		List<MemberFocusFlight> seatArriveList = getListByConditions(flightNumber,flightDate,"arrival","seat");
		memberList = getMemberList(seatArriveList);
		if(memberList != null && !memberList.isEmpty()){
			
			String msg = null;
			String title = null;
		      SystemDictionary dictionary = this.dictionaryService.getByName("flightDepart_passenger");
		      if (dictionary != null)
		      {
		        msg = MessageFormat.format(dictionary.getDescription(), new Object[] { flightNumber });
		        title = dictionary.getValue();
		      }
		      else {
		        msg = "尊敬的用户：您好！您关注的航班号为" + flightNumber + "的进港航班已经到达！感谢您的乘坐。";
		        title = "航班到达提示";
		      }
			for(int i=0;i<memberList.size();i++){
				if(memberList.get(i) instanceof MemberQQ){
					MemberQQ memberQQ = (MemberQQ) memberList.get(i);
					sendMsg(memberQQ.getId(), title, msg);
				}else if(memberList.get(i) instanceof MemberWeiBo){
					MemberWeiBo bo = (MemberWeiBo) memberList.get(i);
					sendMsg(bo.getId(), title, msg);
				}else if(memberList.get(i) instanceof MemberWeixin){
					MemberWeixin memberWeixin = (MemberWeixin) memberList.get(i);
					sendMsg(memberWeixin.getId(), title, msg);
				}else if(memberList.get(i) instanceof Member){
					Member member = (Member) memberList.get(i);
					sendMsg(member.getId(), title, msg);
				}
			}
		}
		//送机人进港航班
		List<MemberFocusFlight> sendArriveList = getListByConditions(flightNumber,flightDate,"arrival","send");
		memberList = getMemberList(sendArriveList);
		if(memberList != null && !memberList.isEmpty()){
			
			String msg = null;
		      String title = null;
		      SystemDictionary dictionary = this.dictionaryService.getByName("flightDepart_visitor");
		      if (dictionary != null)
		      {
		        msg = MessageFormat.format(dictionary.getDescription(), new Object[] { flightNumber });
		        title = dictionary.getValue();
		      }
		      else {
		        msg = "尊敬的用户：您好！您关注的航班号为" + flightNumber + "的进港航班已经到达！";
		        title = "航班到达提示";
		      }
			for(int i=0;i<memberList.size();i++){
				if(memberList.get(i) instanceof MemberQQ){
					MemberQQ memberQQ = (MemberQQ) memberList.get(i);
					sendMsg(memberQQ.getId(), title, msg);
				}else if(memberList.get(i) instanceof MemberWeiBo){
					MemberWeiBo bo = (MemberWeiBo) memberList.get(i);
					sendMsg(bo.getId(), title, msg);
				}else if(memberList.get(i) instanceof MemberWeixin){
					MemberWeixin memberWeixin = (MemberWeixin) memberList.get(i);
					sendMsg(memberWeixin.getId(), title, msg);
				}else if(memberList.get(i) instanceof Member){
					Member member = (Member) memberList.get(i);
					sendMsg(member.getId(), title, msg);
				}
			}
		}
		//接机人进港航班
		List<MemberFocusFlight> receptArriveList = getListByConditions(flightNumber,flightDate,"arrival","recept");
		memberList = getMemberList(receptArriveList);
		if(memberList != null && !memberList.isEmpty()){
			
			String msg = null;
		      String title = null;
		      SystemDictionary dictionary = this.dictionaryService.getByName("flightDepart_receive");
		      if (dictionary != null)
		      {
		        msg = MessageFormat.format(dictionary.getDescription(), new Object[] { flightNumber });
		        title = dictionary.getValue();
		      }
		      else {
		        msg = "尊敬的用户：您好！您关注的航班号为" + flightNumber + "的进港航班已经到达！请您准时接机。";
		        title = "航班到达提示";
		      }
			for(int i=0;i<memberList.size();i++){
				if(memberList.get(i) instanceof MemberQQ){
					MemberQQ memberQQ = (MemberQQ) memberList.get(i);
					sendMsg(memberQQ.getId(), title, msg);
				}else if(memberList.get(i) instanceof MemberWeiBo){
					MemberWeiBo bo = (MemberWeiBo) memberList.get(i);
					sendMsg(bo.getId(), title, msg);
				}else if(memberList.get(i) instanceof MemberWeixin){
					MemberWeixin memberWeixin = (MemberWeixin) memberList.get(i);
					sendMsg(memberWeixin.getId(), title, msg);
				}else if(memberList.get(i) instanceof Member){
					Member member = (Member) memberList.get(i);
					sendMsg(member.getId(), title, msg);
				}
			}
		}
	}
	
	@Override
	public void sendMsgAtFlightDelay(String flightNumber, String flightDate,Date newDate)
			throws ServiceException {
		List<Object> memberList = new ArrayList<Object>();
		//乘机人离港航班
		List<MemberFocusFlight> seatDepartList = getListByConditions(flightNumber,flightDate,null,null);
		memberList = getMemberList(seatDepartList);
		if(memberList != null && !memberList.isEmpty()){
			
			String msg = null;
		      String title = null;
		      SystemDictionary dictionary = this.dictionaryService.getByName("flightDelayInfo");
		      if (dictionary != null)
		      {
		        msg = MessageFormat.format(dictionary.getDescription(), new Object[] { flightNumber });
		        title = dictionary.getValue();
		      }
		      else {
		        msg = "尊敬的用户：您好！您关注的航班号为" + flightNumber + "的航班延误！给您带来不便，敬请谅解！请关注机场屏显动态，我们会实时发布最新航班信息。";
		        title = "航班延误信息";
		      }
			
			for(int i=0;i<memberList.size();i++){
				if(memberList.get(i) instanceof MemberQQ){
					MemberQQ memberQQ = (MemberQQ) memberList.get(i);
					sendMsg(memberQQ.getId(), title, msg);
				}else if(memberList.get(i) instanceof MemberWeiBo){
					MemberWeiBo bo = (MemberWeiBo) memberList.get(i);
					sendMsg(bo.getId(), title, msg);
				}else if(memberList.get(i) instanceof MemberWeixin){
					MemberWeixin memberWeixin = (MemberWeixin) memberList.get(i);
					sendMsg(memberWeixin.getId(), title, msg);
				}else if(memberList.get(i) instanceof Member){
					Member member = (Member) memberList.get(i);
					sendMsg(member.getId(), title, msg);
				}
			}
		}
	}

	@Override
	public void sendMsgAtCancelFlight(String flightNumber, String flightDate)
			throws ServiceException {
		List<Object> memberList = new ArrayList<Object>();
		//乘机人离港航班
		List<MemberFocusFlight> seatDepartList = getListByConditions(flightNumber,flightDate,null,null);
		memberList = getMemberList(seatDepartList);
		if(memberList != null && !memberList.isEmpty()){
			
			String msg = null;
		      String title = null;
		      SystemDictionary dictionary = this.dictionaryService.getByName("flightCancelInfo");
		      if (dictionary != null)
		      {
		        msg = MessageFormat.format(dictionary.getDescription(), new Object[] { flightNumber });
		        title = dictionary.getValue();
		      }
		      else {
		        msg = "尊敬的用户：您好！非常抱歉，您关注的航班号为" + flightNumber + "的航班取消了！给您带来不便，敬请谅解！";
		        title = "航班取消信息";
		      }
			for(int i=0;i<memberList.size();i++){
				if(memberList.get(i) instanceof MemberQQ){
					MemberQQ memberQQ = (MemberQQ) memberList.get(i);
					sendMsg(memberQQ.getId(), title, msg);
				}else if(memberList.get(i) instanceof MemberWeiBo){
					MemberWeiBo bo = (MemberWeiBo) memberList.get(i);
					sendMsg(bo.getId(), title, msg);
				}else if(memberList.get(i) instanceof MemberWeixin){
					MemberWeixin memberWeixin = (MemberWeixin) memberList.get(i);
					sendMsg(memberWeixin.getId(), title, msg);
				}else if(memberList.get(i) instanceof Member){
					Member member = (Member) memberList.get(i);
					sendMsg(member.getId(), title, msg);
				}
			}
		}
	}
	
	/**
	 * 会员关注计划航班时发送站内信
	 * @param flightNumber  航班编号
	 * @param flightDate	航班日期
	 * @param flightState	航班状态
	 * @param memberRole	关注角色
	 * @param member		会员
	 * @throws ServiceException
	 */
	private void sendPlanFlightMessage(String flightNumber, String flightDate,
			String flightState, String memberRole,String member_id) throws ServiceException{
		if("depart".equals(flightState)){
			AirportPlan dept = airportPlanService.queryDepartByNoAndDate(flightNumber,flightDate,versionFlag);
			if(dept != null){
				AirportInfo des = airportInfoService.queryByFourCode(dept.getArrive(), versionFlag);
				if(des != null){
					if("seat".equals(memberRole)){
						String msg = null;
			            String title = null;
			            SystemDictionary dictionary = this.dictionaryService.getByName("departFlightFocus_passenger");
			            if (dictionary != null)
			            {
			              msg = MessageFormat.format(dictionary.getDescription(), new Object[] { des.getName(), flightNumber });
			              title = dictionary.getValue();
			            }
			            else {
			              msg = "尊敬的用户，您好！感谢您关注由昆明飞往" + des.getName() + "的航班" + flightNumber;
			              title = "航班关注提示";
			            }
						sendMsg(member_id, title,msg);
					}
					if("send".equals(memberRole)){
						
						String msg = null;
			            String title = null;
			            SystemDictionary dictionary = this.dictionaryService.getByName("departFlightFocus_visitor");
			            if (dictionary != null)
			            {
			              msg = MessageFormat.format(dictionary.getDescription(), new Object[] { des.getName(), flightNumber });
			              title = dictionary.getValue();
			            }
			            else {
			              msg = "尊敬的用户，您好！感谢您关注由昆明飞往" + des.getName() + "的航班" + flightNumber + "，送机人员请在安检口止步,谢谢合作！";
			              title = "航班关注提示";
			            }
						sendMsg(member_id, title,msg);
					}
					if("recept".equals(memberRole)){
						
						String msg = null;
			            String title = null;
			            SystemDictionary dictionary = this.dictionaryService.getByName("departFlightFocus_receive");
			            if (dictionary != null)
			            {
			              msg = MessageFormat.format(dictionary.getDescription(), new Object[] { des.getName(), flightNumber });
			              title = dictionary.getValue();
			            }
			            else {
			              msg = "尊敬的用户，您好！您已关注由昆明飞往" + des.getName() + "的航班" + flightNumber + "，接机时请将车辆停靠在指定位置，谢谢合作！";
			              title = "航班关注提示";
			            }
						sendMsg(member_id, title,msg);
					}
				}
			}
		}
		if("arrival".equals(flightState)){
			AirportPlan arrivalFlight = airportPlanService.queryArrivalByNoAndDate(flightNumber,flightDate,versionFlag);
			if(arrivalFlight != null){
				AirportInfo org = airportInfoService.queryByFourCode(arrivalFlight.getDept(), versionFlag);
				if(org != null){
					if("seat".equals(memberRole)){
						
						String msg = null;
			            String title = null;
			            SystemDictionary dictionary = this.dictionaryService.getByName("arriveFlightFocus_passenger");
			            if (dictionary != null)
			            {
			              msg = MessageFormat.format(dictionary.getDescription(), new Object[] { org.getName(), flightNumber });
			              title = dictionary.getValue();
			            }
			            else {
			              msg = "尊敬的用户，您好！感谢您关注由" + org.getName() + "飞往昆明的航班" + flightNumber;
			              title = "航班关注提示";
			            }
						sendMsg(member_id, title,msg);
					}
					if("send".equals(memberRole)){
						
						String msg = null;
			            String title = null;
			            SystemDictionary dictionary = this.dictionaryService.getByName("arriveFlightFocus_visitor");
			            if (dictionary != null)
			            {
			              msg = MessageFormat.format(dictionary.getDescription(), new Object[] { org.getName(), flightNumber });
			              title = dictionary.getValue();
			            }
			            else {
			              msg = "尊敬的用户，您好！感谢您关注由" + org.getName() + "飞往昆明的航班" + flightNumber + "，送机人员请在安检口止步,谢谢合作！";
			              title = "航班关注提示";
			            }
						sendMsg(member_id, title,msg);
					}
					if("recept".equals(memberRole)){
						
						String msg = null;
			            String title = null;
			            SystemDictionary dictionary = this.dictionaryService.getByName("arriveFlightFocus_receive");
			            if (dictionary != null)
			            {
			              msg = MessageFormat.format(dictionary.getDescription(), new Object[] { org.getName(), flightNumber });
			              title = dictionary.getValue();
			            }
			            else {
			              msg = "尊敬的用户，您好！感谢您关注由由" + org.getName() + "飞往昆明的航班" + flightNumber + "，接机时请将车辆停靠在指定位置，谢谢合作！";
			              title = "航班关注提示";
			            }
						sendMsg(member_id, title,msg);
					}
				}
			}
		}
		
	}

	/**
	 * 会员关注进港航班时发送站内信
	 * @param flightNumber  航班编号
	 * @param flightDate    航班日期
	 * @param memberRole	关注角色
	 * @param member	会员
	 * @throws ServiceException
	 */
	private void sendArrivalMessage(String flightNumber, String flightDate,
			String memberRole, String member_id) throws ServiceException {
		ArrivalAtPort arrivalFlight = arrivalAtPortService.queryByFlightNoAndDate(flightNumber,flightDate,versionFlag);
		if(arrivalFlight != null){
			AirportInfo org = airportInfoService.queryByThreeCode(arrivalFlight.getOrg(), versionFlag);
			if(org != null){
				if("seat".equals(memberRole)){
					
					String msg = null;
			          String title = null;
			          SystemDictionary dictionary = this.dictionaryService.getByName("arriveFlightFocus_passenger");
			          if (dictionary != null)
			          {
			            msg = MessageFormat.format(dictionary.getDescription(), new Object[] { org.getName(), flightNumber });
			            title = dictionary.getValue();
			          }
			          else {
			            msg = "尊敬的用户，您好！感谢您关注由" + org.getName() + "飞往昆明的航班" + flightNumber;
			            title = "航班关注提示";
			          }
					sendMsg(member_id, title,msg);
				}
				if("send".equals(memberRole)){
					
					String msg = null;
			          String title = null;
			          SystemDictionary dictionary = this.dictionaryService.getByName("arriveFlightFocus_visitor");
			          if (dictionary != null)
			          {
			            msg = MessageFormat.format(dictionary.getDescription(), new Object[] { org.getName(), flightNumber });
			            title = dictionary.getValue();
			          }
			          else {
			            msg = "尊敬的用户，您好！感谢您关注由" + org.getName() + "飞往昆明的航班" + flightNumber + "，送机人员请在安检口止步,谢谢合作！";
			            title = "航班关注提示";
			          }
					sendMsg(member_id, title,msg);
				}
				if("recept".equals(memberRole)){
					
					String msg = null;
			          String title = null;
			          SystemDictionary dictionary = this.dictionaryService.getByName("arriveFlightFocus_receive");
			          if (dictionary != null)
			          {
			            msg = MessageFormat.format(dictionary.getDescription(), new Object[] { org.getName(), flightNumber });
			            title = dictionary.getValue();
			          }
			          else {
			            msg = "尊敬的用户，您好！感谢您关注由由" + org.getName() + "飞往昆明的航班" + flightNumber + "，接机时请将车辆停靠在指定位置，谢谢合作！";
			            title = "航班关注提示";
			          }
					sendMsg(member_id, title,msg);
				}
			}
		}
		
	}

	/**
	 * 会员关注离港航班时发送站内信
	 * @param flightNumber  航班号
	 * @param flightDate	航班日期
	 * @param memberRole	关注角色
	 * @param member	会员
	 * @throws ServiceException
	 */
	private void sendDepartMessage(String flightNumber, String flightDate,
			String memberRole,String member_id) throws ServiceException {
		DepartFromPort dept = departFromPortService.queryByFlightNoAndDate(flightNumber, flightDate, versionFlag);
		if(dept != null){
			AirportInfo des = airportInfoService.queryByThreeCode(dept.getDes(), versionFlag);
			if(des != null){
				if("seat".equals(memberRole)){
					
					String msg = null;
			          String title = null;
			          SystemDictionary dictionary = this.dictionaryService.getByName("departFlightFocus_passenger");
			          if (dictionary != null)
			          {
			            msg = MessageFormat.format(dictionary.getDescription(), new Object[] { des.getName(), flightNumber });
			            title = dictionary.getValue();
			          }
			          else {
			            msg = "尊敬的用户，您好！感谢您关注由昆明飞往" + des.getName() + "的航班" + flightNumber;
			            title = "航班关注提示";
			          }
					sendMsg(member_id, title,msg);
				}
				if("send".equals(memberRole)){
					
					String msg = null;
			          String title = null;
			          SystemDictionary dictionary = this.dictionaryService.getByName("departFlightFocus_visitor");
			          if (dictionary != null)
			          {
			            msg = MessageFormat.format(dictionary.getDescription(), new Object[] { des.getName(), flightNumber });
			            title = dictionary.getValue();
			          }
			          else {
			            msg = "尊敬的用户，您好！感谢您关注由昆明飞往" + des.getName() + "的航班" + flightNumber + "，送机人员请在安检口止步,谢谢合作！";
			            title = "航班关注提示";
			          }
					sendMsg(member_id, title,msg);
				}
				if("recept".equals(memberRole)){
					
					String msg = null;
			          String title = null;
			          SystemDictionary dictionary = this.dictionaryService.getByName("departFlightFocus_receive");
			          if (dictionary != null)
			          {
			            msg = MessageFormat.format(dictionary.getDescription(), new Object[] { des.getName(), flightNumber });
			            title = dictionary.getValue();
			          }
			          else {
			            msg = "尊敬的用户，您好！您已关注由昆明飞往" + des.getName() + "的航班" + flightNumber + "，接机时请将车辆停靠在指定位置，谢谢合作！";
			            title = "航班关注提示";
			          }
					sendMsg(member_id, title,msg);
				}
			}
		}
	}
		

	/**
	 * 站内推送信息
	 * @param member 会员
	 * @param msg 信息
	 * @throws ServiceException
	 */
	private void sendMsg(String member_id, String title, String msg) throws ServiceException {
		MemberSendInfo msi = new MemberSendInfo();
		msi.setMember_id(member_id);
		msi.setTitle(title);
		msi.setMessage(msg);
		msi.setMethod(PushMethod.flight);
		memberSendInfoService.persist(msi);
	}

	private List<MemberFocusFlight> getListByConditions(String flightNumber,String flightDate,
			String flightState,String memberRole) {
		return dbDAO.queryByList(MemberFocusFlight.class.getSimpleName(),
				deleteFlag, versionFlag, "and flightNumber =?3 and flightDate =?4 " +
						"and s.flightState=?5 and s.memberRole =?6", 
						new Object[]{flightNumber,flightDate,flightState,memberRole}, orderBy, null);
	}
	private List<Object> getMemberList(List<MemberFocusFlight> list){
		List<Object> memberList = new ArrayList<Object>();
		if(list != null && !list.isEmpty()){
			for(MemberFocusFlight obj : list){
				if(GeneralUtil.isNotNull(obj.getMember_id())){
					try {
						MemberQQ memberQQ = memberQqService.get(MemberQQ.class, obj.getMember_id());
						MemberWeiBo bo = memberWeiBoService.queryById(MemberWeiBo.class.getName(), obj.getMember_id());
						MemberWeixin memberWeixin = memberWeixinService.queryById(MemberWeixin.class.getName(), obj.getMember_id());
						if(memberQQ != null){
							memberList.add(memberQQ);
						}else if(bo != null){
							memberList.add(bo);
						}else if(memberWeixin != null){
							memberList.add(memberWeixin);
						}else{
							Member member = memberService.queryById(Member.class.getSimpleName(), obj.getMember_id());
							if(GeneralUtil.isNotNull(member.getPhone())){
								memberList.add(member);
							}
						}
					} catch (ServiceException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return memberList;
	}
}
