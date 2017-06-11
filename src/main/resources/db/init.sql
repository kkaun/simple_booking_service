SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `booking_service` ;

CREATE SCHEMA IF NOT EXISTS `booking_service` DEFAULT CHARACTER SET utf8 ;
USE `booking_service` ;

DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL DEFAULT 1000000,
  `email` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `registered` VARCHAR(45) NULL,
PRIMARY KEY (`id`))
  ENGINE = InnoDB;


DROP TABLE IF EXISTS `user_role` ;

CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` INT NOT NULL,
  `role` VARCHAR(45) NULL,
PRIMARY KEY (`user_id`),
CONSTRAINT `fk_user_role_user`
FOREIGN KEY (`user_id`)
REFERENCES `user` (`id`)
ON DELETE NO ACTION
ON UPDATE NO ACTION)
  ENGINE = InnoDB;


DROP TABLE IF EXISTS `hotel` ;

CREATE TABLE IF NOT EXISTS `hotel` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
PRIMARY KEY (`id`))
  ENGINE = InnoDB;


DROP TABLE IF EXISTS `apartment` ;

CREATE TABLE IF NOT EXISTS `apartment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `number` INT NOT NULL,
  `type` VARCHAR(45) NULL,
  `persons_num` TINYINT NOT NULL,
  `is_reserved` TINYINT NOT NULL,
  `hotel_id` INT NOT NULL,
  `price` DECIMAL(11,4) NULL,
PRIMARY KEY (`number`, `hotel_id`),
INDEX `fk_apartment_hotel1_idx` (`hotel_id` ASC),
CONSTRAINT `fk_apartment_hotel1`
FOREIGN KEY (`hotel_id`)
REFERENCES `hotel` (`id`)
ON DELETE NO ACTION
ON UPDATE NO ACTION)
  ENGINE = InnoDB;


DROP TABLE IF EXISTS `booking` ;

CREATE TABLE IF NOT EXISTS `booking` (
  `id` INT NOT NULL,
  `date_added` VARCHAR(45) NULL,
  `in_date` VARCHAR(45) NULL,
  `out_date` VARCHAR(45) NULL,
  `sum` DECIMAL(11,4) NULL,
  `user_id` INT NOT NULL,
  `apartment_number` INT NOT NULL,
  `apartment_hotel_id` INT NOT NULL,
PRIMARY KEY (`id`, `user_id`, `apartment_number`, `apartment_hotel_id`),
INDEX `fk_booking_user1_idx` (`user_id` ASC),
INDEX `fk_booking_apartment1_idx` (`apartment_number` ASC, `apartment_hotel_id` ASC),
CONSTRAINT `fk_booking_user1`
FOREIGN KEY (`user_id`)
REFERENCES `user` (`id`)
ON DELETE NO ACTION
ON UPDATE NO ACTION,
CONSTRAINT `fk_booking_apartment1`
FOREIGN KEY (`apartment_number` , `apartment_hotel_id`)
REFERENCES `apartment` (`number` , `hotel_id`)
ON DELETE NO ACTION
ON UPDATE NO ACTION)
  ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
