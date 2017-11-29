package com.ticket.serviceImpl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonAnnex;
import com.ticket.service.ICommonAnnexService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.FileUtil;
import com.ticket.util.GeneralUtil;

/**
 * 公共附件表业务接口实现类
 * 
 * @ClassName: ICommonAnnexService
 * @Description: 提供公共附件表操作的增删改查
 * @author HiSay
 * @date 2013-09-16 16:05:53
 * 
 */
public class CommonAnnexServiceImpl extends
		BaseServiceImpl<CommonAnnex, String> implements ICommonAnnexService {

	//private static final Log log = LogFactory.getLog(CommonAnnexServiceImpl.class);

	@Override
	public boolean remove(String id) throws ServiceException {
		CommonAnnex commonAnnex = dbDAO.queryById(this
				.getTableNameFromEntity(CommonAnnex.class), id);
		dbDAO.remove(commonAnnex);
		return true;
	}

	@Override
	public List<CommonAnnex> queryListByEntityId(String entityId,
			Integer annexType, Integer size) throws ServiceException {
		orderBy = "s.status.orderValue desc, s.id desc";
		if (annexType != null) {
			return dbDAO.queryByList(this
					.getTableNameFromEntity(CommonAnnex.class), deleteFlag,
					versionFlag, "and s.entityId=?3 and s.annexType=?4",
					new Object[] { entityId, annexType }, orderBy, size);
		} else {
			return dbDAO.queryByList(this
					.getTableNameFromEntity(CommonAnnex.class), deleteFlag,
					versionFlag, "and s.entityId=?3",
					new Object[] { entityId }, orderBy, size);
		}
	}

	@Override
	public CommonAnnex queryCommonAnnexByEntityId(String entityId,
			Integer annexType) throws ServiceException {
		return this.queryListByEntityId(entityId, annexType, 1).get(0);
	}

	@Override
	public boolean removeByEntityId(String entityId) throws ServiceException {
		List<CommonAnnex> list = this.queryListByEntityId(entityId, null, null);
		if (list != null && !list.isEmpty()) {
			List<String> annexPathList = new ArrayList<String>();
			for (CommonAnnex annex : list) {
				if (GeneralUtil.isNotNull(annex.getAnnexPath())) {
					annexPathList.add(annex.getAnnexPath());
				}
				dbDAO.remove(annex);
			}
			if (!annexPathList.isEmpty()) {
				for (String path : annexPathList) {
					FileUtil.deleteFile(getFileBasePath() + path);
				}
			}
		}
		return true;
	}

	@Override
	public boolean persist(String files, String annexTitle,
			String annexContent, String annexOrderValue, String entityName,
			String entityId, String versionFlag) {
		if (files != null) {
			String[] fileArray = files.split(",");
			String annexPostfix = null; // 附件后缀
			String annexPath = null; // 附件路径
			String annexType = null; // 附件类型
			Long annexSize = null; // 附件大小
			CommonAnnex annex = null;

			// 解析附件的字段
			String[] annexTitles = null;
			String[] annexContents = null;
			String[] annexOrderValues = null;
			if (annexTitle != null) {
				annexTitles = annexTitle.split(",");
			}
			if (annexContent != null) {
				annexContents = annexContent.split(",");
			}
			if (annexOrderValue != null) {
				annexOrderValues = annexOrderValue.split(",");
			}

			int i = 0;
			for (String file : fileArray) {
				file = file.trim();
				annexType = file.substring(file
						.indexOf(ContextConstants.ANNEX_TYPE_SEPARATOR)
						+ ContextConstants.ANNEX_TYPE_SEPARATOR.length());
				annexPath = moveAnnex(versionFlag, file.substring(0, file
						.indexOf(ContextConstants.ANNEX_TYPE_SEPARATOR)));
				annexPostfix = annexPath
						.substring(annexPath.lastIndexOf(".") + 1);
				annexSize = getAnnexSize(annexPath);

				annex = new CommonAnnex();

				annex.setTitle(annexTitles[i]);
				annex.setContent(annexContents[i]);
				annex.getStatus().setOrderValue(
						Integer.valueOf(annexOrderValues[i].trim()));

				annex.getStatus().setVersionFlag(versionFlag);
				annex.setAnnexPath(annexPath);
				annex.setAnnexSize(annexSize);
				annex.setAnnexType(Integer.valueOf(annexType));
				annex.setEntityName(entityName);
				annex.setEntityId(entityId);
				annex.setExtensionName(annexPostfix);
				dbDAO.persist(annex);
				i++;
			}
		}
		return true;
	}

	/**
	 * 
	 * @Title: CommonAnnexServiceImpl
	 * @Description: 转移附件
	 * @param @param versionFlag
	 * @param @param filePath
	 * @param @return
	 * @return
	 * @throws
	 */
	private String moveAnnex(String versionFlag, String filePath) {
		File tempFile = new File(this.getFileBasePath() + filePath);
		String tempFileName = tempFile.getName();
		String targetFilePath = createTargetFile(versionFlag, tempFileName);
		File targetFile = new File(targetFilePath);
		if (!tempFile.getPath().equals(targetFile.getPath())) { // 服务器的图片可以重用
			FileUtil.copyFile(tempFile, targetFile);
			FileUtil.deleteFile(this.getFileBasePath() + filePath);
		}
		return targetFilePath.substring(
				targetFilePath.indexOf(ContextConstants.UPLOAD_PATH) - 1)
				.replace(File.separator, "/");
	}

	/**
	 * 
	 * @Title: CommonAnnexServiceImpl
	 * @Description: 创建上传路径
	 * @param @param fileName
	 * @param @return
	 * @return
	 * @throws
	 */
	@Override
	public String createTargetFile(String versionFlag, String fileName) {
		String filePath = this.getFileBasePath() + ContextConstants.UPLOAD_PATH
				+ File.separator + versionFlag + File.separator
				+ this.parseTypeByFileName(fileName) + File.separator
				+ this.createDateDir() + File.separator + fileName;
		// 生成路径
		File f = new File(filePath.substring(0, filePath
				.lastIndexOf(File.separator)));
		if (!f.exists()) {
			f.mkdirs();
		}
		return filePath;
	}

	/**
	 * 
	 * @Title: SWFUploadAction
	 * @Description: 根据文件类别解析类别
	 * @param @param fileName
	 * @param @return
	 * @return
	 * @throws
	 */
	public String parseTypeByFileName(String fileName) {
		String postfix = fileName.substring(fileName.lastIndexOf("."), fileName
				.length());
		// 图片格式
		String[] picturePostfixArray = new String[] { ".bmp", ".jpg", ".jpeg",
				".png", ".gif" };
		for (String p : picturePostfixArray) {
			if (postfix.equals(p)) {
				return ContextConstants.PICTURE_UPLOAD_PATH;
			}
		}
		// 视频格式
		picturePostfixArray = new String[] { ".flv", ".avi", ".mp4" };
		for (String p : picturePostfixArray) {
			if (postfix.equals(p)) {
				return ContextConstants.VIDEO_UPLOAD_PATH;
			}
		}
		// 音频格式
		picturePostfixArray = new String[] { ".mp3" };
		for (String p : picturePostfixArray) {
			if (postfix.equals(p)) {
				return ContextConstants.VOICE_UPLOAD_PATH;
			}
		}
		// flash格式
		picturePostfixArray = new String[] { ".swf" };
		for (String p : picturePostfixArray) {
			if (postfix.equals(p)) {
				return ContextConstants.FLASH_UPLOAD_PATH;
			}
		}
		// zip格式
		picturePostfixArray = new String[] { ".zip" };
		for (String p : picturePostfixArray) {
			if (postfix.equals(p)) {
				return ContextConstants.ZIP_UPLOAD_PATH;
			}
		}
		// rar格式
		picturePostfixArray = new String[] { ".rar" };
		for (String p : picturePostfixArray) {
			if (postfix.equals(p)) {
				return ContextConstants.RAR_UPLOAD_PATH;
			}
		}
		// doc格式
		picturePostfixArray = new String[] { ".doc", ".wps" };
		for (String p : picturePostfixArray) {
			if (postfix.equals(p)) {
				return ContextConstants.DOC_UPLOAD_PATH;
			}
		}
		// excel格式
		picturePostfixArray = new String[] { ".xls", ".et" };
		for (String p : picturePostfixArray) {
			if (postfix.equals(p)) {
				return ContextConstants.EXCEL_UPLOAD_PATH;
			}
		}
		// other格式
		return "other";
	}

	/**
	 * 
	 * @Title: SWFUploadAction
	 * @Description: 创建日期文件夹
	 * @param @return
	 * @return
	 * @throws
	 */
	private String createDateDir() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		return sdf.format(new Date());
	}

	/**
	 * 
	 * @Title: CommonAnnexServiceImpl
	 * @Description: 获取附件的大小
	 * @param @return
	 * @return
	 * @throws
	 */
	private Long getAnnexSize(String filePath) {
		if (filePath != null) {
			File file = new File(this.getFileBasePath() + filePath);
			if (file.exists()) {
				return file.length();
			}
		}
		return 0L;
	}

	@Override
	public String getFirstFiles(String files) {
		if (files != null) {
			String filePath = files.substring(0, files
					.indexOf(ContextConstants.ANNEX_TYPE_SEPARATOR));

			File tempFile = new File(this.getFileBasePath() + filePath);
			String tempFileName = tempFile.getName();
			String targetFilePath = createTargetFile(versionFlag, tempFileName);

			return targetFilePath.substring(
					targetFilePath.indexOf(ContextConstants.UPLOAD_PATH) - 1)
					.replace(File.separator, "/");
		}
		return null;
	}

	@Override
	public List<File> queryDirectoryList(String parentFile) {
		if (parentFile != null) {
			List<File> fileList = new ArrayList<File>();
			if ("/".equals(parentFile) || "".equals(parentFile)) { // 显示附件的根目录
				String realParentDir = this.getFileBasePath();
				File file = new File(realParentDir);
				File[] files = file.listFiles();
				if (files != null && files.length > 0) {
					for (int i = 0; i < files.length; i++) {// 过滤非附件目录
						if (ContextConstants.UPLOAD_TEMP_PATH.equals(files[i]
								.getName())
								|| ContextConstants.UPLOAD_PATH.equals(files[i]
										.getName())) {
							fileList.add(files[i]);
						}
					}
				}
				return fileList;
			} else { // 在附件目录中加载目录
				String realParentDir = this.getFileBasePath() + parentFile;
				File file = new File(realParentDir);
				File[] files = file.listFiles();
				if (files != null && files.length > 0) {
					for (int i = 0; i < files.length; i++) {// 过滤非附件目录
						fileList.add(files[i]);
					}
				}
				return fileList;
			}
		}
		return null;
	}

	@Override
	public String parseDefaultImageByFileName(String fileName) {
		String type = this.parseTypeByFileName(fileName);
		if (ContextConstants.PICTURE_UPLOAD_PATH.equals(type)) { // 如果是图片格式，则显示原图片
			return fileName;
		} else if (ContextConstants.VOICE_UPLOAD_PATH.equals(type)) {
			return ContextConstants.VOICE_UPLOAD_PATH_IMAGE;
		} else if (ContextConstants.ZIP_UPLOAD_PATH.equals(type)) {
			return ContextConstants.ZIP_UPLOAD_PATH_IMAGE;
		} else if (ContextConstants.RAR_UPLOAD_PATH.equals(type)) {
			return ContextConstants.RAR_UPLOAD_PATH_IMAGE;
		} else if (ContextConstants.DOC_UPLOAD_PATH.equals(type)) {
			return ContextConstants.DOC_UPLOAD_PATH_IMAGE;
		} else if (ContextConstants.EXCEL_UPLOAD_PATH.equals(type)) {
			return ContextConstants.EXCEL_UPLOAD_PATH_IMAGE;
		} else if (ContextConstants.RAR_UPLOAD_PATH.equals(type)) {
			return ContextConstants.RAR_UPLOAD_PATH_IMAGE;
		} else if (ContextConstants.FLASH_UPLOAD_PATH.equals(type)) {
			return ContextConstants.FLASH_UPLOAD_PATH_IMAGE;
		} else if (ContextConstants.VIDEO_UPLOAD_PATH.equals(type)) {
			return ContextConstants.VIDEO_UPLOAD_PATH_IMAGE;
		}
		return ContextConstants.OTHER_UPLOAD_PATH_IMAGE;
	}

	@Override
	public Boolean changeOrderValue(String id, Integer orderValue) {
		CommonAnnex commonAnnex = dbDAO.queryById(this
				.getTableNameFromEntity(CommonAnnex.class), id);
		if (commonAnnex != null) {
			commonAnnex.getStatus().setOrderValue(orderValue);
			dbDAO.merge(commonAnnex);
			return true;
		}
		return false;
	}

	@Override
	public Boolean editCommonAnnex(String id, String title, String content) {
		CommonAnnex commonAnnex = dbDAO.queryById(this
				.getTableNameFromEntity(CommonAnnex.class), id);
		title = DecoderUtil.UtfDecoder(title);
		content = DecoderUtil.UtfDecoder(content);
		commonAnnex.setTitle(title);
		commonAnnex.setContent(content);
		dbDAO.merge(commonAnnex);
		return true;
	}

	@Override
	public List<CommonAnnex> queryAdminAll(Integer size, String entityId)
			throws ServiceException {
		return dbDAO.queryByList(CommonAnnex.class.getSimpleName(), deleteFlag,
				versionFlag, "and s.entityId=?3", new Object[] { entityId },
				orderBy, size);
	}

	@Override
	public List<CommonAnnex> queryByObjectIdAnnex(String entityName,
			String entityId, String versionFlag) throws ServiceException {
		return dbDAO.queryByList(CommonAnnex.class.getSimpleName(), deleteFlag,
				versionFlag, "and s.entityId=?3 and s.entityName like ?4",
				new Object[] { entityId, "%" + entityName + "%" },
				"s.status.orderValue desc, s.id desc", null);
	}

	@Override
	public CommonAnnex queryByAnnexPath(String annexPath)
			throws ServiceException {
		return dbDAO.queryByCustom(CommonAnnex.class.getSimpleName(),
				deleteFlag, versionFlag, "and s.annexPath=?3",
				new Object[] { annexPath });
	}

	@Override
	public CommonAnnex queryByUrl(String url, String entityName)
			throws ServiceException {
		if (GeneralUtil.isNotNull(entityName)) {
			return dbDAO.queryByCustom(CommonAnnex.class.getSimpleName(),
					deleteFlag, versionFlag,
					"and s.annexPath like ?3 and s.entityName like ?4",
					new Object[] {"%" + url, "%" + entityName + "%" });
		} else {
			return dbDAO.queryByCustom(CommonAnnex.class.getSimpleName(),
					deleteFlag, versionFlag, "and s.annexPath like ?3",
					new Object[] { "%" + url });
		}
	}

	@Override
	public List<CommonAnnex> queryList(Integer type, String entityId,
			String versoinFlag, String entityName) throws ServiceException {
		if(type != null) {
			return dbDAO.queryByList(CommonAnnex.class.getSimpleName(), deleteFlag,
					versionFlag, "and s.entityId=?3 and s.annexType=?4",
					new Object[] { entityId, type },
					"s.status.orderValue desc, s.id desc", null);
		} else {
			return dbDAO.queryByList(CommonAnnex.class.getSimpleName(), deleteFlag,
					versionFlag, "and s.entityId=?3",
					new Object[] { entityId},
					"s.status.orderValue desc, s.id desc", null);
		}
	}

	@Override
	public List<CommonAnnex> queryListAll(String versionFlag, String entityId)
			throws ServiceException {
		return dbDAO.queryByList(CommonAnnex.class.getSimpleName(), deleteFlag,
				versionFlag, "and s.entityId=?3", new Object[] { entityId },
				"s.status.orderValue desc, s.id desc", null);
	}
}