package com.gilxyj.gytvhrserver.service;

import com.gilxyj.gytvhrserver.bean.Salary;
import com.gilxyj.gytvhrserver.mapper.SalaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @program: gytvhr
 * @description: salary
 * @author: GilbertXiao
 * @create: 2020-01-14 21:42
 **/
@Service
public class SalaryService {


    @Autowired
    SalaryMapper salaryMapper;

    public List<Salary> getAllSalaries() {
        return salaryMapper.getAllSalaries();
    }

    public Integer deleteSalaryById(Integer id) {
        return salaryMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteSalaryByIds(Integer[] ids) {
        return salaryMapper.deleteSalaryByIds(ids);
    }

    public Integer addSalary(Salary salary) {
        salary.setCreateDate(LocalDate.now());
        return salaryMapper.insertSelective(salary);
    }


    public Integer updateSalary(Salary salary) {
        return salaryMapper.updateByPrimaryKeySelective(salary);
    }
}
