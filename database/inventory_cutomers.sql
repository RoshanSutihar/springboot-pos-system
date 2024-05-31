-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: inventory
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cutomers`
--

DROP TABLE IF EXISTS `cutomers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cutomers` (
  `cus_id` int NOT NULL AUTO_INCREMENT,
  `cus_name` varchar(60) DEFAULT NULL,
  `cus_phone` varchar(45) DEFAULT NULL,
  `cus_add` varchar(80) DEFAULT NULL,
  `cus_email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cus_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cutomers`
--

LOCK TABLES `cutomers` WRITE;
/*!40000 ALTER TABLE `cutomers` DISABLE KEYS */;
INSERT INTO `cutomers` VALUES (1,'John Doe','555-1234','123 Main St','john.doe5@example.com'),(3,'Alice Smith','555-5678','456 Elm St','alice.smith1@example.com'),(4,'Bob Johnson','555-9012','789 Oak St','bob.johnson1@example.com'),(7,'Sophia Brown','555-2345','303 Birch St','sophia.brown@example.com'),(8,'Jacob Miller','555-6789','404 Cedar St','jacob.miller@example.com'),(9,'Isabella Gonzales','555-78901','909 Elm St','isabella.gonzales@example.com'),(10,'William Hernandez','555-34536','808 Oak St','william.hernandez@example.com'),(11,'James Lopez','555-23345','1010 Cedar St','james.lopez@example.com'),(12,'Ava Perez','555-67869','1111 Birch St','ava.perez@example.com'),(13,'Logan Reyes','555-12324','1212 Maple St','logan.reyes@example.com'),(14,'Bob Johnson test','55590129183','789 Oak St ','bob.johnson1@ample.com'),(15,'CMSC 106','123456789','room 421','mail@tempmai.com'),(16,'Roshan Sutihar','986120372','711 E Boldt Way Spc 1504','roshan@email.com');
/*!40000 ALTER TABLE `cutomers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-31 12:00:17
