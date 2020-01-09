package com.gilxyj.gytvhrserver.controller.system.basic;

import com.gilxyj.gytvhrserver.bean.Position;
import com.gilxyj.gytvhrserver.bean.RespBean;
import com.gilxyj.gytvhrserver.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: gytvhrserver
 * @description:
 * @author: GilbertXiao
 * @create: 2019-10-19 20:35
 **/

@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {

    @Autowired
    PositionService positionService;


    @GetMapping("/")
    public List<Position> getAllPositions(){
        return positionService.getAllPositions();
    }

    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position){

        Integer result=positionService.addPosition(position);

        if(result==1){
            return RespBean.ok("新增成功！");
        }else {
            return RespBean.error("新增失败！");
        }

    }

    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position){
        Integer result=positionService.updatePosition(position);
        if (result==1){
            return RespBean.ok("更新成功");
        }else {
            return RespBean.error("更新失败");
        }
    }

    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable Long id){

        Integer result=positionService.deletePosition(id);
        if (result==1){
            return RespBean.ok("删除成功");
        }else {
            return RespBean.error("删除失败");
        }
    }

    @DeleteMapping("/")
    public RespBean deletePositionsByIds(Long[] ids){
        Integer result=positionService.deletePositionsByIds(ids);
        if (result==ids.length){
            return RespBean.ok("删除成功");
        }else {
            return RespBean.error("删除失败");
        }
    }



}
