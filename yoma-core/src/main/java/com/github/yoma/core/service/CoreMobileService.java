package com.github.yoma.core.service;

import com.github.pagehelper.PageInfo;
import com.github.yoma.core.dao.CoreMobileDao;
import com.github.yoma.core.domain.CoreMobile;
import com.github.yoma.core.dto.CoreMobileQueryDTO;
import com.github.yoma.common.persistence.CrudService;
import com.github.yoma.common.utils.SecurityUtils;
import com.github.yoma.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 手机等移动设备 业务层
 * 
 * @author 马世豪
 * @version 2020-05-22
 */
@Service
@Transactional(readOnly = true)
public class CoreMobileService extends CrudService<CoreMobileDao, CoreMobile> {

    @Override
    public CoreMobile get(Long id) {
        return super.get(id);
    }

    public List<CoreMobile> findList(CoreMobileQueryDTO queryDTO) {
        return super.findList(queryDTO);
    }

    public PageInfo<CoreMobile> findPage(CoreMobileQueryDTO queryDTO) {
        return super.findPage(queryDTO);
    }

    @Transactional(readOnly = false)
    public int batchDelete(CoreMobileQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }

    @Transactional(readOnly = false)
    @Override
    public void save(CoreMobile coreMobile) {
        // 校验唯一性，来判定是否已经存在对应的记录
        String registrationId = coreMobile.getRegistrationId();
        String huaweiPushToken = coreMobile.getHuaweiPushToken();
        String deviceToken = coreMobile.getDeviceToken();
        String imei = coreMobile.getImei();
        String iosUuid = coreMobile.getIosUuid();

        CoreMobileQueryDTO coreMobileQueryDTO = new CoreMobileQueryDTO();

        if (StringUtils.isNotEmpty(registrationId)) {
            coreMobileQueryDTO.setRegistrationId(registrationId);
            saveOrUpdateByCondition(coreMobile, coreMobileQueryDTO);
        } /*else if (StringUtils.isNotEmpty(huaweiPushToken)) {
            coreMobileQueryDTO.setHuaweiPushToken(huaweiPushToken);
            saveOrUpdateByCondition(coreMobile, coreMobileQueryDTO);
        }*/ else if (StringUtils.isNotEmpty(deviceToken)) {
            coreMobileQueryDTO.setRegistrationId(null);
            coreMobileQueryDTO.setDeviceToken(deviceToken);
            saveOrUpdateByCondition(coreMobile, coreMobileQueryDTO);
        } else if (StringUtils.isNotEmpty(imei)) {
            coreMobileQueryDTO.setRegistrationId(null);
            coreMobileQueryDTO.setDeviceToken(null);
            coreMobileQueryDTO.setImei(imei);
            saveOrUpdateByCondition(coreMobile, coreMobileQueryDTO);
        } else if (StringUtils.isNotEmpty(iosUuid)) {
            coreMobileQueryDTO.setRegistrationId(null);
            coreMobileQueryDTO.setDeviceToken(null);
            coreMobileQueryDTO.setImei(null);
            coreMobileQueryDTO.setIosUuid(iosUuid);
            saveOrUpdateByCondition(coreMobile, coreMobileQueryDTO);
        }

    }

    private void saveOrUpdateByCondition(CoreMobile coreMobile, CoreMobileQueryDTO coreMobileQueryDTO) {
        List<CoreMobile> list;
        list = this.findList(coreMobileQueryDTO);
        if (list.isEmpty()) {
            super.save(coreMobile);
        } else {
            update(coreMobile, list);
        }
    }

    private void update(CoreMobile coreMobile, List<CoreMobile> list) {
        CoreMobile coreMobileDetail;
        Long userId = SecurityUtils.getUserId();
        coreMobileDetail = list.get(0);

        coreMobile.setId(coreMobileDetail.getId());
        coreMobile.setLastLoginAccount(userId);
        coreMobile.setLastLoginTime(LocalDateTime.now());
        super.save(coreMobile);
    }

    @Transactional(readOnly = false)
    @Override
    public int delete(CoreMobile coreMobile) {
        return super.delete(coreMobile);
    }

}
