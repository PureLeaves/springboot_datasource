package com.example.demo.mssql.service;

import com.example.demo.mssql.mapper.MSVipMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author FCX
 * @date Create in 20:16 2019/5/8
 * @description Vip业务层
 */
@Service
public class MSVipService {
    @Autowired
    private MSVipMapper msVipMapper;

    /**
     * 分页查询Vip名列表
     * @return
     */
    public List<String> findMSVipNameAll(){
        PageInfo<String> pageInfo = PageHelper.startPage(1, 4,"id desc").doSelectPageInfo(()
                -> msVipMapper.getMSVipNameAll());
        return pageInfo.getList();
    }
}
