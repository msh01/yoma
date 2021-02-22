package com.github.yoma.order.dao;

import com.github.yoma.order.domain.BetonCheckingDetail;
import com.github.yoma.order.dto.BetonCheckingDetailQueryDTO;
import com.github.yoma.common.persistence.CrudDao;
import org.springframework.stereotype.Repository;

/**
 * 对账明细DAO接口
 *
 * @author 马世豪
 * @version 2020-12-02
 */
@Repository
public interface BetonCheckingDetailDao extends CrudDao<BetonCheckingDetail> {

    public BetonCheckingDetail createBetonCheckingDetail(BetonCheckingDetailQueryDTO queryDTO);

}
