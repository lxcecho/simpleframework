package com.xc.joy.orm.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author lxcecho 909231497@qq.com
 * @since 22:28 08-07-2022
 */
@Entity
@Table(name = "t_order")
@Data
public class Order implements Serializable {

    private Long id;

    @Column(name = "mid")
    private Long memberId;

    private String detail;

    private Long createTime;

    private String createTimeFmt;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", detail='" + detail + '\'' +
                ", createTime=" + createTime +
                ", createTimeFmt='" + createTimeFmt + '\'' +
                '}';
    }
}
