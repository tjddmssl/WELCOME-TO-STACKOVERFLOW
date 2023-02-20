package com.preproject.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageInfo {
    private int size;
    private int page;
    private long totalElement;
    private int totalPage;
    private boolean first;
    private boolean last;
}
