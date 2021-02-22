package com.github.yoma.core.domain;

import javax.persistence.*;

import com.github.yoma.common.persistence.DataEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 角色信息Entity 既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据） 基于lombok ，所以无需生成getter 和 setter方法
 * 
 * @author 马世豪
 * @version 2020-04-07
 */
@Data
@Table(name = "core_role")
@ApiModel(value = "CoreRole", description = "角色信息表")
public class CoreRole extends DataEntity<CoreRole> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "角色主键")
    private Long id;

    @ApiModelProperty(value = "accountProject 中间表的主键")
    private Long accountProjectId;

    @Column(name = "role_name")
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    @ApiModelProperty(value = "角色名称")
    private List<Long> groupList;

    @Column(name = "role_type")
    @ApiModelProperty(value = "角色类型 11平台 22公司 33项目")
    private Integer roleType;
    @ApiModelProperty(value = "项目状态")
    private Integer projectStatus;

    @Column(name = "role_des")
    @ApiModelProperty(value = "角色说明")
    private String roleDes;

    @Column(name = "role_target")
    @ApiModelProperty(value = "公司/项目角色关联到对应的id 平台角色设为0")
    private Long roleTarget;

    @Column(name = "role_menu")
    @ApiModelProperty(value = "角色菜单表 - 缓存用字段")
    private String roleMenu;

    @Column(name = "role_action")
    @ApiModelProperty(value = "角色操作权限 缓存字段")
    private String roleAction;

    @ApiModelProperty(value = "公司名称")
    private String companyName;
    @ApiModelProperty(value = "公司id")
    private Long companyId;
    @ApiModelProperty(value = "项目名称")
    private String projectName;
    @ApiModelProperty(value = "项目所属公司名称")
    private String projectCompanyName;
    @ApiModelProperty(value = "项目id")
    private Long projectId;

    public String getProjectCompanyName() {
        String projectCompanyName = this.projectCompanyName;
        if (projectCompanyName == null) {
            projectCompanyName = "独立项目";
        }
        return projectCompanyName;
    }
}
