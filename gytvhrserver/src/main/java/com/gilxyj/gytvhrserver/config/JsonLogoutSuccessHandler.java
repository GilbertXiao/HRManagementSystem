package com.gilxyj.gytvhrserver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gilxyj.gytvhrserver.bean.RespBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: gytvhrserver
 * @description: logout handler
 * @author: GilbertXiao
 * @create: 2019-10-10 22:55
 **/
@Component
public class JsonLogoutSuccessHandler implements LogoutSuccessHandler {

    private static final Logger LOGGER= LoggerFactory.getLogger(JsonLogoutSuccessHandler.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(new ObjectMapper().writeValueAsString(RespBean.ok("注销成功")));

        writer.flush();
        writer.close();

    }
}
