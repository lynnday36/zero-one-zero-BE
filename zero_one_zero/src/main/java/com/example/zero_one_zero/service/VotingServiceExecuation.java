package com.example.zero_one_zero.service;

import com.example.zero_one_zero.dto.VoteResultDto;
import com.example.zero_one_zero.dto.VoteStatisticsDto;
import com.example.zero_one_zero.entity.Participants;
import com.example.zero_one_zero.exceptions.ResourceNotFoundException;
import com.example.zero_one_zero.repository.ParticipantsRepository;
import com.example.zero_one_zero.repository.VoteValuesRepository;
import com.example.zero_one_zero.repository.VotingRoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public VoteStatisticsDto calculateVoteResults(Long roomId){
        List<VoteResultDto> result = new ArrayList<>();
        Long selectdMaxSize = 0L;
        Long voteCount = 0L;

        List<Participants> participants = participantsRepository.findByRoomId(roomId);
        String voteTitle = votingRoomRepository.getVoteTitleByRoomId(roomId);
        for(Participants participant : participants){
            Long voteValueId = participant.getVoteValuesId();
            if(voteValueId != null){
                Optional<VoteResultDto> existingResult = result.stream()
                        .filter(r-> r.getVoteValueId().equals(voteValueId))
                        .findFirst(); //id값찾고

                if (existingResult.isPresent()){ //선택누적
                    VoteResultDto updateResult = existingResult.get();
                    updateResult.setSelectedSize(updateResult.getSelectedSize()+1);
                }
                else{
                    result.add(new VoteResultDto(voteValueId,1));
                }
                selectdMaxSize = Math.max(selectdMaxSize, participant.getVoteValuesId());
                voteCount++;
            }
        }
        return new VoteStatisticsDto(voteTitle,result, selectdMaxSize, voteCount);
    }
}
