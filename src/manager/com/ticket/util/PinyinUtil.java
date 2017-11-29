package com.ticket.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {
   
	/**
     * 将汉字转换为全拼
     * 
     * @param src
     * @return String
     */
    public static String getPinYin(String src) {
        char[] charArr = src.toCharArray();
        String[] temp = new String[charArr.length];
        // 设置汉字拼音输出的格式
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        String distStr = "";
        try {
            for (int i = 0; i < charArr.length; i++) {
                // 判断能否为汉字字符
                if (Character.toString(charArr[i]).matches("[\\u4E00-\\u9FA5]+")) {
                	temp = PinyinHelper.toHanyuPinyinStringArray(charArr[i], format);// 将汉字的几种全拼都存到t2数组中
                    distStr += temp[0];// 取出该汉字全拼的第一种读音并连接到字符串t4后
                } else {
                    // 如果不是汉字字符，间接取出字符并连接到字符串t4后
                	distStr += Character.toString(charArr[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return distStr;
    }

    /**
     * 提取每个汉字的首字母
     * 
     * @param str
     * @return String
     */
    public static String getPinYinHeadChar(String str) {
        try {
        	String convert = "";
            String[] pinyinArray = null;
            for (int j = 0; j < str.length(); j++) {
                // 提取汉字的首字母
                pinyinArray = PinyinHelper.toHanyuPinyinStringArray(str.charAt(j));
                if (pinyinArray != null) {
                    convert += pinyinArray[0].charAt(0);
                } else {
                    convert += str.charAt(j);
                }
            }
            return convert;
        } catch(Exception e) {
        	return null;
        }
    }
    
    /**
     * @author wangjiafeng
     * 获取字符串的第一个字的首字母拼音
     * @method getPinYinByFirstChar
     * @param str
     * @return
     * @return String
     * @date 2014-9-16 下午01:55:31
     */
    public static String getPinYinByFirstChar(String str){
    	 try {
    		 String convert = "";
             String[] pinyinArray = null;
             pinyinArray = PinyinHelper.toHanyuPinyinStringArray(str.charAt(0));
             if (pinyinArray != null) {
                 convert += pinyinArray[0].charAt(0);
             } else {
                 convert += str.charAt(0);
             }
             return convert.toUpperCase();
    	 } catch(Exception e) {
    		 return null;
    	 }
    }

    /**
     * 将字符串转换成ASCII码
     * 
     * @param cnStr
     * @return String
     */
    public static String getCnASCII(String cnStr) {
        try {
        	 StringBuffer strBuf = new StringBuffer();
             // 将字符串转换成字节序列

             byte[] bGBK = cnStr.getBytes();
             for (int i = 0; i < bGBK.length; i++) {
                 // 将每个字符转换成ASCII码

                 strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
             }
             return strBuf.toString();
        } catch(Exception e) {
        	return null;
        }
    }
    
    /**
     * 获取简码和全拼的组合
     * @param str
     * @return
     */
    public static String getPinYinAndHeadChar(String str){
    	return getPinYinHeadChar(str) + "|" + getPinYin(str);
    }
    
}