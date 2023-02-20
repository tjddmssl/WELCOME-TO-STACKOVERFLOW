package com.preproject.server.question.mapper;

import com.preproject.server.question.dto.QuestionDto;
import com.preproject.server.question.entity.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

  Question questionPostDtoToQuestion(QuestionDto.Post questionPost);


}
