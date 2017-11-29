package priv.tuyou.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class CommendUtil {

	private static Runtime runtime = Runtime.getRuntime();
	
	public static String exec(String commend) throws IOException{
		
		Process pro = runtime.exec(commend);
		InputStream input = pro.getInputStream();
		String result = IOUtils.toString(input);
		IOUtils.closeQuietly(input);
		return result;
	}
	
	public static List<String> execList(String commend) throws IOException{
		
		Process pro = runtime.exec(commend);
		InputStream input = pro.getInputStream();
		List<String> result = IOUtils.readLines(input);
		IOUtils.closeQuietly(input);
		return result;
	}
}
