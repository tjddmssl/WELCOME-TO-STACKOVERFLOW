package com.preproject.server.answer.mapper;

import com.preproject.server.answer.dto.AnswerGetResponseDto;
import com.preproject.server.answer.dto.AnswerPatchDto;
import com.preproject.server.answer.dto.AnswerPostDto;
import com.preproject.server.answer.dto.AnswerResponseDto;
import com.preproject.server.answer.entity.Answer;
import com.preproject.server.comment.dto.CommentSimpleDto;
import com.preproject.server.comment.service.CommentService;
import com.preproject.server.member.dto.MemberSimpleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnswerMapper {

    Answer answerPostDtoToAnswer(AnswerPostDto answerPostDto);
    Answer answerPatchDtoToAnswer(AnswerPatchDto answerPatchDto);

    default AnswerResponseDto answerToAnswerResponseDto(Answer answer){
        return AnswerResponseDto.builder()
                .answerId(answer.getId())
                .memberId(answer.getMember().getId())
                .questionId(answer.getQuestion().getId())
                .createdDate(answer.getCreatedDate())
                .lastModifiedDate(answer.getLastModifiedDate())
                .build();
    }

    @Mapping(source = "answer.question.id", target = "questionId")
    AnswerGetResponseDto answerToAnswerGetResponseDto(Answer answer);
//    {
//        return AnswerGetResponseDto.builder()
//                .content(answer.getContent())
//                .voteCount(answer.getVoteCount())
//                .member(new MemberSimpleDto(
//                        answer.getMember().getId(),
//                        answer.getMember().getDisplayName(),
//                        answer.getMember().getProfile()
//                ))
//
//                .build();
//
//    }

}
