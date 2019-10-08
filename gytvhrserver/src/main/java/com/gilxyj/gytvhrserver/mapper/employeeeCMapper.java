package com.gilxyj.gytvhrserver.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.Date;

import com.gilxyj.gytvhrserver.bean.employeeeC;

import java.util.List;

public interface employeeeCMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(employeeeC record);

    int insertSelective(employeeeC record);

    employeeeC selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(employeeeC record);

    int updateByPrimaryKey(employeeeC record);





}
