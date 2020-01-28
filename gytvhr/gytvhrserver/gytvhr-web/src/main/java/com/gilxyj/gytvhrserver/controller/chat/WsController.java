package com.gilxyj.gytvhrserver.controller.chat;

import com.gilxyj.gytvhrserver.bean.ChatMsg;
import com.gilxyj.gytvhrserver.bean.Hr;
import com.gilxyj.gytvhrserver.common.HrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.time.LocalDate;

/**
 * @program: gytvhr
 * @description:
 * @author: GilbertXiao
 * @create: 2020-01-26 23:36
 **/
@Controller
public class WsController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/ws/chat")
    public void handleMsg(Authentication authentication, ChatMsg chatMsg){
        //Hr currentHr = HrUtils.getCurrentHr(); 这个方法用不了
        Hr hr = (Hr) authentication.getPrincipal();
        chatMsg.setFrom(hr.getUsername());
        chatMsg.setFromNickName(hr.getName());
        chatMsg.setDate(LocalDate.now());
        simpMessagingTemplate.convertAndSendToUser(chatMsg.getTo(), "/queue/chat",chatMsg);
    }

}
