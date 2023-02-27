package com.preproject.server.vote.exception;

import com.preproject.server.exception.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum VoteExceptionCode implements ExceptionCode {

  ALREADY_VOTED(HttpStatus.BAD_REQUEST, "you already voted this content."),
  NOT_SIGNED_IN(HttpStatus.FORBIDDEN, "you have to sign in to vote."),
  TOO_EARLY_VOTE(HttpStatus.TOO_EARLY,"A few minutes must pass after signing up to vote")
  ;

  private final HttpStatus status;
  private final String message;
}
