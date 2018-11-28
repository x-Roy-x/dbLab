drop database `lab_6_r`; 

CREATE SCHEMA `lab_6_r` DEFAULT CHARACTER SET utf8;
USE `lab_6_r`;

CREATE TABLE Password_of_shopper
(
  password_of_shopper VARCHAR(50) NOT NULL,
  PRIMARY KEY (password_of_shopper)
) ENGINE = InnoDB;

CREATE TABLE Shopper
(
  shopper_id INT NOT NULL AUTO_INCREMENT,
  surname_of_shopper VARCHAR(50) NOT NULL,
  name_of_shopper VARCHAR(50) NOT NULL,
  password_of_shopper VARCHAR(50) NOT NULL,
  age_of_shopper VARCHAR(50) NULL,
  PRIMARY KEY (shopper_id),
  CONSTRAINT FOREIGN KEY (password_of_shopper)
    REFERENCES Password_of_shopper (password_of_shopper) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE Good
(
  good_id INT NOT NULL AUTO_INCREMENT,
  name_of_good VARCHAR(50) NOT NULL,
  country_of_manufacture VARCHAR(50) NOT NULL,
  price VARCHAR(50) NOT NULL,
  PRIMARY KEY (good_id)
) ENGINE = InnoDB;

CREATE TABLE  Shopper_and_good (
  shopper_id INT NOT NULL,
  good_id INT NOT NULL,
  PRIMARY KEY (shopper_id, good_id),
  CONSTRAINT  FOREIGN KEY (shopper_id)
    REFERENCES  Shopper(shopper_id) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT   FOREIGN KEY (good_id)
    REFERENCES  Good (good_id) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE = InnoDB;

INSERT INTO Password_of_shopper(password_of_shopper)
VALUES('12345'),
	('12346'),
	('12347'),
	('12348'),
	('12349'),
	('123410');


INSERT INTO Shopper(shopper_id, name_of_shopper, surname_of_shopper, password_of_shopper, age_of_shopper)
VALUES(1, 'Дмитро','Мельник', '12345', '18'),
    (2, 'Оксана','Шевченко', '12346', '18'),
    (3, 'Олександр','Бойко', '12347', '18'),
    (4, 'Людмила','Коваленко', '12348', '18'),
    (5, 'Андрій','Бондаренко', '12349', '18'),
    (6, 'Наталія','Ткаченко', '123410', '18'),
    (7, 'Петро','Ковальчук', '12345', '18'),
    (8, 'Ангеліна','Кравченко', '12346', '18'),
    (9, 'Віталій','Олійник', '12347', '18'),
	(10, 'Надія','Шевчук', '12348', '18'),
    (11, 'Дмитро','Коваль', '12349', '18'),
    (12, 'Оксана','Поліщук', '123410', '18'),
    (13, 'Олександр','Бондар', '12345', '18'),
    (14, 'Людмила','Ткачук', '12346', '18'),
    (15, 'Андрій','Морозов', '12347', '18'),
    (16, 'Наталія','Марченко', '12348', '18'),
    (17, 'Петро','Кравчук', '12349', '18'),
    (18, 'Ангеліна','Клименок', '123410', '18'),
    (19, 'Віталій','Павленко', '12345', '18'),
	(20, 'Надія','Савчук', '12346', '18');


INSERT INTO Good(good_id, name_of_good, country_of_manufacture, price)
VALUES(1, 'Молоко','Україна', '20'),
    (2, 'Шоколад','Україна', '25'),
    (3, 'Хліб','Україна', '50'),
    (4, 'Гречка','Україна', '40'),
    (5, 'Вода','Україна', '80'),
    (6, 'Макарони','Україна', '30');


INSERT INTO Shopper_and_good(shopper_id, good_id)
VALUES(1,1),
	(2,2),
	(3,3),
	(4,4),
	(5,5),
	(6,6),
	(7,1),
	(8,2),
	(9,3),
	(10,4),
	(11,5),
	(12,6),
	(13,1),
	(14,2),
	(15,3),
	(16,4),
    (17,5),
    (18,6),
    (19,1),
    (20,2);

