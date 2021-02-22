package com.github.yoma.common.result;

/**
 * 相应给前端的结果集封装(业务实现)
 *
 * @author yingw
 * @date 2019/12/11 11:02.
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * http请求返回的最外层对象
 * Created by wzh-zhua on 2018/10/1.
 */
@Data
public class CommonResponse<T> {
    @ApiModelProperty("响应代码")
    private Integer code;
    @ApiModelProperty("响应提示信息")
    private String msg;


}
