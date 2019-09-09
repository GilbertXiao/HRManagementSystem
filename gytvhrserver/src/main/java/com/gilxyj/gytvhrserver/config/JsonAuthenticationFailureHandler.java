package com.gilxyj.gytvhrserver.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gilxyj.gytvhrserver.bean.RespBean;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: wlbbl
 * @description:
 * @author: GilbertXiao
 * @create: 2019-09-03 01:30
 **/
@Component
public class JsonAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
        RespBean error = RespBean.error("登录失败!");
        if(e instanceof UsernameNotFoundException){
            error.setMsg("用户名不存在，登录失败!");
        } else if (e instanceof BadCredentialsException) {
            error.setMsg("密码输入错误，登录失败!");
        } else if (e instanceof DisabledException) {
            error.setMsg("账户被禁用，登录失败!");
        } else if (e instanceof CredentialsExpiredException) {
            error.setMsg("密码过期，登录失败!");
        } else if (e instanceof AccountExpiredException) {
            error.setMsg("账户过期，登录失败!");
        }
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.write(new ObjectMapper().writeValueAsString(error));
        writer.flush();
        writer.close();
    }
}
