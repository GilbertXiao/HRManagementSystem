package com.gilxyj.gytvhrserver.controller.emp;

import com.gilxyj.gytvhrserver.bean.RespPageBean;
import com.gilxyj.gytvhrserver.service.EmpBasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: gytvhrserver
 * @description:
 * @author: GilbertXiao
 * @create: 2019-11-23 19:41
 **/
@RestController
@RequestMapping("/employee/basic")
public class EmpBasicController  {

    @Autowired
    EmpBasicService empBasicService;

    @GetMapping("/")
    public RespPageBean getEmpByPage(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer size ){
        return empBasicService.getEmpByPage(page,size);
    }
}
