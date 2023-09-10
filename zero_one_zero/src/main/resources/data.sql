INSERT INTO votingroom(vote_title, room_code, modify_code, vote_description, creator_name, person_size) VALUES('뭐드실', 'qwert123', 'aaaa', '중식만허용','니노' ,12);
INSERT INTO vote_values(room_id, vote_label) VALUES(2, '포카칩');
INSERT INTO participants(room_id, participants_name,is_name_selected,vote_values_id) VALUES(2, '키유', true, 5);

select * from votingroom;
select * from participants;
select * from vote_values;

