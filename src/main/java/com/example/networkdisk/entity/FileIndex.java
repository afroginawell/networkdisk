package com.example.networkdisk.entity;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;


/**
 * FileIndex对应数据库fileindextable表
 */
@Entity // 说明类的性质，实例
@Table(name = "fileindextable") // 类对应的数据库表名
@Data // lombok的快捷生成注解
public class FileIndex {
    @Id // 主键注解
    @GeneratedValue(strategy = GenerationType.AUTO) // 主键生成的策略，AUTO指由程序控制生成
    @Column(name = "id", nullable = false)
    private String id;  // id从文件详情表获取，不需要生成
    @Lob // 主要用于数据库文件类型的映射，可以映射成为Blob和Clob
    @Basic(fetch=LAZY)  // 延迟加载
    @Column(name = "file", nullable = false)
    private byte[] file;
}
