package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.constants.JQueryUploadConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.ScenicSpots;
import com.ticket.service.IScenicSpotsService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * 旅游景点业务接口实现类
 * @ClassName: IScenicSpotsService   
 * @Description: 提供旅游景点操作的增删改查   
 * @author HiSay  
 * @date 2016-09-30 09:54:17
 *
 */
public class ScenicSpotsServiceImpl extends BaseServiceImpl<ScenicSpots, String> implements IScenicSpotsService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(ScenicSpotsServiceImpl.class);

	@Override
	public boolean persist(String name,String introduce,String detail,String picture,String versionFlag) throws ServiceException {
		ScenicSpots scenicSpots = new ScenicSpots();
		scenicSpots.setName(DecoderUtil.UtfDecoder(name));
		scenicSpots.setIntroduce(DecoderUtil.UtfDecoder(introduce));
		scenicSpots.setDetail(DecoderUtil.UtfDecoder(detail));
		CommonEntity status = scenicSpots.getStatus();
		status.setVersionFlag(versionFlag);
		scenicSpots.setStatus(status);
		dbDAO.persist(scenicSpots);
		if(GeneralUtil.isNotNull(picture)){
			this.addAnnex(scenicSpots, scenicSpots.getId(), picture, JQueryUploadConstants.PICTURE_TYPE_DEFAULT, versionFlag);
		}
		return true;
	}
	
	@Override
	public boolean merge(String id, String name,String introduce,String detail, String picture,String versionFlag) throws ServiceException {
		ScenicSpots scenicSpots = dbDAO.queryById(this.getTableNameFromEntity(ScenicSpots.class), id);
		scenicSpots.setName(DecoderUtil.UtfDecoder(name));
		scenicSpots.setIntroduce(introduce);
		scenicSpots.setDetail(DecoderUtil.UtfDecoder(detail));
		CommonEntity status = scenicSpots.getStatus();
		status.setVersionFlag(versionFlag);
		scenicSpots.setStatus(status);
		dbDAO.merge(scenicSpots);
		if(GeneralUtil.isNotNull(picture)){
			this.addAnnex(scenicSpots, scenicSpots.getId(), picture, JQueryUploadConstants.PICTURE_TYPE_DEFAULT, versionFlag);
		}
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		ScenicSpots scenicSpots = dbDAO.queryById(this.getTableNameFromEntity(ScenicSpots.class), id);
		dbDAO.remove(scenicSpots);
		return true;
	}

	@Override
	public boolean hotEntity(String id, Integer hot) throws ServiceException {
		ScenicSpots scenicSpots = this.queryById(ScenicSpots.class.getName(), id);
		if(scenicSpots != null){
			CommonEntity status = scenicSpots.getStatus();
			status.setHot(hot);
			this.merge(scenicSpots);
		}
		return true;
	}

	@Override
	public List<ScenicSpots> queryHot() throws ServiceException {
		List<ScenicSpots> list = dbDAO.executeJPQLForQuery("select c from " + ScenicSpots.class.getName() + " c where c.status.hot = 1 and c.status.deleteFlag = 0", ScenicSpots.class);
		return list;
	}

}