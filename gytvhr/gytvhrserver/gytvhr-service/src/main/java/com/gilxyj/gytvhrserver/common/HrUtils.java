package com.gilxyj.gytvhrserver.common;



import org.springframework.security.core.context.SecurityContextHolder;
import com.gilxyj.gytvhrserver.bean.Hr;

public class HrUtils {
    public static Hr getCurrentHr() {
        return (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
