package com.preproject.server.tag.controller;

import com.preproject.server.answer.repository.AnswerRepository;
import com.preproject.server.comment.repository.CommentRepository;
import com.preproject.server.dto.ResponseDto;
import com.preproject.server.member.repository.MemberRepository;
import com.preproject.server.question.repository.QuestionRepository;
import com.preproject.server.tag.dto.TagGetDto;
import com.preproject.server.tag.repository.TagRepository;
import com.preproject.server.tag.service.TagService;
import com.preproject.server.tag.service.TagTransService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class TagController {

  private final TagRepository tagRepository;
  private final MemberRepository memberRepository;
  private final QuestionRepository questionRepository;
  private final CommentRepository commentRepository;
  private final AnswerRepository answerRepository;


  private final TagTransService tagTransService;
  private final TagService tagService;

  @GetMapping("/tags")
  public ResponseEntity getTags(
      @PageableDefault(size = 28, sort = "id") Pageable pageable) {
    Page<TagGetDto> page = tagTransService.tagPageToTagGetDtoPage(tagService.findTags(pageable));
    return ResponseEntity.ok().body(new ResponseDto<>(page));
  }
}
