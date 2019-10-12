DROP DATABASE  IF EXISTS `Kinder-Animal-Food`;

CREATE DATABASE  IF NOT EXISTS `Kinder-Animal-Food`;
USE `Kinder-Animal-Food`;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` char(80) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `birthdate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roles`
--

INSERT INTO `role` (name)
VALUES 
('ROLE_USER'),('ROLE_ADMIN');

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  
  PRIMARY KEY (`user_id`,`role_id`),
  
  KEY `FK_ROLE_idx` (`role_id`),
  
  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) 
  REFERENCES `role` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `user` VALUES (1,'admin','$2a$10$jHv51I.opW58VUwKDoz0EOapAYJkwUXhbtseuFLRMiejlGXP1J4ui','Not disclose','1992-10-26');
INSERT INTO `users_roles` VALUES (1,1);
INSERT INTO `users_roles` VALUES (1,2);

--
-- Table structure for table `Accreditation`
--

DROP TABLE IF EXISTS `Accreditation`;
CREATE TABLE `Accreditation` (
  `accreditation_id` int(10) NOT NULL AUTO_INCREMENT,
  `accreditation_name` varchar(255) NOT NULL,
  `rating` varchar(5) NOT NULL,

  PRIMARY KEY (`accreditation_id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `Accreditation` VALUES 
(1, 'Cage Caged', 'good'), (2, 'New Cage caged', 'avoid');


--
-- Table structure for table `Brand`
--

DROP TABLE IF EXISTS `Brand`;
CREATE TABLE `Brand` (
  `brand_id` int(10) NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(255) NOT NULL,
  `brand_category` varchar(255) NOT NULL,
  `accreditation_id` int(10) NOT NULL,
  `image` varchar(255) NOT NULL,

  PRIMARY KEY (`brand_id`),

  CONSTRAINT `FK_Accreditation` FOREIGN KEY (`accreditation_id`) 
  REFERENCES `Accreditation` (`accreditation_id`) 
  ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `Brand` VALUES ('1', 'A egg', 'egg', 1, 'c://image1');
INSERT INTO `Brand` VALUES ('2', 'B egg', 'egg', 2, 'c://image2');
INSERT INTO `Brand` VALUES ('3', 'A egg', 'egg', 2, 'c://image1');
INSERT INTO `Brand` VALUES ('4', 'B egg', 'egg', 1, 'c://image2');


--
-- Table structure for table `Store`
--

DROP TABLE IF EXISTS `Store`;
CREATE TABLE `Store` (
  `store_id` int(10) NOT NULL AUTO_INCREMENT,
  `store_name` varchar(255) NOT NULL,
  `store_address` varchar(255) NOT NULL,
  `store_state` varchar(255) NOT NULL,
  `store_longitude` varchar(255) NOT NULL,
  `store_latitude` varchar(255) NOT NULL,
  `brands_num` int(10) NOT NULL,

  PRIMARY KEY (`store_id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `Store` VALUES ('1', 'Coles', '1 King St', 'NSW', '151', '33', 2);
INSERT INTO `Store` VALUES ('2', 'WW', '2 King St', 'NSW', '152', '34', 2);


--
-- Table structure for table `Product`
--

DROP TABLE IF EXISTS `Product`;
CREATE TABLE `Product`(
  `product_id` int (10) NOT NULL AUTO_INCREMENT,
  `store_id` int(10) NOT NULL,
  `brand_id` int(10) NOT NULL,

  PRIMARY KEY (`product_id`),

  CONSTRAINT `FK_Store` FOREIGN KEY (`store_id`) 
  REFERENCES `Store` (`store_id`) 
  ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT `FK_Brand` FOREIGN KEY (`brand_id`) 
  REFERENCES `Brand` (`brand_id`) 
  ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `Product` (`store_id`, `brand_id`) VALUES ('1', '1');
INSERT INTO `Product` (`store_id`, `brand_id`) VALUES ('1', '2');
INSERT INTO `Product` (`store_id`, `brand_id`) VALUES ('2', '1');
INSERT INTO `Product` (`store_id`, `brand_id`) VALUES ('2', '2');

