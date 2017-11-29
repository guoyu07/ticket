package priv.tuyou.bo;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import priv.tuyou.enumer.Unit;
import priv.tuyou.util.CommendUtil;

/**
 * 内存使用情况
 * @author：涂有
 * @date 2017年1月4日 下午5:04:18
 */
public class MemoryUsage {
	
	/**
	 * 单位
	 */
	private Unit unit;

	/**
	 * 内存总共大小
	 */
	private double total;
	
	/**
	 * 已使用内存大小
	 */
	private double used;
	
	/**
	 * 可使用内存大小
	 */
	private double free;
	
	public static MemoryUsage getInstance() throws IOException{
		
		List<String> result = CommendUtil.execList("free -g");
		
		String line = result.get(1);
		String[] params = line.split("\\s+");
		
		BigDecimal total = new BigDecimal(params[1]);
		BigDecimal used = new BigDecimal(params[2]);
		BigDecimal free = new BigDecimal(params[3]);
		
		MemoryUsage usage = new MemoryUsage();
		usage.setTotal(total.setScale(2, RoundingMode.HALF_UP).doubleValue());
		usage.setFree(free.setScale(2, RoundingMode.HALF_UP).doubleValue());
		usage.setUsed(used.setScale(2, RoundingMode.HALF_UP).doubleValue());
		usage.setUnit(Unit.G);
		
		return usage;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getUsed() {
		return used;
	}

	public void setUsed(double used) {
		this.used = used;
	}

	public double getFree() {
		return free;
	}

	public void setFree(double free) {
		this.free = free;
	}

	@Override
	public String toString() {
		return "MemoryUsage [unit=" + unit + ", total=" + total + ", used=" + used + ", free=" + free + "]";
	}
}
