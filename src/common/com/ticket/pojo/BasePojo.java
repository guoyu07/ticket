package com.ticket.pojo;

import java.io.Serializable;

import javax.persistence.Transient;

/**
 * 实体保存基类
 * @ClassName: BasePojo
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author HiSay 聚名项目部（云南天蝎网络科技有限公司）
 * @date 2015-2-22 下午06:15:03
 */
public class BasePojo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Transient
	private String ids = null;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
