-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 27, 2015 at 03:30 PM
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
-- Table structure for table `collaborator`
--

CREATE TABLE IF NOT EXISTS `collaborator` (
  `user_id` bigint(20) DEFAULT NULL,
  `diagram_id` bigint(20) DEFAULT NULL,
  KEY `FK_Reference_2` (`user_id`),
  KEY `FK_Reference_3` (`diagram_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `diagram_status`
--

CREATE TABLE IF NOT EXISTS `diagram_status` (
  `status_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `status_type` varchar(256) COLLATE utf8_general_mysql500_ci NOT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci AUTO_INCREMENT=1 ;

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
-- Table structure for table `history`
--

CREATE TABLE IF NOT EXISTS `history` (
  `event_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  KEY `FK_Reference_6` (`event_id`),
  KEY `FK_Reference_7` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

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
(1, 'Maxim Semikin', 'maxim.semikin@gmail.com', '$2a$10$VeR2bSE78J60m.wG8kcE5.LaAqx2Tb/Gp/SFA3s1u114RBshoVyFK', '2015-11-27 15:28:45', '2015-11-27 15:28:45');

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE IF NOT EXISTS `user_role` (
  `role_id` bigint(20) NOT NULL,
  `role` varchar(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`role_id`, `role`, `user_id`) VALUES
(1, 'ROLE_USER', 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `collaborator`
--
ALTER TABLE `collaborator`
  ADD CONSTRAINT `FK_Reference_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_Reference_3` FOREIGN KEY (`diagram_id`) REFERENCES `diagram` (`diagram_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `diagram`
--
ALTER TABLE `diagram`
  ADD CONSTRAINT `FK_Reference_1` FOREIGN KEY (`owner_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `FK_Reference_4` FOREIGN KEY (`status_id`) REFERENCES `diagram_status` (`status_id`);

--
-- Constraints for table `event`
--
ALTER TABLE `event`
  ADD CONSTRAINT `FK_Reference_5` FOREIGN KEY (`event_type_id`) REFERENCES `eventtype` (`event_type_id`);

--
-- Constraints for table `history`
--
ALTER TABLE `history`
  ADD CONSTRAINT `FK_Reference_6` FOREIGN KEY (`event_id`) REFERENCES `event` (`event_id`),
  ADD CONSTRAINT `FK_Reference_7` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `token`
--
ALTER TABLE `token`
  ADD CONSTRAINT `forket` FOREIGN KEY (`userId`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
