INSERT INTO stream (current_number_video, full_users, private_stream, stream_title, fk_playlist, fk_user, create_date,
                    active_stream)
VALUES (0, 1, FALSE, 'This is my first stream on this .', 1, 1, '2021-05-20', TRUE);

INSERT INTO stream (current_number_video, full_users, private_stream, stream_title, fk_playlist, fk_user, create_date,
                    active_stream)
VALUES (0, 20, FALSE, 'Смотрим синтел', 2, 4, '2021-06-22', TRUE);

INSERT INTO stream (current_number_video, full_users, private_stream, stream_title, fk_playlist, fk_user, create_date,
                    active_stream)
VALUES (0, 10, FALSE, 'Смотрим аниме', 3, 5, '2021-06-22', TRUE);

INSERT INTO stream (current_number_video, full_users, private_stream, stream_title, fk_playlist, fk_user, create_date,
                    active_stream)
VALUES (0, 5, FALSE, 'Смотрим фильм какого то ноунейма', 4, 6, '2021-06-23', TRUE);

INSERT INTO stream_has_user(
	stream_id, user_id)
	VALUES (1, 2);

INSERT INTO stream_has_category(
	stream_id, category_id)
	VALUES (1, 2),(2, 1),(2, 2),(3, 2),(4, 6);

