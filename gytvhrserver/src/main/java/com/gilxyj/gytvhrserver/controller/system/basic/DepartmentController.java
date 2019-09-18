package com.gilxyj.gytvhrserver.controller.system.basic;

import com.gilxyj.gytvhrserver.bean.Department;
import com.gilxyj.gytvhrserver.bean.RespBean;
import com.gilxyj.gytvhrserver.service.DepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: gytvhrserver
 * @description:
 * @author: GilbertXiao
 * @create: 2019-09-10 23:52
 **/
@RestController
@RequestMapping("/system/basic/dep")
public class DepartmentController {

    @Autowired
    DepService depService;

    @GetMapping("/")
    public List<Department> getAllTreeDepartment(){
       return depService.getAllTreeDepartment();
    }

    @GetMapping("/allDeps")
    public List<Department> getAllDepartment(){
        return depService.getAllDepartment();
    }

    @DeleteMapping("/")
    public RespBean deleteDepartmentById(Department department){

        depService.deleteDepartmentById(department);
        if(department.getResult()==1){
            return RespBean.ok("部门删除成功");
        }else if(department.getResult()==-2){
            return RespBean.error("该部门下还有子部门，删除失败");
        }else if(department.getResult()==-1){
            return RespBean.error("该部门下还有员工，删除失败");
        }

        return RespBean.error("删除失败");
    }

    @PostMapping("/")
    public RespBean addDepartment(@RequestBody Department department){
        depService.addDepartment(department);
        if(department.getResult()==1){
            return RespBean.ok("部门添加成功！",department);
        }
        return RespBean.error("添加失败");
    }

}
