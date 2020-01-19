package com.gilxyj.gytvhrserver.controller.sal;

import com.gilxyj.gytvhrserver.bean.RespBean;
import com.gilxyj.gytvhrserver.bean.RespPageBean;
import com.gilxyj.gytvhrserver.bean.Salary;
import com.gilxyj.gytvhrserver.service.EmpBasicService;
import com.gilxyj.gytvhrserver.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: gytvhr
 * @description: sobconfig
 * @author: GilbertXiao
 * @create: 2020-01-16 22:12
 **/
@RestController
@RequestMapping("/salary/sobcfg")
public class SobConfigController {

    @Autowired
    EmpBasicService empBasicService;

    @Autowired
    SalaryService salaryService;

    @GetMapping("/")
    public RespPageBean getAllEmployee(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size){
        RespPageBean empByPage = empBasicService.getEmpByPage(page, size, null, null);
        return empByPage;

    }


    @GetMapping("/salaries")
    public List<Salary> getAllSalaries() {
        return salaryService.getAllSalaries();
    }

    @PutMapping("/")
    public RespBean updateEmployeeSalaryById(Integer eid, Integer sid) {
        Integer result = empBasicService.updateEmployeeSalaryById(eid, sid);
        if (result == 1 || result == 2) {
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }


}
