package com.example.networkdisk.entity;

public class FileIndex {
    private String id;
    private byte[] file;

    public FileIndex(){

    }

    public FileIndex(String id, byte[] file) {
        this.id = id;
        this.file = file;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }


}
