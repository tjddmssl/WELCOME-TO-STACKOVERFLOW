package com.preproject.server.dto;

import lombok.Data;

@Data
public class PageParam {
    int page;
    int size;
    String properties;
}
