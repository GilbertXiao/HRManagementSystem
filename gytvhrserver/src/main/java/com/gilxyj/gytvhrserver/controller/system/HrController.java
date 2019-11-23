package com.gilxyj.gytvhrserver.controller.system;

import com.gilxyj.gytvhrserver.bean.Hr;
import com.gilxyj.gytvhrserver.bean.RespBean;
import com.gilxyj.gytvhrserver.bean.Role;
import com.gilxyj.gytvhrserver.service.HrService;
import com.gilxyj.gytvhrserver.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    RoleService roleService;



    @GetMapping("/")
    public List<Hr> getAllHrsExcudeLogin(){
        return hrService.getAllHrExceptAdmin();
    }

    @GetMapping("/role")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

    @PutMapping("/role")
    public RespBean updateHrRoles(Integer hrId,Integer[] roleId){
        int length=roleId==null?0:roleId.length;
        if(hrService.updateHrRoles(hrId, roleId)==length){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @PutMapping("/")
    public RespBean updateHr(@RequestBody Hr hr){
        if (hrService.updateHr(hr)==1) {
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }
}
