package com.preproject.server.vote.controller;

import com.preproject.server.vote.service.VoteService;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@Validated
public class VoteController {

  private final VoteService voteService;

  // TODO 세션에서 어떻게 체크해요???? 몰라서 그냥 request param 으로 가져올게요????
  @PostMapping("/questions/{id}/vote-up")
  public ResponseEntity postQuestionVoteUp(@PathVariable("id") @Positive long questionId) {
    long voteCount = voteService.voteUp(questionId);
    return ResponseEntity.ok().body(voteCount);
  }

  @PostMapping("/questions/{id}/vote-down")
  public ResponseEntity postQuestionVoteDown(@PathVariable("id") @Positive long questionId) {
    long voteCount = voteService.voteDown(questionId);
    return ResponseEntity.ok().body(voteCount);
  }
}
