package com.preproject.server.question.controller;

import com.preproject.server.question.dto.QuestionDto;
import com.preproject.server.question.mapper.QuestionMapper;
import com.preproject.server.question.service.QuestionService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class QuestionController {

  private final QuestionService questionService;
  private final QuestionMapper mapper;

  public QuestionController(QuestionService questionService, QuestionMapper mapper) {
    this.questionService = questionService;
    this.mapper = mapper;
  }

  @PostMapping("/questions")
  public ResponseEntity postQuestion(@Valid @RequestBody QuestionDto.Post requestBody){
    questionService.createQuestion(mapper.questionPostDtoToQuestion(requestBody));
    return new ResponseEntity(HttpStatus.CREATED);
  }
}
