package com.example.networkdisk.entity;

import java.util.Date;

/**
 * 系统日志实体类
 */
public class SystemLog {
    private Date time;
    private String id;
    private String event;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
