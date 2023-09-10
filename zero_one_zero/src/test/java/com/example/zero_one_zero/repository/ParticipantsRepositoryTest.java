package com.example.zero_one_zero.repository;

import com.example.zero_one_zero.entity.Participants;
import com.example.zero_one_zero.entity.VoteValues;
import com.example.zero_one_zero.entity.Votingroom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //datajpa는 기본 내장이므로 외장연동시 이거 필요
@DisplayName("특정 방의 참가자 목록")

class ParticipantsRepositoryTest {

    @Autowired
    ParticipantsRepository participantsRepository;
    @Test
    void findByRoomId() {
        {
            Long roomId = 2L;
            List<Participants> participants = participantsRepository.findByRoomId(roomId);
            Votingroom votingroom = new Votingroom(2L, "제주도 숙소","123456","aaaa","서귀포시말고 갈데있으면 카톡해","샤샤",12,false);
            Participants expected = new Participants(13L, votingroom,"하루",true,4L);
            assertEquals(expected.toString(), participants.toString(), "참가자");
            
            //데이터확인용으로 돌린거라 어차피 이거 결과는 틀린게 맞음
        }
    }
}