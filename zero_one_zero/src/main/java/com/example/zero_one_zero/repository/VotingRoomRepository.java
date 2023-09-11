package com.example.zero_one_zero.repository;

import com.example.zero_one_zero.entity.Votingroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VotingRoomRepository extends JpaRepository<Votingroom, Long> {

    List<Votingroom> findAll();
    @Query(value = "SELECT * FROM votingroom WHERE room_code = :Code", nativeQuery = true)
    Votingroom findByCode(String Code);

    @Query(value = "SELECT vote_title FROM votingroom WHERE room_Id = :roomId", nativeQuery = true)
    String getVoteTitleByRoomId(Long roomId);

    Votingroom findByRoomId(Long roomId);

}
