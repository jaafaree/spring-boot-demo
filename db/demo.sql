/*
Navicat MySQL Data Transfer

Source Server         : @local
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-01-09 09:20:09
*/
CREATE DATABASE demo DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
Use demo;
SET NAMES utf8;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `cn_user`
-- ----------------------------
DROP TABLE IF EXISTS `cn_user`;
CREATE TABLE `cn_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` char(2) DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  `birthday` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `mobile` varchar(16) DEFAULT NULL,
  `crt_time` varchar(20) DEFAULT NULL,
  `crt_usr` varchar(20) DEFAULT NULL,
  `crt_host` varchar(20) DEFAULT NULL,
  `upd_time` varchar(20) DEFAULT NULL,
  `upd_usr` varchar(20) DEFAULT NULL,
  `upd_host` varchar(20) DEFAULT NULL,
  `status` char(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `req_log`
-- ----------------------------
DROP TABLE IF EXISTS `req_log`;
CREATE TABLE `req_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_ip` varchar(30) DEFAULT NULL,
  `uri` longtext,
  `type` varchar(50) DEFAULT NULL,
  `method` varchar(10) DEFAULT NULL,
  `param_data` longtext,
  `session_id` longtext,
  `time` varchar(50) DEFAULT NULL,
  `return_time` varchar(50) DEFAULT NULL,
  `return_data` longtext,
  `status_code` varchar(10) DEFAULT NULL,
  `time_consuming` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


