package com.github.yoma.common.result;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页响应数据(业务实现)
 *
 * @author yingw
 * @date 2019/12/11 17:32.
 */
@Data
@ApiModel("分页响应数据")
public class PageResponse<T> extends CommonResponse {
    @ApiModelProperty("多条记录，以分页对象返回")
    private PageInfo<T> data;


}
