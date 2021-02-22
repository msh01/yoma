package com.github.yoma.base.modules.system.service;

import com.github.yoma.base.modules.system.domain.DictDetail;
import com.github.yoma.base.modules.system.service.dto.DictDetailDTO;
import com.github.yoma.base.modules.system.service.dto.DictDetailQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
public interface DictDetailService {

    DictDetailDTO findById(Long id);

    DictDetailDTO create(DictDetail resources);

    void update(DictDetail resources);

    void delete(Long id);

    Map queryAll(DictDetailQueryCriteria criteria, Pageable pageable);
}
