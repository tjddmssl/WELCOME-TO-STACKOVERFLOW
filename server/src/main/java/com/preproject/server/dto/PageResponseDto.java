package com.preproject.server.dto;

import lombok.Data;

@Data
public class PageResponseDto <T>{
    T response;
    PageInfo pageInfo;
}
