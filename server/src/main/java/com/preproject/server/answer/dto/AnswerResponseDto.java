package com.preproject.server.answer.dto;

import lombok.*;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerResponseDto {
    private Long answerId;
    private Long questionId;
    private Long memberId;
    private String content;


}
