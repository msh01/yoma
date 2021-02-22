package com.github.yoma.core.domain;

import com.github.yoma.common.persistence.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 手机等移动设备Entity 既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据） 基于lombok ，所以无需生成getter 和 setter方法
 *
 * @author 马世豪
 * @version 2020-05-22
 */
@Data
@Table(name = "core_mobile")
@ApiModel(value = "CoreMobile", description = "安装APP的手机等移动设备")
public class CoreMobile extends DataEntity<CoreMobile> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "账户主键")
    private Long id;

    @Column(name = "os_type")
    @ApiModelProperty(value = "安卓10 苹果20")
    private String osType;
    @ApiModelProperty(value = "设备厂商 HUAWEI XIAOMI OPPO MEIZU ")
    private String mobileFactory;

    @Column(name = "registration_id")
    @ApiModelProperty(value = "极光推送的设备注册id")
    private String registrationId;

    @Column(name = "huawei_push_token")
    @ApiModelProperty(value = "华为推送token，用于区分每台设备")
    private String huaweiPushToken;

    @Column(name = "ip")
    @ApiModelProperty(value = "设备ip")
    private String ip;

    @Column(name = "last_login_account")
    @ApiModelProperty(value = "此设备最后一次登录的账号id")
    private Long lastLoginAccount;

    @Column(name = "last_login_time")
    @ApiModelProperty(value = "此设备最后一次登录的世界")
    private LocalDateTime lastLoginTime;

    @Column(name = "imei")
    @ApiModelProperty(value = "Android设备唯一标识")
    private String imei;

    @Column(name = "devide_token")
    @ApiModelProperty(value = "IOS设备唯一标识,通知推送为用到")
    private String deviceToken;
    @ApiModelProperty(value = "手机厂商标志,通知推送为用到。用作区分是华为设备还是其它厂商的设备")
    private String deviceBrand;

    @Column(name = "idfa")
    @ApiModelProperty(value = "IOS设备唯一标识，广告标识符")
    private String idfa;

    @Column(name = "ios_uuid")
    @ApiModelProperty(value = "IOS设备唯一标识(在IOS7.0以后版本苹果无法获取)")
    private String iosUuid;

}
