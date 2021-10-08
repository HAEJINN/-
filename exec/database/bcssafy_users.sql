-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 3.34.135.3    Database: bcssafy
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `modified_at` datetime DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `user_profile_image` varchar(255) DEFAULT NULL,
  `user_status` varchar(255) DEFAULT NULL,
  `user_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2021-10-06 11:24:48','2021-10-06 11:24:48','lhh','lhh','$2a$10$txXO6ZnPpjAckYMMs4wfrOLJQgI2vWxijLNjs4VoY2AImpYQmaVgi','profile.jpg','ACTIVITY',NULL),(2,'2021-10-06 11:25:42','2021-10-06 11:25:42','lhh2','lhh2','$2a$10$214h98z5Y3ZqYKQlaHAv7uJs/R7SxqNHCaBaVBVTUPXU5T3hzwrO2','profile.jpg','ACTIVITY',NULL),(3,'2021-10-07 12:11:14','2021-10-07 12:11:14','ghghkd','호황','$2a$10$JOS8Hi1M001xRJmqciaLQuqGW29yRU7co1wN8WqpSW27xd5iBWf6W','profile.jpg','ACTIVITY',NULL),(4,'2021-10-07 12:16:33','2021-10-07 12:16:33','123123','123123','$2a$10$vHRCRorVcaZBpCieBT7cKOijRsJdNKTkwEGB7bCe2aDyJSMtcM5GK','profile.jpg','ACTIVITY',NULL),(5,'2021-10-07 12:16:35','2021-10-07 12:16:35','123123','123123','$2a$10$UffnqioLYEzhMp4JBOruD.dIu6zXA142B8BZsSEcCBqFqTdE9ts9y','profile.jpg','ACTIVITY',NULL),(6,'2021-10-07 12:17:05','2021-10-07 12:32:20','12341234','12341234','$2a$10$xHrWhmHy5qHX30L/zu2s2OU.p26NnJLwZbp7YZPwntza/Cl8eWlfi','1633609939458_1cda57498b8d6a45129ea2292902819d7154249a3890514a43687a85e6b6cc82.png','ACTIVITY',''),(7,'2021-10-07 12:17:17','2021-10-07 12:17:17','qwer1234@naver.com','undefined','$2a$10$QglbpC0VeBSBJxR4LEHOXObz.aNeM2iz8ZfV8Clz5iIGr9mnleyUe','profile.jpg','ACTIVITY',NULL),(8,'2021-10-07 13:03:51','2021-10-07 13:10:25','lhh4','lhh4','$2a$10$uJAhhCCssUrDCosXdD425ODWtrwsQYGEUTcX3zVbVRVLHO6DtH2xy','1633612224288_짱구.png','ACTIVITY','ㅎㅇ'),(9,'2021-10-07 13:51:24','2021-10-07 13:51:24','kwj1270@naver.com','김우재','$2a$10$jFRzd51soJ9PKpvJxnMG5e2LYOppSv17PkNnILLSEwX5jNvOVk1TS','profile.jpg','ACTIVITY',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-08 12:02:04
