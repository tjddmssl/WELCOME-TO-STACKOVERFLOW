package com.preproject.server.answer.controller;

import com.preproject.server.answer.dto.AnswerGetResponseDto;
import com.preproject.server.answer.dto.AnswerPatchDto;
import com.preproject.server.answer.dto.AnswerPostDto;
import com.preproject.server.answer.dto.AnswerResponseDto;
import com.preproject.server.answer.entity.Answer;
import com.preproject.server.answer.mapper.AnswerMapper;
import com.preproject.server.answer.service.AnswerService;
import com.preproject.server.answer.service.AnswerTransService;
import com.preproject.server.dto.ResponseDto;
import com.preproject.server.member.dto.MemberDetailAnswerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Validated
@Slf4j
public class AnswerController {

  private final AnswerService answerService;
  private final AnswerTransService answerTransService;
  private final AnswerMapper mapper;

  @PostMapping("/questions/{question-id}/answers")
  public ResponseEntity postAnswer(@Valid @RequestBody AnswerPostDto answerPostDto,
      @PathVariable("question-id") @Positive Long questionId) {
    answerPostDto.setQuestionId(questionId);
    log.info("## POST ANSWER ##");
    log.info("## request body content: {}", answerPostDto.getContent());
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
    log.info("## PATCH ANSWER ##");
    log.info("## request body content: {}", answerPatchDto);

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
    log.info("## GET ANSWER ##");
    List<Answer> answerList = answerService.findAnswersByQuestionId(questionId);
    List<AnswerGetResponseDto> answerGetResponseDtoList
        = answerTransService.answerListToAnswerGetResponseDtoList(answerList);
    log.info("## answer : {}", answerGetResponseDtoList.get(0));
    return ResponseEntity.ok().body(new ResponseDto<>(answerGetResponseDtoList));
  }

  @DeleteMapping("/questions/{question-id}/answers/{answer-id}")
  public ResponseEntity deleteAnswer(@PathVariable("question-id") @Positive Long questionId,
      @PathVariable("answer-id") @Positive Long answerId) {
    log.info("## DELETE ANSWER ##");
    answerService.removeAnswer(answerId);
    return ResponseEntity.noContent().build();

  }

  @GetMapping("/users/{id}/answers")
  public ResponseEntity getMemberDetailAnswer(@PathVariable Long id,
      @PageableDefault(sort = "createdDate") Pageable pageable) {
    log.info("## MEMBER DETAIL ANSWER ##");
    Page<Answer> answerPage = answerService.getAnswersByUser(pageable, id);
    Page<MemberDetailAnswerDto> result = answerPage.map(MemberDetailAnswerDto::new);
    log.info("## result: {}", result.getContent().get(0).toString());
    return new ResponseEntity(new ResponseDto<>(result), HttpStatus.OK);

  }

  @GetMapping("/users/{id}/voted-answers")
  public ResponseEntity getMemberVotedAnswer(@PathVariable Long id,
      @PageableDefault(sort = "createdDate") Pageable pageable) {

    log.info("## get member voted answer ##");
    Page<Answer> answerPage = answerService.getVotedAnswers(pageable, id);
    Page<MemberDetailAnswerDto> result = answerPage.map(MemberDetailAnswerDto::new);
    log.info("## result: {}",  result.getContent().get(0).toString());
    return new ResponseEntity(new ResponseDto<>(result), HttpStatus.OK);

  }
}
