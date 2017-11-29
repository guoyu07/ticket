package com.ticket.tags;

import java.io.File;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 辅助JQueryUpload上传标签，用于在上传非图片附件时显示默认图片。
 * 用于判断图片在硬盘是否存在，如果不存在则显示默认图片。
 * @ClassName: JQueryUploadTag
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author HiSay 聚名项目部（云南天蝎网络科技有限公司）
 * @date 2014-12-22 下午03:56:30
 */
public class JQueryUploadTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 图片路径
	 */
	private String src = null;
	/**
	 * 默认图片路径，如果指定的src不存在，则显示defaultSrc指定的路径
	 */
	private String defaultSrc = "/common/jQueryUpload/videoAnnex.jpg";
	
	/** 常用图片格式 */
	private String imageFormat = "jpg,gif,jpeg,png,bmp,ico,uco";
	
	@Override
	public int doAfterBody() throws JspException {
		return super.doAfterBody();
	}

	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

	@SuppressWarnings("deprecation")
	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			//服务器的路径
			String serverPath = pageContext.getRequest().getRealPath("");
			//文件的绝对路径
			String filePath = serverPath + src;
			//判断文件是否存在
			if(src != null && defaultSrc != null) {
				File file = new File(filePath);
				if(file.exists()) {
					//获取扩展名
					String ext = src.substring(src.lastIndexOf(".")+1);
					if(imageFormat.indexOf(ext) != -1) {
						out.print(src);
					} else {
						out.print(defaultSrc);
					}
				} else {
					out.print(defaultSrc);
				}
			}
		} catch (Exception e) {
		}
		return EVAL_BODY_INCLUDE;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getDefaultSrc() {
		return defaultSrc;
	}

	public void setDefaultSrc(String defaultSrc) {
		this.defaultSrc = defaultSrc;
	}
}
