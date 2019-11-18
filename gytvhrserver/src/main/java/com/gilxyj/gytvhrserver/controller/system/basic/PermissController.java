package com.gilxyj.gytvhrserver.controller.system.basic;

import com.gilxyj.gytvhrserver.bean.Menu;
import com.gilxyj.gytvhrserver.bean.RespBean;
import com.gilxyj.gytvhrserver.bean.Role;
import com.gilxyj.gytvhrserver.service.MenuRoleService;
import com.gilxyj.gytvhrserver.service.MenuService;
import com.gilxyj.gytvhrserver.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: gytvhrserver
 * @description:
 * @author: GilbertXiao
 * @create: 2019-10-24 22:32
 **/
@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {

    @Autowired
    RoleService roleService;

    @Autowired
    MenuRoleService menuRoleService;

    @Autowired
    MenuService menuService;

    @GetMapping("/")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

    @GetMapping("/menu")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenuTree();
    }

    @GetMapping("/selectedMenu/{rid}")
    public List<Integer> getMenuIdByRoleId(@PathVariable("rid") Long rid){
        return menuService.getMenuIdByRoleId(rid);
    }

    @PostMapping("/role")
    public RespBean addRole(@RequestBody Role role){

        if(roleService.addRole(role)==1){
            return RespBean.ok("新增角色成功");
        }else {
            return RespBean.error("新增角色失败");
        }

    }

    @PutMapping("/roleMenu")
    public RespBean updateMenuByRoleId(Integer rid,Integer[] mid){
        if (mid == null) {
            mid=new Integer[]{};
        }
        if (mid.length == menuRoleService.updateMenuByRoleId(rid, mid)) {
            return RespBean.ok("修改成功");

        }
        return RespBean.error("修改失败");
    }

    @DeleteMapping("/role/{rid}")
    public RespBean deleteRoleByRid(@PathVariable("rid") Integer rid){
        if(roleService.deleteByRid(rid)==1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
