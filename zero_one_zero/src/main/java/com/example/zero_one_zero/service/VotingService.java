package com.example.zero_one_zero.service;

import com.example.zero_one_zero.dto.VoteStatisticsDto;
import com.example.zero_one_zero.dto.finishVoteResponseDto;
import jakarta.transaction.Transactional;

public interface VotingService {
    void updateParticipantSelection(Long roomId, String username);
    void executeVote(Long roomId, String username, Long voteValuesId);
    VoteStatisticsDto calculateVoteResults(Long roomId);
    finishVoteResponseDto finishVote(Long roomId, String modifyCodes);

}
