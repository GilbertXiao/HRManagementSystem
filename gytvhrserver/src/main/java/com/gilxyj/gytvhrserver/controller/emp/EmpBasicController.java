package com.gilxyj.gytvhrserver.controller.emp;

import com.gilxyj.gytvhrserver.bean.*;
import com.gilxyj.gytvhrserver.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDate;
import java.util.List;

/**
 * @program: gytvhrserver
 * @description:
 * @author: GilbertXiao
 * @create: 2019-11-23 19:41
 **/
@RestController
@RequestMapping("/employee/basic")
public class EmpBasicController {

    @Autowired
    EmpBasicService empBasicService;

    @Autowired
    NationService nationService;

    @Autowired
    PoliticsStatusService politicsStatusService;

    @Autowired
    JobLevelService jobLevelService;

    @Autowired
    PositionService positionService;

    @Autowired
    DepService depService;

    @GetMapping("/")
    public RespPageBean getEmpByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, Employee employee, @RequestParam(required=false) LocalDate[] dateRange) {
        return empBasicService.getEmpByPage(page, size, employee,dateRange);
    }

    @GetMapping("/nation")
    public List<Nation> getAllNations(){
        return nationService.getAllNations();
    }

    @GetMapping("/politicsStatus")
    public List<PoliticsStatus> getAllPoliticsStatus(){
        return politicsStatusService.getAllPoliticsStatus();
    }

    @GetMapping("/jobLevel")
    public List<JobLevel> getAllJobLevel(){
        return jobLevelService.getAllJobLevel();
    }

    @GetMapping("/position")
    public List<Position> getAllPositions(){
        return positionService.getAllPositions();
    }

    @GetMapping("/department")
    public List<Department> getAllDepartment(){
        return depService.getAllTreeDepartment();
    }







}
