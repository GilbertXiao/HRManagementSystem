package com.gilxyj.gytvhrserver.mapper;
import org.apache.ibatis.annotations.Param;

import com.gilxyj.gytvhrserver.bean.Position;

import java.util.Collection;
import java.util.List;

public interface PositionMapper {
        int deleteByPrimaryKey(Long id);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);

    List<Position> getAllPositions();


    int deleteByIdIn(@Param("idCollection")Collection<Long> idCollection);






}
