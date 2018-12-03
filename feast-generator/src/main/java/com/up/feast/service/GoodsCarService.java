package com.up.feast.service;

import base.BaseResponse;
import com.alibaba.fastjson.JSONObject;
import com.up.feast.mapper.GoodCarDAO;
import com.up.feast.model.GoodCar;

import javax.annotation.Resource;

/**
 * Description 购物车service
 *
 * @author liub
 * @date 2018-12-03 14:07
 */
public class GoodsCarService {

    @Resource
    private GoodCarDAO goodCarDAO;

    /**
     * 添加购物车
     *
     * @param requestBody
     * @return
     */
    public BaseResponse addShoppingCar(String requestBody) {
        GoodCar goodCar = JSONObject.parseObject(requestBody, GoodCar.class);
        int num = goodCarDAO.insert(goodCar);
        if (num == 1) {
            return BaseResponse.newInstanceSuccess("添加购物车成功");
        } else {
            return BaseResponse.newInstanceError("添加购物车失败");
        }
    }
}
