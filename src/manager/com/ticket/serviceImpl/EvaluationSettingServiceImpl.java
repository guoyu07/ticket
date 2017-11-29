package com.ticket.serviceImpl;


import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.EvaluationSetting;
import com.ticket.service.IEvaluationSettingService;
import com.ticket.service.ITreeService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.ResourceUtil;

/**
 * 评价设置业务接口实现类
 * @ClassName: IEstimateSetManageService   
 * @Description: 提供评价设置操作的增删改查   
 * @author HiSay  
 * @date 2016-01-26 10:56:19
 *
 */
public class EvaluationSettingServiceImpl extends BaseServiceImpl<EvaluationSetting, String> implements IEvaluationSettingService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(EvaluationSettingServiceImpl.class);
	@Resource(name="treeService")
	private ITreeService<EvaluationSetting, String> treeService;
	
	@Override
	public boolean merge(String id, Integer enabled,String name, Integer order,EvaluationSetting parent) {
		EvaluationSetting estimateSetManage = dbDAO.queryById(this.getTableNameFromEntity(EvaluationSetting.class), id);
		estimateSetManage.setEnabled(enabled);
		estimateSetManage.setParent(parent);
		estimateSetManage.setName(DecoderUtil.UtfDecoder(name));
		estimateSetManage.getStatus().setOrderValue(order);
		dbDAO.merge(estimateSetManage);
		return true;
	}

	@Override
	public boolean persist(Integer enabled,String name, Integer order,String parent_id) throws ServiceException {
		EvaluationSetting parent = queryById(EvaluationSetting.class.getName(), parent_id);
		return persist( enabled, name, order, parent);
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		EvaluationSetting estimateSetManage = dbDAO.queryById(this.getTableNameFromEntity(EvaluationSetting.class), id);
		dbDAO.remove(estimateSetManage);
		return true;
	}

	@Override
	public List<EvaluationSetting> findByParentId(String parentId) {
		List<EvaluationSetting> list = dbDAO.executeJPQLForQueryEntity("select c from " + EvaluationSetting.class.getName() + " c where c.parent.id = ? and c.enabled=0", parentId);
		return list;
	}

	@Override
	public EvaluationSetting getByName(String name) {
		EvaluationSetting manage = dbDAO.queryByCustom(EvaluationSetting.class.getName(), 
				deleteFlag, versionFlag, "and name=?3", new Object[]{name});
		return manage;
	}

	@Override
	public boolean persist(
			Integer enabled, String name, Integer order,
			EvaluationSetting parent) throws ServiceException {
		if(existSameUnderParent(parent, name)){
			
			throw new ServiceException(ResourceUtil.getText("dictionary.existSameUnderParent"));
		}
		EvaluationSetting estimateSetManage = new EvaluationSetting();
		estimateSetManage.setEnabled(enabled);
		estimateSetManage.setParent(parent);
		estimateSetManage.setName(DecoderUtil.UtfDecoder(name));
		estimateSetManage.getStatus().setOrderValue(order);
		dbDAO.persist(estimateSetManage);
		return true;
	}

	@Override
	public boolean merge(
			String target, Integer enabled, String name, Integer order,
			String parent_id) throws ServiceException {
		EvaluationSetting parent = queryById(EvaluationSetting.class.getName(), parent_id);
		return merge( target, enabled, name, order, parent);
	}

	@Override
	public boolean existSameUnderParent(EvaluationSetting parent, String name) {
		List<EvaluationSetting> list = null;
		if(parent != null){
			list = treeService.querySubByParent(EvaluationSetting.class, parent.getId());
		}else{
			list = treeService.queryRootNode(EvaluationSetting.class);
		}
		for(EvaluationSetting e : list){
			if(e.getName().equals(name)){
				return true;
			}
		}
		return false;
	}
	
}