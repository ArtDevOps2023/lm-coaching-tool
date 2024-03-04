-- drop pass_fail_flag from flow_section
ALTER TABLE `flow_section` DROP COLUMN `pass_fail_flag`;

-- add pass_fail_flag to employee_flow instead
ALTER TABLE `employee_flow` ADD COLUMN `pass_fail_flag` varchar(1) DEFAULT 'U' AFTER `status`;

CREATE TABLE `lmph_be`.`employee_subsection_status` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `employee_id` BIGINT NOT NULL,
  `subsection_id` BIGINT NOT NULL,
  `status` VARCHAR(7) NULL,
  `start_date` DATETIME NULL,
  `completed_date` DATETIME NULL,
  PRIMARY KEY (`id`),

  CONSTRAINT `fk_employees_employee_subsection_status_id`
  FOREIGN KEY (employee_id)
  REFERENCES `employees`(id),

  CONSTRAINT `fk_subsection_employee_subsection_status_subsection_id`
  FOREIGN KEY (subsection_id)
  REFERENCES `subsection`(subsection_id)

 );


ALTER TABLE `lmph_be`.`employee_flow`
DROP FOREIGN KEY `employee_flow_subsection_id_FK`,
DROP FOREIGN KEY `employee_flow_section_id_FK`;
ALTER TABLE `lmph_be`.`employee_flow`
    DROP COLUMN `completed_date`,
    DROP COLUMN `start_date`,
    DROP COLUMN `pass_fail_flag`,
    DROP COLUMN `status`,
    DROP COLUMN `subsection_id`,
    DROP COLUMN `section_id`,
    DROP INDEX `employee_flow_subsection_id_IDX` ,
    DROP INDEX `employee_flow_section_id_IDX` ;
;

ALTER TABLE `lmph_be`.`flow`
ADD COLUMN `color` VARCHAR(45) NULL AFTER `name`;

ALTER TABLE `lmph_be`.`employee_flow`
ADD COLUMN `sort_order` INT NOT NULL AFTER `flow_id`;