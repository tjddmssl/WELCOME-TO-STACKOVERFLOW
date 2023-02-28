package com.preproject.server.answer.mapper;

import com.preproject.server.answer.dto.AnswerGetResponseDto;
import com.preproject.server.answer.dto.AnswerPatchDto;
import com.preproject.server.answer.dto.AnswerPostDto;
import com.preproject.server.answer.dto.AnswerResponseDto;
import com.preproject.server.answer.entity.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnswerMapper {

  Answer answerPostDtoToAnswer(AnswerPostDto answerPostDto);

  Answer answerPatchDtoToAnswer(AnswerPatchDto answerPatchDto);

  @Mapping(target = "questionId", source = "question.id")
  @Mapping(target = "memberId", source = "member.id")
  @Mapping(target = "answerId", source = "id")
  AnswerResponseDto answerToAnswerResponseDto(Answer answer);

  @Mapping(source = "question.id", target = "questionId")
  AnswerGetResponseDto answerToAnswerGetResponseDto(Answer answer);

}
