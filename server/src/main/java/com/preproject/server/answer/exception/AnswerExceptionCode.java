package com.preproject.server.answer.exception;

import com.preproject.server.exception.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AnswerExceptionCode implements ExceptionCode {

    ANSWER_NOT_FOUND(HttpStatus.NOT_FOUND, "Answer Not Found");

    private final HttpStatus status;
    private final String message;

}
