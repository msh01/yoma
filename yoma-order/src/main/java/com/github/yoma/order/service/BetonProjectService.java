package com.github.yoma.order.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.github.yoma.order.dao.BetonProjectDao;
import com.github.yoma.order.domain.BetonProject;
import com.github.yoma.order.dto.BetonProjectQueryDTO;
import com.github.yoma.common.persistence.CrudService;
import com.github.yoma.common.utils.SandConstants;
import com.github.yoma.common.utils.StringUtils;

/**
 * 工程信息 业务层
 *
 * @author 马世豪
 * @version 2020-11-20
 */
@Service
@Transactional(readOnly = true)
public class BetonProjectService extends CrudService<BetonProjectDao, BetonProject> {

    public static HashMap<String, String> textValueMap;

    public HashMap<String, String> getAllMap() {
        BetonProjectQueryDTO queryDTO = new BetonProjectQueryDTO();
        List<BetonProject> list = this.findList(queryDTO);
        textValueMap = new HashMap<>();
        list.forEach(x -> {
            String text = x.getProjectName();
            Long value = x.getId();
            textValueMap.put(text, value.toString());
        });
        return textValueMap;
    }

    public Long getValueByText(String text) {
        String s = this.getAllMap().get(text);
        Long value = 0L;
        if (StringUtils.isEmpty(s)) {
            throw new RuntimeException("不存在" + text + "的项目，请在系统录入后重新导入！");
        } else {
            value = Long.valueOf(s);
        }
        return value;
    }

    @Override
    public BetonProject get(Long id) {
        return super.get(id);
    }

    public List<BetonProject> findList(BetonProjectQueryDTO queryDTO) {
        return super.findList(queryDTO);
    }

    public PageInfo<BetonProject> findPage(BetonProjectQueryDTO queryDTO) {
        return super.findPage(queryDTO);
    }

    @Transactional(readOnly = false)
    public int batchDelete(BetonProjectQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }

    @Transactional(readOnly = false)
    @Override
    public void save(BetonProject betonProject) {
        betonProject.setClientId(SandConstants.SELF_STATION_ID);
        betonProject.setCreateId(1L);
        betonProject.setState(1);
        betonProject.setCreateName("admin");
        super.save(betonProject);
    }

    @Transactional(readOnly = false)
    @Override
    public int delete(BetonProject betonProject) {
        return super.delete(betonProject);
    }

}
