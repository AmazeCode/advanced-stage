/*
 Navicat Premium Data Transfer

 Source Server         : Localhost
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : mbg

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 12/05/2024 20:25:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mbg_class
-- ----------------------------
DROP TABLE IF EXISTS `mbg_class`;
CREATE TABLE `mbg_class`  (
  `id` bigint NOT NULL COMMENT 'ID',
  `school_id` bigint NULL DEFAULT NULL COMMENT '学校',
  `class_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '班级名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mbg_class
-- ----------------------------
INSERT INTO `mbg_class` VALUES (1, 1, '火箭班');
INSERT INTO `mbg_class` VALUES (2, 1, '卫星班');

-- ----------------------------
-- Table structure for mbg_user
-- ----------------------------
DROP TABLE IF EXISTS `mbg_user`;
CREATE TABLE `mbg_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mbg_user
-- ----------------------------
INSERT INTO `mbg_user` VALUES (1, 'xiaozhang', 1);
INSERT INTO `mbg_user` VALUES (2, 'xiaoli', 1);

SET FOREIGN_KEY_CHECKS = 1;
