package com.gilxyj.gytvhrserver.service;

import com.gilxyj.gytvhrserver.bean.*;
import com.gilxyj.gytvhrserver.bean.excel.EmployeeDTO;
import com.gilxyj.gytvhrserver.mapper.EmployeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    RabbitTemplate rabbitTemplate;

    private static final Logger LOGGER= LoggerFactory.getLogger(EmpBasicService.class);

    public RespPageBean getEmpByPage(Integer page, Integer size, Employee employee, LocalDate[] dateRange) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Employee> employeeList = employeeMapper.getEmpByPage(page, size, employee, dateRange);
        Long total = employeeMapper.getTotal(employee, dateRange);
        RespPageBean respPageBean = new RespPageBean();
        respPageBean.setData(employeeList);
        respPageBean.setTotal(total);
        return respPageBean;
    }

    public Integer addEmp(Employee employee) {
        employee.setContractTerm(getContractTerm(employee.getBeginContract(), employee.getEndContract()));
        int result = employeeMapper.insertSelective(employee);
        if (result == 1) {
            Employee emp = employeeMapper.getEmpById(employee.getId());
            LOGGER.info(emp.toString());
            rabbitTemplate.convertAndSend("gytvhr.mail.welcome", emp);

        }
        return result;
    }


    public Integer updateEmp(Employee employee) {
        return employeeMapper.updateByPrimaryKeySelective(employee);
    }

    public Integer deleteEmp(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteEmpByIds(Integer[] ids) {
        return employeeMapper.deleteByIds(Arrays.asList(ids));
    }

    public int addEmps(List<Employee> employeeList) {
        return employeeMapper.insertList(employeeList);
    }

    public List<Employee> getEmployeeFromDTO(List<EmployeeDTO> employeeDTOS, List<Nation> allNations, List<PoliticsStatus> allPoliticsStatus, List<Department> allDepartment, List<Position> allPositions, List<JobLevel> allJobLevel) {
        Map<String, Long> nationNameIdMap = allNations.stream().collect(Collectors.toMap(Nation::getName, Nation::getId));
        Map<String, Long> politicsStatusNameIdMap = allPoliticsStatus.stream().collect(Collectors.toMap(PoliticsStatus::getName, PoliticsStatus::getId));
        Map<String, Long> departmentNameIdMap = allDepartment.stream().collect(Collectors.toMap(Department::getName, Department::getId));
        Map<String, Long> positionNameIdMap = allPositions.stream().collect(Collectors.toMap(Position::getName, Position::getId));
        Map<String, Long> JobLevelNameIdMap = allJobLevel.stream().collect(Collectors.toMap(JobLevel::getName, JobLevel::getId));
        List<Employee> employees = new ArrayList<>();
        for (EmployeeDTO employeeDTO : employeeDTOS) {
            Employee employee = new Employee();
            employeeDTO.setNationId(nationNameIdMap.get(employeeDTO.getNationName()));
            employeeDTO.setPoliticId(politicsStatusNameIdMap.get(employeeDTO.getPoliticsStatusName()));
            employeeDTO.setDepartmentId(departmentNameIdMap.get(employeeDTO.getDepartmentName()));
            employeeDTO.setPosId(positionNameIdMap.get(employeeDTO.getPositionName()));
            employeeDTO.setJobLevelId(JobLevelNameIdMap.get(employeeDTO.getJobLevelName()));
            employeeDTO.setContractTerm(getContractTerm(employeeDTO.getBeginContract(), employeeDTO.getEndContract()));
            BeanUtils.copyProperties(employeeDTO, employee);
            employees.add(employee);
        }
        return employees;

    }

    public static Double getContractTerm(LocalDate beginContract, LocalDate endContract) {


        BigDecimal days = new BigDecimal((endContract.toEpochDay() - beginContract.toEpochDay()));
        BigDecimal term = days.divide(new BigDecimal(365), 2, BigDecimal.ROUND_HALF_UP);

        return term.doubleValue();

    }

    public Integer updateEmployeeSalaryById(Integer eid, Integer sid) {
        return employeeMapper.updateEmployeeSalaryById(eid, sid);
    }
}
