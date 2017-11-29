package com.ticket.action;

import java.io.File;

import com.ticket.util.CompressPicUtil;
/**
 * 罚单图片上传
 * @author xw
 *
 */
public class FileAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private File file;
	private String fileFileName;
	private String fileContentType;

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
//		System.out.println("名字：" + fileFileName);
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
//		System.out.println("类型：" + fileContentType);
		this.fileContentType = fileContentType;
	}
	public String getFileType(){
		int index = this.getFileFileName().indexOf(".");
		String type = this.getFileFileName().substring(index);
		return type;
	}
	
	/**
	 * 上传图片并返回图片路径
	 * @return
	 * @throws Exception
	 */
	public String loadAndShow() throws Exception {
		File f = this.getFile();
		String type = getFileType();
//		System.out.println(type);//.jpg
		CompressPicUtil util = new CompressPicUtil(f,300,300,true,type);
//		String thumbnailpath = util.getThumbnailPath();//缩略图的路劲
//		String thumbnailoutPath = thumbnailpath.replace("\\", "\\\\");
		String realPath = util.getOutPath();//压缩后图片的路径
//		String realOutPath = realPath.replace("\\", "\\\\");
		if(f == null){
			data = "你未选择需要上传的图片";
		}else{
			long fileLen = file.length()/1024;
			if(fileLen > 200){
				util.compressAllExceptGif(200);
				util.changePicSizeAll();
			}else{
				util.compressAllExceptGif(fileLen);
				util.changePicSizeAll();
			}
			data = realPath;
		}	
		return TEXT;
	}
}
