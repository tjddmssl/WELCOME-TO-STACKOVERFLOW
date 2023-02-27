package com.preproject.server.vote.controller;

import com.preproject.server.vote.service.VoteService;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@Validated
public class VoteController {

  private final VoteService voteService;

  @PostMapping("/questions/{id}/vote-up")
  public ResponseEntity postQuestionVoteUp(@PathVariable("id") @Positive long questionId) {
    long voteCount = voteService.questionVoteUp(questionId);
    return ResponseEntity.ok().body(voteCount);
  }

  @PostMapping("/questions/{id}/vote-down")
  public ResponseEntity postQuestionVoteDown(@PathVariable("id") @Positive long questionId) {
    long voteCount = voteService.questionVoteDown(questionId);
    return ResponseEntity.ok().body(voteCount);
  }

  @PostMapping("/questions/{question-id}/answers/{answer-id}/vote_up")
  public ResponseEntity postAnswerVoteUp(@PathVariable("question-id") @Positive long questionId,
      @PathVariable("answer-id") @Positive Long answerId) {
    long voteCount = voteService.answerVoteUp(questionId, answerId);
    return ResponseEntity.ok().body(voteCount);
  }

  @PostMapping("/questions/{question-id}/answers/{answer-id}/vote_down")
  public ResponseEntity postAnswerVoteDown(@PathVariable("question-id") @Positive long questionId,
      @PathVariable("answer-id") @Positive Long answerId) {
    long voteCount = voteService.answerVoteDown(questionId, answerId);
    return ResponseEntity.ok().body(voteCount);
  }

}
