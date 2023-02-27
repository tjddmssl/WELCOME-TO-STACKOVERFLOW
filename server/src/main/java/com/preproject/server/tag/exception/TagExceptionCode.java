package com.preproject.server.tag.exception;

import com.preproject.server.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TagExceptionCode implements ExceptionCode {

  TAG_NOT_FOUND(HttpStatus.NOT_FOUND, "Tag Not Found"),
  TAG_ALREADY_ADDED(HttpStatus.BAD_REQUEST, "Tag is already added")
  // TODO 추가
  ;

  private final HttpStatus status;
  private final String message;
}
