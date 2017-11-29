package com.ticket.action;

import java.io.IOException;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import priv.tuyou.bo.CpuUsage;
import priv.tuyou.bo.HardIO;
import priv.tuyou.bo.HardUsage;
import priv.tuyou.bo.LoadAverage;
import priv.tuyou.bo.MemoryUsage;
import priv.tuyou.bo.TrafficUsage;

/**
 * 系统监控控制器
 * @author：涂有
 * @date 2017年1月6日 下午1:59:59
 */
public class MonitorAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	/**
	 * CPU使用率
	 * @author：涂有
	 * @date 2017年1月6日 下午2:01:17
	 * @return
	 * @throws IOException 
	 */
	public String cpuUsage() throws IOException{
		
		CpuUsage usage = CpuUsage.getInstance();
		ActionContext.getContext().put("usage", usage);
		return "cpuUsage";
	}
	
	/**
	 * 系统负载
	 * @author：涂有
	 * @date 2017年1月6日 下午2:13:41
	 * @return
	 * @throws IOException
	 */
	public String loadAverage() throws IOException{
		
		LoadAverage load = LoadAverage.getInstance();
		ActionContext.getContext().put("load", load);
		return "load";
	}
	
	/**
	 * 硬盘使用率
	 * @author：涂有
	 * @date 2017年1月6日 下午2:15:12
	 * @return
	 * @throws IOException
	 */
	public String hardUsage() throws IOException{
		
		HardUsage usage = HardUsage.getInstance();
		ActionContext.getContext().put("usage", usage);
		return "hardUsage";
	}
	
	/**
	 * 硬盘IO
	 * @author：涂有
	 * @date 2017年1月6日 下午2:16:11
	 * @return
	 * @throws IOException
	 */
	public String hardIO() throws IOException{
		
		List<HardIO> ios = HardIO.getInstance();
		ActionContext.getContext().put("ios", ios);
		return "hardIO";
	}
	
	/**
	 * 内存使用率
	 * @author：涂有
	 * @date 2017年1月6日 下午2:17:34
	 * @return
	 * @throws IOException
	 */
	public String memoryUsage() throws IOException{
		
		MemoryUsage usage = MemoryUsage.getInstance();
		ActionContext.getContext().put("usage", usage);
		return "memoryUsage";
	}
	
	/**
	 * 网络使用情况
	 * @author：涂有
	 * @date 2017年1月6日 下午2:19:30
	 * @return
	 * @throws IOException
	 */
	public String trafficUsage() throws IOException{
		
		List<TrafficUsage> usage = TrafficUsage.getInstance();
		ActionContext.getContext().put("usages", usage);
		return "trafficUsage";
	}
}
