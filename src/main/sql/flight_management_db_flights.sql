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
-- Table structure for table `flights`
--

DROP TABLE IF EXISTS `flights`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flights` (
  `flight_id` int(11) NOT NULL AUTO_INCREMENT,
  `airline` int(11) NOT NULL,
  `flight_number` varchar(20) NOT NULL,
  `from_city` int(11) NOT NULL,
  `to_city` int(11) NOT NULL,
  `start_time` varchar(10) NOT NULL,
  `end_time` varchar(10) NOT NULL,
  `duration` varchar(10) NOT NULL,
  `on_sunday` tinyint(4) NOT NULL,
  `on_monday` tinyint(4) NOT NULL,
  `on_tuesday` tinyint(4) NOT NULL,
  `on_wednesday` tinyint(4) NOT NULL,
  `on_thursday` tinyint(4) NOT NULL,
  `on_friday` tinyint(4) NOT NULL,
  `on_saturday` tinyint(4) NOT NULL,
  `instrument` varchar(20) NOT NULL,
  `business_class_seats` int(11) NOT NULL,
  `non_business_class_seats` int(11) NOT NULL,
  `business_class_seats_price` int(11) NOT NULL,
  `non_business_class_seat_price` int(11) NOT NULL,
  `row_count` int(11) NOT NULL,
  `column_count` int(11) NOT NULL,
  `meal` int(11) NOT NULL,
  `active` tinyint(4) NOT NULL,
  `updated_by` varchar(100) NOT NULL,
  `updated_on` datetime NOT NULL,
  PRIMARY KEY (`flight_id`),
  UNIQUE KEY `unique_flight_number` (`flight_number`),
  KEY `fk_flight_airlines` (`airline`),
  KEY `fk_from_operating_citis` (`from_city`),
  KEY `fk_to_operating_citis` (`to_city`),
  KEY `fk_flight_meals` (`meal`),
  CONSTRAINT `fk_flight_airlines` FOREIGN KEY (`airline`) REFERENCES `airlines` (`airline_id`),
  CONSTRAINT `fk_flight_meals` FOREIGN KEY (`meal`) REFERENCES `meals` (`meal_id`),
  CONSTRAINT `fk_from_operating_citis` FOREIGN KEY (`from_city`) REFERENCES `operating_cities` (`city_id`),
  CONSTRAINT `fk_to_operating_citis` FOREIGN KEY (`to_city`) REFERENCES `operating_cities` (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flights`
--

LOCK TABLES `flights` WRITE;
/*!40000 ALTER TABLE `flights` DISABLE KEYS */;
INSERT INTO `flights` VALUES (1,1,'AIR-7896',1,2,'10:42','11:58','01 h 16 m',1,1,1,1,1,1,1,'Ins-a',30,50,6857,4857,5,5,3,1,'Biraj Halder','2021-09-25 15:52:51'),(2,2,'INDI-23563',2,1,'12:35','13:55','01 h 20 m',1,1,1,1,1,1,1,'Ins-b',50,50,6970,4960,5,4,3,1,'Biraj Halder','2021-09-25 15:54:25'),(3,2,'INDI-23570',1,2,'00:35','01:55','01 h 20 m',1,1,1,1,1,1,1,'Ins-a',30,30,5400,3500,3,3,3,1,'Biraj Halder','2021-09-25 15:55:38'),(4,1,'AIR-7887',2,1,'00:42','01:45','01 h 03 m',1,1,1,1,1,1,1,'Ins-b',30,30,5500,3500,3,4,3,1,'Biraj Halder','2021-09-25 15:57:10');
/*!40000 ALTER TABLE `flights` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-27 21:12:58
