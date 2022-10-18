package com.example.networkdisk.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * SystemFileLog对应数据库systemfilelogtable表
 */
@Entity // 说明类的性质，实例
@Table(name = "systemfilelogtable") // 类对应的数据库表名
@Data // lombok的快捷生成注解
public class SystemFileLog {
    @Id // 主键注解
    @Temporal(TemporalType.TIMESTAMP)   // 将java的Date类型映射为符合数据库的TimeStamp类型（日期+时间）
    @Column(name = "time", nullable = false)
    private Date time;  // 由数据库系统自动生成，不需要人为设置
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "event", nullable = false)
    private String event;
}
