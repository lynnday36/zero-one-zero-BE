package com.example.zero_one_zero.controller;

import com.example.zero_one_zero.entity.Participants;
import com.example.zero_one_zero.entity.Votingroom;
import com.example.zero_one_zero.repository.VotingRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VotingroomController {
    @Autowired
    private VotingRoomRepository votingRoomRepository;
    @GetMapping("/app/vote/{roomId}") /*
    *
    *일단 룸아이디로 해놨는데 암호들어오면 리디렉션 걸거나 해야함*/
    public Votingroom getVote(@PathVariable Long roomId){
        return votingRoomRepository.findById(roomId).orElse(null); //룸정보 반환 ok
    }

}
