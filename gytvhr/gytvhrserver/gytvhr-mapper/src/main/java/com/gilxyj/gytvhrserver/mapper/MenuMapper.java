package com.gilxyj.gytvhrserver.mapper;



import com.gilxyj.gytvhrserver.bean.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface MenuMapper {
    List<Menu> getAllMenu();

    List<Menu> getMenusByHrId(Long hrId);

    List<Long> getMenusByRid(Long rid);

    List<Menu> getAllMenuTree(@Param("id") Long id);

    List<Integer> getMenuIdByRoleId(Long rid);

}
