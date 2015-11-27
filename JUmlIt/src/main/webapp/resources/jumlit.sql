-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Ноя 27 2015 г., 20:39
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
-- Структура таблицы `collaborator`
--

CREATE TABLE IF NOT EXISTS `collaborator` (
  `user_id` bigint(20) DEFAULT NULL,
  `diagram_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `diagram_status`
--

CREATE TABLE IF NOT EXISTS `diagram_status` (
  `status_id` bigint(20) NOT NULL,
  `status_type` varchar(256) COLLATE utf8_general_mysql500_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

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
-- Структура таблицы `history`
--

CREATE TABLE IF NOT EXISTS `history` (
  `event_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

--
-- Дамп данных таблицы `user`
--

INSERT INTO `user` (`user_id`, `full_name`, `email`, `password`, `registration_date`, `last_available`) VALUES
(1, 'Евгений Ковалев', 'didevgen@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '2015-11-13 12:36:22', '2015-11-13 12:36:22'),
(2, 'jdhsjfhs', 'selekh@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '2015-11-20 17:11:05', '2015-11-20 17:11:05');

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
(2, 'ROLE_USER', 2);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `collaborator`
--
ALTER TABLE `collaborator`
  ADD KEY `FK_Reference_2` (`user_id`),
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
-- Индексы таблицы `history`
--
ALTER TABLE `history`
  ADD KEY `FK_Reference_6` (`event_id`),
  ADD KEY `FK_Reference_7` (`user_id`);

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
-- AUTO_INCREMENT для таблицы `diagram`
--
ALTER TABLE `diagram`
  MODIFY `diagram_id` bigint(20) NOT NULL AUTO_INCREMENT;
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
-- AUTO_INCREMENT для таблицы `token`
--
ALTER TABLE `token`
  MODIFY `tokenId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT для таблицы `user`
--
ALTER TABLE `user`
  MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `collaborator`
--
ALTER TABLE `collaborator`
  ADD CONSTRAINT `FK_Reference_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_Reference_3` FOREIGN KEY (`diagram_id`) REFERENCES `diagram` (`diagram_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `diagram`
--
ALTER TABLE `diagram`
  ADD CONSTRAINT `FK_Reference_1` FOREIGN KEY (`owner_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `FK_Reference_4` FOREIGN KEY (`status_id`) REFERENCES `diagram_status` (`status_id`);

--
-- Ограничения внешнего ключа таблицы `event`
--
ALTER TABLE `event`
  ADD CONSTRAINT `FK_Reference_5` FOREIGN KEY (`event_type_id`) REFERENCES `eventtype` (`event_type_id`);

--
-- Ограничения внешнего ключа таблицы `history`
--
ALTER TABLE `history`
  ADD CONSTRAINT `FK_Reference_6` FOREIGN KEY (`event_id`) REFERENCES `event` (`event_id`),
  ADD CONSTRAINT `FK_Reference_7` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Ограничения внешнего ключа таблицы `token`
--
ALTER TABLE `token`
  ADD CONSTRAINT `forket` FOREIGN KEY (`userId`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
