/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50150
 Source Host           : localhost:3306
 Source Schema         : db_livebp

 Target Server Type    : MySQL
 Target Server Version : 50150
 File Encoding         : 65001

 Date: 27/02/2020 15:30:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for l_fans
-- ----------------------------
DROP TABLE IF EXISTS `l_fans`;
CREATE TABLE `l_fans`  (
  `userphone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fansphone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  INDEX `FK_UPP`(`userphone`) USING BTREE,
  INDEX `FK_FPP`(`fansphone`) USING BTREE,
  CONSTRAINT `FK_FPP` FOREIGN KEY (`fansphone`) REFERENCES `l_user` (`userphone`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_UPP` FOREIGN KEY (`userphone`) REFERENCES `l_user` (`userphone`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of l_fans
-- ----------------------------
INSERT INTO `l_fans` VALUES ('13802395586', '18925789992');
INSERT INTO `l_fans` VALUES ('13662920679', '13802395586');
INSERT INTO `l_fans` VALUES ('13802395586', '13662920679');
INSERT INTO `l_fans` VALUES ('18925789992', '13662920679');

-- ----------------------------
-- Table structure for l_follow
-- ----------------------------
DROP TABLE IF EXISTS `l_follow`;
CREATE TABLE `l_follow`  (
  `userphone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `followphone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  INDEX `FK_UP`(`userphone`) USING BTREE,
  INDEX `FK_FP`(`followphone`) USING BTREE,
  CONSTRAINT `FK_FP` FOREIGN KEY (`followphone`) REFERENCES `l_user` (`userphone`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_UP` FOREIGN KEY (`userphone`) REFERENCES `l_user` (`userphone`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of l_follow
-- ----------------------------
INSERT INTO `l_follow` VALUES ('18925789992', '13802395586');
INSERT INTO `l_follow` VALUES ('13802395586', '13662920679');
INSERT INTO `l_follow` VALUES ('13662920679', '13802395586');
INSERT INTO `l_follow` VALUES ('13662920679', '18925789992');

-- ----------------------------
-- Table structure for l_user
-- ----------------------------
DROP TABLE IF EXISTS `l_user`;
CREATE TABLE `l_user`  (
  `userphone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userpwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `useremail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `usersex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`userphone`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of l_user
-- ----------------------------
INSERT INTO `l_user` VALUES ('13226422719', 'huangxitao', '123456', '13662920671@163.com', '1');
INSERT INTO `l_user` VALUES ('13662920670', 'huangxitao2', '123456', '13662920671@163.com', '1');
INSERT INTO `l_user` VALUES ('13662920679', '黄锡滔', '123456', '13662920679@163.com', '1');
INSERT INTO `l_user` VALUES ('13802395586', 'huangshaoyi', '123456', '13662920671@163.com', '0');
INSERT INTO `l_user` VALUES ('18925789992', 'yexiulan', '123456', '13662920671@163.com', '0');

-- ----------------------------
-- Table structure for l_userinfo
-- ----------------------------
DROP TABLE IF EXISTS `l_userinfo`;
CREATE TABLE `l_userinfo`  (
  `userphone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `touxiang_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `usertext` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userphone`) USING BTREE,
  INDEX `FK_UPPP`(`userphone`) USING BTREE,
  CONSTRAINT `FK_UPPP` FOREIGN KEY (`userphone`) REFERENCES `l_user` (`userphone`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of l_userinfo
-- ----------------------------
INSERT INTO `l_userinfo` VALUES ('13226422719', '13226422719.jpg', NULL);
INSERT INTO `l_userinfo` VALUES ('13662920670', '13662920670.jpg', NULL);
INSERT INTO `l_userinfo` VALUES ('13662920679', '13662920679.png', 'I am fine!');
INSERT INTO `l_userinfo` VALUES ('13802395586', '13802395586.jpg', '哈哈哈');
INSERT INTO `l_userinfo` VALUES ('18925789992', 'touxiang.png', NULL);

SET FOREIGN_KEY_CHECKS = 1;
