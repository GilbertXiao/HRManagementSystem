package com.gilxyj.gytvhrserver.bean;

import java.util.List;

/**
 * @program: gytvhrserver
 * @description: Page Bean
 * @author: GilbertXiao
 * @create: 2019-11-23 19:58
 **/
public class RespPageBean {

    private Long total;
    private List<?> data;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
