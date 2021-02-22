/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.github.yoma.common.persistence;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 数据Entity类
 *
 * @author ThinkGem
 * @version 2014-05-16
 */
@Data
public abstract class DataEntity<T> extends BaseEntity<T> {

    private static final long serialVersionUID = 1L;

    //
    @ApiModelProperty(value = "1 正常/未存档  11 已存档/逻辑删除")
    private Integer archive;
    //
    @ApiModelProperty(value = "创建人id", hidden = true)
    protected String createBy;
    //
    @ApiModelProperty(value = "创建时间", hidden = true)
    protected LocalDateTime createTime;
    //
    //
    @ApiModelProperty(value = "修改人id", hidden = true)
    protected String updateBy;
    //
    //
    @ApiModelProperty(value = "修改时间", hidden = true)
    protected LocalDateTime updateTime;

    public DataEntity() {
        super();
    }

    /**
     * 插入之前执行方法，需要手动调用
     */
    @Override
    public void preInsert() {
        this.setUpdateTime(LocalDateTime.now());
        this.setCreateTime(LocalDateTime.now());
    }

    /**
     * 更新之前执行方法，需要手动调用
     */
    @Override
    public void preUpdate() {
        this.setUpdateTime(LocalDateTime.now());
    }

}
