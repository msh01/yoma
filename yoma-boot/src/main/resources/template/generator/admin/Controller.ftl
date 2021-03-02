/*
*  Copyright 2019-2020 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package ${package}.rest;

import com.github.pagehelper.PageInfo;
import ${package}.domain.${className};
import ${package}.service.${className}Service;
import ${package}.service.dto.${className}QueryCriteria;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author ${author}
* @date ${date}
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "${apiAlias}管理")
@RequestMapping("/api/${changeClassName}")
public class ${className}Controller {

                @Autowired
                private ${className}Service ${changeClassName}Service;

                /**
                * 列表查询
                */
                @ApiOperation(value = " 列表查询")
                @PostMapping("list")
                @AnonymousAccess
                public PageResponse<${className}> list(@RequestBody ${className}QueryDTO queryDTO) {
                PageInfo<${className}> pageInfo = ${changeClassName}Service.findPage(queryDTO);
                PageResponse<${className}> pageResponse = ResponseUtil.pageSuccess(pageInfo);
                return pageResponse;
                }

                /**
                * 保存或修改
                */
                @ApiOperation(value = " 保存或修改")
                @PostMapping("/save")
                @AnonymousAccess
                public DetailResponse<${className}> save(@RequestBody ${className} ${changeClassName}) {
                ${changeClassName}Service.save(${changeClassName});
                DetailResponse<${className}> success = ResponseUtil.detailSuccess(${changeClassName});
                return success;
                }


                /**
                * 详情
                */
                @ApiOperation("详情")
                @GetMapping("/detail/{${changeClassName}Id}")
                @AnonymousAccess
                public DetailResponse<${className}> detail(@PathVariable Long ${changeClassName}Id) {
                ${className}  ${changeClassName}=new ${className}();
                ${changeClassName}.setId(${changeClassName}Id);
                ${changeClassName} = ${changeClassName}Service.get(${changeClassName});
                DetailResponse<${className}> success = ResponseUtil.detailSuccess(${changeClassName});
                return success;
                }


                /**
                * 删除操作
                */
                @ApiOperation("删除")
                @PostMapping("/delete/{${changeClassName}Id}")
                @AnonymousAccess
                public CommonResponse delete(@PathVariable Long ${changeClassName}Id) {
                ${className}	${changeClassName}=new ${className}();
                ${changeClassName}.setId(${changeClassName}Id);
                int count=${changeClassName}Service.delete(${changeClassName});
                CommonResponse success = ResponseUtil.success();
                return success;
                }



                /**
                * 删除操作-批量
                */
                @ApiOperation("批量删除")
                @PostMapping("/batch/delete")
                @AnonymousAccess
                public CommonResponse batchDelete(@RequestBody BatchDTO batchDTO) {
                // 获取当前操作人信息
                ${className}QueryDTO	queryDTO=new ${className}QueryDTO();

                queryDTO.setBatchIdList(batchDTO.getBatchIdList());
                int count=${changeClassName}Service.batchDelete(queryDTO);
                CommonResponse success = ResponseUtil.success();
                return success;
                }
}