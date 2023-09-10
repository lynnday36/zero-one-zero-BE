package com.example.zero_one_zero.repository;

import com.example.zero_one_zero.entity.Participants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;




public interface ParticipantsRepository extends JpaRepository<Participants,Long> {
    @Query(value = "SELECT * FROM participants WHERE room_id = :roomId", nativeQuery = true)
    //특정 투표방의 참석자 리스트
    List<Participants> findByRoomId(Long roomId);
    @Query(value = "SELECT * FROM participants WHERE room_id = :roomId and participants_name = :username", nativeQuery = true)
    Participants findByRoomIdandName(Long roomId, String username);
}
