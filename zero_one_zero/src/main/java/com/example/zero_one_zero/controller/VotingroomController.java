package com.example.zero_one_zero.controller;
import com.example.zero_one_zero.dto.VotingroomDto;
import com.example.zero_one_zero.service.VotingroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vote")
public class VotingroomController {
    @Autowired
    private VotingroomService votingroomService;
    @GetMapping("/{roomId}")
    /*
    *
    *일단 룸아이디로 해놨는데 암호들어오면 리디렉션 걸거나 해야함*/
    public VotingroomDto getVote(@PathVariable Long roomId){

        return votingroomService.getVotingroomDto(roomId);

    }


}
