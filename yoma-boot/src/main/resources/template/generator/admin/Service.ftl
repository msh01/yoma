package ${package}.service;

import ${package}.domain.${className};
import ${package}.service.dto.${className}Dto;
import ${package}.service.dto.${className}QueryCriteria;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageInfo;

/**
* @description 服务接口
* @author ${author}
* @date ${date}
**/
@Service
@Transactional(readOnly = true)
public class ${className}Service extends CrudService<${className}Dao, ${className}> {

        @Override
        public ${className} get(Long id) {
        return super.get(id);
        }

        public List<${className}> findList(${className}QueryDTO queryDTO) {
        return super.findList(queryDTO);
        }

        public PageInfo<${className}> findPage(${className}QueryDTO queryDTO) {
        return super.findPage(queryDTO);
        }

        @Transactional(readOnly = false)
        public int batchDelete(${className}QueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
        }


        @Transactional(readOnly = false)
        @Override
        public void save(${className} ${className}) {
        super.save(${className});
        }


        @Transactional(readOnly = false)
        @Override
        public int delete(${className} ${className}) {
        return super.delete(${className});
        }

}