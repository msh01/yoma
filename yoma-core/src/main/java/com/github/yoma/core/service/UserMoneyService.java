package com.github.yoma.core.service;

import java.util.List;

import com.github.yoma.core.dao.UserMoneyDao;
import com.github.yoma.core.domain.UserMoney;
import com.github.yoma.core.dto.UserMoneyQueryDTO;
import com.github.yoma.common.persistence.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageInfo;

/**
 * 账户资金 业务层
 * @author 马世豪
 * @version 2020-12-21
 */
@Service
@Transactional(readOnly = true)
public class UserMoneyService extends CrudService<UserMoneyDao, UserMoney> {

	@Override
	public UserMoney get(Long id) {
		return super.get(id);
	}

	public List<UserMoney> findList(UserMoneyQueryDTO queryDTO) {
		return super.findList(queryDTO);
	}

	public PageInfo<UserMoney> findPage(UserMoneyQueryDTO queryDTO) { return super.findPage(queryDTO); }

	@Transactional(readOnly = false)
    public int batchDelete(UserMoneyQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }


	@Transactional(readOnly = false)
	@Override
	public void save(UserMoney userMoney) { super.save(userMoney); }


	@Transactional(readOnly = false)
	@Override
	public int delete(UserMoney userMoney) {
		return super.delete(userMoney);
	}

}
