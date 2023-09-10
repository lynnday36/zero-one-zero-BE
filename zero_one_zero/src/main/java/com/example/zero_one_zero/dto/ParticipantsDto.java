package com.example.zero_one_zero.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantsDto {
    private Long participantsId;
    private String participantsName;
    private Boolean isNameSelected;
    private Long voteValuesId;
    private Long roomId;
}
