package com.example.zero_one_zero.repository;

import com.example.zero_one_zero.entity.VoteValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VoteValuesRepository extends JpaRepository<VoteValues, Long> {
    //특정 투표방의 선택지 리스트
    @Query(value = "SELECT * FROM vote_values WHERE room_id = :roomId", nativeQuery = true)
    List<VoteValues> findByRoomId(Long roomId);
}
