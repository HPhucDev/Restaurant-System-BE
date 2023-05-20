CREATE TABLE `restaurant` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `short_description` varchar(200) DEFAULT NULL,
  `from_hour` int DEFAULT NULL,
  `from_minute` int DEFAULT NULL,
  `to_hour` int DEFAULT NULL,
  `to_minute` int DEFAULT NULL,
  `is_open` int DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `images` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `full_name` varchar(45) DEFAULT NULL,
  `birth_day` date DEFAULT NULL,
  `gender` enum('Nam','Nữ','Khác') DEFAULT 'Nam',
  `path_avatar` varchar(1000) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone_number` varchar(11) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `access_token` varchar(1000) DEFAULT NULL,
  `status` enum('Đã kích hoạt','Chưa kích hoạt','Đã xóa') DEFAULT 'Chưa kích hoạt',
  `role` enum('Nhân viên bếp','Nhân viên phục vụ','Nhân viên thu ngân','Quản lý') DEFAULT 'Nhân viên phục vụ',
  `id_restaurant` bigint DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `FK37wyytriq3mrkdw2jngtls6iy` (`id_restaurant`),
  CONSTRAINT `FK37wyytriq3mrkdw2jngtls6iy` FOREIGN KEY (`id_restaurant`) REFERENCES `restaurant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=278 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address_detail` varchar(255) DEFAULT NULL,
  `id_user` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user_idx_idx` (`id_user`),
  CONSTRAINT `id_user_idx` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `food_type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` enum('Gỏi','Lẩu','Nướng','Nước') DEFAULT 'Nướng',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `food_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url_images` varchar(1000) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `status` enum('Kích hoạt','Hết hạn','Hỏng','Chưa kích hoạt') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `id_restaurant` bigint NOT NULL,
  `id_food_type` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_restaurant_idx` (`id_restaurant`),
  KEY `id_food_type_idx` (`id_food_type`),
  CONSTRAINT `id_food_type_fk` FOREIGN KEY (`id_food_type`) REFERENCES `food_type` (`id`),
  CONSTRAINT `id_restaurant_fk` FOREIGN KEY (`id_restaurant`) REFERENCES `restaurant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=268 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `total` double DEFAULT NULL,
  `date_in` datetime DEFAULT NULL,
  `date_out` datetime DEFAULT NULL,
  `note` varchar(1000) DEFAULT NULL,
  `table_name` enum('TABLE_1','TABLE_2','TABLE_3','TABLE_4','TABLE_5','TABLE_6','TABLE_7','TABLE_8','TABLE_9','TABLE_10','TABLE_11','TABLE_12','TABLE_13','TABLE_14','TABLE_15','TABLE_16','TABLE_17','TABLE_18') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'TABLE_1',
  `id_user` bigint NOT NULL,
  `id_restaurant` bigint DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `id_user_idx` (`id_user`),
  KEY `id_restaurant_fk_idx` (`id_restaurant`),
  CONSTRAINT `id_restaurant_fk_id` FOREIGN KEY (`id_restaurant`) REFERENCES `restaurant` (`id`),
  CONSTRAINT `id_user_fk` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=283 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `order_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_food_item` bigint NOT NULL,
  `id_order` bigint NOT NULL,
  `quantity` double DEFAULT NULL,
  `note` varchar(100) DEFAULT NULL,
  `status` enum('Mới','Chấp nhận','Từ chối','Hoàn thành') DEFAULT 'Mới',
  PRIMARY KEY (`id`),
  KEY `fk_detail_food_idx` (`id_food_item`),
  KEY `fk_detail_order_idx` (`id_order`),
  CONSTRAINT `fk_detail_food` FOREIGN KEY (`id_food_item`) REFERENCES `food_item` (`id`),
  CONSTRAINT `fk_detail_order` FOREIGN KEY (`id_order`) REFERENCES `order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `order_pairing` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `table_name_join` enum('TABLE_1','TABLE_2','TABLE_3','TABLE_4','TABLE_5','TABLE_6','TABLE_7','TABLE_8','TABLE_9','TABLE_10','TABLE_11','TABLE_12','TABLE_13','TABLE_14','TABLE_15','TABLE_16','TABLE_17','TABLE_18') DEFAULT 'TABLE_1',
  `id_order` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_idx` (`id_order`),
  CONSTRAINT `fk_order` FOREIGN KEY (`id_order`) REFERENCES `order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `order_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `datetime_status` datetime DEFAULT NULL,
  `id_order` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_order_fk_id_idx` (`id_order`),
  CONSTRAINT `id_order_fk_id` FOREIGN KEY (`id_order`) REFERENCES `order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=284 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


