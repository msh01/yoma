package com.github.yoma.base.modules.fastdfs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties({"bytes"})
@Data
public class FileMeta {

    private String fileName;
    private String fileSize;
    private String fileType;
    private String url;
    // 图片类型时才有用
    private int height;
    private int width;
    private Double scale;// 图片宽高比

    private byte[] bytes;

}
