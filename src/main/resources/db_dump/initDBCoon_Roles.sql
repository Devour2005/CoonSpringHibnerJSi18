
-- ORDER OF TABLES IS VERY IMPORTANT

--  SCRIPT USED TO INIT DATABASE
SET FOREIGN_KEY_CHECKS=0;

CREATE DATABASE IF NOT EXISTS coonportalsprng;
USE coonportalsprng;
DROP TABLE IF EXISTS members CASCADE;
DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS computers CASCADE;
DROP TABLE IF EXISTS usercomp CASCADE;

USE coonportalsprng;
CREATE TABLE roles (
  role_id   INT(11)    NOT NULL,
  rolename VARCHAR(30) NOT NULL DEFAULT 'admin',
  PRIMARY KEY (role_id)
)
  ENGINE =InnoDB;

USE coonportalsprng;
CREATE TABLE members (
  user_id   INT(11) AUTO_INCREMENT,
  login    VARCHAR(30) NOT NULL NULL DEFAULT '' UNIQUE,
  name     VARCHAR(30) NOT NULL DEFAULT '',
  password VARCHAR(30) NOT NULL DEFAULT '',
  email    VARCHAR(30) NOT NULL DEFAULT '' UNIQUE,
  regdate  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
  role_id   INT(11)  NOT NULL DEFAULT 1,
  INDEX `role_id_fk` (`role_id`),
  CONSTRAINT `role_id_fk` FOREIGN KEY (`role_id`)  REFERENCES `roles` (`role_id`)
  ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (user_id)
)
  ENGINE =InnoDB;



USE coonportalsprng;
CREATE TABLE computers (
  comp_id   INT(11) AUTO_INCREMENT,
  pcname   VARCHAR(30) NOT NULL UNIQUE,
  monitor  VARCHAR(30) NOT NULL DEFAULT '',
  keyboard VARCHAR(30) NOT NULL DEFAULT '',
  mouse    VARCHAR(30) NOT NULL DEFAULT '',
  cpu      VARCHAR(30) NOT NULL DEFAULT '',
  PRIMARY KEY (comp_id)
)
  ENGINE =InnoDB;

USE coonportalsprng;
CREATE TABLE usercomp (
  user_id INT(11) NOT NULL,
  comp_id INT(11) /*NOT NULL*/,
  INDEX fk_user_id (user_id),
  INDEX fk_comp_id (comp_id),
  CONSTRAINT fk_user_id FOREIGN KEY (user_id)
  REFERENCES members (user_id)
     ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_comp_id FOREIGN KEY (comp_id)
  REFERENCES computers (comp_id)
      ON DELETE CASCADE ON UPDATE CASCADE
)
  ENGINE =InnoDB;

USE coonportalsprng;
INSERT INTO `roles` (`role_id`,`rolename`)
  VALUES
  (1, 'admin'),
  (2, 'user');

-- SCRIPT USED TO INITIALIZE TABLES DATA
USE coonportalsprng;
INSERT INTO `members` (`login`, `name`, `password`, `email`, `role_id`)
  VALUES
  ('user1', 'user1', '1', 'user1@g.c', '1'),
  ('user2', 'user2', '2', 'user2@g.c', '2'),
  ('user3', 'user3', '3', 'user3@g.c', '2'),
  ('user4', 'user4', '4', 'user4@g.c', '2'),
  ('user5', 'user5', '5', 'user5@g.c', '2'),
  ('user6', 'user6', '6', 'user6@g.c', '2'),
  ('user7', 'user7', '7', 'user7@g.c', '2'),
  ('user8', 'user8', '8', 'user8@g.c', '2');

-- SCRIPT USED TO INITIALIZE TABLES DATA

USE coonportalsprng;
INSERT INTO `computers` (`pcname`, `monitor`, `keyboard`, `mouse`, `cpu`)
  VALUES
  ('PC1', 'LG', 'Sven', 'Logitech', 'AMD Athlon X2'),
  ('PC2', 'Samsung', 'Sven', 'A4tech', 'Intel Core i3 3210'),
  ('PC3', 'ViewSonic', 'Sven', 'Asus', 'AMD Athlon 64 FX'),
  ('PC4', 'Samsung', 'Sven', 'Razer', 'Intel Xeon E3-1230'),
  ('PC5', 'LG', 'Sven', 'Sven', 'AMD Turion 64'),
  ('PC6', 'Asus', 'Sven', 'Sven', 'Intel Core i7 3770'),
  ('PC7', 'Apple', 'Sven', 'Logitech', 'Intel Core i5 3470T'),
  ('PC8', 'Asus', 'Sven', 'Asus', 'Intel Core i5 3550S');

-- SCRIPT USED TO INITIALIZE TABLES DATA

USE coonportalsprng;
INSERT INTO `usercomp` (`user_id`, `comp_id`)
  VALUES
  ('1', '3'),
  ('2', '2'),
  ('3', '7'),
  ('4', '5'),
  ('5', '8'),
  ('6', '1'),
  ('7', '4'),
  ('8', '6');
SET FOREIGN_KEY_CHECKS=1;

 
  