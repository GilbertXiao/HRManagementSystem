package com.gilxyj.gytvhrserver.service;


import com.gilxyj.gytvhrserver.bean.Menu;
import com.gilxyj.gytvhrserver.common.HrUtils;
import com.gilxyj.gytvhrserver.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Service
@Transactional
@CacheConfig(cacheNames = "LocalCache")
public class MenuService {
    @Autowired
    MenuMapper menuMapper;

    @Cacheable(key="#root.methodName")
    public List<Menu> getAllMenu(){
        return menuMapper.getAllMenu();
    }


    public List<Menu> getMenusByHrId(Long id) {
        return menuMapper.getMenusByHrId(id);
    }

    public List<Menu> getAllMenuTree() {
        return menuMapper.getAllMenuTree(1L);
    }

    public List<Integer> getMenuIdByRoleId(Long rid) {
        return menuMapper.getMenuIdByRoleId(rid);
    }
}
