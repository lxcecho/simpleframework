package com.xc.joy.transaction;

import com.alibaba.fastjson.JSON;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author lxcecho 909231497@qq.com
 * @since 22:38 08-07-2022
 */
@ContextConfiguration(locations = {"classpath*:application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;

    @Test
//    @Ignore
    public void queryAll() {
        try {
            List<Employee> list = employeeService.queryAll();
            System.out.println(JSON.toJSONString(list, true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
//    @Ignore
    public void testRemove() {
        try {
            boolean r = employeeService.remove(1L);
            System.out.println(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Ignore
    public void testLogin() {
        try {
            employeeService.login(1L, "echo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAdd() {
        try {
            Employee employee = new Employee("lxcecho", "GuangXi Qinzhou", 18);
            employeeService.add(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
