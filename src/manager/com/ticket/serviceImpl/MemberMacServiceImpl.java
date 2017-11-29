package com.ticket.serviceImpl;


import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.MemberMac;
import com.ticket.pojo.WifiPush;
import com.ticket.service.IMassInfomationService;
import com.ticket.service.IMemberMacService;
import com.ticket.service.IMemberSendInfoService;
import com.ticket.service.IMemberService;
import com.ticket.service.IMessageTemplateService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.service.IWifiAreaMessageService;
import com.ticket.service.IWifiAreaService;
import com.ticket.service.IWifiService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * 用户的mac地址业务接口实现类
 * @ClassName: IMemberMacService   
 * @Description: 提供用户的mac地址操作的增删改查   
 * @author HiSay  
 * @date 2016-08-09 10:51:20
 *
 */
public class MemberMacServiceImpl extends BaseServiceImpl<MemberMac, String> implements IMemberMacService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(MemberMacServiceImpl.class);
	@Resource
	private IMemberService memberService;
	@Resource
	private IWifiService wifiService;
	@Resource
	private IWifiAreaService wifiAreaService;
	@Resource
	private IWifiAreaMessageService wifiMessageService;
	@Resource
	private IMessageTemplateService messageTemplateService;
	@Resource
	private IMassInfomationService massInfomationService;
	@Resource
	private IMemberSendInfoService sendInfoService;
	@Resource
	private ISystemDictionaryService dictionaryService;
	
	@Override
	public WifiPush persistPush(String mid, String rid, String mac, String ts, String sig, String tel) {
		
		WifiPush push = new WifiPush();
		push.setMid(mid);
		push.setMac(mac);
		push.setTs(ts);
		push.setSig(sig);
		push.setTel(tel);
		push.setRid(rid);
		dbDAO.persist(push);
		return push;
	}

	@Override
	public boolean persist(String phone,String mac, String versionFlag) throws ServiceException {
		MemberMac memberMac = query(phone, mac);
		if(memberMac != null){
			
			return true;
		}
		memberMac = new MemberMac();
		memberMac.setPhone(phone);
		memberMac.setMac(DecoderUtil.UtfDecoder(mac));
		CommonEntity status = memberMac.getStatus();
		status.setVersionFlag(versionFlag);
		memberMac.setStatus(status);
		dbDAO.persist(memberMac);
		return true;
	}
	
	@Override
	public boolean merge(String id, String phone,String mac, String versionFlag) throws ServiceException {
		MemberMac memberMac = dbDAO.queryById(this.getTableNameFromEntity(MemberMac.class), id);
		memberMac.setPhone(phone);
		memberMac.setMac(DecoderUtil.UtfDecoder(mac));
		CommonEntity status = memberMac.getStatus();
		status.setVersionFlag(versionFlag);
		memberMac.setStatus(status);
		dbDAO.merge(memberMac);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		MemberMac memberMac = dbDAO.queryById(this.getTableNameFromEntity(MemberMac.class), id);
		dbDAO.remove(memberMac);
		return true;
	}
	
	@Override
	public MemberMac query(String phone, String mac) {
		
		MemberMac memberMac = dbDAO.executeJPQLForQuerySingle(
				"select s from " + MemberMac.class.getName() + " s where s.phone = ? and s.mac = ?", MemberMac.class, phone, mac);
		return memberMac;
	}
	
	@Override
	public List<MemberMac> queryByMac(String mac) {
		
		List<MemberMac> memberMac = dbDAO.executeJPQLForQuery(
				"select s from " + MemberMac.class.getName() + " s where s.mac = ?", MemberMac.class, mac);
		return memberMac;
	}
	
	@Override
	public List<MemberMac> queryByPhone(String phone) {
		
		List<MemberMac> memberMac = dbDAO.executeJPQLForQuery(
				"select s from " + MemberMac.class.getName() + " s where s.phone = ?", MemberMac.class, phone);
		return memberMac;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void receiveMobileData(String json) throws ServiceException{
		
//		{
//		    "data": [
//		        {
//		            "mid": 21738,
//		            "rid": 21738,
//		            "mac": "fc:64:ba:30:cc:4a",
//		            "ts": 1466595993,
//		            "sig": 10,
//		            "tel": 18208808642
//		        },
//		        {
//		            "mid": 20951,
//		            "rid": 20951,
//		            "mac": "fc:64:ba:30:cc:4a",
//		            "ts": 1466595993,
//		            "sig": 25,
//		            "tel": 0
//		        }
//		    ]
//		}
		
		if(GeneralUtil.isNotNull(json)){
			
			try {
				JSONObject jsonObject = JSONObject.parseObject(json);
				JSONArray jsonArray = jsonObject.getJSONArray("data");
				for(int i = 0; i < jsonArray.size(); i++){
					
					JSONObject item = jsonArray.getJSONObject(i);
					String mid = item.getString("mid"); //商家id
					String rid = item.getString("rid"); //设备id
					String mac = item.getString("mac"); //手机mac地址
					String ts = item.getString("ts"); //时间戳
					String sig = item.getString("sig"); //信号强度0~65535， 一般是0-100
					String tel = item.getString("tel"); //手机号码
					
					//保存推送的数据
					persistPush(mid, rid, mac, ts, sig, tel);
					
					//如果号码不为空，尝试保存mac和phone的对象关系
					if(GeneralUtil.isNotNull(tel) && GeneralUtil.isNotNull(mac) && !"0".equals(tel)){
						
						persist(tel, mac, versionFlag);
					}
					
					if(GeneralUtil.isNotNull(mac) && GeneralUtil.isNotNull(rid)){
						
						sendInfoService.wifiPush(mac, rid);
					}
				}
			} catch (Exception e) {
				
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
		}
	}
}