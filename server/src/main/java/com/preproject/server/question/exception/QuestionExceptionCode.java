package com.preproject.server.question.exception;

import com.preproject.server.exception.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum QuestionExceptionCode implements ExceptionCode {

  QUESTION_NOT_FOUND(HttpStatus.NOT_FOUND, "Question Not Found"),
  UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "you can't edit this question."),
  NOT_SIGNED_IN(HttpStatus.FORBIDDEN, "you must to sign in.")
  // TODO
  ;

  private final HttpStatus status;
  private final String message;
}
