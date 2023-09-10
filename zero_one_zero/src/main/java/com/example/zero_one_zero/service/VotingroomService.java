package com.example.zero_one_zero.service;

import com.example.zero_one_zero.dto.VotingroomDto;
import com.example.zero_one_zero.dto.createVoteDto;

public interface VotingroomService {
    VotingroomDto getVotingroomDto(Long roomId);
    Long findRoomIdByCode(String code);
    String createVotingroom(VotingroomDto requestDto);
}
