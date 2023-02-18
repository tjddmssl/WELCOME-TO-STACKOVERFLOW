package com.preproject.server.Member.dto;

import com.preproject.server.Member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class ResponseDto<T> {
    private T response;

    public ResponseDto(T response) {
        this.response = response;
    }
}
