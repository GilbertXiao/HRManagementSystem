package com.gilxyj.gytvhrserver.service;

import com.gilxyj.gytvhrserver.bean.JobLevel;
import com.gilxyj.gytvhrserver.mapper.JobLevelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: gytvhrserver
 * @description:
 * @author: GilbertXiao
 * @create: 2019-09-15 15:29
 **/
@Service
public class JobLevelService {

    @Autowired
    JobLevelMapper jobLevelMapper;

    public List<JobLevel> getAllJobLevel(){
        return jobLevelMapper.getAllJobLevel();
    }
}
