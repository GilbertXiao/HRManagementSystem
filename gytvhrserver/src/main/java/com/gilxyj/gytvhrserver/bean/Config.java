package com.gilxyj.gytvhrserver.bean;

public class Config {
    private String configKey;

    private Integer configValue;

    private String remark;

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public Integer getConfigValue() {
        return configValue;
    }

    public void setConfigValue(Integer configValue) {
        this.configValue = configValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}