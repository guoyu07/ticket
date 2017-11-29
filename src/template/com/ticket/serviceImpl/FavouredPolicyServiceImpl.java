package com.ticket.serviceImpl;


import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.ApplayClass;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.FavouredPolicy;
import com.ticket.service.IFavouredPolicyService;
import com.ticket.util.DecoderUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 优惠政策业务接口实现类
 * @ClassName: IFavouredPolicyService   
 * @Description: 提供优惠政策操作的增删改查   
 * @author HiSay  
 * @date 2015-11-14 14:34:06
 *
 */
public class FavouredPolicyServiceImpl extends BaseServiceImpl<FavouredPolicy, String> implements IFavouredPolicyService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(FavouredPolicyServiceImpl.class);
	
	@Override
	public boolean merge(String id, String favouredPolicyNo,String name,Double rechargeAmount,Double discountRate,String discountWay,String descript,Integer grade,Double giftAmount, String versionFlag) throws ServiceException {
		FavouredPolicy favouredPolicy = dbDAO.queryById(this.getTableNameFromEntity(FavouredPolicy.class), id);
		favouredPolicy.setFavouredPolicyNo(DecoderUtil.UtfDecoder(favouredPolicyNo));
		favouredPolicy.setName(DecoderUtil.UtfDecoder(name));
		favouredPolicy.setRechargeAmount(rechargeAmount);
		favouredPolicy.setDiscountRate(discountRate);
		favouredPolicy.setDiscountWay(discountWay);
		favouredPolicy.setGrade(grade);
		favouredPolicy.setDescript(DecoderUtil.UtfDecoder(descript));
		CommonEntity status = favouredPolicy.getStatus();
		status.setVersionFlag(versionFlag);
		favouredPolicy.setStatus(status);
		dbDAO.merge(favouredPolicy);
		return true;
	}

	@Override
	public boolean persist(String favouredPolicyNo,String name,Double rechargeAmount,Double discountRate,String discountWay,String descript,Integer grade, Double giftAmount,String versionFlag) throws ServiceException {
		FavouredPolicy favouredPolicy = new FavouredPolicy();
		favouredPolicy.setFavouredPolicyNo(DecoderUtil.UtfDecoder(favouredPolicyNo));
		favouredPolicy.setName(DecoderUtil.UtfDecoder(name));
		favouredPolicy.setRechargeAmount(rechargeAmount);
		favouredPolicy.setDiscountRate(discountRate);
		favouredPolicy.setDiscountWay(discountWay);
		favouredPolicy.setGrade(grade);
		favouredPolicy.setDescript(DecoderUtil.UtfDecoder(descript));
		CommonEntity status = favouredPolicy.getStatus();
		status.setVersionFlag(versionFlag);
		favouredPolicy.setStatus(status);
		dbDAO.persist(favouredPolicy);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		FavouredPolicy favouredPolicy = dbDAO.queryById(this.getTableNameFromEntity(FavouredPolicy.class), id);
		dbDAO.remove(favouredPolicy);
		return true;
	}
	
	@Override
	public List<FavouredPolicy> queryList(String versionFlag)
			throws ServiceException {
		return dbDAO.queryByList(FavouredPolicy.class.getSimpleName(), deleteFlag, versionFlag, null, null, orderBy, null);
	}

	@Override
	public List<FavouredPolicy> queryByCustomerId(String customerId)
			throws ServiceException {
		List<FavouredPolicy> list = dbDAO.executeJPQLForQueryEntity("select c from FavouredPolicy c where c.discountWay = ?", customerId);
		return list;
	}
}