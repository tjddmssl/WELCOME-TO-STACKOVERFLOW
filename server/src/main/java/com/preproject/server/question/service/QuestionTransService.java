package com.preproject.server.question.service;

import com.preproject.server.Member.Service.MemberService;
import com.preproject.server.question.dto.QuestionPostDto;
import com.preproject.server.question.entity.Question;
import com.preproject.server.question.mapper.QuestionMapper;
import com.preproject.server.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionTransService {

  private final QuestionMapper questionMapper;
  private final QuestionRepository questionRepository;
  private final QuestionService questionService;
  private final MemberService memberService;

  public Question QuestionPostDtoToQuestion(QuestionPostDto questionPostDto) {
    Question question = questionMapper.questionPostDtoToQuestion(questionPostDto);
    question.setMember(memberService.getMember(questionPostDto.getMemberId()));
    return question;
  }


}
