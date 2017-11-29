package com.ticket.interceptor;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.ticket.pojo.BjdjServiceItem;
import com.ticket.pojo.BjdjServicePackage;
import com.ticket.service.IBjdjServiceItemService;
import com.ticket.service.IBjdjServicePackageItemService;
import com.ticket.service.IBjdjServicePackageService;
import com.ticket.service.ISystemDictionaryService;
/**
 * pc端便捷登机和电瓶车的拦截器
 * @author Administrator
 *
 */
public class PCBjdjInterceptor extends MethodFilterInterceptor{

	private static final long serialVersionUID = 1L;
	@Resource
	protected ISystemDictionaryService dictionaryService;
	@Resource
	private IBjdjServiceItemService serviceItemService;
	@Resource
	private IBjdjServicePackageService servicePackageService;
	@Resource
	private IBjdjServicePackageItemService packageItemService;
	
	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		
		//电瓶车文字
		String electromobile = dictionaryService.getValueByName("electromobile");
		ActionContext.getContext().put("electromobile",electromobile);
		
		//电瓶车价格
		Double electromobilePrice = Double.parseDouble(dictionaryService.getValueByName("electromobile_price"));
		ActionContext.getContext().put("electromobilePrice",electromobilePrice);
		
		//获得便捷登机
		BjdjServicePackage package1 = servicePackageService.getMinPrice();
		ActionContext.getContext().put("package1", package1);
				
		//获取所有的便捷登机项目
		List<BjdjServiceItem> items = serviceItemService.get(package1);
		ActionContext.getContext().put("bjdj_items", items);
		
		return arg0.invoke();
	}
}
