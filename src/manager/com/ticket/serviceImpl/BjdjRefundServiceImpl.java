package com.ticket.serviceImpl;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.ticket.enumer.PayMethod;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjOrder;
import com.ticket.pojo.BjdjRefund;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.SystemDictionary;
import com.ticket.service.IBjdjRefundService;
import com.ticket.service.IBjdjServiceCodeService;
import com.ticket.service.IPayService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.util.DateUtil;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.ResourceUtil;

/**
 * 退款记录业务接口实现类
 * @ClassName: IBjdjRefundService   
 * @Description: 提供退款记录操作的增删改查   
 * @author HiSay  
 * @date 2016-11-25 10:59:56
 *
 */
public class BjdjRefundServiceImpl extends BaseServiceImpl<BjdjRefund, String> implements IBjdjRefundService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(BjdjRefundServiceImpl.class);
	@Resource
	protected ISystemDictionaryService systemDictionaryService;
	@Resource
	protected IBjdjServiceCodeService serviceCodeService;
//	@Resource(name="baiduService")
//	private IPayService baiduService;
	@Resource(name="alipayService")
	private IPayService alipayService;
	@Resource(name="wxOpenService")
	private IPayService wxOpenService;
	@Resource(name="swiftService")
	private IPayService swiftService;

	@Override
	@Transactional(rollbackFor=Exception.class)
	public BjdjRefund apply(String order_id, String reason) throws ServiceException {
		
		//验证非空
		if(GeneralUtil.isNull(order_id)){
			
			throw new ServiceException(ResourceUtil.getText("order.id.required"));
		}
		
		BjdjOrder order = get(BjdjOrder.class, order_id);
		//验证订单是否已经支付了
		if(!"paid".equals(order.getState().getName())){
			
			throw new ServiceException(ResourceUtil.getText("order.noPay"));
		}
		
		//验证订单对应的服务码是否有已经使用的情况，如果有，则不能再退款
		List<BjdjServiceCode> serviceCodes = order.getServiceCodes();
		for(BjdjServiceCode serviceCode : serviceCodes){
			
			if(!"unused".equals(serviceCode.getState().getName())){
				
				throw new ServiceException(ResourceUtil.getText("serviceCode.used", new String[]{serviceCode.getCode()}));
			}
		}
		
		BjdjRefund bjdjRefund = new BjdjRefund();
		bjdjRefund.setOrder(order);
		bjdjRefund.setReason(DecoderUtil.UtfDecoder(reason));
		dbDAO.persist(bjdjRefund);
		
		//设置订单状态为退款中
		SystemDictionary refunding = systemDictionaryService.getByName("refunding");
		if(refunding == null){
			
			throw new ServiceException("后台没有设置退款的订单状态，请联系管理员");
		}
		order.setState(refunding);
		merge(order);
		
		//冻结服务码
		serviceCodeService.froze(order.getServiceCodes());
		
		return bjdjRefund;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public BjdjRefund audit(String id,Boolean allow,String remark) throws ServiceException {
		
		BjdjRefund bjdjRefund = dbDAO.queryById(this.getTableNameFromEntity(BjdjRefund.class), id);
		
		if(allow){ //如果允许退款，就先发起退款的到第三方支付平台
			
			//先冻结
			bjdjRefund.setFreeze(true);
			bjdjRefund.setAllow(allow);
			bjdjRefund.setRemark(remark);
			merge(bjdjRefund);
			
			BjdjOrder order= bjdjRefund.getOrder();
			PayMethod payMethod = order.getPayMethod();
			if(payMethod == PayMethod.alipay){
				
				alipayService.orderRefund(bjdjRefund);
			}else if(payMethod == PayMethod.weixin){
				
				wxOpenService.orderRefund(bjdjRefund);
			}else if(payMethod == PayMethod.swift){
				
				swiftService.orderRefund(bjdjRefund);
			}else if(payMethod == PayMethod.baidu){
				
//				String responseJsonStr = baiduService.orderRefund(bjdjRefund);
//				if(StringUtils.isBlank(responseJsonStr)){
//					
//					throw new ServiceException("未知错误");
//				}
//				
//				JSONObject responseJson = JSONObject.parseObject(responseJsonStr);
//				if(StringUtils.isNotBlank(responseJson.getString("result"))){
//					
//					response(bjdjRefund, true, responseJsonStr);
//				}else{
//					
//					response(bjdjRefund, false, responseJsonStr);
//				}
			}
		}else{ //如果不允许退款，就直接审核不通过
			
			bjdjRefund.setAllow(allow);
			bjdjRefund.setRemark(DecoderUtil.UtfDecoder(remark));
			dbDAO.merge(bjdjRefund);
			
			//解冻服务码
			serviceCodeService.unFroze(bjdjRefund.getOrder().getServiceCodes());
		}
		
		return bjdjRefund;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public BjdjRefund response(BjdjRefund bjdjRefund, boolean success, String xml) throws ServiceException {
		
		bjdjRefund.setSuccess(success);
		bjdjRefund.setFreeze(false);
		bjdjRefund.setTime(new Date());
		bjdjRefund.setResponseXML(xml);
		merge(bjdjRefund);
		
		BjdjOrder order = bjdjRefund.getOrder();
		SystemDictionary refunded = systemDictionaryService.getByName("refunded");
		if(refunded == null){
			
			throw new ServiceException("后台没有设置退款的订单状态，请联系管理员");
		}
		order.setState(refunded);
		merge(order);
		
		//回收服务码
		if(success){
			
			serviceCodeService.recover(order.getServiceCodes());
		}
		
		return bjdjRefund;
	}

	@Override
	public BjdjRefund queryWaitDealWith(BjdjOrder order) {
		
		BjdjRefund refund = dbDAO.executeJPQLForQuerySingle(
				"select s from " + BjdjRefund.class.getName() + " s where s.order=? and s.allow is null"
				, BjdjRefund.class, order);
		return refund;
	}
	
	@Override
	public BjdjRefund query(BjdjOrder order, String createTime) {
		
		Date date = DateUtil.parseStringToDate(createTime, "yyyyMMddHHmmss");
		BjdjRefund refund = dbDAO.executeJPQLForQuerySingle(
				"select s from " + BjdjRefund.class.getName() + " s where s.order=? and s.status.createTime=?"
				, BjdjRefund.class, order, date);
		return refund;
	}
}