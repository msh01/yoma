package com.github.yoma.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.github.yoma.order.dao.ClientInforDao;
import com.github.yoma.order.domain.ClientInfor;
import com.github.yoma.order.dto.ClientInforQueryDTO;
import com.github.yoma.common.persistence.CrudService;

/**
 * 搅拌站登录用户 业务层
 *
 * @author 马世豪
 * @version 2020-12-03
 */
@Service
@Transactional(readOnly = true)
public class ClientInforService extends CrudService<ClientInforDao, ClientInfor> {

    @Override
    public ClientInfor get(Long id) {
        return super.get(id);
    }

    public List<ClientInfor> findList(ClientInforQueryDTO queryDTO) {
        return super.findList(queryDTO);
    }

    public PageInfo<ClientInfor> findPage(ClientInforQueryDTO queryDTO) {
        return super.findPage(queryDTO);
    }

    @Transactional(readOnly = false)
    public int batchDelete(ClientInforQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }

    @Transactional(readOnly = false)
    @Override
    public void save(ClientInfor clientInfor) {
        super.save(clientInfor);
    }

    @Transactional(readOnly = false)
    @Override
    public int delete(ClientInfor clientInfor) {
        return super.delete(clientInfor);
    }

}
