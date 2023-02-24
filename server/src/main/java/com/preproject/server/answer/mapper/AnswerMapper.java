package com.preproject.server.answer.mapper;

import com.preproject.server.answer.dto.AnswerGetResponseDto;
import com.preproject.server.answer.dto.AnswerPatchDto;
import com.preproject.server.answer.dto.AnswerPostDto;
import com.preproject.server.answer.dto.AnswerResponseDto;
import com.preproject.server.answer.entity.Answer;
import com.preproject.server.member.dto.MemberSimpleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

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
    @Mapping(source = "id", target = "answerId")
    @Mapping(source = "question.id", target = "questionId")
    AnswerGetResponseDto answerToAnswerGetResponseDto(Answer answer) ;
//    {
//        return AnswerGetResponseDto.builder()
//                .answerId(answer.getId())
//                .content(answer.getContent())
//                .voteCount(answer.getVoteCount())
//                .questionId(answer.getQuestion().getId()).build();
//    }

}
