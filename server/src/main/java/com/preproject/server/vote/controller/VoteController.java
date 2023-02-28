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

  @PostMapping("/questions/{id}/vote-up")
  public ResponseEntity postQuestionVoteUp(@PathVariable("id") @Positive long questionId) {
    return ResponseEntity.ok().body(voteService.questionVoteUp(questionId));
  }

  @PostMapping("/questions/{id}/vote-down")
  public ResponseEntity postQuestionVoteDown(@PathVariable("id") @Positive long questionId) {
    return ResponseEntity.ok().body(voteService.questionVoteDown(questionId));
  }

  @PostMapping("/questions/{question-id}/answers/{answer-id}/vote-up")
  public ResponseEntity postAnswerVoteUp(@PathVariable("question-id") @Positive long questionId,
      @PathVariable("answer-id") @Positive Long answerId) {
    return ResponseEntity.ok().body(voteService.answerVoteUp(questionId, answerId));
  }

  @PostMapping("/questions/{question-id}/answers/{answer-id}/vote-down")
  public ResponseEntity postAnswerVoteDown(@PathVariable("question-id") @Positive long questionId,
      @PathVariable("answer-id") @Positive Long answerId) {
    return ResponseEntity.ok().body(voteService.answerVoteDown(questionId, answerId));
  }

}
