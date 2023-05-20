DROP TABLE IF EXISTS user;

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6;

DELETE FROM user;

INSERT INTO `user` (`id`, `name`, `age`, `email`, `create_time`, `update_time`, `version`) VALUES (1, 'neo', 18, 'smile1@ityouknow.com', NULL, NULL, NULL);
INSERT INTO `user` (`id`, `name`, `age`, `email`, `create_time`, `update_time`, `version`) VALUES (2, 'keep', 36, 'smile@ityouknow.com', NULL, NULL, NULL);
INSERT INTO `user` (`id`, `name`, `age`, `email`, `create_time`, `update_time`, `version`) VALUES (3, 'pure', 28, 'smile@ityouknow.com', NULL, NULL, NULL);
INSERT INTO `user` (`id`, `name`, `age`, `email`, `create_time`, `update_time`, `version`) VALUES (4, 'smile', 21, 'smile@ityouknow.com', NULL, NULL, NULL);
INSERT INTO `user` (`id`, `name`, `age`, `email`, `create_time`, `update_time`, `version`) VALUES (5, 'it', 24, 'smile@ityouknow.com', NULL, NULL, NULL);