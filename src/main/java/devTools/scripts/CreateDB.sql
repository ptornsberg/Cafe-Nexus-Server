-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema cafe_nexus
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cafe_nexus
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS ROFL DEFAULT CHARACTER SET utf8 ;
USE ROFL ;

-- -----------------------------------------------------
-- Table `cafe_nexus`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ROFL.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `salt` VARCHAR(6) NOT NULL,
  `password` VARCHAR(64) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(60) NOT NULL,
  `gender` VARCHAR(1) NOT NULL,
  `description` LONGTEXT NULL,
  `major` VARCHAR(60) NULL,
  `semester` INT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cafe_nexus`.`events`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ROFL.`events` (
  `event_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(60) NOT NULL,
  `description` LONGTEXT NULL,
  `beginning` TIMESTAMP NULL,
  `ending` TIMESTAMP NULL,
  `owner_id` INT NOT NULL,
  `created` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`event_id`),
  INDEX `fk_events_users1_idx` (`owner_id` ASC),
  CONSTRAINT `fk_events_users1`
    FOREIGN KEY (`owner_id`)
    REFERENCES ROFL.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cafe_nexus`.`posts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ROFL.`posts` (
  `post_id` INT NOT NULL AUTO_INCREMENT,
  `content` LONGTEXT NOT NULL,
  `created` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `event_id` INT NULL,
  `parent_id` INT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`post_id`),
  INDEX `fk_Post_Event_idx` (`event_id` ASC),
  INDEX `fk_post_post1_idx` (`parent_id` ASC),
  INDEX `fk_post_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_Post_Event`
    FOREIGN KEY (`event_id`)
    REFERENCES ROFL.`events` (`event_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_post1`
    FOREIGN KEY (`parent_id`)
    REFERENCES ROFL.`posts` (`post_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES ROFL.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cafe_nexus`.`events_has_users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ROFL.`events_has_users` (
  `user_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `event_id`),
  INDEX `fk_user_has_event_event1_idx` (`event_id` ASC),
  INDEX `fk_user_has_event_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_event_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES ROFL.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_event_event1`
    FOREIGN KEY (`event_id`)
    REFERENCES ROFL.`events` (`event_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
