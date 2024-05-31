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
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(80) DEFAULT NULL,
  `product_desc` varchar(100) DEFAULT NULL,
  `product_qty` int DEFAULT NULL,
  `product_cat` varchar(40) DEFAULT NULL,
  `product_supp` varchar(40) DEFAULT NULL,
  `product_price` double DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Product ABC','Description of Product ABC',72,'books','Dummy Supplier',19),(2,'Smartphone X','High-performance smartphone with advanced features',119,'electronics','Dummy Supplier',21),(3,'Bluetooth Speaker','Portable speaker with wireless connectivity',80,'electronics','Dummy Supplier',50),(4,'Smart Watch','Fitness tracker and smartwatch hybrid',30,'electronics','Dummy Supplier',200),(5,'LED TV','High-definition LED television',37,'electronics','Dummy Supplier',900),(6,'Digital Camera','Professional-grade digital camera with lenses',43,'electronics','ABC Supplier',15),(7,'Plato','Article',76,'books','Dummy Supplier',60),(8,'Geoscience','Uni Book',13,'books','Dummy Supplier',49),(9,'Wireless Headphones','Premium wireless headphones with active noise cancellation',99,'electronics','Dummy Supplier',249),(10,'Aquafina bottle','1l mineral water',54,'kitchen','XYZ Traders',20),(11,'Dell Laptop','510 GB',34,'electronics','VWX Enterprises',4999),(12,'Table','45X20 Inch table for computers',42,'furniture','VWX Enterprises',39),(13,'Cooking Pot Set','10-piece non-stick cooking pot set',50,'kitchen','DEF Wholesalers',80),(14,'Leather Sofa','Luxury leather sofa with reclining feature',30,'furniture','LMN Enterprises',1200),(15,'Organic Apples','Fresh organic apples from local farms',200,'foods','PQR Imports',3),(16,'Office Chair','Ergonomic office chair with lumbar support',75,'furniture','UVW Exports',150),(17,'Mechanical Keyboard','Backlit mechanical keyboard with customizable keys',120,'electronics','MNO Manufacturers',100),(18,'Ceramic Dinnerware Set','20-piece ceramic dinnerware set for 4',40,'kitchen','STU Wholesalers',60),(19,'Textbooks','Comprehensive textbooks for university students',150,'books','Lawrence University',85),(20,'Inkjet Printer','High-speed inkjet printer with wireless connectivity',60,'electronics','XY print service',200),(21,'Sticky Notes','Pack of 12 colorful sticky notes',500,'stationary','Badgers enterprise',10);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-31 12:00:18
