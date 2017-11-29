package com.ticket.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;

import com.ticket.enumer.VpnStatus;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.SystemDictionary;
import com.ticket.pojo.VpnBroken;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.service.IVpnBrokenService;
import com.ticket.util.SmsUtil;

/**
 * vpn断线页面实现
 * @author tuyou
 */
public class VpnBrokenServiceImpl extends BaseServiceImpl<VpnBroken, String> implements IVpnBrokenService{

	@Resource
	private ISystemDictionaryService dictionaryService;
	
	@Override
	public VpnBroken get() {
		
		List<VpnBroken> list = dbDAO.executeJPQLForQueryEntity("select s from " + VpnBroken.class.getName() + " s where s.state = ?", VpnStatus.untreated);
		if(!list.isEmpty()){
			
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public VpnBroken tryAdd() {
		
		VpnBroken broken = get();
		if(broken != null){
			
			return null;
		}
		
		broken = new VpnBroken();
		dbDAO.persist(broken);
		return broken;
	}
	
	@Override
	public VpnBroken process(VpnStatus state, String remark) {
		
		VpnBroken broken = get();
		if(broken != null){
			
			broken.setState(state);
			broken.setRemark(remark);
			merge(broken);
			return broken;
		}
		return null;
	}
	
	@Override
	public VpnBroken setPageNotify(boolean pageNotify) {
		
		VpnBroken broken = get();
		if(broken != null){
			
			broken.setPageNotify(pageNotify);
			merge(broken);
			return broken;
		}
		return null;
	}

	@Override
	public void sendNotify(final VpnBroken vpnBroken) {
		
		final List<SystemDictionary> dicts = dictionaryService.querySubByParentName("vpn_sms");
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {

				for(int i = 0; i < dicts.size(); i++){
					
					try {
						SmsUtil.sendSms(dicts.get(i).getValue(), "紧急：vpn于时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(vpnBroken.getTime()) + "断线，请及时处理");
					} catch (ServiceException e) {
						e.printStackTrace();
					}
					
					//如果达到发送的最高限制，就停止发送
					if(i == dicts.size() - 1){
						
						vpnBroken.setSmsNotifyCount(vpnBroken.getSmsNotifyCount() + 1);
						merge(vpnBroken);
						
						if(vpnBroken.getSmsNotifyCount() == VpnBroken.notifyNumber){
							
							timer.cancel();
						}
					}
				}
			}
		}, 0, 30 * 60 * 1000);
	}
	
	
}