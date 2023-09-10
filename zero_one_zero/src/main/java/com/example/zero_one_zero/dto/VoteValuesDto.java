package com.example.zero_one_zero.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoteValuesDto {
    private Long voteValuesId;
    private String voteLabel;
    private Long roomId;

}
