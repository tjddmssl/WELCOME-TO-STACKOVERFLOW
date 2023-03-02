package com.preproject.server.question.controller;

import com.preproject.server.dto.ResponseDto;
import com.preproject.server.question.dao.RelatedQuestionDao;
import com.preproject.server.question.dto.*;
import com.preproject.server.question.entity.Question;
import com.preproject.server.question.mapper.QuestionMapper;
import com.preproject.server.question.service.QuestionService;
import com.preproject.server.question.service.QuestionTransService;
import com.preproject.server.utils.UriCreator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

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
        questionTransService.questionPostDtoToQuestion(requestBody));
    URI uri = UriCreator.createUri(DEFAULT_URI, question.getId());
    QuestionResponseDto responseDto = questionMapper.questionToQuestionResponseDto(question);
    return ResponseEntity.created(uri).body(new ResponseDto<>(responseDto));
  }

  @PatchMapping("/questions/{id}")
  public ResponseEntity patchQuestion(@RequestBody QuestionPatchDto requestBody,
      @PathVariable("id") @Positive long id) {
    log.info("#### PATCH QUESTION#### ");
    log.info("#### requestBody = {}", requestBody.toString());
    Question question = questionService.updateQuestion(
        questionTransService.questionPatchDtoToQuestion(id, requestBody));
    URI uri = UriCreator.createUri(DEFAULT_URI, id);
    QuestionResponseDto responseDto = questionMapper.questionToQuestionResponseDto(question);
    return ResponseEntity.ok().header("Location", uri.toString())
        .body(new ResponseDto<>(responseDto));
  }

  @GetMapping("/questions/{id}")
  public ResponseEntity getQuestion(@PathVariable("id") @Positive long id) {
    log.info("#### getQeustions#### ");
    Question question = questionService.findQuestion(id);
    QuestionGetDto questionGetDto = questionTransService.questionToQuestionGetDto(question);
    return ResponseEntity.ok().body(new ResponseDto<>(questionGetDto));
  }

  @GetMapping("/questions")
  public ResponseEntity getQuestions(
      @PageableDefault(sort = "createdDate") Pageable pageable) {
    log.info("### GET PAGE ####");
    Page<Question> questionPage = questionService.findQuestions(pageable);
    Page<QuestionListGetDto> questionListGetDtoPage = questionTransService.questionToQuestionListGetDto(
        questionPage);
    return ResponseEntity.ok().body(new ResponseDto<>(questionListGetDtoPage));
  }

  @GetMapping("/questions/{id}/related-questions")
  public ResponseEntity getRelatedQuestions(@PathVariable("id") @Positive long questionId) {
    List<RelatedQuestionDao> questionDaoList = questionService.findRelatedQuestions(questionId);
    return ResponseEntity.ok().body(new ResponseDto<>(questionDaoList));
  }

  @GetMapping("/questions/tagged/{tag-name}")
  public ResponseEntity getQuestionsByTagName(@PathVariable("tag-name") String tagName,
      @PageableDefault(sort = "createdDate") Pageable pageable) {
    Page<Question> questionPage = questionService.findQuestionsByTagName(tagName, pageable);
    Page<QuestionListGetDto> questionListGetDtoPage = questionTransService.questionToQuestionListGetDto(
        questionPage);
    return ResponseEntity.ok().body(questionListGetDtoPage);
  }

  @GetMapping("/users/{id}/voted-questions")
  public ResponseEntity getVotedQuestions(@PathVariable("id") @Positive long memberId,
      @PageableDefault(sort = "createdDate") Pageable pageable) {
    Page<Question> questionPage = questionService.findVotedQuestions(pageable, memberId);
    Page<VotedQuestionDto> votedQuestionDtoPage = questionTransService.questionPageToVotedQuestionDto(
        questionPage);
    return ResponseEntity.ok().body(votedQuestionDtoPage);
  }

  @GetMapping("/users/{id}/questions")
  public ResponseEntity getQuestionsByUserId(@PathVariable("id") @Positive long memberId,
      @PageableDefault(sort = "createdDate") Pageable pageable) {
    Page<Question> questionPage = questionService.findQuestionsByUser(pageable, memberId);
    Page<MemberQuestionDto> memberQuestionDtoPage = questionTransService.questionPageToMemberQuestionDto(
        questionPage);
    return ResponseEntity.ok().body(memberQuestionDtoPage);
  }

  @GetMapping("/questions/search")
  public ResponseEntity getQuestionByKeyword(
      @RequestParam(value = "keyword", defaultValue = "") String keyword,
      @PageableDefault(sort = "createdDate") Pageable pageable) {
    Page<Question> questionPage = questionService.getSearchedPage(keyword, pageable);
    Page<QuestionListGetDto> questionListGetDtoPage = questionTransService.questionToQuestionListGetDto(questionPage);
    return ResponseEntity.ok().body(questionListGetDtoPage);
  }

  @DeleteMapping("/questions/{id}")
  public ResponseEntity deleteQuestion(@PathVariable("id") @Positive long id) {
    questionService.removeQuestion(id);
    return ResponseEntity.noContent().build();
  }

}
