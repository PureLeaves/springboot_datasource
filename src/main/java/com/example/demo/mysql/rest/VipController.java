package com.example.demo.mysql.rest;

import com.example.demo.mysql.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author FCX
 * @date Create in 19:34 2019/4/28
 * @description vip控制层
 */
@RestController
public class VipController {
    @Autowired
    private VipService vipService;

    /**
     * 查询vip名
     * @return
     */
    @GetMapping("/vip/name")
    public Object hello() {
        List<String> list = vipService.findVipNameAll();
        return list;
    }
}

