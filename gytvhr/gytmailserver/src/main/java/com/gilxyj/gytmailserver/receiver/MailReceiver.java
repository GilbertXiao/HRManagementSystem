package com.gilxyj.gytmailserver.receiver;

import com.gilxyj.gytvhrserver.bean.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * @program: gytvhr
 * @description: mailreceiver
 * @author: GilbertXiao
 * @create: 2020-01-07 22:16
 **/
@Component
public class MailReceiver {


    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    MailProperties mailProperties;

    @Autowired
    TemplateEngine templateEngine;


    private static final Logger LOGGER = LoggerFactory.getLogger(MailReceiver.class);


    @RabbitListener(queues = "gytvhr.mail.welcome")
    public void welcomeHandler(Employee employee) {
        LOGGER.info(employee.toString());
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        try {
            mimeMessageHelper.setTo(employee.getEmail());
            mimeMessageHelper.setFrom(mailProperties.getUsername());
            mimeMessageHelper.setSubject("入职欢迎");
            mimeMessageHelper.setSentDate(new Date());

            Context context = new Context();
            context.setVariable("name", employee.getName());
            context.setVariable("posName", employee.getPosition().getName());
            context.setVariable("joblevelName", employee.getJobLevel().getName());
            context.setVariable("departmentName", employee.getDepartment().getName());

            String welcome = templateEngine.process("welcome", context);
            mimeMessageHelper.setText(welcome, true);
            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
            LOGGER.error(e.toString());
        }


    }

}
