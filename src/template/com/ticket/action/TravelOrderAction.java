package com.ticket.action;

/**
 * 旅游咨询订单的请求控制器
 * @author tuyou
 */
public class TravelOrderAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 国内旅游页面
	 * @return
	 */
	public String domesticTravelPage(){
		
		return "domesticTravelPage";
	}
	
	/**
	 * 国际旅游页面
	 * @return
	 */
	public String foreignTravelPage(){
		
		return "foreignTravelPage";
	}
	
	/**
	 * 自由行页面
	 * @return
	 */
	public String freeWalkerPage(){
		
		return "freeWalkerPage";
	}
	
	/**
	 * 自由行组合页面
	 * @return
	 */
	public String freeWalkerComboPage(){
		
		return "freeWalkerComboPage";
	}
	
	/**
	 * 线路详情页面
	 * @return
	 */
	public String lineDetailPage(){
		
		return "lineDetailPage";
	}
	
	/**
	 * 景点详情页面
	 * @return
	 */
	public String scenicSpotDetailPage(){
		
		return "scenicSpotDetailPage";
	}
	
	/**
	 * 服务组合页面
	 * @return
	 */
	public String serviceCombinationPage(){
		
		return "serviceCombinationPage";
	}
	
	/**
	 * 服务预订页面
	 * @return
	 */
	public String serviceOrderPage(){
		
		return "serviceOrderPage";
	}
	
	/**
	 * 旅游咨询页面
	 * @return
	 */
	public String travelInformationPage(){
		
		return "travelInformationPage";
	}
	
	/**
	 * 旅游咨询页面
	 * @return
	 */
	public String travelInformationPage2(){
		
		return "travelInformationPage2";
	}
	
	/**
	 * 旅游预订页面
	 * @return
	 */
	public String travelOrderPage(){
		
		return "travelOrderPage";
	}
}
