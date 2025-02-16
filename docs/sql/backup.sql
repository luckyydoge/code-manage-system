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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='教学班级表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES (1,400001,'高数上一班',50,242501,1001,200001),(2,400002,'高数下一班',50,242501,1002,200002),(3,400003,'大物一班',50,242501,1003,200003),(4,400004,'模电一班',50,242501,1004,200003);
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `course_id` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,1001,'高等数学上','2024-09-01 00:00:00','2025-01-01 00:00:00','disable'),(2,1002,'高等数学下','2024-09-01 00:00:00','2025-01-01 00:00:00','disable'),(3,1003,'大学物理','2024-09-01 00:00:00','2025-01-01 00:00:00','disable'),(4,1004,'模拟电路','2024-09-01 00:00:00','2025-01-01 00:00:00','disable'),(5,1111,'test','2020-02-02 08:00:00','2021-01-01 08:00:00','disable');
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
  `status` varchar(16) NOT NULL COMMENT '状态 enable | disable',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `job_id` (`job_id`),
  KEY `course_id` (`course_id`),
  KEY `class_id` (`class_id`),
  CONSTRAINT `job_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
  CONSTRAINT `job_ibfk_2` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='作业表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学期表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semester`
--

LOCK TABLES `semester` WRITE;
/*!40000 ALTER TABLE `semester` DISABLE KEYS */;
INSERT INTO `semester` VALUES (1,232401,'2324学年第一学期','2023-09-01 00:00:00','2024-02-04 00:00:00','disable'),(2,232402,'2324学年第二学期','2024-02-04 00:00:00','2024-09-01 00:00:00','disable'),(3,242501,'2425学年第一学期','2024-09-01 00:00:00','2025-02-04 00:00:00','disable'),(4,242502,'2425学年第二学期','2025-02-04 00:00:00','2025-09-01 00:00:00','disable'),(5,262701,'111','1970-01-01 13:37:50','1970-01-01 13:37:50','disable');
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
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,300001,'刘七','男','111','test@csu.edu.cn','计科2206'),(2,300002,'孙八','男','111','test@csu.edu.cn','计科2206'),(3,300003,'刘宇','男','13800138001','liuyu@qq.com','计科2201'),(4,300004,'李思琪','女','13900139002','lisiqi@qq.com','计科2203'),(5,300005,'张逸飞','男','13700137003','zhangyifei@qq.com','计科2202'),(6,300006,'刘悦琳','女','13600136004','liuyuelin@qq.com','计科2204'),(7,300007,'赵星宇','男','13500135005','zhaoxingyu@qq.com','计科2205'),(8,300008,'刘悦琳','女','13400134006','liuyuelin2@qq.com','计科2201'),(9,300009,'孙逸风','男','13300133007','sunyifeng@qq.com','计科2203'),(10,300010,'周思远','女','13200132008','zhousiyuan@qq.com','计科2202'),(11,300011,'吴宇航','男','13100131009','wuyuhang@qq.com','计科2204'),(12,300012,'欧阳悦瑶','女','13000130010','ouyangyueyao@qq.com','计科2205'),(13,300013,'程逸轩','男','18800188011','chengyixuan@qq.com','计科2201'),(14,300014,'徐梦琪','女','18900189012','xumengqi@qq.com','计科2203'),(15,300015,'黄星瑶','女','18700187013','huangxingyao@qq.com','计科2202'),(16,300016,'朱逸飞','男','18600186014','zhuyifei@qq.com','计科2204'),(17,300017,'林思远','女','18500185015','linsiyuan@qq.com','计科2205'),(18,300018,'秦飞扬','男','18200182016','qinfeiyang@qq.com','计科2201'),(19,300019,'杨静怡','女','18300183017','yangjingyi@qq.com','计科2203'),(20,300020,'周宇航','男','18400184018','zhouyuhang@qq.com','计科2202'),(21,300021,'吴思琪','女','18100181019','wusiqi@qq.com','计科2204'),(22,300022,'孙浩然','男','13800138041','sunhaoran@qq.com','计科2202'),(24,300023,'李雨欣','女','13900139042','liyuxin@qq.com','计科2201'),(25,300024,'高天宇','男','13700137043','gaotianyu2@qq.com','计科2203'),(26,300025,'赵梦琪','女','13600136044','zhaomengqi@qq.com','计科2205'),(27,300026,'孙逸飞','男','13500135045','sunyifei@qq.com','计科2204'),(28,300027,'李静怡','女','13400134046','lijingyi2@qq.com','计科2202'),(29,300028,'周宇航','男','13300133047','zhouyuhang2@qq.com','计科2201'),(30,300029,'吴思远','女','13200132048','wusiyuan2@qq.com','计科2203'),(31,300030,'郑逸尘','男','13100131049','zhengyichen@qq.com','计科2205'),(32,300031,'王梦瑶','女','13000130050','wangmengyao@qq.com','计科2204'),(33,300032,'刘逸轩','男','18800188051','liuyixuan@qq.com','计科2202'),(34,300033,'张星宇','男','18900189052','zhangxingyu@qq.com','计科2201'),(35,300034,'黄思琪','女','18700187053','huangsiqi@qq.com','计科2203'),(36,300035,'林逸飞','男','18600186054','linyifei@qq.com','计科2205'),(37,300036,'秦星瑶','女','18500185055','qinxingyao@qq.com','计科2204'),(38,300037,'杨宇航','男','18200182056','yangyuhang@qq.com','计科2202'),(39,300038,'周悦瑶','女','18300183057','zhouyueyao@qq.com','计科2201'),(40,300039,'吴逸尘','男','18400184058','wuyichen@qq.com','计科2203'),(41,300040,'徐梦琪','女','18100181059','xumengqi2@qq.com','计科2205'),(42,300041,'王浩然','男','13800138021','wanghaoran@qq.com','计科2202'),(44,300042,'陈雨欣','女','13900139022','chenyuxin@qq.com','计科2204'),(45,300043,'高天宇','男','13700137023','gaotianyu@qq.com','计科2201'),(46,300044,'赵梦瑶','女','13600136024','zhaomengyao@qq.com','计科2203'),(47,300045,'孙宇航','男','13500135025','sunyuhang@qq.com','计科2205'),(48,300046,'李静怡','女','13400134026','lijingyi@qq.com','计科2202'),(49,300047,'周逸飞','男','13300133027','zhouyifei@qq.com','计科2204'),(50,300048,'吴思远','女','13200132028','wusiyuan@qq.com','计科2201'),(51,300049,'郑飞扬','男','13100131029','zhengfeiyang@qq.com','计科2203'),(52,300050,'王梦琪','女','13000130030','wangmengqi@qq.com','计科2205'),(53,300051,'刘宇航','男','18800188031','liuyuhang@qq.com','计科2202'),(54,300052,'张星瑶','女','18900189032','zhangxingyao@qq.com','计科2204'),(55,300053,'黄逸尘','男','18700187033','huangyichen@qq.com','计科2201'),(56,300054,'林思琪','女','18600186034','linsiqi@qq.com','计科2203'),(57,300055,'秦逸轩','男','18500185035','qinyixuan@qq.com','计科2205'),(58,300056,'杨星宇','男','18200182036','yangxingyu@qq.com','计科2202'),(59,300057,'周悦琳','女','18300183037','zhouyuelin@qq.com','计科2204'),(60,300058,'吴宇航','男','18400184038','wuyuhang2@qq.com','计科2201'),(61,300059,'徐思远','女','18100181039','xusiyuan@qq.com','计科2203'),(62,300060,'韩梦瑶','女','18000180040','hanmengyao@qq.com','计科2205');
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
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生选课班级表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_class`
--

LOCK TABLES `student_class` WRITE;
/*!40000 ALTER TABLE `student_class` DISABLE KEYS */;
INSERT INTO `student_class` VALUES (1,300003,400001),(2,300004,400001),(3,300005,400001),(4,300006,400001),(5,300007,400001),(6,300008,400001),(7,300009,400001),(8,300010,400001),(9,300011,400001),(10,300012,400001),(11,300013,400001),(12,300014,400001),(13,300015,400001),(14,300016,400001),(15,300017,400001),(16,300018,400001),(17,300019,400001),(18,300020,400001),(19,300021,400001),(20,300022,400001),(21,300022,400003),(22,300023,400003),(23,300024,400003),(24,300025,400003),(25,300026,400003),(26,300027,400003),(27,300028,400003),(28,300029,400003),(29,300030,400003),(30,300031,400003),(31,300032,400003),(32,300033,400003),(33,300034,400003),(34,300035,400003),(35,300036,400003),(36,300037,400003),(37,300038,400003),(38,300039,400003),(39,300040,400003),(41,300041,400002),(40,300041,400003),(42,300042,400002),(43,300043,400002),(44,300044,400002),(45,300045,400002),(46,300046,400002),(47,300047,400002),(48,300048,400002),(49,300049,400002),(50,300050,400002),(51,300051,400002),(52,300052,400002),(53,300053,400002),(54,300054,400002),(55,300055,400002),(56,300056,400002),(57,300057,400002),(58,300058,400002),(59,300059,400002),(60,300060,400002);
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
  `status` varchar(16) NOT NULL COMMENT '状态 created | submitted | stop',
  PRIMARY KEY (`id`),
  KEY `job_id` (`job_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `student_job_ibfk_1` FOREIGN KEY (`job_id`) REFERENCES `job` (`job_id`),
  CONSTRAINT `student_job_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生作业表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_job`
--

LOCK TABLES `student_job` WRITE;
/*!40000 ALTER TABLE `student_job` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户登录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,100001,'123456','admin'),(2,100002,'222','student'),(35,200003,'123456','teacher'),(36,200004,'123456','teacher'),(37,200005,'123456','teacher'),(38,200006,'123456','teacher'),(39,200007,'123456','teacher'),(40,200008,'123456','teacher'),(41,200009,'123456','teacher'),(42,200010,'123456','teacher'),(43,200011,'123456','teacher'),(44,200012,'123456','teacher'),(45,200013,'123456','teacher'),(46,200014,'123456','teacher'),(47,200015,'123456','teacher'),(48,200016,'123456','teacher'),(49,200017,'123456','teacher'),(50,200018,'123456','teacher'),(51,200019,'123456','teacher'),(52,200020,'123456','teacher'),(53,200021,'123456','teacher'),(54,200022,'123456','teacher'),(55,200023,'123456','teacher'),(56,200024,'123456','teacher'),(57,200025,'123456','teacher'),(58,200026,'123456','teacher'),(59,200027,'123456','teacher'),(60,200028,'123456','teacher'),(61,200029,'123456','teacher'),(62,200030,'123456','teacher'),(63,200031,'123456','teacher'),(64,200032,'123456','teacher'),(65,300003,'123456','teacher'),(66,300004,'123456','teacher'),(67,300005,'123456','teacher'),(68,300006,'123456','teacher'),(69,300007,'123456','teacher'),(70,300008,'123456','teacher'),(71,300009,'123456','teacher'),(72,300010,'123456','teacher'),(73,300011,'123456','teacher'),(74,300012,'123456','teacher'),(75,300013,'123456','teacher'),(76,300014,'123456','teacher'),(77,300015,'123456','teacher'),(78,300016,'123456','teacher'),(79,300017,'123456','teacher'),(80,300018,'123456','teacher'),(81,300019,'123456','teacher'),(82,300020,'123456','teacher'),(83,300021,'123456','teacher'),(84,300022,'123456','teacher'),(85,300022,'123456','teacher'),(86,300023,'123456','teacher'),(87,300024,'123456','teacher'),(88,300025,'123456','teacher'),(89,300026,'123456','teacher'),(90,300027,'123456','teacher'),(91,300028,'123456','teacher'),(92,300029,'123456','teacher'),(93,300030,'123456','teacher'),(94,300031,'123456','teacher'),(95,300032,'123456','teacher'),(96,300033,'123456','teacher'),(97,300034,'123456','teacher'),(98,300035,'123456','teacher'),(99,300036,'123456','teacher'),(100,300037,'123456','teacher'),(101,300038,'123456','teacher'),(102,300039,'123456','teacher'),(103,300040,'123456','teacher'),(104,300041,'123456','teacher'),(105,300041,'123456','teacher'),(106,300042,'123456','teacher'),(107,300043,'123456','teacher'),(108,300044,'123456','teacher'),(109,300045,'123456','teacher'),(110,300046,'123456','teacher'),(111,300047,'123456','teacher'),(112,300048,'123456','teacher'),(113,300049,'123456','teacher'),(114,300050,'123456','teacher'),(115,300051,'123456','teacher'),(116,300052,'123456','teacher'),(117,300053,'123456','teacher'),(118,300054,'123456','teacher'),(119,300055,'123456','teacher'),(120,300056,'123456','teacher'),(121,300057,'123456','teacher'),(122,300058,'123456','teacher'),(123,300059,'123456','teacher'),(124,300060,'123456','teacher'),(125,200032,'123456','teacher');
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

-- Dump completed on 2025-02-16 17:47:38
