package com.preproject.server.member.exception;

import com.preproject.server.exception.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberExceptionCode implements ExceptionCode {

  MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "Member Not Found"),
  MEMBER_EXIST(HttpStatus.CONFLICT, "Member is already Exist!"),
  MEMBER_JWT_EXIST(HttpStatus.CONFLICT, "JWT Registry Member is Exist")
  // TODO 추가
  ;

  private final HttpStatus status;
  private final String message;
}
