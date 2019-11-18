package com.gilxyj.gytvhrserver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gilxyj.gytvhrserver.bean.RespBean;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: gytvhrserver
 * @description: 解决未登录的时候访问接口,带来的默认重定向问题
 * @author: GilbertXiao
 * @create: 2019-10-10 23:31
 **/
@Component
public class JsonAuthencationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(401);
        PrintWriter writer = response.getWriter();
        RespBean respBean = RespBean.error("访问失败");
        if (authException instanceof InsufficientAuthenticationException) {
            respBean.setMsg("请求失败，请联系管理员!");
        }
        writer.write(new ObjectMapper().writeValueAsString(respBean));
        writer.flush();
        writer.close();
    }
}
