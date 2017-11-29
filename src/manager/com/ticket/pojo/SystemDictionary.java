package com.ticket.pojo;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
/**
 * 系统字典表
 * @ClassName: SysDictionary   
 * @Description: 系统字典表
 * @author HiSay  
 * @date  2015-10-24 15:27:39
 */
@Entity
@Table(name="ticket_SystemDictionary",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class SystemDictionary implements Serializable {

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
	 * 字典名称
	 */
	@Column(length=255)
	private String name = null;

	/**
	 * 字典值
	 */
	@Column(length=255)
	private String value = null;
	
	/**
	 * 注释
	 */
	private String description = null;
	
	/**
	 * 父字典ID
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private SystemDictionary parent = null;
	
	/**
	 * 拼音
	 */
	@Column
	private String pingYin = null;
	
	public SystemDictionary() {
		super();
	}

	public SystemDictionary(String id){
		super();
		this.id = id;
	}
	
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public SystemDictionary getParent() {
		return parent;
	}
	public void setParent(SystemDictionary parent) {
		this.parent = parent;
	}

	public String getPingYin() {
		return pingYin;
	}

	public void setPingYin(String pingYin) {
		this.pingYin = pingYin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SystemDictionary other = (SystemDictionary) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
