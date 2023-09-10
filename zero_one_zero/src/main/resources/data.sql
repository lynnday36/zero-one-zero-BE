INSERT INTO votingroom(vote_title, modify_codes, vote_description, creator_name, person_size) VALUES('ee', 'qjtelkwskdg', 'fff', 'aaa', 5);
INSERT INTO vote_values(room_id, vote_label) VALUES(1, '짜장');
INSERT INTO participants(room_id, participants_name,is_name_selected,vote_values_id) VALUES(1, '니노', true, 3);
INSERT INTO participants(room_id, participants_name) VALUES(2, '삼삼');
select * from votingroom;