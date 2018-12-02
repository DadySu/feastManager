package com.up.feast.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * g_good
 * @author 
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GGood implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品价格
     */
    private Long price;

    /**
     * 商品原价格
     */
    private Long oldprice;

    /**
     * 商品简介
     */
    private String description;
    /**
     * 销售数量
     */
    private Short sellcount;

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

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

}