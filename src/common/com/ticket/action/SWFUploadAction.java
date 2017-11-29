package com.ticket.action;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.service.ICommonAnnexService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.FileUtil;
import com.ticket.util.ImageUtil;
import com.ticket.util.SpringUtil;


/**
 * Ajax上传文件的控制器
 * @ClassName: AjaxFileUploadAction
 * @Description: 
 * @author 李应龙
 * @date Aug 24, 2013 11:09:29 AM
 */
public class SWFUploadAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	
	//通用附件业务层
	private ICommonAnnexService commonAnnexService = (ICommonAnnexService) SpringUtil.getObjectFromSpring("commonAnnexService");
	
	private File file = null; //上传的文件
	private String fileFileName = null; //单个上传的文件名称
	private String fileContentType = null; //上传的文件类型
	
	private String[] filenames = null; //批量上传的文件
	private String[] uploads = null;
	
	private String filePath = null; //文件的路径

	private String message = "你已成功上传文件";
	
	private String pressThing = null;     //要打印的内容
	private String waterMarkType = null;  //要打印的类型
	private String position = null;       //要打印的位置
	private String sourceImage = null;    //要打印的原图片
	private String fontColor = null;      //字体颜色
	private String fontSize = null;       //字体大小
    private String alpha = null;          //透明度
    
    private String commonAnnexId = null;    //实体commonAnnexId
	
	/**
	 * 测试方法
	 * @Title: AjaxFileUploadAction
	 * @param @return
	 * @param @throws Exception 
	 * @return 
	 * @throws
	 */
	public String test() throws Exception {
		return "swfUploadTest";
	}
	
	/**
	 * @Title: AjaxFileUploadAction
	 * @Description: 上传文件  
	 * @param @return
	 * @param @throws Exception 
	 * @return 
	 * @throws
	 */
	@Override
	public String execute() throws Exception {
		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;
		try {
			File f = this.getFile();
			inputStream = new FileInputStream(f);
			String unRepeatFileName = createUnRepeatFileName(fileFileName);
			outputStream = new FileOutputStream(this.createTempUploadPath(this.getVersionFlag(), unRepeatFileName));
			byte[] buf = new byte[1024];
			int length = 0; 
			while((length = inputStream.read(buf)) != -1) {
				outputStream.write(buf, 0, length);
			}
			//输出文件路径到页面
			data = parseTempPathToWeb(this.getVersionFlag(), unRepeatFileName);
			return TEXT;
		} catch (Exception e) {
			e.printStackTrace();
			return TEXT;
		} finally {
			inputStream.close();
			outputStream.flush();
			outputStream.close();
		}
	}
	
	/**
	 * @throws ServiceException 
	 * 
	 * @Title: SWFUploadAction
	 * @Description: 删除上传的文件
	 * @param @return 
	 * @return 
	 * @throws
	 */
	public String delete() throws ServiceException {
		//实体id不为空，删除实体
		if (commonAnnexId != null) {
			commonAnnexService.remove(commonAnnexId);
		}
		String fileBasePath = this.getFileBasePath();
		filePath = fileBasePath + filePath.substring(1);
		filePath = filePath.replace("/", File.separator); //将web路径分隔符转换为服务器路径分隔符
		FileUtil.deleteFile(filePath);
		data = "ok";
		return TEXT;
	}
	
	/**
	 * 
	 * @Title: SWFUploadAction
	 * @Description: 打水印
	 * @param @return 
	 * @return 
	 * @throws
	 */
	public String pressWaterMark() {
		try {
			Float x = Float.valueOf(position.split(",")[0]);
			Float y = Float.valueOf(position.split(",")[1]);
			if("0".equals(waterMarkType)) { //打印文字
				sourceImage = this.getFileBasePath() + File.separator + sourceImage;
				
				ImageUtil.pressText(DecoderUtil.UtfDecoder(pressThing), sourceImage, 
						"宋体", Font.PLAIN, Integer.valueOf(fontColor, 16), Integer.valueOf(fontSize), x, y, Float.valueOf(alpha));
			} else {
				//打印图片
				pressThing = this.getFileBasePath() + File.separator + pressThing;
				sourceImage = this.getFileBasePath() + File.separator + sourceImage;
				ImageUtil.pressImage(pressThing, sourceImage, x, y, Float.valueOf(alpha));
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String postfix = sourceImage.substring(sourceImage.lastIndexOf("."));
			String targetFile = this.getFileBasePath() + ContextConstants.UPLOAD_TEMP_PATH + File.separator  + System.currentTimeMillis() + postfix;
			FileUtil.copyFile(new File(sourceImage), new File(targetFile));
			FileUtil.deleteFile(sourceImage);
			data = "ok" + targetFile.substring(targetFile.indexOf(ContextConstants.UPLOAD_TEMP_PATH) - 1).replace("\\", "/");
			return TEXT;
		} catch (Exception e) {
			
			data = "error";
			return TEXT;
		}
	}
	
	/**
	 * 
	 * @Title: SWFUploadAction
	 * @Description: 解析临时上传路径
	 * @param @param fileName
	 * @param @return 
	 * @return 
	 * @throws
	 */
	private String createTempUploadPath(String versionFlag, String fileName) {
		String filePath = this.getFileBasePath() + ContextConstants.UPLOAD_TEMP_PATH 
							+ File.separator + fileName;
		//生成路径
		File f = new File(filePath.substring(0, filePath.lastIndexOf(File.separator)));
		if (!f.exists()) {
			f.mkdirs();
		}
		return filePath;
	}
	
	
	
	/**
	 * 
	 * @Title: SWFUploadAction
	 * @Description: 解析上传路径到web页面  
	 * @param @param fileName
	 * @param @return 
	 * @return 
	 * @throws
	 */
	private String parseTempPathToWeb(String versionFlag, String fileName) {
		String filePath = "/" + ContextConstants.UPLOAD_TEMP_PATH 
		                   + "/" + fileName;
		return filePath;
	}
	
	
	
	
	
	/**
	 * 
	 * @Title: SWFUploadAction
	 * @Description: 创建 不会重复的文件名称  
	 * @param @param fileName
	 * @param @return 
	 * @return 
	 * @throws
	 */
	private String createUnRepeatFileName(String fileName) {
		String postfix = fileName.substring(fileName.indexOf("."), fileName.length());
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return new Date().getTime() + postfix;
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

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	
	public String[] getFilename() {
		return filenames;
	}

	public void setFilename(String[] filename) {
		filenames = filename;
	}

	public String[] getUpload() {
		return uploads;
	}

	public void setUpload(String[] upload) {
		uploads = upload;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getPressThing() {
		return pressThing;
	}

	public void setPressThing(String pressThing) {
		this.pressThing = pressThing;
	}

	public String getWaterMarkType() {
		return waterMarkType;
	}

	public void setWaterMarkType(String waterMarkType) {
		this.waterMarkType = waterMarkType;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSourceImage() {
		return sourceImage;
	}

	public void setSourceImage(String sourceImage) {
		this.sourceImage = sourceImage;
	}

	public String getFontColor() {
		return fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	public String getFontSize() {
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}

	public String getAlpha() {
		return alpha;
	}

	public void setAlpha(String alpha) {
		this.alpha = alpha;
	}

	public String getCommonAnnexId() {
		return commonAnnexId;
	}

	public void setCommonAnnexId(String commonAnnexId) {
		this.commonAnnexId = commonAnnexId;
	}
}
