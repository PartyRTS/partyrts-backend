INSERT INTO stream (current_number_video, full_users, private_stream, stream_title, fk_playlist, fk_user, create_date,
                    active_stream)
VALUES (0, 1, FALSE, 'myfirststream', 1, 1, '2021-05-20', TRUE);

INSERT INTO stream_has_user(
	stream_id, user_id)
	VALUES (1, 1);

