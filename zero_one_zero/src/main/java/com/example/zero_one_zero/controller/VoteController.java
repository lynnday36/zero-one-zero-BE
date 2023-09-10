package com.example.zero_one_zero.controller;

import com.example.zero_one_zero.dto.userSelectDto;
import com.example.zero_one_zero.service.VotingService;
import com.example.zero_one_zero.service.VotingroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vote")
public class VoteController {
    @Autowired
    private VotingService votingService;

    @PutMapping("/room/{roomId}/selectUser")
    public void selectUser(@PathVariable Long roomId, @RequestBody userSelectDto username){ //유저선택
        votingService.updateParticipantSelection(roomId,username.getUsername());
    }
}
