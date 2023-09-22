package com.example.zero_one_zero.controller;

import com.example.zero_one_zero.dto.*;
import com.example.zero_one_zero.entity.Votingroom;
import com.example.zero_one_zero.exceptions.ResourceNotFoundException;
import com.example.zero_one_zero.repository.VotingRoomRepository;
import com.example.zero_one_zero.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/vote")
public class VoteController {
    @Autowired
    private VotingService votingService;

    /*@PutMapping("/room/{roomId}/selectUser") //유저선택시 IsSelected true, json 인풋값 username : string
    public void selectUser(@PathVariable Long roomId, @RequestBody userSelectDto username){ //유저선택
        votingService.updateParticipantSelection(roomId,username.getUserName());
    }*/

    @PutMapping("/room/{roomId}/putCastVote") //투표 수행 -> 성공/fail 여부 리턴, 이때 selectedname이랑 votevalueid 모두 변경
    public ResponseEntity<String> putCastVote(@PathVariable Long roomId, @RequestBody doVoteDto castVoteInfo){//투표실행 - votevalueId, userName가 들어옴
        try{
        votingService.executeVote(roomId, castVoteInfo.getUserName(), castVoteInfo.getVoteValueId());
            return ResponseEntity.ok("vote success");
        } catch(ResourceNotFoundException er){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("vote failed");
        }
    }

    @GetMapping("/room/{roomId}/voteResults") //투표결과 api
    public ResponseEntity<VoteStatisticsDto> getVoteResults(@PathVariable Long roomId){
        try{
            VoteStatisticsDto voteStatistics = votingService.calculateVoteResults(roomId);
            return new ResponseEntity<>(voteStatistics, HttpStatus.OK);
        } catch(ResourceNotFoundException er){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/room/{roomId}/finishVote") //투표끝, 벌칙자 이름 api
    public ResponseEntity<finishVoteResponseDto> finishVote(@PathVariable Long roomId, @RequestBody finishVoteRequestDto requestDto) {
        finishVoteResponseDto responseDto = votingService.finishVote(roomId, requestDto.getModifyCode());

        if (responseDto != null) {
            return ResponseEntity.ok(responseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
