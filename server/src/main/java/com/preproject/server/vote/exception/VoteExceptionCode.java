package com.preproject.server.vote.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum VoteExceptionCode {

  ALREADY_VOTED(HttpStatus.BAD_REQUEST, "you already voted this content"),

  ;

  private final HttpStatus status;
  private final String message;
}
