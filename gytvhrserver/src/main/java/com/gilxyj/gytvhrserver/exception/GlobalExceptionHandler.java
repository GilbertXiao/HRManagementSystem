package com.gilxyj.gytvhrserver.exception;

import com.gilxyj.gytvhrserver.bean.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @program: gytvhrserver
 * @description:
 * @author: GilbertXiao
 * @create: 2019-10-20 23:31
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public RespBean sqlException(SQLException sqlException){
        if(sqlException instanceof SQLIntegrityConstraintViolationException){
            return RespBean.error("该数据有其他关联数据，操作失败!");
        }
        return RespBean.error("数据库操作失败！");
    }
}
