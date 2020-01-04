package com.gilxyj.gytvhrserver.mapper;

import com.gilxyj.gytvhrserver.bean.HrRole;
import org.apache.ibatis.annotations.Param;

public interface HrRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HrRole record);

    int insertSelective(HrRole record);

    HrRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HrRole record);

    int updateByPrimaryKey(HrRole record);

    int deleteByHrid(@Param("hrid") Integer hrid);


    int insertByHrIdAndRoleId(@Param("hrId") Integer hrId, @Param("roleId") Integer[] roleId);
}
