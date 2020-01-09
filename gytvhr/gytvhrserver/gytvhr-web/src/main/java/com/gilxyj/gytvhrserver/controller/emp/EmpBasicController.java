package com.gilxyj.gytvhrserver.controller.emp;

import com.gilxyj.gytvhrserver.bean.*;
import com.gilxyj.gytvhrserver.bean.excel.EmployeeDTO;
import com.gilxyj.gytvhrserver.common.FileUtil;
import com.gilxyj.gytvhrserver.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: gytvhrserver
 * @description:
 * @author: GilbertXiao
 * @create: 2019-11-23 19:41
 **/
@RestController
@RequestMapping("/employee/basic")
public class EmpBasicController {

    @Autowired
    EmpBasicService empBasicService;

    @Autowired
    NationService nationService;

    @Autowired
    PoliticsStatusService politicsStatusService;

    @Autowired
    JobLevelService jobLevelService;

    @Autowired
    PositionService positionService;

    @Autowired
    DepService depService;

    @Autowired
    CodeConfigService codeConfigService;

    @GetMapping("/")
    public RespPageBean getEmpByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, Employee employee, @RequestParam(required=false) LocalDate[] dateRange) {
        return empBasicService.getEmpByPage(page, size, employee,dateRange);
    }

    @GetMapping("/nation")
    public List<Nation> getAllNations(){
        return nationService.getAllNations();
    }

    @GetMapping("/politicsStatus")
    public List<PoliticsStatus> getAllPoliticsStatus(){
        return politicsStatusService.getAllPoliticsStatus();
    }

    @GetMapping("/jobLevel")
    public List<JobLevel> getAllJobLevel(){
        return jobLevelService.getAllJobLevel();
    }

    @GetMapping("/position")
    public List<Position> getAllPositions(){
        return positionService.getAllPositions();
    }

    @GetMapping("/department")
    public List<Department> getAllDepartment(){
        return depService.getAllTreeDepartment();
    }

    @GetMapping("/maxWorkId")
    public String getMaxWorkId(){
        return codeConfigService.getMaxWorkId();
    }


    @PostMapping("/emp")
    public RespBean addEmp(@RequestBody Employee employee){
        Integer integer = empBasicService.addEmp(employee);
        if(integer==1){
            return RespBean.ok("新增员工成功！");
        }else {
            return RespBean.error("新增员工失败！");
        }
    }

    @PutMapping("/emp")
    public RespBean updateEmp(@RequestBody Employee employee){
        Integer integer = empBasicService.updateEmp(employee);
        if(integer==1){
            return RespBean.ok("编辑员工成功！");
        }else {
            return RespBean.error("编辑员工失败！");
        }
    }

    @DeleteMapping("/emp/{id}")
    public RespBean deleteEmp(@PathVariable Integer id){
        Integer integer = empBasicService.deleteEmp(id);
        if(integer==1){
            return RespBean.ok("删除员工成功！");
        }else {
            return RespBean.error("删除员工失败！");
        }
    }

    @DeleteMapping("/emp/")
    public RespBean deleteEmp(Integer[] ids){
        Integer integer = empBasicService.deleteEmpByIds(ids);
        if(integer==ids.length){
            return RespBean.ok("删除员工成功！");
        }else {
            return RespBean.error("删除员工失败！");
        }
    }


    /*@GetMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response){
        RespPageBean empByPage = empBasicService.getEmpByPage(null, null, null, null);
        List<Employee> data = ( List<Employee>)empByPage.getData();
        Map dataMap = new HashMap<String, Object>();
        dataMap.put("empList", data);
        String[] sheetName = {"员工信息","员工Test"};
        FileUtil.exportExcelFromTemplate("employee.xlsx", sheetName, dataMap, "员工表.xlsx", response);

    }*/


    @GetMapping("/exportExcel")
    public ResponseEntity<byte[]> exportExcel(){
        RespPageBean empByPage = empBasicService.getEmpByPage(null, null, null, null);
        List<Employee> data = ( List<Employee>)empByPage.getData();
        Map dataMap = new HashMap<String, Object>();
        dataMap.put("empList", data);
        /*dataMap.put("empList1", data);*/
        String[] sheetName = {"员工信息"};
        ResponseEntity responseEntity = FileUtil.exportExcelFromTemplate2("employee.xlsx", sheetName, dataMap, "员工表.xlsx");
        return responseEntity;

    }

    @PostMapping("/importEmp")
    public RespBean importEmp(MultipartFile file) {
        List<EmployeeDTO> employeeDTOS = FileUtil.importExcel(file, 0, 1, EmployeeDTO.class);
        List<Employee> employeeFromDTO = empBasicService.getEmployeeFromDTO(employeeDTOS, nationService.getAllNations(), politicsStatusService.getAllPoliticsStatus(), depService.getAllDepartment(), positionService.getAllPositions(), jobLevelService.getAllJobLevel());
        if (empBasicService.addEmps(employeeFromDTO) == employeeDTOS.size()) {
            return RespBean.ok("导入成功!");
        }
        return RespBean.error("导入失败!");
    }





}
