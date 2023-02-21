package com.preproject.server.tag.exception;

import com.preproject.server.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum TagExceptionCode implements ExceptionCode {

  TAG_NOT_FOUND(HttpStatus.NOT_FOUND, "Tag Not Found"),
  // TODO 추가
  ;

  private HttpStatus status;
  private String message;
}
