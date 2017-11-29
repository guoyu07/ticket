package com.ticket.pojo;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 公共附件表
 * @ClassName: CommonAnnex   
 * @Description: 保存所有实体的附件、图片、音（视）频信息
 * @author HiSay  
 * @date  2013-09-16 16:05:53
 *
 */
@Entity
@Table(name="ticket_CommonAnnex",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class CommonAnnex extends BasePojo implements Serializable {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */ 
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@Id
	private String id = UUID.randomUUID().toString();
	/**
	 * 实体状态
	 */
	@Embedded
	private CommonEntity status = new CommonEntity();
	/**
	 * 附件的标题
	 */
	@Column
	private String title = null;
	/**
	 * 附件的内容
	 */
	@Column(length=2000)
	private String content = null;
	/**
	 * 附件所属的实体名称(例如:ContentMoedel,Module,Product等)
	 */
	@Column(length=50)
	private String entityName = null;
	/**
	 * 附件所属的实体的UUID
	 */
	@Column(length=36)
	private String entityId = null;
	/**
	 * 附件的扩展名(例如:JPG,GIF,PNG,BMP等)
	 */
	@Column(length=50)
	private String extensionName = null;
	/**
	 * 附件的类型(例如:缩略图,大图,附件,视频等)
	 */
	@Column
	private Integer annexType = null;
	/**
	 * 附件的大小
	 */
	@Column
	private Long annexSize = null;
	/**
	 * 附件的路径
	 */
	@Column
	private String annexPath = null;
	/**
	 * 附件的原始名称
	 */
	@Column
	private String originalName = null;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public CommonEntity getStatus() {
		return status;
	}
	public void setStatus(CommonEntity status) {
		this.status = status;
	}
	
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getExtensionName() {
		return extensionName;
	}
	public void setExtensionName(String extensionName) {
		this.extensionName = extensionName;
	}
	public Integer getAnnexType() {
		return annexType;
	}
	public void setAnnexType(Integer annexType) {
		this.annexType = annexType;
	}
	public Long getAnnexSize() {
		return annexSize;
	}
	public void setAnnexSize(Long annexSize) {
		this.annexSize = annexSize;
	}
	public String getAnnexPath() {
		return annexPath;
	}
	public void setAnnexPath(String annexPath) {
		this.annexPath = annexPath;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public String getEntityId() {
		return entityId;
	}
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	public String getTimeStampName(){
		
		return annexPath.substring(annexPath.lastIndexOf("/") + 1);
	}
	public String getTimeStamp(){
		
		String timeStampName = getTimeStampName();
		return timeStampName.substring(0, timeStampName.indexOf("."));
	}
	public String getDictory(){
		
		return annexPath.substring(0, annexPath.lastIndexOf("/"));
	}
}
