package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.enumer.SearchType;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.CountSearch;
import com.ticket.pojo.Member;
import com.ticket.service.ICountSearchService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * 统计搜索词表业务接口实现类
 * @ClassName: ICountSearchService   
 * @Description: 提供统计搜索词表操作的增删改查   
 * @author HiSay  
 * @date 2016-03-10 15:49:15
 *
 */
public class CountSearchServiceImpl extends BaseServiceImpl<CountSearch, String> implements ICountSearchService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(CountSearchServiceImpl.class);

	@Override
	public CountSearch persist(String searchWord,String keyword,String negative, String ip, Member member, SearchType type) throws ServiceException {
		
		CountSearch countSearch = new CountSearch();
		countSearch.setSearchWord(DecoderUtil.UtfDecoder(searchWord));
		countSearch.setKeyword(DecoderUtil.UtfDecoder(keyword));
		countSearch.setNegative(DecoderUtil.UtfDecoder(negative));
		countSearch.setIp(ip);
		countSearch.setMember(member);
		countSearch.setType(type);
		dbDAO.persist(countSearch);
		return countSearch;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		CountSearch countSearch = dbDAO.queryById(this.getTableNameFromEntity(CountSearch.class), id);
		dbDAO.remove(countSearch);
		return true;
	}

	@Override
	public boolean merge(String id, String goUrl, String goUrlHref, String versionFlag) throws ServiceException {
		CountSearch countSearch = dbDAO.queryById(this.getTableNameFromEntity(CountSearch.class), id);
		countSearch.setGoUrl(DecoderUtil.UtfDecoder(goUrl));
		countSearch.setGoUrlHref(DecoderUtil.UtfDecoder(goUrlHref));
		CommonEntity status = countSearch.getStatus();
		status.setVersionFlag(versionFlag);
		countSearch.setStatus(status);
		dbDAO.merge(countSearch);
		return true;
	}

	@Override
	public boolean batchRealDelete(String idsValue, String versionFlag)
			throws ServiceException {
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(CountSearch.class.getSimpleName(),idsValue,null);
		return true;
	}

	@Override
	public List<CountSearch> queryAllOrderByCreateTime()
			throws ServiceException {
		List<CountSearch> countSearchs = dbDAO.executeJPQLForQuery("select c from " + CountSearch.class.getName() + " c where c.status.deleteFlag=0 order by c.status.createTime desc",CountSearch.class);
		return countSearchs;
	}
}