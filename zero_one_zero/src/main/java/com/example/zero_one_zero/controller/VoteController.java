package com.example.zero_one_zero.controller;

import com.example.zero_one_zero.dto.VoteStatisticsDto;
import com.example.zero_one_zero.dto.doVoteDto;
import com.example.zero_one_zero.dto.userSelectDto;
import com.example.zero_one_zero.exceptions.ResourceNotFoundException;
import com.example.zero_one_zero.service.VotingService;
import com.example.zero_one_zero.service.VotingroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/vote")
public class VoteController {
    @Autowired
    private VotingService votingService;

    @PutMapping("/room/{roomId}/selectUser") //유저선택시 IsSelected true, json 인풋값 username : string
    public void selectUser(@PathVariable Long roomId, @RequestBody userSelectDto username){ //유저선택
        votingService.updateParticipantSelection(roomId,username.getUserName());
    }
    @PutMapping("/room/{roomId}/putCastVote") //투표 수행 -> 투표 제목 및 선택지 선택된 수, 참석자수, 누적투표수반환
    public ResponseEntity<VoteStatisticsDto> putCastVote(@PathVariable Long roomId, @RequestBody doVoteDto castVoteInfo){//투표실행 - votevalueId, userName가 들어옴
        HttpHeaders headers = new HttpHeaders();
        try{
        votingService.executeVote(roomId, castVoteInfo.getUserName(), castVoteInfo.getVoteValueId());
        VoteStatisticsDto voteStatistics = votingService.calculateVoteResults(roomId);
            return new ResponseEntity<>(voteStatistics, HttpStatus.OK);
        } catch(ResourceNotFoundException er){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
