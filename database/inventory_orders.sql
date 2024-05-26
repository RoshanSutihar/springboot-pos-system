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
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` varchar(80) DEFAULT NULL,
  `total_amount` double DEFAULT NULL,
  `payment_type` varchar(45) DEFAULT NULL,
  `order_status` varchar(45) DEFAULT NULL,
  `order_date` varchar(45) DEFAULT NULL,
  `ship_date` varchar(45) DEFAULT NULL,
  `order_origin` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  UNIQUE KEY `order_id_UNIQUE` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (4,'Frank',100,'cash','pending','2024-05-10','2024-05-10','store'),(5,'Ram',100,'cash','pending','2024-05-10','2024-05-10','store'),(6,'harry',100,'cash','pending','2024-05-10','2024-05-10','store'),(8,'gita',100,'cash','complete','2024-05-10','2024-05-10','store'),(9,'Gita',100,'cash','delivered','2024-05-10','2024-05-10','store'),(10,'Gita',100,'cash','complete','2024-05-10','2024-05-10','store'),(11,'Gita',100,'cash','pending','2024-05-10','2024-05-10','store'),(12,'Alice Smith',93.6,'cash','delivered','2024-05-12','2024-05-23','store'),(14,'Roshan Sutihar',100,'cash','complete','2024-05-15','2024-05-20','store'),(15,'John Doe',1677.98,'paypal','pending','2024-05-15','2024-05-28','store'),(16,'John Doe',78,'cash','pending','2024-05-16','2024-05-17','store'),(17,'Roshan Sutihar',100,'Credit Card','pending','2024-05-16','2024-05-20','ecommerce'),(18,'James Lopez',499.9,'cash','pending','2024-05-16','2024-05-29','store'),(19,'Bob Johnson',499.90000000000003,'Credit Card','pending','2024-05-16','2024-05-18','ecommerce'),(20,'Logan Reyes',1499.87,'cash','pending','2024-05-16','2024-05-17','store'),(21,'Jacob Miller',9599.88,'cash','pending','2024-05-16','2024-05-23','store'),(22,'Roshan Sutihar',100,'Credit Card','pending','2024-05-16','2024-05-21','store'),(23,'William Hernandez',9599.88,'cash','pending','2024-05-16','2024-05-30','ecommerce'),(24,'William Hernandez',8999.9,'cash','pending','2024-05-17','2024-05-23','store'),(25,'Sophia Brown',9039.88,'cash','pending','2024-05-20','2024-05-24','store'),(26,'Alice Smith',499.9,'paypal','pending','2024-05-20','2024-05-30','store'),(27,'Gita',130,'cash','pending','2024-05-25','2024-05-31','store');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-26 16:29:08
