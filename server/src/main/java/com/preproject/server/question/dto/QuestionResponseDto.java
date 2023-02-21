package com.preproject.server.question.dto;

import com.preproject.server.Member.entity.Member;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponseDto {
    private String title;
    private String content;
    private Long viewCount;
    private Long memberId;
    private List<String> tags;
}
