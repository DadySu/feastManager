/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.22 : Database - feast
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`feast` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `feast`;

/*Table structure for table `g_good` */

DROP TABLE IF EXISTS `g_good`;

CREATE TABLE `g_good` (
  `id` bigint(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) DEFAULT NULL COMMENT '商品名称',
  `price` decimal(10,0) DEFAULT NULL COMMENT '商品价格',
  `oldPrice` decimal(10,0) DEFAULT NULL COMMENT '商品原价格',
  `sellCount` smallint(5) DEFAULT NULL COMMENT '销售数量',
  `rating` smallint(5) DEFAULT NULL COMMENT '评论数',
  `info` varchar(100) DEFAULT NULL COMMENT '商品描述',
  `good_kind_id` bigint(8) DEFAULT NULL COMMENT '商品种类id',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL COMMENT '商品简介',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `g_good` */

insert  into `g_good`(`id`,`name`,`price`,`oldPrice`,`sellCount`,`rating`,`info`,`good_kind_id`,`create_time`,`update_time`,`description`) values (1,'皮蛋瘦肉粥','10',NULL,229,100,'一碗皮蛋瘦肉粥，总是我到粥店时的不二之选。香浓软滑，饱腹暖心，皮蛋的Q弹与瘦肉的滑嫩伴着粥香溢于满口，让人喝这样的一碗粥也觉得心满意足',1,NULL,NULL,'咸粥'),(2,'扁豆焖面','14',NULL,188,96,NULL,1,NULL,NULL,NULL),(3,'葱花饼','10',NULL,124,85,NULL,1,NULL,NULL,NULL),(4,'牛肉馅饼','14',NULL,114,91,NULL,1,NULL,NULL,NULL),(5,'招牌猪肉白菜锅贴/10个','17',NULL,101,78,NULL,1,NULL,NULL,NULL),(6,'红枣山药粥套餐','29','36',17,5,NULL,2,NULL,NULL,'红枣山药糙米粥,素材包,爽口莴笋丝,四川泡菜或八宝酱菜,配菜可备注'),(7,'红豆薏米粥套餐','37',NULL,301,100,NULL,3,NULL,NULL,'红豆薏米粥,三鲜干蒸烧卖,拍黄瓜');

/*Table structure for table `g_goods_kind` */

DROP TABLE IF EXISTS `g_goods_kind`;

CREATE TABLE `g_goods_kind` (
  `id` bigint(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) DEFAULT NULL COMMENT '菜单名称',
  `typed` tinyint(2) DEFAULT NULL COMMENT '菜单类别',
  `merchant_id` bigint(8) DEFAULT NULL COMMENT '商户id',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `g_goods_kind` */

insert  into `g_goods_kind`(`id`,`name`,`typed`,`merchant_id`,`update_time`,`create_time`) values (1,'热销榜',-1,123,'2018-12-02 12:17:47','2018-12-01 19:40:20'),(2,'单人精彩套餐',2,123,'2018-12-02 12:17:47','2018-12-01 19:40:20'),(3,'冰爽饮品限时特惠',1,123,'2018-12-02 12:17:47','2018-12-01 19:40:20'),(4,'精选热菜',-1,123,'2018-12-02 12:17:47','2018-12-01 19:40:20'),(5,'爽口凉菜',-1,123,'2018-12-02 12:17:47','2018-12-01 19:40:20'),(6,NULL,NULL,NULL,'2018-12-02 12:17:47','2018-12-01 19:40:20');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
