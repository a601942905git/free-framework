/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50718
Source Host           : 127.0.0.1:3306
Source Database       : free_admin

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-09-18 22:12:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for free_organization
-- ----------------------------
DROP TABLE IF EXISTS `free_organization`;
CREATE TABLE `free_organization` (
  `ID` int(8) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(10) NOT NULL,
  `PID` int(8) NOT NULL,
  `ORDER_NUM` int(2) NOT NULL,
  `ICON` varchar(10) DEFAULT NULL,
  `STATUS` enum('1','-1') NOT NULL COMMENT '1启用-1停用',
  `SAVE_DATE` datetime NOT NULL,
  `SAVE_PERSON` varchar(15) NOT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATE_PERSON` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of free_organization
-- ----------------------------
INSERT INTO `free_organization` VALUES ('1', '2234', '2', '1', null, '1', '2017-09-10 18:15:34', '111111', '2017-09-17 14:41:58', 'admin123');
INSERT INTO `free_organization` VALUES ('2', '测试哈哈', '121', '1', null, '1', '2017-09-14 20:57:10', '111111', '2017-09-14 20:59:58', '111111');
INSERT INTO `free_organization` VALUES ('3', '第三方', '1', '1', null, '1', '2017-09-14 21:26:30', '111111', '2017-09-14 21:30:22', '111111');
INSERT INTO `free_organization` VALUES ('4', 'hah', '1', '1', null, '1', '2017-09-17 04:59:28', 'admin123', null, null);
INSERT INTO `free_organization` VALUES ('5', 'ddd', '1', '1', null, '1', '2017-09-17 11:31:48', 'admin123', null, null);
INSERT INTO `free_organization` VALUES ('6', 'bb22', '1', '1', null, '1', '2017-09-17 11:37:06', 'admin123', '2017-09-17 11:37:18', 'admin123');
INSERT INTO `free_organization` VALUES ('7', 'bbvv', '123', '12', null, '-1', '2017-09-17 11:42:54', 'admin123', '2017-09-17 11:43:08', 'admin123');

-- ----------------------------
-- Table structure for free_resource
-- ----------------------------
DROP TABLE IF EXISTS `free_resource`;
CREATE TABLE `free_resource` (
  `ID` int(8) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(10) NOT NULL,
  `PID` int(8) NOT NULL,
  `ORDER_NUM` int(2) NOT NULL,
  `PATH` varchar(50) DEFAULT NULL,
  `TYPE` enum('1','2') NOT NULL COMMENT '1菜单2操作',
  `STATUS` enum('1','-1') NOT NULL COMMENT '1启用-1停用',
  `SAVE_DATE` datetime NOT NULL,
  `SAVE_PERSON` varchar(15) NOT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATE_PERSON` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of free_resource
-- ----------------------------
INSERT INTO `free_resource` VALUES ('1', '测试222', '1', '1', 'www.baidu.com', '1', '-1', '2017-09-13 17:20:20', '111111', '2017-09-17 14:44:38', null);
INSERT INTO `free_resource` VALUES ('2', '测试2', '1', '1', 'www.baidu.com', '2', '1', '2017-09-13 17:20:44', '111111', null, null);
INSERT INTO `free_resource` VALUES ('3', '测试3', '1', '1', 'www.baidu.com', '1', '1', '2017-09-13 17:24:13', '111111', null, null);
INSERT INTO `free_resource` VALUES ('4', '测试', '1', '1', 'www.baidu.com', '1', '1', '2017-09-14 21:15:37', '111111', null, null);
INSERT INTO `free_resource` VALUES ('5', 'aa', '2', '2', '2', '1', '1', '2017-09-17 11:38:18', 'admin123', null, null);

-- ----------------------------
-- Table structure for free_role
-- ----------------------------
DROP TABLE IF EXISTS `free_role`;
CREATE TABLE `free_role` (
  `ID` int(8) NOT NULL AUTO_INCREMENT COMMENT '角色主鍵',
  `NAME` varchar(10) NOT NULL COMMENT '角色名称',
  `SAVE_DATE` datetime NOT NULL,
  `SAVE_PERSON` varchar(15) NOT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATE_PERSON` varchar(15) DEFAULT NULL,
  `STATUS` enum('1','-1') NOT NULL COMMENT '1启用-1停用',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10006 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of free_role
-- ----------------------------
INSERT INTO `free_role` VALUES ('10000', '管理员1', '2017-09-10 00:18:52', '111111', '2017-09-14 21:00:22', '111111', '1');
INSERT INTO `free_role` VALUES ('10002', '是打发斯蒂芬三大的是', '2017-09-14 21:03:35', '111111', null, null, '1');
INSERT INTO `free_role` VALUES ('10003', '第三方', '2017-09-14 21:04:04', '111111', '2017-09-14 21:05:10', '111111', '1');
INSERT INTO `free_role` VALUES ('10004', '測試1', '2017-09-17 04:59:58', 'admin123', '2017-09-17 05:00:06', 'admin123', '1');
INSERT INTO `free_role` VALUES ('10005', 'nn12', '2017-09-17 11:37:59', 'admin123', '2017-09-17 14:43:13', 'admin123', '1');

-- ----------------------------
-- Table structure for free_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `free_role_resource`;
CREATE TABLE `free_role_resource` (
  `ID` int(8) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` int(8) NOT NULL,
  `AUTH_ID` int(8) NOT NULL COMMENT '权限编号,可以是菜单编号也可以是资源编号',
  `SAVE_DATE` datetime NOT NULL,
  `SAVE_PERSON` varchar(15) NOT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATE_PERSON` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of free_role_resource
-- ----------------------------

-- ----------------------------
-- Table structure for free_user
-- ----------------------------
DROP TABLE IF EXISTS `free_user`;
CREATE TABLE `free_user` (
  `ID` int(8) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(10) NOT NULL,
  `AGE` int(3) DEFAULT NULL,
  `FACE` varchar(50) NOT NULL,
  `MOBILE` varchar(11) NOT NULL,
  `TYPE` enum('1','2') NOT NULL,
  `LOGIN_CODE` varchar(15) NOT NULL,
  `LOGIN_PASSWORD` varchar(32) NOT NULL,
  `SEX` enum('1','2') NOT NULL,
  `SAVE_DATE` datetime NOT NULL,
  `SAVE_PERSON` varchar(15) NOT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATE_PERSON` varchar(15) DEFAULT NULL,
  `STATUS` enum('1','-1') NOT NULL COMMENT '1启用-1停用',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10005 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of free_user
-- ----------------------------
INSERT INTO `free_user` VALUES ('10000', 'aa', '111', '111', '13260803856', '1', '123456', '123456', '1', '2017-09-10 00:23:52', '111111', '2017-09-13 17:07:21', '111111', '-1');
INSERT INTO `free_user` VALUES ('10001', 'test', '26', 'baidu.com', '13260803857', '2', '13260803857', 'Aa601942905', '2', '2017-09-13 20:38:10', '111111', '2017-09-14 20:51:44', '111111', '-1');
INSERT INTO `free_user` VALUES ('10002', '测试卷哦', '22', '啊哈哈', '13260803856', '1', '13260803856', 'Aa601942905', '1', '2017-09-13 22:04:34', '111111', '2017-09-14 20:51:18', '111111', '1');
INSERT INTO `free_user` VALUES ('10003', 'lp', null, '', '13260803856', '1', 'admin123', 'DAC2AD179E2962F9C3BE51AAD8B34394', '1', '2017-09-17 04:08:38', '111111', null, null, '1');
INSERT INTO `free_user` VALUES ('10004', 'aa22', '22', 'baidu.com', '13260803856', '2', 'a601942905', '123C3075018E88473B811612A91A3EDC', '1', '2017-09-17 11:37:41', 'admin123', '2017-09-17 14:40:47', 'admin123', '-1');

-- ----------------------------
-- Table structure for free_user_role
-- ----------------------------
DROP TABLE IF EXISTS `free_user_role`;
CREATE TABLE `free_user_role` (
  `ID` int(8) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(8) NOT NULL,
  `ROLE_ID` int(8) NOT NULL,
  `SAVE_DATE` datetime NOT NULL,
  `SAVE_PERSON` varchar(15) NOT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATE_PERSON` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of free_user_role
-- ----------------------------
