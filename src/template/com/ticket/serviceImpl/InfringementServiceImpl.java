package com.ticket.serviceImpl;


import java.util.ArrayList;
import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.Infringement;
import com.ticket.service.IInfringementService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 内部员工罚单业务接口实现类
 * @ClassName: IInfringementService   
 * @Description: 提供内部员工罚单操作的增删改查   
 * @author HiSay  
 * @date 2016-03-04 19:58:20
 *
 */
public class InfringementServiceImpl extends BaseServiceImpl<Infringement, String> implements IInfringementService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(InfringementServiceImpl.class);
	
	@Override
	public boolean merge(String id, String time,String inspectName,String unitName,String illegalMatter,String rules,String rectificationOpinion,String scenePhoto,String numberId,String illegalName,String illegalCard, String versionFlag) throws ServiceException {
		Infringement infringement = dbDAO.queryById(this.getTableNameFromEntity(Infringement.class), id);
		infringement.setTime(DecoderUtil.UtfDecoder(time));
		infringement.setInspectName(DecoderUtil.UtfDecoder(inspectName));
		infringement.setUnitName(DecoderUtil.UtfDecoder(unitName));
		infringement.setIllegalMatter(DecoderUtil.UtfDecoder(illegalMatter));
		infringement.setRules(DecoderUtil.UtfDecoder(rules));
		infringement.setRectificationOpinion(DecoderUtil.UtfDecoder(rectificationOpinion));
		infringement.setScenePhoto(DecoderUtil.UtfDecoder(scenePhoto));
		infringement.setNumberId(DecoderUtil.UtfDecoder(numberId));
		infringement.setIllegalName(DecoderUtil.UtfDecoder(illegalName));
		infringement.setIllegalCard(DecoderUtil.UtfDecoder(illegalCard));
		CommonEntity status = infringement.getStatus();
		status.setVersionFlag(versionFlag);
		infringement.setStatus(status);
		dbDAO.merge(infringement);
		return true;
	}

	@Override
	public boolean persist(String time,String inspectName,String unitName,String illegalMatter,String rules,String rectificationOpinion,String scenePhoto,String numberId,String illegalName,String illegalCard, String versionFlag) throws ServiceException {
		Infringement infringement = new Infringement();
		infringement.setTime(DecoderUtil.UtfDecoder(time));
		infringement.setInspectName(DecoderUtil.UtfDecoder(inspectName));
		infringement.setUnitName(DecoderUtil.UtfDecoder(unitName));
		infringement.setIllegalMatter(DecoderUtil.UtfDecoder(illegalMatter));
		infringement.setRules(DecoderUtil.UtfDecoder(rules));
		infringement.setRectificationOpinion(DecoderUtil.UtfDecoder(rectificationOpinion));
		infringement.setScenePhoto(DecoderUtil.UtfDecoder(scenePhoto));
		infringement.setNumberId(DecoderUtil.UtfDecoder(numberId));
		infringement.setIllegalName(DecoderUtil.UtfDecoder(illegalName));
		infringement.setIllegalCard(DecoderUtil.UtfDecoder(illegalCard));
		CommonEntity status = infringement.getStatus();
		status.setVersionFlag(versionFlag);
		infringement.setStatus(status);
		dbDAO.persist(infringement);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		Infringement infringement = dbDAO.queryById(this.getTableNameFromEntity(Infringement.class), id);
		dbDAO.remove(infringement);
		return true;
	}

	@Override
	public Long infringementCount() throws ServiceException {
		Object object = dbDAO.executeJPQLForQuerySingle("select count(*) from " + Infringement.class.getName());
		Long count = (Long)object;
		return count;
	}

	@Override
	public Infringement findById(String id) throws ServiceException {
		Infringement infringement = dbDAO.queryById(Infringement.class.getName(), id);
		return infringement;
	}

	@Override
	public List<Infringement> findByTimeAndUnitNameAndIllegalName(String time,String unitName,String illegalName)
			throws ServiceException {
		String sb = "select c from " + Infringement.class.getName() + " c where ";
		List<Infringement> list = new ArrayList<Infringement>();
		if(GeneralUtil.isNotNull(time)){//时间不为空
			sb += " c.time = ?";
		}
		if(GeneralUtil.isNotNull(unitName) && GeneralUtil.isNull(time)){//单位名称不为空
			sb += " c.unitName = ?";
		}
		if(GeneralUtil.isNotNull(unitName) && GeneralUtil.isNotNull(time)){//时间和单位都不为空
			sb += " and c.unitName = ?";
		}
		if(GeneralUtil.isNotNull(illegalName) && GeneralUtil.isNull(time) && GeneralUtil.isNull(unitName)){//违规人不为空
			sb += " c.illegalName = ?";
		}
		if(GeneralUtil.isNotNull(illegalName) && (GeneralUtil.isNotNull(unitName) || GeneralUtil.isNull(illegalName))){//违规人不为空（时间或单位不为空）
			sb += " and c.illegalName = ?";
		}
		
		if(GeneralUtil.isNotNull(time) && GeneralUtil.isNull(unitName) && GeneralUtil.isNull(illegalName)){
			list = dbDAO.executeJPQLForQueryEntity(sb, time);
		}
		if(GeneralUtil.isNull(time) && GeneralUtil.isNotNull(unitName) && GeneralUtil.isNull(illegalName)){
			list = dbDAO.executeJPQLForQueryEntity(sb, unitName);
		}
		if(GeneralUtil.isNotNull(time) && GeneralUtil.isNotNull(unitName) && GeneralUtil.isNull(illegalName)){
			list = dbDAO.executeJPQLForQueryEntity(sb, time,unitName);
		}
		if(GeneralUtil.isNotNull(time) && GeneralUtil.isNotNull(unitName) && GeneralUtil.isNotNull(illegalName)){
			list = dbDAO.executeJPQLForQueryEntity(sb, time,unitName,illegalName);
		}
		if(GeneralUtil.isNotNull(time) && GeneralUtil.isNull(unitName) && GeneralUtil.isNotNull(illegalName)){
			list = dbDAO.executeJPQLForQueryEntity(sb, time,illegalName);
		}
		if(GeneralUtil.isNull(time) && GeneralUtil.isNotNull(unitName) && GeneralUtil.isNotNull(illegalName)){
			list = dbDAO.executeJPQLForQueryEntity(sb, unitName,illegalName);
		}
		if(GeneralUtil.isNull(time) && GeneralUtil.isNull(unitName) && GeneralUtil.isNotNull(illegalName)){
			list = dbDAO.executeJPQLForQueryEntity(sb, illegalName);
		}
		return list;
	}

}