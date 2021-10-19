-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: flight_management_db
-- ------------------------------------------------------
-- Server version	8.0.11

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
-- Table structure for table `operating_cities`
--

DROP TABLE IF EXISTS `operating_cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operating_cities` (
  `city_id` int(11) NOT NULL AUTO_INCREMENT,
  `city_code` varchar(10) NOT NULL,
  `city_name` varchar(100) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `updated_by` varchar(100) NOT NULL,
  `updated_on` datetime NOT NULL,
  PRIMARY KEY (`city_id`),
  UNIQUE KEY `unique_city_code` (`city_code`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operating_cities`
--

LOCK TABLES `operating_cities` WRITE;
/*!40000 ALTER TABLE `operating_cities` DISABLE KEYS */;
INSERT INTO `operating_cities` VALUES (1,'KOL','Kolkata',1,'Biraj Halder','2021-09-25 15:46:52'),(2,'BAN','Bangalore',1,'Biraj Halder','2021-09-25 15:46:52'),(3,'BHU','Bhubaneswar',1,'Biraj Halder','2021-09-25 15:46:52'),(4,'DEL','Delhi',1,'Biraj Halder','2021-09-25 15:46:52'),(5,'CHN','Chennai',1,'Biraj Halder','2021-09-25 15:46:53'),(6,'GWH','Guwahati',1,'Biraj Halder','2021-09-25 15:46:53'),(7,'BOM','Mumbai',1,'Biraj Halder','2021-09-25 15:46:53');
/*!40000 ALTER TABLE `operating_cities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-27 21:12:59
