package com.example.demo.mssql.rest;

import com.example.demo.mssql.service.MSVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author FCX
 * @date Create in 20:13 2019/5/8
 * @description vip控制层
 */
@Controller
public class MSVipController {
    @Autowired
    private MSVipService msVipService;

    /**
     * 查询vip名
     * @return
     */
    @GetMapping("/msvip/name")
    @ResponseBody
    public Object findMSVipName() {
        List<String> list = msVipService.findMSVipNameAll();
        return list;
    }
}
