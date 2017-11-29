package com.ticket.constants;

import java.io.File;

/**
 * 系统常量类
 * @ClassName: ContextConstants   
 * @Description: 常量工具类, 对于系统里面常用的一些数值进行常量定义.便于程序员阅读和使用.   
 * @author HiSay  
 * @date Jul 8, 2013 9:39:16 AM   
 *
 */
public class ContextConstants {
	
	/**
	 * 默认语言版本为中文
	 */
	public static final String VERSION_FLAG_OF_CHINESE = "site";
	/**
	 * 实体状态公用属性值:0
	 * <p>可以作为信息的未审核状态、信息的未删除状态等等<p>
	 */
	public static final int STATUS_OF_ZERO = 0;
	/**
	 * 实体状态公用属性值:1
	 * <p>可以作为信息的已审核状态、信息的已删除状态等等<p>
	 */
	public static final int STATUS_OF_ONE = 1;
	/**
	 * 验证码放入session的键值
	 */
	public static final String SMS_VERIFICATE_CODE = "verificateCodeSms";
	/**
	 * 验证码放入session的键值
	 */
	public static final String SCOPE_VERIFICATE_CODE = "verificateCode";
	/**
	 * 后台管理员登陆系统后放入session的键值
	 */
	public static final String SCOPE_SYSTEM_USER = "systemUser";
	/**
	 * 后台管理员登陆系统后放入session的键值
	 */
	public static final String SCOPE_SYSTEM_EMPLOYEEINFO = "systemEmployeeInfo";
	/**
	 * 后台渠道用户登陆系统后放入session的键值
	 */
	public static final String SCOPE_CUSTOMER_USER = "customerUser";
	
	/**
	 * 后台员工登陆系统后放入session的键值
	 */
	public static final String SCOPE_EMPLOYEEINFO_USER = "employeeInfoUser";
	
	/**
	 * 已登录员工在session中的键值 
	 */
	public static final String SCOPE_EMPLOYEEINFO = "sessionEmployeeInfo";
	
	/**
	 * 已登录会员在session中的键值 
	 */
	public static final String SCOPE_MEMBER = "sessionMember";

	/**
	 * 会员作为游客身份在session中的值
	 */
	public static final String MEMBER_TEMP_RANDOM = "memberTempRandom";
	
	/**
	 * 登陆后需要跳转的页面
	 */
	public static final String LOGIN_REDIRECT_URL = "loginRedirectUrl";
	/**
	 * 系统基本设置对象放入application的键值
	 */
	public static final String SCOPE_SYSTEM_SETTING = "systemConfig";
	/**
	 * 后台存放在顶部导航模块列表的键值
	 */
	public static final String ADMIN_TOP_MODULE_LIST = "adminTopModuleList";
	/**
	 * 后台存放在顶部导航模块列表的键值
	 */
	public static final String ADMIN_LEFT_MODULE_LIST = "adminLeftModuleList";
	/**
	 * 后台左侧导航树的HTML值的键值
	 */
	public static final String ADMIN_LEFT_MODULE_HTML = "adminLeftModuleHtml";
	/**
	 * 后台用户存放在session里面的权限列表
	 */
	public static final String ADMIN_USE_AUTHORITY_NAME_LIST = "adminUserAuthorityNameList";
	/**
	 * 后台分页大小
	 */
	public static final int ADMIN_COMMON_PAGE_SIZE = 30;
	/**
	 * 商铺管理中心分页大小
	 */
	public static final int STORE_MANAGER_COMMON_PAGE_SIZE = 10;
	/**
	 * 文件上传分隔符
	 */
	public static final String UPLOAD_SEPARATOR_VALUE = "$$";
	/**
	 * 上传路径
	 */
	public static final String UPLOAD_PATH = "upload" + File.separator;
	/**
	 * 临时上传路径
	 */
	public static final String UPLOAD_TEMP_PATH = "temp" + File.separator;
	/**
	 * 图片上传路径
	 */
	public static final String PICTURE_UPLOAD_PATH = "picture";
	/**
	 * 视频上传路径
	 */
	public static final String VIDEO_UPLOAD_PATH = "video";
	/**
	 * 音频上传路径
	 */
	public static final String VOICE_UPLOAD_PATH = "voice";
	/**
	 * flash上传路径
	 */
	public static final String FLASH_UPLOAD_PATH = "flash";
	/**
	 *word文档上传路径
	 */
	public static final String DOC_UPLOAD_PATH = "doc";
	/**
	 * excel表格上传路径
	 */
	public static final String EXCEL_UPLOAD_PATH = "excel";
	/**
	 * zip上传路径
	 */
	public static final String ZIP_UPLOAD_PATH = "zip";
	/**
	 * rar上传路径
	 */
	public static final String RAR_UPLOAD_PATH = "rar";
	/**
	 * 其他文件上传路径
	 */
	public static final String OTHER_UPLOAD_PATH = "other";
	/**
	 * 出发城市
	 */
	public static final String ORGCITY = "orgCity";
	/**
	 * 出发城市三字码
	 */
	public static final String ORGCITYCODE = "orgCityCode";
	
	/**
	 * 到达城市
	 */
	public static final String DESCITY = "desCity";
	/**
	 * 到达城市三字码
	 */
	public static final String DESCITYCODE = "desCityCode";
	/**
	 * 视频上传路径,默认图片
	 */
	public static final String VIDEO_UPLOAD_PATH_IMAGE = "/manager/images/default/video.jpg";
	/**
	 * 音频上传路径,默认图片
	 */
	public static final String VOICE_UPLOAD_PATH_IMAGE = "/manager/images/default/voice.jpg";
	/**
	 * flash上传路径,默认图片
	 */
	public static final String FLASH_UPLOAD_PATH_IMAGE = "/manager/images/default/flash.jpg";
	/**
	 *word文档上传路径,默认图片
	 */
	public static final String DOC_UPLOAD_PATH_IMAGE = "/manager/images/default/doc.jpg";
	/**
	 * excel表格上传路径,默认图片
	 */
	public static final String EXCEL_UPLOAD_PATH_IMAGE = "/manager/images/default/excel.jpg";
	/**
	 * zip上传路径,默认图片
	 */
	public static final String ZIP_UPLOAD_PATH_IMAGE = "/manager/images/default/zip.jpg";
	/**
	 * rar上传路径,默认图片
	 */
	public static final String RAR_UPLOAD_PATH_IMAGE = "/manager/images/default/rar.jpg";
	/**
	 * 其他文件上传路径,默认图片
	 */
	public static final String OTHER_UPLOAD_PATH_IMAGE = "/manager/images/default/other.jpg";
	
	
	/**
	 * 上传的附件的类型分隔符， 图片路径$$$$图片类别
	 */
	public static final String ANNEX_TYPE_SEPARATOR = "$$$$";
	
	/**
	 * 管理员用户名
	 */
	public static final String ADMIN = "yngk";
	
	/**
	 * 用户是否设置过快捷菜单
	 */
	public static final String ISSETQUICKMENU = "isSetQuickMenu";
	
	public static final String ISCHANGEPERSONALDATA = "isChangePersonalData";
	
	/**
	 * 激活项保存session的key
	 */
	public static final String ACTIVATE_ITEM_HTML = "ACTIVATE_ITEM_HTML";
	
	/**
	 * 文件服务器的上传文件路径
	 */
	public static String FILE_SERVER_PREFIX = "http://image.kmcsia.com/upload/";
}
