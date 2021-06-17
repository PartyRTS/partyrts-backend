INSERT INTO playlist (title, fk_user)
VALUES ('myfirstplaylist',1);

INSERT INTO video_has_playlist (number_video, playlist_id, video_id)
VALUES (1,1,1);

INSERT INTO video_has_playlist (number_video, playlist_id, video_id)
VALUES (2,1,2);

INSERT INTO video_has_playlist (number_video, playlist_id, video_id)
VALUES (3,1,3);
