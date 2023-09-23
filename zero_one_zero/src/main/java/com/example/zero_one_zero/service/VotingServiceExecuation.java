package com.example.zero_one_zero.service;

import com.example.zero_one_zero.dto.VoteResultDto;
import com.example.zero_one_zero.dto.VoteStatisticsDto;
import com.example.zero_one_zero.dto.finishVoteResponseDto;
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

import java.util.*;

@Service
public class VotingServiceExecuation implements VotingService {
    @Autowired
    private VotingRoomRepository votingRoomRepository;
    @Autowired
    private ParticipantsRepository participantsRepository;
    @Autowired
    private VoteValuesRepository voteValuesRepository;

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

        Votingroom votingRoom = votingRoomRepository.findByRoomId(roomId);
        if (votingRoom == null) {
            throw new ResourceNotFoundException("Not found");
        }

        List<Participants> participants = participantsRepository.findByRoomId(roomId);
        String voteTitle = votingRoomRepository.getVoteTitleByRoomId(roomId);

        Map<Long, String> voteLabelMap = new HashMap<>(); //라벨불러오기
        List<VoteValues> voteValuesList = voteValuesRepository.findByRoomId(roomId);
        for(VoteValues voteValues : voteValuesList){
            voteLabelMap.put(voteValues.getVoteValuesId(), voteValues.getVoteLabel());
        }

        for (VoteValues voteValue : voteValuesList) {
            result.add(new VoteResultDto(voteValue.getVoteValuesId(), 0, voteValue.getVoteLabel())); //투표안된 선택지때문에 0으로 이니셜라이징
        }

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
                voteCount++;
            }
            selectedMaxSize += (participant.getIsNameSelected() != null && participant.getIsNameSelected()) ? 1L : 0L; //총명수로 바꿔야함
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
        else if(notVotedParticipants.isEmpty()){
            return new finishVoteResponseDto(null);
        }

        return null;
    }
}
