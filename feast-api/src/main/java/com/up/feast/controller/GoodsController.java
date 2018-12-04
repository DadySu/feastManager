package com.up.feast.controller;

import base.BaseResponse;
import com.up.feast.controller.base.BaseController;
import com.up.feast.controller.handler.GoodsHandler;
import com.up.feast.service.GoodsService;
import com.up.feast.vo.request.GoodsCarRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description 商品相关接口
 * @author liub
 * @date 2018-12-01 19:07
 */
@Api(tags = "商品相关接口")
@Slf4j
@RestController
public class GoodsController extends BaseController {

    @Resource
    private GoodsService goodsService;
    @Resource
    private GoodsHandler goodsHandler;

    @GetMapping("/goods/queryGoodsById/{merchantId}")
    @ApiOperation("根据商户id查询商品信息")
    @ApiParam(value = "merchantId")
    public String queryGoodsByKindId(@PathVariable String merchantId){
        log.info("查询商品详情的参数 {}",merchantId);
        String response = goodsService.selectByMerchantId(merchantId);
        log.info("查询商品详情返回的结果 {}", response);
        return response;
    }

    @GetMapping("/goods/addShoppingCar")

    @ApiImplicitParam(value = GoodsCarRequest.ADD_SHOPPING_CAR)
    public BaseResponse addShoppingCar(@RequestParam String requestBody) {
        log.info("添加购物车的参数 {}", requestBody);
        return goodsHandler.addShoppingCar(requestBody, GoodsCarRequest.class);
    }

    @GetMapping("/goods/queryShoppingCar")
    @ApiOperation("查询购物车信息")
    public BaseResponse queryShoppingCar(@RequestParam String userId, @RequestParam String merchant_id) {
        log.info("查询购物车信息的参数 userId :{},merchant_id :{}", userId, merchant_id);
        BaseResponse response = goodsHandler.queryShoppingCar(userId, merchant_id);
        log.info("查询购物车信息返回结果 response :{}}", response);
        return response;
    }
}
