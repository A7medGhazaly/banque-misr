-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 05, 2021 at 11:06 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `paticipant`
--

-- --------------------------------------------------------

--
-- Table structure for table `champion_group`
--

CREATE TABLE `champion_group` (
  `ID` int(8) NOT NULL,
  `GROUP_NAME` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `champion_group`
--

INSERT INTO `champion_group` (`ID`, `GROUP_NAME`) VALUES
(1, 'Group A'),
(2, 'Group B'),
(3, 'Group C'),
(4, 'Group D');

-- --------------------------------------------------------

--
-- Table structure for table `champion_league`
--

CREATE TABLE `champion_league` (
  `id` int(8) NOT NULL,
  `start_at` date NOT NULL,
  `ends_at` date DEFAULT NULL,
  `winner` int(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `champion_league`
--

INSERT INTO `champion_league` (`id`, `start_at`, `ends_at`, `winner`) VALUES
(23, '2021-10-04', '2021-10-05', 2),
(24, '2021-10-05', '2021-10-05', 10);

-- --------------------------------------------------------

--
-- Table structure for table `champion_league_matches`
--

CREATE TABLE `champion_league_matches` (
  `ID` int(8) NOT NULL,
  `FIRST_PARTICIPANT` int(8) NOT NULL,
  `SECOND_PARTICIPANT` int(8) NOT NULL,
  `WINNER` int(8) DEFAULT NULL,
  `champion_league_id` int(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `closed_round`
--

CREATE TABLE `closed_round` (
  `ID` int(8) NOT NULL,
  `FIRST_PARTICIPANT` int(8) NOT NULL,
  `SECOND_PARTICIPANT` int(8) NOT NULL,
  `WINNER` int(8) DEFAULT NULL,
  `champion_league_id` int(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `closed_round`
--

INSERT INTO `closed_round` (`ID`, `FIRST_PARTICIPANT`, `SECOND_PARTICIPANT`, `WINNER`, `champion_league_id`) VALUES
(13, 4, 1, 4, 23),
(14, 5, 2, 2, 23),
(15, 6, 3, 3, 23),
(16, 10, 7, 10, 23),
(17, 11, 8, 8, 23),
(18, 12, 9, 12, 23),
(19, 4, 1, 4, 23),
(20, 5, 2, 2, 23),
(21, 6, 3, 3, 23),
(22, 10, 7, 10, 23),
(23, 11, 8, 8, 23),
(24, 12, 9, 12, 23),
(25, 4, 1, 1, 23),
(26, 5, 2, 5, 23),
(27, 6, 3, 6, 23),
(28, 10, 7, 7, 23),
(29, 11, 8, 11, 23),
(30, 12, 9, 12, 23),
(31, 4, 1, 1, 23),
(32, 5, 2, 5, 23),
(33, 6, 3, 6, 23),
(34, 10, 7, 10, 23),
(35, 11, 8, 11, 23),
(36, 12, 9, 12, 23),
(37, 4, 1, 4, 24),
(38, 5, 2, 2, 24),
(39, 6, 3, 6, 24),
(40, 10, 7, 10, 24),
(41, 11, 8, 11, 24),
(42, 12, 9, 9, 24);

-- --------------------------------------------------------

--
-- Table structure for table `participant`
--

CREATE TABLE `participant` (
  `ID` int(8) NOT NULL,
  `ENGLISH_NAME` varchar(500) NOT NULL,
  `EMAIL` varchar(500) NOT NULL,
  `GROUP_id` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `participant`
--

INSERT INTO `participant` (`ID`, `ENGLISH_NAME`, `EMAIL`, `GROUP_id`) VALUES
(1, 'mona', 'mona@yahoo.com', 1),
(2, 'noha', 'noha@yaoo.com', 1),
(3, 'tarek', 'tarek@yaoo.com', 1),
(4, 'ahmed', 'ahmed@yahoo.com', 2),
(5, 'ali', 'ali@yahoo.com', 2),
(6, 'nessma', 'nessma@yaoo.com', 2),
(7, 'mosataf', 'mosataf@yaoo.com', 3),
(8, 'adel', 'del@yaoo.com', 3),
(9, 'selim', 'selim@yaoo.com', 3),
(10, 'refaat', 'ahmed.ghazaly@asset.com.eg', 4),
(11, 'sayed', 'sayed@yahoo.com', 4),
(12, 'momen', 'momen@yahoo.com', 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `champion_group`
--
ALTER TABLE `champion_group`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `champion_league`
--
ALTER TABLE `champion_league`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `champion_league_matches`
--
ALTER TABLE `champion_league_matches`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `champion_league_id` (`champion_league_id`),
  ADD KEY `FIRST_PARTICIPANT` (`FIRST_PARTICIPANT`),
  ADD KEY `SECOND_PARTICIPANT` (`SECOND_PARTICIPANT`),
  ADD KEY `WINNER` (`WINNER`);

--
-- Indexes for table `closed_round`
--
ALTER TABLE `closed_round`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `champion_league_id` (`champion_league_id`),
  ADD KEY `FIRST_PARTICIPANT` (`FIRST_PARTICIPANT`),
  ADD KEY `SECOND_PARTICIPANT` (`SECOND_PARTICIPANT`),
  ADD KEY `WINNER` (`WINNER`);

--
-- Indexes for table `participant`
--
ALTER TABLE `participant`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `GROUP_id` (`GROUP_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `champion_league`
--
ALTER TABLE `champion_league`
  MODIFY `id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `champion_league_matches`
--
ALTER TABLE `champion_league_matches`
  MODIFY `ID` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=140;

--
-- AUTO_INCREMENT for table `closed_round`
--
ALTER TABLE `closed_round`
  MODIFY `ID` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `participant`
--
ALTER TABLE `participant`
  MODIFY `ID` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `champion_league_matches`
--
ALTER TABLE `champion_league_matches`
  ADD CONSTRAINT `champion_league_matches_ibfk_1` FOREIGN KEY (`champion_league_id`) REFERENCES `champion_league` (`id`),
  ADD CONSTRAINT `champion_league_matches_ibfk_2` FOREIGN KEY (`FIRST_PARTICIPANT`) REFERENCES `participant` (`ID`),
  ADD CONSTRAINT `champion_league_matches_ibfk_3` FOREIGN KEY (`SECOND_PARTICIPANT`) REFERENCES `participant` (`ID`),
  ADD CONSTRAINT `champion_league_matches_ibfk_4` FOREIGN KEY (`WINNER`) REFERENCES `participant` (`ID`);

--
-- Constraints for table `closed_round`
--
ALTER TABLE `closed_round`
  ADD CONSTRAINT `closed_round_ibfk_1` FOREIGN KEY (`champion_league_id`) REFERENCES `champion_league` (`id`),
  ADD CONSTRAINT `closed_round_ibfk_2` FOREIGN KEY (`FIRST_PARTICIPANT`) REFERENCES `participant` (`ID`),
  ADD CONSTRAINT `closed_round_ibfk_3` FOREIGN KEY (`SECOND_PARTICIPANT`) REFERENCES `participant` (`ID`),
  ADD CONSTRAINT `closed_round_ibfk_4` FOREIGN KEY (`WINNER`) REFERENCES `participant` (`ID`);

--
-- Constraints for table `participant`
--
ALTER TABLE `participant`
  ADD CONSTRAINT `participant_ibfk_1` FOREIGN KEY (`GROUP_id`) REFERENCES `champion_group` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
