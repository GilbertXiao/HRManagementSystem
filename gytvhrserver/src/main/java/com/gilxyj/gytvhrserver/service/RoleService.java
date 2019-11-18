package com.gilxyj.gytvhrserver.service;

import com.gilxyj.gytvhrserver.bean.Role;
import com.gilxyj.gytvhrserver.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: gytvhrserver
 * @description:
 * @author: GilbertXiao
 * @create: 2019-10-24 22:34
 **/
@Service
public class RoleService {

    @Autowired
    RoleMapper roleMapper;


    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }


    public Integer addRole(Role role) {
        return roleMapper.insertSelective(role);
    }

    public int deleteByRid(Integer rid) {
        return roleMapper.deleteByPrimaryKey(rid);
    }
}
