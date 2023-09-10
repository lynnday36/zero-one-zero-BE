package com.example.zero_one_zero.service;

import com.example.zero_one_zero.dto.ParticipantsDto;
import com.example.zero_one_zero.dto.VoteValuesDto;
import com.example.zero_one_zero.dto.VotingroomDto;
import com.example.zero_one_zero.dto.createVoteDto;
import com.example.zero_one_zero.entity.Participants;
import com.example.zero_one_zero.entity.VoteValues;
import com.example.zero_one_zero.entity.Votingroom;
import com.example.zero_one_zero.exceptions.ResourceNotFoundException;
import com.example.zero_one_zero.repository.ParticipantsRepository;
import com.example.zero_one_zero.repository.VoteValuesRepository;
import com.example.zero_one_zero.repository.VotingRoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class VotingroomServiceExecuation implements VotingroomService {
    @Autowired
    private VotingRoomRepository votingRoomRepository;
    @Autowired
    private ParticipantsRepository participantsRepository;
    @Autowired
    private VoteValuesRepository voteValuesRepository;

    //코드로 방 찾기
    @Override
    @Transactional
    public Long findRoomIdByCode(String code) {
        Votingroom votingroom = votingRoomRepository.findByCode(code);
        return votingroom != null ? votingroom.getRoomId() : null;
    }


    //투표룸 정보 불러오기
    @Override
    @Transactional
    public VotingroomDto getVotingroomDto(Long roomId){
        Votingroom votingroom = votingRoomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Votingroom", "roomId", roomId));

        List<VoteValuesDto> voteValuesdto = voteValuesRepository.findByRoomId(roomId)
                .stream()
                .map(this::convertToVoteValuesdto)
                .collect(Collectors.toList());

        List<ParticipantsDto> participantsResponses = participantsRepository.findByRoomId(roomId)
                .stream()
                .map(this::convertToParticipantsdto)
                .collect(Collectors.toList());

        return VotingroomDto.builder()
                .roomId(votingroom.getRoomId())
                .roomCodes(votingroom.getRoomCode())
                .creatorName(votingroom.getCreatorName())
                .voteTitle(votingroom.getVoteTitle())
                .voteDescription(votingroom.getVoteDescription())
                .personSize(votingroom.getPersonSize())
                .selectList(voteValuesdto)
                .participantList(participantsResponses)
                .build();
    }
    private ParticipantsDto convertToParticipantsdto(Participants participants) {
        return ParticipantsDto.builder()
                .participantsId(participants.getParticipantsId())
                .participantsName(participants.getParticipantsName())
                .isNameSelected(participants.getIsNameSelected())
                .voteValuesId(participants.getVoteValuesId())
                .roomId(participants.getVotingroom().getRoomId())
                .build();
    }

    private VoteValuesDto convertToVoteValuesdto(VoteValues voteValues) {
        return VoteValuesDto.builder()
                .voteValuesId(voteValues.getVoteValuesId())
                .voteLabel(voteValues.getVoteLabel())
                .roomId(voteValues.getVotingroom().getRoomId())
                .build();
    }

    //투표생성하기+비밀번호 생성 및 저장/반환

    @Override
    @Transactional
    public String createVotingroom(VotingroomDto requestDto) {
        String roomCode = generateRandomPassword();

        Votingroom votingroom = new Votingroom();//룸생성

        votingroom.setRoomCode(roomCode);

        votingroom.setVoteTitle(requestDto.getVoteTitle());
        votingroom.setVoteDescription(requestDto.getVoteDescription());
        votingroom.setCreatorName(requestDto.getCreatorName());
        votingroom.setPersonSize(requestDto.getParticipantList().size() + 1); // 작성자 포함이므로

        Votingroom savedroom = votingRoomRepository.save(votingroom);

        /*List<String> participantsList = new ArrayList<>();
        for (String participantName : participantsList) {
            Participants participant = new Participants();
            participant.setParticipantsName(participantName);
            participant.setVotingroom(votingroom);
            participant.setIsNameSelected(false);
            participantsRepository.save(participant);
        }

        List<String> voteLabelList = new ArrayList<>();
        for (String voteLabel : voteLabelList) {
            VoteValues voteValues = new VoteValues();
            voteValues.setVoteLabel(voteLabel);
            voteValues.setVotingroom(votingroom);
            voteValuesRepository.save(voteValues);
        }*/



        return roomCode;
    }

    private String generateRandomPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 12; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        } /*
            password 중복 가능성 있음
        */

        return password.toString();
    }
}
