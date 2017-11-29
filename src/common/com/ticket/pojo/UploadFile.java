package com.ticket.pojo;

/**
 * JQuery Uploadify 上传的辅助类
 * @ClassName: UploadFile
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author HiSay 聚名项目部（云南天蝎网络科技有限公司）
 * @date 2014-12-22 下午01:12:02
 */
public class UploadFile {
	
	/** 文件编号 */
	private String id = null;
	/** 文件名称 */
	private String name = null;
	/** 文件类型 1:大图 2:小图 3:其他文件 */
	private Integer type = null;
	/** 上传视频的时候, 作为视频上传成功提示的缩略图 */
	private String videoImage = null;
	/** 图片的原始名称 */
	private String oldName = null;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getVideoImage() {
		return videoImage;
	}
	public void setVideoImage(String videoImage) {
		this.videoImage = videoImage;
	}
	public String getOldName() {
		return oldName;
	}
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
}
