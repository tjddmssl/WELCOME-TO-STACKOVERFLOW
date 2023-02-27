package com.preproject.server.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerAllResponseDto {
    private Integer answerCount;
    private List<AnswerGetResponseDto> answerGetResponseDtos;


}
