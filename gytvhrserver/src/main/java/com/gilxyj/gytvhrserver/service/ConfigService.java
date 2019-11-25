package com.gilxyj.gytvhrserver.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.gilxyj.gytvhrserver.bean.Config;
import com.gilxyj.gytvhrserver.mapper.ConfigMapper;
@Service
public class ConfigService{

    @Resource
    private ConfigMapper configMapper;

    public int deleteByPrimaryKey(String configKey) {
        return configMapper.deleteByPrimaryKey(configKey);
    }

    public int insert(Config record) {
        return configMapper.insert(record);
    }

    public int insertSelective(Config record) {
        return configMapper.insertSelective(record);
    }

    public Config selectByPrimaryKey(String configKey) {
        return configMapper.selectByPrimaryKey(configKey);
    }

    public int updateByPrimaryKeySelective(Config record) {
        return configMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Config record) {
        return configMapper.updateByPrimaryKey(record);
    }

}
