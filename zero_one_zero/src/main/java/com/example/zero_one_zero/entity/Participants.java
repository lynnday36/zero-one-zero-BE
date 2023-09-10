package com.example.zero_one_zero.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@DynamicInsert
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

    // 참여자들이름
public class Participants {

        // id
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long paricipantsId;

        // room_id
    @ManyToOne
    @JoinColumn(name="roomId", referencedColumnName = "roomId")
        private Votingroom votingroom;
    @Column
        // 참석자이름
        private String participantsName;
    @Column
    @ColumnDefault("false")
        // 이름선택여부
        private Boolean isNameSelected;
    @Column
        // 투표선택지번호
        private Long voteValuesId;

}
