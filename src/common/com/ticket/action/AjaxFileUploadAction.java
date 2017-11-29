package com.ticket.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.struts2.ServletActionContext;

/**
 * Ajax上传文件的控制器
 * @ClassName: AjaxFileUploadAction
 * @Description: 
 * @author HiSay
 * @date Aug 24, 2013 11:09:29 AM
 */
public class AjaxFileUploadAction extends BaseAction {
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */ 
	private static final long serialVersionUID = 1L;
	
	private File file = null;
	private String fileFileName = null;
	private String fileFileContentType = null;

	private String message = "你已成功上传文件";

	/**
	 * 测试方法
	 * @Title: AjaxFileUploadAction
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param @return
	 * @param @throws Exception 
	 * @return 
	 * @throws
	 */
	public String test() throws Exception {
		return "ajaxFileUploadTest";
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileFileContentType() {
		return fileFileContentType;
	}

	public void setFileFileContentType(String fileFileContentType) {
		this.fileFileContentType = fileFileContentType;
	}

	@SuppressWarnings("deprecation")
	@Override
	public String execute() throws Exception {
		String path = ServletActionContext.getRequest().getRealPath("/upload");
		try {
			File f = this.getFile();
			if (this.getFileFileName().endsWith(".exe")) {
				message = "对不起,你上传的文件格式不允许!!!";
				return ERROR;
			}
			FileInputStream inputStream = new FileInputStream(f);
			FileOutputStream outputStream = new FileOutputStream(path + "/" + this.getFileFileName());
			byte[] buf = new byte[1024];
			int length = 0;
			while((length = inputStream.read(buf)) != -1) {
				outputStream.write(buf, 0, length);
			}
			inputStream.close();
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
			message = "对不起,文件上传失败了!!!!";
		}
		return SUCCESS;
	}
}
