package com.ticket.serviceImpl;

import java.rmi.server.ServerCloneException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.ChannelType;
import com.ticket.pojo.OrderInfo;
import com.ticket.pojo.OrderInfoDetail;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.SystemDictionary;
import com.ticket.service.IBjdjServiceCodeOperationService;
import com.ticket.service.IBjdjServiceCodeService;
import com.ticket.service.IChannelCustomerInfoService;
import com.ticket.service.IChannelTypeService;
import com.ticket.service.ICustomerAccountService;
import com.ticket.service.IOrderInfoDetailService;
import com.ticket.service.IOrderInfoService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.util.Arith;
import com.ticket.util.DateUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.PaginationContext;

/**
 * 订单数据业务接口实现类
 * @ClassName: IOrderInfoService   
 * @Description: 提供订单数据操作的增删改查   
 * @author HiSay  
 * @date 2015-11-04 18:04:35
 *
 */
public class OrderInfoServiceImpl extends BaseServiceImpl<OrderInfo, String> implements IOrderInfoService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(OrderInfoServiceImpl.class);
	@Resource
	private ISystemDictionaryService systemDictionaryService = null;
	@Resource
	private IBjdjServiceCodeService bjdjServiceCodeService = null;
	@Resource
	private IChannelTypeService channelTypeService = null;
	@Resource
	private IChannelCustomerInfoService channelCustomerInfoService = null;
	@Resource
	private IOrderInfoDetailService orderInfoDetailService = null;
	@Resource
	private IBjdjServiceCodeOperationService bjdjServiceCodeOperationService = null;
	@Resource
	private ICustomerAccountService customerAccountService = null;

	@Override
	public boolean remove(String id) throws ServiceException {
		OrderInfo orderInfo = dbDAO.queryById(this.getTableNameFromEntity(OrderInfo.class), id);
		dbDAO.remove(orderInfo);
		return true;
	}

	@Override
	public String getEmployeeSaleCount(String employeeId) {
		String sql=" select  sum(buyCount) from "+OrderInfo.class.getSimpleName()+" where  payAccount in(select id from "+ChannelCustomerInfo.class.getSimpleName()+" where employee_id = ?1)";
		Double result  = dbDAO.executeCountSQL(sql, new Object[]{employeeId});
		return result.toString();
	}

	@Override
	public String add(Integer buyCount, String payWay,String channelCustomerInfoId) throws ServerCloneException {
		try {
			String result = "";
			//获取便捷等级的价格
			SystemDictionary priceDataDictionary = systemDictionaryService.getByName("bjdj_price");
			ChannelCustomerInfo channelCustomerInfo = channelCustomerInfoService
								.queryById(ChannelCustomerInfo.class.getSimpleName(), channelCustomerInfoId);
			//buyCount由优惠策略赠送固定数量的服务码
			//渠道客户类别
			ChannelType channelType = channelCustomerInfo.getChannelType();
			//获取服务码池子类型
			SystemDictionary systemDictionary = channelType.getType();
			if(systemDictionary != null){
				List<BjdjServiceCode> bjdjServiceCodes = bjdjServiceCodeService
					.getNotUsedByType(systemDictionary.getName(), buyCount);
				if(bjdjServiceCodes != null && bjdjServiceCodes.size() > 0){
					if(bjdjServiceCodes.size() < buyCount){
						result = "服务码数量不足,目前服务码数量为"+bjdjServiceCodes.size();
					}else{
						//实付金额
						Double paidAmount = Arith.mul(Double.valueOf(priceDataDictionary.getValue()), buyCount);
						Double sumAccount = customerAccountService.getSumMoney(channelCustomerInfoId);
						if(sumAccount < paidAmount){
							result = "账户金额不足,请充值!";
						}else{
							//优惠金额
							Double discountAmount = 0D;
							//消费积分
							Integer useIntegral = 0;
							OrderInfo orderInfo = new OrderInfo();
							orderInfo.setOrderId(DateUtil.parseCurrentDateToLongString("yyyyMMddHHmmss"));
							orderInfo.setGoodsPrice(Double.valueOf(priceDataDictionary.getValue()));
							orderInfo.setPayTime(new Date());
							orderInfo.setBuyCount(buyCount);
							orderInfo.setPayWay(payWay);
							orderInfo.setChannelCustomerInfo(get(ChannelCustomerInfo.class, channelCustomerInfoId));
							orderInfo.setPaidAmount(paidAmount);
							orderInfo.setDiscountAmount(discountAmount);
							orderInfo.setUseIntegral(useIntegral);
							this.persist(orderInfo);
							for(BjdjServiceCode b:bjdjServiceCodes){
								OrderInfoDetail orderInfoDetail = new OrderInfoDetail();
								orderInfoDetail.setBjdjServiceCode(b);
								orderInfoDetail.setOrderInfo(orderInfo);
								orderInfoDetailService.persist(orderInfoDetail);
								//改变服务码的状态为已使用
								//b.setState(systemDictionaryService.getUnderParentByName("service_code_status", "used"));
								b.setIsBind(true);
								bjdjServiceCodeService.merge(b);
								//添加购买记录
								bjdjServiceCodeOperationService.persist(b, systemDictionaryService
										.getUnderParentByName("operation", "used"),
										channelCustomerInfoId, orderInfo.getId(), orderInfoDetail.getId());
								result = "true";
							}
							//走充值金额里边扣除消费总计
							StringBuffer sb = new StringBuffer();
							sb.append(channelCustomerInfo.getLoginId());
							sb.append("在");
							sb.append(DateUtil.parseDateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
							sb.append("购买单价为");
							sb.append(priceDataDictionary.getValue());
							sb.append("元的便捷登机服务码");
							sb.append(buyCount);
							sb.append("个,总计");
							sb.append(paidAmount);
							sb.append("元,并优惠");
							sb.append(discountAmount);
							sb.append("元");
							customerAccountService.add(channelCustomerInfoId, 0,
									-paidAmount, -discountAmount, ChannelCustomerInfo.class.getSimpleName(),
									channelCustomerInfoId, sb.toString());
						}
					}
				}else{
					result = "服务码池子为空";
				}
			}else {
				result = "请绑定服务码池子类型";
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "购买服务码错误";
		}
	}

	@Override
	public Pagination queryAll(String channelCustomerInfoId, Integer pageSize)
			throws ServiceException {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append(" ");
			List<Object[]> objects = new ArrayList<Object[]>();
			if(GeneralUtil.isNotNull(channelCustomerInfoId)){
				sb.append("and s.channelCustomerInfo_id=?3 ");
				objects.add(new Object[]{3,channelCustomerInfoId});
			}
			return dbDAO.queryByPageModuleNew(getTableNameFromEntity(OrderInfo.class), deleteFlag, versionFlag,
					sb.toString(), objects, orderBy, PaginationContext.getOffset(), pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean addByEmployeeInfoZengLog(Integer orderType,
			String channelCustomerInfoId, Integer count,
			List<OrderInfoDetail> orInfoDetails) throws ServiceException {
		try {
			//获取便捷等级的价格
			SystemDictionary priceDataDictionary = systemDictionaryService.getByName("bjdj_price");
			//优惠金额
			Double discountAmount = 0D;
			//消费积分
			Integer useIntegral = 0;
//			Double paidAmount = Arith.mul(Double.valueOf(priceDataDictionary.getValue()),count);
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setOrderId(DateUtil.parseCurrentDateToLongString("yyyyMMddHHmmss"));
			orderInfo.setGoodsPrice(Double.valueOf(priceDataDictionary.getValue()));
			orderInfo.setPayTime(new Date());
			orderInfo.setOrderType(orderType);
			orderInfo.setBuyCount(count);
			orderInfo.setPayWay("赠送");
			orderInfo.setChannelCustomerInfo(get(ChannelCustomerInfo.class, channelCustomerInfoId));
			//赠送,金额为0
			//orderInfo.setPaidAmount(paidAmount);
			orderInfo.setPaidAmount(0D);
			orderInfo.setDiscountAmount(discountAmount);
			orderInfo.setUseIntegral(useIntegral);
			this.persist(orderInfo);
			for(OrderInfoDetail od:orInfoDetails){
				OrderInfoDetail orderInfoDetail = new OrderInfoDetail();
				orderInfoDetail.setOrderInfo(orderInfo);
				orderInfoDetail.setOrderType(orderType);
				orderInfoDetail.setBjdjServiceCode(od.getBjdjServiceCode());
				orderInfoDetailService.persist(orderInfoDetail);
				//添加购买记录或服务码变动记录
				bjdjServiceCodeOperationService.persist(bjdjServiceCodeService
						.queryById(BjdjServiceCode.class.getSimpleName(), od.getBjdjServiceCode().getId()), systemDictionaryService
						.getUnderParentByName("operation", "used"),
						channelCustomerInfoId, orderInfo.getId(), orderInfoDetail.getId());
				//这里由于是转赠,所以就不写进账户,也不进行账户的扣款处理
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public long queryAllBuyCount(String employee_id) throws ServiceException {
		List<ChannelCustomerInfo> infos = channelCustomerInfoService.queryAll(ChannelCustomerInfo.class);
		long count = 0;
		if(GeneralUtil.isNotNull(infos)){
			for(ChannelCustomerInfo info:infos){
				List<OrderInfo> orderInfos = null;
				if(GeneralUtil.isNotNull(employee_id)){
					orderInfos = dbDAO.executeJPQLForQueryEntity("select c from OrderInfo c,ChannelCustomerInfo cc where c.channelCustomerInfo.id = cc.id and cc.id = ? and cc.employeeInfo_id = ?", info.getId(),employee_id);
				}
				orderInfos = dbDAO.executeJPQLForQueryEntity("select c from " + OrderInfo.class.getName() + " c where c.channelCustomerInfo.id = ?", info.getId());
				if(GeneralUtil.isNotNull(orderInfos)){
					for(OrderInfo orderInfo:orderInfos){
						count += orderInfo.getBuyCount();
					}
				}
			}
		}
		return count;
	}

	@Override
	public double queryAllPaidAmount() throws ServiceException {
		List<ChannelCustomerInfo> infos = channelCustomerInfoService.queryAll(ChannelCustomerInfo.class);
		double paidAmount = 0;
		if(GeneralUtil.isNotNull(infos)){
			for(ChannelCustomerInfo info:infos){
				List<OrderInfo> orderInfos = dbDAO.executeJPQLForQueryEntity("select c from " + OrderInfo.class.getName() + " c where c.channelCustomerInfo.id = ?", info.getId());
				if(GeneralUtil.isNotNull(orderInfos)){
					for(OrderInfo orderInfo:orderInfos){
						paidAmount += orderInfo.getPaidAmount();
					}
				}
			}
		}
		return paidAmount;
	}

	@Override
	public long queryByChannelCustomerInfo_id(String channelCustomerInfo_id,Date startDate,Date endDate)
			throws ServiceException {
		endDate = new Date(endDate.getTime() + 24 * 60 * 60 * 1000 - 1);
		List<OrderInfo> list = dbDAO.executeJPQLForQueryEntity("select c from " + OrderInfo.class.getName() + " c where c.channelCustomerInfo.id = ? and c.status.createTime >= ? and c.status.createTime <= ?", channelCustomerInfo_id ,startDate , endDate);
		long count = 0;
		if(GeneralUtil.isNotNull(list)){
			for(OrderInfo info:list){
				count += info.getBuyCount();
			}
		}
		return count;
	}

	@Override
	public List<OrderInfo> queryAllByCustomerInfo_id(
			String channelCustomerInfo_id) throws ServiceException {
		List<OrderInfo> list = dbDAO.executeJPQLForQueryEntity("select c from " + OrderInfo.class.getName() + " c where c.channelCustomerInfo.id = ?", channelCustomerInfo_id);
		return list;
	}

	@Override
	public long queryAllBjdjServiceCodeAllReadyActive(
			String channelCustomerInfo_id,Date startDate,Date endDate) throws ServiceException {
		endDate = new Date(endDate.getTime() + 24 * 60 * 60 * 1000 - 1);
		List<Object> list = dbDAO.executeJPQLForQuery("select count(sc) from BjdjServiceCode sc, "
											+ "OrderInfoDetail o , "
											+ "OrderInfo oo ,"
											+ "ChannelCustomerInfo c,"
											+ "SystemDictionary s,"
											+ "BjdjAppointment b"
											+ " where sc.id = o.bjdjServiceCode_id and oo.channelCustomerInfo_id = c.id "
											+ " and o.orderInfo_id = oo.id and sc.state = s.id "
											+ " and b.serviceCode = sc.id "
											+ " and s.name = 'activated' and c.id = ? "
											+ " and b.time >= ? and b.time <= ?",channelCustomerInfo_id,startDate,endDate);
		long count = 0 ;
		if(GeneralUtil.isNotNull(list)){
			count = (Long)list.get(0);
		}
		
		return count;
	}

	@Override
	public long queryAllBjdjServiceCodeAllReadyValidation(
			String channelCustomerInfo_id,Date startDate,Date endDate) throws ServiceException {
		endDate = new Date(endDate.getTime() + 24 * 60 * 60 * 1000 - 1);
		List<Object> list = dbDAO.executeJPQLForQuery("select count(sc) from BjdjServiceCode sc, "
				+ "OrderInfoDetail o , "
				+ "OrderInfo oo ,"
				+ "ChannelCustomerInfo c,"
				+ "SystemDictionary s,"
				+ "BjdjValidation b"
				+ " where sc.id = o.bjdjServiceCode_id and oo.channelCustomerInfo_id = c.id "
				+ " and o.orderInfo_id = oo.id and sc.state = s.id " +
				" and s.name = 'validated' and c.id = ?"
				+ " and b.time >= ? and b.time <= ?",channelCustomerInfo_id,startDate,endDate);
		long count = 0;
		if(GeneralUtil.isNotNull(list)){
			count = (Long)list.get(0);
		}
		return count;
	}

}