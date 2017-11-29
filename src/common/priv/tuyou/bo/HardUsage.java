package priv.tuyou.bo;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import priv.tuyou.enumer.Unit;
import priv.tuyou.util.CommendUtil;

/**
 * 硬盘使用率
 * @author：涂有
 * @date 2017年1月4日 下午4:23:45
 */
public class HardUsage {
	
	/**
	 * 单位
	 */
	private Unit unit;

	/**
	 * 总的
	 */
	private float total;
	
	/**
	 * 已使用
	 */
	private float used;
	
	/**
	 * 可用
	 */
	private float free;
	
	public static HardUsage getInstance() throws IOException{
		
//		df: "/run/user/0/gvfs": 权限不够
//		文件系统                     1K-块     已用       可用 已用% 挂载点
//		/dev/mapper/centos-root   52403200 13960932   38442268   27% /
//		devtmpfs                  32770964        0   32770964    0% /dev
//		tmpfs                     32781016      164   32780852    1% /dev/shm
//		tmpfs                     32781016  3310968   29470048   11% /run
//		tmpfs                     32781016        0   32781016    0% /sys/fs/cgroup
//		/dev/sda2                   508588   120812     387776   24% /boot
//		/dev/sda1                   204580    10000     194580    5% /boot/efi
//		/dev/mapper/centos-home   51752256    73752   51678504    1% /home
//		/dev/sdb2                195063728    32932  195030796    1% /backup
//		/dev/sdb1               1756954196 14345196 1742609000    1% /data
//		tmpfs                      6556204       28    6556176    1% /run/user/0
//		tmpfs                      6556204       12    6556192    1% /run/user/42
//		tmpfs                      6556204        0    6556204    0% /run/user/1002
//		List<String> result = CommendUtil.execList("df -k");
//		result.add("文件系统                     1K-块     已用       可用 已用% 挂载点");
//		result.add("/dev/mapper/centos-root   52403200 13960932   38442268   27% /");
//		result.add("devtmpfs                  32770964        0   32770964    0% /dev");
//		result.add("/dev/sdb1               1756954196 14345196 1742609000    1% /data");
		
		List<String> result = CommendUtil.execList("df -k");
		System.out.println("result:" + result);
		
		BigDecimal unit = new BigDecimal(1024 * 1024);
		BigDecimal total = new BigDecimal(0);
		BigDecimal free = new BigDecimal(0);
		BigDecimal used = new BigDecimal(0);
		total.setScale(2, RoundingMode.CEILING);
		free.setScale(2, RoundingMode.CEILING);
		used.setScale(2, RoundingMode.CEILING);
		
		for(int i = result.size() - 1; i >= 1; i--){
			
			String line = result.get(i);
			if(!"".equals(line.trim())){
				
				String[] params = line.split("\\s+");
				try {
					int totalItem = Integer.parseInt(params[1].trim());
					int usedItem = Integer.parseInt(params[2].trim());
					int freeItem = Integer.parseInt(params[3].trim());
					total = total.add(new BigDecimal(totalItem));
					used = used.add(new BigDecimal(usedItem));
					free = free.add(new BigDecimal(freeItem));
					
				} catch (NumberFormatException e) {
					break;
				}
			}
		}
		total = total.divide(unit);
		free = free.divide(unit);
		used = used.divide(unit);
		
		HardUsage usage = new HardUsage();
		usage.setTotal(total.floatValue());
		usage.setFree(free.floatValue());
		usage.setUsed(used.floatValue());
		usage.setUnit(Unit.G);
		return usage;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public float getUsed() {
		return used;
	}

	public void setUsed(float used) {
		this.used = used;
	}

	public float getFree() {
		return free;
	}

	public void setFree(float left) {
		this.free = left;
	}

	@Override
	public String toString() {
		return "HardUsage [unit=" + unit + ", total=" + total + ", used=" + used + ", free=" + free + "]";
	}
}
