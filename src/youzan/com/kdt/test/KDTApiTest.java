package com.kdt.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kdt.api.KdtApiClient;
import com.kdt.common.GlobalConfig;

/*
 * 这是个例子
 */
public class KDTApiTest {
	
	public static void main(String[] args){
		get();
//		add();
	}
	
	/*
	 * 测试获取单个商品信息
	 */
	private static void get(){
//		String method = "kdt.item.get";//单个商品
//		String method = "kdt.itemcategories.tags.get";//获取商品自定义标签列表
//		String method = "kdt.items.inventory.get";//获取仓库中的商品列表
		String method = "kdt.items.onsale.get";//获取出售中的商品列表
//		String method = "kdt.regions.get";//获取区域地名列表信息
//		String method = "kdt.offlines.get";//获取网点列表
//		String method = "kdt.shop.basic.get";//获取店铺基本信息
//		String method = "kdt.multistore.offline.goods.sku.get";//获取网点商品sku
		HashMap<String, String> params = new HashMap<String, String>();
//		params.put("alias", "cjiwtcjz");
//		params.put("num_iid", "316698362");
		//params.put("page_size", "20");
//		params.put("level", "1");
		
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
			JSONObject jsonObject = JSONObject.parseObject(result.toString().substring(12,result.length()-1));
			JSONArray array = jsonObject.getJSONArray("items");
			if(array.size() > 0){
				for(int i=0;i<array.size();i++){
					JSONObject jsonObject2 = array.getJSONObject(i);
					String name = jsonObject2.getString("title");
					String num_iid = jsonObject2.getString("num_iid");
					String alias = jsonObject2.getString("alias");
					String detail_url = jsonObject2.getString("detail_url");
					String price = jsonObject2.getString("price");
					String sold_num = jsonObject2.getString("sold_num");
					String listing = jsonObject2.getString("is_listing");
				}
			}
//			JSONObject jsonObject2 = jsonObject.getJSONObject("items");
//			JSONArray array = JSONArray.parseArray(result.toString().substring(12,result.length()-1));
			System.out.println(result.toString().substring(12,result.length()-1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 测试获取添加商品
	 */
	private static void add() {
		String method = "kdt.item.add";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("price", "999.01");
		params.put("title", "测试商品，请忽略");
		params.put("desc", "这是一个号商铺");
		params.put("is_virtual", "0");
		params.put("post_fee", "0");
		params.put("tag_ids", "10560440");
		params.put("quantity", "123");
//		params.put("cid", "4000000");
		params.put("sku_properties", "");
		params.put("sku_quantities", "");
		params.put("sku_prices", "");
		params.put("sku_outer_ids", "");
		String fileKey = "images[]";
		List<String> filePaths = new ArrayList<String>();
		
		// 图片路径
		filePaths.add("/Users/xiongwei/图片/1.jpg");
		filePaths.add("/Users/xiongwei/图片/1.jpg");
		
		KdtApiClient kdtApiClient;
		HttpResponse response;
		
		try {
			kdtApiClient = new KdtApiClient(GlobalConfig.APP_ID, GlobalConfig.APP_SECRET);
			response = kdtApiClient.post(method, params, filePaths, fileKey);
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
	}
}
