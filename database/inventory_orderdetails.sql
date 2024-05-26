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
-- Table structure for table `orderdetails`
--

DROP TABLE IF EXISTS `orderdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderdetails` (
  `details_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int DEFAULT NULL,
  `product_name` varchar(60) DEFAULT NULL,
  `product_qty` int DEFAULT NULL,
  `product_unitprice` double DEFAULT NULL,
  `product_total` double DEFAULT NULL,
  PRIMARY KEY (`details_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetails`
--

LOCK TABLES `orderdetails` WRITE;
/*!40000 ALTER TABLE `orderdetails` DISABLE KEYS */;
INSERT INTO `orderdetails` VALUES (1,4,NULL,0,50,NULL),(2,4,NULL,0,30,NULL),(3,5,NULL,2,50,NULL),(4,5,NULL,1,30,NULL),(5,6,'Product c',2,50,NULL),(6,6,'Product d',1,30,NULL),(7,8,'Product t',2,50,100),(8,8,'Product n',1,30,30),(9,9,'Product t',2,50,100),(10,9,'Product n',1,30,30),(11,10,'Product ABC',2,50,100),(12,11,'Product ABC',2,50,100),(13,12,'Product ABC',12,7.8,93.6),(14,13,'LED TV',2,899.99,1799.98),(15,13,'Smart Watch',1,199.99,199.99),(16,14,'LED TV',2,899.99,1799.98),(17,14,'Smart Watch',1,199.99,199.99),(18,15,'1',10,7.8,78),(19,15,'2',2,799.99,1599.98),(20,16,'1',10,7.8,78),(21,17,'LED TV',2,899.99,1799.98),(22,18,'3',10,49.99,499.90000000000003),(23,19,'3',10,49.99,499.90000000000003),(24,20,'5',1,899.99,899.99),(25,20,'3',12,49.99,599.88),(26,21,'Smartphone X',12,799.99,9599.880000000001),(27,22,'LED TV',2,899.99,1799.98),(28,23,'Smartphone X',12,799.99,9599.88),(29,24,'LED TV',10,899.99,8999.9),(30,25,'LED TV',2,899.99,1799.98),(31,25,'Plato',4,59.99,239.96),(32,25,'Smartphone X',1,799.99,799.99),(33,25,'Digital Camera',4,1499.99,5999.96),(34,25,'Smart Watch',1,199.99,199.99),(35,26,'Bluetooth Speaker',10,49.99,499.9),(36,27,'Product t',2,50,100),(37,27,'Product n',1,30,30);
/*!40000 ALTER TABLE `orderdetails` ENABLE KEYS */;
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
