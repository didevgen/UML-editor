-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Ноя 29 2015 г., 15:42
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
-- Структура таблицы `agrument`
--

CREATE TABLE IF NOT EXISTS `agrument` (
  `argument_id` int(11) NOT NULL,
  `argument_name` varchar(105) DEFAULT NULL,
  `argument_type` varchar(105) DEFAULT NULL,
  `method_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `class`
--

CREATE TABLE IF NOT EXISTS `class` (
  `class_id` int(11) NOT NULL,
  `class_name` varchar(45) DEFAULT NULL,
  `is_static` tinyint(1) DEFAULT NULL,
  `diagram_id` bigint(20) DEFAULT NULL,
  `class_access` varchar(105) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
(3, 4);

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

--
-- Дамп данных таблицы `diagram`
--

INSERT INTO `diagram` (`diagram_id`, `owner_id`, `status_id`, `json_data`, `created_date`, `last_updated`, `name`, `description`) VALUES
(1, 3, -1, '', '2015-11-27 22:36:32', '2015-11-27 22:36:32', '', ''),
(2, 3, -1, '', '2015-11-27 22:50:23', '2015-11-27 22:50:23', '', ''),
(3, 4, -1, '', '2015-11-27 23:33:12', '2015-11-28 23:50:50', 'Diagram1', 'MyDiagram'),
(4, 4, -1, '', '2015-11-27 23:33:12', '2015-11-27 23:33:12', 'Diagram2', 'MyDiagram2');

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
  `is_staitc` tinyint(1) DEFAULT NULL,
  `field_name` varchar(70) COLLATE utf8_general_mysql500_ci DEFAULT NULL,
  `field_type` varchar(45) COLLATE utf8_general_mysql500_ci DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

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
-- Структура таблицы `method`
--

CREATE TABLE IF NOT EXISTS `method` (
  `method_id` int(11) NOT NULL,
  `is_static` tinyint(1) DEFAULT NULL,
  `method_name` varchar(105) DEFAULT NULL,
  `return_type` varchar(105) DEFAULT NULL,
  `method_access` varchar(105) DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `position`
--

CREATE TABLE IF NOT EXISTS `position` (
  `position_id` int(11) NOT NULL,
  `class_id` int(11) DEFAULT NULL,
  `x` int(11) DEFAULT NULL,
  `y` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

--
-- Дамп данных таблицы `user`
--

INSERT INTO `user` (`user_id`, `full_name`, `email`, `password`, `registration_date`, `last_available`) VALUES
(1, 'Евгений Ковалев', 'didevgen@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '2015-11-13 12:36:22', '2015-11-13 12:36:22'),
(2, 'jdhsjfhs', 'selekh@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '2015-11-20 17:11:05', '2015-11-20 17:11:05'),
(3, 'Евгений Ковалев', 'evgenijkovaljov@rambler.ru', '$2a$10$/W5EF0lDedW6PBG6r5lH6evepgOMslKTme7aK0Ba8bInK3jimT2aO', '2015-11-27 22:21:19', '2015-11-27 22:21:19'),
(4, 'Василий Теркин', 'terkin@gmail.com', '$2a$10$gh2QTC1TelYAvAKPsKvJjewN0UZFDOC8yp57HYVBxLfV.pmfyOrWu', '2015-11-27 23:32:46', '2015-11-27 23:32:46');

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
(4, 'ROLE_USER', 4);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `agrument`
--
ALTER TABLE `agrument`
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
-- Индексы таблицы `method`
--
ALTER TABLE `method`
  ADD PRIMARY KEY (`method_id`),
  ADD KEY `method_to_class_idx` (`class_id`);

--
-- Индексы таблицы `position`
--
ALTER TABLE `position`
  ADD PRIMARY KEY (`position_id`),
  ADD KEY `pos_to_class_idx` (`class_id`);

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
-- AUTO_INCREMENT для таблицы `agrument`
--
ALTER TABLE `agrument`
  MODIFY `argument_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT для таблицы `class`
--
ALTER TABLE `class`
  MODIFY `class_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT для таблицы `diagram`
--
ALTER TABLE `diagram`
  MODIFY `diagram_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
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
  MODIFY `field_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT для таблицы `method`
--
ALTER TABLE `method`
  MODIFY `method_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT для таблицы `position`
--
ALTER TABLE `position`
  MODIFY `position_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT для таблицы `token`
--
ALTER TABLE `token`
  MODIFY `tokenId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT для таблицы `user`
--
ALTER TABLE `user`
  MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `agrument`
--
ALTER TABLE `agrument`
  ADD CONSTRAINT `argument_to_method` FOREIGN KEY (`method_id`) REFERENCES `method` (`method_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `class`
--
ALTER TABLE `class`
  ADD CONSTRAINT `class_to_diagram` FOREIGN KEY (`diagram_id`) REFERENCES `diagram` (`diagram_id`) ON DELETE CASCADE ON UPDATE CASCADE;

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
-- Ограничения внешнего ключа таблицы `method`
--
ALTER TABLE `method`
  ADD CONSTRAINT `method_to_class` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `position`
--
ALTER TABLE `position`
  ADD CONSTRAINT `pos_to_class` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `token`
--
ALTER TABLE `token`
  ADD CONSTRAINT `forket` FOREIGN KEY (`userId`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
