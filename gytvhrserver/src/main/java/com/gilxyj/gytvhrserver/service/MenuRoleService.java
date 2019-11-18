package com.gilxyj.gytvhrserver.service;

import com.gilxyj.gytvhrserver.mapper.MenuRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: gytvhrserver
 * @description:
 * @author: GilbertXiao
 * @create: 2019-11-17 22:58
 **/

@Service
public class MenuRoleService {


    @Autowired
    MenuRoleMapper menuRoleMapper;

    @Transactional(rollbackFor = Exception.class)
    public Integer updateMenuByRoleId(Integer rid, Integer[] mid){
        menuRoleMapper.deleteByRid(rid);
        if(mid==null||mid.length==0){
            return 0;
        }
        return menuRoleMapper.insertRidAndMids(rid,mid);
    }
}
