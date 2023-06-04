/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : express_db

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2018-07-04 11:07:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) default NULL,
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('a', 'a');

-- ----------------------------
-- Table structure for `company`
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `companyId` int(11) NOT NULL auto_increment,
  `companyName` varchar(50) default NULL,
  PRIMARY KEY  (`companyId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('1', '韵达快递');
INSERT INTO `company` VALUES ('2', '申通快递');
INSERT INTO `company` VALUES ('3', '圆通快递');
INSERT INTO `company` VALUES ('4', '顺丰快递');

-- ----------------------------
-- Table structure for `expresstake`
-- ----------------------------
DROP TABLE IF EXISTS `expresstake`;
CREATE TABLE `expresstake` (
  `orderId` int(11) NOT NULL auto_increment,
  `taskTitle` varchar(50) default NULL,
  `companyObj` int(11) default NULL,
  `waybill` varchar(20) default NULL,
  `receiverName` varchar(20) default NULL,
  `telephone` varchar(30) default NULL,
  `receiveMemo` longtext,
  `takePlace` varchar(80) default NULL,
  `giveMoney` float default NULL,
  `takeStateObj` varchar(30) default NULL,
  `userObj` varchar(20) default NULL,
  `addTime` varchar(20) default NULL,
  PRIMARY KEY  (`orderId`),
  KEY `FK6DD6A7F7CD858B95` (`companyObj`),
  KEY `FK6DD6A7F7C80FC67` (`userObj`),
  CONSTRAINT `FK6DD6A7F7C80FC67` FOREIGN KEY (`userObj`) REFERENCES `userinfo` (`user_name`),
  CONSTRAINT `FK6DD6A7F7CD858B95` FOREIGN KEY (`companyObj`) REFERENCES `company` (`companyId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of expresstake
-- ----------------------------
INSERT INTO `expresstake` VALUES ('1', '帮我取一个衣服包裹', '1', '23498592014810', '李小双', '13980430834', '收货验证码是234083', '芙蓉8-224宿舍', '5', '已接单', 'user1', '2018-03-04 15:14:53');
INSERT INTO `expresstake` VALUES ('2', '帮我取个充电器', '3', '5141251414144', '李璐璐', '13985039343', '马上去学校圆通营业点帮我取', '女生宿舍下面', '10', '已接单', 'user1', '2018-03-08 11:09:15');
INSERT INTO `expresstake` VALUES ('3', '我有个u怕到了谁拿下', '2', '5158147105104015', '大神汪', '13508340835', '快点，我上课用的', '男生8宿舍', '6', '已接单', 'user1', '2018-07-04 10:30:38');

-- ----------------------------
-- Table structure for `notice`
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `noticeId` int(11) NOT NULL auto_increment,
  `title` varchar(80) default NULL,
  `content` longtext,
  `publishDate` varchar(30) default NULL,
  PRIMARY KEY  (`noticeId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1', '快递代拿app开题了', '以后同学们需要跑腿的，或者想跑腿的都可以来这个平台赚点外快哦！', '2018-03-04 14:12:15');

-- ----------------------------
-- Table structure for `orderstate`
-- ----------------------------
DROP TABLE IF EXISTS `orderstate`;
CREATE TABLE `orderstate` (
  `orderStateId` int(11) NOT NULL auto_increment,
  `orderStateName` varchar(20) default NULL,
  PRIMARY KEY  (`orderStateId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderstate
-- ----------------------------
INSERT INTO `orderstate` VALUES ('1', '派送中');
INSERT INTO `orderstate` VALUES ('2', '派送完毕');

-- ----------------------------
-- Table structure for `takeorder`
-- ----------------------------
DROP TABLE IF EXISTS `takeorder`;
CREATE TABLE `takeorder` (
  `orderId` int(11) NOT NULL auto_increment,
  `expressTakeObj` int(11) default NULL,
  `userObj` varchar(20) default NULL,
  `takeTime` varchar(20) default NULL,
  `orderStateObj` int(11) default NULL,
  `ssdt` longtext,
  `evaluate` varchar(60) default NULL,
  PRIMARY KEY  (`orderId`),
  KEY `FKCFE93EE710500675` (`expressTakeObj`),
  KEY `FKCFE93EE7C80FC67` (`userObj`),
  KEY `FKCFE93EE771554D79` (`orderStateObj`),
  CONSTRAINT `FKCFE93EE710500675` FOREIGN KEY (`expressTakeObj`) REFERENCES `expresstake` (`orderId`),
  CONSTRAINT `FKCFE93EE771554D79` FOREIGN KEY (`orderStateObj`) REFERENCES `orderstate` (`orderStateId`),
  CONSTRAINT `FKCFE93EE7C80FC67` FOREIGN KEY (`userObj`) REFERENCES `userinfo` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of takeorder
-- ----------------------------
INSERT INTO `takeorder` VALUES ('1', '1', 'user3', '2018-03-04 16:14:56', '1', '正在前往韵达快递办事点', '--');
INSERT INTO `takeorder` VALUES ('2', '2', 'user3', '2018-03-08 11:37:33', '2', '马上到了', '速度很快！');
INSERT INTO `takeorder` VALUES ('4', '3', 'user3', '2018-07-04 10:56:52', '1', '我到宿舍楼下了，你出来取下', '--');

-- ----------------------------
-- Table structure for `userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `user_name` varchar(20) NOT NULL,
  `password` varchar(30) default NULL,
  `userType` varchar(20) default NULL,
  `name` varchar(20) default NULL,
  `gender` varchar(4) default NULL,
  `birthDate` datetime default NULL,
  `userPhoto` varchar(50) default NULL,
  `telephone` varchar(20) default NULL,
  `email` varchar(50) default NULL,
  `address` varchar(80) default NULL,
  `authFile` varchar(50) default NULL,
  `shenHeState` varchar(20) default NULL,
  `regTime` varchar(30) default NULL,
  PRIMARY KEY  (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('user1', '123', '普通用户', '王霞', '男', '2018-03-01 00:00:00', 'upload/56BFA3B7C52104B34724964D16B8C2FD.jpg', '13573598343', 'wangxia@163.com', '四川成都红星路13号', '', '已审核', '2018-03-04 14:15:15');
INSERT INTO `userinfo` VALUES ('user2', '123', '普通用户', '王敏', '男', '2018-03-08 00:00:00', 'upload/E4FA3F023AEAE9996EA1E962DE7FC572.jpg', '13840830834', 'wangmin@163.com', '四川自贡', '', '已审核', '2018-03-08 10:37:02');
INSERT INTO `userinfo` VALUES ('user3', '123', '快递员', '张快递', '女', '2018-03-08 00:00:00', 'upload/7974EEE8941ACBB6691E51671420B7AA.jpg', '13084838034', 'kuaidi@163.com', '四川南充', 'upload/4149F864FF84A2819321FE472220C30E.jpg', '已审核', '2018-03-08 10:37:56');
