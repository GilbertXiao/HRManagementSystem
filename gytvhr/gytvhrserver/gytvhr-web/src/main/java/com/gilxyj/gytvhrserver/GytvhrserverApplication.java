package com.gilxyj.gytvhrserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = "com.gilxyj.gytvhrserver.mapper")
@EnableCaching
public class GytvhrserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(GytvhrserverApplication.class, args);
    }

}
