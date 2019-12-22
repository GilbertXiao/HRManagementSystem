package com.gilxyj.gytvhrserver.bean;

import java.time.LocalDate;

public class OpLog {
    private Integer id;

    private LocalDate adddate;

    private String operate;

    private Integer hrid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getAdddate() {
        return adddate;
    }

    public void setAdddate(LocalDate adddate) {
        this.adddate = adddate;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate == null ? null : operate.trim();
    }

    public Integer getHrid() {
        return hrid;
    }

    public void setHrid(Integer hrid) {
        this.hrid = hrid;
    }
}
