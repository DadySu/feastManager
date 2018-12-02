package com.up.feast.mapper;

import com.up.feast.model.GGood;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * GGoodDAO继承基类
 */
@Mapper
@Component
public interface GGoodDAO extends MyBatisBaseDao<GGood, Long> {

}