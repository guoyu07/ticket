package com.ticket.serviceImpl;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.CommonTraveller;
import com.ticket.pojo.CommonTravellerCard;
import com.ticket.pojo.Member;
import com.ticket.service.ICommonTravellerCardService;
import com.ticket.service.ICommonTravellerService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.util.DateUtil;
import com.ticket.util.DecoderUtil;
import com.ticket.util.IDCardUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 常用旅客业务接口实现类
 * @ClassName: ICommonTravellerService   
 * @Description: 提供常用旅客操作的增删改查   
 * @author HiSay  
 * @date 2016-01-05 16:30:37
 *
 */
public class CommonTravellerServiceImpl extends BaseServiceImpl<CommonTraveller, String> implements ICommonTravellerService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(CommonTravellerServiceImpl.class);
	@Resource
	ISystemDictionaryService dictionaryService;
	@Resource
	ICommonTravellerCardService cardService;
	
	@Override
	public boolean merge(String id, String ChaName,String eName,String phone,String gender,String birth,String memberId, String versionFlag) throws ServiceException {
		CommonTraveller commonTraveller = dbDAO.queryById(this.getTableNameFromEntity(CommonTraveller.class), id);
		commonTraveller.setChaName(DecoderUtil.UtfDecoder(ChaName));
		commonTraveller.setEngName(DecoderUtil.UtfDecoder(eName));
		commonTraveller.setPhone(DecoderUtil.UtfDecoder(phone));
		commonTraveller.setGender(DecoderUtil.UtfDecoder(gender));
		commonTraveller.setBirth(DecoderUtil.UtfDecoder(birth));
		commonTraveller.setMemberId(DecoderUtil.UtfDecoder(memberId));
		CommonEntity status = commonTraveller.getStatus();
		status.setVersionFlag(versionFlag);
		commonTraveller.setStatus(status);
		dbDAO.merge(commonTraveller);
		return true;
	}

	@Override
	public CommonTraveller persist(String ChaName,String eName,String phone,String gender,String birth,String memberId, String versionFlag) throws ServiceException {
		CommonTraveller commonTraveller = new CommonTraveller();
		commonTraveller.setChaName(DecoderUtil.UtfDecoder(ChaName));
		commonTraveller.setEngName(DecoderUtil.UtfDecoder(eName));
		commonTraveller.setPhone(DecoderUtil.UtfDecoder(phone));
		commonTraveller.setGender(DecoderUtil.UtfDecoder(gender));
		commonTraveller.setBirth(DecoderUtil.UtfDecoder(birth));
		commonTraveller.setMemberId(DecoderUtil.UtfDecoder(memberId));
		CommonEntity status = commonTraveller.getStatus();
		status.setVersionFlag(versionFlag);
		commonTraveller.setStatus(status);
		dbDAO.persist(commonTraveller);
		return commonTraveller;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		CommonTraveller commonTraveller = dbDAO.queryById(this.getTableNameFromEntity(CommonTraveller.class), id);
		dbDAO.remove(commonTraveller);
		return true;
	}

	@Override
	public List<CommonTraveller> getByMemberId(String memberId)
			throws ServiceException {
		List<CommonTraveller> list = dbDAO.executeJPQLForQuery("select c from " + CommonTraveller.class.getName() + " c where c.memberId = ? order by c.status.createTime desc", CommonTraveller.class, memberId);
		return list;
	}

	@Override
	public CommonTraveller findById(String id,String memberId) {
		CommonTraveller traveller = dbDAO.executeJPQLForQuerySingle("select c from " + CommonTraveller.class.getName() + " c where c.id = ? and c.memberId = ?", CommonTraveller.class, id, memberId);
		return traveller;
	}

	@Override
	public CommonTraveller findByMemberIdAndName(String memberId, String name) {
//		String regChaName = "[\u4e00-\u9fa5]+";
		String regEngName = "[A-Za-z ]+";
//		Pattern p1 = Pattern.compile(regChaName);
		Pattern p2 = Pattern.compile(regEngName);
//		Matcher m1 = p1.matcher(name);
		Matcher m2 = p2.matcher(name);
		CommonTraveller traveller = null;
//		if(m1.matches()){
//			object = dbDAO.executeJPQLForQuerySingle("select c from " + CommonTraveller.class.getName() + " c where c.memberId = ? and c.chaName = ?", memberId,name);
//		}
		if(m2.matches()){
			traveller = dbDAO.executeJPQLForQuerySingle("select c from " + CommonTraveller.class.getName() + " c where c.memberId = ? and c.engName = ?", CommonTraveller.class, memberId,name);
		}else{
			traveller = dbDAO.executeJPQLForQuerySingle("select c from " + CommonTraveller.class.getName() + " c where c.memberId = ? and c.chaName = ?", CommonTraveller.class, memberId,name);
		}
		return traveller;
	}

	@Override
	public void persistOrNot(Member member, String name, String idCard, String cardType) {
		
		CommonTravellerCard card = cardService.findByIdCardValue(member, idCard);
		if(card == null){
			
			cardType = cardType.trim();
			idCard = idCard.trim();
			if("身份证".equals(cardType) && idCard.length() == 18){
				
				String chaName = null;
				String engName = null;
				String regEngName = "[A-Za-z ]+";
				if(name.matches(regEngName)){
					
					engName = name;
				}else{
					
					chaName = name;
				}
				try {
					CommonTraveller traveller = persist(chaName, engName, null, IDCardUtil.sex(idCard), 
							DateUtil.formatDateToSimpleString(IDCardUtil.getBirth(idCard).getTime()), member.getId(), versionFlag);
					cardService.persist(cardType, idCard, traveller.getId(), versionFlag);
				} catch (ServiceException e) {
					e.printStackTrace();
				}
			}else{
				
				String chaName = null;
				String engName = null;
				String regEngName = "[A-Za-z ]+";
				if(name.matches(regEngName)){
					
					engName = name;
				}else{
					
					chaName = name;
				}
				try {
					CommonTraveller traveller = persist(chaName, engName, null, null, null, member.getId(), versionFlag);
					cardService.persist(cardType, idCard, traveller.getId(), versionFlag);
				} catch (ServiceException e) {
					e.printStackTrace();
				}
			}
		}
	}
}