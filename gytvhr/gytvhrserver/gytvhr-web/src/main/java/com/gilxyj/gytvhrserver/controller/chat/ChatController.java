package com.gilxyj.gytvhrserver.controller.chat;

import com.gilxyj.gytvhrserver.bean.Hr;
import com.gilxyj.gytvhrserver.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: gytvhr
 * @description: Chat
 * @author: GilbertXiao
 * @create: 2020-01-26 16:14
 **/

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    HrService hrService;

    @GetMapping("/hr")
    public List<Hr> getAllHrsExcudeLogin(String keyword){
        return hrService.getAllHrExceptAdmin(keyword);
    }

}
