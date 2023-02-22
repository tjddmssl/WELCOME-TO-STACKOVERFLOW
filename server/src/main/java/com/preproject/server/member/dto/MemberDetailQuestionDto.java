package com.preproject.server.member.dto;

import com.preproject.server.question.entity.Question;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MemberDetailQuestionDto {
    private Long id;
    private String title;
    private List<String> tags;
    private long votedCount;
    private long viewCount;
    private LocalDateTime createdDate;

    public MemberDetailQuestionDto(Question question) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.tags = question.getTagQuestions().stream().map(tag -> tag.getTag().getName()).collect(Collectors.toList());
        this.votedCount = question.getVoteCount();
        this.viewCount = question.getViewCount();
        this.createdDate = question.getCreatedDate();
    }
}
