package com.preproject.server.comment.exception;


import com.preproject.server.exception.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommentExceptionCode implements ExceptionCode {

  NOT_SIGNED_IN(HttpStatus.UNAUTHORIZED, "you must sign in to write a comment."),
  ;

  private final HttpStatus status;
  private final String message;
}
