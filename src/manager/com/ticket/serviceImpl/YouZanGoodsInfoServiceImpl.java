package com.ticket.serviceImpl;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kdt.api.KdtApiClient;
import com.kdt.common.GlobalConfig;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.YouZanGoodsInfo;
import com.ticket.service.IYouZanGoodsInfoService;
import com.ticket.util.DecoderUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.springframework.transaction.annotation.Transactional;

/**
 * 有赞出售中的商品业务接口实现类
 * @ClassName: IYouZanGoodsInfoService   
 * @Description: 提供有赞出售中的商品操作的增删改查   
 * @author HiSay  
 * @date 2017-01-05 16:13:06
 *
 */
public class YouZanGoodsInfoServiceImpl extends BaseServiceImpl<YouZanGoodsInfo, String> implements IYouZanGoodsInfoService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(YouZanGoodsInfoServiceImpl.class);

	@Override
	public boolean persist(String num_iid,String name,String alias,String detail_url,String price,String sold_num,String listing, String versionFlag) throws ServiceException {
		YouZanGoodsInfo youZanGoodsInfo = new YouZanGoodsInfo();
		youZanGoodsInfo.setNum_iid(DecoderUtil.UtfDecoder(num_iid));
		youZanGoodsInfo.setName(DecoderUtil.UtfDecoder(name));
		youZanGoodsInfo.setAlias(DecoderUtil.UtfDecoder(alias));
		youZanGoodsInfo.setDetail_url(DecoderUtil.UtfDecoder(detail_url));
		youZanGoodsInfo.setPrice(DecoderUtil.UtfDecoder(price));
		youZanGoodsInfo.setSold_num(DecoderUtil.UtfDecoder(sold_num));
		youZanGoodsInfo.setListing(DecoderUtil.UtfDecoder(listing));
		CommonEntity status = youZanGoodsInfo.getStatus();
		status.setVersionFlag(versionFlag);
		youZanGoodsInfo.setStatus(status);
		dbDAO.persist(youZanGoodsInfo);
		return true;
	}
	
	@Override
	public boolean merge(String id, String num_iid,String name,String alias,String detail_url,String price,String sold_num,String listing, String versionFlag) throws ServiceException {
		YouZanGoodsInfo youZanGoodsInfo = dbDAO.queryById(this.getTableNameFromEntity(YouZanGoodsInfo.class), id);
		youZanGoodsInfo.setNum_iid(DecoderUtil.UtfDecoder(num_iid));
		youZanGoodsInfo.setName(DecoderUtil.UtfDecoder(name));
		youZanGoodsInfo.setAlias(DecoderUtil.UtfDecoder(alias));
		youZanGoodsInfo.setDetail_url(DecoderUtil.UtfDecoder(detail_url));
		youZanGoodsInfo.setPrice(DecoderUtil.UtfDecoder(price));
		youZanGoodsInfo.setSold_num(DecoderUtil.UtfDecoder(sold_num));
		youZanGoodsInfo.setListing(DecoderUtil.UtfDecoder(listing));
		CommonEntity status = youZanGoodsInfo.getStatus();
		status.setVersionFlag(versionFlag);
		youZanGoodsInfo.setStatus(status);
		dbDAO.merge(youZanGoodsInfo);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		YouZanGoodsInfo youZanGoodsInfo = dbDAO.queryById(this.getTableNameFromEntity(YouZanGoodsInfo.class), id);
		dbDAO.remove(youZanGoodsInfo);
		return true;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void getYouZanOpenData() {
		String method = "kdt.items.onsale.get";
		HashMap<String, String> params = new HashMap<String, String>();
		KdtApiClient kdtApiClient;
		HttpResponse response;
		
		try {
			kdtApiClient = new KdtApiClient(GlobalConfig.APP_ID, GlobalConfig.APP_SECRET);
			response = kdtApiClient.get(method, params);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line);
			}
			JSONObject jsonObject = JSONObject.parseObject(result.toString().substring(12,result.length()-1));
			JSONArray array = jsonObject.getJSONArray("items");
			YouZanGoodsInfo goodsInfo = null;
			if(array.size() > 0){
				for(int i=0;i<array.size();i++){
					JSONObject jsonObject2 = array.getJSONObject(i);
					String name = jsonObject2.getString("title");
					String num_iid = jsonObject2.getString("num_iid");
					String alias = jsonObject2.getString("alias");
					String detail_url = jsonObject2.getString("detail_url");
					String price = jsonObject2.getString("price");
					String sold_num = jsonObject2.getString("sold_num");
					String listing = jsonObject2.getString("is_listing");
					goodsInfo = getByNum_iid(num_iid);
					if(goodsInfo == null){
						goodsInfo = new YouZanGoodsInfo();
						goodsInfo.setAlias(alias);
						goodsInfo.setName(name);
						goodsInfo.setDetail_url(detail_url);
						goodsInfo.setNum_iid(num_iid);
						goodsInfo.setPrice(price);
						goodsInfo.setSold_num(sold_num);
						goodsInfo.setListing(listing);
						persist(goodsInfo);
					}else{
						goodsInfo.setAlias(alias);
						goodsInfo.setName(name);
						goodsInfo.setDetail_url(detail_url);
						goodsInfo.setNum_iid(num_iid);
						goodsInfo.setPrice(price);
						goodsInfo.setSold_num(sold_num);
						goodsInfo.setListing(listing);
						merge(goodsInfo);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public YouZanGoodsInfo getByNum_iid(String num_iid) {
		return dbDAO.executeJPQLForQuerySingle("select c from " + YouZanGoodsInfo.class.getName() + " c where c.num_iid = ?", YouZanGoodsInfo.class, num_iid);
	}

}