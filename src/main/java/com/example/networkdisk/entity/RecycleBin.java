package com.example.networkdisk.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * RecycleBin对应数据库recyclebintable表
 */
@Entity // 说明类的性质，实例
@Table(name = "recyclebintable") // 类对应的数据库表名
@Data // lombok的快捷生成注解
public class RecycleBin {
    @Id // 主键注解
    @GeneratedValue(strategy = GenerationType.AUTO) // 主键生成的策略，AUTO指由程序控制生成
    @Column(name = "fileid", nullable = false)
    private String fileid;
    @Column(name = "userid", nullable = false)
    private String userid;
    @Column(name = "time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP) // 将java的Date类型映射为符合数据库的TimeStamp类型（日期+时间）
    private Date time;  // 由数据库系统自动生成，不需要人为设置
}
