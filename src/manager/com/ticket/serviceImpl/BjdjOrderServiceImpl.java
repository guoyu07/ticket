package com.ticket.serviceImpl;


import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.ticket.enumer.BjdjOrderType;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjOrder;
import com.ticket.pojo.BjdjPage;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.BjdjServicePackage;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.Member;
import com.ticket.pojo.SystemDictionary;
import com.ticket.service.IBjdjOrderService;
import com.ticket.service.IBjdjRefundService;
import com.ticket.service.IBjdjServiceCodeService;
import com.ticket.service.IBjdjServicePackageService;
import com.ticket.service.IPayService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.OrderNumberUtil;
import com.ticket.util.QRCodeHelper;
import com.ticket.util.ResourceUtil;
import com.ticket.util.SmsUtil;

/**
 * 便捷登机订单表业务接口实现类
 * @ClassName: IBjdjOrderService   
 * @Description: 提供便捷登机订单表操作的增删改查   
 * @author tuyou  
 * @date 2015-10-23 15:22:31
 */
public class BjdjOrderServiceImpl extends BaseServiceImpl<BjdjOrder, String> implements IBjdjOrderService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(BjdjOrderServiceImpl.class);
	
	@Resource
	private IBjdjServiceCodeService serviceCodeService;
	@Resource
	private IBjdjServicePackageService servicePackageService;
//	@Resource(name="baiduService")
//	private IPayService baiduService;
	@Resource(name="alipayService")
	private IPayService alipayService;
	@Resource(name="wxOpenService")
	private IPayService wxOpenService;
	@Resource
	private ISystemDictionaryService systemDictionaryService;
	@Resource
	private IBjdjRefundService bjdjRefundService;
	
	@Override
	public BjdjOrder merge(
			String id, 
			String number,
			String member_id, 
			Double price, 
			BjdjOrderType type,
			SystemDictionary state,
			String versionFlag) throws ServiceException {
		
		BjdjOrder bjdjOrder = dbDAO.queryById(this.getTableNameFromEntity(BjdjOrder.class), id);
		bjdjOrder.setNumber(number);
		bjdjOrder.setMember(new Member(member_id));
		bjdjOrder.setState(state);
		bjdjOrder.setPrice(price);
		bjdjOrder.setType(type);
		CommonEntity status = bjdjOrder.getStatus();
		status.setVersionFlag(versionFlag);
		bjdjOrder.setStatus(status);
		dbDAO.merge(bjdjOrder);
		return bjdjOrder;
	}

	@Override
	public BjdjOrder persist(String member_id, BjdjServicePackage packageType, Integer count, BjdjOrderType type, String mobile) throws ServiceException {
		
		BjdjOrder bjdjOrder = new BjdjOrder();
		
		bjdjOrder.setMember(get(Member.class, member_id));
		bjdjOrder.setCount(count);
		
		SystemDictionary noPay = systemDictionaryService.getByName("noPay");
		bjdjOrder.setState(noPay);
		bjdjOrder.setType(type);
		bjdjOrder.setMobile(mobile);
		bjdjOrder.setTimeout(1800);
		bjdjOrder.setPackageType(packageType);
		
		String orderNumber = OrderNumberUtil.generate();
		bjdjOrder.setNumber(orderNumber);
		
		switch (bjdjOrder.getType()) {
		case bjdj:
			Double price = packageType.getPrice();
			bjdjOrder.setPrice(price);
			bjdjOrder.setName(packageType.getName());
			break;
		case electromobile:
			break;
		default:
			break;
		}
		dbDAO.persist(bjdjOrder);
		return bjdjOrder;
	}

	@Override
	public boolean remove(String id) throws ServiceException {

		BjdjOrder order = queryById(BjdjOrder.class.getName(), id);
		remove(order);
		return true;
	}
	
	@Override
	public void remove(BjdjOrder object) throws ServiceException {
		
		SystemDictionary noUse = systemDictionaryService.getByName("unused");
		serviceCodeService.recycle(object.getServiceCodes(), noUse);
		batchRealDelete(BjdjOrder.class, object.getId());
	}
	
	@Override
	public BjdjOrder generateOrder(Member member, BjdjServicePackage packageType, Integer count, BjdjOrderType type) throws ServiceException{
		
		if(member == null){
			
			throw new ServiceException(ResourceUtil.getText("user.notLogin"));
		}
		
		if(count == null){
			
			throw new ServiceException(ResourceUtil.getText("order.count.required"));
		}
		
		//检验服务码是否有库存
		List<BjdjServiceCode> codes = serviceCodeService.getNotUsedByType("散客", count);
		if(codes.size() != count){
			
			throw new ServiceException(ResourceUtil.getText("serviceCode.notEnough"));
		}
		
		//生成我方订单
		BjdjOrder order = persist(member.getId(), packageType, count, type, member.getPhone());
		return order;
	}

	@Override
	public List<BjdjOrder> queryByMemberId(String member_id) {
		
		List<BjdjOrder> order = dbDAO.executeJPQLForQueryEntity(
				"select s from " + BjdjOrder.class.getName() + " s where s.member = ? order by s.status.createTime desc", 
				new Member(member_id));
		return order;
	}
	
	@Override
	public List<BjdjOrder> queryByMemberIdAndStatus(String member_id,
			SystemDictionary state) {

		List<BjdjOrder> order = dbDAO.executeJPQLForQueryEntity(
				"select s from " + BjdjOrder.class.getName() + " s where member_id = ? and state = ? order by s.status.createTime desc", 
				member_id, state);
		return order;
	}
	
	@Override
	public List<BjdjOrder> queryByMemberIdAndDontComment(String member_id) {

		List<BjdjOrder> canComments = new ArrayList<BjdjOrder>();
		List<BjdjOrder> orders = queryByMemberId(member_id);
		for(BjdjOrder order : orders){
			
			try {
				if(queryCanCommentServiceCode(order).size() > 0){
					
					canComments.add(order);
				}
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		return canComments;
	}
	
	@Override
	public BjdjOrder getByServiceCode(String serviceCodeId) {
		
		BjdjOrder order = (BjdjOrder)dbDAO.executeJPQLForQuerySingle(
				"select s from " + BjdjOrder.class.getName() + " s,"
						+ BjdjServiceCode.class.getName() + " c where s.serviceCode.id = c.id and c.code=? order by s.status.createTime desc", 
						serviceCodeId);
		return order;
	}
	
	@Override
	public BjdjOrder getByNumber(String number) {
		
		BjdjOrder order = (BjdjOrder)dbDAO.executeJPQLForQuerySingle(
				"select s from " + BjdjOrder.class.getName() + " s where s.number = ?", number);
		return order;
	}
	
	@Override
	public BjdjOrder getByNumberOut(String numberOut) {
		
		BjdjOrder order = (BjdjOrder)dbDAO.executeJPQLForQuerySingle(
				"select s from " + BjdjOrder.class.getName() + " s where s.numberOut = ?", numberOut);
		return order;
	}
	
	@Override
	public List<BjdjServiceCode> queryMyServiceCode(String orderId) throws ServiceException {
		
		BjdjOrder order = queryById(BjdjOrder.class.getSimpleName(), orderId);
		return queryMyServiceCode(order);
	}

	@Override
	public List<BjdjServiceCode> queryCanDonationServiceCode(String orderId) throws ServiceException {
		
		BjdjOrder order = queryById(BjdjOrder.class.getSimpleName(), orderId);
		return queryCanDonationServiceCode(order);
	}

	@Override
	public List<BjdjServiceCode> queryCanActivateServiceCode(String orderId) throws ServiceException {
		
		BjdjOrder order = queryById(BjdjOrder.class.getSimpleName(), orderId);
		return queryCanActivateServiceCode(order);
	}
	
	@Override
	public List<BjdjServiceCode> queryCanCommentServiceCode(String orderId)
			throws ServiceException {
		
		BjdjOrder order = queryById(BjdjOrder.class.getSimpleName(), orderId);
		return queryCanCommentServiceCode(order);
	}

	@Override
	public boolean canRefund(String orderId) throws ServiceException {
		
		BjdjOrder order = queryById(BjdjOrder.class.getSimpleName(), orderId);
		return canRefund(order);
	}

	@Override
	public boolean canDelete(String orderId) throws ServiceException {
		
		BjdjOrder order = queryById(BjdjOrder.class.getSimpleName(), orderId);
		return canDelete(order);
	}
	
	@Override
	public List<BjdjServiceCode> queryMyServiceCode(BjdjOrder order) throws ServiceException {
		
		List<BjdjServiceCode> canDonation = new ArrayList<BjdjServiceCode>();
		
		for(BjdjServiceCode serviceCode : order.getServiceCodes()){
			
			if(serviceCodeService.isMine(serviceCode)){
				
				canDonation.add(serviceCode);
			}
		}
		return canDonation;
	}

	@Override
	public List<BjdjServiceCode> queryCanDonationServiceCode(BjdjOrder order) throws ServiceException {
		
		List<BjdjServiceCode> canDonation = new ArrayList<BjdjServiceCode>();
		//订单noPay不可以激活
		if(!"paid".equals(order.getState().getName())){

			return canDonation;
		}
		
		for(BjdjServiceCode serviceCode : queryMyServiceCode(order)){

			if(serviceCodeService.canDonate(serviceCode)){
				
				canDonation.add(serviceCode);
			}
		}
		return canDonation;
	}

	@Override
	public List<BjdjServiceCode> queryCanActivateServiceCode(BjdjOrder order) throws ServiceException  {
		
		List<BjdjServiceCode> canActivate = new ArrayList<BjdjServiceCode>();
		//订单noPay不可以激活
		if(!"paid".equals(order.getState().getName())){

			return canActivate;
		}else if(order.getType() == BjdjOrderType.electromobile){  //电瓶车订单不能激活
			
			return canActivate;
		}
		
		for(BjdjServiceCode serviceCode : queryMyServiceCode(order)){
			
			try {
				if(serviceCodeService.canActivate(serviceCode)){
					
					canActivate.add(serviceCode);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return canActivate;
	}
	
	@Override
	public List<BjdjServiceCode> queryCanCommentServiceCode(BjdjOrder order)
			throws ServiceException {
		
		List<BjdjServiceCode> canComment = new ArrayList<BjdjServiceCode>();
		for(BjdjServiceCode serviceCode : queryMyServiceCode(order)){
			
			if(serviceCodeService.canComment(serviceCode)){
				
				canComment.add(serviceCode);
			}
		}
		return canComment;
	}

	@Override
	public boolean canRefund(BjdjOrder order) throws ServiceException  {
		
		//订单noPay不可以退款
		if(!"paid".equals(order.getState().getName())){

			return false;
		}
		
		//支付时间大于7天的不允许退款
		if(order.getPayTime().getTime() < System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000){
			
			return false;
		}
		
		List<BjdjServiceCode> mys = queryMyServiceCode(order);
		if(mys.size() == 0){
			
			return false;
		}
		
		//服务码有使用的不可以退款
		for(BjdjServiceCode serviceCode : mys){
			
			if(!"unused".equals(serviceCode.getState().getName())){
				
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean canDelete(BjdjOrder order) throws ServiceException  {
		
		//如果是订单已经付款，但是服务码还没有全部用完，则不能删除
		if("paid".equals(order.getState().getName())){
			
			for(BjdjServiceCode serviceCode : order.getServiceCodes()){
				
				if(!"used".equals(serviceCode.getState().getName())){
					
					return false;
				}
			}
		}else if("refunding".equals(order.getState().getName())){ //如果在申请退款中不能删除
			
			return false;
		}
		return true;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void checkExpire() {

		SystemDictionary expireDict = systemDictionaryService.getUnderParentByName("order_state", "expired");
		
		//先查询所有noPay的订单
		List<BjdjOrder> orders = dbDAO.executeJPQLForQuery(
				"select o from "+BjdjOrder.class.getSimpleName()+" o where state=?", 
				BjdjOrder.class, systemDictionaryService.getByName("noPay"));
		
		Date now = new Date();
		//如果超时，则设置为状态为“已过期”
		for(BjdjOrder order : orders){
			
			if(order.getStatus().getCreateTime().getTime() + order.getTimeout()*1000 < now.getTime()){
				
				order.setState(expireDict);
				merge(order);
			}
		}
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public BjdjOrder setPaid(String order_no, String numberOut) throws ServiceException{
		
		BjdjOrder order = getByNumber(order_no);
		
		//此判断防止重复提交
		if("paid".equals(order.getState().getName())){
			
			return order;
		}else if(order.getServiceCodes() != null && order.getServiceCodes().size() > 0){
			
			return order;
		}
		SystemDictionary payed = systemDictionaryService.getByName("paid");
		order.setState(payed);
		
		//************************设置订单状态******************
		order.setPayTime(new Date());
		order.setNumberOut(numberOut);
		
		List<BjdjServiceCode> codes = serviceCodeService.getNotUsedByType("散客", order.getCount());
		if(codes.size() != order.getCount()){
			
			throw new ServiceException(ResourceUtil.getText("serviceCode.notEnough"));
		}
		
		//**********************生成服务码和二维码*******************
		String rootPath = ServletActionContext.getServletContext().getRealPath(File.separator);
		StringBuffer serviceCodes = new StringBuffer();
		for(int i = 0; i < order.getCount(); i++){
			
			String suffix = "jpg";
			String picName = UUID.randomUUID() + "." + suffix;
			String httpPath = "/upload/" + picName;
			BjdjServiceCode code = codes.get(i);
			try {
				QRCodeHelper.encoderQRCode(code.getCode(), true, rootPath+"/upload/", picName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			code.setOrder(order);
			code.setIsBind(true);
			code.setTwoDimensionCodePath(httpPath);
			code.setOrderType(order.getType());
			code.setMember(order.getMember());
			code.setPackageType(order.getPackageType());
			//设置服务码过期时间
			SystemDictionary dictionary = systemDictionaryService.getByName("bjdjServiceCodeExpiration");
			String month = dictionary.getValue();
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, Integer.parseInt(month));
			code.setExpireTime(calendar.getTime());
			serviceCodeService.merge(code);
			serviceCodes.append(code.getCode()).append(",");
		}
		merge(order);
		
		//修改
		BjdjServicePackage bjdjServicePackage = order.getPackageType();
		BjdjPage bjdjPage = bjdjServicePackage.getBjdjPage();
		SystemDictionary parent = bjdjPage.getSmsTemplate();
		SystemDictionary dictionary = systemDictionaryService.queryByParentAndName(parent, "sms.buy.success");
		
		if(dictionary != null && dictionary.getDescription() != null){
			
			SmsUtil.sendSms(order.getMobile(),
					MessageFormat.format(GeneralUtil.removeHtmlTags(dictionary.getDescription()), 
							order.getName(), 
							order.getNumber(), 
							serviceCodes.substring(0, serviceCodes.length()-1)
							));
		}
		return order;
	}

	@Override
	public List<BjdjServiceCode> getByOrderId(BjdjOrder order) throws ServiceException {
		List<BjdjServiceCode> bjdjServiceCodes = dbDAO.executeJPQLForQuery("select c from " + BjdjServiceCode.class.getName() + " c where c.order = ?", BjdjServiceCode.class, order);
		return bjdjServiceCodes;
	}
}