package com.gilxyj.gytvhrserver.common;



import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import com.gilxyj.gytvhrserver.bean.Hr;

public class HrUtils {


    public static Hr getCurrentHr() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Hr principal =(Hr) authentication.getPrincipal();
        return  principal;
    }
}
