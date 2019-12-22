package com.gilxyj.gytvhrserver.service;

import com.gilxyj.gytvhrserver.bean.Position;
import com.gilxyj.gytvhrserver.mapper.PositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;

import java.util.List;

/**
 * @program: gytvhrserver
 * @description:
 * @author: GilbertXiao
 * @create: 2019-10-19 21:45
 **/
@Service
public class PositionService {

    @Autowired
    PositionMapper positionMapper;

    public List<Position> getAllPositions() {
        return positionMapper.getAllPositions();
    }

    public Integer addPosition(Position position) {
        position.setCreateDate(new Timestamp(System.currentTimeMillis()));
        position.setEnabled(1);
        return positionMapper.insertSelective(position);
    }

    public Integer updatePosition(Position position) {
        return positionMapper.updateByPrimaryKeySelective(position);
    }

    public Integer deletePosition(Long id) {

        return positionMapper.deleteByPrimaryKey(id);
    }

    public Integer deletePositionsByIds(Long[] ids) {
        return positionMapper.deleteByIdIn(Arrays.asList(ids));
    }
}
