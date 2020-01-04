package com.gilxyj.gytvhrserver.mapper;

import com.gilxyj.gytvhrserver.bean.Department;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> getAllTreeDepartment(Integer pid);

    void deleteDepartmentById(Department department);

    void addDepartment(Department department);

    List<Department> getAllDepartment();







}
