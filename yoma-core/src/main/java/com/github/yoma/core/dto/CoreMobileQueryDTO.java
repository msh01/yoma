package com.github.yoma.core.dto;

import com.github.yoma.common.persistence.BaseQueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 手机等移动设备 Query对象 用来接收页面传递过来的查询条件参数,并将其传递到serveice层，dao层。
 * 
 * @author 马世豪
 * @version 2020-05-22
 */
@Data
@ApiModel("安装APP的手机等移动设备")
public class CoreMobileQueryDTO extends BaseQueryDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "安卓10 苹果20")
    private String osType;

    @ApiModelProperty(value = "极光推送的设备注册id")
    private String registrationId;
    @ApiModelProperty(value = "项目id")
    private Long projectId;
    @ApiModelProperty(value = "中间表id")
    private Long accountProjectId;
    @ApiModelProperty(value = "手机厂商标志,通知推送为用到。用作区分是华为设备还是其它厂商的设备")
    private String deviceBrand;

    @ApiModelProperty(value = "此设备最后一次登录的账号id")
    private Long lastLoginAccount;

    @ApiModelProperty(value = "华为推送token，用于区分每台设备")
    private String huaweiPushToken;

    @ApiModelProperty(value = "范围筛选 此设备最后一次登录的世界开始")
    private LocalDateTime beginLastLoginTime;

    @ApiModelProperty(value = "范围筛选 此设备最后一次登录的世界结束")
    private LocalDateTime endLastLoginTime;

    @ApiModelProperty(value = "Android设备唯一标识")
    private String imei;

    @ApiModelProperty(value = "IOS设备唯一标识,通知推送为用到")
    private String deviceToken;

    @ApiModelProperty(value = "IOS设备唯一标识，广告标识符")
    private String idfa;

    @ApiModelProperty(value = "IOS设备唯一标识(在IOS7.0以后版本苹果无法获取)")
    private String iosUuid;

}
