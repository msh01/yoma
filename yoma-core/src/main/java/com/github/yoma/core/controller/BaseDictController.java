package com.github.yoma.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.github.yoma.common.persistence.BaseController;
import com.github.yoma.common.persistence.BatchDTO;
import com.github.yoma.common.result.CommonResponse;
import com.github.yoma.common.result.DetailResponse;
import com.github.yoma.common.result.PageResponse;
import com.github.yoma.common.result.ResponseUtil;
import com.github.yoma.core.domain.BaseDict;
import com.github.yoma.core.dto.BaseDictQueryDTO;
import com.github.yoma.core.service.BaseDictService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 数据字典RestFull服务
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@RestController
@RequestMapping(value = "/core/baseDict")
@Api(tags = "数据字典")
public class BaseDictController extends BaseController {

    @Autowired
    private BaseDictService baseDictService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @GetMapping("list")
    public PageResponse<BaseDict> list(BaseDictQueryDTO queryDTO) {
        PageInfo<BaseDict> pageInfo = baseDictService.findPage(queryDTO);
        PageResponse<BaseDict> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    public DetailResponse<BaseDict> save(@RequestBody BaseDict baseDict) {
        baseDictService.save(baseDict);
        DetailResponse<BaseDict> success = ResponseUtil.detailSuccess(baseDict);
        return success;
    }

    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{baseDictId}")
    public DetailResponse<BaseDict> detail(@PathVariable Long baseDictId) {
        BaseDict baseDict = new BaseDict();
        baseDict.setId(baseDictId);
        baseDict = baseDictService.get(baseDict);
        DetailResponse<BaseDict> success = ResponseUtil.detailSuccess(baseDict);
        return success;
    }

    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{baseDictId}")
    public CommonResponse delete(@PathVariable Long baseDictId) {
        BaseDict baseDict = new BaseDict();
        baseDict.setId(baseDictId);
        int count = baseDictService.delete(baseDict);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

    /**
     * 删除操作-批量
     */
    @ApiOperation("批量删除")
    @PostMapping("/batch/delete")
    public CommonResponse batchDelete(@RequestBody BatchDTO batchDTO) {
        // 获取当前操作人信息
        BaseDictQueryDTO queryDTO = new BaseDictQueryDTO();

        queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        int count = baseDictService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

}
