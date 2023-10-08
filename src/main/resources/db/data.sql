INSERT INTO users (email, login, name, birthday)
VALUES ('email_1', 'login_1', 'name_1', '2001-01-01'),
	   ('email_2', 'login_2', 'name_2', '2002-02-02'),
       ('email_3', 'login_3', 'name_3', '2003-03-03');

INSERT INTO friends (id_user, id_friend, accept)
VALUES (1, 2, true),
       (1, 3, false);
       
INSERT INTO mpa (title)
VALUES ('G'),
	   ('PG'),
	   ('PG13'),
	   ('R'),
	   ('NC');

INSERT INTO films (name, description, release_date, duration, mpa)
VALUES ('film_1','desc_1','2021-01-01',100, 1),
	   ('film_2','desc_2','2022-02-02',120, 2),
	   ('film_3','desc_3','2023-03-03',130, 3);

INSERT INTO likes (id_film, id_user)
VALUES (1,1),
       (1,2),
       (2,3);

INSERT INTO genres (name)
VALUES ('comedy'),
	   ('drama'),
	   ('cartton'),
	   ('thriller'),
	   ('action');

INSERT INTO film_genre (id_film, id_genre)
VALUES (1,1),
       (1,5),
       (2,2),
       (2,4),
       (3,3);    
