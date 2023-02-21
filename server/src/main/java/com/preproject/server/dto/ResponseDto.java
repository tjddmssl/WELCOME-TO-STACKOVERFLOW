package com.preproject.server.dto;

import lombok.Data;

@Data
public class ResponseDto<T> {
    private T response;

    public ResponseDto(T response) {
        this.response = response;
    }
}
