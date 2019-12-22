package com.gilxyj.gytvhrserver.service;

import com.gilxyj.gytvhrserver.bean.Nation;
import com.gilxyj.gytvhrserver.mapper.NationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: gytvhrserver
 * @description:
 * @author: GilbertXiao
 * @create: 2019-12-10 23:57
 **/
@Service
public class NationService {

    @Autowired
    NationMapper nationMapper;

    public List<Nation> getAllNations(){
        return nationMapper.getAllNations();
    }


}
