package priv.tuyou.test;
import java.io.IOException;

import priv.tuyou.bo.CpuUsage;
import priv.tuyou.bo.HardIO;
import priv.tuyou.bo.HardUsage;
import priv.tuyou.bo.LoadAverage;
import priv.tuyou.bo.MemoryUsage;
import priv.tuyou.bo.TrafficUsage;

public class Test{

	public static void main(String[] args) throws IOException {
		
//		System.out.println(MemoryUsage.getInstance());
		System.out.println(HardUsage.getInstance());
//		System.out.println(CpuUsage.getInstance());
//		System.out.println(TrafficUsage.getInstance());
//		System.out.println(LoadAverage.getInstance());
//		System.out.println(HardIO.getInstance());
	}
}
