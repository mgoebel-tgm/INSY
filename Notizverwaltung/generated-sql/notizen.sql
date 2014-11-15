
# This is a fix for InnoDB in MySQL >= 4.1.x
# It "suspends judgement" for fkey relationships until are tables are set.
SET FOREIGN_KEY_CHECKS = 0;

-- ---------------------------------------------------------------------
-- notiz
-- ---------------------------------------------------------------------

DROP TABLE IF EXISTS `notiz`;

CREATE TABLE `notiz`
(
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `text` VARCHAR(1000) NOT NULL,
    `betreff` VARCHAR(250) NOT NULL,
    `datum` DATE NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- person
-- ---------------------------------------------------------------------

DROP TABLE IF EXISTS `person`;

CREATE TABLE `person`
(
    `name` VARCHAR(250) NOT NULL,
    `beschreibung` VARCHAR(1000),
    PRIMARY KEY (`name`)
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- todonotiz
-- ---------------------------------------------------------------------

DROP TABLE IF EXISTS `todonotiz`;

CREATE TABLE `todonotiz`
(
    `status` VARCHAR(250) NOT NULL,
    `prioritaet` INTEGER
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- rezept
-- ---------------------------------------------------------------------

DROP TABLE IF EXISTS `rezept`;

CREATE TABLE `rezept`
(
    
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- projekt
-- ---------------------------------------------------------------------

DROP TABLE IF EXISTS `projekt`;

CREATE TABLE `projekt`
(
    `name` VARCHAR(250) NOT NULL,
    `startdatum` DATE NOT NULL,
    `enddatum` DATE NOT NULL,
    PRIMARY KEY (`name`)
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- subnotiz
-- ---------------------------------------------------------------------

DROP TABLE IF EXISTS `subnotiz`;

CREATE TABLE `subnotiz`
(
    `index` INTEGER NOT NULL,
    PRIMARY KEY (`index`)
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- Projektteilnehmer
-- ---------------------------------------------------------------------

DROP TABLE IF EXISTS `Projektteilnehmer`;

CREATE TABLE `Projektteilnehmer`
(
    
) ENGINE=InnoDB;

# This restores the fkey checks, after having unset them earlier
SET FOREIGN_KEY_CHECKS = 1;
