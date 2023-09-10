package com.example.zero_one_zero.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString

public class VoteValuesDto {
    private Long voteValueId;
    private Long roomId;
    private String voteLabel;
}
