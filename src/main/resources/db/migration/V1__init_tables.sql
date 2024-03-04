-- lmph_be.employees definition

CREATE TABLE IF NOT EXISTS `employees` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `last_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `middle_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `gender` varchar(5) DEFAULT NULL,
  `marital_status` varchar(5) DEFAULT NULL,
  `company_position` varchar(300) DEFAULT NULL,
  `date_hired` date DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- lmph_be.contacts definition

CREATE TABLE IF NOT EXISTS `contacts` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `employee_id` bigint NOT NULL,
  `contact_no` varchar(30) DEFAULT NULL,
  `is_primary` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `contacts_employee_id_IDX` (`employee_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- lmph_be.addresses definition

CREATE TABLE IF NOT EXISTS `addresses` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `employee_id` bigint NOT NULL,
  `address1` varchar(100) DEFAULT NULL,
  `address2` varchar(100) DEFAULT NULL,
  `is_primary` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `addresses_employee_id_IDX` (`employee_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- lmph_be.section definition

CREATE TABLE IF NOT EXISTS `section` (
  `section_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`section_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- lmph_be.subsection definition

CREATE TABLE IF NOT EXISTS `subsection` (
  `subsection_id` bigint NOT NULL AUTO_INCREMENT,
  `section_id` bigint NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `facilitator` varchar(50) DEFAULT NULL,
  `details` varchar(100) DEFAULT NULL,
  `target_day` varchar(50) DEFAULT NULL,
  `target_sprint` varchar(50) DEFAULT NULL,
  `sort_order` int DEFAULT '0',
  PRIMARY KEY (`subsection_id`),
  KEY `subsection_section_id_IDX` (`section_id`) USING BTREE,
  CONSTRAINT `subsection_section_id_FK` FOREIGN KEY (`section_id`) REFERENCES `section` (`section_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- lmph_be.flow definition

CREATE TABLE IF NOT EXISTS `flow` (
  `flow_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`flow_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- lmph_be.flow_section definition

CREATE TABLE IF NOT EXISTS `flow_section` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `section_id` bigint NOT NULL,
  `flow_id` bigint NOT NULL,
  `pass_fail_flag` boolean DEFAULT FALSE,
  `sort_order` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `flow_section_section_id_IDX` (`section_id`) USING BTREE,
  KEY `flow_section_flow_id_IDX` (`flow_id`) USING BTREE,
  CONSTRAINT `flow_section_section_id_FK` FOREIGN KEY (`section_id`) REFERENCES `section` (`section_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `flow_section_flow_id_FK` FOREIGN KEY (`flow_id`) REFERENCES `flow` (`flow_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- lmph_be.employee_flow definition

CREATE TABLE IF NOT EXISTS `employee_flow` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `employee_id` bigint NOT NULL,
  `flow_id` bigint NOT NULL,
  `section_id` bigint NOT NULL,
  `subsection_id` bigint NOT NULL,
  `status` varchar(50) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `completed_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `employee_flow_employee_id_IDX` (`employee_id`) USING BTREE,
  KEY `employee_flow_flow_id_IDX` (`flow_id`) USING BTREE,
  KEY `employee_flow_section_id_IDX` (`section_id`) USING BTREE,
  KEY `employee_flow_subsection_id_IDX` (`subsection_id`) USING BTREE,
  CONSTRAINT `employee_flow_employee_id_FK` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `employee_flow_flow_id_FK` FOREIGN KEY (`flow_id`) REFERENCES `flow` (`flow_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `employee_flow_section_id_FK` FOREIGN KEY (`section_id`) REFERENCES `section` (`section_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `employee_flow_subsection_id_FK` FOREIGN KEY (`subsection_id`) REFERENCES `subsection` (`subsection_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

