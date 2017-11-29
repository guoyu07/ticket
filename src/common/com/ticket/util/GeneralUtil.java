package com.ticket.util;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 综合工具类
 * @ClassName: GeneralUtil   
 * @Description: 对数据的验证、转换等操作。   
 * @author HiSay  
 * @date Jul 8, 2013 11:38:19 AM   
 *
 */
public class GeneralUtil {
	//地球半径常量
	private static final double EARTH_RADIUS = 6378137.0;
	
	public static final String[] zodiacArr = { "猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊" };
	 
	public static final String[] constellationArr = { "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座" };
	 
	public static final int[] constellationEdgeDay = { 20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22 };
	
	private GeneralUtil() {
	}
	
	public static char[] getAlpha(char start, char end){
		
		char[] chars = new char[end - start + 1];
		for(int i = start; i <= end; i++){
			
			chars[i - start] = (char)i;
		}
		return chars;
	}
	
	/**
	 * 根据日期获取生肖
	 * @return
	 */
	public static String getZodica(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    return zodiacArr[cal.get(Calendar.YEAR) % 12];
	}
	 
	/**
	 * 根据日期获取星座
	 * @return
	 */
	public static String getConstellation(Date date) {
	    if (date == null) {
	        return "";
	    }
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    int month = cal.get(Calendar.MONTH);
	    int day = cal.get(Calendar.DAY_OF_MONTH);
	    if (day < constellationEdgeDay[month]) {
	        month = month - 1;
	    }
	    if (month >= 0) {
	        return constellationArr[month];
	    }
	    // default to return 魔羯
	    return constellationArr[11];
	}
	
	/**
	 * Html字符串转为纯文本字符串
	 * @Title: html2Text 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param inputString
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public static String html2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;// 返回文本字符串
	}
	
	private static final ConvertUtil<String> convertUtil = new ConvertUtil<String>();
	
	/**
	 * 过滤请求参数里面的不合法字符，问号后面的不要。
	 * @param url
	 * @return
	 */
	public static String filterInValidFromRequest(String url) {
		try {
			if(url.indexOf("?") != -1) {
				return url.substring(0, url.indexOf("?"));
			} else {
				return url;
			}
		} catch(Exception e) {
			return url;
		}
	}
	
	/**
	 * 转换金额数字, 保留两位小数
	 * @param money
	 * @return
	 */
	public static Double convertData(Double money) {
		BigDecimal bg = new BigDecimal(money);
		double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}
	
	/**
	 * 给idsValue里面的值都添加上单引号。
	* @Title: convertIdsValueToString 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param idsValue
	* @param @param separator
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String convertIdsValueToString(String idsValue, String separator) {
		try {
			List<String> idsValueList = convertUtil.stringToList(idsValue, separator) ;
			StringBuffer resultStr = new StringBuffer();
			for(String tempStr : idsValueList) {
				resultStr.append("'").append(tempStr).append("'");
				resultStr.append(",");
			}
			return resultStr.substring(0, resultStr.length() - 1);
		} catch(Exception e) {
			return idsValue;
		}
	}
	
	/**
	 * 把首字母变为小写字幕
	* @Title: firstCharacterToLowerCase 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param value
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String firstCharacterToLowerCase(String value) {
		try {
			String v1 = value.substring(0, 1);
			String v2 = value.substring(1);
			v1 = v1.toLowerCase();
			return v1 + v2;
		} catch(Exception e) {
			return value;
		}
	}
	
	/**
	 * 获取constants.properties里面的值
	 * @Title: FreeMarkerUitl
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param @return    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public static String getPropertyValue(String key) {
		try {
			InputStream path = GeneralUtil.class.getClassLoader().getResourceAsStream("constants.properties");
			Properties pros = new Properties();
			pros.load(path);
			return pros.getProperty(key);
		} catch(Exception e) {
			return "";
		}
	}
	
	/**
	 * 判断str是否为数字
	 * @Title: GenericDAOImpl
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param @param str
	 * @param @return    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>检测给定的对象是否不为<code>null</code> 并且是一个 <code>{@link java.lang.Integer}</code> 
	 * 或是一个能被转换为 <code>{@link java.lang.Integer}</code> 对象.</p>
	 * @param checkObj 需要被检测的对象
	 * @return 被检测的对象是一个 <code>{@link java.lang.Integer}</code>
	 * 对象并且不为空则或是一个可以被转换为 {@link java.lang.Integer}
	 * 类型的字符串时返回 <code>true</code>, 否则返回 <code>false</code> . 
	 * */
	public static boolean isInteger(Object checkObj) {
		if (checkObj == null) {
			return false;
		}

		if (String.class.isAssignableFrom(checkObj.getClass())) {
			try {
				Integer.parseInt(checkObj.toString());
				return true;
			} catch (Exception ex) {
				return false;
			}
		}

		if (Integer.class.isAssignableFrom(checkObj.getClass())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * <p>检测给定的对象是否不为 <code>null</code> 并且是一个 <code>{@link java.lang.Long}</code> 
	 * 或是一个能被转换为 <code>{@link java.lang.Long}</code> 对象.</p>
	 * @param checkObj 需要被检测的对象
	 * @return 被检测的对象是一个 <code>{@link java.lang.Long}</code>
	 * 对象并且不为空则或是一个可以被转换为 {@link java.lang.Long}
	 * 类型的字符串时返回 <code>true</code>, 否则返回 <code>false</code> . 
	 * */
	public static boolean isLong(Object checkObj) {
		if (checkObj == null) {
			return false;
		}

		if (String.class.isAssignableFrom(checkObj.getClass())) {
			try {
				Long.parseLong(checkObj.toString());
				return true;
			} catch (Exception ex) {
				return false;
			}
		}

		if (Long.class.isAssignableFrom(checkObj.getClass())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * <p>检测给定的对象是否不为 <code>null</code> 并且是一个 <code>{@link java.lang.Float}</code> 
	 * 或是一个能被转换为 <code>{@link java.lang.Float}</code> 对象.</p>
	 * @param checkObj 需要被检测的对象
	 * @return 被检测的对象是一个 <code>{@link java.lang.Float}</code>
	 * 对象并且不为空则或是一个可以被转换为 {@link java.lang.Float}
	 * 类型的字符串时返回 <code>true</code>, 否则返回 <code>false</code> . 
	 * */
	public static boolean isFloat(Object checkObj) {
		if (checkObj == null) {
			return false;
		}

		if (String.class.isAssignableFrom(checkObj.getClass())) {
			try {
				Float.parseFloat(checkObj.toString());
				return true;
			} catch (Exception ex) {
				return false;
			}
		}

		if (Float.class.isAssignableFrom(checkObj.getClass())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * <p>检测给定的对象是否不为 <code>null</code> 并且是一个 <code>{@link java.lang.Double}</code> 
	 * 或是一个能被转换为 <code>{@link java.lang.Double}</code> 对象.</p>
	 * @param checkObj 需要被检测的对象
	 * @return 被检测的对象是一个 <code>{@link java.lang.Double}</code>
	 * 对象并且不为空则或是一个可以被转换为 {@link java.lang.Double}
	 * 类型的字符串时返回 <code>true</code>, 否则返回 <code>false</code> . 
	 * */
	public static boolean isDouble(Object checkObj) {
		if (checkObj == null) {
			return false;
		}

		if (String.class.isAssignableFrom(checkObj.getClass())) {
			try {
				Double.parseDouble(checkObj.toString());
				return true;
			} catch (Exception ex) {
				return false;
			}
		}
		if (Double.class.isAssignableFrom(checkObj.getClass())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * <p>检则一个对象是否不为空, 如果为字符串则会剔除空格来进行验证.</p>
	 * <p style="color:red;">字符串数据 <code>""</code> 同样为视为 <code>null</code> .</p>
	 * @param checkObj 需要被检测的对象
	 * @return 如查不为空,返回<code>true</code>,如果为空返回<code>false</code>.
	 * */
	public static boolean isNotNull(Object checkObj) {
		if (checkObj == null) {
			return false;
		}

		if (String.class.isAssignableFrom(checkObj.getClass())) {
			if ("".equals(checkObj.toString().trim())
					|| "null".equals(checkObj.toString().trim().toLowerCase())) {
				return false;
			}
		}

		return true;
	}

	/**
	 * <p>检测一个对象是否为空, 如果为字符串则会剔除空格进行验证.</p>
	 * <p style="color:red;">字符串数据 <code>""</code> 同样被视为 <code>null</code> .</p>
	 * @param checkObj 需要被检测的对象.
	 * @return 如果对象为空返回 <code>true</code> 不为空返回 <code>false</code> .
	 * */
	public static boolean isNull(Object checkObj) {
		if (checkObj == null) {
			return true;
		}

		if (String.class.isAssignableFrom(checkObj.getClass())) {
			if (checkObj.toString().trim().length() < 1
					|| "".equals(checkObj.toString().trim())
					|| "null".equals(checkObj.toString().trim().toLowerCase()))
				return true;
		}

		return false;
	}

	/**
	 * 检查字符串是否在minLength和maxLength之间,
	 * 如查strValue为<code>null</code>或minLength大于maxLength都
	 * 将直接返回<code>false</code>.此方法不会剔除字符串中的空格
	 * 来进行检查,如果字符串长度是在minLength和
	 * maxLength之间将返回<code>true</code>
	 * @param strValue 需要检查的字符串
	 * @param minLength 字符串最小长度
	 * @param maxLength 字符串最大长度
	 * @return 在minLength之间返回<code>true</code>,否则返回<code>false</code>.
	 * */
	public static boolean checkLength(String strValue, Integer minLength,
			Integer maxLength) {
		if (strValue == null) {
			return false;
		}

		if (minLength > maxLength) {
			return false;
		}

		if (strValue.length() >= minLength && strValue.length() <= maxLength) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 取得一个按指定编码转换的字符
	 * @param sourceString 需要转换的源字符串
	 * @param changeEncoding 改变后的字符串编码
	 * @return 成功则返回转换成功的字符串,失败则返回<code>null</code>对象
	 * */
	public static String getStringFromEncoding(String sourceString,
			String changeEncoding) {
		try {
			return new String(sourceString.getBytes(), changeEncoding);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * 取得一个按指定编号转换的字符
	 * @param sourceString 需要转换的源字符串
	 * @param getSourceEncoding 取得源字符串的编号
	 * @param changeEncoding 改变后的字符串编码
	 * @return 成功则返回转换成功的字符串,失败则返回<code>null</code>对象
	 * */
	public static String getStringFromEncoding(String sourceString,
			String getSourceEncoding, String changeEncoding) {
		try {
			sourceString = new String(sourceString.getBytes(getSourceEncoding),
					changeEncoding);
			return sourceString;
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * 获得文件存储的随机时间
	 */
	public String getRandomTime() {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddhhmmss");
		String strCreateTime = fmt.format(new Date().getTime()); //文件创建日期
		return strCreateTime;
	}

	/**
	 * 
	 * removeHTMLTag(移除指定字符串的HTML标记)
	 * @param content 指定的字符串
	 * @return {@link java.lang.String} 返回移除HTML标记后的字符串
	 */
	public static String removeHtmlTags(String content) {
		String temp = content.replaceAll("\\s*|\t|\r|\n|nbsp;", "").replaceAll(
				"<.*?>", "").replaceAll("&.*?;", "").replaceAll(
				"^[0-9a-zA-Z\u00ff\uffff]", "");
		return temp;
	}

	/**
	 * 过滤content里面的非法字符和前后空格.
	 * @Title: GeneralUtil
	 * @Description: 对传入的content进行格式过滤.   
	 * @param @param content
	 * @param @return    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public static String filterInvalidCharacterFromContent(String content) {
		try {
			String temp = content.replaceAll("\\s*|\t|\r|\n|nbsp;", "").replaceAll(
					"<.*?>", "").replaceAll("&.*?;", "").replaceAll(
					"^[0-9a-zA-Z\u00ff\uffff]", "").trim();
			return temp;
		} catch(Exception e) {
			return "";
		}
	}
	
	/**
	 * 
	 * getAbsoultePath(获取服务器的绝对路径)
	 * @return 当前项目所在的绝对路径
	 */
	public static String getAbsoultePath() {
		return GeneralUtil.class.getResource("/").getPath();
	}

	/**
	 * 
	 * subString(按照指定的字符串和长度,返回截取长度后的字符串)
	 * @param value 要截取的字符串
	 * @param size  要截取的大小
	 * @return 返回截取后的字符串
	 */
	public static String subString(String value, int size) {
		if (GeneralUtil.isNotNull(value)) {
			if (value.length() < size) {
				return value;
			} else {
				return value.substring(0, size);
			}
		} else {
			return null;
		}
	}

	/**
	 * 
	 * getSizeBySeparator(根据分割符来返回字符串中分割字符串后的个数)
	 * @param strSource 要分割的字符串
	 * @param separator 分隔符号
	 * @return 分割后的个数
	 */
	public static int getSizeBySeparator(String strSource, String separator) {
		int intPos = 0;
		int i = 1;
		while ((intPos = strSource.indexOf(separator)) != -1) {
			String temp = strSource.substring(intPos + 1, strSource.length());
			strSource = temp;
			i++;
		}
		return i;
	}

	/**
	 * 全部替换字符串方法
	 * @param strSource 源字符串
	 * @param strFrom   原字符串
	 * @param strTo     目标字符串
	 * @return 替换后的字符串
	 */
	public String replace(String strSource, String strFrom, String strTo) {
		if (strFrom == null || strFrom.equals("")) {
			return strSource;
		}
		int intFromLen = strFrom.length();
		int intPos;
		String strDest = "";
		while ((intPos = strSource.indexOf(strFrom)) != -1) {
			strDest = strDest + strSource.substring(0, intPos);
			strDest = strDest + strTo;
			strSource = strSource.substring(intPos + intFromLen);
		}
		strDest = strDest + strSource;
		return strDest;
	}

	/**
	 * 部分替换字符串，指定开始替换位置和指定替换次数的函数
	 * @param strSource    源字符串
	 * @param strFrom      原字符串
	 * @param strTo        目标字符串
	 * @param intStart     开始位置
	 * @param intCount     替换次数
	 * @return             替换后的字符串
	 */
	public String replace(String strSource, String strFrom, String strTo,
			int intStart, int intCount) {
		if (intCount < 0) {
			intCount = strSource.length();
		}
		if (strSource == null) {
			return strSource;
		}
		int intFromLen = strFrom.length();
		int runCount = 1;
		int intPos;
		String strDest = "";
		while (((intPos = strSource.indexOf(strFrom, intStart)) != -1)
				&& (runCount <= intCount)) {
			runCount += 1;
			strDest = strDest + strSource.substring(0, intPos);
			strDest = strDest + strTo;
			strSource = strSource.substring(intPos + intFromLen);
		}
		strDest = strDest + strSource;
		return strDest;
	}

	/**
	 * getStringByLen   (获取字符串的长度)
	 * @param sourceStr 源字符串
	 * @param strLen    获取的长度
	 * @param hasOmit   是否忽略 
	 * 	<p>当源字符串的长度大于strLen时, 如果hasOmit为true, 
	 * 	则用...填充, 如果为false则不进行填充</p> 
	 */
	public static String getStringByLen(String sourceStr, int strLen,
			boolean hasOmit) {
		strLen = strLen * 2;
		int newLen = 0; // 计算字符串长度
		int charLen = 0;
		String omitStr = hasOmit ? "..." : "";

		for (int i = 0; i < sourceStr.length(); i++) {
			if ((int) sourceStr.charAt(i) > 255) {
				charLen = 2;
			} else {
				charLen = 1;
			}
			// 获得当前字符串长度
			newLen = newLen + charLen;
			if (newLen >= strLen) {
				// 获得希望的字符位置
				return (sourceStr.substring(0, i) + omitStr).replaceAll(" ",
						"&nbsp;");

			}
		}
		return sourceStr.replaceAll(" ", "&nbsp;");
	}

	/**
	 * 内部UBB代码，目前支持[b],[i],[br],[url],[color],[size]
	 * 
	 * @param ubbstr     源字符串
	 * @param showimg    显示图像？
	 * @param showplayer 显示媒体？
	 * @return
	 */
	public String UBB2HTML(String ubbstr, boolean showimg, boolean showplayer) {
		if (!ubbstr.equals("")) {
			String str = replace(ubbstr, "[b]", "<b>");
			str = replace(str, "[/b]", "</b>");
			str = replace(str, "[i]", "<i>");
			str = replace(str, "[/i]", "</i>");
			str = replace(str, "[br]", "<br>");

			String tempStr = "";
			tempStr = UBBStr(str, "url");
			tempStr = UBBStr(tempStr, "color");
			tempStr = UBBStr(tempStr, "size");
			return tempStr;
		} else {
			return ubbstr;
		}
	}

	public String UBB2HTML(String ubbstr) {
		return UBB2HTML(ubbstr, true, true);
	}

	/**
	 * 内部UBB内部方法
	 * @param str   源字符串
	 * @param key   ubb关键字
	 * @return   ubb2html字符串
	 */
	public String UBBStr(String str, String key) {
		String tempStr = str;
		if (tempStr == null) {
			return tempStr;
		}
		tempStr = replace(tempStr, "[/url]", "</a>");
		tempStr = replace(tempStr, "[/color]", "</font>");
		tempStr = replace(tempStr, "[/size]", "</font>");
		String text = "";
		int begin = 0;
		int end = 0;
		while ((begin = tempStr.indexOf("[" + key + "=", begin)) >= 0) {
			begin = begin + key.length() + 2;
			end = tempStr.indexOf("]", begin);
			text = tempStr.substring(begin, end);
			if (key.equals("url")) {
				tempStr = replace(tempStr, "[url=" + text + "]",
						"<a target=_blank href=\"" + text + "\">", 0, 1);
			}
			if (key.equals("color")) {
				tempStr = replace(tempStr, "[color=" + text + "]",
						"<font color=\"" + text + "\">", 0, 1);
			}
			if (key.equals("size")) {
				tempStr = replace(tempStr, "[size=" + text + "]",
						"<font size=\"" + text + "\">", 0, 1);
			}
		}
		return tempStr;
	}

	/**
	 * 计算两个经纬度之间的距离（返回单位是米）
	 * @Title: getDistance 
	 * @Description: TODO
	 * @param @param longitude1
	 * @param @param latitude1
	 * @param @param longitude2
	 * @param @param latitude2
	 * @param @return    设定文件 
	 * @return double    返回类型 
	 * @throws
	 */
	public static double getDistance(double longitude1, double latitude1, double longitude2, double latitude2) {
		double Lat1 = rad(latitude1);
		double Lat2 = rad(latitude2);
		double a = Lat1 - Lat2;
		double b = rad(longitude1) - rad(longitude2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(Lat1) * Math.cos(Lat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}
	
	/**
	 * 给idsValue里面的值都添加上单引号。
	* @Title: convertIdsValueToString 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param idsValue
	* @param @param separator
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String convertIdsValueToStringByDelete(String idsValue, String separator) {
		try {
			List<String> idsValueList = convertUtil.stringToList(idsValue, separator) ;
			StringBuffer resultStr = new StringBuffer();
			for(String tempStr : idsValueList) {
				resultStr.append(tempStr);
				resultStr.append(",");
			}
			return resultStr.substring(0, resultStr.length() - 1);
		} catch(Exception e) {
			return idsValue;
		}
	}
	
	/**
	 * 将所有的pt替换为px
	 * @param content
	 * @return
	 */
	public static String replacePtToPx(String content){
		try {
			String px = "[Pp][Tt]";
			Pattern p = Pattern.compile(px);
		    Matcher m = p.matcher(content);
		     
		     return m.replaceAll("px");
		} catch (Exception e) {
			return content;
		}
	}

	/**
	 * 获取随机编号
	 * @return
	 */
	public static String getRandomId() {
		StringBuffer sBuffer=new StringBuffer("");    
        for (int i = 0; i < 5; i++) {    
            char randomCharacter=(char)(Math.random()*26+'A');    
            sBuffer.append(randomCharacter);    
        }  
        int num = (int) (Math.random()*90000+10000);
		return sBuffer.toString()+num;
	}
	private static char getRandomCharacter() {    
        // TODO Auto-generated method stub    
        switch ((int)(Math.random()*2)) {    
        case 0:    
            return (char)(Math.random()*26+'A');    
    
        case 1:    
            return (char)(Math.random()*26+'a');    
        }    
        return 0;    
    }    
	/**
	 * 为一个url后面拼接一个参数
	 * @param url
	 * @param name 参数名称
	 * @param value 参数值
	 * @return
	 */
	public static String addParam(String url, String name, String value){
		
		if (GeneralUtil.isNull(url)) {
			
			return null;
		}else{
			
			StringBuffer sb = new StringBuffer(url);
			if(isNotNull(value)){
				
				int index = sb.indexOf("#"); 
				if(index >= 0){
					
					sb.deleteCharAt(index);
				}else if(sb.indexOf("?") >= 0){
					
					sb.append("&");
				}else{
					
					sb.append("?");
				}
		    	
		    	sb.append(name).append("=").append(value);
		    }
			return sb.toString();
		}
	}
	
	/**
	 * 把一个数组用一个分隔符连接起来
	 * @param list
	 * @param separate
	 * @return
	 */
	public static String join(List<String> list, String separate){
		
		StringBuffer sb = new StringBuffer();
		if(list != null){
			
			for(int i = 0; i < list.size(); i++){
				
				sb.append(list.get(i));
				if(i != list.size()-1){
					
					sb.append(separate);
				}
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		
		System.out.println(Arrays.toString(getAlpha('A', 'D')));
//		System.out.println(removeHtmlTags("<P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;'><SPAN STYLE='FONT-SIZE:16PX;COLOR:#666666;LINE-HEIGHT:1.5;'> 根据民航局和国际民航组织的要求，请您仔细阅读以下关于危险品规定的告知。</SPAN></SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'> 含有危险品货物的航空运输必须符合国家及国际的有关规定。托运含有危险品的货物必须如实申报并且正确的识别所含的危险品。属于危险品的物质和制品包括易燃、自然、腐蚀、有毒、爆炸、放射性、感染性和遇湿危险物质以及压缩气体、氧化剂、有机过氧化物和其他相关物质。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#337FE5;'><STRONG>有关法律内容:</STRONG></SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;'><SPAN STYLE='LINE-HEIGHT:1.5;FONT-SIZE:16PX;'>《</SPAN><SPAN STYLE='FONT-SIZE:16PX;COLOR:#666666;LINE-HEIGHT:1.5;'>中华人民共和国民用航空法》</SPAN></SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>第一百零一条……禁止以非危险品品名托运危险品。禁止旅客随身携带危险品乘坐民用航空器。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>第一百一十七条 托运人应当对航空货运单上所填关于货物的说明和声明的正确性负责……</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>《中华人民共和国合同法》</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>第二百九十八条 承运人告知重要事项义务承运人应当向旅客及时告知有关不能正常运输的重要事由和安全运输应当注意的事项。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>《中国民用航空危险品运输管理规定》</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>第276.93条 托运要求 将危险品的包装件或合成包装件提交航空运输前，应当按照本规定和技术细则的规定，保证该危险品不是航空运输禁运的危险品，并正确地进行分类、包装、加标记、贴标签、提交正确填制的危险品航空运输文件。禁止以非危险品品名托运危险品。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#337FE5;'><STRONG>锂电池安全运输提示</STRONG></SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>可携带的锂电池</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>可以作为手提行李携带含不超过100WH锂电池的笔记本电脑、手机、照相机等个人用便携式电子设备及备用电池登上客机。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>限制携带的锂电池</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'> 经航空公司批准，可以携带含超过100WH但不超过160WH锂电池的电子设备登机。每位旅客携带此类备用锂电池不能超过两个，且不能托运。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>禁止携带的锂电池</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>禁止携带或托运超过160WH的大型锂电池或电子设备。如需运送超过160WH的大型锂电池或电子设备应向航空公司申报咨询进行货运。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>锂电池的保护措施</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>备用电池必须单个做好保护以防短路（放入原零售包装或以其他方式将电极绝缘，如在暴露的电极上贴胶带，或将每个电池放入单独的塑料袋或保护盒中）</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>违规携带锂电池的严重后果</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>违规携带锂电池，将危害旅客人身和财产安全。造成严重后果的，还将收到法律制裁。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>易含危险品的物品</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>不是所有的危险品都容易被识别。旅客及托运人申报的普通物品中可能含有未被识别出的危险品，这些物品也可能在邮件或行李中。因此了解隐含危险品的常识是非常必要的。在交运或接收货物、邮件、行李及航空公司随机装载的物品时，应仔细检查，确定其属性后，方可进行操作。如有任何怀疑，均不得轻易放过。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>航空器备件/航空器设备/紧急航材（AOG）部件/运营人物资（COMAT）——可能含有爆炸品（照明弹或烟火信号弹）、化学氧气发生器、废弃的轮胎、压缩气体钢瓶（氧气、二氧化碳、氦气或灭火器）、油漆、胶粘剂、气溶胶、救生设备、设备中的燃油、急救箱、湿电池或锂电池、火柴等。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>汽车、汽车配件（小型汽车，发动机，摩托车）——可能含有湿电池、发动机、灭火器、氮气减震支架、气囊充气机/气囊组件、装有或曾盛装过燃油的化油器或油箱、含有压缩气体的轮胎充气装置等等。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>呼吸器——可能有压缩空气或氧气钢瓶、化学氧气发生器或冷冻液氧。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>野营/远足设备——可能含有易燃气体（丁烷，丙烷等）、易燃液体（煤油，汽油等）、易燃固体（固体酒精，火柴等）或其他危险品。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>化学品——可能含有任何类别符合危险物品定义的物质，尤其是易燃液体、易燃固体、氧化剂、有机过氧化物、毒性或腐蚀性物质。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>集运货物——可能含有任何种类的危险品。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>深冷液化气体——可能有冷冻液化气体，如：液氮，夜氦等。深冷液体之所以危险是因为皮肤与其接触会伤害皮肤组织，而且在狭小的空间一旦泄露会使人窒息。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>精液&NBSP;——可能与固体二氧化碳（干冰）或冷冻液化气体一起包装。参见“液氮罐”。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>气瓶——可能含有压缩或液化气体。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>牙医设备——可能含有易燃树脂或溶剂、压缩或液化气体、汞和放射性物品。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>诊断标本——可能含有感染性物质。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>潜水设备——可能带有通常装有压缩空气或特殊气体混合物的钢瓶（如水中呼吸气罐，背负式气瓶等）。空钢瓶（压力表读数为零）可以收运。潜水灯可能带有铅酸充电电池而且高强度潜水灯在空气中开启时可产生极度的热量。为了安全携带，要将电池和灯泡拆下。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;'><SPAN STYLE='FONT-SIZE:16PX;COLOR:#666666;LINE-HEIGHT:1.5;'>钻探和矿井设备</SPAN><SPAN STYLE='FONT-SIZE:16PX;COLOR:#666666;LINE-HEIGHT:1.5;'>——可能含有爆炸品或其他危险品</SPAN></SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>含冷冻液态氮的绝热包装——可能含有游离液氮。只有在包装以任何朝向放置液氮都不会流出的情况下，才属于非限制性的。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>电气设备——可能含有磁性物质，或在开关传动装置和电子管中可能含汞，或可能含湿电池。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>电动器械（轮椅、移动式辅助设备，草坪车、高尔夫车等）——可能含有湿电池</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>探险设备——可能含有爆炸物质（照明弹）、易燃液体（汽油）、易燃气体（丙烷、野营用气体）或具有其他危险性物品。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>摄影和媒体设备——可能带有爆炸性烟火装置、内燃机的发电机、湿电池、燃油和放热物品。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>冷冻胚胎——可能含有冷冻液化气体或固体二氧化碳（干冰）。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>冷冻食品——可能装于固体二氧化碳（干冰）中，从而对人和动物造成不利影响。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>燃料——可能含有易燃液体，易燃固体或易燃气体。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>燃油控制装置——可能含有易燃液体。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>热气球——可能含有易燃气体钢瓶，灭火气、电池等。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>家居用品——可能含有达到危险品标准的物品，其中包括易燃液体，如溶剂型油漆、胶粘剂、上光剂等；气溶胶；漂白剂，腐蚀剂罐或下水道清洁剂；弹药；火柴等。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>仪器——可能内装有汞的气压计、压力计、汞开关、整流管、温度计等。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>实验室实验设备——可能含有达到危险品标准的物品，特别是易燃液体、易燃固体、氧化剂、有机过氧化物、毒害品或腐蚀品。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>机械部件——可能带有胶黏剂、油漆、密封剂、溶剂、湿性和锂电池、汞、压缩或液化气钢瓶等。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>磁性以及其他类似材料——可能单独或共同的达到规则中的磁性材料标准。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>金属建材/金属管/金属栅栏——可能含有铁磁性材料，应根据特殊装载要求进行装载一面对航空器仪器造成影响。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>医用物品——可能含有达到危险物品标准的物品，特别是易燃液体、易燃固体、氧化剂、有机过氧化物、毒害品或腐蚀品。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>旅客行李——可能含有达到危险品标准的物品例如：烟火、家用易燃液体、腐蚀剂罐或下水道清洁剂、易燃气体或打火机，液化充气罐、野营灶具钢瓶、火柴、弹药、漂白剂、气溶胶等。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>医药品—可能含有符合危险品标准的物品，特别是放射性物质、易燃液体、易燃固体、氧化剂、有机过氧化物、毒性或腐蚀性物质。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>电冰箱、冰柜、空调——可能含有液化气体或氨溶液。当含有非易燃、无毒气体小于12公斤或浓度低于35%的氨溶液少于12升时，为非限制性货物。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>赛车或摩托对设备——可能有发动机、化油器或盛燃油的或仍有残余油的燃油箱、易燃气溶胶、压缩气体钢瓶、硝基甲烷（易燃液体）、以及其他燃油添加剂或湿电池等。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>修理工具箱——可能含有胶黏剂，纤维素、油漆、有机过氧化物、溶剂、树脂等。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>演出，电影及舞台特殊效果设备——可能含有易燃的、易爆的物品或舞台发烟的干冰。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>游泳池用化学品——可能含有氧化剂或腐蚀品。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>电子设备或仪器的开关——可能含有汞。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>工具箱——可能含有爆炸品（射钉枪）、压缩气体、气溶胶、易燃气体（丁烷罐或火炬），易燃胶黏剂、油漆、腐蚀性液体（酸性或碱性的清洁用品等）。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>火炬、发光棒——微型火炬及发光棒可能含有易燃气体且装备有电启动器。大型火炬可能由火炬头（通常带有自燃开关）和含有易燃气体的容器或气瓶组成。</SPAN></P><P><SPAN STYLE='FONT-FAMILY:SIMHEI;FONT-SIZE:16PX;LINE-HEIGHT:1.5;COLOR:#666666;'>疫苗——可能有固体二氧化碳（干冰）。</SPAN></P><P><BR /></P>"));
	}
}
