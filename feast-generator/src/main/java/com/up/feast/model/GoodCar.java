package com.up.feast.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * g_good_car
 *
 * @author liub
 * @date 2018/12/2 12:22
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "goodCar", description = "购物车实体类")
public class GoodCar implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    private Long goodId;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodName;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
     * 商户id
     */
    @ApiModelProperty(value = "商户id")
    private Long merchantId;

    /**
     * 配送地址
     */
    @ApiModelProperty(value = "配送地址")
    private String location;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "配送手机号")
    private String mobile;

    /**
     * 数量
     */
    @ApiModelProperty(value = "该商品数量")
    private Short amount;

    /**
     * 价格
     */
    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    private static final long serialVersionUID = 1L;
}