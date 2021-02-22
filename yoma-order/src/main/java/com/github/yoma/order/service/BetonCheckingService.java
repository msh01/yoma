package com.github.yoma.order.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.github.pagehelper.PageInfo;
import com.github.yoma.order.dao.BetonCheckingDao;
import com.github.yoma.order.domain.BetonChecking;
import com.github.yoma.order.domain.BetonCheckingDetail;
import com.github.yoma.order.domain.BetonCheckingSetting;
import com.github.yoma.order.domain.BetonPriceDetail;
import com.github.yoma.order.dto.BetonCheckingDetailQueryDTO;
import com.github.yoma.order.dto.BetonCheckingQueryDTO;
import com.github.yoma.order.dto.BetonCheckingSettingQueryDTO;
import com.github.yoma.order.service.excel.converter.LocalDateConverter;
import com.github.yoma.order.vo.FillData;
import com.github.yoma.common.persistence.CrudService;
import com.github.yoma.tools.utils.GenerateNum;
import com.github.yoma.common.utils.SandConstants;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;

/**
 * 对账结果 业务层
 *
 * @author 马世豪
 * @version 2020-12-02
 */
@Service
@Transactional(readOnly = true)
public class BetonCheckingService extends CrudService<BetonCheckingDao, BetonChecking> {

    @Autowired
    BetonPriceDetailService priceDetailService;
    @Autowired
    BetonCheckingDetailService checkingDetailService;
    @Autowired
    BetonCheckingSettingService settingService;

    @Override
    public BetonChecking get(Long id) {
        return super.get(id);
    }

    public List<BetonChecking> findList(BetonCheckingQueryDTO queryDTO) {
        return super.findList(queryDTO);
    }

    public void download(BetonCheckingQueryDTO queryDTO, HttpServletResponse response) throws IOException {
        Long checkingId = queryDTO.getCheckingId();
        BetonChecking betonChecking = this.get(checkingId);
        Long projectId = betonChecking.getProjectId();
        BetonCheckingSettingQueryDTO betonCheckingSettingQueryDTO = new BetonCheckingSettingQueryDTO();
        betonCheckingSettingQueryDTO.setProjectId(projectId);
        List<BetonCheckingSetting> settings = settingService.findList(betonCheckingSettingQueryDTO);
        String templateUrl = settings.get(0).getTemplateUrl();

        String fileBaseUrl = "http://img.dev.x-code.top/";
        String fileID = templateUrl;
        String remoteUrl = fileBaseUrl + fileID;

        // Long equipmentProject = iotEquipment.getEquipmentProject();
        URL url = null;
        InputStream is = null;
        url = new URL(remoteUrl);
        is = url.openStream();

        String projectPath = System.getProperty("user.dir");
        // response为HttpServletResponse对象
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        // test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        response.setHeader("Content-Disposition", "attachment;filename=file.xlsx");
        ServletOutputStream out = response.getOutputStream();

        // String tmpDir = System.getProperty("java.io.tmpdir");
        // String tempPath = projectPath + "/" + IdUtil.fastSimpleUUID() + ".xlsx";
        // File tempFile = new File(tempPath);

        EasyExcel.write(out).withTemplate(is).sheet().registerConverter(new LocalDateConverter()).doFill(betonChecking);

        // 此处记得关闭输出Servlet流
        IoUtil.close(out);
        IoUtil.close(is);
        // List<Map<String, Object>> list = new ArrayList<>();
        // FileUtil.downloadExcel(list, response);
    }

    public void download2(BetonCheckingQueryDTO queryDTO, HttpServletResponse response) throws IOException {
        Long checkingId = queryDTO.getCheckingId();
        BetonChecking betonChecking = this.get(checkingId);
        Long projectId = betonChecking.getProjectId();
        BetonCheckingSettingQueryDTO betonCheckingSettingQueryDTO = new BetonCheckingSettingQueryDTO();
        betonCheckingSettingQueryDTO.setProjectId(projectId);
        List<BetonCheckingSetting> settings = settingService.findList(betonCheckingSettingQueryDTO);
        String templateUrl = settings.get(0).getTemplateUrl();

        String fileBaseUrl = "http://img.dev.x-code.top/";
        String fileID = templateUrl;
        String remoteUrl = fileBaseUrl + fileID;

        // Long equipmentProject = iotEquipment.getEquipmentProject();
        URL url = null;
        InputStream is = null;
        url = new URL(remoteUrl);
        is = url.openStream();

        String projectPath = System.getProperty("user.dir");
        // response为HttpServletResponse对象
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        // test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        response.setHeader("Content-Disposition", "attachment;filename=file.xlsx");
        ServletOutputStream out = response.getOutputStream();

        // String tmpDir = System.getProperty("java.io.tmpdir");
        // String tempPath = projectPath + "/" + IdUtil.fastSimpleUUID() + ".xlsx";
        // File tempFile = new File(tempPath);

        ExcelWriter excelWriter =
            EasyExcel.write(out).withTemplate(is).registerConverter(new LocalDateConverter()).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();

        // EasyExcel.write(out).withTemplate(is).sheet().registerConverter(new
        // LocalDateConverter()).doFill(betonChecking);

        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        excelWriter.fill(data(), fillConfig, writeSheet);
        excelWriter.fill(data(), fillConfig, writeSheet);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("date", "2019年10月9日13:28:28");
        map.put("total", 1000);
        excelWriter.fill(map, writeSheet);
        excelWriter.finish();

        // 此处记得关闭输出Servlet流
        IoUtil.close(out);
        IoUtil.close(is);
        // List<Map<String, Object>> list = new ArrayList<>();
        // FileUtil.downloadExcel(list, response);
    }

    public void download3(BetonCheckingQueryDTO queryDTO, HttpServletResponse response) throws IOException {

        Long checkingId = queryDTO.getCheckingId();
        BetonChecking betonChecking = this.get(checkingId);
        Long projectId = betonChecking.getProjectId();
        BetonCheckingSettingQueryDTO betonCheckingSettingQueryDTO = new BetonCheckingSettingQueryDTO();
        betonCheckingSettingQueryDTO.setProjectId(projectId);
        List<BetonCheckingSetting> settings = settingService.findList(betonCheckingSettingQueryDTO);
        String templateUrl = settings.get(0).getTemplateUrl();
        BetonCheckingDetailQueryDTO betonCheckingDetailQueryDTO = new BetonCheckingDetailQueryDTO();
        betonCheckingDetailQueryDTO.setCheckingNo1(betonChecking.getCheckingNo());
        List<BetonCheckingDetail> details = checkingDetailService.findList(betonCheckingDetailQueryDTO);

        String fileBaseUrl = "http://img.dev.x-code.top/";
        String fileID = templateUrl;
        String remoteUrl = fileBaseUrl + fileID;
        String projectPath = System.getProperty("user.dir");
        String tmpDir = System.getProperty("java.io.tmpdir");
        String tempPath = projectPath + "/" + IdUtil.fastSimpleUUID() + ".xlsx";
        File tempFile = new File(tempPath);

        URL url = null;
        InputStream is = null;
        url = new URL(remoteUrl);
        is = url.openStream();
        FileUtils.copyURLToFile(url, tempFile);

        TemplateExportParams params = new TemplateExportParams(tempPath);

        betonChecking.setStationName("河南紫东混凝土有限公司");
        betonChecking.setContractNo("111111111");
        betonChecking.setAmountSum(100.0);
        betonChecking.setAmountSumTax(150.0);
        betonChecking.setMonthSum(1000.0);
        betonChecking.setTotalSum(2000.0);
        betonChecking.setMonthSumRMB("伍拾亿零壹圆整");
        betonChecking.setTotalSumRMB("伍拾亿零壹圆整");
        betonChecking.setNumSum(66L);
        List<Map<String, Object>> listValueMap = new ArrayList<>();
        details.forEach(x -> {
            Map<String, Object> t = BeanUtil.beanToMap(x);
            listValueMap.add(t);
        });
        int size = details.size();
        betonChecking.setListSize(size);
        betonChecking.setRecycleListSize(size);

        Map<String, Object> valueMap = BeanUtil.beanToMap(betonChecking);
        valueMap.put("maplist", listValueMap);

        // response为HttpServletResponse对象
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        // test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        response.setHeader("Content-Disposition", "attachment;filename=file.xlsx");
        ServletOutputStream out = response.getOutputStream();

        Workbook workbook = ExcelExportUtil.exportExcel(params, valueMap);
        workbook.write(out);

        // 此处记得关闭输出Servlet流
        IoUtil.close(out);
        IoUtil.close(is);
        tempFile.delete();
    }

    @Transactional(readOnly = false)
    public BetonChecking createBetonChecking(BetonCheckingQueryDTO queryDTO) {
        List<BetonCheckingDetail> checkingDetails = queryDTO.getCheckingDetails();
        Long projectId = queryDTO.getProjectId();
        ArrayList<BetonPriceDetail> betonPriceDetails = new ArrayList<>();
        String generateOrder = GenerateNum.getInstance().GenerateOrder();
        checkingDetails.forEach(x -> {
            BetonPriceDetail betonPriceDetail = new BetonPriceDetail();
            BeanUtil.copyProperties(x, betonPriceDetail);

            betonPriceDetail.setProjectId(projectId);
            betonPriceDetail.setBetonClientId(queryDTO.getBetonClientId());
            betonPriceDetail.setClientId(SandConstants.SELF_STATION_ID);
            betonPriceDetail.setCheckingNo(generateOrder);
            betonPriceDetail.setType1(0);

            betonPriceDetail.setUnitPrice(x.getUnitPrice1());
            betonPriceDetail.setUnitSellingPrice(x.getUnitSellingPrice1());
            betonPriceDetails.add(betonPriceDetail);
        });
        this.priceDetailService.batchInsert(betonPriceDetails);
        // TODO 需要修改动态
        queryDTO.setCheckingNo(generateOrder);
        queryDTO.setType(0);
        queryDTO.setCreateId(1L);
        queryDTO.setCumNum(1);
        return dao.createBetonChecking(queryDTO);
    }

    public PageInfo<BetonChecking> findPage(BetonCheckingQueryDTO queryDTO) {
        return super.findPage(queryDTO);
    }

    @Transactional(readOnly = false)
    public int batchDelete(BetonCheckingQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }

    private List<FillData> data() {
        List<FillData> list = new ArrayList<FillData>();
        for (int i = 0; i < 10; i++) {
            FillData fillData = new FillData();
            list.add(fillData);
            fillData.setName("张三");
            fillData.setNumber(5.2);
        }
        return list;
    }

    @Transactional(readOnly = false)
    @Override
    public void save(BetonChecking betonChecking) {
        super.save(betonChecking);
    }

    @Transactional(readOnly = false)
    @Override
    public int delete(BetonChecking betonChecking) {
        return super.delete(betonChecking);
    }

}
