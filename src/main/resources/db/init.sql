SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `booking_service`;

CREATE SCHEMA IF NOT EXISTS `booking_service`
  DEFAULT CHARACTER SET utf8;
USE `booking_service`;

--  //////////////////////////////////////


DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id`           INT NOT NULL AUTO_INCREMENT,
  `city_name`    VARCHAR(45),
  `country_name` VARCHAR(45),
  INDEX `idx_country_name` (`country_name`),
  PRIMARY KEY (`id`)
)
  AUTO_INCREMENT = 100000,
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;


DROP TABLE IF EXISTS `country`;
CREATE TABLE `country` (
  `id`           TINYINT NOT NULL AUTO_INCREMENT,
  `country_name` VARCHAR(45),
  INDEX `idx_country_name`(`country_name`),
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

-- /////////////////////////////////////////

DROP TABLE IF EXISTS `user`;

CREATE TABLE IF NOT EXISTS `user` (
  `id`         INT         NOT NULL AUTO_INCREMENT,
  `email`      VARCHAR(45) NOT NULL UNIQUE,
  `name`       VARCHAR(45) NULL,
  `password`   VARCHAR(45) NOT NULL,
  `registered` TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
  INDEX `user_unique_email_idx`(`email`),
  PRIMARY KEY (`id`)
)
  AUTO_INCREMENT = 100000,
  ENGINE = InnoDB;


DROP TABLE IF EXISTS `user_role`;

CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` INT         NOT NULL,
  `role`    VARCHAR(45) NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_user_role_user1`
  FOREIGN KEY (`user_id`)
  REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

--    /////////////////////////////////////////


DROP TABLE IF EXISTS `vote`;

CREATE TABLE IF NOT EXISTS `vote` (
  `id`              INT           NOT NULL AUTO_INCREMENT,
  `rate`            DECIMAL(2, 1) NOT NULL,
  `user_comment`    TINYTEXT      NULL,
  `date_added`      TIMESTAMP     NOT NULL,
  `user_id`         INT           NOT NULL,
  `hotel_id`        INT           NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_vote_hotel1_idx` (`hotel_id` ASC),
  INDEX `fk_vote_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_vote_hotel1`
  FOREIGN KEY (`hotel_id`)
  REFERENCES `hotel` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_id1`
  FOREIGN KEY (`user_id`)
  REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  AUTO_INCREMENT = 100000,
  ENGINE = InnoDB;

--   //////////////////////////////////////////

DROP TABLE IF EXISTS `hotel`;

CREATE TABLE IF NOT EXISTS `hotel` (
  `id`          INT           NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(45)   NOT NULL,
  `rating`      DECIMAL(2, 1) NULL,
  `stars`       TINYINT       NULL,
  `country_id`  TINYINT       NOT NULL,
  `city_id`     INT           NOT NULL,
  `address`     VARCHAR(45)   NOT NULL,
  `phone`       VARCHAR(15)   NULL,
  `description` TINYTEXT      NULL,
  `check_in`    TIME          NOT NULL,
  `check_out`   TIME          NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_hotel_country1_idx` (`country_id` ASC),
  INDEX `fk_hotel_city1_idx` (`city_id` ASC),
  CONSTRAINT `fk_hotel_country1`
  FOREIGN KEY (`country_id`)
  REFERENCES `country` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_hotel_city1`
  FOREIGN KEY (`city_id`)
  REFERENCES `city` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  AUTO_INCREMENT = 100000,
  ENGINE = InnoDB;


DROP TABLE IF EXISTS `apt_type`;

CREATE TABLE IF NOT EXISTS `apt_type` (
  `id`               TINYINT      NOT NULL,
  `beds_arrangement` VARCHAR(255) NULL,
  `category`         VARCHAR(255) NULL,
  `person_num`       TINYINT      NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;


DROP TABLE IF EXISTS `apartment`;

CREATE TABLE IF NOT EXISTS `apartment` (
  `id`                INT            NOT NULL AUTO_INCREMENT,
  `apt_type_id`       TINYINT        NOT NULL,
  `overall_quantity`  TINYINT        NOT NULL,
  `reserved_quantity` TINYINT        NOT NULL,
  `price`             DECIMAL(11, 4) NULL,
  `hotel_id`          INT            NOT NULL,
  PRIMARY KEY (`id`, `hotel_id`),
  INDEX `fk_apartment_hotel1_idx` (`hotel_id` ASC),
  INDEX `fk_apartment_apt_type1_idx` (`apt_type_id` ASC),
  CONSTRAINT `fk_apartment_hotel1`
  FOREIGN KEY (`hotel_id`)
  REFERENCES `hotel` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_apartment_apt_type1`
  FOREIGN KEY (`apt_type_id`)
  REFERENCES `apt_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  AUTO_INCREMENT = 100000,
  ENGINE = InnoDB;


DROP TABLE IF EXISTS `booking`;

CREATE TABLE IF NOT EXISTS `booking` (
  `id`                 BIGINT         NOT NULL AUTO_INCREMENT,
  `active`             BOOLEAN        DEFAULT TRUE,
  `date_added`         TIMESTAMP      DEFAULT CURRENT_TIMESTAMP,
  `in_date`            TIMESTAMP      DEFAULT CURRENT_TIMESTAMP,
  `out_date`           TIMESTAMP      DEFAULT CURRENT_TIMESTAMP,
  `sum`                DECIMAL(11, 4) NOT NULL,
  `person_num`         TINYINT        NOT NULL,
  `extra_beds`         TINYINT        NULL,
  `user_id`            INT            NOT NULL,
  `apartment_id`       INT            NOT NULL,
  `apartment_hotel_id` INT            NOT NULL,
  PRIMARY KEY (`id`, `user_id`, `apartment_id`, `apartment_hotel_id`),
  INDEX `fk_booking_user1_idx` (`user_id` ASC),
  INDEX `fk_booking_apartment1_idx` (`apartment_id` ASC),
  INDEX `fk_booking_apartment_hotel1_idx` (`apartment_hotel_id` ASC),
  CONSTRAINT `fk_booking_user1`
  FOREIGN KEY (`user_id`)
  REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_booking_apartment1`
  FOREIGN KEY (`apartment_id`, `apartment_hotel_id`)
  REFERENCES `apartment` (`id`, `hotel_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
)
  AUTO_INCREMENT = 100000,
  ENGINE = InnoDB;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
