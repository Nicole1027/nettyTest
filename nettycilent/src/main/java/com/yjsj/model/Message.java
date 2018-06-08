package com.yjsj.model;

import java.io.Serializable;

public class Message implements Serializable {
    String user;
    String messageStr;

    public String getUser() {
        return user;
    }

    public Message setUser() {
        this.user = System.getProperty("user.name");
        return this;
    }

    public Message setUser(String user){
        this.user = user;
        return this;
    }

    public String getMessageStr() {
       return messageStr;
    }

    public Message setMessageStr(String messageStr) {
        this.messageStr = messageStr;
        return this;
    }
}
