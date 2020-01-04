package com.gilxyj.gytvhrserver.bean;

import java.time.LocalDate;

public class employeeeC {
    private Integer id;

    private Integer eid;

    private LocalDate ecdate;

    private String ecreason;

    private Integer ecpoint;

    private Integer ectype;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public LocalDate getEcdate() {
        return ecdate;
    }

    public void setEcdate(LocalDate ecdate) {
        this.ecdate = ecdate;
    }

    public String getEcreason() {
        return ecreason;
    }

    public void setEcreason(String ecreason) {
        this.ecreason = ecreason == null ? null : ecreason.trim();
    }

    public Integer getEcpoint() {
        return ecpoint;
    }

    public void setEcpoint(Integer ecpoint) {
        this.ecpoint = ecpoint;
    }

    public Integer getEctype() {
        return ectype;
    }

    public void setEctype(Integer ectype) {
        this.ectype = ectype;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
