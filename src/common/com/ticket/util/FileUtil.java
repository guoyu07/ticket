package com.ticket.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.constants.ContextConstants;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 文件操作工具类
 * @ClassName: FileUtil   
 * @Description: 对文件进行保存、删除、复制和移动等操作   
 * @author HiSay  
 * @date Jul 8, 2013 11:55:26 AM   
 *
 */
public class FileUtil {
	private FileUtil(){};
	private static final Log log = LogFactory.getLog(FileUtil.class);
	
	/**
	 * 保存文件
	 */
	public static void saveFile(InputStream stream, String filePath) {
		OutputStream bos = null; //文件输出流
		try {
			bos = new FileOutputStream(filePath); //通过输出流构建文件
			int bytesRead = 0; //阅读输入文件流的标识
			byte[] buffer = new byte[1024];
			//读取输入流
			while ((bytesRead = stream.read(buffer, 0, buffer.length)) != -1){
			    bos.write(buffer, 0, bytesRead);//将文件写入服务器
			}           
		} catch(Exception e) {
			log.info("保存文件失败!", e);
		}  finally {
			try {
				if(bos != null) bos.close();
				if(stream != null) stream.close();
			} catch (Exception e1) {
				log.info("保存文件失败!", e1);
			}			
		}
	}
	
	/**
	 * 删除文件
	 */
	public static void deleteFile(String filePath) {
		try {
			File f = new File(filePath);
			f.delete();
		} catch (Exception e) {
			log.info("删除文件失败!", e);
		}
	}
	
	/**
	 * 通过大图片生成小图片
	 */
	public static void saveFileSmall(InputStream stream, String filePath, String shortFilePath)  {
		OutputStream bos = null; //文件输入流
		try {
			bos = new FileOutputStream(filePath); //通过文件路径构建文件输出流
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = stream.read(buffer, 0, buffer.length)) != -1) {
				bos.write(buffer, 0, bytesRead);// 将文件写入服务器
			}
			
			File nfile = new File(filePath);
			if (!nfile.exists())
				nfile.createNewFile();
			
			Image img = ImageIO.read(nfile);
			BufferedImage nimg = new BufferedImage(160, 90, BufferedImage.TYPE_INT_RGB);
			nimg.getGraphics().drawImage(img, 0, 0, 160, 90, null);
			OutputStream out=new FileOutputStream(new File(shortFilePath));//目的图片
		    JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		    encoder.encode(nimg);
		    out.close();
		} catch (Exception e) {
		}
		 finally {
			try {
				if(bos != null) bos.close();
				if(stream != null) stream.close();
			} catch (Exception e1) {
			}	
		}
	}
	
	/**
	 * Remove files from the soure directory to the target directory
	 * Kunming AYKJ Technology Co., Ltd.
	 * Mar 26, 2010 
	 * @param sourcePath  the source directory
	 * @param sourceFile  the source file name
	 * @param destinationPath  the target directory
	 */
	public static void copyFile(String sourcePath, String sourceFile, String destinationPath) {
		try {
			File fromFile = new File(sourcePath + File.separator + sourceFile);
			BufferedInputStream bis  = new BufferedInputStream(new FileInputStream(fromFile));
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destinationPath + File.separator + sourceFile));
			int result;
			while((result=bis.read()) != -1) {
				bos.write(result);
			}
			bos.close();
			bis.close();
		} catch(Exception e) {
			return;
		}
	}
	
	/**
	 * 抽取编辑器里面的本地图片路径
	 * @param content
	 */
	public static void extractEditorImage(String content) {
		
	}
	
	/**
	 * 拷贝文件目录
	 * @Title: FileUtil
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param @param sourceDir
	 * @param @param targetDir    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public static void copyDirectiory(String sourceDir, String targetDir) {
		try {
			// 新建目标目录 
			(new File(targetDir)).mkdirs();
			// 获取源文件夹当前下的文件或目录 
			File[] file = (new File(sourceDir)).listFiles();
			for (int i = 0; i < file.length; i++) {
				if (file[i].isFile()) {
					// 源文件 
					File sourceFile = file[i];
					// 目标文件 
					File targetFile = new File(new File(targetDir)
							.getAbsolutePath()
							+ File.separator + file[i].getName());
					copyFile(sourceFile, targetFile);
				}
				if (file[i].isDirectory()) {
					// 准备复制的源文件夹 
					String dir1 = sourceDir + "/" + file[i].getName();
					// 准备复制的目标文件夹 
					String dir2 = targetDir + "/" + file[i].getName();
					copyDirectiory(dir1, dir2);
				}
			}
		} catch(Exception e) {
		}
	}
	
	/**
	 * 拷贝文件操作
	 * @Title: FileUtil
	 * @Description:拷贝指定文件到指定目录   
	 * @param @param sourceFile 源文件
	 * @param @param targetFile 目标目录(目录文件)
	 * @return  void
	 * @throws
	 */
	public static void copyFile(File sourceFile, File targetFile) {
		try {
			// 新建文件输入流并对它进行缓冲 
			FileInputStream input = new FileInputStream(sourceFile);
			BufferedInputStream inBuff = new BufferedInputStream(input);

			// 新建文件输出流并对它进行缓冲 
			FileOutputStream output = new FileOutputStream(targetFile);
			BufferedOutputStream outBuff = new BufferedOutputStream(output);

			// 缓冲数组 
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流 
			outBuff.flush();

			//关闭流 
			inBuff.close();
			outBuff.close();
			output.close();
			input.close();
		} catch(Exception e) {
		}
	}
	
	/**
	 * 过滤文件路径
	 * @param path
	 * @return
	 */
	public static String filterFilePath(String path) {
		if(GeneralUtil.isNotNull(path) && path.indexOf(ContextConstants.UPLOAD_SEPARATOR_VALUE) != -1) {
			return path.substring(0, path.indexOf(ContextConstants.UPLOAD_SEPARATOR_VALUE));
		} else {
			return path;
		}
	}
	
	/**
	 * 从上传的文件流解析文件名
	 * @param image
	 * @param imageFileName
	 * @param imageContentType
	 * @param fileBasePath
	 * @return
	 */
	public static String parseFile(File image, String imageFileName, String imageContentType, String fileBasePath, String smallPicture) {
		try {
			FileInputStream fileInputStream = null;
			String fileName = imageFileName;
			String outFileName = null;
	        if(image != null){    
                fileInputStream = new FileInputStream(image);
                if(fileName == null || fileName.trim().equals("") || fileInputStream.available() == 0.0) {   
                    return null;
                }
                MoveUploadedFileUtil moveFileUtil = new MoveUploadedFileUtil();
            	File moveFile = moveFileUtil.moveByExtension(fileBasePath, "upload", fileInputStream, new File(fileName));
            	String fileDirectory = moveFile.getPath();
            	fileDirectory = fileDirectory.substring(fileDirectory.indexOf("ROOT") + 5, fileDirectory.lastIndexOf(File.separator) + 1);
            	fileDirectory = fileDirectory.replace("\\", "/");
            	outFileName = fileDirectory + moveFile.getName();
    			outFileName = outFileName.replace("\\", "/");
    			outFileName = "/" + outFileName;
    			return outFileName;
	        } else {
	        	return smallPicture;
	        }
		} catch(Exception e) {
			log.info(e.fillInStackTrace());
			return null;
		}
	}
	
	/**
	 * 从上传的文件流解析文件名集合
	 * @param image
	 * @param imageFileName
	 * @param imageContentType
	 * @param fileBasePath
	 * @return
	 */
	public static List<String> parseFiles(File[] images, String[] imagesFileName, String[] imagesContentType, String fileBasePath, String[] bigPicture) {
		try {
			FileInputStream fileInputStream = null;
			List<String> fileNameList = null;
			if(images != null && images.length > 0) {
				fileNameList = new ArrayList<String>();
				for(int i=0; i<images.length; i++) {
					String fileName = imagesFileName[i];
					String outFileName = null;
					fileInputStream = new FileInputStream(images[i]);
	                if(fileName == null || fileName.trim().equals("") || fileInputStream.available() == 0.0) {   
	                    continue;
	                }
	                MoveUploadedFileUtil moveFileUtil = new MoveUploadedFileUtil();
	            	File moveFile = moveFileUtil.moveByExtension(fileBasePath, "upload", fileInputStream, new File(fileName));
	            	String fileDirectory = moveFile.getPath();
	            	fileDirectory = fileDirectory.substring(fileDirectory.indexOf("ROOT") + 5, fileDirectory.lastIndexOf(File.separator) + 1);
	            	fileDirectory = fileDirectory.replace("\\", "/");
	            	outFileName = fileDirectory + moveFile.getName();
	    			outFileName = outFileName.replace("\\", "/");
	    			outFileName = "/" + outFileName;
	    			fileNameList.add(outFileName);
				}
			} else {
				return Arrays.asList(bigPicture);
			}
	        return fileNameList;
		} catch(Exception e) {
			log.info(e.fillInStackTrace());
			return null;
		}
	}
}