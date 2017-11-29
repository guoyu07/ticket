package com.ticket.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * xml工具类（例如：转为map）
 * @author tuyou
 *
 */
public class XMLUtil {
	
	/**
	 * 返回根节点名称
	 * @param xml
	 * @return
	 * @throws DocumentException
	 */
	public static String getRootElement(String xml) throws DocumentException{
		
		Document document = DocumentHelper.parseText(xml);   
		Element root = document.getRootElement();
		return root.getName();
	}
	
	/**
	 * 遍历xml的节点组装成一个map
	 * @param doc
	 * @return
	 * @throws DocumentException 
	 */
	public static Map<String, String> parseTomap(String xml) throws DocumentException{
		
		Document document = DocumentHelper.parseText(xml);   
		Element root = document.getRootElement();
		return parseTomap(root);
	}
	
	private static Map<String, String> parseTomap(Element element){
		
		Map<String, String> map = new HashMap<String, String>();
		map.put(element.getName(), element.getTextTrim());
		if(element.nodeCount() > 0){
			
			for(Iterator i = element.elementIterator(); i.hasNext();){
				
				Element sub = (Element)i.next();
				Map<String, String> subMap = parseTomap(sub);
				map.putAll(subMap);
			}
		}
		return map;
	}
	
	
	/**
	 * 遍历xml的节点组装成一个map
	 * @param doc
	 * @return
	 * @throws DocumentException 
	 */
	public static TreeMap<String, String> parseToTreemap(String xml) throws DocumentException{
		
		Document document = DocumentHelper.parseText(xml);   
		Element root = document.getRootElement();
		return parseToTreemap(root);
	}
	
	private static TreeMap<String, String> parseToTreemap(Element element){
		
		TreeMap<String, String> map = new TreeMap<String, String>();
		map.put(element.getName(), element.getTextTrim());
		if(element.nodeCount() > 0){
			
			for(Iterator i = element.elementIterator(); i.hasNext();){
				
				Element sub = (Element)i.next();
				Map<String, String> subMap = parseTomap(sub);
				map.putAll(subMap);
			}
		}
		return map;
	}
}
