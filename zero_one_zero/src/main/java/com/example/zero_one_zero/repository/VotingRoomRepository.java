package com.example.zero_one_zero.repository;

import com.example.zero_one_zero.entity.Votingroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VotingRoomRepository extends JpaRepository<Votingroom, Long> {

    List<Votingroom> findAll();
    @Query(value = "SELECT * FROM votingroom WHERE modify_codes = :Code", nativeQuery = true)
    Votingroom findByCode(String Code);
}
