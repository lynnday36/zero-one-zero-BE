package com.example.zero_one_zero.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteStatisticsDto {
    private String voteTitle;
    private List<VoteResultDto> result;
    private Long selectedMaxSize;
    private Long cumulativeVoteCount;
}
