package com.gilxyj.gytvhrserver.mapper;

import com.gilxyj.gytvhrserver.bean.Employee;
import org.apache.ibatis.annotations.Param;


import java.time.LocalDate;
import java.util.List;

public interface EmployeeMapper<insertList> {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    List<Employee> getEmpByPage(@Param("page") Integer page, @Param("size") Integer size, @Param("employee") Employee employee, @Param("dateRange") LocalDate[] dateRange);

    Long getTotal(@Param("employee") Employee employee, @Param("dateRange") LocalDate[] dateRange);

    String getMaxWorkIdByMaxId();

    Integer deleteByIds(@Param("ids") List<Integer> ids);

    Integer insertList(@Param("emps") List<Employee> employees);


    Employee getEmpById(@Param("id") Long id);

    Integer updateEmployeeSalaryById(@Param("eid") Integer eid, @Param("sid") Integer sid);

}
