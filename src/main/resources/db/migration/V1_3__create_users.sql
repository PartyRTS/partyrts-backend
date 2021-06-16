INSERT INTO _user (birthday_date, description, email, first_name, logo_url, registration_date, second_name)
VALUES ('1995-04-29','I am admin.','admin@mail.ru','adminName','https://w3schoolsrus.github.io/images/admin.png','2021-05-20','adminSurname');

INSERT INTO _user (birthday_date, description, email, first_name, logo_url, registration_date, second_name)
VALUES ('1995-05-29','I am simple user.','secuser@mail.ru','user2Name','http://s1.iconbird.com/ico/0712/iconshockrealvista/w400h4001341689813administrator.png','2021-05-20','user2Surname');

INSERT INTO user_has_global_roles (id_user, id_global_role)
VALUES (1,2),(2,1);