package com.github.yoma.base.modules.system.service.dto;

import lombok.Data;
import com.github.yoma.common.annotation.Query;

/**
 * 公共查询类
 */
@Data
public class DictQueryCriteria {

    // 多字段模糊
    @Query(blurry = "name,remark")
    private String blurry;
}
