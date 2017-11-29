package com.ticket.service;

import java.util.List;

import com.ticket.pojo.BjdjCommentKeywordStatistics;

/**
 * @Description：评论关键词统计的服务层
 * @author：涂有
 * @date 2015年12月19日 上午11:40:48
 */
public interface IBjdjCommentKeywordStatisticsService extends IBaseService<BjdjCommentKeywordStatistics, String>  {

	/**
	 * @Description：保存一个关键词统计对象
	 * @date 2015年12月19日 上午11:49:37
	 * @param keyword
	 * @param count
	 */
	void persist(String keyword, Integer count);
	
	/**
	 * @Description：删除一个关键词的统计
	 * @date 2015年12月19日 上午11:49:37
	 * @param keyword
	 * @param count
	 */
	void remove(String keyword);
	
	/**
	 * @Description：根据关键词得到对象
	 * @date 2015年12月19日 上午11:46:22
	 * @param keyword
	 * @return
	 */
	BjdjCommentKeywordStatistics getByKeyword(String keyword);
	
	/**
	 * @Description：关键词统计次数加count次
	 * @date 2015年12月19日 上午11:47:47
	 * @param keyword
	 * @param count
	 */
	void addCount(String keyword, Integer count);

	/**
	 * @Description：增加统计的次数（如果统计对象不在，则保存）
	 * @date 2015年12月19日 下午2:59:27
	 * @param keyword
	 * @param count
	 * @return
	 */
	BjdjCommentKeywordStatistics addOrPersist(String keyword, Integer count);
	
	/**
	 * @Description：关键词统计次数减count次
	 * @date 2015年12月19日 上午11:47:47
	 * @param keyword
	 * @param count
	 */
	void subCountOrRemove(String keyword, Integer count);
	
	/**
	 * @Description：增加一段内容，并统计其中关键词
	 * @date 2015年12月19日 下午3:05:02
	 * @param content
	 */
	void addContent(String content);
	
	/**
	 * @Description：删除一段内容，并减少其中关键词的统计
	 * @date 2015年12月19日 下午3:05:47
	 * @param content
	 */
	void removeContent(String content);

	/**
	 * @Description：得到统计次数最大的count个关键词
	 * @date 2015年12月19日 下午3:40:10
	 * @param count
	 */
	List<BjdjCommentKeywordStatistics> getMax(int count);
}
