package com.ticket.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.ticket.constants.ContextConstants;
import com.ticket.pojo.CommonAnnex;
import com.ticket.pojo.UploadFile;
import com.ticket.service.ICommonAnnexService;
import com.ticket.util.FileUtil;
import com.ticket.util.MoveUploadedFileUtil;
import com.ticket.util.WebUtil;

/**
 * 文件上传控制器
 * 
 * @ClassName: JQueryFileUpLoadAction
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author HiSay 聚名项目部（云南天蝎网络科技有限公司）
 * @date 2014-12-22 下午12:51:06
 * 
 */
public class JQueryFileUpLoadAction extends BaseAction implements
		ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	private File picture = null;
	private String pictureFileName = null;
	private String pictureContentType = null;
	private UploadFile uploadFile = null;
	private String fileDirectory = null;
	private String name = null;
	private String versionFlag = ContextConstants.VERSION_FLAG_OF_CHINESE; // 版本标识
	@Resource private ICommonAnnexService commonAnnexService = null;
	
	/**
	 * 删除上传图片
	 */
	public String deleteImage() throws Exception {
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");
			out = response.getWriter();
			String name = null; // 删除的文件名称

			// 判断文件名是否存在
			if (WebUtil.validateRequestParameter(request, "name")) {
				name = request.getParameter("name");
			}

			// 如果文件名不为空, 则执行删除操作
			if (name != null) {
				String fileBasePath = this.getFileBasePath()
						+ ContextConstants.UPLOAD_TEMP_PATH + versionFlag
						+ File.separator;
				// 删除上传的文件
				FileUtil.deleteFile(fileBasePath + name);
			}

			out.print("suc");
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	/**
	 * 删除上传图片 用于程序更新时删除图片
	 */
	public String deleteImageUpdate() throws Exception {
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");
			out = response.getWriter();
			String filePath = null; // 删除的文件名称

			// 判断文件名是否存在
			if (WebUtil.validateRequestParameter(request, "name")) {
				filePath = request.getParameter("name");
			}

			// 如果文件名不为空, 则执行删除操作
			if (filePath != null) {
				CommonAnnex annex = commonAnnexService.queryByUrl(filePath, null) ;
				if(annex != null) {
					filePath = annex.getAnnexPath();
				}
				String fileRealPath = this.getFileBasePath() + filePath;
				// 删除上传的文件
				FileUtil.deleteFile(fileRealPath);
				// 删除附件记录
				commonAnnexService.remove(annex);
			}

			out.print("suc");
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	/**
	 * 批量上传图片
	 * 
	 * @return
	 */
	public String fileUpload() throws Exception {
		try {
			String tt = picture.toString();
			tt = tt.substring(tt.lastIndexOf("_") + 1, tt.lastIndexOf("."));
			// String tt = this.getRandom(picture);

			Integer type = null; // 图片类型 1大图 2小图 3其他文件
			if (WebUtil.validateRequestParameter(request, "type")) {
				type = Integer.parseInt(request.getParameter("type"));
			}
			// 获得容器中上传文件夹所在的物理路径
			// String fileBasePath = this.getFileBasePath() +
			// ContextConstants.FILE_TEMP_PATH;

			// 遍历上传文件写入磁盘
			FileInputStream fileInputStream = null;
			if (this.getPicture() != null) {
				String filename = this.getPictureFileName();
				fileInputStream = new FileInputStream(this.getPicture());
				if (filename == null || filename.trim().equals("")
						|| fileInputStream.available() == 0.0) {
					fileInputStream = null;
					return null;
				}
				try {
					String testFlag = request.getParameter("versionFlag");
					MoveUploadedFileUtil moveFileUtil = new MoveUploadedFileUtil();
					File moveFile = moveFileUtil.moveByExtension(this
							.getFileBasePath(), "temp" + File.separator
							+ testFlag, fileInputStream, new File(filename));
					String moveFileName = moveFile.getName();
					String moveFileId = moveFileName.substring(0, moveFileName
							.lastIndexOf("."));
					UploadFile file = new UploadFile();
					fileDirectory = moveFile.getPath();
					file.setOldName(filename);
					fileDirectory = fileDirectory.substring(fileDirectory
							.indexOf("ROOT") + 5, fileDirectory
							.lastIndexOf(File.separator) + 1);
					fileDirectory = fileDirectory.replace("\\", "/");
					file.setName(fileDirectory + moveFileName);
					file.setId(moveFileId);
					file.setType(type);
					this.setUploadFile(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
		}
		return "fileList";
	}

	@Override
	public void setServletRequest(HttpServletRequest paramHttpServletRequest) {
		this.request = paramHttpServletRequest;
	}

	@Override
	public void setServletResponse(HttpServletResponse paramHttpServletResponse) {
		this.response = paramHttpServletResponse;
	}

	public File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	public String getPictureFileName() {
		return pictureFileName;
	}

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

	public String getPictureContentType() {
		return pictureContentType;
	}

	public void setPictureContentType(String pictureContentType) {
		this.pictureContentType = pictureContentType;
	}

	public String getFileDirectory() {
		return fileDirectory;
	}

	public void setFileDirectory(String fileDirectory) {
		this.fileDirectory = fileDirectory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersionFlag() {
		return versionFlag;
	}

	public void setVersionFlag(String versionFlag) {
		this.versionFlag = versionFlag;
	}

	public UploadFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(UploadFile uploadFile) {
		this.uploadFile = uploadFile;
	}

}