
-- Dumping database structure for matcha_db
CREATE DATABASE IF NOT EXISTS `matcha_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_uca1400_ai_ci */;
USE `matcha_db`;

-- Dumping structure for table matcha_db.bookings
CREATE TABLE IF NOT EXISTS `bookings` (
  `id` varchar(50) NOT NULL,
  `client_id` varchar(50) DEFAULT NULL,
  `talent_id` varchar(50) DEFAULT NULL,
  `service_id` varchar(50) DEFAULT NULL,
  `waktu_mulai` datetime NOT NULL,
  `waktu_selesai` datetime NOT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `client_id` (`client_id`),
  KEY `talent_id` (`talent_id`),
  KEY `service_id` (`service_id`),
  CONSTRAINT `1` FOREIGN KEY (`client_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `2` FOREIGN KEY (`talent_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `3` FOREIGN KEY (`service_id`) REFERENCES `services` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Dumping data for table matcha_db.bookings: ~0 rows (approximately)

-- Dumping structure for table matcha_db.profiles
CREATE TABLE IF NOT EXISTS `profiles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `talent_id` varchar(50) NOT NULL,
  `bio` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `talent_id` (`talent_id`),
  CONSTRAINT `1` FOREIGN KEY (`talent_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Dumping data for table matcha_db.profiles: ~0 rows (approximately)

-- Dumping structure for table matcha_db.services
CREATE TABLE IF NOT EXISTS `services` (
  `id` varchar(50) NOT NULL,
  `talent_id` varchar(50) NOT NULL,
  `nama_layanan` varchar(100) NOT NULL,
  `tarif_dasar` double NOT NULL,
  `deskripsi` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `talent_id` (`talent_id`),
  CONSTRAINT `1` FOREIGN KEY (`talent_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Dumping data for table matcha_db.services: ~3 rows (approximately)
INSERT INTO `services` (`id`, `talent_id`, `nama_layanan`, `tarif_dasar`, `deskripsi`) VALUES
	('SRV001', 'TL001', 'Teman Mabar Game', 50000, 'Mabar game online rank up'),
	('SRV002', 'TL002', 'Teman Nonton Bioskop', 150000, 'Nemenin nonton film di bioskop'),
	('SRV003', 'TL001', 'Teman Curhat Online', 30000, 'Dengerin curhat via Discord / Telpon');

-- Dumping structure for table matcha_db.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` varchar(50) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `no_telp` varchar(20) DEFAULT NULL,
  `role` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Dumping data for table matcha_db.users: ~3 rows (approximately)
INSERT INTO `users` (`id`, `nama`, `email`, `password`, `no_telp`, `role`) VALUES
	('CL001', 'Didit Suyou Putra Lemon Tea', 'DiditLemonTea@gmail.com', '12345', '081122334455', 'CLIENT'),
	('TL001', 'Haniel J.S', 'haneil@gmail.com', '12345', '081234567890', 'TALENT'),
	('TL002', 'Kaizone', 'Kaizone@gmail.com', '12345', '081298765432', 'TALENT');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
invoices