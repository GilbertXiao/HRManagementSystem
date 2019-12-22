package com.gilxyj.gytvhrserver.mapper;
import java.util.List;

import com.gilxyj.gytvhrserver.bean.CodeConfig;
import org.apache.ibatis.annotations.Param;

public interface CodeConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CodeConfig record);

    int insertSelective(CodeConfig record);

    CodeConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CodeConfig record);

    int updateByPrimaryKey(CodeConfig record);

    CodeConfig getByConfigKey(@Param("configKey")String configKey);







}
