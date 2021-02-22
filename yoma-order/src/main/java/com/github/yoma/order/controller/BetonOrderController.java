package com.github.yoma.order.controller;

import com.github.pagehelper.PageInfo;

import com.github.yoma.common.annotation.AnonymousAccess;
import com.github.yoma.order.businessenum.CompTypesEnum;
import com.github.yoma.order.domain.BetonOrder;
import com.github.yoma.order.dto.BetonOrderQueryDTO;
import com.github.yoma.order.service.BetonOrderService;
import com.github.yoma.order.vo.DictVo;
import com.github.yoma.common.persistence.BaseController;
import com.github.yoma.common.persistence.BatchDTO;
import com.github.yoma.common.result.CommonResponse;
import com.github.yoma.common.result.DetailResponse;
import com.github.yoma.common.result.PageResponse;
import com.github.yoma.common.result.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.*;

/**
 * 商砼运输订单RestFull服务
 *
 * @author 马世豪
 * @version 2020-11-19
 */
@RestController
@RequestMapping(value = "/order/betonOrder")
@Api(tags = "商砼运输订单")
public class BetonOrderController extends BaseController {

    @Autowired
    private BetonOrderService betonOrderService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @RequestMapping("list")
    @AnonymousAccess
    public PageResponse<BetonOrder> list(BetonOrderQueryDTO queryDTO) {
        PageInfo<BetonOrder> pageInfo = betonOrderService.findPage(queryDTO);
        PageResponse<BetonOrder> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }
    // @ApiOperation(value = "下载Excel模板")
    // @AnonymousAccess
    // @GetMapping("/excel/template")
    // public void downloadTemplate(HttpServletResponse response) {
    // iotEquipmentService.downloadTemplate(response);
    // }

    @ApiOperation(value = "导入excel数据")
    @AnonymousAccess
    @PostMapping("/excel/import")
    public CommonResponse importExcel(@RequestBody BetonOrder betonOrder) throws Exception {
        this.betonOrderService.importExcel(betonOrder);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

    /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    @AnonymousAccess
    public DetailResponse<BetonOrder> save(@RequestBody BetonOrder betonOrder) {
        betonOrderService.save(betonOrder);
        DetailResponse<BetonOrder> success = ResponseUtil.detailSuccess(betonOrder);
        return success;
    }

    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{betonOrderId}")
    @AnonymousAccess
    public DetailResponse<BetonOrder> detail(@PathVariable Long betonOrderId) {
        BetonOrder betonOrder = new BetonOrder();
        betonOrder.setId(betonOrderId);
        betonOrder = betonOrderService.get(betonOrder);
        DetailResponse<BetonOrder> success = ResponseUtil.detailSuccess(betonOrder);
        return success;
    }

    /**
     * 枚举来充当数据字典
     */
    @ApiOperation("枚举来充当数据字典")
    @GetMapping("/getAllDict")
    @AnonymousAccess
    public DetailResponse<DictVo> getAllDict(@PathVariable Long betonOrderId) {
        Map<String, Object> resultMap = new HashMap<>();
        DictVo dictVo = new DictVo();
        resultMap.put("CompTypesEnum", CompTypesEnum.values());
        resultMap.put("CompTypesEnum", CompTypesEnum.values());
        resultMap.put("CompTypesEnum", CompTypesEnum.values());
        DetailResponse<DictVo> success = ResponseUtil.detailSuccess(dictVo);
        return success;
    }

    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{betonOrderId}")
    @AnonymousAccess
    public CommonResponse delete(@PathVariable Long betonOrderId) {
        BetonOrder betonOrder = new BetonOrder();
        betonOrder.setId(betonOrderId);
        int count = betonOrderService.delete(betonOrder);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

    /**
     * 删除操作-批量
     */
    @ApiOperation("批量删除")
    @PostMapping("/batch/de2lete")
    @AnonymousAccess
    public CommonResponse batchDelete(@RequestBody BatchDTO batchDTO) {
        // public CommonResponse batchDelete(@RequestBody List<Long> ids) {
        // 获取当前操作人信息
        BetonOrderQueryDTO queryDTO = new BetonOrderQueryDTO();
        // queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        queryDTO.setBatchIdStr(batchDTO.getBatchIdStr());
        int count = betonOrderService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

}
