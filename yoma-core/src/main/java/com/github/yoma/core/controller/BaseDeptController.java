package com.github.yoma.core.controller;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.github.yoma.common.annotation.AnonymousAccess;
import com.github.yoma.core.domain.BaseDept;
import com.github.yoma.core.dto.BaseDeptQueryDTO;
import com.github.yoma.core.service.BaseDeptService;
import com.github.yoma.common.persistence.BaseController;
import com.github.yoma.common.persistence.BatchDTO;
import com.github.yoma.common.result.CommonResponse;
import com.github.yoma.common.result.DetailResponse;
import com.github.yoma.common.result.PageResponse;
import com.github.yoma.common.result.ResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 部门RestFull服务
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@RestController
@RequestMapping(value = "/core/baseDept")
@Api(tags = "部门")
public class BaseDeptController extends BaseController {

    @Autowired
    private BaseDeptService baseDeptService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @GetMapping("list")
    @AnonymousAccess
    public PageResponse<BaseDept> list(BaseDeptQueryDTO queryDTO) {
        PageInfo<BaseDept> pageInfo = baseDeptService.findPage(queryDTO);
        PageResponse<BaseDept> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    // @ApiOperation("查询部门")
    // @GetMapping
    // @AnonymousAccess
    // public ResponseEntity getDepts(BaseDeptQueryDTO queryDTO) {
    // // 数据权限
    // // criteria.setIds(dataScope.getDeptIds());
    // // List<DeptDTO> deptDTOS = deptService.queryAll(criteria);
    // List<BaseDept> list = baseDeptService.findList(queryDTO);
    // return new ResponseEntity<>(baseDeptService.buildTree(list), HttpStatus.OK);
    // }

    @ApiOperation("查询部门")
    @GetMapping
    @AnonymousAccess
    // @PreAuthorize("@el.check('user:list','dept:list')")
    public PageResponse<BaseDept> query(BaseDeptQueryDTO queryDTO) {
        queryDTO.setPageNo(-1);
        PageInfo<BaseDept> pageInfo = baseDeptService.findPage(queryDTO);
        List<BaseDept> list = pageInfo.getList();
        // List<BaseDept> deduplication = baseDeptService.deduplication(list);
        // pageInfo.setList(deduplication);
        PageResponse<BaseDept> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    @ApiOperation("查询部门:根据ID获取同级与上级数据")
    @PostMapping("/superior")
    @AnonymousAccess
    public ResponseEntity<Object> getSuperior(@RequestBody List<Long> ids) {
        Set<BaseDept> deptDtos = new LinkedHashSet<>();
        for (Long id : ids) {
            BaseDept deptDto = baseDeptService.get(id);
            List<BaseDept> depts = baseDeptService.getSuperior(deptDto, new ArrayList<>());
            deptDtos.addAll(depts);
        }
        Object o = baseDeptService.buildTree(new ArrayList<>(deptDtos));
        return new ResponseEntity<>(o, HttpStatus.OK);
    }

    /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    @AnonymousAccess
    public DetailResponse<BaseDept> save(@RequestBody BaseDept baseDept) {
        baseDeptService.save(baseDept);
        DetailResponse<BaseDept> success = ResponseUtil.detailSuccess(baseDept);
        return success;
    }

    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{baseDeptId}")
    @AnonymousAccess
    public DetailResponse<BaseDept> detail(@PathVariable Long baseDeptId) {
        BaseDept baseDept = new BaseDept();
        baseDept.setId(baseDeptId);
        baseDept = baseDeptService.get(baseDept);
        DetailResponse<BaseDept> success = ResponseUtil.detailSuccess(baseDept);
        return success;
    }

    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{baseDeptId}")
    @AnonymousAccess
    public CommonResponse delete(@PathVariable Long baseDeptId) {
        BaseDept baseDept = new BaseDept();
        baseDept.setId(baseDeptId);
        int count = baseDeptService.delete(baseDept);
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
        BaseDeptQueryDTO queryDTO = new BaseDeptQueryDTO();

        queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        int count = baseDeptService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

}
