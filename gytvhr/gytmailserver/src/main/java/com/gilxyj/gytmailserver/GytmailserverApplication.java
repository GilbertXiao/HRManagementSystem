package com.gilxyj.gytmailserver;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class GytmailserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(GytmailserverApplication.class, args);
    }

    @Bean
    Queue queue(){

        return new Queue("gytvhr.mail.welcome");
    }


}
