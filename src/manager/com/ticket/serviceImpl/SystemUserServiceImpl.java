package com.ticket.serviceImpl;


import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.SystemUser;
import com.ticket.service.ISystemUserService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.MD5Util;

/**
 * 系统管理员业务接口实现类
 * @ClassName: ISystemUserService   
 * @Description: 提供系统管理员操作的增删改查   
 * @author HiSay  
 * @date 2014-10-14 09:35:50
 *
 */
public class SystemUserServiceImpl extends BaseServiceImpl<SystemUser, String> implements ISystemUserService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(SystemUserServiceImpl.class);
	
	@Override
	public boolean merge(String id, String name,Integer sex,String loginId,String password, String phone, String versionFlag) throws ServiceException {
		SystemUser systemUser = dbDAO.queryById(this.getTableNameFromEntity(SystemUser.class), id);
		systemUser.setName(DecoderUtil.UtfDecoder(name));
		systemUser.setSex(sex);
		systemUser.setLoginId(DecoderUtil.UtfDecoder(loginId));
		
		if(!systemUser.getPassword().equals(password)){
			
			systemUser.setPassword(MD5Util.Azdg.strMD5(password));
		}
		CommonEntity status = systemUser.getStatus();
		status.setVersionFlag(versionFlag);
		systemUser.setStatus(status);
		systemUser.setPhone(DecoderUtil.UtfDecoder(phone));
		dbDAO.merge(systemUser);
		return true;
	}

	@Override
	public boolean persist(String name,Integer sex,String loginId,String password, String phone, String versionFlag) throws ServiceException {
		SystemUser systemUser = new SystemUser();
		systemUser.setName(DecoderUtil.UtfDecoder(name));
		systemUser.setSex(sex);
		systemUser.setLoginId(DecoderUtil.UtfDecoder(loginId));
		systemUser.setPassword(MD5Util.Azdg.strMD5(password));
		CommonEntity status = systemUser.getStatus();
		status.setVersionFlag(versionFlag);
		systemUser.setStatus(status);
		systemUser.setPhone(DecoderUtil.UtfDecoder(phone));
		dbDAO.persist(systemUser);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		SystemUser systemUser = dbDAO.queryById(this.getTableNameFromEntity(SystemUser.class), id);
		dbDAO.remove(systemUser);
		return true;
	}

	@Override
	public boolean validateLoginIsExists(String loginId, String id) throws ServiceException {
		loginId = DecoderUtil.UtfDecoder(loginId);		
		SystemUser su = dbDAO.queryByCustom(SystemUser.class.getSimpleName(), deleteFlag, versionFlag, "and s.loginId=?3", new Object[]{loginId}) ;
		//如果用户已存在
		if(su != null) {
			if(GeneralUtil.isNotNull(id)) {
				//如果用户不是当前用户
				if(!su.getId().equals(id)) {
					return true;
				}
			} else {
				return true;
			}
		}
		return false;
	}

	@Override
	public SystemUser queryByLoginIdAndPassword(String loginId, String password) throws ServiceException {
		loginId = DecoderUtil.UtfDecoder(loginId);
		return dbDAO.queryByCustom(SystemUser.class.getSimpleName(), deleteFlag, versionFlag, "and s.loginId=?3 and s.password=?4", new Object[]{loginId, MD5Util.Azdg.strMD5(password)}) ;
	}

	@Override
	public boolean updatePassword(String systemUserId, String password)
			throws ServiceException {
		SystemUser user = this.queryById(SystemUser.class.getSimpleName(), systemUserId) ;
		user.setPassword(MD5Util.Azdg.strMD5(password));
		dbDAO.merge(user);
		return true;
	}

	@Override
	public boolean batchRealDelete(String idsValue, String versionFlag)
			throws ServiceException {
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, versionFlag);
		dbDAO.deleteAll(SystemUser.class.getSimpleName(), idsValue, null);
		return true;
	}

	@Override
	public String restartProject() throws ServiceException {
		String cmd = "cmd /c start D:\\kmairport.bat";// pass
		Process ps = null;
		try {
            ps = Runtime.getRuntime().exec(cmd);
            ps.waitFor();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        	ps.destroy();
        }
        return "<h3>项目重启命令发送完成！请等待大约10秒左右即可打开。</h3>";
	}

//	@Override
//	public String add(String name, Integer sex, String loginId,
//			String password, String phone,String employee_id, String versionFlag)
//			throws ServiceException {
//		
//		SystemUser systemUser = new SystemUser();
//		systemUser.setName(DecoderUtil.UtfDecoder(name));
//		systemUser.setSex(sex);
//		systemUser.setLoginId(DecoderUtil.UtfDecoder(loginId));
//		systemUser.setPassword(MD5Util.Azdg.strMD5(password));
//		systemUser.setEmployee_id(employee_id);
//		CommonEntity status = systemUser.getStatus();
//		status.setVersionFlag(versionFlag);
//		systemUser.setStatus(status);
//		systemUser.setPhone(DecoderUtil.UtfDecoder(phone));
//		dbDAO.persist(systemUser);
//		if(systemUser.getId()!=null){
//			return systemUser.getId();
//		}else{
//			return null;
//		}
//	}

}