package com.up.feast.mapper;

import com.up.feast.model.GGoodsKind;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * GGoodsKindDAO继承基类
 */
@Mapper
@Component
public interface GGoodsKindDAO extends MyBatisBaseDao<GGoodsKind, Long> {
    /**
     * 根据商户id获取商品详情
     * @param merchantId
     * @return
     */
    List<GGoodsKind> selectByMerchantId(@Param(value = "merchantId") Long merchantId);
}