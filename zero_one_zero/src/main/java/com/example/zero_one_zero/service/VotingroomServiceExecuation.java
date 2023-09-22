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

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.UUID;
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
                .roomCode(votingroom.getRoomCode())
                .creatorName(votingroom.getCreatorName())
                .voteTitle(votingroom.getVoteTitle())
                .voteDescription(votingroom.getVoteDescription())
                .personSize(votingroom.getPersonSize())
                .selectList(voteValuesdto)
                .modifyCode(votingroom.getModifyCode())
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
    public String createVotingroom(createVoteDto requestDto) {//룸생성
        String roomCode = generateRandomPassword();

        Votingroom votingroom = new Votingroom();
        votingroom.setRoomCode(roomCode);
        votingroom.setModifyCode(requestDto.getModifyCode());
        votingroom.setVoteTitle(requestDto.getVoteTitle());
        votingroom.setVoteDescription(requestDto.getVoteDescription());
        votingroom.setCreatorName(requestDto.getCreatorName());
        votingroom.setPersonSize(requestDto.getParticipantList().size() + 1); // 작성자 포함이므로

        Votingroom savedroom = votingRoomRepository.save(votingroom);

        for(String voteItems : requestDto.getSelectList()){
            VoteValues voteValues = new VoteValues();
            voteValues.setVotingroom(savedroom);
            voteValues.setVoteLabel(voteItems);
            voteValuesRepository.save(voteValues);
        }

        for(String participantName : requestDto.getParticipantList()){
            Participants participants = new Participants();
            participants.setVotingroom(savedroom);
            participants.setParticipantsName(participantName);
            participantsRepository.save(participants);
        }
        return roomCode;
    }

    private String generateRandomPassword() {
        return UUID.randomUUID().toString();
    }
/*
    @Override
    @Transactional
    public void modifyVote(String modifyCode, VotingroomDto votingroomDto){

        Votingroom votingroom = votingRoomRepository.findByModifyCode(modifyCode)
                .orElseThrow(()->new ResourceNotFoundException("room","Code",modifyCode));

        //참가자 수정사항 수정

        for(ParticipantsDto participantsDto : votingroomDto.getParticipantList()){
            Participants participants = participantsRepository.findById(participantsDto.getParticipantsId())
                    .orElseThrow(()-> new ResolutionException("Cannot find participant with ID : " + participantsDto.getParticipantsId()));
            participants.setParticipantsName(participantsDto.getParticipantsName());
            participantsRepository.save(participants);
        }

        for(VoteValuesDto voteValuesDto : votingroomDto.getSelectList()){
            VoteValues voteValues = voteValuesRepository.findById(voteValuesDto.getVoteValuesId())
                    .orElseThrow(()-> new ResolutionException("Cannot find votelabel with ID : " + voteValuesDto.getVoteValuesId()));
            voteValues.setVoteLabel(voteValuesDto.getVoteLabel());
            voteValuesRepository.save(voteValues);
        }

        //투표방관련사항 수정
        votingroom.setVoteTitle(votingroomDto.getVoteTitle());
        votingroom.setVoteDescription(votingroomDto.getVoteDescription());
        votingroom.setCreatorName(votingroomDto.getCreatorName());
        votingroom.setPersonSize(votingroomDto.getParticipantList().size()+1);
        votingRoomRepository.save(votingroom);
    }*/
}
