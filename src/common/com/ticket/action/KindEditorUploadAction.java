package com.ticket.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.ticket.constants.ContextConstants;
import com.ticket.service.ICommonAnnexService;
import com.ticket.util.FileUtil;

/**
 * 后台KindEditor编辑器Action
 */
public class KindEditorUploadAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Resource private ICommonAnnexService commonAnnexService = null; //附件业务层
	
	private File imgFile = null;
	private String imgFileFileName = null;
	private String imgFileContentType = null;
	private String[] dir = null;
	private String[] localUrl = null;
	private String[] order = null;
	private String[] path = null;

	// 上传文件
	@SuppressWarnings("unchecked")
	public String fileUpload() throws Exception {
		// 定义允许上传的文件扩展名
		String[] fileTypes = new String[] {"gif", "jpg", "jpeg", "png", "pdf", "doc",
				"xls", "rar", "zip", "swf" , "ppt", "docx", "xlsx", "icon"};
		// 最大文件大小
		long maxSize = 8388608;

		// 设置返回的类型
		ServletActionContext.getResponse().setContentType(
				"text/html; charset=UTF-8");
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");

		// 客户端的笔
		PrintWriter out = ServletActionContext.getResponse().getWriter();

		if (this.getImgFile() == null) {
			out.println(getError("请选择文件。"));
			return null;
		}

		if (this.getImgFile() != null) {
			File file = this.getImgFile();
			String fileName = this.getImgFileFileName();
			FileInputStream fileInputStream = new FileInputStream(file);

			// 检查文件大小
			if (fileInputStream.available() > maxSize) {
				out.println(getError("上传文件大小超过限制。"));
				return null;
			}
			// 检查扩展名
			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			if (!Arrays.<String> asList(fileTypes).contains(fileExt)) {
				out.println(getError("上传文件扩展名是不允许的扩展名。"));
				return null;
			}
			String fileNameReName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileExt;
			String filePath = commonAnnexService.createTargetFile(versionFlag, fileNameReName);
			FileUtil.saveFile(fileInputStream, filePath);

			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			String outFileName = filePath.substring(filePath.indexOf(ContextConstants.UPLOAD_PATH));
			outFileName = outFileName.replace("\\", "/");
			outFileName = "/" + outFileName;
			obj.put("url", outFileName);
			out.println(obj.toJSONString());
		}
		return null;
	}

	// 管理文件
	@SuppressWarnings("unchecked")
	public String fileManager() throws Exception {

		// 根目录路径，可以指定绝对路径，比如 /var/www/attached/
		String rootPath = ServletActionContext.getServletContext().getRealPath(
				"/")
				+ ContextConstants.UPLOAD_PATH + File.separator;
		// 根目录URL，可以指定绝对路径，比如 http://www.yoursite.com/attached/
		String rootUrl = ServletActionContext.getRequest().getContextPath()
				+ ContextConstants.UPLOAD_PATH + File.separator;
		// 图片扩展名
		String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png", "pdf" };

		// 根据path参数，设置各路径和URL
		String path = ServletActionContext.getRequest().getParameter("path") != null ? ServletActionContext
				.getRequest().getParameter("path")
				: "";
		String currentPath = rootPath + path;
		String currentUrl = rootUrl + path;
		String currentDirPath = path;
		String moveupDirPath = "";
		if (!"".equals(path)) {
			String str = currentDirPath.substring(0,
					currentDirPath.length() - 1);
			moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str
					.lastIndexOf("/") + 1) : "";
		}

		// 排序形式，name or size or type
		String order = ServletActionContext.getRequest().getParameter("order") != null ? ServletActionContext
				.getRequest().getParameter("order").toLowerCase()
				: "name";

		// 不允许使用..移动到上一级目录
		if (path.indexOf("..") >= 0) {
			ServletActionContext.getResponse().getWriter().println(
					"Access is not allowed.");
			return null;
		}
		// 最后一个字符不是/
		if (!"".equals(path) && !path.endsWith("/")) {
			ServletActionContext.getResponse().getWriter().println(
					"Parameter is not valid.");
			return null;
		}
		// 目录不存在或不是目录
		File currentPathFile = new File(currentPath);
		if (!currentPathFile.isDirectory()) {
			ServletActionContext.getResponse().getWriter().println(
					"Directory does not exist.");
			return null;
		}

		// 遍历目录取的文件信息
		List<Hashtable> fileList = new ArrayList<Hashtable>();
		if (currentPathFile.listFiles() != null) {
			for (File file : currentPathFile.listFiles()) {
				Hashtable<String, Object> hash = new Hashtable<String, Object>();
				String fileName = file.getName();
				if (file.isDirectory()) {
					hash.put("is_dir", true);
					hash.put("has_file", (file.listFiles() != null));
					hash.put("filesize", 0L);
					hash.put("is_photo", false);
					hash.put("filetype", "");
				} else if (file.isFile()) {
					String fileExt = fileName.substring(
							fileName.lastIndexOf(".") + 1).toLowerCase();
					hash.put("is_dir", false);
					hash.put("has_file", false);
					hash.put("filesize", file.length());
					hash.put("is_photo", Arrays.<String> asList(fileTypes)
							.contains(fileExt));
					hash.put("filetype", fileExt);
				}
				hash.put("filename", fileName);
				hash.put("datetime",
						new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file
								.lastModified()));
				fileList.add(hash);
			}
		}

		if ("size".equals(order)) {
			Collections.sort(fileList, new SizeComparator());
		} else if ("type".equals(order)) {
			Collections.sort(fileList, new TypeComparator());
		} else {
			Collections.sort(fileList, new NameComparator());
		}
		JSONObject result = new JSONObject();
		result.put("moveup_dir_path", moveupDirPath.replace("\\", "/"));
		result.put("current_dir_path", currentDirPath.replace("\\", "/"));
		result.put("current_url", currentUrl.replace("\\", "/"));
		result.put("total_count", fileList.size());
		result.put("file_list", fileList);

		ServletActionContext.getResponse().setContentType(
				"application/json; charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(
				result.toJSONString());
		return null;
	}

	// 删除文件
	public String fileDelete() throws Exception {
		PrintWriter out = null;
		try {
			ServletActionContext.getResponse().setContentType(
					"text/html;charset=utf-8");
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			out = ServletActionContext.getResponse().getWriter();

			// 删除的文件路径
			String fileUrl = ServletActionContext.getRequest().getParameter(
					"fileUrl") != null ? ServletActionContext.getRequest()
					.getParameter("fileUrl") : "";
			fileUrl = java.net.URLDecoder.decode(fileUrl, "UTF-8");
			String fullUrl = ServletActionContext.getServletContext()
					.getRealPath("/")
					+ fileUrl.substring(1);
			deleteFile(fullUrl);
			out.print("suc");
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}

	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	public String getImgFileFileName() {
		return imgFileFileName;
	}

	public void setImgFileFileName(String imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}

	public String getImgFileContentType() {
		return imgFileContentType;
	}

	public void setImgFileContentType(String imgFileContentType) {
		this.imgFileContentType = imgFileContentType;
	}

	/**
	 * 保存文件
	 */
	public static void saveFile(InputStream stream, String filePath) {
		OutputStream bos = null; // 文件输出流
		try {
			bos = new FileOutputStream(filePath); // 通过输出流构建文件
			int bytesRead = 0; // 阅读输入文件流的标识
			byte[] buffer = new byte[1024];
			// 读取输入流
			while ((bytesRead = stream.read(buffer, 0, buffer.length)) != -1) {
				bos.write(buffer, 0, bytesRead);// 将文件写入服务器
			}
		} catch (Exception e) {
		} finally {
			try {
				if (bos != null)
					bos.close();
				if (stream != null)
					stream.close();
			} catch (Exception e1) {
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
		}
	}

	public String[] getDir() {
		return dir;
	}

	public void setDir(String[] dir) {
		this.dir = dir;
	}

	public String[] getLocalUrl() {
		return localUrl;
	}

	public void setLocalUrl(String[] localUrl) {
		this.localUrl = localUrl;
	}

	public String[] getOrder() {
		return order;
	}

	public void setOrder(String[] order) {
		this.order = order;
	}

	public String[] getPath() {
		return path;
	}

	public void setPath(String[] path) {
		this.path = path;
	}
	
}

/**
 * 按名称排序的排序器
 * 
 * @author 海水
 * 
 */
@SuppressWarnings( { "unchecked" })
class NameComparator implements Comparator {
	public int compare(Object a, Object b) {
		Hashtable hashA = (Hashtable) a;
		Hashtable hashB = (Hashtable) b;
		if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
			return -1;
		} else if (!((Boolean) hashA.get("is_dir"))
				&& ((Boolean) hashB.get("is_dir"))) {
			return 1;
		} else {
			return ((String) hashA.get("filename")).compareTo((String) hashB
					.get("filename"));
		}
	}
}

/**
 * 按大小排序的排序器
 * 
 * @author 海水
 * 
 */
@SuppressWarnings( { "unchecked" })
class SizeComparator implements Comparator {
	public int compare(Object a, Object b) {
		Hashtable hashA = (Hashtable) a;
		Hashtable hashB = (Hashtable) b;
		if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
			return -1;
		} else if (!((Boolean) hashA.get("is_dir"))
				&& ((Boolean) hashB.get("is_dir"))) {
			return 1;
		} else {
			if (((Long) hashA.get("filesize")) > ((Long) hashB.get("filesize"))) {
				return 1;
			} else if (((Long) hashA.get("filesize")) < ((Long) hashB
					.get("filesize"))) {
				return -1;
			} else {
				return 0;
			}
		}
	}
}

/**
 * 按类型排序的排序器
 * 
 * @author 海水
 * 
 */
@SuppressWarnings( { "unchecked" })
class TypeComparator implements Comparator {
	public int compare(Object a, Object b) {
		Hashtable hashA = (Hashtable) a;
		Hashtable hashB = (Hashtable) b;
		if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
			return -1;
		} else if (!((Boolean) hashA.get("is_dir"))
				&& ((Boolean) hashB.get("is_dir"))) {
			return 1;
		} else {
			return ((String) hashA.get("filetype")).compareTo((String) hashB
					.get("filetype"));
		}
	}
}

