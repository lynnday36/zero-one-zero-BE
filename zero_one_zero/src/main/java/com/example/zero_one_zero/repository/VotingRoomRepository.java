package com.example.zero_one_zero.repository;

import com.example.zero_one_zero.entity.VoteValues;
import com.example.zero_one_zero.entity.Votingroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VotingRoomRepository extends JpaRepository<Votingroom, Long> {

    List<Votingroom> findAll();
}
