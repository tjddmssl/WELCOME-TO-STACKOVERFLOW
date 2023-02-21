package com.preproject.server.Member.exception;

import com.preproject.server.exception.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberExceptionCode implements ExceptionCode {

  MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "Member Not Found"),
  // TODO 추가
  ;

  private final HttpStatus status;
  private final String message;
}
