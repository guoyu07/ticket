package priv.tuyou.bo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import priv.tuyou.enumer.Unit;
import priv.tuyou.util.CommendUtil;

/**
 * 磁盘IO
 * @author：涂有
 * @date 2017年1月4日 下午4:41:59
 */
public class HardIO {

	/**
	 * 设备名称
	 */
	private String deivceName;
	
	/**
	 * 单位
	 */
	private Unit unit;
	
	/**
	 * 每秒处理的IO次数
	 */
	private float tps;
	
	/**
	 * 每秒从设备读取的数据量
	 */
	private float readPs;
	
	/**
	 * 每秒从设备写的数据量
	 */
	private float writePs;
	
	/**
	 * 读取的数据总量
	 */
	private int read;
	
	/**
	 * 写入的数据总量
	 */
	private int write;
	
	public static List<HardIO> getInstance() throws IOException{
		
//		Linux 3.10.0-229.el7.x86_64 (67.230.55.116.broad.km.yn.dynamic.163data.com.cn)  2017年01月06日  _x86_64_        (32 CPU)
//
//		Device:            tps    MB_read/s    MB_wrtn/s    MB_read    MB_wrtn
//		sda               0.79         0.01         0.01      26574      40008
//		sdb               1.66         0.00         0.33      15265    1607323
//		dm-0              0.99         0.00         0.01      23790      34885
//		dm-1              0.41         0.00         0.00       2751       5116
//		dm-2              0.00         0.00         0.00          4          3
//		List<String> result = new ArrayList<String>();
//		result.add("Linux 3.10.0-229.el7.x86_64 (67.230.55.116.broad.km.yn.dynamic.163data.com.cn)  2017年01月06日  _x86_64_        (32 CPU)");
//		result.add("");
//		result.add("Device:            tps    MB_read/s    MB_wrtn/s    MB_read    MB_wrtn");
//		result.add("sda               0.79         0.01         0.01      26574      40008");
//		result.add("sdb               1.66         0.00         0.33      15265    1607323");
//		result.add("dm-0              0.99         0.00         0.01      23790      34885");
//		result.add("dm-1              0.41         0.00         0.00       2751       5116");
//		result.add("dm-2              0.00         0.00         0.00          4          3");
//		result.add("");
		
		List<String> result = CommendUtil.execList("iostat -d -m");
		System.out.println("result:" + result);
		List<HardIO> ios = new ArrayList<HardIO>();
		for(int i = 3; i < result.size(); i++){
			
			String line = result.get(i);
			if("".equals(line.trim())){
				
				break;
			}
			
			String[] params = line.trim().split("\\s+");
			
			HardIO io = new HardIO();
			io.setUnit(Unit.M);
			io.setDeivceName(params[0].trim());
			io.setTps(Float.parseFloat(params[1].trim()));
			io.setReadPs(Float.parseFloat(params[2].trim()));
			io.setWritePs(Float.parseFloat(params[3].trim()));
			io.setRead(Integer.parseInt(params[4].trim()));
			io.setWrite(Integer.parseInt(params[5].trim()));
			ios.add(io);
		}
		return ios;
	}

	public String getDeivceName() {
		return deivceName;
	}

	public void setDeivceName(String deivceName) {
		this.deivceName = deivceName;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public float getTps() {
		return tps;
	}

	public void setTps(float tps) {
		this.tps = tps;
	}

	public float getReadPs() {
		return readPs;
	}

	public void setReadPs(float readPs) {
		this.readPs = readPs;
	}

	public float getWritePs() {
		return writePs;
	}

	public void setWritePs(float writePs) {
		this.writePs = writePs;
	}

	public int getRead() {
		return read;
	}

	public void setRead(int read) {
		this.read = read;
	}

	public int getWrite() {
		return write;
	}

	public void setWrite(int write) {
		this.write = write;
	}

	@Override
	public String toString() {
		return "HardIO [deivceName=" + deivceName + ", unit=" + unit + ", tps=" + tps + ", readPs=" + readPs
				+ ", writePs=" + writePs + ", read=" + read + ", write=" + write + "]";
	}
}
