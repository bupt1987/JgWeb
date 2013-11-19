-- phpMyAdmin SQL Dump
-- version 4.0.2
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2013 年 11 月 14 日 23:36
-- 服务器版本: 5.1.50-community
-- PHP 版本: 5.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


--
-- 数据库: `jgframework`
--

CREATE DATABASE IF NOT EXISTS `jgframework` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `jgframework`;

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(50) NOT NULL,
  `last_login_time` bigint(20) unsigned NOT NULL,
  `creat_time` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `username` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `user_info`
--

CREATE TABLE IF NOT EXISTS `user_info` (
  `uid` int(10) unsigned NOT NULL,
  `actions` longtext NOT NULL,
  `experience` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `nickname` varchar(30) NOT NULL,
  PRIMARY KEY (`uid`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

