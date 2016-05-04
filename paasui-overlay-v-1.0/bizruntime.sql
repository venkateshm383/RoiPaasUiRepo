-- MySQL dump 10.13  Distrib 5.5.47, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: bizruntime
-- ------------------------------------------------------
-- Server version	5.5.47-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `COURSE_DETAILS`
--

DROP TABLE IF EXISTS `COURSE_DETAILS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `COURSE_DETAILS` (
  `id` int(11) NOT NULL DEFAULT '0',
  `course_name` varchar(30) DEFAULT NULL,
  `fee` int(11) DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `COURSE_DETAILS`
--

LOCK TABLES `COURSE_DETAILS` WRITE;
/*!40000 ALTER TABLE `COURSE_DETAILS` DISABLE KEYS */;
INSERT INTO `COURSE_DETAILS` VALUES (101,'NET',499,4),(202,'java',600,0),(222,'was',3000,0),(1011,'Physics',400,4),(1104,'Hadoop',7000,7),(1105,'Selenium',5000,2),(1106,'sql server',5000,2),(1107,'ASP',55000,4),(1108,'Oracle',55000,4),(3939,'webservice',5000,5),(4402,'jumla',400,4),(34000,'java',444,4);
/*!40000 ALTER TABLE `COURSE_DETAILS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `STUDENT_DETAILS`
--

DROP TABLE IF EXISTS `STUDENT_DETAILS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `STUDENT_DETAILS` (
  `id` int(11) NOT NULL DEFAULT '0',
  `student_name` varchar(20) DEFAULT NULL,
  `course_name` varchar(20) DEFAULT NULL,
  `fee` int(11) DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `STUDENT_DETAILS`
--

LOCK TABLES `STUDENT_DETAILS` WRITE;
/*!40000 ALTER TABLE `STUDENT_DETAILS` DISABLE KEYS */;
INSERT INTO `STUDENT_DETAILS` VALUES (0,'Sitesh','java',3000,5),(89,'jitu','db2',700,4),(1111,'MALIKKK','ORACLE',22222,8),(2222,'Sachin','java',300,4),(8989,'jitu','db2',700,4),(89892,'jitu','db2',700,4);
/*!40000 ALTER TABLE `STUDENT_DETAILS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER_DETAILS`
--

DROP TABLE IF EXISTS `USER_DETAILS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER_DETAILS` (
  `user_name` varchar(20) NOT NULL DEFAULT '',
  `password` varchar(20) DEFAULT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `state` varchar(15) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER_DETAILS`
--

LOCK TABLES `USER_DETAILS` WRITE;
/*!40000 ALTER TABLE `USER_DETAILS` DISABLE KEYS */;
INSERT INTO `USER_DETAILS` VALUES ('biz','biz123','Bizruntime','runtime','Karnataka','karnataka'),('jitu','jitu123','jitu','barik','Karnataka','bangalore'),('ram','ram123','RamChandra','dd','Odisha','bam'),('ritu','ritu','Rity','malini','kerala','thiru');
/*!40000 ALTER TABLE `USER_DETAILS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER_LOGIN`
--

DROP TABLE IF EXISTS `USER_LOGIN`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER_LOGIN` (
  `UNAME` varchar(20) NOT NULL DEFAULT '',
  `PASS` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`UNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER_LOGIN`
--

LOCK TABLES `USER_LOGIN` WRITE;
/*!40000 ALTER TABLE `USER_LOGIN` DISABLE KEYS */;
INSERT INTO `USER_LOGIN` VALUES ('biz','biz123'),('bizruntime','bizruntime@123'),('jitu','jitu123'),('ram','ram123'),('ritu','ritu');
/*!40000 ALTER TABLE `USER_LOGIN` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acl`
--

DROP TABLE IF EXISTS `acl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acl` (
  `action` varchar(25) DEFAULT NULL,
  `sourceip` varchar(10) DEFAULT NULL,
  `destip` varchar(10) DEFAULT NULL,
  `aclname` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acl`
--

LOCK TABLES `acl` WRITE;
/*!40000 ALTER TABLE `acl` DISABLE KEYS */;
INSERT INTO `acl` VALUES ('deny','1','2','re'),('pass','172.0.0.2','172.0.0.4','networkRule'),('pass','173.16.1.2','10.0.0.6','networkRule1'),('deny','10.0.0.6','172.16.1.2','networkRule2'),('pass','172.0.0.2','172.16.1.2','networkRule3'),('pass','172.1.1.2','172.1.1.3','Acl-Pass-01');
/*!40000 ALTER TABLE `acl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `addService`
--

DROP TABLE IF EXISTS `addService`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `addService` (
  `serviceName` varchar(25) NOT NULL DEFAULT '',
  `type` varchar(50) DEFAULT NULL,
  `applicantionName` varchar(50) DEFAULT NULL,
  `imageRegistry` varchar(50) DEFAULT NULL,
  `imageRepository` varchar(50) DEFAULT NULL,
  `tag` varchar(50) DEFAULT NULL,
  `run` varchar(50) DEFAULT NULL,
  `hostname` varchar(50) DEFAULT NULL,
  `typename` varchar(25) DEFAULT NULL,
  `envirnament` varchar(25) DEFAULT NULL,
  `envpath` varchar(25) DEFAULT NULL,
  `envinterval` varchar(25) DEFAULT NULL,
  `envtimeout` varchar(25) DEFAULT NULL,
  `envthresold` varchar(25) DEFAULT NULL,
  `envignore` varchar(25) DEFAULT NULL,
  `volume` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`serviceName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addService`
--

LOCK TABLES `addService` WRITE;
/*!40000 ALTER TABLE `addService` DISABLE KEYS */;
INSERT INTO `addService` VALUES ('fuse_apps','500','fuse_apps','vikashsingh005/pass','vikashsingh005/pass','jbossfuse','/opt/fuse/bin/start & sleep 4000','localhost','TCP','HTTP','/',NULL,NULL,NULL,NULL,NULL),('fuse_cas','500','fuse_cas','vikashsingh005/pass','vikashsingh005/pass','jbossfuse','/opt/fuse/bin/start & sleep 4000','localhost','NONE','HTTP','/',NULL,NULL,NULL,NULL,NULL),('fuse_demo','500','fuse_demo','vikashsingh005/pass','vikashsingh005/pass','jbossfuse','/opt/fuse/bin/start & sleep 4000','localhost','TCP','HTTP','/',NULL,NULL,NULL,NULL,'/mnt:/mnt'),('fuse_onPass','500','fuse_onPass','vikashsingh005/pass','vikashsingh005/pass','jbossfuse','/opt/fuse/bin/start & sleep 4000','localhost','HTTP','HTTP','/',NULL,NULL,NULL,NULL,NULL),('fuse_qa','500','fuse_demo','vikashsingh005/pass','vikashsingh005/pass','jbossfuse','/opt/fuse/bin/start & sleep 4000','localhost','TCP','HTTP','/',NULL,NULL,NULL,NULL,'/mnt:/mnt'),('jbossfuse_demo','500','jbossfuse_demo','vikashsingh005/pass','vikashsingh005/pass','jbossfuse','/opt/fuse/bin/start & sleep 4000','localhost','TCP','HTTP','/',NULL,NULL,NULL,NULL,'/mnt:/mnt/');
/*!40000 ALTER TABLE `addService` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `applicantUser`
--

DROP TABLE IF EXISTS `applicantUser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `applicantUser` (
  `applicantName` varchar(25) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicantUser`
--

LOCK TABLES `applicantUser` WRITE;
/*!40000 ALTER TABLE `applicantUser` DISABLE KEYS */;
INSERT INTO `applicantUser` VALUES ('jbossfuse','jbossfuse'),('fuse_demo','fuse_demo');
/*!40000 ALTER TABLE `applicantUser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appsummary`
--

DROP TABLE IF EXISTS `appsummary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appsummary` (
  `applicantName` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `imageRegistry` varchar(50) DEFAULT NULL,
  `imageRepository` varchar(50) DEFAULT NULL,
  `tag` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appsummary`
--

LOCK TABLES `appsummary` WRITE;
/*!40000 ALTER TABLE `appsummary` DISABLE KEYS */;
INSERT INTO `appsummary` VALUES ('dev-2','dev-2','docker.io','guysyml/private','27102015'),('dev-03','dev-03','guysyml/private','guysyml/private','appache2');
/*!40000 ALTER TABLE `appsummary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bizruntime`
--

DROP TABLE IF EXISTS `bizruntime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bizruntime` (
  `name` varchar(30) DEFAULT NULL,
  `email` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bizruntime`
--

LOCK TABLES `bizruntime` WRITE;
/*!40000 ALTER TABLE `bizruntime` DISABLE KEYS */;
INSERT INTO `bizruntime` VALUES ('sita@gmail.com','ram');
/*!40000 ALTER TABLE `bizruntime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `CATEGORY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESC` varchar(255) NOT NULL,
  `NAME` varchar(10) NOT NULL,
  PRIMARY KEY (`CATEGORY_ID`),
  UNIQUE KEY `CATEGORY_ID` (`CATEGORY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (3,'INVESTMENT COMPANY','INVESTMENT'),(4,'CONSUMER COMPANY','CONSUMER');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `childnodedata`
--

DROP TABLE IF EXISTS `childnodedata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `childnodedata` (
  `user_id` double DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `id` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `childnodedata_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `usernodedata` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `childnodedata`
--

LOCK TABLES `childnodedata` WRITE;
/*!40000 ALTER TABLE `childnodedata` DISABLE KEYS */;
INSERT INTO `childnodedata` VALUES (111,'aaa',1),(111,'bbb',2),(111,'ccc',3),(222,'xxx',4),(222,'yyy',5),(222,'zzz',6);
/*!40000 ALTER TABLE `childnodedata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cloud_providers`
--

DROP TABLE IF EXISTS `cloud_providers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cloud_providers` (
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `private_cloud` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `default_region` varchar(255) DEFAULT NULL,
  `account_id` int(11) DEFAULT NULL,
  `external_id` varchar(255) DEFAULT NULL,
  `role_arn` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cloud_providers`
--

LOCK TABLES `cloud_providers` WRITE;
/*!40000 ALTER TABLE `cloud_providers` DISABLE KEYS */;
INSERT INTO `cloud_providers` VALUES ('binod','Amazon Web Services',NULL,'sasa','us-west-2',0,NULL,'aA'),('cxzc','Amazon Web Services',NULL,'czcz','us-west-1',0,NULL,'czxc');
/*!40000 ALTER TABLE `cloud_providers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `container_types`
--

DROP TABLE IF EXISTS `container_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `container_types` (
  `name` varchar(255) DEFAULT NULL,
  `cpu_shares` int(11) DEFAULT NULL,
  `memory` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `container_types`
--

LOCK TABLES `container_types` WRITE;
/*!40000 ALTER TABLE `container_types` DISABLE KEYS */;
INSERT INTO `container_types` VALUES ('binod',1,0,'this is binod'),('paul',1,0,'THIS IS PAUL');
/*!40000 ALTER TABLE `container_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emp`
--

DROP TABLE IF EXISTS `emp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emp` (
  `empno` int(11) DEFAULT NULL,
  `empname` varchar(20) DEFAULT NULL,
  `empaddr` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp`
--

LOCK TABLES `emp` WRITE;
/*!40000 ALTER TABLE `emp` DISABLE KEYS */;
INSERT INTO `emp` VALUES (100,'scott','bb'),(200,'mozila','us'),(101,'miller','do'),(121,'ram','sq'),(122,'sri ram','1'),(1221,'jay ram','2');
/*!40000 ALTER TABLE `emp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee2`
--

DROP TABLE IF EXISTS `employee2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee2` (
  `employee_id` int(11) NOT NULL DEFAULT '0',
  `employee_name` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee2`
--

LOCK TABLES `employee2` WRITE;
/*!40000 ALTER TABLE `employee2` DISABLE KEYS */;
INSERT INTO `employee2` VALUES (101,'Akhandalmani Malik'),(102,'Sri Ram'),(106,'sam'),(107,'alent'),(104,'stephen');
/*!40000 ALTER TABLE `employee2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `envirnament`
--

DROP TABLE IF EXISTS `envirnament`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `envirnament` (
  `containername` varchar(25) DEFAULT NULL,
  `service` varchar(50) DEFAULT NULL,
  `tag` varchar(20) DEFAULT NULL,
  `host` varchar(20) DEFAULT NULL,
  `ipadress` varchar(20) DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `envirnament`
--

LOCK TABLES `envirnament` WRITE;
/*!40000 ALTER TABLE `envirnament` DISABLE KEYS */;
INSERT INTO `envirnament` VALUES ('dev-01','devservice','latest','1.1.1.1','1.0.0.0','running'),('dev-02','devservice','latest','1.1.1.1','1.0.0.0','running'),('dev-02','devservice','latest','1.1.1.1','1.0.0.0','running'),('w','tomcat','latest','w','w','w'),('w','jbossFuse','jbossfuse','w','w','w'),('w','sanat-demo','jbossfuse','w','w','w'),('w','sanat-demo','jbossfuse','w','w','w'),('w','sanat-demo','jbossfuse','w','w','w'),('dev.fuse','fusejboss','jbossfuse','192.168.1.219','w','running'),('dev.fuse','jbossfuse_demo','jbossfuse','192.168.1.219','w','running'),('dev.fuse','fuse_demo','jbossfuse','192.168.1.219','w','running'),('dev.fuse','fuse_apps','jbossfuse','192.168.1.219','w','running'),('dev.fuse','fuse_apps','jbossfuse','192.168.1.219','w','running'),('dev.fuse','fuse_cas','jbossfuse','192.168.1.219','w','running'),('dev.fuse','jbossfuse_demo','jbossfuse','192.168.1.219','w','running');
/*!40000 ALTER TABLE `envirnament` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `environment_types`
--

DROP TABLE IF EXISTS `environment_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `environment_types` (
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `accept_tag` varchar(255) DEFAULT NULL,
  `promote_tag` varchar(255) DEFAULT NULL,
  `action` varchar(255) DEFAULT NULL,
  `restart_interval` int(11) DEFAULT NULL,
  `quiet_period` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `environment_types`
--

LOCK TABLES `environment_types` WRITE;
/*!40000 ALTER TABLE `environment_types` DISABLE KEYS */;
INSERT INTO `environment_types` VALUES ('binod','this is binod','dkfhk','fhjkdfd','notify',1,1),('paul','kjdflkdjs','dfs','fdsf','notify',2,2);
/*!40000 ALTER TABLE `environment_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `environment_variable`
--

DROP TABLE IF EXISTS `environment_variable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `environment_variable` (
  `envkey` varchar(25) DEFAULT NULL,
  `envvalue` varchar(25) DEFAULT NULL,
  `serviceName` varchar(25) DEFAULT NULL,
  KEY `environment_variable_ibfk_1` (`serviceName`),
  CONSTRAINT `environment_variable_ibfk_1` FOREIGN KEY (`serviceName`) REFERENCES `addService` (`serviceName`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `environment_variable`
--

LOCK TABLES `environment_variable` WRITE;
/*!40000 ALTER TABLE `environment_variable` DISABLE KEYS */;
/*!40000 ALTER TABLE `environment_variable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `firewall_inbounds`
--

DROP TABLE IF EXISTS `firewall_inbounds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `firewall_inbounds` (
  `in_type` varchar(255) DEFAULT NULL,
  `in_protocol` varchar(255) DEFAULT NULL,
  `in_portrange` int(11) DEFAULT NULL,
  `in_source` varchar(255) DEFAULT NULL,
  `in_ip` varchar(255) DEFAULT NULL,
  `in_name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `firewall_inbounds`
--

LOCK TABLES `firewall_inbounds` WRITE;
/*!40000 ALTER TABLE `firewall_inbounds` DISABLE KEYS */;
INSERT INTO `firewall_inbounds` VALUES ('Https','hhta',80,'192.0.0.1','1.1.1.2',NULL),('Https','hhta',80,'192.0.0.1','1.1.1.1',NULL),('242','2342',2342,'2342','2342',NULL),('Https','hhta',80,'192.0.0.1','1.1.1.1','java'),('Https','hhta',80,'192.0.0.1','1.1.1.1','java1'),('HTTP','TCP',80,'12','10.1.1.1','fire-inbound'),('HTTP','TCP',80,NULL,'1.1.1.0','flowin'),('HTTP','ICMP',80,NULL,'10.0.0.4','11');
/*!40000 ALTER TABLE `firewall_inbounds` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `firewall_outbounds`
--

DROP TABLE IF EXISTS `firewall_outbounds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `firewall_outbounds` (
  `out_type` varchar(255) DEFAULT NULL,
  `out_protocol` varchar(255) DEFAULT NULL,
  `out_portrange` int(11) DEFAULT NULL,
  `out_source` varchar(255) DEFAULT NULL,
  `out_ip` varchar(255) DEFAULT NULL,
  `out_name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `firewall_outbounds`
--

LOCK TABLES `firewall_outbounds` WRITE;
/*!40000 ALTER TABLE `firewall_outbounds` DISABLE KEYS */;
INSERT INTO `firewall_outbounds` VALUES ('HTTPS','TCP',80,'45','10.0.0.5','firewalloutbound'),('HTTP','TCP',50505,'45','10.0.0.5','firewalloutbound2'),('All TCP','TCP',90,'Anywhere','10.0.0.10','firewall2'),('HTTP','TCP',80,'Custome IP','10.0.0.100','firewall3'),('HTTP','TCP',80,'Custome IP','10.0.0.2','firewall4');
/*!40000 ALTER TABLE `firewall_outbounds` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `host_scaling_policy`
--

DROP TABLE IF EXISTS `host_scaling_policy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `host_scaling_policy` (
  `name` varchar(255) DEFAULT NULL,
  `host_groups` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `host_scaling_policy`
--

LOCK TABLES `host_scaling_policy` WRITE;
/*!40000 ALTER TABLE `host_scaling_policy` DISABLE KEYS */;
INSERT INTO `host_scaling_policy` VALUES ('binod','binod group'),('poul','poul  group');
/*!40000 ALTER TABLE `host_scaling_policy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image_registry`
--

DROP TABLE IF EXISTS `image_registry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `image_registry` (
  `name` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  `private_cloud` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image_registry`
--

LOCK TABLES `image_registry` WRITE;
/*!40000 ALTER TABLE `image_registry` DISABLE KEYS */;
INSERT INTO `image_registry` VALUES ('vika','index.docker.io','A','pc1','vikashsingh005','bizruntime@123'),('vikashsingh005','index.docker.io','A','pc1','vikashsingh005','bizruntime@123'),('vikashsingh005/pass','index.docker.io','A','pc1','vikashsingh005','bizruntime@123'),('vikashsingh005','index.docker.io','A','pc1','vikashsingh005','bizruntime@123'),('Demo-Images','index.docker.io','A','pc1','vikashsingh005','bizruntime@123');
/*!40000 ALTER TABLE `image_registry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `network_policy`
--

DROP TABLE IF EXISTS `network_policy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `network_policy` (
  `portname` varchar(25) DEFAULT NULL,
  `porttype` varchar(25) DEFAULT NULL,
  `hostport` varchar(25) DEFAULT NULL,
  `containerport` varchar(25) DEFAULT NULL,
  `serviceName` varchar(25) DEFAULT NULL,
  KEY `network_policy_ibfk_1` (`serviceName`),
  CONSTRAINT `network_policy_ibfk_1` FOREIGN KEY (`serviceName`) REFERENCES `addService` (`serviceName`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `network_policy`
--

LOCK TABLES `network_policy` WRITE;
/*!40000 ALTER TABLE `network_policy` DISABLE KEYS */;
/*!40000 ALTER TABLE `network_policy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parent`
--

DROP TABLE IF EXISTS `parent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parent` (
  `id` varchar(5) NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parent`
--

LOCK TABLES `parent` WRITE;
/*!40000 ALTER TABLE `parent` DISABLE KEYS */;
/*!40000 ALTER TABLE `parent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person_details`
--

DROP TABLE IF EXISTS `person_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person_details` (
  `person_id` int(11) NOT NULL,
  `person_Name` varchar(255) DEFAULT NULL,
  `person_Country` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person_details`
--

LOCK TABLES `person_details` WRITE;
/*!40000 ALTER TABLE `person_details` DISABLE KEYS */;
INSERT INTO `person_details` VALUES (0,'Andrew','Russia'),(111,'Thea','Queen');
/*!40000 ALTER TABLE `person_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `register`
--

DROP TABLE IF EXISTS `register`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `register` (
  `company_name` varchar(255) DEFAULT NULL,
  `company_address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `register`
--

LOCK TABLES `register` WRITE;
/*!40000 ALTER TABLE `register` DISABLE KEYS */;
INSERT INTO `register` VALUES ('bizruntime','sarjapur','binodkumar@live.in','12345678Aa'),('bizruntime','sarjapur','umar@bizruntime.com','12345678Aa'),('bizruntime','sarjapur','alok.ranjan@bizruntime.com','12345678aA'),('bizruntime','sarjapur','binod@gmail.com','12345678Aa'),('biz-01','chennai','viksonai.singh@gmail.com','BizRuntime@123'),('biz-02','banglore','jpsingh182001@gmail.com','BizRuntime@123'),('biz-021','chennai','vikash@syml.ca','BizRuntime@123'),('mj','mj','mj@mj.com','Manoj@123'),('biz-023','chennai','guy@syml.ca','BizRuntime@123'),('sdf','sf','jk@jk.com','Manoj@123'),('biz-011','mumbai','vi@gmail.com','BizRuntime@123'),('biz-0212','banglore','vik@gmail.com','Bizuntime@123'),('bizruntime-01','banglore','v@gmail.com','BizRuntime@123'),('mjcompnay','raj','mj1@mj1.com','Manoj@123'),('bizruntime56','chennai','viksonai.singh@gmai1l.com','BizRuntime@123'),('bizruntime-02','banglore','vs@gmail.com','BizRuntime@123'),('bizruntime-03','banglore','vm@gmail.com','BizRuntime@123'),('bizruntime-09','banglore','s@sahu.com','BizRuntime@123'),('bizruntime123','bizruntime123','mj@mj.com12','Manoj@123'),('biz-03','chennai','viksonai.singh123@gmail.com','BizRuntime@123'),('biz-04','chennai','vikash.singhs@bizruntime.com','BizRuntime@123'),('biz','biz','mp@mp.com','Manoj@123'),('kk','kk','kk@kk.com','Manoj@123'),('bizruntime-02','banglore','s@gmail.com','BizRuntime@123'),('bizruntime','bizruntime','sanat.meti@bizruntime.com','Bizruntime@123');
/*!40000 ALTER TABLE `register` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource_selection`
--

DROP TABLE IF EXISTS `resource_selection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource_selection` (
  `rank` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `container_types` varchar(255) DEFAULT NULL,
  `environment_types` varchar(255) DEFAULT NULL,
  `host_groups` varchar(255) DEFAULT NULL,
  `placement` varchar(255) DEFAULT NULL,
  `minimum` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource_selection`
--

LOCK TABLES `resource_selection` WRITE;
/*!40000 ALTER TABLE `resource_selection` DISABLE KEYS */;
INSERT INTO `resource_selection` VALUES (1,'binod','mycontainer','sass','saas','availableMemory',3),(2,'paul','sds','dsa','ds','availableMemory',3);
/*!40000 ALTER TABLE `resource_selection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route`
--

DROP TABLE IF EXISTS `route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `route` (
  `typename` varchar(25) DEFAULT NULL,
  `portname` varchar(25) DEFAULT NULL,
  `routetype` varchar(25) DEFAULT NULL,
  `target` varchar(25) DEFAULT NULL,
  `serviceName` varchar(25) DEFAULT NULL,
  KEY `route_ibfk_1` (`serviceName`),
  CONSTRAINT `route_ibfk_1` FOREIGN KEY (`serviceName`) REFERENCES `addService` (`serviceName`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
/*!40000 ALTER TABLE `route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scaling_and_recovery`
--

DROP TABLE IF EXISTS `scaling_and_recovery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scaling_and_recovery` (
  `application` varchar(255) DEFAULT NULL,
  `services` varchar(255) DEFAULT NULL,
  `environment_types` varchar(255) DEFAULT NULL,
  `desired_count` varchar(255) DEFAULT NULL,
  `auto_recovery` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scaling_and_recovery`
--

LOCK TABLES `scaling_and_recovery` WRITE;
/*!40000 ALTER TABLE `scaling_and_recovery` DISABLE KEYS */;
INSERT INTO `scaling_and_recovery` VALUES ('binod','binod','binod','binod','binod'),('pol','pol','pol','pol','pol'),('santanu','santanu','santanu','santanu','santanu');
/*!40000 ALTER TABLE `scaling_and_recovery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_affinities`
--

DROP TABLE IF EXISTS `service_affinities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `service_affinities` (
  `application` varchar(255) DEFAULT NULL,
  `services` varchar(255) DEFAULT NULL,
  `environment_types` varchar(255) DEFAULT NULL,
  `affinity` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_affinities`
--

LOCK TABLES `service_affinities` WRITE;
/*!40000 ALTER TABLE `service_affinities` DISABLE KEYS */;
INSERT INTO `service_affinities` VALUES ('paul','paul','paul','paul');
/*!40000 ALTER TABLE `service_affinities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock` (
  `STOCK_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STOCK_CODE` varchar(10) NOT NULL,
  `STOCK_NAME` varchar(20) NOT NULL,
  PRIMARY KEY (`STOCK_ID`),
  UNIQUE KEY `STOCK_ID` (`STOCK_ID`),
  UNIQUE KEY `STOCK_CODE` (`STOCK_CODE`),
  UNIQUE KEY `STOCK_NAME` (`STOCK_NAME`),
  UNIQUE KEY `STOCK_NAME_2` (`STOCK_NAME`),
  UNIQUE KEY `STOCK_CODE_2` (`STOCK_CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (2,'7052','PADINI');
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock_category`
--

DROP TABLE IF EXISTS `stock_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock_category` (
  `STOCK_ID` int(11) NOT NULL,
  `CATEGORY_ID` int(11) NOT NULL,
  PRIMARY KEY (`STOCK_ID`,`CATEGORY_ID`),
  KEY `FK97929007EB260666` (`CATEGORY_ID`),
  KEY `FK9792900752804C0E` (`STOCK_ID`),
  CONSTRAINT `FK9792900752804C0E` FOREIGN KEY (`STOCK_ID`) REFERENCES `stock` (`STOCK_ID`),
  CONSTRAINT `FK97929007EB260666` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `category` (`CATEGORY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_category`
--

LOCK TABLES `stock_category` WRITE;
/*!40000 ALTER TABLE `stock_category` DISABLE KEYS */;
INSERT INTO `stock_category` VALUES (2,3),(2,4);
/*!40000 ALTER TABLE `stock_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storage`
--

DROP TABLE IF EXISTS `storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storage` (
  `servicename` varchar(25) DEFAULT NULL,
  `tag` varchar(20) DEFAULT NULL,
  `volumesize` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storage`
--

LOCK TABLES `storage` WRITE;
/*!40000 ALTER TABLE `storage` DISABLE KEYS */;
INSERT INTO `storage` VALUES ('fuse_qa','jbossfuse','500'),('fuse_qa','jbossfuse','66'),('fuse_qa','jbossfuse','10'),('jbossfuse_demo','jbossfuse','12'),('fuse_demo','jbossfuse','10'),('fuse_qa','jbossfuse','10'),('fuse_demo','jbossfuse','10'),('fuse_demo','jbossfuse','10'),('fuse_demo','jbossfuse','10'),('fuse_demo','jbossfuse','10'),('fuse_demo','jbossfuse','10M');
/*!40000 ALTER TABLE `storage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subnet`
--

DROP TABLE IF EXISTS `subnet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subnet` (
  `vpc_name` varchar(255) DEFAULT NULL,
  `subnet_name` varchar(255) DEFAULT NULL,
  `cidr` varchar(255) DEFAULT NULL,
  `acl` varchar(255) DEFAULT NULL,
  `subnetId` varchar(50) DEFAULT NULL,
  `vpcId` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subnet`
--

LOCK TABLES `subnet` WRITE;
/*!40000 ALTER TABLE `subnet` DISABLE KEYS */;
INSERT INTO `subnet` VALUES ('pass_demo',NULL,'172.16.1.0/24','acl 1','subnet-8e3163b1','vpc-d973affa'),('fuse_demo',NULL,'172.16.1.0/24','acl 1','subnet-f4c7bd83','vpc-83c445ba'),('vpc-demo-01',NULL,'172.16.1.0/24','acl 3','subnet-bff7b3c7','vpc-027890a0');
/*!40000 ALTER TABLE `subnet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usernodedata`
--

DROP TABLE IF EXISTS `usernodedata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usernodedata` (
  `id` double NOT NULL DEFAULT '0',
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usernodedata`
--

LOCK TABLES `usernodedata` WRITE;
/*!40000 ALTER TABLE `usernodedata` DISABLE KEYS */;
INSERT INTO `usernodedata` VALUES (111,'malik'),(222,'vibek');
/*!40000 ALTER TABLE `usernodedata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) DEFAULT NULL,
  `user_name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (102,'Ram'),(103,'Sita'),(104,'Laxman'),(105,'Bajarangi'),(101,'Umar'),(210,'Omm Namah Sibaya');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vpc`
--

DROP TABLE IF EXISTS `vpc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vpc` (
  `vpc_name` varchar(255) DEFAULT NULL,
  `vpc_region` varchar(255) DEFAULT NULL,
  `cidr` varchar(255) DEFAULT NULL,
  `acl` varchar(255) DEFAULT NULL,
  `vpcId` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vpc`
--

LOCK TABLES `vpc` WRITE;
/*!40000 ALTER TABLE `vpc` DISABLE KEYS */;
INSERT INTO `vpc` VALUES ('pass_demo','US','172.16.0.0/24','networkRule','vpc-d973affa'),('fuse_demo','US','172.16.0.0/24','networkRule','vpc-83c445ba'),('vpc-demo-01','US','173.16.0.0/24','networkRule2','vpc-027890a0');
/*!40000 ALTER TABLE `vpc` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-16 16:49:43
