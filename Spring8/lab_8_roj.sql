DROP DATABASE IF EXISTS Lab_8_r;

CREATE DATABASE Lab_8_r;

USE Lab_8_r;

CREATE TABLE password_of_shopper
(
password_of_shopper_id int NOT NULL auto_increment ,
password_of_shopper int NULL,
primary key (password_of_shopper_id)
);

CREATE TABLE shopper
(
shopper_id int NOT NULL auto_increment,
name_of_shopper nvarchar(50) NULL,
surname_of_shopper nvarchar(50) NULL,
password_of_shopper_id int NULL,
primary key (shopper_id)
);

ALTER TABLE shopper

  add constraint FK_shopper_and_password_of_shopper FOREIGN KEY (password_of_shopper_id) REFERENCES password_of_shopper (password_of_shopper_id);


CREATE TABLE good
(
good_id int   NOT NULL auto_increment  ,
name_of_good nvarchar(50) NULL,
country_of_manufacture nvarchar(50) NULL,
primary key (good_id)
);


CREATE TABLE shopper_good
(
id int  NOT NULL auto_increment ,
good_id int NULL,
shopper_id int NULL,
primary key (id)
);

ALTER TABLE shopper_good

  add constraint FK_good_id FOREIGN KEY(good_id) REFERENCES good (good_id),
  add constraint FK_shopper_id FOREIGN KEY(shopper_id) REFERENCES shopper (shopper_id);


INSERT password_of_shopper(password_of_shopper) VALUES
(12345),
(12346),
(12347),
(12348),
(12349),
(123410);

INSERT shopper(name_of_shopper, surname_of_shopper, password_of_shopper_id)
VALUES('Дмитро','Мельник', 1),
    ('Оксана','Шевченко', 2),
    ('Олександр','Бойко', 3),
    ('Людмила','Коваленко', 4),
    ('Андрій','Бондаренко', 5),
    ('Наталія','Ткаченко', 6),
    ('Петро','Ковальчук', 1),
    ('Ангеліна','Кравченко', 2),
    ('Віталій','Олійник', 3),
	('Надія','Шевчук', 4),
    ('Дмитро','Коваль', 5),
    ('Оксана','Поліщук', 6),
    ('Олександр','Бондар', 1),
    ('Людмила','Ткачук', 2),
    ('Андрій','Морозов', 3),
    ('Наталія','Марченко', 4),
    ('Петро','Кравчук', 5),
    ('Ангеліна','Клименок', 6),
    ('Віталій','Павленко', 1),
	('Надія','Савчук', 2);



INSERT good(name_of_good, country_of_manufacture) 
VALUES('Молоко','Україна'),
    ('Шоколад','Україна'),
    ('Хліб','Україна'),
    ('Гречка','Україна'),
    ('Вода','Україна'),
    ('Макарони','Україна');


INSERT shopper_good(shopper_id, good_id) 
VALUES(1,1),
(2,2),
(3,3),
(4,4),
(5,5),
(6,6),
(7,1),
(8,2),
(9,3),
(10,4);
