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
public class VotingroomDto {
    private Long roomId;
    private String voteTitle;
    private String modifyCodes;
    private String roomCodes;
    private String voteDescription;
    private String creatorName;
    private Integer personSize;
    private boolean isOvered;
    private List<VoteValuesDto> selectList;
    private List<ParticipantsDto> participantList;
}
