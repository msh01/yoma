package com.github.yoma.common.dto;

import lombok.Data;

/**
 * 给消息队列服务传递消息的对象封装
 * @author msh01
 * @version 1.0.0
 * @createTime 2020年05月01日 22:15:00
 */
@Data
public class MqttMsgDTO {
    private  String topic;
    private  String content;
}
