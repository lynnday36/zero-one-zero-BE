package com.example.zero_one_zero.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.util.List;

@Entity
@DynamicInsert //for columndefault
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

// 투표작성
public class Votingroom {

    // room_id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @Column
    // 투표제목
    private String voteTitle;

    @Column
    // 투표수정번호(랜덤)
    private String modifyCodes;

    @Column
    // 투표내용
    private String voteDescription;

    @Column
    // creator_name
    private String creatorName;
    @Column
    // person_size
    private Integer personSize;
    @Column
    //is_overed
    @ColumnDefault("false")
    private boolean isOvered;

}