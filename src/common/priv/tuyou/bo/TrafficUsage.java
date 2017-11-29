package priv.tuyou.bo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import priv.tuyou.util.CommendUtil;

/**
 * 网络使用情况
 * @author：涂有
 * @date 2017年1月4日 下午4:28:43
 */
public class TrafficUsage {

	/**
	 * 网卡名称
	 */
	private String cardName;
	
	/**
	 * 每秒接收的数据包量
	 */
	private float rxpcks;
	
	/**
	 * 每秒发出的数据包量
	 */
	private float txpcks;
	
	/**
	 * 每秒接收的字节数
	 */
	private float rxKB;
	
	/**
	 * 每秒发送的字节数
	 */
	private float txKB;
	
	/**
	 * 每秒收到的压缩包的数量
	 */
	private float rxcmp;
	
	/**
	 * 每秒发送的压缩包数量
	 */
	private float txcmp;
	
	/**
	 * 每秒收到广播包数量
	 */
	private float rxmcst;
	
	public static List<TrafficUsage> getInstance() throws IOException{

//		Linux 3.10.0-123.9.3.el7.x86_64 (tuyou)         01/05/2017      _x86_64_        (1 CPU)
//
//		05:37:15 PM     IFACE   rxpck/s   txpck/s    rxkB/s    txkB/s   rxcmp/s   txcmp/s  rxmcst/s
//		05:37:16 PM      eth0      0.00      0.00      0.00      0.00      0.00      0.00      0.00
//		05:37:16 PM      eth1      2.00      0.00      0.13      0.00      0.00      0.00      0.00
//		05:37:16 PM        lo      0.00      0.00      0.00      0.00      0.00      0.00      0.00
//
//		Average:        IFACE   rxpck/s   txpck/s    rxkB/s    txkB/s   rxcmp/s   txcmp/s  rxmcst/s
//		Average:         eth0      0.00      0.00      0.00      0.00      0.00      0.00      0.00
//		Average:         eth1      2.00      0.00      0.13      0.00      0.00      0.00      0.00
//		Average:           lo      0.00      0.00      0.00      0.00      0.00      0.00      0.00
//		List<String> list = new ArrayList<String>();
//		list.add("Linux 3.10.0-123.9.3.el7.x86_64 (tuyou)         01/05/2017      _x86_64_        (1 CPU)");
//		list.add("05:37:15 PM     IFACE   rxpck/s   txpck/s    rxkB/s    txkB/s   rxcmp/s   txcmp/s  rxmcst/s");
//		list.add("05:37:16 PM      eth0      0.00      0.00      0.00      0.00      0.00      0.00      0.00");
//		list.add("05:37:16 PM      eth1      2.00      0.00      0.13      0.00      0.00      0.00      0.00");
//		list.add("05:37:16 PM        lo      0.00      0.00      0.00      0.00      0.00      0.00      0.00");
//		list.add("");
//		list.add("Average:        IFACE   rxpck/s   txpck/s    rxkB/s    txkB/s   rxcmp/s   txcmp/s  rxmcst/s");
//		list.add("Average:         eth0      0.00      0.00      0.00      0.00      0.00      0.00      0.00");
//		list.add("Average:         eth1      2.00      0.00      0.13      0.00      0.00      0.00      0.00");
//		list.add("Average:           lo      0.00      0.00      0.00      0.00      0.00      0.00      0.00");
		
		List<String> list = CommendUtil.execList("sar -n DEV 1 1");
		List<TrafficUsage> usages = new ArrayList<TrafficUsage>(5);
		for(int i = list.size() - 1; i >= 0; i--){
			
			String line = list.get(i);
			String next = list.get(i - 1);
			if("".equals(next.trim())){
				
				break;
			}
			
			String[] params = line.split("\\s+");
			
			TrafficUsage usage = new TrafficUsage();
			usage.setCardName(params[1]);
			usage.setRxpcks(Float.parseFloat(params[2]));
			usage.setTxpcks(Float.parseFloat(params[3]));
			usage.setRxKB(Float.parseFloat(params[4]));
			usage.setTxKB(Float.parseFloat(params[5]));
			usage.setRxcmp(Float.parseFloat(params[6]));
			usage.setTxcmp(Float.parseFloat(params[7]));
			usage.setRxmcst(Float.parseFloat(params[8]));
			usages.add(usage);
		}
		return usages;
	}
	
	public float getRxpcks() {
		return rxpcks;
	}
	public void setRxpcks(float rxpcks) {
		this.rxpcks = rxpcks;
	}
	public float getTxpcks() {
		return txpcks;
	}
	public void setTxpcks(float txpcks) {
		this.txpcks = txpcks;
	}
	public float getRxKB() {
		return rxKB;
	}
	public void setRxKB(float rxKB) {
		this.rxKB = rxKB;
	}
	public float getTxKB() {
		return txKB;
	}
	public void setTxKB(float txKB) {
		this.txKB = txKB;
	}
	public float getRxcmp() {
		return rxcmp;
	}
	public void setRxcmp(float rxcmp) {
		this.rxcmp = rxcmp;
	}
	public float getTxcmp() {
		return txcmp;
	}
	public void setTxcmp(float txcmp) {
		this.txcmp = txcmp;
	}
	public float getRxmcst() {
		return rxmcst;
	}
	public void setRxmcst(float rxmcst) {
		this.rxmcst = rxmcst;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	@Override
	public String toString() {
		return "TrafficUsage [cardName=" + cardName + ", rxpcks=" + rxpcks + ", txpcks=" + txpcks + ", rxKB=" + rxKB
				+ ", txKB=" + txKB + ", rxcmp=" + rxcmp + ", txcmp=" + txcmp + ", rxmcst=" + rxmcst + "]";
	}
}
