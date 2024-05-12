CREATE TABLE `pet_cad` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(100) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) NOT NULL,
  `pet_breed` varchar(50) NOT NULL,
  `pet_kind` varchar(50) NOT NULL,
  `pet_name` varchar(80) NOT NULL,
  `pet_owner` varchar(80) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;