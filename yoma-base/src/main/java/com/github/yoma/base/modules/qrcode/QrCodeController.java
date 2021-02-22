package com.github.yoma.base.modules.qrcode;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.yoma.common.annotation.AnonymousAccess;
import com.github.yoma.common.utils.QRCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "二维码")
@Controller
public class QrCodeController {

    @AnonymousAccess
    @ApiOperation("根据 url 生成 普通二维码并返回文件流")
    @RequestMapping(value = "/createCommonQRCode")
    public void createCommonQRCode(HttpServletResponse response, String content) throws Exception {
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            // 使用工具类生成二维码
            QRCodeUtil.encode(content, stream);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }
    // @AnonymousAccess
    // @ApiOperation("根据 url 生成 普通二维码")
    // @PostMapping(value = "/createCommonQRCodeUrl")
    // public void createCommonQRCodeReturnUrl(HttpServletResponse response, String url) throws Exception {
    // ServletOutputStream stream = null;
    // try {
    // stream = response.getOutputStream();
    // // 使用工具类生成二维码
    // QRCodeUtil.encode(url, stream);
    // } catch (Exception e) {
    // e.getStackTrace();
    // } finally {
    // if (stream != null) {
    // stream.flush();
    // stream.close();
    // }
    // }
    // }

    // @AnonymousAccess
    // @ApiOperation("根据 url 生成 带有logo二维码")
    // @PostMapping(value = "/createLogoQRCode")
    // public void createLogoQRCode(HttpServletResponse response, String url) throws Exception {
    // ServletOutputStream stream = null;
    // try {
    // stream = response.getOutputStream();
    // String logoPath = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "templates"
    // + File.separator + "logo.jpg";
    // // 使用工具类生成二维码
    // QRCodeUtil.encode(url, logoPath, stream, true);
    // } catch (Exception e) {
    // e.getStackTrace();
    // } finally {
    // if (stream != null) {
    // stream.flush();
    // stream.close();
    // }
    // }
    // }

}
