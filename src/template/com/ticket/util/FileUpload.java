package com.ticket.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

/**
 * @Description：件上传类(暂时只支持jpg、png、gif格式的文件)
 * @author：涂有
 * @date 2015年11月7日 下午2:34:01
 */
public class FileUpload {

	/**
	 * 上传的文件数组，不仅支持单文件上传，还支持多文件上传,
	 * 要求表单的文件name必须是files,否则取不到值
	 */
	private File[] files;
	
	/**
	 * 上传的文件的文件名
	 */
	private String[] filesFileName;
	
	/**
	 * 上传的文件的文件类型，支持类型有：jpg(image/jpeg)
	 */
	private String[] filesContentType;
	
	/**
	 * 文件上传的默认目录
	 */
	private String defaultDerectory = "/upload";
	
	/**
	 * 保存上传的文件到默认目录
	 * @return
	 */
	public String saveToPath(){
		
		return saveToPath(defaultDerectory);
	}
	
	/**
	 * 保存上传的文件到指定目录
	 * @param path 指定的目录
	 * @return
	 */
	public String saveToPath(String path){
		
		//要返回的文件相对路径字符串（相对与项目根目录）
		StringBuffer UUIDName = new StringBuffer();
		//获取项目的根目录路径
		String projectRoot = ServletActionContext.getServletContext().getRealPath(File.separator);
		for(int i = 0; i < files.length; i++){
			
			String fileName = null;
			String formatName = null;
			if("image/jpeg".equals(filesContentType[i])){
				
				fileName = UUID.randomUUID() + ".jpg";
				formatName = "jpg";
			}else if("image/gif".equals(filesContentType[i])){
				
				fileName = UUID.randomUUID() + ".gif";
				formatName = "gif";
			}else if("image/png".equals(filesContentType[i])){
				
				fileName = UUID.randomUUID() + ".png";
				formatName = "png";
			}else{
				UUIDName.delete(0, UUIDName.length()).toString();
				continue;
			}
			UUIDName.append(path);
			UUIDName.append("/");
			UUIDName.append(fileName);
			if(i < files.length - 1){
				UUIDName.append(",");
			}
			
			String absolutePath = projectRoot + path + "/" + fileName;
//			try {
//				ImageUtil imageUtil = ImageUtil.fromImageFile(files[i]);
//				int width = imageUtil.getImageWidth();
//				int height = imageUtil.getImageHeight();
//				if(width > 500){ //图片最大只能500，否则压缩
//					
//					ImageUtil.zoomImage(files[i].getPath(), absolutePath, 500, height * 500 / width, formatName);
//				}else{ //否则直接保存
					
					this.write(files[i], new File(absolutePath));
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
		return UUIDName.toString();
	}
	
	/**
	 * 把源文件的内容写到目标文件中
	 * @param source
	 * @param dest
	 */
	private void write(File source, File dest){
		
		try {
			FileInputStream in = new FileInputStream(source);
			FileOutputStream out = new FileOutputStream(dest);
			
			int readed = -1;
			byte[] buffer = new byte[1024];
			while((readed = in.read(buffer)) > 0){
				out.write(buffer, 0, readed);
			}
			
			if(in != null){
				in.close();
			}
			if(out != null){
				out.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}

	public String[] getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileName(String[] filesFileName) {
		this.filesFileName = filesFileName;
	}

	public String[] getFilesContentType() {
		return filesContentType;
	}

	public void setFilesContentType(String[] filesContentType) {
		this.filesContentType = filesContentType;
	}
}
