package base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import common.utils.MapUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author liub
 * @date 2018/12/2 12:33
 * @version 1.0
 */
public abstract class BaseService<T> {

    private Class<T> entityClass;

    public BaseService() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        entityClass = (Class) params[0];
    }

    public abstract List<T> query(T model) throws Exception;

    public abstract int queryCount(T model);

    public abstract Long save(T model);

    public abstract T getById(Long id);

    public abstract void update(T model);

    public abstract T getByCriteria(T model);

    public List<T> query(Map<String, Object> searchParams) throws Exception {
        return query(mapToModel(searchParams));
    }

    public PageInfo queryPage(Map<String, Object> searchParams) throws Exception {
        BaseCriteria criteria = BaseCriteria.newInstance();
        criteria.setPageParams(searchParams);
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        T t = mapToModel(searchParams);
        return new PageInfo(this.query(t));
    }

    public T mapToModel(Map<String, Object> searchParams) throws Exception {
        return (T) MapUtil.mapToObject(searchParams, entityClass);
    }
}
