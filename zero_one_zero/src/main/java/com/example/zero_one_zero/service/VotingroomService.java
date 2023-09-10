package com.example.zero_one_zero.service;

import com.example.zero_one_zero.dto.VotingroomDto;
import com.example.zero_one_zero.dto.createVoteDto;
import jakarta.transaction.Transactional;

public interface VotingroomService {
    VotingroomDto getVotingroomDto(Long roomId);
    Long findRoomIdByCode(String code);

    @Transactional
    String createVotingroom(createVoteDto requestDto);
}
