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
-- Table structure for table `roaster`
--

DROP TABLE IF EXISTS `roaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roaster` (
  `roaster_id` int(11) NOT NULL AUTO_INCREMENT,
  `flight` int(11) NOT NULL,
  `roaster_date` datetime NOT NULL,
  `depurture` varchar(10) NOT NULL,
  `arrival` varchar(10) NOT NULL,
  `from_city` int(11) NOT NULL,
  `to_city` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `delay_time_in_mins` int(11) DEFAULT NULL,
  `business_class_seats_available` int(11) NOT NULL,
  `non_business_class_seats_available` int(11) NOT NULL,
  `business_class_seat_price` int(11) NOT NULL,
  `non_business_class_seat_price` int(11) NOT NULL,
  `remarks` varchar(200) DEFAULT NULL,
  `updated_by` varchar(100) NOT NULL,
  `updated_on` datetime NOT NULL,
  PRIMARY KEY (`roaster_id`),
  KEY `fk_roaster_flights` (`flight`),
  KEY `fk_from_operating_cities` (`from_city`),
  KEY `fk_to_operating_cities` (`to_city`),
  KEY `fk_roaster_status` (`status`),
  CONSTRAINT `fk_from_operating_cities` FOREIGN KEY (`from_city`) REFERENCES `operating_cities` (`city_id`),
  CONSTRAINT `fk_roaster_flights` FOREIGN KEY (`flight`) REFERENCES `flights` (`flight_id`),
  CONSTRAINT `fk_roaster_status` FOREIGN KEY (`status`) REFERENCES `roaster_status` (`status_id`),
  CONSTRAINT `fk_to_operating_cities` FOREIGN KEY (`to_city`) REFERENCES `operating_cities` (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roaster`
--

LOCK TABLES `roaster` WRITE;
/*!40000 ALTER TABLE `roaster` DISABLE KEYS */;
INSERT INTO `roaster` VALUES (1,1,'2021-09-30 00:00:00','10:42','11:58',1,2,1,0,30,50,6857,4857,'','Biraj Halder','2021-09-25 15:57:51'),(2,2,'2021-09-30 00:00:00','12:35','13:55',2,1,1,0,50,50,6970,4960,'','Biraj Halder','2021-09-25 15:57:53'),(3,3,'2021-09-30 00:00:00','00:35','01:55',1,2,1,0,30,30,5400,3500,'','Biraj Halder','2021-09-25 15:57:54'),(4,4,'2021-09-30 00:00:00','00:42','01:45',2,1,1,0,28,30,5500,3500,'','Biraj Halder','2021-09-25 16:06:27'),(5,1,'2021-09-29 00:00:00','10:42','11:58',1,2,1,0,28,50,6857,4857,'','Biraj Halder','2021-09-25 16:06:26'),(6,2,'2021-09-29 00:00:00','12:35','13:55',2,1,1,0,50,50,6970,4960,'','Biraj Halder','2021-09-25 15:58:10'),(7,3,'2021-09-29 00:00:00','00:35','01:55',1,2,1,0,30,30,5400,3500,'','Biraj Halder','2021-09-25 15:58:11'),(8,4,'2021-09-29 00:00:00','00:42','01:45',2,1,1,0,27,30,5500,3500,'','Biraj Halder','2021-09-25 16:11:22'),(9,1,'2021-10-01 00:00:00','10:42','11:58',1,2,1,0,30,50,6857,4857,'','Biraj Halder','2021-09-25 15:58:22'),(10,2,'2021-10-01 00:00:00','12:35','13:55',2,1,1,0,50,50,6970,4960,'','Biraj Halder','2021-09-25 15:58:23'),(11,3,'2021-10-01 00:00:00','00:35','01:55',1,2,1,0,30,30,5400,3500,'','Biraj Halder','2021-09-25 15:58:25'),(12,4,'2021-10-01 00:00:00','00:42','01:45',2,1,1,0,30,30,5500,3500,'','Biraj Halder','2021-09-25 15:58:26'),(13,1,'2021-10-02 00:00:00','10:42','11:58',1,2,1,0,30,50,6857,4857,'','Biraj Halder','2021-09-25 15:58:34'),(14,2,'2021-10-02 00:00:00','12:35','13:55',2,1,1,0,50,50,6970,4960,'','Biraj Halder','2021-09-25 15:58:36'),(15,3,'2021-10-02 00:00:00','00:35','01:55',1,2,1,0,30,30,5400,3500,'','Biraj Halder','2021-09-25 15:58:37'),(16,4,'2021-10-02 00:00:00','00:42','01:45',2,1,1,0,30,30,5500,3500,'','Biraj Halder','2021-09-25 15:58:39');
/*!40000 ALTER TABLE `roaster` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-27 21:12:55
