package com.ticket.serviceImpl;


import java.util.ArrayList;
import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BusinessInfo;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.BindYouZan;
import com.ticket.pojo.YouZanGoodsInfo;
import com.ticket.service.IBindYouZanService;
import com.ticket.util.GeneralUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * 有赞商品绑定机场商家业务接口实现类
 * @ClassName: IBindYouZanService   
 * @Description: 提供有赞商品绑定机场商家操作的增删改查   
 * @author HiSay  
 * @date 2017-01-11 10:26:44
 *
 */
public class BindYouZanServiceImpl extends BaseServiceImpl<BindYouZan, String> implements IBindYouZanService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(BindYouZanServiceImpl.class);

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean persist(BusinessInfo businessInfo,List<YouZanGoodsInfo> goodsInfos, String versionFlag) throws ServiceException {
		if(GeneralUtil.isNotNull(goodsInfos)){
			for(YouZanGoodsInfo goodsInfo:goodsInfos){
				BindYouZan bindYouZan = new BindYouZan();
				bindYouZan.setBusinessInfo(businessInfo);
				bindYouZan.setGoodsInfo(goodsInfo);
				CommonEntity status = bindYouZan.getStatus();
				status.setVersionFlag(versionFlag);
				bindYouZan.setStatus(status);
				dbDAO.persist(bindYouZan);
			}
		}
		return true;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean merge(String id, BusinessInfo businessInfo,List<YouZanGoodsInfo> goodsInfos, String versionFlag) throws ServiceException {
		List<BindYouZan> bindYouZans = this.queryByBusiness(businessInfo.getId());//全部绑定
		List<BindYouZan> bindYouZans2 = new ArrayList<BindYouZan>();
		if(bindYouZans != null && bindYouZans.size() > 0){
			if(goodsInfos != null && goodsInfos.size() > 0){
				for(BindYouZan bindYouZan:bindYouZans){
					boolean a = false;
					for(YouZanGoodsInfo goodsInfo:goodsInfos){
						if(bindYouZan.getGoodsInfo().getId().equals(goodsInfo.getId())){//如果绑定中有,编辑中也有
							a = true;
							continue;
						}else{//如果绑定中有，编辑中没有
						}
					}
					if(!a){
						bindYouZans2.add(bindYouZan);	
					}
				}
			}
		}
		
		if(bindYouZans2 != null && bindYouZans2.size() > 0){//删除
			for(BindYouZan bindYouZan:bindYouZans2){
				this.remove(bindYouZan);
			}
		}
		
		if(GeneralUtil.isNotNull(goodsInfos)){
			for(YouZanGoodsInfo goodsInfo:goodsInfos){
				BindYouZan bindYouZan = this.queryByBusinessAndGoods(id, goodsInfo.getId());
				if(bindYouZan != null){
//					bindYouZan.setBusinessInfo(businessInfo);
//					bindYouZan.setGoodsInfo(goodsInfo);
//					CommonEntity status = bindYouZan.getStatus();
//					status.setVersionFlag(versionFlag);
//					bindYouZan.setStatus(status);
//					dbDAO.merge(bindYouZan);
				}else{
					bindYouZan = new BindYouZan();
					bindYouZan.setBusinessInfo(businessInfo);
					bindYouZan.setGoodsInfo(goodsInfo);
					CommonEntity status = bindYouZan.getStatus();
					status.setVersionFlag(versionFlag);
					bindYouZan.setStatus(status);
					dbDAO.persist(bindYouZan);
				}
			}
		}
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		BindYouZan bindYouZan = dbDAO.queryById(this.getTableNameFromEntity(BindYouZan.class), id);
		dbDAO.remove(bindYouZan);
		return true;
	}

	@Override
	public BindYouZan queryByBusinessAndGoods(String business_id,
			String goods_id) {
		return dbDAO.executeJPQLForQuerySingle("select c from " + BindYouZan.class.getName() + " c where c.businessInfo.id = ? and c.goodsInfo.id = ?", BindYouZan.class, business_id, goods_id);
	}

	@Override
	public List<BindYouZan> queryByBusiness(String business_id) {
		return dbDAO.executeJPQLForQuery("select c from " + BindYouZan.class.getName() + " c where c.businessInfo.id = ?", BindYouZan.class, business_id);
	}

	@Override
	public boolean persist(BusinessInfo businessInfo,
			YouZanGoodsInfo goodsInfo, String versionFlag)
			throws ServiceException {
		BindYouZan bindYouZan = new BindYouZan();
		bindYouZan.setBusinessInfo(businessInfo);
		bindYouZan.setGoodsInfo(goodsInfo);
		CommonEntity status = bindYouZan.getStatus();
		status.setVersionFlag(versionFlag);
		bindYouZan.setStatus(status);
		dbDAO.persist(bindYouZan);
		return true;
	}

	@Override
	public boolean merge(String id, BusinessInfo businessInfo,
			YouZanGoodsInfo goodsInfo, String versionFlag)
			throws ServiceException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean batchRemove(String[] ids,String business_id) throws ServiceException {
		if(GeneralUtil.isNotNull(ids)){
			for(String id:ids){
				BindYouZan bindYouZan = this.queryByBusinessAndGoods(business_id, id);
				if(bindYouZan != null){
					dbDAO.remove(bindYouZan);
				}
			}
		}
		return true;
	}

}