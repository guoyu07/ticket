package com.ticket.action;

import com.ticket.util.FileUpload;

/**
 * @Description：文件上传的公共类
 * @author：涂有
 * @date 2015年11月7日 上午11:56:07
 */
public class FileUploadAction extends BaseAction{

	/**
	 * 文件的封装类
	 */
	FileUpload file;
	
	public String execute(){
		
		data = file.saveToPath();
		return TEXT;
	}
	
	
	public FileUpload getFile() {
		return file;
	}

	public void setFile(FileUpload file) {
		this.file = file;
	}
}
