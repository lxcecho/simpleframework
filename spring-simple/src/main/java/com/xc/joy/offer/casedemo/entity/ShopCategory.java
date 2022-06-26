package com.xc.joy.offer.casedemo.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author lxcecho
 * @since 2021/1/4
 */
@Data
public class ShopCategory {

    private Long shopCategoryId;

    private String shopCategoryName;

    private String shopCategoryDesc;

    private String shopCategoryImg;

    private Integer priority;

    private Date createTime;

    private Date lastEditTime;

    private ShopCategory parent;

}
