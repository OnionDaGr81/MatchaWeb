CREATE DATABASE IF NOT EXISTS `matcha_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_uca1400_ai_ci */;
USE `matcha_db`;

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
INSERT IGNORE INTO `users` (`id`, `nama`, `email`, `password`, `no_telp`, `role`) VALUES
	('CL001', 'Didit Suyou Putra Lemon Tea', 'DiditLemonTea@gmail.com', '12345', '081122334455', 'CLIENT'),
	('TL001', 'Haniel J.S', 'haneil@gmail.com', '12345', '081234567890', 'TALENT'),
	('TL002', 'Kaizone', 'Kaizone@gmail.com', '12345', '081298765432', 'TALENT');

-- Dumping structure for table matcha_db.profiles
CREATE TABLE IF NOT EXISTS `profiles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `talent_id` varchar(50) NOT NULL,
  `bio` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `talent_id` (`talent_id`),
  CONSTRAINT `fk_profiles_talent` FOREIGN KEY (`talent_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Dumping structure for table matcha_db.services
CREATE TABLE IF NOT EXISTS `services` (
  `id` varchar(50) NOT NULL,
  `talent_id` varchar(50) NOT NULL,
  `nama_layanan` varchar(100) NOT NULL,
  `tarif_dasar` double NOT NULL,
  `deskripsi` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `talent_id` (`talent_id`),
  CONSTRAINT `fk_services_talent` FOREIGN KEY (`talent_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Dumping data for table matcha_db.services: ~3 rows (approximately)
INSERT IGNORE INTO `services` (`id`, `talent_id`, `nama_layanan`, `tarif_dasar`, `deskripsi`) VALUES
	('SRV001', 'TL001', 'Teman Mabar Game', 50000, 'Mabar game online rank up'),
	('SRV002', 'TL002', 'Teman Nonton Bioskop', 150000, 'Nemenin nonton film di bioskop'),
	('SRV003', 'TL001', 'Teman Curhat Online', 30000, 'Dengerin curhat via Discord / Telpon');

-- Dumping structure for table matcha_db.talent_availability
CREATE TABLE IF NOT EXISTS `talent_availability` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `talent_id` varchar(50) NOT NULL,
  `hari` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `talent_id` (`talent_id`),
  CONSTRAINT `fk_avail_talent` FOREIGN KEY (`talent_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Dumping structure for table matcha_db.bookings
CREATE TABLE IF NOT EXISTS `bookings` (
  `id` varchar(50) NOT NULL,
  `client_id` varchar(50) DEFAULT NULL,
  `talent_id` varchar(50) DEFAULT NULL,
  `service_id` varchar(50) DEFAULT NULL,
  `waktu_mulai` datetime NOT NULL,
  `waktu_selesai` datetime NOT NULL,
  `status` varchar(20) NOT NULL DEFAULT 'Pending',
  `catatan` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `client_id` (`client_id`),
  KEY `talent_id` (`talent_id`),
  KEY `service_id` (`service_id`),
  CONSTRAINT `fk_bookings_client` FOREIGN KEY (`client_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_bookings_talent` FOREIGN KEY (`talent_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_bookings_service` FOREIGN KEY (`service_id`) REFERENCES `services` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

ALTER TABLE bookings ADD COLUMN catatan TEXT DEFAULT NULL;

-- Dumping structure for table matcha_db.invoices
CREATE TABLE IF NOT EXISTS `invoices` (
  `id` varchar(50) NOT NULL,
  `booking_id` varchar(50) NOT NULL,
  `jumlah` double NOT NULL,
  `status` varchar(20) NOT NULL DEFAULT 'Unpaid',
  `created_at` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `booking_id` (`booking_id`),
  CONSTRAINT `fk_invoices_booking` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;