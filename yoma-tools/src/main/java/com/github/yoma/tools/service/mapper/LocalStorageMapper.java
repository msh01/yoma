package com.github.yoma.tools.service.mapper;

import com.github.yoma.common.base.BaseMapper;
import com.github.yoma.tools.domain.LocalStorage;
import com.github.yoma.tools.service.dto.LocalStorageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author Zheng Jie
* @date 2019-09-05
*/
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocalStorageMapper extends BaseMapper<LocalStorageDTO, LocalStorage> {

}
