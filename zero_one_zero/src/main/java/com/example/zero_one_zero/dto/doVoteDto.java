package com.example.zero_one_zero.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.AnyKeyJavaClass;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class doVoteDto {
    private String userName;
    private Long voteValueId;
}
