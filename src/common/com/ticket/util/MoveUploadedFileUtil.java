package com.ticket.util;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 转移上传的文件
 * @ClassName: MoveUploadedFileUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author HiSay 聚名项目部（云南天蝎网络科技有限公司）
 * @date 2014-12-22 下午01:18:04
 */
public class MoveUploadedFileUtil {
	/**
	 * 根据扩展名转移图片
	 * 图片格式转移到/upload/image
	 * flash转移到/upload/flash
	 * 视频转移到/upload/video
	 * 附件转移到/upload/attachment
	 * @param /file
	 */
	public File moveByExtension(String realPath, String directory, InputStream fileInputStream, File file) {
		String fileName = file.getName();
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		String[] imageExts = new String[]{"jpg", "jpeg", "gif", "png", "bmp"};
		String[] flashExts = new String[]{"swf"};
		String[] videoExts = new String[]{"flv", "avi", "rmvb", "rm", "mov", "mp4", "3gp", "wmv", "asf", "mkv"};
		//判断是否是图片
		for(String ext : imageExts) {
			if(extension.equals(ext)) {
				return this.saveFile(realPath, directory, "image", extension, fileInputStream);
			}
		}
		//判断是否是flash
		for(String ext : flashExts) {
			if(extension.equals(ext)) {
				return this.saveFile(realPath, directory, "flash", extension, fileInputStream);
			}
		}
		//判断是否是视频
		for(String ext : videoExts) {
			if(extension.equals(ext)) {
				return this.saveFile(realPath, directory, "video", extension, fileInputStream);
			}
		}
		//其他格式的文件当做附件
		return this.saveFile(realPath, directory, "attachment", extension, fileInputStream);
	}
	
	/**
	 * 根据文件类型和时间保存文件
	 * @param directory 目录upload|temp
	 * @param type 文件的类型 image|flash|video|attachment
	 * @throws InterruptedException 
	 */
	private File saveFile(String realPath, String directory, String type, String ext, InputStream fileInputStream) {
		String filePath;
		String separator = File.separator;
		String createTimeString = new SimpleDateFormat("yyyyMM").format(new Date());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String randomFileName =  DateUtil.getSystemDateTime();
		
		filePath = realPath + directory + separator + type + separator + createTimeString + separator;
		File fileDir = new File(filePath);
		if(!fileDir.isDirectory()) {
			fileDir.mkdirs();
		}
		filePath += randomFileName + "." + ext;
		FileUtil.saveFile(fileInputStream, filePath);
		return new File(filePath);
	}
	
}
