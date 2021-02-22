package com.github.yoma.common.persistence;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 当前登陆用户的基本信息封装，访问其他微服务时需要传递当前对象参数，用作鉴权
 *
 * @author xingjiali
 * @date 2019/8/27 9:43.
 */
@Data
@ApiModel(value = "UserBean对象",description="UserBean对象")
public class UserBean {

    @ApiModelProperty(value = "用户id",hidden = true)
    Long userId;

    @ApiModelProperty(value = "用户姓名",hidden = true)
    String userName;

    @ApiModelProperty(value = "用户当前组织",hidden = true)
    Long orgId;

    @ApiModelProperty(value = "用户归属的应用id",hidden = true)
    Long appId;

    @ApiModelProperty(value = "用户信息的头部封装",hidden = true)
    private Map<String, String> headMap = new HashMap();

    @ApiModelProperty(value = "用户信息的map组装",hidden = true)
    private Map<String, Object> additionalUser = new HashMap();

}
