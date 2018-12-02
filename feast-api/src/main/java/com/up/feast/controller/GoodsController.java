package com.up.feast.controller;

import com.up.feast.controller.base.BaseController;
import com.up.feast.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/goods/queryGoodsById/{merchantId}")
    @ApiOperation("根据商户id查询商品信息")
    @ApiParam(value = "merchantId")
    public String queryGoodsByKindId(@PathVariable String merchantId){
        log.info("查询商品详情的参数 {}",merchantId);
        return goodsService.selectByMerchantId(merchantId);
    }
}
