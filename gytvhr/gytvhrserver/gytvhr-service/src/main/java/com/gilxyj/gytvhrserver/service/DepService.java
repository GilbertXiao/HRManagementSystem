package com.gilxyj.gytvhrserver.service;

import com.gilxyj.gytvhrserver.bean.Department;
import com.gilxyj.gytvhrserver.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: gytvhrserver
 * @description:
 * @author: GilbertXiao
 * @create: 2019-09-10 23:56
 **/
@Service
public class DepService {

    @Autowired
    DepartmentMapper departmentMapper;
    public List<Department> getAllTreeDepartment() {
        return departmentMapper.getAllTreeDepartment(-1);
    }

    public void deleteDepartmentById(Department department) {
        departmentMapper.deleteDepartmentById(department);
    }

    public void addDepartment(Department department) {
        departmentMapper.addDepartment(department);
    }

    public List<Department> getAllDepartment() {
        return departmentMapper.getAllDepartment();
    }
}
