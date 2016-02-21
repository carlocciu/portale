CREATE DATABASE  IF NOT EXISTS `PortaleStudenti` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `PortaleStudenti`;
-- MySQL dump 10.13  Distrib 5.6.22, for osx10.8 (x86_64)
--
-- Host: localhost    Database: PortaleStudenti
-- ------------------------------------------------------
-- Server version	5.6.17

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
-- Table structure for table `AppelloEsame`
--

DROP TABLE IF EXISTS `AppelloEsame`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AppelloEsame` (
  `Id_Appello` varchar(255) NOT NULL,
  `Data` datetime NOT NULL,
  `Ref_Materia` char(6) NOT NULL,
  `Ref_Docente` char(6) NOT NULL,
  PRIMARY KEY (`Id_Appello`),
  KEY `Ref_Materia_idx` (`Ref_Materia`),
  CONSTRAINT `Ref_Materia` FOREIGN KEY (`Ref_Materia`) REFERENCES `Materia` (`Id_Materia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Ref_Docente` FOREIGN KEY (`Ref_Docente`) REFERENCES `Docente` (`Matricola`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AppelloEsame`
--

LOCK TABLES `AppelloEsame` WRITE;
/*!40000 ALTER TABLE `AppelloEsame` DISABLE KEYS */;
INSERT INTO `AppelloEsame` VALUES ('123456','2016-02-19 10:29:09','123456', '000112'),('333333','2016-02-19 16:21:36','888888', '987654'),('555555','2016-02-19 16:14:07','111222', '001122');
/*!40000 ALTER TABLE `AppelloEsame` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CorsoDiLaurea`
--

DROP TABLE IF EXISTS `CorsoDiLaurea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CorsoDiLaurea` (
  `Id_CdL` char(6) NOT NULL,
  `Nome` varchar(255) NOT NULL,
  `Ref_Dipartimento` char(6) NOT NULL,
  PRIMARY KEY (`Id_CdL`),
  KEY `Ref_Dipartimento` (`Ref_Dipartimento`),
  CONSTRAINT `Ref_Dipartimento` FOREIGN KEY (`Ref_Dipartimento`) REFERENCES `Dipartimento` (`Id_Dip`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CorsoDiLaurea`
--

LOCK TABLES `CorsoDiLaurea` WRITE;
/*!40000 ALTER TABLE `CorsoDiLaurea` DISABLE KEYS */;
INSERT INTO `CorsoDiLaurea` VALUES ('098765','Ingegneria Informatica','112233'),('444444','Lettere moderne','777777');
/*!40000 ALTER TABLE `CorsoDiLaurea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Dipartimento`
--

DROP TABLE IF EXISTS `Dipartimento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Dipartimento` (
  `Id_Dip` char(6) NOT NULL,
  `Nome` varchar(255) NOT NULL,
  `Ref_Scuola` varchar(255) NOT NULL,
  PRIMARY KEY (`Id_Dip`),
  KEY `Ref_Scuola_idx` (`Ref_Scuola`),
  CONSTRAINT `Ref_Scuola` FOREIGN KEY (`Ref_Scuola`) REFERENCES `Scuola` (`Id_Scuola`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Dipartimento`
--

LOCK TABLES `Dipartimento` WRITE;
/*!40000 ALTER TABLE `Dipartimento` DISABLE KEYS */;
INSERT INTO `Dipartimento` VALUES ('112233','Ingegneria Informatica Chimica Meccanica e Gestionale','090909'),('777777','Lettere moderne e contemporanee','111111');
/*!40000 ALTER TABLE `Dipartimento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Docente`
--

DROP TABLE IF EXISTS `Docente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Docente` (
  `Matricola` char(6) NOT NULL,
  `Nome` varchar(255) NOT NULL,
  `Cognome` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  PRIMARY KEY (`Matricola`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Docente`
--

LOCK TABLES `Docente` WRITE;
/*!40000 ALTER TABLE `Docente` DISABLE KEYS */;
INSERT INTO `Docente` VALUES ('000112','Rosario','Sorbello','ludovica'),('001122','John','Doe','hello'),('987654','Jane','Doe','mydog');
/*!40000 ALTER TABLE `Docente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EsameVerbalizzato`
--

DROP TABLE IF EXISTS `EsameVerbalizzato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EsameVerbalizzato` (
  `Ref_Studente` char(6) NOT NULL,
  `Ref_Verbale` char(6) NOT NULL,
  `Esito` enum('positivo','negativo') NOT NULL,
  `Voto` enum('18','19','20','21','22','23','24','25','26','27','28','29','30','30L') DEFAULT NULL,
  `DataEsame` datetime NOT NULL,
  PRIMARY KEY (`Ref_Studente`,`Ref_Verbale`),
  KEY `Ref_Verbale` (`Ref_Verbale`),
  CONSTRAINT `esameverbalizzato_ibfk_1` FOREIGN KEY (`Ref_Studente`) REFERENCES `Studente` (`Matricola`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `esameverbalizzato_ibfk_2` FOREIGN KEY (`Ref_Verbale`) REFERENCES `Verbale` (`Id_Verbale`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EsameVerbalizzato`
--

LOCK TABLES `EsameVerbalizzato` WRITE;
/*!40000 ALTER TABLE `EsameVerbalizzato` DISABLE KEYS */;
INSERT INTO `EsameVerbalizzato` VALUES ('000001','111111','positivo','27','2016-02-19 10:36:54'),('000002','222222','positivo','27','2016-02-19 16:14:18'),('000003','000111','positivo','27','2016-02-19 16:21:48');
/*!40000 ALTER TABLE `EsameVerbalizzato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Insegnamento`
--

DROP TABLE IF EXISTS `Insegnamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Insegnamento` (
  `Ref_Materia` char(6) NOT NULL,
  `Ref_Docente` char(6) NOT NULL,
  KEY `Ref_Materia` (`Ref_Materia`),
  KEY `Ref_Docente` (`Ref_Docente`),
  CONSTRAINT `insegnamento_ibfk_1` FOREIGN KEY (`Ref_Materia`) REFERENCES `Materia` (`Id_Materia`),
  CONSTRAINT `insegnamento_ibfk_2` FOREIGN KEY (`Ref_Docente`) REFERENCES `Docente` (`Matricola`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Insegnamento`
--

LOCK TABLES `Insegnamento` WRITE;
/*!40000 ALTER TABLE `Insegnamento` DISABLE KEYS */;
INSERT INTO `Insegnamento` VALUES ('123456','000112'),('111222','001122'),('888888','987654');
/*!40000 ALTER TABLE `Insegnamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Materia`
--

DROP TABLE IF EXISTS `Materia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Materia` (
  `Id_Materia` char(6) NOT NULL,
  `Nome` varchar(255) NOT NULL,
  `Ordinamento` varchar(255) NOT NULL,
  `Docente` char(6) NOT NULL,
  `Anno` int(11) NOT NULL,
  `CFU` int(11) NOT NULL,
  PRIMARY KEY (`Id_Materia`),
  KEY `Matricola_idx` (`Docente`),
  CONSTRAINT `Docente` FOREIGN KEY (`Docente`) REFERENCES `Docente` (`Matricola`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Materia`
--

LOCK TABLES `Materia` WRITE;
/*!40000 ALTER TABLE `Materia` DISABLE KEYS */;
INSERT INTO `Materia` VALUES ('111222','Ingegneria del software','Informatica','001122',2,6),('123456','Basi di dati','Informatica','000112',1,6),('888888','Storia moderna','Lettere','987654',2,12);
/*!40000 ALTER TABLE `Materia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PianoDiStudi`
--

DROP TABLE IF EXISTS `PianoDiStudi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PianoDiStudi` (
  `Ref_Studente` char(6) NOT NULL,
  `Ref_Materia` char(6) NOT NULL,
  `Ref_CorsoDiLaurea` char(6) NOT NULL,
  `Ref_Voto` enum('18','19','20','21','22','23','24','25','26','27','28','29','30','30L') DEFAULT NULL,
  `DataEsame` datetime NOT NULL,
  PRIMARY KEY (`Ref_Studente`,`Ref_Materia`),
  KEY `Ref_Materia` (`Ref_Materia`),
  KEY `Ref_CorsoDiLaurea` (`Ref_CorsoDiLaurea`),
  CONSTRAINT `pianodistudi_ibfk_1` FOREIGN KEY (`Ref_Studente`) REFERENCES `Studente` (`Matricola`),
  CONSTRAINT `pianodistudi_ibfk_2` FOREIGN KEY (`Ref_Materia`) REFERENCES `Materia` (`Id_Materia`),
  CONSTRAINT `pianodistudi_ibfk_3` FOREIGN KEY (`Ref_CorsoDiLaurea`) REFERENCES `CorsoDiLaurea` (`Id_CdL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PianoDiStudi`
--

LOCK TABLES `PianoDiStudi` WRITE;
/*!40000 ALTER TABLE `PianoDiStudi` DISABLE KEYS */;
INSERT INTO `PianoDiStudi` VALUES ('000001','123456','098765','27','2016-02-19 10:37:00'),('000002','111222','098765','27','2016-02-19 16:14:22'),('000003','888888','444444','27','2016-02-19 16:21:51');
/*!40000 ALTER TABLE `PianoDiStudi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Prenotazione`
--

DROP TABLE IF EXISTS `Prenotazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Prenotazione` (
  `Ref_Studente` char(6) NOT NULL,
  `Ref_Appello` varchar(255) NOT NULL,
  KEY `Ref_Studente` (`Ref_Studente`),
  KEY `Ref_Appello` (`Ref_Appello`),
  CONSTRAINT `prenotazione_ibfk_1` FOREIGN KEY (`Ref_Studente`) REFERENCES `Studente` (`Matricola`),
  CONSTRAINT `prenotazione_ibfk_2` FOREIGN KEY (`Ref_Appello`) REFERENCES `AppelloEsame` (`Id_Appello`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Prenotazione`
--

LOCK TABLES `Prenotazione` WRITE;
/*!40000 ALTER TABLE `Prenotazione` DISABLE KEYS */;
INSERT INTO `Prenotazione` VALUES ('000001','123456'),('000002','555555'),('000003','333333');
/*!40000 ALTER TABLE `Prenotazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Scuola`
--

DROP TABLE IF EXISTS `Scuola`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Scuola` (
  `Id_Scuola` varchar(255) NOT NULL,
  `Nome` varchar(255) NOT NULL,
  PRIMARY KEY (`Id_Scuola`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Scuola`
--

LOCK TABLES `Scuola` WRITE;
/*!40000 ALTER TABLE `Scuola` DISABLE KEYS */;
INSERT INTO `Scuola` VALUES ('090909','Scuola politecnica'),('111111','Scuola delle scienze umane');
/*!40000 ALTER TABLE `Scuola` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Studente`
--

DROP TABLE IF EXISTS `Studente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Studente` (
  `Matricola` char(6) NOT NULL,
  `Nome` varchar(255) NOT NULL,
  `Cognome` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Telefono` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL,
  PRIMARY KEY (`Matricola`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Studente`
--

LOCK TABLES `Studente` WRITE;
/*!40000 ALTER TABLE `Studente` DISABLE KEYS */;
INSERT INTO `Studente` VALUES ('000001','Enrico','Casella','ciaociao','1234567890','cslnrc@gmail.com'),('000002','Carlo','Nuccio','baubau','0987654321','carlonuccio91@gmail.com'),('000003','Riccardo','Musmeci','miaomiao','1111111111','musmex92@gmail.com');
/*!40000 ALTER TABLE `Studente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Verbale`
--

DROP TABLE IF EXISTS `Verbale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Verbale` (
  `Id_Verbale` char(6) NOT NULL,
  `Ora_Apertura` datetime NOT NULL,
  `Ora_Chiusura` datetime DEFAULT NULL,
  `Ref_CdL` char(6) NOT NULL,
  `Ref_AppelloEsame` char(6) NOT NULL,
  `Ref_Materia` char(6) NOT NULL,
  PRIMARY KEY (`Id_Verbale`),
  UNIQUE KEY `Ref_AppelloEsame` (`Ref_AppelloEsame`,`Ref_Materia`),
  KEY `Ref_Materia_idx` (`Ref_Materia`),
  KEY `Ref_CdL` (`Ref_CdL`),
  CONSTRAINT `verbale_ibfk_3` FOREIGN KEY (`Ref_Materia`) REFERENCES `Materia` (`Id_Materia`),
  CONSTRAINT `verbale_ibfk_1` FOREIGN KEY (`Ref_CdL`) REFERENCES `CorsoDiLaurea` (`Id_CdL`),
  CONSTRAINT `verbale_ibfk_2` FOREIGN KEY (`Ref_AppelloEsame`) REFERENCES `AppelloEsame` (`Id_Appello`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Verbale`
--

LOCK TABLES `Verbale` WRITE;
/*!40000 ALTER TABLE `Verbale` DISABLE KEYS */;
INSERT INTO `Verbale` VALUES ('000111','2016-02-19 16:21:40','2016-02-19 16:21:40','444444','333333','888888'),('111111','2016-02-19 10:29:16','2016-02-19 10:29:16','098765','123456','123456'),('222222','2016-02-19 16:14:13','2016-02-19 16:14:13','098765','555555','111222');
/*!40000 ALTER TABLE `Verbale` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-19 16:22:40
