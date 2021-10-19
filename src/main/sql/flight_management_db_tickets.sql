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
-- Table structure for table `tickets`
--

DROP TABLE IF EXISTS `tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tickets` (
  `ticket_id` int(11) NOT NULL AUTO_INCREMENT,
  `ticket_number` varchar(20) NOT NULL,
  `booking` int(11) NOT NULL,
  `passenger_name` varchar(100) NOT NULL,
  `passenger_age` int(11) NOT NULL,
  `passenger_contact` varchar(20) NOT NULL,
  `passenger_identity_number` varchar(50) NOT NULL,
  `passenger_identity_type` varchar(20) NOT NULL,
  `seat_type` varchar(10) NOT NULL,
  PRIMARY KEY (`ticket_id`),
  UNIQUE KEY `unique_ticket_number` (`ticket_number`),
  KEY `fk_bookings_ticket` (`booking`),
  CONSTRAINT `fk_bookings_ticket` FOREIGN KEY (`booking`) REFERENCES `bookings` (`booking_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tickets`
--

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
INSERT INTO `tickets` VALUES (1,'8348536821',1,'Rakesh Roy',35,'80756984589','AFCTY5678E','PAN','BUSINESS'),(2,'9881884143',1,'Rihana Roy',32,'80756984589','1235 5698 2587','AADHAR','BUSINESS'),(3,'3731261935',2,'Rakesh Roy',35,'80756984589','AFCTY5678E','PAN','BUSINESS'),(4,'4539914927',2,'Rihana Roy',32,'80756984589','1235 5698 2587','AADHAR','BUSINESS'),(5,'4687724756',3,'Rakesh Roy',35,'8075698459','1258 8965 8792','AADHAR','BUSINESS'),(6,'2628833867',3,'Brijesh Patel',60,'9336897458','8596 2589 3598','AADHAR','BUSINESS'),(7,'1585821291',3,'Sraddha Patel',59,'9336897458','5874 9632 5874','AADHAR','BUSINESS');
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-27 21:12:56
