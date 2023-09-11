package com.example.zero_one_zero.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class VoteResultDto {
    private Long voteValueId;
    private int selectedSize;
}
