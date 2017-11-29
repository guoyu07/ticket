package com.ticket.serviceImpl;

import java.util.List;

import com.ticket.pojo.BjdjCommentKeywordStatistics;
import com.ticket.pojo.CommonEntity;
import com.ticket.service.IBjdjCommentKeywordStatisticsService;

/**
 * @Description：评论关键词统计的服务层
 * @author：涂有
 * @date 2015年12月19日 上午11:57:18
 */
public class BjdjCommentKeywordStatisticsServiceImpl 
	extends BaseServiceImpl<BjdjCommentKeywordStatistics, String> 
	implements IBjdjCommentKeywordStatisticsService {

	public static final String separate = "[\\s|`|~|!|@|#|$|%|^|&|*|(|)|_|+|-|=|\\[|\\]|;|'|<|>|?|/|.|,|}|{|\"|:|·|！|￥|……|（|）|【|】|；|：|“|”|’|‘|，|。|？|、]|《|》";
	
	@Override
	public void persist(String keyword, Integer count) {

		BjdjCommentKeywordStatistics statistics = new BjdjCommentKeywordStatistics();
		statistics.setKeyword(keyword);
		if(count == null || count <= 0){
			
			statistics.setCount(1);
		}else{
			
			statistics.setCount(count);
		}
		CommonEntity status = statistics.getStatus();
		status.setVersionFlag(versionFlag);
		statistics.setStatus(status);
		dbDAO.persist(statistics);
	}

	@Override
	public void remove(String keyword) {

		dbDAO.executeJPQLForUpdate(
				"delete from " + BjdjCommentKeywordStatistics.class.getName() + " where keyword=?", 
				keyword);
	}

	@Override
	public BjdjCommentKeywordStatistics getByKeyword(String keyword) {
		
		BjdjCommentKeywordStatistics statistics = dbDAO.executeJPQLForQuerySingle(
				"select s from " + BjdjCommentKeywordStatistics.class.getName() + " s where keyword=?",
				BjdjCommentKeywordStatistics.class, 
				keyword);
		return statistics;
	}

	@Override
	public void addCount(String keyword, Integer count) {

		BjdjCommentKeywordStatistics statistics = getByKeyword(keyword);
		statistics.setCount(statistics.getCount() + count);
		dbDAO.merge(statistics);
	}

	@Override
	public void subCountOrRemove(String keyword, Integer count) {

		BjdjCommentKeywordStatistics statistics = getByKeyword(keyword);
		statistics.setCount(statistics.getCount() - count);
		//如果小于零，则删除
		if(statistics.getCount() <= 0){
			
			dbDAO.remove(statistics);
		}else{ //不小于零，则修改统计次数
			
			dbDAO.merge(statistics);
		}
	}

	@Override
	public BjdjCommentKeywordStatistics addOrPersist(String keyword,
			Integer count) {
		
		BjdjCommentKeywordStatistics keywordObj = getByKeyword(keyword);
		if(keywordObj == null){
			
			persist(keyword, 0);
		}else{
			
			keywordObj.setCount(keywordObj.getCount() + count);
			merge(keywordObj);
		}
		return keywordObj;
	}

	@Override
	public void addContent(String content) {
		
		String[] keywords = content.split(separate);
		for(String keyword : keywords){
			
			keyword = keyword.trim();
			if(keyword.length() < 10){
				
				addOrPersist(keyword.trim(), 1);
			}
		}
	}

	@Override
	public void removeContent(String content) {
		
		String[] keywords = content.split(separate);
		for(String keyword : keywords){
			
			subCountOrRemove(keyword, 1);
		}
	}

	@Override
	public List<BjdjCommentKeywordStatistics> getMax(int count) {
		
		List<BjdjCommentKeywordStatistics> list = dbDAO.executeJPQLForQuery(
				"select s from " + BjdjCommentKeywordStatistics.class.getName() + " s order by s.count desc", 
				BjdjCommentKeywordStatistics.class, 
				0, 
				count);
		return list;
	}
}
