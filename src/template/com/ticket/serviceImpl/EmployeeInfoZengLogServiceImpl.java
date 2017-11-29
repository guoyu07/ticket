package com.ticket.serviceImpl;


import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.ticket.exception.ServiceException;
import com.ticket.exception.SmallException;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.EmployeeInfoZengLog;
import com.ticket.pojo.EmployeeInfoZengLogDetail;
import com.ticket.pojo.OrderInfoDetail;
import com.ticket.service.IBjdjServiceCodeService;
import com.ticket.service.IChannelCustomerInfoService;
import com.ticket.service.IEmployeeInfoZengLogDetailService;
import com.ticket.service.IEmployeeInfoZengLogService;
import com.ticket.service.IMemberService;
import com.ticket.service.IOrderInfoDetailService;
import com.ticket.service.IOrderInfoService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SmsUtil;
import com.ticket.util.TwoDimensionCode;

/**
 * 员工转移服务码主表业务接口实现类
 * @ClassName: IEmployeeInfoZengLogService   
 * @Description: 提供员工转移服务码主表操作的增删改查   
 * @author HiSay  
 * @date 2016-01-18 17:18:08
 *
 */
public class EmployeeInfoZengLogServiceImpl extends BaseServiceImpl<EmployeeInfoZengLog, String> implements IEmployeeInfoZengLogService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(EmployeeInfoZengLogServiceImpl.class);
	@Resource
	private IOrderInfoDetailService orderInfoDetailService = null;
	@Resource
	private IChannelCustomerInfoService channelCustomerInfoService = null;
	@Resource
	private IEmployeeInfoZengLogDetailService employeeInfoZengLogDetailService = null;
	@Resource
	private IOrderInfoService orderInfoService = null;
	@Resource
	protected IMemberService memberService;
	@Resource
	protected IBjdjServiceCodeService serviceCodeService;
	
	@Override
	public boolean merge(String id,  String versionFlag) throws ServiceException {
		EmployeeInfoZengLog employeeInfoZengLog = dbDAO.queryById(this.getTableNameFromEntity(EmployeeInfoZengLog.class), id);
		CommonEntity status = employeeInfoZengLog.getStatus();
		status.setVersionFlag(versionFlag);
		employeeInfoZengLog.setStatus(status);
		dbDAO.merge(employeeInfoZengLog);
		return true;
	}

	@Override
	public boolean persist( String versionFlag) throws ServiceException {
		EmployeeInfoZengLog employeeInfoZengLog = new EmployeeInfoZengLog();
		CommonEntity status = employeeInfoZengLog.getStatus();
		status.setVersionFlag(versionFlag);
		employeeInfoZengLog.setStatus(status);
		dbDAO.persist(employeeInfoZengLog);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		EmployeeInfoZengLog employeeInfoZengLog = dbDAO.queryById(this.getTableNameFromEntity(EmployeeInfoZengLog.class), id);
		dbDAO.remove(employeeInfoZengLog);
		return true;
	}

	@Override
	public String add(EmployeeInfo employeeInfo, Integer count,
			String channelCustomerInfoLoginId,String remark) throws ServiceException {
		try {
			ChannelCustomerInfo channelCustomerInfo = channelCustomerInfoService
					.queryById(ChannelCustomerInfo.class.getSimpleName(), channelCustomerInfoLoginId);
			if(channelCustomerInfo == null){
				return "目标客户不存在,不能进行操作";
			}
			if(employeeInfo != null && GeneralUtil.isNull(employeeInfo.getChannelCustomerInfo())){
				return "您没有绑定渠道客户,不能进行操作";
			}
			List<OrderInfoDetail> list = orderInfoDetailService
						.getServiceCodeIdsByUnused(employeeInfo.getChannelCustomerInfo().getId(),true,count);
			if(list.isEmpty()){
				return "您没有足够数量的服务码,不能进行操作";
			}
			if(list.size() < count){
				return "您的服务码数量不足,不能进行操作";
			}
			EmployeeInfoZengLog employeeInfoZengLog = new EmployeeInfoZengLog();
			employeeInfoZengLog.setEmployeeInfo(employeeInfo);
			employeeInfoZengLog.setChannelCustomerInfo(channelCustomerInfo);
			employeeInfoZengLog.setCount(count);
			employeeInfoZengLog.setRemark(remark);
			this.persist(employeeInfoZengLog);
			for(OrderInfoDetail orderInfoDetail:list){
				EmployeeInfoZengLogDetail employeeInfoZengLogDetail  = new EmployeeInfoZengLogDetail();
				employeeInfoZengLogDetail.setEmployeeInfoZengLog(employeeInfoZengLog);
				employeeInfoZengLogDetail.setOrderInfoDetail(orderInfoDetail);
				employeeInfoZengLogDetailService.persist(employeeInfoZengLogDetail);
				//将订单设置成已转赠
				orderInfoDetail.setState(1);
				orderInfoDetailService.merge(orderInfoDetail);
			}
			//添加赠送的订单,将该用户的服务码转移到指定客户的服务码池子里边去
			orderInfoService.addByEmployeeInfoZengLog(1, channelCustomerInfo.getId(), count, list);
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "赠送失败";
		}
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public EmployeeInfoZengLog addForDistribution(
			String channelCustomerInfo_id, String name, String mobile, String[] orderDetail) throws ServiceException {
		
		ChannelCustomerInfo channelCustomer = channelCustomerInfoService.get(ChannelCustomerInfo.class, channelCustomerInfo_id);
		if(channelCustomer == null){
			
			throw new SmallException("目标客户不存在,不能进行操作");
		}
		List<OrderInfoDetail> list = new ArrayList<OrderInfoDetail>();
		for(String id : orderDetail){
			
			list.add(get(OrderInfoDetail.class, id));
		}
		
		EmployeeInfoZengLog employeeInfoZengLog = new EmployeeInfoZengLog();
		employeeInfoZengLog.setChannelCustomerInfo(get(ChannelCustomerInfo.class, channelCustomerInfo_id));
		employeeInfoZengLog.setCount(list.size());
		employeeInfoZengLog.setName(name);
		employeeInfoZengLog.setMobile(mobile);
		this.persist(employeeInfoZengLog);
		
		StringBuffer codes = new StringBuffer();
		for(OrderInfoDetail orderInfoDetail:list){
			EmployeeInfoZengLogDetail employeeInfoZengLogDetail = new EmployeeInfoZengLogDetail();
			employeeInfoZengLogDetail.setEmployeeInfoZengLog(employeeInfoZengLog);
			employeeInfoZengLogDetail.setOrderInfoDetail(orderInfoDetail);
			employeeInfoZengLogDetailService.persist(employeeInfoZengLogDetail);
			//将订单设置成已转赠
			orderInfoDetail.setState(1);
			orderInfoDetailService.merge(orderInfoDetail);
			
			BjdjServiceCode serviceCode = get(BjdjServiceCode.class, orderInfoDetail.getBjdjServiceCode().getId());
			TwoDimensionCode codeUtil = new TwoDimensionCode();
			String rootPath = ServletActionContext.getServletContext().getRealPath(File.separator);
			String suffix = "png";
			String httpPath = "/upload/" + UUID.randomUUID() + "." + suffix;
			codeUtil.encoderQRCode(serviceCode.getCode(), rootPath + httpPath, suffix, 20);
			serviceCode.setIsBind(true);
			serviceCode.setMember(memberService.generateByMobileAndSendSms(mobile, false));
			serviceCode.setTwoDimensionCodePath(httpPath);
			//默认过期时间三个月
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, 3);
			serviceCode.setExpireTime(calendar.getTime());
			serviceCodeService.merge(serviceCode);
			codes.append(serviceCode.getCode()).append(",");
		}
		
		SmsUtil.sendSms(mobile, "你收到服务码：" + codes.substring(0, codes.length()-1));
		return null;
	}
	
	
}