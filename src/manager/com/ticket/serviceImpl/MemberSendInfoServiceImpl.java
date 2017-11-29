package com.ticket.serviceImpl;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.DepartFromPort;
import com.ticket.pojo.FlightCompany;
import com.ticket.pojo.Member;
import com.ticket.pojo.MemberFocusFlight;
import com.ticket.pojo.MemberMac;
import com.ticket.pojo.MemberQQ;
import com.ticket.pojo.MemberSendInfo;
import com.ticket.pojo.MemberSendInfo.PushMethod;
import com.ticket.pojo.MemberWeiBo;
import com.ticket.pojo.MemberWeixin;
import com.ticket.pojo.WifiArea;
import com.ticket.pojo.WifiAreaMessage;
import com.ticket.service.IDepartFromPortService;
import com.ticket.service.IFlightCompanyService;
import com.ticket.service.IMassInfomationService;
import com.ticket.service.IMemberFocusFlightService;
import com.ticket.service.IMemberMacService;
import com.ticket.service.IMemberSendInfoService;
import com.ticket.service.IMemberService;
import com.ticket.service.IMessageTemplateService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.service.IWifiAreaMessageService;
import com.ticket.service.IWifiAreaService;
import com.ticket.service.IWifiService;
import com.ticket.util.DateUtil;
import com.ticket.util.JPushClientUtil;
import com.ticket.util.ParamVariableUtil;

/**
 * 会员信息发送记录业务接口实现类
 * @ClassName: IMemberSendInfoService   
 * @Description: 提供会员信息发送记录操作的增删改查   
 * @author HiSay  
 * @date 2016-02-03 20:53:58
 *
 */
public class MemberSendInfoServiceImpl extends BaseServiceImpl<MemberSendInfo, String> implements IMemberSendInfoService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(MemberSendInfoServiceImpl.class);
	@Resource
	protected IMemberService memberService;
	@Resource
	protected IMemberMacService memberMacService;
	@Resource
	protected IMassInfomationService massInfomationService;
	@Resource
	private IWifiService wifiService;
	@Resource
	private IWifiAreaService wifiAreaService;
	@Resource
	private IWifiAreaMessageService wifiMessageService;
	@Resource
	private IMessageTemplateService messageTemplateService;
	@Resource
	private ISystemDictionaryService dictionaryService;
	@Resource
	private IMemberFocusFlightService memberFocusFlightService;
	@Resource
	private IFlightCompanyService flightCompanyService;
	@Resource
	private IDepartFromPortService departFromPortService;
	
	@Override
	public boolean merge(String id, String member_id,String phone,String message, String versionFlag) throws ServiceException {
		
		MemberSendInfo memberSendInfo = dbDAO.queryById(this.getTableNameFromEntity(MemberSendInfo.class), id);
//		Member member = memberService.get(Member.class, member_id);
		memberSendInfo.setMember_id(member_id);
		memberSendInfo.setPhone(phone);
		memberSendInfo.setMessage(message);
		dbDAO.merge(memberSendInfo);
		return true;
	}

	@Override
	public boolean persist(String member_id, PushMethod method, String massInfo_id,String title,String message, String url, String flightNumber, Date flightDate, String versionFlag) {
		
		MemberSendInfo memberSendInfo = new MemberSendInfo();
		memberSendInfo.setMember_id(member_id);
		memberSendInfo.setMethod(method);
		memberSendInfo.setMassInfo_id(massInfo_id);
		memberSendInfo.setTitle(title);
		memberSendInfo.setMessage(message);
		memberSendInfo.setUrl(url);
		memberSendInfo.setFlightNumber(flightNumber);
		memberSendInfo.setFlightDate(flightDate);
		dbDAO.persist(memberSendInfo);
		
		//发送极光推送
		JPushClientUtil.sendToSingleUser(member_id, title, message, url);
		return true;
	}
	
	@Override
	public MemberSendInfo persist(String member_id, PushMethod method,String title,String message, String url) {
		
		MemberSendInfo memberSendInfo = new MemberSendInfo();
		memberSendInfo.setMember_id(member_id);
		memberSendInfo.setMethod(method);
		memberSendInfo.setMassInfo_id("");
		memberSendInfo.setTitle(title);
		memberSendInfo.setMessage(message);
		memberSendInfo.setUrl(url);
		dbDAO.persist(memberSendInfo);
		
		//发送极光推送
		JPushClientUtil.sendToSingleUser(member_id, title, message, url);
		return memberSendInfo;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		MemberSendInfo memberSendInfo = dbDAO.queryById(this.getTableNameFromEntity(MemberSendInfo.class), id);
		dbDAO.remove(memberSendInfo);
		return true;
	}
	
	@Override
	public List<MemberSendInfo> query(String member_id) {
		
		//不可加t.h5=true
		List<MemberSendInfo> list = dbDAO.executeJPQLForQuery("select t from " + MemberSendInfo.class.getName() + " t where t.member_id=? order by t.status.createTime desc", MemberSendInfo.class, member_id);
		return list;
	}

	@Override
	public List<MemberSendInfo> queryMy() {
		Object object = ActionContext.getContext().getSession().get(ContextConstants.SCOPE_MEMBER);
		Member member = null;
		MemberQQ memberQQ = null;
		MemberWeiBo bo = null;
		MemberWeixin memberWeixin = null;
		if(object != null){
			if(object instanceof MemberQQ){
				memberQQ = (MemberQQ) object;
				return query(memberQQ.getId());
			}else if(object instanceof MemberWeiBo){
				bo = (MemberWeiBo) object;
				return query(bo.getId());
			}else if(object instanceof MemberWeixin){
				memberWeixin = (MemberWeixin) object;
				return query(memberWeixin.getId());
			}else if(object instanceof Member){
				member = (Member) object;
				return query(member.getId());
			}
		}
		return null;
		
	}
	
	@Override
	public MemberSendInfo queryPrevMessage(MemberSendInfo msg)
			throws ServiceException {
		
		Object object = ActionContext.getContext().getSession().get(ContextConstants.SCOPE_MEMBER);
		Member member = null;
		MemberQQ memberQQ = null;
		MemberWeiBo bo = null;
		MemberWeixin memberWeixin = null;
		String member_id = null;
		if(object instanceof MemberQQ){
			memberQQ = (MemberQQ) object;
			member_id = memberQQ.getId();
		}else if(object instanceof MemberWeiBo){
			bo = (MemberWeiBo) object;
			member_id = bo.getId();
		}else if(object instanceof MemberWeixin){
			memberWeixin = (MemberWeixin) object;
			member_id = memberWeixin.getId();
		}else if(object instanceof Member){
			member = (Member) object;
			member_id = member.getId();
		}
		
		String sql = "select t from " + MemberSendInfo.class.getSimpleName() + " t " + 
		"where t.id != ? and t.member_id=? and t.status.createTime >= ? order by t.status.createTime";
	    MemberSendInfo list = (MemberSendInfo)this.dbDAO.executeJPQLForQuerySingle(sql, MemberSendInfo.class, 
	    		msg.getId(), member_id, msg.getStatus().getCreateTime());
	    return list;
	}

	@Override
	public MemberSendInfo queryNextMessage(MemberSendInfo msg)
			throws ServiceException {
		
		Object object = ActionContext.getContext().getSession().get(ContextConstants.SCOPE_MEMBER);
		Member member = null;
		MemberQQ memberQQ = null;
		MemberWeiBo bo = null;
		MemberWeixin memberWeixin = null;
		String member_id = null;
		if(object instanceof MemberQQ){
			memberQQ = (MemberQQ) object;
			member_id = memberQQ.getId();
		}else if(object instanceof MemberWeiBo){
			bo = (MemberWeiBo) object;
			member_id = bo.getId();
		}else if(object instanceof MemberWeixin){
			memberWeixin = (MemberWeixin) object;
			member_id = memberWeixin.getId();
		}else if(object instanceof Member){
			member = (Member) object;
			member_id = member.getId();
		}
		
		String sql = "select t from " + MemberSendInfo.class.getSimpleName() + " t " + 
			      "where t.id != ? and t.member_id=? and t.status.createTime <= ? order by t.status.createTime desc";
	    MemberSendInfo list = (MemberSendInfo)this.dbDAO.executeJPQLForQuerySingle(sql, MemberSendInfo.class, 
	    		msg.getId(), member_id, msg.getStatus().getCreateTime());
	    return list;
	}
	
	@Override
	public MemberSendInfo queryByUrl(String visitUrl) throws ServiceException {
		
		MemberSendInfo msg = dbDAO.queryByCustom(this.getTableNameFromEntity(MemberSendInfo.class)
				, deleteFlag, versionFlag, "and s.status.visitUrl=?3", new Object[]{Long.valueOf(visitUrl)}) ;
		if(msg != null) {
			return msg;
		}
		return null;
	}

	@Override
	public long numberOfUnread(String member_id) {
		
		String sql = "select count(t) from " + MemberSendInfo.class.getSimpleName() + " t where t.member_id=? and t.h5=false and t.status.deleteFlag=0";
		Long list = dbDAO.executeJPQLForQuerySingle(sql, Long.class, member_id);
		return list;
	}

	@Override
	public long myNumberOfUnread() {
		Object object = ActionContext.getContext().getSession().get(ContextConstants.SCOPE_MEMBER);
		Member member = null;
		MemberQQ memberQQ = null;
		MemberWeiBo bo = null;
		MemberWeixin memberWeixin = null;
		if(object != null){
			if(object instanceof MemberQQ){
				memberQQ = (MemberQQ) object;
				return numberOfUnread(memberQQ.getId());
			}else if(object instanceof MemberWeiBo){
				bo = (MemberWeiBo) object;
				return numberOfUnread(bo.getId());
			}else if(object instanceof MemberWeixin){
				memberWeixin = (MemberWeixin) object;
				return numberOfUnread(memberWeixin.getId());
			}else if(object instanceof Member){
				member = (Member) object;
				return numberOfUnread(member.getId());
			}
		}
		return 0;
	}

	@Override
	public void wifiPush(String mac, String rid) {

		//查询关联此mac的所有手机号码
		List<MemberMac> members = memberMacService.queryByMac(mac);
		//查询关联此设备的所有需要推送的消息
		List<WifiAreaMessage> templates = wifiMessageService.query(rid);
		for(int k = 0; k < members.size(); k++){
			
			final Member member = memberService.queryByMobile(members.get(k).getPhone(), versionFlag);
			if(member != null){
				
				for(int j = 0; j < templates.size(); j++){
					
					final WifiAreaMessage wifiMessage = templates.get(j);
					
					//达到时间间隔才发送
					if(sendInterval(wifiMessage.getWifiArea(), member.getId(), wifiMessage.getMessage().getTitle())){
						
						String title = wifiMessage.getMessage().getTitle();
						String content = wifiMessage.getMessage().getContent();
						String url = wifiMessage.getMessage().getUrl();
						if(ParamVariableUtil.containsVariable(content) 
								|| ParamVariableUtil.containsVariable(url)
								|| ParamVariableUtil.containsVariable(title)){  //特殊的信息要特殊处理一下（把参数设置设置进去）
							
							//替换“航班日期”变量
							String flightDate = DateUtil.formatDateToShortString(new Date());
							List<MemberFocusFlight> list = memberFocusFlightService.queryDepartListByDate(member.getId(), flightDate);
							for(int i = 0; i < list.size(); i++){
								
								String newTitle = ParamVariableUtil.replace(title, "flightDate", flightDate);
								String newContent = ParamVariableUtil.replace(content, "flightDate", flightDate);
								String newUrl = ParamVariableUtil.replace(url, "flightDate", flightDate);
								
								MemberFocusFlight focusFlight = list.get(i);
								DepartFromPort departFromPort = departFromPortService.queryByFlightNoAndDate(focusFlight.getFlightNumber(), focusFlight.getFlightDate(), versionFlag);
								if(departFromPort != null){
									
									//替换“值机柜台”变量
									newTitle = ParamVariableUtil.replace(newTitle, "cid", departFromPort.getCid());
									newContent = ParamVariableUtil.replace(newContent,  "cid", departFromPort.getCid());
									newUrl = ParamVariableUtil.replace(newUrl, "cid",departFromPort.getCid());
									
									//替换“登机口”变量
									newTitle = ParamVariableUtil.replace(newTitle, "gat", departFromPort.getGat());
									newContent = ParamVariableUtil.replace(newContent,  "gat", departFromPort.getGat());
									newUrl = ParamVariableUtil.replace(newUrl, "gat",departFromPort.getGat());
									
									//替换“航班号”变量
									String newCompanyName = "";
									if(ParamVariableUtil.contains(title, "flightNumber")){
										
										FlightCompany company = flightCompanyService.queryEntityByTwoCode(departFromPort.getAcw(), versionFlag);
										if(company != null){
											
											newCompanyName = company.getName() == null ? "" : "(" + company.getName() + ")";
										}
									}
									newTitle = ParamVariableUtil.replace(newTitle, "flightNumber", focusFlight.getFlightNumber() + newCompanyName);
									newContent = ParamVariableUtil.replace(newContent, "flightNumber", focusFlight.getFlightNumber() + newCompanyName);
									newUrl = ParamVariableUtil.replace(newUrl, "flightNumber", focusFlight.getFlightNumber() + newCompanyName);
									
									//发送站内信
									persist(member.getId(), PushMethod.wifi, "", newTitle, newContent, newUrl, null, null, versionFlag);
								}
							}
						}else{ //不含参数则直接发送
							
							//发送站内信
							persist(member.getId(), PushMethod.wifi, "", title, content, url, null, null, versionFlag);
						}
					}
				}
			}
		}
	}
	
	@Override
	public boolean sendInterval(WifiArea area, String member_id, String title) {
		
		try {
//			int among = Integer.parseInt(dictionaryService.getByName("send_time_interval").getValue());
			Calendar time = Calendar.getInstance();
			time.add(Calendar.MINUTE, -area.getInterv());
			Long size = dbDAO.executeJPQLForQuerySingle(
					"select count(s) from " + MemberSendInfo.class.getName() + " s"
					+ " where s.member_id = ? and s.title = ? and s.status.createTime >= ?", 
					Long.class, member_id, title, time.getTime());
			if(size > 0){
				
				return false;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return true;
	}
}