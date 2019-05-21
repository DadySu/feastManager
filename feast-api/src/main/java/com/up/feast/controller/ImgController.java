package com.up.feast.controller;

import base.BaseResponse;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Description
 *
 * @author liub
 * @date 2019-04-10 12:40
 */
@Api(tags = "下载图片接口")
@Slf4j
@RestController
public class ImgController {


    @GetMapping("/downloadImg")
    public String downloadImg(HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + "headPic.jpg");
        File imgFile = new File("D:\\selfWorkspace\\feastManager\\Logo.jpg");
        responseFile(response, imgFile);
        return "true";
    }

    private void responseFile(HttpServletResponse response, File imgFile) {
        try (InputStream is = new FileInputStream(imgFile);
             OutputStream os = response.getOutputStream()) {
            // 图片文件流缓存池
            byte[] buffer = new byte[1024];
            while (is.read(buffer) != -1) {
                os.write(buffer);
            }
            os.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    @GetMapping("/baseImg")
    public String baseImg() {
        log.info("base64img");
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream("D:\\selfWorkspace\\feastManager\\Logo.jpg");
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        //返回Base64编码过的字节数组字符串
        return encoder.encode(data);
    }


    @GetMapping("/testResponse")
    public BaseResponse testResponse() {
        BaseResponse response = BaseResponse.newInstanceSuccess();
        Map map = new HashMap<>(1);
        map.put("uniqueCode", "1515616");
        response.setBody(map);

        return response;
    }
}
