package com.github.yoma.core.service;

import java.util.List;

import com.github.yoma.core.dao.PaymentRequestDao;
import com.github.yoma.core.domain.PaymentRequest;
import com.github.yoma.core.dto.PaymentRequestQueryDTO;
import com.github.yoma.common.persistence.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageInfo;

/**
 * 提现申请 业务层
 * @author 马世豪
 * @version 2020-12-21
 */
@Service
@Transactional(readOnly = true)
public class PaymentRequestService extends CrudService<PaymentRequestDao, PaymentRequest> {

	@Override
	public PaymentRequest get(Long id) {
		return super.get(id);
	}

	public List<PaymentRequest> findList(PaymentRequestQueryDTO queryDTO) {
		return super.findList(queryDTO);
	}

	public PageInfo<PaymentRequest> findPage(PaymentRequestQueryDTO queryDTO) {
		return super.findPage(queryDTO);
	}

	@Transactional(readOnly = false)
    public int batchDelete(PaymentRequestQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }


	@Transactional(readOnly = false)
	@Override
	public void save(PaymentRequest paymentRequest) {
		// 先检查 用户可用金额是否足够
		// 锁定用户金额
		super.save(paymentRequest);
	}


	@Transactional(readOnly = false)
	@Override
	public int delete(PaymentRequest paymentRequest) {
		return super.delete(paymentRequest);
	}

}
