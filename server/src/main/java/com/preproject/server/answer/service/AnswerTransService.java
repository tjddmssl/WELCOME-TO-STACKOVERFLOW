package com.preproject.server.answer.service;

import com.preproject.server.answer.dto.AnswerPatchDto;
import com.preproject.server.answer.dto.AnswerPostDto;
import com.preproject.server.answer.entity.Answer;
import com.preproject.server.answer.mapper.AnswerMapper;
import com.preproject.server.member.Service.MemberService;
import com.preproject.server.question.service.QuestionService;
import com.preproject.server.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnswerTransService {

  private final MemberService memberService;
  private final QuestionService questionService;
  private final AnswerMapper answerMapper;

  public Answer answerPostDtoToAnswer(AnswerPostDto answerPostDto) {
    Answer answer = answerMapper.answerPostDtoToAnswer(answerPostDto);
    answer.setMember(memberService.getMember(answerPostDto.getMemberId()));
    answer.setQuestion(questionService.findQuestion(answerPostDto.getQuestionId()));
    return answer;
  }

  public Answer answerPatchDtoToAnswer(AnswerPatchDto answerPatchDto, long questionId,
      long answerId) {
    answerPatchDto.setId(answerId);
    Answer answer = answerMapper.answerPatchDtoToAnswer(answerPatchDto);
    answer.setQuestion(questionService.getQuestion(questionId));
    return answer;
  }
}
