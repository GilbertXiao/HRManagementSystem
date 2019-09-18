package com.gilxyj.gytvhrserver.controller.system.basic;

import com.gilxyj.gytvhrserver.bean.JobLevel;
import com.gilxyj.gytvhrserver.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: gytvhrserver
 * @description:
 * @author: GilbertXiao
 * @create: 2019-09-15 15:26
 **/
@RestController
@RequestMapping("/system/basic/joblevel")
public class JobLevelController {

    @Autowired
    JobLevelService jobLevelService;

    @GetMapping("/")
    public List<JobLevel> getAllJobLevel(){

        return jobLevelService.getAllJobLevel();
    }
}
