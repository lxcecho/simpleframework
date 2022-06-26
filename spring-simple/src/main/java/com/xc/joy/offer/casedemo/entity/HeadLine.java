package com.xc.joy.offer.casedemo.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author lxcecho
 * @since 2021/1/4
 */
@Data
public class HeadLine {

    private Long lineId;

    private String lineName;

    private String lineLink;

    private String lineImg;

    private Integer priority;

    private Integer enableStatus;

    private Date createTime;

    private Date lastEditTime;

}
