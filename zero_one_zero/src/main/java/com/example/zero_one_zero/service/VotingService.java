package com.example.zero_one_zero.service;

import jakarta.transaction.Transactional;

public interface VotingService {
    void updateParticipantSelection(Long roomId, String username);
    void executeVote(Long roomId, String username, Long voteValuesId);
}
