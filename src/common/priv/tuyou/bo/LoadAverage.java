package priv.tuyou.bo;

import java.io.IOException;

import priv.tuyou.util.CommendUtil;

/**
 * 系统负载
 * @author：涂有
 * @date 2017年1月4日 下午4:15:19
 */
public class LoadAverage {

	/**
	 * 一分钟之内的负载
	 */
	private float one;
	
	/**
	 * 五分钟之内的负载
	 */
	private float five;
	
	/**
	 * 十分钟之内的负载
	 */
	private float ten;
	
	public static LoadAverage getInstance() throws IOException{
		
//		17:01:26 up 62 days,  4:37,  3 users,  load average: 0.70, 0.38, 0.28
		
		String result = CommendUtil.exec("uptime");
		System.out.println("result:" + result);
		String[] results = result.split(":");
		String averages = results[results.length - 1];
		String[] average = averages.trim().split(",");
		
		LoadAverage load = new LoadAverage();
		load.setOne(Float.parseFloat(average[0].trim()));
		load.setFive(Float.parseFloat(average[1].trim()));
		load.setTen(Float.parseFloat(average[2].trim()));
		return load;
	}

	public float getOne() {
		return one;
	}

	public void setOne(float one) {
		this.one = one;
	}

	public float getFive() {
		return five;
	}

	public void setFive(float five) {
		this.five = five;
	}

	public float getTen() {
		return ten;
	}

	public void setTen(float ten) {
		this.ten = ten;
	}

	@Override
	public String toString() {
		return "LoadAverage [one=" + one + ", five=" + five + ", ten=" + ten + "]";
	}
}
