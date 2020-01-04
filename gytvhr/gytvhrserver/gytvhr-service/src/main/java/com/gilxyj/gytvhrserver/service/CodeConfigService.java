package com.gilxyj.gytvhrserver.service;

import com.gilxyj.gytvhrserver.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;

import com.gilxyj.gytvhrserver.bean.CodeConfig;
import com.gilxyj.gytvhrserver.mapper.CodeConfigMapper;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CodeConfigService{

    @Resource
    private CodeConfigMapper codeConfigMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    public int deleteByPrimaryKey(Integer id) {
        return codeConfigMapper.deleteByPrimaryKey(id);
    }


    public int insert(CodeConfig record) {
        return codeConfigMapper.insert(record);
    }


    public int insertSelective(CodeConfig record) {
        return codeConfigMapper.insertSelective(record);
    }


    public CodeConfig selectByPrimaryKey(Integer id) {
        return codeConfigMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(CodeConfig record) {
        return codeConfigMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(CodeConfig record) {
        return codeConfigMapper.updateByPrimaryKey(record);
    }

    public String getMaxWorkId(){
        CodeConfig codeConfig=codeConfigMapper.getByConfigKey("workId");
        String maxWorkId=employeeMapper.getMaxWorkIdByMaxId();
        Integer totalMaxWorkId=0;
        if(Integer.valueOf(codeConfig.getConfigValue())>Integer.valueOf(maxWorkId)){
            totalMaxWorkId=Integer.valueOf(codeConfig.getConfigValue())+1;
            codeConfig.setConfigValue(String.valueOf(totalMaxWorkId));
        }else {
            totalMaxWorkId = Integer.valueOf(maxWorkId) + 1;
            codeConfig.setConfigValue(String.valueOf(totalMaxWorkId));
        }
        codeConfigMapper.updateByPrimaryKeySelective(codeConfig);
        return String.format("%08d", totalMaxWorkId);
    }

}
