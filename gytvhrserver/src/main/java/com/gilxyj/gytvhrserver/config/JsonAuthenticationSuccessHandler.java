package com.gilxyj.gytvhrserver.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gilxyj.gytvhrserver.bean.Hr;
import com.gilxyj.gytvhrserver.bean.RespBean;
import com.gilxyj.gytvhrserver.common.HrUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
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
 * @create: 2019-09-03 01:27
 **/

@Component
public class JsonAuthenticationSuccessHandler implements
        AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
        resp.setContentType("application/json;charset=utf-8");
        Hr currentHr = HrUtils.getCurrentHr();
        PrintWriter out = resp.getWriter();
        ObjectMapper om = new ObjectMapper();
        String s = om.writeValueAsString(RespBean.ok("登录成功!", currentHr));
        out.write(s);
        out.flush();
        out.close();
    }
}
