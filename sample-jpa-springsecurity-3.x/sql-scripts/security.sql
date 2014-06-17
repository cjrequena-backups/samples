SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';


-- -----------------------------------------------------
-- Table `SECURITY`.`ROLE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SECURITY`.`ROLE` ;

CREATE  TABLE IF NOT EXISTS `SECURITY`.`ROLE` (
  `id_role` INT NOT NULL AUTO_INCREMENT ,
  `role_name` VARCHAR(45) NOT NULL ,
  `date_created` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,
  `date_updated` TIMESTAMP NULL ,
  PRIMARY KEY (`id_role`) ,
  UNIQUE INDEX `name_UNIQUE` (`role_name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SECURITY`.`RIGHT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SECURITY`.`RIGHT` ;

CREATE  TABLE IF NOT EXISTS `SECURITY`.`RIGHT` (
  `id_right` INT NOT NULL AUTO_INCREMENT ,
  `right_name` VARCHAR(45) NOT NULL ,
  `date_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `date_updated` TIMESTAMP NULL ,
  PRIMARY KEY (`id_right`) ,
  UNIQUE INDEX `name_UNIQUE` (`right_name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SECURITY`.`USER`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SECURITY`.`USER` ;

CREATE  TABLE IF NOT EXISTS `SECURITY`.`USER` (
  `id_user` INT(11) NOT NULL AUTO_INCREMENT ,
  `email` VARCHAR(100) NOT NULL ,
  `password` VARCHAR(50) NOT NULL ,
  `enabled` TINYINT(1)  NOT NULL DEFAULT 0 ,
  `alias` VARCHAR(50) NULL ,
  `first_name` VARCHAR(50) NOT NULL ,
  `last_name` VARCHAR(50) NOT NULL ,
  `gender` VARCHAR(1) NULL ,
  `birthday` DATE NULL ,
  `id_country` INT(11) NULL ,
  `id_city` INT(11) NULL ,
  `zip_code` VARCHAR(10) NULL ,
  `mobile_phone` VARCHAR(20) NULL ,
  `other_phone` VARCHAR(20) NULL ,
  `google_account` VARCHAR(100) NULL ,
  `msn_account` VARCHAR(100) NULL ,
  `facebook_account` VARCHAR(100) NULL ,
  `skype` VARCHAR(100) NULL ,
  `date_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `date_updated` TIMESTAMP NULL DEFAULT NULL ,
  `accountNonExpired` TINYINT(1)  NOT NULL DEFAULT 1 ,
  `accountNonLocked` TINYINT(1)  NOT NULL DEFAULT 1 ,
  `credentialsNonExpired` TINYINT(1)  NOT NULL DEFAULT 1 ,
  `meta_data_1` VARCHAR(45) NULL ,
  `meta_data_2` VARCHAR(45) NULL ,
  `meta_data_3` VARCHAR(45) NULL ,
  `meta_data_4` VARCHAR(45) NULL ,
  `meta_data_5` VARCHAR(45) NULL ,
  PRIMARY KEY (`id_user`) ,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) ,
  UNIQUE INDEX `alias_UNIQUE` (`alias` ASC) )
ENGINE = InnoDB
PACK_KEYS = Default;


-- -----------------------------------------------------
-- Table `SECURITY`.`USER_TEMPORARY`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SECURITY`.`USER_TEMPORARY` ;

CREATE  TABLE IF NOT EXISTS `SECURITY`.`USER_TEMPORARY` (
  `id_user_temporary` INT NOT NULL AUTO_INCREMENT ,
  `uuid` VARCHAR(36) NOT NULL ,
  `email` VARCHAR(100) NOT NULL ,
  `first_name` VARCHAR(50) NOT NULL ,
  `last_name` VARCHAR(50) NOT NULL ,
  `password` VARCHAR(32) NOT NULL ,
  `mobile_phone` VARCHAR(20) NOT NULL ,
  `enabled` TINYINT(1)  NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`id_user_temporary`) ,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) ,
  UNIQUE INDEX `uuid_UNIQUE` (`uuid` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SECURITY`.`USER_ROLE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SECURITY`.`USER_ROLE` ;

CREATE  TABLE IF NOT EXISTS `SECURITY`.`USER_ROLE` (
  `id_user` INT(11) NOT NULL ,
  `id_role` INT NOT NULL ,
  PRIMARY KEY (`id_user`, `id_role`) ,
  INDEX `fk_USER_has_ROLE_ROLE1_idx` (`id_role` ASC) ,
  INDEX `fk_USER_has_ROLE_USER_idx` (`id_user` ASC) ,
  CONSTRAINT `fk_USER_has_ROLE_USER`
    FOREIGN KEY (`id_user` )
    REFERENCES `SECURITY`.`USER` (`id_user` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USER_has_ROLE_ROLE1`
    FOREIGN KEY (`id_role` )
    REFERENCES `SECURITY`.`ROLE` (`id_role` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SECURITY`.`ROLE_RIGHT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SECURITY`.`ROLE_RIGHT` ;

CREATE  TABLE IF NOT EXISTS `SECURITY`.`ROLE_RIGHT` (
  `id_role` INT NOT NULL ,
  `id_right` INT NOT NULL ,
  PRIMARY KEY (`id_role`, `id_right`) ,
  INDEX `fk_ROLE_has_RIGHT_RIGHT1_idx` (`id_right` ASC) ,
  INDEX `fk_ROLE_has_RIGHT_ROLE1_idx` (`id_role` ASC) ,
  CONSTRAINT `fk_ROLE_has_RIGHT_ROLE1`
    FOREIGN KEY (`id_role` )
    REFERENCES `SECURITY`.`ROLE` (`id_role` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ROLE_has_RIGHT_RIGHT1`
    FOREIGN KEY (`id_right` )
    REFERENCES `SECURITY`.`RIGHT` (`id_right` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
