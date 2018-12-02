package com.up.feast.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * g_goods_kind
 * @author 
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GGoodsKind implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单类别
     */
    private Integer typed;

    /**
     * 商户id
     */
    private Long merchantId;

    private Date updateTime;

    private Date createTime;

    /**
     * 主键
     */
    private Long goodsId;
    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品价格
     */
    private Long price;

    /**
     * 商品原价格
     */
    private Long oldPrice;

    /**
     * 销售数量
     */
    private Short sellCount;

    /**
     * 评论数
     */
    private Short rating;

    /**
     * 商品描述
     */
    private String info;

    /**
     * 商品种类id
     */
    private Long goodKindId;

    /**
     * 商品简介
     */
    private String description;
}