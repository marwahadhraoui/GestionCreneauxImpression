-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Apr 02, 2025 at 05:11 PM
-- Server version: 5.7.36
-- PHP Version: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gestioncreneauximpression`
--

-- --------------------------------------------------------

--
-- Table structure for table `creneaux`
--

DROP TABLE IF EXISTS `creneaux`;
CREATE TABLE IF NOT EXISTS `creneaux` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `heure` time(6) DEFAULT NULL,
  `statut` varchar(255) DEFAULT NULL,
  `secretaire_id` int(11) DEFAULT NULL,
  `heure_debut` time(6) DEFAULT NULL,
  `heure_fin` time(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnjj0qqeoqdo3sweym5pa8t56s` (`secretaire_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `creneaux`
--

INSERT INTO `creneaux` (`id`, `date`, `heure`, `statut`, `secretaire_id`, `heure_debut`, `heure_fin`) VALUES
(12, '2025-04-03', NULL, 'RESERVE', NULL, '10:30:00.000000', '11:00:00.000000'),
(13, '2025-04-04', NULL, 'RESERVE', NULL, '11:00:00.000000', '11:30:00.000000'),
(35, '2025-04-04', NULL, 'ANNULE', NULL, '12:30:00.000000', '13:00:00.000000'),
(37, '2025-04-03', NULL, 'RESERVE', NULL, '13:30:00.000000', '14:00:00.000000'),
(38, '2025-04-01', NULL, 'TERMINE', NULL, '12:00:00.000000', '12:30:00.000000');

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `matiere` varchar(255) DEFAULT NULL,
  `nbr_page` int(11) NOT NULL,
  `niveau` varchar(255) DEFAULT NULL,
  `specialite` varchar(255) DEFAULT NULL,
  `type_impression` varchar(255) DEFAULT NULL,
  `creneaux_id` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKetxe740pqgxgukt8qg3n4m3h4` (`creneaux_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`id`, `matiere`, `nbr_page`, `niveau`, `specialite`, `type_impression`, `creneaux_id`, `version`) VALUES
(8, 'maths', 150, '2 eme', 'informatique', 'examen', 12, NULL),
(9, 'compta', 200, '2 eme', 'informatique', 'ds', 13, NULL),
(26, 'sgbd', 50, '1', 'informatique', 'test test', 35, NULL),
(30, 'mecanique', 100, '3 eme', 'mecatronique', 'cours', 37, NULL),
(31, 'system admin', 80, '3 eme', 'informatique', 'ds', 38, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `dtype` varchar(31) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `mdp` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`dtype`, `id`, `email`, `mdp`, `nom`, `prenom`, `role`) VALUES
('User', 1, 'marwa@gmail.com', 'marwa', 'Hadhraoui', 'Marwa', 'SECRETAIRE');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `creneaux`
--
ALTER TABLE `creneaux`
  ADD CONSTRAINT `FKnjj0qqeoqdo3sweym5pa8t56s` FOREIGN KEY (`secretaire_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `FKm98w62pu6mm8yntg8ot2ibg9b` FOREIGN KEY (`creneaux_id`) REFERENCES `creneaux` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
