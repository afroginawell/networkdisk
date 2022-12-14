package com.example.networkdisk.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * FileDetail对应数据库filedetailtable表
 */
@Entity // 说明类的性质，实例
@Table(name = "filedetailtable") // 类对应的数据库表名
@Data // lombok的快捷生成注解
public class FileDetail {
    @Id // 主键注解
    @GeneratedValue(strategy = GenerationType.AUTO) // 主键生成的策略，AUTO指由程序控制生成
    @Column(name = "fileid", nullable = false)
    private String fileid;
    @Column(name = "parentid", nullable = false)
    private String parentid;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP) // 将java的Date类型映射为符合数据库的TimeStamp类型（日期+时间）
    private Date time;  // 该值由数据库自动生成，不需要外部设置
    @Column(name = "style", nullable = false)
    private String style;
    @Column(name = "size", nullable = false)
    private String size;
}
