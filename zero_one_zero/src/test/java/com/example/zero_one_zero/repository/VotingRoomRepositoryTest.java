package com.example.zero_one_zero.repository;

import com.example.zero_one_zero.entity.Votingroom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //datajpa는 기본 내장이므로 외장연동시 이거 필요
@DisplayName("특정 방의 투표 선택지 목록")

class VotingRoomRepositoryTest {


    @Autowired
    VotingRoomRepository votingRoomRepository;
    @Test
    void findByCode() {
        {
            String code = "123456";
            Votingroom votingroom = votingRoomRepository.findByCode(code);
            Votingroom expected =  new Votingroom(2L, "제주도 숙소","123456","서귀포시말고 갈데있으면 카톡해","샤샤",12,false);
            assertEquals(expected.toString(), votingroom.toString(), "찾아낸 방 출력");
        }
    }
}