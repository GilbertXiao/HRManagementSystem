package com.gilxyj.gytvhrserver.mapper;

import com.gilxyj.gytvhrserver.bean.PoliticsStatus;

import java.util.List;

public interface PoliticsStatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PoliticsStatus record);

    int insertSelective(PoliticsStatus record);

    PoliticsStatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PoliticsStatus record);

    int updateByPrimaryKey(PoliticsStatus record);

    List<PoliticsStatus> getAllPoliticsStatus();
}
