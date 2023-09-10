package com.example.zero_one_zero.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class createVoteDto {
    private String voteTitle;
    private String voteCreator;
    private String voteDescription;
    private String roomCode;
    private String modifyCode;
    private String creatorName;
    private List<String> selectList;
    private List<String> participantList;


}
