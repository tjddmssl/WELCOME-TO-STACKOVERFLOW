package com.preproject.server.vote.dto;

import com.preproject.server.vote.IS_VOTED;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class VoteResponseDto {

  private long voteCount;
  private IS_VOTED isVoted;
}
