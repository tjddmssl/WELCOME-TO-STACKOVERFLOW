package com.preproject.server.question.mapper;

import com.preproject.server.question.dto.QuestionPostDto;
import com.preproject.server.question.dto.QuestionResponseDto;
import com.preproject.server.question.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionMapper {

  Question questionPostDtoToQuestion(QuestionPostDto questionPost);
// TODO tag enum 상의
  QuestionResponseDto questionToQuestionPostResponseDto(Question question);

}
