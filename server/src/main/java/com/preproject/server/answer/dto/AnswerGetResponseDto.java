package com.preproject.server.answer.dto;

import com.preproject.server.comment.dto.CommentSimpleDto;
import com.preproject.server.member.dto.MemberSimpleDto;
import lombok.*;

import java.time.LocalDateTime;
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerGetResponseDto {

    private Long answerId;
    private Long questionId;
    private Long voteCount;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private MemberSimpleDto memberSimpleDto;
    private CommentSimpleDto commentSimpleDto;
}
