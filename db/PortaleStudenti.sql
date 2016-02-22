DROP DATABASE IF EXISTS `PortaleStudenti`;
CREATE DATABASE  IF NOT EXISTS `PortaleStudenti` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `PortaleStudenti`;
-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: 127.0.0.1    Database: PortaleStudenti
-- ------------------------------------------------------
-- Server version	5.6.28

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
  `Aula` varchar(255) DEFAULT NULL,
  `Ref_Docente` char(6) NOT NULL,
  PRIMARY KEY (`Id_Appello`),
  KEY `Ref_Materia_idx` (`Ref_Materia`),
  KEY `Ref_Docente` (`Ref_Docente`),
  CONSTRAINT `Ref_Docente` FOREIGN KEY (`Ref_Docente`) REFERENCES `Docente` (`Matricola`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Ref_Materia` FOREIGN KEY (`Ref_Materia`) REFERENCES `Materia` (`Id_Materia`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AppelloEsame`
--

LOCK TABLES `AppelloEsame` WRITE;
/*!40000 ALTER TABLE `AppelloEsame` DISABLE KEYS */;
INSERT INTO `AppelloEsame` VALUES ('1','2016-02-02 09:00:00','000001','F130','000001'),('2','2016-02-04 10:00:00','000002','F310','000003'),('3','2016-02-21 08:30:00','000001','F130','000001'),('4','2016-02-21 09:45:00','000002','F100','000003'),('5','2016-02-22 09:30:00','111222','A320','000002'),('6','2016-02-03 11:00:00','111222','A230','000002'),('7','2016-01-30 11:30:00','123456','A310','000002'),('8','2016-02-21 10:00:00','123456','A320','000002');
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
INSERT INTO `Docente` VALUES ('000001','Mario','Rossi','ciao'),('000002','Paolo','Bianchi','ciao'),('000003','Luca','Verdi','ciao'),('000112','Rosario','Sorbello','ludovica'),('001122','John','Doe','hello'),('987654','Jane','Doe','ciao');
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
  `Ref_Verbale` int(11) NOT NULL,
  `Esito` enum('positivo','negativo') NOT NULL,
  `Voto` enum('18','19','20','21','22','23','24','25','26','27','28','29','30','30L') DEFAULT NULL,
  `DataEsame` datetime NOT NULL,
  `Domande` varchar(4000) NOT NULL,
  PRIMARY KEY (`Ref_Studente`,`Ref_Verbale`),
  KEY `Ref_Verbale` (`Ref_Verbale`),
  CONSTRAINT `esameverbalizzato_ibfk_1` FOREIGN KEY (`Ref_Studente`) REFERENCES `Studente` (`Matricola`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EsameVerbalizzato`
--

LOCK TABLES `EsameVerbalizzato` WRITE;
/*!40000 ALTER TABLE `EsameVerbalizzato` DISABLE KEYS */;
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
INSERT INTO `Insegnamento` VALUES ('000001','000001'),('000002','000003'),('111222','000002'),('123456','000002');
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
  `Anno` int(11) NOT NULL,
  `CFU` int(11) NOT NULL,
  `Ordinamento` varchar(255) NOT NULL,
  `Ref_CdL` char(6) NOT NULL,
  PRIMARY KEY (`Id_Materia`),
  KEY `materia_ibfk_1` (`Ref_CdL`),
  CONSTRAINT `materia_ibfk_1` FOREIGN KEY (`Ref_CdL`) REFERENCES `CorsoDiLaurea` (`Id_CdL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Materia`
--

LOCK TABLES `Materia` WRITE;
/*!40000 ALTER TABLE `Materia` DISABLE KEYS */;
INSERT INTO `Materia` VALUES ('000001','Analisi 1',1,12,'Nuovo','098765'),('000002','Fisica 2',2,6,'Nuovo','098765'),('111222','Ingegneria del software',2,6,'Nuovo','098765'),('123456','Basi di dati',3,6,'Nuovo','098765'),('888888','Storia moderna',2,12,'Nuovo','444444');
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
  `DataEsame` datetime DEFAULT NULL,
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
INSERT INTO `PianoDiStudi` VALUES ('000001','000001','098765',NULL,NULL),('000001','000002','098765',NULL,NULL),('000001','111222','098765',NULL,NULL),('000001','123456','098765',NULL,NULL),('000002','000001','098765',NULL,NULL),('000002','000002','098765',NULL,NULL),('000002','111222','098765',NULL,NULL),('000002','123456','098765',NULL,NULL),('000003','000001','098765',NULL,NULL),('000003','000002','098765',NULL,NULL),('000003','111222','098765',NULL,NULL),('000003','123456','098765',NULL,NULL),('000004','000001','098765',NULL,NULL),('000004','000002','098765',NULL,NULL),('000004','111222','098765',NULL,NULL),('000004','123456','098765',NULL,NULL);
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
  `Ref_Appello` int(11) NOT NULL,
  PRIMARY KEY (`Ref_Appello`,`Ref_Studente`),
  KEY `Ref_Studente` (`Ref_Studente`),
  KEY `Ref_Appello` (`Ref_Appello`),
  CONSTRAINT `prenotazione_ibfk_1` FOREIGN KEY (`Ref_Studente`) REFERENCES `Studente` (`Matricola`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Prenotazione`
--

LOCK TABLES `Prenotazione` WRITE;
/*!40000 ALTER TABLE `Prenotazione` DISABLE KEYS */;
INSERT INTO `Prenotazione` VALUES ('000001',1),('000003',1),('000002',2),('000003',2),('000004',2),('000002',3),('000004',3),('000001',4),('000002',5),('000003',5),('000004',6),('000001',7),('000003',7),('000004',7),('000002',8);
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
INSERT INTO `Studente` VALUES ('000001','Enrico','Casella','ciao','1234567890','cslnrc@gmail.com'),('000002','Carlo','Nuccio','ciao','1234567890','carlonuccio91@gmail.com'),('000003','Riccardo','Musmeci','ciao','1234567890','musmex92@gmail.com'),('000004','Fabrizio','Cacicia','ciao','1234567890','fabryx92@gmail.com');
/*!40000 ALTER TABLE `Studente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Verbale`
--

DROP TABLE IF EXISTS `Verbale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Verbale` (
  `Id_Verbale` int(11) NOT NULL AUTO_INCREMENT,
  `Ora_Apertura` datetime NOT NULL,
  `Ora_Chiusura` datetime DEFAULT NULL,
  `Ref_CdL` char(6) NOT NULL,
  `Ref_AppelloEsame` char(6) NOT NULL,
  `Ref_Materia` char(6) NOT NULL,
  PRIMARY KEY (`Id_Verbale`),
  UNIQUE KEY `Ref_AppelloEsame` (`Ref_AppelloEsame`,`Ref_Materia`),
  KEY `Ref_Materia_idx` (`Ref_Materia`),
  KEY `Ref_CdL` (`Ref_CdL`),
  CONSTRAINT `verbale_ibfk_1` FOREIGN KEY (`Ref_CdL`) REFERENCES `CorsoDiLaurea` (`Id_CdL`),
  CONSTRAINT `verbale_ibfk_2` FOREIGN KEY (`Ref_AppelloEsame`) REFERENCES `AppelloEsame` (`Id_Appello`),
  CONSTRAINT `verbale_ibfk_3` FOREIGN KEY (`Ref_Materia`) REFERENCES `Materia` (`Id_Materia`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Verbale`
--

LOCK TABLES `Verbale` WRITE;
/*!40000 ALTER TABLE `Verbale` DISABLE KEYS */;
INSERT INTO `Verbale` VALUES (1,'2016-02-21 19:32:27',NULL,'098765','1','000001'),(2,'2016-02-21 14:00:00',NULL,'098765','3','000001');
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

-- Dump completed on 2016-02-21 20:35:13
