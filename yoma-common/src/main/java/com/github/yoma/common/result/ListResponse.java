package com.github.yoma.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 非分页的列表响应数据
 *
 * @author yingw
 * @date 2019/12/11 17:32.
 */
@Data
@ApiModel("非分页的列表响应数据")
public class ListResponse<T> extends CommonResponse {
    @ApiModelProperty("多条记录，以分页对象返回")
    private List<T> data;


}
