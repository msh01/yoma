/*
 * Copyright 2009-2014 by WWW.KNET.CN All rights reserved.
 */

package com.github.yoma.base.modules.fastdfs;

import com.github.yoma.common.annotation.AnonymousAccess;
import com.github.yoma.common.persistence.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 处理文件上传
 *
 * @author 马世豪
 */
@Slf4j
@Api(tags = "文件上传")
@RestController
@RequestMapping(value = "/file")
public class FileController extends BaseController {

    @Autowired
    FastdfsService fastdfsService;

    @ApiOperation("文件上传")
    @AnonymousAccess
    @PostMapping(value = "/upload")
    public Map<String, Object> upload(MultipartHttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Boolean flag = true;
        List<FileMeta> files = new LinkedList<FileMeta>();
        Map<String, MultipartFile> mtpMap = request.getFileMap();
        try {
            for (Map.Entry<String, MultipartFile> entry : mtpMap.entrySet()) {
                FileMeta fileMeta = null;
                MultipartFile mpf = entry.getValue();
                String fileName = mpf.getOriginalFilename();
                fileMeta = new FileMeta();
                fileMeta.setFileName(fileName);
                fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
                String contentType = mpf.getContentType();// 判断文件类型
                fileMeta.setFileType(contentType);
                String fileId = "";
                if (contentType.startsWith("image") && !contentType.contains("svg")) {
                    // 获取图片高度和宽度
                    getImgWidthAndHeight(fileMeta, mpf);
                    fileId = fastdfsService.uploadImage(mpf);
                } else {
                    fileId = fastdfsService.uploadFile(mpf);
                }
                log.info("fileId===={}", fileId);
                fileMeta.setUrl(fileId);

                files.add(fileMeta);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            flag = false;
        }
        resultMap.put("files", files);
        resultMap.put("success", flag);
        return resultMap;

    }

    // @ApiOperation("文件上传")
    // @AnonymousAccess
    // @PostMapping(value = "/upload")
    // public Map<String, Object> upload(@RequestParam(value = "multipartFiles") MultipartFile[] multipartFiles) {
    // Map<String, Object> resultMap = new HashMap<String, Object>();
    // Boolean flag = true;
    // List<FileMeta> files = new LinkedList<FileMeta>();
    //
    // Arrays.stream(multipartFiles).forEach(mpf -> {
    // String fileName = mpf.getOriginalFilename();
    // FileMeta fileMeta = new FileMeta();
    // fileMeta.setFileName(fileName);
    // fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
    // String contentType = mpf.getContentType();// 判断文件类型
    // fileMeta.setFileType(contentType);
    // String fileId = null;
    // try {
    // fileId = fastdfsService.upload(mpf);
    // log.info("fileId===={}", fileId);
    // fileMeta.setUrl(fileId);
    // if (contentType.startsWith("image")) {
    // // 获取图片高度和宽度
    // getImgWidthAndHeight(fileMeta, mpf);
    // }
    // files.add(fileMeta);
    // fileMeta.setUrl(fileId);
    // } catch (Exception e) {
    // log.error(e.getMessage(), e);
    // e.printStackTrace();
    // }
    //
    // });
    // resultMap.put("files", files);
    // resultMap.put("success", flag);
    // return resultMap;
    //
    // }

    private void getImgWidthAndHeight(FileMeta fileMeta, MultipartFile mpf) throws IOException {
        InputStream mpfInputStream = mpf.getInputStream();
        BufferedImage bimg = ImageIO.read(mpfInputStream);
        int width = bimg.getWidth();
        int height = bimg.getHeight();
        fileMeta.setWidth(width);
        fileMeta.setHeight(height);
        DecimalFormat df = new DecimalFormat("#.##");
        Double result = Double.valueOf(width) / Double.valueOf(height);
        String s = df.format(result);
        Double scale = Double.parseDouble(s);
        fileMeta.setScale(scale);
    }

}
