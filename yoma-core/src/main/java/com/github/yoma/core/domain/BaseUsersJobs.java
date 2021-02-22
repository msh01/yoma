package com.github.yoma.core.domain;

import javax.persistence.*;

import com.github.yoma.common.persistence.DataEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户岗位关联Entity 既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据） 基于lombok ，所以无需生成getter 和 setter方法
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@Data
@Table(name = "base_users_jobs")
@ApiModel(value = "BaseUsersJobs", description = "base_users_jobs")
public class BaseUsersJobs extends DataEntity<BaseUsersJobs> {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "用户ID")
    private String userId;

    @Column(name = "job_id")
    @ApiModelProperty(value = "岗位ID")
    private Long jobId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "主键")
    private Long id;

}
