package com.ticket.action;

/**
 * 综合的文件管理器
 * @ClassName: GeneralFileManagerAction   
 * @Description: 包括文件上传、查看、删除和修改   
 * @author HiSay  
 * @date Aug 24, 2013 11:39:39 AM   
 *
 */
public class GeneralFileManagerAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	/**
	 * 打开图片上传的窗口
	 */
	@Override
	public String execute() throws Exception {
		return "fileIndex";
	}
}
