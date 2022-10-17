package com.example.networkdisk.entity;

import java.sql.Timestamp;

public class ShareFile {

    private String fileid;
    private String userid;
    private String shareid;
    private Timestamp time;
    private String accessid;

    public ShareFile(String fileid, String userid, String shareid, Timestamp time, String accessid) {
        this.fileid = fileid;
        this.userid = userid;
        this.shareid = shareid;
        this.time = time;
        this.accessid = accessid;
    }

    public ShareFile(){}

    public String getFileid() {
        return fileid;
    }

    public void setFileid(String fileid) {
        this.fileid = fileid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getShareid() {
        return shareid;
    }

    public void setShareid(String shareid) {
        this.shareid = shareid;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getAccessid() {
        return accessid;
    }

    public void setAccessid(String accessid) {
        this.accessid = accessid;
    }
}
