package com.gilxyj.gytvhrserver.bean;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @program: gytvhr
 * @description:
 * @author: GilbertXiao
 * @create: 2020-01-26 23:40
 **/
public class ChatMsg  {

    private String to;
    private String from;
    private String fromNickName;

    private String content;
    private LocalDate date;

    public ChatMsg(String to, String content) {
        this.to = to;
        this.content = content;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getFromNickName() {
        return fromNickName;
    }

    public void setFromNickName(String fromNickName) {
        this.fromNickName = fromNickName;
    }
}
