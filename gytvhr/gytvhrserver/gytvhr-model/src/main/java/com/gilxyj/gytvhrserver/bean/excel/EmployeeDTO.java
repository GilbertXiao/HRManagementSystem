package com.gilxyj.gytvhrserver.bean.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.gilxyj.gytvhrserver.bean.*;

import java.time.LocalDate;

/**
 * @program: gytvhrserver
 * @description: FOR Excel import
 * @author: GilbertXiao
 * @create: 2019-12-28 23:59
 **/

public class EmployeeDTO  {

    @Excel(name = "姓名")
    private String name;
    @Excel(name = "工号")
    private String workID;
    @Excel(name = "性别")
    private String gender;
    @Excel(name = "出生日期",format = "yyyy-MM-dd")
    private LocalDate birthday;
    @Excel(name = "身份证号码")
    private String idCard;
    @Excel(name = "婚姻状况")
    private String wedlock;

    private Long nationId;
    @Excel(name = "民族")
    private String nationName;

    @Excel(name = "籍贯")
    private String nativePlace;


    private Long politicId;
    @Excel(name = "政治面貌")
    private String politicsStatusName;

    @Excel(name = "电话号码")
    private String phone;
    @Excel(name = "联系地址")
    private String address;

    private Long departmentId;
    @Excel(name = "所属部门")
    private String departmentName;


    private Long jobLevelId;
    @Excel(name = "职称")
    private String jobLevelName;

    private Long posId;
    @Excel(name = "职位")
    private String positionName;

    @Excel(name = "聘用形式")
    private String engageForm;
    @Excel(name = "最高学历")
    private String tiptopDegree;
    @Excel(name = "专业")
    private String specialty;
    @Excel(name = "毕业院校")
    private String school;
    @Excel(name = "入职日期",format = "yyyy-MM-dd")
    private LocalDate beginDate;
    @Excel(name = "在职状态")
    private String workState;
    @Excel(name = "邮箱")
    private String email;
    @Excel(name = "合同期限(年)")
    private Double contractTerm;
    @Excel(name = "合同起始日期",format = "yyyy-MM-dd")
    private LocalDate beginContract;
    @Excel(name = "合同终止日期",format = "yyyy-MM-dd")
    private LocalDate endContract;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkID() {
        return workID;
    }

    public void setWorkID(String workID) {
        this.workID = workID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getWedlock() {
        return wedlock;
    }

    public void setWedlock(String wedlock) {
        this.wedlock = wedlock;
    }

    public Long getNationId() {
        return nationId;
    }

    public void setNationId(Long nationId) {
        this.nationId = nationId;
    }

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public Long getPoliticId() {
        return politicId;
    }

    public void setPoliticId(Long politicId) {
        this.politicId = politicId;
    }

    public String getPoliticsStatusName() {
        return politicsStatusName;
    }

    public void setPoliticsStatusName(String politicsStatusName) {
        this.politicsStatusName = politicsStatusName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getJobLevelId() {
        return jobLevelId;
    }

    public void setJobLevelId(Long jobLevelId) {
        this.jobLevelId = jobLevelId;
    }

    public String getJobLevelName() {
        return jobLevelName;
    }

    public void setJobLevelName(String jobLevelName) {
        this.jobLevelName = jobLevelName;
    }

    public Long getPosId() {
        return posId;
    }

    public void setPosId(Long posId) {
        this.posId = posId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getEngageForm() {
        return engageForm;
    }

    public void setEngageForm(String engageForm) {
        this.engageForm = engageForm;
    }

    public String getTiptopDegree() {
        return tiptopDegree;
    }

    public void setTiptopDegree(String tiptopDegree) {
        this.tiptopDegree = tiptopDegree;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public String getWorkState() {
        return workState;
    }

    public void setWorkState(String workState) {
        this.workState = workState;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getContractTerm() {
        return contractTerm;
    }

    public void setContractTerm(Double contractTerm) {
        this.contractTerm = contractTerm;
    }

    public LocalDate getBeginContract() {
        return beginContract;
    }

    public void setBeginContract(LocalDate beginContract) {
        this.beginContract = beginContract;
    }

    public LocalDate getEndContract() {
        return endContract;
    }

    public void setEndContract(LocalDate endContract) {
        this.endContract = endContract;
    }
}
