package com.xc.joy.transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/*
 * @author lxcecho 909231497@qq.com
 * @since 22:38 08-07-2022
 */
@Repository
public class EmployeeDao {

    private JdbcTemplate template;

    @Resource(name = "dataSource")
    protected void setDataSource(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    public List<Employee> selectAll() throws Exception {
        String sql = "select * from t_member";
        return template.query(sql, new RowMapper<Employee>() {
            public Employee mapRow(ResultSet rs, int i) throws SQLException {
                Employee employee = new Employee();
                employee.setName(rs.getString("name"));
                employee.setId(rs.getLong("id"));
                employee.setAddr(rs.getString("addr"));
                employee.setAge(rs.getInt("age"));
                return employee;
            }
        });
    }

    public boolean insert(Employee m) throws Exception {
        String sql = "insert into t_member(id,name,addr,age) value(?,?,?,?)";
        int count = template.update(sql, m.getId(), m.getName(), m.getAddr(), m.getAge());
        return count > 0;
    }

    public boolean delete(long id) throws Exception {
        return template.update("delete from t_member where id = ?", id) > 0;
    }

    public boolean update(long id, String name) throws Exception {
        return template.update("update t_member set name = ? where id = ?", name, id) > 0;
    }

}
