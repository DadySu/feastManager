package com.up.feast.controller.handler;

import base.BaseResponse;
import com.alibaba.fastjson.JSONObject;
import com.up.feast.annotation.ValidateAnnotation;
import com.up.feast.model.GoodCar;
import com.up.feast.service.GoodsCarService;
import com.up.feast.vo.request.GoodsCarRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Description
 *
 * @author liub
 * @date 2018-12-04 14:55
 */
@Component
@Slf4j
public class GoodsHandler {

    @Resource
    private GoodsCarService goodsCarService;

    /**
     * 查询购物车
     *
     * @param userId merchant_id
     * @return
     */
    @ValidateAnnotation
    public BaseResponse queryShoppingCar(String userId, String merchant_id) {
        return goodsCarService.queryShoppingCar(userId, merchant_id);
    }

    /**
     * 添加购物车
     *
     * @param requestBody
     * @return
     */
    public BaseResponse addShoppingCar(String requestBody, Class<GoodsCarRequest> goodsCarRequestClass) {
        JSONObject goodCarObject = JSONObject.parseObject(requestBody);
        GoodsCarRequest goodsCarRequest = JSONObject.toJavaObject(goodCarObject, goodsCarRequestClass);

        GoodCar goodCar = new GoodCar();
        BeanUtils.copyProperties(goodsCarRequest, goodCar);
        return goodsCarService.addShoppingCar(goodCar);
    }
}
