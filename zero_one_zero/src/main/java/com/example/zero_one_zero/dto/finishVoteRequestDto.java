package com.example.zero_one_zero.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class finishVoteRequestDto {
    private Long roomId;
    private String modifyCodes;
}
