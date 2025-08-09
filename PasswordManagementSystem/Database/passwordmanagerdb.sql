-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 29, 2024 at 07:45 AM
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
-- Database: `passwordmanagerdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `password_audit`
--

CREATE TABLE `password_audit` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `old_encrypted_password` text NOT NULL,
  `new_encrypted_password` text NOT NULL,
  `changed_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `website` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `encrypted_password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `website`, `username`, `encrypted_password`) VALUES
(3, 'instagram.com', 'that.clumsy_one', 'L3bKQTlWFGhBP5pjlMmpFQ=='),
(4, 'google.com', 'utsavamistry30@gmail.com', 'pkTQH5wJJJu4fJNlQwuoog==');

--
-- Triggers `users`
--
DELIMITER $$
CREATE TRIGGER `password_change_trigger` AFTER UPDATE ON `users` FOR EACH ROW BEGIN
    IF OLD.encrypted_password != NEW.encrypted_password THEN
        INSERT INTO password_audit(user_id, old_encrypted_password, new_encrypted_password)
        VALUES (OLD.id, OLD.encrypted_password, NEW.encrypted_password);
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `user_activity_trigger` AFTER INSERT ON `users` FOR EACH ROW BEGIN
    INSERT INTO user_activity_log(user_id, action)
    VALUES (NEW.id, 'Account Created');
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `user_activity_log`
--

CREATE TABLE `user_activity_log` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `action` varchar(255) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_activity_log`
--

INSERT INTO `user_activity_log` (`id`, `user_id`, `action`, `timestamp`) VALUES
(3, 3, 'Account Created', '2024-08-27 05:50:31'),
(4, 4, 'Account Created', '2024-08-28 13:49:16');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `password_audit`
--
ALTER TABLE `password_audit`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_activity_log`
--
ALTER TABLE `user_activity_log`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `password_audit`
--
ALTER TABLE `password_audit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `user_activity_log`
--
ALTER TABLE `user_activity_log`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `password_audit`
--
ALTER TABLE `password_audit`
  ADD CONSTRAINT `password_audit_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `user_activity_log`
--
ALTER TABLE `user_activity_log`
  ADD CONSTRAINT `user_activity_log_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
