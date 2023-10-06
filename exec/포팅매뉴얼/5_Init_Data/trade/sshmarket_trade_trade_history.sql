-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: j9a503a.p.ssafy.io    Database: sshmarket_trade
-- ------------------------------------------------------
-- Server version	8.1.0

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
-- Table structure for table `trade_history`
--

DROP TABLE IF EXISTS `trade_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trade_history` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `is_reviewed` bit(1) NOT NULL,
  `main_image` varchar(2000) NOT NULL,
  `price` int NOT NULL,
  `title` varchar(500) NOT NULL,
  `trade_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4pjpr1pl5mpsw49f8isoyl0ad` (`trade_id`),
  CONSTRAINT `FK4pjpr1pl5mpsw49f8isoyl0ad` FOREIGN KEY (`trade_id`) REFERENCES `trade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade_history`
--

LOCK TABLES `trade_history` WRITE;
/*!40000 ALTER TABLE `trade_history` DISABLE KEYS */;
INSERT INTO `trade_history` VALUES (3,'2023-10-06 01:57:37.143674','2023-10-06 01:59:27.630519',_binary '','https://a503.s3.ap-northeast-2.amazonaws.com/article/image/28d9aafb-5f72-4ee2-983b-e20d31a768a3.jpg',5500,'싸피 아파트 주민 여러분 고구마 사가세요!',9),(4,'2023-10-06 02:06:24.465047','2023-10-06 02:08:03.245556',_binary '','https://a503.s3.ap-northeast-2.amazonaws.com/article/image/c3113c0c-5ef0-4510-9694-e9276ae25699.jpg',2500,'신선한 양파 팔아요 [택배가능 / 경기]',11);
/*!40000 ALTER TABLE `trade_history` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-06 11:44:50
