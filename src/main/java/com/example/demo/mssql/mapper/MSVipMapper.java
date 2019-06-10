package com.example.demo.mssql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author FCX
 * @date Create in 20:14 2019/5/8
 * @description vip dao层
 */
@Mapper
public interface MSVipMapper {

    /**
     * 查询所有的姓名
     * @return
     */
    @Select("SELECT Name FROM AP_VIP")
    List<String> getMSVipNameAll();
}
