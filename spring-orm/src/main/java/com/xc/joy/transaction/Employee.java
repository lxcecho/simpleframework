package com.xc.joy.transaction;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * @author lxcecho 909231497@qq.com
 * @since 22:38 08-07-2022
 */
@Entity
@Table(name = "t_employee")
@Data
public class Employee implements Serializable {

    @Id
    private Long id;

    private String name;

    private String addr;

    private Integer age;

    public Employee(String name, String addr, Integer age) {
        this.name = name;
        this.addr = addr;
        this.age = age;
    }

    public Employee() {
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                ", age=" + age +
                '}';
    }
}
