package com.example.networkdisk.entity;

import java.sql.Timestamp;

/**
 * 用户文件类
 */
public class FileDetail {

    public FileDetail(){}

    public FileDetail(String fileid, String parentid, String name, Timestamp time, String style, String size) {
        this.fileid = fileid;
        this.parentid = parentid;
        this.name = name;
        this.time = time;
        this.style = style;
        this.size = size;
    }

    private String fileid;
    private String parentid;
    private String name;
    private Timestamp time;
    private String style;
    private String size;


    public String getFileid() {
        return fileid;
    }

    public void setFileid(String fileid) {
        this.fileid = fileid;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "FileDetail{" +
                "fileid='" + fileid + '\'' +
                ", parentid='" + parentid + '\'' +
                ", name='" + name + '\'' +
                ", time=" + time +
                ", style='" + style + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
