package com.github.yoma.core.dao;

import com.github.yoma.core.domain.PaymentRequest;
import com.github.yoma.common.persistence.CrudDao;
import org.springframework.stereotype.Repository;

/**
 * 提现申请DAO接口
 * @author 马世豪
 * @version 2020-12-21
 */
@Repository
public interface PaymentRequestDao extends CrudDao<PaymentRequest> {

}
