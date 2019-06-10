package com.example.demo.mysql.service;

import com.example.demo.mysql.mapper.VipMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author FCX
 * @date Create in 18:00 2019/5/8
 * @description Vip业务层
 */
@Service
public class VipService {
    @Autowired
    private VipMapper vipMapper;

    /**
     * 分页查询Vip名列表
     * @return
     */
    public List<String> findVipNameAll() {
        PageInfo<String> pageInfo = PageHelper.startPage(1, 3,"id asc").doSelectPageInfo(()
                -> vipMapper.getVipNameAll());
        return pageInfo.getList();
    }
}
