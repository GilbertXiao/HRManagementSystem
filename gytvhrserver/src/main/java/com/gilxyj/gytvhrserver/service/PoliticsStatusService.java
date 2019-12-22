package com.gilxyj.gytvhrserver.service;

import com.gilxyj.gytvhrserver.bean.PoliticsStatus;
import com.gilxyj.gytvhrserver.mapper.PoliticsStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: gytvhrserver
 * @description:
 * @author: GilbertXiao
 * @create: 2019-12-11 00:07
 **/
@Service
public class PoliticsStatusService {

    @Autowired
    PoliticsStatusMapper politicsStatusMapper;

    public List<PoliticsStatus> getAllPoliticsStatus(){
        return politicsStatusMapper.getAllPoliticsStatus();
    }
}
