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


@RestController
@RequestMapping("/vote")
public class VoteController {
    @Autowired
    private VotingService votingService;

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
            return ResponseEntity.ok(responseDto); //URI와 modifyCode만 맞으면 json에 roomId없어도 돌아가긴 함, 웬만한 에러는 다 500
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
