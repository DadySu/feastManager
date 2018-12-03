package com.up.feast.mapper;

import com.up.feast.model.GoodCar;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * GoodCarDAO继承基类
 *
 * @author liub
 * @date 2018/12/2 12:22
 */
@Mapper
@Component
public interface GoodCarDAO extends MyBatisBaseDao<GoodCar, Long> {


}