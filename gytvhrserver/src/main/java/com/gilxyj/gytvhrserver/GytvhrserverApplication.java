package com.gilxyj.gytvhrserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.gilxyj.gytvhrserver.mapper")
public class GytvhrserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(GytvhrserverApplication.class, args);
    }

}
