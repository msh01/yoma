/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.github.yoma.common.persistence;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.List;

/**
 * 树状结构的实体父类
 * @author 马世豪
 * @version 2019-09-02
 *
 */
@Data
public abstract class TreeEntity<T> extends DataEntity<T> {

	private static final long serialVersionUID = 1L;

	@Column(name = "c_name")
	@ApiModelProperty(value = "显示名称")
	private String name;

	@Column(name = "n_parent_id")
	@ApiModelProperty(value = "父级id")
	private Long parentId;

	@Column(name = "n_level")
	@ApiModelProperty(value = "层级")
	private Integer level;

	@ApiModelProperty(value = "子级list")
	private List<T> children;


	@ApiModelProperty(value = "是否叶子节点")
	private Boolean isLeaf;





	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}




	/**
	 * 重写get方法
	 * @return
	 */
	public Boolean getIsLeaf() {
		if (this.children==null||this.children.size()==0) {
			isLeaf=true;
		} else {
			isLeaf=false;
		}
		return isLeaf;
	}

}
