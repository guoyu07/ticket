package com.ticket.serviceImpl;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjHall;
import com.ticket.pojo.BjdjHallGate;
import com.ticket.pojo.ChannelHallRelation;
import com.ticket.pojo.InfoPosition;
import com.ticket.pojo.SystemDictionary;
import com.ticket.service.IBjdjAppointmentService;
import com.ticket.service.IBjdjHallService;
import com.ticket.service.IChannelHallRelationService;
import com.ticket.util.DecoderUtil;

/**
 * 服务厅表业务接口实现类
 * @ClassName: IBjdjHallService   
 * @Description: 提供服务厅表操作的增删改查   
 * @author HiSay  
 * @date 2015-10-23 15:24:57
 *
 */
public class BjdjHallServiceImpl extends BaseServiceImpl<BjdjHall, String> implements IBjdjHallService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(BjdjHallServiceImpl.class);
	@Resource
	private IChannelHallRelationService channelHallRelationService;
	@Resource
	private IBjdjAppointmentService appointmentService;
	
	@Override
	public boolean merge(String id, String number,Integer capacity,String longitude,String latitude,String description, String versionFlag) throws ServiceException {
		BjdjHall bjdjHall = dbDAO.queryById(this.getTableNameFromEntity(BjdjHall.class), id);
		bjdjHall.setNumber(DecoderUtil.UtfDecoder(number));
		bjdjHall.setCapacity(capacity);
		bjdjHall.setLongitude(DecoderUtil.UtfDecoder(longitude));
		bjdjHall.setLatitude(DecoderUtil.UtfDecoder(latitude));
		bjdjHall.setDescription(DecoderUtil.UtfDecoder(description));
		dbDAO.merge(bjdjHall);
		return true;
	}

	@Override
	public boolean persist(String number,Integer capacity,String longitude,String latitude,String description, String versionFlag) throws ServiceException {
		
		BjdjHall bjdjHall = new BjdjHall();
		bjdjHall.setNumber(DecoderUtil.UtfDecoder(number));
		bjdjHall.setCapacity(capacity);
		bjdjHall.setLongitude(DecoderUtil.UtfDecoder(longitude));
		bjdjHall.setLatitude(DecoderUtil.UtfDecoder(latitude));
		bjdjHall.setDescription(DecoderUtil.UtfDecoder(description));
		dbDAO.persist(bjdjHall);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		BjdjHall bjdjHall = dbDAO.queryById(this.getTableNameFromEntity(BjdjHall.class), id);
		dbDAO.remove(bjdjHall);
		return true;
	}

	@Override
	public BjdjHall getByName(String hallNumber) {
		
		BjdjHall hall = (BjdjHall)dbDAO.executeJPQLForQuerySingle(
				"select s from " + BjdjHall.class.getName() + " s where s.number=?", hallNumber);
		return hall;
	}
	
	@Override
	public BjdjHall accessibleHall(Date flightDate, SystemDictionary channel){
		
		int allSurplus = appointmentService.surplus(flightDate, channel);
		if(allSurplus > 0){
			
			List<ChannelHallRelation> list = channelHallRelationService.queryByChannel(channel);
			for(ChannelHallRelation chr : list){
				
				int surplus = appointmentService.surplus(flightDate, chr.getHall());
				if(surplus > 0){
					
					return chr.getHall();
				}
			}
		}
		return null;
	}

	@Override
	public int totalCapcity(SystemDictionary channel) {
		
		int totalCapcity = 0;
		List<ChannelHallRelation> list = channelHallRelationService.queryByChannel(channel);
		for(int i = 0; i < list.size(); i++){
			
			BjdjHall hall = list.get(i).getHall();
			totalCapcity += hall.getCapacity();
		}
		return totalCapcity;
	}

	
	//****************************以下是大厅登机口的关联关系操作方法*******************************
	@Override
	public void addGate(BjdjHall hall, InfoPosition gate, int meter, String description) throws ServiceException {
		
		BjdjHallGate hallGate = queryByHallAndGate(hall, gate);
		if(hallGate != null){
			
			throw new ServiceException("已存在“" + hall.getNumber() + "”大厅和“" + gate.getName() + "”登机口的关系，不能重复添加");
		}
		
		hallGate = new BjdjHallGate();
		hallGate.setHall(hall);
		hallGate.setGate(gate);
		hallGate.setMeter(meter);
		hallGate.setDescription(description);
		dbDAO.persist(hallGate);
	}
	
	@Override
	public void editGate(String id, BjdjHall hall, InfoPosition gate, int meter, String description) throws ServiceException {
		
		BjdjHallGate hallGate = get(BjdjHallGate.class, id);
		hallGate.setHall(hall);
		hallGate.setGate(gate);
		hallGate.setMeter(meter);
		hallGate.setDescription(description);
		dbDAO.merge(hallGate);
	}

	@Override
	public List<BjdjHallGate> queryGates(BjdjHall hall) {
		
		List<BjdjHallGate> list = dbDAO.executeJPQLForQuery(
				"select hg from " + BjdjHallGate.class.getSimpleName() + " hg where hg.hall = ?", 
				BjdjHallGate.class, hall);
		return list;
	}

	@Override
	public BjdjHallGate queryNearestGate(BjdjHall hall) {
		
		BjdjHallGate list = dbDAO.executeJPQLForQuerySingle(
				"select hg from " + BjdjHallGate.class.getSimpleName() + " hg"
						+ " where hg.hall=?"
						+ " and hg.meter = (select min(hg2.meter) from " + BjdjHallGate.class.getSimpleName() + " hg2 where hg2.hall=?)", 
				BjdjHallGate.class, hall, hall);
		return list;
	}

	@Override
	public BjdjHallGate queryByHallAndGate(BjdjHall hall, InfoPosition gate) {
		
		BjdjHallGate hallGate = dbDAO.executeJPQLForQuerySingle(
				"select t from " + BjdjHallGate.class.getName() + " t where t.hall=? and t.gate=?", 
				BjdjHallGate.class, hall, gate);
		return hallGate;
	}
	
}