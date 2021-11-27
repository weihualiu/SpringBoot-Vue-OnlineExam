SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userId` int(9) NOT NULL AUTO_INCREMENT COMMENT 'ID号',
  `loginName` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '登录名',
  `userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '姓名',
  `gender` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '性别',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电子邮箱',
  `pwd` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密码',
  `role` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色(0管理员，1老师，2学生)',
  PRIMARY KEY (`userId`) USING BTREE,
  INDEX `gender`(`gender`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10001 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (0, 'admin', '超级管理员', '男', 'auhiewuil@gmail.com', '123456', '0');
INSERT INTO `user` VALUES (1, 'shaowei', '邵伟', '女', 'shao.wei@rytong.com', '123456', '1');
INSERT INTO `user` VALUES (2, 'chenlei', '陈雷', '男', 'chen.lei@rytong.com', '123456', '2');
INSERT INTO `user` VALUES (3, 'zhaowentao', '赵文涛', '男', 'zhao.wentao@rytong.com', '123456', '2');
INSERT INTO `user` VALUES (4, 'liuweihua', '刘伟华', '男', 'liu.weihua@rytong.com', '123456', '2');

-- ----------------------------
-- Table structure for paper_manage
-- ----------------------------
DROP TABLE IF EXISTS `paper_manage`;
CREATE TABLE `paper_manage`  (
  `paperId` int(9) NOT NULL AUTO_INCREMENT COMMENT '试卷编号',
  `description` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '该次考试介绍',
  `totalTime` int(3) NULL DEFAULT NULL COMMENT '持续时长分钟',
  `totalScore` int(4) NULL DEFAULT NULL COMMENT '总分',
  `startTime` varchar(14) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '开始时间',
  `endTime` varchar(14) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`paperId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '试卷管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of paper_manage
-- ----------------------------
INSERT INTO `paper_manage` VALUES (1, 'Android开发规范20211120', 10, 100, '20211118103000', '20211118104000');
INSERT INTO `paper_manage` VALUES (2, 'EWP开发规范20211120', 10, 100, '20211118103000', '20211118104000');


DROP TABLE IF EXISTS `paper_question`;
CREATE TABLE `paper_question` (
  `paperId` int(9) NOT NULL COMMENT '试卷编号',
  `questionType` int(1) NOT NULL COMMENT '试题类型 1填空;2选择;3判断',
  `questionId` int(9) NOT NULL COMMENT '试题编号'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '试卷题库关联表' ROW_FORMAT = Dynamic;

INSERT INTO `paper_question` VALUES (1, 1, 1);
INSERT INTO `paper_question` VALUES (1, 2, 1);
INSERT INTO `paper_question` VALUES (1, 3, 1);
INSERT INTO `paper_question` VALUES (2, 1, 1);
INSERT INTO `paper_question` VALUES (2, 2, 1);
INSERT INTO `paper_question` VALUES (2, 3, 1);


DROP TABLE IF EXISTS `question_type`;
CREATE TABLE `question_type` (
  `typeId` int(9) NOT NULL AUTO_INCREMENT COMMENT '题库类型编号',
  `qtypeName` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型名称',
  `parentId` int(9) NOT NULL COMMENT '上级类型编号',
  PRIMARY KEY (`typeId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '题库类型';

INSERT INTO `question_type` VALUES (1, 'Android开发规范', 0);
INSERT INTO `question_type` VALUES (2, '基础编码规范', 1);
INSERT INTO `question_type` VALUES (3, 'GIT提交规范', 1);


-- ----------------------------
-- Table structure for fill_question
-- ----------------------------
DROP TABLE IF EXISTS `fill_question`;
CREATE TABLE `fill_question`  (
  `questionId` int(9) NOT NULL AUTO_INCREMENT COMMENT '试题编号',
  `question` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '试题内容',
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '正确答案',
  `analysis` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '题目解析',
  `score` int(2) NULL DEFAULT 2 COMMENT '分数',
  `level` varchar(5) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '难度等级',
  `typeId` int(9) NOT NULL COMMENT '所属题类型',
  PRIMARY KEY (`questionId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '填空题题库' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fill_question
-- ----------------------------
INSERT INTO `fill_question` VALUES (10000, '从计算机网络系统组成的角度看，计算机网络可以分为()和()', '通信子网资源子网', NULL, 2, '3', 2);
INSERT INTO `fill_question` VALUES (10001, '收发电子邮件，属于ISO/OSI RM中 ()层的功能。', '应用', NULL, 2, '1', 2);

-- ----------------------------
-- Table structure for judge_question
-- ----------------------------
DROP TABLE IF EXISTS `judge_question`;
CREATE TABLE `judge_question`  (
  `questionId` int(9) NOT NULL AUTO_INCREMENT COMMENT '试题编号',
  `question` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '试题内容',
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '正确答案',
  `analysis` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '题目解析',
  `score` int(2) NULL DEFAULT 2 COMMENT '分数',
  `level` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '难度等级',
  `typeId` int(9) NOT NULL COMMENT '所属题类型',
  PRIMARY KEY (`questionId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '判断题题库表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of judge_question
-- ----------------------------
INSERT INTO `judge_question` VALUES (10001, '与有线网相比,无线网的数据传输率一般相对较慢', 'T', NULL, 2, '1', 2);
INSERT INTO `judge_question` VALUES (10002, 'OSI参考模型中,不同节点的同等层具有不同的功能', 'F', NULL, 2, '1', 2);


-- ----------------------------
-- Table structure for multi_question
-- ----------------------------
DROP TABLE IF EXISTS `multi_question`;
CREATE TABLE `multi_question`  (
  `questionId` int(9) NOT NULL AUTO_INCREMENT COMMENT '试题编号',
  `question` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '问题题目',
  `answerA` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '选项A',
  `answerB` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '选项B',
  `answerC` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '选项C',
  `answerD` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '选项D',
  `rightAnswer` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '正确答案',
  `analysis` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '题目解析',
  `score` int(2) NULL DEFAULT 2 COMMENT '分数',
  `typeId` int(9) NOT NULL COMMENT '所属题类型',
  `level` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '难度等级',
  PRIMARY KEY (`questionId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '选择题题库表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of multi_question
-- ----------------------------
INSERT INTO `multi_question` VALUES (10000, 'DNS 服务器和DHCP服务器的作用是（）', '将IP地址翻译为计算机名，为客户机分配IP地址', '将IP地址翻译为计算机名、解析计算机的MAC地址', '将计算机名翻译为IP地址、为客户机分配IP地址', '将计算机名翻译为IP地址、解析计算机的MAC地址', 'C', NULL, 2, 2, '2');
INSERT INTO `multi_question` VALUES (10001, 'HTTP协议通常使用什么协议进行传输（）', 'ARP', 'DHCP', 'UDP', 'TCP', 'D', NULL, 2, 2, '2');


DROP TABLE IF EXISTS `paper_answer`;
CREATE TABLE `paper_answer` (
  `userId` int(9) NOT NULL,
  `paperId` int(9) NOT NULL,
  `questionId` int(9) NOT NULL,
  `questionType` int(1) NOT NULL,
  `answer` varchar(50) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '试卷答题记录表';


INSERT INTO `paper_answer` VALUES (2, 1, 1, 1, '测试');

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `userId` int(9) NOT NULL COMMENT '用户编号',
  `paperId` int(9) NOT NULL COMMENT '试卷编号',
  `score` int(5) NOT NULL COMMENT '成绩',
  `answerDate` varchar(14) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '答题时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '成绩管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES (4, 1, 89, '20211124165303');
INSERT INTO `score` VALUES (3, 2, 55, '20211124165303');

DROP TABLE IF EXISTS `paper_user`;
CREATE TABLE `paper_user` (
  `userId` int(9) NOT NULL,
  `paperId` int(9) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '试卷指定考试用户' ROW_FORMAT = Dynamic;

INSERT INTO `paper_user` VALUES (3, 1);
INSERT INTO `paper_user` VALUES (4, 1);

SET FOREIGN_KEY_CHECKS = 1;
