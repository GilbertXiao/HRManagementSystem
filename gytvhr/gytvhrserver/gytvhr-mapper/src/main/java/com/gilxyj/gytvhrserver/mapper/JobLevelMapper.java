package com.gilxyj.gytvhrserver.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.Collection;

import com.gilxyj.gytvhrserver.bean.JobLevel;

import java.util.List;

public interface JobLevelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JobLevel record);

    int insertSelective(JobLevel record);

    JobLevel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JobLevel record);

    int updateByPrimaryKey(JobLevel record);

    List<JobLevel> getAllJobLevel();

    int deleteByIdIn(@Param("idCollection")Collection<Long> idCollection);


}
