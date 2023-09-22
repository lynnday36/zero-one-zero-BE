package com.example.zero_one_zero.controller;
import com.example.zero_one_zero.dto.VotingroomDto;
import com.example.zero_one_zero.dto.createVoteDto;
import com.example.zero_one_zero.service.VotingroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class VotingroomController {
    @Autowired
    private VotingroomService votingroomService;

    @GetMapping("/{roomCode}") //룸코드들어왔을때 룸아이디페이지로 리다이렉션
    public ResponseEntity<?> getVote(@PathVariable String roomCode) {
        Long roomId = votingroomService.findRoomIdByCode(roomCode);
        HttpHeaders headers = new HttpHeaders();
        if (roomId != null) {
            headers.setLocation(URI.create("/vote/room/"+ roomId));
            return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); //틀리면 404
        }
    }
    /*
     *
     *일단 룸아이디로 해놨는데 룸코드들어오면 리다이렉트 걸거나 해야함->리다이렉트해결*/
    @GetMapping("/vote/room/{roomId}")
    public VotingroomDto getVoteAsId(@PathVariable Long roomId){ //실질적으로 정보 보여주는 메서드

        return votingroomService.getVotingroomDto(roomId);

    }

    //투표 생성, 생성과 동시에 입장코드 반환
    @PostMapping("/vote/putCreateNewVote")
    public ResponseEntity<String> putCreateNewVote(@RequestBody createVoteDto requestDto) { //이름입력받을때 크리에이터 네임 따로 받아야함
        try{
        String roomCode = votingroomService.createVotingroom(requestDto);
        String jsonResponse = "{\"roomCode\" : \""+ roomCode+"\"}";
        return ResponseEntity.ok(jsonResponse);
        } catch(Exception er){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Room creating failed"); //틀리면 500
        }
    }

}
