package com.ticket.serviceImpl;


import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.Member;
import com.ticket.pojo.QuickMenu;
import com.ticket.pojo.QuickMenuMemberSetting;
import com.ticket.pojo.SpecialPersonWithQuickMenu;
import com.ticket.service.IQuickMenuMemberSettingService;
import com.ticket.service.IQuickMenuService;
import com.ticket.service.ISpecialPersonWithQuickMenuService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * 会员设置快捷菜单业务接口实现类
 * @ClassName: IQuickMenuMemberSettingService   
 * @Description: 提供会员设置快捷菜单操作的增删改查   
 * @author HiSay  
 * @date 2015-10-31 13:04:17
 *
 */
public class QuickMenuMemberSettingServiceImpl extends BaseServiceImpl<QuickMenuMemberSetting, String> implements IQuickMenuMemberSettingService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(QuickMenuMemberSettingServiceImpl.class);
	@Resource
	IQuickMenuService quickMenuService;
	@Resource
	ISpecialPersonWithQuickMenuService specialPersonWithQuickMenuService;

	@Override
	public void init(String member_id) {
		
		for(char i = 'a'; i <= 'm'; i++){
			
			List<QuickMenu> menus = quickMenuService.queryQuickMenuListByVisitor(i + "", versionFlag);
			for(int j = 0; j < menus.size(); j++){
				
				persist(member_id, menus.get(j).getId(), null, i+"", versionFlag);
			}
		}
	}
	
	@Override
	public void setBySpecialPerson(String types, int personCount, String memberSelfMenuId, String position) {

		removeBySelfMenuId(memberSelfMenuId,versionFlag);
		if(GeneralUtil.isNotNull(types)){
			
			Member member = getMember();
			List<String> strList = convertUtil.stringToList(types, ",");
			for(String personType:strList){
				
				List<SpecialPersonWithQuickMenu> serviceMenuList = specialPersonWithQuickMenuService.queryListByPersonType(personType,versionFlag);
				if(serviceMenuList != null && !serviceMenuList.isEmpty()){
					
					for(SpecialPersonWithQuickMenu menu:serviceMenuList){
						
						if(!validateIfSettingFlight(member.getId(), menu.getQuickMenu_id(), memberSelfMenuId, position)){
							
							//无陪旅客服务，当人数大于1是自动不显示
							QuickMenu quickMenu = quickMenuService.get(QuickMenu.class, menu.getQuickMenu_id());
							if(quickMenu.getName().indexOf("无陪") >= 0 && personCount > 1){
								
								continue;
							}
							
							//保存快捷菜单
							persist(member.getId(), menu.getQuickMenu_id(), memberSelfMenuId, position, versionFlag);
						}
					}
				}
			}
		}
	}
	
	@Override
	public boolean merge(String id, String member_id,String quickMenu_id, String versionFlag) throws ServiceException {
		QuickMenuMemberSetting quickMenuMemberSetting = dbDAO.queryById(this.getTableNameFromEntity(QuickMenuMemberSetting.class), id);
		quickMenuMemberSetting.setMember_id(DecoderUtil.UtfDecoder(member_id));
		quickMenuMemberSetting.setQuickMenu_id(DecoderUtil.UtfDecoder(quickMenu_id));
		CommonEntity status = quickMenuMemberSetting.getStatus();
		status.setVersionFlag(versionFlag);
		quickMenuMemberSetting.setStatus(status);
		dbDAO.merge(quickMenuMemberSetting);
		return true;
	}

	@Override
	public boolean persist(String member_id,String quickMenu_id,String flightQuickMenu,String defaultShowPosition, String versionFlag) {
		QuickMenuMemberSetting quickMenuMemberSetting = new QuickMenuMemberSetting();
		quickMenuMemberSetting.setMember_id(DecoderUtil.UtfDecoder(member_id));
		quickMenuMemberSetting.setQuickMenu_id(DecoderUtil.UtfDecoder(quickMenu_id));
		quickMenuMemberSetting.setDefaultShowPosition(defaultShowPosition);
		quickMenuMemberSetting.setFlightQuickMenu(flightQuickMenu);
		dbDAO.persist(quickMenuMemberSetting);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		QuickMenuMemberSetting quickMenuMemberSetting = dbDAO.queryById(this.getTableNameFromEntity(QuickMenuMemberSetting.class), id);
		dbDAO.remove(quickMenuMemberSetting);
		return true;
	}

	@Override
	public List<QuickMenuMemberSetting> queryListByMember(String memberId)
			throws ServiceException {
		return dbDAO.queryByList(QuickMenuMemberSetting.class.getSimpleName(), deleteFlag, versionFlag, "and s.member_id=?3", new Object[]{memberId}, orderBy, null); 
	}

	@Override
	public boolean validateIfSetting(String memberId, String quickMenuId,String defaultShowPosition)
			throws ServiceException {
		List<QuickMenuMemberSetting> msList= dbDAO.queryByList(QuickMenuMemberSetting.class.getSimpleName(), deleteFlag, versionFlag, "and s.member_id =?3 and s.quickMenu_id =?4 and s.defaultShowPosition =?5", new Object[]{memberId,quickMenuId,defaultShowPosition}, orderBy, null);
		if(msList != null && !msList.isEmpty()){
			return true;
		}
		return false;
	}

	@Override
	public List<QuickMenuMemberSetting> queryListByMemberIdAndSet(String defaultShowPosition,
		String versionFlag) throws ServiceException {
		Member member = (Member)getSession().get(ContextConstants.SCOPE_MEMBER);
		String tableName = QuickMenuMemberSetting.class.getSimpleName();
		String tableName2 = QuickMenu.class.getSimpleName();
		String sql = "select t1 from "+tableName+" t1 where t1.defaultShowPosition =? and t1.member_id =? "+
						"and t1.quickMenu_id in (select t2.id from "+tableName2+" t2)";
		List<QuickMenuMemberSetting> list = dbDAO.executeJPQLForQueryEntity(sql, defaultShowPosition,member.getId());
		
		return list;
	}

	@Override
	public List<QuickMenuMemberSetting> queryQuickMenuListByFront(Integer size,String versionFlag) throws ServiceException {
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		List<QuickMenuMemberSetting> memberSetList = dbDAO.queryByList(QuickMenuMemberSetting.class.getSimpleName(), deleteFlag, versionFlag, "and s.member_id = ?3 and s.defaultShowPosition = 'g'", new Object[]{member.getId()}, orderBy, size);
		if(memberSetList != null && !memberSetList.isEmpty()){
			return memberSetList;
		}
		return null;
	}

	@Override
	public List<QuickMenuMemberSetting> queryListByMemberHasSet(String defaultShowPosition, String versionFlag) {
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		List<QuickMenuMemberSetting> list = dbDAO.queryByList(QuickMenuMemberSetting.class.getSimpleName(), deleteFlag, versionFlag, "and s.member_id = ?3 and s.defaultShowPosition =?4", new Object[]{member.getId(),defaultShowPosition}, orderBy, null);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public boolean validateCount(String defaultShowPosition,Integer menuCount,String flightQuickMenu, String versionFlag) throws ServiceException {
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		Long count = 0l;
		if(GeneralUtil.isNotNull(flightQuickMenu)){
			count = dbDAO.getTotalCount(QuickMenuMemberSetting.class.getSimpleName(), deleteFlag, versionFlag, 
					"and s.member_id =?3 and s.defaultShowPosition =?4 and s.flightQuickMenu =?5", new Object[]{member.getId(),defaultShowPosition,flightQuickMenu});
		}else{
			count = dbDAO.getTotalCount(QuickMenuMemberSetting.class.getSimpleName(), deleteFlag, versionFlag, 
					"and s.defaultShowPosition =?3 and s.member_id =?4", new Object[]{defaultShowPosition,member.getId()});
		}
		if( count >= 7){
			return true;
		}
		return false;
	}

	@Override
	public boolean validateCenterCount(String defaultShowPosition,String versionFlag) {
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		Long count = dbDAO.getTotalCount(QuickMenuMemberSetting.class.getSimpleName(), deleteFlag, versionFlag, 
				"and s.defaultShowPosition =?3 and s.member_id =?4", new Object[]{defaultShowPosition,member.getId()});
		if(count >= 5){
			return true;
		}
		return false;
	}

	@Override
	public QuickMenuMemberSetting queryByMenuIdAndPosition(String quickMenuId,
			String defaultShowPosition) throws ServiceException {
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		QuickMenuMemberSetting obj = dbDAO.queryByCustom(QuickMenuMemberSetting.class.getSimpleName(), deleteFlag, versionFlag, "and s.member_id =?3 and s.quickMenu_id =?4 and s.defaultShowPosition =?5", new Object[]{member.getId(),quickMenuId,defaultShowPosition});
		if(obj != null){
			return obj;
		}
		return null;
	}

	@Override
	public List<QuickMenuMemberSetting> queryListByFlightMenu(String flightQuickMenu,Integer size,String position, String versionFlag) throws ServiceException {
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		List<QuickMenuMemberSetting> list = dbDAO.queryByList(QuickMenuMemberSetting.class.getSimpleName(), deleteFlag, versionFlag, "and s.flightQuickMenu =?3 and s.member_id =?4 and s.defaultShowPosition =?5", new Object[]{flightQuickMenu,member.getId(),position}, orderBy, size);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<QuickMenuMemberSetting> queryListByServiceMenu(String flightQuickMenu,
			String versionFlag) throws ServiceException {
		List<QuickMenuMemberSetting> list = dbDAO.queryByList(QuickMenuMemberSetting.class.getSimpleName(), deleteFlag, versionFlag, "and s.flightQuickMenu =?3", new Object[]{flightQuickMenu}, orderBy, null);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public boolean validateIfSettingFlight(String member_id, String quickMenu_id, String flightQuickMenu,String position) {
		QuickMenuMemberSetting obj = dbDAO.queryByCustom(QuickMenuMemberSetting.class.getSimpleName(), deleteFlag, versionFlag, "and s.member_id =?3 and s.quickMenu_id =?4 and s.flightQuickMenu =?5 and s.defaultShowPosition =?6", new Object[]{member_id,quickMenu_id,flightQuickMenu,position});
		if(obj != null){
			return true;
		}
		return false;
	}

	@Override
	public List<QuickMenuMemberSetting> queryQuickMenuListByFront(
			String memberId, int i, String versionFlag) throws ServiceException {
		List<QuickMenuMemberSetting> memberSetList = dbDAO.queryByList(QuickMenuMemberSetting.class.getSimpleName(), deleteFlag, versionFlag, "and s.member_id = ?3 and s.defaultShowPosition = 'g'", new Object[]{memberId}, orderBy, i);
		if(memberSetList != null && !memberSetList.isEmpty()){
			return memberSetList;
		}
		return null;
	}

	@Override
	public List<QuickMenuMemberSetting> queryServiceListByMemberHasSet(
			String position, String flightQuickMenu, String versionFlag)
			throws ServiceException {
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		List<QuickMenuMemberSetting> list = dbDAO.queryByList(QuickMenuMemberSetting.class.getSimpleName(), deleteFlag, versionFlag,
				"and s.member_id =?3 and s.defaultShowPosition =?4 and s.flightQuickMenu =?5", new Object[]{member.getId(),position,flightQuickMenu}, orderBy, null);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public void removeBySelfMenuId(String memberSelfMenuId, String versionFlag) {
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		if(member != null){
			dbDAO.deleteByWhere(QuickMenuMemberSetting.class.getSimpleName(), deleteFlag, versionFlag, "and s.flightQuickMenu =?3 and s.member_id =?4", new Object[]{memberSelfMenuId,member.getId()});
		}
	}
}