DROP SCHEMA IF EXISTS `room_service`;

CREATE SCHEMA IF NOT EXISTS `room_service`;

USE `room_service` ;

CREATE TABLE `country` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
);

CREATE TABLE `room` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bulb` bit(1) DEFAULT 0,
  `name` varchar(255) NOT NULL UNIQUE,
  `country_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `usr` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL UNIQUE,
  `country_id` bigint NOT NULL,
  `room_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),

  CONSTRAINT `fk_user_to_country` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`),
  CONSTRAINT `fk_user_to_room` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`)
);

ALTER TABLE `room` ADD CONSTRAINT `fk_room_to_country` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`);
ALTER TABLE `room` ADD  CONSTRAINT `fk_room_to_user` FOREIGN KEY (`user_id`) REFERENCES `usr` (`id`);

INSERT INTO `country` (`id`, `name`) VALUES ('1', 'Беларусь');
INSERT INTO `country` (`id`, `name`) VALUES ('2', 'Польша');
INSERT INTO `country` (`id`, `name`) VALUES ('3', 'Литва');
INSERT INTO `country` (`id`, `name`) VALUES ('4', 'США');

INSERT INTO `usr` (`id`, `name`, `country_id`) VALUES ('1', 'Роман', 1);
INSERT INTO `usr` (`id`, `name`, `country_id`) VALUES ('2', 'Томас', '2');
INSERT INTO `usr` (`id`, `name`, `country_id`) VALUES ('3', 'Владислав', '3');
INSERT INTO `usr` (`id`, `name`, `country_id`) VALUES ('4', 'Роберт', '4');

INSERT INTO `room` (`id`, `name`, `country_id`, `user_id`) VALUES ('1', 'Комната Романа', '1', '1');
INSERT INTO `room` (`id`, `name`, `country_id`, `user_id`) VALUES ('2', 'Комната Романа2', '1', '1');
INSERT INTO `room` (`id`, `name`, `country_id`, `user_id`) VALUES ('3', 'Комната Томаса', '2', '2');
INSERT INTO `room` (`id`, `name`, `country_id`, `user_id`) VALUES ('4', 'Комната Владислава', '3', '3');

UPDATE usr SET room_id = 3 where id=1;
UPDATE usr SET room_id = 3 where id=2;
UPDATE usr SET room_id = 3 where id=3;
