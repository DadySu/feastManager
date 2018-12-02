package common.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.cglib.beans.BeanMap;

import java.util.List;
import java.util.Map;


/** 
* @ClassName: MapUtil 
* @Description: map 工具类
* @author mengfanzhu
* @date Aug 16, 2017 10:50:55 AM 
*/
public class MapUtil {
	/**
	 * @Title: MapUtil
	 * @Description: map 转 object
	 * @param map
	 * @param beanClass
	 * @return
	 * @throws Exception
	 * @author mengfanzhu
	 * @throws
	 */
	public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
		if (map == null) { return null; }
		Object obj = beanClass.newInstance();
		BeanUtils.populate(obj, map);
		return obj;
	}

	/**
	 * 将对象装换为map
	 * @param bean
	 * @return
	 */
	public static <T> Map<String, Object> beanToMap(T bean) {
		Map<String, Object> map = Maps.newHashMap();
		if (bean != null) {
			BeanMap beanMap = BeanMap.create(bean);
			for (Object key : beanMap.keySet()) {
				map.put(key+"", beanMap.get(key));
			}
		}
		return map;
	}

	/**
	 * 将map装换为javabean对象
	 * @param map
	 * @param bean
	 * @return
	 */
	public static <T> T mapToBean(Map<String, Object> map,T bean) {
		BeanMap beanMap = BeanMap.create(bean);
		beanMap.putAll(map);
		return bean;
	}

	/**
	 * 将List<T>转换为List<Map<String, Object>>
	 * @param objList
	 */
	public static <T> List<Map<String, Object>> objectsToMaps(List<T> objList) {
		List<Map<String, Object>> list = Lists.newArrayList();
		if (objList != null && objList.size() > 0) {
			Map<String, Object> map = null;
			T bean = null;
			for (int i = 0,size = objList.size(); i < size; i++) {
				bean = objList.get(i);
				map = beanToMap(bean);
				list.add(map);
			}
		}
		return list;
	}

	/**
	 * 将List<Map<String,Object>>转换为List<T>
	 * @param maps
	 * @param clazz
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static <T> List<T> mapsToObjects(List<Map<String, Object>> maps,Class<T> clazz) throws InstantiationException, IllegalAccessException {
		List<T> list = Lists.newArrayList();
		if (maps != null && maps.size() > 0) {
			Map<String, Object> map = null;
			T bean = null;
			for (int i = 0,size = maps.size(); i < size; i++) {
				map = maps.get(i);
				bean = clazz.newInstance();
				mapToBean(map, bean);
				list.add(bean);
			}
		}
		return list;
	}


	public static void main(String[] args) throws Exception {
		User user =new User();
		user.setAge("234");
		user.setName("第三方你");
		user.setOld("放地上发快递");
		Map<String,Object>  res = beanToMap(user);
		for(String key:res.keySet()){
			System.out.println("key:"+key+":"+"| value:"+res.get(key));
			res.put(key,res.get(key)+"test");
		}
		user = (User)mapToBean(res,new User());
		System.out.println(user.getAge()+"|"+user.getName()+"|"+user.getOld());
	}
}

@Setter
@Getter
class User{
	private String name;
	private String old;
	private String age;
}
