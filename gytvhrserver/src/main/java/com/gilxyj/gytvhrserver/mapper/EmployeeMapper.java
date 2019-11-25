package com.gilxyj.gytvhrserver.mapper;

import com.gilxyj.gytvhrserver.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    List<Employee> getEmpByPage(@Param("page") Integer page, @Param("size") Integer size);

    Long getTotal();

}
