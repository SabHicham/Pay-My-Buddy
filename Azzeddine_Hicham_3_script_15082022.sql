CREATE DATABASE  IF NOT EXISTS `paymybuddy` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `paymybuddy`;
-- MySQL dump 10.13  Distrib 8.0.30, for macos12 (x86_64)
--
-- Host: localhost    Database: paymybuddy
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `bank`
--

DROP TABLE IF EXISTS `bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bank` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank`
--

LOCK TABLES `bank` WRITE;
/*!40000 ALTER TABLE `bank` DISABLE KEYS */;
INSERT INTO `bank` VALUES (1,'CIC'),(2,'CA');
/*!40000 ALTER TABLE `bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contact` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `friend_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `contact_ibfk_1_idx` (`user_id`),
  KEY `friend_id_fkey` (`friend_id`),
  CONSTRAINT `friend_id_fkey` FOREIGN KEY (`friend_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_id_fkey` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (1,9,10),(4,13,9),(5,14,9),(6,13,10),(7,15,9),(8,9,9),(9,9,9),(10,9,13),(11,9,13),(12,9,13),(13,9,13),(14,9,13),(15,9,14);
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(45) NOT NULL,
  `amount` double NOT NULL,
  `emitter` double NOT NULL,
  `receiver` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,'cinema',15.5,9,10),(20,'a',100,9,10),(21,'cinema',50,14,9),(22,'cinéma',5,9,10),(23,'cinéma',2,13,10),(24,'b',30000,13,10),(25,'cinéma',50,15,9),(26,'Restaurant ',5,15,9),(27,'cinéma',4,9,13),(28,'cinéma',100,9,13),(29,'cinéma',1,9,13),(30,'cinéma',12,9,10),(31,'a',2,9,10),(32,'cinéma',1,9,10),(33,'a',1,9,10),(34,'cinéma',15,9,10),(35,'a',1,9,10),(36,'cinéma',1,9,10),(37,'cinéma',1,9,13),(38,'cinéma',4,9,10),(39,'a',2,9,13),(40,'a',2,9,13),(41,'cinéma',8,9,14),(42,'cinéma',3,9,14),(43,'b',7,9,10),(44,'c',2,9,13),(45,'cinéma',3,9,14),(46,'a',2,9,13);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` longtext NOT NULL,
  `sold` double DEFAULT NULL,
  `bank_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `bank_id_fk_idx` (`bank_id`),
  CONSTRAINT `bank_id_fk` FOREIGN KEY (`bank_id`) REFERENCES `bank` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (9,'Hicham','Hich','hicham@email.com','$2a$10$dhThga/0GCu7vrxJCxHvnO4E140cpc3m3RNZdvOZnDy89q3WqRpgG',5932.725,1),(10,'sabrina','sab','sabrina@email.com','$2a$10$q/QTF/J4700lIkHu/KDQV.YX/pP9Lw9qhH.Qg8qOq6E0.lA1b.7QK',31990.244999999995,1),(13,'eren','jager','eren@email.com','$2a$10$V7.OYenh/HlyD6iCnvazFeRAhK3eJHkzrHkqSBreBZNpKSRa74xsq',116.42999999999999,2),(14,'mikasa','ackerman','mikasa@email.com','$2a$10$2yQMaX6lt5AD9aC0gvv70exQddnJI6VgPQQKcg4jBR5CurFE0Lldi',63.93,2),(15,'clément','mentor','clement@email.com','$2a$10$TxZT95Kg/XFmmFjUb52E5OXeTeumpAPipS0xAK6aqgvNlrbWf8..q',45,1),(17,'abc','efgh','abc@email.com','$2a$10$Xoc/2GO/romHpDvKqMeezuS.xugUWcyY0WQui.mw3a3l9ikAdAd1C',0,1),(19,'abc2','efgh2','abc2@email.com','$2a$10$EtXpTX15xd.khGo1/iD0zeyRm1sMMJepMm/bqc7HQpvLTN/f8cAE2',0,1),(20,'aqw','xsz','aqw@email.com','$2a$10$sstFQqBt3xkEwo6ADWEHSOsL07Q0dnGSQk99RyUbFCrx6ONZ9oLT6',0,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-07  0:43:04
