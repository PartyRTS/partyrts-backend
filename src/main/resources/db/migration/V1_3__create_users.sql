INSERT INTO _user (birthday_date, description, email, first_name, logo_url,banned, registration_date, second_name)
VALUES ('1995-04-29','I am admin.','admin@mail.ru','admin','https://w3schoolsrus.github.io/images/admin.png',false,'2021-05-20','admin');

INSERT INTO _user (birthday_date, description, email, first_name, logo_url,banned, registration_date, second_name)
VALUES ('1995-05-29','I am simple user.','secuser@mail.ru','Larry','http://s1.iconbird.com/ico/0712/iconshockrealvista/w400h4001341689813administrator.png',false,'2021-05-20','Brown');

INSERT INTO _user (birthday_date, description, email, first_name, logo_url,banned, registration_date, second_name)
VALUES ('1995-05-29','I am simple user.','arthur@mail.ru','Arthur','https://chto-eto-takoe.ru/uryaimg/32574385521dd1847f7d1e5b940491ef.jpg',false,'2021-06-21','Holmes');

INSERT INTO _user (birthday_date, description, email, first_name, logo_url,banned, registration_date, second_name)
VALUES ('1995-05-29','I am simple user.','amy@mail.ru','Amy','https://chto-eto-takoe.ru/uryaimg/32574385521dd1847f7d1e5b940491ef.jpg',false,'2021-06-21','Scott');

INSERT INTO _user (birthday_date, description, email, first_name, logo_url,banned, registration_date, second_name)
VALUES ('1995-05-29','I am simple user.','john@mail.ru','John','https://chto-eto-takoe.ru/uryaimg/32574385521dd1847f7d1e5b940491ef.jpg',false,'2021-06-21','Anderson');

INSERT INTO _user (birthday_date, description, email, first_name, logo_url,banned, registration_date, second_name)
VALUES ('1995-05-29','I am simple user.','cheryl@mail.ru','Cheryl','https://chto-eto-takoe.ru/uryaimg/32574385521dd1847f7d1e5b940491ef.jpg',false,'2021-06-21','Holland');

INSERT INTO user_has_global_roles (id_user, id_global_role)
VALUES (1,2),(1,3),(2,1),(3,1),(4,1),(5,1),(6,1);