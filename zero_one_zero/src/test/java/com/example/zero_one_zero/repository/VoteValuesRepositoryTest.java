package com.example.zero_one_zero.repository;

import com.example.zero_one_zero.entity.VoteValues;
import com.example.zero_one_zero.entity.Votingroom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //datajpa는 기본 내장이므로 외장연동시 이거 필요
@DisplayName("특정 방의 투표 선택지 목록")

class VoteValuesRepositoryTest {
    @Autowired
    VoteValuesRepository voteValuesRepository;
    /*@Test
    void findByRoomId() {
        {
            Long roomId = 2L; //몇번방의 선택지들
            List<VoteValues> voteValues = voteValuesRepository.findByRoomId(roomId); //선택지목록저장
            Votingroom votingroom = new Votingroom(2L, "제주도 숙소","123456","aaaa","서귀포시말고 갈데있으면 카톡해","샤샤",12,false);
            VoteValues a = new VoteValues(11L, votingroom,"펜션");
            VoteValues b = new VoteValues(12L, votingroom,"게스트하우스");
            VoteValues c = new VoteValues(13L, votingroom,"호텔");
            VoteValues d = new VoteValues(14L, votingroom,"에어비앤비");
            VoteValues e = new VoteValues(15L, votingroom,"캡슐호텔");
            VoteValues f = new VoteValues(16L, votingroom,"하숙집");
            VoteValues g = new VoteValues(17L, votingroom,"그냥밤새기");
            VoteValues h = new VoteValues(18L, votingroom,"여행안가기");
            VoteValues i = new VoteValues(19L, votingroom,"노숙");
            VoteValues j = new VoteValues(20L, votingroom,"지하철");
            List<VoteValues> expected = Arrays.asList(a,b,c,d,e,f,g,h,i,j);
            assertEquals(expected.toString(), voteValues.toString(), "2번방 투표선택지 출력");
        }
    }*/
}