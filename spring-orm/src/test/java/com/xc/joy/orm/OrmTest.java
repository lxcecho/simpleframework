package com.xc.joy.orm;

import com.alibaba.fastjson.JSON;
import com.xc.joy.orm.dao.MemberDao;
import com.xc.joy.orm.dao.OrderDao;
import com.xc.joy.orm.entity.Member;
import com.xc.joy.orm.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.core.common.Page;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author lxcecho 909231497@qq.com
 * @since 22:05 08-07-2022
 */
@ContextConfiguration(locations = {"classpath:application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class OrmTest {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmdd");

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private OrderDao orderDao;

    // ORM（对象关系映射 Object Relation Mapping）
    // Hibernate/Spring JDBC/MyBatis/JPA 一对多、多对多、一对一

    // Hibernate 全自动档  不需要写一句SQL语句
    // MyBatis 半自动（手自一体） 支持简单的映射，复杂关系，需要自己写SQL
    // Spring JDBC 全手动挡，所有的SQL都要自己写，它帮我们设计了一套标准  模板模式

    // 为什么有了 MyBatis 我还要自己的手写 ORM 框架呢？
    // 1、用 MyBatis，我可控性无法保证
    // 2、我有不敢用 Hibernate，高级玩家玩的，
    // 3、没有时间自己从 0 到 1 写一个 ORM 框架
    // 4、站在巨人的肩膀上再升级，做二次开发

    // 约定优于配置
    // 1、先制定顶层接口,参数返回值全部统一
    // List<?> Page<?>  select(QueryRule queryRule)
    // Int   delete(T entity) entity 中 的 ID 不能为空，如果 ID 为空，其他条件不能为空，都为空不予执行
    // ReturnId  insert(T entity) 只要 entity 不等于 null
    // Int  update(T entity) entity 中的 ID 不能为空，如果 ID 为空，其他条件不能为空，都为空不予执行

    // 基于 JDBC 封装了一套
    // 基于 Redis 封装了一套
    // 基于 MongoDB
    // 基于 ElasticSearch
    // 基于 Hive
    // 基于 HBase

    // QueryRule

    @Test
    public void testSelectForPage() {
        try {
            Page page = memberDao.selectForPage(2, 3);
            System.out.println("总条数： " + page.getTotal());
            System.out.println("当前第几页：" + page.getPageNo());
            System.out.println("每页多少条：" + page.getPageSize());
            System.out.println("本页的数据：" + JSON.toJSONString(page.getRows(), true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testSelectAllForMember() {
        try {
            List<Member> result = memberDao.selectAll();
            System.out.println(JSON.toJSONString(result, true));
//            System.out.println(Arrays.toString(result.toArray()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
//    @Ignore
    public void testInsertMember() {
        try {
            for (int age = 25; age < 35; age++) {
                Member member = new Member();
                member.setAge(age);
                member.setName("Tom");
                member.setAddr("Hunan Changsha");
                memberDao.insert(member);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
//	@Ignore
    public void testInsertOrder() {
        try {
            Order order = new Order();
            order.setMemberId(1L);
            order.setDetail("历史订单");
            Date date = sdf.parse("20190426123456");
            order.setCreateTime(date.getTime());
            orderDao.insertOne(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
