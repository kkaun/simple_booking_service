
INSERT INTO country
(country_name)
VALUES
  ('Russia'),
  ('Ukraine');


INSERT INTO city
(city_name, country_name)
VALUES
  ('Moscow', 'Russia'),
  ('St Petersburg', 'Russia'),
  ('Kiev', 'Ukraine'),
  ('Lviv', 'Ukraine');




INSERT INTO users (name, email, password)
VALUES
  ('User1', 'user1@yandex.ru', 'password1'),
  ('User2', 'user2@yandex.ru', 'password2'),
  ('User3', 'user3@yandex.ru', 'password3');

INSERT INTO users (name, email, password)
VALUES ('Manager', 'manager@gmail.com', 'manager');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_USER', 100001),
  ('ROLE_USER', 100002),
  ('ROLE_HOTEL_MANAGER', 100003),
  ('ROLE_SYSTEM_ADMIN', 100004),
  ('ROLE_USER', 100004);





INSERT INTO hotel (name, rating, stars, country_id, city_id, address, phone, description, check_in, check_out)
VALUES
  ('HOTEL1', 9.1, 3, 1, 100000, 'Address1', '8943111111', null, '14:00:00', '12:00:00'),
  ('HOTEL2', null, 4, 1, 100001, 'Address2', '8943111111', 'Description2', '14:00:00', '12:00:00'),
  ('HOTEL3', 6.8, null, 2, 100002, 'Address3', '8943111111', 'Description3', '14:00:00', '12:00:00'),
  ('HOTEL4', 7.4, 4, 2, 100003, 'Address4', '8943111111', 'Description4', '14:00:00', '12:00:00');



INSERT INTO apt_type(beds_arrangement, category, person_num)
VALUES
  ('ARRANGEMENT1', 'STANDARD', 1),
  ('ARRANGEMENT2', 'STANDARD', 2),
  ('ARRANGEMENT3', 'SUPERIOR', 2),
  ('ARRANGEMENT4', 'COMFORT', 2),
  ('ARRANGEMENT5', 'FAMILY', 4);



INSERT INTO apartment (apt_type_id, overall_quantity, reserved_quantity, price, hotel_id)
VALUES
  (1, 2, 1, 1200.00, 100000),
  (2, 3, 3, 1500.00, 100000),
  (3, 4, 0, 3200.00, 100001),
  (4, 3, 1, 4600.00, 100002),
  (5, 6, 2, 5200.00, 100003);




INSERT INTO booking (active, date_added, in_date, out_date, sum, person_num, extra_beds, user_id, apartment_id, apartment_hotel_id)
VALUES
  (1, '2016-05-12 16:17:00', '2017-05-23 14:00:00', '2017-05-28 12:00:00', 6000.00, 1, 0, 100000, 100000, 100000),
  (1, '2017-01-23 11:21:00', '2017-04-14 14:00:00', '2017-04-17 12:00:00', 4500.00, 2, 0, 100001, 100001, 100000),
  (1, '2017-03-08 19:25:00', '2017-06-07 14:00:00', '2017-06-30 12:00:00', 3200.00, 2, 0, 100002, 100002, 100001),
  (1, '2017-05-02 10:43:00', '2017-05-21 14:00:00', '2017-05-23 12:00:00', 10000.00, 2, 1, 100000, 100003, 100002),
  (0, '2017-02-15 16:35:00', '2017-05-23 14:00:00', '2017-05-28 12:00:00', 31200.00, 4, 0, 100004, 100004, 100003);



INSERT INTO vote (rate, user_comment, date_added, user_id, hotel_id)
VALUES
  (9.5, 'User Vote comment  1', '2017-06-16 19:32:00', 100000, 100002),
  (6.5, 'User Vote comment  2', '2017-05-04 09:45:00', 100001, 100000),
  (8.0, 'User Vote comment  3', '2017-06-09 14:45:00', 100002, 100001);