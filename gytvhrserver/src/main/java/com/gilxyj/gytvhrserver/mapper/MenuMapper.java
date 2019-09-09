package com.gilxyj.gytvhrserver.mapper;



import com.gilxyj.gytvhrserver.bean.Menu;

import java.util.List;


public interface MenuMapper {
    List<Menu> getAllMenu();

    List<Menu> getMenusByHrId(Long hrId);

    List<Menu> menuTree();

    List<Long> getMenusByRid(Long rid);
}
