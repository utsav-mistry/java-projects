-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 29, 2024 at 05:42 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `audioplayerdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `albums`
--

CREATE TABLE `albums` (
  `album_id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `artist_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `albums`
--

INSERT INTO `albums` (`album_id`, `title`, `artist_id`) VALUES
(1, 'Dil Se', 1),
(2, 'Madhubala', 2),
(3, 'Ae Dil Hai Mushkil', 3),
(4, 'Kishore Kumar Hits', 4),
(5, 'Evergreen Classics', 5),
(6, 'Romantic Hits', 6),
(7, 'Sunidhi Chauhan Collection', 7),
(8, 'Shreya Ghoshal Top Songs', 8),
(9, 'Neha Kakkar Fever', 9),
(10, 'Bappi Lahiri Hits', 10);

-- --------------------------------------------------------

--
-- Table structure for table `artists`
--

CREATE TABLE `artists` (
  `artist_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `artists`
--

INSERT INTO `artists` (`artist_id`, `name`) VALUES
(1, 'A. R. Rahman'),
(21, 'Alka Yagnik'),
(17, 'Amitabh Bachchan'),
(3, 'Arijit Singh'),
(26, 'Babul Supriyo'),
(10, 'Bappi Lahiri'),
(16, 'Gulzar'),
(13, 'Hariharan'),
(12, 'Jagjit Singh'),
(20, 'Kailash Kher'),
(4, 'Kishore Kumar'),
(11, 'Kumar Sanu'),
(2, 'Lata Mangeshkar'),
(14, 'Manna Dey'),
(5, 'Mohammad Rafi'),
(23, 'Mohit Chauhan'),
(9, 'Neha Kakkar'),
(25, 'Pankaj Udhas'),
(22, 'Rahat Fateh Ali Khan'),
(24, 'Rishi Kapoor'),
(15, 'S. P. Balasubrahmanyam'),
(28, 'Sadhana Sargam'),
(8, 'Shreya Ghoshal'),
(27, 'Sonu Nigam'),
(19, 'Sukhwinder Singh'),
(7, 'Sunidhi Chauhan'),
(6, 'Udit Narayan'),
(18, 'Zubeen Garg');

-- --------------------------------------------------------

--
-- Table structure for table `genres`
--

CREATE TABLE `genres` (
  `genre_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `genres`
--

INSERT INTO `genres` (`genre_id`, `name`) VALUES
(25, 'Alternative'),
(21, 'Ambient'),
(26, 'Blues'),
(1, 'Bollywood'),
(3, 'Classical'),
(23, 'Country'),
(14, 'Dance'),
(6, 'Devotional'),
(19, 'EDM'),
(22, 'Electronic'),
(9, 'Folk'),
(12, 'Fusion'),
(2, 'Ghazal'),
(16, 'Hip Hop'),
(17, 'Indie'),
(10, 'Instrumental'),
(15, 'Jazz'),
(30, 'Latin'),
(18, 'Metal'),
(8, 'Patriotic'),
(4, 'Pop'),
(11, 'Qawwali'),
(28, 'R&B'),
(24, 'Reggae'),
(5, 'Rock'),
(7, 'Romantic'),
(29, 'Ska'),
(27, 'Soul'),
(13, 'Sufi'),
(20, 'Traditional');

-- --------------------------------------------------------

--
-- Table structure for table `songgenres`
--

CREATE TABLE `songgenres` (
  `song_id` int(11) NOT NULL,
  `genre_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `songgenres`
--

INSERT INTO `songgenres` (`song_id`, `genre_id`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(6, 1),
(7, 1),
(8, 1),
(9, 1),
(10, 1);

-- --------------------------------------------------------

--
-- Table structure for table `songs`
--

CREATE TABLE `songs` (
  `song_id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `artist_id` int(11) DEFAULT NULL,
  `album_id` int(11) DEFAULT NULL,
  `duration` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `songs`
--

INSERT INTO `songs` (`song_id`, `title`, `artist_id`, `album_id`, `duration`) VALUES
(1, 'Chaiyya Chaiyya', 1, 1, 320),
(2, 'Tujhse Naraz Nahin Zindagi', 2, 2, 300),
(3, 'Tum Mile', 3, 3, 270),
(4, 'Pal Pal Dil Ke Paas', 4, 4, 290),
(5, 'Kya Hua Tera Wada', 5, 5, 310),
(6, 'Pehla Pehla Pyaar', 6, 6, 305),
(7, 'Sheila Ki Jawani', 7, 7, 285),
(8, 'Teri Galliyan', 8, 8, 295),
(9, 'Mileya Mileya', 9, 9, 320),
(10, 'I Am a Disco Dancer', 10, 10, 330),
(11, 'Chaiyya Chaiyya', 1, 1, 320),
(12, 'Tujhse Naraz Nahin Zindagi', 2, 2, 300),
(13, 'Tum Mile', 3, 3, 270),
(14, 'Chaiyya Chaiyya', 1, 1, 320),
(15, 'Tujhse Naraz Nahin Zindagi', 2, 2, 300),
(16, 'Tum Mile', 3, 3, 270),
(17, 'Chaiyya Chaiyya', 1, 1, 320),
(18, 'Tujhse Naraz Nahin Zindagi', 2, 2, 300),
(19, 'Tum Mile', 3, 3, 270),
(20, 'Chaiyya Chaiyya', 1, 1, 320),
(21, 'Tujhse Naraz Nahin Zindagi', 2, 2, 300),
(22, 'Tum Mile', 3, 3, 270),
(23, 'Tum Hi Ho', 4, 4, 240),
(24, 'Channa Mereya', 5, 5, 270),
(25, 'Jeene Laga Hoon', 6, 6, 250),
(26, 'Raabta', 7, 7, 260),
(27, 'Sun Saathiya', 8, 8, 240),
(28, 'Pal', 9, 9, 250),
(29, 'Kaun Tujhe', 10, 10, 230),
(31, 'Chaiyya Chaiyya', 1, 1, 320),
(32, 'Tujhse Naraz Nahin Zindagi', 2, 2, 300),
(33, 'Tum Mile', 3, 3, 270),
(34, 'Tum Hi Ho', 4, 4, 240),
(35, 'Channa Mereya', 5, 5, 270),
(36, 'Jeene Laga Hoon', 6, 6, 250),
(37, 'Raabta', 7, 7, 260),
(38, 'Sun Saathiya', 8, 8, 240),
(39, 'Pal', 9, 9, 250),
(40, 'Kaun Tujhe', 10, 10, 230),
(41, 'Hasi', 11, 1, 250);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `albums`
--
ALTER TABLE `albums`
  ADD PRIMARY KEY (`album_id`),
  ADD UNIQUE KEY `title` (`title`),
  ADD KEY `artist_id` (`artist_id`);

--
-- Indexes for table `artists`
--
ALTER TABLE `artists`
  ADD PRIMARY KEY (`artist_id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `genres`
--
ALTER TABLE `genres`
  ADD PRIMARY KEY (`genre_id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `songgenres`
--
ALTER TABLE `songgenres`
  ADD PRIMARY KEY (`song_id`,`genre_id`),
  ADD KEY `genre_id` (`genre_id`);

--
-- Indexes for table `songs`
--
ALTER TABLE `songs`
  ADD PRIMARY KEY (`song_id`),
  ADD KEY `artist_id` (`artist_id`),
  ADD KEY `album_id` (`album_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `albums`
--
ALTER TABLE `albums`
  MODIFY `album_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `artists`
--
ALTER TABLE `artists`
  MODIFY `artist_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `genres`
--
ALTER TABLE `genres`
  MODIFY `genre_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `songs`
--
ALTER TABLE `songs`
  MODIFY `song_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `albums`
--
ALTER TABLE `albums`
  ADD CONSTRAINT `albums_ibfk_1` FOREIGN KEY (`artist_id`) REFERENCES `artists` (`artist_id`);

--
-- Constraints for table `songgenres`
--
ALTER TABLE `songgenres`
  ADD CONSTRAINT `songgenres_ibfk_1` FOREIGN KEY (`song_id`) REFERENCES `songs` (`song_id`),
  ADD CONSTRAINT `songgenres_ibfk_2` FOREIGN KEY (`genre_id`) REFERENCES `genres` (`genre_id`);

--
-- Constraints for table `songs`
--
ALTER TABLE `songs`
  ADD CONSTRAINT `songs_ibfk_1` FOREIGN KEY (`artist_id`) REFERENCES `artists` (`artist_id`),
  ADD CONSTRAINT `songs_ibfk_2` FOREIGN KEY (`album_id`) REFERENCES `albums` (`album_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
