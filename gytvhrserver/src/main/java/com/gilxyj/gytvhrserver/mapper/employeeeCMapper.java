package com.gilxyj.gytvhrserver.mapper;

import com.gilxyj.gytvhrserver.bean.employeeeC;

public interface employeeeCMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(employeeeC record);

    int insertSelective(employeeeC record);

    employeeeC selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(employeeeC record);

    int updateByPrimaryKey(employeeeC record);
}