package com.example.zero_one_zero.controller;

import com.example.zero_one_zero.repository.VoteValuesRepository;
import com.example.zero_one_zero.repository.VotingRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoteValuesController {
    @Autowired
    VotingRoomRepository votingRoomRepository;
    @Autowired
    VoteValuesRepository voteValuesRepository;

}
