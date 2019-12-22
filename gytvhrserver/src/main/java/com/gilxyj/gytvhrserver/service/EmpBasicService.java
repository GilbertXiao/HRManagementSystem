package com.gilxyj.gytvhrserver.service;

import com.gilxyj.gytvhrserver.bean.Employee;
import com.gilxyj.gytvhrserver.bean.RespPageBean;
import com.gilxyj.gytvhrserver.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;

/**
 * @program: gytvhrserver
 * @description:
 * @author: GilbertXiao
 * @create: 2019-11-23 20:12
 **/
@Service
public class EmpBasicService {

    @Autowired
    EmployeeMapper employeeMapper;

    public RespPageBean getEmpByPage(Integer page, Integer size, Employee employee, LocalDate[] dateRange) {

        page = (page - 1) * size;
        List<Employee> employeeList = employeeMapper.getEmpByPage(page, size,employee, dateRange);
        Long total = employeeMapper.getTotal(employee, dateRange);
        RespPageBean respPageBean = new RespPageBean();
        respPageBean.setData(employeeList);
        respPageBean.setTotal(total);
        return respPageBean;
    }


}
