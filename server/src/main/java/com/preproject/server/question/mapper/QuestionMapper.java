package com.preproject.server.question.mapper;

import com.preproject.server.question.dto.QuestionPatchDto;
import com.preproject.server.question.dto.QuestionPostDto;
import com.preproject.server.question.dto.QuestionResponseDto;
import com.preproject.server.question.entity.Question;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionMapper {

  Question questionPostDtoToQuestion(QuestionPostDto questionPost);

  Question questionPatchDtoToQuestion(QuestionPatchDto questionPatchDto);

  default QuestionResponseDto questionToQuestionResponseDto(Question question) {
    List<String> names = question.getTagQuestions().stream()
        .map(tagQuestion -> tagQuestion.getTag().getName()).collect(Collectors.toList());
    return QuestionResponseDto.builder()
        .title(question.getTitle())
        .content(question.getContent())
        .memberId(question.getMember().getId())
        .tags(names)
        .build();
  }


}
