package com.ticket.serviceImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.pojo.AirportDepartment;
import com.ticket.service.IAirportDepartmentService;

/**
 * 服务厅表业务接口实现类
 * @ClassName: IBjdjHallService   
 * @Description: 提供服务厅表操作的增删改查   
 * @author HiSay  
 * @date 2015-10-23 15:24:57
 */
public class AirportDepartmentServiceImpl extends SystemOrgServiceImpl
	implements IAirportDepartmentService<AirportDepartment, String> {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(AirportDepartmentServiceImpl.class);

}