package com.gilxyj.gytvhrserver.mapper;

import com.gilxyj.gytvhrserver.bean.Config;

public interface ConfigMapper {
    int deleteByPrimaryKey(String configKey);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(String configKey);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);
}