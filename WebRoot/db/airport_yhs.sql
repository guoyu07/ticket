-- MySQL dump 10.13  Distrib 5.1.51, for Win32 (ia32)
--
-- Host: localhost    Database: ticket
-- ------------------------------------------------------
-- Server version	5.1.51-community

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
-- Table structure for table `ticket_pageredirecttemplate`
--

DROP TABLE IF EXISTS `ticket_pageredirecttemplate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_pageredirecttemplate` (
  `id` varchar(255) NOT NULL,
  `isSinglePage` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  `toPage` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `directory` varchar(255) DEFAULT NULL,
  `toPageAjax` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_pageredirecttemplate`
--

LOCK TABLES `ticket_pageredirecttemplate` WRITE;
/*!40000 ALTER TABLE `ticket_pageredirecttemplate` DISABLE KEYS */;
INSERT INTO `ticket_pageredirecttemplate` VALUES ('06668375-c73e-4df6-abbd-096337d1fbb1',1,'机场派出所',0,0,'2015-10-22 17:06:30',0,0,0,0,'site',1445504790276,'airportPoliceStation',0,'convenientService',NULL),('07154e06-9634-44c6-b64a-a35be9232e15',1,'儿童娱乐区',0,0,'2015-10-22 16:43:02',0,0,0,0,'site',1445503382409,'publicFacilities/childEntertainmentDistrict',0,NULL,NULL),('0e9c836b-4c28-4585-86d0-319ee6a3a17a',1,'问询台',0,0,'2015-10-22 16:56:38',0,0,0,0,'site',1445504198388,'convenientService/theInformationDesk',0,NULL,NULL),('0f9823b9-6290-4c95-9695-40e518402af9',1,'老残障人服务',0,0,'2015-10-22 16:47:08',0,0,0,0,'site',1445503628920,'caringService/oldDisabledPerson01',0,NULL,NULL),('11ac560a-5a94-45fc-9f04-c6b12975de1b',1,'火车票代售',0,0,'2015-10-22 16:59:34',0,0,0,0,'site',1445504374723,'convenientService/trainTicketCosignee',1,NULL,NULL),('1f4416cf-79c1-49b1-a4b9-65b11dec8afb',1,'商务中心',0,0,'2015-10-22 16:37:49',0,0,0,0,'site',1445503069790,'travelService/businessCenter',0,NULL,NULL),('2da99bb8-b304-41b0-be61-4a3cd50be45d',1,'百度翻译',0,0,'2015-10-22 17:00:18',0,0,0,0,'site',1445504418406,'baiduTranslate',1,'convenientService',NULL),('422aa756-a9a2-44cc-b00e-5ddab2ab2aa4',1,'internationalFlightNotice',0,0,'2015-10-22 16:36:07',0,0,0,0,'site',1445502967839,'flightNotice/internationalFlightNotice',0,NULL,NULL),('46b89ca8-f419-46e7-bf1d-b5627ac83608',1,'国内乘机须知',0,0,'2015-10-22 16:35:18',0,0,0,0,'site',1445502918724,'flightNotice/domisticFlightNotice',0,NULL,NULL),('545cac0a-3e1d-46f7-9a6b-02040788d016',1,'计时休息室',0,0,'2015-10-22 16:39:30',0,0,0,0,'site',1445503170695,'travelService/timeLounge',0,NULL,NULL),('54db1d34-5f67-433d-b7e6-28b6cbb30e03',1,'机场寻人',0,0,'2015-10-22 17:04:08',0,0,0,0,'site',1445504648531,'airportSearchPeople',0,'convenientService',NULL),('5742b8fe-a2c4-4113-b4a1-cdec9d995a3d',1,'自助充电站',0,0,'2015-10-22 16:41:03',0,0,0,0,'site',1445503263962,'publicFacilities/selfChargingStation',0,NULL,NULL),('59f8378f-d17b-445d-9409-bc8c0e58f553',1,'医疗健康',0,0,'2015-10-22 16:57:32',0,0,0,0,'site',1445504252365,'convenientService/healthCare',0,NULL,NULL),('5b4497bb-6f83-426b-9c49-da1b85b46438',1,'WiFi取号机',0,0,'2015-10-22 16:43:55',0,0,0,0,'site',1445503435032,'publicFacilities/WIFTNumbertakingMachine',0,NULL,NULL),('5b6c6092-4935-4cd4-b5fd-377585ba3fda',1,'文化展示',0,0,'2015-10-22 16:36:54',0,0,0,0,'site',1445503014003,'culture/cultureDisplay',0,NULL,NULL),('5bb662bd-7d69-4351-815e-8a31825d8150',1,'更衣室',0,0,'2015-10-22 16:41:53',0,0,0,0,'site',1445503313904,'publicFacilities/lockerRoom',0,NULL,NULL),('661637c7-6c83-4dd9-aa06-5d03ef11c96b',1,'手推车',0,0,'2015-10-22 16:43:26',0,0,0,0,'site',1445503406278,'publicFacilities/trolley',0,NULL,NULL),('66ca1ffd-d852-4640-84f9-61035e6fa6fa',1,'头等舱商务舱服务',0,0,'2015-10-22 16:39:05',0,0,0,0,'site',1445503145302,'travelService/firstBusinessCabinLounge',0,NULL,NULL),('6b7c2e8b-0ce1-4ace-8eab-3983510bf7e5',1,'百事特贵宾厅',0,0,'2015-10-22 16:38:31',0,0,0,0,'site',1445503111431,'travelService/baishiteLounge',0,NULL,NULL),('7378dda1-0b92-48ad-a193-d54329481b96',1,'公告栏',0,0,'2015-10-22 17:11:51',0,0,0,0,'site',1445505111688,'notice-board',1,'newsNotice',NULL),('859f0a74-d95d-440a-b0de-b5155ee3d87b',1,'图片新闻',0,0,'2015-10-22 17:12:22',0,0,0,0,'site',1445505142740,'pictureNews',1,'newsNotice',NULL),('88e12fcf-ae6c-484c-97a8-8d6ba6c5bc8d',1,'机场要闻',0,0,'2015-10-22 17:13:30',0,0,0,0,'site',1445505210379,'airportFocusNews',1,'newsNotice',NULL),('94ca1644-6c6e-41f6-9a7a-99b8d2e62af1',1,'母婴室',0,0,'2015-10-22 16:45:55',0,0,0,0,'site',1445503555690,'caringService/maternalAndInfantRoom01',0,NULL,NULL),('99148906-b033-4505-b288-fce799048377',1,'城市候机楼',0,0,'2015-10-22 16:44:28',0,0,0,0,'site',1445503468520,'publicFacilities/cityTerminal',0,NULL,NULL),('9cf79941-ddca-403b-a828-3f8f0daf43b9',1,'婴幼儿手推车',0,0,'2015-10-22 16:47:36',0,0,0,0,'site',1445503656888,'caringService/babyTrolley',0,NULL,NULL),('b3de2349-fa89-498b-ac19-fef62d53769c',1,'无人伴护老人及儿童',0,0,'2015-10-22 16:46:30',0,0,0,0,'site',1445503590632,'caringService/noAccompanyPassengerService',0,NULL,NULL),('b5d2ba82-4999-4e52-9c32-14e87de2a709',1,'中国邮政',0,0,'2015-10-22 17:07:12',0,0,0,0,'site',1445504832147,'ChinaPost',0,'convenientService',NULL),('bac45522-625c-426e-a065-f114b073ea2d',1,'行李打包',0,0,'2015-10-22 16:51:12',0,0,0,0,'site',1445503872228,'luggageService/luggagePack',0,NULL,NULL),('c2552232-2f30-4cb3-b4a1-cb584e570078',1,'祈祷室',0,0,'2015-10-22 16:41:24',0,0,0,0,'site',1445503284007,'publicFacilities/prayerRoom',0,NULL,NULL),('ce0d129e-08e8-48d3-946b-76c2d5b75557',1,'自助售货机',0,0,'2015-10-22 16:42:21',0,0,0,0,'site',1445503341842,'publicFacilities/vendingMachine',0,NULL,NULL),('ce5d7b3c-5671-4e6e-9b2e-c4fe35c88a74',1,'吸烟室',0,0,'2015-10-22 16:45:13',0,0,0,0,'site',1445503513561,'publicFacilities/smokingArea',0,NULL,NULL),('d5aeffa1-7ac1-4a67-ad57-57d631c6e32d',1,'行李查询',0,0,'2015-10-22 16:51:33',0,0,0,0,'site',1445503893596,'luggageService/luggageQuery',0,NULL,NULL),('dc5142d5-9f6e-4edc-94bd-1b2f0a6b9c76',1,'72小时过境签',0,0,'2015-10-22 17:04:48',0,0,0,0,'site',1445504688560,'seventytwoHourTransitSign',0,'convenientService',NULL),('eaa2314f-42d4-4640-9889-5093b2ef7f39',1,'行李寄存',0,0,'2015-10-22 16:50:52',0,0,0,0,'site',1445503852233,'luggageService/luggageDeposit',0,NULL,NULL),('eb27030b-6f1f-48f3-84a3-4adea74b5e57',1,'临时乘机证明',0,0,'2015-10-22 16:58:16',0,0,0,0,'site',1445504296191,'convenientService/temporaryOpportunityToProve',0,NULL,NULL),('edaec508-6c24-40be-9c75-3038a43a6c17',1,'卫生间',0,0,'2015-10-22 16:44:55',0,0,0,0,'site',1445503495755,'publicFacilities/toilet',0,NULL,NULL),('eeb5376d-5007-40dc-8ccf-0e3a1afe1067',1,'遗失物品查询',0,0,'2015-10-22 17:08:20',0,0,0,0,'site',1445504900613,'lostGoodsQuery',0,'convenientService',NULL),('f2490c15-8da1-4be0-9843-1fd00f02fd13',1,'自主存取款一体机',0,0,'2015-10-22 17:03:29',0,0,0,0,'site',1445504609359,'ATM',0,'convenientService',NULL),('fd547642-1d57-4c06-9586-94e90705624b',1,'金融服务',0,0,'2015-10-22 17:07:47',0,0,0,0,'site',1445504867656,'currencyExchange',0,'convenientService',NULL);
/*!40000 ALTER TABLE `ticket_pageredirecttemplate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_article`
--

DROP TABLE IF EXISTS `ticket_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_article` (
  `id` varchar(255) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `introduce` varchar(255) DEFAULT NULL,
  `memberStore_id` varchar(255) DEFAULT NULL,
  `newsClass_id` varchar(50) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `viewPageRedirectTemplate_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_article`
--

LOCK TABLES `ticket_article` WRITE;
/*!40000 ALTER TABLE `ticket_article` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_commonannex`
--

DROP TABLE IF EXISTS `ticket_commonannex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_commonannex` (
  `id` varchar(255) NOT NULL,
  `annexPath` varchar(255) DEFAULT NULL,
  `annexSize` bigint(20) DEFAULT NULL,
  `annexType` int(11) DEFAULT NULL,
  `content` longtext,
  `entityId` varchar(36) DEFAULT NULL,
  `entityName` varchar(50) DEFAULT NULL,
  `extensionName` varchar(50) DEFAULT NULL,
  `originalName` varchar(255) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_commonannex`
--

LOCK TABLES `ticket_commonannex` WRITE;
/*!40000 ALTER TABLE `ticket_commonannex` DISABLE KEYS */;
INSERT INTO `ticket_commonannex` VALUES ('27a758e2-11b4-4a50-a331-7e20b57e12ae','/upload/site/image/201510/20151014060051.jpg',NULL,1,NULL,'34651617-4a6d-468f-82a5-4a8ae98b8acc','News','jpg','20150518053524.jpg',0,0,'2015-10-14 18:01:07',0,0,0,0,'site',1444816867694,'20150518053524.jpg'),('33b86805-8c4d-4971-b38d-be6ee3ea3d91','/upload/site/image/201510/20151014060103.jpg',NULL,2,NULL,'34651617-4a6d-468f-82a5-4a8ae98b8acc','News','jpg','20150526020007.jpg',0,0,'2015-10-14 18:01:07',0,0,0,1,'site',1444816867788,'20150526020007.jpg'),('63c2fbe8-994d-4019-9fa1-ea2501914ad1','/upload/site/image/201510/20151014060101.jpg',NULL,2,NULL,'34651617-4a6d-468f-82a5-4a8ae98b8acc','News','jpg','20150518044051.jpg',0,0,'2015-10-14 18:01:07',0,0,0,2,'site',1444816867725,'20150518044051.jpg'),('ce1b43f4-dfce-455b-8455-75d34599f7a9','/upload/site/image/201510/20151014060144.png',NULL,1,NULL,'6fb99d5d-441f-40d4-868e-f2369f383164','News','png','20150518030853.png',0,0,'2015-10-14 18:01:48',0,0,0,0,'site',1444816908453,'20150518030853.png'),('d1fb4ea8-f9c8-44a4-ad7b-6b6e51c6d8d3','/upload/site/image/201510/20151014055715.jpg',NULL,2,NULL,'6fb99d5d-441f-40d4-868e-f2369f383164','News','jpg','20150519012553.jpg',0,0,'2015-10-14 17:57:34',0,0,0,1,'site',1444816654937,'20150519012553.jpg'),('e7f6a5eb-3d70-4479-8895-f003b6b6b9fa','/upload/site/image/201510/20151014055713.jpg',NULL,2,NULL,'6fb99d5d-441f-40d4-868e-f2369f383164','News','jpg','20150519012529.jpg',0,0,'2015-10-14 17:57:34',0,0,0,2,'site',1444816654827,'20150519012529.jpg');
/*!40000 ALTER TABLE `ticket_commonannex` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_databasebackupslog`
--

DROP TABLE IF EXISTS `ticket_databasebackupslog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_databasebackupslog` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sqlPath` varchar(255) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  `systemUser_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_databasebackupslog`
--

LOCK TABLES `ticket_databasebackupslog` WRITE;
/*!40000 ALTER TABLE `ticket_databasebackupslog` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_databasebackupslog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_databaserestorelog`
--

DROP TABLE IF EXISTS `ticket_databaserestorelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_databaserestorelog` (
  `id` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  `systemUser_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_databaserestorelog`
--

LOCK TABLES `ticket_databaserestorelog` WRITE;
/*!40000 ALTER TABLE `ticket_databaserestorelog` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_databaserestorelog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_electrombile`
--

DROP TABLE IF EXISTS `ticket_electrombile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_electrombile` (
  `id` varchar(255) NOT NULL,
  `electrombileId` varchar(255) DEFAULT NULL,
  `ifUsing` int(11) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_electrombile`
--

LOCK TABLES `ticket_electrombile` WRITE;
/*!40000 ALTER TABLE `ticket_electrombile` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_electrombile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_infoposition`
--

DROP TABLE IF EXISTS `ticket_infoposition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_infoposition` (
  `id` varchar(255) NOT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `news_id` varchar(255) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_infoposition`
--

LOCK TABLES `ticket_infoposition` WRITE;
/*!40000 ALTER TABLE `ticket_infoposition` DISABLE KEYS */;
INSERT INTO `ticket_infoposition` VALUES ('5c7bdba5-f770-40e8-bb70-949994b6605b',24.849235,102.78275,'fbe00b7e-21ad-4d36-9343-85b678e8c10c',0,0,'2015-10-22 11:29:57',0,0,0,0,'site',1445484597831),('e0069b24-5135-4e14-bbb4-f660ddce0925',24.815655,102.492993,'3be9f488-497b-458f-8438-b7bdd3d62c49',0,0,'2015-10-21 10:40:26',0,0,0,0,'site',1445395226752);
/*!40000 ALTER TABLE `ticket_infoposition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_member`
--

DROP TABLE IF EXISTS `ticket_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_member` (
  `id` varchar(255) NOT NULL,
  `IDCard` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `loginName` varchar(255) DEFAULT NULL,
  `loginPwd` varchar(255) DEFAULT NULL,
  `memberLevel_id` varchar(255) DEFAULT NULL,
  `nickName` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  `realName` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  `weChatId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_member`
--

LOCK TABLES `ticket_member` WRITE;
/*!40000 ALTER TABLE `ticket_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_memberfavorite`
--

DROP TABLE IF EXISTS `ticket_memberfavorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_memberfavorite` (
  `id` varchar(255) NOT NULL,
  `member_id` varchar(255) DEFAULT NULL,
  `objectId` varchar(255) DEFAULT NULL,
  `objectType` varchar(255) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_memberfavorite`
--

LOCK TABLES `ticket_memberfavorite` WRITE;
/*!40000 ALTER TABLE `ticket_memberfavorite` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_memberfavorite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_memberlevel`
--

DROP TABLE IF EXISTS `ticket_memberlevel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_memberlevel` (
  `id` varchar(255) NOT NULL,
  `descript` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `needScore` varchar(255) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_memberlevel`
--

LOCK TABLES `ticket_memberlevel` WRITE;
/*!40000 ALTER TABLE `ticket_memberlevel` DISABLE KEYS */;
INSERT INTO `ticket_memberlevel` VALUES ('ce731a0a-840f-467c-9957-2c29ffedee79','金牌会员','金牌会员','1000',0,0,'2015-10-19 17:03:41',0,0,0,0,'site',1445245421068);
/*!40000 ALTER TABLE `ticket_memberlevel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_news`
--

DROP TABLE IF EXISTS `ticket_news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_news` (
  `id` varchar(255) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `content` longtext,
  `newsClass_id` varchar(255) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  `subTitle` varchar(255) DEFAULT NULL,
  `systemUser_id` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `viewPageRedirectTemplate_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_news`
--

LOCK TABLES `ticket_news` WRITE;
/*!40000 ALTER TABLE `ticket_news` DISABLE KEYS */;
INSERT INTO `ticket_news` VALUES ('3be9f488-497b-458f-8438-b7bdd3d62c49','小呆','飞机场的派出所是搞啥的<br />','463e2468-fc1a-414a-89de-c105461ed612',0,0,'2015-10-21 10:40:26',0,0,0,0,'site',1445395226750,'机场安全','8dea01b1-9bfd-4635-be39-c1fa6438b85b','机场派出所',NULL),('fbe00b7e-21ad-4d36-9343-85b678e8c10c','国内乘机须知','国内乘机须知国内乘机须知国内乘机须知国内乘机须知国内乘机须知国内乘机须知国内乘机须知国内乘机须知国内乘机须知','463e2468-fc1a-414a-89de-c105461ed612',0,0,'2015-10-22 11:29:57',0,0,0,0,'site',1445484597831,'国内乘机须知','8dea01b1-9bfd-4635-be39-c1fa6438b85b','国内乘机须知',NULL);
/*!40000 ALTER TABLE `ticket_news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_newsclass`
--

DROP TABLE IF EXISTS `ticket_newsclass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_newsclass` (
  `id` varchar(255) NOT NULL,
  `descript` varchar(255) DEFAULT NULL,
  `isDefaultShow` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `englishName` varchar(255) DEFAULT NULL,
  `listPageRedirectTemplate_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_newsclass`
--

LOCK TABLES `ticket_newsclass` WRITE;
/*!40000 ALTER TABLE `ticket_newsclass` DISABLE KEYS */;
INSERT INTO `ticket_newsclass` VALUES ('0567c8ab-63ff-4131-9be9-e3479429a4ad','机场咨询服务',0,'问询台','cac7d243-96f6-4674-9845-20bd4b5c6baf',0,0,'2015-10-22 14:48:59',0,0,0,0,'site',1445496539845,'wenxuntai','theQueryDesk',NULL),('08f863d7-9f49-48ab-8d89-4fc67f434817','乘客乘坐飞机的注意事项<br />',0,'乘机须知',NULL,0,0,'2015-10-22 14:19:19',0,0,0,0,'site',1445494759937,'chengjixuzhi','flightNotice',NULL),('0b468d88-641e-457f-ad2e-707710d4679c','行李托运相关信息<br />',0,'行李托运','9ef29329-cfca-4614-89d3-31bd21c383fe',0,0,'2015-10-22 14:33:57',0,0,0,0,'site',1445495637675,'行李托运','luggageConsignment',NULL),('0cad8378-e844-437e-aa9d-4d7e5653f694','机场自助充电站<br />',0,'自助充电站','349d6590-1917-44a3-a502-b4696d9dd32f',0,0,'2015-10-22 15:07:33',0,0,0,0,'site',1445497653777,'zizhuchongdianzhan','selfChargingStation',NULL),('177b1cb2-546c-4280-b0f1-352f420a8277','长水文化<br />',0,'长水文化',NULL,0,0,'2015-10-22 16:22:19',0,0,0,0,'site',1445502139952,'changshuiwenhua','culture',''),('198934b4-60d6-4b90-b121-58ccbbe37e22','商人旅客服务<br />',0,'商旅服务',NULL,0,0,'2015-10-22 15:19:56',0,0,0,0,'site',1445498396715,'shanglvfuwu','businessService',NULL),('20df2213-401e-4d58-85b8-a16e93f423f3','机场更衣室',0,'更衣室','349d6590-1917-44a3-a502-b4696d9dd32f',0,0,'2015-10-22 15:04:43',0,0,0,0,'site',1445497483339,'gengyishi','lockingRoom',NULL),('22945254-411b-4223-beed-c52716c37112','乘客在中国境内乘飞机的注意事项<br />',0,'国内乘机须知','08f863d7-9f49-48ab-8d89-4fc67f434817',0,0,'2015-10-22 14:20:43',0,0,0,0,'site',1445494843106,'guoneichengjixuzhi','domisticFlightNotice',NULL),('23d3e348-300c-498a-9675-472124aae7fe','商务中心<br />',0,'商务中心','198934b4-60d6-4b90-b121-58ccbbe37e22',0,0,'2015-10-22 15:20:39',0,0,0,0,'site',1445498439885,'shangwuzhognxin','businessCenter',NULL),('27ec9834-3713-40d9-b116-ebea58c6bd22','机场餐饮',0,'机场餐饮','e7e5e6a1-a3af-4683-a37d-6f2f6999653c',0,0,'2015-10-22 16:15:03',0,0,0,0,'site',1445501703249,'jichangcanyin','airportResturant',''),('2a750a3e-f34d-41ec-88c5-a307e27fec68','机场为病人提供的救护场所<br />',0,'医疗健康','cac7d243-96f6-4674-9845-20bd4b5c6baf',0,0,'2015-10-22 14:41:44',0,0,0,0,'site',1445496104321,'yiliaojiankang','healthCare',NULL),('2ae523b4-b655-47e7-a687-7de9efc256c2','为乘客办理临时乘机证明的相关流程信息<br />',0,'临时乘机证明','cac7d243-96f6-4674-9845-20bd4b5c6baf',0,0,'2015-10-22 14:44:17',0,0,0,0,'site',1445496257324,'linshichengjizhengming','temporaryFlightProve',NULL),('2b6c3601-0b9a-48e9-a0d1-582fe403b033','自动售货机',0,'自动售货机','349d6590-1917-44a3-a502-b4696d9dd32f',0,0,'2015-10-22 15:06:26',0,0,0,0,'site',1445497586233,'zidongshouhuoji','vendingMachine',NULL),('2e732441-f2ef-4339-bb74-26706513cb4c','火车票代售服务',0,'火车票代售','cac7d243-96f6-4674-9845-20bd4b5c6baf',0,0,'2015-10-22 14:53:25',0,0,0,0,'site',1445496805993,'huochepiaodaishou','trainTicketConsignee',NULL),('33bb5b1f-024c-4794-aedf-2ba0e63ffb2b','提供网上翻译服务<br />',0,'百度翻译','cac7d243-96f6-4674-9845-20bd4b5c6baf',0,0,'2015-10-22 14:51:52',0,0,0,0,'site',1445496712317,'baidufanyi','baiduTranslation',NULL),('349d6590-1917-44a3-a502-b4696d9dd32f','机场公共设施<br />',0,'公共设施',NULL,0,0,'2015-10-22 14:55:42',0,0,0,0,'site',1445496942127,'gonggongsheshi','publicFacilities',NULL),('366c0d27-cffd-45e4-a483-f08cf178849d','母婴室',0,'母婴室','3f48ee06-ddf3-493d-b0b1-ebf55de9669b',0,0,'2015-10-22 15:15:17',0,0,0,0,'site',1445498117940,'muyingshi','maternalAndInfantRoom',NULL),('37986525-f2ab-42e5-bc90-7e905358c09a','机场提供的自助存取款一体机<br />',0,'自主存取款一体机','cac7d243-96f6-4674-9845-20bd4b5c6baf',0,0,'2015-10-22 14:54:28',0,0,0,0,'site',1445496868433,'zizhucunqukuanyitiji','ATM',NULL),('37b41b6a-87e6-4f13-969b-5abe7a554225','机票改签',0,'机票改签','e7e5e6a1-a3af-4683-a37d-6f2f6999653c',0,0,'2015-10-22 16:16:56',0,0,0,0,'site',1445501816625,'jipiaogaiqian','mealTicket',''),('3933a5f3-672b-4b68-b044-f10b1ab58574','机场寻人服务信息<br />',0,'机场寻人','cac7d243-96f6-4674-9845-20bd4b5c6baf',0,0,'2015-10-22 14:45:45',0,0,0,0,'site',1445496345348,'jichangsxunren','airportQueryPeople',NULL),('3b539493-1964-432e-8f03-0e344a51b9c3','<p>\r\n	百事特贵宾厅\r\n</p>',0,'百事特贵宾厅','198934b4-60d6-4b90-b121-58ccbbe37e22',0,0,'2015-10-22 15:22:20',0,0,0,0,'site',1445498540668,'baishiteguibinting','baishiteLounge',NULL),('3f48ee06-ddf3-493d-b0b1-ebf55de9669b','爱心服务',0,'爱心服务',NULL,0,0,'2015-10-22 15:12:37',0,0,0,0,'site',1445497957899,'aixinfuwu','caringService',NULL),('4a865ec5-3a17-4d5d-93de-6b1d6e8d892a','机场儿童娱乐区<br />',0,'儿童娱乐区','349d6590-1917-44a3-a502-b4696d9dd32f',0,0,'2015-10-22 15:05:35',0,0,0,0,'site',1445497535166,'ertongyulequ','childrenJokePlace',NULL),('4c961cc2-adc3-456d-9bee-4834ab0c0e41','为没有家人陪伴的老人及儿童服务<br />',0,'无人伴护老人及儿童','3f48ee06-ddf3-493d-b0b1-ebf55de9669b',0,0,'2015-10-22 15:18:35',0,0,0,0,'site',1445498315200,'wurenbanhu','noCompanyPeople',NULL),('4ca6958f-879d-4949-8d76-5681e4c4e074','文化展示&nbsp;&nbsp;&nbsp;&nbsp;',0,'文化展示','177b1cb2-546c-4280-b0f1-352f420a8277',0,0,'2015-10-22 16:24:07',0,0,0,0,'site',1445502247417,'wenhuazhanshi','cultureShow',''),('4e454307-2a7f-47c0-a353-57b688118205','相关的行李查询信息<br />',0,'行李查询','9ef29329-cfca-4614-89d3-31bd21c383fe',0,0,'2015-10-22 14:38:43',0,0,0,0,'site',1445495923815,'xinglichaxun','luggageQuery',NULL),('5246a203-50f0-45f4-8557-e3a41dc77885','乘客需要跨国乘机须知的注意事项<br />',0,'国际乘机须知','08f863d7-9f49-48ab-8d89-4fc67f434817',0,0,'2015-10-22 14:21:42',0,0,0,0,'site',1445494902707,'guojichengjixuzhi','internationalFlightNotice',NULL),('5c41f23e-64aa-4a29-9e8b-5392c866db02','办理过境签相关信息<br />',0,'72小时过境签','cac7d243-96f6-4674-9845-20bd4b5c6baf',0,0,'2015-10-22 14:46:57',0,0,0,0,'site',1445496417962,'guojingqian','seventytwoTransitSign',NULL),('5c988eda-f4ac-4207-8ad8-5f3385157cba','老年人，残疾人，行动不便的人专用卫生间<br />',0,'老残障人卫生间','3f48ee06-ddf3-493d-b0b1-ebf55de9669b',0,0,'2015-10-22 15:14:02',0,0,0,0,'site',1445498042826,'laocanzhangrenweishengjain','disabledPeopleToilet',NULL),('61915ad8-d630-4d24-a807-b47aed894eb6','机场派出所<br />',0,'机场派出所','cac7d243-96f6-4674-9845-20bd4b5c6baf',0,0,'2015-10-22 14:44:56',0,0,0,0,'site',1445496296582,'jichangpaichusuo','airportPoliceStation',NULL),('6a8f933a-d97e-4216-95b5-4195555c1284','机票预定<br />',0,'机票预定',NULL,0,0,'2015-10-22 16:21:24',0,0,0,0,'site',1445502084840,'jipiaoyuding','ticketBook',''),('6ba191d6-7ecc-4a7f-b86f-882b703d095f','机场酒店',0,'机场酒店','e7e5e6a1-a3af-4683-a37d-6f2f6999653c',0,0,'2015-10-22 16:15:34',0,0,0,0,'site',1445501734226,'jichangjiudian','airportHotel',''),('6c897125-bcce-4410-982a-a2aff5377628','手推车<br />',0,'手推车','349d6590-1917-44a3-a502-b4696d9dd32f',0,0,'2015-10-22 15:08:00',0,0,0,0,'site',1445497680939,'shoutuiche','trolley',NULL),('6f74d74a-6a9d-42f0-b2b2-86e1cf4fd372','机场祈祷室',0,'祈祷室','349d6590-1917-44a3-a502-b4696d9dd32f',0,0,'2015-10-22 15:04:11',0,0,0,0,'site',1445497451504,'qidaoshi','prayerRoom',NULL),('75693fcc-a786-4388-9364-737498237537','航班查询',0,'航班查询',NULL,0,0,'2015-10-22 15:44:35',0,0,0,0,'site',1445499875381,'hangbanchaxun','flightQuery',''),('77c91b68-412e-499a-bfb2-e904498fa43e','机场购物<br />',0,'机场购物','e7e5e6a1-a3af-4683-a37d-6f2f6999653c',0,0,'2015-10-22 16:16:07',0,0,0,0,'site',1445501767368,'jichanggouwu','airportShopping',''),('7a96a6cc-ae1a-4eed-8014-b002cbb6ed50','wifi取号机<br />',0,'WiFi取号机','349d6590-1917-44a3-a502-b4696d9dd32f',0,0,'2015-10-22 15:08:48',0,0,0,0,'site',1445497728497,'wifiquhaoji','wifiGetNumberMachine',NULL),('7e1fc23e-7fc3-4e0e-b67e-34c526167652','关注我们',0,'关注我们',NULL,0,0,'2015-10-22 16:25:55',0,0,0,0,'site',1445502355983,'guanzhuwomen','focusUs',''),('874495d2-e287-4a25-951c-8ad0be91ef82','城市候机楼',0,'城市候机楼','349d6590-1917-44a3-a502-b4696d9dd32f',0,0,'2015-10-22 15:10:35',0,0,0,0,'site',1445497835500,'chengshihoujilou','cityTeminal',NULL),('88cff97c-116f-445e-adfd-48442438d5fc','免责声明<br />',0,'免责声明',NULL,0,0,'2015-10-22 16:26:51',0,0,0,0,'site',1445502411515,'mianzeshengming','disclaimer',''),('8cc38c44-4234-4c05-8fa0-60275a69c1e1','婴幼儿手推车',0,'婴幼儿手推车','3f48ee06-ddf3-493d-b0b1-ebf55de9669b',0,0,'2015-10-22 15:16:56',0,0,0,0,'site',1445498216825,'yingyouershoutuiche','babyTrolley',NULL),('90aa446a-8cd2-403e-ad4f-81aa1848a5ae','国内乘机须知',0,'国内乘机须知','041d817f-abd1-48fc-81d1-b594fdb23982',0,0,'2015-10-19 17:21:17',0,0,0,0,'site',1445246477086,NULL,NULL,NULL),('90b46c99-5c32-4ce4-b59f-7b7300a0cba7','头等舱商务舱服务<br />',0,'头等舱商务舱服务','198934b4-60d6-4b90-b121-58ccbbe37e22',0,0,'2015-10-22 15:42:55',0,0,0,0,'site',1445499775464,'shangwucangfuwu','firstClassBusinessClassService',''),('93f9dcf9-c1fa-43d2-a766-5544f73f90a5','提供相关的邮政服务<br />',0,'中国邮政','cac7d243-96f6-4674-9845-20bd4b5c6baf',0,0,'2015-10-22 14:51:11',0,0,0,0,'site',1445496671167,'zhongguoyouzheng','ChinaPost',NULL),('9caf2118-0b9a-4b43-b9d6-4f0df9848745','机场对行动不便的人提供轮椅服务<br />',0,'轮椅服务','3f48ee06-ddf3-493d-b0b1-ebf55de9669b',0,0,'2015-10-22 15:16:04',0,0,0,0,'site',1445498164912,'lunyifuwu','wheelchairService',NULL),('9ef29329-cfca-4614-89d3-31bd21c383fe','提供乘客相关的行李代办服务流程<br />',0,'行李服务',NULL,0,0,'2015-10-22 14:31:24',0,0,0,0,'site',1445495484785,'xinglifuwu','luggageService',NULL),('aa6b16b0-8498-4ae9-8d81-2727d55dae44','网上值机<br />',0,'网上值机',NULL,0,0,'2015-10-22 15:45:55',0,0,0,0,'site',1445499955959,'wangshangzhiji','checkinOnline',''),('b297d515-16c8-44a7-a2d5-af21004f15d2','提供货币兑换等服务<br />',0,'金融服务','cac7d243-96f6-4674-9845-20bd4b5c6baf',0,0,'2015-10-22 14:50:20',0,0,0,0,'site',1445496620007,'jinrongfuwu','financeService',NULL),('b3f09e4f-96e6-4b64-a3d5-0c26105a83d6','公告栏提供近期的招商公告和活动信息<br />',0,'公告栏','ccfc2b09-0434-4334-bf8f-629ba8bb419c',0,0,'2015-10-22 14:29:05',0,0,0,0,'site',1445495345897,'gonggaolan','noticeBoard',NULL),('bc6d0f7d-cd49-4f0e-8321-1b3769b7e855','乘客遗失物品查询平台<br />',0,'遗失物品查询','cac7d243-96f6-4674-9845-20bd4b5c6baf',0,0,'2015-10-22 14:42:34',0,0,0,0,'site',1445496154397,'yishiwupinchaxun','lostGoodsQuery',NULL),('c005d868-ee88-4a1e-bc92-b55335383428','机场卫生间<br />',0,'卫生间','349d6590-1917-44a3-a502-b4696d9dd32f',0,0,'2015-10-22 15:02:36',0,0,0,0,'site',1445497356937,'weishengjian','toilet',NULL),('c68cb84c-277d-4f98-b4ef-9acc7b9dc066','行李打包相关信息<br />',0,'行李打包','9ef29329-cfca-4614-89d3-31bd21c383fe',0,0,'2015-10-22 14:32:24',0,0,0,0,'site',1445495544041,'xinglidabao','luggagePack',NULL),('c82f438e-d0f4-47e0-a9b2-3df240639b79','机场实时发布的重要新闻信息<br />',0,'机场要闻','ccfc2b09-0434-4334-bf8f-629ba8bb419c',0,0,'2015-10-22 14:27:24',0,0,0,0,'site',1445495244845,'jichangyaowen','airportFocusNews','88e12fcf-ae6c-484c-97a8-8d6ba6c5bc8d'),('c8b871d6-f5a9-44bb-8319-d56f2154ba11','国外乘机须知',0,'国内乘机须知','a2eed3ed-71d0-49b0-b354-0696ef530dc0',0,0,'2015-10-19 17:38:19',0,0,0,0,'site',1445247499657,NULL,NULL,NULL),('cac7d243-96f6-4674-9845-20bd4b5c6baf','为乘客提供方便的机场服务<br />',0,'便民服务',NULL,0,0,'2015-10-22 14:40:22',0,0,0,0,'site',1445496022737,'bianminfuwu','convenientService',NULL),('cbd4f6c0-f20f-4848-855f-2df8978da5be','附有相关图片的新闻信息<br />',0,'图片新闻','ccfc2b09-0434-4334-bf8f-629ba8bb419c',0,0,'2015-10-22 14:26:27',0,0,0,0,'site',1445495187584,'tupianxinwen','pictureNews',NULL),('ccfc2b09-0434-4334-bf8f-629ba8bb419c','机场相关的新闻和实时发布的通知消息<br />',0,'新闻公告',NULL,0,0,'2015-10-22 14:24:41',0,0,0,0,'site',1445495081319,'xinwengonggao','newsNotice',NULL),('cd3a8950-c76b-43f0-bd64-ded84d4d7e3b','保险购买<br />',0,'保险',NULL,0,0,'2015-10-22 15:46:54',0,0,0,0,'site',1445500014981,'baoxian','insurance',''),('d41eadf2-7504-4d89-83db-4c728a6e1cbf','计时休息室',0,'计时休息室','198934b4-60d6-4b90-b121-58ccbbe37e22',0,0,'2015-10-22 15:21:24',0,0,0,0,'site',1445498484750,'jishixiuxishi','timeLounge',NULL),('d568368f-a05f-49df-b293-10915874a45b','国外乘机须知',0,'国外乘机须知','041d817f-abd1-48fc-81d1-b594fdb23982',0,0,'2015-10-19 17:21:32',0,0,0,0,'site',1445246492263,NULL,NULL,NULL),('d6b983c3-8981-42e4-a228-ca5ac54e0090','',0,'国外乘机须知','a2eed3ed-71d0-49b0-b354-0696ef530dc0',0,0,'2015-10-19 17:38:33',0,0,0,0,'site',1445247513566,NULL,NULL,NULL),('e2d37879-a421-465e-a521-56a593759735','行李寄存相关的信息<br />',0,'行李寄存','9ef29329-cfca-4614-89d3-31bd21c383fe',0,0,'2015-10-22 14:35:39',0,0,0,0,'site',1445495739655,'xinglijicun','luggageStore',NULL),('e7e5e6a1-a3af-4683-a37d-6f2f6999653c','航班延误',0,'航班延误',NULL,0,0,'2015-10-22 15:48:15',0,0,0,0,'site',1445500095383,'hangbanyanwu','flightDelay',''),('ed43a441-541e-4628-af7f-7d88ee201152','航班实时信息以及航班的变更信息<br />',0,'航班动态',NULL,0,0,'2015-10-22 14:23:41',0,0,0,0,'site',1445495021405,'hangbandongtai','flightDynamic',NULL),('eefc2dd8-277d-47d3-a3f9-5d6ae3fe6bff','机场吸烟室<br />',0,'吸烟室','349d6590-1917-44a3-a502-b4696d9dd32f',0,0,'2015-10-22 15:03:25',0,0,0,0,'site',1445497405648,'xiyanshi','smokingRoom',NULL),('f77ceecb-c539-4d57-aed9-a54c5f6d25e2','落地签自拍系统',0,'落地签自拍系统','cac7d243-96f6-4674-9845-20bd4b5c6baf',0,0,'2015-10-22 14:48:15',0,0,0,0,'site',1445496495383,'luodiqianzipaixitong','landSelfieSystem',NULL);
/*!40000 ALTER TABLE `ticket_newsclass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_newscomment`
--

DROP TABLE IF EXISTS `ticket_newscomment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_newscomment` (
  `id` varchar(255) NOT NULL,
  `content` longtext,
  `memberIp` varchar(255) DEFAULT NULL,
  `member_id` varchar(255) DEFAULT NULL,
  `news_id` varchar(255) DEFAULT NULL,
  `star` int(11) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_newscomment`
--

LOCK TABLES `ticket_newscomment` WRITE;
/*!40000 ALTER TABLE `ticket_newscomment` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_newscomment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_privilegeinfo`
--

DROP TABLE IF EXISTS `ticket_privilegeinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_privilegeinfo` (
  `id` varchar(255) NOT NULL,
  `descript` varchar(255) DEFAULT NULL,
  `includeMethods` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_privilegeinfo`
--

LOCK TABLES `ticket_privilegeinfo` WRITE;
/*!40000 ALTER TABLE `ticket_privilegeinfo` DISABLE KEYS */;
INSERT INTO `ticket_privilegeinfo` VALUES ('9c2ce025-f730-495b-807b-dc9e5570c123','删除<br />','delete,remove','删除',0,0,'2015-10-20 17:31:25',0,0,0,0,'site',1445333485146),('aa2e8349-05c9-44db-a0d7-527c64c9cf75','更新数据库信息<br />','update,merge','更新',0,0,'2015-10-19 17:03:15',0,0,0,0,'site',1445245395630),('b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','添加的权限<br />','add,persist','添加',0,0,'2015-10-19 15:43:27',0,0,0,0,'site',1445240607780);
/*!40000 ALTER TABLE `ticket_privilegeinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_roleinfo`
--

DROP TABLE IF EXISTS `ticket_roleinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_roleinfo` (
  `id` varchar(255) NOT NULL,
  `descript` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_roleinfo`
--

LOCK TABLES `ticket_roleinfo` WRITE;
/*!40000 ALTER TABLE `ticket_roleinfo` DISABLE KEYS */;
INSERT INTO `ticket_roleinfo` VALUES ('2a1dcfe2-529c-4807-92bb-5f9a63dec061','管理系统','超级管理员',0,0,'2015-10-19 15:42:41',0,0,0,0,'site',1445240561491),('442941c4-17f1-482f-94d8-d9d39860712d','部门经理','部门经理',0,0,'2015-10-20 17:38:22',0,0,0,0,'site',1445333902604);
/*!40000 ALTER TABLE `ticket_roleinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_roleprivilege`
--

DROP TABLE IF EXISTS `ticket_roleprivilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_roleprivilege` (
  `id` varchar(255) NOT NULL,
  `privilegeId` varchar(255) DEFAULT NULL,
  `roleId` varchar(255) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  `systemModuleId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_roleprivilege`
--

LOCK TABLES `ticket_roleprivilege` WRITE;
/*!40000 ALTER TABLE `ticket_roleprivilege` DISABLE KEYS */;
INSERT INTO `ticket_roleprivilege` VALUES ('00b9e232-9ba9-47e8-b83d-b5bc115e7f54','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:46',0,0,0,0,'site',1445494666338,'33db1afb-862b-44a1-b54e-2fef5b2eb003'),('00f53d97-314f-44fc-a807-12ca87467203','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:30',0,0,0,0,'site',1445474370436,'4738dbcb-bdc9-496c-ab7f-cd1a4d4b5a8b'),('02166352-0538-460c-837e-549dd67d57f9','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:36',0,0,0,0,'site',1445474376088,'f0af133d-e253-490d-859e-481c072393ee'),('02e13ba9-93de-4762-bf3f-4f34a79e17cb','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:39',0,0,0,0,'site',1445494659220,'35b400fb-2f3f-42cd-be0d-580d06b60d86'),('03f9b3d5-dc94-46b2-99c0-4bd2a44cd991','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:43',0,0,0,0,'site',1445494663456,'8161bdd4-2f90-4079-a434-8f1d8d937049'),('03fab623-6f00-403f-b3cd-49993a90a28d','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:36',0,0,0,0,'site',1445474376772,'b783233e-d891-4651-8df3-aaae13ab0b69'),('03fcb77a-873b-452d-8365-87861edce7ac','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:46',0,0,0,0,'site',1445494666946,'22b70bc4-6dbb-4015-953a-3c092eb9798c'),('051908bf-07a6-432f-9ccb-8afe95b93970','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:32',0,0,0,0,'site',1445474372580,'d3759e96-1aec-4d5f-8bd1-f1b9e7d630ad'),('061f9c7e-4c23-450c-bd64-071a56a93f82','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:38',0,0,0,0,'site',1445474378964,'d6f5537b-4534-4ba3-a351-f6be97537d2e'),('067f98d1-6a4b-4f75-979e-57c7b56612b7','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:40',0,0,0,0,'site',1445494660705,'e577d00a-9afe-4f9c-a2f6-b84be30a0884'),('07c15ef4-b594-40a4-9dba-4deb6e2369d9','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:35',0,0,0,0,'site',1445474375337,'3cad172d-eaba-4748-903e-617ea2b6df8f'),('07f1bf08-fc7f-4d4c-ac2b-b91eadb9646e','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:44',0,0,0,0,'site',1445494664415,'f0af133d-e253-490d-859e-481c072393ee'),('083b7ce9-a6d0-4f1b-b2aa-dbf3cc508acb','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:39',0,0,0,0,'site',1445494659130,'337d7c3d-27ba-4d39-92c7-39f72fcf2db4'),('09d5269a-f596-4c44-86e6-05600ecbc89b','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:34',0,0,0,0,'site',1445474374997,'e6c92868-3109-4e0b-8fa9-6d781c873a19'),('0a393057-4454-4373-a174-eb1e970479ba','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:43',0,0,0,0,'site',1445494663331,'e6c92868-3109-4e0b-8fa9-6d781c873a19'),('0b14d30d-8188-4981-b63d-1d0064a4d6fc','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:35',0,0,0,0,'site',1445474375739,'879b2bc4-e3d4-42de-bfc0-43774cd0482f'),('0b4fbbfc-bdd7-4dc0-8062-1ab5875f141e','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:40',0,0,0,0,'site',1445494660736,'ae9e7b3c-2dfe-49fb-b48a-5cc7d3644e3e'),('0bf9dfa8-9c5f-43dd-9061-6ec74de19caa','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:38',0,0,0,0,'site',1445474378314,'2cd3982c-d469-440d-922a-75e965968f23'),('0ca367c7-0d0c-45af-abb8-8e9082d2c015','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:32',0,0,0,0,'site',1445474372721,'fdb9fe15-c954-4bf7-adf8-0893038a8b71'),('0d1b347c-0140-43d2-ab52-a17e027deca1','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:36',0,0,0,0,'site',1445474376513,'10cc580a-7f73-41b0-8f8b-d264bb3b892f'),('0e2461e4-f4b4-4f76-a22d-bf3804c0b7a5','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:43',0,0,0,0,'site',1445494663714,'ac4ccdbc-4b5b-46e5-862f-ea8e319b56a5'),('0f22cd1d-5a94-455e-95e2-dcc836da94c4','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:43',0,0,0,0,'site',1445494663156,'7cd4debb-1981-4c07-867c-6d7f6fc1e955'),('0f2e04c8-a59a-4474-aa42-ecd2681c568f','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:35',0,0,0,0,'site',1445474375621,'ac4ccdbc-4b5b-46e5-862f-ea8e319b56a5'),('0f73fb05-4594-4872-8abe-b1aa0dfa5edb','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:40',0,0,0,0,'site',1445494660678,'e577d00a-9afe-4f9c-a2f6-b84be30a0884'),('0f8b8e27-5c9d-4bda-985f-1df1a8015f3f','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:41',0,0,0,0,'site',1445494661112,'d3759e96-1aec-4d5f-8bd1-f1b9e7d630ad'),('0fea2f5f-d073-4fea-8228-f27ecedc2fee','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:31',0,0,0,0,'site',1445474371996,'e577d00a-9afe-4f9c-a2f6-b84be30a0884'),('10499768-d29c-456a-81e7-17b35fb860c1','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:36',0,0,0,0,'site',1445474376579,'10cc580a-7f73-41b0-8f8b-d264bb3b892f'),('106c9a90-519b-4b85-bc9c-eff270aca599','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:45',0,0,0,0,'site',1445494665132,'10cc580a-7f73-41b0-8f8b-d264bb3b892f'),('109b3e38-151c-4521-9f1a-229169316ac3','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:35',0,0,0,0,'site',1445474375704,'879b2bc4-e3d4-42de-bfc0-43774cd0482f'),('117d473e-5974-48b6-8f99-832c50fcede3','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:39',0,0,0,0,'site',1445494659512,'4b18167e-ecad-489e-87b6-44c90d04c9a4'),('11c5f5b5-46d2-46f7-a2cf-5bdeb2191085','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:34',0,0,0,0,'site',1445474374913,'e6c92868-3109-4e0b-8fa9-6d781c873a19'),('12dd8f22-23d2-4361-ac99-92888209046c','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:33',0,0,0,0,'site',1445474373612,'af9a68c5-84b9-4ffd-b238-93711b0797a8'),('138d836a-c1b7-467c-8026-e6d0e37107ef','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:37',0,0,0,0,'site',1445474377730,'08f8586c-9013-48d0-81ec-362be4a97d4a'),('158c05b8-4acd-4e9b-a9cb-3599592f6735','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:43',0,0,0,0,'site',1445494663187,'7cd4debb-1981-4c07-867c-6d7f6fc1e955'),('168e1190-b36b-4eb6-80ce-a837890ae6db','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:43',0,0,0,0,'site',1445494663014,'6661c054-ba77-4ef7-8036-66b36073c3b0'),('1799536a-61ea-444e-9551-67bcb3157b16','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:34',0,0,0,0,'site',1445474374230,'7b52f775-b5dc-4d73-b041-452d5c4d0805'),('17b08fd1-8f0e-41cd-a893-49a55334a978','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:32',0,0,0,0,'site',1445474372788,'b2240fb3-61e2-427e-8cd4-ec474380594d'),('18de0552-0a8c-4f72-896d-f2425b42eab8','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:41',0,0,0,0,'site',1445494661170,'d3759e96-1aec-4d5f-8bd1-f1b9e7d630ad'),('191d42cc-3fb5-44db-8593-42dd3467f93a','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:32',0,0,0,0,'site',1445474372145,'ae9e7b3c-2dfe-49fb-b48a-5cc7d3644e3e'),('1948b3f5-958d-44a0-9a6c-269c975641b2','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:34',0,0,0,0,'site',1445474374537,'6661c054-ba77-4ef7-8036-66b36073c3b0'),('1b02f80c-338a-4474-b0ca-6bbd0679a0fe','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:33',0,0,0,0,'site',1445474373071,'5a57bdb0-33df-4879-a1a1-0a2b805b1676'),('1b70bcef-2079-412c-9099-5dc1a9d63b7d','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:42',0,0,0,0,'site',1445494662322,'a0b1bbc8-0fab-4997-92dc-d216898562ac'),('1ba758b3-46d7-48c9-95a9-7a3ad26dae9d','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:36',0,0,0,0,'site',1445474376697,'9b427f92-d323-4800-b98f-151cd6486237'),('1bd1762e-f397-4a5d-8091-286c219f280d','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:42',0,0,0,0,'site',1445494662831,'9d12aa16-5cdf-4fb7-b7d3-81058ed8024f'),('1c0badd9-cfcf-4a8f-8068-19fb20aa534e','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:38',0,0,0,0,'site',1445494658919,'4738dbcb-bdc9-496c-ab7f-cd1a4d4b5a8b'),('1c39e316-d07c-4400-9ec3-6a67e93907f6','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:47',0,0,0,0,'site',1445494667416,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('1c6f5a7b-be77-4362-aa87-d366b1052e8b','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:35',0,0,0,0,'site',1445474375938,'8ecea40a-bd6b-4dfe-914c-aa44bc5fc201'),('1e4e3a44-8327-4d84-af86-d876d7dc8305','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:39',0,0,0,0,'site',1445494659394,'49019030-af3b-4edb-bec3-ae7e6c55a279'),('1ed077ea-dcaa-46ed-ad0c-73b088ef4b3b','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:30',0,0,0,0,'site',1445474370370,'4738dbcb-bdc9-496c-ab7f-cd1a4d4b5a8b'),('1f7a4d67-f273-46c5-9232-e6d2f9a286b6','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:45',0,0,0,0,'site',1445494665087,'10cc580a-7f73-41b0-8f8b-d264bb3b892f'),('21410cf9-4dca-438c-bd66-2db2a75f7cf5','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:30',0,0,0,0,'site',1445474370537,'d39e3ad7-a482-462f-9eb9-454b26b9db5b'),('2276d6fb-62f7-4781-81d5-7e8321705e8e','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:38',0,0,0,0,'site',1445474378205,'7cbad721-91a7-460b-8916-bb19bbe5e0f2'),('22a48e4d-8b0c-4bc2-a08c-0b2e4c7bead9','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:37',0,0,0,0,'site',1445474377889,'dbe283e0-6fb1-4b13-a57a-0517a59f5474'),('2352d0d1-2273-4ffc-88e3-67f3b2d60bd2','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:33',0,0,0,0,'site',1445474373170,'d5a06e48-e12f-4146-b07e-7f7e85b1f1c0'),('2373990d-90ed-464f-8fd8-a2354c669ba3','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:42',0,0,0,0,'site',1445494662045,'8df8840d-610b-409d-a2bb-e912d3c20ec5'),('23e61a19-ab9e-4dd9-aeab-aee0f2bc03f2','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:35',0,0,0,0,'site',1445474375663,'879b2bc4-e3d4-42de-bfc0-43774cd0482f'),('24a3d0b8-f1f7-4879-bb96-91167b9c9450','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:36',0,0,0,0,'site',1445474376263,'079f46b3-b738-4afb-8524-f624f2e96766'),('2559bb3e-3ebb-44e4-b247-703a91fc0f16','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:32',0,0,0,0,'site',1445474372545,'d3759e96-1aec-4d5f-8bd1-f1b9e7d630ad'),('25908207-6fd2-43c0-a8c2-e9b14148e1bb','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:40',0,0,0,0,'site',1445494660823,'1b219275-1f31-4d72-ac8a-86f62caac828'),('25cbaf3f-3307-4304-ad71-32555ae00931','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:41',0,0,0,0,'site',1445494661695,'5a57bdb0-33df-4879-a1a1-0a2b805b1676'),('25ddb8a3-1ee5-4afe-a6f6-3912b7b417d1','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:43',0,0,0,0,'site',1445494663804,'879b2bc4-e3d4-42de-bfc0-43774cd0482f'),('26151a18-5c8c-488c-b462-5df5f3025555','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:43',0,0,0,0,'site',1445494663773,'879b2bc4-e3d4-42de-bfc0-43774cd0482f'),('2632912e-9ba8-4d51-894b-2d6528d91452','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:37',0,0,0,0,'site',1445474377096,'39f7e44b-d90a-41aa-8327-895aee0ea7ec'),('26337ee2-fff5-472e-b84d-a097316fee68','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:45',0,0,0,0,'site',1445494665255,'9b427f92-d323-4800-b98f-151cd6486237'),('278b8b58-4e4e-452c-93c8-77b6b36b54f7','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:46',0,0,0,0,'site',1445494666448,'b8a2a9e1-3f6d-4ec3-954f-5aa96bb1c3c6'),('28c2a05f-373f-448b-b075-98e49a2c7299','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:36',0,0,0,0,'site',1445474376346,'079f46b3-b738-4afb-8524-f624f2e96766'),('28d03213-d334-4e5d-9dc1-442f5819a416','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:30',0,0,0,0,'site',1445474370604,'337d7c3d-27ba-4d39-92c7-39f72fcf2db4'),('28da0672-c4c7-408e-99e1-e04add993357','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:45',0,0,0,0,'site',1445494665954,'b6f04f26-86b9-4a47-8fff-55f7b40d61e0'),('293f6f66-9c01-4277-95a6-2493319b343a','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:40',0,0,0,0,'site',1445494660462,'a589064e-4bb9-4d62-a100-21f734154200'),('297511f6-7df3-4acf-a1ba-39bf7123e300','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:33',0,0,0,0,'site',1445474373504,'8df8840d-610b-409d-a2bb-e912d3c20ec5'),('2a32c693-0c8c-4f42-927a-10198543fbf9','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:39',0,0,0,0,'site',1445494659005,'d39e3ad7-a482-462f-9eb9-454b26b9db5b'),('2b45b0ec-aca9-4e05-b777-7c7bebb1a47c','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:47',0,0,0,0,'site',1445494667113,'d6f5537b-4534-4ba3-a351-f6be97537d2e'),('2b83c7cb-1e2d-44bf-abb1-5648c7439def','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:33',0,0,0,0,'site',1445474373978,'ce2eaf51-93f2-49c4-b9bf-a4fed3e7f599'),('2bdb8de2-4938-473b-9eb7-2a20fd2d324a','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:32',0,0,0,0,'site',1445474372679,'fdb9fe15-c954-4bf7-adf8-0893038a8b71'),('2be49d00-ed0f-4eb1-8677-428ef749e526','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:44',0,0,0,0,'site',1445494664735,'6b54fa40-45b7-4c08-81dc-28a4e6ca5c82'),('2d3f3082-656e-44ef-94fa-9af9e1aa41ed','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:35',0,0,0,0,'site',1445474375546,'ac4ccdbc-4b5b-46e5-862f-ea8e319b56a5'),('2dd23625-4202-48e0-87de-db617b2d97f8','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:42',0,0,0,0,'site',1445494662523,'ce2eaf51-93f2-49c4-b9bf-a4fed3e7f599'),('2e129325-ef8e-4750-b26f-d0cc8424c441','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:44',0,0,0,0,'site',1445494664180,'f0af133d-e253-490d-859e-481c072393ee'),('2eea659d-3f37-439b-a213-28338fd91ea2','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:33',0,0,0,0,'site',1445474373355,'f78238fd-a4e5-464c-b474-4f184493296b'),('2f8d345a-1995-459b-a4d9-b9de95f3a0c0','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:46',0,0,0,0,'site',1445494666749,'2cd3982c-d469-440d-922a-75e965968f23'),('30880365-df71-4ec0-b9a3-a12daabaac79','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:39',0,0,0,0,'site',1445474379338,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('312d57de-6cb5-40ba-af68-39588e1f6cff','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:38',0,0,0,0,'site',1445494658977,'d39e3ad7-a482-462f-9eb9-454b26b9db5b'),('31319595-22e4-4a31-92a0-18d8519002f8','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:31',0,0,0,0,'site',1445474371521,'ff956575-07a9-4984-b068-c85473609687'),('31add873-9e67-4165-ae9b-c362a7dd5ab7','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:33',0,0,0,0,'site',1445474373647,'af9a68c5-84b9-4ffd-b238-93711b0797a8'),('32a289a3-1a50-4997-b01e-4f400eae3f00','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:42',0,0,0,0,'site',1445494662162,'db188e80-1d5d-4d3f-81b3-484206ee2107'),('33342a87-c24e-4db4-bf5b-f28596a327a3','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:44',0,0,0,0,'site',1445494664004,'8ecea40a-bd6b-4dfe-914c-aa44bc5fc201'),('34d9167e-3248-44e3-817d-f0fcbbeaf8c5','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:34',0,0,0,0,'site',1445474374013,'ce2eaf51-93f2-49c4-b9bf-a4fed3e7f599'),('359ca386-eed4-4568-85f3-87ad3db0ac60','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:42',0,0,0,0,'site',1445494662581,'a11d5394-3b77-4b5f-8556-b9235d6c0daf'),('365850b7-2b5f-490a-9400-1e3f55fac616','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:31',0,0,0,0,'site',1445474371679,'424e93ea-a975-48d3-b53e-3ced5f795a98'),('37357bd2-b58d-47b5-8a38-fff4e85f7890','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:31',0,0,0,0,'site',1445474371913,'e577d00a-9afe-4f9c-a2f6-b84be30a0884'),('37d94caf-cad3-4e7c-8007-bde9487298ed','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:37',0,0,0,0,'site',1445474377280,'b6f04f26-86b9-4a47-8fff-55f7b40d61e0'),('388115e7-8c3c-49c9-aa1e-6c6f5f90de81','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:42',0,0,0,0,'site',1445494662890,'9d12aa16-5cdf-4fb7-b7d3-81058ed8024f'),('39bec748-5837-4b07-aa67-4218c4235f4e','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:30',0,0,0,0,'site',1445474370338,'159648a9-ed33-4a26-9da0-c9aab2ff4a9f'),('3a510c46-1fc3-48ba-88d8-0f6457cd91b2','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:41',0,0,0,0,'site',1445494661022,'9cc1a792-91bf-433d-963c-d6b8b088438e'),('3aa7694d-b474-4a0d-9bd8-43064b629926','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:43',0,0,0,0,'site',1445494663054,'6661c054-ba77-4ef7-8036-66b36073c3b0'),('3b1feec9-0d11-4c8b-bb79-992262fe4257','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:37',0,0,0,0,'site',1445474377131,'38cda477-f403-4720-9d05-c90eef30c73d'),('3b64965a-57bc-4fc5-b9e2-0583c9021668','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:46',0,0,0,0,'site',1445494666872,'8e22e8c8-649b-4286-baf4-0c48e88fdfb6'),('3b8b85a3-987b-4eba-954d-a4bb1142f775','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:31',0,0,0,0,'site',1445474371954,'e577d00a-9afe-4f9c-a2f6-b84be30a0884'),('3b99286f-0ab4-4e0c-a3cb-a0e2b48f9cb3','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:35',0,0,0,0,'site',1445474375980,'8ecea40a-bd6b-4dfe-914c-aa44bc5fc201'),('3e8c2180-b994-41fa-8978-f75dcbf78b8c','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:41',0,0,0,0,'site',1445494661411,'d0ec8c40-9569-4d00-a389-b853748bf849'),('3ecdfbad-0066-4e7f-9af7-f5b4e0ee12ee','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:30',0,0,0,0,'site',1445474370188,'03d6cd9d-f7b3-4778-b5f3-21797330e369'),('3efb94a6-e109-44e2-a488-68729083f776','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:38',0,0,0,0,'site',1445494658830,'159648a9-ed33-4a26-9da0-c9aab2ff4a9f'),('40b288a1-e39b-48c0-9863-3f7336cf36bb','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:38',0,0,0,0,'site',1445494658727,'03d6cd9d-f7b3-4778-b5f3-21797330e369'),('41169e90-b933-4ba5-9a1b-935a68772a39','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:37',0,0,0,0,'site',1445474377238,'b6f04f26-86b9-4a47-8fff-55f7b40d61e0'),('4147826f-9527-4092-aa25-6692bd100231','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:34',0,0,0,0,'site',1445474374262,'9d12aa16-5cdf-4fb7-b7d3-81058ed8024f'),('416b6c51-7cc5-4947-a826-ee7a9e52558c','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:45',0,0,0,0,'site',1445494665298,'9b427f92-d323-4800-b98f-151cd6486237'),('41f1f0e1-28c2-4d11-8524-48724cb31b35','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:44',0,0,0,0,'site',1445494664656,'6b54fa40-45b7-4c08-81dc-28a4e6ca5c82'),('41f402a0-b52f-4c0f-a651-714edc1e466c','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:30',0,0,0,0,'site',1445474370671,'35b400fb-2f3f-42cd-be0d-580d06b60d86'),('423117b6-9d72-4ebb-9eba-340f40a572de','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:33',0,0,0,0,'site',1445474373713,'db188e80-1d5d-4d3f-81b3-484206ee2107'),('44001ad4-f69b-4ef0-beb7-a3fe90c5d8b6','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:30',0,0,0,0,'site',1445474370502,'d39e3ad7-a482-462f-9eb9-454b26b9db5b'),('44a18960-90f7-4f78-8e3f-330c2c072d32','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:33',0,0,0,0,'site',1445474373395,'0ada0a09-5b0d-4b98-a166-88832accecd1'),('457e8fb7-d457-408a-aa10-eff70f52d909','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:41',0,0,0,0,'site',1445494661380,'d0ec8c40-9569-4d00-a389-b853748bf849'),('45aaa497-8b1e-47e0-ad24-d364aa8b94db','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:37',0,0,0,0,'site',1445474377572,'8ac3c968-6a0a-4435-aaf6-6f392c57a620'),('475a38e9-93d3-4f0d-b85b-84de481ed399','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:41',0,0,0,0,'site',1445494661839,'f78238fd-a4e5-464c-b474-4f184493296b'),('47c31b73-b47c-4cd0-8ffb-89ac78dd19a9','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:43',0,0,0,0,'site',1445494663121,'7cd4debb-1981-4c07-867c-6d7f6fc1e955'),('483c5d24-9fa5-47f8-9eea-6059f3554987','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:43',0,0,0,0,'site',1445494663554,'d61b2487-b747-4321-a4c1-9b527bfc23f2'),('4867ec7d-842e-457c-a45e-bc4b96f6ce49','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:46',0,0,0,0,'site',1445494666165,'08f8586c-9013-48d0-81ec-362be4a97d4a'),('4a26e650-2bd3-440b-a1f0-9e75a4abbfcd','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:42',0,0,0,0,'site',1445494662956,'64050402-de11-47a8-a8e9-1ec9ed99f7e0'),('4a7a47a1-0340-4271-b2bf-92454e894174','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:43',0,0,0,0,'site',1445494663898,'f24c449b-42f7-4628-85d7-e4012905ec49'),('4a90d4be-fad3-421f-8193-d4908dded2cb','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:39',0,0,0,0,'site',1445494659662,'a33d3db2-c3cf-434f-9a35-8d40e0de2e8d'),('4aa914d8-c2b9-48b5-bbc6-3f79779add9a','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:31',0,0,0,0,'site',1445474371846,'51e21a1a-3757-4dde-b813-1f78dbf881fa'),('4bc1de80-398c-4f76-abe3-15bbf2a3a5fd','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:43',0,0,0,0,'site',1445494663589,'3cad172d-eaba-4748-903e-617ea2b6df8f'),('4be5ed82-3a0c-4d64-a761-76be179f95a3','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:45',0,0,0,0,'site',1445494665498,'11e05cb9-0f9f-4771-9c7c-cc9809956439'),('4c4dbfb7-08e1-4b97-912f-6940fd7bcae8','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:33',0,0,0,0,'site',1445474373946,'ce2eaf51-93f2-49c4-b9bf-a4fed3e7f599'),('4d623744-d691-463a-bc98-a44979d38a47','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:34',0,0,0,0,'site',1445474374304,'9d12aa16-5cdf-4fb7-b7d3-81058ed8024f'),('4d7a22da-e562-48e5-974c-6a4d936f0380','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:32',0,0,0,0,'site',1445474372855,'d0ec8c40-9569-4d00-a389-b853748bf849'),('4da05721-de46-4ae0-ae62-d6e96e7d1293','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:42',0,0,0,0,'site',1445494662454,'ce2eaf51-93f2-49c4-b9bf-a4fed3e7f599'),('4e28b8bf-9e1b-4030-a8d6-bfc4293d89d7','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:46',0,0,0,0,'site',1445494666063,'8ac3c968-6a0a-4435-aaf6-6f392c57a620'),('4e50bc99-7474-4611-918c-d4c5e4ae62e5','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:46',0,0,0,0,'site',1445494666638,'7cbad721-91a7-460b-8916-bb19bbe5e0f2'),('4ec026ab-df86-4db8-9f99-dfc5f4c28d6a','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:38',0,0,0,0,'site',1445474378038,'b8a2a9e1-3f6d-4ec3-954f-5aa96bb1c3c6'),('4ede23d8-9439-40f5-a91f-466e2320b9ab','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:46',0,0,0,0,'site',1445494666598,'7cbad721-91a7-460b-8916-bb19bbe5e0f2'),('4f2b1ce4-4ab6-452f-b75d-d5e21ad0f6a2','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:30',0,0,0,0,'site',1445474370813,'366a40f2-6f5b-4c66-a168-9d774cb69507'),('4f5a7f09-2766-4bdc-8290-2b0cc025a1b7','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:38',0,0,0,0,'site',1445474378538,'8e22e8c8-649b-4286-baf4-0c48e88fdfb6'),('4f64599d-5b1d-4409-acb2-4a9f63f35b0a','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:34',0,0,0,0,'site',1445474374371,'64050402-de11-47a8-a8e9-1ec9ed99f7e0'),('4fbf5c66-13d7-4dcb-ac5d-eb90d35b6c3d','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:46',0,0,0,0,'site',1445494666488,'b8a2a9e1-3f6d-4ec3-954f-5aa96bb1c3c6'),('4fcba2c4-40ca-4097-b345-bcbb34aece6c','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:43',0,0,0,0,'site',1445494663304,'e6c92868-3109-4e0b-8fa9-6d781c873a19'),('502aa110-7d1a-4159-8a32-4b9e71b4e36d','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:40',0,0,0,0,'site',1445494660897,'1b219275-1f31-4d72-ac8a-86f62caac828'),('50a86e3d-6801-4487-bbbc-bf2956c7b792','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:43',0,0,0,0,'site',1445494663964,'8ecea40a-bd6b-4dfe-914c-aa44bc5fc201'),('5165124c-b7b6-4e98-9033-201f3acbdc51','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:33',0,0,0,0,'site',1445474373904,'a0b1bbc8-0fab-4997-92dc-d216898562ac'),('52c07028-eaaa-4131-9093-d4c4589e2332','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:45',0,0,0,0,'site',1445494665605,'39f7e44b-d90a-41aa-8327-895aee0ea7ec'),('534ff19e-6dec-4f24-9882-f0010fc74096','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:43',0,0,0,0,'site',1445494663687,'ac4ccdbc-4b5b-46e5-862f-ea8e319b56a5'),('5354f7e4-4d88-4578-be5f-3f9c89a7af73','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:39',0,0,0,0,'site',1445494659247,'366a40f2-6f5b-4c66-a168-9d774cb69507'),('55198ffe-1747-46d6-8561-7be9d9b736d0','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:43',0,0,0,0,'site',1445494663362,'e6c92868-3109-4e0b-8fa9-6d781c873a19'),('56deccb8-e9b4-4bc9-ae78-0840d1bc264e','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:37',0,0,0,0,'site',1445474377171,'38cda477-f403-4720-9d05-c90eef30c73d'),('570b2caf-98e5-463f-ba66-503a813d0f21','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:31',0,0,0,0,'site',1445474371454,'a8b1f0b4-0afd-4d63-9d14-ef77f045c7a2'),('57f6eb3c-4ba7-418a-a42b-a1be97b22e84','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:46',0,0,0,0,'site',1445494666673,'2cd3982c-d469-440d-922a-75e965968f23'),('58d63483-ff40-43e4-9270-8236e51bf4d1','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:42',0,0,0,0,'site',1445494662723,'7b52f775-b5dc-4d73-b041-452d5c4d0805'),('5930b7c0-aa7f-432b-b019-ca92c4a8b20e','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:34',0,0,0,0,'site',1445474374045,'a11d5394-3b77-4b5f-8556-b9235d6c0daf'),('59418333-7e3c-42fe-a6d0-4f0e35a7015a','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:45',0,0,0,0,'site',1445494665529,'11e05cb9-0f9f-4771-9c7c-cc9809956439'),('597314b4-7ebe-4391-9818-8103e372ecca','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:37',0,0,0,0,'site',1445474377922,'33db1afb-862b-44a1-b54e-2fef5b2eb003'),('599b8d61-bf80-4a94-a6d3-8525e6ddd95a','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:46',0,0,0,0,'site',1445494666129,'08f8586c-9013-48d0-81ec-362be4a97d4a'),('5a32f3d3-0496-4e52-a04e-1be73e5743d9','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:34',0,0,0,0,'site',1445474374688,'7cd4debb-1981-4c07-867c-6d7f6fc1e955'),('5aa48021-aba4-4197-b37c-2c521086522b','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:43',0,0,0,0,'site',1445494663523,'d61b2487-b747-4321-a4c1-9b527bfc23f2'),('5af316a3-722e-4826-974f-d4790c4e5602','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:41',0,0,0,0,'site',1445494661322,'b2240fb3-61e2-427e-8cd4-ec474380594d'),('5b265cf4-2c1d-48d7-a23e-9506e88057f2','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:34',0,0,0,0,'site',1445474374872,'7b5b131b-00cd-488a-acf3-966258070ef9'),('5dbf4ae7-88cb-47a2-a7cc-30b2a7135258','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:41',0,0,0,0,'site',1445494661606,'4f1838d1-20c0-4b43-a698-7707f62c625f'),('5e1c2983-3489-4f76-9cbc-6529ed65d3db','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:31',0,0,0,0,'site',1445474371745,'a589064e-4bb9-4d62-a100-21f734154200'),('5f0ed880-8c30-476d-9f57-5f8cf3a58821','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:37',0,0,0,0,'site',1445474377206,'38cda477-f403-4720-9d05-c90eef30c73d'),('60348b9e-1e21-4f7f-9f93-9cfc22631671','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:33',0,0,0,0,'site',1445474373679,'af9a68c5-84b9-4ffd-b238-93711b0797a8'),('60b67373-7f69-4746-beb2-8c8265e11647','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:30',0,0,0,0,'site',1445474370221,'03d6cd9d-f7b3-4778-b5f3-21797330e369'),('61ec90f1-6398-4991-89f1-589760f78f55','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:32',0,0,0,0,'site',1445474372113,'ae9e7b3c-2dfe-49fb-b48a-5cc7d3644e3e'),('620b0f04-68a5-48ee-82ce-c97e77a2325c','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:39',0,0,0,0,'site',1445494659045,'d39e3ad7-a482-462f-9eb9-454b26b9db5b'),('622f8aa2-a936-4e8f-afff-55612dd161d8','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:40',0,0,0,0,'site',1445494660937,'98b09cf5-837d-431c-b24c-b35e24e259d4'),('6363815d-105e-40ee-8eb5-4f06b2432c42','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:34',0,0,0,0,'site',1445474374612,'6661c054-ba77-4ef7-8036-66b36073c3b0'),('63cad3ba-a7a3-44d5-bb78-270cdeea75c6','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:38',0,0,0,0,'site',1445474378072,'b8a2a9e1-3f6d-4ec3-954f-5aa96bb1c3c6'),('644c52a1-52a9-4a99-ac15-1282853a41d3','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:42',0,0,0,0,'site',1445494662986,'64050402-de11-47a8-a8e9-1ec9ed99f7e0'),('65cbb167-dffe-46c0-9748-c52d69625b74','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:41',0,0,0,0,'site',1445494661470,'4f1838d1-20c0-4b43-a698-7707f62c625f'),('688d33e6-a815-410c-8e46-5ae9dad07abd','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:39',0,0,0,0,'site',1445494659630,'a33d3db2-c3cf-434f-9a35-8d40e0de2e8d'),('69bb2109-350d-4832-8fd6-7ddd7a85f566','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:41',0,0,0,0,'site',1445494661197,'fdb9fe15-c954-4bf7-adf8-0893038a8b71'),('6a029a38-fc52-4100-81dc-bfb242774bad','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:34',0,0,0,0,'site',1445474374838,'7b5b131b-00cd-488a-acf3-966258070ef9'),('6a98316b-b0bf-4d06-8ea6-5ac1f50096c4','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:40',0,0,0,0,'site',1445494660489,'a589064e-4bb9-4d62-a100-21f734154200'),('6b1173f8-1ba1-4e83-afd0-503b40f56d8a','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:39',0,0,0,0,'site',1445494659423,'47ed5424-7189-449f-8a01-58ac7613834a'),('6b276ac5-d705-4627-972f-cc52a1d3882d','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:40',0,0,0,0,'site',1445494660265,'424e93ea-a975-48d3-b53e-3ced5f795a98'),('6b3ae7c7-9a97-4de3-a2fc-c9b6993ba5d4','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:30',0,0,0,0,'site',1445474370912,'49019030-af3b-4edb-bec3-ae7e6c55a279'),('6b485d02-51ed-410b-bc3f-a61cb8e8c505','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:31',0,0,0,0,'site',1445474371287,'2990a75f-bd36-4e61-a753-4415495a95eb'),('6b891a91-424a-4e06-8abf-c2d39177fa95','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:30',0,0,0,0,'site',1445474370470,'d39e3ad7-a482-462f-9eb9-454b26b9db5b'),('6c8329d9-0226-4e2a-b1da-bb23db3e3cc4','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:45',0,0,0,0,'site',1445494665730,'39f7e44b-d90a-41aa-8327-895aee0ea7ec'),('6cdd7029-50f0-4485-8026-70d7ec68926f','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:34',0,0,0,0,'site',1445474374337,'9d12aa16-5cdf-4fb7-b7d3-81058ed8024f'),('6d4e73a6-b2d1-4d63-8424-0a1a2b24c045','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:30',0,0,0,0,'site',1445474370778,'366a40f2-6f5b-4c66-a168-9d774cb69507'),('6d6a651c-72a0-4399-81ce-d525f50b49ae','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:33',0,0,0,0,'site',1445474373796,'db188e80-1d5d-4d3f-81b3-484206ee2107'),('6d85dc0f-6f3c-4e8c-96f2-989dbd08ba7c','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:38',0,0,0,0,'site',1445494658756,'03d6cd9d-f7b3-4778-b5f3-21797330e369'),('6d9943f8-5177-42a8-919f-347ad5f7a960','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:39',0,0,0,0,'site',1445494659188,'35b400fb-2f3f-42cd-be0d-580d06b60d86'),('6e365c86-3d49-4cb8-880a-815771a92092','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:33',0,0,0,0,'site',1445474373571,'8df8840d-610b-409d-a2bb-e912d3c20ec5'),('6ecaf5f7-a789-491d-8de9-441c6091bb5c','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:37',0,0,0,0,'site',1445474377655,'08f8586c-9013-48d0-81ec-362be4a97d4a'),('700144bf-d910-4528-a697-83a0b68a9f20','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:35',0,0,0,0,'site',1445474375104,'8161bdd4-2f90-4079-a434-8f1d8d937049'),('70c58302-3dfd-41d5-82a3-4b1caa569e3e','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:40',0,0,0,0,'site',1445494660069,'ff956575-07a9-4984-b068-c85473609687'),('71316470-76b7-459b-a1d4-014e740c83c5','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:33',0,0,0,0,'site',1445474373103,'5a57bdb0-33df-4879-a1a1-0a2b805b1676'),('713c27bb-8c6b-4345-8c3e-1e3816284533','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:43',0,0,0,0,'site',1445494663745,'ac4ccdbc-4b5b-46e5-862f-ea8e319b56a5'),('72caa3d9-9cd7-4ffc-9e2a-94441403ee72','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:35',0,0,0,0,'site',1445474375454,'3cad172d-eaba-4748-903e-617ea2b6df8f'),('72f77515-b6f2-4df9-8ef0-6b93eeceb2a5','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:43',0,0,0,0,'site',1445494663839,'879b2bc4-e3d4-42de-bfc0-43774cd0482f'),('73be6233-511b-45b9-9bfb-6d3c8674b79b','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:30',0,0,0,0,'site',1445474370135,'03d6cd9d-f7b3-4778-b5f3-21797330e369'),('74b7a5c4-30bc-423d-a20b-7019aac2c10f','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:35',0,0,0,0,'site',1445474375779,'f24c449b-42f7-4628-85d7-e4012905ec49'),('74cf5b72-69b5-4a8e-8ca0-7d924b6101c1','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:41',0,0,0,0,'site',1445494661987,'8df8840d-610b-409d-a2bb-e912d3c20ec5'),('75585f41-15a6-43a6-955d-92dbe7037745','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:35',0,0,0,0,'site',1445474375396,'3cad172d-eaba-4748-903e-617ea2b6df8f'),('7626e6d4-6b8d-498f-a3fc-59a08563a354','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:36',0,0,0,0,'site',1445474376971,'39f7e44b-d90a-41aa-8327-895aee0ea7ec'),('78469981-411a-48ab-af57-e5d68b669631','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:35',0,0,0,0,'site',1445474375029,'e6c92868-3109-4e0b-8fa9-6d781c873a19'),('7866b26a-6b7f-42d3-849e-7f9cb3edb2cd','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:36',0,0,0,0,'site',1445474376622,'9b427f92-d323-4800-b98f-151cd6486237'),('78b0dac9-abbf-4e06-9470-e46c6c4c7d96','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:41',0,0,0,0,'site',1445494661228,'fdb9fe15-c954-4bf7-adf8-0893038a8b71'),('78f161e8-4d69-4a64-a772-522ac935402b','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:40',0,0,0,0,'site',1445494660192,'424e93ea-a975-48d3-b53e-3ced5f795a98'),('792288f8-2c0c-47ed-8620-0269b7549589','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:33',0,0,0,0,'site',1445474373872,'a0b1bbc8-0fab-4997-92dc-d216898562ac'),('796385e3-9b30-4b47-ad59-9d15caadf45e','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:39',0,0,0,0,'site',1445494659480,'47ed5424-7189-449f-8a01-58ac7613834a'),('79fa3275-6ead-4c64-8cd0-6404a42a257c','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:46',0,0,0,0,'site',1445494666831,'8e22e8c8-649b-4286-baf4-0c48e88fdfb6'),('7ae135b3-fbed-4b6b-b87e-5851b00fd4bc','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:33',0,0,0,0,'site',1445474373272,'f78238fd-a4e5-464c-b474-4f184493296b'),('7c5941b6-e00e-47e0-8ab9-01742d5ea34d','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:30',0,0,0,0,'site',1445474370845,'366a40f2-6f5b-4c66-a168-9d774cb69507'),('7c8caae6-72cd-4b3e-9545-46fff4d064d6','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:35',0,0,0,0,'site',1445474375822,'f24c449b-42f7-4628-85d7-e4012905ec49'),('7d4b1b42-7e11-4539-bfe6-ce5016366d34','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:44',0,0,0,0,'site',1445494664040,'8ecea40a-bd6b-4dfe-914c-aa44bc5fc201'),('7d975f0f-bdf3-414c-8d36-e13f1ff5675c','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:39',0,0,0,0,'site',1445494659161,'35b400fb-2f3f-42cd-be0d-580d06b60d86'),('7e393dd7-28d8-4438-83c9-b58d4fd4f3c0','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:45',0,0,0,0,'site',1445494665214,'9b427f92-d323-4800-b98f-151cd6486237'),('7ec4c3f4-56b7-4135-a659-b525e68fa6ca','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:43',0,0,0,0,'site',1445494663620,'3cad172d-eaba-4748-903e-617ea2b6df8f'),('7ef2a916-2b9c-46e8-a37c-b4df07e0ce9e','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:33',0,0,0,0,'site',1445474373237,'d5a06e48-e12f-4146-b07e-7f7e85b1f1c0'),('7f751d79-ea7f-4ad4-b6f5-92c334b0eabb','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:35',0,0,0,0,'site',1445474375905,'f24c449b-42f7-4628-85d7-e4012905ec49'),('7faee745-0a0f-4622-a5d7-ad5dda1f471f','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:31',0,0,0,0,'site',1445474371387,'a8b1f0b4-0afd-4d63-9d14-ef77f045c7a2'),('7fc77ebd-c3c0-436f-87cb-efd1604d4179','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:41',0,0,0,0,'site',1445494661897,'0ada0a09-5b0d-4b98-a166-88832accecd1'),('801d1373-7e53-493a-b22a-ab705f1d9cd8','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:33',0,0,0,0,'site',1445474373754,'db188e80-1d5d-4d3f-81b3-484206ee2107'),('82954be0-3ffd-4dcd-988e-8067c43b78d2','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:47',0,0,0,0,'site',1445494667332,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('83c4c151-7531-4cd5-be83-aec29ac78b23','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:39',0,0,0,0,'site',1445494659603,'4b18167e-ecad-489e-87b6-44c90d04c9a4'),('8435c934-6b07-4277-80d1-c73ee6a50c64','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:36',0,0,0,0,'site',1445474376380,'f27e2913-3bcd-48f6-bc2d-ccbca5ccb840'),('847dcec4-a33b-4d54-add2-e16728fdf636','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:43',0,0,0,0,'site',1445494663929,'f24c449b-42f7-4628-85d7-e4012905ec49'),('84a69d8d-937e-48a4-a0ee-f5811ca2986f','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:46',0,0,0,0,'site',1445494666263,'dbe283e0-6fb1-4b13-a57a-0517a59f5474'),('87205f28-b6d0-4205-aef3-68754749f3ad','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:45',0,0,0,0,'site',1445494665463,'b783233e-d891-4651-8df3-aaae13ab0b69'),('887b649a-b534-471a-89ea-6d3ae466665f','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:35',0,0,0,0,'site',1445474375139,'8161bdd4-2f90-4079-a434-8f1d8d937049'),('8997f9b0-b25e-4dad-8c0b-40c0c3e588f2','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:30',0,0,0,0,'site',1445474370704,'35b400fb-2f3f-42cd-be0d-580d06b60d86'),('89bb6e80-c658-464c-8ce0-49aa95225d0a','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:39',0,0,0,0,'site',1445494659453,'47ed5424-7189-449f-8a01-58ac7613834a'),('8a33a75a-a5f3-49b2-9210-1ae45f61b279','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:40',0,0,0,0,'site',1445494660862,'1b219275-1f31-4d72-ac8a-86f62caac828'),('8a890fb4-dbc7-400c-86ba-99289b33b4f0','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:32',0,0,0,0,'site',1445474372412,'9cc1a792-91bf-433d-963c-d6b8b088438e'),('8ac1f5d5-88f2-439f-98f0-9d3ee12db446','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:42',0,0,0,0,'site',1445494662354,'a0b1bbc8-0fab-4997-92dc-d216898562ac'),('8aeaff99-4ae1-487f-9b3c-60dd1934ba80','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:44',0,0,0,0,'site',1445494664887,'079f46b3-b738-4afb-8524-f624f2e96766'),('8b84d26c-2aa5-4a8c-9959-7030a7b363de','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:42',0,0,0,0,'site',1445494662861,'9d12aa16-5cdf-4fb7-b7d3-81058ed8024f'),('8be3d952-fdc2-402c-861d-a35671e9b664','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:39',0,0,0,0,'site',1445474379123,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('8d1193ca-66dc-4e10-90d1-1a1d08a85f48','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:40',0,0,0,0,'site',1445494660648,'e577d00a-9afe-4f9c-a2f6-b84be30a0884'),('8dad5913-8670-4a1f-8cfa-215af5471429','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:31',0,0,0,0,'site',1445474371879,'51e21a1a-3757-4dde-b813-1f78dbf881fa'),('8db94514-3cdb-4fc9-80ef-1f91ed17ae20','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:34',0,0,0,0,'site',1445474374155,'7b52f775-b5dc-4d73-b041-452d5c4d0805'),('8e084463-3013-4748-af32-fd68eec73c73','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:33',0,0,0,0,'site',1445474373537,'8df8840d-610b-409d-a2bb-e912d3c20ec5'),('90253f35-1ba9-420a-92d7-5ec28a69f15b','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:38',0,0,0,0,'site',1445474378389,'2cd3982c-d469-440d-922a-75e965968f23'),('903fcc93-48e3-43fa-98a6-1dd7b3da6631','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:43',0,0,0,0,'site',1445494663245,'7b5b131b-00cd-488a-acf3-966258070ef9'),('90a8e074-b038-4d4b-912e-941facdab975','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:34',0,0,0,0,'site',1445474374195,'7b52f775-b5dc-4d73-b041-452d5c4d0805'),('90aa06bc-72f6-4a8c-86c0-2bd0a4f62120','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:34',0,0,0,0,'site',1445474374572,'6661c054-ba77-4ef7-8036-66b36073c3b0'),('90b8384f-cb6d-400e-8a98-7ea29e6eaab9','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:36',0,0,0,0,'site',1445474376447,'f27e2913-3bcd-48f6-bc2d-ccbca5ccb840'),('9123ad7e-7d51-4108-afb2-6f7c10afa5ca','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:35',0,0,0,0,'site',1445474375063,'8161bdd4-2f90-4079-a434-8f1d8d937049'),('913652eb-7a11-4c17-8196-dafddc570fce','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:43',0,0,0,0,'site',1445494663390,'8161bdd4-2f90-4079-a434-8f1d8d937049'),('91b9c8dc-a421-4a26-9068-de3fc71f1d39','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:33',0,0,0,0,'site',1445474373138,'5a57bdb0-33df-4879-a1a1-0a2b805b1676'),('91ec8ebc-079e-4631-a7ec-ffb4f02f54d5','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:39',0,0,0,0,'site',1445474379056,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('92ab39b5-1015-433a-9246-6dae051f4439','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:33',0,0,0,0,'site',1445474373838,'a0b1bbc8-0fab-4997-92dc-d216898562ac'),('92c6591c-6891-4895-bdc2-3328a3be14aa','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:46',0,0,0,0,'site',1445494666523,'b8a2a9e1-3f6d-4ec3-954f-5aa96bb1c3c6'),('9335454b-e1e7-42fe-91f4-a891c389e130','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:36',0,0,0,0,'site',1445474376196,'6b54fa40-45b7-4c08-81dc-28a4e6ca5c82'),('936f61b7-e2ea-4a7f-9cd4-1120064cab7f','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:37',0,0,0,0,'site',1445474377014,'39f7e44b-d90a-41aa-8327-895aee0ea7ec'),('93a6637a-2387-4ea1-bb80-02e2daf0cb01','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:37',0,0,0,0,'site',1445474377321,'b6f04f26-86b9-4a47-8fff-55f7b40d61e0'),('945b48f0-82b4-4831-a9c1-d3dc7abf1698','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:38',0,0,0,0,'site',1445474378622,'22b70bc4-6dbb-4015-953a-3c092eb9798c'),('945bbfcd-f27a-4ca3-a7d8-3bd57fa91ea9','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:45',0,0,0,0,'site',1445494665171,'10cc580a-7f73-41b0-8f8b-d264bb3b892f'),('94a74859-1552-430e-978b-ae62022b9b63','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:31',0,0,0,0,'site',1445474371603,'424e93ea-a975-48d3-b53e-3ced5f795a98'),('9511a978-4d8e-4e3a-9d68-c934cb77e1ab','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:44',0,0,0,0,'site',1445494664474,'6b54fa40-45b7-4c08-81dc-28a4e6ca5c82'),('953fe687-c08a-40d2-b3ef-7ba0140b0439','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:42',0,0,0,0,'site',1445494662656,'a11d5394-3b77-4b5f-8556-b9235d6c0daf'),('95a6ebc5-dcf9-4e86-b72a-51e4de2cb8af','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:30',0,0,0,0,'site',1445474370404,'4738dbcb-bdc9-496c-ab7f-cd1a4d4b5a8b'),('96c393af-485b-4a77-ada3-fbaf0bfb2009','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:45',0,0,0,0,'site',1445494665923,'b6f04f26-86b9-4a47-8fff-55f7b40d61e0'),('96f2a27c-ac9a-41c5-aea7-985c5e47f58c','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:30',0,0,0,0,'site',1445474370572,'337d7c3d-27ba-4d39-92c7-39f72fcf2db4'),('97549777-6e21-4925-b8ac-3e37d1b57391','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:42',0,0,0,0,'site',1445494662073,'af9a68c5-84b9-4ffd-b238-93711b0797a8'),('97ef5b9e-f424-4861-a75d-8360b63e5552','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:31',0,0,0,0,'site',1445474371221,'a33d3db2-c3cf-434f-9a35-8d40e0de2e8d'),('9818f071-6e50-475e-afcd-af211bae286c','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:44',0,0,0,0,'site',1445494664229,'f0af133d-e253-490d-859e-481c072393ee'),('982013ff-94aa-4c25-b055-06592c5e76c4','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:32',0,0,0,0,'site',1445474372646,'fdb9fe15-c954-4bf7-adf8-0893038a8b71'),('9a3efdb6-bfdc-479e-bcb3-410070c1454f','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:39',0,0,0,0,'site',1445494659778,'2990a75f-bd36-4e61-a753-4415495a95eb'),('9b3e133d-0a1f-4f3e-bfaa-9b2766333955','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:39',0,0,0,0,'site',1445494659831,'a8b1f0b4-0afd-4d63-9d14-ef77f045c7a2'),('9b8b15c7-3f22-41ed-bd3f-d94994e21200','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:31',0,0,0,0,'site',1445474371646,'424e93ea-a975-48d3-b53e-3ced5f795a98'),('9bb8201d-780b-45ac-b90f-08a9ffb72f26','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:37',0,0,0,0,'site',1445474377996,'33db1afb-862b-44a1-b54e-2fef5b2eb003'),('9c5a81e9-78aa-414f-a3b6-e0dac4033792','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:46',0,0,0,0,'site',1445494666231,'dbe283e0-6fb1-4b13-a57a-0517a59f5474'),('9cd22fff-b9b0-40aa-8f02-d2a31336df40','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:38',0,0,0,0,'site',1445494658794,'159648a9-ed33-4a26-9da0-c9aab2ff4a9f'),('9cf4352b-d247-43e9-8866-a8de65495d0e','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:44',0,0,0,0,'site',1445494664771,'079f46b3-b738-4afb-8524-f624f2e96766'),('9e842ab9-ba6c-4db3-be81-957e0bd88a34','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:40',0,0,0,0,'site',1445494660039,'ff956575-07a9-4984-b068-c85473609687'),('9ee4b424-bb20-4c52-ab34-ce917dabad6d','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:32',0,0,0,0,'site',1445474372313,'98b09cf5-837d-431c-b24c-b35e24e259d4'),('9f04e137-1bfb-4d0c-b07a-ae729284cf57','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:39',0,0,0,0,'site',1445494659356,'49019030-af3b-4edb-bec3-ae7e6c55a279'),('9f73605e-9a33-4cdd-8047-a46535246f0f','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:32',0,0,0,0,'site',1445474372212,'1b219275-1f31-4d72-ac8a-86f62caac828'),('a0a290d3-e2e5-4603-8a0b-b89cb1807600','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:46',0,0,0,0,'site',1445494666090,'8ac3c968-6a0a-4435-aaf6-6f392c57a620'),('a0f42d69-6b7b-4e04-a5c9-e362cec35d51','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:46',0,0,0,0,'site',1445494666023,'8ac3c968-6a0a-4435-aaf6-6f392c57a620'),('a159d919-9eb7-4b6c-b4fd-15b8788997ef','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:33',0,0,0,0,'site',1445474373037,'4f1838d1-20c0-4b43-a698-7707f62c625f'),('a1f7e30a-98a0-45a8-acf7-17ffe1c9be4e','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:36',0,0,0,0,'site',1445474376888,'11e05cb9-0f9f-4771-9c7c-cc9809956439'),('a288f4aa-565f-4b08-9877-66891c90263d','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:40',0,0,0,0,'site',1445494660764,'ae9e7b3c-2dfe-49fb-b48a-5cc7d3644e3e'),('a3a56b87-24e7-4245-abf6-713e3664258e','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:37',0,0,0,0,'site',1445474377614,'8ac3c968-6a0a-4435-aaf6-6f392c57a620'),('a4056bcd-d0fa-4530-b75d-1f0f7603335f','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:43',0,0,0,0,'site',1445494663088,'6661c054-ba77-4ef7-8036-66b36073c3b0'),('a4dbd501-36ab-4f84-a956-5ae756012ffd','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:36',0,0,0,0,'site',1445474376022,'8ecea40a-bd6b-4dfe-914c-aa44bc5fc201'),('a4f6704c-db98-47c1-8ac1-2b43f798ecbd','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:33',0,0,0,0,'site',1445474373470,'0ada0a09-5b0d-4b98-a166-88832accecd1'),('a5fb64ff-02f6-4c11-8611-dadef5bee16b','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:34',0,0,0,0,'site',1445474374763,'7cd4debb-1981-4c07-867c-6d7f6fc1e955'),('a635f83e-b0d4-451d-80c1-70d6998da0c4','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:32',0,0,0,0,'site',1445474372345,'98b09cf5-837d-431c-b24c-b35e24e259d4'),('a6e5162e-3dcd-4cae-aa77-169bc5c90e7a','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:42',0,0,0,0,'site',1445494662696,'7b52f775-b5dc-4d73-b041-452d5c4d0805'),('a81fe8a1-29af-470c-bf37-58e0699da7d8','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:46',0,0,0,0,'site',1445494666563,'7cbad721-91a7-460b-8916-bb19bbe5e0f2'),('a8e27870-e691-4f3f-8f96-f98fa818902c','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:42',0,0,0,0,'site',1445494662214,'db188e80-1d5d-4d3f-81b3-484206ee2107'),('aaf9a502-022c-4f9b-975d-6b9a1a42851a','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:36',0,0,0,0,'site',1445474376547,'10cc580a-7f73-41b0-8f8b-d264bb3b892f'),('ac52770b-176a-47ce-9f68-06bad526595a','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:41',0,0,0,0,'site',1445494661664,'5a57bdb0-33df-4879-a1a1-0a2b805b1676'),('ac7820bb-b311-4035-87ab-c5b460e15068','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:31',0,0,0,0,'site',1445474371571,'ff956575-07a9-4984-b068-c85473609687'),('ada59480-3bad-464e-bfbd-36977bf88fa9','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:41',0,0,0,0,'site',1445494661139,'d3759e96-1aec-4d5f-8bd1-f1b9e7d630ad'),('aef8f94a-fb99-4a21-991a-d71ac000cf53','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:32',0,0,0,0,'site',1445474372279,'98b09cf5-837d-431c-b24c-b35e24e259d4'),('afddcb38-d760-4a11-9c26-1e7be6c100a6','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:39',0,0,0,0,'site',1445494659748,'2990a75f-bd36-4e61-a753-4415495a95eb'),('affe0e9f-9deb-4a24-a1d8-e7834272bae8','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:38',0,0,0,0,'site',1445474378430,'8e22e8c8-649b-4286-baf4-0c48e88fdfb6'),('b180a6fc-2c1a-4438-9b81-2d1d8e2ce247','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:32',0,0,0,0,'site',1445474372247,'1b219275-1f31-4d72-ac8a-86f62caac828'),('b1f5d326-782b-4b54-b8b2-93d634c14436','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:40',0,0,0,0,'site',1445494660589,'51e21a1a-3757-4dde-b813-1f78dbf881fa'),('b3453e43-e9c7-46d4-bab5-ded94fb823c8','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:46',0,0,0,0,'site',1445494666713,'2cd3982c-d469-440d-922a-75e965968f23'),('b3996893-049c-401d-b71f-92c4231be7dd','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:30',0,0,0,0,'site',1445474370254,'159648a9-ed33-4a26-9da0-c9aab2ff4a9f'),('b3e169dd-602f-4383-b0e0-a4c4e43e1246','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:39',0,0,0,0,'site',1445494659327,'49019030-af3b-4edb-bec3-ae7e6c55a279'),('b3ed24c1-8765-472c-9dd0-a87dfbd54d66','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:46',0,0,0,0,'site',1445494666413,'33db1afb-862b-44a1-b54e-2fef5b2eb003'),('b5749bc6-eb3a-411f-af54-64952debe4ec','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:38',0,0,0,0,'site',1445474378239,'7cbad721-91a7-460b-8916-bb19bbe5e0f2'),('b5de148e-f871-41ae-8605-65bac8815d25','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:34',0,0,0,0,'site',1445474374080,'a11d5394-3b77-4b5f-8556-b9235d6c0daf'),('b69e9298-c65e-461d-b12c-583b226ece9f','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:45',0,0,0,0,'site',1445494665804,'38cda477-f403-4720-9d05-c90eef30c73d'),('b7095254-324e-44d3-a824-2c1f78aa0a8f','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:42',0,0,0,0,'site',1445494662381,'a0b1bbc8-0fab-4997-92dc-d216898562ac'),('b7d32c55-8f15-414b-92d6-74cbcd3ecf26','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:41',0,0,0,0,'site',1445494661439,'d0ec8c40-9569-4d00-a389-b853748bf849'),('b8adef33-d3ce-4cd5-bfd9-97b41dcffb6e','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:33',0,0,0,0,'site',1445474373430,'0ada0a09-5b0d-4b98-a166-88832accecd1'),('b8bf2633-8cc4-4446-92da-7a5e44156037','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:32',0,0,0,0,'site',1445474372505,'9cc1a792-91bf-433d-963c-d6b8b088438e'),('b93da8c8-ae6a-4999-a003-208100b10e99','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:46',0,0,0,0,'site',1445494666298,'dbe283e0-6fb1-4b13-a57a-0517a59f5474'),('b96855c0-1821-4f8f-a135-1bea04617adc','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:37',0,0,0,0,'site',1445474377697,'08f8586c-9013-48d0-81ec-362be4a97d4a'),('b9742054-9386-4836-8148-286d2809c39a','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:34',0,0,0,0,'site',1445474374647,'7cd4debb-1981-4c07-867c-6d7f6fc1e955'),('b9b4a6b2-95e7-43b7-9d89-ead6ce63729d','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:38',0,0,0,0,'site',1445474378272,'7cbad721-91a7-460b-8916-bb19bbe5e0f2'),('b9ea902c-f9cf-45f2-9aac-5f7fa364e1c2','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:42',0,0,0,0,'site',1445494662130,'af9a68c5-84b9-4ffd-b238-93711b0797a8'),('ba341858-90ca-4a34-a6ff-479fe8339ff8','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:32',0,0,0,0,'site',1445474372970,'4f1838d1-20c0-4b43-a698-7707f62c625f'),('bae8e231-b3f6-4e17-882a-1f0501ee00b6','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:32',0,0,0,0,'site',1445474372612,'d3759e96-1aec-4d5f-8bd1-f1b9e7d630ad'),('bb5a65d7-7a63-4658-8969-b0bc409d8209','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:35',0,0,0,0,'site',1445474375179,'d61b2487-b747-4321-a4c1-9b527bfc23f2'),('bc3f4741-d7cd-44a4-8145-ef8ceee43bfe','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:46',0,0,0,0,'site',1445494666907,'22b70bc4-6dbb-4015-953a-3c092eb9798c'),('bcdbbe6f-a28d-4b09-99a7-bd9102d2df26','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:45',0,0,0,0,'site',1445494665565,'11e05cb9-0f9f-4771-9c7c-cc9809956439'),('be1a4300-484d-4c2e-8f61-1bbe9972e1a6','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:32',0,0,0,0,'site',1445474372821,'b2240fb3-61e2-427e-8cd4-ec474380594d'),('bf90522c-cfb0-4bc2-98b2-38aeab465721','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:39',0,0,0,0,'site',1445494659072,'337d7c3d-27ba-4d39-92c7-39f72fcf2db4'),('c04f4005-c4d7-40c9-ac08-eb2c2c800443','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:39',0,0,0,0,'site',1445494659297,'366a40f2-6f5b-4c66-a168-9d774cb69507'),('c0549cc4-ee07-44d1-81c0-e167a4d2a5bb','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:39',0,0,0,0,'site',1445494659547,'4b18167e-ecad-489e-87b6-44c90d04c9a4'),('c0fda67b-ce51-4d35-937c-2a78d3f1b1f4','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:36',0,0,0,0,'site',1445474376654,'9b427f92-d323-4800-b98f-151cd6486237'),('c2014d9f-f4b8-41af-a579-39f66028d80e','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:42',0,0,0,0,'site',1445494662554,'ce2eaf51-93f2-49c4-b9bf-a4fed3e7f599'),('c3950bad-767e-4549-b7b7-c44ceb6bb844','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:42',0,0,0,0,'site',1445494662803,'7b52f775-b5dc-4d73-b041-452d5c4d0805'),('c57fed91-ca55-4de2-85c4-d7897ed92929','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:41',0,0,0,0,'site',1445494661256,'fdb9fe15-c954-4bf7-adf8-0893038a8b71'),('c5bc285a-9a51-4659-827f-c9821b468225','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:40',0,0,0,0,'site',1445494660358,'a589064e-4bb9-4d62-a100-21f734154200'),('c5c00873-2a69-4999-ad28-926b1f2a2570','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:37',0,0,0,0,'site',1445474377764,'dbe283e0-6fb1-4b13-a57a-0517a59f5474'),('c679096e-aa1c-42d2-84b7-e90cfe00990d','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:36',0,0,0,0,'site',1445474376306,'079f46b3-b738-4afb-8524-f624f2e96766'),('c6fb8fc9-8249-4220-be10-dbb158809f1e','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:36',0,0,0,0,'site',1445474376055,'f0af133d-e253-490d-859e-481c072393ee'),('c7292ed5-b919-4330-9909-55cc38b44fc3','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:34',0,0,0,0,'site',1445474374447,'64050402-de11-47a8-a8e9-1ec9ed99f7e0'),('c75c9fe9-d97f-4c2e-95f4-4e834babc7c8','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:47',0,0,0,0,'site',1445494667082,'d6f5537b-4534-4ba3-a351-f6be97537d2e'),('c7783f74-00b8-4cef-bbde-32b17345b087','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:43',0,0,0,0,'site',1445494663648,'3cad172d-eaba-4748-903e-617ea2b6df8f'),('c7c745c7-0da0-4e05-bd1a-3b094b39b574','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:43',0,0,0,0,'site',1445494663214,'7b5b131b-00cd-488a-acf3-966258070ef9'),('c8833c83-ad91-4ad3-9efc-f35bc8fe3e7f','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:42',0,0,0,0,'site',1445494662620,'a11d5394-3b77-4b5f-8556-b9235d6c0daf'),('c94d8fe9-8f67-4363-9895-623079d8d259','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:45',0,0,0,0,'site',1445494665773,'38cda477-f403-4720-9d05-c90eef30c73d'),('c95abe87-b642-4185-97de-41d2e488f4a6','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:31',0,0,0,0,'site',1445474371713,'a589064e-4bb9-4d62-a100-21f734154200'),('c9a6f3cc-d548-4cd5-8e38-f23af6456cd7','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:45',0,0,0,0,'site',1445494665840,'38cda477-f403-4720-9d05-c90eef30c73d'),('c9fc8c2b-ec28-4ff9-867b-ac93ad62aeb7','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:42',0,0,0,0,'site',1445494662014,'8df8840d-610b-409d-a2bb-e912d3c20ec5'),('caab353e-a67b-4e90-a92c-c8c48515c5c6','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:34',0,0,0,0,'site',1445474374796,'7b5b131b-00cd-488a-acf3-966258070ef9'),('cb2e0a7d-07ee-4374-8be6-b3c11490b756','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:39',0,0,0,0,'site',1445494659969,'ff956575-07a9-4984-b068-c85473609687'),('cb808a4a-a226-4974-8f46-38ae7b20f6a5','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:43',0,0,0,0,'site',1445494663870,'f24c449b-42f7-4628-85d7-e4012905ec49'),('cbb39290-a53c-4a18-b476-0e7087623a29','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:38',0,0,0,0,'site',1445474378473,'8e22e8c8-649b-4286-baf4-0c48e88fdfb6'),('ccac13fe-54d2-42d3-a115-08b3ef788046','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:41',0,0,0,0,'site',1445494661520,'4f1838d1-20c0-4b43-a698-7707f62c625f'),('cd43eef2-cd5c-48eb-bf1e-8c2c39a63c4c','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:39',0,0,0,0,'site',1445494659278,'366a40f2-6f5b-4c66-a168-9d774cb69507'),('cd7eb446-99ad-4486-a7c6-2e7d87bca276','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:41',0,0,0,0,'site',1445494661053,'9cc1a792-91bf-433d-963c-d6b8b088438e'),('cd97b3a3-59f6-4300-b2ba-87bc18ab6ab5','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:43',0,0,0,0,'site',1445494663421,'8161bdd4-2f90-4079-a434-8f1d8d937049'),('ce9a4637-182b-48c6-95d5-626ad9d1b047','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:31',0,0,0,0,'site',1445474371080,'4b18167e-ecad-489e-87b6-44c90d04c9a4'),('cef6dd5b-003a-4a1e-8c6f-c84016ec35ee','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:41',0,0,0,0,'site',1445494661637,'5a57bdb0-33df-4879-a1a1-0a2b805b1676'),('cfd54027-5d8f-4942-acf7-01b25933f8ad','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:36',0,0,0,0,'site',1445474376847,'11e05cb9-0f9f-4771-9c7c-cc9809956439'),('d0f4b97d-f8e6-469b-a2be-1196e315c4f6','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:42',0,0,0,0,'site',1445494662287,'db188e80-1d5d-4d3f-81b3-484206ee2107'),('d1286869-d2cf-47ef-96c0-702b8af9ac70','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:32',0,0,0,0,'site',1445474372896,'d0ec8c40-9569-4d00-a389-b853748bf849'),('d16d5936-4a62-4529-824b-44129952d157','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:32',0,0,0,0,'site',1445474372938,'d0ec8c40-9569-4d00-a389-b853748bf849'),('d2644a85-6057-4a47-ad31-14c4c3e7d84c','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:45',0,0,0,0,'site',1445494665057,'f27e2913-3bcd-48f6-bc2d-ccbca5ccb840'),('d2b30331-90d0-41ab-bfdd-387e3e6ae90e','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:31',0,0,0,0,'site',1445474371154,'4b18167e-ecad-489e-87b6-44c90d04c9a4'),('d2d3e08e-a381-4e7a-a2a8-fa57fbc7ba2b','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:38',0,0,0,0,'site',1445474378863,'d6f5537b-4534-4ba3-a351-f6be97537d2e'),('d3251510-cc5f-4f8b-adb5-5755676d81e3','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:30',0,0,0,0,'site',1445474370745,'35b400fb-2f3f-42cd-be0d-580d06b60d86'),('d4b29e62-fad1-4e49-8bb0-3a225aa254dc','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:37',0,0,0,0,'site',1445474377956,'33db1afb-862b-44a1-b54e-2fef5b2eb003'),('d798500d-d1dd-461a-b011-1cf8a3b47be8','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:31',0,0,0,0,'site',1445474371321,'2990a75f-bd36-4e61-a753-4415495a95eb'),('d7ad67ae-a7da-4880-9b64-46f9fa8ea035','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:41',0,0,0,0,'site',1445494661723,'d5a06e48-e12f-4146-b07e-7f7e85b1f1c0'),('d82ce263-15ab-420e-b2ca-603715de428a','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:39',0,0,0,0,'site',1445494659914,'a8b1f0b4-0afd-4d63-9d14-ef77f045c7a2'),('d8b83d96-be23-4150-b8b1-fcea5f8f6836','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:30',0,0,0,0,'site',1445474370287,'159648a9-ed33-4a26-9da0-c9aab2ff4a9f'),('d97acf90-5c1a-45b3-9651-2c692fafded9','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:41',0,0,0,0,'site',1445494661929,'0ada0a09-5b0d-4b98-a166-88832accecd1'),('d98128c5-cc8f-409e-9be6-ed8d75b21a98','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:43',0,0,0,0,'site',1445494663272,'7b5b131b-00cd-488a-acf3-966258070ef9'),('d9c17d17-39df-47bf-8acb-3b5b193d257c','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:39',0,0,0,0,'site',1445494659720,'2990a75f-bd36-4e61-a753-4415495a95eb'),('da94ebe2-91b6-4319-a043-d598084e468a','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:34',0,0,0,0,'site',1445474374413,'64050402-de11-47a8-a8e9-1ec9ed99f7e0'),('dc60dce8-8ce4-4377-a43e-4b1ac32d62d5','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:30',0,0,0,0,'site',1445474370978,'47ed5424-7189-449f-8a01-58ac7613834a'),('dc81a5cd-d3a5-4e8e-9255-87cfef48198b','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:31',0,0,0,0,'site',1445474371112,'4b18167e-ecad-489e-87b6-44c90d04c9a4'),('dca15191-b9dd-481e-9c05-4bd5bf7e7958','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:46',0,0,0,0,'site',1445494666788,'8e22e8c8-649b-4286-baf4-0c48e88fdfb6'),('dd43b32b-eeb5-4a8b-9e64-1f12f3ef0e25','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:45',0,0,0,0,'site',1445494665423,'b783233e-d891-4651-8df3-aaae13ab0b69'),('ddb34202-53fd-4c78-bfdb-e92d6a26eacd','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:40',0,0,0,0,'site',1445494660795,'ae9e7b3c-2dfe-49fb-b48a-5cc7d3644e3e'),('de2c72ea-cc85-4090-b480-ab8b07c08ce4','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:37',0,0,0,0,'site',1445474377805,'dbe283e0-6fb1-4b13-a57a-0517a59f5474'),('dee3f146-1456-4e87-bf30-092ac6f79fc8','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:38',0,0,0,0,'site',1445494658889,'4738dbcb-bdc9-496c-ab7f-cd1a4d4b5a8b'),('df6970ee-cb25-4f73-a310-2aa2c1c8a96a','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:39',0,0,0,0,'site',1445494659689,'a33d3db2-c3cf-434f-9a35-8d40e0de2e8d'),('df86766c-481a-442f-b179-888630f53967','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:38',0,0,0,0,'site',1445474378705,'d6f5537b-4534-4ba3-a351-f6be97537d2e'),('dfdb992b-694d-4612-9398-98f52ef39471','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:31',0,0,0,0,'site',1445474371355,'2990a75f-bd36-4e61-a753-4415495a95eb'),('e02509d9-bd89-40dc-811a-bdec93643f2f','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:46',0,0,0,0,'site',1445494666990,'22b70bc4-6dbb-4015-953a-3c092eb9798c'),('e04c2c36-a078-4b34-b394-a41cded490f1','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:44',0,0,0,0,'site',1445494664848,'079f46b3-b738-4afb-8524-f624f2e96766'),('e0ede425-16b4-4916-8255-aac9da269a46','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:43',0,0,0,0,'site',1445494663496,'d61b2487-b747-4321-a4c1-9b527bfc23f2'),('e1156135-e298-447c-a4d4-1d933d30a81f','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:40',0,0,0,0,'site',1445494660620,'51e21a1a-3757-4dde-b813-1f78dbf881fa'),('e1694935-d1f8-45e4-93da-4a26d74efa25','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:41',0,0,0,0,'site',1445494661354,'b2240fb3-61e2-427e-8cd4-ec474380594d'),('e2219401-8b2e-4558-9252-348b5ee26b98','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:33',0,0,0,0,'site',1445474373205,'d5a06e48-e12f-4146-b07e-7f7e85b1f1c0'),('e332c7f6-3b6c-49bd-b38a-56b2e199aebd','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:39',0,0,0,0,'site',1445494659103,'337d7c3d-27ba-4d39-92c7-39f72fcf2db4'),('e3a83a60-d09f-4049-9e9d-187234df24b5','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:30',0,0,0,0,'site',1445474370946,'49019030-af3b-4edb-bec3-ae7e6c55a279'),('e3fee988-bc07-42ec-898f-5f7c00fb5d18','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:35',0,0,0,0,'site',1445474375297,'d61b2487-b747-4321-a4c1-9b527bfc23f2'),('e468215c-f26f-4aeb-8307-f3c6da7cd932','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:38',0,0,0,0,'site',1445494658947,'4738dbcb-bdc9-496c-ab7f-cd1a4d4b5a8b'),('e5129619-4885-492d-a83c-b1e65d5a9ffc','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:40',0,0,0,0,'site',1445494660561,'51e21a1a-3757-4dde-b813-1f78dbf881fa'),('e62f9dc7-6b6e-431d-b87c-c8bcad98799e','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:31',0,0,0,0,'site',1445474371186,'a33d3db2-c3cf-434f-9a35-8d40e0de2e8d'),('e65c4c74-a51a-4d26-ac49-e0c126febbc6','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:31',0,0,0,0,'site',1445474371779,'a589064e-4bb9-4d62-a100-21f734154200'),('e6d7e306-d45c-452d-bece-a83fa42eae02','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:47',0,0,0,0,'site',1445494667038,'d6f5537b-4534-4ba3-a351-f6be97537d2e'),('e6f7e75b-ad13-4b82-9fe4-b9695fabb02d','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:31',0,0,0,0,'site',1445474371421,'a8b1f0b4-0afd-4d63-9d14-ef77f045c7a2'),('e71032c9-eeb3-4f67-9fa0-286babfa24c8','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:42',0,0,0,0,'site',1445494662103,'af9a68c5-84b9-4ffd-b238-93711b0797a8'),('e7291f41-fd8d-420d-a79f-4727f6d5ba35','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:39',0,0,0,0,'site',1445494659874,'a8b1f0b4-0afd-4d63-9d14-ef77f045c7a2'),('e74fe426-6db9-4d98-89a3-7a926e2314fc','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:30',0,0,0,0,'site',1445474370637,'337d7c3d-27ba-4d39-92c7-39f72fcf2db4'),('e77e1291-8b38-4174-a0f4-60a92285d209','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:32',0,0,0,0,'site',1445474372180,'1b219275-1f31-4d72-ac8a-86f62caac828'),('e7a54d84-722b-42bc-ba85-b555c7a2dd71','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:36',0,0,0,0,'site',1445474376730,'b783233e-d891-4651-8df3-aaae13ab0b69'),('e8c463d7-ca5e-497f-9aa4-b737c43907a2','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:35',0,0,0,0,'site',1445474375221,'d61b2487-b747-4321-a4c1-9b527bfc23f2'),('e9a97368-d455-48de-a45a-1ad8fcf1a3de','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:36',0,0,0,0,'site',1445474376413,'f27e2913-3bcd-48f6-bc2d-ccbca5ccb840'),('e9e78496-ab4e-493d-a6b8-2a42073cd0a1','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:40',0,0,0,0,'site',1445494660964,'98b09cf5-837d-431c-b24c-b35e24e259d4'),('ea1b1f0d-f174-493a-8297-e9aca57fb3bc','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:31',0,0,0,0,'site',1445474371045,'47ed5424-7189-449f-8a01-58ac7613834a'),('ea86096e-d372-42c5-b509-24f6d58eea8e','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:38',0,0,0,0,'site',1445474378590,'22b70bc4-6dbb-4015-953a-3c092eb9798c'),('eab2ec7d-9bbf-4dcf-aefd-fd20b8ac2e04','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:31',0,0,0,0,'site',1445474371490,'ff956575-07a9-4984-b068-c85473609687'),('eb3b523c-9db7-485f-8a5a-2be206a72525','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:41',0,0,0,0,'site',1445494661081,'9cc1a792-91bf-433d-963c-d6b8b088438e'),('eb700e37-5367-4561-8455-e2f03df62ec9','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:34',0,0,0,0,'site',1445474374121,'a11d5394-3b77-4b5f-8556-b9235d6c0daf'),('ebaf4d42-8583-445c-9d04-b2c15dfcc3f9','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:38',0,0,0,0,'site',1445494658861,'159648a9-ed33-4a26-9da0-c9aab2ff4a9f'),('ec597d03-d567-4973-baa0-af6a6579361f','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:41',0,0,0,0,'site',1445494661294,'b2240fb3-61e2-427e-8cd4-ec474380594d'),('ec914d29-9aa4-49eb-a535-50265c6f6c76','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:45',0,0,0,0,'site',1445494665879,'b6f04f26-86b9-4a47-8fff-55f7b40d61e0'),('ed6a69cb-2ab6-422c-b29d-dab008c80dd3','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:36',0,0,0,0,'site',1445474376121,'f0af133d-e253-490d-859e-481c072393ee'),('ef412f9b-0da1-462d-a301-4d357e08812d','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:41',0,0,0,0,'site',1445494661780,'d5a06e48-e12f-4146-b07e-7f7e85b1f1c0'),('ef6d2d14-4a6f-4661-b8f7-17a51fcae103','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:33',0,0,0,0,'site',1445474373312,'f78238fd-a4e5-464c-b474-4f184493296b'),('ef8bf09a-c427-42ef-9529-bf2d6a555925','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:36',0,0,0,0,'site',1445474376164,'6b54fa40-45b7-4c08-81dc-28a4e6ca5c82'),('efc9db7d-e300-49cc-855e-47d187d49a1a','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:41',0,0,0,0,'site',1445494661870,'f78238fd-a4e5-464c-b474-4f184493296b'),('f0431869-6241-4e41-8bce-5be58f6daf98','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:47',0,0,0,0,'site',1445494667218,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('f05f636f-321f-4cd9-b869-ca9c34d3ef06','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:33',0,0,0,0,'site',1445474373005,'4f1838d1-20c0-4b43-a698-7707f62c625f'),('f0c5d2d7-0f4b-44be-8221-2b7f9a85b4cb','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:42',0,0,0,0,'site',1445494662920,'64050402-de11-47a8-a8e9-1ec9ed99f7e0'),('f0e6293f-3a3c-4bd3-98d0-efbe9b681295','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:32',0,0,0,0,'site',1445474372754,'b2240fb3-61e2-427e-8cd4-ec474380594d'),('f184a9a9-f455-4225-a390-276aad06edaf','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:40',0,0,0,0,'site',1445494660131,'424e93ea-a975-48d3-b53e-3ced5f795a98'),('f1be168f-86b1-4590-961b-d12d4b163c71','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:31',0,0,0,0,'site',1445474371254,'a33d3db2-c3cf-434f-9a35-8d40e0de2e8d'),('f1f1b582-cb21-4f8c-a9c6-f0dfeaae74fe','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:37',0,0,0,0,'site',1445474377531,'8ac3c968-6a0a-4435-aaf6-6f392c57a620'),('f2115c10-831d-49ca-9da9-1ce1518237ca','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:38',0,0,0,0,'site',1445474378114,'b8a2a9e1-3f6d-4ec3-954f-5aa96bb1c3c6'),('f21e8a2f-4cd0-4d04-a77c-f2ddde68ec6d','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:35',0,0,0,0,'site',1445474375589,'ac4ccdbc-4b5b-46e5-862f-ea8e319b56a5'),('f28a2b59-0761-4479-abe9-91bf75a56d7a','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:40',0,0,0,0,'site',1445494660995,'98b09cf5-837d-431c-b24c-b35e24e259d4'),('f4225b19-7464-4941-9177-7d3a1c2a45d8','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:46',0,0,0,0,'site',1445494666196,'08f8586c-9013-48d0-81ec-362be4a97d4a'),('f4ae02d5-d41e-443a-95dc-4c769956bb2d','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:36',0,0,0,0,'site',1445474376230,'6b54fa40-45b7-4c08-81dc-28a4e6ca5c82'),('f4d118ca-9f1f-46fd-a285-19157b56ff10','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:38',0,0,0,0,'site',1445474378355,'2cd3982c-d469-440d-922a-75e965968f23'),('f6063f4d-626f-4fa2-8975-50d2855da603','aa2e8349-05c9-44db-a0d7-527c64c9cf75','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:31',0,0,0,0,'site',1445474371012,'47ed5424-7189-449f-8a01-58ac7613834a'),('f7becafa-d1b6-4a10-96ac-81421992221b','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:41',0,0,0,0,'site',1445494661753,'d5a06e48-e12f-4146-b07e-7f7e85b1f1c0'),('f7c54663-a212-4133-a90e-e6b519a3265d','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:36',0,0,0,0,'site',1445474376805,'b783233e-d891-4651-8df3-aaae13ab0b69'),('f8ac5847-fc12-48e5-85e4-3030d5e5d543','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:31',0,0,0,0,'site',1445474371812,'51e21a1a-3757-4dde-b813-1f78dbf881fa'),('f9512446-1ee9-47ef-90b6-f1274eb58c99','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:45',0,0,0,0,'site',1445494665379,'b783233e-d891-4651-8df3-aaae13ab0b69'),('f9bf54f7-1369-4bf5-9254-4e7ade100fde','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:44',0,0,0,0,'site',1445494664923,'f27e2913-3bcd-48f6-bc2d-ccbca5ccb840'),('f9eb3065-179d-4e90-802c-fe36ec5a68c0','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:41',0,0,0,0,'site',1445494661956,'0ada0a09-5b0d-4b98-a166-88832accecd1'),('fa181d20-7459-4556-b649-81e7b30207b8','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:32',0,0,0,0,'site',1445474372380,'9cc1a792-91bf-433d-963c-d6b8b088438e'),('fa784ddf-1a48-4905-b408-f86c9691170f','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:32',0,0,0,0,'site',1445474372079,'ae9e7b3c-2dfe-49fb-b48a-5cc7d3644e3e'),('fa9407be-9ea9-4f7e-8b31-a0acbcd0a266','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:38',0,0,0,0,'site',1445494658681,'03d6cd9d-f7b3-4778-b5f3-21797330e369'),('fb038514-103d-4840-b128-cd3b89296219','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:38',0,0,0,0,'site',1445474378664,'22b70bc4-6dbb-4015-953a-3c092eb9798c'),('fbfc8ae2-deb2-4f4d-840a-11ad9d4cf214','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:46',0,0,0,0,'site',1445494666374,'33db1afb-862b-44a1-b54e-2fef5b2eb003'),('fcf09c38-f597-4b06-9c51-598009ae071a','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:44',0,0,0,0,'site',1445494664963,'f27e2913-3bcd-48f6-bc2d-ccbca5ccb840'),('fdddba74-4c85-4767-a1cb-a32ac8ace4fd','9c2ce025-f730-495b-807b-dc9e5570c123','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:30',0,0,0,0,'site',1445474370879,'49019030-af3b-4edb-bec3-ae7e6c55a279'),('feaec075-c899-431a-8d80-993e0cb8f704','aa2e8349-05c9-44db-a0d7-527c64c9cf75','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:45',0,0,0,0,'site',1445494665640,'39f7e44b-d90a-41aa-8327-895aee0ea7ec'),('fee3a05a-103f-43f3-b45e-2c0ebb60efb4','9c2ce025-f730-495b-807b-dc9e5570c123','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:41',0,0,0,0,'site',1445494661812,'f78238fd-a4e5-464c-b474-4f184493296b'),('ff1a5d29-a926-4c8c-b042-d7ad022f8b98','b3dabeb4-5665-4f7a-98b2-de6a2006d0d3','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-22 08:39:36',0,0,0,0,'site',1445474376931,'11e05cb9-0f9f-4771-9c7c-cc9809956439');
/*!40000 ALTER TABLE `ticket_roleprivilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_servicebook`
--

DROP TABLE IF EXISTS `ticket_servicebook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_servicebook` (
  `id` varchar(255) NOT NULL,
  `alipayName` varchar(255) DEFAULT NULL,
  `alipayTransld` varchar(255) DEFAULT NULL,
  `bankName` varchar(255) DEFAULT NULL,
  `bookAmount` int(11) DEFAULT NULL,
  `flightNumber` varchar(255) DEFAULT NULL,
  `idCard` varchar(255) DEFAULT NULL,
  `member_id` varchar(255) DEFAULT NULL,
  `payStatus` int(11) DEFAULT NULL,
  `payTime` datetime DEFAULT NULL,
  `payWay` int(11) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `serviceKey` varchar(255) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  `useTime` datetime DEFAULT NULL,
  `weChatTransld` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_servicebook`
--

LOCK TABLES `ticket_servicebook` WRITE;
/*!40000 ALTER TABLE `ticket_servicebook` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_servicebook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_systemconfig`
--

DROP TABLE IF EXISTS `ticket_systemconfig`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_systemconfig` (
  `id` varchar(255) NOT NULL,
  `description` longtext,
  `isAllowCreate` int(11) DEFAULT NULL,
  `isAllowSignIn` int(11) DEFAULT NULL,
  `keyword` varchar(20) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  `nameFront` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_systemconfig`
--

LOCK TABLES `ticket_systemconfig` WRITE;
/*!40000 ALTER TABLE `ticket_systemconfig` DISABLE KEYS */;
INSERT INTO `ticket_systemconfig` VALUES ('8dea01b1-9bfd-4635-be39-c1fa6438b85b','昆明长水国际机场',1,1,'昆明长水国际机场','昆明长水国际机场管理系统',1,1,'2015-03-17 11:40:31',0,0,0,0,'site, site',324242324,'昆明长水国际机场');
/*!40000 ALTER TABLE `ticket_systemconfig` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_systemmodule`
--

DROP TABLE IF EXISTS `ticket_systemmodule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_systemmodule` (
  `id` varchar(255) NOT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `isDefaultShow` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` varchar(50) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  `moduleUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_systemmodule`
--

LOCK TABLES `ticket_systemmodule` WRITE;
/*!40000 ALTER TABLE `ticket_systemmodule` DISABLE KEYS */;
INSERT INTO `ticket_systemmodule` VALUES ('03d6cd9d-f7b3-4778-b5f3-21797330e369','default.png',0,'管理公告栏','159648a9-ed33-4a26-9da0-c9aab2ff4a9f',0,0,'2015-10-21 17:15:58',0,0,0,2,'site',1445418958382,'/news_manage.action?versionFlag=site'),('079f46b3-b738-4afb-8524-f624f2e96766','default.png',0,'新增会员等级','f27e2913-3bcd-48f6-bc2d-ccbca5ccb840',0,0,'2015-10-14 16:47:21',0,0,0,5,'site',1444812441140,'/memberLevel_add.action?versionFlag=site'),('08f8586c-9013-48d0-81ec-362be4a97d4a','default.png',0,'新闻评论','b8a2a9e1-3f6d-4ec3-954f-5aa96bb1c3c6',0,0,'2015-10-13 13:35:17',0,0,0,3,'site',1444714517796,'##'),('0ada0a09-5b0d-4b98-a166-88832accecd1','default.png',0,'行李服务','d3759e96-1aec-4d5f-8bd1-f1b9e7d630ad',0,0,'2015-10-19 17:27:29',0,0,0,9,'site',1445246849771,'##'),('10cc580a-7f73-41b0-8f8b-d264bb3b892f','default.png',0,'会员中心',NULL,0,0,'2015-10-14 16:46:12',0,0,0,1,'site',1444812372313,'#'),('11e05cb9-0f9f-4771-9c7c-cc9809956439','default.png',0,'新闻回收站','dbe283e0-6fb1-4b13-a57a-0517a59f5474',0,0,'2015-10-13 13:38:36',0,0,0,2,'site',1444714716841,'/news_recycle.action?versionFlag=site'),('1b219275-1f31-4d72-ac8a-86f62caac828','default.png',0,'电瓶车管理','db188e80-1d5d-4d3f-81b3-484206ee2107',0,0,'2015-10-19 17:33:33',0,0,0,3,'site',1445247213789,'##'),('22b70bc4-6dbb-4015-953a-3c092eb9798c','default.png',0,'代码管理','81166827-47a1-44ec-a9e0-81d60e3f72f8',0,0,'2015-10-13 09:56:54',0,0,0,10,'site',1444701414820,'##'),('2990a75f-bd36-4e61-a753-4415495a95eb','default.png',0,'新增机场派出所','d9c0a96c-ba87-49d3-a815-71f38a4b0a5f',0,0,'2015-10-21 15:11:17',0,0,0,0,'site',1445411477180,'/newsClass_add.action?versionFlag=site'),('2cd3982c-d469-440d-922a-75e965968f23','default.png',0,'删除模块','22b70bc4-6dbb-4015-953a-3c092eb9798c',0,0,'2015-10-13 09:58:02',0,0,0,1,'site',1444701482888,'/generateTemplateCode_delete.action?versionFlag=site'),('337d7c3d-27ba-4d39-92c7-39f72fcf2db4','default.png',0,'管理机场派出所','366a40f2-6f5b-4c66-a168-9d774cb69507',0,0,'2015-10-21 15:33:21',0,0,0,0,'site',1445412801577,'/news_manage.action?versionFlag=site'),('33db1afb-862b-44a1-b54e-2fef5b2eb003','default.png',0,'新闻类别','b8a2a9e1-3f6d-4ec3-954f-5aa96bb1c3c6',0,0,'2015-10-13 13:34:43',0,0,0,5,'site',1444714483057,'##'),('35b400fb-2f3f-42cd-be0d-580d06b60d86','default.png',0,'新增机场派出所','366a40f2-6f5b-4c66-a168-9d774cb69507',0,0,'2015-10-21 15:33:21',0,0,0,0,'site',1445412801518,'/news_add.action?versionFlag=site'),('38cda477-f403-4720-9d05-c90eef30c73d','default.png',0,'新增新闻','dbe283e0-6fb1-4b13-a57a-0517a59f5474',0,0,'2015-10-13 13:37:43',0,0,0,5,'site',1444714663196,'/news_add.action?versionFlag=site'),('39f7e44b-d90a-41aa-8327-895aee0ea7ec','default.png',0,'管理新闻','dbe283e0-6fb1-4b13-a57a-0517a59f5474',0,0,'2015-10-13 13:38:07',0,0,0,4,'site',1444714687855,'/news_manage.action?fversionFlag=site'),('3cad172d-eaba-4748-903e-617ea2b6df8f','default.png',0,'新增用户信息','f24c449b-42f7-4628-85d7-e4012905ec49',0,0,'2015-10-19 14:31:58',0,0,0,3,'site',1445236318261,'/systemUser_add.action?versionFlag=site'),('424e93ea-a975-48d3-b53e-3ced5f795a98','default.png',0,'评价系统','db188e80-1d5d-4d3f-81b3-484206ee2107',0,0,'2015-10-19 17:35:45',0,0,0,2,'site',1445247345154,'##'),('46d3dffa-4d32-4741-8582-b07a5c5414ae','default.png',0,'系统参数设置','d6f5537b-4534-4ba3-a351-f6be97537d2e',0,0,'2015-10-22 20:54:17',0,0,0,1,'site',1445518457147,'/systemConfig_setting.action?versionFlag=site'),('4738dbcb-bdc9-496c-ab7f-cd1a4d4b5a8b','default.png',0,'新增公告栏','159648a9-ed33-4a26-9da0-c9aab2ff4a9f',0,0,'2015-10-21 17:15:58',0,0,0,3,'site',1445418958359,'/news_add.action?versionFlag=site'),('47ed5424-7189-449f-8a01-58ac7613834a','default.png',0,'新增机场派出所','92caf843-baba-400d-8927-ecfaf5c865dc',0,0,'2015-10-21 15:19:46',0,0,0,0,'site',1445411986199,'/news_add.action?versionFlag=site'),('49019030-af3b-4edb-bec3-ae7e6c55a279','default.png',0,'机场派出所回收站','366a40f2-6f5b-4c66-a168-9d774cb69507',0,0,'2015-10-21 15:33:21',0,0,0,0,'site',1445412801601,'/news_recycle.action?versionFlag=site'),('4b18167e-ecad-489e-87b6-44c90d04c9a4','default.png',0,'管理机场派出所','92caf843-baba-400d-8927-ecfaf5c865dc',0,0,'2015-10-21 15:19:46',0,0,0,0,'site',1445411986229,'/news_manage.action?versionFlag=site'),('4f1838d1-20c0-4b43-a698-7707f62c625f','default.png',0,'公共设施','d3759e96-1aec-4d5f-8bd1-f1b9e7d630ad',0,0,'2015-10-19 17:29:02',0,0,0,6,'site',1445246942929,'##'),('51e21a1a-3757-4dde-b813-1f78dbf881fa','default.png',0,'电瓶车信息回收站','1b219275-1f31-4d72-ac8a-86f62caac828',0,0,'2015-10-19 17:34:43',0,0,0,2,'site',1445247283170,'/electrombile_recycle.action?versionFlag=site'),('5a57bdb0-33df-4879-a1a1-0a2b805b1676','default.png',0,'商旅服务','d3759e96-1aec-4d5f-8bd1-f1b9e7d630ad',0,0,'2015-10-19 17:28:38',0,0,0,7,'site',1445246918254,'##'),('60f4e4bf-b13a-4af5-9592-8ef5019fd32a','default.png',0,'管理新闻模版','e1a8a328-5e7c-4471-9174-efea2a05e199',0,0,'2015-10-22 16:30:05',0,0,0,2,'site',1445502605177,'/pageRedirectTemplate_manage.action?versionFlag=site'),('64050402-de11-47a8-a8e9-1ec9ed99f7e0','default.png',0,'管理权限信息','ac4ccdbc-4b5b-46e5-862f-ea8e319b56a5',0,0,'2015-10-19 14:34:48',0,0,0,3,'site',1445236488864,'/privilegeInfo_manage.action?versionFlag=site'),('6661c054-ba77-4ef7-8036-66b36073c3b0','default.png',0,'新增权限信息','ac4ccdbc-4b5b-46e5-862f-ea8e319b56a5',0,0,'2015-10-19 14:34:27',0,0,0,4,'site',1445236467998,'/privilegeInfo_add.action?versionFlag=site'),('6b54fa40-45b7-4c08-81dc-28a4e6ca5c82','default.png',0,'管理会员等级','f27e2913-3bcd-48f6-bc2d-ccbca5ccb840',0,0,'2015-10-14 16:47:46',0,0,0,4,'site',1444812466703,'/memberLevel_manage.action?versionFlag=site'),('7b52f775-b5dc-4d73-b041-452d5c4d0805','default.png',0,'会员信息','10cc580a-7f73-41b0-8f8b-d264bb3b892f',0,0,'2015-10-19 14:38:22',0,0,0,4,'site',1445236702180,'##'),('7b5b131b-00cd-488a-acf3-966258070ef9','default.png',0,'管理角色信息','879b2bc4-e3d4-42de-bfc0-43774cd0482f',0,0,'2015-10-19 14:33:36',0,0,0,3,'site',1445236416041,'/roleInfo_manage.action?versionFlag=site'),('7cbad721-91a7-460b-8916-bb19bbe5e0f2','default.png',0,'系统菜单设置','d6f5537b-4534-4ba3-a351-f6be97537d2e',0,0,'2015-10-13 10:00:30',0,0,0,1,'site',1444701630636,'systemModule_manage.action?versionFlag=site'),('7cd4debb-1981-4c07-867c-6d7f6fc1e955','default.png',0,'角色信息回收站','879b2bc4-e3d4-42de-bfc0-43774cd0482f',0,0,'2015-10-19 14:33:51',0,0,0,1,'site',1445236431735,'/roleInfo_recycle.action?versionFlag=site'),('8161bdd4-2f90-4079-a434-8f1d8d937049','default.png',0,'用户信息回收站','f24c449b-42f7-4628-85d7-e4012905ec49',0,0,'2015-10-19 14:32:43',0,0,0,1,'site',1445236363903,'/systemUser_recycle.action?versionFlag=site'),('879b2bc4-e3d4-42de-bfc0-43774cd0482f','default.png',0,'角色信息','8ecea40a-bd6b-4dfe-914c-aa44bc5fc201',0,0,'2015-10-19 14:31:14',0,0,0,4,'site',1445236274784,'##'),('8ac3c968-6a0a-4435-aaf6-6f392c57a620','default.png',0,'新增类别','33db1afb-862b-44a1-b54e-2fef5b2eb003',0,0,'2015-10-13 13:36:00',0,0,0,5,'site',1444714560251,'/newsClass_add.action?versionFlag=site'),('8dea01b1-9bfd-4635-be39-c1fa6438b85b','0',1,'系统管理',NULL,1,1,'2015-03-17 11:35:21',0,0,0,0,'site',109842384,'#'),('8df8840d-610b-409d-a2bb-e912d3c20ec5','default.png',0,'国外乘机须知','1bfc2489-75db-4e79-9972-2516db79b12e',0,0,'2015-10-19 17:27:11',0,0,0,1,'site',1445246831912,'##'),('8e22e8c8-649b-4286-baf4-0c48e88fdfb6','default.png',0,'添加模块','22b70bc4-6dbb-4015-953a-3c092eb9798c',0,0,'2015-10-13 09:57:37',0,0,0,2,'site',1444701457307,'/generateTemplateCode_add.action?versionFag=site'),('8ecea40a-bd6b-4dfe-914c-aa44bc5fc201','default.png',0,'权限中心',NULL,0,0,'2015-10-19 14:30:42',0,0,0,3,'site',1445236242832,'#'),('98b09cf5-837d-431c-b24c-b35e24e259d4','default.png',0,'网上值机','db188e80-1d5d-4d3f-81b3-484206ee2107',0,0,'2015-10-19 17:33:09',0,0,0,4,'site',1445247189926,'##'),('9b427f92-d323-4800-b98f-151cd6486237','default.png',0,'评论回收站','08f8586c-9013-48d0-81ec-362be4a97d4a',0,0,'2015-10-13 13:40:10',0,0,0,1,'site',1444714810847,'/newsComment_recycle.action?flag=site'),('9cc1a792-91bf-433d-963c-d6b8b088438e','default.png',0,'航班查询','db188e80-1d5d-4d3f-81b3-484206ee2107',0,0,'2015-10-19 17:32:41',0,0,0,5,'site',1445247161506,'##'),('9d12aa16-5cdf-4fb7-b7d3-81058ed8024f','default.png',0,'权限信息回收站','ac4ccdbc-4b5b-46e5-862f-ea8e319b56a5',0,0,'2015-10-19 14:35:07',0,0,0,1,'site',1445236507356,'/privilegeInfo_recycle.action?versionFlag=site'),('a0b1bbc8-0fab-4997-92dc-d216898562ac','default.png',0,'会员信息回收站','7b52f775-b5dc-4d73-b041-452d5c4d0805',0,0,'2015-10-19 14:40:11',0,0,0,2,'site',1445236811920,'/member_recycle.action?versionFlag=site'),('a11d5394-3b77-4b5f-8556-b9235d6c0daf','default.png',0,'新增会员信息','7b52f775-b5dc-4d73-b041-452d5c4d0805',0,0,'2015-10-19 14:39:00',0,0,0,4,'site',1445236740146,'/member_add.action?versionFlag=site'),('a33d3db2-c3cf-434f-9a35-8d40e0de2e8d','default.png',0,'机场派出所回收站','92caf843-baba-400d-8927-ecfaf5c865dc',0,0,'2015-10-21 15:19:46',0,0,0,0,'site',1445411986253,'/news_recycle.action?versionFlag=site'),('a589064e-4bb9-4d62-a100-21f734154200','default.png',0,'机票验真','db188e80-1d5d-4d3f-81b3-484206ee2107',0,0,'2015-10-19 17:35:23',0,0,0,3,'site',1445247323524,'##'),('a8b1f0b4-0afd-4d63-9d14-ef77f045c7a2','default.png',0,'机场派出所回收站','d9c0a96c-ba87-49d3-a815-71f38a4b0a5f',0,0,'2015-10-21 15:11:17',0,0,0,0,'site',1445411477297,'/newsClass_recycle.action?versionFlag=site'),('ac4ccdbc-4b5b-46e5-862f-ea8e319b56a5','default.png',0,'权限信息','8ecea40a-bd6b-4dfe-914c-aa44bc5fc201',0,0,'2015-10-19 14:31:30',0,0,0,3,'site',1445236290867,'##'),('ae9e7b3c-2dfe-49fb-b48a-5cc7d3644e3e','default.png',0,'新增电瓶车','1b219275-1f31-4d72-ac8a-86f62caac828',0,0,'2015-10-19 17:33:52',0,0,0,4,'site',1445247232192,'/electrombile_add.action?versionFlag=site'),('af9a68c5-84b9-4ffd-b238-93711b0797a8','default.png',0,'国内乘机须知','1bfc2489-75db-4e79-9972-2516db79b12e',0,0,'2015-10-19 17:26:55',0,0,0,2,'site',1445246815964,'##'),('b2240fb3-61e2-427e-8cd4-ec474380594d','default.png',0,'关注我们','d3759e96-1aec-4d5f-8bd1-f1b9e7d630ad',0,0,'2015-10-19 17:29:41',0,0,0,4,'site',1445246981900,'##'),('b34f6440-c2bb-48d4-9f6d-b2e603b8624f','default.png',0,'新增模版','e1a8a328-5e7c-4471-9174-efea2a05e199',0,0,'2015-10-22 16:29:37',0,0,0,3,'site',1445502577035,'/pageRedirectTemplate_add.action?versionFlag=site'),('b6f04f26-86b9-4a47-8fff-55f7b40d61e0','default.png',0,'管理类别','33db1afb-862b-44a1-b54e-2fef5b2eb003',0,0,'2015-10-13 13:36:39',0,0,0,4,'site',1444714599410,'/newsClass_manage.action?versionFlag=site'),('b783233e-d891-4651-8df3-aaae13ab0b69','default.png',0,'管理评论','08f8586c-9013-48d0-81ec-362be4a97d4a',0,0,'2015-10-13 13:39:29',0,0,0,3,'site',1444714769844,'/newsComment_manage.action?flag=site'),('b8a2a9e1-3f6d-4ec3-954f-5aa96bb1c3c6','default.png',0,'资讯中心',NULL,0,0,'2015-10-13 13:34:25',0,0,0,14,'site',1444714465398,'#'),('ce2eaf51-93f2-49c4-b9bf-a4fed3e7f599','default.png',0,'管理会员信息','7b52f775-b5dc-4d73-b041-452d5c4d0805',0,0,'2015-10-19 14:39:23',0,0,0,3,'site',1445236763810,'/member_manage.action?versionFlag=site'),('d0ec8c40-9569-4d00-a389-b853748bf849','default.png',0,'长水文化','d3759e96-1aec-4d5f-8bd1-f1b9e7d630ad',0,0,'2015-10-19 17:29:24',0,0,0,5,'site',1445246964696,'##'),('d3759e96-1aec-4d5f-8bd1-f1b9e7d630ad','default.png',0,'服务管理','db188e80-1d5d-4d3f-81b3-484206ee2107',0,0,'2015-10-19 17:30:46',0,0,0,1,'site',1445247046471,'##'),('d39e3ad7-a482-462f-9eb9-454b26b9db5b','default.png',0,'公告栏回收站','159648a9-ed33-4a26-9da0-c9aab2ff4a9f',0,0,'2015-10-21 17:15:58',0,0,0,1,'site',1445418958409,'/news_recycle.action?versionFlag=site'),('d5a06e48-e12f-4146-b07e-7f7e85b1f1c0','default.png',0,'爱心服务','d3759e96-1aec-4d5f-8bd1-f1b9e7d630ad',0,0,'2015-10-19 17:28:13',0,0,0,8,'site',1445246893342,'##'),('d61b2487-b747-4321-a4c1-9b527bfc23f2','default.png',0,'管理用户信息','f24c449b-42f7-4628-85d7-e4012905ec49',0,0,'2015-10-19 14:32:19',0,0,0,2,'site',1445236339760,'/systemUser_manage.action?versionFlag=site'),('d6f5537b-4534-4ba3-a351-f6be97537d2e','default.png',0,'系统基本设置','8dea01b1-9bfd-4635-be39-c1fa6438b85b',0,0,'2015-03-17 11:42:11',0,0,0,1,'site',1426563731354,'/systemConfig_setting.action?versionFlag=site'),('db188e80-1d5d-4d3f-81b3-484206ee2107','default.png',0,'服务中心',NULL,0,0,'2015-10-19 17:25:42',0,0,0,3,'site',1445246742051,'##'),('dbe283e0-6fb1-4b13-a57a-0517a59f5474','default.png',0,'新闻信息','b8a2a9e1-3f6d-4ec3-954f-5aa96bb1c3c6',0,0,'2015-10-13 13:35:02',0,0,0,4,'site',1444714502262,'##'),('e1a8a328-5e7c-4471-9174-efea2a05e199','default.png',0,'新闻模版管理','b8a2a9e1-3f6d-4ec3-954f-5aa96bb1c3c6',0,0,'2015-10-22 16:28:58',0,0,0,1,'site',1445502538451,'##'),('e577d00a-9afe-4f9c-a2f6-b84be30a0884','default.png',0,'管理电瓶车信息','1b219275-1f31-4d72-ac8a-86f62caac828',0,0,'2015-10-19 17:34:13',0,0,0,3,'site',1445247253028,'/electrombile_manage.action?versionFlag=site'),('e6c92868-3109-4e0b-8fa9-6d781c873a19','default.png',0,'新增角色信息','879b2bc4-e3d4-42de-bfc0-43774cd0482f',0,0,'2015-10-19 14:33:15',0,0,0,4,'site',1445236395081,'/roleInfo_add.action?versionFlag=site'),('f0af133d-e253-490d-859e-481c072393ee','default.png',0,'等级回收站','f27e2913-3bcd-48f6-bc2d-ccbca5ccb840',0,0,'2015-10-14 16:48:12',0,0,0,3,'site',1444812492195,'/memberLevel_recycle.action?versionFlag=site'),('f24c449b-42f7-4628-85d7-e4012905ec49','default.png',0,'用户信息','8ecea40a-bd6b-4dfe-914c-aa44bc5fc201',0,0,'2015-10-19 14:31:01',0,0,0,5,'site',1445236261795,'##'),('f27e2913-3bcd-48f6-bc2d-ccbca5ccb840','default.png',0,'会员等级','10cc580a-7f73-41b0-8f8b-d264bb3b892f',0,0,'2015-10-14 16:46:31',0,0,0,5,'site',1444812391715,'##'),('f78238fd-a4e5-464c-b474-4f184493296b','default.png',0,'便民服务','d3759e96-1aec-4d5f-8bd1-f1b9e7d630ad',0,0,'2015-10-19 17:27:44',0,0,0,8,'site',1445246864781,'##'),('fdb9fe15-c954-4bf7-adf8-0893038a8b71','default.png',0,'免责申明','d3759e96-1aec-4d5f-8bd1-f1b9e7d630ad',0,0,'2015-10-19 17:30:01',0,0,0,3,'site',1445247001851,'##'),('ff956575-07a9-4984-b068-c85473609687','default.png',0,'管理机场派出所','d9c0a96c-ba87-49d3-a815-71f38a4b0a5f',0,0,'2015-10-21 15:11:17',0,0,0,0,'site',1445411477263,'/newsClass_manage.action?versionFlag=site');
/*!40000 ALTER TABLE `ticket_systemmodule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_systemplugin`
--

DROP TABLE IF EXISTS `ticket_systemplugin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_systemplugin` (
  `id` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_systemplugin`
--

LOCK TABLES `ticket_systemplugin` WRITE;
/*!40000 ALTER TABLE `ticket_systemplugin` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_systemplugin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_systemupdatelog`
--

DROP TABLE IF EXISTS `ticket_systemupdatelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_systemupdatelog` (
  `id` varchar(255) NOT NULL,
  `newVersion` varchar(20) DEFAULT NULL,
  `oldVersion` varchar(20) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  `updateContent` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_systemupdatelog`
--

LOCK TABLES `ticket_systemupdatelog` WRITE;
/*!40000 ALTER TABLE `ticket_systemupdatelog` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_systemupdatelog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_systemuser`
--

DROP TABLE IF EXISTS `ticket_systemuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_systemuser` (
  `id` varchar(255) NOT NULL,
  `loginId` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_systemuser`
--

LOCK TABLES `ticket_systemuser` WRITE;
/*!40000 ALTER TABLE `ticket_systemuser` DISABLE KEYS */;
INSERT INTO `ticket_systemuser` VALUES ('18ac871e-2669-4933-ae2f-04c4e5cc8192','test','测试用户','111111','13899999999',1,0,0,'2015-10-19 15:43:54',0,0,0,0,'site',1445240634518),('8dea01b1-9bfd-4635-be39-c1fa6438b85b','yngk','云南冠科','111111','13888888888',1,1,1,'2015-03-17 11:37:23',0,0,0,0,'site',24243242424);
/*!40000 ALTER TABLE `ticket_systemuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_systemuserloginlog`
--

DROP TABLE IF EXISTS `ticket_systemuserloginlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_systemuserloginlog` (
  `id` varchar(255) NOT NULL,
  `ip` varchar(50) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  `systemUser_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_systemuserloginlog`
--

LOCK TABLES `ticket_systemuserloginlog` WRITE;
/*!40000 ALTER TABLE `ticket_systemuserloginlog` DISABLE KEYS */;
INSERT INTO `ticket_systemuserloginlog` VALUES ('04a1b44d-1dfd-4f5a-8d2f-bf50c645c6c6','0:0:0:0:0:0:0:1',0,0,'2015-10-22 09:49:23',0,0,0,0,'site',1445478563192,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('05116ab7-84f0-4cb1-ae3f-8699898a2076','0:0:0:0:0:0:0:1',0,0,'2015-10-22 08:32:20',0,0,0,0,'site',1445473940797,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('0b4bf6cb-28b5-4c8c-94a4-bb37c64c4f5a','0:0:0:0:0:0:0:1',0,0,'2015-10-22 11:03:07',0,0,0,0,'site',1445482987068,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('0cd1a2d5-4039-4367-b9cb-837f1d996f98','0:0:0:0:0:0:0:1',0,0,'2015-10-21 15:12:03',0,0,0,0,'site',1445411523266,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('0ce273e0-54f4-4aad-8bf2-29c1373698d9','0:0:0:0:0:0:0:1',0,0,'2015-10-21 13:47:40',0,0,0,0,'site',1445406460915,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('136a0286-7e36-47f0-add3-3a1c3e3071be','0:0:0:0:0:0:0:1',0,0,'2015-10-21 18:01:13',0,0,0,0,'site',1445421673860,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('1bbcbec6-704c-4148-822b-edfcd758c667','0:0:0:0:0:0:0:1',0,0,'2015-10-22 16:49:45',0,0,0,0,'site',1445503785093,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('1fc75aa9-a5a6-44e9-a8b2-13d7ab683428','0:0:0:0:0:0:0:1',0,0,'2015-10-21 17:11:58',0,0,0,0,'site',1445418718110,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('246fd5fe-664b-40c6-a842-44013d9c2f5c','0:0:0:0:0:0:0:1',0,0,'2015-10-22 09:50:01',0,0,0,0,'site',1445478601353,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('28a3d128-e0c3-4ddc-8472-e046d061707d','fe80:0:0:0:b981:6552:28d4:b8db',0,0,'2015-10-22 14:12:45',0,0,0,0,'site',1445494365694,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('29e25fb6-b25f-4091-a149-d8bcf36f475f','0:0:0:0:0:0:0:1',0,0,'2015-10-22 08:15:00',0,0,0,0,'site',1445472900913,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('3132e2ae-b6e8-4166-9aa6-fe0e1aab4d96','0:0:0:0:0:0:0:1',0,0,'2015-10-21 17:27:36',0,0,0,0,'site',1445419656378,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('34c18352-8dd0-44da-a3eb-ccf6297b0cad','0:0:0:0:0:0:0:1',0,0,'2015-10-22 09:44:49',0,0,0,0,'site',1445478289296,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('3609b9a8-0090-4bee-8bc2-632f0e103733','0:0:0:0:0:0:0:1',0,0,'2015-10-21 15:19:19',0,0,0,0,'site',1445411959250,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('3e8c907a-5322-4fa1-ab3c-41d81fc6f304','0:0:0:0:0:0:0:1',0,0,'2015-10-21 17:55:28',0,0,0,0,'site',1445421328090,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('44593b35-4afb-421c-87cd-46ce685d7225','0:0:0:0:0:0:0:1',0,0,'2015-10-22 08:33:01',0,0,0,0,'site',1445473981316,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('50cc7c80-73ee-45eb-a1fa-a09e9877bdb3','0:0:0:0:0:0:0:1',0,0,'2015-10-22 13:34:34',0,0,0,0,'site',1445492074915,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('5b68b21d-f1d9-4d4d-9cac-eb83b9b8836d','127.0.0.1',0,0,'2015-10-21 15:05:52',0,0,0,0,'site',1445411152384,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('5cf74ff4-7612-486a-a964-9b131f5cec00','0:0:0:0:0:0:0:1',0,0,'2015-10-22 20:53:32',0,0,0,0,'site',1445518412553,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('5f7fb590-0d44-48db-abc6-492ff9e23f98','0:0:0:0:0:0:0:1',0,0,'2015-10-21 16:27:19',0,0,0,0,'site',1445416039539,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('6478c94a-741b-461b-838c-c2577f3d1e67','fe80:0:0:0:b981:6552:28d4:b8db',0,0,'2015-10-21 10:51:58',0,0,0,0,'site',1445395918196,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('6962bc21-a1fc-4cae-8a93-18711f188734','0:0:0:0:0:0:0:1',0,0,'2015-10-19 17:25:51',0,0,0,0,'site',1445246751417,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('6acc3f68-345a-40a2-aa3e-d70bfc3327bf','fe80:0:0:0:b981:6552:28d4:b8db',0,0,'2015-10-21 12:02:23',0,0,0,0,'site',1445400143913,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('71f96cb8-c9f2-4e25-814a-cd4ecbea7f75','0:0:0:0:0:0:0:1',0,0,'2015-10-21 18:26:19',0,0,0,0,'site',1445423179462,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('7e460a07-9793-4ba4-897b-268055de5b24','0:0:0:0:0:0:0:1',0,0,'2015-10-20 17:24:01',0,0,0,0,'site',1445333041140,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('7fa0eba0-8635-4271-9dae-0f8a5da70c05','0:0:0:0:0:0:0:1',0,0,'2015-10-22 23:04:47',0,0,0,0,'site',1445526287201,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('87010646-b4ab-4cf4-9c24-e48cdac7e693','0:0:0:0:0:0:0:1',0,0,'2015-10-22 17:05:07',0,0,0,0,'site',1445504707010,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('8b456573-adb1-4f22-8d3c-964213ded29a','fe80:0:0:0:b981:6552:28d4:b8db',0,0,'2015-10-21 10:36:37',0,0,0,0,'site',1445394997287,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('9414e768-6198-42f5-b024-a86837d5930b','fe80:0:0:0:b981:6552:28d4:b8db',0,0,'2015-10-21 10:24:59',0,0,0,0,'site',1445394299008,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('9c0087f5-607b-4015-bb0c-20696e003122','fe80:0:0:0:b981:6552:28d4:b8db',0,0,'2015-10-21 09:51:51',0,0,0,0,'site',1445392311907,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('a04d2d52-7735-4eb8-87e3-b415e19eb960','fe80:0:0:0:b981:6552:28d4:b8db',0,0,'2015-10-21 11:27:57',0,0,0,0,'site',1445398077750,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('a4d190dc-6f76-4e30-a95a-1c3d0db21640','0:0:0:0:0:0:0:1',0,0,'2015-10-22 08:48:02',0,0,0,0,'site',1445474882455,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('ab6e2cb5-a1da-4a5e-a22b-819341818dc1','fe80:0:0:0:b981:6552:28d4:b8db',0,0,'2015-10-21 11:01:04',0,0,0,0,'site',1445396464735,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('b005cf23-c16f-45bc-a3cf-1068a6c69d7d','fe80:0:0:0:b981:6552:28d4:b8db',0,0,'2015-10-21 11:41:36',0,0,0,0,'site',1445398896395,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('b1047b64-713f-462f-a850-3a7614deb7e7','0:0:0:0:0:0:0:1',0,0,'2015-10-21 16:26:18',0,0,0,0,'site',1445415978439,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('b88a22c1-6a81-4d6e-873b-c49124bd89b6','0:0:0:0:0:0:0:1',0,0,'2015-10-21 14:01:44',0,0,0,0,'site',1445407304121,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('baf54258-dd36-4370-8ea2-86649812aa49','0:0:0:0:0:0:0:1',0,0,'2015-10-22 09:48:39',0,0,0,0,'site',1445478519769,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('c310d658-80de-44c2-9a61-5ca96ed6ef1e','fe80:0:0:0:b981:6552:28d4:b8db',0,0,'2015-10-22 15:40:23',0,0,0,0,'site',1445499623244,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('c9e2acf4-d25d-48af-8134-86c4292fbc3a','0:0:0:0:0:0:0:1',0,0,'2015-10-22 11:51:17',0,0,0,0,'site',1445485877287,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('ca841686-cd95-4ec3-81c5-e41726d889db','fe80:0:0:0:b981:6552:28d4:b8db',0,0,'2015-10-22 14:12:16',0,0,0,0,'site',1445494336352,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('ce0f4fce-d8ef-4fa5-b7f2-be3f2ff77737','0:0:0:0:0:0:0:1',0,0,'2015-10-20 18:02:53',0,0,0,0,'site',1445335373779,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('ce233c4c-e090-4868-a305-2c7113931079','0:0:0:0:0:0:0:1',0,0,'2015-10-21 16:59:16',0,0,0,0,'site',1445417956987,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('da417256-a4e3-485d-8650-463d702f220d','0:0:0:0:0:0:0:1',0,0,'2015-10-22 13:01:20',0,0,0,0,'site',1445490080240,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('dd5af65d-1df4-40b0-9fc5-63d35429f82d','fe80:0:0:0:b981:6552:28d4:b8db',0,0,'2015-10-22 17:43:06',0,0,0,0,'site',1445506986874,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('e0537d57-3a19-43ae-a556-c4f856c2f9ae','0:0:0:0:0:0:0:1',0,0,'2015-10-21 17:32:15',0,0,0,0,'site',1445419935409,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('ecfc7661-9a6e-4a8f-902e-4fbf5917214a','fe80:0:0:0:b981:6552:28d4:b8db',0,0,'2015-10-20 17:26:12',0,0,0,0,'site',1445333172579,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('ee8cc3e3-d9f2-49cc-9d4f-346bc8489ced','0:0:0:0:0:0:0:1',0,0,'2015-10-22 21:05:25',0,0,0,0,'site',1445519125570,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('f095743d-4e2c-4a9f-ac14-911781ebefcd','0:0:0:0:0:0:0:1',0,0,'2015-10-21 16:25:47',0,0,0,0,'site',1445415947585,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('f2b80761-923c-445a-9300-e3131936b0e0','0:0:0:0:0:0:0:1',0,0,'2015-10-21 17:53:37',0,0,0,0,'site',1445421217330,'8dea01b1-9bfd-4635-be39-c1fa6438b85b'),('f6f27ba6-8199-4010-ac06-a3695c100743','0:0:0:0:0:0:0:1',0,0,'2015-10-21 17:56:13',0,0,0,0,'site',1445421373299,'8dea01b1-9bfd-4635-be39-c1fa6438b85b');
/*!40000 ALTER TABLE `ticket_systemuserloginlog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_systemuseroperation`
--

DROP TABLE IF EXISTS `ticket_systemuseroperation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_systemuseroperation` (
  `id` varchar(255) NOT NULL,
  `content` longtext,
  `ip` varchar(50) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  `systemUser_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_systemuseroperation`
--

LOCK TABLES `ticket_systemuseroperation` WRITE;
/*!40000 ALTER TABLE `ticket_systemuseroperation` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_systemuseroperation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_systemversion`
--

DROP TABLE IF EXISTS `ticket_systemversion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_systemversion` (
  `id` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_systemversion`
--

LOCK TABLES `ticket_systemversion` WRITE;
/*!40000 ALTER TABLE `ticket_systemversion` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_systemversion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_userrole`
--

DROP TABLE IF EXISTS `ticket_userrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_userrole` (
  `id` varchar(255) NOT NULL,
  `roleId` varchar(255) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_userrole`
--

LOCK TABLES `ticket_userrole` WRITE;
/*!40000 ALTER TABLE `ticket_userrole` DISABLE KEYS */;
INSERT INTO `ticket_userrole` VALUES ('0c3f607c-6b7e-4f6f-8c13-1dbbff7192f4','442941c4-17f1-482f-94d8-d9d39860712d',0,0,'2015-10-22 14:17:25',0,0,0,0,'site',1445494645124,'18ac871e-2669-4933-ae2f-04c4e5cc8192'),('17633903-57b9-41a5-918c-1034d179eccb','2a1dcfe2-529c-4807-92bb-5f9a63dec061',0,0,'2015-10-21 17:00:03',0,0,0,0,'site',1445418003828,'8dea01b1-9bfd-4635-be39-c1fa6438b85b');
/*!40000 ALTER TABLE `ticket_userrole` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-22 23:49:36
