package com.ticket.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.struts2.ServletActionContext;

import com.ticket.exception.ServiceException;

/**
 * 汇率控制器
 * @author hj
 */
public class BaiduExchangeRateAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
    private String from = null;
    
    private String to=null;
    
    private String amount =null;

    private String httpUrl = "http://apis.baidu.com/apistore/currencyservice/currency";
    
    private String httpUrl_weathers = "http://apis.baidu.com/apistore/weatherservice/recentweathers";
    /**
     * 汇率控制器
     * @return
     * @throws Exception
     */
    public String exchange() throws Exception {
	    BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();
	    httpUrl+="?fromCurrency="+from+"&toCurrency="+to+"&amount="+amount;
        URL url = new URL(httpUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("apikey",  "4b3678622fe81f7e1cfea254ad8c0190");
        connection.connect();
        InputStream is = connection.getInputStream();
        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String strRead = null;
        while ((strRead = reader.readLine()) != null) {
            sbf.append(strRead);
            sbf.append("\r\n");
        }
        reader.close();
        data = sbf.toString();
        return TEXT;
    }
    /**
     * 天气控制器
     * @throws ServiceException
     */
    public void weathers() throws ServiceException {
	    BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();
	    httpUrl_weathers+="?cityname="+from+"&cityid="+to+"&amount="+amount;
	    try {
	        URL url = new URL(httpUrl);
	        HttpURLConnection connection = (HttpURLConnection) url
	                .openConnection();
	        connection.setRequestMethod("GET");
	        connection.setRequestProperty("apikey",  "4b3678622fe81f7e1cfea254ad8c0190");
	        connection.connect();
	        InputStream is = connection.getInputStream();
	        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        String strRead = null;
	        while ((strRead = reader.readLine()) != null) {
	            sbf.append(strRead);
	            sbf.append("\r\n");
	        }
	        reader.close();
	        result = sbf.toString();
	        
	        ServletActionContext.getResponse().setContentType("text/plain;charset=utf-8");
	    	ServletActionContext.getResponse().getWriter().write(result); 
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	
}
    
    
    
	public void setTo(String to) {
		this.to = to;
	}

	public String getTo() {
		return to;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getFrom() {
		return from;
	}
	
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getAmount() {
		return amount;
	}
    
    
	
	
	
}
