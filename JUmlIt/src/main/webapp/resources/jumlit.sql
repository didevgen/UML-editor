-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 04, 2015 at 04:25 PM
-- Server version: 5.6.27-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `jumlit`
--

-- --------------------------------------------------------

--
-- Table structure for table `argument`
--

CREATE TABLE IF NOT EXISTS `argument` (
  `argument_id` int(11) NOT NULL AUTO_INCREMENT,
  `argument_name` varchar(105) DEFAULT NULL,
  `argument_type` varchar(105) DEFAULT NULL,
  `method_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`argument_id`),
  KEY `argument_to_method_idx` (`method_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `class`
--

CREATE TABLE IF NOT EXISTS `class` (
  `class_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(45) DEFAULT NULL,
  `is_static` tinyint(1) DEFAULT NULL,
  `diagram_id` bigint(20) DEFAULT NULL,
  `class_access` varchar(105) DEFAULT NULL,
  `class_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`class_id`),
  KEY `class_to_diagram_idx` (`diagram_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=18 ;

--
-- Dumping data for table `class`
--

INSERT INTO `class` (`class_id`, `class_name`, `is_static`, `diagram_id`, `class_access`, `class_type`) VALUES
(1, 'MyClazz', 0, 4, 'private', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `collaborator`
--

CREATE TABLE IF NOT EXISTS `collaborator` (
  `user_id` bigint(20) NOT NULL DEFAULT '0',
  `diagram_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`,`diagram_id`),
  KEY `FK_Reference_3` (`diagram_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

--
-- Dumping data for table `collaborator`
--

INSERT INTO `collaborator` (`user_id`, `diagram_id`) VALUES
(3, 4),
(1, 7),
(1, 8);

-- --------------------------------------------------------

--
-- Table structure for table `diagram`
--

CREATE TABLE IF NOT EXISTS `diagram` (
  `diagram_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `owner_id` bigint(20) DEFAULT NULL,
  `status_id` bigint(20) DEFAULT NULL,
  `json_data` varchar(2056) COLLATE utf8_general_mysql500_ci NOT NULL,
  `created_date` datetime NOT NULL,
  `last_updated` datetime NOT NULL,
  `name` varchar(256) COLLATE utf8_general_mysql500_ci DEFAULT NULL,
  `description` varchar(3000) COLLATE utf8_general_mysql500_ci DEFAULT NULL,
  PRIMARY KEY (`diagram_id`),
  KEY `FK_Reference_1` (`owner_id`),
  KEY `FK_Reference_4` (`status_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci AUTO_INCREMENT=10 ;

--
-- Dumping data for table `diagram`
--

INSERT INTO `diagram` (`diagram_id`, `owner_id`, `status_id`, `json_data`, `created_date`, `last_updated`, `name`, `description`) VALUES
(3, 4, -1, '', '2015-11-27 23:33:12', '2015-11-28 23:50:50', 'Diagram1', 'MyDiagram'),
(4, 4, -1, '', '2015-11-27 23:33:12', '2015-11-27 23:33:12', 'Diagram2', 'MyDiagram2'),
(7, 5, -1, '', '2015-12-04 16:22:33', '2015-12-04 16:22:33', 'fsdafads', 'fadsfsd'),
(8, 5, -1, '', '2015-12-04 16:23:00', '2015-12-04 16:23:00', 'adsfd', 'asdf');

-- --------------------------------------------------------

--
-- Table structure for table `diagram_status`
--

CREATE TABLE IF NOT EXISTS `diagram_status` (
  `status_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `status_type` varchar(256) COLLATE utf8_general_mysql500_ci NOT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci AUTO_INCREMENT=1 ;

--
-- Dumping data for table `diagram_status`
--

INSERT INTO `diagram_status` (`status_id`, `status_type`) VALUES
(-1, 'Normal');

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

CREATE TABLE IF NOT EXISTS `event` (
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `event_type_id` bigint(20) DEFAULT NULL,
  `timestamp` datetime NOT NULL,
  PRIMARY KEY (`event_id`),
  KEY `FK_Reference_5` (`event_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `eventtype`
--

CREATE TABLE IF NOT EXISTS `eventtype` (
  `event_type_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(256) COLLATE utf8_general_mysql500_ci NOT NULL,
  PRIMARY KEY (`event_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `field`
--

CREATE TABLE IF NOT EXISTS `field` (
  `field_id` int(11) NOT NULL AUTO_INCREMENT,
  `field_access` varchar(105) COLLATE utf8_general_mysql500_ci DEFAULT NULL,
  `is_static` tinyint(1) DEFAULT NULL,
  `field_name` varchar(70) COLLATE utf8_general_mysql500_ci DEFAULT NULL,
  `field_type` varchar(45) COLLATE utf8_general_mysql500_ci DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`field_id`),
  KEY `field_to_class_idx` (`class_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Table structure for table `history`
--

CREATE TABLE IF NOT EXISTS `history` (
  `event_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  KEY `FK_Reference_7` (`user_id`),
  KEY `FK_Reference_6` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

-- --------------------------------------------------------

--
-- Table structure for table `method`
--

CREATE TABLE IF NOT EXISTS `method` (
  `method_id` int(11) NOT NULL AUTO_INCREMENT,
  `is_static` tinyint(1) DEFAULT NULL,
  `method_name` varchar(105) DEFAULT NULL,
  `return_type` varchar(105) DEFAULT NULL,
  `method_access` varchar(105) DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`method_id`),
  KEY `method_to_class_idx` (`class_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Table structure for table `position`
--

CREATE TABLE IF NOT EXISTS `position` (
  `x` int(11) DEFAULT NULL,
  `y` int(11) DEFAULT NULL,
  `class_id` int(11) NOT NULL,
  PRIMARY KEY (`class_id`),
  KEY `position_to_class_idx` (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

--
-- Dumping data for table `position`
--

INSERT INTO `position` (`x`, `y`, `class_id`) VALUES
(508, 99, 1);

-- --------------------------------------------------------

--
-- Table structure for table `token`
--

CREATE TABLE IF NOT EXISTS `token` (
  `tokenId` int(11) NOT NULL AUTO_INCREMENT,
  `tokenValue` varchar(45) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`tokenId`),
  KEY `forket_idx` (`userId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `token`
--

INSERT INTO `token` (`tokenId`, `tokenValue`, `userId`) VALUES
(3, 'ebeb300882677f350ea818c8f333f5b9', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(256) COLLATE utf8_general_mysql500_ci NOT NULL,
  `email` varchar(256) COLLATE utf8_general_mysql500_ci NOT NULL,
  `password` varchar(256) COLLATE utf8_general_mysql500_ci NOT NULL,
  `registration_date` datetime NOT NULL,
  `last_available` datetime NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci AUTO_INCREMENT=6 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `full_name`, `email`, `password`, `registration_date`, `last_available`) VALUES
(1, 'Евгений Ковалев', 'didevgen@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '2015-11-13 12:36:22', '2015-11-13 12:36:22'),
(2, 'jdhsjfhs', 'selekh@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '2015-11-20 17:11:05', '2015-11-20 17:11:05'),
(3, 'Евгений Ковалев', 'evgenijkovaljov@rambler.ru', '$2a$10$/W5EF0lDedW6PBG6r5lH6evepgOMslKTme7aK0Ba8bInK3jimT2aO', '2015-11-27 22:21:19', '2015-11-27 22:21:19'),
(4, 'Василий Теркин', 'terkin@gmail.com', '$2a$10$gh2QTC1TelYAvAKPsKvJjewN0UZFDOC8yp57HYVBxLfV.pmfyOrWu', '2015-11-27 23:32:46', '2015-11-27 23:32:46'),
(5, 'Maxim Semikin', 'max.semikin@gmail.com', '$2a$10$m3SAzRRDv9xXomjjAMQVNOku1jGxyLCiQX28KGCp4EDeX9prUEWCK', '2015-12-03 22:06:14', '2015-12-03 22:06:14');

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE IF NOT EXISTS `user_role` (
  `role_id` bigint(20) NOT NULL,
  `role` varchar(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`role_id`, `role`, `user_id`) VALUES
(1, 'ROLE_USER', 1),
(2, 'ROLE_USER', 2),
(3, 'ROLE_USER', 3),
(4, 'ROLE_USER', 4),
(5, 'ROLE_USER', 5);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `argument`
--
ALTER TABLE `argument`
  ADD CONSTRAINT `argument_to_method` FOREIGN KEY (`method_id`) REFERENCES `method` (`method_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `class`
--
ALTER TABLE `class`
  ADD CONSTRAINT `class_to_diagram` FOREIGN KEY (`diagram_id`) REFERENCES `diagram` (`diagram_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `diagram`
--
ALTER TABLE `diagram`
  ADD CONSTRAINT `FK_Reference_1` FOREIGN KEY (`owner_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_Reference_4` FOREIGN KEY (`status_id`) REFERENCES `diagram_status` (`status_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `event`
--
ALTER TABLE `event`
  ADD CONSTRAINT `FK_Reference_5` FOREIGN KEY (`event_type_id`) REFERENCES `eventtype` (`event_type_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `field`
--
ALTER TABLE `field`
  ADD CONSTRAINT `field_to_class` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `history`
--
ALTER TABLE `history`
  ADD CONSTRAINT `FK_Reference_6` FOREIGN KEY (`event_id`) REFERENCES `event` (`event_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_Reference_7` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `method`
--
ALTER TABLE `method`
  ADD CONSTRAINT `method_to_class` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `position`
--
ALTER TABLE `position`
  ADD CONSTRAINT `position_to_class` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `token`
--
ALTER TABLE `token`
  ADD CONSTRAINT `forket` FOREIGN KEY (`userId`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
