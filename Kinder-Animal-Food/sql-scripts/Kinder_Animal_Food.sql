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






DROP TABLE IF EXISTS Store;
CREATE TABLE Store(
store_id INTEGER PRIMARY KEY AUTO_INCREMENT,
store_name VARCHAR(255) NOT NULL,
store_address VARCHAR(255),
store_state VARCHAR(255),
store_longitude VARCHAR(255),
store_latitude VARCHAR(255),
brands_num INTEGER(10)
)AUTO_INCREMENT = 1;
INSERT INTO Store VALUES ("123","Coles","1 King St","NSW","151","33",20);

DROP TABLE IF EXISTS Brand;
CREATE TABLE Brand(
brand_id INTEGER PRIMARY KEY AUTO_INCREMENT,
brand_name VARCHAR(255) NOT NULL,
brand_category VARCHAR(255),
image VARCHAR(255)
)AUTO_INCREMENT = 1;
INSERT INTO Brand VALUES ("123","A egg","egg","c://image");