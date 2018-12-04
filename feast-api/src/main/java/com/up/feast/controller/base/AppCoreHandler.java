package com.up.feast.controller.base;


import base.BaseResponse;
import com.alibaba.fastjson.JSONObject;
import common.CommonUtils;
import common.utils.ApiErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;

@Slf4j
@Component
public class AppCoreHandler {

    /**
     * process 接口核心前置处理
     *
     * @param requestBody
     * @return BaseResponse
     */
    protected BaseResponse processBefore(String requestBody, Class clazz) throws Exception {
        log.info("===========接收接口" + clazz.getName() + "逻辑处理===========");
        log.info("===========Request:" + requestBody);
        //1.密文校验
        BaseResponse baseResponse = BaseResponse.newInstanceSuccess();
        baseResponse.setBody(requestBody);
        //2.版本校验
        String requestBodyStr = String.valueOf(baseResponse.getBody());
        Map<String, Object> requestMap = JSONObject.parseObject(requestBodyStr, new TreeMap<String, Object>().getClass());
        log.info("===========校验json参数===========");
        baseResponse = CommonUtils.validateRequest(requestMap, clazz);
        //3.基础参数校验（非空，长度）
        if (!ApiErrorEnum.SUCCESS.getRetCode().equals(baseResponse.getRetCode())) {
            log.info("===========参数校验失败，原因：" + baseResponse.getRetMessage() + "===========");
            return baseResponse;
        }
        baseResponse.setBody(requestBodyStr);
        return baseResponse;
    }

    /**
     * process  接口核心后置处理
     *
     * @param baseResponse
     * @return BaseResponse
     */
    protected BaseResponse processAfter(BaseResponse baseResponse) {
        try {
            if (!ApiErrorEnum.SUCCESS.getRetCode().equals(baseResponse.getRetCode())) {
                log.info("===========业务处理失败，原因：" + baseResponse.getRetMessage() + "===========");
                return baseResponse;
            }
            log.info("===========接收接口逻辑处理成功,返回值:===========" + JSONObject.toJSONString(baseResponse));
            return baseResponse;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("接口逻辑处理异常:{}", e);
            return BaseResponse.newInstanceError("接口逻辑处理异常");
        }
    }


}
