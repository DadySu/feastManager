package base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author mengfanzhu
 * @Date 3/19/18 17:24
 * @Version 1.0
 */
public  interface BaseMapper<T, PK> {

    T getById(@Param("id") PK id);

    T getByCriteria(T model);

    int queryCount(T model);

    List<T> query(T model);

    Long insert(T t);

    int update(T t);

    int delete(PK id);

    void deleteByModel(T model);
}
