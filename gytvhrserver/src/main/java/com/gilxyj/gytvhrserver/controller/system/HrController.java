package com.gilxyj.gytvhrserver.controller.system;

import com.gilxyj.gytvhrserver.bean.Hr;
import com.gilxyj.gytvhrserver.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: gytvhrserver
 * @description:
 * @author: GilbertXiao
 * @create: 2019-11-18 23:59
 **/
@RestController
@RequestMapping("/system/hr")
public class HrController {

    @Autowired
    HrService hrService;

    @GetMapping("/")
    public List<Hr> getAllHrsExcudeLogin(){
        return hrService.getAllHrExceptAdmin();
    }
}
