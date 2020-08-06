--
-- Table structure for table `kama_record`
--

DROP TABLE IF EXISTS `kama_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kama_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dev_number` varchar(32) NOT NULL,
  `imsi` varchar(32),
  `imei` varchar(32),
  `tmsi` varchar(32),
  `longitude` varchar(32),  
  `latitude` varchar(32),
  `rtime` bigint(20), 
  `operid` varchar(16),
  `phone` varchar(16),
  `provinceid` varchar(16),
  `cityid` varchar(16),
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;  
/*!40101 SET character_set_client = @saved_cs_client */;
