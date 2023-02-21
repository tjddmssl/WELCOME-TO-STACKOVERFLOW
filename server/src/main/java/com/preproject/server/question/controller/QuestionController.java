package com.preproject.server.question.controller;

import com.preproject.server.Member.entity.Member;
import com.preproject.server.Member.repository.MemberRepository;
import com.preproject.server.question.dto.QuestionPatchDto;
import com.preproject.server.question.dto.QuestionPostDto;
import com.preproject.server.question.dto.QuestionResponseDto;
import com.preproject.server.question.entity.Question;
import com.preproject.server.question.mapper.QuestionMapper;
import com.preproject.server.question.repository.QuestionRepository;
import com.preproject.server.question.service.QuestionService;
import com.preproject.server.question.service.QuestionTransService;
import java.net.URI;
import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Validated
public class QuestionController {

  private final QuestionService questionService;
  private final QuestionTransService questionTransService;
  private final QuestionRepository repository;
  private final MemberRepository memberRepository;

  @PostMapping("/questions")
  public ResponseEntity postQuestion(@Valid @RequestBody QuestionPostDto requestBody) {
    Question question = questionService.createQuestion(questionTransService.QuestionPostDtoToQuestion(requestBody));
    URI uri = UriComponentsBuilder.newInstance().path("/questions" + "/{resource-id}")
        .buildAndExpand(question.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @PatchMapping("/questions/{id}")
  public ResponseEntity patchQuestion(@RequestBody QuestionPatchDto requestBody, @PathVariable("id") @Positive long id){
    URI uri = UriComponentsBuilder.newInstance().path("/questions" + "/{resource-id}")
        .buildAndExpand(id).toUri();
    return ResponseEntity.ok(uri);
  }

  @GetMapping("/questions/{id}")
  public ResponseEntity getQuestion(@PathVariable("id") @Positive long id){
    return new ResponseEntity(HttpStatus.OK);
  }

  @DeleteMapping("/questions/{id}")
  public ResponseEntity deleteQuestion(@PathVariable("id") @Positive long id){
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }



}
