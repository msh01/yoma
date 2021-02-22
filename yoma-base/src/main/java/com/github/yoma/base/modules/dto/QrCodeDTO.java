package com.github.yoma.base.modules.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("二维码参数传递")
public class QrCodeDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文本内容（通常为url）")
    private String content;

}
