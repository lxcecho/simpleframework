package javax.core.common.jdbc;

import com.xc.joy.orm.framework.QueryRule;

import javax.core.common.Page;
import java.util.List;
import java.util.Map;

/**
 * @author lxcecho 909231497@qq.com
 * @since 21:34 09-07-2022
 */
public interface BaseDao<T, PK> {

    /**
     * 获取列表
     *
     * @param queryRule 查询条件
     * @return 结果集
     * @throws Exception 异常
     */
    List<T> select(QueryRule queryRule) throws Exception;

    /**
     * 获取分页结果
     *
     * @param queryRule 查询条件
     * @param pageNo    页码
     * @param pageSize  每页条数
     * @return 分页结果集
     * @throws Exception 异常
     */
    Page<?> select(QueryRule queryRule, int pageNo, int pageSize) throws Exception;

    /**
     * 根据SQL获取列表
     *
     * @param sql  SQL语句
     * @param args 参数
     * @return 结果集
     * @throws Exception 异常
     */
    List<Map<String, Object>> selectBySql(String sql, Object... args) throws Exception;

    /**
     * 根据SQL获取分页
     *
     * @param sql      SQL语句
     * @param pageNo   页码
     * @param pageSize 每页条数
     * @return 分页结果集
     * @throws Exception 异常
     */
    Page<Map<String, Object>> selectBySqlToPage(String sql, Object[] param, int pageNo, int pageSize) throws Exception;


    /**
     * 删除一条记录
     *
     * @param entity entity 中的 ID 不能为空，如果 ID 为空，其他条件不能为空，都为空不予执行
     * @return 操作结果
     * @throws Exception 异常
     */
    boolean delete(T entity) throws Exception;

    /**
     * 批量删除
     *
     * @param list 入参
     * @return 返回受影响的行数
     * @throws Exception 异常
     */
    int deleteAll(List<T> list) throws Exception;

    /**
     * 插入一条记录并返回插入后的 ID
     *
     * @param entity 只要 entity 不等于 null，就执行插入
     * @return 操作结果
     * @throws Exception 异常
     */
    PK insertAndReturnId(T entity) throws Exception;

    /**
     * 插入一条记录自增 ID
     *
     * @param entity 入参
     * @return 操作结果
     * @throws Exception 异常
     */
    boolean insert(T entity) throws Exception;

    /**
     * 批量插入
     *
     * @param list 入参
     * @return 返回受影响的行数
     * @throws Exception 异常
     */
    int insertAll(List<T> list) throws Exception;

    /**
     * 修改一条记录
     *
     * @param entity entity中的 ID 不能为空，如果 ID 为空，其他条件不能为空，都为空不予执行
     * @return 操作结果
     * @throws Exception 异常
     */
    boolean update(T entity) throws Exception;
}
