package com.preproject.server.dto;

import lombok.Data;

@Data
public class PageInfo {
    private int size;
    private int page;
    private int totalElement;
    private int totalPage;
    private boolean first;
    private boolean last;
}
