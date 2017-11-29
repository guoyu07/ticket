package com.ticket.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.ticket.service.IInDataService;
import com.ticket.util.DeleteFileUtil;
import com.ticket.util.GeneralUtil;

/**
 * 导入航班计划的类
 * @author zzf
 *
 */
public class AirPlanAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String filePath;
	
	
	@Resource private IInDataService dataService;
	/**
	 * 表示的是页面的跳转要执行的
	 * @return
	 */
	public String toPage(){
		
		return "toPage";
	}
	
	/**
	 * 这里是导入数据的方法
	 * @return
	 */
	public String intoData(){
		
		boolean flag = false;
		//获取httpServletResponse 对象
		HttpServletResponse httpServletResponse = ServletActionContext.getResponse();
		PrintWriter writer = null;
		try {
			
			writer = httpServletResponse.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		
		JSONObject msg = new JSONObject();
		
		
		try {
		
			if(GeneralUtil.isNotNull(filePath)){
				
				String[] fileNeedPath = filePath.split("\\.");
				String tempFilePath = null;
				if(filePath.indexOf("xlsx") != -1){
					
					tempFilePath = fileNeedPath[0] + ".xlsx";
				} else {
					
					tempFilePath = fileNeedPath[0] + ".xls";
				}
				
				tempFilePath = request.getSession().getServletContext().getRealPath("") + tempFilePath;
				
				if(DeleteFileUtil.fileIsExists(tempFilePath)){	//当前文件存在的情况下才执行
					
					flag = dataService.inDataExcelToDataBase(tempFilePath);
					//删除导入上传导入文件
					DeleteFileUtil.deleteFile(tempFilePath);
				}
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			flag = false;
		}
		
		
		if(flag){		//	表示的是成功
			
			
			msg.put("success", "数据导入成功");
		}else {			//	表示的是失败
			
			msg.put("fail", "数据导入失败");
		}
		
		writer.print(msg);  
		writer.flush();
		writer.close();
		
		return "success";
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
