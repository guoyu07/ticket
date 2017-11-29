-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.5.45 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  8.2.0.4675
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 ticket.bjdjcar 结构
CREATE TABLE IF NOT EXISTS `bjdjcar` (
  `id` varchar(255) NOT NULL,
  `factory_time` datetime DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `member_id` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `operation_user_id` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `factoryTime` datetime DEFAULT NULL,
  `systemUser_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ticket.bjdjcar 的数据：~0 rows (大约)
DELETE FROM `bjdjcar`;
/*!40000 ALTER TABLE `bjdjcar` DISABLE KEYS */;
/*!40000 ALTER TABLE `bjdjcar` ENABLE KEYS */;


-- 导出  表 ticket.bjdjcarorder 结构
CREATE TABLE IF NOT EXISTS `bjdjcarorder` (
  `id` varchar(255) NOT NULL,
  `car_id` varchar(255) DEFAULT NULL,
  `member_id` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ticket.bjdjcarorder 的数据：~0 rows (大约)
DELETE FROM `bjdjcarorder`;
/*!40000 ALTER TABLE `bjdjcarorder` DISABLE KEYS */;
/*!40000 ALTER TABLE `bjdjcarorder` ENABLE KEYS */;


-- 导出  表 ticket.bjdjcarorderprocess 结构
CREATE TABLE IF NOT EXISTS `bjdjcarorderprocess` (
  `id` varchar(255) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  `phase` varchar(255) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ticket.bjdjcarorderprocess 的数据：~0 rows (大约)
DELETE FROM `bjdjcarorderprocess`;
/*!40000 ALTER TABLE `bjdjcarorderprocess` DISABLE KEYS */;
/*!40000 ALTER TABLE `bjdjcarorderprocess` ENABLE KEYS */;


-- 导出  表 ticket.bjdjcomment 结构
CREATE TABLE IF NOT EXISTS `bjdjcomment` (
  `id` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `member_id` varchar(255) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ticket.bjdjcomment 的数据：~0 rows (大约)
DELETE FROM `bjdjcomment`;
/*!40000 ALTER TABLE `bjdjcomment` DISABLE KEYS */;
/*!40000 ALTER TABLE `bjdjcomment` ENABLE KEYS */;


-- 导出  表 ticket.bjdjhall 结构
CREATE TABLE IF NOT EXISTS `bjdjhall` (
  `id` varchar(255) NOT NULL,
  `capacity` int(11) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `latitude` varchar(255) DEFAULT NULL,
  `longitude` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ticket.bjdjhall 的数据：~0 rows (大约)
DELETE FROM `bjdjhall`;
/*!40000 ALTER TABLE `bjdjhall` DISABLE KEYS */;
/*!40000 ALTER TABLE `bjdjhall` ENABLE KEYS */;


-- 导出  表 ticket.bjdjorder 结构
CREATE TABLE IF NOT EXISTS `bjdjorder` (
  `id` varchar(255) NOT NULL,
  `member_id` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ticket.bjdjorder 的数据：~0 rows (大约)
DELETE FROM `bjdjorder`;
/*!40000 ALTER TABLE `bjdjorder` DISABLE KEYS */;
/*!40000 ALTER TABLE `bjdjorder` ENABLE KEYS */;


-- 导出  表 ticket.bjdjqueue 结构
CREATE TABLE IF NOT EXISTS `bjdjqueue` (
  `id` varchar(255) NOT NULL,
  `hall_id` varchar(255) DEFAULT NULL,
  `member_id` varchar(255) DEFAULT NULL,
  `sequence` int(11) DEFAULT NULL,
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

-- 正在导出表  ticket.bjdjqueue 的数据：~0 rows (大约)
DELETE FROM `bjdjqueue`;
/*!40000 ALTER TABLE `bjdjqueue` DISABLE KEYS */;
/*!40000 ALTER TABLE `bjdjqueue` ENABLE KEYS */;


-- 导出  表 ticket.bjdjservicecode 结构
CREATE TABLE IF NOT EXISTS `bjdjservicecode` (
  `id` varchar(255) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  `type_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ticket.bjdjservicecode 的数据：~0 rows (大约)
DELETE FROM `bjdjservicecode`;
/*!40000 ALTER TABLE `bjdjservicecode` DISABLE KEYS */;
/*!40000 ALTER TABLE `bjdjservicecode` ENABLE KEYS */;


-- 导出  表 ticket.bjdjservicecodelog 结构
CREATE TABLE IF NOT EXISTS `bjdjservicecodelog` (
  `id` varchar(255) NOT NULL,
  `member_id` varchar(255) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `operation_user_id` varchar(255) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `systemUser_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ticket.bjdjservicecodelog 的数据：~0 rows (大约)
DELETE FROM `bjdjservicecodelog`;
/*!40000 ALTER TABLE `bjdjservicecodelog` DISABLE KEYS */;
/*!40000 ALTER TABLE `bjdjservicecodelog` ENABLE KEYS */;


-- 导出  表 ticket.sysdictionary 结构
CREATE TABLE IF NOT EXISTS `sysdictionary` (
  `id` varchar(255) NOT NULL,
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
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ticket.sysdictionary 的数据：~0 rows (大约)
DELETE FROM `sysdictionary`;
/*!40000 ALTER TABLE `sysdictionary` DISABLE KEYS */;
/*!40000 ALTER TABLE `sysdictionary` ENABLE KEYS */;


-- 导出  表 ticket.sysuserextends 结构
CREATE TABLE IF NOT EXISTS `sysuserextends` (
  `id` varchar(255) NOT NULL,
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
  `type` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ticket.sysuserextends 的数据：~0 rows (大约)
DELETE FROM `sysuserextends`;
/*!40000 ALTER TABLE `sysuserextends` DISABLE KEYS */;
/*!40000 ALTER TABLE `sysuserextends` ENABLE KEYS */;


-- 导出  表 ticket.ticket_advert 结构
CREATE TABLE IF NOT EXISTS `ticket_advert` (
  `id` varchar(255) NOT NULL,
  `content` longtext,
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
  `url` varchar(255) DEFAULT NULL,
  `advertType_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ticket.ticket_advert 的数据：~1 rows (大约)
DELETE FROM `ticket_advert`;
/*!40000 ALTER TABLE `ticket_advert` DISABLE KEYS */;
INSERT INTO `ticket_advert` (`id`, `content`, `name`, `audit`, `commend`, `createTime`, `deleteFlag`, `hits`, `hot`, `orderValue`, `versionFlag`, `visitUrl`, `url`, `advertType_id`) VALUES
	('d3f5349d-5bac-4e1e-96f6-1561e2e82055', '飞机哇', '飞机', 0, 0, '2015-10-27 17:02:24', 0, 0, 0, 0, 'site', 1445936544562, '#', '9cf82c6d-d655-43b3-8e99-544e3323e55b');
/*!40000 ALTER TABLE `ticket_advert` ENABLE KEYS */;


-- 导出  表 ticket.ticket_adverttype 结构
CREATE TABLE IF NOT EXISTS `ticket_adverttype` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ticket.ticket_adverttype 的数据：~1 rows (大约)
DELETE FROM `ticket_adverttype`;
/*!40000 ALTER TABLE `ticket_adverttype` DISABLE KEYS */;
INSERT INTO `ticket_adverttype` (`id`, `descript`, `name`, `audit`, `commend`, `createTime`, `deleteFlag`, `hits`, `hot`, `orderValue`, `versionFlag`, `visitUrl`) VALUES
	('9cf82c6d-d655-43b3-8e99-544e3323e55b', '飞机广告', '飞机广告', 0, 0, '2015-10-27 17:01:51', 0, 0, 0, 0, 'site', 1445936511481);
/*!40000 ALTER TABLE `ticket_adverttype` ENABLE KEYS */;


-- 导出  表 ticket.ticket_article 结构
CREATE TABLE IF NOT EXISTS `ticket_article` (
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

-- 正在导出表  ticket.ticket_article 的数据：~0 rows (大约)
DELETE FROM `ticket_article`;
/*!40000 ALTER TABLE `ticket_article` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_article` ENABLE KEYS */;


-- 导出  表 ticket.ticket_bjdjcar 结构
CREATE TABLE IF NOT EXISTS `ticket_bjdjcar` (
  `id` varchar(255) NOT NULL,
  `factoryTime` datetime DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `member_id` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
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
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ticket.ticket_bjdjcar 的数据：~0 rows (大约)
DELETE FROM `ticket_bjdjcar`;
/*!40000 ALTER TABLE `ticket_bjdjcar` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_bjdjcar` ENABLE KEYS */;


-- 导出  表 ticket.ticket_bjdjcarorder 结构
CREATE TABLE IF NOT EXISTS `ticket_bjdjcarorder` (
  `id` varchar(255) NOT NULL,
  `car_id` varchar(255) DEFAULT NULL,
  `member_id` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ticket.ticket_bjdjcarorder 的数据：~0 rows (大约)
DELETE FROM `ticket_bjdjcarorder`;
/*!40000 ALTER TABLE `ticket_bjdjcarorder` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_bjdjcarorder` ENABLE KEYS */;


-- 导出  表 ticket.ticket_bjdjcarorderprocess 结构
CREATE TABLE IF NOT EXISTS `ticket_bjdjcarorderprocess` (
  `id` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  `phase` varchar(255) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ticket.ticket_bjdjcarorderprocess 的数据：~0 rows (大约)
DELETE FROM `ticket_bjdjcarorderprocess`;
/*!40000 ALTER TABLE `ticket_bjdjcarorderprocess` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_bjdjcarorderprocess` ENABLE KEYS */;


-- 导出  表 ticket.ticket_bjdjcomment 结构
CREATE TABLE IF NOT EXISTS `ticket_bjdjcomment` (
  `id` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `member_id` varchar(255) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ticket.ticket_bjdjcomment 的数据：~0 rows (大约)
DELETE FROM `ticket_bjdjcomment`;
/*!40000 ALTER TABLE `ticket_bjdjcomment` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_bjdjcomment` ENABLE KEYS */;


-- 导出  表 ticket.ticket_bjdjhall 结构
CREATE TABLE IF NOT EXISTS `ticket_bjdjhall` (
  `id` varchar(255) NOT NULL,
  `capacity` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `latitude` varchar(255) DEFAULT NULL,
  `longitude` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
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

-- 正在导出表  ticket.ticket_bjdjhall 的数据：~0 rows (大约)
DELETE FROM `ticket_bjdjhall`;
/*!40000 ALTER TABLE `ticket_bjdjhall` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_bjdjhall` ENABLE KEYS */;


-- 导出  表 ticket.ticket_bjdjorder 结构
CREATE TABLE IF NOT EXISTS `ticket_bjdjorder` (
  `id` varchar(255) NOT NULL,
  `member_id` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ticket.ticket_bjdjorder 的数据：~0 rows (大约)
DELETE FROM `ticket_bjdjorder`;
/*!40000 ALTER TABLE `ticket_bjdjorder` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_bjdjorder` ENABLE KEYS */;


-- 导出  表 ticket.ticket_bjdjqueue 结构
CREATE TABLE IF NOT EXISTS `ticket_bjdjqueue` (
  `id` varchar(255) NOT NULL,
  `hall_id` varchar(255) DEFAULT NULL,
  `member_id` varchar(255) DEFAULT NULL,
  `sequence` int(11) DEFAULT NULL,
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

-- 正在导出表  ticket.ticket_bjdjqueue 的数据：~0 rows (大约)
DELETE FROM `ticket_bjdjqueue`;
/*!40000 ALTER TABLE `ticket_bjdjqueue` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_bjdjqueue` ENABLE KEYS */;


-- 导出  表 ticket.ticket_bjdjservicecode 结构
CREATE TABLE IF NOT EXISTS `ticket_bjdjservicecode` (
  `id` varchar(255) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `commend` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `orderValue` int(11) DEFAULT NULL,
  `versionFlag` varchar(20) DEFAULT NULL,
  `visitUrl` bigint(20) DEFAULT NULL,
  `status_id` varchar(255) DEFAULT NULL,
  `type_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ticket.ticket_bjdjservicecode 的数据：~0 rows (大约)
DELETE FROM `ticket_bjdjservicecode`;
/*!40000 ALTER TABLE `ticket_bjdjservicecode` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_bjdjservicecode` ENABLE KEYS */;


-- 导出  表 ticket.ticket_bjdjservicecodelog 结构
CREATE TABLE IF NOT EXISTS `ticket_bjdjservicecodelog` (
  `id` varchar(255) NOT NULL,
  `member_id` varchar(255) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
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
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ticket.ticket_bjdjservicecodelog 的数据：~0 rows (大约)
DELETE FROM `ticket_bjdjservicecodelog`;
/*!40000 ALTER TABLE `ticket_bjdjservicecodelog` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_bjdjservicecodelog` ENABLE KEYS */;


-- 导出  表 ticket.ticket_commonannex 结构
CREATE TABLE IF NOT EXISTS `ticket_commonannex` (
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

-- 正在导出表  ticket.ticket_commonannex 的数据：~19 rows (大约)
DELETE FROM `ticket_commonannex`;
/*!40000 ALTER TABLE `ticket_commonannex` DISABLE KEYS */;
INSERT INTO `ticket_commonannex` (`id`, `annexPath`, `annexSize`, `annexType`, `content`, `entityId`, `entityName`, `extensionName`, `originalName`, `audit`, `commend`, `createTime`, `deleteFlag`, `hits`, `hot`, `orderValue`, `versionFlag`, `visitUrl`, `title`) VALUES
	('01853e70-9f45-41b9-b8c4-35fbe6b5cf77', '/upload/site/image/201510/20151027025646.jpg', NULL, 1, NULL, 'd0d339f9-bbbe-485f-92d4-d353684b1dff', 'News', 'jpg', 'ap02.jpg', 0, 0, '2015-10-27 14:57:03', 0, 0, 0, 0, 'site', 1445929023281, 'ap02.jpg'),
	('2265fadc-672c-4886-b834-764d6f5ec239', '/upload/site/image/201510/20151028010631.jpg', NULL, 1, NULL, 'bd6c9a35-b0fc-4d7a-94bd-2934ab9612cb', 'News', 'jpg', '1DX_9531.jpg', 0, 0, '2015-10-28 13:06:38', 0, 0, 0, 0, 'site', 1446008798270, '1DX_9531.jpg'),
	('27a758e2-11b4-4a50-a331-7e20b57e12ae', '/upload/site/image/201510/20151014060051.jpg', NULL, 1, NULL, '34651617-4a6d-468f-82a5-4a8ae98b8acc', 'News', 'jpg', '20150518053524.jpg', 0, 0, '2015-10-14 18:01:07', 0, 0, 0, 0, 'site', 1444816867694, '20150518053524.jpg'),
	('328369be-bdeb-4694-8c79-c198c546f47b', '/upload/site/image/201510/20151027050216.jpg', NULL, 1, NULL, 'd3f5349d-5bac-4e1e-96f6-1561e2e82055', 'Advert', 'jpg', 'ap01.jpg', 0, 0, '2015-10-27 17:02:24', 0, 0, 0, 0, 'site', 1445936544812, 'ap01.jpg'),
	('33b86805-8c4d-4971-b38d-be6ee3ea3d91', '/upload/site/image/201510/20151014060103.jpg', NULL, 2, NULL, '34651617-4a6d-468f-82a5-4a8ae98b8acc', 'News', 'jpg', '20150526020007.jpg', 0, 0, '2015-10-14 18:01:07', 0, 0, 0, 1, 'site', 1444816867788, '20150526020007.jpg'),
	('3ef40744-de9b-4e45-8e39-25d168d48545', '/upload/site/image/201510/20151027114057.jpg', NULL, 1, NULL, '8cde7677-905f-4f96-b9b2-4b93bab947f7', 'Advert', 'jpg', 'ap01.jpg', 0, 0, '2015-10-27 11:41:01', 0, 0, 0, 0, 'site', 1445917261692, 'ap01.jpg'),
	('56f069a8-dd0d-4fdf-b415-c5a77d616cc2', '/upload/site/image/201510/20151028115217.jpg', NULL, 1, NULL, '7e1c3858-ec0e-4f74-8d50-ef410b18ad6b', 'News', 'jpg', 'ap03.jpg', 0, 0, '2015-10-28 11:52:23', 0, 0, 0, 0, 'site', 1446004343345, 'ap03.jpg'),
	('5be3f151-9a63-4b43-8b74-365ccb7d24fb', '/upload/site/image/201510/20151026055655.jpg', NULL, 1, NULL, '944af43b-3194-450f-86f7-44ffa6b2c9e5', 'News', 'jpg', 'ap03.jpg', 0, 0, '2015-10-26 17:57:01', 0, 0, 0, 0, 'site', 1445853421639, 'ap03.jpg'),
	('63c2fbe8-994d-4019-9fa1-ea2501914ad1', '/upload/site/image/201510/20151014060101.jpg', NULL, 2, NULL, '34651617-4a6d-468f-82a5-4a8ae98b8acc', 'News', 'jpg', '20150518044051.jpg', 0, 0, '2015-10-14 18:01:07', 0, 0, 0, 2, 'site', 1444816867725, '20150518044051.jpg'),
	('73d32aed-5084-440f-af8f-1b8efd46a153', '/upload/site/image/201510/20151026055734.jpg', NULL, 1, NULL, '7d4bc08e-adb0-4693-8464-12813d210b00', 'News', 'jpg', '1DX_9717.jpg', 0, 0, '2015-10-26 17:57:39', 0, 0, 0, 0, 'site', 1445853459540, '1DX_9717.jpg'),
	('8003239a-0468-4955-ac7d-5e513c8a43e6', '/upload/site/image/201510/20151027020050.jpg', NULL, 1, NULL, '86fc0f3f-0ea2-4eda-ab65-98cfc37a5635', 'Advert', 'jpg', 'ap02.jpg', 0, 0, '2015-10-27 14:01:08', 0, 0, 0, 0, 'site', 1445925668875, 'ap02.jpg'),
	('809646b5-c269-475f-bb83-142be6f99c40', '/upload/site/image/201510/20151026062425.jpg', NULL, 1, NULL, 'be993f2a-29fe-448b-98e5-65a2e5a620c1', 'News', 'jpg', 'ap01.jpg', 0, 0, '2015-10-26 18:24:31', 0, 0, 0, 0, 'site', 1445855071364, 'ap01.jpg'),
	('9e30cb8b-f13e-44bd-816c-d9237835854e', '/upload/site/image/201510/20151027114120.jpg', NULL, 1, NULL, 'f8bc6a4b-12c1-4a2c-bc1b-fa052025d00a', 'Advert', 'jpg', 'ap03.jpg', 0, 0, '2015-10-27 11:41:25', 0, 0, 0, 0, 'site', 1445917285928, 'ap03.jpg'),
	('bdb6e297-8f1e-4e18-8e82-fa0d08bd7f10', '/upload/site/image/201510/20151028115002.jpg', NULL, 1, NULL, 'c6a41ece-ea84-489d-91b6-9b05237a67c9', 'News', 'jpg', 'ap01.jpg', 0, 0, '2015-10-28 11:50:12', 0, 0, 0, 0, 'site', 1446004212878, 'ap01.jpg'),
	('ca3b28f8-0b52-493f-ae9e-987aacb3599e', '/upload/site/image/201510/20151028115558.jpg', NULL, 1, NULL, '9c833438-d44d-442d-bada-287c93c976ad', 'News', 'jpg', '夜景.jpg', 0, 0, '2015-10-28 11:56:12', 0, 0, 0, 0, 'site', 1446004572450, '夜景.jpg'),
	('ce1b43f4-dfce-455b-8455-75d34599f7a9', '/upload/site/image/201510/20151014060144.png', NULL, 1, NULL, '6fb99d5d-441f-40d4-868e-f2369f383164', 'News', 'png', '20150518030853.png', 0, 0, '2015-10-14 18:01:48', 0, 0, 0, 0, 'site', 1444816908453, '20150518030853.png'),
	('d1fb4ea8-f9c8-44a4-ad7b-6b6e51c6d8d3', '/upload/site/image/201510/20151014055715.jpg', NULL, 2, NULL, '6fb99d5d-441f-40d4-868e-f2369f383164', 'News', 'jpg', '20150519012553.jpg', 0, 0, '2015-10-14 17:57:34', 0, 0, 0, 1, 'site', 1444816654937, '20150519012553.jpg'),
	('e7f6a5eb-3d70-4479-8895-f003b6b6b9fa', '/upload/site/image/201510/20151014055713.jpg', NULL, 2, NULL, '6fb99d5d-441f-40d4-868e-f2369f383164', 'News', 'jpg', '20150519012529.jpg', 0, 0, '2015-10-14 17:57:34', 0, 0, 0, 2, 'site', 1444816654827, '20150519012529.jpg'),
	('ead2cbbf-d2a9-450f-b50a-a5e5741822dd', '/upload/site/image/201510/20151027025049.jpg', NULL, 1, NULL, 'e476f51a-875f-4b45-b432-78b9fbd51cc9', 'News', 'jpg', '1DX_6460.jpg', 0, 0, '2015-10-27 14:51:00', 0, 0, 0, 0, 'site', 1445928660545, '1DX_6460.jpg');
/*!40000 ALTER TABLE `ticket_commonannex` ENABLE KEYS */;


-- 导出  表 ticket.ticket_databasebackupslog 结构
CREATE TABLE IF NOT EXISTS `ticket_databasebackupslog` (
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

-- 正在导出表  ticket.ticket_databasebackupslog 的数据：~0 rows (大约)
DELETE FROM `ticket_databasebackupslog`;
/*!40000 ALTER TABLE `ticket_databasebackupslog` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_databasebackupslog` ENABLE KEYS */;


-- 导出  表 ticket.ticket_databaserestorelog 结构
CREATE TABLE IF NOT EXISTS `ticket_databaserestorelog` (
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

-- 正在导出表  ticket.ticket_databaserestorelog 的数据：~0 rows (大约)
DELETE FROM `ticket_databaserestorelog`;
/*!40000 ALTER TABLE `ticket_databaserestorelog` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_databaserestorelog` ENABLE KEYS */;


-- 导出  表 ticket.ticket_electrombile 结构
CREATE TABLE IF NOT EXISTS `ticket_electrombile` (
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

-- 正在导出表  ticket.ticket_electrombile 的数据：~0 rows (大约)
DELETE FROM `ticket_electrombile`;
/*!40000 ALTER TABLE `ticket_electrombile` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_electrombile` ENABLE KEYS */;


-- 导出  表 ticket.ticket_infoposition 结构
CREATE TABLE IF NOT EXISTS `ticket_infoposition` (
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
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ticket.ticket_infoposition 的数据：~8 rows (大约)
DELETE FROM `ticket_infoposition`;
/*!40000 ALTER TABLE `ticket_infoposition` DISABLE KEYS */;
INSERT INTO `ticket_infoposition` (`id`, `latitude`, `longitude`, `news_id`, `audit`, `commend`, `createTime`, `deleteFlag`, `hits`, `hot`, `orderValue`, `versionFlag`, `visitUrl`, `name`) VALUES
	('192999a3-aa07-4f65-88be-eaa0dc1c535e', 24.918465, 102.696513, '56a66bcb-14c8-45ac-96f8-df4e35c46dd8', 0, 0, '2015-10-25 20:49:49', 0, 0, 0, 0, 'site', 1445777389520, '问询台'),
	('429cbf9b-e282-4fd5-a875-360f66db00c5', 24.837693, 102.770102, 'da7b9294-45ac-4835-ba7b-f0d7b0122821', 0, 0, '2015-10-25 20:56:56', 0, 0, 0, 0, 'site', 1445777816536, '登机口'),
	('5fd3eaa5-ad0e-40c0-8573-569466e8ee49', 24.869169, 102.936253, '893403cb-5c84-4581-a478-34399273a368', 0, 0, '2015-10-26 17:35:48', 0, 0, 0, 0, 'site', 1445852148764, '乘机柜台'),
	('86b33493-39b5-4ddc-a841-7d48ba075331', 24.991847, 102.491843, '35420bd0-87ac-43c7-8fcc-9bedca6bfd15', 0, 0, '2015-10-24 11:49:49', 0, 0, 0, 0, 'site', 1445658589405, '娱乐区'),
	('916ba088-f8d4-44ec-8be6-2ae4ad35ae47', 24.792564, 103.081706, '3648c43d-edf9-42a9-8218-daf7dd81ebbd', 0, 0, '2015-10-26 15:13:44', 0, 0, 0, 0, 'site', 1445843624429, '更衣室'),
	('b3021122-b607-4eaf-8b5c-d6943c61f9b3', 24.808309, 102.944877, '2f5f355d-5164-4370-85fe-bbf45dd9c338', 0, 0, '2015-10-25 20:38:47', 0, 0, 0, 0, 'site', 1445776727269, '登机口'),
	('cf9e98b0-1846-4fc1-b372-3a5e6ec01210', 24.900637, 102.745956, '1545d3fa-6ac1-439d-9f22-06acf1554bde', 0, 0, '2015-10-26 15:21:32', 0, 0, 0, 0, 'site', 1445844092491, '娱乐设施'),
	('f908782a-4635-4328-83ac-130fca895d93', 24.808309, 102.927629, '944af43b-3194-450f-86f7-44ffa6b2c9e5', 0, 0, '2015-10-25 22:37:42', 0, 0, 0, 0, 'site', 1445783862080, '机场出口');
/*!40000 ALTER TABLE `ticket_infoposition` ENABLE KEYS */;


-- 导出  表 ticket.ticket_member 结构
CREATE TABLE IF NOT EXISTS `ticket_member` (
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

-- 正在导出表  ticket.ticket_member 的数据：~0 rows (大约)
DELETE FROM `ticket_member`;
/*!40000 ALTER TABLE `ticket_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_member` ENABLE KEYS */;


-- 导出  表 ticket.ticket_memberfavorite 结构
CREATE TABLE IF NOT EXISTS `ticket_memberfavorite` (
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

-- 正在导出表  ticket.ticket_memberfavorite 的数据：~0 rows (大约)
DELETE FROM `ticket_memberfavorite`;
/*!40000 ALTER TABLE `ticket_memberfavorite` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_memberfavorite` ENABLE KEYS */;


-- 导出  表 ticket.ticket_memberlevel 结构
CREATE TABLE IF NOT EXISTS `ticket_memberlevel` (
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

-- 正在导出表  ticket.ticket_memberlevel 的数据：~1 rows (大约)
DELETE FROM `ticket_memberlevel`;
/*!40000 ALTER TABLE `ticket_memberlevel` DISABLE KEYS */;
INSERT INTO `ticket_memberlevel` (`id`, `descript`, `name`, `needScore`, `audit`, `commend`, `createTime`, `deleteFlag`, `hits`, `hot`, `orderValue`, `versionFlag`, `visitUrl`) VALUES
	('ce731a0a-840f-467c-9957-2c29ffedee79', '金牌会员', '金牌会员', '1000', 0, 0, '2015-10-19 17:03:41', 0, 0, 0, 0, 'site', 1445245421068);
/*!40000 ALTER TABLE `ticket_memberlevel` ENABLE KEYS */;


-- 导出  表 ticket.ticket_news 结构
CREATE TABLE IF NOT EXISTS `ticket_news` (
  `id` varchar(255) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `content` text,
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
  `introduce` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ticket.ticket_news 的数据：~29 rows (大约)
DELETE FROM `ticket_news`;
/*!40000 ALTER TABLE `ticket_news` DISABLE KEYS */;
INSERT INTO `ticket_news` (`id`, `author`, `content`, `newsClass_id`, `audit`, `commend`, `createTime`, `deleteFlag`, `hits`, `hot`, `orderValue`, `versionFlag`, `visitUrl`, `subTitle`, `systemUser_id`, `title`, `viewPageRedirectTemplate_id`, `introduce`) VALUES
	('00590e4d-2717-4d2e-91b8-50c788133305', 'admin', '行李打包行李打包行李打包行李打包行李打包行李打包行李打包行李打包行李打包行李打包行李打包行李打包行李打包行李打包行李打包行李打包行李打包行李打包行李打包行李打包行李打包行李打包行李打包行李打包行李打包行李打包行李打包行李打包行李打包行李打包行李打包行李打包行李打包行李打包行李打包行李打包行李打包行李打包', '9ef29329-cfca-4614-89d3-31bd21c383fe', 1, 0, '2015-10-24 17:41:44', 0, 31, 0, 0, 'site', 1445679704990, '行李打包', '8dea01b1-9bfd-4635-be39-c1fa6438b85b', '行李打包服务', '7778cd5d-53e9-40bf-9558-e40067096f8f', ''),
	('1205d51d-7754-440a-9a1c-d19b42ac77b4', 'admin', '头等舱服务头等舱服务头等舱服务头等舱服务头等舱服务头等舱服务头等舱服务头等舱服务头等舱服务头等舱服务头等舱服务头等舱服务头等舱服务头等舱服务头等舱服务头等舱服务头等舱服务头等舱服务头等舱服务头等舱服务头等舱服务头等舱服务', '90b46c99-5c32-4ce4-b59f-7b7300a0cba7', 1, 0, '2015-10-24 17:48:57', 0, 8, 0, 0, 'site', 1445680137675, '头等舱服务', '8dea01b1-9bfd-4635-be39-c1fa6438b85b', '头等舱服务', 'e3099bcc-9b93-45cf-a5cd-0392d5838c71', NULL),
	('1545d3fa-6ac1-439d-9f22-06acf1554bde', 'admin', '机场自助取款机机场自助取款机机场自助取款机机场自助取款机机场自助取款机机场自助取款机机场自助取款机机场自助取款机机场自助取款机机场自助取款机机场自助取款机机场自助取款机机场自助取款机机场自助取款机机场自助取款机机场自助取款机机场自助取款机机场自助取款机机场自助取款机<br />', '349d6590-1917-44a3-a502-b4696d9dd32f', 1, 0, '2015-10-26 15:07:25', 0, 11, 0, 0, 'site', 1445843245689, '', '8dea01b1-9bfd-4635-be39-c1fa6438b85b', '机场ATM', '4c6a7265-cc45-4156-83c9-0f11efd33d22', NULL),
	('26d139d7-cf22-44af-af3a-eb31d1f1bebe', 'admin', '百事特贵宾厅百事特贵宾厅百事特贵宾厅百事特贵宾厅百事特贵宾厅百事特贵宾厅百事特贵宾厅百事特贵宾厅百事特贵宾厅百事特贵宾厅百事特贵宾厅百事特贵宾厅百事特贵宾厅百事特贵宾厅百事特贵宾厅百事特贵宾厅百事特贵宾厅百事特贵宾厅百事特贵宾厅百事特贵宾厅百事特贵宾厅百事特贵宾厅百事特贵宾厅', '198934b4-60d6-4b90-b121-58ccbbe37e22', 1, 0, '2015-10-24 17:50:53', 0, 12, 0, 0, 'site', 1445680253766, '百事特贵宾厅', '8dea01b1-9bfd-4635-be39-c1fa6438b85b', '百事特贵宾厅', 'e3099bcc-9b93-45cf-a5cd-0392d5838c71', NULL),
	('2741e715-27a8-4184-944b-eafa94ac64fb', 'admin', '事件发生于下午6点45分，这架<a target="_blank" href="http://data.carnoc.com/aircraft/type/list/boeing-737-800.html">波音737-800</a>飞机正从亚庇飞往吉隆坡。\r\n<p>\r\n	雪邦警方称，当时一名乘客正在座位上看机上娱乐视频，大腿上放着一个袋子，袋子里装着电子烟，电子烟的开关不知道什么时候被触发，接着起火。\r\n</p>\r\n<p>\r\n	“小火导致乘客的大腿和左臂轻度烧伤。”另一名刚好是医生的乘客立刻对他进行了处置。\r\n</p>\r\n<br />', '59d927da-180e-4c8b-9b6e-e3509df93d02', 1, 0, '2015-10-26 14:54:48', 0, 7, 0, 0, 'site', 1445842488836, '副标题', '8dea01b1-9bfd-4635-be39-c1fa6438b85b', '电子烟电池机上起火 烧伤乘客大腿', '504e3a82-cf1a-4d8f-9f44-fbf22bd79155', NULL),
	('3648c43d-edf9-42a9-8218-daf7dd81ebbd', 'admin', '更衣室更衣室更衣室', '349d6590-1917-44a3-a502-b4696d9dd32f', 1, 0, '2015-10-26 15:13:44', 0, 8, 0, 0, 'site', 1445843624429, '', '8dea01b1-9bfd-4635-be39-c1fa6438b85b', '更衣室', '4c6a7265-cc45-4156-83c9-0f11efd33d22', NULL),
	('3a877fdd-0120-4f85-979f-e34f0f82bafc', 'admin', '免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明免责声明', '88cff97c-116f-445e-adfd-48442438d5fc', 1, 0, '2015-10-25 20:22:38', 0, 14, 0, 0, 'site', 1445775758716, '免责声明', '8dea01b1-9bfd-4635-be39-c1fa6438b85b', '免责声明', 'bc82aa43-20bf-4041-ba4f-8819c69d40d4', NULL),
	('4896e6c5-50f3-41d9-8734-25f4f3550afb', 'admin', '<span class="con">乘坐飞机注意事项\r\n 飞机违禁物：\r\n 以下为违禁物品的例子∶\r\n1枪械，包括猎枪、气枪、牲口麻醉屠宰机、推膛式枪、信号枪、起步枪、弹药、复制或\r\n仿制枪械、弩。\r\n2爆炸品，包括军用、商用或自制爆炸品、爆炸装置、雷管、催泪子弹、榴弹、地雷及其\r\n它爆炸军火、复制或仿制爆炸品或装置。\r\n3 原装或经改装可引致他人受伤的尖锐或有刀刃对象、弹簧折</span>', '5246a203-50f0-45f4-8557-e3a41dc77885', 1, 0, '2015-10-24 14:41:54', 0, 20, 0, 0, 'site', 1445668914420, '国际乘机时间须知', '8dea01b1-9bfd-4635-be39-c1fa6438b85b', '国际乘机时间须知', 'a577650a-85ca-4210-8022-6390cb8b3d9f', NULL),
	('56a66bcb-14c8-45ac-96f8-df4e35c46dd8', 'admin', '国内安全检查须知国内安全检查须知国内安全检查须知国内安全检查须知国内安全检查须知国内安全检查须知国内安全检查须知国内安全检查须知国内安全检查须知国内安全检查须知', '22945254-411b-4223-beed-c52716c37112', 1, 0, '2015-10-25 20:49:49', 0, 18, 0, 0, 'site', 1445777389520, '国内安全检查须知', '8dea01b1-9bfd-4635-be39-c1fa6438b85b', '国内安全检查须知', 'a577650a-85ca-4210-8022-6390cb8b3d9f', NULL),
	('6a956a0c-14f2-4717-b4c7-45befd261e92', 'admin', '爱心服务爱心服务爱心服务爱心服务爱心服务爱心服务爱心服务爱心服务爱心服务爱心服务爱心服务爱心服务爱心服务爱心服务爱心服务爱心服务爱心服务爱心服务爱心服务爱心服务爱心服务爱心服务爱心服务爱心服务爱心服务爱心服务爱心服务爱心服务爱心服务爱心服务', '3f48ee06-ddf3-493d-b0b1-ebf55de9669b', 1, 0, '2015-10-24 16:53:59', 0, 8, 0, 0, 'site', 1445676839067, '爱心服务', '8dea01b1-9bfd-4635-be39-c1fa6438b85b', '爱心服务', 'f2e138f9-f567-4557-8397-9cc7c916c73c', NULL),
	('71fa200f-147d-4df8-ad3f-fe37214468c1', 'admin', '　民航资源网2015年10月23日消息：香港时间23日21:56，香港机场官方网站更新特别通告：所有往返机场的陆路运输回复正常。\r\n<p>\r\n	今（23）日晚间近8时，青马管理公司通知，香港青马大桥上下层行车线，港铁机场快线、港铁东涌线全线封闭。现场交通瘫痪。香港机场官方网站此前发布特别通告：由于青马大桥发生技术故障，往返机场的陆路交通服务暂停。\r\n</p>\r\n<br />', 'c82f438e-d0f4-47e0-a9b2-3df240639b79', 1, 0, '2015-10-24 13:42:59', 0, 13, 0, 0, 'site', 1445665379236, '往返香港机场陆路运输回复正常', '8dea01b1-9bfd-4635-be39-c1fa6438b85b', '往返香港机场陆路运输回复正常', '504e3a82-cf1a-4d8f-9f44-fbf22bd79155', NULL),
	('7d4bc08e-adb0-4693-8464-12813d210b00', 'admin', '电子烟电池机上起火电子烟电池机上起火电子烟电池机上起火电子烟电池机上起火电子烟电池机上起火电子烟电池机上起火电子烟电池机上起火', 'a5e371be-bdde-488f-b639-ce86dfbe5c9b', 1, 0, '2015-10-26 14:57:53', 0, 10, 0, 0, 'site', 1445842673788, '电子烟电池机上起火', '8dea01b1-9bfd-4635-be39-c1fa6438b85b', '电子烟电池机上起火', '691e5271-26f7-4109-914b-32a5ebd22815', NULL),
	('7e1c3858-ec0e-4f74-8d50-ef410b18ad6b', 'admin', '该航线由首次在石家庄机场运营的西藏航空空客A319执飞，每周一、三、五、日执飞4班，航班号为TV9838/TV9837。航班时刻为17时50分从\r\n石家庄起飞，19时50分飞抵兰州； \r\n20时40分从兰州起飞，22时45分飞抵拉萨。从拉萨起飞的时刻为12时25分从拉萨起飞，14时15分飞抵兰州；15时05分从兰州起飞，17时飞抵\r\n石家庄。', '508e3a06-71ad-44a0-80c1-6ab19eaad9c5', 1, 0, '2015-10-25 21:31:15', 0, 35, 0, 0, 'site', 1445779875973, '石家庄机场首次开通至拉萨航线', '8dea01b1-9bfd-4635-be39-c1fa6438b85b', '石家庄机场首次开通至拉萨航线', '72d1e1e8-b7d3-40b8-830e-2b809d903da3', ''),
	('893403cb-5c84-4581-a478-34399273a368', 'admin', '国际安检查须知国际安检查须知国际安检查须知国际安检查须知国际安检查须知国际安检查须知国际安检查须知国际安检查须知国际安检查须知国际安检查须知国际安检查须知国际安检查须知国际安检查须知国际安检查须知国际安检查须知国际安检查须知国际安检查须知国际安检查须知国际安检查须知国际安检查须知国际安检查须知国际安检查须知国际安检查须知国际安检查须知国际安检查须知国际安检查须知国际安检查须知国际安检查须知国际安检查须知', '5246a203-50f0-45f4-8557-e3a41dc77885', 1, 0, '2015-10-24 14:46:55', 0, 11, 0, 0, 'site', 1445669215310, '国际安检', '8dea01b1-9bfd-4635-be39-c1fa6438b85b', '国际安全检查须知', 'a577650a-85ca-4210-8022-6390cb8b3d9f', NULL),
	('8aede3b9-b0db-45de-94a4-54c1531b6d59', 'admin', '关注我们', '7e1fc23e-7fc3-4e0e-b67e-34c526167652', 1, 0, '2015-10-25 20:31:37', 0, 31, 0, 0, 'site', 1445776297249, 'payAttentionToUs', '8dea01b1-9bfd-4635-be39-c1fa6438b85b', '关注我们', '4fc9eace-175c-4768-9569-b484f5c40f85', NULL),
	('8cc39739-0ed3-4127-9344-4c4aacc748ba', 'admin', '啊顺德区威箝位器阿斯顿啊地区为文去问啊上的箝位器文大色3去诶额箝位器文为请问额企鹅请问大师发烧阿荣旗为请问日期人权为荣权威人权为荣韦荣强五日前我人气旺人情味微软轻武器人情味人气王 认为箝位器文去<br />', '349d6590-1917-44a3-a502-b4696d9dd32f', 0, 0, '2015-10-26 15:29:38', 0, 0, 0, 0, 'site', 1445844578815, '啊山东的撒旦', '8dea01b1-9bfd-4635-be39-c1fa6438b85b', '啊是大师的', '4c6a7265-cc45-4156-83c9-0f11efd33d22', NULL),
	('944af43b-3194-450f-86f7-44ffa6b2c9e5', 'admin', '石家庄机场首次开通至拉萨航线石家庄机场首次开通至拉萨航线石家庄机场首次开通至拉萨航线石家庄机场首次开通至拉萨航线石家庄机场首次开通至拉萨航线石家庄机场首次开通至拉萨航线石家庄机场首次开通至拉萨航线石家庄机场首次开通至拉萨航线石家庄机场首次开通至拉萨航线石家庄机场首次开通至拉萨航线石家庄机场首次开通至拉萨航线石家庄机场首次开通至拉萨航线石家庄机场首次开通至拉萨航线石家庄机场首次开通至拉萨航线', 'a5e371be-bdde-488f-b639-ce86dfbe5c9b', 1, 0, '2015-10-25 22:21:25', 0, 7, 0, 0, 'site', 1445782885186, '石家庄机场首次开通至拉萨航线', '8dea01b1-9bfd-4635-be39-c1fa6438b85b', '石家庄机场首次', '691e5271-26f7-4109-914b-32a5ebd22815', NULL),
	('9c833438-d44d-442d-bada-287c93c976ad', 'admin', '第三', '508e3a06-71ad-44a0-80c1-6ab19eaad9c5', 1, 0, '2015-10-28 11:56:12', 0, 5, 0, 0, 'site', 1446004572370, '', '8dea01b1-9bfd-4635-be39-c1fa6438b85b', '第三章', '72d1e1e8-b7d3-40b8-830e-2b809d903da3', ''),
	('9ea54be5-673b-4483-9a89-09bee4430d0c', 'admin', '便民服务便民服务便民服务便民服务便民服务便民服务便民服务便民服务便民服务便民服务便民服务便民服务便民服务便民服务便民服务便民服务便民服务便民服务便民服务便民服务便民服务便民服务便民服务便民服务便民服务便民服务便民服务便民服务', 'cac7d243-96f6-4674-9845-20bd4b5c6baf', 1, 0, '2015-10-24 17:00:55', 0, 34, 0, 0, 'site', 1445677255892, '便民服务', '8dea01b1-9bfd-4635-be39-c1fa6438b85b', '便民服务', '91bb70ad-f852-402d-b1db-de74c4c9329d', NULL),
	('a1065a56-68f0-4169-84bb-6ffeccca0a1f', 'admin', 'newsNoticenewsNoticenewsNoticenewsNoticenewsNoticenewsNoticenewsNoticenewsNoticenewsNoticenewsNoticenewsNoticenewsNoticenewsNoticenewsNoticenewsNoticenewsNoticenewsNoticenewsNoticenewsNoticenewsNoticenewsNoticenewsNotice', '59d927da-180e-4c8b-9b6e-e3509df93d02', 1, 0, '2015-10-24 16:27:53', 0, 14, 0, 0, 'site', 1445675273733, '机场要闻', '8dea01b1-9bfd-4635-be39-c1fa6438b85b', '机场要闻', '504e3a82-cf1a-4d8f-9f44-fbf22bd79155', NULL),
	('afe40655-45af-402f-b74b-d1fb1b799bfa', 'admin', '国内行李运输须知国内行李运输须知国内行李运输须知国内行李运输须知国内行李运输须知国内行李运输须知国内行李运输须知国内行李运输须知国内行李运输须知国内行李运输须知国内行李运输须知国内行李运输须知国内行李运输须知国内行李运输须知国内行李运输须知', '22945254-411b-4223-beed-c52716c37112', 0, 0, '2015-10-26 15:32:37', 0, 0, 0, 0, 'site', 1445844757259, '', '8dea01b1-9bfd-4635-be39-c1fa6438b85b', '国内行李运输须知', '901bb441-33a3-48cf-8cd4-06b1af1246e6', NULL),
	('bd6c9a35-b0fc-4d7a-94bd-2934ab9612cb', 'admin', '　民航资源网2015年10月23日消息：香港时间23日21:56，香港机场官方网站更新特别通告：所有往返机场的陆路运输回复正常。\r\n<p>\r\n	今（23）日晚间近8时，青马管理公司通知，香港青马大桥上下层行车线，港铁机场快线、港铁东涌线全线封闭。现场交通瘫痪。香港机场官方网站此前发布特别通告：由于青马大桥发生技术故障，往返机场的陆路交通服务暂停。\r\n</p>\r\n<br />', 'a5e371be-bdde-488f-b639-ce86dfbe5c9b', 1, 0, '2015-10-24 14:21:11', 0, 7, 0, 0, 'site', 1445667671006, '往返香港机场陆路运输回复正常', '8dea01b1-9bfd-4635-be39-c1fa6438b85b', '往返香港机场陆路运输回复', '', ''),
	('be993f2a-29fe-448b-98e5-65a2e5a620c1', 'admin', '啊是大谁 啊收发室地方撒地方啊水电费阿三发生发生大反攻时的身份嘎斯打法是否<br />', '4ca6958f-879d-4949-8d76-5681e4c4e074', 1, 0, '2015-10-25 19:59:07', 0, 8, 0, 0, 'site', 1445774347303, '机场文化', '8dea01b1-9bfd-4635-be39-c1fa6438b85b', '机场文化', '', '阿斯顿  阿斯顿 <br />'),
	('c6a41ece-ea84-489d-91b6-9b05237a67c9', 'admin', '测试公告测试公告测试公告测试公告测试公告测试公告测试公告测试公告测试公告测试公告测试公告测试公告测试公告测试公告测试公告测试公告测试公告测试公告测试公告测试公告测试公告测试公告测试公告测试公告', '508e3a06-71ad-44a0-80c1-6ab19eaad9c5', 1, 0, '2015-10-27 16:26:26', 0, 5, 0, 0, 'site', 1445934386141, '测试公告', '8dea01b1-9bfd-4635-be39-c1fa6438b85b', '测试公告', '72d1e1e8-b7d3-40b8-830e-2b809d903da3', '测试公告测试公告'),
	('d0d339f9-bbbe-485f-92d4-d353684b1dff', 'admin', '服务介绍服务介绍服务介绍服务介绍服务介绍服务介绍服务介绍服务介绍服务介绍服务介绍服务介绍服务介绍服务介绍服务介绍服务介绍服务介绍服务介绍服务介绍服务介绍服务介绍服务介绍服务介绍服务介绍服务介绍', 'cfb3ea70-6e75-4931-8f5a-f4ef042e559e', 1, 0, '2015-10-27 14:57:03', 0, 4, 0, 0, 'site', 1445929023201, '服务介绍', '8dea01b1-9bfd-4635-be39-c1fa6438b85b', '服务介绍', '399aaa99-ca86-4507-a121-5f02a77bc6d3', '服务介绍服务介绍服务介绍'),
	('da7b9294-45ac-4835-ba7b-f0d7b0122821', 'admin', '国内乘机时间须知国内乘机时间须知国内乘机时间须知国内乘机时间须知国内乘机时间须知国内乘机时间须知国内乘机时间须知国内乘机时间须知国内乘机时间须知国内乘机时间须知国内乘机时间须知国内乘机时间须知国内乘机时间须知国内乘机时间须知', '22945254-411b-4223-beed-c52716c37112', 1, 0, '2015-10-25 20:56:56', 0, 24, 0, 0, 'site', 1445777816536, '国内乘机时间须知', '8dea01b1-9bfd-4635-be39-c1fa6438b85b', '国内乘机时间须知', 'a577650a-85ca-4210-8022-6390cb8b3d9f', NULL),
	('e476f51a-875f-4b45-b432-78b9fbd51cc9', 'admin', '<p>\r\n	使用条款1\r\n</p>\r\n<p>\r\n	使用条款2\r\n</p>\r\n<p>\r\n	使用条款3\r\n</p>\r\n<p>\r\n	使用条款4\r\n</p>', 'cfb3ea70-6e75-4931-8f5a-f4ef042e559e', 1, 0, '2015-10-27 14:45:10', 0, 5, 0, 0, 'site', 1445928310253, '使用条款', '8dea01b1-9bfd-4635-be39-c1fa6438b85b', '使用条款', '399aaa99-ca86-4507-a121-5f02a77bc6d3', '摘要<br />'),
	('e4a59923-fbb5-4f51-a554-11ee55d37f17', 'admin', '人的一生是要经历许多阶段的，比如说纯真无邪的少年时代，激情如火的<a href="http://www.duwenzhang.com/huati/qingchun/index1.html">青春岁月</a>，厚重沉稳的中年时期，从容淡定的<a href="http://www.duwenzhang.com/wenzhang/renshengzheli/">人生暮年</a>。每个时候都有独特的风景，每段岁月都会给人不同的感受。可进入中年的她，突然间<a href="http://www.duwenzhang.com/huati/ganjue/index1.html">感觉</a>自己，就一下从躁动中宁静下来了，不经意间就有了种坐看云起云舒，我自心境如水的超然。\r\n<p>\r\n	她感到在无意中，一切都漫漫地淡下来了，常常会挂着<a href="http://www.duwenzhang.com/huati/weixiao/index1.html">淡淡的微笑</a>，给人一种和谐<a href="http://www.duwenzhang.com/huati/wenxin/index1.html">温馨之感</a>；常常看淡<a href="http://www.duwenzhang.com/huati/mingli/index1.html">名利</a>和物质，却看重人与人之间的<a href="http://www.duwenzhang.com/huati/ganqing/index1.html">感情</a>，常常不会<a href="http://www.duwenzhang.com/huati/chongdong/index1.html">冲动</a>行事，也不会轻易<a href="http://www.duwenzhang.com/huati/houhui/index1.html">后悔</a>，她会为自己的决定负责。可当她一旦爱上一个人，一定会坚守自己的那份爱，<a href="http://www.duwenzhang.com/wenzhang/aiqingwenzhang/">爱情</a>的保质期是“永远”。\r\n</p>\r\n<p>\r\n	她还会在秋阳明丽的早晨或午后为自己沏一壶香茗，手捧一本书细细品位，慢慢欣赏。她懂得什么是智性美，她更愿意在闲暇的时候去学习书法音乐美术，或者去充电接受最新的科技知识，来提高自己的<a href="http://www.duwenzhang.com/wenzhang/renshengzheli/20080818/15697.html">修养</a>和品位，她不会把<a href="http://www.duwenzhang.com/huati/shijian/index1.html">时间</a>浪费在世俗的纷争和无聊的麻将中，更不会和别人去攀比高档名牌的服饰和<a href="http://www.duwenzhang.com/huati/xurong/index1.html">虚荣</a>的炫耀，她知道<a href="http://www.duwenzhang.com/huati/meili/index1.html">真正的美丽</a>一定是由内而外散发出来的。\r\n</p>\r\n<p>\r\n	可是她也记得不久前还在为工作上的事<a href="http://www.duwenzhang.com/huati/fannao/index1.html">烦恼</a>不已，什么上司不赏识呀，工作业绩不突出啦，还有同事之间不服气了，等等，等等，整个身心陷进了争强好胜的泥沼里，苦苦挣扎，不能释怀，可是到了中年一切就都云开日出了，不是不<a href="http://www.duwenzhang.com/huati/nuli/index1.html">努力</a>工作，只是觉得自己尽力就问心无愧了，至于结果就不会去过多考虑了，这样反而同事之间的关系和谐了，人的精神就愉快了，心胸也宽广了。\r\n</p>\r\n<p>\r\n	她也有曾经陷入爱恋中不能自拔的时候。那时，在热恋中<a href="http://www.duwenzhang.com/huati/tongku/index1.html">痛苦</a>，因为怕<a href="http://www.duwenzhang.com/huati/shiqu/index1.html">失去</a>，所以猜忌怀疑，无事生非，互相折磨；在<a href="http://www.duwenzhang.com/huati/shilian/index1.html">失恋中</a>更痛苦，因为无所依傍，所以<a href="http://www.duwenzhang.com/huati/gudu/index1.html">孤独</a><a href="http://www.duwenzhang.com/huati/jimo/index1.html">寂寞</a>，痛不欲生，自我戕害。可是到了人生的这个时期，不管是热恋也好，失恋也罢，都能平静地对待，诗意的化解。不是说心如止水，情如枯井，而是能理智地看待，睿智地经营，这样使情爱更彰显出深沉含蓄之美，情深意切之境。让相爱的双方没有压力，更能<a href="http://www.duwenzhang.com/huati/xiangshou/index1.html">享受</a>爱本身给人带来的<a href="http://www.duwenzhang.com/huati/kuaile/index1.html">快乐</a>。\r\n</p>\r\n<p>\r\n	她想每个人的一生中的某个阶段是需要某种热闹的，那时侯饱涨的<a href="http://www.duwenzhang.com/huati/shengming/index1.html">生命力</a>需要向外奔突，就象急湍的河流一样。但一个人不能永远停留在这个阶段。经过了激烈的撞击之后，生命就来到了一块开阔的谷地，汇蓄成了一片浩瀚的的湖泊。这时就会变得异常的平和宁静，这种脱离了世俗的宁静，是以丰富的精神内涵为依傍的。它是一种超脱，一种繁华落尽见<a href="http://www.duwenzhang.com/huati/zhenqing/index1.html">真情</a>的纯粹，一种精神的升华。托尔斯泰曾经说过：“随着年岁增长，我的生命越来越精神化了”。说的就是这样的感触。\r\n</p>\r\n<p>\r\n	人淡如菊，就是一种丰富的精神安静。具有这种品格的人，能够浸润在风晨雨夕，面对着阶柳庭花，听得到自然的呼吸，感受得到自然的脉搏。这时，斗室便是八极，<a href="http://www.duwenzhang.com/huati/neixin/index1.html">内心</a>顿成宇宙；这时，精神就会富有，心胸就会博大；这时，便<a href="http://www.duwenzhang.com/huati/yongyou/index1.html">拥有</a>了一份澄明清澈，一份从容淡定。人生就从此不寂寞了。\r\n</p>', 'cfb3ea70-6e75-4931-8f5a-f4ef042e559e', 0, 0, '2015-10-28 15:09:54', 0, 0, 0, 0, 'site', 1446016194522, '使用说明', '8dea01b1-9bfd-4635-be39-c1fa6438b85b', '使用说明', '399aaa99-ca86-4507-a121-5f02a77bc6d3', '人的一生是要经历许多阶段的，比如说纯真无邪的少年时代，激情如火的<a href="http://www.duwenzhang.com/huati/qingchun/index1.html">青春岁月</a>，厚重沉稳的中年时期，从容淡定的<a href="http://www.duwenzhang.com/wenzhang/renshengzheli/">人生暮年</a>。每个时候都有独特的风景，每段岁月都会给人不同的感受。可进入中年的她，突然间<a href="http://www.duwenzhang.com/huati/ganjue/index1.html">感觉</a>自己，就一下从躁动中宁静下来了，不经意间就有了种坐看云起云舒，我自心境如水的超然。\r\n<p>\r\n	她感到在无意中，一切都漫漫地淡下来了，常常会挂着<a href="http://www.duwenzhang.com/huati/weixiao/index1.html">淡淡的微笑</a>，给人一种和谐<a href="http://www.duwenzhang.com/huati/wenxin/index1.html">温馨之感</a>；常常看淡<a href="http://www.duwenzhang.com/huati/mingli/index1.html">名利</a>和物质，却看重人与人之间的<a href="http://www.duwenzhang.com/huati/ganqing/index1.html">感情</a>，常常不会<a href="http://www.duwenzhang.com/huati/chongdong/index1.html">冲动</a>行事，也不会轻易<a href="http://www.duwenzhang.com/huati/houhui/index1.html">后悔</a>，她会为自己的决定负责。可当她一旦爱上一个人，一定会坚守自己的那份爱，<a href="http://www.duwenzhang.com/wenzhang/aiqingwenzhang/">爱情</a>的保质期是“永远”。\r\n</p>\r\n<p>\r\n	她还会在秋阳明丽的早晨或午后为自己沏一壶香茗，手捧一本书细细品位，慢慢欣赏。她懂得什么是智性美，她更愿意在闲暇的时候去学习书法音乐美术，或者去充电接受最新的科技知识，来提高自己的<a href="http://www.duwenzhang.com/wenzhang/renshengzheli/20080818/15697.html">修养</a>和品位，她不会把<a href="http://www.duwenzhang.com/huati/shijian/index1.html">时间</a>浪费在世俗的纷争和无聊的麻将中，更不会和别人去攀比高档名牌的服饰和<a href="http://www.duwenzhang.com/huati/xurong/index1.html">虚荣</a>的炫耀，她知道<a href="http://www.duwenzhang.com/huati/meili/index1.html">真正的美丽</a>一定是由内而外散发出来的。\r\n</p>\r\n<p>\r\n	可是她也记得不久前还在为工作上的事<a href="http://www.duwenzhang.com/huati/fannao/index1.html">烦恼</a>不已，什么上司不赏识呀，工作业绩不突出啦，还有同事之间不服气了，等等，等等，整个身心陷进了争强好胜的泥沼里，苦苦挣扎，不能释怀，可是到了中年一切就都云开日出了，不是不<a href="http://www.duwenzhang.com/huati/nuli/index1.html">努力</a>工作，只是觉得自己尽力就问心无愧了，至于结果就不会去过多考虑了，这样反而同事之间的关系和谐了，人的精神就愉快了，心胸也宽广了。\r\n</p>\r\n<p>\r\n	她也有曾经陷入爱恋中不能自拔的时候。那时，在热恋中<a href="http://www.duwenzhang.com/huati/tongku/index1.html">痛苦</a>，因为怕<a href="http://www.duwenzhang.com/huati/shiqu/index1.html">失去</a>，所以猜忌怀疑，无事生非，互相折磨；在<a href="http://www.duwenzhang.com/huati/shilian/index1.html">失恋中</a>更痛苦，因为无所依傍，所以<a href="http://www.duwenzhang.com/huati/gudu/index1.html">孤独</a><a href="http://www.duwenzhang.com/huati/jimo/index1.html">寂寞</a>，痛不欲生，自我戕害。可是到了人生的这个时期，不管是热恋也好，失恋也罢，都能平静地对待，诗意的化解。不是说心如止水，情如枯井，而是能理智地看待，睿智地经营，这样使情爱更彰显出深沉含蓄之美，情深意切之境。让相爱的双方没有压力，更能<a href="http://www.duwenzhang.com/huati/xiangshou/index1.html">享受</a>爱本身给人带来的<a href="http://www.duwenzhang.com/huati/kuaile/index1.html">快乐</a>。\r\n</p>\r\n<p>\r\n	她想每个人的一生中的某个阶段是需要某种热闹的，那时侯饱涨的<a href="http://www.duwenzhang.com/huati/shengming/index1.html">生命力</a>需要向外奔突，就象急湍的河流一样。但一个人不能永远停留在这个阶段。经过了激烈的撞击之后，生命就来到了一块开阔的谷地，汇蓄成了一片浩瀚的的湖泊。这时就会变得异常的平和宁静，这种脱离了世俗的宁静，是以丰富的精神内涵为依傍的。它是一种超脱，一种繁华落尽见<a href="http://www.duwenzhang.com/huati/zhenqing/index1.html">真情</a>的纯粹，一种精神的升华。托尔斯泰曾经说过：“随着年岁增长，我的生命越来越精神化了”。说的就是这样的感触。\r\n</p>\r\n<p>\r\n	人淡如菊，就是一种丰富的精神安静。具有这种品格的人，能够浸润在风晨雨夕，面对着阶柳庭花，听得到自然的呼吸，感受得到自然的脉搏。这时，斗室便是八极，<a href="http://www.duwenzhang.com/huati/neixin/index1.html">内心</a>顿成宇宙；这时，精神就会富有，心胸就会博大；这时，便<a href="http://www.duwenzhang.com/huati/yongyou/index1.html">拥有</a>了一份澄明清澈，一份从容淡定。人生就从此不寂寞了。\r\n</p>'),
	('f8dccd78-394e-4fa9-b919-8e28adc2bd51', 'admin', '测试测试', '59d927da-180e-4c8b-9b6e-e3509df93d02', 1, 0, '2015-10-27 15:47:29', 0, 9, 0, 0, 'site', 1445932049734, '测试', '8dea01b1-9bfd-4635-be39-c1fa6438b85b', '测试', '504e3a82-cf1a-4d8f-9f44-fbf22bd79155', '测试');
/*!40000 ALTER TABLE `ticket_news` ENABLE KEYS */;


-- 导出  表 ticket.ticket_newsclass 结构
CREATE TABLE IF NOT EXISTS `ticket_newsclass` (
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

-- 正在导出表  ticket.ticket_newsclass 的数据：~30 rows (大约)
DELETE FROM `ticket_newsclass`;
/*!40000 ALTER TABLE `ticket_newsclass` DISABLE KEYS */;
INSERT INTO `ticket_newsclass` (`id`, `descript`, `isDefaultShow`, `name`, `parent_id`, `audit`, `commend`, `createTime`, `deleteFlag`, `hits`, `hot`, `orderValue`, `versionFlag`, `visitUrl`, `alias`, `englishName`, `listPageRedirectTemplate_id`) VALUES
	('067416db-deff-40ef-8583-95d641ce17b7', '便捷登机<br />', 0, '便捷登机', NULL, 0, 0, '2015-10-26 18:35:04', 0, 0, 0, 0, 'site', 1445855704093, 'bianjiedengji', 'ConvenientBoarding', '708f13e8-a071-4856-a3a6-bd76f25e8ad4'),
	('08f863d7-9f49-48ab-8d89-4fc67f434817', '乘客乘坐飞机的注意事项<br />', 0, '乘机须知', NULL, 0, 0, '2015-10-22 14:19:19', 0, 0, 0, 0, 'site', 1445494759937, 'chengjixuzhi', 'flightNotice', 'd9fc7be2-55fb-4adf-ad2b-be0e85380e8e'),
	('177b1cb2-546c-4280-b0f1-352f420a8277', '长水文化<br />', 0, '机场文化', NULL, 0, 0, '2015-10-22 16:22:19', 0, 0, 0, 0, 'site', 1445502139952, 'jichangwenhua', 'culture', 'c97c49e9-2ba5-4656-85f2-9ab8bcaef6f1'),
	('198934b4-60d6-4b90-b121-58ccbbe37e22', '商人旅客服务<br />', 0, '商旅服务', NULL, 0, 0, '2015-10-22 15:19:56', 0, 0, 0, 0, 'site', 1445498396715, 'shanglvfuwu', 'businessService', 'af581355-3edb-4058-9f4e-626d95de30eb'),
	('22945254-411b-4223-beed-c52716c37112', '乘客在中国境内乘飞机的注意事项<br />', 0, '国内乘机须知', '08f863d7-9f49-48ab-8d89-4fc67f434817', 0, 0, '2015-10-22 14:20:43', 0, 0, 0, 0, 'site', 1445494843106, 'guoneichengjixuzhi', 'domisticFlightNotice', '901bb441-33a3-48cf-8cd4-06b1af1246e6'),
	('27ec9834-3713-40d9-b116-ebea58c6bd22', '机场餐饮', 0, '机场餐饮', 'e7e5e6a1-a3af-4683-a37d-6f2f6999653c', 0, 0, '2015-10-22 16:15:03', 0, 0, 0, 0, 'site', 1445501703249, 'jichangcanyin', 'airportResturant', ''),
	('349d6590-1917-44a3-a502-b4696d9dd32f', '机场公共设施<br />', 0, '公共设施', NULL, 0, 0, '2015-10-22 14:55:42', 0, 0, 0, 0, 'site', 1445496942127, 'gonggongsheshi', 'publicFacilities', NULL),
	('37b41b6a-87e6-4f13-969b-5abe7a554225', '机票改签', 0, '机票改签', 'e7e5e6a1-a3af-4683-a37d-6f2f6999653c', 0, 0, '2015-10-22 16:16:56', 0, 0, 0, 0, 'site', 1445501816625, 'jipiaogaiqian', 'mealTicket', ''),
	('3f48ee06-ddf3-493d-b0b1-ebf55de9669b', '爱心服务', 0, '爱心服务', NULL, 0, 0, '2015-10-22 15:12:37', 0, 0, 0, 0, 'site', 1445497957899, 'aixinfuwu', 'caringService', NULL),
	('4ca6958f-879d-4949-8d76-5681e4c4e074', '文化展示    ', 0, '文化展示', '177b1cb2-546c-4280-b0f1-352f420a8277', 0, 0, '2015-10-22 16:24:07', 0, 0, 0, 0, 'site', 1445502247417, 'wenhuazhanshi', 'cultureShow', '5b6c6092-4935-4cd4-b5fd-377585ba3fda'),
	('508e3a06-71ad-44a0-80c1-6ab19eaad9c5', '公告栏', 0, '公告栏', 'ccfc2b09-0434-4334-bf8f-629ba8bb419c', 0, 0, '2015-10-26 13:45:28', 0, 0, 0, 0, 'site', 1445838328501, 'gonggaolan', 'noticeBoard', '7378dda1-0b92-48ad-a193-d54329481b96'),
	('5246a203-50f0-45f4-8557-e3a41dc77885', '乘客需要跨国乘机须知的注意事项<br />', 0, '国际乘机须知', '08f863d7-9f49-48ab-8d89-4fc67f434817', 0, 0, '2015-10-22 14:21:42', 0, 0, 0, 0, 'site', 1445494902707, 'guojichengjixuzhi', 'internationalFlightNotice', 'e93654dd-d617-4a16-8a90-8a5e5b5e9845'),
	('59d927da-180e-4c8b-9b6e-e3509df93d02', '机场要闻', 0, '机场要闻', 'ccfc2b09-0434-4334-bf8f-629ba8bb419c', 0, 0, '2015-10-26 13:44:46', 0, 0, 0, 0, 'site', 1445838286809, 'jichangyaowen', 'airportFocusNews', '88e12fcf-ae6c-484c-97a8-8d6ba6c5bc8d'),
	('6ba191d6-7ecc-4a7f-b86f-882b703d095f', '机场酒店', 0, '机场酒店', 'e7e5e6a1-a3af-4683-a37d-6f2f6999653c', 0, 0, '2015-10-22 16:15:34', 0, 0, 0, 0, 'site', 1445501734226, 'jichangjiudian', 'airportHotel', ''),
	('77c91b68-412e-499a-bfb2-e904498fa43e', '机场购物<br />', 0, '机场购物', 'e7e5e6a1-a3af-4683-a37d-6f2f6999653c', 0, 0, '2015-10-22 16:16:07', 0, 0, 0, 0, 'site', 1445501767368, 'jichanggouwu', 'airportShopping', ''),
	('7e1fc23e-7fc3-4e0e-b67e-34c526167652', '关注我们', 0, '关注我们', NULL, 0, 0, '2015-10-22 16:25:55', 0, 0, 0, 0, 'site', 1445502355983, 'guanzhuwomen', 'payAttentionToUs', 'fc8b10c1-44f6-421b-8ae9-6bb35b5b309a'),
	('88cff97c-116f-445e-adfd-48442438d5fc', '免责声明<br />', 0, '免责声明', NULL, 0, 0, '2015-10-22 16:26:51', 0, 0, 0, 0, 'site', 1445502411515, 'mianzeshengming', 'disclaimer', '22d79c14-2e1f-4c52-981c-70bec4c60e6b'),
	('90aa446a-8cd2-403e-ad4f-81aa1848a5ae', '国内乘机须知', 0, '国内乘机须知', '041d817f-abd1-48fc-81d1-b594fdb23982', 0, 0, '2015-10-19 17:21:17', 0, 0, 0, 0, 'site', 1445246477086, NULL, NULL, NULL),
	('90b46c99-5c32-4ce4-b59f-7b7300a0cba7', '头等舱商务舱服务<br />', 0, '头等舱商务舱服务', '198934b4-60d6-4b90-b121-58ccbbe37e22', 0, 0, '2015-10-22 15:42:55', 0, 0, 0, 0, 'site', 1445499775464, 'shangwucangfuwu', 'firstClassBusinessClassService', '66ca1ffd-d852-4640-84f9-61035e6fa6fa'),
	('9ef29329-cfca-4614-89d3-31bd21c383fe', '提供乘客相关的行李代办服务流程<br />', 0, '行李服务', NULL, 0, 0, '2015-10-22 14:31:24', 0, 0, 0, 0, 'site', 1445495484785, 'xinglifuwu', 'luggageService', NULL),
	('a5e371be-bdde-488f-b639-ce86dfbe5c9b', '图片新闻', 0, '图片新闻', 'ccfc2b09-0434-4334-bf8f-629ba8bb419c', 0, 0, '2015-10-26 13:46:05', 0, 0, 0, 0, 'site', 1445838365740, 'tupianxinwen', 'pictureNews', '859f0a74-d95d-440a-b0de-b5155ee3d87b'),
	('b9dd6a24-8c88-417c-91f6-a7802e532ed6', '出发', 0, '出发', NULL, 0, 0, '2015-10-28 15:40:36', 0, 0, 0, 0, 'site', 1446018036713, 'chufa', 'setout', 'd0d9485c-8273-49ce-9401-6f95549be3f6'),
	('c8b871d6-f5a9-44bb-8319-d56f2154ba11', '国外乘机须知', 0, '国内乘机须知', 'a2eed3ed-71d0-49b0-b354-0696ef530dc0', 0, 0, '2015-10-19 17:38:19', 0, 0, 0, 0, 'site', 1445247499657, NULL, NULL, NULL),
	('cac7d243-96f6-4674-9845-20bd4b5c6baf', '为乘客提供方便的机场服务<br />', 0, '便民服务', NULL, 0, 0, '2015-10-22 14:40:22', 0, 0, 0, 0, 'site', 1445496022737, 'bianminfuwu', 'convenientService', NULL),
	('ccfc2b09-0434-4334-bf8f-629ba8bb419c', '机场相关的新闻和实时发布的通知消息<br />', 0, '新闻公告', NULL, 0, 0, '2015-10-22 14:24:41', 0, 0, 0, 0, 'site', 1445495081319, 'xinwengonggao', 'newsNotice', '67d6e681-a5e6-4457-b0b8-e59becfccf4f'),
	('cfb3ea70-6e75-4931-8f5a-f4ef042e559e', '图文详情<br />', 0, '图文详情', '067416db-deff-40ef-8583-95d641ce17b7', 0, 0, '2015-10-27 14:19:15', 0, 0, 0, 0, 'site', 1445926755978, 'tuwenxiangqing', 'graphicDetails', '79b6cc74-04ad-4587-8f2e-805252fef015'),
	('d0d55b8f-7829-48d9-bd2e-e0e83c5151f1', '中转链接', 0, '中转', NULL, 0, 0, '2015-10-29 10:30:02', 0, 0, 0, 0, 'site', 1446085802364, 'zhongzhuan', 'transfer', '844360e1-cb5d-46dc-89e8-85a2793bf55d'),
	('d568368f-a05f-49df-b293-10915874a45b', '国外乘机须知', 0, '国外乘机须知', '041d817f-abd1-48fc-81d1-b594fdb23982', 0, 0, '2015-10-19 17:21:32', 0, 0, 0, 0, 'site', 1445246492263, NULL, NULL, NULL),
	('d6b983c3-8981-42e4-a228-ca5ac54e0090', '', 0, '国外乘机须知', 'a2eed3ed-71d0-49b0-b354-0696ef530dc0', 0, 0, '2015-10-19 17:38:33', 0, 0, 0, 0, 'site', 1445247513566, NULL, NULL, NULL),
	('e11e6ba9-942c-422c-b955-725c58c6d52f', '到达', 0, '到达', NULL, 0, 0, '2015-10-28 17:13:43', 0, 0, 0, 0, 'site', 1446023623819, 'daoda', 'reach', '0c12a86f-a9ec-4a94-8642-1269e64d9d27');
/*!40000 ALTER TABLE `ticket_newsclass` ENABLE KEYS */;


-- 导出  表 ticket.ticket_newscomment 结构
CREATE TABLE IF NOT EXISTS `ticket_newscomment` (
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

-- 正在导出表  ticket.ticket_newscomment 的数据：~0 rows (大约)
DELETE FROM `ticket_newscomment`;
/*!40000 ALTER TABLE `ticket_newscomment` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_newscomment` ENABLE KEYS */;


-- 导出  表 ticket.ticket_pageredirecttemplate 结构
CREATE TABLE IF NOT EXISTS `ticket_pageredirecttemplate` (
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

-- 正在导出表  ticket.ticket_pageredirecttemplate 的数据：~38 rows (大约)
DELETE FROM `ticket_pageredirecttemplate`;
/*!40000 ALTER TABLE `ticket_pageredirecttemplate` DISABLE KEYS */;
INSERT INTO `ticket_pageredirecttemplate` (`id`, `isSinglePage`, `name`, `audit`, `commend`, `createTime`, `deleteFlag`, `hits`, `hot`, `orderValue`, `versionFlag`, `visitUrl`, `toPage`, `type`, `directory`, `toPageAjax`) VALUES
	('06668375-c73e-4df6-abbd-096337d1fbb1', 1, '机场派出所', 0, 0, '2015-10-22 17:06:30', 1, 0, 0, 0, 'site', 1445504790276, 'airportPoliceStation', 0, 'convenientService', 'convenientServiceAjax'),
	('0c12a86f-a9ec-4a94-8642-1269e64d9d27', 0, '到达模板', 0, 0, '2015-10-28 17:13:09', 0, 0, 0, 0, 'site', 1446023589053, 'reachTemplate', 1, 'reach', 'reachAjax'),
	('1f4416cf-79c1-49b1-a4b9-65b11dec8afb', 1, '商务中心', 0, 0, '2015-10-22 16:37:49', 0, 0, 0, 0, 'site', 1445503069790, 'businessCenter', 1, 'travelService', 'travelServiceAjax'),
	('22d79c14-2e1f-4c52-981c-70bec4c60e6b', 0, '免责声明', 0, 0, '2015-10-23 10:29:16', 0, 0, 0, 0, 'site', 1445567356720, 'disclaimer', 1, 'disclaimer', 'disclaimerAjax'),
	('399aaa99-ca86-4507-a121-5f02a77bc6d3', 1, '图文详情详细模板', 0, 0, '2015-10-27 14:42:14', 0, 0, 0, 0, 'site', 1445928134159, 'graphicDetailsTemplate', 0, 'convenientBoarding', 'graphicDetailsTemplateAjax'),
	('46b89ca8-f419-46e7-bf1d-b5627ac83608', 1, '国内乘机须知', 0, 0, '2015-10-22 16:35:18', 1, 0, 0, 0, 'site', 1445502918724, 'domisticFlightNotice', 0, 'flightNotice', 'flightNoticeAjax'),
	('4c6a7265-cc45-4156-83c9-0f11efd33d22', 1, '公共设施模版', 0, 0, '2015-10-24 17:19:18', 0, 0, 0, 0, 'site', 1445678358206, 'publicFacilitiesTemplate', 0, 'publicFacilities', 'publicFacilitiesAjax'),
	('4fc9eace-175c-4768-9569-b484f5c40f85', 1, '关注我们详细模板', 0, 0, '2015-10-25 20:31:04', 0, 0, 0, 0, 'site', 1445776264812, 'payAttentionToUsTemplate', 0, 'payAttentionToUs', 'payAttentionToUsAjax'),
	('504e3a82-cf1a-4d8f-9f44-fbf22bd79155', 1, '机场要闻详细模版', 0, 0, '2015-10-24 14:28:48', 0, 0, 0, 0, 'site', 1445668128114, 'airportFocusNews-content', 0, 'newsNotice', 'airportFocusNewsAjax'),
	('5b6c6092-4935-4cd4-b5fd-377585ba3fda', 1, '文化展示', 0, 0, '2015-10-22 16:36:54', 0, 0, 0, 0, 'site', 1445503014003, 'cultureDisplayTemplate', 1, 'culture', 'cultureAjax'),
	('66ca1ffd-d852-4640-84f9-61035e6fa6fa', 1, '头等舱商务舱服务', 0, 0, '2015-10-22 16:39:05', 0, 0, 0, 0, 'site', 1445503145302, 'firstBusinessCabinLounge', 1, 'travelService', 'travelServiceAjax'),
	('67d6e681-a5e6-4457-b0b8-e59becfccf4f', 0, '新闻公告', 0, 0, '2015-10-24 13:10:21', 0, 0, 0, 0, 'site', 1445663421696, 'newsNoticeTemplate', 1, 'newsNotice', 'newsNoticeAjax'),
	('691e5271-26f7-4109-914b-32a5ebd22815', 1, '图片新闻详细模版', 0, 0, '2015-10-26 14:10:39', 0, 0, 0, 0, 'site', 1445839839824, 'pictureNews-content', 0, 'newsNotice', 'pictureNews-contentAjax'),
	('708f13e8-a071-4856-a3a6-bd76f25e8ad4', 0, '便捷登机', 0, 0, '2015-10-27 14:12:13', 0, 0, 0, 0, 'site', 1445926333262, '#', 1, '#', '##'),
	('72d1e1e8-b7d3-40b8-830e-2b809d903da3', 1, '公告栏详细模版', 0, 0, '2015-10-23 17:05:28', 0, 0, 0, 0, 'site', 1445591128606, 'noticeBoardContent', 0, 'newsNotice', 'noticeBoardContentAjax'),
	('7378dda1-0b92-48ad-a193-d54329481b96', 0, '公告栏', 0, 0, '2015-10-22 17:11:51', 0, 0, 0, 0, 'site', 1445505111688, 'notice-board', 1, 'newsNotice', 'notice-boardAjax'),
	('7778cd5d-53e9-40bf-9558-e40067096f8f', 1, '行李服务详细模版', 0, 0, '2015-10-24 15:26:34', 0, 0, 0, 0, 'site', 1445671594357, 'luggageServiceTemplate', 0, 'luggageService', 'luggageServiceAjax'),
	('79b6cc74-04ad-4587-8f2e-805252fef015', 0, '图文详情模板', 0, 0, '2015-10-27 14:35:18', 0, 0, 0, 0, 'site', 1445927718924, 'graphicDetails', 1, 'convenientBoarding', 'graphicDetailsAjax'),
	('844360e1-cb5d-46dc-89e8-85a2793bf55d', 0, '中转模板', 0, 0, '2015-10-29 10:29:29', 0, 0, 0, 0, 'site', 1446085769803, 'transferTemplate', 1, 'transfer', 'transferTemplateAjax'),
	('859f0a74-d95d-440a-b0de-b5155ee3d87b', 0, '图片新闻', 0, 0, '2015-10-22 17:12:22', 0, 0, 0, 0, 'site', 1445505142740, 'pictureNews', 1, 'pictureNews', 'pictureNewsAjax'),
	('88e12fcf-ae6c-484c-97a8-8d6ba6c5bc8d', 0, '机场要闻', 0, 0, '2015-10-22 17:13:30', 0, 0, 0, 0, 'site', 1445505210379, 'airportFocusNews', 1, 'newsNotice', 'airportFocusNewsAjax'),
	('8a7927fd-1fec-4204-889b-ce3e7a801c47', 1, '商务中心', 0, 0, '2015-10-23 09:22:16', 1, 0, 0, 0, 'site', 1445563336258, 'businessCenter', 1, 'travelService', 'businessCenterAjax'),
	('901bb441-33a3-48cf-8cd4-06b1af1246e6', 0, '国内乘机须知', 0, 0, '2015-10-23 09:25:17', 0, 0, 0, 0, 'site', 1445563517990, 'domisticFlightNotice', 1, 'flightNotice', 'flightNoticeAjax'),
	('91bb70ad-f852-402d-b1db-de74c4c9329d', 1, '便民服务模版', 0, 0, '2015-10-24 17:00:21', 0, 0, 0, 0, 'site', 1445677221683, 'convenientServiceTemplate', 0, 'convenientService', 'convenientServiceAjax'),
	('a577650a-85ca-4210-8022-6390cb8b3d9f', 1, '乘机须知详细模版', 0, 0, '2015-10-24 14:39:26', 0, 0, 0, 0, 'site', 1445668766139, 'flightNoticeTemplate', 0, 'flightNotice', 'flightNoticeAjax'),
	('af581355-3edb-4058-9f4e-626d95de30eb', 0, '商旅服务', 0, 0, '2015-10-24 17:53:41', 0, 0, 0, 0, 'site', 1445680421973, '#', 1, 'travelService', 'travelServiceAjax'),
	('bc82aa43-20bf-4041-ba4f-8819c69d40d4', 1, '免责声明详细模板', 0, 0, '2015-10-25 20:20:09', 0, 0, 0, 0, 'site', 1445775609147, 'disclaimerTempLate', 0, 'disclaimer', 'disclaimerAjax'),
	('c97c49e9-2ba5-4656-85f2-9ab8bcaef6f1', 1, '机场文化', 0, 0, '2015-10-23 10:19:49', 0, 0, 0, 0, 'site', 1445566789586, 'cultureDisplay', 1, 'culture', 'cultureAajx'),
	('d0d9485c-8273-49ce-9401-6f95549be3f6', 0, '出发模板', 0, 0, '2015-10-28 15:44:01', 0, 0, 0, 0, 'site', 1446018241847, 'setoutTemplate', 1, 'setout', 'setoutTemplateAjax'),
	('d5aeffa1-7ac1-4a67-ad57-57d631c6e32d', 1, '行李查询', 0, 0, '2015-10-22 16:51:33', 1, 0, 0, 0, 'site', 1445503893596, 'luggageQuery', 1, 'luggageService', 'luggageServiceAjax'),
	('d9fc7be2-55fb-4adf-ad2b-be0e85380e8e', 0, '乘机须知', 0, 0, '2015-10-23 15:29:43', 0, 0, 0, 0, 'site', 1445585383697, 'flightNotice', 1, 'flightNotice', 'flightNoticeAjax'),
	('e3099bcc-9b93-45cf-a5cd-0392d5838c71', 1, '商旅服务模版', 0, 0, '2015-10-24 17:48:21', 0, 0, 0, 0, 'site', 1445680101680, 'travelServiceTemplate', 0, 'travelService', 'travelServiceAjax'),
	('e93654dd-d617-4a16-8a90-8a5e5b5e9845', 0, '国际乘机须知', 0, 0, '2015-10-23 09:36:28', 0, 0, 0, 0, 'site', 1445564188482, 'internationalFlightNotice', 0, 'flightNotice', 'flightNoticeAjax'),
	('eaa2314f-42d4-4640-9889-5093b2ef7f39', 1, '行李寄存', 0, 0, '2015-10-22 16:50:52', 1, 0, 0, 0, 'site', 1445503852233, 'luggageDeposit', 1, 'luggageService', 'luggageServiceAjax'),
	('eeb5376d-5007-40dc-8ccf-0e3a1afe1067', 1, '遗失物品查询', 0, 0, '2015-10-22 17:08:20', 1, 0, 0, 0, 'site', 1445504900613, 'lostGoodsQuery', 0, 'convenientService', 'convenientServiceAjax'),
	('f2e138f9-f567-4557-8397-9cc7c916c73c', 1, '爱心服务模版', 0, 0, '2015-10-24 16:46:30', 0, 0, 0, 0, 'site', 1445676390463, 'caringServiceTemplate', 0, 'caringService', 'caringServiceAjax'),
	('fc8b10c1-44f6-421b-8ae9-6bb35b5b309a', 1, '关注我们', 0, 0, '2015-10-23 10:26:47', 0, 0, 0, 0, 'site', 1445567207298, 'payAttentionToUsTemplate', 0, 'payAttentionToUs', 'payAttentionToUsAjax'),
	('fd547642-1d57-4c06-9586-94e90705624b', 1, '金融服务', 0, 0, '2015-10-22 17:07:47', 0, 0, 0, 0, 'site', 1445504867656, 'currencyExchange', 1, 'convenientService', 'convenientServiceAjax');
/*!40000 ALTER TABLE `ticket_pageredirecttemplate` ENABLE KEYS */;


-- 导出  表 ticket.ticket_privilegeinfo 结构
CREATE TABLE IF NOT EXISTS `ticket_privilegeinfo` (
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

-- 正在导出表  ticket.ticket_privilegeinfo 的数据：~3 rows (大约)
DELETE FROM `ticket_privilegeinfo`;
/*!40000 ALTER TABLE `ticket_privilegeinfo` DISABLE KEYS */;
INSERT INTO `ticket_privilegeinfo` (`id`, `descript`, `includeMethods`, `name`, `audit`, `commend`, `createTime`, `deleteFlag`, `hits`, `hot`, `orderValue`, `versionFlag`, `visitUrl`) VALUES
	('9c2ce025-f730-495b-807b-dc9e5570c123', '删除<br />', 'delete,remove', '删除', 0, 0, '2015-10-20 17:31:25', 0, 0, 0, 0, 'site', 1445333485146),
	('aa2e8349-05c9-44db-a0d7-527c64c9cf75', '更新数据库信息<br />', 'update,merge', '更新', 0, 0, '2015-10-19 17:03:15', 0, 0, 0, 0, 'site', 1445245395630),
	('b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '添加的权限<br />', 'add,persist', '添加', 0, 0, '2015-10-19 15:43:27', 0, 0, 0, 0, 'site', 1445240607780);
/*!40000 ALTER TABLE `ticket_privilegeinfo` ENABLE KEYS */;


-- 导出  表 ticket.ticket_roleinfo 结构
CREATE TABLE IF NOT EXISTS `ticket_roleinfo` (
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

-- 正在导出表  ticket.ticket_roleinfo 的数据：~2 rows (大约)
DELETE FROM `ticket_roleinfo`;
/*!40000 ALTER TABLE `ticket_roleinfo` DISABLE KEYS */;
INSERT INTO `ticket_roleinfo` (`id`, `descript`, `name`, `audit`, `commend`, `createTime`, `deleteFlag`, `hits`, `hot`, `orderValue`, `versionFlag`, `visitUrl`) VALUES
	('2a1dcfe2-529c-4807-92bb-5f9a63dec061', '管理系统', '超级管理员', 0, 0, '2015-10-19 15:42:41', 0, 0, 0, 0, 'site', 1445240561491),
	('442941c4-17f1-482f-94d8-d9d39860712d', '部门经理', '部门经理', 0, 0, '2015-10-20 17:38:22', 0, 0, 0, 0, 'site', 1445333902604);
/*!40000 ALTER TABLE `ticket_roleinfo` ENABLE KEYS */;


-- 导出  表 ticket.ticket_roleprivilege 结构
CREATE TABLE IF NOT EXISTS `ticket_roleprivilege` (
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

-- 正在导出表  ticket.ticket_roleprivilege 的数据：~438 rows (大约)
DELETE FROM `ticket_roleprivilege`;
/*!40000 ALTER TABLE `ticket_roleprivilege` DISABLE KEYS */;
INSERT INTO `ticket_roleprivilege` (`id`, `privilegeId`, `roleId`, `audit`, `commend`, `createTime`, `deleteFlag`, `hits`, `hot`, `orderValue`, `versionFlag`, `visitUrl`, `systemModuleId`) VALUES
	('00b9e232-9ba9-47e8-b83d-b5bc115e7f54', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:46', 0, 0, 0, 0, 'site', 1445494666338, '33db1afb-862b-44a1-b54e-2fef5b2eb003'),
	('00f53d97-314f-44fc-a807-12ca87467203', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:30', 0, 0, 0, 0, 'site', 1445474370436, '4738dbcb-bdc9-496c-ab7f-cd1a4d4b5a8b'),
	('02166352-0538-460c-837e-549dd67d57f9', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:36', 0, 0, 0, 0, 'site', 1445474376088, 'f0af133d-e253-490d-859e-481c072393ee'),
	('02e13ba9-93de-4762-bf3f-4f34a79e17cb', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:39', 0, 0, 0, 0, 'site', 1445494659220, '35b400fb-2f3f-42cd-be0d-580d06b60d86'),
	('03f9b3d5-dc94-46b2-99c0-4bd2a44cd991', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:43', 0, 0, 0, 0, 'site', 1445494663456, '8161bdd4-2f90-4079-a434-8f1d8d937049'),
	('03fab623-6f00-403f-b3cd-49993a90a28d', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:36', 0, 0, 0, 0, 'site', 1445474376772, 'b783233e-d891-4651-8df3-aaae13ab0b69'),
	('03fcb77a-873b-452d-8365-87861edce7ac', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:46', 0, 0, 0, 0, 'site', 1445494666946, '22b70bc4-6dbb-4015-953a-3c092eb9798c'),
	('051908bf-07a6-432f-9ccb-8afe95b93970', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:32', 0, 0, 0, 0, 'site', 1445474372580, 'd3759e96-1aec-4d5f-8bd1-f1b9e7d630ad'),
	('061f9c7e-4c23-450c-bd64-071a56a93f82', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:38', 0, 0, 0, 0, 'site', 1445474378964, 'd6f5537b-4534-4ba3-a351-f6be97537d2e'),
	('067f98d1-6a4b-4f75-979e-57c7b56612b7', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:40', 0, 0, 0, 0, 'site', 1445494660705, 'e577d00a-9afe-4f9c-a2f6-b84be30a0884'),
	('07c15ef4-b594-40a4-9dba-4deb6e2369d9', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:35', 0, 0, 0, 0, 'site', 1445474375337, '3cad172d-eaba-4748-903e-617ea2b6df8f'),
	('07f1bf08-fc7f-4d4c-ac2b-b91eadb9646e', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:44', 0, 0, 0, 0, 'site', 1445494664415, 'f0af133d-e253-490d-859e-481c072393ee'),
	('083b7ce9-a6d0-4f1b-b2aa-dbf3cc508acb', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:39', 0, 0, 0, 0, 'site', 1445494659130, '337d7c3d-27ba-4d39-92c7-39f72fcf2db4'),
	('09d5269a-f596-4c44-86e6-05600ecbc89b', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:34', 0, 0, 0, 0, 'site', 1445474374997, 'e6c92868-3109-4e0b-8fa9-6d781c873a19'),
	('0a393057-4454-4373-a174-eb1e970479ba', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:43', 0, 0, 0, 0, 'site', 1445494663331, 'e6c92868-3109-4e0b-8fa9-6d781c873a19'),
	('0b14d30d-8188-4981-b63d-1d0064a4d6fc', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:35', 0, 0, 0, 0, 'site', 1445474375739, '879b2bc4-e3d4-42de-bfc0-43774cd0482f'),
	('0b4fbbfc-bdd7-4dc0-8062-1ab5875f141e', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:40', 0, 0, 0, 0, 'site', 1445494660736, 'ae9e7b3c-2dfe-49fb-b48a-5cc7d3644e3e'),
	('0bf9dfa8-9c5f-43dd-9061-6ec74de19caa', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:38', 0, 0, 0, 0, 'site', 1445474378314, '2cd3982c-d469-440d-922a-75e965968f23'),
	('0ca367c7-0d0c-45af-abb8-8e9082d2c015', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:32', 0, 0, 0, 0, 'site', 1445474372721, 'fdb9fe15-c954-4bf7-adf8-0893038a8b71'),
	('0d1b347c-0140-43d2-ab52-a17e027deca1', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:36', 0, 0, 0, 0, 'site', 1445474376513, '10cc580a-7f73-41b0-8f8b-d264bb3b892f'),
	('0e2461e4-f4b4-4f76-a22d-bf3804c0b7a5', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:43', 0, 0, 0, 0, 'site', 1445494663714, 'ac4ccdbc-4b5b-46e5-862f-ea8e319b56a5'),
	('0f22cd1d-5a94-455e-95e2-dcc836da94c4', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:43', 0, 0, 0, 0, 'site', 1445494663156, '7cd4debb-1981-4c07-867c-6d7f6fc1e955'),
	('0f2e04c8-a59a-4474-aa42-ecd2681c568f', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:35', 0, 0, 0, 0, 'site', 1445474375621, 'ac4ccdbc-4b5b-46e5-862f-ea8e319b56a5'),
	('0f73fb05-4594-4872-8abe-b1aa0dfa5edb', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:40', 0, 0, 0, 0, 'site', 1445494660678, 'e577d00a-9afe-4f9c-a2f6-b84be30a0884'),
	('0f8b8e27-5c9d-4bda-985f-1df1a8015f3f', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:41', 0, 0, 0, 0, 'site', 1445494661112, 'd3759e96-1aec-4d5f-8bd1-f1b9e7d630ad'),
	('0fea2f5f-d073-4fea-8228-f27ecedc2fee', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:31', 0, 0, 0, 0, 'site', 1445474371996, 'e577d00a-9afe-4f9c-a2f6-b84be30a0884'),
	('10499768-d29c-456a-81e7-17b35fb860c1', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:36', 0, 0, 0, 0, 'site', 1445474376579, '10cc580a-7f73-41b0-8f8b-d264bb3b892f'),
	('106c9a90-519b-4b85-bc9c-eff270aca599', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:45', 0, 0, 0, 0, 'site', 1445494665132, '10cc580a-7f73-41b0-8f8b-d264bb3b892f'),
	('109b3e38-151c-4521-9f1a-229169316ac3', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:35', 0, 0, 0, 0, 'site', 1445474375704, '879b2bc4-e3d4-42de-bfc0-43774cd0482f'),
	('117d473e-5974-48b6-8f99-832c50fcede3', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:39', 0, 0, 0, 0, 'site', 1445494659512, '4b18167e-ecad-489e-87b6-44c90d04c9a4'),
	('11c5f5b5-46d2-46f7-a2cf-5bdeb2191085', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:34', 0, 0, 0, 0, 'site', 1445474374913, 'e6c92868-3109-4e0b-8fa9-6d781c873a19'),
	('12dd8f22-23d2-4361-ac99-92888209046c', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:33', 0, 0, 0, 0, 'site', 1445474373612, 'af9a68c5-84b9-4ffd-b238-93711b0797a8'),
	('138d836a-c1b7-467c-8026-e6d0e37107ef', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:37', 0, 0, 0, 0, 'site', 1445474377730, '08f8586c-9013-48d0-81ec-362be4a97d4a'),
	('158c05b8-4acd-4e9b-a9cb-3599592f6735', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:43', 0, 0, 0, 0, 'site', 1445494663187, '7cd4debb-1981-4c07-867c-6d7f6fc1e955'),
	('168e1190-b36b-4eb6-80ce-a837890ae6db', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:43', 0, 0, 0, 0, 'site', 1445494663014, '6661c054-ba77-4ef7-8036-66b36073c3b0'),
	('1799536a-61ea-444e-9551-67bcb3157b16', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:34', 0, 0, 0, 0, 'site', 1445474374230, '7b52f775-b5dc-4d73-b041-452d5c4d0805'),
	('17b08fd1-8f0e-41cd-a893-49a55334a978', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:32', 0, 0, 0, 0, 'site', 1445474372788, 'b2240fb3-61e2-427e-8cd4-ec474380594d'),
	('18de0552-0a8c-4f72-896d-f2425b42eab8', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:41', 0, 0, 0, 0, 'site', 1445494661170, 'd3759e96-1aec-4d5f-8bd1-f1b9e7d630ad'),
	('191d42cc-3fb5-44db-8593-42dd3467f93a', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:32', 0, 0, 0, 0, 'site', 1445474372145, 'ae9e7b3c-2dfe-49fb-b48a-5cc7d3644e3e'),
	('1948b3f5-958d-44a0-9a6c-269c975641b2', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:34', 0, 0, 0, 0, 'site', 1445474374537, '6661c054-ba77-4ef7-8036-66b36073c3b0'),
	('1b02f80c-338a-4474-b0ca-6bbd0679a0fe', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:33', 0, 0, 0, 0, 'site', 1445474373071, '5a57bdb0-33df-4879-a1a1-0a2b805b1676'),
	('1b70bcef-2079-412c-9099-5dc1a9d63b7d', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:42', 0, 0, 0, 0, 'site', 1445494662322, 'a0b1bbc8-0fab-4997-92dc-d216898562ac'),
	('1ba758b3-46d7-48c9-95a9-7a3ad26dae9d', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:36', 0, 0, 0, 0, 'site', 1445474376697, '9b427f92-d323-4800-b98f-151cd6486237'),
	('1bd1762e-f397-4a5d-8091-286c219f280d', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:42', 0, 0, 0, 0, 'site', 1445494662831, '9d12aa16-5cdf-4fb7-b7d3-81058ed8024f'),
	('1c0badd9-cfcf-4a8f-8068-19fb20aa534e', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:38', 0, 0, 0, 0, 'site', 1445494658919, '4738dbcb-bdc9-496c-ab7f-cd1a4d4b5a8b'),
	('1c39e316-d07c-4400-9ec3-6a67e93907f6', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:47', 0, 0, 0, 0, 'site', 1445494667416, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('1c6f5a7b-be77-4362-aa87-d366b1052e8b', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:35', 0, 0, 0, 0, 'site', 1445474375938, '8ecea40a-bd6b-4dfe-914c-aa44bc5fc201'),
	('1e4e3a44-8327-4d84-af86-d876d7dc8305', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:39', 0, 0, 0, 0, 'site', 1445494659394, '49019030-af3b-4edb-bec3-ae7e6c55a279'),
	('1ed077ea-dcaa-46ed-ad0c-73b088ef4b3b', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:30', 0, 0, 0, 0, 'site', 1445474370370, '4738dbcb-bdc9-496c-ab7f-cd1a4d4b5a8b'),
	('1f7a4d67-f273-46c5-9232-e6d2f9a286b6', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:45', 0, 0, 0, 0, 'site', 1445494665087, '10cc580a-7f73-41b0-8f8b-d264bb3b892f'),
	('21410cf9-4dca-438c-bd66-2db2a75f7cf5', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:30', 0, 0, 0, 0, 'site', 1445474370537, 'd39e3ad7-a482-462f-9eb9-454b26b9db5b'),
	('2276d6fb-62f7-4781-81d5-7e8321705e8e', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:38', 0, 0, 0, 0, 'site', 1445474378205, '7cbad721-91a7-460b-8916-bb19bbe5e0f2'),
	('22a48e4d-8b0c-4bc2-a08c-0b2e4c7bead9', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:37', 0, 0, 0, 0, 'site', 1445474377889, 'dbe283e0-6fb1-4b13-a57a-0517a59f5474'),
	('2352d0d1-2273-4ffc-88e3-67f3b2d60bd2', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:33', 0, 0, 0, 0, 'site', 1445474373170, 'd5a06e48-e12f-4146-b07e-7f7e85b1f1c0'),
	('2373990d-90ed-464f-8fd8-a2354c669ba3', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:42', 0, 0, 0, 0, 'site', 1445494662045, '8df8840d-610b-409d-a2bb-e912d3c20ec5'),
	('23e61a19-ab9e-4dd9-aeab-aee0f2bc03f2', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:35', 0, 0, 0, 0, 'site', 1445474375663, '879b2bc4-e3d4-42de-bfc0-43774cd0482f'),
	('24a3d0b8-f1f7-4879-bb96-91167b9c9450', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:36', 0, 0, 0, 0, 'site', 1445474376263, '079f46b3-b738-4afb-8524-f624f2e96766'),
	('2559bb3e-3ebb-44e4-b247-703a91fc0f16', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:32', 0, 0, 0, 0, 'site', 1445474372545, 'd3759e96-1aec-4d5f-8bd1-f1b9e7d630ad'),
	('25908207-6fd2-43c0-a8c2-e9b14148e1bb', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:40', 0, 0, 0, 0, 'site', 1445494660823, '1b219275-1f31-4d72-ac8a-86f62caac828'),
	('25cbaf3f-3307-4304-ad71-32555ae00931', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:41', 0, 0, 0, 0, 'site', 1445494661695, '5a57bdb0-33df-4879-a1a1-0a2b805b1676'),
	('25ddb8a3-1ee5-4afe-a6f6-3912b7b417d1', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:43', 0, 0, 0, 0, 'site', 1445494663804, '879b2bc4-e3d4-42de-bfc0-43774cd0482f'),
	('26151a18-5c8c-488c-b462-5df5f3025555', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:43', 0, 0, 0, 0, 'site', 1445494663773, '879b2bc4-e3d4-42de-bfc0-43774cd0482f'),
	('2632912e-9ba8-4d51-894b-2d6528d91452', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:37', 0, 0, 0, 0, 'site', 1445474377096, '39f7e44b-d90a-41aa-8327-895aee0ea7ec'),
	('26337ee2-fff5-472e-b84d-a097316fee68', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:45', 0, 0, 0, 0, 'site', 1445494665255, '9b427f92-d323-4800-b98f-151cd6486237'),
	('278b8b58-4e4e-452c-93c8-77b6b36b54f7', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:46', 0, 0, 0, 0, 'site', 1445494666448, 'b8a2a9e1-3f6d-4ec3-954f-5aa96bb1c3c6'),
	('28c2a05f-373f-448b-b075-98e49a2c7299', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:36', 0, 0, 0, 0, 'site', 1445474376346, '079f46b3-b738-4afb-8524-f624f2e96766'),
	('28d03213-d334-4e5d-9dc1-442f5819a416', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:30', 0, 0, 0, 0, 'site', 1445474370604, '337d7c3d-27ba-4d39-92c7-39f72fcf2db4'),
	('28da0672-c4c7-408e-99e1-e04add993357', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:45', 0, 0, 0, 0, 'site', 1445494665954, 'b6f04f26-86b9-4a47-8fff-55f7b40d61e0'),
	('293f6f66-9c01-4277-95a6-2493319b343a', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:40', 0, 0, 0, 0, 'site', 1445494660462, 'a589064e-4bb9-4d62-a100-21f734154200'),
	('297511f6-7df3-4acf-a1ba-39bf7123e300', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:33', 0, 0, 0, 0, 'site', 1445474373504, '8df8840d-610b-409d-a2bb-e912d3c20ec5'),
	('2a32c693-0c8c-4f42-927a-10198543fbf9', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:39', 0, 0, 0, 0, 'site', 1445494659005, 'd39e3ad7-a482-462f-9eb9-454b26b9db5b'),
	('2b45b0ec-aca9-4e05-b777-7c7bebb1a47c', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:47', 0, 0, 0, 0, 'site', 1445494667113, 'd6f5537b-4534-4ba3-a351-f6be97537d2e'),
	('2b83c7cb-1e2d-44bf-abb1-5648c7439def', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:33', 0, 0, 0, 0, 'site', 1445474373978, 'ce2eaf51-93f2-49c4-b9bf-a4fed3e7f599'),
	('2bdb8de2-4938-473b-9eb7-2a20fd2d324a', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:32', 0, 0, 0, 0, 'site', 1445474372679, 'fdb9fe15-c954-4bf7-adf8-0893038a8b71'),
	('2be49d00-ed0f-4eb1-8677-428ef749e526', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:44', 0, 0, 0, 0, 'site', 1445494664735, '6b54fa40-45b7-4c08-81dc-28a4e6ca5c82'),
	('2d3f3082-656e-44ef-94fa-9af9e1aa41ed', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:35', 0, 0, 0, 0, 'site', 1445474375546, 'ac4ccdbc-4b5b-46e5-862f-ea8e319b56a5'),
	('2dd23625-4202-48e0-87de-db617b2d97f8', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:42', 0, 0, 0, 0, 'site', 1445494662523, 'ce2eaf51-93f2-49c4-b9bf-a4fed3e7f599'),
	('2e129325-ef8e-4750-b26f-d0cc8424c441', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:44', 0, 0, 0, 0, 'site', 1445494664180, 'f0af133d-e253-490d-859e-481c072393ee'),
	('2eea659d-3f37-439b-a213-28338fd91ea2', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:33', 0, 0, 0, 0, 'site', 1445474373355, 'f78238fd-a4e5-464c-b474-4f184493296b'),
	('2f8d345a-1995-459b-a4d9-b9de95f3a0c0', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:46', 0, 0, 0, 0, 'site', 1445494666749, '2cd3982c-d469-440d-922a-75e965968f23'),
	('30880365-df71-4ec0-b9a3-a12daabaac79', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:39', 0, 0, 0, 0, 'site', 1445474379338, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('312d57de-6cb5-40ba-af68-39588e1f6cff', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:38', 0, 0, 0, 0, 'site', 1445494658977, 'd39e3ad7-a482-462f-9eb9-454b26b9db5b'),
	('31319595-22e4-4a31-92a0-18d8519002f8', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:31', 0, 0, 0, 0, 'site', 1445474371521, 'ff956575-07a9-4984-b068-c85473609687'),
	('31add873-9e67-4165-ae9b-c362a7dd5ab7', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:33', 0, 0, 0, 0, 'site', 1445474373647, 'af9a68c5-84b9-4ffd-b238-93711b0797a8'),
	('32a289a3-1a50-4997-b01e-4f400eae3f00', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:42', 0, 0, 0, 0, 'site', 1445494662162, 'db188e80-1d5d-4d3f-81b3-484206ee2107'),
	('33342a87-c24e-4db4-bf5b-f28596a327a3', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:44', 0, 0, 0, 0, 'site', 1445494664004, '8ecea40a-bd6b-4dfe-914c-aa44bc5fc201'),
	('34d9167e-3248-44e3-817d-f0fcbbeaf8c5', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:34', 0, 0, 0, 0, 'site', 1445474374013, 'ce2eaf51-93f2-49c4-b9bf-a4fed3e7f599'),
	('359ca386-eed4-4568-85f3-87ad3db0ac60', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:42', 0, 0, 0, 0, 'site', 1445494662581, 'a11d5394-3b77-4b5f-8556-b9235d6c0daf'),
	('365850b7-2b5f-490a-9400-1e3f55fac616', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:31', 0, 0, 0, 0, 'site', 1445474371679, '424e93ea-a975-48d3-b53e-3ced5f795a98'),
	('37357bd2-b58d-47b5-8a38-fff4e85f7890', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:31', 0, 0, 0, 0, 'site', 1445474371913, 'e577d00a-9afe-4f9c-a2f6-b84be30a0884'),
	('37d94caf-cad3-4e7c-8007-bde9487298ed', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:37', 0, 0, 0, 0, 'site', 1445474377280, 'b6f04f26-86b9-4a47-8fff-55f7b40d61e0'),
	('388115e7-8c3c-49c9-aa1e-6c6f5f90de81', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:42', 0, 0, 0, 0, 'site', 1445494662890, '9d12aa16-5cdf-4fb7-b7d3-81058ed8024f'),
	('39bec748-5837-4b07-aa67-4218c4235f4e', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:30', 0, 0, 0, 0, 'site', 1445474370338, '159648a9-ed33-4a26-9da0-c9aab2ff4a9f'),
	('3a510c46-1fc3-48ba-88d8-0f6457cd91b2', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:41', 0, 0, 0, 0, 'site', 1445494661022, '9cc1a792-91bf-433d-963c-d6b8b088438e'),
	('3aa7694d-b474-4a0d-9bd8-43064b629926', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:43', 0, 0, 0, 0, 'site', 1445494663054, '6661c054-ba77-4ef7-8036-66b36073c3b0'),
	('3b1feec9-0d11-4c8b-bb79-992262fe4257', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:37', 0, 0, 0, 0, 'site', 1445474377131, '38cda477-f403-4720-9d05-c90eef30c73d'),
	('3b64965a-57bc-4fc5-b9e2-0583c9021668', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:46', 0, 0, 0, 0, 'site', 1445494666872, '8e22e8c8-649b-4286-baf4-0c48e88fdfb6'),
	('3b8b85a3-987b-4eba-954d-a4bb1142f775', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:31', 0, 0, 0, 0, 'site', 1445474371954, 'e577d00a-9afe-4f9c-a2f6-b84be30a0884'),
	('3b99286f-0ab4-4e0c-a3cb-a0e2b48f9cb3', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:35', 0, 0, 0, 0, 'site', 1445474375980, '8ecea40a-bd6b-4dfe-914c-aa44bc5fc201'),
	('3e8c2180-b994-41fa-8978-f75dcbf78b8c', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:41', 0, 0, 0, 0, 'site', 1445494661411, 'd0ec8c40-9569-4d00-a389-b853748bf849'),
	('3ecdfbad-0066-4e7f-9af7-f5b4e0ee12ee', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:30', 0, 0, 0, 0, 'site', 1445474370188, '03d6cd9d-f7b3-4778-b5f3-21797330e369'),
	('3efb94a6-e109-44e2-a488-68729083f776', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:38', 0, 0, 0, 0, 'site', 1445494658830, '159648a9-ed33-4a26-9da0-c9aab2ff4a9f'),
	('40b288a1-e39b-48c0-9863-3f7336cf36bb', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:38', 0, 0, 0, 0, 'site', 1445494658727, '03d6cd9d-f7b3-4778-b5f3-21797330e369'),
	('41169e90-b933-4ba5-9a1b-935a68772a39', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:37', 0, 0, 0, 0, 'site', 1445474377238, 'b6f04f26-86b9-4a47-8fff-55f7b40d61e0'),
	('4147826f-9527-4092-aa25-6692bd100231', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:34', 0, 0, 0, 0, 'site', 1445474374262, '9d12aa16-5cdf-4fb7-b7d3-81058ed8024f'),
	('416b6c51-7cc5-4947-a826-ee7a9e52558c', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:45', 0, 0, 0, 0, 'site', 1445494665298, '9b427f92-d323-4800-b98f-151cd6486237'),
	('41f1f0e1-28c2-4d11-8524-48724cb31b35', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:44', 0, 0, 0, 0, 'site', 1445494664656, '6b54fa40-45b7-4c08-81dc-28a4e6ca5c82'),
	('41f402a0-b52f-4c0f-a651-714edc1e466c', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:30', 0, 0, 0, 0, 'site', 1445474370671, '35b400fb-2f3f-42cd-be0d-580d06b60d86'),
	('423117b6-9d72-4ebb-9eba-340f40a572de', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:33', 0, 0, 0, 0, 'site', 1445474373713, 'db188e80-1d5d-4d3f-81b3-484206ee2107'),
	('44001ad4-f69b-4ef0-beb7-a3fe90c5d8b6', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:30', 0, 0, 0, 0, 'site', 1445474370502, 'd39e3ad7-a482-462f-9eb9-454b26b9db5b'),
	('44a18960-90f7-4f78-8e3f-330c2c072d32', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:33', 0, 0, 0, 0, 'site', 1445474373395, '0ada0a09-5b0d-4b98-a166-88832accecd1'),
	('457e8fb7-d457-408a-aa10-eff70f52d909', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:41', 0, 0, 0, 0, 'site', 1445494661380, 'd0ec8c40-9569-4d00-a389-b853748bf849'),
	('45aaa497-8b1e-47e0-ad24-d364aa8b94db', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:37', 0, 0, 0, 0, 'site', 1445474377572, '8ac3c968-6a0a-4435-aaf6-6f392c57a620'),
	('475a38e9-93d3-4f0d-b85b-84de481ed399', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:41', 0, 0, 0, 0, 'site', 1445494661839, 'f78238fd-a4e5-464c-b474-4f184493296b'),
	('47c31b73-b47c-4cd0-8ffb-89ac78dd19a9', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:43', 0, 0, 0, 0, 'site', 1445494663121, '7cd4debb-1981-4c07-867c-6d7f6fc1e955'),
	('483c5d24-9fa5-47f8-9eea-6059f3554987', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:43', 0, 0, 0, 0, 'site', 1445494663554, 'd61b2487-b747-4321-a4c1-9b527bfc23f2'),
	('4867ec7d-842e-457c-a45e-bc4b96f6ce49', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:46', 0, 0, 0, 0, 'site', 1445494666165, '08f8586c-9013-48d0-81ec-362be4a97d4a'),
	('4a26e650-2bd3-440b-a1f0-9e75a4abbfcd', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:42', 0, 0, 0, 0, 'site', 1445494662956, '64050402-de11-47a8-a8e9-1ec9ed99f7e0'),
	('4a7a47a1-0340-4271-b2bf-92454e894174', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:43', 0, 0, 0, 0, 'site', 1445494663898, 'f24c449b-42f7-4628-85d7-e4012905ec49'),
	('4a90d4be-fad3-421f-8193-d4908dded2cb', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:39', 0, 0, 0, 0, 'site', 1445494659662, 'a33d3db2-c3cf-434f-9a35-8d40e0de2e8d'),
	('4aa914d8-c2b9-48b5-bbc6-3f79779add9a', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:31', 0, 0, 0, 0, 'site', 1445474371846, '51e21a1a-3757-4dde-b813-1f78dbf881fa'),
	('4bc1de80-398c-4f76-abe3-15bbf2a3a5fd', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:43', 0, 0, 0, 0, 'site', 1445494663589, '3cad172d-eaba-4748-903e-617ea2b6df8f'),
	('4be5ed82-3a0c-4d64-a761-76be179f95a3', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:45', 0, 0, 0, 0, 'site', 1445494665498, '11e05cb9-0f9f-4771-9c7c-cc9809956439'),
	('4c4dbfb7-08e1-4b97-912f-6940fd7bcae8', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:33', 0, 0, 0, 0, 'site', 1445474373946, 'ce2eaf51-93f2-49c4-b9bf-a4fed3e7f599'),
	('4d623744-d691-463a-bc98-a44979d38a47', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:34', 0, 0, 0, 0, 'site', 1445474374304, '9d12aa16-5cdf-4fb7-b7d3-81058ed8024f'),
	('4d7a22da-e562-48e5-974c-6a4d936f0380', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:32', 0, 0, 0, 0, 'site', 1445474372855, 'd0ec8c40-9569-4d00-a389-b853748bf849'),
	('4da05721-de46-4ae0-ae62-d6e96e7d1293', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:42', 0, 0, 0, 0, 'site', 1445494662454, 'ce2eaf51-93f2-49c4-b9bf-a4fed3e7f599'),
	('4e28b8bf-9e1b-4030-a8d6-bfc4293d89d7', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:46', 0, 0, 0, 0, 'site', 1445494666063, '8ac3c968-6a0a-4435-aaf6-6f392c57a620'),
	('4e50bc99-7474-4611-918c-d4c5e4ae62e5', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:46', 0, 0, 0, 0, 'site', 1445494666638, '7cbad721-91a7-460b-8916-bb19bbe5e0f2'),
	('4ec026ab-df86-4db8-9f99-dfc5f4c28d6a', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:38', 0, 0, 0, 0, 'site', 1445474378038, 'b8a2a9e1-3f6d-4ec3-954f-5aa96bb1c3c6'),
	('4ede23d8-9439-40f5-a91f-466e2320b9ab', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:46', 0, 0, 0, 0, 'site', 1445494666598, '7cbad721-91a7-460b-8916-bb19bbe5e0f2'),
	('4f2b1ce4-4ab6-452f-b75d-d5e21ad0f6a2', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:30', 0, 0, 0, 0, 'site', 1445474370813, '366a40f2-6f5b-4c66-a168-9d774cb69507'),
	('4f5a7f09-2766-4bdc-8290-2b0cc025a1b7', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:38', 0, 0, 0, 0, 'site', 1445474378538, '8e22e8c8-649b-4286-baf4-0c48e88fdfb6'),
	('4f64599d-5b1d-4409-acb2-4a9f63f35b0a', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:34', 0, 0, 0, 0, 'site', 1445474374371, '64050402-de11-47a8-a8e9-1ec9ed99f7e0'),
	('4fbf5c66-13d7-4dcb-ac5d-eb90d35b6c3d', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:46', 0, 0, 0, 0, 'site', 1445494666488, 'b8a2a9e1-3f6d-4ec3-954f-5aa96bb1c3c6'),
	('4fcba2c4-40ca-4097-b345-bcbb34aece6c', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:43', 0, 0, 0, 0, 'site', 1445494663304, 'e6c92868-3109-4e0b-8fa9-6d781c873a19'),
	('502aa110-7d1a-4159-8a32-4b9e71b4e36d', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:40', 0, 0, 0, 0, 'site', 1445494660897, '1b219275-1f31-4d72-ac8a-86f62caac828'),
	('50a86e3d-6801-4487-bbbc-bf2956c7b792', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:43', 0, 0, 0, 0, 'site', 1445494663964, '8ecea40a-bd6b-4dfe-914c-aa44bc5fc201'),
	('5165124c-b7b6-4e98-9033-201f3acbdc51', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:33', 0, 0, 0, 0, 'site', 1445474373904, 'a0b1bbc8-0fab-4997-92dc-d216898562ac'),
	('52c07028-eaaa-4131-9093-d4c4589e2332', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:45', 0, 0, 0, 0, 'site', 1445494665605, '39f7e44b-d90a-41aa-8327-895aee0ea7ec'),
	('534ff19e-6dec-4f24-9882-f0010fc74096', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:43', 0, 0, 0, 0, 'site', 1445494663687, 'ac4ccdbc-4b5b-46e5-862f-ea8e319b56a5'),
	('5354f7e4-4d88-4578-be5f-3f9c89a7af73', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:39', 0, 0, 0, 0, 'site', 1445494659247, '366a40f2-6f5b-4c66-a168-9d774cb69507'),
	('55198ffe-1747-46d6-8561-7be9d9b736d0', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:43', 0, 0, 0, 0, 'site', 1445494663362, 'e6c92868-3109-4e0b-8fa9-6d781c873a19'),
	('56deccb8-e9b4-4bc9-ae78-0840d1bc264e', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:37', 0, 0, 0, 0, 'site', 1445474377171, '38cda477-f403-4720-9d05-c90eef30c73d'),
	('570b2caf-98e5-463f-ba66-503a813d0f21', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:31', 0, 0, 0, 0, 'site', 1445474371454, 'a8b1f0b4-0afd-4d63-9d14-ef77f045c7a2'),
	('57f6eb3c-4ba7-418a-a42b-a1be97b22e84', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:46', 0, 0, 0, 0, 'site', 1445494666673, '2cd3982c-d469-440d-922a-75e965968f23'),
	('58d63483-ff40-43e4-9270-8236e51bf4d1', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:42', 0, 0, 0, 0, 'site', 1445494662723, '7b52f775-b5dc-4d73-b041-452d5c4d0805'),
	('5930b7c0-aa7f-432b-b019-ca92c4a8b20e', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:34', 0, 0, 0, 0, 'site', 1445474374045, 'a11d5394-3b77-4b5f-8556-b9235d6c0daf'),
	('59418333-7e3c-42fe-a6d0-4f0e35a7015a', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:45', 0, 0, 0, 0, 'site', 1445494665529, '11e05cb9-0f9f-4771-9c7c-cc9809956439'),
	('597314b4-7ebe-4391-9818-8103e372ecca', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:37', 0, 0, 0, 0, 'site', 1445474377922, '33db1afb-862b-44a1-b54e-2fef5b2eb003'),
	('599b8d61-bf80-4a94-a6d3-8525e6ddd95a', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:46', 0, 0, 0, 0, 'site', 1445494666129, '08f8586c-9013-48d0-81ec-362be4a97d4a'),
	('5a32f3d3-0496-4e52-a04e-1be73e5743d9', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:34', 0, 0, 0, 0, 'site', 1445474374688, '7cd4debb-1981-4c07-867c-6d7f6fc1e955'),
	('5aa48021-aba4-4197-b37c-2c521086522b', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:43', 0, 0, 0, 0, 'site', 1445494663523, 'd61b2487-b747-4321-a4c1-9b527bfc23f2'),
	('5af316a3-722e-4826-974f-d4790c4e5602', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:41', 0, 0, 0, 0, 'site', 1445494661322, 'b2240fb3-61e2-427e-8cd4-ec474380594d'),
	('5b265cf4-2c1d-48d7-a23e-9506e88057f2', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:34', 0, 0, 0, 0, 'site', 1445474374872, '7b5b131b-00cd-488a-acf3-966258070ef9'),
	('5dbf4ae7-88cb-47a2-a7cc-30b2a7135258', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:41', 0, 0, 0, 0, 'site', 1445494661606, '4f1838d1-20c0-4b43-a698-7707f62c625f'),
	('5e1c2983-3489-4f76-9cbc-6529ed65d3db', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:31', 0, 0, 0, 0, 'site', 1445474371745, 'a589064e-4bb9-4d62-a100-21f734154200'),
	('5f0ed880-8c30-476d-9f57-5f8cf3a58821', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:37', 0, 0, 0, 0, 'site', 1445474377206, '38cda477-f403-4720-9d05-c90eef30c73d'),
	('60348b9e-1e21-4f7f-9f93-9cfc22631671', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:33', 0, 0, 0, 0, 'site', 1445474373679, 'af9a68c5-84b9-4ffd-b238-93711b0797a8'),
	('60b67373-7f69-4746-beb2-8c8265e11647', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:30', 0, 0, 0, 0, 'site', 1445474370221, '03d6cd9d-f7b3-4778-b5f3-21797330e369'),
	('61ec90f1-6398-4991-89f1-589760f78f55', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:32', 0, 0, 0, 0, 'site', 1445474372113, 'ae9e7b3c-2dfe-49fb-b48a-5cc7d3644e3e'),
	('620b0f04-68a5-48ee-82ce-c97e77a2325c', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:39', 0, 0, 0, 0, 'site', 1445494659045, 'd39e3ad7-a482-462f-9eb9-454b26b9db5b'),
	('622f8aa2-a936-4e8f-afff-55612dd161d8', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:40', 0, 0, 0, 0, 'site', 1445494660937, '98b09cf5-837d-431c-b24c-b35e24e259d4'),
	('6363815d-105e-40ee-8eb5-4f06b2432c42', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:34', 0, 0, 0, 0, 'site', 1445474374612, '6661c054-ba77-4ef7-8036-66b36073c3b0'),
	('63cad3ba-a7a3-44d5-bb78-270cdeea75c6', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:38', 0, 0, 0, 0, 'site', 1445474378072, 'b8a2a9e1-3f6d-4ec3-954f-5aa96bb1c3c6'),
	('644c52a1-52a9-4a99-ac15-1282853a41d3', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:42', 0, 0, 0, 0, 'site', 1445494662986, '64050402-de11-47a8-a8e9-1ec9ed99f7e0'),
	('65cbb167-dffe-46c0-9748-c52d69625b74', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:41', 0, 0, 0, 0, 'site', 1445494661470, '4f1838d1-20c0-4b43-a698-7707f62c625f'),
	('688d33e6-a815-410c-8e46-5ae9dad07abd', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:39', 0, 0, 0, 0, 'site', 1445494659630, 'a33d3db2-c3cf-434f-9a35-8d40e0de2e8d'),
	('69bb2109-350d-4832-8fd6-7ddd7a85f566', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:41', 0, 0, 0, 0, 'site', 1445494661197, 'fdb9fe15-c954-4bf7-adf8-0893038a8b71'),
	('6a029a38-fc52-4100-81dc-bfb242774bad', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:34', 0, 0, 0, 0, 'site', 1445474374838, '7b5b131b-00cd-488a-acf3-966258070ef9'),
	('6a98316b-b0bf-4d06-8ea6-5ac1f50096c4', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:40', 0, 0, 0, 0, 'site', 1445494660489, 'a589064e-4bb9-4d62-a100-21f734154200'),
	('6b1173f8-1ba1-4e83-afd0-503b40f56d8a', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:39', 0, 0, 0, 0, 'site', 1445494659423, '47ed5424-7189-449f-8a01-58ac7613834a'),
	('6b276ac5-d705-4627-972f-cc52a1d3882d', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:40', 0, 0, 0, 0, 'site', 1445494660265, '424e93ea-a975-48d3-b53e-3ced5f795a98'),
	('6b3ae7c7-9a97-4de3-a2fc-c9b6993ba5d4', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:30', 0, 0, 0, 0, 'site', 1445474370912, '49019030-af3b-4edb-bec3-ae7e6c55a279'),
	('6b485d02-51ed-410b-bc3f-a61cb8e8c505', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:31', 0, 0, 0, 0, 'site', 1445474371287, '2990a75f-bd36-4e61-a753-4415495a95eb'),
	('6b891a91-424a-4e06-8abf-c2d39177fa95', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:30', 0, 0, 0, 0, 'site', 1445474370470, 'd39e3ad7-a482-462f-9eb9-454b26b9db5b'),
	('6c8329d9-0226-4e2a-b1da-bb23db3e3cc4', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:45', 0, 0, 0, 0, 'site', 1445494665730, '39f7e44b-d90a-41aa-8327-895aee0ea7ec'),
	('6cdd7029-50f0-4485-8026-70d7ec68926f', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:34', 0, 0, 0, 0, 'site', 1445474374337, '9d12aa16-5cdf-4fb7-b7d3-81058ed8024f'),
	('6d4e73a6-b2d1-4d63-8424-0a1a2b24c045', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:30', 0, 0, 0, 0, 'site', 1445474370778, '366a40f2-6f5b-4c66-a168-9d774cb69507'),
	('6d6a651c-72a0-4399-81ce-d525f50b49ae', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:33', 0, 0, 0, 0, 'site', 1445474373796, 'db188e80-1d5d-4d3f-81b3-484206ee2107'),
	('6d85dc0f-6f3c-4e8c-96f2-989dbd08ba7c', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:38', 0, 0, 0, 0, 'site', 1445494658756, '03d6cd9d-f7b3-4778-b5f3-21797330e369'),
	('6d9943f8-5177-42a8-919f-347ad5f7a960', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:39', 0, 0, 0, 0, 'site', 1445494659188, '35b400fb-2f3f-42cd-be0d-580d06b60d86'),
	('6e365c86-3d49-4cb8-880a-815771a92092', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:33', 0, 0, 0, 0, 'site', 1445474373571, '8df8840d-610b-409d-a2bb-e912d3c20ec5'),
	('6ecaf5f7-a789-491d-8de9-441c6091bb5c', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:37', 0, 0, 0, 0, 'site', 1445474377655, '08f8586c-9013-48d0-81ec-362be4a97d4a'),
	('700144bf-d910-4528-a697-83a0b68a9f20', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:35', 0, 0, 0, 0, 'site', 1445474375104, '8161bdd4-2f90-4079-a434-8f1d8d937049'),
	('70c58302-3dfd-41d5-82a3-4b1caa569e3e', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:40', 0, 0, 0, 0, 'site', 1445494660069, 'ff956575-07a9-4984-b068-c85473609687'),
	('71316470-76b7-459b-a1d4-014e740c83c5', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:33', 0, 0, 0, 0, 'site', 1445474373103, '5a57bdb0-33df-4879-a1a1-0a2b805b1676'),
	('713c27bb-8c6b-4345-8c3e-1e3816284533', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:43', 0, 0, 0, 0, 'site', 1445494663745, 'ac4ccdbc-4b5b-46e5-862f-ea8e319b56a5'),
	('72caa3d9-9cd7-4ffc-9e2a-94441403ee72', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:35', 0, 0, 0, 0, 'site', 1445474375454, '3cad172d-eaba-4748-903e-617ea2b6df8f'),
	('72f77515-b6f2-4df9-8ef0-6b93eeceb2a5', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:43', 0, 0, 0, 0, 'site', 1445494663839, '879b2bc4-e3d4-42de-bfc0-43774cd0482f'),
	('73be6233-511b-45b9-9bfb-6d3c8674b79b', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:30', 0, 0, 0, 0, 'site', 1445474370135, '03d6cd9d-f7b3-4778-b5f3-21797330e369'),
	('74b7a5c4-30bc-423d-a20b-7019aac2c10f', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:35', 0, 0, 0, 0, 'site', 1445474375779, 'f24c449b-42f7-4628-85d7-e4012905ec49'),
	('74cf5b72-69b5-4a8e-8ca0-7d924b6101c1', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:41', 0, 0, 0, 0, 'site', 1445494661987, '8df8840d-610b-409d-a2bb-e912d3c20ec5'),
	('75585f41-15a6-43a6-955d-92dbe7037745', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:35', 0, 0, 0, 0, 'site', 1445474375396, '3cad172d-eaba-4748-903e-617ea2b6df8f'),
	('7626e6d4-6b8d-498f-a3fc-59a08563a354', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:36', 0, 0, 0, 0, 'site', 1445474376971, '39f7e44b-d90a-41aa-8327-895aee0ea7ec'),
	('78469981-411a-48ab-af57-e5d68b669631', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:35', 0, 0, 0, 0, 'site', 1445474375029, 'e6c92868-3109-4e0b-8fa9-6d781c873a19'),
	('7866b26a-6b7f-42d3-849e-7f9cb3edb2cd', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:36', 0, 0, 0, 0, 'site', 1445474376622, '9b427f92-d323-4800-b98f-151cd6486237'),
	('78b0dac9-abbf-4e06-9470-e46c6c4c7d96', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:41', 0, 0, 0, 0, 'site', 1445494661228, 'fdb9fe15-c954-4bf7-adf8-0893038a8b71'),
	('78f161e8-4d69-4a64-a772-522ac935402b', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:40', 0, 0, 0, 0, 'site', 1445494660192, '424e93ea-a975-48d3-b53e-3ced5f795a98'),
	('792288f8-2c0c-47ed-8620-0269b7549589', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:33', 0, 0, 0, 0, 'site', 1445474373872, 'a0b1bbc8-0fab-4997-92dc-d216898562ac'),
	('796385e3-9b30-4b47-ad59-9d15caadf45e', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:39', 0, 0, 0, 0, 'site', 1445494659480, '47ed5424-7189-449f-8a01-58ac7613834a'),
	('79fa3275-6ead-4c64-8cd0-6404a42a257c', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:46', 0, 0, 0, 0, 'site', 1445494666831, '8e22e8c8-649b-4286-baf4-0c48e88fdfb6'),
	('7ae135b3-fbed-4b6b-b87e-5851b00fd4bc', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:33', 0, 0, 0, 0, 'site', 1445474373272, 'f78238fd-a4e5-464c-b474-4f184493296b'),
	('7c5941b6-e00e-47e0-8ab9-01742d5ea34d', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:30', 0, 0, 0, 0, 'site', 1445474370845, '366a40f2-6f5b-4c66-a168-9d774cb69507'),
	('7c8caae6-72cd-4b3e-9545-46fff4d064d6', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:35', 0, 0, 0, 0, 'site', 1445474375822, 'f24c449b-42f7-4628-85d7-e4012905ec49'),
	('7d4b1b42-7e11-4539-bfe6-ce5016366d34', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:44', 0, 0, 0, 0, 'site', 1445494664040, '8ecea40a-bd6b-4dfe-914c-aa44bc5fc201'),
	('7d975f0f-bdf3-414c-8d36-e13f1ff5675c', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:39', 0, 0, 0, 0, 'site', 1445494659161, '35b400fb-2f3f-42cd-be0d-580d06b60d86'),
	('7e393dd7-28d8-4438-83c9-b58d4fd4f3c0', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:45', 0, 0, 0, 0, 'site', 1445494665214, '9b427f92-d323-4800-b98f-151cd6486237'),
	('7ec4c3f4-56b7-4135-a659-b525e68fa6ca', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:43', 0, 0, 0, 0, 'site', 1445494663620, '3cad172d-eaba-4748-903e-617ea2b6df8f'),
	('7ef2a916-2b9c-46e8-a37c-b4df07e0ce9e', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:33', 0, 0, 0, 0, 'site', 1445474373237, 'd5a06e48-e12f-4146-b07e-7f7e85b1f1c0'),
	('7f751d79-ea7f-4ad4-b6f5-92c334b0eabb', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:35', 0, 0, 0, 0, 'site', 1445474375905, 'f24c449b-42f7-4628-85d7-e4012905ec49'),
	('7faee745-0a0f-4622-a5d7-ad5dda1f471f', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:31', 0, 0, 0, 0, 'site', 1445474371387, 'a8b1f0b4-0afd-4d63-9d14-ef77f045c7a2'),
	('7fc77ebd-c3c0-436f-87cb-efd1604d4179', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:41', 0, 0, 0, 0, 'site', 1445494661897, '0ada0a09-5b0d-4b98-a166-88832accecd1'),
	('801d1373-7e53-493a-b22a-ab705f1d9cd8', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:33', 0, 0, 0, 0, 'site', 1445474373754, 'db188e80-1d5d-4d3f-81b3-484206ee2107'),
	('82954be0-3ffd-4dcd-988e-8067c43b78d2', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:47', 0, 0, 0, 0, 'site', 1445494667332, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('83c4c151-7531-4cd5-be83-aec29ac78b23', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:39', 0, 0, 0, 0, 'site', 1445494659603, '4b18167e-ecad-489e-87b6-44c90d04c9a4'),
	('8435c934-6b07-4277-80d1-c73ee6a50c64', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:36', 0, 0, 0, 0, 'site', 1445474376380, 'f27e2913-3bcd-48f6-bc2d-ccbca5ccb840'),
	('847dcec4-a33b-4d54-add2-e16728fdf636', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:43', 0, 0, 0, 0, 'site', 1445494663929, 'f24c449b-42f7-4628-85d7-e4012905ec49'),
	('84a69d8d-937e-48a4-a0ee-f5811ca2986f', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:46', 0, 0, 0, 0, 'site', 1445494666263, 'dbe283e0-6fb1-4b13-a57a-0517a59f5474'),
	('87205f28-b6d0-4205-aef3-68754749f3ad', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:45', 0, 0, 0, 0, 'site', 1445494665463, 'b783233e-d891-4651-8df3-aaae13ab0b69'),
	('887b649a-b534-471a-89ea-6d3ae466665f', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:35', 0, 0, 0, 0, 'site', 1445474375139, '8161bdd4-2f90-4079-a434-8f1d8d937049'),
	('8997f9b0-b25e-4dad-8c0b-40c0c3e588f2', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:30', 0, 0, 0, 0, 'site', 1445474370704, '35b400fb-2f3f-42cd-be0d-580d06b60d86'),
	('89bb6e80-c658-464c-8ce0-49aa95225d0a', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:39', 0, 0, 0, 0, 'site', 1445494659453, '47ed5424-7189-449f-8a01-58ac7613834a'),
	('8a33a75a-a5f3-49b2-9210-1ae45f61b279', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:40', 0, 0, 0, 0, 'site', 1445494660862, '1b219275-1f31-4d72-ac8a-86f62caac828'),
	('8a890fb4-dbc7-400c-86ba-99289b33b4f0', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:32', 0, 0, 0, 0, 'site', 1445474372412, '9cc1a792-91bf-433d-963c-d6b8b088438e'),
	('8ac1f5d5-88f2-439f-98f0-9d3ee12db446', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:42', 0, 0, 0, 0, 'site', 1445494662354, 'a0b1bbc8-0fab-4997-92dc-d216898562ac'),
	('8aeaff99-4ae1-487f-9b3c-60dd1934ba80', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:44', 0, 0, 0, 0, 'site', 1445494664887, '079f46b3-b738-4afb-8524-f624f2e96766'),
	('8b84d26c-2aa5-4a8c-9959-7030a7b363de', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:42', 0, 0, 0, 0, 'site', 1445494662861, '9d12aa16-5cdf-4fb7-b7d3-81058ed8024f'),
	('8be3d952-fdc2-402c-861d-a35671e9b664', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:39', 0, 0, 0, 0, 'site', 1445474379123, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('8d1193ca-66dc-4e10-90d1-1a1d08a85f48', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:40', 0, 0, 0, 0, 'site', 1445494660648, 'e577d00a-9afe-4f9c-a2f6-b84be30a0884'),
	('8dad5913-8670-4a1f-8cfa-215af5471429', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:31', 0, 0, 0, 0, 'site', 1445474371879, '51e21a1a-3757-4dde-b813-1f78dbf881fa'),
	('8db94514-3cdb-4fc9-80ef-1f91ed17ae20', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:34', 0, 0, 0, 0, 'site', 1445474374155, '7b52f775-b5dc-4d73-b041-452d5c4d0805'),
	('8e084463-3013-4748-af32-fd68eec73c73', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:33', 0, 0, 0, 0, 'site', 1445474373537, '8df8840d-610b-409d-a2bb-e912d3c20ec5'),
	('90253f35-1ba9-420a-92d7-5ec28a69f15b', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:38', 0, 0, 0, 0, 'site', 1445474378389, '2cd3982c-d469-440d-922a-75e965968f23'),
	('903fcc93-48e3-43fa-98a6-1dd7b3da6631', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:43', 0, 0, 0, 0, 'site', 1445494663245, '7b5b131b-00cd-488a-acf3-966258070ef9'),
	('90a8e074-b038-4d4b-912e-941facdab975', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:34', 0, 0, 0, 0, 'site', 1445474374195, '7b52f775-b5dc-4d73-b041-452d5c4d0805'),
	('90aa06bc-72f6-4a8c-86c0-2bd0a4f62120', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:34', 0, 0, 0, 0, 'site', 1445474374572, '6661c054-ba77-4ef7-8036-66b36073c3b0'),
	('90b8384f-cb6d-400e-8a98-7ea29e6eaab9', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:36', 0, 0, 0, 0, 'site', 1445474376447, 'f27e2913-3bcd-48f6-bc2d-ccbca5ccb840'),
	('9123ad7e-7d51-4108-afb2-6f7c10afa5ca', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:35', 0, 0, 0, 0, 'site', 1445474375063, '8161bdd4-2f90-4079-a434-8f1d8d937049'),
	('913652eb-7a11-4c17-8196-dafddc570fce', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:43', 0, 0, 0, 0, 'site', 1445494663390, '8161bdd4-2f90-4079-a434-8f1d8d937049'),
	('91b9c8dc-a421-4a26-9068-de3fc71f1d39', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:33', 0, 0, 0, 0, 'site', 1445474373138, '5a57bdb0-33df-4879-a1a1-0a2b805b1676'),
	('91ec8ebc-079e-4631-a7ec-ffb4f02f54d5', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:39', 0, 0, 0, 0, 'site', 1445474379056, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('92ab39b5-1015-433a-9246-6dae051f4439', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:33', 0, 0, 0, 0, 'site', 1445474373838, 'a0b1bbc8-0fab-4997-92dc-d216898562ac'),
	('92c6591c-6891-4895-bdc2-3328a3be14aa', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:46', 0, 0, 0, 0, 'site', 1445494666523, 'b8a2a9e1-3f6d-4ec3-954f-5aa96bb1c3c6'),
	('9335454b-e1e7-42fe-91f4-a891c389e130', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:36', 0, 0, 0, 0, 'site', 1445474376196, '6b54fa40-45b7-4c08-81dc-28a4e6ca5c82'),
	('936f61b7-e2ea-4a7f-9cd4-1120064cab7f', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:37', 0, 0, 0, 0, 'site', 1445474377014, '39f7e44b-d90a-41aa-8327-895aee0ea7ec'),
	('93a6637a-2387-4ea1-bb80-02e2daf0cb01', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:37', 0, 0, 0, 0, 'site', 1445474377321, 'b6f04f26-86b9-4a47-8fff-55f7b40d61e0'),
	('945b48f0-82b4-4831-a9c1-d3dc7abf1698', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:38', 0, 0, 0, 0, 'site', 1445474378622, '22b70bc4-6dbb-4015-953a-3c092eb9798c'),
	('945bbfcd-f27a-4ca3-a7d8-3bd57fa91ea9', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:45', 0, 0, 0, 0, 'site', 1445494665171, '10cc580a-7f73-41b0-8f8b-d264bb3b892f'),
	('94a74859-1552-430e-978b-ae62022b9b63', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:31', 0, 0, 0, 0, 'site', 1445474371603, '424e93ea-a975-48d3-b53e-3ced5f795a98'),
	('9511a978-4d8e-4e3a-9d68-c934cb77e1ab', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:44', 0, 0, 0, 0, 'site', 1445494664474, '6b54fa40-45b7-4c08-81dc-28a4e6ca5c82'),
	('953fe687-c08a-40d2-b3ef-7ba0140b0439', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:42', 0, 0, 0, 0, 'site', 1445494662656, 'a11d5394-3b77-4b5f-8556-b9235d6c0daf'),
	('95a6ebc5-dcf9-4e86-b72a-51e4de2cb8af', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:30', 0, 0, 0, 0, 'site', 1445474370404, '4738dbcb-bdc9-496c-ab7f-cd1a4d4b5a8b'),
	('96c393af-485b-4a77-ada3-fbaf0bfb2009', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:45', 0, 0, 0, 0, 'site', 1445494665923, 'b6f04f26-86b9-4a47-8fff-55f7b40d61e0'),
	('96f2a27c-ac9a-41c5-aea7-985c5e47f58c', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:30', 0, 0, 0, 0, 'site', 1445474370572, '337d7c3d-27ba-4d39-92c7-39f72fcf2db4'),
	('97549777-6e21-4925-b8ac-3e37d1b57391', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:42', 0, 0, 0, 0, 'site', 1445494662073, 'af9a68c5-84b9-4ffd-b238-93711b0797a8'),
	('97ef5b9e-f424-4861-a75d-8360b63e5552', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:31', 0, 0, 0, 0, 'site', 1445474371221, 'a33d3db2-c3cf-434f-9a35-8d40e0de2e8d'),
	('9818f071-6e50-475e-afcd-af211bae286c', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:44', 0, 0, 0, 0, 'site', 1445494664229, 'f0af133d-e253-490d-859e-481c072393ee'),
	('982013ff-94aa-4c25-b055-06592c5e76c4', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:32', 0, 0, 0, 0, 'site', 1445474372646, 'fdb9fe15-c954-4bf7-adf8-0893038a8b71'),
	('9a3efdb6-bfdc-479e-bcb3-410070c1454f', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:39', 0, 0, 0, 0, 'site', 1445494659778, '2990a75f-bd36-4e61-a753-4415495a95eb'),
	('9b3e133d-0a1f-4f3e-bfaa-9b2766333955', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:39', 0, 0, 0, 0, 'site', 1445494659831, 'a8b1f0b4-0afd-4d63-9d14-ef77f045c7a2'),
	('9b8b15c7-3f22-41ed-bd3f-d94994e21200', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:31', 0, 0, 0, 0, 'site', 1445474371646, '424e93ea-a975-48d3-b53e-3ced5f795a98'),
	('9bb8201d-780b-45ac-b90f-08a9ffb72f26', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:37', 0, 0, 0, 0, 'site', 1445474377996, '33db1afb-862b-44a1-b54e-2fef5b2eb003'),
	('9c5a81e9-78aa-414f-a3b6-e0dac4033792', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:46', 0, 0, 0, 0, 'site', 1445494666231, 'dbe283e0-6fb1-4b13-a57a-0517a59f5474'),
	('9cd22fff-b9b0-40aa-8f02-d2a31336df40', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:38', 0, 0, 0, 0, 'site', 1445494658794, '159648a9-ed33-4a26-9da0-c9aab2ff4a9f'),
	('9cf4352b-d247-43e9-8866-a8de65495d0e', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:44', 0, 0, 0, 0, 'site', 1445494664771, '079f46b3-b738-4afb-8524-f624f2e96766'),
	('9e842ab9-ba6c-4db3-be81-957e0bd88a34', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:40', 0, 0, 0, 0, 'site', 1445494660039, 'ff956575-07a9-4984-b068-c85473609687'),
	('9ee4b424-bb20-4c52-ab34-ce917dabad6d', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:32', 0, 0, 0, 0, 'site', 1445474372313, '98b09cf5-837d-431c-b24c-b35e24e259d4'),
	('9f04e137-1bfb-4d0c-b07a-ae729284cf57', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:39', 0, 0, 0, 0, 'site', 1445494659356, '49019030-af3b-4edb-bec3-ae7e6c55a279'),
	('9f73605e-9a33-4cdd-8047-a46535246f0f', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:32', 0, 0, 0, 0, 'site', 1445474372212, '1b219275-1f31-4d72-ac8a-86f62caac828'),
	('a0a290d3-e2e5-4603-8a0b-b89cb1807600', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:46', 0, 0, 0, 0, 'site', 1445494666090, '8ac3c968-6a0a-4435-aaf6-6f392c57a620'),
	('a0f42d69-6b7b-4e04-a5c9-e362cec35d51', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:46', 0, 0, 0, 0, 'site', 1445494666023, '8ac3c968-6a0a-4435-aaf6-6f392c57a620'),
	('a159d919-9eb7-4b6c-b4fd-15b8788997ef', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:33', 0, 0, 0, 0, 'site', 1445474373037, '4f1838d1-20c0-4b43-a698-7707f62c625f'),
	('a1f7e30a-98a0-45a8-acf7-17ffe1c9be4e', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:36', 0, 0, 0, 0, 'site', 1445474376888, '11e05cb9-0f9f-4771-9c7c-cc9809956439'),
	('a288f4aa-565f-4b08-9877-66891c90263d', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:40', 0, 0, 0, 0, 'site', 1445494660764, 'ae9e7b3c-2dfe-49fb-b48a-5cc7d3644e3e'),
	('a3a56b87-24e7-4245-abf6-713e3664258e', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:37', 0, 0, 0, 0, 'site', 1445474377614, '8ac3c968-6a0a-4435-aaf6-6f392c57a620'),
	('a4056bcd-d0fa-4530-b75d-1f0f7603335f', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:43', 0, 0, 0, 0, 'site', 1445494663088, '6661c054-ba77-4ef7-8036-66b36073c3b0'),
	('a4dbd501-36ab-4f84-a956-5ae756012ffd', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:36', 0, 0, 0, 0, 'site', 1445474376022, '8ecea40a-bd6b-4dfe-914c-aa44bc5fc201'),
	('a4f6704c-db98-47c1-8ac1-2b43f798ecbd', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:33', 0, 0, 0, 0, 'site', 1445474373470, '0ada0a09-5b0d-4b98-a166-88832accecd1'),
	('a5fb64ff-02f6-4c11-8611-dadef5bee16b', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:34', 0, 0, 0, 0, 'site', 1445474374763, '7cd4debb-1981-4c07-867c-6d7f6fc1e955'),
	('a635f83e-b0d4-451d-80c1-70d6998da0c4', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:32', 0, 0, 0, 0, 'site', 1445474372345, '98b09cf5-837d-431c-b24c-b35e24e259d4'),
	('a6e5162e-3dcd-4cae-aa77-169bc5c90e7a', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:42', 0, 0, 0, 0, 'site', 1445494662696, '7b52f775-b5dc-4d73-b041-452d5c4d0805'),
	('a81fe8a1-29af-470c-bf37-58e0699da7d8', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:46', 0, 0, 0, 0, 'site', 1445494666563, '7cbad721-91a7-460b-8916-bb19bbe5e0f2'),
	('a8e27870-e691-4f3f-8f96-f98fa818902c', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:42', 0, 0, 0, 0, 'site', 1445494662214, 'db188e80-1d5d-4d3f-81b3-484206ee2107'),
	('aaf9a502-022c-4f9b-975d-6b9a1a42851a', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:36', 0, 0, 0, 0, 'site', 1445474376547, '10cc580a-7f73-41b0-8f8b-d264bb3b892f'),
	('ac52770b-176a-47ce-9f68-06bad526595a', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:41', 0, 0, 0, 0, 'site', 1445494661664, '5a57bdb0-33df-4879-a1a1-0a2b805b1676'),
	('ac7820bb-b311-4035-87ab-c5b460e15068', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:31', 0, 0, 0, 0, 'site', 1445474371571, 'ff956575-07a9-4984-b068-c85473609687'),
	('ada59480-3bad-464e-bfbd-36977bf88fa9', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:41', 0, 0, 0, 0, 'site', 1445494661139, 'd3759e96-1aec-4d5f-8bd1-f1b9e7d630ad'),
	('aef8f94a-fb99-4a21-991a-d71ac000cf53', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:32', 0, 0, 0, 0, 'site', 1445474372279, '98b09cf5-837d-431c-b24c-b35e24e259d4'),
	('afddcb38-d760-4a11-9c26-1e7be6c100a6', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:39', 0, 0, 0, 0, 'site', 1445494659748, '2990a75f-bd36-4e61-a753-4415495a95eb'),
	('affe0e9f-9deb-4a24-a1d8-e7834272bae8', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:38', 0, 0, 0, 0, 'site', 1445474378430, '8e22e8c8-649b-4286-baf4-0c48e88fdfb6'),
	('b180a6fc-2c1a-4438-9b81-2d1d8e2ce247', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:32', 0, 0, 0, 0, 'site', 1445474372247, '1b219275-1f31-4d72-ac8a-86f62caac828'),
	('b1f5d326-782b-4b54-b8b2-93d634c14436', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:40', 0, 0, 0, 0, 'site', 1445494660589, '51e21a1a-3757-4dde-b813-1f78dbf881fa'),
	('b3453e43-e9c7-46d4-bab5-ded94fb823c8', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:46', 0, 0, 0, 0, 'site', 1445494666713, '2cd3982c-d469-440d-922a-75e965968f23'),
	('b3996893-049c-401d-b71f-92c4231be7dd', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:30', 0, 0, 0, 0, 'site', 1445474370254, '159648a9-ed33-4a26-9da0-c9aab2ff4a9f'),
	('b3e169dd-602f-4383-b0e0-a4c4e43e1246', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:39', 0, 0, 0, 0, 'site', 1445494659327, '49019030-af3b-4edb-bec3-ae7e6c55a279'),
	('b3ed24c1-8765-472c-9dd0-a87dfbd54d66', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:46', 0, 0, 0, 0, 'site', 1445494666413, '33db1afb-862b-44a1-b54e-2fef5b2eb003'),
	('b5749bc6-eb3a-411f-af54-64952debe4ec', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:38', 0, 0, 0, 0, 'site', 1445474378239, '7cbad721-91a7-460b-8916-bb19bbe5e0f2'),
	('b5de148e-f871-41ae-8605-65bac8815d25', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:34', 0, 0, 0, 0, 'site', 1445474374080, 'a11d5394-3b77-4b5f-8556-b9235d6c0daf'),
	('b69e9298-c65e-461d-b12c-583b226ece9f', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:45', 0, 0, 0, 0, 'site', 1445494665804, '38cda477-f403-4720-9d05-c90eef30c73d'),
	('b7095254-324e-44d3-a824-2c1f78aa0a8f', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:42', 0, 0, 0, 0, 'site', 1445494662381, 'a0b1bbc8-0fab-4997-92dc-d216898562ac'),
	('b7d32c55-8f15-414b-92d6-74cbcd3ecf26', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:41', 0, 0, 0, 0, 'site', 1445494661439, 'd0ec8c40-9569-4d00-a389-b853748bf849'),
	('b8adef33-d3ce-4cd5-bfd9-97b41dcffb6e', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:33', 0, 0, 0, 0, 'site', 1445474373430, '0ada0a09-5b0d-4b98-a166-88832accecd1'),
	('b8bf2633-8cc4-4446-92da-7a5e44156037', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:32', 0, 0, 0, 0, 'site', 1445474372505, '9cc1a792-91bf-433d-963c-d6b8b088438e'),
	('b93da8c8-ae6a-4999-a003-208100b10e99', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:46', 0, 0, 0, 0, 'site', 1445494666298, 'dbe283e0-6fb1-4b13-a57a-0517a59f5474'),
	('b96855c0-1821-4f8f-a135-1bea04617adc', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:37', 0, 0, 0, 0, 'site', 1445474377697, '08f8586c-9013-48d0-81ec-362be4a97d4a'),
	('b9742054-9386-4836-8148-286d2809c39a', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:34', 0, 0, 0, 0, 'site', 1445474374647, '7cd4debb-1981-4c07-867c-6d7f6fc1e955'),
	('b9b4a6b2-95e7-43b7-9d89-ead6ce63729d', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:38', 0, 0, 0, 0, 'site', 1445474378272, '7cbad721-91a7-460b-8916-bb19bbe5e0f2'),
	('b9ea902c-f9cf-45f2-9aac-5f7fa364e1c2', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:42', 0, 0, 0, 0, 'site', 1445494662130, 'af9a68c5-84b9-4ffd-b238-93711b0797a8'),
	('ba341858-90ca-4a34-a6ff-479fe8339ff8', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:32', 0, 0, 0, 0, 'site', 1445474372970, '4f1838d1-20c0-4b43-a698-7707f62c625f'),
	('bae8e231-b3f6-4e17-882a-1f0501ee00b6', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:32', 0, 0, 0, 0, 'site', 1445474372612, 'd3759e96-1aec-4d5f-8bd1-f1b9e7d630ad'),
	('bb5a65d7-7a63-4658-8969-b0bc409d8209', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:35', 0, 0, 0, 0, 'site', 1445474375179, 'd61b2487-b747-4321-a4c1-9b527bfc23f2'),
	('bc3f4741-d7cd-44a4-8145-ef8ceee43bfe', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:46', 0, 0, 0, 0, 'site', 1445494666907, '22b70bc4-6dbb-4015-953a-3c092eb9798c'),
	('bcdbbe6f-a28d-4b09-99a7-bd9102d2df26', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:45', 0, 0, 0, 0, 'site', 1445494665565, '11e05cb9-0f9f-4771-9c7c-cc9809956439'),
	('be1a4300-484d-4c2e-8f61-1bbe9972e1a6', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:32', 0, 0, 0, 0, 'site', 1445474372821, 'b2240fb3-61e2-427e-8cd4-ec474380594d'),
	('bf90522c-cfb0-4bc2-98b2-38aeab465721', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:39', 0, 0, 0, 0, 'site', 1445494659072, '337d7c3d-27ba-4d39-92c7-39f72fcf2db4'),
	('c04f4005-c4d7-40c9-ac08-eb2c2c800443', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:39', 0, 0, 0, 0, 'site', 1445494659297, '366a40f2-6f5b-4c66-a168-9d774cb69507'),
	('c0549cc4-ee07-44d1-81c0-e167a4d2a5bb', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:39', 0, 0, 0, 0, 'site', 1445494659547, '4b18167e-ecad-489e-87b6-44c90d04c9a4'),
	('c0fda67b-ce51-4d35-937c-2a78d3f1b1f4', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:36', 0, 0, 0, 0, 'site', 1445474376654, '9b427f92-d323-4800-b98f-151cd6486237'),
	('c2014d9f-f4b8-41af-a579-39f66028d80e', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:42', 0, 0, 0, 0, 'site', 1445494662554, 'ce2eaf51-93f2-49c4-b9bf-a4fed3e7f599'),
	('c3950bad-767e-4549-b7b7-c44ceb6bb844', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:42', 0, 0, 0, 0, 'site', 1445494662803, '7b52f775-b5dc-4d73-b041-452d5c4d0805'),
	('c57fed91-ca55-4de2-85c4-d7897ed92929', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:41', 0, 0, 0, 0, 'site', 1445494661256, 'fdb9fe15-c954-4bf7-adf8-0893038a8b71'),
	('c5bc285a-9a51-4659-827f-c9821b468225', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:40', 0, 0, 0, 0, 'site', 1445494660358, 'a589064e-4bb9-4d62-a100-21f734154200'),
	('c5c00873-2a69-4999-ad28-926b1f2a2570', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:37', 0, 0, 0, 0, 'site', 1445474377764, 'dbe283e0-6fb1-4b13-a57a-0517a59f5474'),
	('c679096e-aa1c-42d2-84b7-e90cfe00990d', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:36', 0, 0, 0, 0, 'site', 1445474376306, '079f46b3-b738-4afb-8524-f624f2e96766'),
	('c6fb8fc9-8249-4220-be10-dbb158809f1e', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:36', 0, 0, 0, 0, 'site', 1445474376055, 'f0af133d-e253-490d-859e-481c072393ee'),
	('c7292ed5-b919-4330-9909-55cc38b44fc3', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:34', 0, 0, 0, 0, 'site', 1445474374447, '64050402-de11-47a8-a8e9-1ec9ed99f7e0'),
	('c75c9fe9-d97f-4c2e-95f4-4e834babc7c8', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:47', 0, 0, 0, 0, 'site', 1445494667082, 'd6f5537b-4534-4ba3-a351-f6be97537d2e'),
	('c7783f74-00b8-4cef-bbde-32b17345b087', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:43', 0, 0, 0, 0, 'site', 1445494663648, '3cad172d-eaba-4748-903e-617ea2b6df8f'),
	('c7c745c7-0da0-4e05-bd1a-3b094b39b574', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:43', 0, 0, 0, 0, 'site', 1445494663214, '7b5b131b-00cd-488a-acf3-966258070ef9'),
	('c8833c83-ad91-4ad3-9efc-f35bc8fe3e7f', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:42', 0, 0, 0, 0, 'site', 1445494662620, 'a11d5394-3b77-4b5f-8556-b9235d6c0daf'),
	('c94d8fe9-8f67-4363-9895-623079d8d259', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:45', 0, 0, 0, 0, 'site', 1445494665773, '38cda477-f403-4720-9d05-c90eef30c73d'),
	('c95abe87-b642-4185-97de-41d2e488f4a6', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:31', 0, 0, 0, 0, 'site', 1445474371713, 'a589064e-4bb9-4d62-a100-21f734154200'),
	('c9a6f3cc-d548-4cd5-8e38-f23af6456cd7', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:45', 0, 0, 0, 0, 'site', 1445494665840, '38cda477-f403-4720-9d05-c90eef30c73d'),
	('c9fc8c2b-ec28-4ff9-867b-ac93ad62aeb7', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:42', 0, 0, 0, 0, 'site', 1445494662014, '8df8840d-610b-409d-a2bb-e912d3c20ec5'),
	('caab353e-a67b-4e90-a92c-c8c48515c5c6', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:34', 0, 0, 0, 0, 'site', 1445474374796, '7b5b131b-00cd-488a-acf3-966258070ef9'),
	('cb2e0a7d-07ee-4374-8be6-b3c11490b756', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:39', 0, 0, 0, 0, 'site', 1445494659969, 'ff956575-07a9-4984-b068-c85473609687'),
	('cb808a4a-a226-4974-8f46-38ae7b20f6a5', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:43', 0, 0, 0, 0, 'site', 1445494663870, 'f24c449b-42f7-4628-85d7-e4012905ec49'),
	('cbb39290-a53c-4a18-b476-0e7087623a29', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:38', 0, 0, 0, 0, 'site', 1445474378473, '8e22e8c8-649b-4286-baf4-0c48e88fdfb6'),
	('ccac13fe-54d2-42d3-a115-08b3ef788046', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:41', 0, 0, 0, 0, 'site', 1445494661520, '4f1838d1-20c0-4b43-a698-7707f62c625f'),
	('cd43eef2-cd5c-48eb-bf1e-8c2c39a63c4c', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:39', 0, 0, 0, 0, 'site', 1445494659278, '366a40f2-6f5b-4c66-a168-9d774cb69507'),
	('cd7eb446-99ad-4486-a7c6-2e7d87bca276', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:41', 0, 0, 0, 0, 'site', 1445494661053, '9cc1a792-91bf-433d-963c-d6b8b088438e'),
	('cd97b3a3-59f6-4300-b2ba-87bc18ab6ab5', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:43', 0, 0, 0, 0, 'site', 1445494663421, '8161bdd4-2f90-4079-a434-8f1d8d937049'),
	('ce9a4637-182b-48c6-95d5-626ad9d1b047', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:31', 0, 0, 0, 0, 'site', 1445474371080, '4b18167e-ecad-489e-87b6-44c90d04c9a4'),
	('cef6dd5b-003a-4a1e-8c6f-c84016ec35ee', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:41', 0, 0, 0, 0, 'site', 1445494661637, '5a57bdb0-33df-4879-a1a1-0a2b805b1676'),
	('cfd54027-5d8f-4942-acf7-01b25933f8ad', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:36', 0, 0, 0, 0, 'site', 1445474376847, '11e05cb9-0f9f-4771-9c7c-cc9809956439'),
	('d0f4b97d-f8e6-469b-a2be-1196e315c4f6', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:42', 0, 0, 0, 0, 'site', 1445494662287, 'db188e80-1d5d-4d3f-81b3-484206ee2107'),
	('d1286869-d2cf-47ef-96c0-702b8af9ac70', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:32', 0, 0, 0, 0, 'site', 1445474372896, 'd0ec8c40-9569-4d00-a389-b853748bf849'),
	('d16d5936-4a62-4529-824b-44129952d157', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:32', 0, 0, 0, 0, 'site', 1445474372938, 'd0ec8c40-9569-4d00-a389-b853748bf849'),
	('d2644a85-6057-4a47-ad31-14c4c3e7d84c', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:45', 0, 0, 0, 0, 'site', 1445494665057, 'f27e2913-3bcd-48f6-bc2d-ccbca5ccb840'),
	('d2b30331-90d0-41ab-bfdd-387e3e6ae90e', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:31', 0, 0, 0, 0, 'site', 1445474371154, '4b18167e-ecad-489e-87b6-44c90d04c9a4'),
	('d2d3e08e-a381-4e7a-a2a8-fa57fbc7ba2b', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:38', 0, 0, 0, 0, 'site', 1445474378863, 'd6f5537b-4534-4ba3-a351-f6be97537d2e'),
	('d3251510-cc5f-4f8b-adb5-5755676d81e3', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:30', 0, 0, 0, 0, 'site', 1445474370745, '35b400fb-2f3f-42cd-be0d-580d06b60d86'),
	('d4b29e62-fad1-4e49-8bb0-3a225aa254dc', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:37', 0, 0, 0, 0, 'site', 1445474377956, '33db1afb-862b-44a1-b54e-2fef5b2eb003'),
	('d798500d-d1dd-461a-b011-1cf8a3b47be8', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:31', 0, 0, 0, 0, 'site', 1445474371321, '2990a75f-bd36-4e61-a753-4415495a95eb'),
	('d7ad67ae-a7da-4880-9b64-46f9fa8ea035', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:41', 0, 0, 0, 0, 'site', 1445494661723, 'd5a06e48-e12f-4146-b07e-7f7e85b1f1c0'),
	('d82ce263-15ab-420e-b2ca-603715de428a', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:39', 0, 0, 0, 0, 'site', 1445494659914, 'a8b1f0b4-0afd-4d63-9d14-ef77f045c7a2'),
	('d8b83d96-be23-4150-b8b1-fcea5f8f6836', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:30', 0, 0, 0, 0, 'site', 1445474370287, '159648a9-ed33-4a26-9da0-c9aab2ff4a9f'),
	('d97acf90-5c1a-45b3-9651-2c692fafded9', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:41', 0, 0, 0, 0, 'site', 1445494661929, '0ada0a09-5b0d-4b98-a166-88832accecd1'),
	('d98128c5-cc8f-409e-9be6-ed8d75b21a98', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:43', 0, 0, 0, 0, 'site', 1445494663272, '7b5b131b-00cd-488a-acf3-966258070ef9'),
	('d9c17d17-39df-47bf-8acb-3b5b193d257c', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:39', 0, 0, 0, 0, 'site', 1445494659720, '2990a75f-bd36-4e61-a753-4415495a95eb'),
	('da94ebe2-91b6-4319-a043-d598084e468a', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:34', 0, 0, 0, 0, 'site', 1445474374413, '64050402-de11-47a8-a8e9-1ec9ed99f7e0'),
	('dc60dce8-8ce4-4377-a43e-4b1ac32d62d5', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:30', 0, 0, 0, 0, 'site', 1445474370978, '47ed5424-7189-449f-8a01-58ac7613834a'),
	('dc81a5cd-d3a5-4e8e-9255-87cfef48198b', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:31', 0, 0, 0, 0, 'site', 1445474371112, '4b18167e-ecad-489e-87b6-44c90d04c9a4'),
	('dca15191-b9dd-481e-9c05-4bd5bf7e7958', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:46', 0, 0, 0, 0, 'site', 1445494666788, '8e22e8c8-649b-4286-baf4-0c48e88fdfb6'),
	('dd43b32b-eeb5-4a8b-9e64-1f12f3ef0e25', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:45', 0, 0, 0, 0, 'site', 1445494665423, 'b783233e-d891-4651-8df3-aaae13ab0b69'),
	('ddb34202-53fd-4c78-bfdb-e92d6a26eacd', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:40', 0, 0, 0, 0, 'site', 1445494660795, 'ae9e7b3c-2dfe-49fb-b48a-5cc7d3644e3e'),
	('de2c72ea-cc85-4090-b480-ab8b07c08ce4', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:37', 0, 0, 0, 0, 'site', 1445474377805, 'dbe283e0-6fb1-4b13-a57a-0517a59f5474'),
	('dee3f146-1456-4e87-bf30-092ac6f79fc8', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:38', 0, 0, 0, 0, 'site', 1445494658889, '4738dbcb-bdc9-496c-ab7f-cd1a4d4b5a8b'),
	('df6970ee-cb25-4f73-a310-2aa2c1c8a96a', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:39', 0, 0, 0, 0, 'site', 1445494659689, 'a33d3db2-c3cf-434f-9a35-8d40e0de2e8d'),
	('df86766c-481a-442f-b179-888630f53967', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:38', 0, 0, 0, 0, 'site', 1445474378705, 'd6f5537b-4534-4ba3-a351-f6be97537d2e'),
	('dfdb992b-694d-4612-9398-98f52ef39471', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:31', 0, 0, 0, 0, 'site', 1445474371355, '2990a75f-bd36-4e61-a753-4415495a95eb'),
	('e02509d9-bd89-40dc-811a-bdec93643f2f', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:46', 0, 0, 0, 0, 'site', 1445494666990, '22b70bc4-6dbb-4015-953a-3c092eb9798c'),
	('e04c2c36-a078-4b34-b394-a41cded490f1', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:44', 0, 0, 0, 0, 'site', 1445494664848, '079f46b3-b738-4afb-8524-f624f2e96766'),
	('e0ede425-16b4-4916-8255-aac9da269a46', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:43', 0, 0, 0, 0, 'site', 1445494663496, 'd61b2487-b747-4321-a4c1-9b527bfc23f2'),
	('e1156135-e298-447c-a4d4-1d933d30a81f', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:40', 0, 0, 0, 0, 'site', 1445494660620, '51e21a1a-3757-4dde-b813-1f78dbf881fa'),
	('e1694935-d1f8-45e4-93da-4a26d74efa25', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:41', 0, 0, 0, 0, 'site', 1445494661354, 'b2240fb3-61e2-427e-8cd4-ec474380594d'),
	('e2219401-8b2e-4558-9252-348b5ee26b98', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:33', 0, 0, 0, 0, 'site', 1445474373205, 'd5a06e48-e12f-4146-b07e-7f7e85b1f1c0'),
	('e332c7f6-3b6c-49bd-b38a-56b2e199aebd', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:39', 0, 0, 0, 0, 'site', 1445494659103, '337d7c3d-27ba-4d39-92c7-39f72fcf2db4'),
	('e3a83a60-d09f-4049-9e9d-187234df24b5', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:30', 0, 0, 0, 0, 'site', 1445474370946, '49019030-af3b-4edb-bec3-ae7e6c55a279'),
	('e3fee988-bc07-42ec-898f-5f7c00fb5d18', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:35', 0, 0, 0, 0, 'site', 1445474375297, 'd61b2487-b747-4321-a4c1-9b527bfc23f2'),
	('e468215c-f26f-4aeb-8307-f3c6da7cd932', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:38', 0, 0, 0, 0, 'site', 1445494658947, '4738dbcb-bdc9-496c-ab7f-cd1a4d4b5a8b'),
	('e5129619-4885-492d-a83c-b1e65d5a9ffc', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:40', 0, 0, 0, 0, 'site', 1445494660561, '51e21a1a-3757-4dde-b813-1f78dbf881fa'),
	('e62f9dc7-6b6e-431d-b87c-c8bcad98799e', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:31', 0, 0, 0, 0, 'site', 1445474371186, 'a33d3db2-c3cf-434f-9a35-8d40e0de2e8d'),
	('e65c4c74-a51a-4d26-ac49-e0c126febbc6', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:31', 0, 0, 0, 0, 'site', 1445474371779, 'a589064e-4bb9-4d62-a100-21f734154200'),
	('e6d7e306-d45c-452d-bece-a83fa42eae02', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:47', 0, 0, 0, 0, 'site', 1445494667038, 'd6f5537b-4534-4ba3-a351-f6be97537d2e'),
	('e6f7e75b-ad13-4b82-9fe4-b9695fabb02d', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:31', 0, 0, 0, 0, 'site', 1445474371421, 'a8b1f0b4-0afd-4d63-9d14-ef77f045c7a2'),
	('e71032c9-eeb3-4f67-9fa0-286babfa24c8', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:42', 0, 0, 0, 0, 'site', 1445494662103, 'af9a68c5-84b9-4ffd-b238-93711b0797a8'),
	('e7291f41-fd8d-420d-a79f-4727f6d5ba35', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:39', 0, 0, 0, 0, 'site', 1445494659874, 'a8b1f0b4-0afd-4d63-9d14-ef77f045c7a2'),
	('e74fe426-6db9-4d98-89a3-7a926e2314fc', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:30', 0, 0, 0, 0, 'site', 1445474370637, '337d7c3d-27ba-4d39-92c7-39f72fcf2db4'),
	('e77e1291-8b38-4174-a0f4-60a92285d209', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:32', 0, 0, 0, 0, 'site', 1445474372180, '1b219275-1f31-4d72-ac8a-86f62caac828'),
	('e7a54d84-722b-42bc-ba85-b555c7a2dd71', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:36', 0, 0, 0, 0, 'site', 1445474376730, 'b783233e-d891-4651-8df3-aaae13ab0b69'),
	('e8c463d7-ca5e-497f-9aa4-b737c43907a2', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:35', 0, 0, 0, 0, 'site', 1445474375221, 'd61b2487-b747-4321-a4c1-9b527bfc23f2'),
	('e9a97368-d455-48de-a45a-1ad8fcf1a3de', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:36', 0, 0, 0, 0, 'site', 1445474376413, 'f27e2913-3bcd-48f6-bc2d-ccbca5ccb840'),
	('e9e78496-ab4e-493d-a6b8-2a42073cd0a1', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:40', 0, 0, 0, 0, 'site', 1445494660964, '98b09cf5-837d-431c-b24c-b35e24e259d4'),
	('ea1b1f0d-f174-493a-8297-e9aca57fb3bc', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:31', 0, 0, 0, 0, 'site', 1445474371045, '47ed5424-7189-449f-8a01-58ac7613834a'),
	('ea86096e-d372-42c5-b509-24f6d58eea8e', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:38', 0, 0, 0, 0, 'site', 1445474378590, '22b70bc4-6dbb-4015-953a-3c092eb9798c'),
	('eab2ec7d-9bbf-4dcf-aefd-fd20b8ac2e04', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:31', 0, 0, 0, 0, 'site', 1445474371490, 'ff956575-07a9-4984-b068-c85473609687'),
	('eb3b523c-9db7-485f-8a5a-2be206a72525', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:41', 0, 0, 0, 0, 'site', 1445494661081, '9cc1a792-91bf-433d-963c-d6b8b088438e'),
	('eb700e37-5367-4561-8455-e2f03df62ec9', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:34', 0, 0, 0, 0, 'site', 1445474374121, 'a11d5394-3b77-4b5f-8556-b9235d6c0daf'),
	('ebaf4d42-8583-445c-9d04-b2c15dfcc3f9', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:38', 0, 0, 0, 0, 'site', 1445494658861, '159648a9-ed33-4a26-9da0-c9aab2ff4a9f'),
	('ec597d03-d567-4973-baa0-af6a6579361f', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:41', 0, 0, 0, 0, 'site', 1445494661294, 'b2240fb3-61e2-427e-8cd4-ec474380594d'),
	('ec914d29-9aa4-49eb-a535-50265c6f6c76', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:45', 0, 0, 0, 0, 'site', 1445494665879, 'b6f04f26-86b9-4a47-8fff-55f7b40d61e0'),
	('ed6a69cb-2ab6-422c-b29d-dab008c80dd3', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:36', 0, 0, 0, 0, 'site', 1445474376121, 'f0af133d-e253-490d-859e-481c072393ee'),
	('ef412f9b-0da1-462d-a301-4d357e08812d', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:41', 0, 0, 0, 0, 'site', 1445494661780, 'd5a06e48-e12f-4146-b07e-7f7e85b1f1c0'),
	('ef6d2d14-4a6f-4661-b8f7-17a51fcae103', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:33', 0, 0, 0, 0, 'site', 1445474373312, 'f78238fd-a4e5-464c-b474-4f184493296b'),
	('ef8bf09a-c427-42ef-9529-bf2d6a555925', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:36', 0, 0, 0, 0, 'site', 1445474376164, '6b54fa40-45b7-4c08-81dc-28a4e6ca5c82'),
	('efc9db7d-e300-49cc-855e-47d187d49a1a', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:41', 0, 0, 0, 0, 'site', 1445494661870, 'f78238fd-a4e5-464c-b474-4f184493296b'),
	('f0431869-6241-4e41-8bce-5be58f6daf98', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:47', 0, 0, 0, 0, 'site', 1445494667218, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('f05f636f-321f-4cd9-b869-ca9c34d3ef06', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:33', 0, 0, 0, 0, 'site', 1445474373005, '4f1838d1-20c0-4b43-a698-7707f62c625f'),
	('f0c5d2d7-0f4b-44be-8221-2b7f9a85b4cb', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:42', 0, 0, 0, 0, 'site', 1445494662920, '64050402-de11-47a8-a8e9-1ec9ed99f7e0'),
	('f0e6293f-3a3c-4bd3-98d0-efbe9b681295', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:32', 0, 0, 0, 0, 'site', 1445474372754, 'b2240fb3-61e2-427e-8cd4-ec474380594d'),
	('f184a9a9-f455-4225-a390-276aad06edaf', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:40', 0, 0, 0, 0, 'site', 1445494660131, '424e93ea-a975-48d3-b53e-3ced5f795a98'),
	('f1be168f-86b1-4590-961b-d12d4b163c71', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:31', 0, 0, 0, 0, 'site', 1445474371254, 'a33d3db2-c3cf-434f-9a35-8d40e0de2e8d'),
	('f1f1b582-cb21-4f8c-a9c6-f0dfeaae74fe', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:37', 0, 0, 0, 0, 'site', 1445474377531, '8ac3c968-6a0a-4435-aaf6-6f392c57a620'),
	('f2115c10-831d-49ca-9da9-1ce1518237ca', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:38', 0, 0, 0, 0, 'site', 1445474378114, 'b8a2a9e1-3f6d-4ec3-954f-5aa96bb1c3c6'),
	('f21e8a2f-4cd0-4d04-a77c-f2ddde68ec6d', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:35', 0, 0, 0, 0, 'site', 1445474375589, 'ac4ccdbc-4b5b-46e5-862f-ea8e319b56a5'),
	('f28a2b59-0761-4479-abe9-91bf75a56d7a', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:40', 0, 0, 0, 0, 'site', 1445494660995, '98b09cf5-837d-431c-b24c-b35e24e259d4'),
	('f4225b19-7464-4941-9177-7d3a1c2a45d8', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:46', 0, 0, 0, 0, 'site', 1445494666196, '08f8586c-9013-48d0-81ec-362be4a97d4a'),
	('f4ae02d5-d41e-443a-95dc-4c769956bb2d', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:36', 0, 0, 0, 0, 'site', 1445474376230, '6b54fa40-45b7-4c08-81dc-28a4e6ca5c82'),
	('f4d118ca-9f1f-46fd-a285-19157b56ff10', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:38', 0, 0, 0, 0, 'site', 1445474378355, '2cd3982c-d469-440d-922a-75e965968f23'),
	('f6063f4d-626f-4fa2-8975-50d2855da603', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:31', 0, 0, 0, 0, 'site', 1445474371012, '47ed5424-7189-449f-8a01-58ac7613834a'),
	('f7becafa-d1b6-4a10-96ac-81421992221b', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:41', 0, 0, 0, 0, 'site', 1445494661753, 'd5a06e48-e12f-4146-b07e-7f7e85b1f1c0'),
	('f7c54663-a212-4133-a90e-e6b519a3265d', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:36', 0, 0, 0, 0, 'site', 1445474376805, 'b783233e-d891-4651-8df3-aaae13ab0b69'),
	('f8ac5847-fc12-48e5-85e4-3030d5e5d543', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:31', 0, 0, 0, 0, 'site', 1445474371812, '51e21a1a-3757-4dde-b813-1f78dbf881fa'),
	('f9512446-1ee9-47ef-90b6-f1274eb58c99', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:45', 0, 0, 0, 0, 'site', 1445494665379, 'b783233e-d891-4651-8df3-aaae13ab0b69'),
	('f9bf54f7-1369-4bf5-9254-4e7ade100fde', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:44', 0, 0, 0, 0, 'site', 1445494664923, 'f27e2913-3bcd-48f6-bc2d-ccbca5ccb840'),
	('f9eb3065-179d-4e90-802c-fe36ec5a68c0', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:41', 0, 0, 0, 0, 'site', 1445494661956, '0ada0a09-5b0d-4b98-a166-88832accecd1'),
	('fa181d20-7459-4556-b649-81e7b30207b8', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:32', 0, 0, 0, 0, 'site', 1445474372380, '9cc1a792-91bf-433d-963c-d6b8b088438e'),
	('fa784ddf-1a48-4905-b408-f86c9691170f', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:32', 0, 0, 0, 0, 'site', 1445474372079, 'ae9e7b3c-2dfe-49fb-b48a-5cc7d3644e3e'),
	('fa9407be-9ea9-4f7e-8b31-a0acbcd0a266', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:38', 0, 0, 0, 0, 'site', 1445494658681, '03d6cd9d-f7b3-4778-b5f3-21797330e369'),
	('fb038514-103d-4840-b128-cd3b89296219', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:38', 0, 0, 0, 0, 'site', 1445474378664, '22b70bc4-6dbb-4015-953a-3c092eb9798c'),
	('fbfc8ae2-deb2-4f4d-840a-11ad9d4cf214', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:46', 0, 0, 0, 0, 'site', 1445494666374, '33db1afb-862b-44a1-b54e-2fef5b2eb003'),
	('fcf09c38-f597-4b06-9c51-598009ae071a', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:44', 0, 0, 0, 0, 'site', 1445494664963, 'f27e2913-3bcd-48f6-bc2d-ccbca5ccb840'),
	('fdddba74-4c85-4767-a1cb-a32ac8ace4fd', '9c2ce025-f730-495b-807b-dc9e5570c123', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:30', 0, 0, 0, 0, 'site', 1445474370879, '49019030-af3b-4edb-bec3-ae7e6c55a279'),
	('feaec075-c899-431a-8d80-993e0cb8f704', 'aa2e8349-05c9-44db-a0d7-527c64c9cf75', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:45', 0, 0, 0, 0, 'site', 1445494665640, '39f7e44b-d90a-41aa-8327-895aee0ea7ec'),
	('fee3a05a-103f-43f3-b45e-2c0ebb60efb4', '9c2ce025-f730-495b-807b-dc9e5570c123', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:41', 0, 0, 0, 0, 'site', 1445494661812, 'f78238fd-a4e5-464c-b474-4f184493296b'),
	('ff1a5d29-a926-4c8c-b042-d7ad022f8b98', 'b3dabeb4-5665-4f7a-98b2-de6a2006d0d3', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-22 08:39:36', 0, 0, 0, 0, 'site', 1445474376931, '11e05cb9-0f9f-4771-9c7c-cc9809956439');
/*!40000 ALTER TABLE `ticket_roleprivilege` ENABLE KEYS */;


-- 导出  表 ticket.ticket_servicebook 结构
CREATE TABLE IF NOT EXISTS `ticket_servicebook` (
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

-- 正在导出表  ticket.ticket_servicebook 的数据：~0 rows (大约)
DELETE FROM `ticket_servicebook`;
/*!40000 ALTER TABLE `ticket_servicebook` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_servicebook` ENABLE KEYS */;


-- 导出  表 ticket.ticket_systemconfig 结构
CREATE TABLE IF NOT EXISTS `ticket_systemconfig` (
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

-- 正在导出表  ticket.ticket_systemconfig 的数据：~1 rows (大约)
DELETE FROM `ticket_systemconfig`;
/*!40000 ALTER TABLE `ticket_systemconfig` DISABLE KEYS */;
INSERT INTO `ticket_systemconfig` (`id`, `description`, `isAllowCreate`, `isAllowSignIn`, `keyword`, `name`, `audit`, `commend`, `createTime`, `deleteFlag`, `hits`, `hot`, `orderValue`, `versionFlag`, `visitUrl`, `nameFront`) VALUES
	('153b4d18-8cfa-4bcd-8f79-410d85bb1e9c', '昆明长水国际机场', 1, 1, '长水机场', '昆明长水国际机场', 0, 0, '2015-10-23 11:19:38', 0, 0, 0, 0, 'site', 1445570378679, '昆明长水国际机场');
/*!40000 ALTER TABLE `ticket_systemconfig` ENABLE KEYS */;


-- 导出  表 ticket.ticket_systemdictionary 结构
CREATE TABLE IF NOT EXISTS `ticket_systemdictionary` (
  `id` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
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
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ticket.ticket_systemdictionary 的数据：~0 rows (大约)
DELETE FROM `ticket_systemdictionary`;
/*!40000 ALTER TABLE `ticket_systemdictionary` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_systemdictionary` ENABLE KEYS */;


-- 导出  表 ticket.ticket_systemmodule 结构
CREATE TABLE IF NOT EXISTS `ticket_systemmodule` (
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

-- 正在导出表  ticket.ticket_systemmodule 的数据：~84 rows (大约)
DELETE FROM `ticket_systemmodule`;
/*!40000 ALTER TABLE `ticket_systemmodule` DISABLE KEYS */;
INSERT INTO `ticket_systemmodule` (`id`, `icon`, `isDefaultShow`, `name`, `parent_id`, `audit`, `commend`, `createTime`, `deleteFlag`, `hits`, `hot`, `orderValue`, `versionFlag`, `visitUrl`, `moduleUrl`) VALUES
	('03d6cd9d-f7b3-4778-b5f3-21797330e369', 'default.png', 0, '管理公告栏', '159648a9-ed33-4a26-9da0-c9aab2ff4a9f', 0, 0, '2015-10-21 17:15:58', 0, 0, 0, 2, 'site', 1445418958382, '/news_manage.action?versionFlag=site'),
	('079f46b3-b738-4afb-8524-f624f2e96766', 'default.png', 0, '新增会员等级', 'f27e2913-3bcd-48f6-bc2d-ccbca5ccb840', 0, 0, '2015-10-14 16:47:21', 0, 0, 0, 5, 'site', 1444812441140, '/memberLevel_add.action?versionFlag=site'),
	('08f8586c-9013-48d0-81ec-362be4a97d4a', 'default.png', 0, '新闻评论', 'b8a2a9e1-3f6d-4ec3-954f-5aa96bb1c3c6', 0, 0, '2015-10-13 13:35:17', 0, 0, 0, 3, 'site', 1444714517796, '##'),
	('0ada0a09-5b0d-4b98-a166-88832accecd1', 'default.png', 0, '行李服务', 'd3759e96-1aec-4d5f-8bd1-f1b9e7d630ad', 0, 0, '2015-10-19 17:27:29', 0, 0, 0, 9, 'site', 1445246849771, '##'),
	('10cc580a-7f73-41b0-8f8b-d264bb3b892f', 'default.png', 0, '会员中心', NULL, 0, 0, '2015-10-14 16:46:12', 0, 0, 0, 1, 'site', 1444812372313, '#'),
	('11327c91-c9dd-4f72-a2f3-077456c03228', 'default.png', 0, '管理广告信息', '9de32548-7200-4d31-83f0-b7367576c805', 0, 0, '2015-10-27 11:01:49', 0, 0, 0, 2, 'site', 1445914909849, '/advert_manage.action?versionFlag=site'),
	('11e05cb9-0f9f-4771-9c7c-cc9809956439', 'default.png', 0, '新闻回收站', 'dbe283e0-6fb1-4b13-a57a-0517a59f5474', 0, 0, '2015-10-13 13:38:36', 0, 0, 0, 2, 'site', 1444714716841, '/news_recycle.action?versionFlag=site'),
	('1b219275-1f31-4d72-ac8a-86f62caac828', 'default.png', 0, '电瓶车管理', 'db188e80-1d5d-4d3f-81b3-484206ee2107', 0, 0, '2015-10-19 17:33:33', 0, 0, 0, 3, 'site', 1445247213789, '##'),
	('22b70bc4-6dbb-4015-953a-3c092eb9798c', 'default.png', 0, '代码管理', '81166827-47a1-44ec-a9e0-81d60e3f72f8', 0, 0, '2015-10-13 09:56:54', 0, 0, 0, 10, 'site', 1444701414820, '##'),
	('2990a75f-bd36-4e61-a753-4415495a95eb', 'default.png', 0, '新增机场派出所', 'd9c0a96c-ba87-49d3-a815-71f38a4b0a5f', 0, 0, '2015-10-21 15:11:17', 0, 0, 0, 0, 'site', 1445411477180, '/newsClass_add.action?versionFlag=site'),
	('2cd3982c-d469-440d-922a-75e965968f23', 'default.png', 0, '删除模块', '22b70bc4-6dbb-4015-953a-3c092eb9798c', 0, 0, '2015-10-13 09:58:02', 0, 0, 0, 1, 'site', 1444701482888, '/generateTemplateCode_delete.action?versionFlag=site'),
	('337d7c3d-27ba-4d39-92c7-39f72fcf2db4', 'default.png', 0, '管理机场派出所', '366a40f2-6f5b-4c66-a168-9d774cb69507', 0, 0, '2015-10-21 15:33:21', 0, 0, 0, 0, 'site', 1445412801577, '/news_manage.action?versionFlag=site'),
	('33db1afb-862b-44a1-b54e-2fef5b2eb003', 'default.png', 0, '新闻类别', 'b8a2a9e1-3f6d-4ec3-954f-5aa96bb1c3c6', 0, 0, '2015-10-13 13:34:43', 0, 0, 0, 5, 'site', 1444714483057, '##'),
	('35b400fb-2f3f-42cd-be0d-580d06b60d86', 'default.png', 0, '新增机场派出所', '366a40f2-6f5b-4c66-a168-9d774cb69507', 0, 0, '2015-10-21 15:33:21', 0, 0, 0, 0, 'site', 1445412801518, '/news_add.action?versionFlag=site'),
	('38cda477-f403-4720-9d05-c90eef30c73d', 'default.png', 0, '新增新闻', 'dbe283e0-6fb1-4b13-a57a-0517a59f5474', 0, 0, '2015-10-13 13:37:43', 0, 0, 0, 5, 'site', 1444714663196, '/news_add.action?versionFlag=site'),
	('39f7e44b-d90a-41aa-8327-895aee0ea7ec', 'default.png', 0, '管理新闻', 'dbe283e0-6fb1-4b13-a57a-0517a59f5474', 0, 0, '2015-10-13 13:38:07', 0, 0, 0, 4, 'site', 1444714687855, '/news_manage.action?fversionFlag=site'),
	('3cad172d-eaba-4748-903e-617ea2b6df8f', 'default.png', 0, '新增用户信息', 'f24c449b-42f7-4628-85d7-e4012905ec49', 0, 0, '2015-10-19 14:31:58', 0, 0, 0, 3, 'site', 1445236318261, '/systemUser_add.action?versionFlag=site'),
	('413f2bd4-8c07-485e-8ead-e3d547f1d40e', 'default.png', 0, '管理广告类型', '6e182c6f-8fe3-454b-9771-b99a7ccac977', 0, 0, '2015-10-27 10:59:27', 0, 0, 0, 2, 'site', 1445914767201, '/advertType_manage.action?versionFlag=site'),
	('424e93ea-a975-48d3-b53e-3ced5f795a98', 'default.png', 0, '评价系统', 'db188e80-1d5d-4d3f-81b3-484206ee2107', 0, 0, '2015-10-19 17:35:45', 0, 0, 0, 2, 'site', 1445247345154, '##'),
	('4738dbcb-bdc9-496c-ab7f-cd1a4d4b5a8b', 'default.png', 0, '新增公告栏', '159648a9-ed33-4a26-9da0-c9aab2ff4a9f', 0, 0, '2015-10-21 17:15:58', 0, 0, 0, 3, 'site', 1445418958359, '/news_add.action?versionFlag=site'),
	('47ed5424-7189-449f-8a01-58ac7613834a', 'default.png', 0, '新增机场派出所', '92caf843-baba-400d-8927-ecfaf5c865dc', 0, 0, '2015-10-21 15:19:46', 0, 0, 0, 0, 'site', 1445411986199, '/news_add.action?versionFlag=site'),
	('49019030-af3b-4edb-bec3-ae7e6c55a279', 'default.png', 0, '机场派出所回收站', '366a40f2-6f5b-4c66-a168-9d774cb69507', 0, 0, '2015-10-21 15:33:21', 0, 0, 0, 0, 'site', 1445412801601, '/news_recycle.action?versionFlag=site'),
	('497adf2d-dc2b-4487-8e5d-e7c7438f6da0', 'default.png', 0, '新增广告信息', '9de32548-7200-4d31-83f0-b7367576c805', 0, 0, '2015-10-27 11:01:30', 0, 0, 0, 3, 'site', 1445914890206, '/advert_add.action?versionFlag=site'),
	('4b18167e-ecad-489e-87b6-44c90d04c9a4', 'default.png', 0, '管理机场派出所', '92caf843-baba-400d-8927-ecfaf5c865dc', 0, 0, '2015-10-21 15:19:46', 0, 0, 0, 0, 'site', 1445411986229, '/news_manage.action?versionFlag=site'),
	('4f1838d1-20c0-4b43-a698-7707f62c625f', 'default.png', 0, '公共设施', 'd3759e96-1aec-4d5f-8bd1-f1b9e7d630ad', 0, 0, '2015-10-19 17:29:02', 0, 0, 0, 6, 'site', 1445246942929, '##'),
	('51e21a1a-3757-4dde-b813-1f78dbf881fa', 'default.png', 0, '电瓶车信息回收站', '1b219275-1f31-4d72-ac8a-86f62caac828', 0, 0, '2015-10-19 17:34:43', 0, 0, 0, 2, 'site', 1445247283170, '/electrombile_recycle.action?versionFlag=site'),
	('5a57bdb0-33df-4879-a1a1-0a2b805b1676', 'default.png', 0, '商旅服务', 'd3759e96-1aec-4d5f-8bd1-f1b9e7d630ad', 0, 0, '2015-10-19 17:28:38', 0, 0, 0, 7, 'site', 1445246918254, '##'),
	('60f4e4bf-b13a-4af5-9592-8ef5019fd32a', 'default.png', 0, '管理新闻模版', 'e1a8a328-5e7c-4471-9174-efea2a05e199', 0, 0, '2015-10-22 16:30:05', 0, 0, 0, 2, 'site', 1445502605177, '/pageRedirectTemplate_manage.action?versionFlag=site'),
	('64050402-de11-47a8-a8e9-1ec9ed99f7e0', 'default.png', 0, '管理权限信息', 'ac4ccdbc-4b5b-46e5-862f-ea8e319b56a5', 0, 0, '2015-10-19 14:34:48', 0, 0, 0, 3, 'site', 1445236488864, '/privilegeInfo_manage.action?versionFlag=site'),
	('6661c054-ba77-4ef7-8036-66b36073c3b0', 'default.png', 0, '新增权限信息', 'ac4ccdbc-4b5b-46e5-862f-ea8e319b56a5', 0, 0, '2015-10-19 14:34:27', 0, 0, 0, 4, 'site', 1445236467998, '/privilegeInfo_add.action?versionFlag=site'),
	('6b54fa40-45b7-4c08-81dc-28a4e6ca5c82', 'default.png', 0, '管理会员等级', 'f27e2913-3bcd-48f6-bc2d-ccbca5ccb840', 0, 0, '2015-10-14 16:47:46', 0, 0, 0, 4, 'site', 1444812466703, '/memberLevel_manage.action?versionFlag=site'),
	('6e182c6f-8fe3-454b-9771-b99a7ccac977', 'default.png', 0, '广告类型', 'b8a2a9e1-3f6d-4ec3-954f-5aa96bb1c3c6', 0, 0, '2015-10-27 10:58:18', 0, 0, 0, 1, 'site', 1445914698680, '##'),
	('7b52f775-b5dc-4d73-b041-452d5c4d0805', 'default.png', 0, '会员信息', '10cc580a-7f73-41b0-8f8b-d264bb3b892f', 0, 0, '2015-10-19 14:38:22', 0, 0, 0, 4, 'site', 1445236702180, '##'),
	('7b5b131b-00cd-488a-acf3-966258070ef9', 'default.png', 0, '管理角色信息', '879b2bc4-e3d4-42de-bfc0-43774cd0482f', 0, 0, '2015-10-19 14:33:36', 0, 0, 0, 3, 'site', 1445236416041, '/roleInfo_manage.action?versionFlag=site'),
	('7cbad721-91a7-460b-8916-bb19bbe5e0f2', 'default.png', 0, '系统菜单设置', 'd6f5537b-4534-4ba3-a351-f6be97537d2e', 0, 0, '2015-10-13 10:00:30', 0, 0, 0, 1, 'site', 1444701630636, 'systemModule_manage.action?versionFlag=site'),
	('7cd4debb-1981-4c07-867c-6d7f6fc1e955', 'default.png', 0, '角色信息回收站', '879b2bc4-e3d4-42de-bfc0-43774cd0482f', 0, 0, '2015-10-19 14:33:51', 0, 0, 0, 1, 'site', 1445236431735, '/roleInfo_recycle.action?versionFlag=site'),
	('8161bdd4-2f90-4079-a434-8f1d8d937049', 'default.png', 0, '用户信息回收站', 'f24c449b-42f7-4628-85d7-e4012905ec49', 0, 0, '2015-10-19 14:32:43', 0, 0, 0, 1, 'site', 1445236363903, '/systemUser_recycle.action?versionFlag=site'),
	('879b2bc4-e3d4-42de-bfc0-43774cd0482f', 'default.png', 0, '角色信息', '8ecea40a-bd6b-4dfe-914c-aa44bc5fc201', 0, 0, '2015-10-19 14:31:14', 0, 0, 0, 4, 'site', 1445236274784, '##'),
	('8ac3c968-6a0a-4435-aaf6-6f392c57a620', 'default.png', 0, '新增类别', '33db1afb-862b-44a1-b54e-2fef5b2eb003', 0, 0, '2015-10-13 13:36:00', 0, 0, 0, 5, 'site', 1444714560251, '/newsClass_add.action?versionFlag=site'),
	('8dea01b1-9bfd-4635-be39-c1fa6438b85b', '0', 1, '系统管理', NULL, 1, 1, '2015-03-17 11:35:21', 0, 0, 0, 0, 'site', 109842384, '#'),
	('8df8840d-610b-409d-a2bb-e912d3c20ec5', 'default.png', 0, '国外乘机须知', '1bfc2489-75db-4e79-9972-2516db79b12e', 0, 0, '2015-10-19 17:27:11', 0, 0, 0, 1, 'site', 1445246831912, '##'),
	('8e22e8c8-649b-4286-baf4-0c48e88fdfb6', 'default.png', 0, '添加模块', '22b70bc4-6dbb-4015-953a-3c092eb9798c', 0, 0, '2015-10-13 09:57:37', 0, 0, 0, 2, 'site', 1444701457307, '/generateTemplateCode_add.action?versionFag=site'),
	('8ecea40a-bd6b-4dfe-914c-aa44bc5fc201', 'default.png', 0, '权限中心', NULL, 0, 0, '2015-10-19 14:30:42', 0, 0, 0, 3, 'site', 1445236242832, '#'),
	('9479f75a-6c85-406a-94d3-48400ef0845d', 'default.png', 0, '广告类型回收站', '6e182c6f-8fe3-454b-9771-b99a7ccac977', 0, 0, '2015-10-27 11:00:09', 0, 0, 0, 1, 'site', 1445914809105, '/advertType_recycle.action?versionFlag=site'),
	('98b09cf5-837d-431c-b24c-b35e24e259d4', 'default.png', 0, '网上值机', 'db188e80-1d5d-4d3f-81b3-484206ee2107', 0, 0, '2015-10-19 17:33:09', 0, 0, 0, 4, 'site', 1445247189926, '##'),
	('9b427f92-d323-4800-b98f-151cd6486237', 'default.png', 0, '评论回收站', '08f8586c-9013-48d0-81ec-362be4a97d4a', 0, 0, '2015-10-13 13:40:10', 0, 0, 0, 1, 'site', 1444714810847, '/newsComment_recycle.action?flag=site'),
	('9cc1a792-91bf-433d-963c-d6b8b088438e', 'default.png', 0, '航班查询', 'db188e80-1d5d-4d3f-81b3-484206ee2107', 0, 0, '2015-10-19 17:32:41', 0, 0, 0, 5, 'site', 1445247161506, '##'),
	('9d12aa16-5cdf-4fb7-b7d3-81058ed8024f', 'default.png', 0, '权限信息回收站', 'ac4ccdbc-4b5b-46e5-862f-ea8e319b56a5', 0, 0, '2015-10-19 14:35:07', 0, 0, 0, 1, 'site', 1445236507356, '/privilegeInfo_recycle.action?versionFlag=site'),
	('9de32548-7200-4d31-83f0-b7367576c805', 'default.png', 0, '广告信息', 'b8a2a9e1-3f6d-4ec3-954f-5aa96bb1c3c6', 0, 0, '2015-10-27 11:01:06', 0, 0, 0, 1, 'site', 1445914866741, '##'),
	('a0b1bbc8-0fab-4997-92dc-d216898562ac', 'default.png', 0, '会员信息回收站', '7b52f775-b5dc-4d73-b041-452d5c4d0805', 0, 0, '2015-10-19 14:40:11', 0, 0, 0, 2, 'site', 1445236811920, '/member_recycle.action?versionFlag=site'),
	('a11d5394-3b77-4b5f-8556-b9235d6c0daf', 'default.png', 0, '新增会员信息', '7b52f775-b5dc-4d73-b041-452d5c4d0805', 0, 0, '2015-10-19 14:39:00', 0, 0, 0, 4, 'site', 1445236740146, '/member_add.action?versionFlag=site'),
	('a33d3db2-c3cf-434f-9a35-8d40e0de2e8d', 'default.png', 0, '机场派出所回收站', '92caf843-baba-400d-8927-ecfaf5c865dc', 0, 0, '2015-10-21 15:19:46', 0, 0, 0, 0, 'site', 1445411986253, '/news_recycle.action?versionFlag=site'),
	('a589064e-4bb9-4d62-a100-21f734154200', 'default.png', 0, '机票验真', 'db188e80-1d5d-4d3f-81b3-484206ee2107', 0, 0, '2015-10-19 17:35:23', 0, 0, 0, 3, 'site', 1445247323524, '##'),
	('a8b1f0b4-0afd-4d63-9d14-ef77f045c7a2', 'default.png', 0, '机场派出所回收站', 'd9c0a96c-ba87-49d3-a815-71f38a4b0a5f', 0, 0, '2015-10-21 15:11:17', 0, 0, 0, 0, 'site', 1445411477297, '/newsClass_recycle.action?versionFlag=site'),
	('ac4ccdbc-4b5b-46e5-862f-ea8e319b56a5', 'default.png', 0, '权限信息', '8ecea40a-bd6b-4dfe-914c-aa44bc5fc201', 0, 0, '2015-10-19 14:31:30', 0, 0, 0, 3, 'site', 1445236290867, '##'),
	('ae9e7b3c-2dfe-49fb-b48a-5cc7d3644e3e', 'default.png', 0, '新增电瓶车', '1b219275-1f31-4d72-ac8a-86f62caac828', 0, 0, '2015-10-19 17:33:52', 0, 0, 0, 4, 'site', 1445247232192, '/electrombile_add.action?versionFlag=site'),
	('af9a68c5-84b9-4ffd-b238-93711b0797a8', 'default.png', 0, '国内乘机须知', '1bfc2489-75db-4e79-9972-2516db79b12e', 0, 0, '2015-10-19 17:26:55', 0, 0, 0, 2, 'site', 1445246815964, '##'),
	('b2240fb3-61e2-427e-8cd4-ec474380594d', 'default.png', 0, '关注我们', 'd3759e96-1aec-4d5f-8bd1-f1b9e7d630ad', 0, 0, '2015-10-19 17:29:41', 0, 0, 0, 4, 'site', 1445246981900, '##'),
	('b2d5fc20-de90-454a-9eef-e87176745059', 'default.png', 0, '系统参数设置', 'd6f5537b-4534-4ba3-a351-f6be97537d2e', 0, 0, '2015-10-23 09:37:25', 0, 0, 0, 1, 'site', 1445564245216, '/systemConfig_setting.action?versionFlag=site'),
	('b34f6440-c2bb-48d4-9f6d-b2e603b8624f', 'default.png', 0, '新增模版', 'e1a8a328-5e7c-4471-9174-efea2a05e199', 0, 0, '2015-10-22 16:29:37', 0, 0, 0, 3, 'site', 1445502577035, '/pageRedirectTemplate_add.action?versionFlag=site'),
	('b65914fb-2613-48ab-80f6-a12299cdf9fa', 'default.png', 0, '新增广告类型', '6e182c6f-8fe3-454b-9771-b99a7ccac977', 0, 0, '2015-10-27 10:59:02', 0, 0, 0, 3, 'site', 1445914742051, '/advertType_add.action?versionFlag=site'),
	('b6f04f26-86b9-4a47-8fff-55f7b40d61e0', 'default.png', 0, '管理类别', '33db1afb-862b-44a1-b54e-2fef5b2eb003', 0, 0, '2015-10-13 13:36:39', 0, 0, 0, 4, 'site', 1444714599410, '/newsClass_manage.action?versionFlag=site'),
	('b783233e-d891-4651-8df3-aaae13ab0b69', 'default.png', 0, '管理评论', '08f8586c-9013-48d0-81ec-362be4a97d4a', 0, 0, '2015-10-13 13:39:29', 0, 0, 0, 3, 'site', 1444714769844, '/newsComment_manage.action?flag=site'),
	('b8a2a9e1-3f6d-4ec3-954f-5aa96bb1c3c6', 'default.png', 0, '资讯中心', NULL, 0, 0, '2015-10-13 13:34:25', 0, 0, 0, 14, 'site', 1444714465398, '#'),
	('c16437db-8664-4c26-993f-0575e5527342', 'default.png', 0, '新闻模板回收站', 'e1a8a328-5e7c-4471-9174-efea2a05e199', 0, 0, '2015-10-27 17:11:03', 0, 0, 0, 1, 'site', 1445937063532, '/pageRedirectTemplate_recycle.action?versionFlag=site'),
	('ce2eaf51-93f2-49c4-b9bf-a4fed3e7f599', 'default.png', 0, '管理会员信息', '7b52f775-b5dc-4d73-b041-452d5c4d0805', 0, 0, '2015-10-19 14:39:23', 0, 0, 0, 3, 'site', 1445236763810, '/member_manage.action?versionFlag=site'),
	('d0ec8c40-9569-4d00-a389-b853748bf849', 'default.png', 0, '长水文化', 'd3759e96-1aec-4d5f-8bd1-f1b9e7d630ad', 0, 0, '2015-10-19 17:29:24', 0, 0, 0, 5, 'site', 1445246964696, '##'),
	('d3759e96-1aec-4d5f-8bd1-f1b9e7d630ad', 'default.png', 0, '服务管理', 'db188e80-1d5d-4d3f-81b3-484206ee2107', 0, 0, '2015-10-19 17:30:46', 0, 0, 0, 1, 'site', 1445247046471, '##'),
	('d39e3ad7-a482-462f-9eb9-454b26b9db5b', 'default.png', 0, '公告栏回收站', '159648a9-ed33-4a26-9da0-c9aab2ff4a9f', 0, 0, '2015-10-21 17:15:58', 0, 0, 0, 1, 'site', 1445418958409, '/news_recycle.action?versionFlag=site'),
	('d5a06e48-e12f-4146-b07e-7f7e85b1f1c0', 'default.png', 0, '爱心服务', 'd3759e96-1aec-4d5f-8bd1-f1b9e7d630ad', 0, 0, '2015-10-19 17:28:13', 0, 0, 0, 8, 'site', 1445246893342, '##'),
	('d61b2487-b747-4321-a4c1-9b527bfc23f2', 'default.png', 0, '管理用户信息', 'f24c449b-42f7-4628-85d7-e4012905ec49', 0, 0, '2015-10-19 14:32:19', 0, 0, 0, 2, 'site', 1445236339760, '/systemUser_manage.action?versionFlag=site'),
	('d6f5537b-4534-4ba3-a351-f6be97537d2e', 'default.png', 0, '系统基本设置', '8dea01b1-9bfd-4635-be39-c1fa6438b85b', 0, 0, '2015-03-17 11:42:11', 0, 0, 0, 1, 'site', 1426563731354, '/systemConfig_setting.action?versionFlag=site'),
	('db188e80-1d5d-4d3f-81b3-484206ee2107', 'default.png', 0, '服务中心', NULL, 0, 0, '2015-10-19 17:25:42', 0, 0, 0, 3, 'site', 1445246742051, '##'),
	('dbe283e0-6fb1-4b13-a57a-0517a59f5474', 'default.png', 0, '新闻信息', 'b8a2a9e1-3f6d-4ec3-954f-5aa96bb1c3c6', 0, 0, '2015-10-13 13:35:02', 0, 0, 0, 4, 'site', 1444714502262, '##'),
	('e1a8a328-5e7c-4471-9174-efea2a05e199', 'default.png', 0, '新闻模版管理', 'b8a2a9e1-3f6d-4ec3-954f-5aa96bb1c3c6', 0, 0, '2015-10-22 16:28:58', 0, 0, 0, 1, 'site', 1445502538451, '##'),
	('e577d00a-9afe-4f9c-a2f6-b84be30a0884', 'default.png', 0, '管理电瓶车信息', '1b219275-1f31-4d72-ac8a-86f62caac828', 0, 0, '2015-10-19 17:34:13', 0, 0, 0, 3, 'site', 1445247253028, '/electrombile_manage.action?versionFlag=site'),
	('e6c92868-3109-4e0b-8fa9-6d781c873a19', 'default.png', 0, '新增角色信息', '879b2bc4-e3d4-42de-bfc0-43774cd0482f', 0, 0, '2015-10-19 14:33:15', 0, 0, 0, 4, 'site', 1445236395081, '/roleInfo_add.action?versionFlag=site'),
	('eddcce93-563b-4b71-952b-e6f8e222753c', 'default.png', 0, '广告信息回收站', '9de32548-7200-4d31-83f0-b7367576c805', 0, 0, '2015-10-27 11:02:34', 0, 0, 0, 1, 'site', 1445914954280, '/advert_recycle.action?versionFlag=site'),
	('f0af133d-e253-490d-859e-481c072393ee', 'default.png', 0, '等级回收站', 'f27e2913-3bcd-48f6-bc2d-ccbca5ccb840', 0, 0, '2015-10-14 16:48:12', 0, 0, 0, 3, 'site', 1444812492195, '/memberLevel_recycle.action?versionFlag=site'),
	('f24c449b-42f7-4628-85d7-e4012905ec49', 'default.png', 0, '用户信息', '8ecea40a-bd6b-4dfe-914c-aa44bc5fc201', 0, 0, '2015-10-19 14:31:01', 0, 0, 0, 5, 'site', 1445236261795, '##'),
	('f27e2913-3bcd-48f6-bc2d-ccbca5ccb840', 'default.png', 0, '会员等级', '10cc580a-7f73-41b0-8f8b-d264bb3b892f', 0, 0, '2015-10-14 16:46:31', 0, 0, 0, 5, 'site', 1444812391715, '##'),
	('f78238fd-a4e5-464c-b474-4f184493296b', 'default.png', 0, '便民服务', 'd3759e96-1aec-4d5f-8bd1-f1b9e7d630ad', 0, 0, '2015-10-19 17:27:44', 0, 0, 0, 8, 'site', 1445246864781, '##'),
	('fdb9fe15-c954-4bf7-adf8-0893038a8b71', 'default.png', 0, '免责申明', 'd3759e96-1aec-4d5f-8bd1-f1b9e7d630ad', 0, 0, '2015-10-19 17:30:01', 0, 0, 0, 3, 'site', 1445247001851, '##'),
	('ff956575-07a9-4984-b068-c85473609687', 'default.png', 0, '管理机场派出所', 'd9c0a96c-ba87-49d3-a815-71f38a4b0a5f', 0, 0, '2015-10-21 15:11:17', 0, 0, 0, 0, 'site', 1445411477263, '/newsClass_manage.action?versionFlag=site');
/*!40000 ALTER TABLE `ticket_systemmodule` ENABLE KEYS */;


-- 导出  表 ticket.ticket_systemplugin 结构
CREATE TABLE IF NOT EXISTS `ticket_systemplugin` (
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

-- 正在导出表  ticket.ticket_systemplugin 的数据：~0 rows (大约)
DELETE FROM `ticket_systemplugin`;
/*!40000 ALTER TABLE `ticket_systemplugin` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_systemplugin` ENABLE KEYS */;


-- 导出  表 ticket.ticket_systemupdatelog 结构
CREATE TABLE IF NOT EXISTS `ticket_systemupdatelog` (
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

-- 正在导出表  ticket.ticket_systemupdatelog 的数据：~0 rows (大约)
DELETE FROM `ticket_systemupdatelog`;
/*!40000 ALTER TABLE `ticket_systemupdatelog` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_systemupdatelog` ENABLE KEYS */;


-- 导出  表 ticket.ticket_systemuser 结构
CREATE TABLE IF NOT EXISTS `ticket_systemuser` (
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

-- 正在导出表  ticket.ticket_systemuser 的数据：~2 rows (大约)
DELETE FROM `ticket_systemuser`;
/*!40000 ALTER TABLE `ticket_systemuser` DISABLE KEYS */;
INSERT INTO `ticket_systemuser` (`id`, `loginId`, `name`, `password`, `phone`, `sex`, `audit`, `commend`, `createTime`, `deleteFlag`, `hits`, `hot`, `orderValue`, `versionFlag`, `visitUrl`) VALUES
	('18ac871e-2669-4933-ae2f-04c4e5cc8192', 'test', '测试用户', '111111', '13899999999', 1, 0, 0, '2015-10-19 15:43:54', 0, 0, 0, 0, 'site', 1445240634518),
	('8dea01b1-9bfd-4635-be39-c1fa6438b85b', 'yngk', '云南冠科', '111111', '13888888888', 1, 1, 1, '2015-03-17 11:37:23', 0, 0, 0, 0, 'site', 24243242424);
/*!40000 ALTER TABLE `ticket_systemuser` ENABLE KEYS */;


-- 导出  表 ticket.ticket_systemuserextends 结构
CREATE TABLE IF NOT EXISTS `ticket_systemuserextends` (
  `id` varchar(255) NOT NULL,
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
  `type` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ticket.ticket_systemuserextends 的数据：~0 rows (大约)
DELETE FROM `ticket_systemuserextends`;
/*!40000 ALTER TABLE `ticket_systemuserextends` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_systemuserextends` ENABLE KEYS */;


-- 导出  表 ticket.ticket_systemuserloginlog 结构
CREATE TABLE IF NOT EXISTS `ticket_systemuserloginlog` (
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

-- 正在导出表  ticket.ticket_systemuserloginlog 的数据：~116 rows (大约)
DELETE FROM `ticket_systemuserloginlog`;
/*!40000 ALTER TABLE `ticket_systemuserloginlog` DISABLE KEYS */;
INSERT INTO `ticket_systemuserloginlog` (`id`, `ip`, `audit`, `commend`, `createTime`, `deleteFlag`, `hits`, `hot`, `orderValue`, `versionFlag`, `visitUrl`, `systemUser_id`) VALUES
	('048693c8-13b2-4c67-8b1d-fe4dc5bea8e0', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-26 14:45:31', 0, 0, 0, 0, 'site', 1445841931391, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('04a1b44d-1dfd-4f5a-8d2f-bf50c645c6c6', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-22 09:49:23', 0, 0, 0, 0, 'site', 1445478563192, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('05116ab7-84f0-4cb1-ae3f-8699898a2076', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-22 08:32:20', 0, 0, 0, 0, 'site', 1445473940797, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('057896e3-77ea-4c0c-b48f-7ae167947d37', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-24 13:56:23', 0, 0, 0, 0, 'site', 1445666183301, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('06487d24-02e6-480a-a777-cefa137b9900', '192.168.0.103', 0, 0, '2015-10-27 12:48:34', 0, 0, 0, 0, 'site', 1445921314937, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('0b4bf6cb-28b5-4c8c-94a4-bb37c64c4f5a', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-22 11:03:07', 0, 0, 0, 0, 'site', 1445482987068, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('0cd1a2d5-4039-4367-b9cb-837f1d996f98', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-21 15:12:03', 0, 0, 0, 0, 'site', 1445411523266, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('0ce273e0-54f4-4aad-8bf2-29c1373698d9', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-21 13:47:40', 0, 0, 0, 0, 'site', 1445406460915, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('0fa78efe-2a5d-45f3-bfd7-83a1f7a1f3e1', '192.168.0.103', 0, 0, '2015-10-27 16:41:56', 0, 0, 0, 0, 'site', 1445935316731, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('13118421-b3cd-4348-b082-0582f85e9135', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-28 14:55:03', 0, 0, 0, 0, 'site', 1446015303309, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('136a0286-7e36-47f0-add3-3a1c3e3071be', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-21 18:01:13', 0, 0, 0, 0, 'site', 1445421673860, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('14ed9ba2-68b4-4d6b-8578-23defc0a1d31', '192.168.191.1', 0, 0, '2015-10-25 21:46:27', 0, 0, 0, 0, 'site', 1445780787075, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('190d60e4-7f53-412d-9fb0-eabf9632e372', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-24 11:08:32', 0, 0, 0, 0, 'site', 1445656112377, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('1935fca0-4b2d-4a24-a39b-aeff8766cb26', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-23 09:14:04', 0, 0, 0, 0, 'site', 1445562844700, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('1a06fa7d-6814-42d5-a2a5-6ae7d95aeaa7', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-23 15:28:42', 0, 0, 0, 0, 'site', 1445585322106, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('1bbcbec6-704c-4148-822b-edfcd758c667', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-22 16:49:45', 0, 0, 0, 0, 'site', 1445503785093, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('1c0d32f0-b02c-48fd-9e31-9082228407ed', '192.168.191.1', 0, 0, '2015-10-25 21:29:07', 0, 0, 0, 0, 'site', 1445779747699, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('1c766638-73b7-4edd-8840-1257c0fbc8a0', '192.168.0.103', 0, 0, '2015-10-27 17:01:26', 0, 0, 0, 0, 'site', 1445936486080, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('1de431b2-5604-4b37-9c09-b8cdafe0abf2', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-27 11:36:33', 0, 0, 0, 0, 'site', 1445916993208, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('1f0968ef-afed-4cee-915a-4c6a61ed3689', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-26 14:21:52', 0, 0, 0, 0, 'site', 1445840512383, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('1fc75aa9-a5a6-44e9-a8b2-13d7ab683428', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-21 17:11:58', 0, 0, 0, 0, 'site', 1445418718110, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('1fe206a0-99bb-4f16-82da-8159988a9344', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-27 10:23:52', 0, 0, 0, 0, 'site', 1445912632049, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('2080ddd9-f6d0-43f5-ad33-655efaeef737', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-26 17:30:06', 0, 0, 0, 0, 'site', 1445851806016, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('246fd5fe-664b-40c6-a842-44013d9c2f5c', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-22 09:50:01', 0, 0, 0, 0, 'site', 1445478601353, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('25463148-b5ef-4388-b533-4c0c8b54ef20', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-29 10:28:19', 0, 0, 0, 0, 'site', 1446085699328, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('28a3d128-e0c3-4ddc-8472-e046d061707d', 'fe80:0:0:0:b981:6552:28d4:b8db', 0, 0, '2015-10-22 14:12:45', 0, 0, 0, 0, 'site', 1445494365694, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('29e25fb6-b25f-4091-a149-d8bcf36f475f', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-22 08:15:00', 0, 0, 0, 0, 'site', 1445472900913, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('305632de-0827-47db-82fb-e9bafb0cf9b2', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-28 13:05:29', 0, 0, 0, 0, 'site', 1446008729006, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('3132e2ae-b6e8-4166-9aa6-fe0e1aab4d96', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-21 17:27:36', 0, 0, 0, 0, 'site', 1445419656378, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('34c18352-8dd0-44da-a3eb-ccf6297b0cad', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-22 09:44:49', 0, 0, 0, 0, 'site', 1445478289296, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('3609b9a8-0090-4bee-8bc2-632f0e103733', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-21 15:19:19', 0, 0, 0, 0, 'site', 1445411959250, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('3a42eddb-85ef-4f33-a48f-78da0c63898e', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-26 18:15:32', 0, 0, 0, 0, 'site', 1445854532081, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('3abba964-1673-451e-b019-3e4be2304a94', '192.168.0.103', 0, 0, '2015-10-27 13:58:08', 0, 0, 0, 0, 'site', 1445925488911, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('3b7717af-551d-4e99-8000-2e563d343473', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-24 13:25:59', 0, 0, 0, 0, 'site', 1445664359169, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('3e8c907a-5322-4fa1-ab3c-41d81fc6f304', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-21 17:55:28', 0, 0, 0, 0, 'site', 1445421328090, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('3fe7a7f8-2e7c-4057-aff2-59f92bf3a696', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-23 08:53:40', 0, 0, 0, 0, 'site', 1445561620883, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('4012cd07-a341-4978-a33d-1e19cf1d7478', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-29 09:18:08', 0, 0, 0, 0, 'site', 1446081488535, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('44593b35-4afb-421c-87cd-46ce685d7225', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-22 08:33:01', 0, 0, 0, 0, 'site', 1445473981316, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('50cc7c80-73ee-45eb-a1fa-a09e9877bdb3', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-22 13:34:34', 0, 0, 0, 0, 'site', 1445492074915, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('571ded1f-3cea-43f4-a8dd-42cc8770cf22', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-24 10:04:45', 0, 0, 0, 0, 'site', 1445652285068, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('5b68b21d-f1d9-4d4d-9cac-eb83b9b8836d', '127.0.0.1', 0, 0, '2015-10-21 15:05:52', 0, 0, 0, 0, 'site', 1445411152384, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('5dbf63d0-1afd-444f-a10f-42aacbe4dfad', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-27 12:02:49', 0, 0, 0, 0, 'site', 1445918569780, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('5f7fb590-0d44-48db-abc6-492ff9e23f98', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-21 16:27:19', 0, 0, 0, 0, 'site', 1445416039539, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('605bfb6c-e0fd-4846-af81-0c1c6832664a', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-23 13:42:49', 0, 0, 0, 0, 'site', 1445578969041, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('63720b0d-d336-4c97-8cd3-71f9e2c6a9a0', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-29 12:00:48', 0, 0, 0, 0, 'site', 1446091248578, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('6478c94a-741b-461b-838c-c2577f3d1e67', 'fe80:0:0:0:b981:6552:28d4:b8db', 0, 0, '2015-10-21 10:51:58', 0, 0, 0, 0, 'site', 1445395918196, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('65e2280b-1a9e-440f-b35b-55e508946719', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-23 14:08:13', 0, 0, 0, 0, 'site', 1445580493356, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('67d49d23-4e9c-4d92-a5b5-5902b585b71e', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-26 14:03:53', 0, 0, 0, 0, 'site', 1445839433947, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('6962bc21-a1fc-4cae-8a93-18711f188734', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-19 17:25:51', 0, 0, 0, 0, 'site', 1445246751417, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('6acc3f68-345a-40a2-aa3e-d70bfc3327bf', 'fe80:0:0:0:b981:6552:28d4:b8db', 0, 0, '2015-10-21 12:02:23', 0, 0, 0, 0, 'site', 1445400143913, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('6cc6daaa-da40-432d-9d38-75a4fc1317a0', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-28 11:20:06', 0, 0, 0, 0, 'site', 1446002406228, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('71f96cb8-c9f2-4e25-814a-cd4ecbea7f75', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-21 18:26:19', 0, 0, 0, 0, 'site', 1445423179462, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('7481ce18-9e3f-4871-8e18-6dcddd9b0064', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-28 15:09:19', 0, 0, 0, 0, 'site', 1446016159719, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('78358a9b-4d5f-49f7-b146-a316886eaac7', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-24 13:08:25', 0, 0, 0, 0, 'site', 1445663305728, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('7e460a07-9793-4ba4-897b-268055de5b24', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-20 17:24:01', 0, 0, 0, 0, 'site', 1445333041140, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('852fde47-4e80-4124-9df1-427699be5319', '192.168.0.103', 0, 0, '2015-10-27 09:46:52', 0, 0, 0, 0, 'site', 1445910412607, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('87010646-b4ab-4cf4-9c24-e48cdac7e693', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-22 17:05:07', 0, 0, 0, 0, 'site', 1445504707010, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('8a16469f-8900-4032-a30c-cde62b8f2b82', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-27 10:45:10', 0, 0, 0, 0, 'site', 1445913910974, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('8b16aed3-2d6b-4bc1-be14-fe5d97671a1c', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-24 16:07:49', 0, 0, 0, 0, 'site', 1445674069222, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('8b456573-adb1-4f22-8d3c-964213ded29a', 'fe80:0:0:0:b981:6552:28d4:b8db', 0, 0, '2015-10-21 10:36:37', 0, 0, 0, 0, 'site', 1445394997287, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('8c875b25-e3ea-41be-b28a-c15f6079b826', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-29 14:09:01', 0, 0, 0, 0, 'site', 1446098941743, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('8f933a09-7810-4437-bb43-00167a9d8a60', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-24 10:40:00', 0, 0, 0, 0, 'site', 1445654400885, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('9414e768-6198-42f5-b024-a86837d5930b', 'fe80:0:0:0:b981:6552:28d4:b8db', 0, 0, '2015-10-21 10:24:59', 0, 0, 0, 0, 'site', 1445394299008, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('96a8f66d-18b7-4ece-9c3a-85a7b2fe427b', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-23 10:57:19', 0, 0, 0, 0, 'site', 1445569039902, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('9a8ac04b-cdfc-419e-8a85-1e0a60051b49', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-29 15:30:15', 0, 0, 0, 0, 'site', 1446103815715, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('9c0087f5-607b-4015-bb0c-20696e003122', 'fe80:0:0:0:b981:6552:28d4:b8db', 0, 0, '2015-10-21 09:51:51', 0, 0, 0, 0, 'site', 1445392311907, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('a00c8072-f9d7-4755-a304-63aa0f6bef82', '192.168.0.103', 0, 0, '2015-10-27 13:58:08', 0, 0, 0, 0, 'site', 1445925488098, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('a03af7a7-c581-49f3-b118-35368926d912', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-29 14:36:51', 0, 0, 0, 0, 'site', 1446100611386, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('a04d2d52-7735-4eb8-87e3-b415e19eb960', 'fe80:0:0:0:b981:6552:28d4:b8db', 0, 0, '2015-10-21 11:27:57', 0, 0, 0, 0, 'site', 1445398077750, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('a4d190dc-6f76-4e30-a95a-1c3d0db21640', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-22 08:48:02', 0, 0, 0, 0, 'site', 1445474882455, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('aa030e1b-7ce9-4fe0-93f6-df3ee1d9a78f', '192.168.0.103', 0, 0, '2015-10-27 18:59:52', 0, 0, 0, 0, 'site', 1445943592714, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('ab6e2cb5-a1da-4a5e-a22b-819341818dc1', 'fe80:0:0:0:b981:6552:28d4:b8db', 0, 0, '2015-10-21 11:01:04', 0, 0, 0, 0, 'site', 1445396464735, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('b005cf23-c16f-45bc-a3cf-1068a6c69d7d', 'fe80:0:0:0:b981:6552:28d4:b8db', 0, 0, '2015-10-21 11:41:36', 0, 0, 0, 0, 'site', 1445398896395, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('b1047b64-713f-462f-a850-3a7614deb7e7', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-21 16:26:18', 0, 0, 0, 0, 'site', 1445415978439, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('b24e20f4-f88d-4a21-916a-6584932ea94f', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-24 15:07:32', 0, 0, 0, 0, 'site', 1445670452650, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('b50c70d8-ab18-4137-92b1-fa228db57ac8', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-23 17:45:38', 0, 0, 0, 0, 'site', 1445593538095, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('b563a80a-b73f-46cf-a262-3f2ca9cb3ede', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-28 15:40:03', 0, 0, 0, 0, 'site', 1446018003404, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('b88a22c1-6a81-4d6e-873b-c49124bd89b6', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-21 14:01:44', 0, 0, 0, 0, 'site', 1445407304121, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('b9cd8f73-38ca-4127-aa55-a4e13f87ba5d', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-23 18:46:02', 0, 0, 0, 0, 'site', 1445597162316, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('b9ceae04-abb8-48c9-83d5-0afbeafa6ac8', '192.168.0.103', 0, 0, '2015-10-27 16:15:20', 0, 0, 0, 0, 'site', 1445933720161, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('baf54258-dd36-4370-8ea2-86649812aa49', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-22 09:48:39', 0, 0, 0, 0, 'site', 1445478519769, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('bce9e5aa-b315-492d-a733-6b390c8cc314', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-28 10:41:48', 0, 0, 0, 0, 'site', 1446000108596, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('c0df2167-19b3-4e85-bf3f-2f5a1f9de0ad', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-23 17:04:18', 0, 0, 0, 0, 'site', 1445591058905, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('c310d658-80de-44c2-9a61-5ca96ed6ef1e', 'fe80:0:0:0:b981:6552:28d4:b8db', 0, 0, '2015-10-22 15:40:23', 0, 0, 0, 0, 'site', 1445499623244, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('c9e2acf4-d25d-48af-8134-86c4292fbc3a', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-22 11:51:17', 0, 0, 0, 0, 'site', 1445485877287, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('ca7628fc-fe7c-4451-a3b0-7d846a32e038', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-28 17:12:01', 0, 0, 0, 0, 'site', 1446023521947, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('ca841686-cd95-4ec3-81c5-e41726d889db', 'fe80:0:0:0:b981:6552:28d4:b8db', 0, 0, '2015-10-22 14:12:16', 0, 0, 0, 0, 'site', 1445494336352, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('ce0f4fce-d8ef-4fa5-b7f2-be3f2ff77737', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-20 18:02:53', 0, 0, 0, 0, 'site', 1445335373779, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('ce1d6115-bc30-4c73-8859-6b5f4209d1a7', '192.168.0.103', 0, 0, '2015-10-27 14:34:11', 0, 0, 0, 0, 'site', 1445927651599, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('ce233c4c-e090-4868-a305-2c7113931079', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-21 16:59:16', 0, 0, 0, 0, 'site', 1445417956987, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('d38b653a-d1cf-42c5-80ae-65e0f275a44e', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-26 13:14:28', 0, 0, 0, 0, 'site', 1445836468112, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('d4e03da9-5ed3-48fd-8851-b62d1110eff8', '192.168.0.103', 0, 0, '2015-10-27 17:41:24', 0, 0, 0, 0, 'site', 1445938884497, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('d66af2e4-05c6-472f-9469-dd81c43767c2', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-27 10:57:35', 0, 0, 0, 0, 'site', 1445914655024, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('d79f5020-ebf5-411d-8805-b066d55fd946', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-26 13:42:50', 0, 0, 0, 0, 'site', 1445838170452, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('d8f342ec-1f29-4679-8db2-6c1e595813af', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-24 11:29:43', 0, 0, 0, 0, 'site', 1445657383372, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('da417256-a4e3-485d-8650-463d702f220d', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-22 13:01:20', 0, 0, 0, 0, 'site', 1445490080240, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('dd5af65d-1df4-40b0-9fc5-63d35429f82d', 'fe80:0:0:0:b981:6552:28d4:b8db', 0, 0, '2015-10-22 17:43:06', 0, 0, 0, 0, 'site', 1445506986874, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('e0537d57-3a19-43ae-a556-c4f856c2f9ae', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-21 17:32:15', 0, 0, 0, 0, 'site', 1445419935409, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('e4910cf2-17cc-4b09-8f1f-f54bde6c0793', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-23 16:12:56', 0, 0, 0, 0, 'site', 1445587976948, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('e90889b5-e9e0-4458-9d88-5025b0bb8b5a', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-24 09:46:07', 0, 0, 0, 0, 'site', 1445651167290, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('e928f388-5bfc-44be-a6f9-7292a2b7b117', '192.168.0.103', 0, 0, '2015-10-27 18:20:08', 0, 0, 0, 0, 'site', 1445941208074, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('eb52958f-7f81-4630-a020-0badb10147c1', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-23 17:25:30', 0, 0, 0, 0, 'site', 1445592330441, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('ecc01ef5-2dd5-43f5-83a8-1e01f1666143', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-26 16:52:32', 0, 0, 0, 0, 'site', 1445849552890, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('ecfc7661-9a6e-4a8f-902e-4fbf5917214a', 'fe80:0:0:0:b981:6552:28d4:b8db', 0, 0, '2015-10-20 17:26:12', 0, 0, 0, 0, 'site', 1445333172579, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('ed25b6ae-06f3-4930-b028-cedfb2cac4e3', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-23 13:09:53', 0, 0, 0, 0, 'site', 1445576993858, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('f0627531-19ef-4977-99a1-19dbe82622cc', '192.168.191.1', 0, 0, '2015-10-25 22:18:23', 0, 0, 0, 0, 'site', 1445782703397, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('f095743d-4e2c-4a9f-ac14-911781ebefcd', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-21 16:25:47', 0, 0, 0, 0, 'site', 1445415947585, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('f2b80761-923c-445a-9300-e3131936b0e0', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-21 17:53:37', 0, 0, 0, 0, 'site', 1445421217330, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('f2dd9a5c-ff0c-401a-8e16-223d214e44f5', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-26 09:20:34', 0, 0, 0, 0, 'site', 1445822434377, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('f3ee2aab-239b-46d2-9dd5-11a2d016a73c', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-24 09:17:23', 0, 0, 0, 0, 'site', 1445649443384, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('f4571bdc-cfe3-40c5-961b-c933c8194d14', '192.168.0.103', 0, 0, '2015-10-27 19:21:40', 0, 0, 0, 0, 'site', 1445944900236, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('f576ee62-3555-4827-b3d3-6635bdae3696', '192.168.191.1', 0, 0, '2015-10-25 19:52:27', 0, 0, 0, 0, 'site', 1445773947100, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('f6f27ba6-8199-4010-ac06-a3695c100743', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-21 17:56:13', 0, 0, 0, 0, 'site', 1445421373299, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('f8728dbc-a949-4ebe-90e0-85684cb27f6f', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-29 13:41:53', 0, 0, 0, 0, 'site', 1446097313069, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('f947a6b8-8c7a-4390-850f-377c1ed6663f', '192.168.0.103', 0, 0, '2015-10-27 15:46:25', 0, 0, 0, 0, 'site', 1445931985937, '8dea01b1-9bfd-4635-be39-c1fa6438b85b'),
	('fc36cfa9-9d62-4b25-aaa6-ff6149e9b5e1', '0:0:0:0:0:0:0:1', 0, 0, '2015-10-23 16:13:24', 0, 0, 0, 0, 'site', 1445588004274, '8dea01b1-9bfd-4635-be39-c1fa6438b85b');
/*!40000 ALTER TABLE `ticket_systemuserloginlog` ENABLE KEYS */;


-- 导出  表 ticket.ticket_systemuseroperation 结构
CREATE TABLE IF NOT EXISTS `ticket_systemuseroperation` (
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

-- 正在导出表  ticket.ticket_systemuseroperation 的数据：~0 rows (大约)
DELETE FROM `ticket_systemuseroperation`;
/*!40000 ALTER TABLE `ticket_systemuseroperation` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_systemuseroperation` ENABLE KEYS */;


-- 导出  表 ticket.ticket_systemversion 结构
CREATE TABLE IF NOT EXISTS `ticket_systemversion` (
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

-- 正在导出表  ticket.ticket_systemversion 的数据：~0 rows (大约)
DELETE FROM `ticket_systemversion`;
/*!40000 ALTER TABLE `ticket_systemversion` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_systemversion` ENABLE KEYS */;


-- 导出  表 ticket.ticket_userrole 结构
CREATE TABLE IF NOT EXISTS `ticket_userrole` (
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

-- 正在导出表  ticket.ticket_userrole 的数据：~2 rows (大约)
DELETE FROM `ticket_userrole`;
/*!40000 ALTER TABLE `ticket_userrole` DISABLE KEYS */;
INSERT INTO `ticket_userrole` (`id`, `roleId`, `audit`, `commend`, `createTime`, `deleteFlag`, `hits`, `hot`, `orderValue`, `versionFlag`, `visitUrl`, `userId`) VALUES
	('0c3f607c-6b7e-4f6f-8c13-1dbbff7192f4', '442941c4-17f1-482f-94d8-d9d39860712d', 0, 0, '2015-10-22 14:17:25', 0, 0, 0, 0, 'site', 1445494645124, '18ac871e-2669-4933-ae2f-04c4e5cc8192'),
	('17633903-57b9-41a5-918c-1034d179eccb', '2a1dcfe2-529c-4807-92bb-5f9a63dec061', 0, 0, '2015-10-21 17:00:03', 0, 0, 0, 0, 'site', 1445418003828, '8dea01b1-9bfd-4635-be39-c1fa6438b85b');
/*!40000 ALTER TABLE `ticket_userrole` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
