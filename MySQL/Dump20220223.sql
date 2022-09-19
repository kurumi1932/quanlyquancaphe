-- MySQL dump 10.13  Distrib 8.0.24, for Win64 (x86_64)
--
-- Host: localhost    Database: quanlyquancaphe
-- ------------------------------------------------------
-- Server version	8.0.24

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
-- Table structure for table `ban`
--

DROP TABLE IF EXISTS `ban`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ban` (
  `MaBan` int NOT NULL AUTO_INCREMENT,
  `TenBan` varchar(45) NOT NULL,
  `TrangThai` varchar(45) NOT NULL,
  `TrangThai2` int NOT NULL,
  PRIMARY KEY (`MaBan`),
  UNIQUE KEY `MaBan_UNIQUE` (`MaBan`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ban`
--

LOCK TABLES `ban` WRITE;
/*!40000 ALTER TABLE `ban` DISABLE KEYS */;
INSERT INTO `ban` VALUES (1,'Bàn 1','Trống',1),(2,'Bàn 2','Trống',1),(3,'Bàn 3','Trống',1),(4,'Bàn 4','Trống',1),(5,'Bàn 5','Trống',1),(6,'Bàn 6','Trống',1),(7,'Bàn 7','Trống',1),(8,'Bàn 8','Trống',1),(9,'Bàn 9','Trống',1),(10,'Bàn 10','Đã đặt trước',1),(11,'Bàn 11','Đã đặt trước',1),(12,'Bàn 12','Trống',1),(13,'Bàn 13','Trống',1),(14,'Bàn 14','Đã đặt trước',1),(15,'Bàn 15','Trống',1),(16,'Bàn 16','Đã đặt trước',1),(17,'Bàn 17','Trống',1),(18,'Bàn 18','Trống',1),(23,'Bàn 19','Đã đặt trước',1),(27,'Bàn 20','Trống',1),(28,'Bàn 21','Trống',1),(29,'Bàn 22','Trống',1),(31,'Bàn 23','Trống',1),(32,'Bàn 24','Đã đặt trước',1),(33,'Bàn 25','Đã đặt trước',0),(35,'Bàn 25','Đã đặt trước',0),(36,'Bàn 25','Đã đặt trước',0);
/*!40000 ALTER TABLE `ban` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `autoTrangThai_Ban` BEFORE INSERT ON `ban` FOR EACH ROW BEGIN
	set new.TrangThai='Trống';
    set new.TrangThai2='1';
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `cthoadon`
--

DROP TABLE IF EXISTS `cthoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cthoadon` (
  `MaHD` varchar(20) NOT NULL,
  `MaMon` int NOT NULL,
  `TenMon` varchar(45) NOT NULL,
  `DVTinh` varchar(45) NOT NULL,
  `SoLuong` int NOT NULL,
  `DonGia` float NOT NULL,
  `ThanhTien` float NOT NULL,
  PRIMARY KEY (`MaHD`,`MaMon`),
  KEY `fk_cthoadon_thucdon_idx` (`MaMon`),
  CONSTRAINT `fk_cthoadon_hoadon` FOREIGN KEY (`MaHD`) REFERENCES `hoadon` (`MaHD`),
  CONSTRAINT `fk_cthoadon_thucdon` FOREIGN KEY (`MaMon`) REFERENCES `thucdon` (`MaMon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cthoadon`
--

LOCK TABLES `cthoadon` WRITE;
/*!40000 ALTER TABLE `cthoadon` DISABLE KEYS */;
INSERT INTO `cthoadon` VALUES ('HD202110050001',2,'Nâu nóng','Ly',1,25000,25000),('HD202110050001',16,'Sinh tố Xoài','Ly',1,30000,30000),('HD202110050001',28,'Cao cao nóng','Ly',1,25000,25000),('HD202110050002',2,'Nâu nóng','Ly',3,25000,25000),('HD202110050002',9,'Coca Cola','Lon',2,25000,50000),('HD202110050002',32,'Bia Ken','Lon',1,25000,25000),('HD202110050003',9,'Coca Cola','Lon',1,25000,25000),('HD202110050003',12,'Trà Gừng','Ly',3,25000,75000),('HD202110050003',28,'Cao cao nóng','Ly',1,25000,25000),('HD202110050004',29,'Ca cao nguội','Ly',1,25000,25000),('HD202110050004',32,'Bia Ken','Lon',2,25000,50000),('HD202110050005',2,'Nâu nóng','Ly',1,25000,25000),('HD202110050005',22,'Kẹo cao su','cái',1,1000,1000),('HD202110050006',2,'Nâu nóng','Ly',3,25000,75000),('HD202110050006',4,'Lọc đá vắt chanh','Chậu',1,40000,40000),('HD202110050007',2,'Nâu nóng','Ly',3,25000,75000),('HD202110050007',32,'Bia Ken','Lon',1,25000,25000),('HD202110050008',2,'Nâu nóng','Ly',3,25000,75000),('HD202110050009',3,'Cafe Sữa','Ly',1,50000,50000),('HD202110050009',29,'Ca cao nguội','Ly',1,25000,25000),('HD202110050009',31,'Đen nóng ','Ly',1,25000,25000),('HD202110080001',2,'Nâu nóng','Ly',1,25000,25000),('HD202110080001',8,'Chanh muối','Chai',1,20000,20000),('HD202110080001',16,'Sinh tố Xoài','Ly',1,30000,30000),('HD202110080001',20,'Sinh tố chanh leo','Ly',1,30000,30000),('HD202110080002',9,'Coca Cola','Lon',1,25000,25000),('HD202110080002',14,'Trà chanh','Ly',1,15000,15000),('HD202110080002',32,'Bia Ken','Lon',1,25000,25000),('HD202110080002',34,'Bia Hà Nội','Lon',1,20000,20000),('HD202110080002',35,'Bia Kenn','Lon',1,25000,25000),('HD202110080003',4,'Lọc đá vắt chanh','Chậu',1,40000,40000),('HD202110080003',19,'Sinh tố Mãng Cầu','Ly',1,35000,35000),('HD202110080003',39,'Mực nướng','Con',1,55000,55000),('HD202110080004',6,'Trà Xanh ','Chai',1,25000,25000),('HD202110080004',39,'Mực nướng','Con',1,55000,55000),('HD202110080005',2,'Nâu nóng','Ly',1,25000,25000),('HD202110080005',12,'Trà Gừng','Ly',2,25000,50000),('HD202110080005',15,'Trà My','Bát',1,200000,200000),('HD202110080005',27,'Thăng Long','Bao',2,20000,40000),('HD202110080006',2,'Nâu nóng','Ly',1,25000,25000),('HD202110080006',4,'Lọc đá vắt chanh','Chậu',1,40000,40000),('HD202110080006',9,'Coca Cola','Lon',2,25000,50000),('HD202110080007',10,'RedBull','Lon',1,25000,25000),('HD202110080007',32,'Bia Ken','Lon',2,25000,50000),('HD202110080007',38,'Bánh mỳ pate','Cái',1,15000,15000),('HD202110100001',2,'Nâu nóng','Ly',1,25000,25000),('HD202110100002',9,'Coca Cola','Lon',1,25000,25000),('HD202110100002',10,'RedBull','Lon',1,25000,25000),('HD202110100003',2,'Nâu nóng','Ly',1,25000,25000),('HD202111140002',9,'Coca Cola','Lon',1,25000,25000),('HD202111140002',21,'Sinh tố dưa chuột','Ly',1,35000,35000),('HD202111140003',2,'Nâu nóng','Ly',1,25000,25000),('HD202111140003',29,'Ca cao nguội','Ly',1,25000,25000),('HD202111140004',8,'Chanh muối','Chai',1,20000,20000),('HD202111140005',9,'Coca Cola','Lon',1,25000,25000),('HD202111140005',12,'Trà Gừng','Ly',1,25000,25000),('HD202111150001',12,'Trà Gừng','Ly',1,25000,25000),('HD202111150001',15,'Trà My','Bát',1,200000,200000),('HD202111150002',6,'Trà Xanh ','Chai',1,25000,25000),('HD202111150002',38,'Bánh mỳ pate','Cái',1,15000,15000),('HD202111150003',4,'Lọc đá vắt chanh','Chậu',1,40000,40000),('HD202111150004',9,'Coca Cola','Lon',1,25000,25000),('HD202111150005',9,'Coca Cola','Lon',1,25000,25000),('HD202111150005',34,'Bia Hà Nội','Lon',1,20000,20000),('HD202111150006',9,'Coca Cola','Lon',1,25000,25000),('HD202202120001',4,'Lọc đá vắt chanh','Chậu',1,40000,40000),('HD202202200001',10,'RedBull','Lon',3,25000,75000),('HD202202200001',12,'Trà Gừng','Ly',1,25000,25000),('HD202202200001',20,'Sinh tố chanh leo','Ly',1,30000,30000),('HD202202200001',33,'Bia Sài Gòn','Lon',1,20000,20000),('HD202202210002',32,'Bia Ken','Lon',1,25000,25000),('HD202202210002',33,'Bia Sài Gòn','Lon',1,20000,20000),('HD202202210002',34,'Bia Hà Nội','Lon',1,20000,20000),('HD202202230001',33,'Bia Sài Gòn','Lon',2,20000,40000),('HD202202230001',36,'Bia Ken','Lon',1,25000,25000);
/*!40000 ALTER TABLE `cthoadon` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `addTongTienHD` AFTER INSERT ON `cthoadon` FOR EACH ROW BEGIN
	declare tong float;
	select sum(ThanhTien) into tong from cthoadon where MaHD=new.MaHD;

	UPDATE hoadon
    set TongTien = tong
	where MaHD = new.MaHD;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `updateTongTienHD` AFTER UPDATE ON `cthoadon` FOR EACH ROW BEGIN
	declare tong float;
	select sum(ThanhTien) into tong from cthoadon where MaHD=new.MaHD;
	UPDATE hoadon
    set TongTien = tong
	where MaHD = new.MaHD;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `cthoadon_AFTER_DELETE` AFTER DELETE ON `cthoadon` FOR EACH ROW BEGIN
	declare tong float;
    if ((select count(*) from cthoadon where MaHD=old.MaHD) >=1) then
		select sum(ThanhTien) into tong from cthoadon where MaHD=old.MaHD;
		UPDATE hoadon
		set TongTien = tong
		where MaHD = old.MaHD;
	else
		UPDATE hoadon
		set TongTien = 0
		where MaHD = old.MaHD;
    end if;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoadon` (
  `MaHD` varchar(20) NOT NULL,
  `MaBan` int NOT NULL,
  `GioDen` datetime NOT NULL,
  `GioCapNhat` datetime NOT NULL,
  `TongTien` float DEFAULT NULL,
  `TrangThai` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `TenNV` varchar(100) NOT NULL,
  `TrangThai2` int NOT NULL,
  PRIMARY KEY (`MaHD`),
  UNIQUE KEY `MaHD_UNIQUE` (`MaHD`),
  KEY `fk_hoadon_ban_idx` (`MaBan`) /*!80000 INVISIBLE */,
  CONSTRAINT `fk_hoadon_ban` FOREIGN KEY (`MaBan`) REFERENCES `ban` (`MaBan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
INSERT INTO `hoadon` VALUES ('HD202110050001',1,'2021-10-05 19:44:47','2022-02-21 21:16:30',80000,'Đã thanh toán','jLabel1',1),('HD202110050002',2,'2021-10-05 19:46:27','2022-02-21 21:16:30',100000,'Đã thanh toán','jLabel1',1),('HD202110050003',3,'2021-10-05 19:49:16','2022-02-21 21:16:30',125000,'Đã thanh toán','jLabel1',1),('HD202110050004',4,'2021-10-05 20:27:40','2022-02-21 21:16:30',75000,'Đã thanh toán','jLabel1',1),('HD202110050005',5,'2021-10-05 20:35:11','2022-02-21 21:16:30',26000,'Đã thanh toán','jLabel1',1),('HD202110050006',6,'2021-10-05 20:36:13','2022-02-21 21:16:30',115000,'Đã thanh toán','jLabel1',1),('HD202110050007',7,'2021-10-05 20:36:34','2022-02-21 21:16:30',100000,'Đã thanh toán','jLabel1',1),('HD202110050008',9,'2021-10-05 20:47:30','2022-02-21 21:16:30',75000,'Đã thanh toán','jLabel1',1),('HD202110050009',7,'2021-10-05 21:22:34','2022-02-21 21:16:30',100000,'Đã thanh toán','jLabel1',1),('HD202110080001',8,'2021-10-08 08:50:05','2022-02-21 21:16:30',105000,'Đã thanh toán','jLabel1',1),('HD202110080002',1,'2021-10-08 08:50:38','2022-02-21 21:16:30',110000,'Đã thanh toán','jLabel1',1),('HD202110080003',1,'2021-10-08 08:52:05','2022-02-21 21:16:30',130000,'Đã thanh toán','jLabel1',1),('HD202110080004',7,'2021-10-08 08:56:39','2022-02-21 21:16:30',80000,'Đã thanh toán','jLabel1',1),('HD202110080005',6,'2021-10-08 08:57:19','2022-02-21 21:16:30',315000,'Đã thanh toán','jLabel1',1),('HD202110080006',8,'2021-10-08 09:26:29','2022-02-21 21:16:30',115000,'Đã thanh toán','jLabel1',1),('HD202110080007',3,'2021-10-08 11:01:22','2022-02-21 21:16:30',90000,'Đã thanh toán','jLabel1',1),('HD202110100001',4,'2021-10-10 21:04:17','2022-02-21 21:16:30',25000,'Đã thanh toán','jLabel1',1),('HD202110100002',4,'2021-10-10 21:05:34','2022-02-21 21:16:30',50000,'Đã thanh toán','jLabel1',1),('HD202110100003',9,'2021-10-10 21:07:13','2022-02-21 21:16:30',25000,'Đã thanh toán','jLabel1',1),('HD202111140002',8,'2021-11-14 23:38:51','2022-02-21 21:16:30',60000,'Đã thanh toán','1',1),('HD202111140003',9,'2021-11-14 23:53:58','2022-02-21 21:16:30',50000,'Đã thanh toán','1',1),('HD202111140004',4,'2021-11-14 23:57:35','2022-02-21 21:16:30',20000,'Đã thanh toán','1',1),('HD202111140005',9,'2021-11-14 23:59:10','2022-02-21 21:16:30',50000,'Đã thanh toán','1',1),('HD202111150001',8,'2021-11-15 00:02:23','2022-02-21 21:16:30',225000,'Đã thanh toán','1',1),('HD202111150002',8,'2021-11-15 00:02:43','2022-02-21 21:16:30',40000,'Đã thanh toán','1',1),('HD202111150003',9,'2021-11-15 00:04:58','2022-02-21 21:16:30',40000,'Đã thanh toán','1',1),('HD202111150004',4,'2021-11-15 00:10:52','2022-02-21 21:16:30',25000,'Đã thanh toán','1',1),('HD202111150005',9,'2021-11-15 00:15:58','2022-02-21 21:16:30',45000,'Đã thanh toán','1',1),('HD202111150006',9,'2021-11-15 00:48:55','2022-02-21 21:16:30',25000,'Đã thanh toán','1',1),('HD202202120001',1,'2022-02-12 13:51:52','2022-02-21 21:16:30',40000,'Đã thanh toán','1',1),('HD202202200001',13,'2022-02-20 17:14:32','2022-02-21 21:16:30',150000,'Đã thanh toán','Trịnh Ngọc Anh',1),('HD202202210001',13,'2022-02-21 21:13:01','2022-02-21 21:16:30',25000,'Đã thanh toán','1',1),('HD202202210002',8,'2022-02-21 21:19:35','2022-02-21 21:20:13',65000,'Đã thanh toán','1',0),('HD202202230001',9,'2022-02-23 10:06:20','2022-02-23 10:08:19',65000,'Đã thanh toán','1',0);
/*!40000 ALTER TABLE `hoadon` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `autoAddData` BEFORE INSERT ON `hoadon` FOR EACH ROW BEGIN
	
    set @mahd1 = CONCAT('HD',date_format(date(now()),'%Y%m%d'));
    SELECT Count(MaHD) into @countMaHD FROM hoadon WHERE MaHD Like concat(@mahd1, '%');
    set @countMaHD = @countMaHD+1;
    
    if @countMaHD > 999 then
		set @mahd2 = @mahd1;
		set @mahd = CONCAT(@mahd2 , @countMaHD);
	elseif @countMaHD > 99 then
		set @mahd2 = CONCAT(@mahd1, '0');
		set @mahd = CONCAT(@mahd2, @countMaHD);
	elseif @countMaHD <= 9 then
		set @mahd2 = CONCAT(@mahd1, '000');
		set @mahd = CONCAT(@mahd2, @countMaHD);
	else
		set @mahd2 = CONCAT(@mahd1, '00');
        set @mahd = CONCAT(@mahd2, @countMaHD);
	end if;
    
	SELECT count(MaHD) into @countMaHD1 FROM hoadon WHERE MaHD = @mahd;
	if(@countMaHD1=1) then
         set @countMaHD = @countMaHD+1;
         set @mahd = CONCAT(@mahd2, @countMaHD);
    end if;
    set new.MaHD = @mahd;
    set new.Gioden = now();
    set new.GioCapNhat = now();
    set new.TrangThai = 'Chưa thanh toán';
    set new.TrangThai2 = '1';
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `autoEditTime_HD` BEFORE UPDATE ON `hoadon` FOR EACH ROW BEGIN
    set new.GioCapNhat = now();
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `MaNV` varchar(10) NOT NULL,
  `TenNV` varchar(100) NOT NULL,
  `GioiTinh` varchar(45) NOT NULL,
  `NgaySinh` date NOT NULL,
  `Sdt` varchar(10) NOT NULL,
  `DiaChi` varchar(150) NOT NULL,
  `TrangThai` varchar(45) NOT NULL,
  PRIMARY KEY (`MaNV`),
  UNIQUE KEY `MaNV_UNIQUE` (`MaNV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES ('1','1','Nam','0025-07-23','1','1','Đang hoạt động'),('admin','Admin','Nam','2000-01-01','xxxxxx','xxxxxxx','Đang hoạt động'),('nv01','Hoàng Thành Công','Nam','2001-01-10','087326232','Hà Nội','Đang hoạt động'),('nv02','Trịnh Ngọc Anh','Nữ','1999-10-18','023462234','Vinh','Đang hoạt động'),('nv03','Hoàng Thị Ngọc Ánh','Nữ','2005-02-20','067532626','Nam Định','Đã nghỉ việc'),('nv04','Nguyễn Văn Hưng','Nam','2000-02-20','3532151613','Hà Nam','Đang hoạt động');
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `autoTrangThai_NV` BEFORE INSERT ON `nhanvien` FOR EACH ROW BEGIN
	set new.TrangThai='Đang hoạt động';
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `nhommon`
--

DROP TABLE IF EXISTS `nhommon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhommon` (
  `MaNhom` int NOT NULL AUTO_INCREMENT,
  `TenNhom` varchar(45) NOT NULL,
  `TrangThai` int NOT NULL,
  PRIMARY KEY (`MaNhom`),
  UNIQUE KEY `MaNhom_UNIQUE` (`MaNhom`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhommon`
--

LOCK TABLES `nhommon` WRITE;
/*!40000 ALTER TABLE `nhommon` DISABLE KEYS */;
INSERT INTO `nhommon` VALUES (1,'Cà phê',1),(2,'Nước đóng chai',1),(3,'Nước-Lon',1),(4,'Lipton-Trà',1),(5,'Sinh tố',1),(6,'Thứ khác',1),(7,'Đồ ăn nhanh',1),(9,'fre',0),(10,'13123',0),(11,'avcc',0),(12,'rgưgg',0);
/*!40000 ALTER TABLE `nhommon` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `nhommon_BEFORE_INSERT` BEFORE INSERT ON `nhommon` FOR EACH ROW BEGIN
	set new.TrangThai = '1';
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `nhommon_AFTER_UPDATE` AFTER UPDATE ON `nhommon` FOR EACH ROW BEGIN
	if new.TrangThai='0' then
    update thucdon
		SET TrangThai='0'
		where MaNhom = new.MaNhom;
    end if;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `taikhoan`
--

DROP TABLE IF EXISTS `taikhoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taikhoan` (
  `UserName` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Level` int NOT NULL,
  PRIMARY KEY (`UserName`),
  UNIQUE KEY `UserName_UNIQUE` (`UserName`),
  CONSTRAINT `fk_taikhoan_nhanvien` FOREIGN KEY (`UserName`) REFERENCES `nhanvien` (`MaNV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan`
--

LOCK TABLES `taikhoan` WRITE;
/*!40000 ALTER TABLE `taikhoan` DISABLE KEYS */;
INSERT INTO `taikhoan` VALUES ('1','1',1),('admin','admin',1),('nv01','nv01',2),('nv02','nv02',2),('nv03','2',1);
/*!40000 ALTER TABLE `taikhoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thucdon`
--

DROP TABLE IF EXISTS `thucdon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thucdon` (
  `MaMon` int NOT NULL AUTO_INCREMENT,
  `TenMon` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `MaNhom` int NOT NULL,
  `DVTinh` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `DonGia` float NOT NULL,
  `TrangThai` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`MaMon`),
  UNIQUE KEY `MaMon_UNIQUE` (`MaMon`),
  KEY `fk_thucdon_nhommon_idx` (`MaNhom`) /*!80000 INVISIBLE */,
  CONSTRAINT `fk_thucdon_nhommon` FOREIGN KEY (`MaNhom`) REFERENCES `nhommon` (`MaNhom`),
  CONSTRAINT `ck_dongiaTD` CHECK ((`DonGia` > 0))
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thucdon`
--

LOCK TABLES `thucdon` WRITE;
/*!40000 ALTER TABLE `thucdon` DISABLE KEYS */;
INSERT INTO `thucdon` VALUES (1,'Cafe Sữa',1,'Ly',50000,'1'),(2,'Nâu nóng',1,'Ly',25000,'1'),(3,'Cafe Sữa',1,'Ly',50000,'1'),(4,'Lọc đá vắt chanh',2,'Chậu',40000,'1'),(5,'Nâu lắc',1,'Ly',69000,'1'),(6,'Trà Xanh ',2,'Chai',25000,'1'),(7,'Trà C2',2,'Chai',20000,'1'),(8,'Chanh muối',2,'Chai',20000,'1'),(9,'Coca Cola',3,'Lon',25000,'1'),(10,'RedBull',3,'Lon',25000,'1'),(11,'Pepsi',3,'Lon',20000,'1'),(12,'Trà Gừng',4,'Ly',25000,'1'),(13,'Trà Dilmah',4,'Ly',25000,'1'),(14,'Trà chanh',4,'Ly',15000,'1'),(15,'Trà My',4,'Bát',200000,'1'),(16,'Sinh tố Xoài',5,'Ly',30000,'1'),(17,'Sinh tố bơ',5,'Ly',35000,'1'),(18,'Sinh tố Dưa Hấu',5,'Ly',30000,'1'),(19,'Sinh tố Mãng Cầu',5,'Ly',35000,'1'),(20,'Sinh tố chanh leo',5,'Ly',30000,'1'),(21,'Sinh tố dưa chuột',5,'Ly',35000,'1'),(22,'Kẹo cao su',6,'cái',1000,'1'),(23,'Hướng Dương',6,'Đĩa',15000,'1'),(24,'Khoai chiên',6,'Miếng',15000,'1'),(25,'Vina',6,'Bao',30000,'1'),(26,'555',6,'Bao',60000,'1'),(27,'Thăng Long',6,'Bao',20000,'1'),(28,'Cao cao nóng',1,'Ly',25000,'1'),(29,'Ca cao nguội',1,'Ly',25000,'1'),(30,'Đen đá',1,'Ly',25000,'1'),(31,'Đen nóng ',1,'Ly',25000,'1'),(32,'Bia Ken',3,'Lon',25000,'1'),(33,'Bia Sài Gòn',3,'Lon',20000,'1'),(34,'Bia Hà Nội',3,'Lon',20000,'1'),(35,'Bia Kenn',3,'Lon',25000,'1'),(36,'Bia Ken',3,'Lon',25000,'1'),(37,'Mỳ tôm',7,'Bát',15000,'1'),(38,'Bánh mỳ pate',7,'Cái',15000,'1'),(39,'Mực nướng',7,'Con',55000,'1'),(47,'1',1,'fawf',112314,'0'),(48,'131',2,'123',1234,'0'),(49,'1',9,'1',1,'0'),(50,'12',1,'1233',12,'0'),(51,'xfh',2,'1ưdf',11234,'0');
/*!40000 ALTER TABLE `thucdon` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `thucdon_BEFORE_INSERT` BEFORE INSERT ON `thucdon` FOR EACH ROW BEGIN
	set new.TrangThai = '1';
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Temporary view structure for view `view_tkhoadon`
--

DROP TABLE IF EXISTS `view_tkhoadon`;
/*!50001 DROP VIEW IF EXISTS `view_tkhoadon`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `view_tkhoadon` AS SELECT 
 1 AS `MaMon`,
 1 AS `TenMon`,
 1 AS `GioDen`,
 1 AS `DonGia`,
 1 AS `ThanhTien`,
 1 AS `SoLuong`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping events for database 'quanlyquancaphe'
--

--
-- Dumping routines for database 'quanlyquancaphe'
--
/*!50003 DROP PROCEDURE IF EXISTS `sp_tkhoadon1` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_tkhoadon1`()
BEGIN
	select MaMon , TenMon, sum(SoLuong) as TongSoLuong, DonGia, sum(ThanhTien) as TongThanhTien 
    from view_tkhoadon
    GROUP BY MaMon , TenMon, DonGia
    ORDER BY TongSoLuong desc;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_tkhoadon2` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_tkhoadon2`(in day1 datetime, in day2 datetime)
BEGIN
	select MaMon , TenMon, sum(SoLuong) as TongSoLuong, DonGia, sum(ThanhTien) as TongThanhTien 
    from view_tkhoadon
	where GioDen BETWEEN day1 AND day2
    GROUP BY MaMon , TenMon, DonGia
    ORDER BY TongSoLuong desc;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_tongtienHD1` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_tongtienHD1`()
BEGIN
	SELECT sum(TongTien) as Tongtieuthu FROM hoadon;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_tongtienHD2` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_tongtienHD2`(in day1 datetime, in day2 datetime)
BEGIN
	SELECT sum(TongTien) as Tongtieuthu FROM hoadon where GioDen BETWEEN day1 AND day2;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `view_tkhoadon`
--

/*!50001 DROP VIEW IF EXISTS `view_tkhoadon`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_tkhoadon` AS select `cthoadon`.`MaMon` AS `MaMon`,`cthoadon`.`TenMon` AS `TenMon`,`hoadon`.`GioDen` AS `GioDen`,`cthoadon`.`DonGia` AS `DonGia`,`cthoadon`.`ThanhTien` AS `ThanhTien`,`cthoadon`.`SoLuong` AS `SoLuong` from (`cthoadon` join `hoadon` on((`cthoadon`.`MaHD` = `hoadon`.`MaHD`))) order by `cthoadon`.`MaMon` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-23 10:13:23
