package com.up.feast.vo.request;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Description
 *
 * @author liub
 * @date 2018-12-04 12:39
 */
@Data
@Getter
public class GoodsCarRequest {

    public static final String ADD_SHOPPING_CAR = "添加购物车参数描述:{\"goodId\":\"商品id\",\"goodName\":\"商品名称\",\"userId\":\"用户id\"," +
            "\"merchantId\":\"商户id\",\"location\":\"配送地址\",\"mobile\":\"配送手机号\"," +
            "\"amount\":\"该商品数量\",\"price\":\"价格\"}";
    /**
     * 商品id
     */
    @NotNull(message = "商品id不能为空")
    private Long goodId;

    /**
     * 商品名称
     */
    @NotEmpty(message = "商品名称不能为空")
    private String goodName;

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Long userId;

    /**
     * 商户id
     */
    @NotNull(message = "商户id不能为空")
    private Long merchantId;

    /**
     * 配送地址
     */
    private String location;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 数量
     */
    @NotNull(message = "数量不能为空")
    private Integer amount;

    /**
     * 价格
     */
    @NotEmpty(message = "价格不能为空")
    private String price;
}
