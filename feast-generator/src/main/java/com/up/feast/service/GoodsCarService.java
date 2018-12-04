package com.up.feast.service;

import base.BaseResponse;
import com.up.feast.mapper.GoodCarDAO;
import com.up.feast.model.GoodCar;
import common.result.err.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description 购物车service
 *
 * @author liub
 * @date 2018-12-03 14:07
 */
@Service
@Slf4j
public class GoodsCarService<T> {

    @Resource
    private GoodCarDAO goodCarDAO;

    /**
     * 添加购物车
     * @param goodCar
     * @return
     */
    public BaseResponse addShoppingCar(GoodCar goodCar) {

        int num = goodCarDAO.insert(goodCar);
        if (num == 1) {
            return BaseResponse.newInstanceSuccess("添加购物车成功");
        } else {
            return BaseResponse.newInstanceError("添加购物车失败");
        }
    }

    /**
     * 查询购物车
     *
     * @param userId merchant_id
     * @return
     */
    public BaseResponse queryShoppingCar(String userId, String merchant_id) {
        Assert.notNull(userId, "userId不能为空");
        Assert.notNull(merchant_id, "merchant_id不能为空");

        try {
            GoodCar goodCar = new GoodCar();
            goodCar.setUserId(Long.parseLong(userId));
            goodCar.setMerchantId(Long.parseLong(merchant_id));

            List<GoodCar> goodCarList = goodCarDAO.selectByUserIdAndMerchantId(goodCar);
            return BaseResponse.newInstanceSuccess(goodCarList.toString());
        } catch (BizException e) {
            log.error("queryShoppingCar bizexception: {}", e.getMessage());
            return BaseResponse.newInstanceError(e.getMessage());
        } catch (Exception e) {
            log.error("queryShoppingCar exception: {}", e.getMessage());
            return BaseResponse.newInstanceError(e.getMessage());
        }


    }
}
