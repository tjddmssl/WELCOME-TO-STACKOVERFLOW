package com.preproject.server.question.exception;

import com.preproject.server.exception.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum QuestionExceptionCode implements ExceptionCode {

  QUESTION_NOT_FOUND(HttpStatus.NOT_FOUND, "Question Not Found"),
  // TODO 추가
  ;

  private final HttpStatus status;
  private final String message;
}
