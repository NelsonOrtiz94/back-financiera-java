-- MySQL dump 10.13  Distrib 8.4.1, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: entidad_financiera
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.24-MariaDB

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) DEFAULT NULL,
  `correo_electronico` varchar(255) DEFAULT NULL,
  `fecha_creacion` date DEFAULT NULL,
  `fecha_modificacion` date DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `nombres` varchar(255) DEFAULT NULL,
  `numero_identificacion` varchar(255) DEFAULT NULL,
  `tipo_identificacion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (2,'Ortiz','N10@gmail.com','2024-07-16','2024-07-19','1995-07-18','Nelson','1121918787','CC'),(3,'Prada','L8@gmail.com','2024-07-16','2024-07-17','2010-07-17','Luis','312456358','TI'),(10,'yy','string@hmail.com','2024-07-18','2024-07-17','2000-07-17','nn','1121911236','CC'),(11,'Ortiz Sanabria','ortiz4302@gmail.com','2024-07-18',NULL,'1995-03-03','Nelson Alejandro','55','CC'),(12,'Sanabria','ortiz4302@gmail.com','2024-07-18',NULL,'1998-05-05','Martha','52525','CC'),(13,'Sanabria','z4302@gmail.com','2024-07-19',NULL,'1999-05-01','Alejandro','112121','CC'),(14,'Sanabria','z4302@gmail.com','2024-07-19',NULL,'1999-05-01','Alejandro','112121','CC'),(15,'Salah','asas@gmail.com','2024-07-19',NULL,'1995-01-01','Sandra','1234','CE'),(16,'Ortiz Sanabria','ortiz4302@gmail.com','2024-07-19',NULL,'1994-01-01','Nelson Alejandro','52525','CC'),(17,'Ortiz Sanabria','ortiz4302@gmail.com','2024-07-19',NULL,'1995-05-05','Nelson Alejandro','52525','CC');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta`
--

DROP TABLE IF EXISTS `cuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuenta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `numero_cuenta` varchar(255) DEFAULT NULL,
  `saldo` double DEFAULT NULL,
  `saldo_disponible` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta`
--

LOCK TABLES `cuenta` WRITE;
/*!40000 ALTER TABLE `cuenta` DISABLE KEYS */;
INSERT INTO `cuenta` VALUES (1,'5387179109',0,0);
/*!40000 ALTER TABLE `cuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `estado` varchar(255) DEFAULT NULL,
  `exentagmf` bit(1) DEFAULT NULL,
  `fecha_creacion` date DEFAULT NULL,
  `fecha_modificacion` date DEFAULT NULL,
  `numero_cuenta` varchar(255) DEFAULT NULL,
  `saldo` decimal(38,2) DEFAULT NULL,
  `tipo_cuenta` varchar(255) DEFAULT NULL,
  `cliente_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5me9o2ajdu105p7rrtxkbthje` (`cliente_id`),
  CONSTRAINT `FK5me9o2ajdu105p7rrtxkbthje` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'string',_binary '','2024-07-18','2024-07-18','123456789',0.00,'cuenta de ahorros',10),(4,'CANCELADA',_binary '','2024-07-18','2024-07-18','5340214277',0.00,'cuenta de ahorros',2),(5,'ACTIVA',_binary '','2024-07-18','2024-07-18','5387179109',0.00,'cuenta de ahorros',2),(6,'INACTIVA',_binary '','2024-07-18','2024-07-18','5322719013',0.00,'cuenta de ahorros',2),(7,'INACTIVA',_binary '','2024-07-18','2024-07-18','3394858578',0.00,'cuenta corriente',2),(8,'ACTIVA',_binary '','2024-07-18','2024-07-18','5379414432',0.00,'cuenta de ahorros',2),(9,'ACTIVA',_binary '','2024-07-18','2024-07-18','5314451251',100.00,'cuenta de ahorros',2),(10,'ACTIVA',_binary '','2024-07-18','2024-07-18','3303946129',100.00,'cuenta corriente',3),(11,'ACTIVA',_binary '','2024-07-18','2024-07-18','5366088785',100.00,'cuenta de ahorros',3),(12,NULL,_binary '','2024-07-18','2024-07-18','3301493937',0.00,'cuenta corriente',10);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaccion`
--

DROP TABLE IF EXISTS `transaccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaccion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` datetime(6) DEFAULT NULL,
  `saldo` double DEFAULT NULL,
  `tipo_cuenta` varchar(255) DEFAULT NULL,
  `cuenta_destino_id` bigint(20) DEFAULT NULL,
  `cuenta_origen_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhaaa8hd9jvoqmwdflt8c6kuqp` (`cuenta_destino_id`),
  KEY `FKxvyfnyjy81i330lml8s0y706` (`cuenta_origen_id`),
  CONSTRAINT `FKhaaa8hd9jvoqmwdflt8c6kuqp` FOREIGN KEY (`cuenta_destino_id`) REFERENCES `cuenta` (`id`),
  CONSTRAINT `FKxvyfnyjy81i330lml8s0y706` FOREIGN KEY (`cuenta_origen_id`) REFERENCES `cuenta` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaccion`
--

LOCK TABLES `transaccion` WRITE;
/*!40000 ALTER TABLE `transaccion` DISABLE KEYS */;
INSERT INTO `transaccion` VALUES (3,'2024-07-21 18:59:59.000000',100,'cuenta de ahorros',NULL,1);
/*!40000 ALTER TABLE `transaccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'entidad_financiera'
--

--
-- Dumping routines for database 'entidad_financiera'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-22 14:46:39
