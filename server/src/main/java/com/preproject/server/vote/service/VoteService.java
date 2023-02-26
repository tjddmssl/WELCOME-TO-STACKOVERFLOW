package com.preproject.server.vote.service;

import com.preproject.server.answer.entity.Answer;
import com.preproject.server.answer.service.AnswerService;
import com.preproject.server.member.Service.MemberService;
import com.preproject.server.question.entity.Question;
import com.preproject.server.question.service.QuestionService;
import com.preproject.server.vote.entity.Vote;
import com.preproject.server.vote.entity.Vote.status;
import com.preproject.server.vote.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class VoteService {

  private final VoteRepository voteRepository;
  private final MemberService memberService;
  private final QuestionService questionService;
  private final AnswerService answerService;

  public Page<Question> createQuestionSimplePage(Pageable pageable, Long id) {
    return voteRepository.findSimpleQuestion(pageable, id);
  }

  public Page<Answer> createAnswerSimplePage(Pageable pageable, Long id) {
    return voteRepository.findSimpleAnswer(pageable, id);
  }

  public long questionVoteUp(long questionId, long memberId) {
    Vote vote = Vote.builder().member(memberService.getMember(memberId))
        .question(questionService.findQuestion(questionId)).status(
            status.VOTE_PLUS).build();
    voteRepository.save(vote);
    return questionService.addQuestionVoteCount(vote.getQuestion(), vote.getStatus());
  }

  public long questionVoteDown(long questionId, long memberId) {
    Vote vote = Vote.builder().member(memberService.getMember(memberId))
        .question(questionService.findQuestion(questionId)).status(
            status.VOTE_MINUS).build();
    voteRepository.save(vote);
    return questionService.addQuestionVoteCount(vote.getQuestion(), vote.getStatus());
  }

  public long answerVoteUp(long answerId, long memberId) {
    Vote vote = Vote.builder().member(memberService.getMember(memberId))
            .answer(answerService.findAnswer(answerId)).status(
                    status.VOTE_PLUS).build();
    voteRepository.save(vote);
    return answerService.addAnswerVoteCount(vote.getAnswer(), vote.getStatus());
  }

  public long answerVoteDown(long answerId, long memberId) {
    Vote vote = Vote.builder().member(memberService.getMember(memberId))
            .answer(answerService.findAnswer(answerId)).status(
                    status.VOTE_MINUS).build();
    voteRepository.save(vote);
    return answerService.addAnswerVoteCount(vote.getAnswer(), vote.getStatus());
  }
}
