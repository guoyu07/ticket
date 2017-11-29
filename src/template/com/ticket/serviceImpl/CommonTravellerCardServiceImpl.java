package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonTraveller;
import com.ticket.pojo.CommonTravellerCard;
import com.ticket.pojo.Member;
import com.ticket.service.ICommonTravellerCardService;
import com.ticket.util.DecoderUtil;

/**
 * 常用旅客证件业务接口实现类
 * @ClassName: ICommonTravellerCardService   
 * @Description: 提供常用旅客证件操作的增删改查   
 * @author HiSay  
 * @date 2016-01-07 15:20:41
 *
 */
public class CommonTravellerCardServiceImpl extends BaseServiceImpl<CommonTravellerCard, String> implements ICommonTravellerCardService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(CommonTravellerCardServiceImpl.class);
	
	@Override
	public boolean merge(String id, String cardType,String cardValue,String parentId, String versionFlag) throws ServiceException {
		CommonTravellerCard commonTravellerCard = dbDAO.queryById(this.getTableNameFromEntity(CommonTravellerCard.class), id);
		commonTravellerCard.setCardType(DecoderUtil.UtfDecoder(cardType));
		commonTravellerCard.setCardValue(DecoderUtil.UtfDecoder(cardValue));
		CommonTraveller traverller = get(CommonTraveller.class, parentId);
		commonTravellerCard.setParent(traverller);
		dbDAO.merge(commonTravellerCard);
		return true;
	}

	@Override
	public boolean persist(String cardType,String cardValue,String parentId, String versionFlag) throws ServiceException {
		CommonTravellerCard commonTravellerCard = new CommonTravellerCard();
		commonTravellerCard.setCardType(DecoderUtil.UtfDecoder(cardType));
		commonTravellerCard.setCardValue(DecoderUtil.UtfDecoder(cardValue));
		CommonTraveller traverller = get(CommonTraveller.class, parentId);
		commonTravellerCard.setParent(traverller);
		dbDAO.persist(commonTravellerCard);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		CommonTravellerCard commonTravellerCard = dbDAO.queryById(this.getTableNameFromEntity(CommonTravellerCard.class), id);
		dbDAO.remove(commonTravellerCard);
		return true;
	}
	
	public List<CommonTravellerCard> findByParentId(String parentId) throws ServiceException{
		List<CommonTravellerCard> list = dbDAO.executeJPQLForQuery("select c from " + CommonTravellerCard.class.getName() + " c where c.parent.id = ?",CommonTravellerCard.class, parentId);
		return list;
	}

	@Override
	public CommonTravellerCard findByIdCardValue(Member member, String cardValue) {
		CommonTravellerCard object = dbDAO.executeJPQLForQuerySingle(
				"select c from " + CommonTravellerCard.class.getName() + " c where c.cardValue = ? and c.parent.memberId=?", 
				CommonTravellerCard.class, cardValue, member.getId());
		return object;
	}

	@Override
	public CommonTravellerCard findByParentIdAndIdCard(String parentId,
			String idCard) {
		CommonTravellerCard commonTravellerCard = dbDAO.executeJPQLForQuerySingle("select c from " + CommonTravellerCard.class.getName() + " c where c.parent.id = ? and c.cardValue = ?",CommonTravellerCard.class, parentId,idCard);
		return commonTravellerCard;
	}

	@Override
	public boolean removeByParentId(String parentId) {
		List<Object> list = dbDAO.executeJPQLForQuery("select c from " + CommonTravellerCard.class.getName() + " c where c.parent.memberId = ?", parentId);
		for(Object o : list){
			CommonTravellerCard c = (CommonTravellerCard)o;
			dbDAO.remove(c);
		}
		return true;
	}
}