package com.example.zero_one_zero.service;

import com.example.zero_one_zero.entity.Participants;
import com.example.zero_one_zero.exceptions.ResourceNotFoundException;
import com.example.zero_one_zero.repository.ParticipantsRepository;
import com.example.zero_one_zero.repository.VoteValuesRepository;
import com.example.zero_one_zero.repository.VotingRoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotingServiceExecuation implements VotingService {
    @Autowired
    private VotingRoomRepository votingRoomRepository;
    @Autowired
    private ParticipantsRepository participantsRepository;
    @Autowired
    private VoteValuesRepository voteValuesRepository;

    @Override
    @Transactional
    public void updateParticipantSelection(Long roomId, String username) {
        Participants participant = participantsRepository.findByRoomIdandName(roomId, username); //ok
        if (participant != null) {
            participant.setIsNameSelected(true);
            participantsRepository.save(participant); // Save the updated participant
        }
        else {
            throw new ResourceNotFoundException("Username", "roomId", username);
        }
    }

    @Override
    @Transactional
    public void executeVote(Long roomId, String username, Long voteValueId){
        Participants participants = participantsRepository.findByRoomIdandName(roomId, username);
        if(participants != null){
            participants.setVoteValuesId(voteValueId);
            participantsRepository.save(participants);
        }
        else{
            throw new ResourceNotFoundException("Username","roomId",username);
        }
    }
} //투표하기 테스트해야함! 안하고잤음
