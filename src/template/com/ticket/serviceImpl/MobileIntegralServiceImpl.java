package com.ticket.serviceImpl;

import javax.annotation.Resource;

import com.ticket.exception.ServiceException;
import com.ticket.service.IBjdjOrderService;
import com.ticket.service.IBjdjServiceCodeService;
import com.ticket.service.IMemberService;
import com.ticket.service.IMobileIntegralService;
import com.ticket.service.ISystemDictionaryService;

public class MobileIntegralServiceImpl implements IMobileIntegralService{

	@Resource
	protected IBjdjOrderService orderService;
	@Resource
	protected IMemberService memberService;
	@Resource
	protected ISystemDictionaryService dictionaryService;
	@Resource
	protected IBjdjServiceCodeService codeService;
	
	@Override
	public int getIntegral(String mobile) throws ServiceException {
		
		return 0;
	}

	@Override
	public void deductIntegral(String mobile) throws ServiceException {

		
	}

//	@Override
//	public BjdjServiceCode buy(String mobile) throws ServiceException{
//		
//		Member member = memberService.generateByMobileAndSendSms(mobile);
//		BjdjOrder order = orderService.generateLocal(member, 1, BjdjOrderType.bjdj);
//		
//		order = orderService.get(BjdjOrder.class, order.getId());
//		SystemDictionary payed = dictionaryService.getByName("paid");
//		order.setState(payed);
//		
//		//************************设置订单状态******************
//		order.setPayMember(member);
//		order.setPayTime(new Date());
//		order.setRealPay(0.00);
//		
//		String bjdj_price = dictionaryService.getValueByName("bjdj_price");
//		order.setDiscountAmount(Double.parseDouble(bjdj_price));
//		
//		String rmb_integral = dictionaryService.getValueByName("rmb/integral");
//		order.setConsumptionIntegral(Double.parseDouble(bjdj_price) * Integer.parseInt(rmb_integral));
//		
//		List<BjdjServiceCode> codes = codeService.getNotUsedByType("散客", order.getCount());
//		if(codes.size() != order.getCount()){
//			
//			throw new ServiceException(ResourceUtil.getText("serviceCode.notEnough"));
//		}
//		
//		//**********************生成服务码和二维码*******************
//		TwoDimensionCode codeUtil = new TwoDimensionCode();
//		String rootPath = ServletActionContext.getServletContext().getRealPath(File.separator);
//		StringBuffer serviceCodes = new StringBuffer();
//		for(int i = 0; i < order.getCount(); i++){
//			
//			String suffix = "png";
//			String httpPath = "/upload/" + UUID.randomUUID() + "." + suffix;
//			BjdjServiceCode code = codes.get(i);
//			codeUtil.encoderQRCode(code.getCode(), rootPath + httpPath, suffix, 20);
//			code.setOrder(order);
//			code.setIsBind(true);
//			code.setMember(member);
//			code.setTwoDimensionCodePath(httpPath);
//			code.setOrderType(order.getType());
//			code.setMember(order.getMember());
//			//默认过期时间三个月
//			Calendar calendar = Calendar.getInstance();
//			calendar.add(Calendar.MONTH, 3);
//			code.setExpireTime(calendar.getTime());
//			codeService.merge(code);
//			serviceCodes.append(code.getCode()).append(",");
//		}
//		orderService.merge(order);
//		SmsUtil.sendSms(order.getMobile(), 
//				ResourceUtil.getText("order.pay.success", 
//				new String[]{
//						order.getName(), 
//						order.getNumber(), 
//						serviceCodes.substring(0, serviceCodes.length()-1)
//				}));
//		return codes.get(0);
//	}
}
