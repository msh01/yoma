package com.github.yoma.tools.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Zheng Jie
 * @date 2019-09-05
 */
@Data
public class PushMsgReq implements Serializable {

    private String title;
    private String topic;

    // 文件名
    private String url;

    // 后缀
    private String msg;

    // 类型
    private String type;

    // 大小
    private String intent;

    // 操作人
    private String subtitle;

    private Timestamp createTime;
}
