package com.preproject.server.question.controller;

import com.preproject.server.dto.ResponseDto;
import com.preproject.server.question.dto.QuestionPatchDto;
import com.preproject.server.question.dto.QuestionPostDto;
import com.preproject.server.question.dto.QuestionResponseDto;
import com.preproject.server.question.entity.Question;
import com.preproject.server.question.mapper.QuestionMapper;
import com.preproject.server.question.service.QuestionService;
import com.preproject.server.question.service.QuestionTransService;
import com.preproject.server.utils.UriCreator;
import java.net.URI;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Validated
@Slf4j
public class QuestionController {

  private final String DEFAULT_URI = "/questions";
  private final QuestionService questionService;
  private final QuestionTransService questionTransService;
  private final QuestionMapper questionMapper;

  @PostMapping("/questions")
  public ResponseEntity postQuestion(@Valid @RequestBody QuestionPostDto requestBody) {
    log.info("## request body: {}", requestBody);
    Question question = questionService.createQuestion(
        questionTransService.QuestionPostDtoToQuestion(requestBody));
    URI uri = UriCreator.createUri(DEFAULT_URI, question.getId());
    QuestionResponseDto responseDto = questionMapper.questionToQuestionResponseDto(question);
    return ResponseEntity.created(uri).body(new ResponseDto<>(responseDto));
  }

  @PatchMapping("/questions/{id}")
  public ResponseEntity patchQuestion(@RequestBody QuestionPatchDto requestBody,
      @PathVariable("id") @Positive long id) {
    Question question = questionService.updateQuestion(questionTransService.QuestionPatchDtoToQuestion(id, requestBody));
    URI uri = UriCreator.createUri(DEFAULT_URI, id);
    QuestionResponseDto responseDto = questionMapper.questionToQuestionResponseDto(question);
    return ResponseEntity.ok().header("Location", uri.toString()).body(new ResponseDto<>(responseDto));
  }

  @GetMapping("/questions/{id}")
  public ResponseEntity getQuestion(@PathVariable("id") @Positive long id) {
    return new ResponseEntity(HttpStatus.OK);
  }

  @DeleteMapping("/questions/{id}")
  public ResponseEntity deleteQuestion(@PathVariable("id") @Positive long id) {
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }


}
