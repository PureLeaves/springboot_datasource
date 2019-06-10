package com.example.demo.mysql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author FCX
 * @date Create in 19:17 2019/5/8
 * @description vip dao层
 */
@Mapper
public interface VipMapper {

    /**
     * 查询所有的姓名
     * @return
     */
    @Select("SELECT name from ap_vip")
    List<String> getVipNameAll();
}
