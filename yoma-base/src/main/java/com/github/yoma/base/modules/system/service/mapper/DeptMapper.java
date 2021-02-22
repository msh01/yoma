package com.github.yoma.base.modules.system.service.mapper;

import com.github.yoma.common.base.BaseMapper;
import com.github.yoma.base.modules.system.domain.Dept;
import com.github.yoma.base.modules.system.service.dto.DeptDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author Zheng Jie
* @date 2019-03-25
*/
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeptMapper extends BaseMapper<DeptDTO, Dept> {

}
