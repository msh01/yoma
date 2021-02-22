package com.github.yoma.tools.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import javax.annotation.Resource;

import com.github.yoma.tools.config.JpushConfig;
import org.springframework.stereotype.Service;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.ServiceHelper;
import cn.jiguang.common.connection.NettyHttpClient;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jiguang.common.resp.ResponseWrapper;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import io.netty.handler.codec.http.HttpMethod;
import lombok.extern.slf4j.Slf4j;

/**
 * @author msh01
 * @version 1.0.0
 * @createTime 2020年05月18日 00:23:00
 */
@Service("jpushService")
@Slf4j
public class JpushService {

    @Resource
    JpushConfig jpushConfig;

    /**
     * 发送自定义消息，由APP端拦截信息后再决定是否创建通知(目前APP用此种方式)
     *
     * @param title
     *            App通知栏标题
     * @param content
     *            App通知栏内容（为了单行显示全，尽量保持在22个汉字以下）
     * @param extrasMap
     *            额外推送信息（不会显示在通知栏，传递数据用）
     * @param alias
     *            别名数组，设定哪些用户手机能接收信息（为空则所有用户都推送）
     * @return PushResult
     */
    public PushResult sendCustomPush(String title, String content, Map<String, String> extrasMap, String... alias)
        throws APIConnectionException, APIRequestException {
        ClientConfig clientConfig = ClientConfig.getInstance();
        clientConfig.setTimeToLive(Long.parseLong(jpushConfig.getLiveTime()));
        // 使用NativeHttpClient网络客户端，连接网络的方式，不提供回调函数
        JPushClient jpushClient =
            new JPushClient(jpushConfig.getMasterSecret(), jpushConfig.getAppkey(), null, clientConfig);
        // 设置为消息推送方式为仅推送消息，不创建通知栏提醒
        // PushPayload payload = buildCustomPushPayload(title, content, extrasMap, alias);
        PushPayload payload = buildCustomPushPayloadByAlias(title, content, extrasMap, alias);
        PushResult result = jpushClient.sendPush(payload);
        return result;
    }

    /**
     * 发送通知消息
     *
     * @param title
     *            App通知栏标题
     * @param content
     *            App通知栏内容（为了单行显示全，尽量保持在22个汉字以下）
     * @param extrasMap
     *            额外推送信息（不会显示在通知栏，传递数据用）
     * @param tags
     *            标签数组，设定哪些用户手机能接收信息（为空则所有用户都推送）
     */
    public PushResult sendPush(String title, String content, Map<String, String> extrasMap, String... tags)
        throws APIConnectionException, APIRequestException {
        ClientConfig clientConfig = ClientConfig.getInstance();
        clientConfig.setTimeToLive(Long.valueOf(jpushConfig.getLiveTime()));
        // 使用NativeHttpClient网络客户端，连接网络的方式，不提供回调函数
        JPushClient jpushClient =
            new JPushClient(jpushConfig.getMasterSecret(), jpushConfig.getAppkey(), null, clientConfig);
        // 设置推送方式
        PushPayload payload = buildPushLoadByTags(title, content, extrasMap, tags);
        PushResult result = jpushClient.sendPush(payload);
        return result;
    }

    public PushResult sendPushByRegistrationIds(String title, String content, Map<String, String> extrasMap,
        Collection<String> registrationIds) throws APIConnectionException, APIRequestException {
        ClientConfig clientConfig = ClientConfig.getInstance();
        clientConfig.setTimeToLive(Long.valueOf(jpushConfig.getLiveTime()));
        // 使用NativeHttpClient网络客户端，连接网络的方式，不提供回调函数
        JPushClient jpushClient =
            new JPushClient(jpushConfig.getMasterSecret(), jpushConfig.getAppkey(), null, clientConfig);
        // 设置推送方式
        PushResult result = new PushResult();
        if (registrationIds.isEmpty()) {
        } else {
            PushPayload payload = buildPushPayloadByRegistrationIds(title, content, extrasMap, registrationIds);
            result = jpushClient.sendPush(payload);
        }
        return result;
    }

    /**
     * 异步请求推送方式 使用NettyHttpClient,异步接口发送请求，通过回调函数可以获取推送成功与否情况
     *
     * @param title
     *            通知栏标题
     * @param content
     *            通知栏内容（为了单行显示全，尽量保持在22个汉字以下）
     * @param extrasMap
     *            额外推送信息（不会显示在通知栏，传递数据用）
     * @param alias
     *            需接收的用户别名数组（为空则所有用户都推送）
     */
    public void sendPushWithCallback(String title, String content, Map<String, String> extrasMap, String... alias) {
        ClientConfig clientConfig = ClientConfig.getInstance();
        clientConfig.setTimeToLive(Long.valueOf(jpushConfig.getLiveTime()));
        String host = (String)clientConfig.get(ClientConfig.PUSH_HOST_NAME);
        NettyHttpClient client = new NettyHttpClient(
            ServiceHelper.getBasicAuthorization(jpushConfig.getAppkey(), jpushConfig.getMasterSecret()), null,
            clientConfig);
        try {
            URI uri = new URI(host + clientConfig.get(ClientConfig.PUSH_PATH));
            PushPayload payload = buildPushPayloadByAlias(title, content, extrasMap, alias);
            client.sendRequest(HttpMethod.POST, payload.toString(), uri, new NettyHttpClient.BaseCallback() {
                @Override
                public void onSucceed(ResponseWrapper responseWrapper) {
                    if (200 == responseWrapper.responseCode) {
                        log.info("极光推送成功");
                    } else {
                        log.info("极光推送失败，返回结果: " + responseWrapper.responseContent);
                    }
                }
            });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            // 需要手动关闭Netty请求进程,否则会一直保留
            client.close();
        }

    }

    /**
     * 设置、更新、设备的 tag, alias 信息。
     *
     * @param registrationId
     *            设备的registrationId
     * @param alias
     *            更新设备的别名属性
     * @param tagsToAdd
     *            添加设备的tag属性
     * @param tagsToRemove
     *            移除设备的tag属性
     */
    public void UpdateDeviceTagAlias(String registrationId, String alias, Set<String> tagsToAdd,
        Set<String> tagsToRemove) throws APIConnectionException, APIRequestException {
        JPushClient jpushClient = new JPushClient(jpushConfig.getMasterSecret(), jpushConfig.getAppkey());
        jpushClient.updateDeviceTagAlias(registrationId, alias, tagsToAdd, tagsToRemove);
    }

    /**
     * 构建Android和IOS的推送通知对象
     *
     * @return PushPayload
     */
    private PushPayload buildPushPayloadByAlias(String title, String content, Map<String, String> extrasMap,
        String... alias) {
        if (extrasMap == null || extrasMap.isEmpty()) {
            extrasMap = new HashMap<String, String>();
        }
        // 批量删除数组中空元素
        String[] newAlias = removeArrayEmptyElement(alias);
        return PushPayload.newBuilder().setPlatform(Platform.android_ios())
            // 别名为空，全员推送；别名不为空，按别名推送
            .setAudience((null == newAlias || newAlias.length == 0) ? Audience.all() : Audience.alias(alias))
            // .setAudience(Audience.registrationId("d"))
            .setNotification(Notification.newBuilder().setAlert(content)
                .addPlatformNotification(AndroidNotification.newBuilder().setTitle(title).addExtras(extrasMap).build())
                .addPlatformNotification(IosNotification.newBuilder().incrBadge(1).addExtras(extrasMap).build())
                .build())
            .build();
    }

    private PushPayload buildPushPayloadByRegistrationIds(String title, String content, Map<String, String> extrasMap,
        Collection<String> registrationIds) {
        if (extrasMap == null || extrasMap.isEmpty()) {
            extrasMap = new HashMap<String, String>();
        }
        return PushPayload.newBuilder().setPlatform(Platform.android_ios())
            .setAudience(Audience.registrationId(registrationIds))
            .setNotification(Notification.newBuilder().setAlert(content)
                .addPlatformNotification(AndroidNotification.newBuilder().setTitle(title).addExtras(extrasMap).build())
                .addPlatformNotification(IosNotification.newBuilder().incrBadge(1).addExtras(extrasMap).build())
                .build())
            .build();
    }

    /**
     * 根据标签推送相应的消息
     *
     * @param title
     *            推送消息标题
     * @param content
     *            推送消息内容
     * @param map
     *            推送额外信息
     * @param tags
     *            推送的目标标签
     * @return
     */
    public PushPayload buildPushLoadByTags(String title, String content, Map<String, String> map, String... tags) {
        if (map.isEmpty()) {
            map = new HashMap<>();
        }
        // 批量删除数组中的空元素
        String[] newTags = removeArrayEmptyElement(tags);
        return PushPayload.newBuilder()
            // 设置推送平台为安卓
            .setPlatform(Platform.android())
            // 设置标签
            .setAudience(Audience.tag(tags))
            // 设置 推送的标签标题
            // 设置通知方式(以alert方式提醒)
            .setNotification(Notification.newBuilder().setAlert(content)
                .addPlatformNotification(AndroidNotification.newBuilder().setTitle(title).addExtras(map).build())
                .build())
            // sendno int 可选 推送序号 纯粹用来作为 API 调用标识
            // 离线消息保留时长 推送当前用户不在线时，为该用户保留多长时间的离线消息(默认 86400 （1 天），最长 10 天。设置为 0 表示不保留离线消息，只有推送当前在线的用户可以收到)
            // apns_production boolean 可选 APNs是否生产环境 True 表示推送生产环境，False 表示要推送开发环境； 如果不指定则为推送生产环境
            // big_push_duration int 可选 定速推送时长（分钟） 又名缓慢推送，把原本尽可能快的推送速度，降低下来，在给定的 n 分钟内，均匀地向这次推送的目标用户推送。最大值为
            // 1440。未设置则不是定速推送
            // .setOptions(Options.newBuilder().setApnsProduction(false).setTimeToLive(8600).setBigPushDuration(1).build())
            // 设置通知内容
            // .setMessage(Message.newBuilder().setTitle("").setMsgContent("").setContentType("").build())
            .build();
    }

    /**
     * 构建Android和IOS的自定义消息的推送消息对象
     *
     * @return PushPayload
     */
    private PushPayload buildCustomPushPayloadByAlias(String title, String content, Map<String, String> extrasMap,
        String... alias) {
        // 批量删除数组中空元素
        String[] newAlias = removeArrayEmptyElement(alias);
        return PushPayload.newBuilder().setPlatform(Platform.android_ios())
            .setAudience((null == newAlias || newAlias.length == 0) ? Audience.all() : Audience.alias(alias))
            .setNotification(Notification.newBuilder().setAlert(content)
                .addPlatformNotification(AndroidNotification.newBuilder().setTitle(title).addExtras(extrasMap).build())
                .build())
            .setMessage(Message.newBuilder().setTitle(title).setMsgContent(content).addExtras(extrasMap).build())
            .build();
    }

    /**
     * 删除别名中的空元素（需删除如：null,""," "）
     *
     * @param strArray
     * @return String[]
     */
    private String[] removeArrayEmptyElement(String... strArray) {
        if (null == strArray || strArray.length == 0) {
            return null;
        }
        List<String> tempList = Arrays.asList(strArray);
        List<String> strList = new ArrayList<String>();
        Iterator<String> iterator = tempList.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            // 消除空格后再做比较
            if (null != str && !"".equals(str.trim())) {
                strList.add(str);
            }
        }
        // 若仅输入"",则会将数组长度置为0
        String[] newStrArray = strList.toArray(new String[strList.size()]);
        return newStrArray;
    }
}
