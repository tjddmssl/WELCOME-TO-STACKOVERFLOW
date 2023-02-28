package com.preproject.server.answer.dto;

import lombok.Data;

import java.util.List;
@Data
public class AnswerPatchDto {
    private long id;
    private Long questionId;
    private String content;
    private Long memberId;
}
