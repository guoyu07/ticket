package com.ticket.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
/**
 * 部门信息
 * @ClassName: DepartmentInfo   
 * @Description: 部门信息表
 * @author HiSay  
 * @date  2015-11-02 22:46:07
 *
 */
@Entity
@Table(name="ticket_DepartmentInfo",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class DepartmentInfo implements Serializable {

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
	 * 部门名称
	 */
	@Column
	private String name;
	
	/**
	 * 部门负责人
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private EmployeeInfo inCharge;

	/**
	 * 部门介绍
	 */
	@Column
	private String introduce;
	/**
	 * 访问地址
	 */
	@Column
	private String path;
	/**
	 * 深度
	 */
	@Column
	private Integer deep;
	/**
	 * 父类别
	 */
	@ManyToOne(optional=true)
	@NotFound(action=NotFoundAction.IGNORE)
	private DepartmentInfo parent;
	/**
	 * 栏目包含的子栏目, 可实现无限极
	 */
	@OrderBy(value="status.orderValue desc, id asc")
	@OneToMany(mappedBy="parent",cascade=CascadeType.REMOVE)
	private List<DepartmentInfo> childs = new ArrayList<DepartmentInfo>();


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
	public DepartmentInfo getParent() {
		return parent;
	}
	public void setParent(DepartmentInfo parent) {
		this.parent = parent;
	}
	public List<DepartmentInfo> getChilds() {
		return childs;
	}
	public void setChilds(List<DepartmentInfo> childs) {
		this.childs = childs;
	}
	public EmployeeInfo getInCharge() {
		return inCharge;
	}
	public void setInCharge(EmployeeInfo inCharge) {
		this.inCharge = inCharge;
	}
}
