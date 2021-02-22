package com.github.yoma.base.modules.system.service.mapper;

import com.github.yoma.common.base.BaseMapper;
import com.github.yoma.base.modules.system.domain.Job;
import com.github.yoma.base.modules.system.service.dto.JobSmallDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author Zheng Jie
* @date 2019-03-29
*/
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobSmallMapper extends BaseMapper<JobSmallDTO, Job> {

}
