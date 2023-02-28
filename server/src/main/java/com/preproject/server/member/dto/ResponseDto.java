package com.preproject.server.member.dto;

import lombok.Data;

@Data
public class ResponseDto<T> {

  private T response;

  public ResponseDto(T response) {
    this.response = response;
  }
}
