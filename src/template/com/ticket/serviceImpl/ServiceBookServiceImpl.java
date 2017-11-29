package com.ticket.serviceImpl;


import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.ServiceBook;
import com.ticket.service.IServiceBookService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.PaginationContext;

/**
 * 服务订单业务接口实现类
 * @ClassName: IServiceBookService   
 * @Description: 提供服务订单操作的增删改查   
 * @author HiSay  
 * @date 2015-10-15 12:56:11
 *
 */
public class ServiceBookServiceImpl extends BaseServiceImpl<ServiceBook, String> implements IServiceBookService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(ServiceBookServiceImpl.class);
	
	@Override
	public boolean merge(String id, String member_id,String idCard,String phone,String flightNumber,Integer bookAmount,Date useTime,String serviceKey,Integer payStatus,Date payTime,Integer payWay,String weChatTransld,String alipayName,String alipayTransld,String bankName, String versionFlag) throws ServiceException {
		ServiceBook serviceBook = dbDAO.queryById(this.getTableNameFromEntity(ServiceBook.class), id);
		serviceBook.setMember_id(DecoderUtil.UtfDecoder(member_id));
		serviceBook.setIdCard(DecoderUtil.UtfDecoder(idCard));
		serviceBook.setPhone(DecoderUtil.UtfDecoder(phone));
		serviceBook.setFlightNumber(DecoderUtil.UtfDecoder(flightNumber));
		serviceBook.setBookAmount(bookAmount);
		serviceBook.setUseTime(useTime);
		serviceBook.setServiceKey(DecoderUtil.UtfDecoder(serviceKey));
		serviceBook.setPayStatus(payStatus);
		serviceBook.setPayTime(payTime);
		serviceBook.setPayWay(payWay);
		serviceBook.setWeChatTransld(DecoderUtil.UtfDecoder(weChatTransld));
		serviceBook.setAlipayName(DecoderUtil.UtfDecoder(alipayName));
		serviceBook.setAlipayTransld(DecoderUtil.UtfDecoder(alipayTransld));
		serviceBook.setBankName(DecoderUtil.UtfDecoder(bankName));
		CommonEntity status = serviceBook.getStatus();
		status.setVersionFlag(versionFlag);
		serviceBook.setStatus(status);
		dbDAO.merge(serviceBook);
		return true;
	}

	@Override
	public boolean persist(String member_id,String idCard,String phone,String flightNumber,Integer bookAmount,Date useTime,String serviceKey,Integer payStatus,Date payTime,Integer payWay,String weChatTransld,String alipayName,String alipayTransld,String bankName, String versionFlag) throws ServiceException {
		ServiceBook serviceBook = new ServiceBook();
		serviceBook.setMember_id(DecoderUtil.UtfDecoder(member_id));
		serviceBook.setIdCard(DecoderUtil.UtfDecoder(idCard));
		serviceBook.setPhone(DecoderUtil.UtfDecoder(phone));
		serviceBook.setFlightNumber(DecoderUtil.UtfDecoder(flightNumber));
		serviceBook.setBookAmount(bookAmount);
		serviceBook.setUseTime(useTime);
		serviceBook.setServiceKey(DecoderUtil.UtfDecoder(serviceKey));
		serviceBook.setPayStatus(payStatus);
		serviceBook.setPayTime(payTime);
		serviceBook.setPayWay(payWay);
		serviceBook.setWeChatTransld(DecoderUtil.UtfDecoder(weChatTransld));
		serviceBook.setAlipayName(DecoderUtil.UtfDecoder(alipayName));
		serviceBook.setAlipayTransld(DecoderUtil.UtfDecoder(alipayTransld));
		serviceBook.setBankName(DecoderUtil.UtfDecoder(bankName));
		CommonEntity status = serviceBook.getStatus();
		status.setVersionFlag(versionFlag);
		serviceBook.setStatus(status);
		dbDAO.persist(serviceBook);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		ServiceBook serviceBook = dbDAO.queryById(this.getTableNameFromEntity(ServiceBook.class), id);
		dbDAO.remove(serviceBook);
		return true;
	}

	@Override
	public boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException {
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(ServiceBook.class.getSimpleName(), idsValue, null);
		return true;
	}

	@Override
	public Integer bookServiceSuc(Integer quantity,String versionFlag) throws ServiceException {
		return quantity - 1;
	}

	@Override
	public Integer cancelServiceSuc(Integer quantity,String versionFlag) throws ServiceException {
		return quantity + 1;
	}

	@Override
	public Integer completeService(Integer quantity,String versionFlag) throws ServiceException {
		return quantity + 1;
	}

	@Override
	public boolean ifTheSeatEnough(Integer totalSeat, Integer seatbeenBookOnline, Integer seatbeenBookUnderline)
			throws ServiceException {
		Integer seatQuantity = totalSeat-seatbeenBookOnline - seatbeenBookUnderline;
		if(seatQuantity > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Pagination queryPageModuleByKeyword(String keyword, int pageSize, String versionFlag) 
			throws ServiceException {
			keyword = DecoderUtil.UtfDecoder(keyword);
			return dbDAO.queryByPageModule(ServiceBook.class.getSimpleName(), deleteFlag, versionFlag, 
					"and s.phone like ?3", new Object[]{"%"+keyword+"%"}, orderBy, PaginationContext.getOffset(), pageSize);
	}

	
}