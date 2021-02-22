package com.github.yoma.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * http请求返回的最外层对象
 * Created by wzh-zhua on 2018/10/1.
 */
@Data
@ApiModel("详情响应数据")
public class DetailResponse<T> extends CommonResponse {

    @ApiModelProperty("单条记录")
    private T data;


}
