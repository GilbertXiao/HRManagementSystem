package com.gilxyj.gytvhrserver.controller.system.basic;

import com.gilxyj.gytvhrserver.bean.JobLevel;
import com.gilxyj.gytvhrserver.bean.RespBean;
import com.gilxyj.gytvhrserver.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: gytvhrserver
 * @description:
 * @author: GilbertXiao
 * @create: 2019-09-15 15:26
 **/
@RequestMapping("/system/basic/joblevel")
@RestController
public class JobLevelController {

    @Autowired
    JobLevelService jobLevelService;

    @GetMapping("/")
    public List<JobLevel> getAllJobLevel(){

        return jobLevelService.getAllJobLevel();
    }

    @PostMapping("/")
    public RespBean addJobLevel(@RequestBody JobLevel jobLevel){
        Integer result= jobLevelService.addJobLevel(jobLevel);
        if (result==1){
            return RespBean.ok("新增成功");
        }else {
            return RespBean.error("新增失败");
        }
    }

    @PutMapping("/")
    public RespBean updateJobLevel(@RequestBody JobLevel jobLevel){
        Integer result= jobLevelService.updateJobLevel(jobLevel);
        if (result==1){
            return RespBean.ok("更新成功");
        }else {
            return RespBean.error("更新失败");
        }
    }

    @DeleteMapping("/{id}")
    public RespBean deleteJobLevel(@PathVariable Long id){
        Integer result= jobLevelService.deleteJobLevelById(id);
        if (result==1){
            return RespBean.ok("删除成功");
        }else {
            return RespBean.error("删除失败");
        }
    }

    @DeleteMapping("/")
    public RespBean deleteJobLevel(Long[] ids){
        Integer result= jobLevelService.deleteJobLevelByIds(ids);
        if (result==ids.length){
            return RespBean.ok("删除成功");
        }else {
            return RespBean.error("删除失败");
        }
    }


}
