package com.sekolah.main.common;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Response<Data> {

    Date date = new Date();
    
    private boolean status;
    private String message;
    Object data;
    private String timestamp = new Timestamp(date.getTime()).toString();

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}