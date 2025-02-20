-- MySQL dump 10.13  Distrib 8.0.31, for Linux (x86_64)
--
-- Host: localhost    Database: code_manage_system
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `SPRING_SESSION`
--

DROP TABLE IF EXISTS `SPRING_SESSION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SPRING_SESSION` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint NOT NULL,
  `LAST_ACCESS_TIME` bigint NOT NULL,
  `MAX_INACTIVE_INTERVAL` int NOT NULL,
  `EXPIRY_TIME` bigint NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_ID`),
  UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SPRING_SESSION`
--

LOCK TABLES `SPRING_SESSION` WRITE;
/*!40000 ALTER TABLE `SPRING_SESSION` DISABLE KEYS */;
/*!40000 ALTER TABLE `SPRING_SESSION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SPRING_SESSION_ATTRIBUTES`
--

DROP TABLE IF EXISTS `SPRING_SESSION_ATTRIBUTES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SPRING_SESSION_ATTRIBUTES` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL,
  PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `SPRING_SESSION` (`PRIMARY_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SPRING_SESSION_ATTRIBUTES`
--

LOCK TABLES `SPRING_SESSION_ATTRIBUTES` WRITE;
/*!40000 ALTER TABLE `SPRING_SESSION_ATTRIBUTES` DISABLE KEYS */;
/*!40000 ALTER TABLE `SPRING_SESSION_ATTRIBUTES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `class_id` int NOT NULL COMMENT '教学班级id',
  `name` varchar(32) NOT NULL COMMENT '教学班级名称',
  `people_count` int NOT NULL COMMENT '班级人数',
  `semester_id` int NOT NULL COMMENT '学期id',
  `course_id` int NOT NULL COMMENT '课程id',
  `teacher_id` int NOT NULL COMMENT '教师id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `class_id` (`class_id`),
  KEY `semester_id` (`semester_id`),
  KEY `course_id` (`course_id`),
  KEY `teacher_id` (`teacher_id`),
  CONSTRAINT `class_ibfk_1` FOREIGN KEY (`semester_id`) REFERENCES `semester` (`semester_id`),
  CONSTRAINT `class_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
  CONSTRAINT `class_ibfk_3` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='教学班级表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES (1,400001,'高数上一班',50,242501,1001,200001),(2,400002,'高数下一班',50,242501,1002,200002),(3,400003,'大物一班',50,242501,1003,200003),(4,400004,'模电一班',50,242501,1004,200003),(6,400005,'111',10,232401,1001,200001);
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `course_id` int NOT NULL COMMENT '课程id',
  `name` varchar(32) NOT NULL COMMENT '课程名称',
  `start_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
  `end_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '结束时间',
  `status` varchar(16) NOT NULL DEFAULT 'disable' COMMENT '状态',
  `semester_id` int NOT NULL COMMENT '学期id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `course_id` (`course_id`),
  KEY `course_semester_FK` (`semester_id`),
  CONSTRAINT `course_semester_FK` FOREIGN KEY (`semester_id`) REFERENCES `semester` (`semester_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,1001,'高等数学上','2024-09-01 00:00:00','2025-02-16 23:39:40','disable',232401),(2,1002,'高等数学下','2024-09-01 00:00:00','2025-02-16 23:39:40','disable',232401),(3,1003,'大学物理','2024-09-01 00:00:00','2025-02-16 23:39:40','disable',232401),(4,1004,'模拟电路','2024-09-01 00:00:00','2025-02-16 23:39:40','disable',232401),(7,1005,'实习','2026-09-01 00:00:00','2027-02-16 23:39:40','disable',232401),(8,1006,'进行1','2024-09-01 00:00:00','2025-03-22 23:39:40','disable',242502),(9,1007,'进行2','2024-09-01 00:00:00','2025-03-22 23:39:40','disable',242502),(11,8,'test','2025-02-18 19:27:03','2025-04-18 19:27:03','disable',232401);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `current_semester`
--

DROP TABLE IF EXISTS `current_semester`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `current_semester` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `semester_id` int NOT NULL COMMENT '学期id',
  PRIMARY KEY (`id`),
  KEY `current_semester_semester_FK` (`semester_id`),
  CONSTRAINT `current_semester_semester_FK` FOREIGN KEY (`semester_id`) REFERENCES `semester` (`semester_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `current_semester`
--

LOCK TABLES `current_semester` WRITE;
/*!40000 ALTER TABLE `current_semester` DISABLE KEYS */;
INSERT INTO `current_semester` VALUES (1,232402);
/*!40000 ALTER TABLE `current_semester` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `job_id` int NOT NULL COMMENT '作业id',
  `title` varchar(64) NOT NULL COMMENT '标题',
  `content` varchar(256) DEFAULT NULL COMMENT '内容',
  `course_id` int NOT NULL COMMENT '课程id',
  `class_id` int NOT NULL COMMENT '教学班级id',
  `status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'enable' COMMENT '状态 enable | disable',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `job_id` (`job_id`),
  KEY `course_id` (`course_id`),
  KEY `class_id` (`class_id`),
  CONSTRAINT `job_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
  CONSTRAINT `job_ibfk_2` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='作业表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (1,1,'test','test',1001,400001,'created','2024-09-01 00:00:00','2025-09-01 00:00:00'),(5,2,'高数第一次作业','完成第一章试卷',1002,400002,'enable','2025-02-18 06:53:44','2025-03-18 06:53:44');
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semester`
--

DROP TABLE IF EXISTS `semester`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `semester` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `semester_id` int NOT NULL COMMENT '学期id',
  `name` varchar(32) NOT NULL COMMENT '学期名称',
  `start_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
  `end_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '结束时间',
  `status` varchar(16) NOT NULL DEFAULT 'disable' COMMENT '状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `semester_id` (`semester_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学期表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semester`
--

LOCK TABLES `semester` WRITE;
/*!40000 ALTER TABLE `semester` DISABLE KEYS */;
INSERT INTO `semester` VALUES (1,232401,'2324学年第一学期','2023-09-01 00:00:00','2024-02-04 00:00:00','disable'),(2,232402,'2324学年第二学期','2024-02-04 00:00:00','2024-09-01 00:00:00','disable'),(3,242501,'2425学年第一学期','2024-09-01 00:00:00','2025-02-04 00:00:00','disable'),(4,242502,'2425学年第二学期','2025-02-04 00:00:00','2025-09-01 00:00:00','disable');
/*!40000 ALTER TABLE `semester` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `student_id` int NOT NULL COMMENT '学号',
  `name` varchar(16) NOT NULL COMMENT '学生名称',
  `sex` varchar(4) NOT NULL COMMENT '性别',
  `phone_number` varchar(16) NOT NULL COMMENT '电话号码',
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '邮箱地址',
  `administrative_class` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '行政班级',
  PRIMARY KEY (`id`),
  UNIQUE KEY `student_id` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,300001,'刘七','男','111','test@csu.edu.cn','计科2206'),(2,300002,'张三','男','17818801224','2647461091@qq.com','计科2201'),(79,300003,'钟俊威','男','17818801224','2647461091@qq.com','计科2201'),(80,300004,'张俊琛','男','18570382437','1750404601@qq.com','计科2203'),(81,300005,'马宏琨','男','18075573568','2171513604@qq.com','计科2203'),(82,300006,'胡展鸣','男','13600136044','3502023457@qq.com','计科2204'),(83,300007,'王豪毅','男','13500135045','2402898955@qq.com','计科2202');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_class`
--

DROP TABLE IF EXISTS `student_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_class` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `student_id` int NOT NULL COMMENT '学生id',
  `class_id` int NOT NULL COMMENT '班级id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `student_class` (`student_id`,`class_id`),
  KEY `student_id` (`student_id`),
  KEY `class_id` (`class_id`),
  CONSTRAINT `student_class_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
  CONSTRAINT `student_class_ibfk_2` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生选课班级表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_class`
--

LOCK TABLES `student_class` WRITE;
/*!40000 ALTER TABLE `student_class` DISABLE KEYS */;
INSERT INTO `student_class` VALUES (77,300003,400002),(78,300004,400002),(79,300005,400002),(80,300006,400002),(81,300007,400002);
/*!40000 ALTER TABLE `student_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_course`
--

DROP TABLE IF EXISTS `student_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_course` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `student_id` int NOT NULL COMMENT '学生id',
  `course_id` int NOT NULL COMMENT '课程id',
  PRIMARY KEY (`id`),
  KEY `student_id` (`student_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `student_course_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
  CONSTRAINT `student_course_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生选课表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_course`
--

LOCK TABLES `student_course` WRITE;
/*!40000 ALTER TABLE `student_course` DISABLE KEYS */;
INSERT INTO `student_course` VALUES (1,300001,1001),(2,300002,1001);
/*!40000 ALTER TABLE `student_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_job`
--

DROP TABLE IF EXISTS `student_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_job` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `job_id` int NOT NULL COMMENT '作业id',
  `student_id` int NOT NULL COMMENT '学生id',
  `status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'created' COMMENT '状态 created | submitted | stop',
  PRIMARY KEY (`id`),
  KEY `job_id` (`job_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `student_job_ibfk_1` FOREIGN KEY (`job_id`) REFERENCES `job` (`job_id`),
  CONSTRAINT `student_job_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生作业表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_job`
--

LOCK TABLES `student_job` WRITE;
/*!40000 ALTER TABLE `student_job` DISABLE KEYS */;
INSERT INTO `student_job` VALUES (20,2,300003,'submitted'),(21,2,300004,'submitted'),(22,2,300005,'submitted'),(23,2,300006,'created'),(24,2,300007,'created');
/*!40000 ALTER TABLE `student_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `teacher_id` int NOT NULL COMMENT '教师id',
  `name` varchar(32) NOT NULL COMMENT '教师名称',
  `email` varchar(32) NOT NULL COMMENT '电子邮箱地址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `teacher_id` (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='教师表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1,200001,'abc',''),(2,200002,'abd',''),(3,200003,'苏明宇','sumingyu@qq.com'),(34,200004,'赵悦','zhaoyue@qq.com'),(35,200005,'江南','jiangnan@qq.com'),(36,200006,'陈逸飞','chenyifei@qq.com'),(37,200007,'周星','zhouxing@qq.com'),(38,200008,'王思瑶','wangsiyao@qq.com'),(39,200009,'李梦琪','limengqi@qq.com'),(40,200010,'张逸尘','zhangyichen@qq.com'),(41,200011,'刘宇辰','liuyuchen@qq.com'),(42,200012,'吴星宇','wuxingyu@qq.com'),(43,200013,'赵悦琳','zhaoyuelin@qq.com'),(44,200014,'钱逸风','qianyifeng@qq.com'),(45,200015,'孙悦','sunyue@qq.com'),(46,200016,'李思远','lisiyuan@qq.com'),(47,200017,'周逸飞','zhouyifei@qq.com'),(48,200018,'吴宇航','wuyuhang@qq.com'),(49,200019,'陈悦瑶','chenyueyao@qq.com'),(50,200020,'王梦琪','wangmengqi@qq.com'),(51,200021,'赵思远','zhaosiyuan@qq.com'),(52,200022,'欧阳逸飞','ouyangyifei@qq.com'),(53,200023,'陆星瑶','luxingyao@qq.com'),(54,200024,'秦逸轩','qinyixuan@qq.com'),(55,200025,'江逸飞','jiangyifei@qq.com'),(56,200026,'范思远','fansiyuan@qq.com'),(57,200027,'郭梦琪','guomengqi@qq.com'),(58,200028,'雷宇辰','leiyuchen@qq.com'),(59,200029,'钟悦琳','zhongyuelin@qq.com'),(60,200030,'龙逸风','longyifeng@qq.com'),(61,200031,'伍星宇','wuxingyu@qq.com'),(62,200032,'test','test@qq.com');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` int NOT NULL COMMENT '用户id',
  `password` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '123456' COMMENT '密码',
  `type` varchar(8) NOT NULL COMMENT '类型 student | admin | teacher',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=148 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户登录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,100001,'123456','admin'),(2,100002,'222','student'),(125,200032,'123456','teacher'),(147,300004,'123456','student');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-20 13:30:52
