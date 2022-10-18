/*
Navicat MySQL Data Transfer

Source Server         : networkdisk
Source Server Version : 80000
Source Host           : localhost:3306
Source Database       : networkdisk

Target Server Type    : MYSQL
Target Server Version : 80000
File Encoding         : 65001

Date: 2022-10-10 21:50:00
*/
-- 如果不存在数据库networkdisk，则创建数据库networkdisk，并设置编码为UTF8
CREATE DATABASE IF NOT EXISTS networkdisk CHARACTER SET UTF8;
-- 使用数据库networkdisk
USE networkdisk;
-- Mysql中如果表和表之间建立了外键约束，则无法删除表及修改表结构。
-- 这种情况下，需要先在Mysql中取消外键约束，SET FROEIGN_KEY_CHECKS = 0;
-- 完成对表的修改后，再设置外键约束，SET FROEIGN_KEY_CHECKS = 1;
SET FOREIGN_KEY_CHECKS = 0;
-- ----------------------------
-- `admintable`的表单结构
-- ----------------------------
DROP TABLE IF EXISTS `admintable`;
CREATE TABLE `admintable`
(
    `id`   varchar(3) COLLATE utf8_unicode_ci  NOT NULL COMMENT '管理员ID',
    `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '昵称',
    `pwd`  varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '密码',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

-- ----------------------------
-- `usertable`的记录
-- ----------------------------
INSERT INTO admintable
VALUES ('001', 'admin', '123456');

-- ----------------------------
-- `usertable`的表单结构
-- ----------------------------
DROP TABLE IF EXISTS `usertable`;
CREATE TABLE `usertable`
(
    `id`    varchar(11) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户ID',
    `email` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '邮箱',
    `pwd`   varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '密码',
    `name`  varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '昵称',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

-- ----------------------------
-- Records of usertable
-- ----------------------------
INSERT INTO usertable
VALUES ('12345678910', '12345678910@qq.com', '12345678910', '第1个用户');

-- ----------------------------
-- Table structure for `systemfilelogtable`
-- ----------------------------
DROP TABLE IF EXISTS `systemfilelogtable`;
CREATE TABLE `systemfilelogtable`
(
    `time`  datetime COLLATE utf8_unicode_ci    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '时间',
    `id`    varchar(11) COLLATE utf8_unicode_ci NOT NULL COMMENT '事务发起者ID',
    `event` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '事务',
    PRIMARY KEY (`time`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

-- ----------------------------
-- Records of systemfilelogtable
-- ----------------------------

-- ----------------------------
-- Table structure for `filesharetable`
-- ----------------------------
DROP TABLE IF EXISTS `filesharetable`;
CREATE TABLE `filesharetable`
(
    `shareid`  varchar(11) COLLATE utf8_unicode_ci NOT NULL COMMENT '文件分享ID',
    `fileid`   varchar(11) COLLATE utf8_unicode_ci NOT NULL COMMENT '文件ID',
    `userid`   varchar(11) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户',
    `time`     datetime COLLATE utf8_unicode_ci    NOT NULL DEFAULT ON UPDATE CURRENT_TIMESTAMP CURRENT_TIMESTAMP COMMENT '分享日期',
    `accessid` varchar(50) COLLATE utf8_unicode_ci COMMENT '指定访问用户ID',
    PRIMARY KEY (`shareid`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

-- ----------------------------
-- Records of filesharetable
-- ----------------------------


-- ----------------------------
-- Table structure for `filedetailtable`
-- ----------------------------
DROP TABLE IF EXISTS `filedetailtable`;
CREATE TABLE `filedetailtable`
(
    `fileid`   varchar(11) COLLATE utf8_unicode_ci NOT NULL COMMENT '文件ID',
    `parentid` varchar(11) COLLATE utf8_unicode_ci NOT NULL COMMENT '父文件ID',
    `name`     varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '文件名',
    `time`     datetime COLLATE utf8_unicode_ci    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `style`    varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '文件类型',
    `size`     varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '文件大小',
    PRIMARY KEY (`fileid`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

-- ----------------------------
-- Records of filedetailtable
-- ----------------------------
INSERT INTO filedetailtable SET fileid='123456789aa',parentid='12345678910',name='我的网盘',style='根目录',size='0 KB';

-- ----------------------------
-- Table structure for `fileindextable`
-- ----------------------------
DROP TABLE IF EXISTS `fileindextable`;
CREATE TABLE `fileindextable`
(
    `id` varchar(11) COLLATE utf8_unicode_ci NOT NULL COMMENT '文件ID',
    `file`   Mediumblob                          NOT NULL COMMENT '文件',
    PRIMARY KEY (`fileid`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

-- ----------------------------
-- Table structure for `recyclebintable`
-- ----------------------------
DROP TABLE IF EXISTS `recyclebintable`;
CREATE TABLE `recyclebintable`
(
    `fileid` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '文件ID',
    `userid` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户ID',
    `time`   datetime COLLATE utf8_unicode_ci    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '删除日期',
    PRIMARY KEY (`fileid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 18
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;