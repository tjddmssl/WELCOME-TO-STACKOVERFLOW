package com.preproject.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageResponseDto <T>{
    T response;
    PageInfo pageInfo;
}
