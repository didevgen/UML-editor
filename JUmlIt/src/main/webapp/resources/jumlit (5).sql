-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Дек 14 2015 г., 17:17
-- Версия сервера: 5.6.26
-- Версия PHP: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `jumlit`
--

-- --------------------------------------------------------

--
-- Структура таблицы `argument`
--

CREATE TABLE IF NOT EXISTS `argument` (
  `argument_id` int(11) NOT NULL,
  `argument_name` varchar(105) DEFAULT NULL,
  `argument_type` varchar(105) DEFAULT NULL,
  `method_id` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `argument`
--

INSERT INTO `argument` (`argument_id`, `argument_name`, `argument_type`, `method_id`) VALUES
(2, 'typeName', 'MyType', 2);

-- --------------------------------------------------------

--
-- Структура таблицы `class`
--

CREATE TABLE IF NOT EXISTS `class` (
  `class_id` int(11) NOT NULL,
  `class_name` varchar(45) DEFAULT NULL,
  `is_static` tinyint(1) DEFAULT NULL,
  `diagram_id` bigint(20) DEFAULT NULL,
  `class_access` varchar(105) DEFAULT NULL,
  `class_type` varchar(45) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `class`
--

INSERT INTO `class` (`class_id`, `class_name`, `is_static`, `diagram_id`, `class_access`, `class_type`) VALUES
(10, 'Class1', 0, 8, 'public', 'Class'),
(18, 'MyClass', 0, 5, 'public', 'Class'),
(19, 'Class2', 0, 5, 'public', 'Class');

-- --------------------------------------------------------

--
-- Структура таблицы `collaborator`
--

CREATE TABLE IF NOT EXISTS `collaborator` (
  `user_id` bigint(20) NOT NULL DEFAULT '0',
  `diagram_id` bigint(20) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

--
-- Дамп данных таблицы `collaborator`
--

INSERT INTO `collaborator` (`user_id`, `diagram_id`) VALUES
(3, 4),
(6, 5),
(5, 6),
(6, 6),
(1, 7),
(3, 7),
(6, 8);

-- --------------------------------------------------------

--
-- Структура таблицы `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `comment_id` int(11) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `diagram_id` bigint(20) DEFAULT NULL,
  `comment_text` longtext,
  `timestamp` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `diagram`
--

CREATE TABLE IF NOT EXISTS `diagram` (
  `diagram_id` bigint(20) NOT NULL,
  `owner_id` bigint(20) DEFAULT NULL,
  `status_id` bigint(20) DEFAULT NULL,
  `json_data` varchar(2056) COLLATE utf8_general_mysql500_ci NOT NULL,
  `created_date` datetime NOT NULL,
  `last_updated` datetime NOT NULL,
  `name` varchar(256) COLLATE utf8_general_mysql500_ci DEFAULT NULL,
  `description` varchar(3000) COLLATE utf8_general_mysql500_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

--
-- Дамп данных таблицы `diagram`
--

INSERT INTO `diagram` (`diagram_id`, `owner_id`, `status_id`, `json_data`, `created_date`, `last_updated`, `name`, `description`) VALUES
(5, 3, -1, '', '2015-12-04 17:26:25', '2015-12-14 12:17:28', 'Diagram', 'edfsdfs'),
(6, 3, -1, '', '2015-12-13 00:56:12', '2015-12-13 01:23:11', 'Diagram1111', 'ываывааыв'),
(7, 6, -1, '', '2015-12-13 01:04:27', '2015-12-13 01:10:07', 'Eugene', 'выаывыа'),
(8, 3, -1, '', '2015-12-14 12:20:11', '2015-12-14 12:20:11', 'TestDiagramWithCollabs', 'For collaborators');

-- --------------------------------------------------------

--
-- Структура таблицы `diagram_status`
--

CREATE TABLE IF NOT EXISTS `diagram_status` (
  `status_id` bigint(20) NOT NULL,
  `status_type` varchar(256) COLLATE utf8_general_mysql500_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

--
-- Дамп данных таблицы `diagram_status`
--

INSERT INTO `diagram_status` (`status_id`, `status_type`) VALUES
(-1, 'Normal');

-- --------------------------------------------------------

--
-- Структура таблицы `event`
--

CREATE TABLE IF NOT EXISTS `event` (
  `event_id` bigint(20) NOT NULL,
  `event_type_id` bigint(20) DEFAULT NULL,
  `timestamp` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `eventtype`
--

CREATE TABLE IF NOT EXISTS `eventtype` (
  `event_type_id` bigint(20) NOT NULL,
  `description` varchar(256) COLLATE utf8_general_mysql500_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `field`
--

CREATE TABLE IF NOT EXISTS `field` (
  `field_id` int(11) NOT NULL,
  `field_access` varchar(105) COLLATE utf8_general_mysql500_ci DEFAULT NULL,
  `is_static` tinyint(1) DEFAULT NULL,
  `field_name` varchar(70) COLLATE utf8_general_mysql500_ci DEFAULT NULL,
  `field_type` varchar(45) COLLATE utf8_general_mysql500_ci DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

--
-- Дамп данных таблицы `field`
--

INSERT INTO `field` (`field_id`, `field_access`, `is_static`, `field_name`, `field_type`, `class_id`) VALUES
(1, 'public', 0, 'mystaticField', 'MyType', 19);

-- --------------------------------------------------------

--
-- Структура таблицы `history`
--

CREATE TABLE IF NOT EXISTS `history` (
  `event_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `history_session`
--

CREATE TABLE IF NOT EXISTS `history_session` (
  `session_id` int(11) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `time_start` datetime DEFAULT NULL,
  `time_finish` datetime DEFAULT NULL,
  `diagram_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `history_session`
--

INSERT INTO `history_session` (`session_id`, `user_id`, `time_start`, `time_finish`, `diagram_id`) VALUES
(1, 3, '2015-12-14 17:49:18', '2015-12-14 17:49:22', 5);

-- --------------------------------------------------------

--
-- Структура таблицы `method`
--

CREATE TABLE IF NOT EXISTS `method` (
  `method_id` int(11) NOT NULL,
  `is_static` tinyint(1) DEFAULT NULL,
  `method_name` varchar(105) DEFAULT NULL,
  `return_type` varchar(105) DEFAULT NULL,
  `method_access` varchar(105) DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `method`
--

INSERT INTO `method` (`method_id`, `is_static`, `method_name`, `return_type`, `method_access`, `class_id`) VALUES
(2, 0, 'doSomething', 'void', 'public', 18),
(3, 0, 'MyBestName', 'void', 'public', 19);

-- --------------------------------------------------------

--
-- Структура таблицы `position`
--

CREATE TABLE IF NOT EXISTS `position` (
  `x` int(11) DEFAULT NULL,
  `y` int(11) DEFAULT NULL,
  `class_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

--
-- Дамп данных таблицы `position`
--

INSERT INTO `position` (`x`, `y`, `class_id`) VALUES
(207, 103, 10),
(271, 302, 18),
(315, 83, 19);

-- --------------------------------------------------------

--
-- Структура таблицы `relationships`
--

CREATE TABLE IF NOT EXISTS `relationships` (
  `relation_id` int(11) NOT NULL,
  `primary_id` int(11) DEFAULT NULL,
  `secondary_id` int(11) DEFAULT NULL,
  `primary_multy` varchar(45) DEFAULT NULL,
  `secondary_multy` varchar(45) DEFAULT NULL,
  `name` varchar(85) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `primary_props` varchar(85) DEFAULT NULL,
  `secondary_props` varchar(85) DEFAULT NULL,
  `diagram_id` bigint(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `token`
--

CREATE TABLE IF NOT EXISTS `token` (
  `tokenId` int(11) NOT NULL,
  `tokenValue` varchar(45) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `token`
--

INSERT INTO `token` (`tokenId`, `tokenValue`, `userId`) VALUES
(3, 'ebeb300882677f350ea818c8f333f5b9', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` bigint(20) NOT NULL,
  `full_name` varchar(256) COLLATE utf8_general_mysql500_ci NOT NULL,
  `email` varchar(256) COLLATE utf8_general_mysql500_ci NOT NULL,
  `password` varchar(256) COLLATE utf8_general_mysql500_ci NOT NULL,
  `registration_date` datetime NOT NULL,
  `last_available` datetime NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

--
-- Дамп данных таблицы `user`
--

INSERT INTO `user` (`user_id`, `full_name`, `email`, `password`, `registration_date`, `last_available`) VALUES
(1, 'Евгений Ковалев', 'didevgen@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '2015-11-13 12:36:22', '2015-11-13 12:36:22'),
(2, 'jdhsjfhs', 'selekh@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '2015-11-20 17:11:05', '2015-11-20 17:11:05'),
(3, 'Евгений Ковалев', 'evgenijkovaljov@rambler.ru', '$2a$10$/W5EF0lDedW6PBG6r5lH6evepgOMslKTme7aK0Ba8bInK3jimT2aO', '2015-11-27 22:21:19', '2015-11-27 22:21:19'),
(5, 'Napoleon Bonapart', 'napoleon@france.com', '$2a$10$vVBN1mgSx7Fd32SD8q.C2./c/vix/IvUYsKRmHC0.xfxSZIsTM.su', '2015-12-11 12:39:56', '2015-12-11 12:39:56'),
(6, 'Василий Теркин', 'terkin@gmail.com', '$2a$10$gWsY.sjCd9YuSWjM9xlpaeOpEZzis1xrs8S4QKShGViCgLfbd3CH2', '2015-12-13 01:03:49', '2015-12-13 01:03:49'),
(7, 'Родион Малиновский', 'inessakovaleva@rambler.ru', '$2a$10$k511igXEP1roKnaBxug4lOsYUNvr3MQhIRBv1pzyls5l55w2dNO2G', '2015-12-13 01:09:13', '2015-12-13 01:09:13');

-- --------------------------------------------------------

--
-- Структура таблицы `user_role`
--

CREATE TABLE IF NOT EXISTS `user_role` (
  `role_id` bigint(20) NOT NULL,
  `role` varchar(20) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `user_role`
--

INSERT INTO `user_role` (`role_id`, `role`, `user_id`) VALUES
(1, 'ROLE_USER', 1),
(2, 'ROLE_USER', 2),
(3, 'ROLE_USER', 3),
(4, 'ROLE_USER', 4),
(5, 'ROLE_USER', 5),
(6, 'ROLE_USER', 6),
(7, 'ROLE_USER', 7);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `argument`
--
ALTER TABLE `argument`
  ADD PRIMARY KEY (`argument_id`),
  ADD KEY `argument_to_method_idx` (`method_id`);

--
-- Индексы таблицы `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`class_id`),
  ADD KEY `class_to_diagram_idx` (`diagram_id`);

--
-- Индексы таблицы `collaborator`
--
ALTER TABLE `collaborator`
  ADD PRIMARY KEY (`user_id`,`diagram_id`),
  ADD KEY `FK_Reference_3` (`diagram_id`);

--
-- Индексы таблицы `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`comment_id`),
  ADD KEY `fk_comment_to_user_idx` (`user_id`),
  ADD KEY `fk_comment_to_diagram_idx` (`diagram_id`);

--
-- Индексы таблицы `diagram`
--
ALTER TABLE `diagram`
  ADD PRIMARY KEY (`diagram_id`),
  ADD KEY `FK_Reference_1` (`owner_id`),
  ADD KEY `FK_Reference_4` (`status_id`);

--
-- Индексы таблицы `diagram_status`
--
ALTER TABLE `diagram_status`
  ADD PRIMARY KEY (`status_id`);

--
-- Индексы таблицы `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`event_id`),
  ADD KEY `FK_Reference_5` (`event_type_id`);

--
-- Индексы таблицы `eventtype`
--
ALTER TABLE `eventtype`
  ADD PRIMARY KEY (`event_type_id`);

--
-- Индексы таблицы `field`
--
ALTER TABLE `field`
  ADD PRIMARY KEY (`field_id`),
  ADD KEY `field_to_class_idx` (`class_id`);

--
-- Индексы таблицы `history`
--
ALTER TABLE `history`
  ADD KEY `FK_Reference_7` (`user_id`),
  ADD KEY `FK_Reference_6` (`event_id`);

--
-- Индексы таблицы `history_session`
--
ALTER TABLE `history_session`
  ADD PRIMARY KEY (`session_id`),
  ADD KEY `fk_sess_to_user_idx` (`user_id`),
  ADD KEY `fk_sess_to_diagr_idx` (`diagram_id`);

--
-- Индексы таблицы `method`
--
ALTER TABLE `method`
  ADD PRIMARY KEY (`method_id`),
  ADD KEY `method_to_class_idx` (`class_id`);

--
-- Индексы таблицы `position`
--
ALTER TABLE `position`
  ADD PRIMARY KEY (`class_id`),
  ADD KEY `position_to_class_idx` (`class_id`);

--
-- Индексы таблицы `relationships`
--
ALTER TABLE `relationships`
  ADD PRIMARY KEY (`relation_id`),
  ADD KEY `fk_relation_idx` (`primary_id`),
  ADD KEY `fk_relation_2_idx` (`secondary_id`),
  ADD KEY `fk_relation3_idx` (`diagram_id`);

--
-- Индексы таблицы `token`
--
ALTER TABLE `token`
  ADD PRIMARY KEY (`tokenId`),
  ADD KEY `forket_idx` (`userId`);

--
-- Индексы таблицы `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- Индексы таблицы `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`role_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `argument`
--
ALTER TABLE `argument`
  MODIFY `argument_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT для таблицы `class`
--
ALTER TABLE `class`
  MODIFY `class_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT для таблицы `comment`
--
ALTER TABLE `comment`
  MODIFY `comment_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT для таблицы `diagram`
--
ALTER TABLE `diagram`
  MODIFY `diagram_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT для таблицы `diagram_status`
--
ALTER TABLE `diagram_status`
  MODIFY `status_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT для таблицы `event`
--
ALTER TABLE `event`
  MODIFY `event_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT для таблицы `eventtype`
--
ALTER TABLE `eventtype`
  MODIFY `event_type_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT для таблицы `field`
--
ALTER TABLE `field`
  MODIFY `field_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT для таблицы `history_session`
--
ALTER TABLE `history_session`
  MODIFY `session_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT для таблицы `method`
--
ALTER TABLE `method`
  MODIFY `method_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT для таблицы `relationships`
--
ALTER TABLE `relationships`
  MODIFY `relation_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT для таблицы `token`
--
ALTER TABLE `token`
  MODIFY `tokenId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT для таблицы `user`
--
ALTER TABLE `user`
  MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `argument`
--
ALTER TABLE `argument`
  ADD CONSTRAINT `argument_to_method` FOREIGN KEY (`method_id`) REFERENCES `method` (`method_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `class`
--
ALTER TABLE `class`
  ADD CONSTRAINT `class_to_diagram` FOREIGN KEY (`diagram_id`) REFERENCES `diagram` (`diagram_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `fk_comment_to_diagram` FOREIGN KEY (`diagram_id`) REFERENCES `diagram` (`diagram_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_comment_to_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `diagram`
--
ALTER TABLE `diagram`
  ADD CONSTRAINT `FK_Reference_1` FOREIGN KEY (`owner_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_Reference_4` FOREIGN KEY (`status_id`) REFERENCES `diagram_status` (`status_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `event`
--
ALTER TABLE `event`
  ADD CONSTRAINT `FK_Reference_5` FOREIGN KEY (`event_type_id`) REFERENCES `eventtype` (`event_type_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `field`
--
ALTER TABLE `field`
  ADD CONSTRAINT `field_to_class` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `history`
--
ALTER TABLE `history`
  ADD CONSTRAINT `FK_Reference_6` FOREIGN KEY (`event_id`) REFERENCES `event` (`event_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_Reference_7` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Ограничения внешнего ключа таблицы `history_session`
--
ALTER TABLE `history_session`
  ADD CONSTRAINT `fk_sess_to_diagr` FOREIGN KEY (`diagram_id`) REFERENCES `diagram` (`diagram_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_sess_to_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `method`
--
ALTER TABLE `method`
  ADD CONSTRAINT `method_to_class` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `position`
--
ALTER TABLE `position`
  ADD CONSTRAINT `position_to_class` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `relationships`
--
ALTER TABLE `relationships`
  ADD CONSTRAINT `fk_relation` FOREIGN KEY (`primary_id`) REFERENCES `class` (`class_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_relation3` FOREIGN KEY (`diagram_id`) REFERENCES `diagram` (`diagram_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_relation_2` FOREIGN KEY (`secondary_id`) REFERENCES `class` (`class_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `token`
--
ALTER TABLE `token`
  ADD CONSTRAINT `forket` FOREIGN KEY (`userId`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
