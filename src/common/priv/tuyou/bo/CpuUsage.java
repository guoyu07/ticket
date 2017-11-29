package priv.tuyou.bo;

import java.io.IOException;
import java.util.List;

import priv.tuyou.util.CommendUtil;

/**
 * CPU使用率
 * @author：涂有
 * @date 2017年1月4日 下午4:17:20
 */
public class CpuUsage {

	/**
	 * 用户时间
	 */
	private float userTime;
	
	/**
	 * 系统时间
	 */
	private float systemTime;
	
	/**
	 * 系统调整进程优先级的时候花费的时间
	 */
	private float niceTime;
	
	/**
	 * 空闲时间
	 */
	private float idleTime;
	
	/**
	 * 等待时间
	 */
	private float waitTime;
	
	/**
	 * 硬件中断处理时间
	 */
	private float hardIrqTime;
	
	/**
	 * 软件中断处理时间
	 */
	private float softIrqTime;
	
	/**
	 * 丢失时间
	 */
	private float stealTime;
	
	public static CpuUsage getInstance() throws IOException{
		
//		Linux 3.10.0-229.el7.x86_64 (67.230.55.116.broad.km.yn.dynamic.163data.com.cn)  2017年01月11日  _x86_64_        (32 CPU)
//
//		18时00分10秒  CPU    %usr   %nice    %sys %iowait    %irq   %soft  %steal  %guest  %gnice   %idle
//		18时00分10秒  all    0.59    0.00    0.12    0.03    0.00    0.00    0.00    0.00    0.00   99.25
		
		List<String> result = CommendUtil.execList("mpstat");
		System.out.println("result:" + result);
		
		if(result.size() >= 2){
			
			String line = result.get(result.size() - 1).trim();
			String[] params = line.split("\\s+");
			float userTime = Float.parseFloat(params[2].trim().split("\\s+")[0]);
			float niceTime = Float.parseFloat(params[3].trim().split("\\s+")[0]);
			float systemTime = Float.parseFloat(params[4].trim().split("\\s+")[0]);
			float waitTime = Float.parseFloat(params[5].trim().split("\\s+")[0]);
			float hardIrqTime = Float.parseFloat(params[6].trim().split("\\s+")[0]);
			float softIrqTime = Float.parseFloat(params[7].trim().split("\\s+")[0]);
			float stealTime = Float.parseFloat(params[8].trim().split("\\s+")[0]);
			float idleTime = Float.parseFloat(params[11].trim().split("\\s+")[0]);
			
			CpuUsage usage = new CpuUsage();
			usage.setUserTime(userTime);
			usage.setSystemTime(systemTime);
			usage.setNiceTime(niceTime);
			usage.setIdleTime(idleTime);
			usage.setWaitTime(waitTime);
			usage.setHardIrqTime(hardIrqTime);
			usage.setSoftIrqTime(softIrqTime);
			usage.setStealTime(stealTime);
			return usage;
		}
		return null;
	}

	public float getUserTime() {
		return userTime;
	}

	public void setUserTime(float userTime) {
		this.userTime = userTime;
	}

	public float getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(float systemTime) {
		this.systemTime = systemTime;
	}

	public float getNiceTime() {
		return niceTime;
	}

	public void setNiceTime(float niceTime) {
		this.niceTime = niceTime;
	}

	public float getIdleTime() {
		return idleTime;
	}

	public void setIdleTime(float idleTime) {
		this.idleTime = idleTime;
	}

	public float getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(float waitTime) {
		this.waitTime = waitTime;
	}

	public float getHardIrqTime() {
		return hardIrqTime;
	}

	public void setHardIrqTime(float hardIrqTime) {
		this.hardIrqTime = hardIrqTime;
	}

	public float getSoftIrqTime() {
		return softIrqTime;
	}

	public void setSoftIrqTime(float softIrqTime) {
		this.softIrqTime = softIrqTime;
	}

	public float getStealTime() {
		return stealTime;
	}

	public void setStealTime(float stealTime) {
		this.stealTime = stealTime;
	}

	@Override
	public String toString() {
		return "CpuUsage [userTime=" + userTime + ", systemTime=" + systemTime + ", niceTime=" + niceTime
				+ ", idleTime=" + idleTime + ", waitTime=" + waitTime + ", hardIrqTime=" + hardIrqTime
				+ ", softIrqTime=" + softIrqTime + ", stealTime=" + stealTime + "]";
	}
}
