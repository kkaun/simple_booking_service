
DELETE FROM vote;
ALTER TABLE vote AUTO_INCREMENT = 100000;
DELETE FROM sub_booking;
ALTER TABLE sub_booking AUTO_INCREMENT = 100000;
DELETE FROM booking;
ALTER TABLE booking AUTO_INCREMENT = 100000;
DELETE FROM apartment;
ALTER TABLE apartment AUTO_INCREMENT = 100000;
DELETE FROM apt_type;
ALTER TABLE apt_type AUTO_INCREMENT = 1;
DELETE FROM hotel;
ALTER TABLE hotel AUTO_INCREMENT = 100000;
DELETE FROM user_role;
ALTER TABLE user_role;
DELETE FROM user;
ALTER TABLE user AUTO_INCREMENT = 100000;
DELETE FROM city;
ALTER TABLE city AUTO_INCREMENT = 100000;
DELETE FROM country;
ALTER TABLE country AUTO_INCREMENT = 1;




INSERT INTO country
(name)
VALUES
  ('Russia'),
  ('Ukraine');


INSERT INTO city
(name, country_id, description, img_path)
VALUES
  ('Moscow', 1, 'Moscow Description', ''),
  ('St Petersburg', 1, 'St Petersburg Description', ''),
  ('Kiev', 2, 'Kiev Description', ''),
  ('Lviv', 2, 'Lviv Description', '');



INSERT INTO user (name, email, phone, password)
VALUES
  ('User1', 'user1@yandex.ru', '483748273423', '$2a$04$LSa4NOGDwRsAomYMG10tdebOfo9BfrAjV9FvymxMt/IORffJ1tJmy'),
  ('User2', 'user2@yandex.ru', '483711111123', '$2a$04$LptUR6XmDAiZH76ojCdNi.M8BPhAyBN8D2uvEPplrPStLwEIXYPvm'),
  ('User3', 'user3@yandex.ru', '483333373423', '$2a$04$cxtnaMTgA/zkzqu5abBmd.Hj5B.Dp9Wfk1iP7ONomAApluyVyqOSa');

INSERT INTO user (name, email, phone, password)
VALUES ('Manager', 'manager@gmail.com', '432523522352', '$2a$04$GVGrIytqazsQlpU7wPgyUuoaWukZoTSJCUVfuXmaRugERWuD0l18q');

INSERT INTO user (name, email, password)
VALUES ('Admin', 'admin@gmail.com', '$2a$04$8pwICPDZ3IsZnKFuvZ2MBe5zqR6DOA20turCpBv9.jy/2Un5SpfZ2');

INSERT INTO user_role (user_id, role) VALUES
  (100000, 'ROLE_USER'),
  (100001, 'ROLE_USER'),
  (100002, 'ROLE_USER'),
  (100003, 'ROLE_MANAGER'),
  (100004, 'ROLE_ADMIN');


INSERT INTO hotel (name, stars, country_id, city_id, address, phone, description, check_in, check_out, max_extra_per_day, manager)
VALUES
  ('HOTEL1', 3, 1, 100000, 'Address1', '89431543453', '', '14:00:00', '12:00:00', 3, 100003),
  ('HOTEL2', 4, 1, 100001, 'Address2', '89431564565', 'Description2', '14:00:00', '12:00:00', 3, 100003),
  ('HOTEL3', 0, 2, 100002, 'Address3', '894312223222', 'Description3', '14:00:00', '12:00:00', 3, 100003),
  ('HOTEL4', 4, 2, 100003, 'Address4', '894312223222', 'Description4', '14:00:00', '12:00:00', 3, 100003);


INSERT INTO apt_type(beds_arrangement, category, person_num)
VALUES

  ('ONE SINGLE BED', 'STANDARD', 1),
  ('TWO SINGLE BEDS', 'STANDARD', 2),
  ('TWO SEPARATE BEDS', 'STANDARD', 2),
  ('TWO SINGLE BEDS', 'SUPERIOR', 2),
  ('TWO SEPARATE BEDS', 'SUPERIOR', 2);


INSERT INTO apartment (apt_type_id, price, hotel_id)
VALUES
  (1, 1200.00, 100000),
  (2, 1500.00, 100000),
  (3, 3290.00, 100001),
  (4, 4600.00, 100002),
  (1, 1200.00, 100003);


INSERT INTO booking (active, date_added, extra_beds, overall_sum, overall_person_num, user_id,
                           hotel_id, booker_name, booker_email, booker_phone)
VALUES
  (1, '2017-05-12 16:17:00', 0, 6000.00, 1, 100000, 100000, 'Name1', 'email1@gmail.com', '384543534822'),
  (1, '2017-01-23 11:21:00', 0, 4500.00, 2, 100001, 100000, 'Name2', 'email2@gmail.com', '384546474822'),
  (1, '2017-03-08 19:25:00', 0, 3200.00, 2, 100002, 100001, 'Name3', 'email3@gmail.com', '384367654822'),
  (1, '2017-05-02 10:43:00', 0, 10000.00, 2, 100000, 100002, 'Name4', 'emai41@gmail.com', '3842344822'),
  (0, '2017-02-15 16:35:00', 0, 31200.00, 2, 100004, 100003, 'Name5', 'email5@gmail.com', '384376754822');


INSERT INTO sub_booking (in_date, out_date, sum, person_num, booking_id, apartment_id, apartment_hotel_id, edited)
VALUES
  ('2017-05-23', '2017-05-28', 6000.00, 1, 100000, 100000, 100000, '2017-09-01 00:00:00'),
  ('2017-04-14', '2017-04-17', 4500.00, 2, 100001, 100001, 100000, '2017-09-01 00:00:00'),
  ('2017-06-06', '2017-06-07', 3200.00, 2, 100002, 100002, 100001, '2017-09-01 00:00:00'),
  ('2017-05-21', '2017-05-23', 10000.00, 2, 100003, 100003, 100002, '2017-09-01 00:00:00'),
  ('2017-05-21', '2017-05-28', 31200.00, 2, 100004, 100004, 100003, '2017-09-01 00:00:00');



INSERT INTO vote (rate, user_comment, date_added, user_id, hotel_id)
VALUES
  (9.5, 'User Vote comment  1', '2017-06-16 19:32:00', 100000, 100000),
  (6.5, 'User Vote comment  2', '2017-05-04 09:45:00', 100001, 100000),
  (5.5, 'User Vote comment  3', '2017-06-10 13:45:00', 100002, 100001);




