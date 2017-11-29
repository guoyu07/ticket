package com.ticket.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
/**
 * 企业行业
 * @ClassName: Industry   
 * @Description: 企业行业
 * @author HiSay  
 * @date  2016-01-11 09:44:02
 *
 */
@Entity
@Table(name="ticket_Industry",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class Industry implements Serializable {

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
	 * 行业名称
	 */
	@Column
	private String name = null;

	/**
	 * 行业描述
	 */
	@Column
	private String introduce = null;

	/**
	 * 访问地址
	 */
	@Column
	private String path = null;

	/**
	 * 深度
	 */
	@Column
	private Integer deep = null;
	/**
	 * 父类别
	 */
	@ManyToOne(optional=true)
	@NotFound(action=NotFoundAction.IGNORE)
	private Industry parent = null;
	/**
	 * 栏目包含的子栏目, 可实现无限极
	 */
	@OrderBy(value="status.orderValue desc, id asc")
	@OneToMany(mappedBy="parent", fetch=FetchType.EAGER, cascade=CascadeType.REMOVE)
	private List<Industry> childs = new ArrayList<Industry>();

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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Integer getDeep() {
		return deep;
	}
	public void setDeep(Integer deep) {
		this.deep = deep;
	}
	public Industry getParent() {
		return parent;
	}
	public void setParent(Industry parent) {
		this.parent = parent;
	}
	public List<Industry> getChilds() {
		return childs;
	}
	public void setChilds(List<Industry> childs) {
		this.childs = childs;
	}
}
