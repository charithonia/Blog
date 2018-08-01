CREATE DATABASE  IF NOT EXISTS `BessieBlogTest` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `BessieBlogTest`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: BessieBlogTest
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `Blog`
--

DROP TABLE IF EXISTS `Blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Blog` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(100) NOT NULL,
  `CreationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ExpirationDate` timestamp NULL DEFAULT NULL,
  `PublicationDate` timestamp NULL DEFAULT NULL,
  `UserId` int(11) NOT NULL,
  `CategoryId` int(11) NOT NULL,
  `Body` text NOT NULL,
  `IsApproved` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`Id`),
  KEY `fk_Blog_UserId` (`UserId`),
  KEY `fk_Blog_CategoryId` (`CategoryId`),
  CONSTRAINT `fk_Blog_CategoryId` FOREIGN KEY (`CategoryId`) REFERENCES `Category` (`Id`),
  CONSTRAINT `fk_Blog_UserId` FOREIGN KEY (`UserId`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Blog`
--

LOCK TABLES `Blog` WRITE;
/*!40000 ALTER TABLE `Blog` DISABLE KEYS */;
INSERT INTO `Blog` VALUES (1,'Blog1','2017-11-12 22:14:48','2018-10-22 14:10:10','2015-10-22 14:10:10',1,1,'ajfkjaf;;asl;ffa;kjd;l',1),(2,'Blog2','2017-11-12 22:14:48','2018-10-22 14:10:10',NULL,3,2,'akfjkdfjaskjf;afa',0),(3,'Blog3','2017-11-12 22:14:48',NULL,'2015-10-22 14:10:10',3,3,'aljfkldajfdj;fjaaf',0),(4,'Blog4','2017-11-12 22:14:48','2015-10-22 14:10:10',NULL,5,4,'ajfd;kdl;a',0),(5,'Blog5','2017-11-12 22:14:48',NULL,NULL,5,5,'adlahfadk',0);
/*!40000 ALTER TABLE `Blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Blog_Hashtag`
--

DROP TABLE IF EXISTS `Blog_Hashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Blog_Hashtag` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `BlogId` int(11) NOT NULL,
  `HashtagId` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `fk_Blog_Hashtag_UNIQUE` (`BlogId`,`HashtagId`),
  KEY `fk_Blog_Hashtag_HashtagId` (`HashtagId`),
  CONSTRAINT `fk_Blog_Hashtag_BlogId` FOREIGN KEY (`BlogId`) REFERENCES `Blog` (`Id`),
  CONSTRAINT `fk_Blog_Hashtag_HashtagId` FOREIGN KEY (`HashtagId`) REFERENCES `Hashtag` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Blog_Hashtag`
--

LOCK TABLES `Blog_Hashtag` WRITE;
/*!40000 ALTER TABLE `Blog_Hashtag` DISABLE KEYS */;
INSERT INTO `Blog_Hashtag` VALUES (1,1,1),(6,1,5),(2,2,2),(7,2,4),(8,3,2),(3,3,3),(9,4,3),(4,4,4),(10,5,1),(5,5,5);
/*!40000 ALTER TABLE `Blog_Hashtag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Category`
--

DROP TABLE IF EXISTS `Category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Category` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Category`
--

LOCK TABLES `Category` WRITE;
/*!40000 ALTER TABLE `Category` DISABLE KEYS */;
INSERT INTO `Category` VALUES (1,'Food'),(2,'Travel'),(3,'Finance'),(4,'Politics'),(5,'Entertainment');
/*!40000 ALTER TABLE `Category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Hashtag`
--

DROP TABLE IF EXISTS `Hashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Hashtag` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Name_UNIQUE` (`Name`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Hashtag`
--

LOCK TABLES `Hashtag` WRITE;
/*!40000 ALTER TABLE `Hashtag` DISABLE KEYS */;
INSERT INTO `Hashtag` VALUES (1,'#idontknowwhathashtagsare'),(4,'#ilovetoblog'),(5,'#nachosrock'),(2,'#theleisurelife'),(3,'#zombieapocalypse');
/*!40000 ALTER TABLE `Hashtag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Image`
--

DROP TABLE IF EXISTS `Image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Image` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Caption` varchar(60) NOT NULL,
  `Image` longblob,
  `AddedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Image`
--

LOCK TABLES `Image` WRITE;
/*!40000 ALTER TABLE `Image` DISABLE KEYS */;
INSERT INTO `Image` VALUES (1,'Nachos',NULL,'2017-11-12 22:14:48'),(2,'Great Escape',NULL,'2017-11-12 22:14:48'),(3,'Flowers',NULL,'2017-11-12 22:14:48'),(4,'Beach Life',NULL,'2017-11-12 22:14:48'),(5,'Bunnies',NULL,'2017-11-12 22:14:48');
/*!40000 ALTER TABLE `Image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Role`
--

DROP TABLE IF EXISTS `Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Role` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role`
--

LOCK TABLES `Role` WRITE;
/*!40000 ALTER TABLE `Role` DISABLE KEYS */;
INSERT INTO `Role` VALUES (1,'Admin'),(2,'Visitor'),(3,'Contributor');
/*!40000 ALTER TABLE `Role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `StaticPage`
--

DROP TABLE IF EXISTS `StaticPage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `StaticPage` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(100) NOT NULL,
  `CreationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ExpirationDate` timestamp NULL DEFAULT NULL,
  `PublicationDate` timestamp NULL DEFAULT NULL,
  `UserId` int(11) NOT NULL,
  `NavOrder` int(11) NOT NULL,
  `IsApproved` tinyint(1) DEFAULT '0',
  `Body` text NOT NULL,
  `Slug` varchar(150) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_StaticPage_UserId` (`UserId`),
  CONSTRAINT `fk_StaticPage_UserId` FOREIGN KEY (`UserId`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `StaticPage`
--

LOCK TABLES `StaticPage` WRITE;
/*!40000 ALTER TABLE `StaticPage` DISABLE KEYS */;
INSERT INTO `StaticPage` VALUES (1,'Page1','2014-10-22 14:10:10','2018-10-22 14:10:10','2015-10-22 14:10:10',1,1,1,'alhdadljdklf','aldhlahd'),(2,'Page2','2014-10-22 14:10:10','2018-10-22 14:10:10',NULL,3,2,1,'alfdjlafha','ahdlfah'),(3,'Page3','2014-10-22 14:10:10',NULL,'2015-10-22 14:10:10',3,3,1,'alkfladkfhl','ahflad'),(4,'Page4','2014-10-22 14:10:10','2015-10-22 14:10:10',NULL,5,4,0,'ahflfhlad','adhlfahd'),(5,'Page5','2014-10-22 14:10:10',NULL,NULL,5,5,0,'aldjfhaldfkla','adlfjf');
/*!40000 ALTER TABLE `StaticPage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `RoleId` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_User_RoleId` (`RoleId`),
  CONSTRAINT `fk_User_RoleId` FOREIGN KEY (`RoleId`) REFERENCES `Role` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'Bessie','bessie@gmail.com',1),(2,'Gina','g@gmail.com',2),(3,'Nick','n@gmail.com',3),(4,'Ken','k@gmail.com',2),(5,'Matt','m@gmail.com',3);
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-31 19:41:43
