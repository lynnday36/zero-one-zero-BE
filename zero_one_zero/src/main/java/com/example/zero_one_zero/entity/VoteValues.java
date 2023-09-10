package com.example.zero_one_zero.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class VoteValues {
    // 투표선택지내용

        // primary vote_values_id
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long voteValuesId;

    @ManyToOne
    @JoinColumn(name="roomId")//외래키
        private Votingroom votingroom;

    @Column
        // 투표선택지
        private String voteLabel;

}
