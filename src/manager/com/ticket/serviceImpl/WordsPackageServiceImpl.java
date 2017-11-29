package com.ticket.serviceImpl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.WordsPackage;
import com.ticket.service.IWordsPackageService;
import com.ticket.util.DecoderUtil;

/**
 * 关键词包业务接口实现类
 * @ClassName: IWordsPackageService   
 * @Description: 提供关键词包操作的增删改查   
 * @author HiSay  
 * @date 2016-09-28 15:44:19
 *
 */
public class WordsPackageServiceImpl extends BaseServiceImpl<WordsPackage, String> implements IWordsPackageService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(WordsPackageServiceImpl.class);

	@Override
	public boolean persist(String name,String keywords, String versionFlag) throws ServiceException {
		WordsPackage wordsPackage = new WordsPackage();
		wordsPackage.setName(DecoderUtil.UtfDecoder(name));
		wordsPackage.setKeywords(DecoderUtil.UtfDecoder(keywords));
		dbDAO.persist(wordsPackage);
		return true;
	}
	
	@Override
	public boolean merge(String name,String keywords, String versionFlag) throws ServiceException {
		
		WordsPackage wordsPackage = get();
		if(wordsPackage == null){
			
			return persist(name, keywords, versionFlag);
		}
		
		wordsPackage.setName(DecoderUtil.UtfDecoder(name));
		wordsPackage.setKeywords(DecoderUtil.UtfDecoder(keywords));
		dbDAO.merge(wordsPackage);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		WordsPackage wordsPackage = dbDAO.queryById(this.getTableNameFromEntity(WordsPackage.class), id);
		dbDAO.remove(wordsPackage);
		return true;
	}

	public WordsPackage get(){
		
		WordsPackage words = dbDAO.executeJPQLForQuerySingle(
				"select s from " + WordsPackage.class.getName() + " s", WordsPackage.class);
		return words;
	}
}