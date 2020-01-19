package com.gilxyj.gytvhrserver.controller.sal;

import com.gilxyj.gytvhrserver.bean.RespBean;
import com.gilxyj.gytvhrserver.bean.Salary;
import com.gilxyj.gytvhrserver.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: gytvhr
 * @description: salary
 * @author: GilbertXiao
 * @create: 2020-01-14 21:40
 **/
@RestController
@RequestMapping("/salary/sob")
public class SalaryController {

    @Autowired
    SalaryService salaryService;

    @GetMapping("/")
    public List<Salary> getAllSalaries(){
        List<Salary> salaries=salaryService.getAllSalaries();
        return salaries;
    }

    @DeleteMapping("/{id}")
    public RespBean deleteSalaryById(@PathVariable Integer id){
        Integer result= salaryService.deleteSalaryById(id);
        if(result==1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @DeleteMapping("/")
    public RespBean deleteSalaryByIds(Integer[] ids){
        Integer result= salaryService.deleteSalaryByIds(ids);
        if(result==ids.length){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @PostMapping("/")
    public RespBean addSalary(@RequestBody Salary salary){
        Integer result= salaryService.addSalary(salary);
        if(result==1){
            return RespBean.ok("新增成功");
        }
        return RespBean.error("新增失败");
    }
    @PutMapping("/")
    public RespBean updateSalary(@RequestBody Salary salary){
        Integer result= salaryService.updateSalary(salary);
        if(result==1){
            return RespBean.ok("修改成功");
        }
        return RespBean.error("修改失败");
    }



}
