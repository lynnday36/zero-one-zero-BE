package com.example.zero_one_zero.service;

import com.example.zero_one_zero.dto.VoteResultDto;
import com.example.zero_one_zero.dto.VoteStatisticsDto;
import com.example.zero_one_zero.dto.finishVoteResponseDto;
import com.example.zero_one_zero.entity.Participants;
import com.example.zero_one_zero.entity.Votingroom;
import com.example.zero_one_zero.exceptions.ResourceNotFoundException;
import com.example.zero_one_zero.repository.ParticipantsRepository;
import com.example.zero_one_zero.repository.VoteValuesRepository;
import com.example.zero_one_zero.repository.VotingRoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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

    //투표실행
    @Override
    @Transactional
    public void executeVote(Long roomId, String username, Long voteValueId){
        Participants participants = participantsRepository.findByRoomIdandName(roomId, username);
        if(participants != null){
            participants.setVoteValuesId(voteValueId);
            participants.setIsNameSelected(true);
            participantsRepository.save(participants);
        }
        else{
            throw new ResourceNotFoundException("Username","roomId",username);
        }
    }

    //투표결과계산

    @Override
    @Transactional
    public VoteStatisticsDto calculateVoteResults(Long roomId){
        List<VoteResultDto> result = new ArrayList<>();
        Long selectedMaxSize = 0L;
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
                voteCount++;
            }
            selectedMaxSize += (participant.getIsNameSelected() != null && participant.getIsNameSelected()) ? 1L : 0L;
        }
        return new VoteStatisticsDto(voteTitle,result, selectedMaxSize, voteCount);
    }
    //투표종료
    @Override
    @Transactional
    public finishVoteResponseDto finishVote(Long roomId, String modifyCodes){
        Votingroom votingroom = votingRoomRepository.findByRoomId(roomId);

        if(votingroom != null && votingroom.getModifyCode().equals(modifyCodes)){
            votingroom.setOvered(true);
            votingRoomRepository.save(votingroom);
        }
        else throw new ResourceNotFoundException("Modifycode","room_id",modifyCodes);
        List<Participants> notVotedParticipants = participantsRepository.findByIdandNullUser(roomId);

        if(!notVotedParticipants.isEmpty()){
            int randomIdx = new Random().nextInt(notVotedParticipants.size());
            Participants randomParticipant = notVotedParticipants.get(randomIdx);
            return new finishVoteResponseDto(randomParticipant.getParticipantsName());
        }
        return null;
    }
}
