package com.github.yoma.order.controller;

import com.github.pagehelper.PageInfo;
import com.github.yoma.common.persistence.BaseController;
import com.github.yoma.common.persistence.BatchDTO;
import com.github.yoma.common.result.CommonResponse;
import com.github.yoma.common.result.DetailResponse;
import com.github.yoma.common.result.PageResponse;
import com.github.yoma.common.result.ResponseUtil;
import com.github.yoma.order.domain.BetonChecking;
import com.github.yoma.order.domain.RmtTempMeter;
import com.github.yoma.order.dto.BetonCheckingQueryDTO;
import com.github.yoma.order.service.BetonCheckingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 西安瑞米特接口服务
 *
 * @author 马世豪
 * @version 2020-12-02
 */
@RestController
@RequestMapping(value = "/rmt/rmtTempMeter")
@Api(tags = "对账结果")
public class RmtTempMeterController extends BaseController {

    @Autowired
    private BetonCheckingService betonCheckingService;


    /**
     * 保存或修改
     */
    @ApiOperation(value = "接收数据")
    @PostMapping("/receive")
    public DetailResponse<RmtTempMeter> createBetonChecking(@RequestBody RmtTempMeter rmtTempMeter) {
        DetailResponse<RmtTempMeter> success = ResponseUtil.detailSuccess(rmtTempMeter);
        return success;
    }


}
