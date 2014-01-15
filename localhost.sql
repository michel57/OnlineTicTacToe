-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Mer 07 Mars 2012 à 10:33
-- Version du serveur: 5.1.44
-- Version de PHP: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `morpion`
--
CREATE DATABASE `morpion` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `morpion`;

-- --------------------------------------------------------

--
-- Structure de la table `historique`
--

CREATE TABLE IF NOT EXISTS `historique` (
  `idPartie` int(11) NOT NULL AUTO_INCREMENT,
  `vainqueur` varchar(255) NOT NULL,
  `perdant` varchar(255) NOT NULL,
  `nul` tinyint(1) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`idPartie`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=116 ;

--
-- Contenu de la table `historique`
--

INSERT INTO `historique` (`idPartie`, `vainqueur`, `perdant`, `nul`, `date`) VALUES
(45, 'Max', 'Michel', 0, '2012-02-26 00:00:00'),
(46, 'Max', 'Michel', 0, '2012-02-26 00:00:00'),
(47, 'Michel', 'Max', 0, '2012-02-27 00:00:00'),
(48, 'Michel', 'Michel', 0, '2012-02-27 00:00:00'),
(49, 'Michel', 'Michel', 0, '2012-02-27 00:00:00'),
(50, 'Michel', 'Michel', 0, '2012-02-27 00:00:00'),
(51, 'ge', 'Michel', 0, '2012-02-27 00:00:00'),
(52, 'Michel', 'Michel', 0, '2012-02-27 00:00:00'),
(53, 'gerger', 'Michel', 0, '2012-02-27 00:00:00'),
(54, 'Michel', 'gferg', 0, '2012-02-27 00:00:00'),
(55, 'Michel', 'Max', 0, '2012-02-27 00:00:00'),
(56, 'Max', 'Michel', 0, '2012-02-27 00:00:00'),
(57, 'Michel', 'Michel', 0, '2012-02-27 00:00:00'),
(58, 'Michel', 'Max', 0, '2012-02-27 00:00:00'),
(59, 'Michel', 'Michel', 0, '2012-02-27 00:00:00'),
(60, 'Hubert', 'Michel', 0, '2012-02-27 00:00:00'),
(61, 'Hubert', 'Michel', 0, '2012-02-27 00:00:00'),
(62, 'Michel', 'Michel', 0, '2012-02-27 00:00:00'),
(63, 'Max', 'Michel', 0, '2012-02-27 00:00:00'),
(64, 'ge', 'csqe', 0, '2012-02-27 00:00:00'),
(65, 'Max', 'Mickael', 0, '2012-02-28 00:00:00'),
(66, 'Mickael', 'Max', 1, '2012-02-28 00:00:00'),
(67, 'Max', 'Michalm', 0, '2012-02-28 00:00:00'),
(68, 'max', 'dzad', 0, '2012-02-28 00:00:00'),
(69, 'max', 'dza', 0, '2012-02-28 00:00:00'),
(70, 'Hubert', 'Jean CLaude', 0, '2012-02-28 00:00:00'),
(71, 'm', 'da', 0, '2012-02-28 11:51:08'),
(72, 'max', 'mich', 0, '2012-02-28 16:12:28'),
(73, 'mich', 'max', 0, '2012-02-28 16:13:23'),
(74, 'lala', 'fez', 0, '2012-02-28 16:28:57'),
(75, 'dzd', 'lala', 0, '2012-02-28 16:31:26'),
(76, 'ddd', 'fez', 0, '2012-02-28 16:42:42'),
(77, 'fezfe', 'fez', 0, '2012-02-28 16:44:53'),
(78, 'fezfe', 'dce', 0, '2012-02-28 16:47:13'),
(79, 'fez', 'fze', 0, '2012-02-28 16:56:10'),
(80, 'fger', 'vezev', 0, '2012-02-28 17:00:51'),
(81, 'feez', 'fref', 0, '2012-02-28 17:28:35'),
(82, 'fez', 'fze', 0, '2012-02-28 19:43:24'),
(83, 'mm', 'max', 0, '2012-02-28 21:22:17'),
(84, 'feez', 'mm', 0, '2012-02-28 21:23:54'),
(85, 'Max', 'Mich', 0, '2012-02-29 07:50:11'),
(86, 'lzlz', 'lzlz', 0, '2012-02-29 08:38:27'),
(87, 'Michel', 'Mask', 0, '2012-02-29 10:43:28'),
(88, 'michel', 'mask', 0, '2012-02-29 10:45:31'),
(89, 'michel', 'miche', 0, '2012-02-29 11:16:06'),
(90, 'mask', 'michel', 0, '2012-02-29 11:20:09'),
(91, 'michel', 'mask', 0, '2012-02-29 11:22:28'),
(92, 'michel', 'mask', 0, '2012-02-29 11:26:38'),
(93, 'michel', 'mask', 0, '2012-02-29 14:01:09'),
(94, 'mask', 'michel', 0, '2012-02-29 14:21:27'),
(95, 'michel', 'mask', 0, '2012-02-29 14:21:54'),
(96, 'mask', 'michel', 0, '2012-02-29 14:22:19'),
(97, 'michel', 'mask', 0, '2012-02-29 14:22:39'),
(98, 'michel', 'mask', 0, '2012-02-29 14:23:39'),
(99, 'mask', 'michel', 0, '2012-02-29 14:23:54'),
(100, 'mask', 'michel', 0, '2012-02-29 14:24:12'),
(101, 'mask', 'michel', 0, '2012-02-29 14:45:00'),
(102, 'Masque', 'Michel', 1, '2012-03-01 08:04:45'),
(103, 'michel', 'FUUUUUUUUUUUUUUUUUUUUUUU', 1, '2012-03-01 08:37:13'),
(104, 'michel', 'YAAA', 0, '2012-03-01 08:38:19'),
(105, 'michel', 'YAAA', 0, '2012-03-01 08:38:29'),
(106, 'michel', 'YAAA', 0, '2012-03-01 08:38:37'),
(107, 'michel', 'YEAAAH', 0, '2012-03-01 08:52:05'),
(108, 'YEAAAH', 'michel', 0, '2012-03-01 08:52:19'),
(109, 'michel', 'YEAAAH', 0, '2012-03-01 08:52:35'),
(110, 'michel', 'YEAAAH', 0, '2012-03-01 08:52:41'),
(111, 'YEAAAH', 'michel', 0, '2012-03-01 08:53:01'),
(112, 'YEAAAH', 'michel', 1, '2012-03-01 08:53:40'),
(113, 'mask1', 'michel1', 0, '2012-03-01 10:21:23'),
(114, 'michel2', 'mask2', 1, '2012-03-01 10:21:51'),
(115, 'michel3', 'mask2', 0, '2012-03-01 10:22:22');
