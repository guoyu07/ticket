package com.ticket.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.apache.http.HttpResponse;

import com.kdt.api.KdtApiClient;
import com.kdt.common.GlobalConfig;

/**
 * 有赞接入控制器
 * @author xiongwei
 *
 */
public class YouZangAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 获取单个商品信息
	 * @return
	 */
	public String getItem(){
		String method = "kdt.item.get";//单个商品
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("num_iid", "316698362");
		KdtApiClient kdtApiClient;
		HttpResponse response;
		
		try {
			kdtApiClient = new KdtApiClient(GlobalConfig.APP_ID, GlobalConfig.APP_SECRET);
			response = kdtApiClient.get(method, params);
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line);
			}

			System.out.println(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取网点列表
	 * @return
	 */
	public String getOfflines(){
		String method = "kdt.offlines.get";
		HashMap<String, String> params = new HashMap<String, String>();
		KdtApiClient kdtApiClient;
		HttpResponse response;
		
		try {
			kdtApiClient = new KdtApiClient(GlobalConfig.APP_ID, GlobalConfig.APP_SECRET);
			response = kdtApiClient.get(method, params);
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line);
			}

			System.out.println(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取出售中的商品列表
	 * @return
	 */
	public String getOnsale(){
		String method = "kdt.items.onsale.get";
		HashMap<String, String> params = new HashMap<String, String>();
		KdtApiClient kdtApiClient;
		HttpResponse response;
		
		try {
			kdtApiClient = new KdtApiClient(GlobalConfig.APP_ID, GlobalConfig.APP_SECRET);
			response = kdtApiClient.get(method, params);
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line);
			}

			System.out.println(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取仓库中的商品列表
	 * @return
	 */
	public String getInventory(){
		String method = "kdt.items.inventory.get";
		HashMap<String, String> params = new HashMap<String, String>();
		KdtApiClient kdtApiClient;
		HttpResponse response;
		
		try {
			kdtApiClient = new KdtApiClient(GlobalConfig.APP_ID, GlobalConfig.APP_SECRET);
			response = kdtApiClient.get(method, params);
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line);
			}

			System.out.println(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取店铺基本信息
	 * @return
	 */
	public String getBasicShop(){
		String method = "kdt.shop.basic.get";
		HashMap<String, String> params = new HashMap<String, String>();
		KdtApiClient kdtApiClient;
		HttpResponse response;
		
		try {
			kdtApiClient = new KdtApiClient(GlobalConfig.APP_ID, GlobalConfig.APP_SECRET);
			response = kdtApiClient.get(method, params);
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line);
			}

			System.out.println(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取网点商品sku
	 * @return
	 */
	public String getOfflinegoods(){
		String method = "kdt.multistore.offline.goods.sku.get";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("num_iid", "316698362");
		
		KdtApiClient kdtApiClient;
		HttpResponse response;
		
		try {
			kdtApiClient = new KdtApiClient(GlobalConfig.APP_ID, GlobalConfig.APP_SECRET);
			response = kdtApiClient.get(method, params);
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line);
			}

			System.out.println(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
