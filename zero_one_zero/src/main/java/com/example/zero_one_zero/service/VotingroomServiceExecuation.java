package com.example.zero_one_zero.service;

import com.example.zero_one_zero.dto.ParticipantsDto;
import com.example.zero_one_zero.dto.VoteValuesDto;
import com.example.zero_one_zero.dto.VotingroomDto;
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

import java.util.List;
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
    public Long findRoomIdByCode(String code) {
        // Implement the logic to find the room ID by the code.
        // You can use votingRoomRepository or any other method that suits your needs.
        // If a matching room is found, return its ID; otherwise, return null.
        // Example:
        Votingroom votingroom = votingRoomRepository.findByCode(code);
        return votingroom != null ? votingroom.getRoomId() : null;
    }


    //투표룸 정보 불러오기
    @Override
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
                .participantsId(participants.getParicipantsId())
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
}
