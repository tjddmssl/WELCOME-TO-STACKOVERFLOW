package com.preproject.server.answer.controller;

import com.preproject.server.answer.dto.AnswerGetResponseDto;
import com.preproject.server.answer.dto.AnswerPatchDto;
import com.preproject.server.answer.dto.AnswerPostDto;
import com.preproject.server.answer.dto.AnswerResponseDto;
import com.preproject.server.answer.entity.Answer;
import com.preproject.server.answer.mapper.AnswerMapper;
import com.preproject.server.answer.service.AnswerService;
import com.preproject.server.answer.service.AnswerTransService;
import com.preproject.server.comment.service.CommentService;
import com.preproject.server.dto.ResponseDto;
import com.preproject.server.utils.UriCreator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Validated
@Slf4j
public class AnswerController {

  private final AnswerService answerService;
  private final AnswerTransService answerTransService;
  private final AnswerMapper mapper;
  private final CommentService commentService;

  @PostMapping("/questions/{question-id}/answers")
  public ResponseEntity postAnswer(@Valid @RequestBody AnswerPostDto answerPostDto,
      @PathVariable("question-id") @Positive Long questionId) {
    answerPostDto.setQuestionId(questionId);
    log.info("## request body: {}", answerPostDto);
    Answer answer = answerService.creatAnswer(
        answerTransService.answerPostDtoToAnswer(answerPostDto));
    URI uri = UriComponentsBuilder.newInstance()
        .path("/questions/{question-id}/answers/{answers-id}")
        .buildAndExpand(questionId, answer.getId()).toUri();
    AnswerResponseDto responseDto = mapper.answerToAnswerResponseDto(answer);
    return ResponseEntity.created(uri).body(new ResponseDto<>(responseDto));

  }

  @PatchMapping("/questions/{question-id}/answers/{answer-id}")
  public ResponseEntity patchAnswer(@Valid @RequestBody AnswerPatchDto answerPatchDto,
      @PathVariable("question-id") @Positive Long questionId,
      @PathVariable("answer-id") @Positive Long answerId) {

    Answer answer = answerService.updateAnswer(
        answerTransService.answerPatchDtoToAnswer(answerPatchDto, questionId, answerId));
    URI uri = UriComponentsBuilder.newInstance()
        .path("/questions/{question-id}/answers/{answers-id}")
        .buildAndExpand(questionId, answer.getId()).toUri();
    AnswerResponseDto responseDto = mapper.answerToAnswerResponseDto(answer);
    return ResponseEntity.ok().header("Location", uri.toString())
        .body(new ResponseDto<>(responseDto));

  }

  @GetMapping("/questions/{question-id}/answers")
  public ResponseEntity getAnswers(@PathVariable("question-id") @Positive Long questionId) {
    List<Answer> answerList = answerService.findAnswersByQuestionId(questionId);
    List<AnswerGetResponseDto> answerGetResponseDtoList
        = answerList.stream().map(mapper::answerToAnswerGetResponseDto)
        .collect(Collectors.toList());
    return new ResponseEntity(answerGetResponseDtoList, HttpStatus.OK);
  }

  @DeleteMapping("/questions/{question-id}/answers/{answer-id}")
  public ResponseEntity deleteAnswer(@PathVariable("question-id") @Positive Long questionId,
      @PathVariable("answer-id") @Positive Long answerId) {
    answerService.removeAnswer(answerId);
    return ResponseEntity.noContent().build();

  }
}
