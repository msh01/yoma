package com.github.yoma.tools.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.PostConstruct;

import com.github.yoma.tools.service.dto.PushMsgHWRsp;
import com.github.yoma.tools.service.dto.PushMsgReq;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.huawei.push.android.AndroidNotification;
import com.huawei.push.android.BadgeNotification;
import com.huawei.push.android.ClickAction;
import com.huawei.push.exception.HuaweiMesssagingException;
import com.huawei.push.message.AndroidConfig;
import com.huawei.push.message.Message;
import com.huawei.push.message.Notification;
import com.huawei.push.messaging.HuaweiApp;
import com.huawei.push.messaging.HuaweiCredential;
import com.huawei.push.messaging.HuaweiMessaging;
import com.huawei.push.messaging.HuaweiOption;
import com.huawei.push.model.Importance;
import com.huawei.push.model.Urgency;
import com.huawei.push.model.Visibility;
import com.huawei.push.reponse.SendResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * @author msh01
 * @version 1.0.0
 * @createTime 2020年06月25日 12:24:00
 *
 */
@Service
@Slf4j
public class HuaweiPushService {

    private HuaweiOption option;

    private HuaweiApp app;

    @Value("${huawei.appId}")
    private String appId;

    @Value("${huawei.appSecret}")
    private String appSecret;

    /**
     * 初始化时加载HuaweiApp，调用SDK包中的方法，获取accessToken 当客户端应用订阅新的主题名称尚不存在时，系统会在HUAWEI Push中使用这个名称创建一个新主题，随后任何客户端都可订阅该主题。
     */
    @PostConstruct
    public void init() {
        // final HuaweiCredential credential = HuaweiCredential.builder().setAppId(appId).setAppSecret(appSecret).build();
        // option = HuaweiOption.builder().setCredential(credential).build();
        //
        // HuaweiApp instance = HuaweiApp.getInstance(option);
        // if (instance != null) {
        //     this.app = instance;
        //     return;
        // } else {
        //     app = HuaweiApp.initializeApp(option);
        // }
    }

    public SendResponse sendPushMessage(List<String> tokenList, PushMsgReq req)
        throws IOException, HuaweiMesssagingException {
        PushMsgHWRsp pushMsgHWRsp = new PushMsgHWRsp();
        Message message = getNormalMessage(tokenList, req);
        HuaweiMessaging huaweiMessaging = HuaweiMessaging.getInstance(app);
        SendResponse response = huaweiMessaging.sendMessage(message);
        log.info("推送消息后华为返回的 response {}", JSON.toJSONString(response));
        return response;
    }

    public SendResponse sendToTopic(String topic, Notification notification, Integer clickActionType, String intent,
        List<String> tokens) throws HuaweiMesssagingException {
        HuaweiMessaging huaweiMessaging = HuaweiMessaging.getInstance(app);

        // LightSettings lightSettings =
        // LightSettings.builder().setColor(Color.builder().setAlpha(0f).setRed(0f).setBlue(1f).setGreen(1f).build())
        // .setLightOnDuration("3.5").setLightOffDuration("5S").build();

        AndroidNotification androidNotification = AndroidNotification.builder()/*.setIcon("/raw/ic_launcher2")*/
            // .setColor("#AACCDD").setSound("/raw/shake").setDefaultSound(true).setTag("tagBoom")
            .setClickAction(ClickAction.builder().setType(clickActionType).setIntent(intent).build())
            // .setBodyLocKey("M.String.body").addBodyLocArgs("boy").addBodyLocArgs("dog").setTitleLocKey("M.String.title")
            // .addTitleLocArgs("Girl").addTitleLocArgs("Cat").setChannelId("Your Channel ID")
            // .setNotifySummary("some summary").setStyle(1).setBigTitle("send topic message title")
            // .setBigBody("send topic message body").setAutoClear(86400000).setNotifyId(486).setGroup("Group1")
            .setImportance(Importance.HIGH.getValue())/*.setLightSettings(lightSettings)*/
            .setBadge(BadgeNotification.builder().setAddNum(1).setBadgeClass("Classic").build())
            .setVisibility(Visibility.PUBLIC.getValue()).setForegroundShow(true).build();

        AndroidConfig androidConfig =
            AndroidConfig.builder().setCollapseKey(-1).setUrgency(Urgency.HIGH.getValue()).setTtl("864000")
                /*.setBiTag("the_sample_bi_tag_for_receipt_service")*/.setNotification(androidNotification).build();

        Message.Builder builder =
            Message.builder().setNotification(notification).setAndroidConfig(androidConfig).setTopic(topic);
        if (tokens != null) {
            builder.addAllToken(tokens);
        }
        Message message = builder.build();

        SendResponse response = huaweiMessaging.sendMessage(message);
        log.info("推送消息后华为返回的 response {}", JSON.toJSONString(response));

        return response;
    }

    /**
     * 华为3.0接口推送消息，推送时需要传递的参数
     *
     * @param tokenList
     * @param req
     * @return
     * @throws UnsupportedEncodingException
     */
    private static Message getNormalMessage(List<String> tokenList, PushMsgReq req)
        throws UnsupportedEncodingException {
        Notification notification =
            new Notification(new String(req.getTitle().getBytes("utf-8"), "utf-8"), req.getMsg());
        ClickAction clickAction = ClickAction.builder().setType(Integer.valueOf(req.getType()))// 设置点击行为的类型：
                                                                                               // 1标识点击行为用户自定义;
                                                                                               // 2标识打开特定URL;
                                                                                               // 3标识打开本业务的APP;
                                                                                               // 4标识打开富媒体信息;
            .setUrl(req.getUrl()) // 打开特定url
            .setIntent(req.getIntent())// 当type为1时必选 自定义页面
            .build();
        BadgeNotification badgeNotification = new BadgeNotification(99, "Classic");
        AndroidNotification androidNotification =
            AndroidNotification.builder().setTitle(new String(req.getTitle().getBytes("utf-8"), "utf-8"))// 标题
                .setBody(req.getMsg())// 消息
                .setColor("#FFFFFF")
                // .setSound("/raw/shake")
                .setClickAction(clickAction)
                // .setTitleLocKey("demo_title_new2")
                // .addTitleLocArgs("title_args")
                // .addTitleLocArgs("title_args2")
                // .setBodyLocKey("demo_content_new2")
                // .addBodyLocArgs("content_args")
                // .addBodyLocArgs("content_args2")
                // .setChannelId("RingRing")
                .setNotifySummary(req.getSubtitle()).setStyle(0) // 0：默认样式 1：大文本样式
                // .setBigTitle(req.getTitle()) //style为1时必选
                // .setBigBody(req.getMsg()) //style为1时必选
                // .setBigPicture("https://developer-portalres-drcn.dbankcdn.com/system/modules/org.opencms.portal.template.core/resources/images/icon_Promotion.png")
                .setAutoClear(86400000).setNotifyId(486)
                // .setBadge(badgeNotification)
                .build();

        AndroidConfig androidConfig =
            AndroidConfig.builder().setCollapseKey(-1).setTtl("1448s").setBiTag(req.getTitle())// 消息回执需要传的
                .setFastAppTargetType(1).setNotification(androidNotification).build();

        String data = "k=v,k=v";
        Message message = Message.builder().setNotification(notification).setAndroidConfig(androidConfig)
            .addAllToken(tokenList).build();

        return message;
    }

}
