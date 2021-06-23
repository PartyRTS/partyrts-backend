INSERT INTO playlist (title, fk_user)
VALUES ('myfirstplaylist',1);

INSERT INTO playlist (title, fk_user)
VALUES ('myfirstplaylist',4);

INSERT INTO playlist (title, fk_user)
VALUES ('myfirstplaylist',5);

INSERT INTO playlist (title, fk_user)
VALUES ('myfirstplaylist',6);

INSERT INTO playlist (title, fk_user)
VALUES ('myfirstplaylist',2);

INSERT INTO video_has_playlist (number_video, playlist_id, video_id)
VALUES (1,1,1),(2,1,2),(3,1,3);

INSERT INTO video_has_playlist (number_video, playlist_id, video_id)
VALUES (1,2,4);

INSERT INTO video_has_playlist (number_video, playlist_id, video_id)
VALUES (3,3,5);

INSERT INTO video_has_playlist (number_video, playlist_id, video_id)
VALUES (3,4,6);

INSERT INTO video_has_playlist (number_video, playlist_id, video_id)
VALUES (1,5,7),(2,5,8),(3,5,9);
