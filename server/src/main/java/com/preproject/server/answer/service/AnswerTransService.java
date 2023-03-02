package com.preproject.server.answer.service;

import com.preproject.server.answer.dto.AnswerGetResponseDto;
import com.preproject.server.answer.dto.AnswerPatchDto;
import com.preproject.server.answer.dto.AnswerPostDto;
import com.preproject.server.answer.entity.Answer;
import com.preproject.server.answer.exception.AnswerExceptionCode;
import com.preproject.server.answer.mapper.AnswerMapper;
import com.preproject.server.exception.BusinessLogicException;
import com.preproject.server.member.Service.MemberService;
import com.preproject.server.question.service.QuestionService;
import com.preproject.server.vote.IS_VOTED;
import com.preproject.server.vote.service.VoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class AnswerTransService {

  private final MemberService memberService;
  private final QuestionService questionService;
  private final AnswerMapper answerMapper;
  private final VoteService voteService;

  private LinkedHashMap checkAuthenticated() {
    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication.getPrincipal().equals("anonymousUser")) {
      throw new BusinessLogicException(AnswerExceptionCode.NOT_SIGNED_IN);
    }
    return (LinkedHashMap) authentication.getPrincipal();
  }

  @Transactional
  public Answer answerPostDtoToAnswer(AnswerPostDto answerPostDto) {
    log.info("#### POST ANSWER ####");
    LinkedHashMap principal = checkAuthenticated();
    Answer answer = answerMapper.answerPostDtoToAnswer(answerPostDto);
    answer.setMember(memberService.getMember(Long.valueOf((Integer) principal.get("id"))));
//    answer.setMember(memberService.getMember(1L));
    answer.setQuestion(questionService.findQuestion(answerPostDto.getQuestionId()));
    return answer;
  }

  @Transactional
  public Answer answerPatchDtoToAnswer(AnswerPatchDto answerPatchDto, long questionId,
      long answerId) {
    log.info("#### PATCH ANSWER #####");
    answerPatchDto.setId(answerId);
    return answerMapper.answerPatchDtoToAnswer(answerPatchDto);
  }

  public List<AnswerGetResponseDto> answerListToAnswerGetResponseDtoList(List<Answer> answerList) {
    log.info("#### ANSWER LIST ####");
    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return answerList.stream().map(answer -> {
      AnswerGetResponseDto answerGetResponseDto = answerMapper.answerToAnswerGetResponseDto(answer);
      if (authentication.getPrincipal().equals("anonymousUser")) {
        answerGetResponseDto.setIsVoted(IS_VOTED.NOT_SIGNED_IN);
      } else {
        LinkedHashMap principal = (LinkedHashMap) authentication.getPrincipal();
        IS_VOTED voted = voteService.getVoteStatus(((Integer) principal.get("id")).longValue(),
            answer.getId());
        answerGetResponseDto.setIsVoted(voted);
      }
      return answerGetResponseDto;
    }).collect(Collectors.toList());
  }


}
