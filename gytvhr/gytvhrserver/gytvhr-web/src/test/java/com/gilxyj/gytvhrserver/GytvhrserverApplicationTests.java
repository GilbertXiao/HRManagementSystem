package com.gilxyj.gytvhrserver;


import com.gilxyj.gytvhrserver.bean.ChatMsg;
import com.gilxyj.gytvhrserver.bean.CodeConfig;
import com.gilxyj.gytvhrserver.service.CodeConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GytvhrserverApplicationTests {

    @Autowired
    CodeConfigService codeConfigService;

    @Test
    public void test01(){
        String maxWorkId = codeConfigService.getMaxWorkId();
        System.out.println(maxWorkId);
    }

    @Test
    public void contextLoads() {

        List<ChatMsg> chatResps = new ArrayList<>();
        for(int i=0;i<100;i++){
            chatResps.add(new ChatMsg(String.valueOf(i),String.valueOf(i)));
        }
        int i=0;
        for (ChatMsg chatResp : chatResps) {
            if(i==98){
                chatResps.remove(chatResp);
                System.out.println("here");
            }

            if(i==97){
                System.out.println("here97");
            }

            i++;

        }


    }


    @Test
    public void contextLoads1() {

        List<ChatMsg> chatResps = new ArrayList<>();
        for(int i=0;i<100;i++){
            chatResps.add(new ChatMsg(String.valueOf(i),String.valueOf(i)));
        }

        int i=0;
        Iterator<ChatMsg> iterator = chatResps.iterator();

        while (iterator.hasNext()){

            if(i==97||i==98){
                iterator.remove();
            }
            i++;
        }

        System.out.println(chatResps.size());

    }


    @Test
    public void contextLoads2() {

        List<ChatMsg> chatResps = new ArrayList<>();
        for(int i=0;i<100;i++){
            chatResps.add(new ChatMsg(String.valueOf(i),String.valueOf(i)));
        }




        for(int i=chatResps.size();i>=0;i--){
            if(i==97||i==98){
                chatResps.remove(chatResps.get(i));
            }

        }

        System.out.println(chatResps.size());
        System.out.println("中文字符");

    }

    @Test
    public void contextLoads3() {

        System.out.println("Default Charset=" + Charset.defaultCharset());
        System.out.println("file.encoding=" + System.getProperty("file.encoding"));
        System.out.println("Default Charset=" + Charset.defaultCharset());
        System.out.println("Default Charset in Use=" + getDefaultCharSet());

    }


    private static String getDefaultCharSet() {
        OutputStreamWriter writer = new OutputStreamWriter(new ByteArrayOutputStream());
        String enc = writer.getEncoding();
        return enc;
    }


}
